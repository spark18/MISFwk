$(document).ready(function(){
	
	$('.simple_overlay #submit').click(function() {
		$.ajax({
			url: 'liganmodify',
			data: {id: $('.simple_overlay #rownum').val(),
				   username: $('#username').val(),
				   line: $('#mies1 #line').val(),
				   area: $('#mies1 #area').val(),
				   number: $('#mies1 #number').val(),
				   road: $('#mies1 #road').val(),
				   stop: $('#mies1 #stop').val(),
				   finalstop: $('#mies1 #finalstop').val(),
				   nextstop: $('#mies1 #nextstop').val(),
				   address: $('#mies1 #address').val(),
				   direction: $('#mies1 #direction').val(),
				   digtime: $('#mies1 #digtime').val(),
				   finishdate: $('#mies1 #finishdate').val(),
				   type: $('#mies1 #type').val(),
				   comments: $('#mies1 #comments').val()
				   },
			success: function(json) {
				$('.simple_overlay .progress').text('\u4fee\u6539\u5b8c\u6210');
				$('.simple_overlay .progress').show();
				function hide(){
					$('.simple_overlay .progress').hide();
				}
				setTimeout(hide, 3000);
			}
		});
	});
	
	$('.simple_overlay_line #submitticket').click(function(){
		
		if($('.simple_overlay_line .tableair input[name=line]').size() > 0) {
			var oldbustypeair = $('#mies3 .tableair input[name=bustype]').val();
			var newbustypeair = $('#mies3 .tableair select[name=bustype]').val();
			if(oldbustypeair == 'air') {
				var entitytype = 'busticketair';
				var datavector = {
						entity: "busticketair",
						username: $('#username').val(), 
						line: $('#mies3 .tableair input[name=line]').val(), 
						type: $('#mies3 .tableair select[name=bustype]').val(),
						airPrice: $('#mies3 .tableair input[name=airPrice]').val(),
						pricetype: $('#mies3 .tableair select[name=pricetype]').val(),
						autoSale: $('#mies3 .tableair select[name=autoSale]').val(),
						opunit: $('#mies3 .tableair input[name=opunit]').val(),
						liganid: $('#mies3 input[name=liganid]').val()	
				};
			} else {
				entitytype = 'busticket';
				var datavector = {
						entity: "busticket",
						username: $('#username').val(), 
						line: $('#mies3 .tabletic input[name=line]').val(), 
						type: $('#mies3 .tabletic select[name=bustype]').val(),
						normPrice: $('#mies3 .tabletic input[name=normPrice]').val(),
						pricetype: $('#mies3 .tableair select[name=pricetype]').val(),
						autoSale: $('#mies3 .tableair select[name=autoSale]').val(),
						opunit: $('#mies3 .tableair input[name=opunit]').val(),
						liganid: $('#mies3 input[name=liganid]').val()	
				};
			}
			$.ajax({
				data: datavector,
				url : entitytype + 'modify',
				type: 'POST',
				success: function(result){
					$('.simple_overlay_approve .progress').hide();
					if('FAILURE' == result) {
						$('#mies3 .progress').text("\u60a8\u65e0\u6743\u6279\u51c6");
						$('#mies3 .progress').show();
					} else {
						$('#mies3 .tableair input[name=bustype]').val(newbustypeair);
						var ticketair = $.parseJSON(result).entity;
						$('#mies3 .tableair input[name=id]').val(ticketair.id);
						$('#mies3 .progress').text("\u4fee\u6539\u5b8c\u6210");
						$('#mies3 .progress').show();
					}
					function hide(){
						$('#mies3 .progress').hide();
					}
					setTimeout(hide, 3000);
					return;
				}
			});
		}
		if($('.simple_overlay_line .tabletic input[name=line]').size() > 0) {
			var oldbustype = $('#mies3 .tabletic input[name=bustype]').val();
			var newbustype = $('#mies3 .tabletic select[name=bustype]').val();
			if(oldbustype == 'air') {
				var entitytype = 'busticketair';
				var datavector = {
						entity: "busticketair",
						username: $('#username').val(), 
						line: $('#mies3 .tableair input[name=line]').val(), 
						type: $('#mies3 .tableair select[name=bustype]').val(),
						airPrice: $('#mies3 .tableair input[name=airPrice]').val(),
						pricetype: $('#mies3 .tableair select[name=pricetype]').val(),
						autoSale: $('#mies3 .tableair select[name=autoSale]').val(),
						opunit: $('#mies3 .tableair input[name=opunit]').val(),
						liganid: $('#mies3 input[name=liganid]').val()	
				};
			} else {
				entitytype = 'busticket';
				var datavector = {
						entity: "busticket",
						username: $('#username').val(), 
						line: $('#mies3 .tabletic input[name=line]').val(), 
						type: $('#mies3 .tabletic select[name=bustype]').val(),
						normPrice: $('#mies3 .tabletic input[name=normPrice]').val(),
						pricetype: $('#mies3 .tabletic select[name=pricetype]').val(),
						autoSale: $('#mies3 .tabletic select[name=autoSale]').val(),
						opunit: $('#mies3 .tabletic input[name=opunit]').val(),
						liganid: $('#mies3 input[name=liganid]').val()	
				};
			}
			$.ajax({
				data: datavector,
				url : entitytype + 'modify',
				type: 'POST',
				success: function(result){
					$('.simple_overlay_approve .progress').hide();
					if('FAILURE' == result) {
						$('#mies3 .progress').text("\u60a8\u65e0\u6743\u6279\u51c6");
						$('#mies3 .progress').show();
					} else {
						$('#mies3 .tabletic input[name=bustype]').val(newbustype);
						var retCode = $.parseJSON(result);
						$('#mies3 .tabletic input[name=id]').val(retCode.entity.id);
						$('#mies3 .progress').text("\u4fee\u6539\u5b8c\u6210");
						$('#mies3 .progress').show();
					}
					function hide(){
						$('#mies3 .progress').hide();
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
			url: 'liganviewlist?username=' + $('#username').val(),
			dataType: 'json',
			colModel : [
			            {display: '\u7ebf\u8def', name : 'line', width : 35, sortable : true, align: 'left'},
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
			            {display: '\u5907\u6ce8', name : 'comments', width : 90, sortable : true, align: 'left'},
			            {display: 'rownum', name : 'rownum', width : 244, sortable : true, align: 'left', hide: true},
			            {display: 'modify', name : 'modify', width : 56, sortable : true, hide: true, align: 'left'},
			            {display: '\u4fee\u6539\u5386\u53f2', name : 'history', width : 56, sortable : true, align: 'left'}
			            ],
			            usepager: true,
			            title: '\u7acb\u6746\u4fe1\u606f\u8868',
			            useRp: true,
			            rp: 50,
			            showTableToggleBtn: true,
			            width: 970,
			            height: 600,
			            onSuccess: function() {
						$('.ligan-grid .ftitle').text($('.ligan-grid .ftitle').text() + ' -- \u8bbe\u5907\u603b\u8ba1 : ' + arguments.callee.caller.caller.arguments[0].numberCount);
			            	$('.ligan tr').each(function(){
			            		var _this = $(this);
			            		
			            		var modify = _this.find('td[abbr="modify"] div').text();
			            		if(modify.toLowerCase() == 'true') {
			            			_this.css('background', "#F0C36D")
			            		}
			            		
			            		var historyDiv = _this.find('td[abbr="history"] div');
			            		historyDiv.empty().append($("<a href='javascript:void(0)'>\u67e5\u770b</a>"));
			            		historyDiv.find('a').click(function(){
			            			$('.ligan-history-grid').empty().append($('<div class="ligan-history"></div>'));
			            			liganmodifyhistorygridify(_this.find('td[abbr="rownum"] div').text());
			            		});
			            		
			            		var div = _this.find('td[abbr="line"] div');
			            		var linenum = div.text();
			            		div.html($('<a href="javascript:void(0)" rel="#mies3" class="buslinelink"><img src="res/css/images/bus.png" class="busline"></a><a href="javascript:void(0)" rel="#mies1">' + linenum + '</a>'));
			            		
			            		div.find('a.buslinelink').click(function(){
			            			$('#mies3 input[name=liganid]').val(_this.find('td[abbr="rownum"] div').text());
			            			$('#mies3 input[name=line]').val(linenum);

			            			$('#mies3 #img-btn-norm object').remove();
			            			$('#mies3 #img-btn-air object').remove();
			            			$('#mies3 #img-btn-norm #img_uploadQueue').remove();
			            			$('#mies3 #img-btn-air #img_uploadQueue').remove();
			            			
			            			$.ajax({
			            				url: 'uplineview',
			            				data: {username: $('#username').val(), 'linenum': linenum},
			            				success: function(json) {
			            					var uplineListView = $.parseJSON(json);
			            					var rows = uplineListView.rows;
			            					$('#mies3 .details .table table.up').remove();
			            					var table = $('<table class="up"></table>');
			            					$('#mies3 .details .table').append(table);
			            					table.append($('<tr><td>\u4e0a\u884c\u7ad9\u540d</td><td>\u526f\u7ad9\u540d</td><td>\u7ad9\u5740</td><td>\u9996\u672b\u73ed\u65f6\u523b</td></tr>'))
			            					for(var index = 0; index < rows.length; index++) {
			            						var row = rows[index];
			            						var col1 = row.cell[0];
			            						if(col1 == null || col1 == undefined || col1 == 'null') {
			            							col1 = '';
			            						}
			            						var col2 = row.cell[1];
			            						if(col2 == null || col2 == undefined || col2 == 'null') {
			            							col2 = '';
			            						}
			            						var col3 = row.cell[2];
			            						if(col3 == null || col3 == undefined || col3 == 'null') {
			            							col3 = '';
			            						}
			            						var col4 = row.cell[3];
			            						if(col4 == null || col4 == undefined || col4 == 'null') {
			            							col4 = '';
			            						}
			            						var tr = $('<tr class="row"><td><input type="hidden" name="id" value="' +  row.cell[4] + '"><input name="name" value="'
			            								+ col1 + '"></td><td><input name="alias" value="' 
			            								+ col2 + '"></td><td><input name="stopaddress" value="' 
			            								+ col3 + '"></td><td><input name="startend" value="' 
			            								+ col4 + '"></td><td><a href="javascript:void(0)" class="checklink" title="\u4fee\u6539\u7ebf\u8def\u4fe1\u606f"><img class="check" src="res/css/images/check.png"></a>'
			            								+ '<a href="javascript:void(0)" rel="#mies5" class="viewhistory" title="\u4fee\u6539\u5386\u53f2"><img class="check" src="res/css/images/viewhistory.png"></a></td></tr>');
			            						var modified = row.cell[6];
			            						if('true' == modified) {
			            							tr.css('background', '#F0C36D');
			            						}
			            						table.append(tr);
			            						
			            					}
			            					table.find('tr').each(function(){
			            						var tr = $(this);
			            						$(this).find('.checklink').click(function() {
					            					$.ajax({
						            					url: 'uplineviewmodify',
						            					data: {id: tr.find('input[name=id]').val(),
							            					   username: $('#username').val(),
							            					   name:  $(this).parent().parent().find('input[name=name]').val(),
							            					   alias:  $(this).parent().parent().find('input[name=alias]').val(),
							            					   stopaddress:  $(this).parent().parent().find('input[name=stopaddress]').val(),
							            					   startend:  $(this).parent().parent().find('input[name=startend]').val(),
							            					   liganid: $('#mies3 input[name=liganid]').val()
							            					   },
						            					success: function(json) {
						            						var retCode = $.parseJSON(json);
						            						tr.parent().parent().find('input[name=id]').val(retCode.entity.id);
						            						$('.simple_overlay .progress').text('\u4fee\u6539\u5b8c\u6210');
						            						$('.simple_overlay .progress').show();
						            						function hide(){
						            							$('.simple_overlay .progress').hide();
						            						}
						            						setTimeout(hide, 3000);
						            					}
						            				});
			            						});
			            						$('#mies5 .close').unbind('click');
			            						$('#mies5 .close').click(function(){
			            							$('#mies5').hide();
			            							var liganid = $('#mies5 input[name=liganid]').val();
			            							$('.ligan tr').each(function(){
			            								if($(this).find('td[abbr="rownum"] div').text() == liganid) {
			            									$(this).find('td[abbr=line] .buslinelink').click();
			            								}
			            							});
			            						});
			            						$(this).find('.viewhistory').click(function(){
			            							//
			            							$('#mies5 input[name=hname]').val('');
			            							$('#mies5 input[name=halias]').val('');
			            							$('#mies5 input[name=hstopaddress]').val('');
			            							$('#mies5 input[name=hstartend]').val('');
			            							$('#mies5 input[name=name]').val('');
			            							$('#mies5 input[name=alias]').val('');
			            							$('#mies5 input[name=stopaddress]').val('');
			            							$('#mies5 input[name=startend]').val('');
			            							$('#mies5 #name').val('');
        				            				$('#mies5 #name').removeClass('text-highlight');
        				            				$('#mies5 #hname').removeClass('text-highlight');
        				            				$('#mies5 #name').parent().removeClass('bg-highlight');
        				            				$('#mies5 #hname').parent().removeClass('bg-highlight');
        				            				$('#mies5 #alias').val('');
        				            				$('#mies5 #alias').removeClass('text-highlight');
        				            				$('#mies5 #halias').removeClass('text-highlight');
        				            				$('#mies5 #alias').parent().removeClass('bg-highlight');
        				            				$('#mies5 #halias').parent().removeClass('bg-highlight');
        				            				$('#mies5 #stopaddress').val('');
        				            				$('#mies5 #stopaddress').removeClass('text-highlight');
        				            				$('#mies5 #hstopaddress').removeClass('text-highlight');
        				            				$('#mies5 #stopaddress').parent().removeClass('bg-highlight');
        				            				$('#mies5 #hstopaddress').parent().removeClass('bg-highlight');
        				            				$('#mies5 #startend').val('');
        				            				$('#mies5 #startend').removeClass('text-highlight');
        				            				$('#mies5 #hstartend').removeClass('text-highlight');
        				            				$('#mies5 #startend').parent().removeClass('bg-highlight');
        				            				$('#mies5 #hstartend').parent().removeClass('bg-highlight');
			            							
			            							$('#mies5 .stophistory-grid').empty();
			            							$('#mies5 .stophistory-grid').append($('<div class="stophistory"></div>'));
			            							$(".stophistory").flexigrid({
			            								url: 'uplinemodifyhistory?username=' + $('#username').val() + '&entity=upline&id=' + tr.find('input[name=id]').val(),
			            								dataType: 'json',
			            								type: 'POST',
			            								colModel : [
			            								            {display: '\u65f6\u95f4', name : 'importtime', width : 240, sortable : true, align: 'left'},
			            								            {display: '\u5907\u6ce8', name : 'importcomments', width : 400, sortable : true, align: 'left'},
			            								            {display: '\u5907\u6ce8', name : 'transactionId', width : 228, sortable : true, align: 'left', hide: true},
			            								            {display: 'id', name : 'id', width : 228, sortable : true, align: 'left', hide: true},
			            								            {display: 'entity', name : 'entity', width : 228, sortable : true, align: 'left', hide: true},
			            								            ],
			            					            usepager: true,
			            					            title: '\u7acb\u6746\u5bfc\u5165\u5386\u53f2\u8bb0\u5f55',
			            					            useRp: true,
			            					            rp: 50,
			            					            showTableToggleBtn: true,
			            					            width: 645,
			            					            height: 400,
			            					            onSuccess: function() {
			            					            	$('.stophistory tr td[abbr=importcomments] div').each(function(){
			            					            		var comments = $(this).text();
			            					            		var entityId = $(this).parent().parent().find('td[abbr="id"] div').text();
				            					            	try {
				            				            			var modifiedField = $.parseJSON(comments);
				            				            			$(this).html($('<a href="javascript:void(0)" >\u7acb\u6746\u8bbe\u5907\u4fee\u6539\u8be6\u60c5</a><span> - ' + modifiedField.modifier + '</span>'));
				            				            			$(this).find('a').click(function(){
				            				            				$('#mies5 #name').val('');
		            				                					$('#mies5 #name').removeClass('text-highlight');
		            				                					$('#mies5 #hname').removeClass('text-highlight');
		            				                					$('#mies5 #name').parent().removeClass('bg-highlight');
		            				                					$('#mies5 #hname').parent().removeClass('bg-highlight');
		            					            					$('#mies5 #alias').val('');
		            					            					$('#mies5 #alias').removeClass('text-highlight');
		            					            					$('#mies5 #halias').removeClass('text-highlight');
		            				                					$('#mies5 #alias').parent().removeClass('bg-highlight');
		            				                					$('#mies5 #halias').parent().removeClass('bg-highlight');
		            					            					$('#mies5 #stopaddress').val('');
		            					            					$('#mies5 #stopaddress').removeClass('text-highlight');
		            					            					$('#mies5 #hstopaddress').removeClass('text-highlight');
		            				                					$('#mies5 #stopaddress').parent().removeClass('bg-highlight');
		            				                					$('#mies5 #hstopaddress').parent().removeClass('bg-highlight');
		            					            					$('#mies5 #startend').val('');
		            					            					$('#mies5 #startend').removeClass('text-highlight');
		            					            					$('#mies5 #hstartend').removeClass('text-highlight');
		            				                					$('#mies5 #startend').parent().removeClass('bg-highlight');
		            				                					$('#mies5 #hstartend').parent().removeClass('bg-highlight');
				            				            				$.ajax({
				            				            					url: 'uplineshow',
				            					            				data: {'id': modifiedField.id,
				            					            					   username: $('#username').val()},
				            					            				success: function(json) {
				            					            					var upline = $.parseJSON(json);
				            					            					$('#mies5 #hname').val(upline.name);
				            					            					$('#mies5 #halias').val(upline.alias);
				            					            					$('#mies5 #hstopaddress').val(upline.stopaddress);
				            					            					$('#mies5 #hstartend').val(upline.startend);
				            					            					
				            					            					var fields = modifiedField.fields;
				            					            					for(var index = 0; index < fields.length; index++) {
				            					            						var field = fields[index];
				            					            						$('#mies5 #h' + field).addClass('text-highlight');
				            					            						$('#mies5 #h' + field).parent().addClass('bg-highlight');
				            					            					}
				            					            				}
				            				            				});
				            				            				
				            				            				$.ajax({
				            				                				url: 'uplineshow',
				            				                				data: {'id': entityId,
				            				                					   username: $('#username').val()},
				            				                				success: function(json) {
				            					            					var downline = $.parseJSON(json);
				            					            					$('#mies5 #name').val(downline.name);
				            					            					$('#mies5 #alias').val(downline.alias);
				            					            					$('#mies5 #stopaddress').val(downline.stopaddress);
				            					            					$('#mies5 #startend').val(downline.startend);
				            					            					
				            					            					var fields = modifiedField.fields;
				            					            					for(var index = 0; index < fields.length; index++) {
				            					            						var field = fields[index];
				            					            						$('#mies5 #' + field).addClass('text-highlight');
				            					            						$('#mies5 #' + field).parent().addClass('bg-highlight');
				            					            					}
				            				                				}
				            				                			});
				            				            			});
				            				            			
				            				            		} catch(e) {
				            				            			// TODO
				            				            		}
			            					            	});
			            					            	$('#mies5').dialog({width:1050 });
			            					            }
			            							});
			            						});
			            					});
			            				}
			            			});
			            			
			            			$.ajax({
			            				url: 'downlineview',
			            				data: {username: $('#username').val(), 'linenum': linenum},
			            				success: function(json) {
			            					var downlineListView = $.parseJSON(json);
			            					var rows = downlineListView.rows;
			            					$('#mies3 .details .table table.down').remove();
			            					var table = $('<table class="down"></table>');
			            					$('#mies3 .details .table').append(table);
			            					table.append($('<tr><td>\u4e0b\u884c\u7ad9\u540d</td><td>\u526f\u7ad9\u540d</td><td>\u7ad9\u5740</td><td>\u9996\u672b\u73ed\u65f6\u523b</td></tr>'))
			            					for(var index = 0; index < rows.length; index++) {
			            						var row = rows[index];
			            						var col1 = row.cell[0];
			            						if(col1 == null || col1 == undefined || col1 == 'null') {
			            							col1 = '';
			            						}
			            						var col2 = row.cell[1];
			            						if(col2 == null || col2 == undefined || col2 == 'null') {
			            							col2 = '';
			            						}
			            						var col3 = row.cell[2];
			            						if(col3 == null || col3 == undefined || col3 == 'null') {
			            							col3 = '';
			            						}
			            						var col4 = row.cell[3];
			            						if(col4 == null || col4 == undefined || col4 == 'null') {
			            							col4 = '';
			            						}
			            						var tr = $('<tr class="row"><td><input type="hidden" name="id" value="' +  row.cell[4] + '"><input name="name" value="'
			            								+ col1 + '"></td><td><input name="alias" value="' 
			            								+ col2 + '"></td><td><input name="stopaddress" value="' 
			            								+ col3 + '"></td><td><input name="startend" value="' 
			            								+ col4 + '"></td><td><a href="javascript:void(0)" class="checklink" title="\u4fee\u6539\u7ebf\u8def\u4fe1\u606f"><img class="check" src="res/css/images/check.png"></a>' 
			            								+ '<a href="javascript:void(0)" rel="#mies5" class="viewhistory" title="\u4fee\u6539\u5386\u53f2"><img class="check" src="res/css/images/viewhistory.png"></a></td></tr>');
			            						var modified = row.cell[6];
			            						if('true' == modified) {
			            							tr.find('td').css('background', '#F0C36D');
			            						}
			            						table.append(tr);
			            						
			            					}
			            					table.find('tr').each(function(){
			            						var tr = $(this);
			            						$(this).find('.checklink').click(function() {
					            					$.ajax({
						            					url: 'downlineviewmodify',
						            					data: {id: tr.find('input[name=id]').val(),
							            					   username: $('#username').val(),
							            					   name:  $(this).parent().parent().find('input[name=name]').val(),
							            					   alias:  $(this).parent().parent().find('input[name=alias]').val(),
							            					   stopaddress:  $(this).parent().parent().find('input[name=stopaddress]').val(),
							            					   startend:  $(this).parent().parent().find('input[name=startend]').val(),
							            					   liganid: $('#mies3 input[name=liganid]').val()
							            					   },
						            					success: function(json) {
						            						var retCode = $.parseJSON(json);
						            						tr.parent().parent().find('input[name=id]').val(retCode.entity.id);
						            						$('.simple_overlay .progress').text('\u4fee\u6539\u5b8c\u6210');
						            						$('.simple_overlay .progress').show();
						            						function hide(){
						            							$('.simple_overlay .progress').hide();
						            						}
						            						setTimeout(hide, 3000);
						            					}
						            				});
			            						});
			            						$(this).find('.viewhistory').click(function(){
			            							$('#mies5').dialog({width:800 });
			            						});
			            						$('#mies5 .close').unbind('click');
			            						$('#mies5 .close').click(function(){
			            							$('#mies5').hide();
			            							var liganid = $('#mies5 input[name=liganid]').val();
			            							$('.ligan tr').each(function(){
			            								if($(this).find('td[abbr=rownum] div').text() == liganid) {
			            									$(this).find('td[abbr=line] .buslinelink').click();
			            								}
			            							});
			            						});
			            						$(this).find('.viewhistory').click(function(){
			            							//
			            							$('#mies5 input[name=liganid]').val(_this.find('td[abbr="rownum"] div').text());
			            							$('#mies5 input[name=hname]').val('');
			            							$('#mies5 input[name=halias]').val('');
			            							$('#mies5 input[name=hstopaddress]').val('');
			            							$('#mies5 input[name=hstartend]').val('');
			            							$('#mies5 input[name=name]').val('');
			            							$('#mies5 input[name=alias]').val('');
			            							$('#mies5 input[name=stopaddress]').val('');
			            							$('#mies5 input[name=startend]').val('');
			            							$('#mies5 #name').val('');
        				            				$('#mies5 #name').removeClass('text-highlight');
        				            				$('#mies5 #hname').removeClass('text-highlight');
        				            				$('#mies5 #name').parent().removeClass('bg-highlight');
        				            				$('#mies5 #hname').parent().removeClass('bg-highlight');
        				            				$('#mies5 #alias').val('');
        				            				$('#mies5 #alias').removeClass('text-highlight');
        				            				$('#mies5 #halias').removeClass('text-highlight');
        				            				$('#mies5 #alias').parent().removeClass('bg-highlight');
        				            				$('#mies5 #halias').parent().removeClass('bg-highlight');
        				            				$('#mies5 #stopaddress').val('');
        				            				$('#mies5 #stopaddress').removeClass('text-highlight');
        				            				$('#mies5 #hstopaddress').removeClass('text-highlight');
        				            				$('#mies5 #stopaddress').parent().removeClass('bg-highlight');
        				            				$('#mies5 #hstopaddress').parent().removeClass('bg-highlight');
        				            				$('#mies5 #startend').val('');
        				            				$('#mies5 #startend').removeClass('text-highlight');
        				            				$('#mies5 #hstartend').removeClass('text-highlight');
        				            				$('#mies5 #startend').parent().removeClass('bg-highlight');
        				            				$('#mies5 #hstartend').parent().removeClass('bg-highlight');
			            							
			            							$('#mies5 .stophistory-grid').empty();
			            							$('#mies5 .stophistory-grid').append($('<div class="stophistory"></div>'));
			            							$('#mies5 .details').append($('<div class="stophistory"></div>'));
			            							$(".stophistory").flexigrid({
			            								url: 'downlinemodifyhistory?username=' + $('#username').val() + '&entity=downline&id=' + tr.find('input[name=id]').val(),
			            								dataType: 'json',
			            								type: 'POST',
			            								colModel : [
			            								            {display: '\u65f6\u95f4', name : 'importtime', width : 240, sortable : true, align: 'left'},
			            								            {display: '\u5907\u6ce8', name : 'importcomments', width : 400, sortable : true, align: 'left'},
			            								            {display: '\u5907\u6ce8', name : 'transactionId', width : 228, sortable : true, align: 'left', hide: true},
			            								            {display: 'id', name : 'id', width : 228, sortable : true, align: 'left', hide: true},
			            								            {display: 'entity', name : 'entity', width : 228, sortable : true, align: 'left', hide: true},
			            								            ],
			            					            usepager: true,
			            					            title: '\u7acb\u6746\u5bfc\u5165\u5386\u53f2\u8bb0\u5f55',
			            					            useRp: true,
			            					            rp: 50,
			            					            showTableToggleBtn: true,
			            					            width: 645,
			            					            height: 400,
			            					            onSuccess: function() {
			            					            	$('.stophistory tr td[abbr=importcomments] div').each(function(){
			            					            		var comments = $(this).text();
			            					            		var entityId = $(this).parent().parent().find('td[abbr="id"] div').text();
				            					            	try {
				            				            			var modifiedField = $.parseJSON(comments);
				            				            			$(this).html($('<a href="javascript:void(0)" >\u7acb\u6746\u8bbe\u5907\u4fee\u6539\u8be6\u60c5</a><span> - ' + modifiedField.modifier + '</span>'));
				            				            			$(this).find('a').click(function(){
				            				            				$('#mies5 #name').val('');
				            				            				$('#mies5 #name').removeClass('text-highlight');
				            				            				$('#mies5 #hname').removeClass('text-highlight');
				            				            				$('#mies5 #name').parent().removeClass('bg-highlight');
				            				            				$('#mies5 #hname').parent().removeClass('bg-highlight');
				            				            				$('#mies5 #alias').val('');
				            				            				$('#mies5 #alias').removeClass('text-highlight');
				            				            				$('#mies5 #halias').removeClass('text-highlight');
				            				            				$('#mies5 #alias').parent().removeClass('bg-highlight');
				            				            				$('#mies5 #halias').parent().removeClass('bg-highlight');
				            				            				$('#mies5 #stopaddress').val('');
				            				            				$('#mies5 #stopaddress').removeClass('text-highlight');
				            				            				$('#mies5 #hstopaddress').removeClass('text-highlight');
				            				            				$('#mies5 #stopaddress').parent().removeClass('bg-highlight');
				            				            				$('#mies5 #hstopaddress').parent().removeClass('bg-highlight');
				            				            				$('#mies5 #startend').val('');
				            				            				$('#mies5 #startend').removeClass('text-highlight');
				            				            				$('#mies5 #hstartend').removeClass('text-highlight');
				            				            				$('#mies5 #startend').parent().removeClass('bg-highlight');
				            				            				$('#mies5 #hstartend').parent().removeClass('bg-highlight');
				            				            				$.ajax({
				            				            					url: 'downlineshow',
				            					            				data: {'id': modifiedField.id,
				            					            					   username: $('#username').val()},
				            					            				success: function(json) {
				            					            					var upline = $.parseJSON(json);
				            					            					$('#mies5 #hname').val(upline.name);
				            					            					$('#mies5 #halias').val(upline.alias);
				            					            					$('#mies5 #hstopaddress').val(upline.stopaddress);
				            					            					$('#mies5 #hstartend').val(upline.startend);
				            					            					
				            					            					var fields = modifiedField.fields;
				            					            					for(var index = 0; index < fields.length; index++) {
				            					            						var field = fields[index];
				            					            						$('#mies5 #h' + field).addClass('text-highlight');
				            					            						$('#mies5 #h' + field).parent().addClass('bg-highlight');
				            					            					}
				            					            				}
				            				            				});
				            				            				
				            				            				$.ajax({
				            				                				url: 'downlineshow',
				            				                				data: {'id': entityId,
				            				                					   username: $('#username').val()},
				            				                				success: function(json) {
				            					            					var downline = $.parseJSON(json);
				            					            					$('#mies5 #stop').val(downline.name);
				            					            					$('#mies5 #alias').val(downline.alias);
				            					            					$('#mies5 #stopaddress').val(downline.stopaddress);
				            					            					$('#mies5 #startend').val(downline.startend);
				            					            					
				            					            					var fields = modifiedField.fields;
				            					            					for(var index = 0; index < fields.length; index++) {
				            					            						var field = fields[index];
				            					            						$('#mies5 #' + field).addClass('text-highlight');
				            					            						$('#mies5 #' + field).parent().addClass('bg-highlight');
				            					            					}
				            				                				}
				            				                			});
				            				            			});
				            				            			
				            				            		} catch(e) {
				            				            			// TODO
				            				            		}
			            					            	});
			            					            }
			            							});
			            						});
			            					});liganshow.jsp
			            				}
			            			});

			            			$.ajax({
				            			url: 'busticketviewshow',
				            			data: {username: $('#username').val(), 'line': linenum, status:'normal', id:-1},
				            			success: function(json) {
				            				if('FAILURE' != json) {
				            					$('#mies3 .details .tabletic table').remove();
				            					$('#mies3 .details .tableair table').remove();
				            					var busticket = $.parseJSON(json);
					            				var table = $('<table></table>');
					            				table.append($('<tr><td>\u8f66\u578b</td><td>\u7968\u4ef7</td><td>\u7968\u4ef7\u7c7b\u578b</td><td>\u65e0\u4eba\u552e\u7968</td><td>\u8fd0\u8425\u5355\u4f4d</td><td>\u7ebf\u8def</td></tr>'));
					            				table.append($('<tr><td><input type="hidden" name="bustype" value="' + busticket.type + '"><input type="hidden" name="id" value="' + busticket.id + '"><select name="bustype" ><option value="norm" >\u666e\u901a\u8f66</option><option value="air">\u7a7a\u8c03\u8f66</option>' 
				            							+ '</select></td><td><input name="normPrice" value="' 
				            							+ busticket.normPrice + '"></td><td><select name="pricetype"><option value="single">\u5355\u4e00</option><option value="multi">\u591a\u7ea7</option>' 
				            							+ '</select></td><td><select name="autoSale"><option value="y">\u662f</option><option value="n">\u5426</option>' 
				            							+ '</select></td><td><input name="opunit" value="' 
				            							+ busticket.opunit + '"></td><td><input disabled name="line" value="' 
				            							+ busticket.line + '"></td><td><a id="showtickethistory" rel="#mies6" href="javascript:void(0)">\u7968\u4ef7\u4fee\u6539\u5386\u53f2</a></td></tr>'));
					            				
					            				
					            				table.find('select[name=bustype]').val(busticket.type);
					            				table.find('select[name=autoSale]').val(busticket.autoSale);
					            				table.find('select[name=pricetype]').val(busticket.pricetype);
					            				$('#mies3 .details .tabletic').append(table);
					            				
					            				$('#mies6 input[name=liganid]').val($('#mies3 input[name=liganid]').val());
					            				$('#mies6 input[name=line]').val($('#mies3 input[name=line]').val());
					            				$('#mies6 .close').unbind('click');
					            				$('#mies6 .close').click(function(){
					            					$('#mies6').hide();
					            					var liganid = $('#mies6 input[name=liganid]').val();
					            					$('.ligan tr').each(function(){
					            						if($(this).find('td[abbr="rownum"] div').text() == liganid) {
					            							$(this).find('td[abbr=line] .buslinelink').click();
					            						}
					            					});
					            				});
					            				$('#mies3 #showtickethistory').click(function(){
					            					$('#mies6 .tickethistory-grid').empty();
					            					$('#mies6 .tickethistory-grid').append($('<div class="tickethistory"></div>'));
					            					var bustype = $(this).parent().parent().find('input[name=bustype]').val();
					            					var entity =  'busticket';
					            					if(bustype == 'air') {
					            						entity = 'busticketair';
					            					}
					            					$("#mies6 .tickethistory").flexigrid({
					            						url: 'ticketmodifyhistory?username=' + $('#username').val() + '&entity=' + entity + '&id=' + $(this).parent().parent().find('input[name=id]').val(),
					            						dataType: 'json',
					            						type: 'POST',
					            						colModel : [
					            						            {display: '\u65f6\u95f4', name : 'importtime', width : 240, sortable : true, align: 'left'},
					            						            {display: '\u5907\u6ce8', name : 'importcomments', width : 400, sortable : true, align: 'left'},
					            						            {display: '\u5907\u6ce8', name : 'transactionId', width : 228, sortable : true, align: 'left', hide: true},
					            						            {display: 'id', name : 'id', width : 228, sortable : true, align: 'left', hide: true},
					            						            {display: 'entity', name : 'entity', width : 228, sortable : true, align: 'left', hide: true},
					            						            ],
					            			            usepager: true,
					            			            title: '\u7acb\u6746\u5bfc\u5165\u5386\u53f2\u8bb0\u5f55',
					            			            useRp: true,
					            			            rp: 50,
					            			            showTableToggleBtn: true,
					            			            width: 645,
					            			            height: 300,
					            			            onSuccess: function() {
					            			            	$('.tickethistory tr td[abbr=importcomments] div').each(function(){
					            			            		var comments = $(this).text();
					            			            		var entityId = $(this).parent().parent().find('td[abbr="id"] div').text();
					            				            	try {
					            			            			var modifiedField = $.parseJSON(comments);
					            			            			$(this).html($('<a href="javascript:void(0)" >\u7968\u4ef7\u4fee\u6539\u8be6\u60c5</a><span> - ' + modifiedField.modifier + '</span>'));
					            			            			$(this).find('a').click(function(){
					            			            				$('#mies6 #type').val('');
					            			            				$('#mies6 #htype').val('');
					            			        					$('#mies6 #type').removeClass('text-highlight');
					            			        					$('#mies6 #htype').removeClass('text-highlight');
					            			        					$('#mies6 #type').parent().removeClass('bg-highlight');
					            			        					$('#mies6 #htype').parent().removeClass('bg-highlight');
					            			        					
					            			        					$('#mies6 #airprice').val('');
					            			        					$('#mies6 #hairprice').val('');
					            			        					$('#mies6 #airprice').removeClass('text-highlight');
					            			        					$('#mies6 #hairprice').removeClass('text-highlight');
					            			        					$('#mies6 #airprice').parent().removeClass('bg-highlight');
					            			        					$('#mies6 #hairprice').parent().removeClass('bg-highlight');
					            			        					
					            			        					$('#mies6 #normprice').val('');
					            			        					$('#mies6 #hnormprice').val('');
					            			        					$('#mies6 #normprice').removeClass('text-highlight');
					            			        					$('#mies6 #hnromprice').removeClass('text-highlight');
					            			        					$('#mies6 #normprice').parent().removeClass('bg-highlight');
					            			        					$('#mies6 #hnormprice').parent().removeClass('bg-highlight');
					            			        					
					            			        					$('#mies6 #pricetype').val('');
					            			        					$('#mies6 #hpricetype').val('');
					            			        					$('#mies6 #pricetype').removeClass('text-highlight');
					            			        					$('#mies6 #hpricetype').removeClass('text-highlight');
					            			        					$('#mies6 #pricetype').parent().removeClass('bg-highlight');
					            			        					$('#mies6 #hpricetype').parent().removeClass('bg-highlight');
					            			        					
					            			        					$('#mies6 #autoSale').val('');
					            			        					$('#mies6 #hautoSale').val('');
					            			        					$('#mies6 #autoSale').removeClass('text-highlight');
					            			        					$('#mies6 #hautoSale').removeClass('text-highlight');
					            			        					$('#mies6 #autoSale').parent().removeClass('bg-highlight');
					            			        					$('#mies6 #hautoSale').parent().removeClass('bg-highlight');
					            			        					
					            			        					$('#mies6 #opunit').val('');
					            			        					$('#mies6 #hopunit').val('');
					            			        					$('#mies6 #opunit').removeClass('text-highlight');
					            			        					$('#mies6 #hopunit').removeClass('text-highlight');
					            			        					$('#mies6 #opunit').parent().removeClass('bg-highlight');
					            			        					$('#mies6 #hopunit').parent().removeClass('bg-highlight');
					            			        					
					            			        					var hentity = modifiedField.entity;
					            			        					
					            			            				$.ajax({
					            			            					url: hentity + 'viewshow',
					            				            				data: {'id': modifiedField.id, status:'as-is', line: $('#mies6 input[name=line]').val(),
					            				            					   username: $('#username').val()},
					            				            				success: function(json) {
					            				            					var busticket = $.parseJSON(json);
					            				            					$('#mies6 #htype').val(busticket.type);
					            				            					if(busticket.type == 'air') {
						            				            					var airprice = busticket.airPrice;
						            				            					if(null == airprice || 'null' == airprice || 'undefined' == airprice) {
						            				            						airprice = '';
						            				            					}
						            				            					$('#mies6 #hairPrice').val(airprice);
					            				            					} else {
						            				            					var normprice = busticket.normPrice;
						            				            					if(null == normprice || 'null' == normprice || 'undefined' == normprice) {
						            				            						normprice = '';
						            				            					}
						            				            					$('#mies6 #hnormPrice').val(normprice);
					            				            					}
					            				            					$('#mies6 #hpricetype').val(busticket.pricetype);
					            				            					$('#mies6 #hautoSale').val(busticket.autoSale);
					            				            					$('#mies6 #hopunit').val(busticket.opunit);
					            				            					
					            				            					var fields = modifiedField.fields;
					            				            					for(var index = 0; index < fields.length; index++) {
					            				            						var field = fields[index];
					            				            						if(field == 'normPrice' && busticket.type == 'air') {
					            				            							field = 'airPrice';
					            				            						}
					            				            						if(field == 'airPrice' && busticket.type == 'norm') {
					            				            							field = 'normPrice';
					            				            						}
					            				            						$('#mies6 #h' + field).addClass('text-highlight');
					            				            						$('#mies6 #h' + field).parent().addClass('bg-highlight');
					            				            					}
					            				            				}
					            			            				});
					            			            				var entity = $(this).parent().parent().parent().find('td[abbr=entity] div').text();
					            			            				$.ajax({
					            			                				url: entity + 'viewshow',
					            			                				data: {'id': entityId, status:'as-is', line: $('#mies6 input[name=line]').val(),
					            			                					   username: $('#username').val()},
					            			                				success: function(json) {
					            				            					var busticket = $.parseJSON(json);
					            				            					$('#mies6 #type').val(busticket.type);
					            				            					if(busticket.type == 'air') {
						            				            					var airprice = busticket.airPrice;
						            				            					if(null == airprice || 'null' == airprice || 'undefined' == airprice) {
						            				            						airprice = '';
						            				            					}
						            				            					$('#mies6 #airPrice').val(airprice);
					            				            					} else {
					            				            						var normprice = busticket.normPrice;
					            				            						if(null == normprice || 'null' == normprice || 'undefined' == normprice) {
					            				            							normprice = '';
					            				            						}
					            				            						$('#mies6 #normPrice').val(normprice);
					            				            					}
					            				            					$('#mies6 #pricetype').val(busticket.pricetype);
					            				            					$('#mies6 #autoSale').val(busticket.autoSale);
					            				            					$('#mies6 #opunit').val(busticket.opunit);
					            				            					
					            				            					var fields = modifiedField.fields;
					            				            					for(var index = 0; index < fields.length; index++) {
					            				            						var field = fields[index];
					            				            						if(field == 'normPrice' && busticket.type == 'air') {
					            				            							field = 'airPrice';
					            				            						}
					            				            						if(field == 'airPrice' && busticket.type == 'norm') {
					            				            							field = 'normPrice';
					            				            						}
					            				            						$('#mies6 #' + field).addClass('text-highlight');
					            				            						$('#mies6 #' + field).parent().addClass('bg-highlight');
					            				            					}
					            			                				}
					            			                			});
					            			            			});
					            			            			
					            			            		} catch(e) {
					            			            			// TODO
					            			            		}
					            			            	});
					            			            	$('#mies6').dialog({width:1050 });
					            			            }
					            					});
					            				});
					            				
					            				$('.simple_overlay_line #norm').show();
					            				$('.simple_overlay_line #img-btn-norm #img_upload_norm').uploadify({
						            			    'uploader'  : 'uploadify/uploadify.swf',
						            			    'script'    : 'busticketimg-uploadify',
						            			    'scriptData': {'liganid': $('#mies3 input[name=liganid]').val(), id: $('#mies3 .tabletic input[name=id]').val(),
						            			    	username: $('#username').val(), entity: "BusTicket", line: $('#mies3 .tabletic input[name=line]').val()},
						            			    'method'	: 'get',
						            			    'cancelImg' : 'uploadify/cancel.png',
						            			    'folder'    : '/uploads',
						            			    'fileDesc'  : '\u7acb\u6746\u56fe\u7247',
						            			    'fileExt'   : '*.JPEG;*.JPG',
						            			    'auto'      : true,
						            			    'multi'		: false,
						            			    'removeCompleted' : false,
						            			    'onError'   : function(event,ID,fileObj,errorObj) {
						            			    	$('.simple_overlay_line #img-btn-norm #img_upload .percentage').text(' - \u7acb\u6746\u56fe\u7247\u4e0a\u4f20\u5931\u8d25');
						            			    }
						            			  });
					            				$('.simple_overlay_line #multi #img-norm a').attr('href', 'busticketimg-show?id=' + $('#mies3 .tabletic input[name=id]').val() + '&username=' + $('#username').val());
					            				$('.simple_overlay_line #multi #img-norm img').attr('src', 'busticketimg-show?id=' + $('#mies3 .tabletic input[name=id]').val() + '&username=' + $('#username').val());
				            				}
				            				
				            			}
				            		});
				            		$.ajax({
				            			url: 'busticketairviewshow',
				            			data: {username: $('#username').val(), 'line': linenum, status:'normal', id:-1},
				            			success: function(json) {
				            				if('FAILURE' != json) {
				            					$('#mies3 .details .tabletic table').remove();
				            					$('#mies3 .details .tableair table').remove();
				            					var busticket = $.parseJSON(json);
				            					var table = $('<table></table>');
				            					table.append($('<tr><td>\u8f66\u578b</td><td>\u7968\u4ef7</td><td>\u7968\u4ef7\u7c7b\u578b</td><td>\u65e0\u4eba\u552e\u7968</td><td>\u8fd0\u8425\u5355\u4f4d</td><td>\u7ebf\u8def</td></tr>'));
				            					table.append($('<tr><td><input type="hidden" name="bustype" value="' + busticket.type + '"><input type="hidden" name="id" value="' + busticket.id + '"><select name="bustype" ><option value="norm" >\u666e\u901a\u8f66</option><option value="air">\u7a7a\u8c03\u8f66</option>' 
				            							+ '</select></td><td><input name="airPrice" value="' 
				            							+ busticket.airPrice + '"></td><td><select name="pricetype"><option value="single">\u5355\u4e00</option><option value="multi">\u591a\u7ea7</option>' 
				            							+ '</select></td><td><select name="autoSale"><option value="y">\u662f</option><option value="n">\u5426</option>' 
				            							+ '</select></td><td><input name="opunit" value="' 
				            							+ busticket.opunit + '"></td><td><input disabled name="line" value="' 
				            							+ busticket.line + '"></td><td><a id="showairtickethistory" rel="#mies6" href="javascript:void(0)">\u7968\u4ef7\u4fee\u6539\u5386\u53f2</a></td></tr>'));
				            					
				            					table.find('select[name=bustype]').val(busticket.type);
					            				table.find('select[name=autoSale]').val(busticket.autoSale);
					            				table.find('select[name=pricetype]').val(busticket.pricetype);
					            				$('#mies3 .details .tableair').append(table);
					            				
					            				$('#mies3 #showairtickethistory').click(function(){
			            							$('#mies6').dialog({width:1050});
			            						});
					            				$('#mies6 input[name=liganid]').val($('#mies3 input[name=liganid]').val());
					            				$('#mies6 input[name=line]').val($('#mies3 input[name=line]').val());
				            					$('#mies6 .close').unbind('click');
				            					$('#mies6 .close').click(function(){
				            						$('#mies6').hide();
				            						var liganid = $('#mies6 input[name=liganid]').val();
				            						$('.ligan tr').each(function(){
				            							if($(this).find('td[abbr="rownum"] div').text() == liganid) {
				            								$(this).find('td[abbr=line] .buslinelink').click();
				            							}
				            						});
				            					});
				            					
				            					$('#mies3 #showairtickethistory').click(function(){
				            						$('#mies6 .tickethistory-grid').empty();
				            						$('#mies6 .tickethistory-grid').append($('<div class="tickethistory"></div>'));
				            						
				            						var bustype = $(this).parent().parent().find('input[name=bustype]').val();
					            					var entity =  'busticket';
					            					if(bustype == 'air') {
					            						entity = 'busticketair';
					            					}
				            						
				            						$("#mies6 .tickethistory").flexigrid({
				            							url: 'ticketmodifyhistory?username=' + $('#username').val() + '&entity=' + entity + '&id=' + $(this).parent().parent().find('input[name=id]').val(),
				            							dataType: 'json',
				            							type: 'POST',
				            							colModel : [
				            							            {display: '\u65f6\u95f4', name : 'importtime', width : 240, sortable : true, align: 'left'},
				            							            {display: '\u5907\u6ce8', name : 'importcomments', width : 400, sortable : true, align: 'left'},
				            							            {display: '\u5907\u6ce8', name : 'transactionId', width : 228, sortable : true, align: 'left', hide: true},
				            							            {display: 'id', name : 'id', width : 228, sortable : true, align: 'left', hide: true},
				            							            {display: 'entity', name : 'entity', width : 228, sortable : true, align: 'left', hide: true},
				            							            ],
				            				            usepager: true,
				            				            title: '\u7acb\u6746\u5bfc\u5165\u5386\u53f2\u8bb0\u5f55',
				            				            useRp: true,
				            				            rp: 50,
				            				            showTableToggleBtn: true,
				            				            width: 645,
				            				            height: 300,
				            				            onSuccess: function() {
				            				            	$('.tickethistory tr td[abbr=importcomments] div').each(function(){
				            				            		var comments = $(this).text();
				            				            		var entityId = $(this).parent().parent().find('td[abbr="id"] div').text();
				            					            	try {
				            				            			var modifiedField = $.parseJSON(comments);
				            				            			$(this).html($('<a href="javascript:void(0)" >\u7968\u4ef7\u4fee\u6539\u8be6\u60c5</a><span> - ' + modifiedField.modifier + '</span>'));
				            				            			$(this).find('a').click(function(){
				            				            				$('#mies6 #type').val('');
				            				            				$('#mies6 #htype').val('');
					            			        					$('#mies6 #type').removeClass('text-highlight');
					            			        					$('#mies6 #htype').removeClass('text-highlight');
					            			        					$('#mies6 #type').parent().removeClass('bg-highlight');
					            			        					$('#mies6 #htype').parent().removeClass('bg-highlight');
					            			        					
					            			        					$('#mies6 #airprice').val('');
					            			        					$('#mies6 #hairprice').val('');
					            			        					$('#mies6 #airprice').removeClass('text-highlight');
					            			        					$('#mies6 #hairprice').removeClass('text-highlight');
					            			        					$('#mies6 #airprice').parent().removeClass('bg-highlight');
					            			        					$('#mies6 #hairprice').parent().removeClass('bg-highlight');
					            			        					
					            			        					$('#mies6 #normprice').val('');
					            			        					$('#mies6 #hnormprice').val('');
					            			        					$('#mies6 #normprice').removeClass('text-highlight');
					            			        					$('#mies6 #hnromprice').removeClass('text-highlight');
					            			        					$('#mies6 #normprice').parent().removeClass('bg-highlight');
					            			        					$('#mies6 #hnormprice').parent().removeClass('bg-highlight');
					            			        					
					            			        					$('#mies6 #pricetype').val('');
					            			        					$('#mies6 #hpricetype').val('');
					            			        					$('#mies6 #pricetype').removeClass('text-highlight');
					            			        					$('#mies6 #hpricetype').removeClass('text-highlight');
					            			        					$('#mies6 #pricetype').parent().removeClass('bg-highlight');
					            			        					$('#mies6 #hpricetype').parent().removeClass('bg-highlight');
					            			        					
					            			        					$('#mies6 #autoSale').val('');
					            			        					$('#mies6 #hautoSale').val('');
					            			        					$('#mies6 #autoSale').removeClass('text-highlight');
					            			        					$('#mies6 #hautoSale').removeClass('text-highlight');
					            			        					$('#mies6 #autoSale').parent().removeClass('bg-highlight');
					            			        					$('#mies6 #hautoSale').parent().removeClass('bg-highlight');
					            			        					
					            			        					$('#mies6 #opunit').val('');
					            			        					$('#mies6 #hopunit').val('');
					            			        					$('#mies6 #opunit').removeClass('text-highlight');
					            			        					$('#mies6 #hopunit').removeClass('text-highlight');
					            			        					$('#mies6 #opunit').parent().removeClass('bg-highlight');
					            			        					$('#mies6 #hopunit').parent().removeClass('bg-highlight');
					            			        					var entityMapping = {busticketair:'BusTicketAirView', busticket:'BusTicketView'};
					            			        					var hentity = modifiedField.entity;
				            				            				$.ajax({
				            				            					url: hentity + 'viewshow',
				            					            				data: {'id': modifiedField.id, status:'as-is', line: $('#mies6 input[name=line]').val(),
				            					            					   username: $('#username').val()},
				            					            				success: function(json) {
					            				            					var busticket = $.parseJSON(json);
					            				            					$('#mies6 #htype').val(busticket.type);
					            				            					if(busticket.type == 'air') {
						            				            					var airprice = busticket.airPrice;
						            				            					if(null == airprice || 'null' == airprice || 'undefined' == airprice) {
						            				            						airprice = '';
						            				            					}
						            				            					$('#mies6 #hairPrice').val(airprice);
					            				            					} else {
						            				            					var normprice = busticket.normPrice;
						            				            					if(null == normprice || 'null' == normprice || 'undefined' == normprice) {
						            				            						normprice = '';
						            				            					}
						            				            					$('#mies6 #hnormPrice').val(normprice);
					            				            					}
					            				            					$('#mies6 #hpricetype').val(busticket.pricetype);
					            				            					$('#mies6 #hautoSale').val(busticket.autoSale);
					            				            					$('#mies6 #hopunit').val(busticket.opunit);
					            				            					
					            				            					var fields = modifiedField.fields;
					            				            					for(var index = 0; index < fields.length; index++) {
					            				            						var field = fields[index];
					            				            						if(field == 'normPrice' && busticket.type == 'air') {
					            				            							field = 'airPrice';
					            				            						}
					            				            						if(field == 'airPrice' && busticket.type == 'norm') {
					            				            							field = 'normPrice';
					            				            						}
					            				            						$('#mies6 #h' + field).addClass('text-highlight');
					            				            						$('#mies6 #h' + field).parent().addClass('bg-highlight');
					            				            					}
					            				            					$('#mies6 #himg img').attr({src:modifiedField.entity + 'img-show?entity=' + entityMapping[modifiedField.entity]+ '&username=' + $('#username').val() + '&id=' + modifiedField.id});
					            				            				}
				            				            				});
				            				            				
				            				            				var entity = $(this).parent().parent().parent().find('td[abbr=entity] div').text();
				            				            				$.ajax({
				            				                				url: entity + 'viewshow',
				            				                				data: {'id': entityId, status:'as-is', line: $('#mies6 input[name=line]').val(),
				            				                					   username: $('#username').val()},
				            				                				success: function(json) {
					            				            					var busticket = $.parseJSON(json);
					            				            					$('#mies6 #type').val(busticket.type);
					            				            					if(busticket.type == 'air') {
						            				            					var airprice = busticket.airPrice;
						            				            					if(null == airprice || 'null' == airprice || 'undefined' == airprice) {
						            				            						airprice = '';
						            				            					}
						            				            					$('#mies6 #airPrice').val(airprice);
					            				            					} else {
						            				            					var normprice = busticket.normPrice;
						            				            					if(null == normprice || 'null' == normprice || 'undefined' == normprice) {
						            				            						normprice = '';
						            				            					}
						            				            					$('#mies6 #normPrice').val(normprice);
					            				            					}
					            				            					$('#mies6 #pricetype').val(busticket.pricetype);
					            				            					$('#mies6 #autoSale').val(busticket.autoSale);
					            				            					$('#mies6 #opunit').val(busticket.opunit);
					            				            					
					            				            					var fields = modifiedField.fields;
					            				            					for(var index = 0; index < fields.length; index++) {
					            				            						var field = fields[index];
					            				            						if(field == 'normPrice' && busticket.type == 'air') {
					            				            							field = 'airPrice';
					            				            						}
					            				            						if(field == 'airPrice' && busticket.type == 'norm') {
					            				            							field = 'normPrice';
					            				            						}
					            				            						$('#mies6 #' + field).addClass('text-highlight');
					            				            						$('#mies6 #' + field).parent().addClass('bg-highlight');
					            				            					}
					            				            					$('#mies6 #img img').attr({src:modifiedField.entity + 'img-show?entity=' + entityMapping[modifiedField.entity]+ '&username=' + $('#username').val() + '&id=' + entityId});
					            				            				}
				            				                			});
				            				            			});
				            				            			
				            				            		} catch(e) {
				            				            			// TODO
				            				            		}
				            				            	});
				            				            }
				            						});
				            					});
				            					
					            				$('.simple_overlay_line #air').show();
					            				$('.simple_overlay_line #img-btn-air #img_upload_air').uploadify({
						            			    'uploader'  : 'uploadify/uploadify.swf',
						            			    'script'    : 'busticketairimg-uploadify',
						            			    'scriptData': {'liganid': $('#mies3 input[name=liganid]').val(), id: $('#mies3 .tableair input[name=id]').val(),
						            			    	username: $('#username').val(), entity: "BusTicketAir", line: $('#mies3 .tableair input[name=line]').val()},
						            			    'method'	: 'get',
						            			    'cancelImg' : 'uploadify/cancel.png',
						            			    'folder'    : '/uploads',
						            			    'fileDesc'  : '\u7acb\u6746\u56fe\u7247',
						            			    'fileExt'   : '*.JPEG;*.JPG',
						            			    'auto'      : true,
						            			    'multi'		: false,
						            			    'removeCompleted' : false,
						            			    'onError'   : function(event,ID,fileObj,errorObj) {
						            			    	$('.simple_overlay_line #img-btn-air #img_upload .percentage').text(' - \u7acb\u6746\u56fe\u7247\u4e0a\u4f20\u5931\u8d25');
						            			    }
						            			  });
					            				
					            				$('.simple_overlay_line #multi #img-air a').attr('href', 'busticketairimg-show?id=' + $('#mies3 .tableair input[name=id]').val() + '&username=' + $('#username').val());
					            				$('.simple_overlay_line #multi #img-air img').attr('src', 'busticketairimg-show?id=' + $('#mies3 .tableair input[name=id]').val() + '&username=' + $('#username').val());
				            				}
				            			}
				            		});

			            		});
			            		
			            		div.find('a[rel="#mies1"]').click(function(){
			            			$('.simple_overlay #rownum').val(_this.find('td[abbr="rownum"] div').text());
			            			$.ajax({
			            				url: 'liganshow',
			            				data: {id: $('.simple_overlay #rownum').val(),
			            					   username: $('#username').val()},
			            				success: function(json) {
			            					$('#mies1').dialog({width:680});
			            					var liganPreview = $.parseJSON(json);
			            					$('#mies1 #line').val(liganPreview.line);
			            					$('#mies1 #area').val(liganPreview.area);
			            					$('#mies1 #number').val(liganPreview.number);
			            					$('#mies1 #road').val(liganPreview.road);
			            					$('#mies1 #stop').val(liganPreview.stop);
			            					$('#mies1 #finalstop').val(liganPreview.finalstop);
			            					$('#mies1 #nextstop').val(liganPreview.nextstop);
			            					$('#mies1 #address').val(liganPreview.address);
			            					$('#mies1 #direction').val(liganPreview.direction);
			            					$('#mies1 #digtime').val(liganPreview.digdate);
			            					$('#mies1 #finishdate').val(liganPreview.finishdate);
			            					$('#mies1 #comments').val(liganPreview.comments);
			            					$('#mies1 #img img').attr('src', 'liganimgshow?entity=LiGan&username=' + $('#username').val() + '&id=' + $('.simple_overlay #rownum').val());
			            				}
			            			});
			            			
			            			$('#img-btn').empty();
			            			$('#img-btn').append($('<input id="img_upload" name="file_upload" type="file" />'));
			            			$('#img_upload').uploadify({
			            			    'uploader'  : 'uploadify/uploadify.swf',
			            			    'script'    : 'liganimguploadify',
			            			    'scriptData': {'id': $('.simple_overlay #rownum').val(),
			            			    	username: $('#username').val(), entity: "LiGan"},
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
			            		});
			            		div.find('a.buslinelink').click(function(){
        							$('#mies3').dialog({width:1050 });
        						});;
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
			            		
			            		var numdiv = _this.find('td[abbr="number"] div');
			            		var num = numdiv.text();
			            		numdiv.html($('<a href="javascript:void(0)" rel="#mies2">' + num + '</a>'));
			            		numdiv.find('a').click(function(){
			            			$.ajax({
			            				url: 'liganviewlist',
			            				data: {number: num,
			            					   username: $('#username').val()},
			            				success: function(json) {
			            					var liganlist = $.parseJSON(json);
			            					$('#rowcount').val(liganlist.total);
			            					$('#number').val(num);
			            					$('#mies2 table .entity').remove();
			            					for(var index = 0; index < liganlist.rows.length;index++) {
			            						var ligan = liganlist.rows[index].cell;
			            						var comments = ligan[11];
			            						if(null == comments || 'null' == comments || undefined == comments) {
			            							comments = '';
			            						}
			            						$('#mies2 table').append($('<tr class="entity"><td><input type="hidden" name="id" value="' + ligan[12] 
			            								+ '" ><input type="text" style="width:40px;" name="line" value="' + ligan[0]
			            								+ '" ></td><td><input type="text" style="width:45px" name="area" value="' + ligan[1] 
			            								+ '" ></td><td><input type="text" style="width:80px" name="road" value="' + ligan[3]
			            								+ '" ></td><td><input type="text" style="width:60px" name="stop" value="' + ligan[4]
			            								+ '" ></td><td><input type="text" style="width:80px" name="address" value="' + ligan[5]
			            								+ '" ></td><td><input type="text" style="width:30px" name="direction" value="' + ligan[6]
			            								+ '" ></td><td><input type="text" style="width:90px" name="finalstop" value="' + ligan[8]
			            								+ '" ></td><td><input type="text" style="width:100px" name="nextstop" value="' + ligan[9]
					            						+ '" ></td><td><input type="text" style="width:100px" name="finishdate" value="' + ligan[10]
			            								+ '" ></td></tr>'));
			            					}
			            					$('#mies2').dialog({width: 800});
			            				}
			            			});
			            			
			            			
			            			
			            		});
			            	});
			            }
		});
	}
	ligangridify();
	
	$('#modify-ligan').click(function(){
		$('#mies2 .progress').text('\u4fee\u6539\u5b8c\u6210');
		$('#mies2 .progress').hide();
		var finished = 0;
		for(var index = 1; index <= $('#rowcount').val(); index++) {
			var tr = $($('#mies2 table tr')[index]);
			$.ajax({url: 'liganmodify',
				data: {id: tr.find('input[name=id]').val(),
					   username: $('#username').val(),
					   number: $('#number').val(),
					   line: tr.find('input[name=line]').val(),
					   area: tr.find('input[name=area]').val(),
					   road: tr.find('input[name=road]').val(),
					   stop: tr.find('input[name=stop]').val(),
					   finalstop: tr.find('input[name=finalstop]').val(),
					   nextstop: tr.find('input[name=nextstop]').val(),
					   address: tr.find('input[name=address]').val(),
					   direction: tr.find('input[name=direction]').val(),
					   finishdate: tr.find('input[name=finishdate]').val(),
					   comments: $('#mies2 #comments').val(),
					   type: $('#mies2 #type').val()
					   },
				success: function(json) {
					finished++;
				}});
		}
		setInterval( function(){
			if(finished == $('#rowcount').val()) {
				$('#mies2 .progress').show();
			}
		}, 300);
	});
	function liganmodifyhistorygridify(id) {
		$(".ligan-history").flexigrid({
			url: 'liganmodifyhistory?username=' + $('#username').val() + '&entity=ligan&id=' + id,
			dataType: 'json',
			type: 'POST',
			colModel : [
			            {display: '\u65f6\u95f4', name : 'importtime', width : 120, sortable : true, align: 'left'},
			            {display: '\u5907\u6ce8', name : 'importcomments', width : 268, sortable : true, align: 'left'},
			            {display: '\u5907\u6ce8', name : 'transactionId', width : 228, sortable : true, align: 'left', hide: true},
			            ],
            usepager: true,
            title: '\u7acb\u6746\u5bfc\u5165\u5386\u53f2\u8bb0\u5f55',
            useRp: true,
            rp: 50,
            showTableToggleBtn: true,
            width: 408,
            height: 600,
            onSuccess: function() {
            	$('.ligan-history input[name=liganid]').val(id);
            	$('.ligan-history tr td[abbr=importcomments] div').each(function(){
            		var comments = $(this).text();
            		try {
            			var modifiedField = $.parseJSON(comments);
            			$(this).html($('<a href="javascript:void(0)" rel="#mies4">\u7acb\u6746\u8bbe\u5907\u4fee\u6539\u8be6\u60c5</a><span> - ' + modifiedField.modifier + '</span>'));
            			$(this).find('a').click(function(){
            				$.ajax({
            					url: 'liganshow',
	            				data: {'id': modifiedField.id,
	            					   username: $('#username').val()},
	            				success: function(json) {
	            					var liganPreview = $.parseJSON(json);
	            					$('#mies4 #hline').val(liganPreview.line).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies4 #harea').val(liganPreview.area).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies4 #hnumber').val(liganPreview.number).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies4 #hroad').val(liganPreview.road).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies4 #hname').val(liganPreview.stop).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies4 #hfinalstop').val(liganPreview.finalstop).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies4 #hnextstop').val(liganPreview.nextstop).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies4 #haddress').val(liganPreview.address).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies4 #hdirection').val(liganPreview.direction).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies4 #hdigtime').val(liganPreview.digdate).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies4 #htype').val(liganPreview.type).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies4 #hfinishdate').val(liganPreview.finishdate).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies4 #hcomments').val(liganPreview.comments).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies4 #himg img').attr('src', 'liganimgshow?entity=LiGan&username=' + $('#username').val() + '&id=' + modifiedField.id).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					var fields = modifiedField.fields;
	            					for(var index = 0; index < fields.length; index++) {
	            						var field = fields[index];
	            						$('#mies4 #h' + field).addClass('text-highlight').parent().addClass('bg-highlight');
	            					}
	            				}
            				});
            				
            				$.ajax({
                				url: 'liganshow',
                				data: {'id': id,
                					   username: $('#username').val()},
                				success: function(json) {
                					var liganPreview = $.parseJSON(json);
                					$('#mies4 #line').val(liganPreview.line).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies4 #area').val(liganPreview.area).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies4 #number').val(liganPreview.number).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies4 #road').val(liganPreview.road).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies4 #stop').val(liganPreview.stop).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies4 #finalstop').val(liganPreview.finalstop).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies4 #nextstop').val(liganPreview.nextstop).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies4 #address').val(liganPreview.address).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies4 #direction').val(liganPreview.direction).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies4 #digtime').val(liganPreview.digdate).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies4 #finishdate').val(liganPreview.finishdate).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies4 #comments').val(liganPreview.comments).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies4 #type').val(liganPreview.type).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies4 #img img').attr('src', 'liganimgshow?entity=LiGan&username=' + $('#username').val() + '&id=' + id).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					var fields = modifiedField.fields;
                					for(var index = 0; index < fields.length; index++) {
                						var field = fields[index];
                						$('#mies4 #' + field).addClass('text-highlight').parent().addClass('bg-highlight');
                					}
                				}
                			});
            			});
            			
            			$(this).find('a').click(function(){
            				$('#mies4').dialog({width:1050});
            			});
            		} catch(e) {
            			// TODO
            		}
            		
            	});
            }
		});
	}
	
});
