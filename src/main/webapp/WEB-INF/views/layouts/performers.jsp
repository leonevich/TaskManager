<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<script type="text/javascript" src="/resources/js/tabcontent.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div id="templatemo_main_leftcol">
    <div id="templatemo_select_page">
        <sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
            <div class="templatemo_mesage">
                <a href="/TaskManager/login"><spring:message code="label.login"/></a>
            </div>
        </sec:authorize>
        <h1><spring:message code="label.performers"/></h1>
        <c:if test="${!empty performerList}">
            <table class="data">
                <tr>
                    <th width="30%"><h2><spring:message code="label.performer.name"/></h2></th>
                    <th width="30%"><h2><spring:message code="label.performer.status"/></h2></th>
                    <th width="30%"><h2><spring:message code="label.performer.type"/></h2></th>
                    <th width="1%">&nbsp;</th>
                </tr>
                <c:forEach items="${performerList}" var="performer">
                    <tr>
                        <td align="center"><a href="performer/${performer.id}"><c:out value="${performer.name}"/></a></td>
                        <td align="center"><a href="performer/${performer.id}"><c:out value="${performer.status}"/></a></td>
                        <td align="center"><a href="performer/${performer.id}"><c:out value="${performer.type}"/></a></td>
                        <sec:authorize ifAnyGranted="ROLE_ADMIN">
                            <td align="center"><a href="edit/performer/${performer.id}"><spring:message
                                    code="label.edit"/></a></td>
                            <td align="center"><a href="delete/performer/${performer.id}"><spring:message
                                    code="label.delete"/></a></td>
                        </sec:authorize>
                    </tr>
                </c:forEach>
            </table>
        </c:if>
    </div>
</div>

