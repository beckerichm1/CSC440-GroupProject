<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="/Simil/supp/js/jquery-1.12.1.js"></script>
<html>
<head>
 <%
           try{
               if (request.getSession().getAttribute("username") == null) {
               }
               else{
       %>
               <jsp:forward page="views/Home/UserHome.jsp" />

       <%     
               }
           }
           catch(Exception e){
           e.printStackTrace();
           }
       %>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/Simil/supp/css/simil.css">

<title>Simil</title>
</head>
<body>
    <div id ="homeWrapper">
	    <div id="homeDiv">
            <div id="logo">Simil!</div>
        </div>
	    <div class="formBox">
	       <!-- Changing onsubmit from return login() to just login() -->
	       <form action="/Simil/LoginController" onsubmit="login()" method="POST">
	           <h3>Sign In</h3>
	           
	           <input type="text" id="username" name="username" placeholder="username" required>
	           <br>
               <input type="password" id="password" name="password" placeholder="password" required>
               <br>
               <button type="submit" value="Log In" class = "button">Log In</button>
	       </form>
	       
        </div>
	    <br>
	    <br>
	    <a href="signup.jsp">Sign Up</a>
    </div>
</body>
</html>
<script>
function login(){
    var username = $("#username").val();
    var password = $("#password").val();
    var url = "/Simil/LoginController";
    //var rdr_url = "views/Interests/Catalog.jsp";
    $.ajax({
    	type: "POST",
        url: url,
        datatype: 'json',
        data: {"username": username, "password": password},
        success: function(data){
        	console.log(data);
        },
        error: function(data){
            alert("Couldn't log in. \nCheck your username and password.")
        }
    });

    
}
</script>