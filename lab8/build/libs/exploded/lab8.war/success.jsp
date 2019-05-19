<%@ page import="myweb.domain.User" %><%--
  Created by IntelliJ IDEA.
  User: andreea
  Date: 18.05.2019
  Time: 19:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="script.js"></script>
    <script src="jquery-2.0.3.js"></script>
</head>
<body>
<% User user;%>
<% user = (User) session.getAttribute("user");
    if (user != null) {
        out.println("Welcome " + user.getUsername());
    }
%>


<div>
    <label>Current city: </label>
    <p id="currentCity"></p>
    <button id="backButton" onclick="back()" style="display: none">Back</button>
    <button style="display: none" onclick='showRoute()' id="destButton">Select as destination</button>
</div>
<br>
<button onclick="displayCities()">Display cities</button>
<ul id="list_of_stations"></ul>
<br>
<ul id="final_route" style="display: none">Final Route</ul>
</body>
</html>
