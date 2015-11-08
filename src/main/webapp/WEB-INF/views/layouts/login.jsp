<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div id="templatemo_main_leftcol">
    <div class="templatemo_leftcol_subcol">
        <h2>Authorization</h2>
        <c:if test="${not empty param.error}">
            <font color="red"> <spring:message code="label.loginerror"/>
                : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} </font>
        </c:if>
        <form method="POST" action="<c:url value="/j_spring_security_check"/>">

            <label for="name">Логин:</label><br/>
            <input type="text" name="j_username"
                   onBlur="if(this.value=='')this.value='name'"
                   onFocus="if(this.value=='name')this.value='' "><br/><br/>
            <label for="username">Пароль:</label><br/>
            <input type="password" name="j_password" required value="Пароль"
                   onBlur="if(this.value=='')this.value='Пароль'"
                   onFocus="if(this.value=='Пароль')this.value='' ">

            <div id="lower">
                <input type="checkbox" name="_spring_security_remember_me"><label class="check" for="checkbox">Запомнить меня</label>
                <input type="submit" value="Войти">
            </div>
        </form>
        <br/>
        <br/>
        <br/>
        <br/>
    </div>
</div>