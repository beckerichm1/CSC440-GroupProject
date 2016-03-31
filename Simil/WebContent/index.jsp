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
<link rel="stylesheet" type="text/css" href="/Simil/supp/css/similHome.css">

<title>Simil</title>
</head>
<body>
    <div id ="homeWrapper">
	    <div id="homeDiv">
            <div id="logo" class="noselect">Simil!</div>
        </div>
	    <div class="formBox">
	       <!-- Changing onsubmit from return login() to just login() -->
	       <form action="javascript:login()">
	       <!-- action="/Simil/LoginController"  --> 
	           <h3 class="homeHeader">Sign In</h3>
	           <label class="error"></label>
	           <div class="inputHolder">
		           <input type="text" id="username" name="username" placeholder="username" required>
		           <br>
	               <input type="password" id="password" name="password" placeholder="password" required >
	               <br>
	               <button type="submit" value="Log In" class = "button">Log In</button>
               </div>
	       </form>
        <div class="link">
            <a class="homeLink" href="signup.jsp">Sign Up</a> 
            <br>
            <a class="homeLink" href="recovery.jsp">Forgot Password?</a>         
        </div>
        </div>

    </div>
</body>
</html>
<script>
function login(){
	//var fields = validateInput();
	//console.log(fields.length);
	//if(fields.length === 0){
	    var username = $("#username").val();
	    var password = $("#password").val();
	    var url = "/Simil/LoginController";
	    //var rdr_url = "views/Interests/Catalog.jsp";
	    $.ajax({
	    	type: "POST",
	        url: url,
	        data: {"username": username, "password": password},
	        success: function(data){
	        	//console.log(data);
	        	window.location = "/Simil/views/Home/UserHome.jsp"
	        },
	        error: function(data){
	            var errorMessage = document.getElementsByClassName("error");
	            var passBox = document.getElementById('password');
	            console.log(errorMessage);
	            passBox.value = "";
	//            document.getElementById("myForm").reset();
	            errorMessage[0].innerHTML = "Invalid username/password combination";
	            errorMessage[0].style.display='inline';
	             
	        	
	           //alert("Couldn't log in. \nCheck your username and password.")
	        }
	        
    });
	// }
/* 	else{
		for (var i = 0; i < fields.length; i++){
			  document.getElementById(fields[i]).validity = false;
		        document.getElementById(fields[i]).style.border="solid 3px #A62A2A";


		}
	}  */
}

function validateInput(){
	var err = new Array(0);
	var user = document.getElementById("username");
	var pass = document.getElementById("password");
	if(user.value === ""){
        err.push(user.id);
    }
	if(pass.value === ""){
        err.push(pass.id);
    }
	console.log("err " + err);
	return err;

	
}
</script>