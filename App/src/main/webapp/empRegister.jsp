<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.exist.employee.*" %>
<%@ page import="java.util.function.*" %>
<html>

	<HEAD>
        <TITLE> Employee </TITLE>
    </HEAD>

<body>
	       <form action="Employee" method="post">
            <table style="with: 50%">
                <tr>
                    <td>Full Name</td>
                    <td>
                        <input type="text" name="title" value="Title" />
                        <input type="text" name="firstname" value="firstname" />
                        <input type="text" name="middlename" value="middlename" />
                        <input type="text" name="lastname" value="lastname"/>
                        <input type="text" name="firstname" value="firstname" />
                        <input type="text" name="suffix" value="Suffix" />
                    </td>
                </tr>
                <tr>
                    <td>Address</td>
                    <td>
                        <input type="text" name="streetno" value="Street No." />
                        <input type="text" name="street" value="Street" />
                        <input type="text" name="brgy" value="Brgy" />
                        <input type="text" name="city" value="City"/>
                        <input type="text" name="zipcode" value="Zipcode" />
                    </td>
                </tr>
                <tr>
                    <td>Birthday</td>
                    <td><input type="text" name="birthday" value="YYYY-MM-DD" /></td>
                </tr>
                <tr>
                    <td>General Weighted Average</td>
                    <td><input type="text" name="gwa" /></td>
                </tr>
                <tr>
                    <td>Currently Hired</td>
                    <td>
                         <select name="currentlyhired">
                          <option value="yes">Yes</option>
                          <option value="no">No</option>
                        </select> 
                    </td>
                </tr>
               <tr>
                    <td>Date Hired</td>
                    <td><input type="text" name="datehired" value="YYYY-MM-DD" /></td>
                </tr>

                <tr>
                    <td>Contact</td>
                    <td>
                        <input type="text" name="landline" value="landline" />
                        <input type="text" name="mobile" value="mobile" />
                        <input type="text" name="email" value="email" />
                    </td>
                </tr>

                <tr>
                    <td>Role</td>
                    <td>
                         <select name="role">
                        <%
                          List<Role> roleList = (List<Role>) request.getAttribute("roleList");%>
                          <%for(Role r: roleList) { %>
                          <option value=<%= r.getRole() %>><%= r.getRole()%></option>
                          <%}; %>
                        
                        </select> 
                    </td>
                </tr>
            </table>
            <input type="submit" value="addemployee" />
        </form>

</body>
</html>
