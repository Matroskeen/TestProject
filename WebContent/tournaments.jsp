<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="com.matroskeen.beans.Tournament"%>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% Map<Byte, ArrayList<Tournament>> tournaments = (Map<Byte, ArrayList<Tournament>>) request.getAttribute("tournaments");%>

<%@include file="menu.jsp" %>

<% if (user != null && user.getRole() == User.ROLE_ADMIN) { %>
	<a href="<%= basePath %>/add_tournament">Add</a>
<% } %>
<h1>Турніри</h1>

<% for(Byte tournamentStatus : Tournament.STATUSES) { %>
	<% ArrayList<Tournament> currentTournaments = tournaments.get(tournamentStatus); %>
	<% if (!currentTournaments.isEmpty()) { %>
		<h2><small><%= Tournament.getStatusName(tournamentStatus) %></small></h2>
		<ul class="list-group">
		
		<% for(Tournament tournament : currentTournaments) { %>
			<li class="list-group-item">
				<h3><a href="tournaments/<%= tournament.getId() %>"><%= tournament.getTitle() %></a></h3>
				
				<% if (tournament.getStatus() == 0) { %>
					<button type="button" class="btn btn-info">Реєстрація</button>
					<button type="button" class="btn btn-success">Шукаю команду</button>
				<% } %>
			</li>
		<% } %>
		</ul>
	<% } %>
<% } %>

<%@include file = "footer.jsp" %>
