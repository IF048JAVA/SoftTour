<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title><tiles:insertAttribute name="title" ignore="true"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="css/settings.css" rel="stylesheet">
    <link href="css/bootstrap.css" rel="stylesheet">
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap-theme.css" rel="stylesheet">
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
    <link href="css/profileStyle.css" rel="stylesheet">
    <link href="css/datepicker.css" rel="stylesheet">
    <link href="css/feedback.css" rel="stylesheet"/>
    <link href="css/select2.css" rel="stylesheet"/>
    <link href="css/select2-bootstrap.css" rel="stylesheet"/>
    <link href="css/star-rating.min.css" rel="stylesheet"/>
    <link href="css/hotels.css" rel="stylesheet"/>

</head>
<body bgcolor="#c0c0c0">
<div class="wrapper container" style="margin-bottom: 15px;">
    <tiles:insertAttribute name="header"/>
    <div class="container" id="white_main">
        <tiles:insertAttribute name="nav"/>
        <tiles:insertAttribute name="body"/>
        <tiles:insertAttribute name="footer"/>
    </div>
</div>
<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/bootstrap.js"></script>
<script src="js/bootstrap-datepicker-ua.js"></script>
<script src="js/calendar.js"></script>
<script src="js/selector.js"></script>
<script src="js/registrationControl.js"></script>
<script src="js/feedback.js"></script>
<script src="js/select2.min.js"></script>
<script src="js/star-rating.min.js"></script>
<script src="js/hotels.js"></script>
</body>
</html>