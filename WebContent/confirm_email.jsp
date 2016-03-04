<% Boolean confirmed = (Boolean) request.getAttribute("confirmed"); %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file = "header.jsp" %>

<%@include file = "menu.jsp" %>

<div class="container" id="thanks-registration">
	<% if (confirmed != null && confirmed) { %>
		<h2 class="text-center">Пошта підтверджена!</h2>
		<h3 class="text-center"><small>Тепер вам доступна вся поточна інформація.</small></h3>
	<% } else { %>
		<h2 class="text-center">Дякуємо за реєстрацію!</h2>
		<h3 class="text-center"><small>На вашу електронну пошту відправлений лист для підтвердження.</small></h3>
	<% } %>
</div>