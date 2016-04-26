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
						<h1>Create New Comment</h1>
					</div>
					<form action="javascript:makeComment()">
                        <div class="formElement">
                            <label class="formLabel">Comment Text: </label> <textarea class="postFormText" name="postText" id="postFormText" required></textarea>
                            <p class="requiredForm">*</p>
                        </div>
                        <button>Create Comment</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script>   
	
	   var commentID = '${param.id}';
	   
	   function makeComment(){
	        var url = "/Simil/PostServlet";
	        var text = document.getElementById("postFormText").value;
	        
	        $.ajax({
	            url: url,
	            datatype : 'json',
	            type: "POST",
	            data : {
	                id : commentID,
	                content : text,
	                param: "comment"
	            },
	            success : function(data) {
	                console.log("Comment Made");
	                window.location = "/Simil/views/Panels/PanelPost.jsp?id=${param.id}";
	            },
	            error : function() {
	                alert('Error creating post...');
	            }
	        })
	   }
	
    </script>
</body>
</html>
