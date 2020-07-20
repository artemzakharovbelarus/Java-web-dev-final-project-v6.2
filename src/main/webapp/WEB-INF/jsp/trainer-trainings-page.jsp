<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 018 18.07.20
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<html lang="${sessionScope.locale}">
<head>
    <link href="css/style1.css" rel="stylesheet" type="text/css"/>
    <link href="css/tr.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css"
          href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
          integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
          crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
            integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
            integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
            crossorigin="anonymous"></script>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale"/>

    <title><fmt:message key="local.your-trainings"/></title>
</head>
<body class="back">
    <div class="sticky-top" style="text-align: center; padding-bottom: 20px; " role=banner>
        <u:nav/>
    </div>
    <div style="width: 15%; float: left; background-color: blue;" role=complementary>
    </div>
    <div style="width: 150px; float: right; background-color: green">
    </div>
    <div style="min-height: 500px; margin-left: 15%; margin-right: 160px;" role=main>
        <table class="table table-dark">
            <thead>
            <tr>
                <th style="text-align: center; vertical-align: middle" scope="col"><fmt:message key="local.id"/></th>
                <th style="text-align: center; vertical-align: middle" scope="col"><fmt:message key="local.Title"/></th>
                <th style="text-align: center; vertical-align: middle" scope="col"><fmt:message key="local.City"/></th>
                <th style="text-align: center; vertical-align: middle" scope="col"><fmt:message key="local.hours"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${trainings}" var="training">
                <c:if test="${training.deletedStatus == false}">
                    <tr>
                        <th style="text-align: center; vertical-align: middle" scope="row">${training.idTraining}</th>
                        <td style="text-align: center; vertical-align: middle" scope="row">
                            <form action="controller" method="get">
                                <input type="hidden" name="idTraining" value="${training.idTraining}">
                                <button type="submit" name="command" value="view-training" class="btn btn-link">
                                        ${training.title}
                                </button>
                            </form>
                        </td>
                        <td style="text-align: center; vertical-align: middle" scope="row">${training.city}</td>
                        <td style="text-align: center; vertical-align: middle" scope="row">${training.hoursAmount}</td>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="sticky-bottom" style="height: 70px; clear: both; background-color: black" role=contentinfo>
        <u:footer/>
    </div>
</body>
</html>
