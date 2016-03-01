<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="/Simil/supp/js/jquery-1.12.1.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Simil</title>
</head>
<body>
    <div id ="homeWrapper">
    <%
	    try{
	        if (request.getSession().getAttribute("username") == null) {
	        }
	        else{
    %>
            <jsp:forward page="views/Home/UserHome.jsp" />
	        	  <!--  System.out.println("OUTSIDE");
	        	   System.out.println(request.getSession().getAttribute("username")); -->
    <%	   
	        }
	    }
	    catch(Exception e){
	    e.printStackTrace();
	    }
    %>
	    <div id="homeDiv">
	        <h1>Welcome to Simil!</h1>
	    </div>
	    <div id="loginForm">
	       <form onsubmit="login()" method="POST">
	           <h3>Log In</h3>
	           <input type="text" id="username" name="username" placeholder="username" required>
	           <br>
               <input type="password" id="password" name="password" placeholder="password" required>
               <br>
               <input type="submit" value="submit">
	       </form>
	    </div>
    </div>
</body>
</html>
<script>
function login(){
    var username = $("#username").val();
    var password = $("#password").val();
    //var username = document.getElementsByName("username");
    //var password = document.getElementsByName("password");
	console.log("IM IN DARN IT");
    var url = "/Simil/LoginController";
    console.log(username);
    console.log(password);
    //var rdr_url = "views/Interests/Catalog.jsp";
    $.ajax({
    	type: "POST",
        url: url,
        data: {"username": username, "password": password},
        success: function(data){
        }
        /* error: function(){
            alert('error'); 
        }*/
    });
    
}
</script>