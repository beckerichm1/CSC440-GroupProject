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
    <div id ="wrapper">
        <div id="headerText">
            <h1>Sign Up</h1>
        </div>
        <div id="signUpForm">
            <form onsubmit="validateSignUpForm()" method="post">
                <div class="formElement">
	               <label>First Name </label>
	               <input type="text" name="fName" required>
	               <br>
                </div>
                <div class="formElement">
	               <label>Last Name: </label>
	               <input type="text" name="lName" required>
	               <br>
                </div>
	            <div class="formElement">
	               <label>Date of Birth: </label>
	               <input type="date" name="dob" required>
	               <br>
                </div>
                <div class="formElement">
	               <label>Email: </label>
	               <input type="email" name="email" required>
	               <br>
                </div>
                <div class="formElement">
	               <label>Alternate Email (optional): </label>
	               <input type="text" name="alt_email">
	               <br>
	            </div>
                <div class="formElement">
	               <label>Username: </label>
	               <input type="text" name="username" required>
	               <br>
	            </div>
                <div class="formElement">
	               <label>Password: </label>
	               <input type="password" name="pass1" required>
	               <br>
	            </div>
                <div class="formElement">
	               <label>Verify Password: </label>
	               <input type="password" name="pass2" required>
	               <br>
	            </div>
                <!-- div class="formElement">
	               <label>Security Question 1: </label>
	               <input type="text" name="secQuestion1" required>
	               <br>
	            </div>
                <div class="formElement">
	               <label>Security Answer 1: </label>
	               <input type="text" name="secAnswer1" required>
	               <br>
	            </div>
                <div class="formElement">
	               <label>Security Question 2: </label>
	               <input type="text" name="secQuestion2" required>
	               <br>
                </div>
                <div class="formElement">
	               <label>Security Answer 2: </label>
	               <input type="text" name="secAnswer2" required>
	               <br>
	            </div-->
	               <input type="submit">
           </form>
        </div>
    </div>
</body>
</html>

<script>
	function signUp(username, fname, lname, email, dob, pass1){
		// Make the ajax call to create account
		var url = "/Simil/SignUpController";
		$.ajax({
			url: url,
			type: "POST",
			data: {"userName": username, "fName": fname, "lName": lname, "email": email, "birth": dob, "pw": pass1},
			success: function(data){<%if(request.getSession().getAttribute("username") != null)
				response.sendRedirect("/Simil");%>},
			error: function(){
	        	alert('error');
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
	    console.log(fname + " " + lname + " " + dob + " " + email);

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
