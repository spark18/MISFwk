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
			<a style="outline-style: none;" href="javascript:void(0)" id="approve-btn" rel="#mies2"> ��׼����</a>
			<div style="float:left">
				<p class="custom-font">����֤����׼ȷ�ԡ�������Ҫ��飺
				</p>
				<div class="clear"></div>
				<ul class="validation-tip">
					<li>�������һ�����ڿ�����֮��</li>
					<li>���������ֶβ���Ϊ��</li>
				</ul>
			</div>
		</div>
		<div class="simple_overlay_approve" id="mies2">
				
			<!-- image details -->
			<div class="details">
				<h3>��׼��ȷ��</h3>
				<p><label for="approver">��׼��</label><input type="text" id="approver"></p>
				<p><label for="approver-passwd">����</label><input type="password" id="approver-passwd"></p>
				<div id="submit">��׼���ݵ���</div>
				<div class="progress"><img src="res/css/images/progress.gif"></div>
			</div>
		
		</div>

	</div>
	<div class="page-body-wrapper"></div>
	<div class="container clearfix page hasRightSidebar">
		<div>
			<div class="title">
				<h4 class="custom-font heading">���е���Ϣ�б�</h4>
			</div>
			<div class="title-history">
				<h4 class="custom-font heading">��ʷ�����¼</h4>
			</div>
		</div>
		<div class="clear"></div>
		<div class="page-content">

			<div class="breadcrumb clearfix">
				<a style="outline-style: none;" href="http://wptitans.com/archin"
					class="home">��ҳ</a> <a style="outline-style: none;"
					href="http://wptitans.com/archin/features/">�������е�������Ϣ</a> <span
					class="current">���е���Ϣ����</span>
			</div>
			<div class="content clearfix">
				<div class="simple_overlay" id="mies1">
				
					<!-- image details -->
					<div class="details">
						<h3>�޸����е���Ϣ</h3>
						<input type="hidden" name="id" id="rownum">
						<div id="fields">
							<p><label for="area">����</label><input type="text" id="area"></p>
							<p><label for="picnumber">���ƺ�</label><input type="text" id="picnumber"></p>
							<p><label for="raod">·��</label><input type="text" id="road"></p>
							<p><label for="adop">�����Ӫ��</label><input type="text" id="adop"></p>
							
						</div>
						<div id="fields2">
							<p><label for="address">��ַ</label><input type="text" id="address"></p>
							<p><label for="direction">����</label><input type="text" id="direction"></p>
							<p><label for="adperiod">�����Ч��</label><input type="text" id="adperiod"></p>
						</div>
						<div id="digtimepanel"><label for="digtime">�ھ�����</label><input type="date" id="digtime"></div>
						<div id="finishdatepanel"><label for="finishdate">�������</label><input type="date" id="finishdate"></div>
						<div id="img"><img /></div>
						<div id="img-btn"><input id="img_upload" name="file_upload" type="file" /></div>
						<div id="submit">�ύ�޸�</div>
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
						href="javascript:void(0)">�Ϻ�����ͣ���豸���޹�˾��Ϣ����ϵͳ</a></li>
					
				</ul>
				<p class="footer-text"></p>
			</div>
		</div>
	</div>

	<div id='errmsgform'>
		<img title="�رմ�����ʾ" id="errmsgclose" src="res/css/images/close.png">
		<div class="clear"></div>
		<span id="errmsgtext">����Excel�ļ���ͷ<br>��ȷ��ͷ,ǰ11�зֱ���:<br>[����,
			���ƺ�, ·��, ��ַ, ����, �ھ�����, �������]
		</span>
	</div>
</body>
</html>