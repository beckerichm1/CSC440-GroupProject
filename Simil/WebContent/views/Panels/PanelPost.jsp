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
                    <div id="postTitle"><h3>Panel Name(hyperlink?) -- Post Title</h3></div>
                    <div id="panelPost">
                       <p class="post">This is an example panel post. Bleep bloop. Ask a question here or make a cool pose or something like that.</p>
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
</html>