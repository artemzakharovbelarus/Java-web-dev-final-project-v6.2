<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 10.06.20
  Time: 15:27
  To change this template use File | Settings | File Templates.
--%>
<html lang="${sessionScope.locale}">
<head>
    <link href="css/back.css" rel="stylesheet" type="text/css"/>
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

    <title><fmt:message key="local.trainings"/></title>
</head>
<body class="back">
    <div class="sticky-top" style="text-align: center; padding-bottom: 20px; " role=banner>
        <u:nav/>
    </div>
    <div style="width: 15%; float: left; background-color: blue;" role=complementary>
    </div>
    <div style="width: 200px; float: right; background-color: green">
    </div>
    <div style="margin-left: 15%; margin-right: 200px;  " role=main>
        <div class="container">
            <div class="row">
                <c:forEach items="${trainings}" var="training">
                        <c:choose>
                            <c:when test="${sessionScope.status == 1}">
                                <div class="col-md-4">
                                    <div class="card mb-4 box-shadow bg-dark">
                                        <c:if test="${training.trainingPhoto == null}">
                                            <img src="<c:url value="/img/default-tr.png"/>"
                                                 class="card-img-top avatar img-circle img-thumbnail"
                                                 data-src="holder.js/100px225?theme=thumb&bg=55595c&fg=eceeef&text=Thumbnail"
                                                 alt="Card image cap"
                                                 style="height: 200px; width: 290px;">
                                        </c:if>
                                        <c:if test="${training.trainingPhoto != null}">
                                            <c:choose>
                                                <c:when test="${training.trainingPhoto eq ''}">
                                                    <img src="<c:url value="/img/default-tr.png"/>"
                                                         class="card-img-top avatar img-circle img-thumbnail"
                                                         data-src="holder.js/100px225?theme=thumb&bg=55595c&fg=eceeef&text=Thumbnail"
                                                         alt="Card image cap"
                                                         style="height: 200px; width: 290px;">
                                                </c:when>
                                                <c:otherwise>
                                                    <img src="data:image/*;base64, ${training.trainingPhoto}"
                                                         class="card-img-top bg-dark avatar img-circle img-thumbnail"
                                                         data-src="holder.js/100px225?theme=thumb&bg=55595c&fg=eceeef&text=Thumbnail"
                                                         alt="Card image cap"
                                                         style="height: 200px; width: 290px; border-color: #3b3939;">
                                                </c:otherwise>
                                            </c:choose>
                                        </c:if>
                                        <div class="card-body bg-dark">
                                            <p class="card-text text-white">${training.title}</p>
                                            <div class="d-flex justify-content-between align-items-center">
                                                <form action="controller" method="get">
                                                    <input type="hidden" name="idTraining" value="${training.idTraining}"/>
                                                    <div class="btn-group-vertical">
                                                        <button type="submit" name="command" value="view-training" class="btn btn-outline-info">
                                                            <i class="fa fa-eye" aria-hidden="true"></i>
                                                            <fmt:message key="local.view"/>
                                                        </button>
                                                        <button type="submit" name="command" value="forward-edit-training" class="btn btn-outline-warning">
                                                            <i class="fa fa-wrench" aria-hidden="true"></i>
                                                            <fmt:message key="local.edit"/>
                                                        </button>
                                                    </div>
                                                </form>
                                                <small class="text-muted">
                                                    <h7 class="text-white">
                                                        <fmt:message key="local.start"/>
                                                        <br>${training.startDate}
                                                    </h7>
                                                    <br>
                                                    <c:choose>
                                                        <c:when test="${training.deletedStatus == true}">
                                                            <h7 class="text-danger">
                                                                    <fmt:message key="local.deleted"/>
                                                            </h7>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <h7 class="text-success">
                                                                <fmt:message key="local.not-deleted"/>
                                                            </h7>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </small>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${training.deletedStatus == false}">
                                    <div class="col-md-4">
                                        <div class="card mb-4 box-shadow bg-dark">
                                            <c:if test="${training.trainingPhoto == null}">
                                                <img src="<c:url value="/img/default-tr.png"/>"
                                                     class="card-img-top avatar img-circle img-thumbnail"
                                                     data-src="holder.js/100px225?theme=thumb&bg=55595c&fg=eceeef&text=Thumbnail"
                                                     alt="Card image cap"
                                                     style="height: 200px; width: 290px;">
                                            </c:if>
                                            <c:if test="${training.trainingPhoto != null}">
                                                <c:choose>
                                                    <c:when test="${training.trainingPhoto eq ''}">
                                                        <img src="<c:url value="/img/default-tr.png"/>"
                                                             class="card-img-top avatar img-circle img-thumbnail"
                                                             data-src="holder.js/100px225?theme=thumb&bg=55595c&fg=eceeef&text=Thumbnail"
                                                             alt="Card image cap"
                                                             style="height: 200px; width: 290px;">
                                                    </c:when>
                                                    <c:otherwise>
                                                        <img src="data:image/*;base64, ${training.trainingPhoto}"
                                                             class="card-img-top bg-dark avatar img-circle img-thumbnail"
                                                             data-src="holder.js/100px225?theme=thumb&bg=55595c&fg=eceeef&text=Thumbnail"
                                                             alt="Card image cap"
                                                             style="height: 200px; width: 290px; border-color: #3b3939;">
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:if>
                                            <div class="card-body bg-dark">
                                                <p class="card-text text-white">${training.title}</p>
                                                <div class="d-flex justify-content-between align-items-center">
                                                    <form action="controller" method="get">
                                                        <input type="hidden" name="idTraining" value="${training.idTraining}"/>
                                                        <div class="btn-group-vertical">
                                                            <button type="submit" name="command" value="view-training" class="btn btn-outline-info">
                                                                <i class="fa fa-eye" aria-hidden="true"></i>
                                                                <fmt:message key="local.view"/>
                                                            </button>
                                                        </div>
                                                    </form>
                                                    <small class="text-muted">
                                                        <h7 class="text-white">
                                                            <fmt:message key="local.start"/>
                                                            <br>${training.startDate}
                                                        </h7>
                                                        <br>
                                                    </small>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                </c:forEach>
            </div>
    </div>
    </div>
    <div class="sticky-bottom" style="height: 70px; clear: both; background-color: black" role=contentinfo>
        <u:footer/>
    </div>
</body>
</html>
