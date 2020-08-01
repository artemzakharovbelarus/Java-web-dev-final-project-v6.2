<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<%@ page isELIgnored="false" %><%--
  Created by IntelliJ IDEA.
  User: user
  Date: 30.07.20
  Time: 03:34
  To change this template use File | Settings | File Templates.
--%>
<html>
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
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale"/>

    <title><fmt:message key="local.profile-editing"/></title>
</head>
<boby>
    <div class="sticky-top" style="text-align: center; padding-bottom: 20px; " role=banner>
        <u:nav/>
    </div>
    <div style="width: 15%; float: left; background-color: blue;" role=complementary>
    </div>
    <div style="width: 150px; float: right; background-color: green">
    </div>
    <div style="height: 500px; margin-bottom: 15px; margin-left: 15%; margin-right: 200px;" class="bg-dark" role=main>
        <div class="container bootstrap snippets">
            <div class="row" id="user-profile">
                <div class="col-lg-3 col-md-4 col-sm-4">
                    <div class="main-box clearfix">
                        <h2 class="text-light">${user.username} </h2>
                        <div class="profile-status">
                            <c:choose>
                                <c:when test="${user.onlineStatus == true}">
                                    <h6 style="color: green;">
                                        <i class="fa fa-check-circle"></i>
                                        <fmt:message key="local.online"/>
                                    </h6>
                                </c:when>
                                <c:otherwise>
                                    <h6 class="text-warning">
                                        <i class="fa fa-times"></i>
                                        <fmt:message key="local.offline"/>
                                    </h6>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <c:if test="${user.userPhoto == null}">
                            <c:choose>
                                <c:when test="${user.sex == 1}">
                                    <img style="width: 200px; height: 200px;" src="<c:url value="/img/default-m.png"/>" alt="" class="profile-img img-responsive center-block">
                                </c:when>
                                <c:when test="${user.sex == 2}">
                                    <img style="width: 200px; height: 200px;" src="<c:url value="/img/default-w.png"/>" alt="" class="profile-img img-responsive center-block">
                                </c:when>
                                <c:otherwise>
                                    <img style="width: 200px; height: 200px;" src="<c:url value="/img/default.png"/>" alt="" class="profile-img img-responsive center-block">
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                        <c:if test="${user.userPhoto != null}">
                            <c:choose>
                                <c:when test="${user.userPhoto == ''}">
                                    <c:if test="${user.sex == 1}">
                                        <img style="width: 200px; height: 200px;" src="<c:url value="/img/default-m.png"/>" alt="" class="profile-img img-responsive center-block">
                                    </c:if>
                                    <c:if test="${user.sex == 2}">
                                        <img style="width: 200px; height: 200px;" src="<c:url value="/img/default-w.png"/>" alt="" class="profile-img img-responsive center-block">
                                    </c:if>
                                    <c:if test="${user.sex != 1 && user.sex != 2}">
                                        <img style="width: 200px; height: 200px;" src="<c:url value="/img/default.png"/>" alt="" class="profile-img img-responsive center-block">
                                    </c:if>
                                </c:when>
                                <c:otherwise>
                                    <img style="width: 200px; height: 200px;" src="<c:url value="${user.userPhoto}"/>" alt="" class="profile-img img-responsive center-block">
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                        <div class="profile-label" style="text-align: center">
                            <h4 class="text-light">${user.status}</h4>
                        </div>
                        <div class="profile-message-btn center-block text-center">
                            <c:if test="${user.status.idStatus == 2}">
                                <div class="btn-group-vertical">
                                    <button type="button" class="btn btn-outline-success">
                                        <i class="fa fa-file-code-o" aria-hidden="true"></i>
                                        <fmt:message key="local.trainer"/>
                                    </button>
                                    <button type="button" class="btn btn-outline-primary">
                                        <i class="fa fa-cogs" aria-hidden="true"></i>
                                        <fmt:message key="local.admin"/>
                                    </button>
                                    <c:if test="${user.leaderStatus == false}">
                                        <button type="button" class="btn btn-outline-secondary">
                                            <i class="fa fa-pencil" aria-hidden="true"></i>
                                            <fmt:message key="local.leader-status"/>
                                        </button>
                                    </c:if>
                                </div>
                            </c:if>
                        </div>
                    </div>
                </div>

                <div class="col-lg-9 col-md-8 col-sm-8">
                    <div class="main-box clearfix">
                        <div class="profile-header">
                            <h2 class="text-light"><fmt:message key="local.profile-editing"/></h2>
                        </div>
                        <br>
                        <br>
                        <div class="row profile-user-info">
                            <div class="col-sm-8">
                                <div class="profile-user-details clearfix">
                                    <div class="profile-user-details-label ">
                                        <h5 class="text-light"><fmt:message key="local.name"/>: ${user.name}</h5>
                                    </div>
                                </div>
                                <div class="profile-user-details clearfix">
                                    <div class="profile-user-details-label">
                                        <h5 class="text-light"><fmt:message key="local.surname"/>: ${user.surname}</h5>
                                    </div>
                                </div>
                                <div class="profile-user-details clearfix">
                                    <div class="profile-user-details-label">
                                        <h5 class="text-light"><fmt:message key="local.username"/>: ${user.username}</h5>
                                    </div>
                                </div>
                                <div class="profile-user-details clearfix">
                                    <div class="profile-user-details-label">
                                        <h5 class="text-light">
                                            <fmt:message key="local.email"/> <i class="fa fa-envelope-o" aria-hidden="true"></i>:
                                            ${user.email}
                                        </h5>
                                    </div>
                                </div>
                                <div class="profile-user-details clearfix">
                                    <div class="profile-user-details-label">
                                        <h5 class="text-light">
                                            <fmt:message key="local.birth-date"/>:
                                            ${user.birthDate}
                                        </h5>
                                    </div>
                                </div>
                                <div class="profile-user-details clearfix">
                                    <div class="profile-user-details-label">
                                        <c:choose>
                                            <c:when test="${user.sex == 1}">
                                                <h5 class="text-light">
                                                    <fmt:message key="local.sex"/>: <fmt:message key="local.male"/>
                                                </h5>
                                            </c:when>
                                            <c:otherwise>
                                                <h5 class="text-light">
                                                    <fmt:message key="local.sex"/>: <fmt:message key="local.female"/>
                                                </h5>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                                <div class="profile-user-details clearfix">
                                    <div class="profile-user-details-label">
                                        <h5 class="text-light">
                                            <fmt:message key="local.leader-status"/>:
                                            <c:choose>
                                                <c:when test="${user.leaderStatus == true}">
                                                    <fmt:message key="local.yes"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <fmt:message key="local.no"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </h5>
                                    </div>
                                </div>
                                <div class="profile-user-details clearfix">
                                    <div class="profile-user-details-label">
                                        <h5 class="text-light">
                                            <fmt:message key="local.bann"/>:
                                            <c:choose>
                                                <c:when test="${user.bannedStatus == true}">
                                                    <fmt:message key="local.yes"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <fmt:message key="local.no"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </h5>
                                    </div>
                                </div>
                                <div class="profile-user-details clearfix">
                                    <div class="profile-user-details-label">
                                        <h5 class="text-light">
                                            <i class="fa fa-github-square" aria-hidden="true"></i>:
                                            <a href="https://github.com/ArtemZakharovBY" target="_blank">
                                                ${user.githubLink}
                                            </a>
                                        </h5>
                                    </div>
                                </div>
                                <div class="profile-user-details clearfix">
                                    <div class="profile-user-details-label">
                                        <h5 class="text-light">
                                            <i class="fa fa-linkedin-square" aria-hidden="true"></i>:
                                            <a href="https://www.linkedin.com/in/artem-zakharov-751b78194/"
                                               target="_blank">
                                                ${user.linkedInLink}
                                            </a>
                                        </h5>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>
    <div class="sticky-bottom" style="height: 70px; clear: both; background-color: black" role=contentinfo>
        <u:footer/>
    </div>
</boby>
</html>
