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
    <link href="<c:url value='/img/plane.ico' />" rel="shortcut icon" type="image/x-icon">
	<link href="<c:url value='/css/settings.css' />" rel="stylesheet">
    <link href="<c:url value='/css/bootstrap.css' />" rel="stylesheet">
    <link href="<c:url value='/css/bootstrap.min.css' />" rel="stylesheet">
    <link href="<c:url value='/css/bootstrap-theme.css' />" rel="stylesheet">
    <link href="<c:url value='/css/bootstrap-theme.min.css' />" rel="stylesheet">
    <link href="<c:url value='/css/bootstrap-table.min.css' />" rel="stylesheet">
    <link href="<c:url value='/css/profileStyle.css' />" rel="stylesheet">
    <link href="<c:url value='/css/datepicker.css' />" rel="stylesheet">
    <link href="<c:url value='/css/feedback.css' />" rel="stylesheet"/>
    <link href="<c:url value='/css/select2.css' />" rel="stylesheet"/>
    <link href="<c:url value='/css/select2-bootstrap.css' />" rel="stylesheet"/>
    <link href="<c:url value='/css/star-rating.min.css' />" rel="stylesheet"/>
    <link href="<c:url value='/css/hotels.css' />" rel="stylesheet"/>
    <link href="<c:url value='/css/style.css' />" rel="stylesheet"/>
    <link href="<c:url value='/css/left_right_style.css' />" rel="stylesheet"/>
    <link href="<c:url value='/css/login.css' />" rel="stylesheet"/>
    <link href="<c:url value='/css/jquery-ui.css' />" rel="stylesheet"/>
    <link href="<c:url value='/css/jquery-ui.min.css' />" rel="stylesheet"/>
    <link href="<c:url value='/css/jquery.dataTables.min.css' />" rel="stylesheet"/>

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
<script src="<c:url value='/js/jquery.js' />" ></script>
<script src="<c:url value='/js/bootstrap.min.js' />" ></script>
<script src="<c:url value='/js/bootstrap.js' />" ></script>
<script src="<c:url value='/js/jquery-ui.js' />" ></script>
<script src="<c:url value='/js/jquery-ui.min.js' />" ></script>
<script src="<c:url value='/js/bootstrap-datepicker-ua.js' />" ></script>
<script src="<c:url value='/js/jquery.tmpl.js' />" ></script>
<script src="<c:url value='/js/calendar.js' />" ></script>
<script src="<c:url value='/js/selector.js' />" ></script>
<script src="<c:url value='/js/feedback.js' />" ></script>
<script src="<c:url value='/js/modal2.js' />" ></script>
<script src="<c:url value='/js/select2.min.js' />" ></script>
<script src="<c:url value='/js/star-rating.min.js' />" ></script>
<script src="<c:url value='/js/hotels.js' />" ></script>
<script src="<c:url value='/js/userFavoriteProfile.js' />" ></script>
<script src="<c:url value='/js/userHistoryProfile.js' />" ></script>
<script src="<c:url value='/js/userInfo.js' />" ></script>
<script src="<c:url value='/js/tours.js' />" ></script>
<script src="<c:url value='/js/equal_div.js' />" ></script>
<script src="<c:url value='/js/validator.js' />" ></script>
<script src="<c:url value='/js/jquery.twbsPagination.min.js' />" ></script>
<script src="<c:url value='/js/searchResult.js' />" ></script>
<script src="<c:url value='/js/star-rating.min.js' />" ></script>
<script src="<c:url value='/js/bootstrap-table.min.js' />" ></script>
<script src="<c:url value='/js/cityFromAutocomplete.js' />" ></script>
<script src="<c:url value='/js/transitButton.js' />" ></script>
<script src="<c:url value='/js/tourOrderModal.js' />" ></script>
<script src="<c:url value='/js/jquery.dataTables.min.js' />" ></script>
<script src="<c:url value='/js/searchForm.js' />" ></script>

</body>
</html>