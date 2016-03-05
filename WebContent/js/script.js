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
	    defaultPreviewContent: '<img src='+ basePath + '/images/avatars/default_avatar.png alt="Your Avatar" style="width:160px">',
	    layoutTemplates: {main2: '{preview} ' + ' {remove} {browse}'},
	    allowedFileExtensions: ["jpg", "png", "gif"]
	});
	
});
