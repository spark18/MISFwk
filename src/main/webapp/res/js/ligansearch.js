$(document).ready(function(){
	
	$.tools.dateinput.localize("ch",  {
		months:        '\u4e00\u6708,\u4e8c\u6708,\u4e09\u6708,\u56db\u6708,\u4e94\u6708,\u516d\u6708,\u4e03\u6708,\u516b\u6708,\u4e5d\u6708,\u5341\u6708,\u5341\u4e00\u6708,\u5341\u4e8c\u6708',
		shortMonths:   '\u4e00\u6708,\u4e8c\u6708,\u4e09\u6708,\u56db\u6708,\u4e94\u6708,\u516d\u6708,\u4e03\u6708,\u516b\u6708,\u4e5d\u6708,\u5341\u6708,\u5341\u4e00\u6708,\u5341\u4e8c\u6708',
		days:          '\u661f\u671f\u5929,\u661f\u671f\u4e00,\u661f\u671f\u4e8c,\u661f\u671f\u4e09,\u661f\u671f\u56db,\u661f\u671f\u4e94,\u661f\u671f\u516d',
		shortDays:     '\u661f\u671f\u5929,\u661f\u671f\u4e00,\u661f\u671f\u4e8c,\u661f\u671f\u4e09,\u661f\u671f\u56db,\u661f\u671f\u4e94,\u661f\u671f\u516d'
	});
	$('#line-digdate:date').dateinput({ 
		lang: 'ch', 
		format: 'mmmm dd,yyyy',
		offset: [30, 0]
	});
	$('#line-finishdate:date').dateinput({ 
		lang: 'ch', 
		format: 'mmmm dd,yyyy',
		offset: [30, 0]
	});
	
	$('#mies4 #submitticket').click(function(){
		var oldbustypeair = $('#mies4 .tableair input[name=bustype]').val();
		var newbustypeair = $('#mies4 .tableair select[name=bustype]').val();
		if(oldbustypeair == 'air') {
			var entitytype = 'busticketair';
			var datavector = {
					entity: "busticketair",
					username: $('#username').val(), 
					line: $('#mies4 .tableair input[name=line]').val(), 
					type: $('#mies4 .tableair select[name=bustype]').val(),
					airPrice: $('#mies4 .tableair input[name=airPrice]').val(),
					pricetype: $('#mies4 .tableair select[name=pricetype]').val(),
					autoSale: $('#mies4 .tableair select[name=autoSale]').val(),
					opunit: $('#mies4 .tableair input[name=opunit]').val(),
					liganid: $('#mies4 input[name=liganid]').val()	
			};
		} else {
			entitytype = 'busticket';
			var datavector = {
					entity: "busticket",
					username: $('#username').val(), 
					line: $('#mies4 .tabletic input[name=line]').val(), 
					type: $('#mies4 .tabletic select[name=bustype]').val(),
					normPrice: $('#mies4 .tabletic input[name=normPrice]').val(),
					pricetype: $('#mies4 .tabletic select[name=pricetype]').val(),
					autoSale: $('#mies4 .tabletic select[name=autoSale]').val(),
					opunit: $('#mies4 .tabletic input[name=opunit]').val(),
					liganid: $('#mies4 input[name=liganid]').val()	
			};
		}
		if($('#mies4 .tableair input[name=line]').size() > 0) {
			$.ajax({
				data: datavector,
				url : entitytype + 'modify',
				type: 'POST',
				success: function(result){
					$('#mies4 .progress').hide();
					if('FAILURE' == result) {
						$('#mies4 .progress').text("\u60a8\u65e0\u6743\u6279\u51c6");
						$('#mies4 .progress').show();
					} else {
						$('#mies4 .tableair input[name=bustype]').val(newbustypeair);
						var ticketair = $.parseJSON(result).entity;
						$('#mies4 .tableair input[name=id]').val(ticketair.id);
						$('#mies4 .progress').text("\u4fee\u6539\u5b8c\u6210");
						$('#mies4 .progress').show();
					}
					function hide(){
						$('#mies4 .progress').hide();
					}
					setTimeout(hide, 3000);
					return;
				}
			});
		}
		if($('#mies4 .tabletic input[name=line]').size() > 0) {
			var oldbustype = $('#mies4 .tabletic input[name=bustype]').val();
			var newbustype = $('#mies4 .tabletic select[name=bustype]').val();
			if(oldbustype == 'air') {
				var entitytype = 'busticketair';
				var datavector = {
						entity: "busticketair",
						username: $('#username').val(), 
						line: $('#mies4 .tableair input[name=line]').val(), 
						type: $('#mies4 .tableair select[name=bustype]').val(),
						airPrice: $('#mies4 .tableair input[name=airPrice]').val(),
						pricetype: $('#mies4 .tableair select[name=pricetype]').val(),
						autoSale: $('#mies4 .tableair select[name=autoSale]').val(),
						opunit: $('#mies4 .tableair input[name=opunit]').val(),
						liganid: $('#mies4 input[name=liganid]').val()	
				};
			} else {
				entitytype = 'busticket';
				var datavector = {
						entity: "busticket",
						username: $('#username').val(), 
						line: $('#mies4 .tabletic input[name=line]').val(), 
						type: $('#mies4 .tabletic select[name=bustype]').val(),
						normPrice: $('#mies4 .tabletic input[name=normPrice]').val(),
						pricetype: $('#mies4 .tabletic select[name=pricetype]').val(),
						autoSale: $('#mies4 .tabletic select[name=autoSale]').val(),
						opunit: $('#mies4 .tabletic input[name=opunit]').val(),
						liganid: $('#mies4 input[name=liganid]').val()	
				};
			}
			$.ajax({
				data: datavector,
				url : entitytype + 'modify',
				type: 'POST',
				success: function(result){
					$('#mies4 .progress').hide();
					if('FAILURE' == result) {
						$('#mies4 .progress').text("\u60a8\u65e0\u6743\u6279\u51c6");
						$('#mies4 .progress').show();
					} else {
						$('#mies4 .tabletic input[name=bustype]').val(newbustype);
						var retCode = $.parseJSON(result);
						$('#mies4 .tabletic input[name=id]').val(retCode.entity.id);
						$('#mies4 .progress').text("\u4fee\u6539\u5b8c\u6210");
						$('#mies4 .progress').show();
					}
					function hide(){
						$('#mies4 .progress').hide();
					}
					setTimeout(hide, 3000);
					return;
				}
			});
		}
	});
	
	$('.simple_overlay #submit').click(function() {
		$.ajax({
			url: 'liganmodify',
			data: {id: $('.simple_overlay #rownum').val(),
				   username: $('#username').val(),
				   line: $('#line').val(),
				   area: $('#area').val(),
				   number: $('#number').val(),
				   road: $('#road').val(),
				   stop: $('#stop').val(),
				   finalstop: $('#finalstop').val(),
				   nextstop: $('#nextstop').val(),
				   address: $('#address').val(),
				   direction: $('#direction').val(),
				   digtime: $('#digtime').val(),
				   finishdate: $('#finishdate').val(),
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

	function ligangridify() {
		$(".ligan").flexigrid({
			url: 'ligansearch?username=' + encodeURIComponent($('#username').val())
				+ '&line=' + encodeURIComponent($('input#line-criteria').val()) + '&area=' + encodeURIComponent($('input#line-area').val())
				+ '&number=' + encodeURIComponent($('input#line-number').val())
				+ '&road=' + encodeURIComponent($('input#line-road').val()) + '&stop=' + encodeURIComponent($('input#line-stop').val())
				+ '&address=' + encodeURIComponent($('input#line-address').val())
				+ '&direction=' + encodeURIComponent($('input#line-direction').val()) + '&digdate=' + encodeURIComponent($('input#line-digdate').val())
				+ '&finalstop=' + encodeURIComponent($('input#line-finalstop').val())
				+ '&nextstop=' + encodeURIComponent($('input#line-nextstop').val()) + '&finishdate=' + encodeURIComponent($('input#line-finishdate').val())
				+ '&comments=' + encodeURIComponent($('input#line-comments').val()),
			dataType: 'json',
			colModel : [
			            {display: '\u7ebf\u8def', name : 'line', width : 60, sortable : true, align: 'left'},
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
			            {display: '\u5907\u6ce8', name : 'comments', width : 70, sortable : true, align: 'left'},
			            {display: 'rownum', name : 'rownum', width : 244, sortable : true, align: 'left', hide: true},
			            {display: '\u4fee\u6539\u5386\u53f2', name : 'history', width : 56, sortable : true, align: 'left'},
			            {display: 'type', name : 'type', width : 56, sortable : true, hide: true, align: 'left'},
			            ],
			            usepager: true,
			            title: '\u7acb\u6746\u4fe1\u606f\u8868',
			            useRp: true,
			            rp: 50,
			            showTableToggleBtn: true,
			            width: 970,
			            height: 600,
			            onSuccess: function() {
						$('.ligan-search-result h4').text('\u7acb\u6746\u641c\u7d22\u7ed3\u679c -- \u8bbe\u5907\u603b\u8ba1 : ' + arguments.callee.caller.caller.arguments[0].numberCount);
			            	$('.ligan tr').each(function(){
			            		var _this = $(this);
			            		
			            		var typeDiv = _this.find('td[abbr="type"] div');
			            		var type = typeDiv.text();
			            		
			            		var historyDiv = _this.find('td[abbr="history"] div');
			            		historyDiv.empty().append($("<a href='javascript:void(0)' rel='#mies3'>\u67e5\u770b</a>"));
			            		historyDiv.find('a').click(function(){
			            			$('#mies3 .details').empty().append($('<div class="ligan-history"></div>'));
			            			liganhistorygridify(_this.find('td[abbr="rownum"] div').text());
			            		});
			            		historyDiv.find('a').click(function(){
			            			$('#mies3').dialog({width: 550})
			            		});
			            		
			            		var div = _this.find('td[abbr="line"] div');
			            		var linenum = div.text();
			            		div.html($('<a href="javascript:void(0)" rel="#mies4" class="buslinelink"><img src="res/css/images/bus.png" class="busline"></a><a href="javascript:void(0)" rel="#mies1">' + linenum + '</a>'));
			            		if(type == 'ligan') {
			            			div.prepend('<img src="res/css/images/ligan.png">');
			            		}
			            		div.find('a[rel=#mies1]').click(function(){
			            			$('.simple_overlay #rownum').val(_this.find('td[abbr="rownum"] div').text());
			            			$.ajax({
			            				url: 'liganshow',
			            				data: {id: $('.simple_overlay #rownum').val(),
			            					   username: $('#username').val()},
			            				success: function(json) {
			            					var liganPreview = $.parseJSON(json);
			            					$('#mies1 #line').val(liganPreview.line);
			            					$('#mies1 #area').val(liganPreview.area);
			            					$('#mies1 #number').val(liganPreview.number);
			            					$('#mies1 #road').val(liganPreview.road);
			            					$('#mies1 #stop').val(liganPreview.stop);
			            					$('#mies1 #finalstop').val(liganPreview.finalstop);
			            					$('#mies1 #nextstop').val(liganPreview.nextstop);
			            					$('#mies1 #address').val(liganPreview.addr);
			            					$('#mies1 #direction').val(liganPreview.direction);
			            					$('#mies1 #digtime').val(liganPreview.digdate);
			            					$('#mies1 #finishdate').val(liganPreview.finishdate);
			            					$('#mies1 #comments').val(liganPreview.comments);
			            					$('#mies1 #img img').attr('src', 'liganimgshow?entity=LiGan&username=' + $('#username').val() + '&id=' + $('.simple_overlay #rownum').val());
			            				}
			            			});
			            			
			            			$('#img-btn object').remove();
			            			$('#img-btn #img_uploadQueue').remove();
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
			            		div.find('a[rel=#mies1]').click(function(){
			            			$('#mies1').dialog({width:700});
			            		});
			            		
			            		div.find('a.buslinelink').click(function(){
			            			$('#mies4 input[name=liganid]').val(_this.find('td[abbr="rownum"] div').text());

			            			$('#mies4 #img-btn-norm object').remove();
			            			$('#mies4 #img-btn-air object').remove();
			            			$('#mies4 #img-btn-norm #img_uploadQueue').remove();
			            			$('#mies4 #img-btn-air #img_uploadQueue').remove();
			            			
			            			$.ajax({
			            				url: 'uplineview',
			            				data: {username: $('#username').val(), 'linenum': linenum},
			            				success: function(json) {
			            					var uplineListView = $.parseJSON(json);
			            					var rows = uplineListView.rows;
			            					$('#mies4 .details .table table').remove();
			            					var table = $('<table class="up"></table>');
			            					$('#mies4 .details .table').append(table);
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
			            								+ '<a href="javascript:void(0)" rel="#mies6" class="viewhistory" title="\u4fee\u6539\u5386\u53f2"><img class="check" src="res/css/images/viewhistory.png"></a></td></tr>');
			            						table.append(tr);
			            						
			            					}
			            					table.find('tr').each(function(){
			            						$(this).find('.checklink').click(function() {
					            					$.ajax({
						            					url: 'uplineviewmodify',
						            					data: {id: $(this).parent().parent().find('input[name=id]').val(),
							            					   username: $('#username').val(),
							            					   name:  $(this).parent().parent().find('input[name=name]').val(),
							            					   alias:  $(this).parent().parent().find('input[name=alias]').val(),
							            					   stopaddress:  $(this).parent().parent().find('input[name=stopaddress]').val(),
							            					   startend:  $(this).parent().parent().find('input[name=startend]').val(),
							            					   liganid: $('#mies4 input[name=liganid]').val()
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
			            						
			            						$(this).find('.viewhistory').click(function(){
			            							$('#mies6').dialog();
			            						});
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
			            						var tr = $(this);
			            						$(this).find('.viewhistory').click(function(){
			            							//
			            							$('#mies6 input[name=liganid]').val(_this.find('td[abbr="rownum"] div').text());
			            							$('#mies6 input[name=hname]').val('');
			            							$('#mies6 input[name=halias]').val('');
			            							$('#mies6 input[name=hstopaddress]').val('');
			            							$('#mies6 input[name=hstartend]').val('');
			            							$('#mies6 input[name=name]').val('');
			            							$('#mies6 input[name=alias]').val('');
			            							$('#mies6 input[name=stopaddress]').val('');
			            							$('#mies6 input[name=startend]').val('');
			            							$('#mies6 #name').val('');
        				            				$('#mies6 #name').removeClass('text-highlight');
        				            				$('#mies6 #hname').removeClass('text-highlight');
        				            				$('#mies6 #name').parent().removeClass('bg-highlight');
        				            				$('#mies6 #hname').parent().removeClass('bg-highlight');
        				            				$('#mies6 #alias').val('');
        				            				$('#mies6 #alias').removeClass('text-highlight');
        				            				$('#mies6 #halias').removeClass('text-highlight');
        				            				$('#mies6 #alias').parent().removeClass('bg-highlight');
        				            				$('#mies6 #halias').parent().removeClass('bg-highlight');
        				            				$('#mies6 #stopaddress').val('');
        				            				$('#mies6 #stopaddress').removeClass('text-highlight');
        				            				$('#mies6 #hstopaddress').removeClass('text-highlight');
        				            				$('#mies6 #stopaddress').parent().removeClass('bg-highlight');
        				            				$('#mies6 #hstopaddress').parent().removeClass('bg-highlight');
        				            				$('#mies6 #startend').val('');
        				            				$('#mies6 #startend').removeClass('text-highlight');
        				            				$('#mies6 #hstartend').removeClass('text-highlight');
        				            				$('#mies6 #startend').parent().removeClass('bg-highlight');
        				            				$('#mies6 #hstartend').parent().removeClass('bg-highlight');
			            							
			            							$('#mies6 .stophistory-grid').empty();
			            							$('#mies6 .stophistory-grid').append($('<div class="stophistory"></div>'));
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
				            				            				$('#mies6 #name').val('');
		            				                					$('#mies6 #name').removeClass('text-highlight');
		            				                					$('#mies6 #hname').removeClass('text-highlight');
		            				                					$('#mies6 #name').parent().removeClass('bg-highlight');
		            				                					$('#mies6 #hname').parent().removeClass('bg-highlight');
		            					            					$('#mies6 #alias').val('');
		            					            					$('#mies6 #alias').removeClass('text-highlight');
		            					            					$('#mies6 #halias').removeClass('text-highlight');
		            				                					$('#mies6 #alias').parent().removeClass('bg-highlight');
		            				                					$('#mies6 #halias').parent().removeClass('bg-highlight');
		            					            					$('#mies6 #stopaddress').val('');
		            					            					$('#mies6 #stopaddress').removeClass('text-highlight');
		            					            					$('#mies6 #hstopaddress').removeClass('text-highlight');
		            				                					$('#mies6 #stopaddress').parent().removeClass('bg-highlight');
		            				                					$('#mies6 #hstopaddress').parent().removeClass('bg-highlight');
		            					            					$('#mies6 #startend').val('');
		            					            					$('#mies6 #startend').removeClass('text-highlight');
		            					            					$('#mies6 #hstartend').removeClass('text-highlight');
		            				                					$('#mies6 #startend').parent().removeClass('bg-highlight');
		            				                					$('#mies6 #hstartend').parent().removeClass('bg-highlight');
				            				            				$.ajax({
				            				            					url: 'uplineshow',
				            					            				data: {'id': modifiedField.id,
				            					            					   username: $('#username').val()},
				            					            				success: function(json) {
				            					            					var upline = $.parseJSON(json);
				            					            					$('#mies6 #hname').val(upline.name);
				            					            					$('#mies6 #halias').val(upline.alias);
				            					            					$('#mies6 #hstopaddress').val(upline.stopaddress);
				            					            					$('#mies6 #hstartend').val(upline.startend);
				            					            					
				            					            					var fields = modifiedField.fields;
				            					            					for(var index = 0; index < fields.length; index++) {
				            					            						var field = fields[index];
				            					            						$('#mies6 #h' + field).addClass('text-highlight');
				            					            						$('#mies6 #h' + field).parent().addClass('bg-highlight');
				            					            					}
				            					            				}
				            				            				});
				            				            				
				            				            				$.ajax({
				            				                				url: 'uplineshow',
				            				                				data: {'id': entityId,
				            				                					   username: $('#username').val()},
				            				                				success: function(json) {
				            					            					var downline = $.parseJSON(json);
				            					            					$('#mies6 #name').val(downline.name);
				            					            					$('#mies6 #alias').val(downline.alias);
				            					            					$('#mies6 #stopaddress').val(downline.stopaddress);
				            					            					$('#mies6 #startend').val(downline.startend);
				            					            					
				            					            					var fields = modifiedField.fields;
				            					            					for(var index = 0; index < fields.length; index++) {
				            					            						var field = fields[index];
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
			            					$('#mies4 .details .table table.down').remove();
			            					var table = $('<table class="down"></table>');
			            					$('#mies4 .details .table').append(table);
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
			            								+ '<a href="javascript:void(0)" rel="#mies6" class="viewhistory" title="\u4fee\u6539\u5386\u53f2"><img class="check" src="res/css/images/viewhistory.png"></a></td></tr>');
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
							            					   liganid: $('#mies4 input[name=liganid]').val()
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
			            							$('#mies6').dialog();
			            						});
			            						$('#mies6 input[name=liganid]').val($('#mies4 input[name=liganid]').val());
			            						$('#mies6 .close').unbind('click');
			            						$('#mies6 .close').click(function(){
			            							$('#mies6').hide();
			            							var liganid = $('#mies6 input[name=liganid]').val();
			            							$('.ligan tr').each(function(){
			            								if($(this).find('td[abbr=rownum] div').text() == liganid) {
			            									$(this).find('td[abbr=line] .buslinelink').click();
			            								}
			            							});
			            						});
			            						$(this).find('.viewhistory').click(function(){
			            							//
			            							$('#mies6 input[name=liganid]').val(_this.find('td[abbr="rownum"] div').text());
			            							$('#mies6 input[name=hname]').val('');
			            							$('#mies6 input[name=halias]').val('');
			            							$('#mies6 input[name=hstopaddress]').val('');
			            							$('#mies6 input[name=hstartend]').val('');
			            							$('#mies6 input[name=name]').val('');
			            							$('#mies6 input[name=alias]').val('');
			            							$('#mies6 input[name=stopaddress]').val('');
			            							$('#mies6 input[name=startend]').val('');
			            							$('#mies6 #name').val('');
        				            				$('#mies6 #name').removeClass('text-highlight');
        				            				$('#mies6 #hname').removeClass('text-highlight');
        				            				$('#mies6 #name').parent().removeClass('bg-highlight');
        				            				$('#mies6 #hname').parent().removeClass('bg-highlight');
        				            				$('#mies6 #alias').val('');
        				            				$('#mies6 #alias').removeClass('text-highlight');
        				            				$('#mies6 #halias').removeClass('text-highlight');
        				            				$('#mies6 #alias').parent().removeClass('bg-highlight');
        				            				$('#mies6 #halias').parent().removeClass('bg-highlight');
        				            				$('#mies6 #stopaddress').val('');
        				            				$('#mies6 #stopaddress').removeClass('text-highlight');
        				            				$('#mies6 #hstopaddress').removeClass('text-highlight');
        				            				$('#mies6 #stopaddress').parent().removeClass('bg-highlight');
        				            				$('#mies6 #hstopaddress').parent().removeClass('bg-highlight');
        				            				$('#mies6 #startend').val('');
        				            				$('#mies6 #startend').removeClass('text-highlight');
        				            				$('#mies6 #hstartend').removeClass('text-highlight');
        				            				$('#mies6 #startend').parent().removeClass('bg-highlight');
        				            				$('#mies6 #hstartend').parent().removeClass('bg-highlight');
			            							
			            							$('#mies6 .stophistory-grid').empty();
			            							$('#mies6 .stophistory-grid').append($('<div class="stophistory"></div>'));
			            							$('#mies6 .details').append($('<div class="stophistory"></div>'));
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
				            				            				$('#mies6 #name').val('');
				            				            				$('#mies6 #name').removeClass('text-highlight');
				            				            				$('#mies6 #hname').removeClass('text-highlight');
				            				            				$('#mies6 #name').parent().removeClass('bg-highlight');
				            				            				$('#mies6 #hname').parent().removeClass('bg-highlight');
				            				            				$('#mies6 #alias').val('');
				            				            				$('#mies6 #alias').removeClass('text-highlight');
				            				            				$('#mies6 #halias').removeClass('text-highlight');
				            				            				$('#mies6 #alias').parent().removeClass('bg-highlight');
				            				            				$('#mies6 #halias').parent().removeClass('bg-highlight');
				            				            				$('#mies6 #stopaddress').val('');
				            				            				$('#mies6 #stopaddress').removeClass('text-highlight');
				            				            				$('#mies6 #hstopaddress').removeClass('text-highlight');
				            				            				$('#mies6 #stopaddress').parent().removeClass('bg-highlight');
				            				            				$('#mies6 #hstopaddress').parent().removeClass('bg-highlight');
				            				            				$('#mies6 #startend').val('');
				            				            				$('#mies6 #startend').removeClass('text-highlight');
				            				            				$('#mies6 #hstartend').removeClass('text-highlight');
				            				            				$('#mies6 #startend').parent().removeClass('bg-highlight');
				            				            				$('#mies6 #hstartend').parent().removeClass('bg-highlight');
				            				            				$.ajax({
				            				            					url: 'downlineshow',
				            					            				data: {'id': modifiedField.id,
				            					            					   username: $('#username').val()},
				            					            				success: function(json) {
				            					            					var upline = $.parseJSON(json);
				            					            					$('#mies6 #hname').val(upline.name);
				            					            					$('#mies6 #halias').val(upline.alias);
				            					            					$('#mies6 #hstopaddress').val(upline.stopaddress);
				            					            					$('#mies6 #hstartend').val(upline.startend);
				            					            					
				            					            					var fields = modifiedField.fields;
				            					            					for(var index = 0; index < fields.length; index++) {
				            					            						var field = fields[index];
				            					            						$('#mies6 #h' + field).addClass('text-highlight');
				            					            						$('#mies6 #h' + field).parent().addClass('bg-highlight');
				            					            					}
				            					            				}
				            				            				});
				            				            				
				            				            				$.ajax({
				            				                				url: 'downlineshow',
				            				                				data: {'id': entityId,
				            				                					   username: $('#username').val()},
				            				                				success: function(json) {
				            					            					var downline = $.parseJSON(json);
				            					            					$('#mies6 #stop').val(downline.name);
				            					            					$('#mies6 #alias').val(downline.alias);
				            					            					$('#mies6 #stopaddress').val(downline.stopaddress);
				            					            					$('#mies6 #startend').val(downline.startend);
				            					            					
				            					            					var fields = modifiedField.fields;
				            					            					for(var index = 0; index < fields.length; index++) {
				            					            						var field = fields[index];
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
			            					            }
			            							});
			            						});
			            					});
			            				}
			            			});
			            			
			            			$.ajax({
				            			url: 'busticketviewshow',
				            			data: {username: $('#username').val(), 'line': linenum, status:'normal', id:-1},
				            			success: function(json) {
				            				if('FAILURE' != json) {
				            					$('#mies4 .details .tabletic table').remove();
				            					var busticket = $.parseJSON(json);
					            				var table = $('<table></table>');
					            				table.append($('<tr><td>\u8f66\u578b</td><td>\u7a7a\u8c03\u8f66\u7968\u4ef7</td><td>\u7968\u4ef7\u7c7b\u578b</td><td>\u65e0\u4eba\u552e\u7968</td><td>\u8fd0\u8425\u5355\u4f4d</td><td>\u7ebf\u8def</td></tr>'));
					            				table.append($('<tr><td><input type="hidden" name="bustype" value="' + busticket.type + '"><input type="hidden" name="id" value="' + busticket.id + '"><select name="bustype" ><option value="norm" >\u666e\u901a\u8f66</option><option value="air">\u7a7a\u8c03\u8f66</option>' 
				            							+ '</select></td><td><input name="airPrice" value="' 
				            							+ busticket.normPrice + '"></td><td><select name="pricetype"><option value="single">\u5355\u4e00</option><option value="multi">\u591a\u7ea7</option>' 
				            							+ '</select></td><td><select name="autoSale"><option value="y">\u662f</option><option value="n">\u5426</option>' 
				            							+ '</select></td><td><input name="opunit" value="' 
				            							+ busticket.opunit + '"></td><td><input disabled name="line" value="' 
				            							+ busticket.line + '"></td><td><a id="showtickethistory" rel="#mies7" href="javascript:void(0)">\u7968\u4ef7\u4fee\u6539\u5386\u53f2</a></td></tr>'));
					            				table.find('select[name=bustype]').val(busticket.type);
					            				table.find('select[name=autoSale]').val(busticket.autoSale);
					            				table.find('select[name=pricetype]').val(busticket.pricetype);
					            				$('#mies4 .details .tabletic').append(table);
					            				
					            				$('#mies4 #showtickethistory').click(function(){
					            					$('#mies7').dialog();
					            				});
					            				$('#mies7 input[name=liganid]').val($('#mies4 input[name=liganid]').val());
					            				$('#mies7 input[name=line]').val($('#mies4 input[name=line]').val());
					            				$('#mies7 .close').unbind('click');
					            				$('#mies7 .close').click(function(){
					            					$('#mies7').hide();
					            					var liganid = $('#mies7 input[name=liganid]').val();
					            					$('.ligan tr').each(function(){
					            						if($(this).find('td[abbr="rownum"] div').text() == liganid) {
					            							$(this).find('td[abbr=line] .buslinelink').click();
					            						}
					            					});
					            				});
					            				$('#mies4 #showtickethistory').click(function(){
					            					$('#mies7 .tickethistory-grid').empty();
					            					$('#mies7 .tickethistory-grid').append($('<div class="tickethistory"></div>'));
					            					var bustype = $(this).parent().parent().find('input[name=bustype]').val();
					            					var entity =  'busticket';
					            					if(bustype == 'air') {
					            						entity = 'busticketair';
					            					}
					            					$("#mies7 .tickethistory").flexigrid({
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
					            			            				$('#mies7 #type').val('');
					            			            				$('#mies7 #htype').val('');
					            			        					$('#mies7 #type').removeClass('text-highlight');
					            			        					$('#mies7 #htype').removeClass('text-highlight');
					            			        					$('#mies7 #type').parent().removeClass('bg-highlight');
					            			        					$('#mies7 #htype').parent().removeClass('bg-highlight');
					            			        					
					            			        					$('#mies7 #airprice').val('');
					            			        					$('#mies7 #hairprice').val('');
					            			        					$('#mies7 #airprice').removeClass('text-highlight');
					            			        					$('#mies7 #hairprice').removeClass('text-highlight');
					            			        					$('#mies7 #airprice').parent().removeClass('bg-highlight');
					            			        					$('#mies7 #hairprice').parent().removeClass('bg-highlight');
					            			        					
					            			        					$('#mies7 #normprice').val('');
					            			        					$('#mies7 #hnormprice').val('');
					            			        					$('#mies7 #normprice').removeClass('text-highlight');
					            			        					$('#mies7 #hnromprice').removeClass('text-highlight');
					            			        					$('#mies7 #normprice').parent().removeClass('bg-highlight');
					            			        					$('#mies7 #hnormprice').parent().removeClass('bg-highlight');
					            			        					
					            			        					$('#mies7 #pricetype').val('');
					            			        					$('#mies7 #hpricetype').val('');
					            			        					$('#mies7 #pricetype').removeClass('text-highlight');
					            			        					$('#mies7 #hpricetype').removeClass('text-highlight');
					            			        					$('#mies7 #pricetype').parent().removeClass('bg-highlight');
					            			        					$('#mies7 #hpricetype').parent().removeClass('bg-highlight');
					            			        					
					            			        					$('#mies7 #autoSale').val('');
					            			        					$('#mies7 #hautoSale').val('');
					            			        					$('#mies7 #autoSale').removeClass('text-highlight');
					            			        					$('#mies7 #hautoSale').removeClass('text-highlight');
					            			        					$('#mies7 #autoSale').parent().removeClass('bg-highlight');
					            			        					$('#mies7 #hautoSale').parent().removeClass('bg-highlight');
					            			        					
					            			        					$('#mies7 #opunit').val('');
					            			        					$('#mies7 #hopunit').val('');
					            			        					$('#mies7 #opunit').removeClass('text-highlight');
					            			        					$('#mies7 #hopunit').removeClass('text-highlight');
					            			        					$('#mies7 #opunit').parent().removeClass('bg-highlight');
					            			        					$('#mies7 #hopunit').parent().removeClass('bg-highlight');
					            			        					
					            			        					var hentity = modifiedField.entity;
					            			        					
					            			            				$.ajax({
					            			            					url: hentity + 'viewshow',
					            				            				data: {'id': modifiedField.id, status:'as-is', line: $('#mies7 input[name=line]').val(),
					            				            					   username: $('#username').val()},
					            				            				success: function(json) {
					            				            					var busticket = $.parseJSON(json);
					            				            					$('#mies7 #htype').val(busticket.type);
					            				            					if(busticket.type == 'air') {
						            				            					var airprice = busticket.airPrice;
						            				            					if(null == airprice || 'null' == airprice || 'undefined' == airprice) {
						            				            						airprice = '';
						            				            					}
						            				            					$('#mies7 #hairPrice').val(airprice);
					            				            					} else {
						            				            					var normprice = busticket.normPrice;
						            				            					if(null == normprice || 'null' == normprice || 'undefined' == normprice) {
						            				            						normprice = '';
						            				            					}
						            				            					$('#mies7 #hnormPrice').val(normprice);
					            				            					}
					            				            					$('#mies7 #hpricetype').val(busticket.pricetype);
					            				            					$('#mies7 #hautoSale').val(busticket.autoSale);
					            				            					$('#mies7 #hopunit').val(busticket.opunit);
					            				            					
					            				            					var fields = modifiedField.fields;
					            				            					for(var index = 0; index < fields.length; index++) {
					            				            						var field = fields[index];
					            				            						if(field == 'normPrice' && busticket.type == 'air') {
					            				            							field = 'airPrice';
					            				            						}
					            				            						if(field == 'airPrice' && busticket.type == 'norm') {
					            				            							field = 'normPrice';
					            				            						}
					            				            						$('#mies7 #h' + field).addClass('text-highlight');
					            				            						$('#mies7 #h' + field).parent().addClass('bg-highlight');
					            				            					}
					            				            				}
					            			            				});
					            			            				var entity = $(this).parent().parent().parent().find('td[abbr=entity] div').text();
					            			            				$.ajax({
					            			                				url: entity + 'viewshow',
					            			                				data: {'id': entityId, status:'as-is', line: $('#mies7 input[name=line]').val(),
					            			                					   username: $('#username').val()},
					            			                				success: function(json) {
					            				            					var busticket = $.parseJSON(json);
					            				            					$('#mies7 #type').val(busticket.type);
					            				            					if(busticket.type == 'air') {
						            				            					var airprice = busticket.airPrice;
						            				            					if(null == airprice || 'null' == airprice || 'undefined' == airprice) {
						            				            						airprice = '';
						            				            					}
						            				            					$('#mies7 #airPrice').val(airprice);
					            				            					} else {
					            				            						var normprice = busticket.normPrice;
					            				            						if(null == normprice || 'null' == normprice || 'undefined' == normprice) {
					            				            							normprice = '';
					            				            						}
					            				            						$('#mies7 #normPrice').val(normprice);
					            				            					}
					            				            					$('#mies7 #pricetype').val(busticket.pricetype);
					            				            					$('#mies7 #autoSale').val(busticket.autoSale);
					            				            					$('#mies7 #opunit').val(busticket.opunit);
					            				            					
					            				            					var fields = modifiedField.fields;
					            				            					for(var index = 0; index < fields.length; index++) {
					            				            						var field = fields[index];
					            				            						if(field == 'normPrice' && busticket.type == 'air') {
					            				            							field = 'airPrice';
					            				            						}
					            				            						if(field == 'airPrice' && busticket.type == 'norm') {
					            				            							field = 'normPrice';
					            				            						}
					            				            						$('#mies7 #' + field).addClass('text-highlight');
					            				            						$('#mies7 #' + field).parent().addClass('bg-highlight');
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
					            				
					            				$('.simple_overlay_line #norm').show();
					            				
					            				$('#img-btn-norm .uploadifyQueue').remove();
					            				$('#img-btn-norm #img_upload_normUploader').remove();
					            				$('.simple_overlay_line #img-btn-norm #img_upload_norm').uploadify({
						            			    'uploader'  : 'uploadify/uploadify.swf',
						            			    'script'    : 'busticketimg-uploadify',
						            			    'scriptData': {'liganid': $('#mies4 input[name=liganid]').val(), id: $('#mies4 .tabletic input[name=id]').val(),
						            			    	username: $('#username').val(), entity: "BusTicket", line: $('#mies4 .tabletic input[name=line]').val()},
						            			    'method'	: 'get',
						            			    'cancelImg' : 'uploadify/cancel.png',
						            			    'folder'    : '/uploads',
						            			    'fileDesc'  : '\u7acb\u6746\u56fe\u7247',
						            			    'fileExt'   : '*.JPEG;*.JPG',
						            			    'auto'      : true,
						            			    'multi'		: false,
						            			    'removeCompleted' : true,
						            			    'onError'   : function(event,ID,fileObj,errorObj) {
						            			    	$('.simple_overlay_line #img-btn-norm #img_upload .percentage').text(' - \u7acb\u6746\u56fe\u7247\u4e0a\u4f20\u5931\u8d25');
						            			    },
						            			    'onAllComplete' : function(event,data) {
						            			    	$('#img-btn-norm .uploadifyQueue').empty();
						            			    }
						            			  });
					            				$('.simple_overlay_line #multi #img-norm a').attr('href', 'busticketimg-show?id=' + $('#mies4 .tabletic input[name=id]').val() + '&username=' + $('#username').val());
					            				$('.simple_overlay_line #multi #img-norm img').attr('src', 'busticketimg-show?id=' + $('#mies4 .tabletic input[name=id]').val() + '&username=' + $('#username').val());
				            				}
				            				
				            			}
				            		});
				            		$.ajax({
				            			url: 'busticketairviewshow',
				            			data: {username: $('#username').val(), 'line': linenum, status:'normal', id:-1},
				            			success: function(json) {
				            				if('FAILURE' != json) {
				            					$('#mies4 .details .tableair table').remove();
				            					var busticket = $.parseJSON(json);
				            					var table = $('<table></table>');
				            					table.append($('<tr><td>\u8f66\u578b</td><td>\u7a7a\u8c03\u8f66\u7968\u4ef7</td><td>\u7968\u4ef7\u7c7b\u578b</td><td>\u65e0\u4eba\u552e\u7968</td><td>\u8fd0\u8425\u5355\u4f4d</td><td>\u7ebf\u8def</td></tr>'));
				            					table.append($('<tr><td><input type="hidden" name="bustype" value="' + busticket.type + '"><input type="hidden" name="id" value="' + busticket.id + '"><select name="bustype" ><option value="norm" >\u666e\u901a\u8f66</option><option value="air">\u7a7a\u8c03\u8f66</option>' 
				            							+ '</select></td><td><input name="airPrice" value="' 
				            							+ busticket.airPrice + '"></td><td><select name="pricetype"><option value="single">\u5355\u4e00</option><option value="multi">\u591a\u7ea7</option>' 
				            							+ '</select></td><td><select name="autoSale"><option value="y">\u662f</option><option value="n">\u5426</option>' 
				            							+ '</select></td><td><input name="opunit" value="' 
				            							+ busticket.opunit + '"></td><td><input disabled name="line" value="' 
				            							+ busticket.line + '"></td><td><a id="showairtickethistory" rel="#mies7" href="javascript:void(0)">\u7968\u4ef7\u4fee\u6539\u5386\u53f2</a></td></tr>'));
				            					table.find('select[name=bustype]').val(busticket.type);
					            				table.find('select[name=autoSale]').val(busticket.autoSale);
					            				table.find('select[name=pricetype]').val(busticket.pricetype);
					            				$('#mies4 .details .tableair').append(table);
					            				
					            				$('#mies4 #showairtickethistory').click(function(){
					            					$('#mies7').dialog();
					            				});
					            				$('#mies7 input[name=liganid]').val($('#mies4 input[name=liganid]').val());
					            				$('#mies7 input[name=line]').val($('#mies4 input[name=line]').val());
				            					$('#mies7 .close').unbind('click');
				            					$('#mies7 .close').click(function(){
				            						$('#mies7').hide();
				            						var liganid = $('#mies7 input[name=liganid]').val();
				            						$('.ligan tr').each(function(){
				            							if($(this).find('td[abbr="rownum"] div').text() == liganid) {
				            								$(this).find('td[abbr=line] .buslinelink').click();
				            							}
				            						});
				            					});
				            					
				            					$('#mies4 #showairtickethistory').click(function(){
				            						$('#mies7 .tickethistory-grid').empty();
				            						$('#mies7 .tickethistory-grid').append($('<div class="tickethistory"></div>'));
				            						
				            						var bustype = $(this).parent().parent().find('input[name=bustype]').val();
					            					var entity =  'busticket';
					            					if(bustype == 'air') {
					            						entity = 'busticketair';
					            					}
				            						
				            						$("#mies7 .tickethistory").flexigrid({
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
				            				            				$('#mies7 #type').val('');
				            				            				$('#mies7 #htype').val('');
					            			        					$('#mies7 #type').removeClass('text-highlight');
					            			        					$('#mies7 #htype').removeClass('text-highlight');
					            			        					$('#mies7 #type').parent().removeClass('bg-highlight');
					            			        					$('#mies7 #htype').parent().removeClass('bg-highlight');
					            			        					
					            			        					$('#mies7 #airprice').val('');
					            			        					$('#mies7 #hairprice').val('');
					            			        					$('#mies7 #airprice').removeClass('text-highlight');
					            			        					$('#mies7 #hairprice').removeClass('text-highlight');
					            			        					$('#mies7 #airprice').parent().removeClass('bg-highlight');
					            			        					$('#mies7 #hairprice').parent().removeClass('bg-highlight');
					            			        					
					            			        					$('#mies7 #normprice').val('');
					            			        					$('#mies7 #hnormprice').val('');
					            			        					$('#mies7 #normprice').removeClass('text-highlight');
					            			        					$('#mies7 #hnromprice').removeClass('text-highlight');
					            			        					$('#mies7 #normprice').parent().removeClass('bg-highlight');
					            			        					$('#mies7 #hnormprice').parent().removeClass('bg-highlight');
					            			        					
					            			        					$('#mies7 #pricetype').val('');
					            			        					$('#mies7 #hpricetype').val('');
					            			        					$('#mies7 #pricetype').removeClass('text-highlight');
					            			        					$('#mies7 #hpricetype').removeClass('text-highlight');
					            			        					$('#mies7 #pricetype').parent().removeClass('bg-highlight');
					            			        					$('#mies7 #hpricetype').parent().removeClass('bg-highlight');
					            			        					
					            			        					$('#mies7 #autoSale').val('');
					            			        					$('#mies7 #hautoSale').val('');
					            			        					$('#mies7 #autoSale').removeClass('text-highlight');
					            			        					$('#mies7 #hautoSale').removeClass('text-highlight');
					            			        					$('#mies7 #autoSale').parent().removeClass('bg-highlight');
					            			        					$('#mies7 #hautoSale').parent().removeClass('bg-highlight');
					            			        					
					            			        					$('#mies7 #opunit').val('');
					            			        					$('#mies7 #hopunit').val('');
					            			        					$('#mies7 #opunit').removeClass('text-highlight');
					            			        					$('#mies7 #hopunit').removeClass('text-highlight');
					            			        					$('#mies7 #opunit').parent().removeClass('bg-highlight');
					            			        					$('#mies7 #hopunit').parent().removeClass('bg-highlight');
					            			        					
					            			        					var hentity = modifiedField.entity;
				            				            				$.ajax({
				            				            					url: hentity + 'viewshow',
				            					            				data: {'id': modifiedField.id, status:'as-is', line: $('#mies7 input[name=line]').val(),
				            					            					   username: $('#username').val()},
				            					            				success: function(json) {
					            				            					var busticket = $.parseJSON(json);
					            				            					$('#mies7 #htype').val(busticket.type);
					            				            					if(busticket.type == 'air') {
						            				            					var airprice = busticket.airPrice;
						            				            					if(null == airprice || 'null' == airprice || 'undefined' == airprice) {
						            				            						airprice = '';
						            				            					}
						            				            					$('#mies7 #hairPrice').val(airprice);
					            				            					} else {
						            				            					var normprice = busticket.normPrice;
						            				            					if(null == normprice || 'null' == normprice || 'undefined' == normprice) {
						            				            						normprice = '';
						            				            					}
						            				            					$('#mies7 #hnormPrice').val(normprice);
					            				            					}
					            				            					$('#mies7 #hpricetype').val(busticket.pricetype);
					            				            					$('#mies7 #hautoSale').val(busticket.autoSale);
					            				            					$('#mies7 #hopunit').val(busticket.opunit);
					            				            					
					            				            					var fields = modifiedField.fields;
					            				            					for(var index = 0; index < fields.length; index++) {
					            				            						var field = fields[index];
					            				            						if(field == 'normPrice' && busticket.type == 'air') {
					            				            							field = 'airPrice';
					            				            						}
					            				            						if(field == 'airPrice' && busticket.type == 'norm') {
					            				            							field = 'normPrice';
					            				            						}
					            				            						$('#mies7 #h' + field).addClass('text-highlight');
					            				            						$('#mies7 #h' + field).parent().addClass('bg-highlight');
					            				            					}
					            				            				}
				            				            				});
				            				            				
				            				            				var entity = $(this).parent().parent().parent().find('td[abbr=entity] div').text();
				            				            				$.ajax({
				            				                				url: entity + 'viewshow',
				            				                				data: {'id': entityId, status:'as-is', line: $('#mies7 input[name=line]').val(),
				            				                					   username: $('#username').val()},
				            				                				success: function(json) {
					            				            					var busticket = $.parseJSON(json);
					            				            					$('#mies7 #type').val(busticket.type);
					            				            					if(busticket.type == 'air') {
						            				            					var airprice = busticket.airPrice;
						            				            					if(null == airprice || 'null' == airprice || 'undefined' == airprice) {
						            				            						airprice = '';
						            				            					}
						            				            					$('#mies7 #airPrice').val(airprice);
					            				            					} else {
						            				            					var normprice = busticket.normPrice;
						            				            					if(null == normprice || 'null' == normprice || 'undefined' == normprice) {
						            				            						normprice = '';
						            				            					}
						            				            					$('#mies7 #normPrice').val(normprice);
					            				            					}
					            				            					$('#mies7 #pricetype').val(busticket.pricetype);
					            				            					$('#mies7 #autoSale').val(busticket.autoSale);
					            				            					$('#mies7 #opunit').val(busticket.opunit);
					            				            					
					            				            					var fields = modifiedField.fields;
					            				            					for(var index = 0; index < fields.length; index++) {
					            				            						var field = fields[index];
					            				            						if(field == 'normPrice' && busticket.type == 'air') {
					            				            							field = 'airPrice';
					            				            						}
					            				            						if(field == 'airPrice' && busticket.type == 'norm') {
					            				            							field = 'normPrice';
					            				            						}
					            				            						$('#mies7 #' + field).addClass('text-highlight');
					            				            						$('#mies7 #' + field).parent().addClass('bg-highlight');
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
					            				
					            				$('#img-btn-air .uploadifyQueue').remove();
					            				$('#img-btn-air #img_upload_airUploader').remove();
					            				$('.simple_overlay_line #air').show();
					            				$('.simple_overlay_line #img-btn-air #img_upload_air').uploadify({
						            			    'uploader'  : 'uploadify/uploadify.swf',
						            			    'script'    : 'busticketairimg-uploadify',
						            			    'scriptData': {'liganid': $('#mies4 input[name=liganid]').val(), id: $('#mies4 .tableair input[name=id]').val(),
						            			    	username: $('#username').val(), entity: "BusTicketAir", line: $('#mies4 .tableair input[name=line]').val()},
						            			    'method'	: 'get',
						            			    'cancelImg' : 'uploadify/cancel.png',
						            			    'folder'    : '/uploads',
						            			    'fileDesc'  : '\u7acb\u6746\u56fe\u7247',
						            			    'fileExt'   : '*.JPEG;*.JPG',
						            			    'auto'      : true,
						            			    'multi'		: false,
						            			    'removeCompleted' : true,
						            			    'onError'   : function(event,ID,fileObj,errorObj) {
						            			    	$('.simple_overlay_line #img-btn-air #img_upload .percentage').text(' - \u7acb\u6746\u56fe\u7247\u4e0a\u4f20\u5931\u8d25');
						            			    },
						            			    'onAllComplete' : function(event,data) {
						            			    	$('#img-btn-air .uploadifyQueue').empty();
						            			    }
						            			  });
					            				
					            				$('.simple_overlay_line #multi #img-air a').attr('href', 'busticketairimg-show?id=' + $('#mies4 .tableair input[name=id]').val() + '&username=' + $('#username').val());
					            				$('.simple_overlay_line #multi #img-air img').attr('src', 'busticketairimg-show?id=' + $('#mies4 .tableair input[name=id]').val() + '&username=' + $('#username').val());
				            				}
				            			}
				            		});

			            		});
			            		
			            		div.find('a.buslinelink').click(function(){
			            			$('#mies4').dialog({width: 1050});
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
			            			$('#mies2').dialog({width:800});
			            		});
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
			            					$('#mies2').dialog();
			            				}
			            			});
			            			
			            			
			            			
			            		});
			            	});
			            }
		});
	}
//	ligangridify();
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
	function liganhistorygridify(id) {
		$(".ligan-history").flexigrid({
			url: 'liganmodifyhistory?username=' + $('#username').val() + '&entity=ligan&id=' + id,
			dataType: 'json',
			type: 'POST',
			colModel : [
			            {display: '\u65f6\u95f4', name : 'importtime', width : 160, sortable : true, align: 'left'},
			            {display: '\u5907\u6ce8', name : 'importcomments', width : 228, sortable : true, align: 'left'},
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
            			$(this).html($('<a href="javascript:void(0)" rel="#mies5">\u7acb\u6746\u8bbe\u5907\u4fee\u6539\u8be6\u60c5</a><span> - ' + modifiedField.modifier + '</span>'));
            			
            			$('#mies5 .close').unbind('click');
            			$('#mies5 .close').click(function(){
            				$('#mies5').hide();
							var liganid = id;
							$('.ligan tr').each(function(){
								if($(this).find('td[abbr="rownum"] div').text() == liganid) {
									$(this).find('td[abbr=line] .buslinelink').click();
								}
							});
							$('#mies5').dialog();
            			});
            			$(this).find('a').click(function(){
            				$.ajax({
            					url: 'liganshow',
	            				data: {'id': modifiedField.id,
	            					   username: $('#username').val()},
	            				success: function(json) {
	            					var liganPreview = $.parseJSON(json);
	            					$('#mies5 #hline').val(liganPreview.line).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies5 #harea').val(liganPreview.area).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies5 #hnumber').val(liganPreview.number).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies5 #hroad').val(liganPreview.road).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies5 #hname').val(liganPreview.stop).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies5 #hfinalstop').val(liganPreview.finalstop).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies5 #hnextstop').val(liganPreview.nextstop).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies5 #haddress').val(liganPreview.address).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies5 #hdirection').val(liganPreview.direction).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies5 #hdigtime').val(liganPreview.digdate).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies5 #htype').val(liganPreview.type).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies5 #hfinishdate').val(liganPreview.finishdate).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies5 #hcomments').val(liganPreview.comments).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies5 #himg img').attr('src', 'liganimgshow?entity=LiGan&username=' + $('#username').val() + '&id=' + modifiedField.id).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					var fields = modifiedField.fields;
	            					for(var index = 0; index < fields.length; index++) {
	            						var field = fields[index];
	            						$('#mies5 #h' + field).addClass('text-highlight').parent().addClass('bg-highlight');
	            					}
	            				}
            				});
            				
            				$.ajax({
                				url: 'liganshow',
                				data: {'id': id,
                					   username: $('#username').val()},
                				success: function(json) {
                					var liganPreview = $.parseJSON(json);
                					$('#mies5 #line').val(liganPreview.line).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies5 #area').val(liganPreview.area).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies5 #number').val(liganPreview.number).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies5 #road').val(liganPreview.road).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies5 #name').val(liganPreview.stop).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies5 #finalstop').val(liganPreview.finalstop).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies5 #nextstop').val(liganPreview.nextstop).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies5 #address').val(liganPreview.address).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies5 #direction').val(liganPreview.direction).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies5 #digtime').val(liganPreview.digdate).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies5 #finishdate').val(liganPreview.finishdate).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies5 #comments').val(liganPreview.comments).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies5 #type').val(liganPreview.type).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies5 #img img').attr('src', 'liganimgshow?entity=LiGan&username=' + $('#username').val() + '&id=' + id).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					var fields = modifiedField.fields;
                					for(var index = 0; index < fields.length; index++) {
                						var field = fields[index];
                						$('#mies5 #' + field).addClass('text-highlight').parent().addClass('bg-highlight');
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
	}
	
	$('.submit').click(function(){
		$('.ligan-grid').empty();
		$('.ligan-grid').append($('<div class="ligan"></div>'));
		ligangridify();
	});
	
});
