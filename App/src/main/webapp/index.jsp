<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.exist.employee.*" %>
<%@ page import="java.util.function.*" %>
<html>

	<HEAD>
        <TITLE>The tableName Database Table </TITLE>
    </HEAD>

<body>
	<H1>The tableName Database Table </H1>

	<%
		List<Employee> empList = (List<Employee>) request.getAttribute("empList");
	%>
	
	<TABLE BORDER="1">
            <TR>
                <TH>ID</TH>
                <TH>Lastname</TH>
                <TH>Firstname</TH>
                <TH>Middlename</TH>
            </TR>
            <% for(Employee e: empList) { %>
            <TR>
                <TD> <%= e.getEmployeeId() %></td>
                <TD> <%= e.getLastname() %></TD>
                <TD> <%= e.getFirstname() %></TD>
                <TD> <%= e.getMiddlename() %></TD>
            </TR>
            <% }; %>
    </TABLE>

</body>
</html>
