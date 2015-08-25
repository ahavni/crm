<%@ page import="java.util.*, web.ani.beans.User" %>
    <html>
        <head>
            <title>Buy Product</title>
            <script src="Validations.js" type="text/javascript"></script>
        </head>
        <body>
           <%
           ArrayList<String> productsList = new ArrayList<String>();
           productsList = (ArrayList<String>)request.getAttribute("products");
           if(productsList.isEmpty()){
           %>
                <p>Sorry, no products in the DB</p>
            <%
            }
            else{
            %>
                <form name="buyProducts" method="POST" action="buyProducts" onSubmit="return buyProductValidation();">
                    <div>
                        <p>Products</p>
                    </div>

                    <div>
                        <select name="selected_product">
                        <option value = "0" selected="selected">Select product</option>
                            <%
                            for(String product:productsList){
                            %>
                            <option value=<%= product %> ><%= product%></option>
                            <%
                            }
                            %>
                        </select>
                    </div>
                    </br>
                    <div>
                        <input type="submit" value="Buy">
                    </div>
                </form>
            <%
            }
            %>
        </body>
    </html>