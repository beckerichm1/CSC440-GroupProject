<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	try {
		if (request.getSession().getAttribute("username") == null) {

			response.sendRedirect("/Simil");

		}
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
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
		<%@ include file="/supp/html/topNav.jsp"%>
		<%@ include file="/supp/html/sideMenu.jsp"%>


		<div id="content">
			<div class="centerDiv">
				<div class="container">
					<div id="headerText">
						<h1>Create New Post</h1>
					</div>
					<form action="javascript:makePost()">
                        <div class="formElement">
                            <label class="formLabel">Post Title: </label> <input type="text"
                                id="postFormTitle" required />
                            <p class="requiredForm">*</p>
                        </div>
                        <div class="formElement">
                            <label class="formLabel">Post Title: </label> <textarea class="postFormText" name="postText" id="postFormText" required></textarea>
                            <p class="requiredForm">*</p>
                        </div>
                        <button>Create Post</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script>   
	
	   var panelID = '${param.id}';
	   var panelName = '${param.name}';
	   
	   function makePost(){
	        var url = "/Simil/PostServlet";
	        var title = document.getElementById("postFormTitle").value;
	        var text = document.getElementById("postFormText").value;
	        
	        console.log(text);
	        console.log(title);
	        $.ajax({
	            url: url,
	            datatype : 'json',
	            type: "POST",
	            data : {
	                name : panelName,
	                id : panelID,
	                title : title,
	                text : text
	            },
	            success : function(data) {
	                console.log("Post Made");
	                window.location = "/Simil/views/Panel.jsp?id=${param.id}&name=${param.name}";
	            },
	            error : function() {
	                alert('Error creating post...');
	            }
	        })
	   }
	
    </script>
</body>
</html>
