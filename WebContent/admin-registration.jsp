
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file = "menu.jsp" %>


<h1 class="main-title">Блатна реєстрація</h1>

<div class="row">
	
	<div class="col-md-6">
		<form method="post" action="<%= basePath %>/admin">
		  <div class="form-group">
		    <label for="nickname">Нікнейм</label>
		    <input type="text" class="form-control" id="nickname" name="nickname" required>
		  </div>
		  <div class="form-group">
		    <label for="email">Електронна пошта</label>
		    <input type="email" class="form-control" id="email" name="email" required>
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

<%@include file = "footer.jsp" %>
