<%@ page contentType="text/html;charset=GBK" language="java"%>
<link href="uploadify/uploadify.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="uploadify/swfobject.js"></script>
<script type="text/javascript" src="uploadify/jquery.uploadify.v2.1.4.js"></script>
<script type="text/javascript" src="res/js/jquery.blockUI.js"></script>
<script type="text/javascript" src="res/js/header.js"></script>
<link rel="stylesheet" href="res/css/style.css">

<div id="top-bar" class="clearfix">
    <div class="container">
	     <a style="outline-style: none;" href="http://wptitans.com/archin" id="logo"><img src="res/css/images/logo.jpg" alt="logo"></a>
       
            
        <ul id="menu" class="menu"><li id="menu-item-13" class="menu-item menu-item-type-post_type menu-item-object-page current-menu-item page_item page-item-4 current_page_item menu-item-13   rel    "><a style="outline-style: none;" href="/shizhonginfo">首页<span> - </span></a> </li>

<li id="menu-item-794" 1="" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-794        showdropdown"><a style="outline-style: none;" href="http://wptitans.com/archin/features/">浏览及查询设备数据</a>
<div style="display: none;" class="sub-menu clearfix   ">
	<div id="menu-item-261" class="menu-item menu-item-type-custom menu-item-object-custom menu-item-261       ">
	<ul class="sub-menu clearfix   ">
		<li id="ligansearch" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-271       "><a style="outline-style: none;" href="javascript:void(0)">搜索设备信息<span>  </span></a></li>
		<li id="tingzinhsearch" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-271       "><a style="outline-style: none;" href="javascript:void(0)">搜索广告亭信息<span>  </span></a></li>
		<li id="yangzhaodiansearch" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-271       "><a style="outline-style: none;" href="javascript:void(0)">搜索扬招点信息<span>  </span></a></li>
		<li id="liganview" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-272       "><a style="outline-style: none;" href="javascript:void(0)">浏览立杆设备信息<span>  </span></a></li>
		<li id="tingziview" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-269       "><a style="outline-style: none;" href="javascript:void(0)">浏览亭子信息<span>  </span></a></li>
		<li id="tingzinhview" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-268       "><a style="outline-style: none;" href="javascript:void(0)">浏览广告亭信息<span>  </span></a></li>
		<li id="yangzhaodianview" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-268       "><a style="outline-style: none;" href="javascript:void(0)">浏览扬招点信息<span>  </span></a></li>
	</ul>
</div>
<span style="left: 548px;" class="tooltip"></span></div>
</li>
<li id="menu-item-104" class="menu-item menu-item-type-post_type menu-item-object-page menu-item-104   rel     showdropdown"><a style="outline-style: none;" href="http://wptitans.com/archin/portfolios/">导入设备数据信息</a> 
<ul class="sub-menu clearfix   " style="top:22px;">
	<li id="import"><a style="outline-style: none;" href="javascript:void(0)">导入立杆数据<span>  </span></a> </li>
	<li id="importline"><a style="outline-style: none;" href="javascript:void(0)">导入上行线路信息<span>  </span></a> </li>
	<li id="importdownline"><a style="outline-style: none;" href="javascript:void(0)">导入下行线路信息<span>  </span></a> </li>
	<li id="importtingzi"><a style="outline-style: none;" href="javascript:void(0)">导入亭子信息<span>  </span></a> </li>
	<li id="importtingzinh"><a style="outline-style: none;" href="javascript:void(0)">导入广告亭信息<span>  </span></a> </li>
	<li id="importyangzhaodian"><a style="outline-style: none;" href="javascript:void(0)">导入扬招点信息<span>  </span></a> </li>
</ul>
</li>
</ul>     
       
    </div>
 </div>
 <form id="redirect">
 	<input type="hidden" name="username" id="username" value="<%=request.getAttribute("username") %>">
 	<input type="hidden" name="target" id="target" >
 </form>