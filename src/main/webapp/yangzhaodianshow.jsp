<%@ page contentType="text/html;charset=GBK" language="java"%>
<html>
<head>
<script type="text/javascript" src="res/js/jquery-1.7.min.js"></script>
<script type="text/javascript"
	src="res/js/jquery-ui-1.8.7.custom.min.js"></script>
<script type="text/javascript" src="res/js/jquery.tools.min.js"></script>
<script type="text/javascript" src="res/js/flexigrid.js"></script>
<script type="text/javascript" src="res/js/yangzhaodianshow.js"></script>
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
					class="current">扬招点设备信息导入</span>
			</div>
			<div class="content clearfix">
				<div class="simple_overlay" id="mies1">
					<input type="hidden" name="id" id="rownum">
					<!-- image details -->
					<div id="fields">
						<p><label for="area">区域</label><input type="text" id="area"></p>
						<p><label for="stopnum">站点编号</label><input type="text" id="stopnum"></p>
						<p><label for="picnumber">画面编号</label><input type="text" id="picnumber"></p>
						<p><label for="entitynum">设施编号</label><input type="text" id="entitynum"></p>
						<p><label for="yangzhaodiantype">设施型号</label>
							<select id="yangzhaodiantype">
								<option value="">--</option>
								<option value="两幅亭7">两幅亭7</option>
								<option value="三幅亭2">三幅亭2</option>
								<option value="三幅亭3">三幅亭3</option>
								<option value="三幅亭4">三幅亭4</option>
								<option value="五幅亭5">五幅亭5</option>
								<option value="五幅亭6">五幅亭6</option>
							</select>
						</p>
						<p><label for="direction">车向</label><input type="text" id="direction"></p>
					</div>
						<div id="fields2">
							<p><label for="adop">广告客户/画面名称</label><input type="text" id="adop"></p>
							<p><label for="raod">路名</label><input type="text" id="road"></p>
							<p><label for="stop">站名</label><input type="text" id="stop"></p>
							<p><label for="address">地址</label><input type="text" id="address"></p>
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
						<input type="hidden" name="number" id="number">
						<table>
							<tr>
								<th style="width:55px">区域</th><th style="width:90px">路名</th>
								<th style="width:90px">地址</th>
								<th style="width:110px">完成时间</th>
							</tr>
						</table>
							<div style="color:#000;margin:15px;float:left">
								<label for="type">信息修改类型</label>
								<select id="type" name="type">
									<option value="线路调整">画面修改</option>
									<option value="扬招点移位">扬招点移位</option>
									<option value="扬招点拆除">扬招点拆除</option>
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
						<input type="hidden" name="yangzhaodianid">
						<h3>扬招点设备信息修改详细记录</h3>
						<div id="old">
							<div id="fields">
								<p><label for="harea">区域</label><input disabled  type="text" id="harea"></p>
								<p><label for="hstopnum">站点编号</label><input disabled  type="text" id="hstopnum"></p>
								<p><label for="hpicnumber">画面编号</label><input disabled  type="text" id="hpicnumber"></p>
								<p><label for="hentitynum">设施编号</label><input disabled  type="text" id="hentitynum"></p>
								<p><label for="hyangzhaodiantype">设施型号</label><input disabled  type="text" id="hyangzhaodiantype"></p>
							</div>
							<div id="fields2">
								<p><label for="hroad">路名</label><input disabled  type="text" id="hroad"></p>
								<p><label for="hstop">站名</label><input disabled  type="text" id="hstop"></p>
								<p><label for="haddress">地址</label><input disabled  type="text" id="haddress"></p>
								<p><label for="hdirection">车向</label><input disabled  type="text" id="hdirection"></p>
								<p><label for="hadop">广告客户/画面名称</label><input disabled  type="text" id="hadop"></p>
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
							<p><label for="hadstart">画面上画日期</label><input type="date" id="hadstart"></p>
							<div class="clear"></div>
							<p><label for="hadend">画面到期日期</label><input type="date" id="hadend"></p>
							<div class="clear"></div>
							<p id="lastcaredatepanel"><label for="hlastcaredate">上次养护日期</label><input type="date" id="hlastcaredate"></p>
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
								<p><label for="yangzhaodiantype">设施型号</label><input disabled  type="text" id="yangzhaodiantype"></p>
							
						</div>
						<div id="fields2">
								<p><label for="road">路名</label><input disabled  type="text" id="road"></p>
								<p><label for="stop">站名</label><input disabled  type="text" id="stop"></p>
								<p><label for="address">地址</label><input disabled  type="text" id="address"></p>
								<p><label for="direction">车向</label><input disabled  type="text" id="direction"></p>
								<p><label for="adop">广告客户/画面名称</label><input disabled  type="text" id="adop"></p>
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
						<p><label for="adstart">画面上画日期</label><input type="date" id="adstart"></p>
						<div class="clear"></div>
						<p><label for="adend">画面到期日期</label><input type="date" id="adend"></p>
						<div class="clear"></div>
						<p id="lastcaredatepanel"><label for="lastcaredate">上次养护日期</label><input type="date" id="lastcaredate"></p>
						<div style="float:left;margin:0 20px;">
							<label for="comments" style="color:#000;float:left">备注</label>
							<textarea disabled id="comments" rows="4" cols="20" style="margin-left:10px;height:120px;width:280px;resize:none;padding:2px"></textarea>
						</div>
						<div id="img"><img /></div>
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
		<span id="errmsgtext">请检查Excel文件表头<br>正确表头,前7列分别是:<br>[区域,
			铭牌号, 路名, 地址, 挖掘日期, 完成日期, 广告有效期, 广告运营商]
		</span>
	</div>
</body>
</html>