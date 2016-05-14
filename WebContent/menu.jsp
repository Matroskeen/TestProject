<%@include file = "header.jsp" %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="container" id="nav-container">
	<ul class="nav nav-pills top-menu">
	  <li role="presentation" class="active"><a href="<%= basePath %>/">Головна</a></li>
	  <li role="presentation"><a href="<%= basePath %>/tournaments">Турніри</a></li>
	  <li role="presentation"><a href="<%= basePath %>/media">Галерея</a></li>
	  <li role="presentation"><a href="<%= basePath %>/contact">Контакти</a></li>
	  <% if (user == null) { %>
		  <% if (!request.getServletPath().contains("registration")) { %>
		  	<li role="presentation" class="pull-right"><a href="<%= basePath %>/registration">Реєстрація</a></li>
		  <% } %>
		  <li role="presentation" class="pull-right">
			<form class="navbar-form" method="post" action="<%= basePath %>/login">
				<input type="text" name="login" class="form-control" placeholder="Логін">
				<input type="password" name="password" class="form-control" placeholder="Пароль">
		  		<button type="submit" class="btn btn-primary">Увійти</button>
			</form>
		  <li>
	  <% } else { %>
	  	<li class="pull-right"><p>Welcome, <a href="<%= basePath %>/edit_profile"><%= user.getNickName() %></a></p></li>
	  <% } %>
	</ul>
</div>
<div class="container">
			<% if (message != null && !message.isEmpty()) { %>
				<div class="alert alert-<%= status %>" role="alert">
				  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
				  <span class="sr-only">Error:</span>
				  <%= message %>
				</div>
			<% } %>
