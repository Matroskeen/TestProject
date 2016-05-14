<%@page import="com.matroskeen.beans.TournamentBean"%>
<%@page import="com.matroskeen.beans.UserBean"%>
<%@page import="java.text.SimpleDateFormat"%>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% TournamentBean tournament = (TournamentBean) request.getAttribute("tournament");%>

<%@include file="menu.jsp" %>

<h1><%= tournament.getTitle() %></h1>
<h2><%= new SimpleDateFormat("MM/dd/yyyy").format(tournament.getDate()) %></h2>

<% if (tournament.getStatus() == 0) { %>
	<button type="button" class="btn btn-info">Реєстрація</button>
	<button type="button" class="btn btn-success">Шукаю команду</button>
<% } %>


<%@include file = "footer.jsp" %>
