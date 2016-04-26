<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%
    // begin admin authentication
    if ((request.getSession().getAttribute("username") != null)) {
        if (request.getSession().getAttribute("accountType").equals("Administrator")) {
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
        <%@ include file="/supp/html/topNav.jsp" %>
        <%@ include file="/supp/html/sideMenu.jsp" %>
    
        <div id="content">
            <div class="centerDiv">
            <div class="container">
                <div id="headerText">
                    <h1>Add Panel Moderator</h1>
                </div>
                <div id="newPanelForm">
                    <form onsubmit="validatePanelForm()" method="post">
                        <div class="formElement">
                            <label>Panel Name: </label> <input type="text" id="name"
                                required> <br>
                        </div>
                        <div class="formElement">
                            <label>Moderator: </label> <input type="text"
                                id="moderator" required> <br>
                        </div>

                        <input type="submit">
                    </form>
                </div>
                
                <a href="/Simil">Go Back</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>

<script>
    $(document).ready(function() {
        getPanels();
    });

    function addPanel(name, description, related, moderators) {
        // Make the ajax call to insert a new panel into database
        var url = "/Simil/PanelServlet";
        
        
        $.ajax({
            url : url,
            type : "POST",
            data : {
                "postType":"add",
                "name" : name,
                "description" : description,
                "related" : related,
                "moderators" : moderators
            },
            success : function() {
                //TODO: Redirect to admin tools, needs to be tested
                response.sendRedirect("/Simil/Dashboard/AdministratorTools")
            },
            error : function(err) {
                console.log(err);
            }
        });
    }

    function getPanels() {
        // Pass in a piece of data so it knows to get all panels?
        console.log("Getting panels");
        var url = "/Simil/PanelServlet";
        $.ajax({
            url : url,
            datatype : 'json',
            data : {
                "param" : 'all'
            },
            success : function(data) {
                populatePanels(data)
            },
            error : function() {
                alert('error');
            }
        });
    }

    function populatePanels(data) {
        console.log("populating panels");
        var $div = $('#related');
        for (var i = 0; i < data.length; i++) {
            var id = data[i][0]
            var name = data[i][1];
            //var desc = data[i][2];
            var entry = '<option value="' + id + '">' + name + '</option>';
            $div.append(entry);
        }
    }

    function getRelatedPanels() {
        var panels = [];
        $(":checked").each(function() {
            panels.push($(this).val());
        }); //name, description, related, moderators
    
        var name = document.getElementById("name").value;
        var description = document.getElementById("description").value;
        var moderator = document.getElementById("moderator").value;
        //TESTING -- DELETE THIS
        
        addPanel(name,description, panels, moderator);
    }

    function validatePanelForm() {
        var alertBool = false;
        var message = "";
        
        var name = document.getElementById("name").value;
        var desc = document.getElementById("description").value;
        //var related = document.forms[0].elements[2].value;
        var mods = "";

        if (name == null || name == "") {
            message += "Panel Name is a required field.\n";
            alertBool = true;
        }
        if (desc == null || desc == "") {
            message += "Panel Description is a required field.\n";
            alertBool = true;
        }
        if (mods == null || mods == "") {
            // Set current user as default mod if none selected
            // Might have to do something different here, to make mods an array?
            mods = "<%=request.getSession().getAttribute("username")%>";
        }
        if (alertBool) {
            alert(message);
            return alertBool;
        }
        getRelatedPanels();
    }
</script>

<%
    // End the admin authentication
        } else {
            response.sendError(403, "Forbidden");
        }
    } else {
        response.sendError(403, "Forbidden");
    }
%>
