        <div id="sideMenu" class="noselect">
            <div id="linkContainer">
                <ul>
                    <li><a class="menuItem" href="/Simil/views/Interests/Catalog.jsp">Interests</a></li>
                    <li><a class="menuItem" href="/Simil/views/Panels/Catalog.jsp">Panels</a></li>
                    <li><a class="menuItem" href="/Simil/views/Home/UserPage.jsp?user=<%=session.getAttribute("username")%>">My Page</a>
                    <%
                        //System.out.println("Account: " + request.getSession().getAttribute("accountType"));
                        if (request.getSession().getAttribute("accountType").equals("Administrator")) {
                    %>
                     <li><a class="menuItem"  href="/Simil/views/Dashboard/AdministratorTools.jsp">Administrator Tools</a></li>
                    <%
                        }
                    %>
                </ul>
            </div>
        </div>
        
        <script>
        $(function(){
            var expanded = false;
            $('#sideMenu').click(function(){
                if (!expanded){
                      $(this).animate({'left' : '0px'}, {duration : 200});
                      expanded = true;
                }
                else{
                    $(this).animate({'left' : '-145px'}, {duration: 200});
                    expanded = false;
                }
            });
        });
        </script>