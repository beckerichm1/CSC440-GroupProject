<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
            <form action="#insertSignUpAction" method="post">
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
	               <input type="text" name="fName">
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
                <div class="formElement">
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
	            </div>
	               <input type="submit">
	               
           </form>
        </div>
    </div>
</body>
</html>