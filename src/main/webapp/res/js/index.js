$(document).ready(function(){
	$('#menu').hide();
	$('.enter').click(function(){
		$('.image-scroller img').fadeOut();
		$('.login-panel').fadeIn();
	});
	$('#cancel-login').click(function(){
		$('.login-panel').fadeOut();
		$('.image-scroller img').fadeIn();
	});
	$('#signin').click(function(){
		$.ajax({
			data: {name: $('#email').val(), passwd: $.md5($('#Passwd').val())},
			url : 'login',
			type: 'POST',
			success: function(result){
				var loginStatus = $.parseJSON(result);
				if(loginStatus.retCode == 'FAILURE') {
					$('#drawer').show();
				} else {
					$('#username').val(loginStatus.username);
					$('#redirect').find('#target').val('liganupload.jsp');
					// Generalize action for other equipment
					$('#redirect').attr({action:'redirect', method:'POST'});
					$('#redirect').submit();
				}
			}
		});
	});
});
