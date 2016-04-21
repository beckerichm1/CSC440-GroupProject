<div id="nav" class="noselect">
	<div id="logoDiv">
		<a id="logo" href="/Simil"><span id="logoSpan">Simil!</span></a>
	</div>
	<div id="navDiv">
	<div id="searchDiv">
			<div class="searchForm">
		       <!-- Changing onsubmit from return login() to just login() -->
		       <form id="search" action="javascript:search()">
		           <div class="searchInputHolder">
			           <input id="searchBar" type="text" name="query" placeholder="Search for users">
		               <a class="noselect" onclick="document.getElementById('search').submit()">
			               <div class="searchButton">
			                  <h4>Search!</h4>
		                   </div>
	                   </a>
	               </div>
		       </form>
			</div>
		</div>
	    <a onclick="document.getElementById('logoutForm').submit()">
		    <div id="logoutDiv">
		           <form id="logoutForm" class="noselect" method="POST" action="/Simil/LogoutController">
		               <span><h4>Log Out</h4></span>
		           </form>
		    </div>
	    </a>
    </div>
    
</div>

<script>
function search(){
	    var query = $("#searchBar").val();
	    window.location = "/Simil/SearchResults.jsp?search=" + query;
}
</script>