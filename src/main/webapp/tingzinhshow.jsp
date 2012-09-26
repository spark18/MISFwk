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
				<h4 class="custom-font heading">�豸��Ϣ�б�</h4>
			</div>
			<div class="title-history">
				<h4 class="custom-font heading">�����޸���ʷ��¼</h4>
			</div>
		</div>
		<div class="clear"></div>
		<div class="page-content">

			<div class="breadcrumb clearfix">
				<a style="outline-style: none;" href="http://wptitans.com/archin"
					class="home">��ҳ</a> <a style="outline-style: none;"
					href="http://wptitans.com/archin/features/">�����豸������Ϣ</a> <span
					class="current">���ͤ�豸��Ϣ����</span>
			</div>
			<div class="content clearfix">
				<div class="simple_overlay" id="mies1">
				
					<!-- image details -->
					<div class="details">
						<h3>�޸Ĺ��ͤ�豸��Ϣ</h3>
						<input type="hidden" name="id" id="rownum">
						<div id="fields">
							<p><label for="area">����</label><input type="text" id="area"></p>
							<p><label for="stopnum">վ����</label><input type="text" id="stopnum"></p>
							<p><label for="picnumber">������</label><input type="text" id="picnumber"></p>
							<p><label for="entitynum">��ʩ���</label><input type="text" id="entitynum"></p>
							<p><label for="nanhuitingtype">��ʩ�ͺ�</label>
								<select id="nanhuitingtype">
									<option value="">--</option>
									<option value="����ͤ7">����ͤ7</option>
									<option value="����ͤ2">����ͤ2</option>
									<option value="����ͤ3">����ͤ3</option>
									<option value="����ͤ4">����ͤ4</option>
									<option value="���ͤ5">���ͤ5</option>
									<option value="���ͤ6">���ͤ6</option>
								</select>
							</p>
							<p><label for="adop">���ͻ�/��������</label><input type="text" id="adop"></p>
							<p><label for="raod">·��</label><input type="text" id="road"></p>
						</div>
						<div id="fields2">
							<p><label for="stop">վ��</label><input type="text" id="stop"></p>
							<p><label for="address">��ַ</label><input type="text" id="address"></p>
							<p><label for="direction">����</label><input type="text" id="direction"></p>
							<p><label for="line">��·</label><input type="text" id="line"></p>
							<p><label for="finalstop">��������</label><input type="text" id="finalstop"></p>
							<p><label for="nextstop">��·��һվ</label><input type="text" id="nextstop"></p>
							<p>
							<label for="type">��Ϣ�޸�����</label>
							<select id="type" name="type">
								<option value="��·����">��·����</option>
								<option value="ͤ����λ">ͤ����λ</option>
								<option value="ͤ�Ӳ��">ͤ�Ӳ��</option>
							</select>
							</p>
						</div>
						<div style="float:left"><label for="adstart">�����ϻ�����</label><input type="date" id="adstart"></div>
						<div style="float:left"><label for="adend">���浽������</label><input type="date" id="adend"></div>
						<div id="digtimepanel"><label for="digtime">�ھ�����</label><input type="date" id="digtime"></div>
						<div id="finishdatepanel"><label for="finishdate">�������</label><input type="date" id="finishdate"></div>
						<div id="lastcaredatepanel"><label for="lastcaredate">�ϴ���������</label><input type="date" id="lastcaredate"></div>
						<div id="img"><img /></div>
						<div style="float:left;margin:20px;">
							<label for="comments" style="color:#000;float:left">��ע</label>
							<textarea id="comments" rows="4" cols="20" style="margin-left:10px;height:220px;width:280px;resize:none;padding:2px"></textarea>
						</div>
						<div id="img-btn"><input id="img_upload" name="file_upload" type="file" /></div>
						<div id="submit">�ύ�޸�</div>
						<div class="progress"><img src="res/css/images/progress.gif" style="padding-top:10px; padding-bottom:20px"></div>
					</div>
				
				</div>
				<div class="simple_overlay" id="mies2" style="width:800px;height:300px">
				
					<!-- image details -->
					<div class="details" style="width:760px">
						<h3>�޸�������Ϣ</h3>
						<input type="hidden" name="count" id="rowcount">
						<input type="hidden" name="picnumber" id="picnumber">
						<table>
							<tr>
								<th style="width:50px">����</th><th style="width:55px">վ����</th><th style="width:90px">������</th>
								<th style="width:50px">��ʩ���</th><th style="width:55px">��ʩ�ͺ�</th><th style="width:90px">���ͻ�/��������</th>
								<th style="width:70px">�����ϻ�����</th><th style="width:90px">���浽������</th><th style="width:40px">·��</th>
								<th style="width:110px">վ��</th><th style="width:110px">��ַ</th><th style="width:110px">����</th>
								<th style="width:110px">��·</th><th style="width:110px">��������</th><th style="width:110px">����·��һվ</th>
								<th style="width:110px">�ڿ�����</th><th style="width:110px">�������</th><th style="width:110px">�ϴ���������</th>
							</tr>
						</table>
							<div style="color:#000;margin:15px;float:left">
								<label for="type">��Ϣ�޸�����</label>
								<select id="type" name="type">
									<option value="��·����">��·����</option>
									<option value="ͤ����λ">ͤ����λ</option>
									<option value="ͤ�Ӳ��">ͤ�Ӳ��</option>
								</select>
							</div>
							<div style="float:left;margin:15px">
								<label for="comments" style="color:#000;float:left">��ע</label>
								<textarea id="comments" rows="4" cols="20" style="margin:4px;height:75px;width:493px;resize:none;padding:2px"></textarea>
							</div>
							<div class="clear"></div>
						<div id="modify-ligan" >�ύ�޸�</div>
						<div class="progress" style="margin:0"><img src="res/css/images/progress.gif" style="padding-top:10px; padding-bottom:20px"></div>
					</div>
				</div>
				<div class="simple_overlay_line" id="mies4">
				
					<!-- image details -->
					<div class="details">
						<input type="hidden" name="tingziid">
						<h3>���ͤ�豸��Ϣ�޸���ϸ��¼</h3>
						<div id="old">
							<div id="fields">
								<p><label for="harea">����</label><input disabled  type="text" id="harea"></p>
								<p><label for="hstopnum">վ����</label><input disabled  type="text" id="hstopnum"></p>
								<p><label for="hpicnumber">������</label><input disabled  type="text" id="hpicnumber"></p>
								<p><label for="hentitynum">��ʩ���</label><input disabled  type="text" id="hentitynum"></p>
								<p><label for="hnanhuitingtype">��ʩ�ͺ�</label><input disabled  type="text" id="hnanhuitingtype"></p>
								<p><label for="hadop">���ͻ�/��������</label><input disabled  type="text" id="hadop"></p>
							</div>
							<div id="fields2">
								<p><label for="hroad">·��</label><input disabled  type="text" id="hroad"></p>
								<p><label for="hstop">վ��</label><input disabled  type="text" id="hstop"></p>
								<p><label for="haddress">��ַ</label><input disabled  type="text" id="haddress"></p>
								<p><label for="hdirection">����</label><input disabled  type="text" id="hdirection"></p>
								<p><label for="hline">��·</label><input disabled  type="text" id="hline"></p>
								<p><label for="hfinalstop">��������</label><input disabled  type="text" id="hfinalstop"></p>
								<p><label for="hnextstop">��·��һվ</label><input disabled  type="text" id="hnextstop"></p>
								<p>
								<label for="htype">��Ϣ�޸�����</label>
								<select disabled  id="htype" name="type">
									<option value="">--</option>
									<option value="��·����">��·����</option>
									<option value="ͤ����λ">ͤ����λ</option>
									<option value="ͤ�Ӳ��">ͤ�Ӳ��</option>
								</select>
								</p>
							</div>
							<div><label for="hadstart">�����ϻ�����</label><input type="date" id="hadstart"></div>
							<div class="clear"></div>
							<div><label for="hadend">���浽������</label><input type="date" id="hadend"></div>
							<div class="clear"></div>
							<div id="digtimepanel"><label for="hdigtime" style="margin-left:20px">�ھ�����</label><input disabled  type="date" id="hdigtime"></div>
							<div class="clear"></div>
							<div id="finishdatepanel"><label for="hfinishdate" style="margin-left:20px">�������</label><input disabled  type="date" id="hfinishdate"></div>
							<div class="clear"></div>
							<div id="lastcaredatepanel"><label for="hlastcaredate">�ϴ���������</label><input type="date" id="hlastcaredate"></div>
							<div style="float:left;margin:0 20px;">
								<label for="hcomments" style="color:#000;float:left">��ע</label>
								<textarea id="hcomments" disabled  rows="4" cols="20" style="margin-left:10px;height:120px;width:280px;resize:none;padding:2px"></textarea>
							</div>
							<div id="himg"><img /></div>
						</div>
						<div id="cur">
							<div id="fields">
								<p><label for="area">����</label><input disabled  type="text" id="area"></p>
								<p><label for="stopnum">վ����</label><input disabled  type="text" id="stopnum"></p>
								<p><label for="picnumber">������</label><input disabled  type="text" id="picnumber"></p>
								<p><label for="entitynum">��ʩ���</label><input disabled  type="text" id="entitynum"></p>
								<p><label for="nanhuitingtype">��ʩ�ͺ�</label><input disabled  type="text" id="nanhuitingtype"></p>
								<p><label for="adop">���ͻ�/��������</label><input disabled  type="text" id="adop"></p>
							
						</div>
						<div id="fields2">
								<p><label for="road">·��</label><input disabled  type="text" id="road"></p>
								<p><label for="stop">վ��</label><input disabled  type="text" id="stop"></p>
								<p><label for="address">��ַ</label><input disabled  type="text" id="address"></p>
								<p><label for="direction">����</label><input disabled  type="text" id="direction"></p>
								<p><label for="line">��·</label><input disabled  type="text" id="line"></p>
								<p><label for="finalstop">��������</label><input disabled  type="text" id="finalstop"></p>
								<p><label for="nextstop">��·��һվ</label><input disabled  type="text" id="nextstop"></p>
							<p>
							<label for="type">��Ϣ�޸�����</label>
							<select disabled  id="type" name="type">
								<option value="">--</option>
								<option value="��·����">��·����</option>
								<option value="ͤ����λ">ͤ����λ</option>
								<option value="ͤ�Ӳ��">ͤ�Ӳ��</option>
							</select>
							</p>
						</div>
						<div><label for="adstart">�����ϻ�����</label><input type="date" id="adstart"></div>
						<div class="clear"></div>
						<div><label for="adend">���浽������</label><input type="date" id="adend"></div>
						<div class="clear"></div>
						<div id="digtimepanel"><label for="digtime" style="margin-left:20px">�ھ�����</label><input disabled  type="date" id="digtime"></div>
						<div class="clear"></div>
						<div id="finishdatepanel"><label for="finishdate" style="margin-left:20px">�������</label><input disabled  type="date" id="finishdate"></div>
						<div class="clear"></div>
						<div id="lastcaredatepanel"><label for="lastcaredate">�ϴ���������</label><input type="date" id="lastcaredate"></div>
						<div style="float:left;margin:0 20px;">
							<label for="comments" style="color:#000;float:left">��ע</label>
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
						<h3>վַ��Ϣ�޸���ϸ��¼</h3>
						<div id="old">
							<div id="fields">
								<p><label for="hname">վ��</label><input disabled  type="text" id="hname"></p>
								<p><label for="halias">��վ��</label><input disabled  type="text" id="halias"></p>
								<p><label for="hstopaddress">վַ</label><input disabled  type="text" id="hstopaddress"></p>
								<p><label for="hstartend">��ĩ��ʱ��</label><input disabled  type="text" id="hstartend"></p>
							</div>
						</div>
						<div id="cur">
							<div id="fields">
								<p><label for="name">վ��</label><input disabled  type="text" id="name"></p>
								<p><label for="alias">��վ��</label><input disabled  type="text" id="alias"></p>
								<p><label for="stopaddress">վַ</label><input disabled  type="text" id="stopaddress"></p>
								<p><label for="startend">��ĩ��ʱ��</label><input disabled  type="text" id="startend"></p>
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
						<h3>Ʊ����Ϣ�޸���ϸ��¼</h3>
						<div id="old">
							<div id="fields">
								<p><label for="htype">����</label><select name="type" id="htype"><option>--</option><option value="norm">��ͨ��</option><option value="air">�յ���</option></select></p>
								<p><label for="hairPrice">�յ���Ʊ��</label><input disabled  type="text" id="hairPrice"></p>
								<p><label for="hnormPrice">��ͨ��Ʊ��</label><input disabled  type="text" id="hnormPrice"></p>
								<p><label for="hpricetype">Ʊ������</label><select name="pricetype" id="hpricetype"><option>--</option><option value="single">��һ</option><option value="multi">�༶</option></select></p>
								<p><label for="hautoSale">������Ʊ</label><select name="autoSale" id="hautoSale"><option>--</option><option value="y">��</option><option value="n">��</option></select></p>
								<p><label for="hopunit">��Ӫ��λ</label><input disabled  type="text" id="hopunit"></p>
							</div>
						</div>
						<div id="cur">
							<div id="fields">
								<p><label for="type">����</label><select name="type" id="type"><option>--</option><option value="norm">��ͨ��</option><option value="air">�յ���</option></select></p>
								<p><label for="airPrice">�յ���Ʊ��</label><input disabled  type="text" id="airPrice"></p>
								<p><label for="normPrice">��ͨ��Ʊ��</label><input disabled  type="text" id="normPrice"></p>
								<p><label for="pricetype">Ʊ������</label><select name="pricetype" id="pricetype"><option>--</option><option value="single">��һ</option><option value="multi">�༶</option></select></p>
								<p><label for="autoSale">������Ʊ</label><select name="autoSale" id="autoSale"><option>--</option><option value="y">��</option><option value="n">��</option></select></p>
								<p><label for="opunit">��Ӫ��λ</label><input disabled  type="text" id="opunit"></p>
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
			���ƺ�, ·��, վ��, ��ַ, ����, ·��, �ھ�����, ��������, ��·��һվ, �������]
		</span>
	</div>
</body>
</html>