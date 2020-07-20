<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 012 12.07.20
  Time: 22:36
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
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet">
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale"/>

    <title><fmt:message key="local.editing-training"/></title>
</head>
<boby>
    <div class="sticky-top" style="text-align: center; padding-bottom: 20px; " role=banner>
        <u:nav/>
    </div>
    <div style="width: 15%; float: left; background-color: blue;" role=complementary>
    </div>
    <div style="width: 150px; float: right; background-color: green">
    </div>
    <div style="height: 900px; margin-bottom: 15px; margin-left: 15%; margin-right: 200px;" class="bg-dark" role=main>
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
                        <div>

                        </div>
                    </div>
                </div>
                <div class="col-lg-9 col-md-8 col-sm-8">
                    <div class="main-box clearfix">
                        <div class="profile-header">
                            <h2 class="text-light"><fmt:message key="local.editing-training"/></h2>
                        </div>
                        <br>
                        <br>
                        <div class="row profile-user-info">
                            <div class="col-sm-8">
                                <form action="controller" method="post">
                                    <input type="hidden" name="idTraining" value="${training.idTraining}"/>
                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text" id="basic-addon3">
                                                <fmt:message key="local.Title"/>:
                                            </span>
                                        </div>
                                        <input type="text" name="title" class="form-control" id="basic-url"
                                               aria-describedby="basic-addon3" placeholder="${training.title}">
                                    </div>
                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text" id="basic-addon3">
                                                <fmt:message key="local.requirements"/>:
                                            </span>
                                        </div>
                                        <input type="text" name="requirements" class="form-control" id="basic-url"
                                               aria-describedby="basic-addon3">
                                    </div>
                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text" id="basic-addon3">
                                                <h7><fmt:message key="local.City"/> <i class="fa fa-globe" aria-hidden="true"></i>:</h7>
                                            </span>
                                        </div>
                                        <input type="text" name="city" class="form-control" id="basic-url"
                                               aria-describedby="basic-addon3">
                                    </div>
                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text" id="basic-addon3">
                                                <h7><fmt:message key="local.duration"/> <i class="fa fa-clock-o" aria-hidden="true"></i>:</h7>
                                            </span>
                                        </div>
                                        <input type="number" min="1" max="30" name="hours-amount" class="form-control" id="basic-url"
                                               aria-describedby="basic-addon3">
                                    </div>
                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text" id="basic-addon3">
                                                <fmt:message key="local.min-members"/>:
                                            </span>
                                        </div>
                                        <input type="number" min="1" max="50" name="min-members" class="form-control" id="basic-url"
                                               aria-describedby="basic-addon3">
                                    </div>
                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text" id="basic-addon3">
                                                <fmt:message key="local.max-members"/>:
                                            </span>
                                        </div>
                                        <input type="number" min="1" max="50" name="max-members" class="form-control" id="basic-url"
                                               aria-describedby="basic-addon3">
                                    </div>
                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text" id="basic-addon3">
                                                <h7><fmt:message key="local.start"/> <i class="fa fa-hourglass-start" aria-hidden="true"></i>:</h7>
                                            </span>
                                        </div>
                                        <input type="date" name="start-date" class="form-control" id="basic-url"
                                               aria-describedby="basic-addon3">
                                    </div>
                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text" id="basic-addon3">
                                                <h7><fmt:message key="local.end"/> <i class="fa fa-hourglass-end" aria-hidden="true"></i>:</h7>
                                            </span>
                                        </div>
                                        <input type="date" name="end-date" class="form-control" id="basic-url"
                                               aria-describedby="basic-addon3">
                                    </div>
                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text" id="basic-addon3">
                                                <fmt:message key="local.id-trainer"/>:
                                            </span>
                                        </div>
                                        <input type="number" name="idTrainer" class="form-control" id="basic-url"
                                               aria-describedby="basic-addon3">
                                    </div>
                                    <div class="input-group">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text">
                                                <fmt:message key="local.description"/>:
                                            </span>
                                        </div>
                                        <textarea rows="5" name="information" class="form-control" aria-label="With textarea">
                                        </textarea>
                                    </div>
                                    <h4></h4>
                                    <div class="input-group mb-3">
                                        <div class="input-group-prepend">
                                            <span class="input-group-text" id="inputGroupFileAddon01"><fmt:message key="local.photo"/></span>
                                        </div>
                                        <div class="custom-file">
                                            <input type="file" class="custom-file-input" id="inputGroupFile01" aria-describedby="inputGroupFileAddon01">
                                            <label class="custom-file-label" for="inputGroupFile01"><fmt:message key="local.choose-file"/></label>
                                        </div>
                                    </div>
                                    <button type="submit" name="command" value="edit-training" class="btn btn-outline-success">
                                        <h7><fmt:message key="local.save"/> <i class="fa fa-floppy-o" aria-hidden="true"></i></h7>
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
