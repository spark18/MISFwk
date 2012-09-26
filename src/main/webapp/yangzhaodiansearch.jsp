<%@ page contentType="text/html;charset=GBK" language="java"%>
<html>
<head>
<script type="text/javascript" src="res/js/jquery-1.7.min.js"></script>
<script type="text/javascript"
	src="res/js/jquery-ui-1.8.7.custom.min.js"></script>
<script type="text/javascript" src="res/js/jquery.tools.min.js"></script>
<script type="text/javascript" src="res/js/flexigrid.js"></script>
<script type="text/javascript" src="res/js/yangzhaodiansearch.js"></script>
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
				<h4 class="custom-font heading">���е���������</h4>
			</div>
			<div class="ligan-search-result">
				<h4 class="custom-font heading">���е��������</h4>
			</div>
		</div>
		<div class="clear"></div>
		<div class="page-content">

			<div class="breadcrumb clearfix">
				<a style="outline-style: none;" href="http://wptitans.com/archin"
					class="home">��ҳ</a> <a style="outline-style: none;"
					href="http://wptitans.com/archin/features/">�����豸������Ϣ</a> <span
					class="current">���е�����</span>
			</div>
			<div class="content clearfix">
				<div class="simple_overlay" id="mies1">
				
					<!-- image details -->
					<div class="details">
						<h3>�޸����е��豸��Ϣ</h3>
						<input type="hidden" name="id" id="rownum">
						<div id="fields">
							<p><label for="area">����</label><input type="text" id="area"></p>
							<p><label for="stopnum">վ����</label><input type="text" id="stopnum"></p>
							<p><label for="picnumber">������</label><input type="text" id="picnumber"></p>
							<p><label for="entitynum">��ʩ���</label><input type="text" id="entitynum"></p>
							<p><label for="yangzhaodiantype">��ʩ�ͺ�</label>
								<select id="yangzhaodiantype">
									<option value="">--</option>
									<option value="����ͤ7">����ͤ7</option>
									<option value="����ͤ2">����ͤ2</option>
									<option value="����ͤ3">����ͤ3</option>
									<option value="����ͤ4">����ͤ4</option>
									<option value="���ͤ5">���ͤ5</option>
									<option value="���ͤ6">���ͤ6</option>
								</select>
							</p>
							<p><label for="direction">����</label><input type="text" id="direction"></p>
						</div>
						<div id="fields2">
							<p><label for="adop">���ͻ�/��������</label><input type="text" id="adop"></p>
							<p><label for="raod">·��</label><input type="text" id="road"></p>
							<p><label for="stop">վ��</label><input type="text" id="stop"></p>
							<p><label for="address">��ַ</label><input type="text" id="address"></p>
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
						<h3>�޸���·��Ϣ</h3>
						<input type="hidden" name="count" id="rowcount">
						<input type="hidden" name="number" id="number">
						<table>
							<tr>
								<th style="width:55px">����</th><th style="width:90px">·��</th>
								<th style="width:90px">��ַ</th>
								<th style="width:110px">���ʱ��</th>
							</tr>
						</table>
							<div style="color:#000;margin:15px;float:left">
								<label for="type">��Ϣ�޸�����</label>
								<select id="type" name="type">
									<option value="��·����">�����޸�</option>
									<option value="���е���λ">���е���λ</option>
									<option value="���е���">���е���</option>
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
						<input type="hidden" name="yangzhaodianid">
						<h3>���/�޸���·��Ϣ</h3>
						<div class="table"></div>
						<div id="multi">
							<fieldset id="norm">
								<Legend style="color:#333">��ͨ���༶Ʊ��</Legend>
								<div id="img-norm"><a target="_blank"><img class="ticimg" src="/busticketimg-show"></a></div>
								<div id="img-btn-norm"><input id="img_upload_norm" name="file_upload" type="file" /></div>
							</fieldset>
							<fieldset id="air">
								<Legend style="color:#333">�յ����༶Ʊ��</Legend>
								<div id="img-air"><a target="_blank"><img class="ticimg" src=""></a></div>
								<div id="img-btn-air"><input id="img_upload_air" name="file_upload" type="file" /></div>
							</fieldset>
						</div>
						<div class="tabletic"></div>
						<div class="tableair"></div>
						<div id="submitticket" style="margin:30px 0 0 30px;">�޸�Ʊ����Ϣ</div>
						<div class="progress"><img src="res/css/images/progress.gif" style="padding-top:10px; padding-bottom:20px"></div>
					</div>
				
				</div>
				<div class="simple_overlay_line" id="mies5">
				
					<!-- image details -->
					<div class="details">
						<input type="hidden" name="tingziid">
						<h3>���е��豸��Ϣ�޸���ϸ��¼</h3>
						<div id="old">
							<div id="fields">
								<p><label for="harea">����</label><input disabled  type="text" id="harea"></p>
								<p><label for="hstopnum">վ����</label><input disabled  type="text" id="hstopnum"></p>
								<p><label for="hpicnumber">������</label><input disabled  type="text" id="hpicnumber"></p>
								<p><label for="hentitynum">��ʩ���</label><input disabled  type="text" id="hentitynum"></p>
								<p><label for="hyangzhaodiantype">��ʩ�ͺ�</label><input disabled  type="text" id="hyangzhaodiantype"></p>
							</div>
							<div id="fields2">
								<p><label for="hroad">·��</label><input disabled  type="text" id="hroad"></p>
								<p><label for="hstop">վ��</label><input disabled  type="text" id="hstop"></p>
								<p><label for="haddress">��ַ</label><input disabled  type="text" id="haddress"></p>
								<p><label for="hdirection">����</label><input disabled  type="text" id="hdirection"></p>
								<p><label for="hadop">���ͻ�/��������</label><input disabled  type="text" id="hadop"></p>
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
							<p><label for="hadstart">�����ϻ�����</label><input type="date" id="hadstart"></p>
							<div class="clear"></div>
							<p><label for="hadend">���浽������</label><input type="date" id="hadend"></p>
							<div class="clear"></div>
							<p id="lastcaredatepanel"><label for="hlastcaredate">�ϴ���������</label><input type="date" id="hlastcaredate"></p>
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
								<p><label for="yangzhaodiantype">��ʩ�ͺ�</label><input disabled  type="text" id="yangzhaodiantype"></p>
							
						</div>
						<div id="fields2">
								<p><label for="road">·��</label><input disabled  type="text" id="road"></p>
								<p><label for="stop">վ��</label><input disabled  type="text" id="stop"></p>
								<p><label for="address">��ַ</label><input disabled  type="text" id="address"></p>
								<p><label for="direction">����</label><input disabled  type="text" id="direction"></p>
								<p><label for="adop">���ͻ�/��������</label><input disabled  type="text" id="adop"></p>
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
						<p><label for="adstart">�����ϻ�����</label><input type="date" id="adstart"></p>
						<div class="clear"></div>
						<p><label for="adend">���浽������</label><input type="date" id="adend"></p>
						<div class="clear"></div>
						<p id="lastcaredatepanel"><label for="lastcaredate">�ϴ���������</label><input type="date" id="lastcaredate"></p>
						<div style="float:left;margin:0 20px;">
							<label for="comments" style="color:#000;float:left">��ע</label>
							<textarea disabled id="comments" rows="4" cols="20" style="margin-left:10px;height:120px;width:280px;resize:none;padding:2px"></textarea>
						</div>
						<div id="img"><img /></div>
						</div>
					</div>
				
				</div>
				<div class="ligan-search-grid">
					<fieldset  class="yangzhaodian-search-fieldset">
						<legend>�������е���������</legend>
						<div class="input" id="area"><label for="line-area">����</label><input id="line-area" type="text" name="area" class="area"></div>
						<div class="input" id="picnumber"><label for="line-number">������</label><input id="line-number" type="text" name="picnumber" class="number"></div>
						<div class="input" id="stopnum"><label for="line-stopnum">վ����</label><input id="line-stopnum" type="text" name="stopnum" class="stopnum"></div>
						<div class="input" id="entitynum"><label for="line-entitynum">��ʩ���</label><input id="line-entitynum" type="text" name="entitynum" class="entitynum"></div>
						<div class="input" id="yangzhaodiantype"><label for="line-nanhuitingtype">��ʩ�ͺ�</label>
							<select id="line-yangzhaodiantype">
								<option value="">--</option>
								<option value="7">����ͤ7</option>
								<option value="2">����ͤ2</option>
								<option value="3">����ͤ3</option>
								<option value="4">����ͤ4</option>
								<option value="5">���ͤ5</option>
								<option value="6">���ͤ6</option>
							</select>
						</div>
						<div class="input" id="adop"><label for="line-adop">���ͻ�/��������</label><input id="line-adop" type="text" name="adop" class="adop"></div>
						<div class="input" id="adstart"><label for="line-adstart">�����ϻ�����</label><input id="line-adstart" type="text" name="adstart" class="adstart"></div>
						<div class="input" id="adend"><label for="line-adend">���浽������</label><input id="line-adend" type="text" name="adend" class="adend"></div>
						<div class="input" id="road"><label for="line-road">·��</label><input id="line-road" type="text" name="road" class="road"></div>
						<div class="input" id="stop"><label for="line-stop">վ��</label><input id="line-stop" type="text" name="stop" class="stop"></div>
						<div class="input" id="address"><label for="line-address">��ַ</label><input id="line-address" type="text" name="address" class="address"></div>
						<div class="input" id="direction"><label for="line-direction">����</label><input id="line-direction" type="text" name="direction" class="direction"></div>
						<div class="input" id="lastcaredate"><label for="line-lastcaredate">�ϴ���������</label><input id="line-lastcaredate" type="date" name="lastcaredate" class="lastcaredate"></div>
						<div class="input" id="comments"><label for="line-comments">��ע</label><input id="line-comments" type="text" name="comments" class="comments"></div>
						<div class="submit">��ѯ���е�</div>
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