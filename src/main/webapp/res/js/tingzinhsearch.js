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
	$('#line-lastcaredate:date').dateinput({ 
		lang: 'ch', 
		format: 'mmmm dd,yyyy',
		offset: [30, 0]
	});
	$('#line-adstart:date').dateinput({ 
		lang: 'ch', 
		format: 'mmmm dd,yyyy',
		offset: [30, 0]
	});
	$('#line-adend:date').dateinput({ 
		lang: 'ch', 
		format: 'mmmm dd,yyyy',
		offset: [30, 0]
	});
	
	$('.simple_overlay #submit').click(function() {
		$.ajax({
			url: 'tingzinhmodify',
			data: {id: $('.simple_overlay #rownum').val(),
				   username: $('#username').val(),
				   line: $('#mies1 #line').val(),
				   area: $('#mies1 #area').val(),
				   picnumber: $('#mies1 #picnumber').val(),
				   stopnum: $('#mies1 #stopnum').val(),
				   entitynum: $('#mies1 #entitynum').val(),
				   road: $('#mies1 #road').val(),
				   stop: $('#mies1 #stop').val(),
				   finalstop: $('#mies1 #finalstop').val(),
				   nextstop: $('#mies1 #nextstop').val(),
				   address: $('#mies1 #address').val(),
				   direction: $('#mies1 #direction').val(),
				   digtime: $('#mies1 #digtime').val(),
				   finishdate: $('#mies1 #finishdate').val(),
				   lastCareDate: $('#mies1 #lastcaredate').val(),
				   nanhuitingType: $('#mies1 #nanhuitingtype').val(),
				   type: $('#mies1 #type').val(),
				   comments: $('#mies1 #comments').val(),
				   adop: $('#mies1 #adop').val(),
				   adstart: $('#mies1 #adstart').val(),
				   adend: $('#mies1 #adend').val()
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
			url: 'tingzinhsearch?username=' + encodeURIComponent($('#username').val())
				+ '&line=' + encodeURIComponent($('input#line-criteria').val()) + '&area=' + encodeURIComponent($('input#line-area').val())
				+ '&picnumber=' + encodeURIComponent($('input#line-number').val())
				+ '&road=' + encodeURIComponent($('input#line-road').val()) + '&stop=' + encodeURIComponent($('input#line-stop').val())
				+ '&address=' + encodeURIComponent($('input#line-address').val())
				+ '&direction=' + encodeURIComponent($('input#line-direction').val()) + '&digdate=' + encodeURIComponent($('input#line-digdate').val())
				+ '&finalstop=' + encodeURIComponent($('input#line-finalstop').val())
				+ '&nextstop=' + encodeURIComponent($('input#line-nextstop').val()) + '&finishdate=' + encodeURIComponent($('input#line-finishdate').val())
				+ '&comments=' + encodeURIComponent($('input#line-comments').val())
				+ '&adop=' + encodeURIComponent($('input#line-adop').val())
				+ '&adstart=' + encodeURIComponent($('input#line-adstart').val())
				+ '&adend=' + encodeURIComponent($('input#line-adend').val())
				+ '&lastcaredate=' + encodeURIComponent($('input#line-lastcaredate').val())
				+ '&stopnum=' + encodeURIComponent($('input#line-stopnum').val())
				+ '&entitynum=' + encodeURIComponent($('input#line-entitynum').val())
				+ '&nanhuitingtype=' + encodeURIComponent($('select#line-nanhuitingtype').val()),
			dataType: 'json',
			colModel : [
			            {display: '\u7ebf\u8def', name : 'line', width : 40, sortable : true, align: 'left'},
			            {display: '\u4fee\u6539\u5386\u53f2', name : 'history', width : 56, sortable : true, align: 'left'},
			            {display: '\u533a\u57df', name : 'area', width : 40, sortable : true, align: 'left'},
			            {display: '\u7ad9\u70b9\u7f16\u53f7', name : 'stopnum', width : 60, sortable : true, align: 'left'},
			            {display: '\u753b\u9762\u7f16\u53f7', name : 'picnumber', width : 60, sortable : true, align: 'left'},
			            {display: '\u8bbe\u65bd\u7f16\u53f7', name : 'entitynum', width : 60, sortable : true, align: 'left'},
			            {display: '\u8bbe\u65bd\u578b\u53f7', name : 'nanhuitingtype', width : 40, sortable : true, align: 'left'},
			            {display: '\u5e7f\u544a\u5ba2\u6237/\u753b\u9762\u540d\u79f0', name : 'adop', width : 147, sortable : true, align: 'left'},
			            {display: '\u753b\u9762\u4e0a\u753b\u65e5\u671f', name : 'adstart', width : 147, sortable : true, align: 'left'},
			            {display: '\u753b\u9762\u5230\u671f\u65e5\u671f', name : 'adend', width : 147, sortable : true, align: 'left'},
			            {display: '\u8def\u540d', name : 'road', width : 50, sortable : true, align: 'left'},
			            {display: '\u7ad9\u540d', name : 'stop', width : 30, sortable : true, align: 'left'},
			            {display: '\u5730\u5740', name : 'address', width : 80, sortable : true, align: 'left'},
			            {display: '\u8f66\u5411', name : 'direction', width : 40, sortable : true, align: 'left'},
			            {display: '\u5f00\u5f80\u65b9\u5411', name : 'finalstop', width : 80, sortable : true, align: 'left'},
			            {display: '\u5404\u7ebf\u8def\u4e0b\u4e00\u7ad9', name : 'nextstop', width : 110, sortable : true, align: 'left'},
			            {display: '\u6316\u5751\u65e5\u671f', name : 'digtime', width : 80, sortable : true, align: 'left'},
			            {display: '\u5b8c\u6210\u65e5\u671f', name : 'finishdate', width : 80, sortable : true, align: 'left'},
			            {display: '\u4e0a\u6b21\u517b\u62a4\u65e5\u671f', name : 'lastcaredate', width : 90, sortable : true, align: 'left'},
			            {display: '\u5907\u6ce8', name : 'comments', width : 147, sortable : true, align: 'left'},
			            {display: 'rownum', name : 'rownum', width : 244, sortable : true, align: 'left', hide: true},
			            {display: 'modify', name : 'modify', width : 56, sortable : true, hide: true, align: 'left'}
			            ],
			buttons:  [{name: '<a href="./downloadtingzinanhui?username=' + $('#username').val() + '&line=' + encodeURIComponent($('input#line-criteria').val()) + '&area=' + encodeURIComponent($('input#line-area').val())
				+ '&picnumber=' + encodeURIComponent($('input#line-number').val())
				+ '&road=' + encodeURIComponent($('input#line-road').val()) + '&stop=' + encodeURIComponent($('input#line-stop').val())
				+ '&address=' + encodeURIComponent($('input#line-address').val())
				+ '&direction=' + encodeURIComponent($('input#line-direction').val()) + '&digdate=' + encodeURIComponent($('input#line-digdate').val())
				+ '&finalstop=' + encodeURIComponent($('input#line-finalstop').val())
				+ '&nextstop=' + encodeURIComponent($('input#line-nextstop').val()) + '&finishdate=' + encodeURIComponent($('input#line-finishdate').val())
				+ '&comments=' + encodeURIComponent($('input#line-comments').val())
				+ '&adop=' + encodeURIComponent($('input#line-adop').val())
				+ '&adstart=' + encodeURIComponent($('input#line-adstart').val())
				+ '&adend=' + encodeURIComponent($('input#line-adend').val())
				+ '&lastcaredate=' + encodeURIComponent($('input#line-lastcaredate').val())
				+ '&stopnum=' + encodeURIComponent($('input#line-stopnum').val())
				+ '&entitynum=' + encodeURIComponent($('input#line-entitynum').val())
				+ '&nanhuitingtype=' + encodeURIComponent($('select#line-nanhuitingtype').val()) + '">\u4e0b\u8f7d\u5e7f\u544a\u4ead</a>'}],
			            usepager: true,
			            title: '\u7acb\u6746\u4fe1\u606f\u8868',
			            useRp: true,
			            rp: 50,
			            showTableToggleBtn: true,
			            width: 970,
			            height: 600,
			            onSuccess: function() {
						$('.ligan-search-result h4').text('\u5e7f\u544a\u4ead\u641c\u7d22\u7ed3\u679c -- \u8bbe\u5907\u603b\u8ba1 : ' + arguments.callee.caller.caller.arguments[0].numberCount);
			            	$.ajax({
			            		url: 'tingzinhcount?entity=TingZiNanHui&username=' + $('#username').val()+ '&line=' + encodeURIComponent($('input#line-criteria').val()) + '&area=' + encodeURIComponent($('input#line-area').val())
			    				+ '&picnumber=' + encodeURIComponent($('input#line-number').val())
			    				+ '&road=' + encodeURIComponent($('input#line-road').val()) + '&stop=' + encodeURIComponent($('input#line-stop').val())
			    				+ '&address=' + encodeURIComponent($('input#line-address').val())
			    				+ '&direction=' + encodeURIComponent($('input#line-direction').val()) + '&digdate=' + encodeURIComponent($('input#line-digdate').val())
			    				+ '&finalstop=' + encodeURIComponent($('input#line-finalstop').val())
			    				+ '&nextstop=' + encodeURIComponent($('input#line-nextstop').val()) + '&finishdate=' + encodeURIComponent($('input#line-finishdate').val())
			    				+ '&comments=' + encodeURIComponent($('input#line-comments').val())
			    				+ '&adop=' + encodeURIComponent($('input#line-adop').val())
			    				+ '&adstart=' + encodeURIComponent($('input#line-adstart').val())
			    				+ '&adend=' + encodeURIComponent($('input#line-adend').val())
			    				+ '&lastcaredate=' + encodeURIComponent($('input#line-lastcaredate').val())
			    				+ '&stopnum=' + encodeURIComponent($('input#line-stopnum').val())
			    				+ '&entitynum=' + encodeURIComponent($('input#line-entitynum').val())
			    				+ '&nanhuitingtype=' + encodeURIComponent($('select#line-nanhuitingtype').val()),
			            		success: function(count) {
			            			var text = $('.ligan-grid .ftitle').text();
			            			$('.ligan-grid .ftitle').text(text + "  \u8bbe\u5907\u603b\u6570: " + count);
			            		}
			            	});
			            	$('.ligan tr').each(function(){
			            		var _this = $(this);
			            		
			            		var modify = _this.find('td[abbr="modify"] div').text();
			            		if(modify.toLowerCase() == 'true') {
			            			_this.find('td').css('background', "#F0C36D")
			            		}

			            		var historyDiv = _this.find('td[abbr="history"] div');
			            		historyDiv.empty().append($("<a href='javascript:void(0)' rel='#mies3'>\u67e5\u770b</a>"));
			            		historyDiv.find('a').click(function(){
			            			$('#mies3').dialog({width:680, height:800});
			            			$('#mies3 .details').empty().append($('<div class="ligan-history"></div>'));
			            			liganhistorygridify(_this.find('td[abbr="rownum"] div').text());
			            		});
			            		
			            		var div = _this.find('td[abbr="line"] div');
			            		var linenum = div.text();
			            		div.html($('<a href="javascript:void(0)" rel="#mies1">' + linenum + '</a>'));

			            		div.find('a[rel=#mies1]').click(function(){
			            			$('.simple_overlay #rownum').val(_this.find('td[abbr="rownum"] div').text());
			            			$.ajax({
			            				url: 'tingzinhshow',
			            				data: {id: $('.simple_overlay #rownum').val(),
			            					   username: $('#username').val()},
			            				success: function(json) {
			            					$('#mies1').dialog({width:680, close:function() {
			            						$('.nanhuiting-search-fieldset .submit').click();
			            					}});
			            					var liganPreview = $.parseJSON(json);
			            					$('#mies1 #line').val(liganPreview.line);
			            					$('#mies1 #area').val(liganPreview.area);
			            					$('#mies1 #stopnum').val(liganPreview.stopnum);
			            					$('#mies1 #entitynum').val(liganPreview.entitynum);
			            					$('#mies1 #nanhuitingtype').val(liganPreview.nanhuitingType);
			            					$('#mies1 #picnumber').val(liganPreview.picnumber);
			            					$('#mies1 #adop').val(liganPreview.adop);
			            					$('#mies1 #adstart').val(liganPreview.adstart);
			            					$('#mies1 #adend').val(liganPreview.adend);
			            					$('#mies1 #lastcaredate').val(liganPreview.lastCareDate);
			            					$('#mies1 #road').val(liganPreview.road);
			            					$('#mies1 #stop').val(liganPreview.stop);
			            					$('#mies1 #finalstop').val(liganPreview.finalstop);
			            					$('#mies1 #nextstop').val(liganPreview.nextstop);
			            					$('#mies1 #address').val(liganPreview.address);
			            					$('#mies1 #direction').val(liganPreview.direction);
			            					$('#mies1 #digtime').val(liganPreview.digtime);
			            					$('#mies1 #finishdate').val(liganPreview.finishdate);
			            					$('#mies1 #comments').val(liganPreview.comments);
			            					$('#mies1 #img img').attr('src', 'tingzinhimgshow?entity=TingZiNanHui&username=' + $('#username').val() + '&id=' + $('.simple_overlay #rownum').val());
			            				}
			            			});
			            			
			            			$('#img-btn object').remove();
			            			$('#img-btn #img_uploadQueue').remove();
			            			$('#img_upload').uploadify({
			            			    'uploader'  : 'uploadify/uploadify.swf',
			            			    'script'    : 'tingzinhimguploadify',
			            			    'scriptData': {'id': $('.simple_overlay #rownum').val(),
			            			    	username: $('#username').val(), entity: "TingZiNanHui"},
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
			            		$("#lastcaredate:date").dateinput({ 
			            			lang: 'ch', 
			            			format: 'mmmm dd,yyyy',
			            			offset: [30, 0]
			            		});
			            		$("#adstart:date").dateinput({ 
			            			lang: 'ch', 
			            			format: 'mmmm dd,yyyy',
			            			offset: [30, 0]
			            		});
			            		$("#adend:date").dateinput({ 
			            			lang: 'ch', 
			            			format: 'mmmm dd,yyyy',
			            			offset: [30, 0]
			            		});
			            		var numdiv = _this.find('td[abbr="number"] div');
			            		var num = numdiv.text();
			            		numdiv.html($('<a href="javascript:void(0)" rel="#mies2">' + num + '</a>'));
			            		numdiv.find('a').overlay();
			            		numdiv.find('a').click(function(){
			            			$.ajax({
			            				url: 'tingzinhviewlist',
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
			$.ajax({url: 'tingzinhmodify',
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
			url: 'tingzinhmodifyhistory?username=' + $('#username').val() + '&entity=tingzinanhui&id=' + id,
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
            	$('#mies3 input[name=tingziid]').val(id);
            	$('.ligan-history tr td[abbr=importcomments] div').each(function(){
            		var comments = $(this).text();
            		try {
            			var modifiedField = $.parseJSON(comments);
            			$(this).html($('<a href="javascript:void(0)" rel="#mies5">\u7acb\u6746\u8bbe\u5907\u4fee\u6539\u8be6\u60c5</a><span> - ' + modifiedField.modifier + '</span>'));
            			
            			$('#mies5 .close').unbind('click');
            			$(this).find('a').click(function(){
            				$('#mies5').dialog({width:1000});
            				$.ajax({
            					url: 'tingzinhshow',
	            				data: {'id': modifiedField.id,
	            					   username: $('#username').val()},
	            				success: function(json) {
	            					var liganPreview = $.parseJSON(json);
	            					$('#mies5 #hline').val(liganPreview.line).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies5 #harea').val(liganPreview.area).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies5 #hstopnum').val(liganPreview.stopnum).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies5 #hentitynum').val(liganPreview.entitynum).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies5 #hnanhuitingtype').val(liganPreview.nanhuitingType).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies5 #hpicnumber').val(liganPreview.picnumber).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies5 #hroad').val(liganPreview.road).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies5 #hstop').val(liganPreview.stop).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies5 #hadop').val(liganPreview.adop).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies5 #hfinalstop').val(liganPreview.finalstop).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies5 #hnextstop').val(liganPreview.nextstop).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies5 #haddress').val(liganPreview.address).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies5 #hdirection').val(liganPreview.direction).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies5 #hadstart').val(liganPreview.adstart).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies5 #hadend').val(liganPreview.adend).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies5 #hdigtime').val(liganPreview.digtime).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies5 #htype').val(liganPreview.type).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies5 #hfinishdate').val(liganPreview.finishdate).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies5 #hlastcaredate').val(liganPreview.lastCareDate).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies5 #hcomments').val(liganPreview.comments).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					$('#mies5 #himg img').attr('src', 'tingzinhimgshow?entity=TingZiNanHui&username=' + $('#username').val() + '&id=' + modifiedField.id).removeClass('text-highlight').parent().removeClass('bg-highlight');
	            					var fields = modifiedField.fields;
	            					for(var index = 0; index < fields.length; index++) {
	            						var field = fields[index];
	            						$('#mies5 #h' + field).addClass('text-highlight').parent().addClass('bg-highlight');
	            					}
	            				}
            				});
            				
            				$.ajax({
                				url: 'tingzinhshow',
                				data: {'id': id,
                					   username: $('#username').val()},
                				success: function(json) {
                					var liganPreview = $.parseJSON(json);
                					$('#mies5 #line').val(liganPreview.line).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies5 #area').val(liganPreview.area).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies5 #stopnum').val(liganPreview.stopnum).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies5 #entitynum').val(liganPreview.entitynum).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies5 #nanhuitingtype').val(liganPreview.nanhuitingType).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies5 #picnumber').val(liganPreview.picnumber).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies5 #road').val(liganPreview.road).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies5 #stop').val(liganPreview.stop).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies5 #adop').val(liganPreview.adop).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies5 #adstart').val(liganPreview.adstart).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies5 #adend').val(liganPreview.adend).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies5 #finalstop').val(liganPreview.finalstop).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies5 #nextstop').val(liganPreview.nextstop).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies5 #address').val(liganPreview.address).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies5 #direction').val(liganPreview.direction).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies5 #digtime').val(liganPreview.digtime).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies5 #finishdate').val(liganPreview.finishdate).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies5 #lastcaredate').val(liganPreview.lastCareDate).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies5 #comments').val(liganPreview.comments).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies5 #type').val(liganPreview.type).removeClass('text-highlight').parent().removeClass('bg-highlight');
                					$('#mies5 #img img').attr('src', 'tingzinhimgshow?entity=TingZiNanHui&username=' + $('#username').val() + '&id=' + id).removeClass('text-highlight').parent().removeClass('bg-highlight');
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
