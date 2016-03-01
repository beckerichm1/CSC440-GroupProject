<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  
    pageEncoding="ISO-8859-1"%>
    <script type="text/javascript" src="/Simil/supp/js/jquery-1.12.1.js"></script>
    <%
        try{
            if (request.getSession().getAttribute("username") == null) {
            	
            	response.sendRedirect("/Simil");
            	
            }
        }
        catch(Exception e){
        e.printStackTrace();
        }
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Simil</title>
    </head> 
    <body>      
        <h1>Welcome, <%= session.getAttribute("username") %></h1>
        <form action="../TestServlet">            
             <a href="Settings.jsp">Settings</a>
             <br>
             <div class='section'>
                <h4>My Panels:</h4>
                <div id="panels">
                    <ul id="panel-list"></ul>
                </div>
             </div>
             <div class='section'>
	             <h4>My Interests:</h4>
	             <div id="interests">
	               <ul id="interest-list"></ul>
	             </div>   
             </div>                   
        </form>     
        <form action="/Simil/LogoutController" method="POST">
             <button type="submit" value="Logout">Logout</button>
        </form>
       
    </body> 
</html>
<script>
	$( document ).ready(function() {
	    
	    $.ajax({
            type: "GET",
            url:"/Simil/UserHomeServlet",
            dataType: "json",
            success:function (data) {displayInfo(data)}
                });  
	});
	
	function displayInfo(data){
		var $interests = $("#interest-list");
		var $panels = $("panel-list");
		for(var x = 0; i < data[0].length; x++){
			var entry = "<li>" + data[0][x] + "</li>/n"
			$interests.append(entry);
		}
		for(var i = 0; i < data[1].length; i++){
			var entry = "<li>" + data[1][i] + "</li>/n"
			$panels.append(entry);
		}
	}
</script>