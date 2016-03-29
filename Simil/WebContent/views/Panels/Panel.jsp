<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="/Simil/supp/js/jquery-1.12.1.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Interests Catalog</title>
</head>

<script>
	$(document).ready(function() {
		getPanelDetails(${param.id});
	});

	function getPanelDetails(id) {
		var url = "/Simil/PanelServlet";
		$.ajax({
			url : url,
			datatype : 'json',
			type: "GET",
			data : {
				param: "one",
				id : id
			},
			success : function(data) {
				generatePage(data)
			},
			error : function() {
				alert('error');
			}
		});
	}

	function generatePage(data) {
		console.log("Data is: " + data);
		console.log("Data[0] is: " + data[0]);
		console.log("Data[1] is: " + data[1]);
		console.log("Data[2] is: " + data[2]);
	}
</script>