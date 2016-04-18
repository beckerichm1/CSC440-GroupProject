<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ include file="/supp/html/RequiredLoginHeader.jsp" %>

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
                <div id="profileDiv">
                    <div id="userImage">
                        <img src="/Simil/supp/imgs/manatee.jpeg">
                    </div>
                    <div id="userName"><%=session.getAttribute("username")%></div>
                    <div id="bioDiv">
                        <h3>Bio</h3>
                        <p>Filler bio text. Pls replace with queried stuff.</p>
                        <p>ANOTHER LINE HERE. LIFE IS SO GREAT BEING A MANATEE</p>
                    </div>
                    <div id="userPanelDiv">
                        <h3>Panels</h3>
                        <p>Filler panels text. Pls replace with queried stuff.</p>
                        <p>ANOTHER LINE HERE. LIFE IS SO GREAT BEING A MANATEE</p>
                    </div>
                    <div id="userBuddiesDiv">
                        <h3>Buddies</h3>
                        <p>Hugh Manatee</p>
                        <p>Ted Manatee</p>
                        <p>Billy Manatee</p>
                        <p>Filler bio text. Pls replace with queried stuff.</p>
                        <p>ANOTHER LINE HERE. LIFE IS SO GREAT BEING A MANATEE</p>
                    </div>
                    <div id="userInterestDiv">
                        <h3>Interests</h3>
                        <p>Hecks</p>
                        <p>League of Lejjins</p>
                        <p>Filler bio text. Pls replace with queried stuff.</p>
                        <p>ANOTHER LINE HERE. LIFE IS SO GREAT BEING A MANATEE</p>
                    </div>
              
              
                </div>

            </div>
        
    </div>
</body>
</html>
