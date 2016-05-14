<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@include file = "menu.jsp" %>
 
<h1 class="main-title">Мій профіль</h1>

<div class="row">
	<form id="edit-profile-form" method="post" action="<%= basePath %>/edit_profile" enctype="multipart/form-data">
		<div class="col-md-6">
			<div id="kv-avatar-errors" class="center-block" style="width: 800px; display:none"></div>
			<div class="kv-avatar" style="width: 200px">
		        <input id="avatar" name="avatar" type="file" class="file-loading">
		   	</div>
	  		<div class="form-group">
	    		<label for="nickname">Нікнейм</label>
	    		<input type="text" class="form-control" id="nickname" name="nickname" 
	    			value="<%= user.getNickName() %>" readonly>
	  		</div>
	  		<div class="form-group">
			    <label for="email">Електронна пошта</label>
			    <input type="email" class="form-control" id="email" name="email" 
			    	value="<%= user.getEmail() %>" readonly>
	  		</div>
	  		<div class="form-group">
			    <label for="steam_account">Аккаунт Steam</label>
			    <input type="text" class="form-control" id="steam_account" name="steam_account" 
	    			value="<%= user.getSteamAccount() == null ? "" : user.getSteamAccount() %>">
	  		</div>
		  	<div class="form-group">
		    	<label for="wot_account">Аккаунт WOT</label>
		    	<input type="text" class="form-control" id="wot_account" name="wot_account" 
		   			value="<%= user.getWotAccount() == null ? "" : user.getWotAccount() %>">
		  	</div>
		  	<div class="form-group">
			    <label for="password">Новий пароль</label>
			    <input type="password" class="form-control" id="password" name="password">
		 	</div>
		 	<div class="form-group">
			    <label for="repeat-password">Повторіть новий пароль</label>
			    <input type="password" class="form-control" id="repeat-password" name="repeat-password">
		 	</div>
			<input type="hidden" id="avatar-name" value="<%= user.getAvatar() %>">  
		 	<button id="save" type="submit" class="btn btn-default">Зберегти</button>
		</div>
	</form>
</div>
<script>
	$("#avatar").fileinput({
	    overwriteInitial: true,
	    maxFileSize: 1500,
	    showClose: false,
	    showCaption: false,
	    browseLabel: '',
	    removeLabel: '',
	    browseIcon: '<i class="glyphicon glyphicon-folder-open"></i>',
	    removeIcon: '<i class="glyphicon glyphicon-remove"></i>',
	    removeTitle: 'Cancel or reset changes',
	    elErrorContainer: '#kv-avatar-errors',
	    msgErrorClass: 'alert alert-block alert-danger',
	    defaultPreviewContent: '<img id="avatar-pict" src="" style="width:160px">',
	    layoutTemplates: {main2: '{preview} ' + '{browse}'},
	    allowedFileExtensions: ["jpg", "png", "gif"]
	});
	
	var avatarName = $('#avatar-name').val();
	if (avatarName === "null" || avatarName === "") {
		avatarName = "default_avatar.png";
	}
	$("#avatar-pict").attr('src', basePath + '/images/avatars/' + avatarName);
</script>

<%@include file = "footer.jsp" %>
