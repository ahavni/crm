<html>
    <head>
        <title>User log in</title>
        <script src="Validations.js" type="text/javascript"></script>
    </head>
    <body>
    <form method="POST" action="login" name="login" onSubmit="return loginValidation();">
        <div>
            <label>Username/E-mail</label>
            <input type="text" name="username"/>
        </div>
        <div>
            <label>Password</label>
            <input type="password" name="password"/>
        </div>
        <div>
            <a href="registerUser.jsp">New registration</a><br>
        </div>
        <div>
            <a href="createDB">DB initialization</a><br>
        </div>
        <input type="submit">
        <%
        String loginResult = (String) request.getAttribute("login_result");
        if(loginResult != null && loginResult.equals("false")){
        %>
        <div>
            <label> Username and/or password are incorrect</label>
        </div>
        <%
        }
        %>
    </form>
    </body>
</html>
