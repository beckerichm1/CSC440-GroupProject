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
                    <h1>Edit Moderators</h1>
                </div>
                <div id="newPanelForm">
                    <form onsubmit="validatePanelForm()" method="post">
                        <div class="formElement">
                            <label>Panel: </label>
                            <select name="panel" id="panels" onchange="getMods()">
                                
                            </select>
                        </div>
                        <div class="formElement">
                            <label>Moderator: </label> <textarea
                                id="moderator" required> </textarea><br>
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

    function getMods(){
    	//document.newPanelForm.panels.options[document.newPanelForm.panels.selectedIndex].value;
    	var id = $("#panels option:selected").attr("id")
    	//var id = document.getElementById("panels").value;
    	console.log("Getting moderators for panelID " + id);
    	var url = "/Simil/PanelServlet";
    	$.ajax({
            url : url,
            type : "GET",
            data : {
            	"param" : "mods",
                "id" : id
            },
            success : function(data) {
            	console.log(data);
            	document.getElementById("moderator").value = data[0];
            },
            error : function(err) {
                console.log(err);
            }
        });
    }
    
    function addMod(id, moderators) {
    	console.log("addMod hit");
        // Make the ajax call to insert a new panel into database
        var url = "/Simil/PanelServlet";
        $.ajax({
            url : url,
            type : "POST",
            data : {
                "postType":"addMod",
                "id" : id,
                "moderators" : moderators
            },
            success : function() {
                //TODO: Redirect to admin tools, needs to be tested
                window.location = "/Simil/views/Dashboard/AdministratorTools.jsp";
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
                populatePanels(data);
            },
            error : function() {
                alert('error');
            }
        });
    }

    function populatePanels(data) {
        var $div = $('#panels');
        var first = true;
        for(var i in data) {
        	if(data[i] != null){
	            var id = data[i][0];
	            var name = data[i][1];
	            var x = parseInt(i) + 1;
	            //var desc = data[i][2];
	            var entry = "<option value='" + x + "' id='" + id +"' onChange='getMods(" 
	            		+ id + ")'>" + name + "</option>";
	            $div.append(entry);
        	}
        }
        getMods();
    }

    function validatePanelForm() {
    	console.log("ValidatePanelForm hit");
        var alertBool = false;
        var message = "";
        
        //var name = document.getElementById("name").value;
        var elt =  document.getElementById("panels");
        var id = elt.options[elt.selectedIndex].value;
        var mods = "";
		
        var moderator = document.getElementById("moderator").value;
        console.log(moderator);
        if (mods == null || mods == "") {
            // Set current user as default mod if none selected
            // Might have to do something different here, to make mods an array?
            mods = "<%=request.getSession().getAttribute("username")%>";
        }
        if (alertBool) {
            alert(message);
            return alertBool;
        }
    	console.log("Calling addMod...");
        addMod(id, moderator);
		
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
