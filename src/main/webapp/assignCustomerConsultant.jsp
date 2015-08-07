<%@ page import="java.util.*, web.ani.beans.User" %>
    <html>
        <head>
        </head>

        <body>

           <%
           ArrayList<User> consultantList = new ArrayList<User>();
           consultantList = (ArrayList<User>)request.getAttribute("consultant");
           ArrayList<User> customerList = new ArrayList<User>();
           customerList = (ArrayList<User>)request.getAttribute("customer");
           if(consultantList == null || customerList == null){
           %>
            <p>Sorry, Something went wrong</p>
            <%
            }
            else{
            %>

            <form method="POST" action="assign">
                <div>
                    <p>Consultant</p>
                </div>

                <div>
                    <select name="selected_consultant">
                        <%
                        for(User user:consultantList){
                        %>
                        <option value=<%= user.getEmail()%> ><%= user.getEmail()%></option>
                        <%
                        }
                        %>
                    </select>
                </div>

                <div>
                    <p>Customer</p>
                </div>
                <div>
                    <select name="selected_customer">
                        <%
                        for(User user:customerList){
                        %>
                        <option value=<%= user.getEmail()%> ><%= user.getEmail()%></option>
                        <%
                        }
                        %>
                    </select>
                </div>
                </br>
                <div>
                    <input type="submit" name="assign" value="Assign">
                </div>
            </form>
            <%
            }
            %>
        </body>
    </html>