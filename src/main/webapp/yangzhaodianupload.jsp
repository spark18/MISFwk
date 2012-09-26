<%@ page contentType="text/html;charset=GBK" language="java"%>
<html>
<head>
<script type="text/javascript" src="res/js/jquery-1.7.min.js"></script>
<script type="text/javascript"
	src="res/js/jquery-ui-1.8.7.custom.min.js"></script>
<script type="text/javascript" src="res/js/jquery.tools.min.js"></script>
<script type="text/javascript" src="res/js/flexigrid.js"></script>
<script type="text/javascript" src="res/js/yangzhaodian.js"></script>
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
<link rel="stylesheet" href="res/css/ligan.css">
<link rel="stylesheet" href="res/css/calender.css">
<link rel="stylesheet" href="res/css/jquery-ui-1.8.18.custom.css">
</head>
<body>


	<jsp:include page="header.jsp"></jsp:include>
<div id="file_upload_panel">
 	<div>
 	    <input id="file_upload" name="file_upload" type="file" />
 	</div>
 </div>
<input type="hidden" name="upload" id="isupload"/>
	<div class="blurb-wrapper">

		<div class="blurb clearfix">
			<a style="outline-style: none;" href="javascript:void(0)" id="approve-btn" rel="#mies2"> 批准导入</a>
			<div style="float:left">
				<p class="custom-font">请验证数据准确性。您可能要检查：
				</p>
				<div class="clear"></div>
				<ul class="validation-tip">
					<li>完成日期一定在挖坑日期之后</li>
					<li>所有数据字段不能为空</li>
				</ul>
			</div>
		</div>
		<div class="simple_overlay_approve" id="mies2">
				
			<!-- image details -->
			<div class="details">
				<h3>批准人确认</h3>
				<p><label for="approver">批准人</label><input type="text" id="approver"></p>
				<p><label for="approver-passwd">密码</label><input type="password" id="approver-passwd"></p>
				<div id="submit">批准数据导入</div>
				<div class="progress"><img src="res/css/images/progress.gif"></div>
			</div>
		
		</div>

	</div>
	<div class="page-body-wrapper"></div>
	<div class="container clearfix page hasRightSidebar">
		<div>
			<div class="title">
				<h4 class="custom-font heading">扬招点信息列表</h4>
			</div>
			<div class="title-history">
				<h4 class="custom-font heading">历史导入记录</h4>
			</div>
		</div>
		<div class="clear"></div>
		<div class="page-content">

			<div class="breadcrumb clearfix">
				<a style="outline-style: none;" href="http://wptitans.com/archin"
					class="home">首页</a> <a style="outline-style: none;"
					href="http://wptitans.com/archin/features/">管理扬招点数据信息</a> <span
					class="current">扬招点信息导入</span>
			</div>
			<div class="content clearfix">
				<div class="simple_overlay" id="mies1">
				
					<!-- image details -->
					<div class="details">
						<h3>修改扬招点信息</h3>
						<input type="hidden" name="id" id="rownum">
						<div id="fields">
							<p><label for="area">区域</label><input type="text" id="area"></p>
							<p><label for="picnumber">铭牌号</label><input type="text" id="picnumber"></p>
							<p><label for="raod">路名</label><input type="text" id="road"></p>
							<p><label for="adop">广告运营商</label><input type="text" id="adop"></p>
							
						</div>
						<div id="fields2">
							<p><label for="address">地址</label><input type="text" id="address"></p>
							<p><label for="direction">车向</label><input type="text" id="direction"></p>
							<p><label for="adperiod">广告有效期</label><input type="text" id="adperiod"></p>
						</div>
						<div id="digtimepanel"><label for="digtime">挖掘日期</label><input type="date" id="digtime"></div>
						<div id="finishdatepanel"><label for="finishdate">完成日期</label><input type="date" id="finishdate"></div>
						<div id="img"><img /></div>
						<div id="img-btn"><input id="img_upload" name="file_upload" type="file" /></div>
						<div id="submit">提交修改</div>
						<div class="progress"><img src="res/css/images/progress.gif" style="padding-top:10px; padding-bottom:20px"></div>
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
			铭牌号, 路名, 地址, 车向, 挖掘日期, 完成日期]
		</span>
	</div>
</body>
</html>