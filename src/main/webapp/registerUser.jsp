<!DOCTYPE html>
<html>
<head>
    <title>Registration name</title>
    <script src="Validations.js" type="text/javascript"></script>
</head>
<body>
<form name="registration" method="POST" action="registration" onSubmit="return formValidation();">
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
        <label>Password</label>
        <input type="password" name="password"/>
    </div>
    <div>
        <input type="radio" name="sex" value="man">Man</input>
        <input type="radio" name="sex" value="woman">Woman</input>
        <input type="radio" name="sex" value="child">Child</input>
    </div>
        <input type="radio" name="user_type" value="customer">Customer</input>
        <input type="radio" name="user_type" value="consultant">Consultant</input>
    <div>
    </div>
    <input type="submit">
    <%
    String regResult = (String) request.getAttribute("reg_result");
    if(regResult != null && regResult.equals("false")){
    %>
    <div>
        <label>Email already used</label>
    </div>
    <%
    }
    %>
</form>
</body>
</html>