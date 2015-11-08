<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>News Website Layout - free website template</title>
    <meta name="keywords" content="News Website Layout, free template, free website, CSS, HTML"/>
    <meta name="description" content="News Website Layout - free HTML CSS template provided by templatemo.com"/>
    <link href="/TaskManager/resources/css/templatemo_style.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" type="text/css" href="/TaskManager/resources/css/tabcontent.css"/>
    <script type="text/javascript" src="/TaskManager/resources/js/tabcontent.js">
        /***********************************************
         * Tab Content script v2.2- © Dynamic Drive DHTML code library (www.dynamicdrive.com)
         * This notice MUST stay intact for legal use
         * Visit Dynamic Drive at http://www.dynamicdrive.com/ for full source code
         ***********************************************/
    </script>
    <!--  HTML CSS Template Designed by w w w . t e m p l a t e m o . c o m  -->
</head>
    <body>
    <div id="templatemo_container">
    <tiles:insertAttribute name="header"/>
        <div id="templatemo_content">
            <tiles:insertAttribute name="leftColumn"/>
            <tiles:insertAttribute name="rightColumn"/>
        </div>
    <tiles:insertAttribute name="footer"/>
    </div>
    </body>
</html>


