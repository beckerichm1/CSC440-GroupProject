<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%
        
        if ((request.getSession().getAttribute("username") != null)) {            	
	         if(request.getSession().getAttribute("accountType").equals("Administrator")){


    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="/Simil/supp/css/simil.css">
<script type="text/javascript" src="/Simil/supp/js/jquery-1.12.1.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administrator Tools</title>
</head>
<body>
    <div id="wrapper">
        <%@ include file="/supp/html/topNav.jsp" %>
        <%@ include file="/supp/html/sideMenu.jsp" %>

            <div id="content">
                <h1>ADMINISTRATOR TOOLS</h1>    
                <ul>
	                <li><a class="link1" href='/Simil/views/Panels/Add.jsp'>Create Panel</a></li>
	                <li><a class="link1" href='/Simil/views/Panels/AddModerator.jsp'>Add Panel Moderator</a></li>
	                <li><a class="link1" href='/Simil/views/Panels/RemoveModerator.jsp'>Remove Panel Moderator</a></li>
                </ul>        
            </div>
        
    </div>
</body>
<script>
    
    $(function(){
        var expanded = false;
        $('#sideMenu').click(function(){
            if (!expanded){
                  $(this).animate({'left' : '0px'}, {duration : 200});
                  expanded = true;
            }
            else{
                $(this).animate({'left' : '-145px'}, {duration: 200});
                expanded = false;
            }
        });
    });
</script>
</html>
<%
	}
	else{
	     response.sendError(403, "Forbidden" );
	}
}
else{
    response.sendError(403, "Forbidden" );
}
%>