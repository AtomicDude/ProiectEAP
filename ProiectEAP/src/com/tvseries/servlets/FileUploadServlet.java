package com.tvseries.servlets;

import com.tvseries.dao.UserInfoDAO;
import com.tvseries.utils.RandomString;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.time.LocalDate;
import java.util.List;


public class FileUploadServlet extends HttpServlet
{

    private int max_file_size;
    private int max_mem_size;
    private ServletContext context;
    private String temp_path;

    private String profile_pictures_path;
    private String profile_pictures_relative;

    private String posters_path;
    private String posters_relative;

    public void init() throws ServletException
    {
        this.max_file_size = 50 * 1024 * 1024;
        this.max_mem_size = 50 * 1024 * 1024;

        this.context = getServletContext();
        this. temp_path = context.getInitParameter("temp_path");

        this.profile_pictures_path = context.getInitParameter("profile_pictures_path");
        this.profile_pictures_relative = context.getInitParameter("profile_pictures_relative");

        this.posters_path = context.getInitParameter("posters_path");
        this.posters_relative = context.getInitParameter("posters_relative");
    }

    public String generateFileName(String orig_name)
    {
        String file_name = "";
        int index = orig_name.lastIndexOf(".");
        String file_extension = index >= 0 ? orig_name.substring(index) : "";
        String hashname = DigestUtils.sha256Hex((orig_name + RandomString.generateString(10)).getBytes());
        file_name = hashname + file_extension;

        return file_name;
    }

    public boolean processProfilePictures(HttpServletRequest req, HttpServletResponse res, List<FileItem> file_items) throws Exception
    {
        File file = null;

        for(FileItem fi : file_items)
        {
            if(!fi.isFormField()) //if fi is an uploaded file
            {
                String file_name = fi.getName();
                String hash_name = generateFileName(file_name);

                HttpSession session = req.getSession();
                String username = (String)session.getAttribute("username");

                if(username != null)
                {
                    //delete previous profile picture
                    String old_relative_path = (String)UserInfoDAO.getUserAttribute(username, "picture_path");

                    if(old_relative_path != null)
                    {
                        old_relative_path = old_relative_path.substring(old_relative_path.lastIndexOf("\\"));
                        File temp_file = new File(this.profile_pictures_path + old_relative_path);

                        if(temp_file.exists())
                        {
                            boolean deleted = temp_file.delete();
                        }
                    }

                    //update database
                    boolean updated =  UserInfoDAO.setUserAttribute(username, "picture_path", profile_pictures_relative + hash_name);

                    if(!updated)
                    {
                        return false;
                    }

                    req.getSession().setAttribute("picture_path", profile_pictures_relative + hash_name);
                }


                file = new File(profile_pictures_path + hash_name);

                fi.write(file);

                return file.exists();
            }
        }

        return false;
    }

    public void processSeasons(HttpServletRequest req, HttpServletResponse res, List<FileItem> file_items) throws Exception
    {
        File file = null;

        String title = null;
        LocalDate release_date = null;
        LocalDate end_date = null;
        Integer season_no = null;
        String season_poster = null;
        String prequel_title = null;
        String sequel_title = null;
        String series_title = null;

        for(FileItem fi : file_items)
        {
            if (!fi.isFormField()) //if fi is an uploaded file
            {
                String file_name = fi.getName();
                String hash_name = generateFileName(file_name);

                season_poster = posters_relative + hash_name;

                file = new File(posters_path + hash_name);

                fi.write(file);

                if(!file.exists())
                {
                    System.out.println("File could not be uploaded!");
                }
            }
            else //fi is a field
            {
                String field = fi.getFieldName();
                String value = fi.getString();

                if(field == null)
                {
                    //do nothing
                }
                else if(field.equals("title"))
                {
                    title = value;
                }
                else if(field.equals("release_date"))
                {
                    if(value != null && !value.equals(""))
                    {
                        release_date = LocalDate.parse(value);
                    }
                }
                else if(field.equals("end_date"))
                {
                    if(value != null && !value.equals(""))
                    {
                        end_date = LocalDate.parse(value);
                    }
                }
                else if(field.equals("season_no"))
                {
                    season_no = Integer.parseInt(value);
                }
                else if(field.equals("prequel_title"))
                {
                    prequel_title = value;
                }
                else if(field.equals("sequel_title"))
                {
                    sequel_title = value;
                }
                else if(field.equals("series_title"))
                {
                    series_title = value;
                }
            }
        }

        Integer prequel_id = null;
        Integer sequel_id = null;
        Integer series_id = null;

        /*
        if(prequel_title != null)
        {
            prequel_id = (Integer)SeasonDAO.getAttribute(prequel_title, "season_id");
        }

        if(sequel_title != null)
        {
            sequel_id = (Integer)SeasonDAO.getAttribute(sequel_title, "season_id");
        }

        if(series_title != null)
        {
            series_id = (Integer)SeriesDAO.getAttribute(series_title, "series_id");
        }


        SeasonDAO.addSeason(title, release_date, end_date, season_no, poster_path, prequel_id, sequel_id, series_id);

         */
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
    {
        String content_type = req.getContentType();
        String servlet_path = req.getServletPath();

        System.out.println(servlet_path);
        if(content_type.contains("multipart/form-data"))
        {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(this.max_mem_size);
            factory.setRepository(new File(this.temp_path));

            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(this.max_file_size);

            try
            {
                List<FileItem> file_items = upload.parseRequest(req);

                if (servlet_path.equals("/uploadprofile_picture"))
                {
                    boolean uploaded = processProfilePictures(req, res, file_items);

                    if(uploaded)
                    {
                        System.out.println("Uploaded");
                    }
                    else
                    {
                        System.out.println("Not uploaded");
                    }
                }
                else if(servlet_path.equals("/administrative/upload_season"))
                {
                    System.out.println(servlet_path);
                    processSeasons(req, res, file_items);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

    }
}
