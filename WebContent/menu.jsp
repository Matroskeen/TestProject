<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="container" id="nav-container">
	<ul class="nav nav-pills top-menu">
	  <li role="presentation" class="active"><a href="<%= basePath %>/">Головна</a></li>
	  <li role="presentation"><a href="<%= basePath %>/tournaments">Турніри</a></li>
	  <li role="presentation"><a href="<%= basePath %>/media">Галерея</a></li>
	  <li role="presentation"><a href="<%= basePath %>/contact">Контакти</a></li>
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
	  
	</ul>
</div>
