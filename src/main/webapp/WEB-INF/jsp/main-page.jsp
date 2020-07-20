<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 08.06.20
  Time: 17:57
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
    <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale"/>

    <title><fmt:message key="local.main-page"/></title>
</head>
<boby>
    <div class="sticky-top" style="text-align: center; padding-bottom: 20px; " role=banner>
        <u:nav/>
    </div>
    <div style="width: 15%; float: left; background-color: blue;" role=complementary>
    </div>
    <div style="width: 200px; float: right; background-color: green">
    </div>
    <div style="margin-left: 15%; margin-right: 200px;" role=main>
        <div id="carouselExampleCaptions" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
                <li data-target="#carouselExampleCaptions" data-slide-to="0" class="active"></li>
                <li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
                <li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
            </ol>
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img style="height: 550px; width: 600px;" src="<c:url value="/img/default-news.jpg"/>"
                         class="d-block w-100" alt="...">
                    <div class="carousel-caption d-none d-md-block">
                        <h5>Мы работаем!</h5>
                        <p>Портал корпоративного образования запущен!</p>
                    </div>
                </div>
                <c:forEach items="${news}" var="newsItem">
                    <div class="carousel-item">
                        <c:if test="${newsItem.newsItemImage == null}">
                            <img style="height: 550px; width: 600px;" src="<c:url value="/img/no-image-news.png"/>"
                                 class="d-block w-100" alt="...">
                        </c:if>
                        <c:if test="${newsItem.newsItemImage != null}">
                            <c:choose>
                                <c:when test="${newsItem.newsItemImage == ''}">
                                    <img style="height: 550px; width: 600px;" src="<c:url value="/img/no-image-news.png"/>"
                                </c:when>
                                <c:otherwise>
                                    <img style="height: 550px; width: 600px;" src="<c:url value="${newsItem.newsItemImage}"/>"
                                         class="d-block w-100" alt="...">
                                </c:otherwise>
                            </c:choose>
                        </c:if>
                        <div class="carousel-caption d-none d-md-block">
                            <h5>${newsItem.newsIntro}</h5>
                            <p>${newsItem.newsItemText}</p>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <a class="carousel-control-prev" href="#carouselExampleCaptions" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselExampleCaptions" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
    </div>
    <div class="sticky-bottom" style="height: 70px; clear: both; background-color: black" role=contentinfo>
        <u:footer/>
    </div>
</boby>
</html>
