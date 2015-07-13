<%@ page import="web.ani.beans.User" %>
    <html>
        <head>
        </head>
        <body>
        <%
            User user = (User) request.getAttribute("user");
            if(user == null){
        %>
        <p>Sorry, Something went wrong</p>
        <%
            }
            else{
        %>
        <p> Welcome,
            <%= user.getFistName() %>
            <%= user.getLastName() %>
        </p>
        <%
        }
        %>
        </body>
</html>