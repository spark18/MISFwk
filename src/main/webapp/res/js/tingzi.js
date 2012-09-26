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
			url : 'tingzipreviewapprove',
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
			url: 'tingzipreview?username=' + $('#username').val() + '&transactionId=' + id,
			dataType: 'json',
			colModel : [
			            {display: '\u7ebf\u8def', name : 'line', width : 40, sortable : true, align: 'left'},
			            {display: '\u533a\u57df', name : 'area', width : 40, sortable : true, align: 'left'},
			            {display: '\u94ed\u724c\u53f7', name : 'number', width : 60, sortable : true, align: 'left'},
			            {display: '\u8def\u540d', name : 'road', width : 50, sortable : true, align: 'left'},
			            {display: '\u7ad9\u540d', name : 'stop', width : 30, sortable : true, align: 'left'},
			            {display: '\u5730\u5740', name : 'address', width : 80, sortable : true, align: 'left'},
			            {display: '\u8f66\u5411', name : 'direction', width : 40, sortable : true, align: 'left'},
			            {display: '\u6316\u5751\u65e5\u671f', name : 'digtime', width : 80, sortable : true, align: 'left'},
			            {display: '\u5f00\u5f80\u65b9\u5411', name : 'finalstop', width : 80, sortable : true, align: 'left'},
			            {display: '\u7ebf\u8def\u4e0b\u4e00\u7ad9', name : 'nextstop', width : 110, sortable : true, align: 'left'},
			            {display: '\u5b8c\u6210\u65e5\u671f', name : 'finishdate', width : 80, sortable : true, align: 'left'},
			            {display: '\u5907\u6ce8', name : 'comments', width : 147, sortable : true, align: 'left'},
			            {display: 'rownum', name : 'rownum', width : 244, sortable : true, align: 'left', hide: true}
			            ],
			            usepager: true,
			            title: '\u4ead\u5b50\u4fe1\u606f\u8868',
			            useRp: true,
			            rp: 50,
			            showTableToggleBtn: true,
			            width: 970,
			            height: 600,
			            onSuccess: function() {
			            	$('.ligan tr').each(function(){
			            		var _this = $(this);
//			            		var div = _this.find('td[abbr="line"] div');
//			            		var linenum = div.text();
//			            		div.html($('<a href="javascript:void(0)" rel="#mies1">' + linenum + '</a>'));
//			            		div.find('a').click(function(){
//			            			$('.simple_overlay #rownum').val(_this.find('td[abbr="rownum"] div').text());
//			            			$.ajax({
//			            				url: 'tingzipreviewshow',
//			            				data: {id: $('.simple_overlay #rownum').val(),
//			            					   username: $('#username').val()},
//			            				success: function(json) {
//			            					var liganPreview = $.parseJSON(json);
//			            					$('#line').val(liganPreview.line);
//			            					$('#area').val(liganPreview.area);
//			            					$('#number').val(liganPreview.number);
//			            					$('#road').val(liganPreview.road);
//			            					$('#stop').val(liganPreview.stop);
//			            					$('#finalstop').val(liganPreview.finalstop);
//			            					$('#nextstop').val(liganPreview.nextstop);
//			            					$('#address').val(liganPreview.addr);
//			            					$('#direction').val(liganPreview.direction);
//			            					$('#digtime').val(liganPreview.digdate);
//			            					$('#finishdate').val(liganPreview.finishdate);
//			            					$('#img img').attr('src', 'tingziimgshow?entity=TingZiPreview&username=' + $('#username').val() + '&id=' + $('.simple_overlay #rownum').val());
//			            				}
//			            			});
//			            			$('.simple_overlay #submit').click(function() {
//			            				$.ajax({
//			            					url: 'tingzipreviewmodify',
//			            					data: {id: $('.simple_overlay #rownum').val(),
//				            					   username: $('#username').val(),
//				            					   line: $('#line').val(),
//				            					   area: $('#area').val(),
//				            					   number: $('#number').val(),
//				            					   road: $('#road').val(),
//				            					   stop: $('#stop').val(),
//				            					   finalstop: $('#finalstop').val(),
//				            					   nextstop: $('#nextstop').val(),
//				            					   address: $('#address').val(),
//				            					   direction: $('#direction').val(),
//				            					   digtime: $('#digtime').val(),
//				            					   finishdate: $('#finishdate').val()
//				            					   },
//			            					success: function(json) {
//			            						$('.simple_overlay .progress').text('\u4fee\u6539\u5b8c\u6210');
//			            						$('.simple_overlay .progress').show();
//			            						function hide(){
//			            							$('.simple_overlay .progress').hide();
//			            						}
//			            						setTimeout(hide, 3000);
//			            					}
//			            				});
//			            			});
//			            			
//			            			$('#img-btn object').remove();
//			            			$('#img-btn #img_uploadQueue').remove();
//			            			$('#img_upload').uploadify({
//			            			    'uploader'  : 'uploadify/uploadify.swf',
//			            			    'script'    : 'tingziimguploadify',
//			            			    'scriptData': {'id': $('.simple_overlay #rownum').val(),
//			            			    	username: $('#username').val(), entity: "TingZiPreview"},
//			            			    'method'	: 'get',
//			            			    'cancelImg' : 'uploadify/cancel.png',
//			            			    'folder'    : '/uploads',
//			            			    'fileDesc'  : '\u7acb\u6746\u56fe\u7247',
//			            			    'fileExt'   : '*.JPEG;*.JPG',
//			            			    'auto'      : true,
//			            			    'multi'		: false,
//			            			    'removeCompleted' : false,
//			            			    'onError'   : function(event,ID,fileObj,errorObj) {
//			            			    	$('#img_upload .percentage').text(' - \u7acb\u6746\u56fe\u7247\u4e0a\u4f20\u5931\u8d25');
//			            			    }
//			            			  });
//			            		});
//			            		div.find('a').click(function(){
//			            			$('#mies1').dialog({width:680});
//			            		});
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
			url: 'tingzipreviewtransaction?username=' + $('#username').val(),
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
			            title: '\u4ead\u5b50\u5bfc\u5165\u5386\u53f2\u8bb0\u5f55',
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
	    'scriptData': {username: $('#username').val(), type: 'tingzi'},
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
