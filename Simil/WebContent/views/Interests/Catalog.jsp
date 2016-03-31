<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="/Simil/supp/js/jquery-1.12.1.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/Simil/supp/css/simil.css">
<title>Interests Catalog</title>
</head>
<body>
	<div id="wrapper">
		<div id="content">
			<div class="checkDiv">
				<div id="headerText">
	                <h1>Select your interests!</h1>
	            </div>
				<div class="orderedList">
					<div class="noselect" id="interestList" ><!-- ul id="interestList" style="list-style: none" -->
	
					</div>
				<div class="centerDiv">
				     <button onClick="chooseInterests()">Continue</button>
				</div>
				</div>
	        </div>
		</div>
	</div>
</body>
</html>

<script>
	$(document).ready(function() {
		getInterests();
	});

	function getInterests() {
		var url = "/Simil/InterestController";
		$.ajax({
			url : url,
			datatype : 'json',
			success : function(data) {
				populateInterests(data)
			},
			error : function() {
				alert('error');
			}
		});
	}

	function populateInterests(data) {
		var $div = $('#interestList');//var item in data
		var y = Math.floor(data.length / 3);
		var z = Math.floor(2 * data.length / 3);
		console.log(y + " " + z);
		for (var x = 0; x < data.length; x++) {
			
 			var entry ="<div class='checkElement'><input type='checkbox' name='interests' value='" + data[x] + "' id='"+ x +"'/>"+
 			"<label class='checkLabel' for='" + x +"'>"+ data[x] + "</label></div>";

				    
	 			/* <input type="checkbox" name="rGroup" value="1" id="r1" checked="checked" />
	 			    <label class="whatever" for="r1"></label> */
		   /*  var entry ="<input type ='checkbox' value='" + data[x] + "'/>" + 
		    "<label class='checkLabel' for='" + data[x] +"'>"+ data[x] + "</label>"  */
		    /* "<div class='checkElement'>" + data[x] + "</div></li>"; */
			
			/* var entry ="<li><input type ='checkbox' value='" + data[x] + "'/>" + 
			"<label class='checkLabel' for='" + data[x] +"'>"+ data[x] + "</label></li>" 
            /* "<div class='checkElement'>" + data[x] + "</div></li>"; */ 
            $div.append(entry);
		}
		

	}

	function chooseInterests() {
		var interests = new Array();// { 'interests[]' : []};
		var i = 0;
		$(":checked").each(function() {
			interests.push($(this).val());
			console.log(interests[0]);
			i++;
		});

		var json = JSON.stringify(interests);
		$.ajax({
			url : "/Simil/InterestController",
			type : "POST",
			data : {
				interests : interests
			},
			dataType : 'json',
			success : function() {
				location.reload();
			},
			error : function(err) {
				location.reload();
				console.log("Error adding interests: " + err);
			}
		});
	}
	
	

	
</script>