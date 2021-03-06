<%@ page import="com.matroskeen.beans.UserBean" %>

<% String basePath = request.getContextPath(); %>
<% UserBean user = (UserBean) session.getAttribute("user"); %>
<% String status = (String) request.getAttribute("status"); %>
<% String message = (String) request.getAttribute("message"); %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Test</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="<%= basePath %>/css/bootstrap.min.css">
<link href="<%= basePath %>/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />

<link rel="stylesheet" href="<%= basePath %>/css/style.css">

<!-- js libraries -->
<script  src="<%= basePath %>/js/jquery.min.js"></script>
<script src="<%= basePath %>/js/bootstrap.min.js"></script>
<script src="<%= basePath %>/js/fileinput.min.js"></script>

<!-- Custom javascripts variables -->
<script>var basePath = "<%= basePath %>";</script>

<!-- Custom javascripts -->

<script src="<%= basePath %>/js/script.js"></script>

</head>
	<body>
