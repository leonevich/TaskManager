<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div id="templatemo_main_leftcol">

    <%-- <div id="templatemo_topnews_one">
         <h2>< c:out value="${}"/></h2>

         <h3 style="color: #999999"><c:out value="${requestScope.news.date}"/></h3>

         <h3 style="color: #000000"><p><c:out value="${requestScope.news.textNews}"/></p></h3>
     </div>
     <div>
         <h2>Обсуждение</h2>
     </div>--%>


    <sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
        <div class="templatemo_mesage">
            <a href="/TaskManager/login"><spring:message code="label.login"/></a>
        </div>
    </sec:authorize>


    <h1><spring:message code="label.tasks"/></h1>
    <c:if test="${!empty taskList}">
        <table class="data">
            <tr>
                <th width="16%"><h2><spring:message code="label.task.name"/></h2></th>
                <th width="16%"><h2><spring:message code="label.task.status"/></h2></th>
                <th width="16%"><h2><spring:message code="label.task.added"/></h2></th>
                <th width="16%"><h2><spring:message code="label.task.type"/></h2></th>
                <th width="16%"><h2><spring:message code="label.task.priority"/></h2></th>
                <th width="1%">&nbsp;</th>
            </tr>
            <c:forEach items="${taskList}" var="task">
                <tr>
                    <td align="center">${task.name}</td>
                    <td align="center">${task.status}</td>
                    <td align="center">${task.creationTime}</td>
                    <td align="center">${task.type}</td>
                    <td align="center">${task.priority}</td>
                    <sec:authorize ifAnyGranted="ROLE_ADMIN">
                        <td align="center"><a href="/TaskManager/performer/${performerId}/edit/task/${task.id}"><spring:message
                                code="label.edit"/></a></td>
                        <td align="center"><a href="/TaskManager/performer/${performerId}/delete/task/${task.id}"><spring:message
                                code="label.delete"/></a></td>
                    </sec:authorize>
                </tr>
            </c:forEach>
        </table>
    </c:if>
</div>
