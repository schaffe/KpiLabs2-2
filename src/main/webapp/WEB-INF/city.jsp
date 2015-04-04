<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/styles/style.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/jquery-latest.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/script.js"></script>
    <title>Cities</title>
</head>
<body>
<div id="popupcontent">
    <form action="${pageContext.request.contextPath}/city/" id="edit_city_form">
        <p>
            <input type="text" name="name" id="edit_city_form_name" required>
        </p>
        <p><input type="number" min="0" name="population" id="edit_city_form_population">
        </p>
        <p><input type="button" onclick="hidePopup()" value="Cancel"><input type="submit" value="Save"></p>
    </form>
    <%--<div id="statusbar"><button onclick="hidePopup();">Close window</button></div>--%>
</div>
<div class="main_content">
<form action="${pageContext.request.contextPath}/city/" id="add_city_form">
    <p>
        <input type="text" name="name" id="add_city_form_name" placeholder = "City" required></p>
    <p>
        <input type="number" min="0" name="population" id="add_city_form_population" placeholder = "Population">
    </p>
    <p><input type="submit" value="Add"></p>
</form>
    </div>
<div class = "main_content">
    <h3>Cities</h3>
    <table class="table" border="0" id="table">
        <tr>
            <th onclick="sortByColumn('name')">City</th>
            <th onclick="sortByColumn('population')">Population</th>
            <th>&nbsp;</th>
            <th>&nbsp;</th>
        </tr>
        <tbody id="table_body">
        </tbody>
    </table>
</div>
<div class="bg"></div>
</body>
</html>