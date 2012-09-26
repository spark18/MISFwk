$(document).ready(function(){
	
	$('#approve-btn').overlay();
	$('.simple_overlay_approve .close').click(function(){
		$('.simple_overlay_approve .progress').hide();
		$('.simple_overlay_approve #approver').val('');
		$('.simple_overlay_approve #approver-passwd').val('')
	});
	$('.simple_overlay_approve #submit').click(function() {
		$('.simple_overlay_approve .progress').show();
		$.ajax({
			data: {	approver: $('.simple_overlay_approve #approver').val(), 
					approverpasswd: $.md5($('.simple_overlay_approve #approver-passwd').val()),
					username: $('#username').val()},
			url : 'yangzhaodianpreviewapprove',
			type: 'POST',
			success: function(result){
				var retStatus = $.parseJSON(result);
				$('.simple_overlay_approve .progress').hide();
				if('FAILURE' == retStatus.retCode) {
					$('.simple_overlay_approve .progress').text("\u60a8\u65e0\u6743\u6279\u51c6");
					$('.simple_overlay_approve .progress').show();
				} else {
					$('.simple_overlay_approve .progress').text("\u6570\u636e\u5df2\u7ecf\u5bfc\u5165\u4e2d\u592e\u6570\u636e\u5e93");
					$('.simple_overlay_approve .progress').show();
				}
				function hide(){
					$('.simple_overlay_approve .progress').hide();
				}
				setTimeout(hide, 3000);
				return;
			}
		});
	});
	
	function ligangridify(transactionId) {
		var id = '';
		if(transactionId != undefined && transactionId != '') {
			id = transactionId;
		}
		$(".ligan").flexigrid({
			url: 'yangzhaodianpreview?username=' + $('#username').val() + '&transactionId=' + id,
			dataType: 'json',
			colModel : [
			            {display: '\u533a\u57df', name : 'area', width : 40, sortable : true, align: 'left'},
			            {display: '\u7ad9\u70b9\u7f16\u53f7', name : 'stopnumber', width : 60, sortable : true, align: 'left'},
			            {display: '\u753b\u9762\u7f16\u53f7', name : 'picnumber', width : 60, sortable : true, align: 'left'},
			            {display: '\u8bbe\u65bd\u7f16\u53f7', name : 'entitynum', width : 60, sortable : true, align: 'left'},
			            {display: '\u8bbe\u65bd\u578b\u53f7', name : 'yangzhaodiantype', width : 60, sortable : true, align: 'left'},
			            {display: '\u5e7f\u544a\u5ba2\u6237/\u753b\u9762\u540d\u79f0', name : 'adop', width : 147, sortable : true, align: 'left'},
			            {display: '\u753b\u9762\u4e0a\u753b\u65e5\u671f', name : 'adstart', width : 147, sortable : true, align: 'left'},
			            {display: '\u753b\u9762\u5230\u671f\u65e5\u671f', name : 'adend', width : 147, sortable : true, align: 'left'},
			            {display: '\u8def\u540d', name : 'road', width : 50, sortable : true, align: 'left'},
			            {display: '\u7ad9\u540d', name : 'stop', width : 30, sortable : true, align: 'left'},
			            {display: '\u5730\u5740', name : 'address', width : 80, sortable : true, align: 'left'},
			            {display: '\u8f66\u5411', name : 'direction', width : 40, sortable : true, align: 'left'},
			            {display: '\u4e0a\u6b21\u517b\u62a4\u65e5\u671f', name : 'lastcaredate', width : 90, sortable : true, align: 'left'},
			            {display: '\u5907\u6ce8', name : 'comments', width : 147, sortable : true, align: 'left'},
			            {display: 'rownum', name : 'rownum', width : 244, sortable : true, align: 'left', hide: true}
			            ],
			            usepager: true,
			            title: '\u626c\u62db\u70b9\u4fe1\u606f\u8868',
			            useRp: true,
			            rp: 50,
			            showTableToggleBtn: true,
			            width: 970,
			            height: 600,
			            onSuccess: function() {
			            	$('.ligan tr').each(function(){
			            		var _this = $(this);
			            		$.tools.dateinput.localize("ch",  {
			            			months:        '\u4e00\u6708,\u4e8c\u6708,\u4e09\u6708,\u56db\u6708,\u4e94\u6708,\u516d\u6708,\u4e03\u6708,\u516b\u6708,\u4e5d\u6708,\u5341\u6708,\u5341\u4e00\u6708,\u5341\u4e8c\u6708',
			            			shortMonths:   '\u4e00\u6708,\u4e8c\u6708,\u4e09\u6708,\u56db\u6708,\u4e94\u6708,\u516d\u6708,\u4e03\u6708,\u516b\u6708,\u4e5d\u6708,\u5341\u6708,\u5341\u4e00\u6708,\u5341\u4e8c\u6708',
			            			days:          '\u661f\u671f\u5929,\u661f\u671f\u4e00,\u661f\u671f\u4e8c,\u661f\u671f\u4e09,\u661f\u671f\u56db,\u661f\u671f\u4e94,\u661f\u671f\u516d',
			            			shortDays:     '\u661f\u671f\u5929,\u661f\u671f\u4e00,\u661f\u671f\u4e8c,\u661f\u671f\u4e09,\u661f\u671f\u56db,\u661f\u671f\u4e94,\u661f\u671f\u516d'
			            		});
			            		$("#digtime:date").dateinput({ 
			            			lang: 'ch', 
			            			format: 'mmmm dd,yyyy',
			            			offset: [30, 0]
			            		});
			            		$("#finishdate:date").dateinput({ 
			            			lang: 'ch', 
			            			format: 'mmmm dd,yyyy',
			            			offset: [30, 0]
			            		});
			            	});
			            }
		});
	}
	ligangridify();
	
	function liganhistorygridify() {
		$(".ligan-history").flexigrid({
			url: 'yangzhaodianpreviewtransaction?username=' + $('#username').val(),
			dataType: 'json',
			type: 'POST',
			onSuccess: function() {
				$('.ligan-history tr').each(function(){
					var _this = $(this);
					var div = $(this).find('td[abbr="importcomments"] div');
					var comments = div.text();
					div.empty();
					div.append('<a href="javascript:void(0)">' + comments + '</a>');
					div.find('a').click(function(){
						var transactionId = _this.find('td[abbr="transactionId"] div').text();
				        $('.ligan-grid').empty().append($('<div class="ligan"></div>'));
				        ligangridify(transactionId);
					});
				});
			},
			colModel : [
			            {display: '\u5bfc\u5165\u65f6\u95f4', name : 'importtime', width : 160, sortable : true, align: 'left'},
			            {display: '\u5907\u6ce8', name : 'importcomments', width : 228, sortable : true, align: 'left'},
			            {display: '\u5907\u6ce8', name : 'transactionId', width : 228, sortable : true, align: 'left', hide: true},
			            ],
			            usepager: true,
			            title: '\u626c\u62db\u70b9\u5bfc\u5165\u5386\u53f2\u8bb0\u5f55',
			            useRp: true,
			            rp: 50,
			            showTableToggleBtn: true,
			            width: 408,
			            height: 600
		});
	}
	liganhistorygridify();
	
	$('#file_upload').uploadify({
	    'uploader'  : 'uploadify/uploadify.swf',
	    'script'    : 'uploadify?',
	    'cancelImg' : 'uploadify/cancel.png',
	    'folder'    : '/uploads',
	    'scriptData': {username: $('#username').val(), type: 'yangzhaodian'},
	    'method'	: 'get',
	    'fileDesc'  : 'Excel CSV \u6587\u4ef6',
	    'fileExt'   : '*.csv',
	    'auto'      : true,
	    'removeCompleted' : false,
	    'onError'   : function(event,ID,fileObj,errorObj) {
	    	$('#file_upload .percentage').text(' - \u8bbe\u5907\u6570\u636e\u6587\u4ef6\u5bfc\u5165\u51fa\u9519');
	    	$.blockUI({ message: $('#errmsgform') });
	    },
	    'onOpen'    : function(event,ID,fileObj) {
	    	$.ajax({
	    		url: 'checklogin',
	    		data: {username: $('#username').val()},
	    		async: false,
	    		success : function(retCode) {
	    			if(retCode == 'FAILURE') {
	    				$.blockUI({ message: $('<div>\u60a8\u7684\u767b\u5f55\u8d85\u65f6,\u8bf7\u91cd\u65b0\u767b\u5f55</div>') });
	    				$('#isupload').val('false');
	    				location.href = '/shizhonginfo';
	    			} else {
	    				$('#menu').hide();
	    			}
	    		}
	    	});	  
	    },
	    'onAllComplete' : function(event,data) {
	    	$('#menu').show();
	    },
	    'onComplete'  : function(event, ID, fileObj, response, data) {
	        var transactionId = response;
	        $('.ligan-grid').empty().append($('<div class="ligan"></div>'));
	        ligangridify(transactionId);
	        
	        $('.ligan-history-grid').empty().append($('<div class="ligan-history"></div>'));
	        liganhistorygridify(transactionId);
	     }
	  });

	$('#errmsgclose').click(function(){
		$.unblockUI();
		$('#file_upload').uploadifyClearQueue();
		$('#menu').show();
	});
});
