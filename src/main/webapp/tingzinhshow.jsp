<%@ page contentType="text/html;charset=GBK" language="java"%>
<html>
<head>
<script type="text/javascript" src="res/js/jquery-1.7.min.js"></script>
<script type="text/javascript"
	src="res/js/jquery-ui-1.8.7.custom.min.js"></script>
<script type="text/javascript" src="res/js/jquery.tools.min.js"></script>
<script type="text/javascript" src="res/js/flexigrid.js"></script>
<script type="text/javascript" src="res/js/tingzinhshow.js"></script>
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
					class="current">广告亭设备信息导入</span>
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
							<p><label for="nanhuitingtype">设施型号</label>
								<select id="nanhuitingtype">
									<option value="">--</option>
									<option value="两幅亭7">两幅亭7</option>
									<option value="三幅亭2">三幅亭2</option>
									<option value="三幅亭3">三幅亭3</option>
									<option value="三幅亭4">三幅亭4</option>
									<option value="五幅亭5">五幅亭5</option>
									<option value="五幅亭6">五幅亭6</option>
								</select>
							</p>
							<p><label for="adop">广告客户/画面名称</label><input type="text" id="adop"></p>
							<p><label for="raod">路名</label><input type="text" id="road"></p>
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
								<option value="亭子移位">亭子移位</option>
								<option value="亭子拆除">亭子拆除</option>
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
						<h3>修改铭牌信息</h3>
						<input type="hidden" name="count" id="rowcount">
						<input type="hidden" name="picnumber" id="picnumber">
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
									<option value="亭子移位">亭子移位</option>
									<option value="亭子拆除">亭子拆除</option>
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
				<div class="simple_overlay_line" id="mies4">
				
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
								<p>
								<label for="htype">信息修改类型</label>
								<select disabled  id="htype" name="type">
									<option value="">--</option>
									<option value="线路调整">线路调整</option>
									<option value="亭子移位">亭子移位</option>
									<option value="亭子拆除">亭子拆除</option>
								</select>
								</p>
							</div>
							<div><label for="hadstart">画面上画日期</label><input type="date" id="hadstart"></div>
							<div class="clear"></div>
							<div><label for="hadend">画面到期日期</label><input type="date" id="hadend"></div>
							<div class="clear"></div>
							<div id="digtimepanel"><label for="hdigtime" style="margin-left:20px">挖掘日期</label><input disabled  type="date" id="hdigtime"></div>
							<div class="clear"></div>
							<div id="finishdatepanel"><label for="hfinishdate" style="margin-left:20px">完成日期</label><input disabled  type="date" id="hfinishdate"></div>
							<div class="clear"></div>
							<div id="lastcaredatepanel"><label for="hlastcaredate">上次养护日期</label><input type="date" id="hlastcaredate"></div>
							<div style="float:left;margin:0 20px;">
								<label for="hcomments" style="color:#000;float:left">备注</label>
								<textarea id="hcomments" disabled  rows="4" cols="20" style="margin-left:10px;height:120px;width:280px;resize:none;padding:2px"></textarea>
							</div>
							<div id="himg"><img /></div>
						</div>
						<div id="cur">
							<div id="fields">
								<p><label for="area">区域</label><input disabled  type="text" id="area"></p>
								<p><label for="stopnum">站点编号</label><input disabled  type="text" id="stopnum"></p>
								<p><label for="picnumber">画面编号</label><input disabled  type="text" id="picnumber"></p>
								<p><label for="entitynum">设施编号</label><input disabled  type="text" id="entitynum"></p>
								<p><label for="nanhuitingtype">设施型号</label><input disabled  type="text" id="nanhuitingtype"></p>
								<p><label for="adop">广告客户/画面名称</label><input disabled  type="text" id="adop"></p>
							
						</div>
						<div id="fields2">
								<p><label for="road">路名</label><input disabled  type="text" id="road"></p>
								<p><label for="stop">站名</label><input disabled  type="text" id="stop"></p>
								<p><label for="address">地址</label><input disabled  type="text" id="address"></p>
								<p><label for="direction">车向</label><input disabled  type="text" id="direction"></p>
								<p><label for="line">线路</label><input disabled  type="text" id="line"></p>
								<p><label for="finalstop">开往方向</label><input disabled  type="text" id="finalstop"></p>
								<p><label for="nextstop">线路下一站</label><input disabled  type="text" id="nextstop"></p>
							<p>
							<label for="type">信息修改类型</label>
							<select disabled  id="type" name="type">
								<option value="">--</option>
								<option value="线路调整">线路调整</option>
								<option value="亭子移位">亭子移位</option>
								<option value="亭子拆除">亭子拆除</option>
							</select>
							</p>
						</div>
						<div><label for="adstart">画面上画日期</label><input type="date" id="adstart"></div>
						<div class="clear"></div>
						<div><label for="adend">画面到期日期</label><input type="date" id="adend"></div>
						<div class="clear"></div>
						<div id="digtimepanel"><label for="digtime" style="margin-left:20px">挖掘日期</label><input disabled  type="date" id="digtime"></div>
						<div class="clear"></div>
						<div id="finishdatepanel"><label for="finishdate" style="margin-left:20px">完成日期</label><input disabled  type="date" id="finishdate"></div>
						<div class="clear"></div>
						<div id="lastcaredatepanel"><label for="lastcaredate">上次养护日期</label><input type="date" id="lastcaredate"></div>
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
				<div class="simple_overlay_line" id="mies6">
				
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