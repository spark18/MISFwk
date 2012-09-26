<%@ page contentType="text/html;charset=GBK" language="java"%>
<html>
<head>
<script type="text/javascript" src="res/js/jquery-1.7.min.js"></script>
<script type="text/javascript"
	src="res/js/jquery-ui-1.8.7.custom.min.js"></script>
<script type="text/javascript" src="res/js/jquery.tools.min.js"></script>
<script type="text/javascript" src="res/js/flexigrid.pack.js"></script>
<script type="text/javascript" src="res/js/index.js"></script>
<script type="text/javascript" src="res/js/custom.js"></script>
<script type="text/javascript" src="res/js/jquery.mouse.js"></script>
<script type="text/javascript" src="res/js/jquery.popeye-2.0.4.min.js"></script>
<script type="text/javascript" src="res/js/jquery.prettyPhoto.js"></script>
<script type="text/javascript" src="res/js/jquery.quartz.3.0.js"></script>
<script type="text/javascript" src="res/js/shortcodes.js"></script>
<script type="text/javascript" src="res/js/colorpicker.js"></script>
<script type="text/javascript" src="res/js/jquery.md5.js"></script>
<link rel="stylesheet" href="res/css/flexigrid.pack.css">
<link rel="stylesheet" href="res/css/index.css">
<link rel="stylesheet" href="res/css/colorpicker.css">
<link rel="stylesheet" href="res/css/hades.css">
<link rel="stylesheet" href="res/css/jquery.popeye.style.css">
<link rel="stylesheet" href="res/css/prettyPhoto.css">
<link rel="stylesheet" href="res/css/quartz.css">
<link rel="stylesheet" href="res/css/shortcodes.css">
<link rel="stylesheet" href="res/css/style.css">
</head>
<body>
	<div id="style-switcher">

		<input id="theme_url"
			value="http://wptitans.com/archin/wp-content/themes/archin"
			type="hidden">
		<div class="input">
			<label for="header_texture">Header Texture</label> <select
				name="header_texture" id="header_texture">
				<option value="diagonal-texture">Diagonal texture</option>
				<option value="big-doodle-texture">Big doodle texture</option>
				<option value="bokeh-texture">Bokeh texture</option>
				<option value="bow_texture">Bow texture</option>
				<option value="checker-texture">Checker texture</option>
				<option value="cloud-texture">Cloud texture</option>
				<option value="doodle-texture">Doodle texture</option>
				<option value="flower-texture">Flower texture</option>
				<option value="gradient-light">Gradient light</option>
				<option value="industrial-texture">Industrial texture</option>
				<option value="paint-textures">Paint textures</option>
				<option value="smoke-texture">Smoke texture</option>
				<option value="wood-texture">Wood texture</option>
			</select>
		</div>

		<div class="input clearfix">
			<label for="header_bg_color">Header bg color</label> <input
				class="colorpickerField1" id="header_bg_color" value="000000"
				type="text"><span class="circ"></span>
		</div>



		<div class="input topborder">
			<label for="footer_texture">Footer Texture</label> <select
				name="footer_texture" id="footer_texture">
				<option value="diagonal-texture">Diagonal texture</option>
				<option value="big-doodle-texture">Big doodle texture</option>
				<option value="bokeh-texture">Bokeh texture</option>
				<option value="bow_texture">Bow texture</option>
				<option value="checker-texture">Checker texture</option>
				<option value="cloud-texture">Cloud texture</option>
				<option value="doodle-texture">Doodle texture</option>
				<option value="flower-texture">Flower texture</option>
				<option value="gradient-light">Gradient light</option>
				<option value="industrial-texture">Industrial texture</option>
				<option value="paint-textures">Paint textures</option>
				<option value="smoke-texture">Smoke texture</option>
				<option value="wood-texture">Wood texture</option>
			</select>
		</div>

		<div class="input clearfix">
			<label for="header_bg_color">Footer bg color</label> <input
				class="colorpickerField1" id="footer_bg_color" value="000000"
				type="text"><span class="circ"></span>
		</div>


	</div>

	<jsp:include page="header.jsp"></jsp:include>
	<div class="slider-wrapper">



		<div class="feature-slider container clearfix">
			<!-- Start of featured slider -->

			<ul id="scroll-nav" class="clearfix">
				<li class="active"><a style="outline-style: none;" href="#"></a></li>
				<li class=""><a style="outline-style: none;" href="#"></a></li>
				<li class=""><a style="outline-style: none;" href="#"></a></li>
				<li class=""><a style="outline-style: none;" href="#"></a></li>
				<li class=""><a style="outline-style: none;" href="#"></a></li>
			</ul>

			<div class=" description-scroller">
				<ul style="top: -247.2px;" class="clearfix">
					<li class="clearfix cloned">
						<!-- Start of featured slide -->


						<div class="description">


							<h2 class="custom-font">------------------------------</h2>
							<p>------------------------------
								------------------------------ ------------------------------</p>
							<a style="outline-style: none;" href="javascript:void(0)"
								class="more">read more</a>
						</div>
					</li>

					<li class="clearfix">
						<!-- Start of featured slide -->
						<div class="description">
							<h2 class="custom-font">上海市中停车设备有限公司信息管理系统</h2>
							<a
								style="float:left;margin-left:20px;padding:0 10px 0;outline-style: none; font-weight: bold; color: #D14836; font-size: 12px"
								title="进入系统" href="javascript:void(0)" class="enter">进入系统</a>
						</div>
					</li>
					<!-- End of featured slide -->

				</ul>
			</div>

			<div class="image-scroller-wrapper">
				<div class="image-scroller">
					<div>
						<ul style="top: 0px;" class="clearfix">

							<li class="clearfix">
								<!-- Start of featured slide --> <a style="outline-style: none;"
								href="http://wptitans.com/archin/"> <img
									src="res/css/images/slide.jpg" alt="sider image"></a>
							</li>
							<li>
								<div class="login-panel">
									<div class="sign-in">
										<div class="signin-box">
											<div class="h2"><h2>登录</h2>
												<div style="clear:both"></div>
											</div>
											
												<label>
													<strong class="email-label">用户名</strong> 
													<input type="text" value="" id="email" name="Email">
												</label> 
												<label> 
													<strong class="passwd-label">密码</strong>
													<input type="password" id="Passwd" name="Passwd">
												</label>
												<label>
													<input type="submit" value="确    定" id="signin" name="signIn" class="g-button g-button-submit">
												</label> 
										</div>
									</div>
								</div>
							</li>
						</ul>
						<div id="drawer">用户名或者密码错误，请您核实</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="body-wrapper "></div>
	<div id="footer" class="clearfix footer-column4">
		<div id="footer-menu" class="clearfix">
			<div class="container">
				<ul id="menu-footer-menu" class="menu">
					<li id="menu-item-1010"><a style="outline-style: none;"
						href="javascript:void(0)">上海市中停车设备有限公司信息管理系统</a></li>

				</ul>
				<p class="footer-text"></p>
			</div>
		</div>
	</div>

</body>
</html>
