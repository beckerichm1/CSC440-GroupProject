<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	
%>
<script type="text/javascript" src="/Simil/supp/js/jquery-1.12.1.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/Simil/supp/css/simil.css">
<title>Panels</title>
</head>
<body>
	<div id="wrapper">
	    <%@ include file="/supp/html/topNav.jsp" %>
        <%@ include file="/supp/html/sideMenu.jsp" %>
		<div id="content">
            <div class="checkDiv">
                <div id="headerText">
                    <h1>Panels!</h1>
                </div>
                <div class="orderedList">
                    <div class="noselect" id="panelList" ><!-- ul id="interestList" style="list-style: none" -->
    
                    </div>
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

	function getPanels() {
		var url = "/Simil/PanelServlet";
		$.ajax({
			url : url,
			datatype : 'json',
			data : {
				param : 'all'
			},
			success : function(data) {
				populatePanels(data)
			},
			error : function() {
				alert('error');
			}
		});
	}

	// Changed the logic here and in the controller/queries so the value in checkbox is panelID 
	// (for panel_account integration)
	function populatePanels(data) {
		var $div = $('#panelList');
		for (var i = 0; i < data.length; i++) {
			console.log(data[i]);
			var id = data[i][0];
			var name = data[i][1];
			var desc = data[i][2];
		
			/* var entry = "<li><input type = 'checkbox' value = '" + id + "'><div>"
					+ name + "\n" + desc + "</div></li>";
			 */
			 // This used to be id=id, now is id=name
			var entry = "<a href='/Simil/views/Panels/Panel.jsp?id=" + id + "&name=" + name + "'><div class='checkElement'><label class='checkLabel panelLink' for='" + id +"'>"+ name + "</label></div></a>";
					
			/* <div class='checkElement'><input type='checkbox' name='interests' value='" + data[x] + "' id='"+ x +"'/>"+
		    "<label class='checkLabel' for='" + x +"'>"+ data[x] + "</label></div> */
			$div.append(entry);
		}
	}

	function joinPanels() {
		var data = {
			'panels[]' : []
		};
		$(":checked").each(function() {
			data['panels[]'].push($(this).val());
		});
		$.ajax({
			url : "/Simil/PanelController",
			type : "POST",
			data : data,
			success : function() {
				return true;
			},
			error : function() {
				alert('error');
			}
		});
	}
</script>