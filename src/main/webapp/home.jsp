<%@ page import="web.ani.beans.User" %>
    <html>
        <head>
        </head>

        <body>

        <%
        User user = (User) request.getAttribute("found_user");
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
            <%
                session.setAttribute("current_user", user);
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