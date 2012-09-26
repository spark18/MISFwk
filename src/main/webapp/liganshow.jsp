<%@ page contentType="text/html;charset=GBK" language="java"%>
<html>
<head>
<script type="text/javascript" src="res/js/jquery-1.7.min.js"></script>
<script type="text/javascript"
	src="res/js/jquery-ui-1.8.7.custom.min.js"></script>
<script type="text/javascript" src="res/js/jquery.tools.min.js"></script>
<script type="text/javascript" src="res/js/flexigrid.js"></script>
<script type="text/javascript" src="res/js/liganshow.js"></script>
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
			<div class="title">
				<h4 class="custom-font heading">设备信息列表</h4>
			</div>
			<div class="title-history">
				<h4 class="custom-font heading">数据修改历史记录</h4>
			</div>
		</div>
		<div class="clear"></div>
		<div class="page-content">

			<div class="breadcrumb clearfix">
				<a style="outline-style: none;" href="http://wptitans.com/archin"
					class="home">首页</a> <a style="outline-style: none;"
					href="http://wptitans.com/archin/features/">管理设备数据信息</a> <span
					class="current">立杆设备信息导入</span>
			</div>
			<div class="content clearfix">
				<div class="simple_overlay" id="mies1">
				
					<!-- image details -->
					<div class="details">
						<h3>修改立杆设备信息</h3>
						<input type="hidden" name="id" id="rownum">
						<div id="fields">
							<p><label for="line">线路</label><input type="text" id="line"></p>
							<p><label for="area">区域</label><input type="text" id="area"></p>
							<p><label for="number">铭牌号</label><input type="text" id="number"></p>
							<p><label for="raod">路名</label><input type="text" id="road"></p>
							<p><label for="stop">站名</label><input type="text" id="stop"></p>
							
						</div>
						<div id="fields2">
							<p><label for="finalstop">开往方向</label><input type="text" id="finalstop"></p>
							<p><label for="nextstop">线路下一站</label><input type="text" id="nextstop"></p>
							<p><label for="address">地址</label><input type="text" id="address"></p>
							<p><label for="direction">车向</label><input type="text" id="direction"></p>
							<p>
							<label for="type">信息修改类型</label>
							<select id="type" name="type">
								<option value="线路调整">线路调整</option>
								<option value="立杆移位">立杆移位</option>
								<option value="立杆拆除">立杆拆除</option>
							</select>
							</p>
						</div>
						<div id="digtimepanel"><label for="digtime">挖掘日期</label><input type="date" id="digtime"></div>
						<div id="finishdatepanel"><label for="finishdate">完成日期</label><input type="date" id="finishdate"></div>
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
					<div class="details" style="width:760px;min-width:200">
						<h3>修改铭牌信息</h3>
						<input type="hidden" name="count" id="rowcount">
						<input type="hidden" name="number" id="number">
						<table>
							<tr>
								<th style="width:50px">线路</th><th style="width:55px">区域</th><th style="width:90px">路名</th>
								<th style="width:70px">站名</th><th style="width:90px">地址</th><th style="width:40px">车向</th>
								<th style="width:110px">开往方向</th><th style="width:110px">下一站</th><th style="width:110px">完成时间</th>
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
				<div class="simple_overlay_line" id="mies3">
				
					<!-- image details -->
					<div class="details">
						<input type="hidden" name="liganid">
						<input type="hidden" name="line">
						<h3>浏览/修改线路信息</h3>
						<div class="table"></div>
						<div id="multi">
							<fieldset id="norm">
								<Legend style="color:#333">普通车多级票价</Legend>
								<div id="img-norm"><a target="_blank"><img src="/busticketimg-show"></a></div>
								<div id="img-btn-norm"><input id="img_upload_norm" name="file_upload" type="file" /></div>
							</fieldset>
							<fieldset id="air">
								<Legend style="color:#333">空调车多级票价</Legend>
								<div id="img-air"><a target="_blank"><img src=""></a></div>
								<div id="img-btn-air"><input id="img_upload_air" name="file_upload" type="file" /></div>
							</fieldset>
						</div>
						<div class="tabletic"></div>
						<div class="tableair"></div>
						<div id="submitticket" style="margin:30px 0 0 30px;">修改票价信息</div>
						<div class="progress"><img src="res/css/images/progress.gif" style="padding-top:10px; padding-bottom:20px"></div>
					</div>
				
				</div>
				<div class="simple_overlay_line" id="mies4">
				
					<!-- image details -->
					<div class="details">
						<input type="hidden" name="liganid">
						<h3>立杆设备信息修改详细记录</h3>
						<div id="old">
							<div id="fields">
								<p><label for="hline">线路</label><input disabled  type="text" id="hline"></p>
								<p><label for="harea">区域</label><input disabled  type="text" id="harea"></p>
								<p><label for="hnumber">铭牌号</label><input disabled  type="text" id="hnumber"></p>
								<p><label for="hroad">路名</label><input disabled  type="text" id="hroad"></p>
								<p><label for="hname">站名</label><input disabled  type="text" id="hname"></p>
								
							</div>
							<div id="fields2">
								<p><label for="hfinalstop">开往方向</label><input disabled  type="text" id="hfinalstop"></p>
								<p><label for="hnextstop">线路下一站</label><input disabled  type="text" id="hnextstop"></p>
								<p><label for="haddress">地址</label><input disabled  type="text" id="haddress"></p>
								<p><label for="hdirection">车向</label><input disabled  type="text" id="hdirection"></p>
								<p>
								<label for="htype">信息修改类型</label>
								<select disabled  id="htype" name="type">
									<option value="">--</option>
									<option value="线路调整">线路调整</option>
									<option value="立杆移位">立杆移位</option>
									<option value="立杆拆除">立杆拆除</option>
								</select>
								</p>
							</div>
							<div id="digtimepanel"><label for="hdigtime" style="margin-left:20px">挖掘日期</label><input disabled  type="date" id="hdigtime"></div>
							<div class="clear"></div>
							<div id="finishdatepanel"><label for="hfinishdate" style="margin-left:20px">完成日期</label><input disabled  type="date" id="hfinishdate"></div>
							<div style="float:left;margin:0 20px;">
								<label for="hcomments" style="color:#000;float:left">备注</label>
								<textarea id="hcomments" disabled  rows="4" cols="20" style="margin-left:10px;height:120px;width:280px;resize:none;padding:2px"></textarea>
							</div>
							<div id="himg"><img /></div>
						</div>
						<div id="cur">
							<div id="fields">
							<p><label for="line">线路</label><input disabled  type="text" id="line"></p>
							<p><label for="area">区域</label><input disabled  type="text" id="area"></p>
							<p><label for="number">铭牌号</label><input disabled  type="text" id="number"></p>
							<p><label for="road">路名</label><input disabled  type="text" id="road"></p>
							<p><label for="name">站名</label><input disabled  type="text" id="name"></p>
							
						</div>
						<div id="fields2">
							<p><label for="finalstop">开往方向</label><input disabled  type="text" id="finalstop"></p>
							<p><label for="nextstop">线路下一站</label><input disabled  type="text" id="nextstop"></p>
							<p><label for="address">地址</label><input disabled  type="text" id="address"></p>
							<p><label for="direction">车向</label><input disabled  type="text" id="direction"></p>
							<p>
							<label for="type">信息修改类型</label>
							<select disabled  id="type" name="type">
								<option value="">--</option>
								<option value="线路调整">线路调整</option>
								<option value="立杆移位">立杆移位</option>
								<option value="立杆拆除">立杆拆除</option>
							</select>
							</p>
						</div>
						<div id="digtimepanel"><label for="digtime" style="margin-left:20px">挖掘日期</label><input disabled  type="date" id="digtime"></div>
						<div class="clear"></div>
						<div id="finishdatepanel"><label for="finishdate" style="margin-left:20px">完成日期</label><input disabled  type="date" id="finishdate"></div>
						<div style="float:left;margin:0 20px;">
							<label for="comments" style="color:#000;float:left">备注</label>
							<textarea disabled id="comments" rows="4" cols="20" style="margin-left:10px;height:120px;width:280px;resize:none;padding:2px"></textarea>
						</div>
						<div id="img"><img /></div>
						</div>
					</div>
				
				</div>
				<div class="simple_overlay_line" id="mies5">
				
					<!-- image details -->
					<div class="details">
						<input type="hidden" name="liganid">
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
				<div class="simple_overlay_line" id="mies6">
				
					<!-- image details -->
					<div class="details">
						<input type="hidden" name="liganid">
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
								<p><label for="himg">票价图片</label><div id="himg"><img /></div></p>
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
								<p><label for="img">票价图片</label><div id="img"><img /></div></p>
							</div>
						</div>
						<div class="tickethistory-grid">
							<div class="tickethistory"></div>
						</div>
					</div>
				
				</div>
				<div class="ligan-grid">
					<div class="ligan"></div>
				</div>
				<div class="ligan-history-grid">
					<div class="ligan-history"></div>
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