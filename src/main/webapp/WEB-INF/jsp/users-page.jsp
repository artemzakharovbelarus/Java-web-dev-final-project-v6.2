<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 011 11.07.20
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<html lang="${sessionScope.locale}">
<head>
    <link href="css/back.css" rel="stylesheet" type="text/css"/>
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

    <title><fmt:message key="local.users"/></title>
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
                <th style="text-align: center; vertical-align: middle" scope="col"><fmt:message key="local.email"/></th>
                <th style="text-align: center; vertical-align: middle" scope="col"><fmt:message key="local.leader-status"/></th>
                <th style="text-align: center; vertical-align: middle" scope="col"><fmt:message key="local.role"/></th>
                <th style="text-align: center; vertical-align: middle" scope="col"><fmt:message key="local.online"/></th>
                <th style="text-align: center; vertical-align: middle" scope="col"><fmt:message key="local.bann"/></th>
                <th style="text-align: center; vertical-align: middle" scope="col"></th>
            </tr>
            </thead>
            <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <th style="text-align: center; vertical-align: middle" scope="row">${user.idUser}</th>
                        <td style="text-align: center; vertical-align: middle">
                            <form action="controller" method="get">
                                <input type="hidden" name="idUser" value="${user.idUser}">
                                <button type="submit" name="command" value="view-user" class="btn btn-link">
                                    ${user.username}
                                </button>
                            </form>
                        </td>
                        <td style="text-align: center; vertical-align: middle">${user.email}</td>
                        <td style="text-align: center; vertical-align: middle">
                            <c:choose>
                                <c:when test="${user.leaderStatus == true}">
                                    <fmt:message key="local.yes"/>
                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="local.no"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td style="text-align: center; vertical-align: middle">
                            <c:choose>
                                <c:when test="${user.status == 'ADMIN'}">
                                    <fmt:message key="local.admin"/>
                                </c:when>
                                <c:when test="${user.status = 'STUDENT'}">
                                    <fmt:message key="local.student"/>
                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="local.trainer"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td style="text-align: center; vertical-align: middle">
                            <c:choose>
                                <c:when test="${user.onlineStatus == true}">
                                    <h7 class="text-success">
                                        <i style="color: green;" class="fa fa-check-circle"></i> <fmt:message key="local.online"/>
                                    </h7>
                                </c:when>
                                <c:otherwise>
                                    <h7 class="text-warning">
                                        <i class="fa fa-times" aria-hidden="true"></i> <fmt:message key="local.offline"/>
                                    </h7>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td style="text-align: center; vertical-align: middle">
                            <c:choose>
                                <c:when test="${user.bannedStatus == true}">
                                    <fmt:message key="local.yes"/>
                                </c:when>
                                <c:otherwise>
                                    <fmt:message key="local.no"/>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td style="text-align: left; vertical-align: middle">
                            <form action="controller" method="post">
                                <input type="hidden" name="idUser" value="${user.idUser}">
                                <input type="hidden" name="bann" value="${user.bannedStatus}"/>
                            <c:if test="${sessionScope.idUser != user.idUser}">
                                <c:choose>
                                    <c:when test="${user.bannedStatus == true}">
                                        <button type="submit" name="command" value="change-banned-status" class="btn btn-success">
                                            <i class="fa fa-unlock" aria-hidden="true"></i> <fmt:message key="local.unblock"/>
                                        </button>
                                    </c:when>
                                    <c:otherwise>
                                        <button type="submit" name="command" value="change-banned-status" class="btn btn-danger">
                                            <i class="fa fa-lock" aria-hidden="true"></i> <fmt:message key="local.block"/>
                                        </button>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>
                            </form>
                        </td>
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
