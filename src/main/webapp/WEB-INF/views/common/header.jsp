<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<div id="templatemo_top_panel">
    <div id="templatemo_sitetitle">
        <spring:message code="label.title"/>
    </div>

    <div id="templatemo_currentdate">
        <script type="text/javascript">
            <!--
            var m_names = new Array("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
            var d_names = new Array("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");

            var currentTime = new Date()
            var day = currentTime.getDay()
            var month = currentTime.getMonth()
            var date = currentTime.getDate()
            var year = currentTime.getFullYear()
            document.write(d_names[day] + ", " + date + " " + m_names[month] + " " + year + ", ")
            var currentTime = new Date()
            var hours = currentTime.getHours()
            var minutes = currentTime.getMinutes()
            if (minutes < 10) {
                minutes = "0" + minutes
            }
            document.write(hours + ":" + minutes + " ")
            if (hours > 11) {
                document.write("PM")
            } else {
                document.write("AM")
            }
            //-->
        </script>
        <br/>

        <div>
            <span style="float: right">
    <a href="?lang=en">en</a>
    |
    <a href="?lang=ru">ru</a>
</span>

        </div>
    </div>
</div>

<div id="templatemo_menu">
    <ul>
        <li><a href="/TaskManager/" ><spring:message code="label.home"/></a></li>
        <li><a href="/TaskManager/all/task"><spring:message code="label.all.task"/></a></li>
        <li><a href="/TaskManager/tasks/generator"><spring:message code="label.generate.data"/></a></li>
        <sec:authorize ifAnyGranted="ROLE_USER,ROLE_ADMIN">
            <li><a href="/TaskManager/logout"><spring:message code="label.logout"/></a></li>
        </sec:authorize>

    </ul>
</div>
