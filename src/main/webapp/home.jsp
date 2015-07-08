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
                if(user.getUserType().equals("customer")){
                %>
                <jsp:include page="customer_menu.jsp" />
                <%
                } else {
                %>
                <jsp:include page="consultant_menu.jsp" />
                <%
                }
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