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
        <%@ include file="/supp/html/topNav.jsp" %>
        <%@ include file="/supp/html/sideMenu.jsp" %>


            <div id="content">
                <div id="panelDiv">
	                <div id="postHeader">

	                </div>
                    <div id="panelPost">
                       
                    </div>
                    <div id="commentSection">
                        <div id="commentDiv">
                            <div class="commentHeader">
                                <p class="commenter">Commenter: HUGH MANATEE(Hyperlink to user page)</p>
                                <!-- Probably display inline next to commenter -->
                                <p class="postTime">Time of comment: 4:20 Blazit</p>
                            </div>
                            <p class="comment">WTF BRO THIS IS SO COOL!</p>
                         </div>
                        <div id="commentDiv">
                            <div class="commentHeader">
                                <p class="commenter">Commenter: Other User 420(Hyperlink to user page)</p>
                                <!-- Probably display inline next to commenter -->
                                <p class="postTime">Time of comment: 4:21 Blazit</p>
                            </div>
                            <p class="comment">Super Duper COOL!</p>
                         </div>
                     </div>

                </div>
             </div>
    </div>
</body>
<script>

var id = '${param.id}';

$(document).ready(function() {
	getPost();
});

function getPost(){
    
    var url = "/Simil/PostServlet";
    $.ajax({
        url : url,
        datatype : 'json',
        type: "GET",
        data : {
            param: "post",
            id : id
        },
        success : function(data) {
            console.log("getPanelPosts succeeded. Returning data.");
            fillPostDetails(data);
        },
        error : function() {
            alert('error');
        }
    });
}

function getComments(){
    
    var url = "/Simil/PostServlet";
    $.ajax({
        url : url,
        datatype : 'json',
        type: "GET",
        data : {
            param: "comments",
            id : id
        },
        success : function(data) {
            console.log("getPanelPosts succeeded. Returning data.");
            fillPanelDetails(data);
        },
        error : function() {
            alert('error');
        }
    });
}


function fillPostDetails(data){
	console.log(data);
	var $postHeader = $('#postHeader');
	var $panelPost = $('#panelPost');
	var titleDiv = document.getElementById("postTitle");
	var panelPostDiv = document.getElementById("panelPost");
	var commentSection = document.getElementById("commentSection");
	//var time = document.getElementsByClass()
    var originalPoster = data[0];
    var panelID = data[1];
    var panelName = data[2];
	var postTitle = data[3];
	var postTime = data[4];
	var postContent = data[5];
	console.log(postTitle);

	//titleDiv.append(postTitle);
	
	var entry ="<div id='postTitle'><h3><a class='link1' href='/Simil/views/Panels/Panel.jsp?id=" + panelID + "&name=" + panelName + "'>" + panelName + "</a>  -- "+ postTitle + "</h3></div>"
    $postHeader.append(entry);
	
	entry="<p class='poster'>By: <a class='link1' href='/Simil/views/Home/UserPage.jsp?user="+ originalPoster + "'>" + originalPoster + "</a></p>"+
	"<p class='postTime'>Posted: " + postTime + "</p>";
    $postHeader.append(entry);
    
    entry = "<p class='post'>"+ postContent +"</p>";
    $panelPost.append(entry);
/*     
	
    <div id="commentDiv">
    <div class="commentHeader">
        <p class="commenter">Commenter: HUGH MANATEE(Hyperlink to user page)</p>
        <!-- Probably display inline next to commenter -->
        <p class="postTime">Time of comment: 4:20 Blazit</p>
    </div>
    <p class="comment">WTF BRO THIS IS SO COOL!</p>
 </div>
	 */
	
	
	
}
</script>
</html>