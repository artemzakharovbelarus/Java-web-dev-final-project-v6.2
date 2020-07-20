<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="locale"/>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item" style="padding-top: 5px;">
                <c:if test="${userPhoto == null}">
                    <img id="photo" src="<c:url value="/img/default.png"/>" style="width: 30px; height: 30px;"
                         alt="${alt}" class="m-auto rounded-circle">
                </c:if>
                <c:if test="${userPhoto != null}">
                    <c:choose>
                        <c:when test="${userPhoto eq ''}">
                            <img id="photo" src="<c:url value="/img/default.png"/>" style="width: 30px; height: 30px;"
                                 alt="${alt}" class="m-auto rounded-circle">
                        </c:when>
                        <c:otherwise>
                            <img id="photo" src="<c:url value="${userPhoto}"/>" style="width: 30px; height: 30px;"
                                 alt="${alt}" class="m-auto rounded-circle">
                        </c:otherwise>
                    </c:choose>
                </c:if>
            </li>
            <li class="nav-item">
                <a class="nav-link text-info" href="${pageContext.request.contextPath}/controller?command=forward-to-main">
                    <fmt:message key="local.home"/>
                    <i class="fa fa-home" aria-hidden="true"></i>
                    <span class="sr-only">(current)</span>
                </a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle text-info" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <fmt:message key="local.trainings"/>
                    <i class="fa fa-book" aria-hidden="true"></i>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=view-all-trainings">
                        <i class="fa fa-eye" aria-hidden="true"></i>
                        <fmt:message key="local.view-trainings"/>
                    </a>
                    <c:choose>
                        <c:when test="${sessionScope.status == 3}">
                            <form action="controller" method="get">
                                <button type="submit" name="command" value="view-all-trainer-trainings" class="dropdown-item">
                                    <i class="fa fa-id-card-o" aria-hidden="true"></i>
                                    <fmt:message key="local.my-trainings"/>
                                </button>
                            </form>
                        </c:when>
                        <c:when test="${sessionScope.status == 2}">
                            <form action="controller" method="get">
                                <input type="hidden" name="idUser" value="${sessionScope.idUser}"/>
                                <button type="submit" name="command" value="view-all-queries" class="dropdown-item">
                                    <i class="fa fa-share-square-o" aria-hidden="true"></i>
                                    <fmt:message key="local.queries"/>
                                </button>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <form action="controller" method="get">
                                <input type="hidden" name="idUser" value="${sessionScope.idUser}"/>
                                <button type="submit" name="command" value="view-all-queries" class="dropdown-item">
                                    <i class="fa fa-plus" aria-hidden="true"></i>
                                    <fmt:message key="local.add-training"/>
                                </button>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </div>
            </li>
            <c:if test="${sessionScope.status == 1}">
                <li class="nav-item">
                    <a class="nav-link text-info" href="${pageContext.request.contextPath}/controller?command=view-all-users">
                        <fmt:message key="local.users"/>
                        <i class="fa fa-users" aria-hidden="true"></i>
                        <span class="sr-only">(current)</span>
                    </a>
                </li>
            </c:if>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle text-info" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <fmt:message key="local.language"/>
                    <i class="fa fa-language" aria-hidden="true"></i>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <form action="controller" method="post">
                        <input type="hidden" name="locale" value="en"/>
                        <button type="submit" name="command" value="change-locale" class="dropdown-item">
                            <img src="<c:url value='/img/eng.jpg'/>" style="width: 20px; height: 20px;">
                            <fmt:message key="local.eng"/>
                        </button>
                    </form>
                    <form action="controller" method="post">
                        <input type="hidden" name="locale" value="by"/>
                        <button type="submit" name="command" value="change-locale" class="dropdown-item">
                            <img src="<c:url value='/img/bel.jpg'/>" style="width: 20px; height: 20px;">
                            <fmt:message key="local.by"/>
                        </button>
                    </form>
                    <form action="controller" method="post">
                        <input type="hidden" name="locale" value="ru"/>
                        <button type="submit" name="command" value="change-locale" class="dropdown-item">
                            <img src="<c:url value='/img/rus.jpg'/>" style="width: 20px; height: 20px;">
                            <fmt:message key="local.ru"/>
                        </button>
                    </form>
                    </form>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle text-info" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <fmt:message key="local.settings"/>
                    <i class="fa fa-cog" aria-hidden="true"></i>
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#">
                        <i class="fa fa-address-card-o" aria-hidden="true"></i>
                        <fmt:message key="local.profile"/>
                    </a>
                    <a class="dropdown-item" href="#">
                        <i class="fa fa-building-o" aria-hidden="true"></i>
                        <fmt:message key="local.about-us"/>
                    </a>
                    <div class="dropdown-divider"></div>
                    <form action="controller" method="get">
                        <input type="hidden" name="online-status" value="${online}"/>
                        <input type="hidden" name="idUser" value="${idUser}"/>
                        <button type="submit" name="command" value="sign-out" class="dropdown-item">
                            <i class="fa fa-sign-out" aria-hidden="true"></i>
                            <fmt:message key="local.sign-out"/>
                        </button>
                    </form>
                </div>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="<fmt:message key="local.search"/>"
                   aria-label="Search">
            <button class="btn btn-outline-info my-2 my-sm-0" type="submit">
                <i class="fa fa-search" aria-hidden="true"></i>
                <fmt:message key="local.search"/>
            </button>
        </form>
    </div>
</nav>
