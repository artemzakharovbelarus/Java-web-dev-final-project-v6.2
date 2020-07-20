<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 015 15.07.20
  Time: 14:19
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

    <title><fmt:message key="local.record"/>: ${training.title}</title>
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
                        <h2 class="text-light">${training.title} </h2>
                        <div class="profile-status">
                        </div>
                        <c:if test="${training.trainingPhoto == null}">
                            <img style="height: 110px; width: 195px;" src="<c:url value="/img/default-tr.png"/>" alt="" class="profile-img img-responsive center-block">
                        </c:if>
                        <c:if test="${training.trainingPhoto != null}">
                            <c:choose>
                                <c:when test="${training.trainingPhoto eq ''}">
                                    <img style="height: 110px; width: 195px;" src="<c:url value="/img/default-tr.png"/>" alt="" class="profile-img img-responsive center-block">
                                </c:when>
                                <c:otherwise>
                                    <img style="height: 110px; width: 195px;" src="<c:url value="${training.trainingPhoto}"/>" alt="" class="profile-img img-responsive center-block">
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                        <div class="profile-message-btn center-block text-center">
                            <h5></h5>
                            <form action="controller" method="post">
                                <input type="hidden" name="idTraining" value="${training.idTraining}"/>
                                <c:choose>
                                    <c:when test="${sessionScope.status == 1}">
                                        <button type="submit" name="command" value="like" class="btn btn-outline-success">
                                            <i class="fa fa-thumbs-up" aria-hidden="true"></i>
                                                ${requestScope.likes-amount}
                                        </button>
                                        <nobr class="text-dark">пасхалка</nobr>
                                        <button type="submit" name="command" value="dislike" class="btn btn-outline-danger">
                                            <i class="fa fa-thumbs-down" aria-hidden="true"></i>
                                                ${requestScope.dislikes-amount}
                                        </button>
                                    </c:when>
                                    <c:otherwise>
                                        <button type="submit" name="command" value="like" class="btn btn-outline-success">
                                            <i class="fa fa-thumbs-up" aria-hidden="true"></i>
                                        </button>
                                        <nobr class="text-dark">пасхалка</nobr>
                                        <button type="submit" name="command" value="dislike" class="btn btn-outline-danger">
                                            <i class="fa fa-thumbs-down" aria-hidden="true"></i>
                                        </button>
                                    </c:otherwise>
                                </c:choose>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-lg-9 col-md-8 col-sm-8">
                    <div class="main-box clearfix">
                        <div class="profile-header">
                            <h2 class="text-light"><fmt:message key="local.query"/></h2>
                        </div>
                        <br>
                        <br>
                        <div class="row profile-user-info">
                            <div class="col-sm-8">
                                <div class="profile-user-details clearfix">
                                    <div class="profile-user-details-label ">
                                        <h5 class="text-light"><fmt:message key="local.Title"/>: ${training.title}</h5>
                                    </div>
                                </div>
                                <div class="profile-user-details clearfix">
                                    <div class="profile-user-details-label">
                                        <h5 class="text-light">
                                            <fmt:message key="local.start"/> <i class="fa fa-hourglass-start" aria-hidden="true"></i>:
                                            ${training.startDate}
                                        </h5>
                                    </div>
                                </div>
                                <div class="profile-user-details clearfix">
                                    <div class="profile-user-details-label">
                                        <h5 class="text-light">
                                            <fmt:message key="local.end"/> <i class="fa fa-hourglass-end" aria-hidden="true"></i>:
                                            ${training.endDate}
                                        </h5>
                                    </div>
                                </div>
                                <h4></h4>
                                <div class="input-group mb-3">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text" id="inputGroupFileAddon01"><fmt:message key="local.resume"/></span>
                                    </div>
                                    <div class="custom-file">
                                        <input type="file" class="custom-file-input" id="inputGroupFile01" aria-describedby="inputGroupFileAddon01">
                                        <label class="custom-file-label" for="inputGroupFile01"><fmt:message key="local.choose-file"/></label>
                                    </div>
                                </div>
                                <form action="controller" method="post">
                                    <input type="hidden" name="idTraining" value="${training.idTraining}">
                                    <input type="hidden" name="idUser" value="${sessionScope.idUser}">
                                    <button type="submit" name="command" value="enroll-training" class="btn btn-outline-success">
                                    <h7><fmt:message key="local.send"/> <i class="fa fa-share-square-o" aria-hidden="true"></i></h7>
                                    </button>
                                </form>
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
