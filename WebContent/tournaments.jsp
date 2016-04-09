<%@page import="com.matroskeen.settings.Role" %>

<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@include file = "menu.jsp" %>

<% if (user != null && user.getRole() == Role.ADMIN) { %>
	<a href="<%= basePath %>/add_tournament">Add</a>
<% } %>
<h1>Tournaments coming soon</h1>

<%@include file = "footer.jsp" %>
