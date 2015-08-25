<%@ page import="java.util.*, web.ani.beans.User" %>
<HTML>
    <head>
        <title>List products</title>
    </head>
    <body>
        <%
        ArrayList<String> productList = new ArrayList<String>();
        productList = (ArrayList<String>)request.getAttribute("productList");
        if(productList.isEmpty()){
        %>
            <p>Sorry, no  data in the database</p>
        <%
        }
        else{
        %>
            <table border="2">
                <tr>
                    <td>Product Name</td>
                </tr>
                <%
                for (String product: productList){
                %>
                <tr>
                 <td><%= product %></td>
                </tr>
                <%
                }
        }
        %>
            </table>
    </body>
</HTML>