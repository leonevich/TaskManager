<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div id="templatemo_main_rightcol">
    <sec:authorize ifAnyGranted="ROLE_ADMIN, ROLE_USER">
        <div class="templatemo_rcol_sectionwithborder">
            <div id="templatemo_newsletter_section">
                <h2><spring:message code="title.add.task"/></h2>
                <form:form method="post" action="${performerId}/add/task" commandName="task">

                    <table>
                        <tr>
                            <td><form:label path="name">
                                <spring:message code="label.task.name"/>
                            </form:label></td>
                            <td><form:input required="true" path="name"/></td>
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
                                                   value="<spring:message code="label.add.task"/>"/></td>
                        </tr>
                    </table>
                </form:form>
            </div>
        </div>
    </sec:authorize>
</div>