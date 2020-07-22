<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 016 16.07.20
  Time: 1:17
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
<body>
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
            <th style="text-align: center; vertical-align: middle" scope="col"><fmt:message key="local.Title"/>:</th>
            <th style="text-align: center; vertical-align: middle" scope="col"><fmt:message key="local.start"/></th>
            <th style="text-align: center; vertical-align: middle" scope="col"><fmt:message key="local.status"/></th>
            <th style="text-align: center; vertical-align: middle" scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${queries}" var="query">
            <tr>
                <c:if test="${query.canceledStatus == false}">
                    <th style="text-align: center; vertical-align: middle" scope="row">${query.idQuery}</th>
                    <td style="text-align: center; vertical-align: middle" scope="row">${query.trainingTitle}</td>
                    <td style="text-align: center; vertical-align: middle" scope="row">${query.startDate}</td>
                    <td style="text-align: center; vertical-align: middle" scope="row">
                        <c:choose>
                            <c:when test="${query.acceptedStatus == 0}">
                                <fmt:message key="local.under-review"/>
                            </c:when>
                            <c:when test="${query.acceptedStatus == 2}">
                                <h7 class="text-success">
                                    <i class="fa fa-check-square" aria-hidden="true"></i>
                                    <fmt:message key="local.accepted"/>
                                </h7>
                            </c:when>
                            <c:when test="${query.acceptedStatus == 1}">
                                <h7 class="text-danger">
                                    <fmt:message key="local.declined"/>
                                </h7>
                            </c:when>
                        </c:choose>
                    </td>
                    <c:if test="${query.acceptedStatus == 2 || query.acceptedStatus == 0}">
                        <form action="controller" method="post">
                            <input type="hidden" name="idQuery" value="${query.idQuery}">
                            <td style="text-align: center; vertical-align: middle" scope="row">
                                <button type="submit" name="command" value="undo-query" class="btn btn-outline-danger">
                                    <i class="fa fa-times"></i> <fmt:message key="local.undo"/>
                                </button>
                            </td>
                        </form>
                    </c:if>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="sticky-bottom" style="height: 70px; clear: both; background-color: black" role=contentinfo>
    <u:footer/>
</div>
</body>
</html>
