<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%
        
        if ((request.getSession().getAttribute("username") != null)) {            	
	         if(request.getSession().getAttribute("accountType").equals("Administrator")){


    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="/Simil/supp/js/jquery-1.12.1.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Tools</title>
</head>
<body>
    <h1>ADMIN TOOLS</h1>
    
    <!-- Link to add/remove panels -->
    
</body>
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