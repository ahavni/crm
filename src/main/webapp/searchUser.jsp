<%@ page import="java.util.*, web.ani.beans.User" %>
<HTML>
    <head>
    </head>
    <body>
        <form method="POST" action="searchUser">
            <div>
                <label>First Name:</label>
                <input type="text" name="first_name"/>
            </div>
            <div>
                <label>Last Name:</label>
                <input type="text" name="last_name"/>
            </div>
            <div>
                <label>Age:</label>
                <input type="text" name="age"/>
            </div>
            <div>
                <label>Address:</label>
                <input type="text" name="address"/>
            </div>
            <div>
                <label>E-mail:</label>
                <input type="email" name="email"/>
            </div>

            <div>
                <label>Sex:</label>
                <input type="radio" name="sex" value="man">Man</input>
                <input type="radio" name="sex" value="woman">Woman</input>
                <input type="radio" name="sex" value="child">Child</input>
            </div>

            <div>
                <input type="checkbox" name="user_type" value="customer">Customer
                <input type="checkbox" name="user_type" value="consultant">Consultant
            </div>

          <input type="submit" value="Submit">
        </form>
    </body>
</HTML>