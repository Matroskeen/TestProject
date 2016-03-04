<% String status = (String) request.getAttribute("status"); %>
<% String message = (String) request.getAttribute("message"); %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file = "header.jsp" %>

<%@include file = "menu.jsp" %>

<div class="container">

	<% if (message != null && !message.isEmpty()) { %>
		<div class="alert alert-<%= status %>" role="alert">
		  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
		  <span class="sr-only">Error:</span>
		  <%= message %>
		</div>
	<% } %>
	<h1>Home page!</h1>
</div>
