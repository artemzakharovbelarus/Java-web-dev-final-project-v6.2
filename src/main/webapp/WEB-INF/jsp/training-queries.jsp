<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 20.07.20
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<html lang="${sessionScope.locale}">
<head>
    <link href="css/style1.css" rel="stylesheet" type="text/css"/>
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

    <title><fmt:message key="local.queries"/></title>
</head>
<body class="back">
    <div class="sticky-top" style="text-align: center; padding-bottom: 20px; " role=banner>
        <u:nav/>
    </div>
    <div style="width: 15%; float: left; background-color: blue;" role=complementary>
    </div>
    <div style="width: 150px; float: right; background-color: green">
    </div>
    <div style="min-height: 520px; margin-left: 15%; margin-right: 160px;" role=main>
        <table class="table table-dark">
            <thead>
            <tr>
                <th style="text-align: center; vertical-align: middle" scope="col"><fmt:message key="local.id"/></th>
                <th style="text-align: center; vertical-align: middle" scope="col"><fmt:message key="local.username"/></th>
                <th style="text-align: center; vertical-align: middle" scope="col"><fmt:message key="local.Title"/></th>
                <th style="text-align: center; vertical-align: middle" scope="col"><fmt:message key="local.start"/></th>
                <th style="text-align: center; vertical-align: middle" scope="col"></th>
                <th style="text-align: center; vertical-align: middle" scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${queries}" var="query">
                <c:if test="${query.acceptedStatus == 0}">
                    <tr>
                        <th style="text-align: center; vertical-align: middle" scope="row">${query.idQuery}</th>
                        <td style="text-align: center; vertical-align: middle" scope="row">${query.username}</td>
                        <td style="text-align: center; vertical-align: middle;" scope="row">${query.trainingTitle}</td>
                        <td style="text-align: center; vertical-align: middle;" scope="row">${query.startDate}</td>
                        <form action="controller" method="post">
                            <input type="hidden" name="idTraining" value="${query.idTraining}">
                            <input type="hidden" name="idQuery" value="${query.idQuery}">
                            <input type="hidden" name="answer" value="2">
                        <td style="text-align: center; vertical-align: middle;" scope="row">
                            <button type="submit" name="command" value="set-query-answer" class="btn btn-outline-success">
                                <i class="fa fa-check" aria-hidden="true"></i>
                                <fmt:message key="local.accept"/>
                            </button>
                        </td>
                        </form>
                        <form action="controller" method="post">
                            <input type="hidden" name="idTraining" value="${query.idTraining}">
                            <input type="hidden" name="idQuery" value="${query.idQuery}">
                            <input type="hidden" name="answer" value="1">
                            <td style="text-align: center; vertical-align: middle;" scope="row">
                            <button type="submit" name="command" value="set-query-answer" class="btn btn-outline-danger">
                                <i class="fa fa-window-close" aria-hidden="true"></i>
                                <fmt:message key="local.decline"/>
                            </button>
                        </td>
                        </form>
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
