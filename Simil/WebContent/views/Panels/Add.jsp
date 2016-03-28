<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
	<%	// begin admin authentication
        if ((request.getSession().getAttribute("username") != null)) {            	
	         if(request.getSession().getAttribute("accountType").equals("Administrator")){
    %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="/Simil/supp/js/jquery-1.12.1.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/Simil/supp/css/simil.css">
<title>Simil</title>
  
</head>
<body>
	<div id="wrapper">
		<div id="headerText">
			<h1>Add a Panel</h1>
		</div>
		<div id="newPanelForm">
			<form onsubmit="validatePanelForm()" method="post">
				<div class="formElement">
					<label>Panel Name: </label> <input type="text" name="name" required>
					<br>
				</div>
				<div class="formElement">
					<label>Panel Description: </label> <input type="text" name="description" required>
					<br>
				</div>
				<!-- Check boxes for related panels and panel moderators -->
				<!-- These can be optional, have at least one mod, the creator, by default -->
				<input type="submit">
			</form>
		</div>
		<a href="/Simil">Go Back</a>
	</div>
</body>
</html>

<script>
	function signUp(name, description, related, moderators){
		// Make the ajax call to create account
		var url = "/Simil/SignUpController";
		$.ajax({
			url: url,
			type: "POST",
			data: {"userName": username, "fName": fname, "lName": lname, "email": email, "birth": dob, "pw": pass1},
			success: function(){<%if(request.getSession().getAttribute("username") != null)
				response.sendRedirect("/Simil");%>},
			error: function(err){
	        	console.log(err);
	        }
		});
	}
	
	
	
	function validateSignUpForm() {
		var alertBool = false;
		var message = "";
	    //var fname = document.forms["signUpForm"]["fName"].value;
	    var fname = document.forms[0].elements[0].value;
	    var lname = document.forms[0].elements[1].value;
	    var dob = document.forms[0].elements[2].value;
	    var email = document.forms[0].elements[3].value;
	    var username = document.forms[0].elements[5].value;
	    var pass1 = document.forms[0].elements[6].value;
	    var pass2 = document.forms[0].elements[7].value;
	    /* console.log(fname + " " + lname + " " + dob + " " + email);
 */
	    /* var q1 = document.forms[0].elements["secQuestion1"].value;
	    var a1 = document.forms[0].elements["secAnswer1"].value;
	    var q2 = document.forms[0].elements["secQuestion2"].value;
	    var a2 = document.forms[0].elements["secAnswer2"].value; */
	    if (fname == null || fname == "") {
			message += "First Name is a required field.\n";
			alertBool = true;
	    }
	    if (lname == null || lname == "") {
			message += "Last Name is a required field.\n";
			alertBool = true;
	    }
	    if (dob == null || dob == "") {
			message += "Birth Date is a required field.\n";
			alertBool = true;
	    }
	    if (email == null || email == "") {
			message += "Email is a required field.\n";
			alertBool = true;
	    }
	    if (username == null || username == "") {
			message += "Username is a required field.\n";
			alertBool = true;
	    }
	    if (pass1 == null || pass1 == "") {
			message += "Password is a required field.\n";
			alertBool = true;
	    }
	    /* if (q1 == null || q1 == "" || q2 == null || q2 == "") {
			message += "Security questions are required fields.\n";
			alert = true;
	    }
	    if (a1 == null || a1 == "" || a2 == null || a2 == "") {
			message += "Security answers are required fields.\n";
			alert = true;
	    } */
	    if (pass2.localeCompare(pass1)!=0){
			message += "Your passwords don't match!";
			alertBool = true;
	    }
	    if(alertBool){
	    	alert(message);
	    	return alertBool;
	    }
		signUp(username, fname, lname, email, dob, pass1);
	}
</script>

<%	// End the admin authentication
	}
	else{
	     response.sendError(403, "Forbidden" );
	}
}
else{
    response.sendError(403, "Forbidden" );
}
%>
