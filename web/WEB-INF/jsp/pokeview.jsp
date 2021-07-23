<%-- 
    Document   : pokeview
    Created on : Jun 17, 2021, 1:44:44 PM
    Author     : USER
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.poke.model.PokeModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>pokeview</title>
        
        <link href="../less/pokeview.less" rel="stylesheet">
        
        <link type="text/css" rel="stylesheet" href="./less/pokeview.less">
        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
        <!-- JavaScript Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="container-fluid header">
            <jsp:include page="template/header.jsp" />
        </div>
        <div class="container">
            <%! int loop_counter = 1; int innerBreak = 1; %>
            <% List<PokeModel> pokedata = (ArrayList) request.getAttribute("pokedata");
               for (PokeModel model : pokedata) {
                   
                    if(innerBreak == 1) {        
            %>
            
            <div class="row">
            <% } %>
                <div class="col-sm-4" style="margin-top: 5px">                
                    <div class="card poke-card" style="box-shadow: 2px 3px #888888; margin-top: 2px"> 
                        <img src="<%=model.getImg()%>" class="card-img-top" alt="<%=model.getImg()%>" style="background-color: lightgray">
                        <div class="card-body">
                            <h5 class="card-title"><%=model.getName()%></h5>
                            <a href="detail/<%=model.getName()%>"><%=model.getName()%></a>
                            <p class="card-text"><%=model.getId()%></p>
                            <p class="card-text"><button class="btn" style="background-color:<%=model.getColorType()%>;color: white"><%=model.getType()%></button></p>
                        </div>                                    
                    </div>                
                </div>
            <% if(loop_counter % 3 == 0) { %>
            </div>
            <% innerBreak = 1; 
                } else  {
                    innerBreak = 0;
                }
            }
            %>
        </div>
    </body>
</html>
            