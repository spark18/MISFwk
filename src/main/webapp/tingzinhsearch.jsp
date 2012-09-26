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
				<h4 class="custom-font heading">���ͤ��������</h4>
			</div>
			<div class="ligan-search-result">
				<h4 class="custom-font heading">���ͤ�������</h4>
			</div>
		</div>
		<div class="clear"></div>
		<div class="page-content">

			<div class="breadcrumb clearfix">
				<a style="outline-style: none;" href="http://wptitans.com/archin"
					class="home">��ҳ</a> <a style="outline-style: none;"
					href="http://wptitans.com/archin/features/">�����豸������Ϣ</a> <span
					class="current">���ͤ����</span>
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
							<p><label for="nanhuitingtype">��ʩ�ͺ�</label><input type="text" id="nanhuitingtype"></p>
							<p><label for="adop">���ͻ�/��������</label><input type="text" id="adop"></p>
							<p><label for="road">·��</label><input type="text" id="road"></p>
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
								<option value="������λ">������λ</option>
								<option value="���˲��">���˲��</option>
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
						<h3>�޸���·��Ϣ</h3>
						<input type="hidden" name="count" id="rowcount">
						<input type="hidden" name="number" id="number">
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
									<option value="������λ">������λ</option>
									<option value="���˲��">���˲��</option>
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
						<input type="hidden" name="tingziid">
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
								<p><label for="htype">��Ϣ�޸�����</label><input disabled type="text" id="htype"></p>
							</div>
							<div><label for="hadstart">�����ϻ�����</label><input disabled type="date" id="hadstart"></div>
							<div class="clear"></div>
							<div><label for="hadend">���浽������</label><input disabled type="date" id="hadend"></div>
							<div class="clear"></div>
							<div id="digtimepanel"><label for="hdigtime" style="margin-left:20px">�ھ�����</label><input disabled  type="date" id="hdigtime"></div>
							<div class="clear"></div>
							<div id="finishdatepanel"><label for="hfinishdate" style="margin-left:20px">�������</label><input disabled  type="date" id="hfinishdate"></div>
							<div class="clear"></div>
							<div id="lastcaredatepanel"><label for="hlastcaredate">�ϴ���������</label><input disabled type="date" id="hlastcaredate"></div>
							<div style="float:left;margin:0 20px;">
								<label for="hcomments" style="color:#000;float:left">��ע</label>
								<textarea id="hcomments" disabled  rows="4" cols="20" style="margin-left:10px;height:120px;width:280px;resize:none;padding:2px"></textarea>
							</div>
							<div id="img"><img /></div>
						</div>
						<div id="cur">
							<div id="fields">
								<p><label for="area">����</label><input disabled type="text" id="area"></p>
								<p><label for="stopnum">վ����</label><input disabled type="text" id="stopnum"></p>
								<p><label for="picnumber">������</label><input disabled type="text" id="picnumber"></p>
								<p><label for="entitynum">��ʩ���</label><input disabled type="text" id="entitynum"></p>
								<p><label for="nanhuitingtype">��ʩ�ͺ�</label><input disabled  type="text" id="nanhuitingtype"></p>
								<p><label for="adop">���ͻ�/��������</label><input disabled type="text" id="adop"></p>
								<p><label for="raod">·��</label><input disabled type="text" id="road"></p>
						</div>
						<div id="fields2">
							<p><label for="stop">վ��</label><input disabled type="text" id="stop"></p>
							<p><label for="address">��ַ</label><input disabled type="text" id="address"></p>
							<p><label for="direction">����</label><input disabled type="text" id="direction"></p>
							<p><label for="line">��·</label><input disabled type="text" id="line"></p>
							<p><label for="finalstop">��������</label><input disabled type="text" id="finalstop"></p>
							<p><label for="nextstop">��·��һվ</label><input disabled type="text" id="nextstop"></p>
							<p><label for="type">��Ϣ�޸�����</label><input disabled type="text" id="type"></p>
						</div>
						<div><label for="adstart">�����ϻ�����</label><input disabled type="date" id="adstart"></div>
						<div class="clear"></div>
						<div><label for="adend">���浽������</label><input disabled type="date" id="adend"></div>
						<div class="clear"></div>
						<div id="digtimepanel"><label for="digtime" style="margin-left:20px">�ھ�����</label><input disabled  type="date" id="digtime"></div>
						<div class="clear"></div>
						<div id="finishdatepanel"><label for="finishdate" style="margin-left:20px">�������</label><input disabled  type="date" id="finishdate"></div>
						<div class="clear"></div>
						<div id="lastcaredatepanel"><label for="lastcaredate">�ϴ���������</label><input disabled type="date" id="lastcaredate"></div>
						<div style="float:left;margin:0 20px;">
							<label for="comments" style="color:#000;float:left">��ע</label>
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
				<div class="simple_overlay_line" id="mies7">
				
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
				<div class="ligan-search-grid">
					<fieldset  class="nanhuiting-search-fieldset">
						<legend>������ͤ��������</legend>
						<div class="input" id="line"><label for="line-criteria">��·</label><input id="line-criteria" type="text" name="line" class="criteria"></div>
						<div class="input" id="area"><label for="line-area">����</label><input id="line-area" type="text" name="area" class="area"></div>
						<div class="input" id="picnumber"><label for="line-number">������</label><input id="line-number" type="text" name="picnumber" class="number"></div>
						<div class="input" id="stopnum"><label for="line-stopnum">վ����</label><input id="line-stopnum" type="text" name="stopnum" class="stopnum"></div>
						<div class="input" id="entitynum"><label for="line-entitynum">��ʩ���</label><input id="line-entitynum" type="text" name="entitynum" class="entitynum"></div>
						<div class="input" id="nanhuitingtype"><label for="line-nanhuitingtype">��ʩ�ͺ�</label>
							<select id="line-nanhuitingtype">
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
						<div class="input" id="digdate"><label for="line-digdate">�ھ�����</label><input id="line-digdate" type="date" name="digdate" class="digdate"></div>
						<div class="input" id="finalstop"><label for="line-finalstop">��������</label><input id="line-finalstop" type="text" name="finalstop" class="finalstop"></div>
						<div class="input" id="nextstop"><label for="line-nextstop">��·��һվ</label><input id="line-nextstop" type="text" name="nextstop" class="nextstop"></div>
						<div class="input" id="finishdate"><label for="line-finishdate">�������</label><input id="line-finishdate" type="date" name="finishdate" class="finishdate"></div>
						<div class="input" id="lastcaredate"><label for="line-lastcaredate">�ϴ���������</label><input id="line-lastcaredate" type="date" name="lastcaredate" class="lastcaredate"></div>
						<div class="input" id="comments"><label for="line-comments">��ע</label><input id="line-comments" type="text" name="comments" class="comments"></div>
						<div class="submit">��ѯ���ͤ</div>
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