<%@page import="com.matroskeen.beans.User"%>
<%@page import="java.io.File"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<% ArrayList<User> users = (ArrayList<User>) request.getAttribute("users"); %>

<%@ include file="menu.jsp" %>

<div class="starter-template">
	<h1>Users</h1>
	<div class="table-responsive">
		<table class="table" data-search="true" data-show-columns="true">
			<%= (users == null || users.isEmpty()) ? "Users are not exists" : "" %>
		    <thead>
		        <tr>
		        	<th data-field="id" data-align="center" data-sortable="true">ID</th>
		        	<th data-field="avatar" data-align="center" data-sortable="false">Аватарка</th>
		            <th data-field="userName" data-align="center" data-sortable="false">Нік</th>
		            <th data-field="email" data-align="center" data-sortable="false">Пошта</th>
		            <th data-field="role" data-align="center" data-sortable="true">Роль</th>
		            <th data-field="lastName" data-align="center" data-sortable="true">Статус</th>
		            <th data-field="registered" data-align="center" data-sortable="true">Дата реєстрації</th>
		            <th data-field="steam_account" data-align="center" data-sortable="false">Аккаунт Steam</th>
		            <th data-field="wot_account" data-align="center">Аккаунт WOT</th>
		        </tr>
		    </thead>
		    <tbody>
		    <% for (User currentUser: users) { %>
			<tr>
				<td><%= currentUser.getId() %></td>
				<td>
					<% if (currentUser.getAvatar().isEmpty()) { %>
						<img src="<%= basePath %>/images/avatars/default_avatar.png" class="img-circle avatar-table">
					<% } else { %>
						<img src="<%= basePath %>/images/avatars/<%= currentUser.getAvatar() %>" class="img-circle avatar-table">
					<% } %>
				</td>
		        <td><%= currentUser.getNickName() %></td>
		        <td><%= currentUser.getEmail() %></td>
		        <td><%= currentUser.getRoleName() %></td>
		        <td><%= currentUser.getStatusName() %></td>
		        <td><%= new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(currentUser.getRegistered()) %></td>
		        <td><%= currentUser.getSteamAccount() %></td>
		        <td><%= currentUser.getWotAccount() %></td>
		    </tr>
			<% } %>
			</tbody>
		</table>
	</div>
</div>

<%@ include file="footer.jsp" %>
