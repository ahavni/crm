<%@ page import="java.util.*, web.ani.beans.User" %>
    <html>
        <head>
        </head>

        <body>

           <%
           ArrayList<String> productsList = new ArrayList<String>();
           productsList = (ArrayList<String>)request.getAttribute("products");
           if(productsList == null){
           %>
            <p>Sorry, Something went wrong</p>
            <%
            }
            else{
            %>

            <form method="POST" action="buyProducts">
                <div>
                    <p>Products</p>
                </div>

                <div>
                    <select name="selected_product">
                    <option selected="selected">Select product</option>
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