package com.tvseries.servlets;

import com.tvseries.dao.UserDAO;
import com.tvseries.utils.RandomString;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;


public class FileUploadServlet extends HttpServlet
{
    public String generateFileName(String orig_name)
    {
        String file_name = "";
        int index = orig_name.lastIndexOf(".");
        String file_extension = index >= 0 ? orig_name.substring(index) : "";
        String hashname = DigestUtils.sha256Hex((orig_name + RandomString.generateString(10)).getBytes());
        file_name = hashname + file_extension;

        return file_name;
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException
    {
        PrintWriter out = res.getWriter(); //

        File file;
        int max_file_size = 50 * 1024 * 1024;
        int max_mem_size = 50 * 1024 * 1024;
        ServletContext context = getServletContext();
        String profile_pictures_path = context.getInitParameter("profile_pictures_path");
        String temp_path = context.getInitParameter("temp_path");
        String relative_path = context.getInitParameter("profile_pictures_relative");
        String content_type = req.getContentType();

        if(content_type.indexOf("multipart/form-data") >= 0)
        {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(max_mem_size);
            factory.setRepository(new File(temp_path));

            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(max_file_size);

            try
            {
                List<FileItem> file_items = upload.parseRequest(req);

                for(FileItem fi : file_items)
                {
                    if(!fi.isFormField()) //if fi is an uploaded file
                    {
                        String field_name = fi.getFieldName();
                        String file_name = fi.getName();
                        String hash_name = generateFileName(file_name);
                        String file_path = temp_path; //set the path to the temp directory

                        if(field_name.equals("profile_picture")) //set the new path
                        {
                            file_path = profile_pictures_path;

                            HttpSession session = req.getSession();
                            String username = (String)session.getAttribute("username");

                            if(username != null)
                            {
                                //delete previous profile picture
                                String old_relative_path = (String)UserDAO.getAttribute(username, "picture_path");
                                if(old_relative_path != null)
                                {
                                    old_relative_path = old_relative_path.substring(old_relative_path.lastIndexOf("\\"));
                                    File temp_file = new File(profile_pictures_path + old_relative_path);

                                    if(temp_file.exists())
                                    {
                                        boolean deleted = temp_file.delete();
                                    }
                                }

                                //update database
                                UserDAO.setString(username, "picture_path", relative_path + hash_name);
                            }
                        }

                        file = new File(file_path + hash_name);

                        fi.write(file);
                        out.println("Uploaded: " + file_path + file_name);
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

    }
}
