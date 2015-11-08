<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div id="templatemo_main_leftcol">
    <sec:authorize ifAnyGranted="ROLE_ADMIN">
        <h2><spring:message code="title.edit.task"/></h2>
        <form:form method="post" action="update" commandName="task">
            <form:input type="hidden" path="id"/>
            <form:input type="hidden" path="creationTime"/>
            <form:input type="hidden" path="type"/>
            <table>
                <tr>
                    <td><form:label path="name">
                        <spring:message code="label.task.name"/>
                    </form:label></td>
                    <td><form:input required="true" path="name"/></td>
                </tr>
                <tr>
                    <td><form:label path="status">
                        <spring:message code="label.task.status"/>
                    </form:label></td>
                    <td><form:select required="true" path="status">
                        <form:option value="" label="--Please Select" selected="selected" disabled="true"/>
                        <form:options items="${statusValues}"/>
                    </form:select></td>
                </tr>
                <tr>
                    <td><form:label path="priority">
                        <spring:message code="label.task.priority"/>
                    </form:label></td>
                    <td><form:select required="true" path="priority">
                        <form:option value="" label="--Please Select" selected="selected" disabled="true"/>
                        <form:options items="${listOfAvailablePriorityTask}"/>
                    </form:select></td>
                </tr>
                <tr>
                    <td colspan="2"><input class="button" type="submit"
                                           value="<spring:message code="label.save"/>"/></td>
                </tr>
            </table>
        </form:form>
    </sec:authorize>
</div>
