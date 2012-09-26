$(document).ready(function(){
	
	function redirect(anchor, target) {
		$(anchor).click(function(){
			$('#redirect').find('#target').val(target);
			// Generalize action for other equipment
			$('#redirect').attr({action:'redirect', method:'POST'});
			$('#redirect').submit();
		});
	}
	redirect('#import', 'liganupload.jsp');
	redirect('#importline', 'lineupload.jsp');
	redirect('#importdownline', 'downlineupload.jsp');
	redirect('#importtingzi', 'tingziupload.jsp');
	redirect('#importtingzinh', 'tingzinhupload.jsp');
	redirect('#importyangzhaodian', 'yangzhaodianupload.jsp');
	redirect('#liganview', 'liganshow.jsp');
	redirect('#ligansearch', 'ligansearch.jsp');
	redirect('#tingzinhsearch', 'tingzinhsearch.jsp');
	redirect('#yangzhaodiansearch', 'yangzhaodiansearch.jsp');
	redirect('#tingziview', 'tingzishow.jsp');
	redirect('#tingzinhview', 'tingzinhshow.jsp');
	redirect('#yangzhaodianview', 'yangzhaodianshow.jsp');

});
