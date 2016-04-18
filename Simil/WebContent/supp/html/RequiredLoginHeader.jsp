<%
    try {
        Object username = request.getSession().getAttribute("username");
        if (username == null) {
            response.sendRedirect("/Simil");
            username = "";
        }
        else{
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
%>