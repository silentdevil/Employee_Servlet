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
                    <td>*Full Name</td>
                    <td>
                        <input type="text" name="title" placeholder="Title" />
                        <input type="text" name="firstname" placeholder="*firstname" required/>
                        <input type="text" name="middlename" placeholder="*middlename" required/>
                        <input type="text" name="lastname" placeholder="*lastname"required/>
                        <input type="text" name="suffix" placeholder="Suffix" />
                    </td>
                </tr>
                <tr>
                    <td>*Address</td>
                    <td>
                        <input type="text" name="streetno" placeholder="*Street No." required/>
                        <input type="text" name="street" placeholder="*Street" required/>
                        <input type="text" name="brgy" placeholder="*Brgy" required/>
                        <input type="text" name="city" placeholder="*City" required/>
                        <input type="text" name="zipcode" placeholder="*Zipcode" required/>
                    </td>
                </tr>
                <tr>
                    <td>*Birthday</td>
                    <td><input type="text" name="birthday" placeholder="*YYYY-MM-DD" required/></td>
                </tr>
                <tr>
                    <td>*General Weighted Average</td>
                    <td><input type="text" name="gwa" required/></td>
                </tr>
                <tr>
                    <td>*Currently Hired</td>
                    <td>
                         <select name="currentlyhired">
                          <option value="TRUE">TRUE</option>
                          <option value="FALSE">FALSE</option>
                        </select> 
                    </td>
                </tr>
               <tr>
                    <td>*Date Hired</td>
                    <td><input type="text" name="datehired" placeholder="*YYYY-MM-DD" required/></td>
                </tr>

                <tr>
                    <td>Contact</td>
                    <td>
                        <input type="text" name="landline" placeholder="landline [XXX-XXXX]" />
                        <input type="text" name="mobile" placeholder="mobile [XXXX-XXX-XXXX]" />
                        <input type="text" id="email" name="email" placeholder="*email" 
                           onblur="validateEmail(value)" required />
                    </td>
                </tr>

                <tr>
                    <td>Role</td>
                    <td>
                         <select name="role">
                        <%
                          List<Role> roleList = (List<Role>) request.getAttribute("roleList");%>
                          <%for(Role r: roleList) { %>
                          <option value=<%= r.getRoleId() %>><%= r.getRole()%></option>
                          <%}; %>
                        
                        </select> 
                    </td>
                </tr>
            </table>
            <input type="submit" value="Save Employee" name="addemployee" />
            <a href="http://localhost:8080/Employee">
                <button name="back"> Back to Main Menu </button>
            </a> 
        </form>
        <script>
        function validateEmail(email){ 
          var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
          if(re.test(email)){
            document.getElementById('email').style.background ='#ccffcc';
            return true;
          }else{
            document.getElementById('email').style.background ='#e35152';
            return false;
          }
        }
        </script>

       
</body>
</html>
