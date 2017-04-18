<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.exist.employee.*" %>
<%@ page import="java.util.function.*" %>
<html>

	<HEAD>
        <TITLE> Edit Employee </TITLE>
    </HEAD>

<body>
    <% EmployeeDto employee = (EmployeeDto) request.getAttribute("employee"); %> 
	<H1>Edit Employee Page</H1>
    <form action="Employee" method="post">
    <table style="with: 50%">
                <tr>
                    <td>Full Name</td>
                    <td>
                        <%= employee.getTitle() +" " + employee.getLastname() + ", " + employee.getFirstname() + " " + employee.getMiddlename() %> 
                    </td>
                </tr>
                <tr>
                    <td>Address</td>
                    <td>
                        <%= employee.getAddress() %>
                    </td>
                </tr>
                <tr>
                    <td>Birthday</td>
                    <td><%= employee.getBirthday() %></td>
                </tr>
                <tr>
                    <td>General Weighted Average</td>
                    <td><%= employee.getGwa() %></td>
                </tr>
                <tr>
                    <td>Currently Hired</td>
                    <td>
                         <select name="currentlyhired">
                          <option value=<%= employee.getCurrentlyHired() %>>
                            <%= employee.getCurrentlyHired() %>
                          </option>
                        </select> 
                    </td>
                </tr>
               <tr>
                    <td>Date Hired</td>
                    <td><%= employee.getDatehired() %></td>
                </tr>

                <tr>
                    <td>Contact</td>
                    <td>
                        <input type="text" name="landline" value=<%= employee.getContact().getLandline() %> />
                        <input type="text" name="mobile" value=<%= employee.getContact().getMobile() %> />
                        <input type="text" name="email" value=<%= employee.getContact().getEmail() %> />
                    </td>
                </tr>

                <tr>
                    <td>Role</td>
                    <td>
                         <table>
                            <% Set<RoleDto> roleSet = employee.getRoles(); %>
                             <% for(RoleDto r: roleSet) { %>
                                <TR>
                                    <TD> <%= r.getRole() %></td>
                                     <td>
                                         <button type="submit" value=<%= r.getRoleId()+""%> name="delEmpRole">Delete </button>
                                    </td>
                                </TR>
                            <% }; %>
                        </table>
                    </td>
                </tr>
            </table>
            <div>
                 <div style="margin-left:100px">
                <table>
                    <% List<Role> roleList = (List<Role>)request.getAttribute("remainingRoles"); %>
                     <% for(Role r: roleList) { %>
                        <TR>
                            <TD> <%= r.getRole() %></td>
                             <td>
                                 <button type="submit" value=<%= r.getRoleId()+""%> name="addEmpRole">ADD </button>
                            </td>
                        </TR>
                    <% }; %>
                </table>
            </div>

            <input type="submit" value="Save Changes" name="savechanges">
        </form>



</body>
</html>
