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
                        <%= employee.getEmployeeName() %> 
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
                    <td><%= employee.getDateHired() %></td>
                </tr>

                <tr>
                    <td>Contact</td>
                    <td>
                       <TABLE BORDER="1">
                                <TR>
                                    <TH>CONTACT TYPE</TH>
                                    <TH>CONTACT INFO</TH>
                                </TR>
                                <% for(ContactDto c: employee.getContacts()) { %>
                                <TR>
                                    <TD> <%= c.getContactType() %></td>
                                    <TD> <%= c.getContactInfo() %></TD>
                            
                                     <td>
                                         <button type="submit" value=<%= c.getContactId()+""%> name="deleteemployeecontact" onclick="return confirm('Are you sure you want to delete this?')">
                                            DELETE
                                         </button>
                                    </td>

                                </TR>

                                <% }; %>


                                <TR>
                                    <td>
                                        <select name="newempcontact">
                                            <% for(int i=1; i < ContactType.SIZE + 1; i++) { %>
                                          <option value=<%= ContactType.valueOf(i)%>>
                                            <%= ContactType.valueOf(i).getMessage() %>
                                          </option>
                                           <% } %>
                                        </select> 
                                    </td>

                                    <td>
                                        <input type="text">
                                    </td>

                                    <td>
                                         <input type="submit" value="ADD" name="addempcontact">
                                    </td>
                                </TR>
                               
                        </TABLE>
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
            <button type="submit" name="back" novalidate> Back to Main Menu </button> 
        </form>



</body>
</html>
