<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="/Simil/supp/css/simil.css">
<script type="text/javascript" src="/Simil/supp/js/jquery-1.12.1.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Simil</title>
</head>
<body>
    <div id="wrapper">
        <div id="nav">
            <div id="logoDiv">
                <a id="logo" href="/Simil"><span id="logoSpan">Simil!</span></a>
            </div>

        </div>
        
            <div id="content">
                <div class="centerDiv">
                    <div id="signUpForm">
                        <div id="headerText">
                            <h1>Reset Password</h1>
                        </div>
                        <form  method="post">
                          <div class="formElement">
                              <label class="formLabel">Email: </label> <input type="text" name="email" required/><p class = "requiredForm">*</p>
                          </div>
                          <div class="formElement">
                              <label class="formLabel">Old Password: </label> <input type="password" name="oldPass" required/><p class = "requiredForm">*</p>
                          </div>
                          <div class="formElement">
                              <label class="formLabel">New Password: </label> <input type="password" name="newPass1" required/><p class = "requiredForm">*</p>
                          </div>
                          <div class="formElement">
                              <label class="formLabel">Confirm Password: </label> <input type="password" name="newPass2" required/><p class = "requiredForm">*</p>
                          </div>
                          <input type="submit">
                         </form>
                    </div>
                    <br>
                   <a class="link2" href="/Simil">Go Back</a>
                </div>
                
            </div>
            </div>
    </div>
</body>
</html>

<script>
function submit(username, email){
    // Make the ajax call to create account
    var url = "/Simil/LoginController";
    $.ajax({
        url: url,
        type: "POST",
        data: {param: "reset", "userName": username, "email": email},
        success: function(){ window.location="/Simil";},
        error: function(err){
            console.log(err);
        }
    });
}
</script>
