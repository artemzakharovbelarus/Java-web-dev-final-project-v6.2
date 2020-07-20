<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 01.07.20
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<html lang="${sessionScope.locale}">
<head>
    <link rel="stylesheet" type="text/css"
          href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/solid.css">
    <link rel="stylesheet" type="text/css" href="css/style1.css">
    <script src="js/validator.js"></script>
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale"/>

    <title><fmt:message key="local.sign-in"/></title>
</head>
<body>
<div class="modal-dialog text-center">
    <div class="col-sm-9 main-section">
        <div class="modal-content">

            <div class="col-12 user-img">
                <img src="<c:url value="/img/face.png"/>">
            </div>

            <div class="col-12 form-input">
                <form action="controller" method="post" oninput="return validateSignIn()">
                    <div class="input-group flex-nowrap">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="addon-wrapping">
                                    <i class="fa fa-user" aria-hidden="true"></i>
                            </span>
                        </div>
                        <input type="text" name="username" id="username" class="form-control"
                               placeholder="<fmt:message key="local.username"/>" aria-label="Username"
                               aria-describedby="addon-wrapping">
                    </div>
                    <br>
                    <div class="input-group flex-nowrap">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="addon-wrapping">
                                <i class="fa fa-lock" aria-hidden="true"></i>
                            </span>
                        </div>
                        <input type="password" name="password" id="password" class="form-control"
                               placeholder="<fmt:message key="local.password"/>" aria-label="Password"
                               aria-describedby="addon-wrapping">
                    </div>
                    <br>
                    <c:if test="${sign_in_message != null}">
                        <p style="color: #FF0000;"><fmt:message key="message.invalid-sign-in-values"/><p>
                    </c:if>
                    <c:if test="${user_banned_message != null}">
                        <p style="color: #FF0000;"><fmt:message key="message.user-banned"/></p>
                    </c:if>
                    <c:if test="${success_sign_up != null}">
                        <p style="color: #00FF00;"><fmt:message key="message.success-sign-up"/></p>
                    </c:if>
                    <input type="hidden" name="command" value="sign-in">
                    <button type="submit" class="btn btn-success">
                        <fmt:message key="local.sign-in"/>
                    </button>
                </form>
            </div>
            <div class="col-12 forgot">
                <p class="add">
                    <a href="restore-password"><fmt:message key="message.forgot-password"/></a>
                </p>
                <p class="add">
                    <a href="sign-up"><fmt:message key="message.no-account"/> <fmt:message key="message.sign-up"/></a>
                </p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
