$(document).ready(function() {
	
	var $menu_li = $(".top-menu li");
	$menu_li.removeClass("active");
	$menu_li.each(function() {
		var $this = $(this);
		var href = $this.find("a").attr("href");
	
		if (document.location.pathname == href || document.location.pathname == (href + "/")) {
			$this.addClass("active");
		}
	});
	
	validateRegisterForm();
	
	function validateRegisterForm() {
		$('#register-form button[type=submit]').on('click', function(e) {
			e.preventDefault();
			
			var $passwordField = $('#password');
			var $repeatPasswordField = $('#repeat-password');
			var password = $passwordField.val();
			var repeatPassword = $repeatPasswordField.val();
			
			if (password.length < 6 || repeatPassword.lengh < 6) {
				insertError($('#password').parent(), $('#repeat-password').parent(), "Мінімальна кількість символів - 6.");
			} else if (password === repeatPassword) {
				$('#register-form').submit();
			} else {
				insertError($('#password').parent(), $('#repeat-password').parent(), "Паролі не співпадають.");
			}
		});
	}
	
	validateEditProfileForm();
	
	function validateEditProfileForm() {
		$('#edit-profile-form button[type=submit]').on('click', function(e) {
			e.preventDefault();
			
			var $passwordField = $('#password');
			var $repeatPasswordField = $('#repeat-password');
			var password = $passwordField.val();
			var repeatPassword = $repeatPasswordField.val();
			
			if (password === repeatPassword) {
				if (password.length > 0 && password.length < 6) {
					insertError($('#password').parent(), $('#repeat-password').parent(), "Мінімальна кількість символів - 6.");
				} else {
					$('#edit-profile-form').submit();
				}
			} else {
				insertError($('#password').parent(), $('#repeat-password').parent(), "Паролі не співпадають.");
			}
		});
	}
	
	function insertError ($password, $repeatPassword, message) {
		$password.addClass('has-error');
		$repeatPassword.addClass('has-error');
		$('.passwords-error').remove();
		$repeatPassword.after('<span class="passwords-error">' + message + '</span>');
	}
	
	removeErrors();
	
	function removeErrors() {
		$('input[type=password]').on('change', function() {
			$('.passwords-error').remove();
			$('input[type=password]').parent().removeClass('has-error');
		});
	}
	
	
});
