<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.exist.employee.*" %>
<%@ page import="java.util.function.*" %>
<html>

	<HEAD>
        <TITLE> Roles </TITLE>
    </HEAD>

<body>
	<H1>The Role Table </H1>

	<%
		List<Role> roleList = (List<Role>) request.getAttribute("roleList");
        String roleStat = (String) request.getAttribute("ROLE_DELETE_STATUS");
        Role role = (Role) request.getAttribute("selected_role");
	%>

    <%
        if (roleStat == "SUCCESS") {
            out.println("<script type=\"text/javascript\">");  
            out.println("alert('Role is successfully deleted');");  
            out.println("</script>");
        } else if(roleStat == "FAILED") {
            out.println("<script type=\"text/javascript\">");  
            out.println("alert('Role is taken, can't delete it.);");  
            out.println("</script>");
        }
    %>

	<form action="Employee" method="post">
	<TABLE BORDER="1">
            <TR>
                <TH>ID</TH>
                <TH>Role</TH>
            </TR>
            <% for(Role r: roleList) { %>
            <TR>
                <TD> <%= r.getRoleId() %></td>
                <TD> <%= r.getRole() %></TD>

                 <td>
                     <button type="submit" value=<%= r.getRoleId()+""%> name="viewroleemp">
                        View Employees
                     </button>
                </td>
        
                 <td>
                     <button type="submit" value=<%= r.getRoleId()+""%> name="deleterole" onclick="return confirm('Are you sure you want to delete this?')">
                        DELETE
                     </button>
                </td>



            </TR>

            <% }; %>


            <TR>
                <td>
                    <input type="submit" value="Add Role" name="addnewrole">
                </td>

                 <td>
                    <input type="text" placeholder="Add Role" name="newrole">
                </td>

                <td>

                </td>
            </TR>
    </TABLE>

    Role : <%= role.getRole() %> <br>
    <% if(role!=null) { %>
       <% for(Employee e: role.getEmployees()) { %>
       <%= e.getEmployeeName() %>  <br>
        <% } %>

    <% } %>
 
    <button type="submit" name="back" novalidate> Back to Main Menu </button> 
</form>

</body>
</html>
