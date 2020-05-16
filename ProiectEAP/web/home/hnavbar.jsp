<!-- The horizontal navigation bar -->
<!-- An auxiliary form needed for buttons -->
<form id = "auxform" name = "auxform" style = "display: none"></form>

<div class = "hnavbar">
    <div class = "hcell">
        <a href = <%=request.getContextPath() + "/index.jsp"%>>Home</a>
    </div>

    <div class = "hcell">
        <a href = <%=request.getContextPath() + "/restricted/list?status=watching"%>>MyList</a>
        <div class = "ddown">
            <a href = <%=request.getContextPath() + "/restricted/list?status=completed"%>>Completed</a>
            <a href = <%=request.getContextPath() + "/restricted/list?status=watching"%>>Watching</a>
            <a href = <%=request.getContextPath() + "/restricted/list?status=plan"%>>Plan to watch</a>
            <a href = <%=request.getContextPath() + "/restricted/list?status=dropped"%>>Dropped</a>
        </div>
    </div>

    <div class = "hcell">
        <a href = <%=request.getContextPath() + "/restricted/profile.jsp"%>>Profile</a>
    </div>

    <div class = "hcell">
        <a href = <%=request.getContextPath() + "/restricted/settings.jsp"%>>Settings</a>
    </div>

    <div class = "hcell hcellright">
        <jsp:include page = "searchbar.jsp"></jsp:include>
    </div>

    <%
        String username = (String)session.getAttribute("username");

        if(username == null)
        {
    %>
            <div class = "hcell">
                <a href = <%=request.getContextPath() + "/login.jsp"%>>Login</a>
            </div>

            <div class = "hcell">
                <a href = <%=request.getContextPath() + "/register.jsp"%>>Register</a>
            </div>
    <%
        }
        else
        {
    %>
            <div class = "hcell">
                <a href = <%=request.getContextPath() + "/restricted/profile.jsp"%>><%=(String)session.getAttribute("display_name")%></a>
                <img src = "<%= request.getContextPath() + (String)session.getAttribute("picture_path")%>" alt = "profile picture" style = "max-width: 40px; max-height: 40px; padding: 0px;">
                <div class = "ddown">
                    <button type = "submit" form = "auxform" formmethod = "post" formaction = <%=request.getContextPath() + "/log"%>>Logout</button>
                </div>
            </div>
    <%
        }
    %>
</div>
