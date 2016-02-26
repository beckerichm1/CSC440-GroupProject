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
<button onclick="getInterests()">Show All Interests</button>
<div id="orderedList" style="columns:5 50px; -moz-columns:5 50px; -webkit-columns:5 50px">
    <ul id="interestList" style="list-style:none">
    	    
    </ul>
</div>
</body>
</html>

<script>
function getInterests(){
	var url = "/Simil/InterestServlet";
	$.ajax({
		url: url,
		datatype: 'json',
		success: function(data){populateInterests(data)},
        error: function(){
        	alert('error');
        }
	});
}

function populateInterests(data){
	var $div = $('#interestList');//var item in data
	var y = Math.floor(data.length/3);
	var z = Math.floor(2 * data.length/3);
	console.log(y + " " + z);
	for(var x = 0; x < data.length; x++){
		var entry = "<li><input type ='checkbox' value='" + data[x] + "'>" + data[x] + "</li>";
/* 		var entry = "<li>" + data[x] + "</li><li>" + data[x + y] + "</li><li>" + data[x + z] + "</li>";
 */		$div.append(entry);
	}
}
</script>