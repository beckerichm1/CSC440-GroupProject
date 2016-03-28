<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<% %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="/Simil/supp/js/jquery-1.12.1.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Interests Catalog</title>
</head>
<body>
<h1>Select your interests!</h1>
<div id="orderedList" style="columns:5 50px; -moz-columns:5 50px; -webkit-columns:5 50px">
    <ul id="interestList" style="list-style:none">
    	    
    </ul>
</div>
<button onClick="chooseInterests()">Continue</button>
</body>
</html>

<script>
$( document ).ready(function() {
    getInterests();
});

function getInterests(){
	var url = "/Simil/InterestController";
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
		$div.append(entry);
	}
}

function chooseInterests(){
	var interests =new Array();// { 'interests[]' : []};
	var i = 0;
	$(":checked").each(function() {
	  interests.push($(this).val());
	  console.log(interests[0]);
	  i++;
	});
	
	var json = JSON.stringify(interests);
	$.ajax({
		url: "/Simil/InterestController",
		type: "POST",
		data: {interests:interests},
		dataType: 'json',
		success: function(){location.reload();},
		error: function(err){
        	console.log("Error adding interests: " + err);
        }
	});
}
</script>