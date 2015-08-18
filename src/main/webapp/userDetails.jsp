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

            <form method="POST" action="update">
                <div>
                    <label>First Name:</label>
                    <input type="text" name="first_name" value=<%= user.getFistName()%> />
                </div>
                <div>
                    <label>Last Name:</label>
                    <input type="text" name="last_name" value = <%= user.getFistName()%> />
                </div>
                <div>
                    <label>Age:</label>
                    <input type="text" name="age" value = <%= user.getAge()%> />
                </div>
                <div>
                    <label>Address:</label>
                    <input type="text" name="address" value = <%= user.getAddress()%> />
                </div>
                <div>
                    <label>E-mail</label>
                    <input type="email" name="email" value = <%= user.getEmail()%> />
                </div>
                <div>
                    <label>Password</label>
                    <input type="password" name="password" />
                </div>
                <div>
                    <span>Sex</span>
                    <input type="radio" name="sex" value="man" <%=user.getSex().equals("man")? "checked": ""%>>Man</input>
                    <input type="radio" name="sex" value="woman" <%=user.getSex().equals("woman")? "checked": ""%>>Woman</input>
                    <input type="radio" name="sex" value="child" <%=user.getSex().equals("child")? "checked": ""%>>Child</input>
                </div>
                <div>
                    <span>UserType</span>
                    <input type="radio" name="user_type" value="customer" <%=user.getUserType().equals("customer")? "checked": ""%>>Customer</input>
                    <input type="radio" name="user_type" value="consultant" <%=user.getUserType().equals("consultant")? "checked": ""%>>Consultant</input>
                </div>
                </br>
                <div>
                    <input type="submit" name="save_changes" value="Save">
                </div>
                </form>

            <%
            }
            %>
        </body>

    </head>
