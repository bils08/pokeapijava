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
        <% List<PokeModel> pokedata = (ArrayList) request.getAttribute("pokedata"); %>
        <div style="background-color:green;height: 100%">
            <img src="<%=pokedata.get(0).getImg()%>" class="card-img-top" alt="<%=pokedata.get(0).getImg()%>" >
        </div>
    </body>
</html>
            