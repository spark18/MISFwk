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
			url : 'uplinepreviewapprove',
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
	$('.simple_overlay_busticket #submit').click(function() {
		$('.simple_overlay_approve .progress').show();
		
		
		if($('#mies3 .table input[name=line]').size() > 0) {
			$.ajax({
				data: { username: $('#username').val(), 
						line: $('#mies3 .table input[name=line]').val(), 
						bustype: $('#mies3 .table select[name=bustype]').val(),
						normPrice: $('#mies3 .table input[name=normPrice]').val(),
						pricetype: $('#mies3 .table select[name=pricetype]').val(),
						autoSale: $('#mies3 .table select[name=autoSale]').val(),
						opunit: $('#mies3 .table input[name=opunit]').val(),
						},
					url : 'busticketpreviewmodify',
					type: 'POST',
					success: function(result){
						var retStatus = $.parseJSON(result);
						$('.simple_overlay_busticket .progress').hide();
						if('FAILURE' == retStatus.retCode) {
							$('.simple_overlay_busticket .progress').text("\u60a8\u65e0\u6743\u6279\u51c6");
							$('.simple_overlay_busticket .progress').show();
						} else {
							$('.simple_overlay_busticket .progress').text("\u6570\u636e\u5df2\u7ecf\u5bfc\u5165\u4e2d\u592e\u6570\u636e\u5e93");
							$('.simple_overlay_busticket .progress').show();
						}
						function hide(){
							$('.simple_overlay_busticket .progress').hide();
						}
						setTimeout(hide, 3000);
						return;
					}
			});
		}
		
		if($('#mies3 .tableair input[name=line]').size() > 0) {
			$.ajax({
				data: { username: $('#username').val(), 
					line: $('#mies3 .tableair input[name=line]').val(), 
					bustype: $('#mies3 .tableair select[name=bustype]').val(),
					airPrice: $('#mies3 .tableair input[name=airPrice]').val(),
					pricetype: $('#mies3 .tableair select[name=pricetype]').val(),
					autoSale: $('#mies3 .tableair select[name=autoSale]').val(),
					opunit: $('#mies3 .tableair input[name=opunit]').val(),
				},
				url : 'busticketairpreviewmodify',
				type: 'POST',
				success: function(result){
					$('.simple_overlay_approve .progress').hide();
					if('FAILURE' == result) {
						$('.simple_overlay_busticket .progress').text("\u60a8\u65e0\u6743\u6279\u51c6");
						$('.simple_overlay_busticket .progress').show();
					} else {
						$('.simple_overlay_busticket .progress').text("\u4fee\u6539\u5b8c\u6210");
						$('.simple_overlay_busticket .progress').show();
					}
					function hide(){
						$('.simple_overlay_busticket .progress').hide();
					}
					setTimeout(hide, 3000);
					return;
				}
			});
		}
	});
	
	function ligangridify(transactionId) {
		var id = '';
		if(transactionId != undefined && transactionId != '') {
			id = transactionId;
		}
		$(".ligan").flexigrid({
			url: 'uplinepreview?username=' + $('#username').val() + '&transactionId=' + id,
			dataType: 'json',
			colModel : [
			            {display: '\u4e0a\u884c\u7ad9\u540d', name : 'name', width : 250, sortable : true, align: 'left'},
			            {display: '\u526f\u7ad9\u540d', name : 'alias', width : 100, sortable : true, align: 'left'},
			            {display: '\u7ad9\u5740', name : 'stopaddress', width : 250, sortable : true, align: 'left'},
			            {display: '\u9996\u672b\u73ed\u65f6\u523b', name : 'startend', width : 200, sortable : true, align: 'left'},
			            {display: 'id', name : 'id', width : 100, sortable: true, align: 'left', hide: true},
			            {display: 'line', name : 'line', width : 100, sortable: true, align: 'left', hide: true},
			            {display: '\u7968\u4ef7\u4fe1\u606f', name : 'ticket', width : 100, sortable : true, align: 'left'}
			            ],
			            usepager: true,
			            title: '\u4e0a\u884c\u7ebf\u8def\u8868',
			            useRp: true,
			            rp: 50,
			            showTableToggleBtn: true,
			            width: 970,
			            height: 600,
			            onSuccess: function() {
			            	$('.ligan tr').each(function(){
			            		var _this = $(this);
			            		var ticketdiv = _this.find('td[abbr="ticket"] div');
			            		ticketdiv.text('');
			            		
//			            		var div = _this.find('td[abbr="name"] div');
//			            		var stopname = div.text();
//			            		div.html($('<a href="javascript:void(0)" rel="#mies1">' + stopname + '</a>'));
//			            		div.find('a').click(function(){
//			            			$('.simple_overlay #rownum').val(_this.find('td[abbr="rownum"] div').text());
//			            			$.ajax({
//			            				url: 'uplinepreview',
//			            				data: {username: $('#username').val(), transactionId: id},
//			            				success: function(json) {
//			            					var uplineListPreview = $.parseJSON(json);
//			            					var rows = uplineListPreview.rows;
//			            					$('#mies1 .details .table table').remove();
//			            					var table = $('<table></table>');
//			            					$('#mies1 .details .table').append(table);
//			            					table.append($('<tr><td>\u4e0a\u884c\u7ad9\u540d</td><td>\u526f\u7ad9\u540d</td><td>\u7ad9\u5740</td><td>\u9996\u672b\u73ed\u65f6\u523b</td></tr>'))
//			            					for(var index = 0; index < rows.length; index++) {
//			            						var row = rows[index];
//			            						var col1 = row.cell[0];
//			            						if(col1 == null || col1 == undefined || col1 == 'null') {
//			            							col1 = '';
//			            						}
//			            						var col2 = row.cell[1];
//			            						if(col2 == null || col2 == undefined || col2 == 'null') {
//			            							col2 = '';
//			            						}
//			            						var col3 = row.cell[2];
//			            						if(col3 == null || col3 == undefined || col3 == 'null') {
//			            							col3 = '';
//			            						}
//			            						var col4 = row.cell[3];
//			            						if(col4 == null || col4 == undefined || col4 == 'null') {
//			            							col4 = '';
//			            						}
//			            						table.append($('<tr class="row"><td><input type="hidden" name="id" value="' +  row.cell[4] + '"><input name="name" value="'
//			            								+ col1 + '"></td><td><input name="alias" value="' 
//			            								+ col2 + '"></td><td><input name="stopaddress" value="' 
//			            								+ col3 + '"></td><td><input name="startend" value="' 
//			            								+ col4 + '"></td></tr>'))
//			            					}
//			            					
//			            					div.find('a').click(function(){
//			            						$('#mies1').dialog({width:680});
//			            					});
//			            				}
//			            			});
//			            			$('.simple_overlay #submit').click(function() {
//			            				$('#mies1 .details .table table tr.row').each(function(){
//			            					$.ajax({
//				            					url: 'uplinepreviewmodify',
//				            					data: {id: $(this).find('input[name=id]').val(),
//					            					   username: $('#username').val(),
//					            					   name:  $(this).find('input[name=name]').val(),
//					            					   alias:  $(this).find('input[name=alias]').val(),
//					            					   stopaddress:  $(this).find('input[name=stopaddress]').val(),
//					            					   startend:  $(this).find('input[name=startend]').val()
//					            					   },
//				            					success: function(json) {
//				            						$('.simple_overlay .progress').text('\u4fee\u6539\u5b8c\u6210');
//				            						$('.simple_overlay .progress').show();
//				            						function hide(){
//				            							$('.simple_overlay .progress').hide();
//				            						}
//				            						setTimeout(hide, 3000);
//				            					}
//				            				});
//			            				});
//			            			});
//			            		});
			            		
			            	});
			            	$($('.ligan tr td[abbr="ticket"] div')[0]).append($('<a href="javascript:void(0)" rel="#mies3">\u7968\u4ef7\u4fe1\u606f</a>'));
			            	$('.ligan tr td[abbr="ticket"] div a').click(function(){
			            		$.ajax({
			            			url: 'busticketpreviewshow',
			            			data: {username: $('#username').val(), line: $($('.ligan tr td[abbr="line"] div')[0]).text()},
			            			success: function(json) {
			            				if('FAILURE' != json) {
			            					$('#mies3 .details .table').empty();
			            					var busticket = $.parseJSON(json);
				            				var table = $('<table></table>');
				            				table.append($('<tr><td>\u8f66\u578b</td><td>\u591a\u7ea7\u7968\u4ef7</td><td>\u7a7a\u8c03\u8f66\u7968\u4ef7</td><td>\u7968\u4ef7\u7c7b\u578b</td><td>\u65e0\u4eba\u552e\u7968</td><td>\u8fd0\u8425\u5355\u4f4d</td><td>\u7ebf\u8def</td></tr>'));
				            				table.append($('<tr><td><input type="hidden" name="id" value="' + busticket.id + '"><select name="bustype" ><option value="norm" >\u666e\u901a\u8f66</option><option value="air">\u7a7a\u8c03\u8f66</option>' 
			            							+ '</select></td><td><input name="normPrice" value="' 
			            							+ busticket.normPrice + '"></td><input name="airPrice" value="' 
			            							+ busticket.airPrice + '"></td><td><select name="pricetype"><option value="single">\u5355\u4e00</option><option value="multi">\u591a\u7ea7</option>' 
			            							+ '</select></td><td><select name="autoSale"><option value="y">\u662f</option><option value="n">\u5426</option>' 
			            							+ '</select></td><td><input name="opunit" value="' 
			            							+ busticket.opunit + '"></td><td><input disabled name="line" value="' 
			            							+ busticket.line + '"></td></tr>'));
				            				table.find('select[name=bustype]').val(busticket.type);
				            				table.find('select[name=autoSale]').val(busticket.autoSale);
				            				table.find('select[name=pricetype]').val(busticket.pricetype);
				            				$('#mies3 .details .table').append(table);
				            				$('#mies3 .details .table #multi_ticket_panel').remove();
				            				function showTicketUploader() {
				            					$('#mies3 .details .table')
				            						.append($('<div id="multi_ticket_panel"><div>\u591a\u7ea7\u7968\u4ef7</div><div><input id="multi_upload" name="file_upload" type="file" /></div></div><a target="_blank" href="busticketimgpreview-show?id=' 
				            								+ table.find('input[name=id]').val() 
					            							+ '&username=' + $('#username').val() +'"><img src="busticketimgpreview-show?id=' 
				            							+ table.find('input[name=id]').val() 
				            							+ '&username=' + $('#username').val() +'" class="ticketimg"></a>'));
				            					$('#mies3 .details .table #multi_upload').uploadify({
						            			    'uploader'  : 'uploadify/uploadify.swf',
						            			    'script'    : 'busticketimgpreview-uploadify',
						            			    'scriptData': {id: table.find('input[name=id]').val(), username: $('#username').val(), line: $($('.ligan tr td[abbr="line"] div')[0]).text()},
						            			    'method'	: 'get',
						            			    'cancelImg' : 'uploadify/cancel.png',
						            			    'folder'    : '/uploads',
						            			    'fileDesc'  : '\u7acb\u6746\u56fe\u7247',
						            			    'fileExt'   : '*.JPEG;*.JPG',
						            			    'auto'      : true,
						            			    'multi'		: false,
						            			    'removeCompleted' : false,
						            			    'onError'   : function(event,ID,fileObj,errorObj) {
						            			    	$('#img_upload .percentage').text(' - \u7acb\u6746\u56fe\u7247\u4e0a\u4f20\u5931\u8d25');
						            			    }
						            			  });
				            				}
				            				
				            				function hideTicketUploader() {
				            					$('#mies3 .details .table #multi_ticket_panel').remove();
				            					$('#mies3 .details .table img').remove();
				            				}
				            				if(busticket.pricetype == 'multi') {
				            					showTicketUploader();
				            				}
				            				
				            				table.find('select[name=pricetype]').change(function(evt){
				            					$('.simple_overlay_busticket #submit').click();
				            					$('#mies3 .details .table #multi_ticket_panel').remove();
				            					var pType = $(evt.target).val();
				            					if(pType == 'multi') {
				            						showTicketUploader();
				            					} else {
				            						hideTicketUploader();
				            					}
				            				});
			            				} else {
			            					$('#mies3 .details .table').hide();
			            				}
			            				
			            			}
			            		});
			            		$.ajax({
			            			url: 'busticketairpreviewshow',
			            			data: {username: $('#username').val(), line: $($('.ligan tr td[abbr="line"] div')[0]).text()},
			            			success: function(json) {
			            				if('FAILURE' != json) {
			            					$('#mies3 .details .tableair').empty();
			            					var busticket = $.parseJSON(json);
			            					var table = $('<table></table>');
			            					table.append($('<tr><td>\u8f66\u578b</td><td\u666e\u901a\u8f66\u7968\u4ef7></td><td>\u7a7a\u8c03\u8f66\u7968\u4ef7</td><td>\u7968\u4ef7\u7c7b\u578b</td><td>\u65e0\u4eba\u552e\u7968</td><td>\u8fd0\u8425\u5355\u4f4d</td><td>\u7ebf\u8def</td></tr>'));
			            					table.append($('<tr><td><input type="hidden" name="id" value="' + busticket.id + '"><select name="bustype" ><option value="norm" >\u666e\u901a\u8f66</option><option value="air">\u7a7a\u8c03\u8f66</option>' 
			            							+ '</select></td><td><input name="airPrice" value="' 
			            							+ busticket.airPrice + '"></td><td><select name="pricetype"><option value="single">\u5355\u4e00</option><option value="multi">\u591a\u7ea7</option>' 
			            							+ '</select></td><td><select name="autoSale"><option value="y">\u662f</option><option value="n">\u5426</option>' 
			            							+ '</select></td><td><input name="opunit" value="' 
			            							+ busticket.opunit + '"></td><td><input disabled name="line" value="' 
			            							+ busticket.line + '"></td></tr>'));
			            					table.find('select[name=bustype]').val(busticket.type);
				            				table.find('select[name=autoSale]').val(busticket.autoSale);
				            				table.find('select[name=pricetype]').val(busticket.pricetype);
				            				$('#mies3 .details .tableair').append(table);
				            				$('#mies3 .details .tableair #multi_ticket_panel').remove();
				            				function showTicketAirUploader() {
				            					$('#mies3 .details .tableair').append($('<div id="multi_ticket_panel"><div>\u591a\u7ea7\u7968\u4ef7</div><div><input id="multi_upload" name="file_upload" type="file" /></div></div><a target="_blank" href="busticketairimgpreview-show?id=' 
				            							+ table.find('input[name=id]').val() 
				            							+'&username=' + $('#username').val() +'"><img src="busticketairimgpreview-show?id=' 
				            							+ table.find('input[name=id]').val() 
				            							+'&username=' + $('#username').val() + '" class="ticketimg"></a>'));
				            					$('#mies3 .details .tableair #multi_upload').uploadify({
						            			    'uploader'  : 'uploadify/uploadify.swf',
						            			    'script'    : 'busticketairimgpreview-uploadify',
						            			    'scriptData': {id: table.find('input[name=id]').val(), username: $('#username').val(), line: $($('.ligan tr td[abbr="line"] div')[0]).text()},
						            			    'method'	: 'get',
						            			    'cancelImg' : 'uploadify/cancel.png',
						            			    'folder'    : '/uploads',
						            			    'fileDesc'  : '\u7acb\u6746\u56fe\u7247',
						            			    'fileExt'   : '*.JPEG;*.JPG',
						            			    'auto'      : true,
						            			    'multi'		: false,
						            			    'removeCompleted' : false,
						            			    'onError'   : function(event,ID,fileObj,errorObj) {
						            			    	$('#img_upload .percentage').text(' - \u7acb\u6746\u56fe\u7247\u4e0a\u4f20\u5931\u8d25');
						            			    }
						            			  });
				            				}
				            				function hideTicketAirUploader() {
				            					$('#mies3 .details .tableair #multi_ticket_panel').remove();
				            					$('#mies3 .details .tableair img').remove();
				            				}
				            				if(busticket.pricetype == 'multi') {
				            					showTicketAirUploader();
				            				}
				            				
				            				table.find('select[name=pricetype]').change(function(evt){
				            					$('.simple_overlay_busticket #submit').click();
				            					$('#mies3 .details .tableair #multi_ticket_panel').remove();
				            					var pType = $(evt.target).val();
				            					if(pType == 'multi') {
				            						showTicketAirUploader();
				            					} else {
				            						hideTicketAirUploader();
				            					}
				            				});
			            				} else {
			            					$('#mies3 .details .tableair').hide();
			            				}
			            			}
			            		});
			            	});
			            	$('.ligan tr td[abbr="ticket"] div a').click(function(){
			            		$('#mies3').dialog({width: 940});
			            	});
			            }
		});
	}
	ligangridify();
	
	function liganhistorygridify() {
		$(".ligan-history").flexigrid({
			url: 'uplinepreviewtransaction?username=' + $('#username').val(),
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
			            title: '\u7ebf\u8def\u5bfc\u5165\u5386\u53f2\u8bb0\u5f55',
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
	    'scriptData': {username: $('#username').val(), type: 'line'},
	    'method'	: 'get',
	    'folder'    : '/uploads',
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
