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
	    <div id="homeDiv">
	        <h1>Welcome to Simil!</h1>
	    </div>
	    <div id="loginForm">
	       <form onsubmit="login()" method="POST">
	           <h3>Log In</h3>
	           <input type="text" name="username" placeholder="username" required>
	           <br>
               <input type="password" name="password" placeholder="password" required>
               <br>
               <input type="submit">
               
	       </form>
	    </div>
    </div>
</body>
</html>
<script>
function login(){
	console.log("IM IN DARN IT");
    var url = "/Simil/LoginController";
    //var rdr_url = "views/Interests/Catalog.jsp";
    $.ajax({
    	type: "POST",
        url: url,
        /* error: function(){
            alert('error'); 
        }*/
    });
}
</script>