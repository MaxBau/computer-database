<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>EPF Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="<c:url value="/css/bootstrap.min.css" />" rel="stylesheet"
	media="screen">
<link href="<c:url value="/css/main.css" />" rel="stylesheet"
	media="screen">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-2.1.0.min.js"></script>
</head>
<body>
	<header class="topbar">
		<h1 class="fill">
			<a href="dashboard"> <spring:message code="app.title"
					text="default text" /></a>
		</h1>
		<sec:authorize ifNotGranted="ROLE_ANONYMOUS">
			<a href="<c:url value="logout" />" id="logoutSecurity" class="btn danger"
				style="float: right"> Logout</a>
		</sec:authorize>
	</header>