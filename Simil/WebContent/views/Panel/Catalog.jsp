<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<% %>
<script type="text/javascript" src="/Simil/supp/js/jquery-1.12.1.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="orderedList" style="columns:5 50px; -moz-columns:5 50px; -webkit-columns:5 50px">
    <ul id="panelList" style="list-style:none">
    	    
    </ul>
</div>
</body>
</html>

<script>
$( document ).ready(function() {
    getPanels();
});

function getPanels(){
	var url = "/Simil/PanelServlet";
	$.ajax({
		url: url,
		datatype: 'json',
		success: function(data){populatePanels(data)},
        error: function(){
        	alert('error');
        }
	});
}

function populatePanels(data){
	var $div = $('#panelList');
	for(var i = 0; i < data.length; i++){
		console.log(data[i]);
		var name = data[i][0];
		var desc = data[i][1];
		var entry = "<li><input type = 'checkbox' value = '" + name + "'><div>" + name + "/n" + desc + "</div></li>";
		$div.append(entry);
	}
}

function joinPanels(){
	var data = { 'panels[]' : []};
	$(":checked").each(function() {
	  data['panels[]'].push($(this).val());
	});
	$.ajax({
		url: "/Simil/PanelController",
		type: "POST",
		data: data,
		success: function(){return true;},
		error: function(){
        	alert('error');
        }
	});
}
</script>