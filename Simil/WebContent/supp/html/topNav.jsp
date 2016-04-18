<div id="nav">
	<div id="logoDiv">
		<a class="noselect" id="logo" href="/Simil"><span id="logoSpan">Simil!</span></a>
	</div>
	<div id="navDiv">
	<div id="searchDiv">
			<div class="searchForm">
		       <!-- Changing onsubmit from return login() to just login() -->
		       <form id="search" action="javascript:search()">
		           <div class="searchInputHolder">
			           <input id="searchBar" type="text" id="username" name="username" placeholder="Search for users">
		               <div class="searchButton">
		                  <a class="noselect" onclick="document.getElementById('search').submit()"><h4>Search!</h4></a>
	                   </div>
	               </div>
		       </form>
			</div>
		</div>
	    <div id="logoutDiv">
	           <form id="logoutForm" class="noselect" method="POST" action="/Simil/LogoutController">
	               <span><a onclick="document.getElementById('logoutForm').submit()"><h4>Log Out</h4></a></span>
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