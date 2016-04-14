<div id="nav">
	<div id="logoDiv">
		<a id="logo" href="/Simil"><span id="logoSpan">Simil!</span></a>
	</div>
	
	<div id="searchDiv">
		<div class="searchForm">
	       <!-- Changing onsubmit from return login() to just login() -->
	       <form action="javascript:search()">
	           <div class="inputHolder">
		           <input type="text" id="username" name="username" placeholder="Search for users">
	               <button type="submit" value="search" class = "button">Search!</button>
               </div>
	       </form>
		</div>
	</div>
</div>

<script>
function search(){
	    var username = $("#username").val();
	    var url = "/Simil/AccountServlet";
	    $.ajax({
	    	type: "GET",
	        url: url,
	        data: {"id": username},
	        success: function(data){
	        	// Perform a redirect, passing along the data to show results of people found
	        	
	        	//console.log(data);
	        	//window.location = "/Simil/views/Home/UserHome.jsp"
	        },
	        error: function(data){
				console.log(data);
	        }
	});
}
</script>