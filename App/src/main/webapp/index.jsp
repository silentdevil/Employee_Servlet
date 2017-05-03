<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.exist.employee.*" %>
<%@ page import="java.util.function.*" %>
<html>

	<HEAD>
        <TITLE> Employee </TITLE>
    </HEAD>

<body>
	<H1>The Employee Database Table </H1>

	<%
		List<Employee> empList = (List<Employee>) request.getAttribute("empList");
        String output = (String) request.getAttribute("addValue");;
	%>
	<form action="Employee" method="post">
	<TABLE BORDER="1">
            <TR>
                <TH>ID</TH>
                <TH>Lastname</TH>
                <TH>Firstname</TH>
                <TH>Middlename</TH>
            </TR>
            <% for(Employee e: empList) { 
                Name name = e.getEmployeeName(); %>
            <TR>
                <TD> <%= e.getEmployeeId() %></td>
                <TD> <%= name.getLastName() %></TD>
                <TD> <%= name.getFirstName() %></TD>
                <TD> <%= name.getMiddleName() %></TD>
                <TD> <%= ButtonFunctions.addValue(e,output)%></TD>
                 <td>
                     <button type="submit" value=<%= e.getEmployeeId()+""%> name="edit">
                        EDIT
                     </button>
                </td>
                 <td>
                     <button type="submit" value=<%= e.getEmployeeId()+""%> name="delete" onclick="return confirm('Are you sure you want to delete this?')">
                        DELETE
                     </button>
                </td>

            </TR>
            <% }; %>
    </TABLE>
    <br>
     <input type="submit" value="Add Employee" name="addEmp"/>
     <input type="submit" value="Sort:Gwa" name="sort_gwa"/>
     <input type="submit" value="Sort:Hiredate" name="sort_date"/>
     <input type="submit" value="Sort:lastname" name="sort_lastname"/>
     <input type="submit" value="Modify Roles" name="modifyroles"/>


</body>
</html>
