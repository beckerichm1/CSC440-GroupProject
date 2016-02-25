<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<% %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table id="interestsTable">
    <tr>
        <td>Interest Name</td>    
    </tr>
</table>
</body>
</html>

<script>
$(document).ready(getInterests(););

function getInterests(){
	var url = "";
	$.ajax({
		url: url,
		data: {param1, param2},
		success: populateInterests(data)
	})
	
}

function populateInterests(var data){
	for(var item in data){
		
	}
}
</script>