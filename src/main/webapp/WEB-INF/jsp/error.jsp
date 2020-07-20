<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 08.06.20
  Time: 22:20
  To change this template use File | Settings | File Templates.
--%>
<html lang="${sessionScope.locale}">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:300,700" rel="stylesheet">
    <link type="text/css" rel="stylesheet" href="css/error.css"/>
    <link href="css/back.css" rel="stylesheet" type="text/css"/>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale"/>
    <title><fmt:message key="local.error-404"/></title>
</head>
<body class="back">
    <div id="notfound">
        <div class="notfound">
            <div class="notfound-404">
                <h1>4<span></span>4</h1>
            </div>
            <h2><fmt:message key="message.page-not-found"/></h2>
            <p><fmt:message key="message.error-text"/></p>
        </div>
    </div>
</body>
</html>
