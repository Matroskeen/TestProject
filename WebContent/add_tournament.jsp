<% String title = (String) request.getAttribute("title"); %>
<% String info = (String) request.getAttribute("info"); %>
<% String order = (String) request.getAttribute("order"); %>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file = "menu.jsp" %>

<h1 class="main-title">Новий турнір</h1>

<div class="row">
	
	<div class="col-md-6">
		<form id="add-tournament-form" method="post" action="<%= basePath %>/tournaments">
		  <div class="form-group">
		    <label for="title">Заголовок</label>
		    <input type="text" class="form-control" id="title" name="title" required
		    	value="<%= title != null? title : "" %>">
		  </div>
		  <div class="form-group">
		    <label for="status">Статус</label>
		    <select class="form-control" name="status" id="status">
			  <option value="0">Активний</option>
			  <option value="1">Планується</option>
			</select>
		  </div>
		  <div class="row">
		  	<div class="col-md-6">
		  		<div class="form-group">
		  	  		<label for="team_players">Кількість людей в команді</label>
			  		<input type="number" class="form-control" id="team_players" name="team_players" required min="1" max="16">
		  		</div>
		  	</div>
		  	<div class="col-md-6">
		  		<div class="form-group">
					<label for="extra_players">Кількість запасних гравців</label>
				    <input type="number" class="form-control" id="extra_players" name="extra_players" min="0" max="10">
				</div>	
		  	</div>
		  </div>
		  <div class="form-group">
		    <label for="date">Дата проведення</label>
		    <input type="date" class="form-control" id="date" name="date" required>
		  </div>
		  <div class="form-group">
		    <label for="info">Додаткова інформація</label>
		    <textarea class="form-control" name="info" id="info" rows="3" style="resize: vertical"><%= info != null? info : "" %></textarea>
		  </div>
		  <div class="form-group">
		    <label for="order">Регламент</label>
		    <textarea class="form-control" name="order" id="order" rows="5" style="resize: vertical"><%= order != null? order : "" %></textarea>
		  </div>
		 <button id="submit" type="submit" class="btn btn-default">Додати</button>
		</form>
	</div>
	
</div>

<%@include file = "footer.jsp" %>
