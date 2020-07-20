<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag isELIgnored="false" %>
<fmt:setLocale value="${cookie['lang'].value}"/>
<fmt:setBundle basename="locale"/>
<footer>
    <div class="sticky-bottom" style="height: 70px; clear: both; background-color: black" role=contentinfo>
        <footer id="sticky-footer" class="py-4 bg-dark text-white-50">
            <div class="container text-center">
                <small><fmt:message key="local.copy"/> 2020. <fmt:message key="local.creator"/></small>
            </div>
        </footer>
    </div>
</footer>