<%@ page contentType="text/html;charset=GBK" language="java"%>
<html>
<head>
<script type="text/javascript" src="res/js/jquery-1.7.min.js"></script>
<script type="text/javascript"
	src="res/js/jquery-ui-1.8.7.custom.min.js"></script>
<script type="text/javascript" src="res/js/jquery.tools.min.js"></script>
<script type="text/javascript" src="res/js/flexigrid.js"></script>
<script type="text/javascript" src="res/js/tingzinhsearch.js"></script>
<script type="text/javascript" src="res/js/custom.js"></script>
<script type="text/javascript" src="res/js/jquery.mouse.js"></script>
<script type="text/javascript" src="res/js/jquery.popeye-2.0.4.min.js"></script>
<script type="text/javascript" src="res/js/jquery.prettyPhoto.js"></script>
<script type="text/javascript" src="res/js/jquery.quartz.3.0.js"></script>
<script type="text/javascript" src="res/js/shortcodes.js"></script>
<script type="text/javascript" src="res/js/colorpicker.js"></script>
<script type="text/javascript" src="res/js/jquery.md5.js"></script>
<link rel="stylesheet" href="res/css/flexigrid.pack.css">

<link rel="stylesheet" href="res/css/colorpicker.css">
<link rel="stylesheet" href="res/css/hades.css">
<link rel="stylesheet" href="res/css/jquery.popeye.style.css">
<link rel="stylesheet" href="res/css/prettyPhoto.css">
<link rel="stylesheet" href="res/css/quartz.css">
<link rel="stylesheet" href="res/css/shortcodes.css">
<link rel="stylesheet" href="res/css/style.css">
<link rel="stylesheet" href="res/css/ligan.css">
<link rel="stylesheet" href="res/css/calender.css">
<link rel="stylesheet" href="res/css/ligansearch.css">
<link rel="stylesheet" href="res/css/jquery-ui-1.8.18.custom.css">
</head>
<body>


	<jsp:include page="header.jsp"></jsp:include>

