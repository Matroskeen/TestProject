<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="com.matroskeen.beans.TournamentBean"%>
<%@page import="com.matroskeen.beans.UserBean"%>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% Map<Byte, ArrayList<TournamentBean>> tournaments = (Map<Byte, ArrayList<TournamentBean>>) request.getAttribute("tournaments");%>

<%@include file="menu.jsp" %>

<% if (user != null && user.getRole() == UserBean.ROLE_ADMIN) { %>
	<a href="<%= basePath %>/add_tournament">Add</a>
<% } %>
<h1>Турніри</h1>

<% for(Byte tournamentStatus : TournamentBean.STATUSES) { %>
	<% ArrayList<TournamentBean> currentTournaments = tournaments.get(tournamentStatus); %>
	<% if (!currentTournaments.isEmpty()) { %>
		<h2><small><%= TournamentBean.getStatusName(tournamentStatus) %></small></h2>
		<ul class="list-group">
		
		<% for(TournamentBean tournament : currentTournaments) { %>
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
