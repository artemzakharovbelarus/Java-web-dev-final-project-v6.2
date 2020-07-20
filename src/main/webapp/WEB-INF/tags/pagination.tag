<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ tag isELIgnored="false" %>
<nav aria-label="Page navigation example">
    <ul class="pagination">
        <li class="page-item">
            <a class="page-link" href="#" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>
        <li class="page-item"><a class="page-link" href="#">1</a></li>
        <li class="page-item"><a class="page-link" href="#">2</a></li>
        <li class="page-item"><a class="page-link" href="#">3</a></li>
        <li class="page-item">
            <a class="page-link" href="#" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
            </a>
        </li>
    </ul>
</nav>
<nav aria-label="...">
    <ul class="pagination justify-content-center">
        <c:choose>
            <c:when test="${currentPage == 1}">
                <c:choose>
                    <c:when test="${numberOfPages == 0}">
                        <li class="page-item disabled">
                            <a class="page-link " href="/blog/main/${currentPage-1}">&laquo; </a>
                        </li>
                        <li class="page-item active">
                            <a class="page-link" href="/blog/main/${currentPage}">${currentPage}<span
                                    class="sr-only"></span></a>
                        </li>
                        <li class="page-item disabled"><a class="page-link"
                                                          href="/blog/main/${currentPage+1}">${currentPage+1}</a></li>
                        <li class="page-item disabled">
                            <a class="page-link" href="/blog/main/${currentPage+1}">&raquo;</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item disabled">
                            <a class="page-link " href="/blog/main/${currentPage-1}">&laquo; </a>
                        </li>
                        <li class="page-item active">
                            <a class="page-link" href="/blog/main/${currentPage}">${currentPage}<span
                                    class="sr-only"></span></a>
                        </li>
                        <li class="page-item"><a class="page-link"
                                                 href="/blog/main/${currentPage+1}">${currentPage+1}</a></li>
                        <li class="page-item">
                            <a class="page-link" href="/blog/main/${currentPage+1}">&raquo;</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:when test="${currentPage == numberOfPages}">
                <li class="page-item ">
                    <a class="page-link" href="/blog/main/${currentPage-1}">&raquo; </a>
                </li>
                <li class="page-item"><a class="page-link" href="/blog/main/${currentPage-1}">${currentPage-1}</a></li>
                <li class="page-item active">
                    <a class="page-link" href="#">${currentPage}<span class="sr-only"></span></a>
                </li>
                <li class="page-item disabled">
                    <a class="page-link" href="/blog/main/${currentPage+1}">&raquo; </a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="page-item disabled">
                    <a class="page-link" href="/blog/main/${currentPage-1}" tabindex="-1"><ftm:message key="prev_page"
                                                                                                       bundle="${value}"/> </a>
                </li>
                <li class="page-item"><a class="page-link" href="/blog/main/${currentPage-1}">${currentPage-1}</a></li>
                <li class="page-item active">
                    <a class="page-link" href="#">${currentPage}<span class="sr-only"></span></a>
                </li>
                <li class="page-item"><a class="page-link" href="/blog/main/${currentPage+1}">${currentPage+1}</a></li>
                <li class="page-item">
                    <a class="page-link" href="/blog/main/${currentPage+1}">&raquo;</a>
                </li>
            </c:otherwise>
        </c:choose>
    </ul>
</nav>
