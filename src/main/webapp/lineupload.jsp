<%@ page contentType="text/html;charset=GBK" language="java"%>
<html>
<head>
<script type="text/javascript" src="res/js/jquery-1.7.min.js"></script>
<script type="text/javascript"
	src="res/js/jquery-ui-1.8.7.custom.min.js"></script>
<script type="text/javascript" src="res/js/jquery.tools.min.js"></script>
<script type="text/javascript" src="res/js/flexigrid.js"></script>
<script type="text/javascript" src="res/js/lineupload.js"></script>
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
				<p class="custom-font">导入上行公交线路信息
				</p>
				<div class="clear"></div>
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
		
		<div class="simple_overlay_busticket" id="mies3">
				
			<!-- image details -->
			<div class="details">
				<h3>公交车型及票价信息</h3>
				<div class="table"></div>
				<div class="tableair"></div>
				<!--  <div id="submit" style="margin-top:20px">修改</div>  -->
				<div class="progress"><img src="res/css/images/progress.gif"></div>
			</div>
		
		</div>

	</div>
	<div class="page-body-wrapper"></div>
	<div class="container clearfix page hasRightSidebar">
		<div>
			<div class="title">
				<h4 class="custom-font heading">上行线路信息列表</h4>
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
					href="http://wptitans.com/archin/features/">管理设备数据信息</a> <span
					class="current">线路信息导入</span>
			</div>
			<div class="content clearfix">
				<div class="simple_overlay" id="mies1">
				
					<!-- image details -->
					<div class="details">
						<h4>修改下行线路信息</h4>
						<div class="table"></div>
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
		<span id="errmsgtext">1. 请检查Excel文件表头<br>2. 请确认系统中无重复线路信息<br>
		</span>
	</div>
</body>
</html>