<input type="hidden" name="upload" id="isupload"/>
	<div class="blurb-wrapper">


	</div>
	<div class="page-body-wrapper"></div>
	<div class="container clearfix page hasRightSidebar">
		<div>
			<div class="ligan-search-panel">
				<h4 class="custom-font heading">广告亭搜索条件</h4>
			</div>
			<div class="ligan-search-result">
				<h4 class="custom-font heading">广告亭搜索结果</h4>
			</div>
		</div>
		<div class="clear"></div>
		<div class="page-content">

			<div class="breadcrumb clearfix">
				<a style="outline-style: none;" href="http://wptitans.com/archin"
					class="home">首页</a> <a style="outline-style: none;"
					href="http://wptitans.com/archin/features/">管理设备数据信息</a> <span
					class="current">广告亭搜索</span>
			</div>
			<div class="content clearfix">
				<div class="simple_overlay" id="mies1">
				
					<!-- image details -->
					<div class="details">
						<h3>修改广告亭设备信息</h3>
						<input type="hidden" name="id" id="rownum">
						<div id="fields">
							<p><label for="area">区域</label><input type="text" id="area"></p>
							<p><label for="stopnum">站点编号</label><input type="text" id="stopnum"></p>
							<p><label for="picnumber">画面编号</label><input type="text" id="picnumber"></p>
							<p><label for="entitynum">设施编号</label><input type="text" id="entitynum"></p>
							<p><label for="nanhuitingtype">设施型号</label><input type="text" id="nanhuitingtype"></p>
							<p><label for="adop">广告客户/画面名称</label><input type="text" id="adop"></p>
							<p><label for="road">路名</label><input type="text" id="road"></p>
						</div>
						<div id="fields2">
							<p><label for="stop">站名</label><input type="text" id="stop"></p>
							<p><label for="address">地址</label><input type="text" id="address"></p>
							<p><label for="direction">车向</label><input type="text" id="direction"></p>
							<p><label for="line">线路</label><input type="text" id="line"></p>
							<p><label for="finalstop">开往方向</label><input type="text" id="finalstop"></p>
							<p><label for="nextstop">线路下一站</label><input type="text" id="nextstop"></p>
							<p>
							<label for="type">信息修改类型</label>
							<select id="type" name="type">
								<option value="线路调整">线路调整</option>
								<option value="立杆移位">立杆移位</option>
								<option value="立杆拆除">立杆拆除</option>
							</select>
							</p>
						</div>
						<div style="float:left"><label for="adstart">画面上画日期</label><input type="date" id="adstart"></div>
						<div style="float:left"><label for="adend">画面到期日期</label><input type="date" id="adend"></div>
						<div id="digtimepanel"><label for="digtime">挖掘日期</label><input type="date" id="digtime"></div>
						<div id="finishdatepanel"><label for="finishdate">完成日期</label><input type="date" id="finishdate"></div>
						<div id="lastcaredatepanel"><label for="lastcaredate">上次养护日期</label><input type="date" id="lastcaredate"></div>
						<div id="img"><img /></div>
						<div style="float:left;margin:20px;">
							<label for="comments" style="color:#000;float:left">备注</label>
							<textarea id="comments" rows="4" cols="20" style="margin-left:10px;height:220px;width:280px;resize:none;padding:2px"></textarea>
						</div>
						<div id="img-btn"><input id="img_upload" name="file_upload" type="file" /></div>
						<div id="submit">提交修改</div>
						<div class="progress"><img src="res/css/images/progress.gif" style="padding-top:10px; padding-bottom:20px"></div>
					</div>
				
				</div>
				<div class="simple_overlay" id="mies2" style="width:800px;height:300px">
				
					<!-- image details -->
					<div class="details" style="width:760px">
						<h3>修改线路信息</h3>
						<input type="hidden" name="count" id="rowcount">
						<input type="hidden" name="number" id="number">
						<table>
							<tr>
								<th style="width:50px">区域</th><th style="width:55px">站点编号</th><th style="width:90px">画面编号</th>
								<th style="width:50px">设施编号</th><th style="width:55px">设施型号</th><th style="width:90px">广告客户/画面名称</th>
								<th style="width:70px">画面上画日期</th><th style="width:90px">画面到期日期</th><th style="width:40px">路名</th>
								<th style="width:110px">站名</th><th style="width:110px">地址</th><th style="width:110px">车向</th>
								<th style="width:110px">线路</th><th style="width:110px">开往方向</th><th style="width:110px">各线路下一站</th>
								<th style="width:110px">挖坑日期</th><th style="width:110px">完成日期</th><th style="width:110px">上次养护日期</th>
							</tr>
						</table>
							<div style="color:#000;margin:15px;float:left">
								<label for="type">信息修改类型</label>
								<select id="type" name="type">
									<option value="线路调整">线路调整</option>
									<option value="立杆移位">立杆移位</option>
									<option value="立杆拆除">立杆拆除</option>
								</select>
							</div>
							<div style="float:left;margin:15px">
								<label for="comments" style="color:#000;float:left">备注</label>
								<textarea id="comments" rows="4" cols="20" style="margin:4px;height:75px;width:493px;resize:none;padding:2px"></textarea>
							</div>
							<div class="clear"></div>
						<div id="modify-ligan" >提交修改</div>
						<div class="progress" style="margin:0"><img src="res/css/images/progress.gif" style="padding-top:10px; padding-bottom:20px"></div>
					</div>
				
				</div>
				<div class="simple_overlay" id="mies3" style="width:450px;height:800px;padding:20px;">
				
					<div class="details" style="width:450px">
						<input type="hidden" name="tingziid">
						<div class="ligan-history">
						</div>
					</div>
				
				</div>
				<div class="simple_overlay_line" id="mies4">
				
					<!-- image details -->
					<div class="details">
						<input type="hidden" name="tingziid">
						<h3>浏览/修改线路信息</h3>
						<div class="table"></div>
						<div id="multi">
							<fieldset id="norm">
								<Legend style="color:#333">普通车多级票价</Legend>
								<div id="img-norm"><a target="_blank"><img class="ticimg" src="/busticketimg-show"></a></div>
								<div id="img-btn-norm"><input id="img_upload_norm" name="file_upload" type="file" /></div>
							</fieldset>
							<fieldset id="air">
								<Legend style="color:#333">空调车多级票价</Legend>
								<div id="img-air"><a target="_blank"><img class="ticimg" src=""></a></div>
								<div id="img-btn-air"><input id="img_upload_air" name="file_upload" type="file" /></div>
							</fieldset>
						</div>
						<div class="tabletic"></div>
						<div class="tableair"></div>
						<div id="submitticket" style="margin:30px 0 0 30px;">修改票价信息</div>
						<div class="progress"><img src="res/css/images/progress.gif" style="padding-top:10px; padding-bottom:20px"></div>
					</div>
				
				</div>
				<div class="simple_overlay_line" id="mies5">
				
					<!-- image details -->
					<div class="details">
						<input type="hidden" name="tingziid">
						<h3>广告亭设备信息修改详细记录</h3>
						<div id="old">
							<div id="fields">
								<p><label for="harea">区域</label><input disabled  type="text" id="harea"></p>
								<p><label for="hstopnum">站点编号</label><input disabled  type="text" id="hstopnum"></p>
								<p><label for="hpicnumber">画面编号</label><input disabled  type="text" id="hpicnumber"></p>
								<p><label for="hentitynum">设施编号</label><input disabled  type="text" id="hentitynum"></p>
								<p><label for="hnanhuitingtype">设施型号</label><input disabled  type="text" id="hnanhuitingtype"></p>
								<p><label for="hadop">广告客户/画面名称</label><input disabled  type="text" id="hadop"></p>
							</div>
							<div id="fields2">
								<p><label for="hroad">路名</label><input disabled  type="text" id="hroad"></p>
								<p><label for="hstop">站名</label><input disabled  type="text" id="hstop"></p>
								<p><label for="haddress">地址</label><input disabled  type="text" id="haddress"></p>
								<p><label for="hdirection">车向</label><input disabled  type="text" id="hdirection"></p>
								<p><label for="hline">线路</label><input disabled  type="text" id="hline"></p>
								<p><label for="hfinalstop">开往方向</label><input disabled  type="text" id="hfinalstop"></p>
								<p><label for="hnextstop">线路下一站</label><input disabled  type="text" id="hnextstop"></p>
								<p><label for="htype">信息修改类型</label><input disabled type="text" id="htype"></p>
							</div>
							<div><label for="hadstart">画面上画日期</label><input disabled type="date" id="hadstart"></div>
							<div class="clear"></div>
							<div><label for="hadend">画面到期日期</label><input disabled type="date" id="hadend"></div>
							<div class="clear"></div>
							<div id="digtimepanel"><label for="hdigtime" style="margin-left:20px">挖掘日期</label><input disabled  type="date" id="hdigtime"></div>
							<div class="clear"></div>
							<div id="finishdatepanel"><label for="hfinishdate" style="margin-left:20px">完成日期</label><input disabled  type="date" id="hfinishdate"></div>
							<div class="clear"></div>
							<div id="lastcaredatepanel"><label for="hlastcaredate">上次养护日期</label><input disabled type="date" id="hlastcaredate"></div>
							<div style="float:left;margin:0 20px;">
								<label for="hcomments" style="color:#000;float:left">备注</label>
								<textarea id="hcomments" disabled  rows="4" cols="20" style="margin-left:10px;height:120px;width:280px;resize:none;padding:2px"></textarea>
							</div>
							<div id="img"><img /></div>
						</div>
						<div id="cur">
							<div id="fields">
								<p><label for="area">区域</label><input disabled type="text" id="area"></p>
								<p><label for="stopnum">站点编号</label><input disabled type="text" id="stopnum"></p>
								<p><label for="picnumber">画面编号</label><input disabled type="text" id="picnumber"></p>
								<p><label for="entitynum">设施编号</label><input disabled type="text" id="entitynum"></p>
								<p><label for="nanhuitingtype">设施型号</label><input disabled  type="text" id="nanhuitingtype"></p>
								<p><label for="adop">广告客户/画面名称</label><input disabled type="text" id="adop"></p>
								<p><label for="raod">路名</label><input disabled type="text" id="road"></p>
						</div>
						<div id="fields2">
							<p><label for="stop">站名</label><input disabled type="text" id="stop"></p>
							<p><label for="address">地址</label><input disabled type="text" id="address"></p>
							<p><label for="direction">车向</label><input disabled type="text" id="direction"></p>
							<p><label for="line">线路</label><input disabled type="text" id="line"></p>
							<p><label for="finalstop">开往方向</label><input disabled type="text" id="finalstop"></p>
							<p><label for="nextstop">线路下一站</label><input disabled type="text" id="nextstop"></p>
							<p><label for="type">信息修改类型</label><input disabled type="text" id="type"></p>
						</div>
						<div><label for="adstart">画面上画日期</label><input disabled type="date" id="adstart"></div>
						<div class="clear"></div>
						<div><label for="adend">画面到期日期</label><input disabled type="date" id="adend"></div>
						<div class="clear"></div>
						<div id="digtimepanel"><label for="digtime" style="margin-left:20px">挖掘日期</label><input disabled  type="date" id="digtime"></div>
						<div class="clear"></div>
						<div id="finishdatepanel"><label for="finishdate" style="margin-left:20px">完成日期</label><input disabled  type="date" id="finishdate"></div>
						<div class="clear"></div>
						<div id="lastcaredatepanel"><label for="lastcaredate">上次养护日期</label><input disabled type="date" id="lastcaredate"></div>
						<div style="float:left;margin:0 20px;">
							<label for="comments" style="color:#000;float:left">备注</label>
							<textarea disabled id="comments" rows="4" cols="20" style="margin-left:10px;height:120px;width:280px;resize:none;padding:2px"></textarea>
						</div>
						<div id="img"><img /></div>
						</div>
					</div>
				
				</div>
				<div class="simple_overlay_line" id="mies6">
				
					<!-- image details -->
					<div class="details">
						<input type="hidden" name="tingziid">
						<h3>站址信息修改详细记录</h3>
						<div id="old">
							<div id="fields">
								<p><label for="hname">站名</label><input disabled  type="text" id="hname"></p>
								<p><label for="halias">副站名</label><input disabled  type="text" id="halias"></p>
								<p><label for="hstopaddress">站址</label><input disabled  type="text" id="hstopaddress"></p>
								<p><label for="hstartend">首末班时刻</label><input disabled  type="text" id="hstartend"></p>
							</div>
						</div>
						<div id="cur">
							<div id="fields">
								<p><label for="name">站名</label><input disabled  type="text" id="name"></p>
								<p><label for="alias">副站名</label><input disabled  type="text" id="alias"></p>
								<p><label for="stopaddress">站址</label><input disabled  type="text" id="stopaddress"></p>
								<p><label for="startend">首末班时刻</label><input disabled  type="text" id="startend"></p>
							</div>
						</div>
						<div class="stophistory-grid">
							<div class="stophistory"></div>
						</div>
					</div>
				
				</div>
				<div class="simple_overlay_line" id="mies7">
				
					<!-- image details -->
					<div class="details">
						<input type="hidden" name="tingziid">
						<input type="hidden" name="line">
						<h3>票价信息修改详细记录</h3>
						<div id="old">
							<div id="fields">
								<p><label for="htype">车型</label><select name="type" id="htype"><option>--</option><option value="norm">普通车</option><option value="air">空调车</option></select></p>
								<p><label for="hairPrice">空调车票价</label><input disabled  type="text" id="hairPrice"></p>
								<p><label for="hnormPrice">普通车票价</label><input disabled  type="text" id="hnormPrice"></p>
								<p><label for="hpricetype">票价类型</label><select name="pricetype" id="hpricetype"><option>--</option><option value="single">单一</option><option value="multi">多级</option></select></p>
								<p><label for="hautoSale">无人售票</label><select name="autoSale" id="hautoSale"><option>--</option><option value="y">是</option><option value="n">否</option></select></p>
								<p><label for="hopunit">运营单位</label><input disabled  type="text" id="hopunit"></p>
							</div>
						</div>
						<div id="cur">
							<div id="fields">
								<p><label for="type">车型</label><select name="type" id="type"><option>--</option><option value="norm">普通车</option><option value="air">空调车</option></select></p>
								<p><label for="airPrice">空调车票价</label><input disabled  type="text" id="airPrice"></p>
								<p><label for="normPrice">普通车票价</label><input disabled  type="text" id="normPrice"></p>
								<p><label for="pricetype">票价类型</label><select name="pricetype" id="pricetype"><option>--</option><option value="single">单一</option><option value="multi">多级</option></select></p>
								<p><label for="autoSale">无人售票</label><select name="autoSale" id="autoSale"><option>--</option><option value="y">是</option><option value="n">否</option></select></p>
								<p><label for="opunit">运营单位</label><input disabled  type="text" id="opunit"></p>
							</div>
						</div>
						<div class="tickethistory-grid">
							<div class="tickethistory"></div>
						</div>
					</div>
				
				</div>
				<div class="ligan-search-grid">
					<fieldset  class="nanhuiting-search-fieldset">
						<legend>输入广告亭搜索条件</legend>
						<div class="input" id="line"><label for="line-criteria">线路</label><input id="line-criteria" type="text" name="line" class="criteria"></div>
						<div class="input" id="area"><label for="line-area">区域</label><input id="line-area" type="text" name="area" class="area"></div>
						<div class="input" id="picnumber"><label for="line-number">画面编号</label><input id="line-number" type="text" name="picnumber" class="number"></div>
						<div class="input" id="stopnum"><label for="line-stopnum">站点编号</label><input id="line-stopnum" type="text" name="stopnum" class="stopnum"></div>
						<div class="input" id="entitynum"><label for="line-entitynum">设施编号</label><input id="line-entitynum" type="text" name="entitynum" class="entitynum"></div>
						<div class="input" id="nanhuitingtype"><label for="line-nanhuitingtype">设施型号</label>
							<select id="line-nanhuitingtype">
								<option value="">--</option>
								<option value="7">两幅亭7</option>
								<option value="2">三幅亭2</option>
								<option value="3">三幅亭3</option>
								<option value="4">三幅亭4</option>
								<option value="5">五幅亭5</option>
								<option value="6">五幅亭6</option>
							</select>
						</div>
						<div class="input" id="adop"><label for="line-adop">广告客户/画面名称</label><input id="line-adop" type="text" name="adop" class="adop"></div>
						<div class="input" id="adstart"><label for="line-adstart">画面上画日期</label><input id="line-adstart" type="text" name="adstart" class="adstart"></div>
						<div class="input" id="adend"><label for="line-adend">画面到期日期</label><input id="line-adend" type="text" name="adend" class="adend"></div>
						<div class="input" id="road"><label for="line-road">路名</label><input id="line-road" type="text" name="road" class="road"></div>
						<div class="input" id="stop"><label for="line-stop">站名</label><input id="line-stop" type="text" name="stop" class="stop"></div>
						<div class="input" id="address"><label for="line-address">地址</label><input id="line-address" type="text" name="address" class="address"></div>
						<div class="input" id="direction"><label for="line-direction">车向</label><input id="line-direction" type="text" name="direction" class="direction"></div>
						<div class="input" id="digdate"><label for="line-digdate">挖掘日期</label><input id="line-digdate" type="date" name="digdate" class="digdate"></div>
						<div class="input" id="finalstop"><label for="line-finalstop">开往方向</label><input id="line-finalstop" type="text" name="finalstop" class="finalstop"></div>
						<div class="input" id="nextstop"><label for="line-nextstop">线路下一站</label><input id="line-nextstop" type="text" name="nextstop" class="nextstop"></div>
						<div class="input" id="finishdate"><label for="line-finishdate">完成日期</label><input id="line-finishdate" type="date" name="finishdate" class="finishdate"></div>
						<div class="input" id="lastcaredate"><label for="line-lastcaredate">上次养护日期</label><input id="line-lastcaredate" type="date" name="lastcaredate" class="lastcaredate"></div>
						<div class="input" id="comments"><label for="line-comments">备注</label><input id="line-comments" type="text" name="comments" class="comments"></div>
						<div class="submit">查询广告亭</div>
					</fieldset>
				</div>
				<div class="ligan-grid">
					<div class="ligan"></div>
				</div>
			</div>
		</div>
	</div>
	<div id="footer" class="clearfix footer-column4">
		<div id="footer-menu" class="clearfix">
			<div class="container">
				<ul id="menu-footer-menu" class="menu">
					<li id="menu-item-161"
						class="menu-item menu-item-type-post_type menu-item-object-page menu-item-161"><a
						style="border-left: medium none; outline-style: none;"
						href="javascript:void(0)">上海市中停车设备有限公司信息管理系统</a></li>
					
				</ul>
				<p class="footer-text"></p>
			</div>
		</div>
	</div>

	<div id='errmsgform'>
		<img title="关闭错误提示" id="errmsgclose" src="res/css/images/close.png">
		<div class="clear"></div>
		<span id="errmsgtext">请检查Excel文件表头<br>正确表头,前11列分别是:<br>[区域,
			铭牌号, 路名, 站名, 地址, 车向, 路线, 挖掘日期, 开往方向, 线路下一站, 完成日期]
		</span>
	</div>
</body>
</html>