function validateSignUpForm() {
	boolean alert = false;
	var message = "";
    var fname = document.forms["signUpForm"]["fName"].value;
    var lname = document.forms["signUpForm"]["lName"].value;
    var dob = document.forms["signUpForm"]["dob"].value;
    var email = document.forms["signUpForm"]["email"].value;
    var username = document.forms["signUpForm"]["username"].value;
    var pass1 = document.forms["signUpForm"]["pass1"].value;
    var pass2 = document.forms["signUpForm"]["pass2"].value;
    var q1 = document.forms["signUpForm"]["secQuestion1"].value;
    var a1 = document.forms["signUpForm"]["secAnswer1"].value;
    var q2 = document.forms["signUpForm"]["secQuestion2"].value;
    var a2 = document.forms["signUpForm"]["secAnswer2"].value;
    if (fname == null || fname == "") {
		message += "First Name is a required field.\n";
		alert = true;
    }
    if (lname == null || lname == "") {
		message += "Last Name is a required field.\n";
		alert = true;
    }
    if (dob == null || dob == "") {
		message += "Birth Date is a required field.\n";
		alert = true;
    }
    if (email == null || email == "") {
		message += "Email is a required field.\n";
		alert = true;
    }
    if (username == null || username == "") {
		message += "Username is a required field.\n";
		alert = true;
    }
    if (pass1 == null || pass1 == "") {
		message += "Password is a required field.\n";
		alert = true;
    }
    if (q1 == null || q1 == "" || q2 == null || q2 == "") {
		message += "Security questions are required fields.\n";
		alert = true;
    }
    if (a1 == null || a1 == "" || a2 == null || a2 == "") {
		message += "Security answers are required fields.\n";
		alert = true;
    }
    if (pass2 != pass1){
		message += "Your passwords don't match!";
		alert = true;
    }
    if(alert)
    	alert(message);
    return !alert;
}