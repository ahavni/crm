<%@ page import="web.ani.beans.User" %>
    <html>
        <head>
        </head>

        <body>

        <%
        User user = (User) request.getSession().getAttribute("user");
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
            <a href="logout.jsp">Log out</a></br>
            <%
                session.setAttribute("user", user);
            %>
        </p>

        <%
        if(user.getUserType().equals("customer")){
        %>

        <jsp:include page="customer_menu.jsp" />

        <%
        } else {
        %>
        <jsp:include page="consultant_menu.jsp" />
        <%
        }
        }
        %>

        </body>
</html>