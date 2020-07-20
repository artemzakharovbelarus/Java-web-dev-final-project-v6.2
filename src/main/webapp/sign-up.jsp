<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 07.07.20
  Time: 12:26
  To change this template use File | Settings | File Templates.
--%>
<html lang="${sessionScope.locale}">
<head>
    <link rel="stylesheet" type="text/css"
          href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/solid.css">
    <link rel="stylesheet" type="text/css" href="css/style1.css">
    <fmt:setLocale value="${sessionScope.locale}"/>
    <fmt:setBundle basename="locale"/>

    <title><fmt:message key="local.sign-up"/></title>
</head>
<body>
    <div class="modal-dialog text-center">
    <div class="col-sm-9 main-section">
        <div class="modal-content">

            <div class="col-12 user-img">
                <img src="<c:url value="/img/face.png"/>">
            </div>

            <div class="col-12 form-input">
                <form action="controller" method="post">
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
                    <c:if test="${invalid_username != null}">
                        <br>
                        <p style="color: #FF0000;"><fmt:message key="local.invalid-username"/></p>
                    </c:if>
                    <br>
                    <div class="input-group flex-nowrap">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="addon-wrapping">
                                <i class="fa fa-envelope" aria-hidden="true"></i>
                            </span>
                        </div>
                        <input type="email" name="email" id="email" class="form-control"
                               placeholder="<fmt:message key="local.email"/>" aria-label="Email"
                               aria-describedby="addon-wrapping">
                    </div>
                    <c:if test="${invalid_email != null}">
                        <br>
                        <p style="color: #FF0000;"><fmt:message key="local.invalid-email"/></p>
                    </c:if>
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
                    <c:if test="${invalid_password != null}">
                        <br>
                        <p style="color: #FF0000;"><fmt:message key="local.invalid-password"/></p>
                    </c:if>
                    <br>
                    <div class="input-group flex-nowrap">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="addon-wrapping">
                                <i class="fa fa-lock" aria-hidden="true"></i>
                            </span>
                        </div>
                        <input type="password" name="confirmed-password" id="confirmed-password" class="form-control"
                               placeholder="<fmt:message key="local.password"/>" aria-label="Password"
                               aria-describedby="addon-wrapping">
                    </div>
                    <c:if test="${no_equals_passwords != null}">
                        <br>
                        <p style="color: #FF0000;"><fmt:message key="local.not-equals-passwords"/></p>
                    </c:if>
                    <br>
                    <input type="hidden" name="command" value="sign-up">
                    <button style="width: 200px;" type="submit" class="btn btn-success">
                        <fmt:message key="local.sign-up"/>
                    </button>
                </form>
            </div>
            <div class="col-12 forgot">
                <p class="add">
                    <a href="sign-in"><fmt:message key="local.have-account"/> <fmt:message key="local.sign-in"/></a>
                </p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
