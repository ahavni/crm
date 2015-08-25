<%@ page import="java.util.*, web.ani.beans.User" %>
<HTML>
    <head>
        <title>List Users</title>
    </head>
    <body>
        <%
        ArrayList<User> userList = new ArrayList<User>();
        userList = (ArrayList<User>)request.getAttribute("usersList");
        if(userList.isEmpty()){
        %>
            <p>Sorry, no users in the database</p>
        <%
        }
        else{
        %>
            <table border="2">
                <tr>
                    <td>First Name</td>
                    <td>Last Name</td>
                    <td>Age</td>
                    <td>Address</td>
                    <td>Email</td>
                    <td>Sex</td>
                    <td>User_type</td>
                </tr>
                <%
                for (User user: userList){
                %>
                    <tr>
                     <td><%= user.getFistName() %></td>
                     <td><%= user.getLastName() %></td>
                     <td><%= user.getAge() %></td>
                     <td><%= user.getAddress() %></td>
                     <td><%= user.getEmail() %></td>
                     <td><%= user.getSex() %></td>
                     <td><%= user.getUserType() %></td>
                    </tr>
                <%
                }
        }
        %>
            </table>
    </body>
</HTML>