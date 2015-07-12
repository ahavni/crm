<%@ page import="java.util.*, web.ani.beans.User" %>
<HTML>
<head>
</head>
<body>
    <%
        ArrayList<User> userList = new ArrayList<User>();
        userList = (ArrayList<User>)request.getAttribute("usersList");
        if(userList == null){
    %>
            <p>Sorry, no  data in the database</p>
    <%
        }
        else{
    %>
            <table border="2">
                <tr>
                    <td>First Name</td>
                    <td>Last Name</td>
                    <td>Age</td>
                </tr>
    <%
            for (User user: userList){
    %>
                <tr>
                 <td><%= user.getFistName() %></td>
                 <td><%= user.getLastName() %></td>
                 <td><%= user.getAge() %></td>
                </tr>
    <%
           }
        }
    %>
        </table>
</body>
</HTML>