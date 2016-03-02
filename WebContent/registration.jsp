<% String errors = (String) request.getAttribute("errors"); %>
<% String nickName = (String) request.getAttribute("nickname"); %>
<% String email = (String) request.getAttribute("email"); %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file = "header.jsp" %>

<%@include file = "menu.jsp" %>

<div class="container">
	<h1 class="main-title">Реєстрація</h1>
	
	<% if (errors != null && !errors.isEmpty()) { %>
		<div class="alert alert-danger" role="alert">
		  <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
		  <span class="sr-only">Error:</span>
		  <%= errors %>
		</div>
	<% } %>
	
	<div class="row">
		
		<div class="col-md-8">
			<form method="post" action="<%= basePath %>/registration">
			  <div class="form-group">
			    <label for="nickname">Нікнейм</label>
			    <input type="text" class="form-control" id="nickname" name="nickname" required 
			    	value="<%= nickName != null? nickName : "" %>">
			  </div>
			  <div class="form-group">
			    <label for="email">Електронна пошта</label>
			    <input type="email" class="form-control" id="email" name="email" required
			    	value="<%= email != null? email : "" %>">
			  </div>
			  <div class="form-group">
			    <label for="password">Пароль</label>
			    <input type="password" class="form-control" id="password" name="password" required>
			  </div>
			  <div class="form-group">
			    <label for="repeat-password">Повторіть пароль</label>
			    <input type="password" class="form-control" id="repeat-password" name="repeat-password" required>
			  </div>
			  
			 <button type="submit" class="btn btn-default">Зареєструватись</button>
			</form>
		</div>
		
	</div>
</div>