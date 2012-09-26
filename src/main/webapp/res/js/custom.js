// JavaScript Document

/* ==================================================================== */
/* ============================ Javascript Code ======================= */
/* ==================================================================== */




/* ==================================================================== */
/* ================= Things need to be ready at first ================= */
/* ==================================================================== */

jQuery(document).ready(function(){
	
	// Check the effect type 
	
	if($("#image_invert").val()=="true")
	{
		 if($(".portfolio").length>0)  
	     $(".portfolio").find("img").css("visibility","hidden");
	}
	
	
	// remove unecessart p tags generated from autop
	
	$(".content").find("p:not(.separator)").each(function(){
	  if(jQuery.trim($(this).html())=="")
		$(this).remove();
	});
	
});	


/* ==================================================================== */
/* ======================== Main Javascript Code ====================== */
/* ==================================================================== */

jQuery(function($){


/* ==================================================================== */
/* ========================== Variable Declaration ==================== */
/* ==================================================================== */
	
var obj,flickr_limit = 5,temp,temp_parent,taxonomy_parent = $(".portfolio-taxonomy li:first")
    ,portfolio = $(".portfolio li")
    , parent,arr,src,block,index=0
    , counter = 0
    , current_cthumb = $(".custom-gallery-thumbs .thumbnails a").first().addClass('iactive')	
    , i =0,sidebar = $(".sidebar"),menu = $("#menu"), wall_height,
      pre_values =  [ 
			    
				[ -15 , -15 ],
				[ 568 , -14],
				[ 568 , 143],
				[ -15 , 323],
				
				[ 233 , 323],
				[ 488 , 323],
				[ -15 , 593],
				[ -15 , 747],
				[ 422 , 747]
			
			];

/* ==================================================================== */
/* ========================== Style Switcher ========================== */
/* ==================================================================== */
var url = $("#theme_url").val(),header_class='te',footer_class='te';
var header = $(".slider-wrapper ,  .slider-wrapper-full , .slider-wrapper-shade , .blurb-wrapper");
var footer = $("#footer");
var switcher = $("#style-switcher");
$("#header_texture").change(function(){
	
	header.removeClass(header_class);
	header_class = $(this).val();
	
	header.addClass($(this).val());
	
	
	
	});

$("#footer_texture").change(function(){
	
	footer.removeClass(footer_class);
	footer_class = $(this).val();
	
	footer.addClass($(this).val());
	
	
	
	});

 $('.colorpickerField1').ColorPicker({

	onSubmit: function(hsb, hex, rgb, el) {

		$(el).val(hex);

		$(el).ColorPickerHide();

	},

	onBeforeShow: function () {
       temp = this;
		$(this).ColorPickerSetColor(this.value);

	},
	
	onShow: function (colpkr) {
		$(colpkr).fadeIn(500);
		return false;
	},
	onHide: function (colpkr) {
		$(colpkr).fadeOut(300);
		return false;
	},
	onChange: function (hsb, hex, rgb,el) {
		$(temp).val(hex);
		if($(temp).attr("id")=="header_bg_color")
		header.css("background-color","#"+hex);
		else if($(temp).attr("id")=="footer_bg_color")
		footer.css("background-color","#"+hex);
		
		
	}
	

})

.bind('keyup', function(){

	$(this).ColorPickerSetColor(this.value);

});
var trigger_flag = true;	

$("#trigger").click(function(e){
	
	if(trigger_flag==false)
	{
		switcher.animate({ left:-(switcher.width()+35) },300);
		trigger_flag = true;
	}
	else
	{
		switcher.animate({ left:0 },300);
		trigger_flag = false;
	}
	
	e.preventDefault();
	});	
/* ==================================================================== */
/* ========================== Dynamic Sidebar Check =================== */
/* ==================================================================== */
  
sidebar.children().removeClass('dynamic-wrap');
if(sidebar.find(".current_page_item").length>0)
{
  var c_menu = sidebar.find(".current_page_item");
  c_menu.prev().find("a").css("border","none");
   
}
  

/* ==================================================================== */
/* ============================ Menu Settings ========================= */
/* ==================================================================== */
  
$("#menu>li>.sub-menu").append("<span class='tooltip'></span>");
menu.children("li").each(function(){
	
    if(!$(this).hasClass("rel"))
      $(this).find(".tooltip").css("left",$(this).position().left+$(this).width()/2);

});

menu.find("li").hover(function(){
   $(this).children('.sub-menu').hide().slideDown('normal');
  },function(){
  $(this).children('.sub-menu').stop(true,true).hide(); 
  });	

menu.children("li").each(function(){
		
		if($(this).children().hasClass("sub-menu"))
		$(this).addClass("showdropdown");
		
		});
		
/* ==================================================================== */
/* ============================ Portfolio  Stuff ====================== */
/* ==================================================================== */

if($(".portfolio-wall-column").length>0)
{
   i =0;
   wall_height = 0;
   
  $(".portfolio-wall-column>ul>li").each(function(){
	  temp = pre_values[i];
	  
	  $(this).css({ left:temp[0] , top:temp[1]   });
	
	  i++;
	  }); 
		wall_height = 1010;
	$(".portfolio-wall-column>ul").height(wall_height);  
}


	$(".portfolio-taxonomy li a").click(function(e){
		taxonomy_parent.removeClass("active");
		taxonomy_parent = $(this).parent();
		taxonomy_parent.addClass("active");
		
		var query = $(this).html();
		var flag= false;
		
		if(jQuery.trim(query)=="All")
		{
			portfolio.fadeIn('normal');	
			e.preventDefault();
			return;
		}
		portfolio.hide();
		portfolio.each(function(){
			flag= false;
			$(this).removeClass('clearleft');
			$(this).find("small a").each(function(){
				if($(this).html()==query)
				{
					flag = true; 
				}
				});
				
			if(flag==false)
			$(this).fadeOut('fast');
			else
			$(this).fadeIn('normal');	
			
			});
		
		e.preventDefault();
		});
			   
$(window).load(function(){
 
   if($(".portfolio").length>0)  
    create_effect($(".portfolio"));
});
	  
/* ==================================================================== */
/* ============================ Slider Stuff ========================== */
/* ==================================================================== */

var blog_scrollable = $(".scrollable-posts-wrapper").scrollable({ api:true , vertical:true  });
var test_scrollable = $(".testimonials").scrollable({ api:true , vertical:true  });
    
$(".scrollable-next").click(function(e){ test_scrollable.next(); e.preventDefault(); });
$(".scrollable-prev").click(function(e){ test_scrollable.prev(); e.preventDefault(); });
$(".scrollable-posts-next").click(function(e){ blog_scrollable.next(); e.preventDefault(); });
$(".scrollable-posts-prev").click(function(e){ blog_scrollable.prev(); e.preventDefault(); });
  

/* ==================================================================== */
/* ========================== Home page Stuff ========================= */
/* ==================================================================== */


$(".homepage-wrap").each(function(){
   if(i==4)
   i=0;
 
   if(i==3)
   $(this).addClass('clearleft').css("background","none");
   i++;
});


/* ==================================================================== */
/* =========================== Footer Stuff =========================== */
/* ==================================================================== */

 $(".footer-wrap ul").each(function(){
	  $(this).find("li").last().css("background","none");
  });
  
   $("#footer-menu li:first a").css("border-left","none");
   $(".footer-notes li:last").css({ "border-bottom":"none" , paddingBottom:0 }); 

/* ==================================================================== */
/* ============================ Misc Stuff ============================ */
/* ==================================================================== */


// contact input settings
  
$("#qemail , #qmsg ,#qname").focusin(function(){ $(this).val('');});

// lightbox initialization 
  
$(".lightbox").prettyPhoto({animationSpeed:'slow'});

// Tabs call

$( ".tabs" ).tabs({ fx: { opacity: 'toggle' }});
$(".ui-tabs .ui-tabs-nav li:first").css("borderLeft","none");

if($("#flickr-images").length>0)
{
		var temp,i,flickr_limit = $("#flickr-nos").val();
var fid =  $("#flickr-id").val();
	i =0;
	$.getJSON("http://api.flickr.com/services/feeds/photos_public.gne?id="+fid+"&lang=en-us&format=json&jsoncallback=?", function(data){
		
$.each(data.items, function(i,item){
		
		if(i>=flickr_limit) 
		return;
		$("<img/>").css({  width:70,height:56 }).attr({
			"src": item.media.m,
			"alt" : item.media.m
			}).appendTo("#flickr-images").wrap("<a href='" + item.link + "'></a>");
			
		
		i++;
	});
	
  });
  
}
	
	
		
if($(".scroller-posts").length>0)
  {
     var showcase = $(".scroller-posts").scrollable({api:true});
     $(".showcase-next").click(function(e){ showcase.next(); e.preventDefault(); });
	 $(".showcase-prev").click(function(e){ showcase.prev(); e.preventDefault(); });
  }
  
if($(".single-scroller-posts").length>0)
  {
	  var showcase = $(".single-scroller-posts").scrollable({api:true});
	   $(".single-showcase-next").click(function(e){ showcase.next(); e.preventDefault(); });
	 $(".single-showcase-prev").click(function(e){ showcase.prev(); e.preventDefault(); });
  }
	

/* ==================================================================== */
/* =========================== Gallery Stuff ========================== */
/* ==================================================================== */  

var gstage = $(".image-stage");	
var ptitle = $(".title").find("a").html();     
  
$(".gallery-menus1 ul li").each(function(){
	  temp = $(this).find("a");
	  
	  if(jQuery.trim(temp.html())==jQuery.trim(ptitle))
	  temp.addClass("active");
	  
});
	   
$(".custom-prev").click(function(e){
	   $(".custom-gallery-thumbs .thumbnails a").removeClass("iactive");
	   
	   if( current_cthumb.prev().length == 0 )
	   current_cthumb =    $(".custom-gallery-thumbs .thumbnails a").last();
	   else
	   current_cthumb =  current_cthumb.prev();
	   
	  
	  
	 setCustomGallery(current_cthumb);
	  
e.preventDefault();  });
	
$(".custom-next").click(function(e){
	   $(".custom-gallery-thumbs .thumbnails a").removeClass("iactive");
	   
	   if( current_cthumb.next().length == 0 )
	   current_cthumb =    $(".custom-gallery-thumbs .thumbnails a").first();
	   else
	   current_cthumb =  current_cthumb.next();
	   
	  
	  
	 setCustomGallery(current_cthumb);
	  
e.preventDefault();  });
	    
  
$(".custom-gallery-thumbs .thumbnails a").click(function(e){
	  $(".custom-gallery-thumbs .thumbnails a").removeClass("iactive");
	  current_cthumb = $(this);
	 setCustomGallery(current_cthumb);
	  e.preventDefault();
});
   
function setCustomGallery(element)
{
	  gstage.find("span").hide();
	  element.addClass("iactive");
	  temp = element.attr("href");
	  src = element.children("img").attr("alt");
	 
	  var title = element.children("img").attr("title");
	 
	 
	  gstage.children("a").attr("href",element.attr("alt"));
	  gstage.children("a").children("img").attr("title",title);
	   
	  
	  gstage.find("img").fadeOut("fast",function(){
	  $(this).attr("src",temp);
	  
	  $(this).load(function(){ $(this).fadeIn("normal",function(){ 
	  gstage.find("span").html("<h2 class='custom-font'>"+$(this).attr("title")+"</h2>"+src).fadeIn("normal");  });   });
	  });
   
}

  
  
if($(".blog-container").length>0)
	{
		$(".blog-container .posts-list ul li").last().css("background","none");
	}
	
/* ==================================================================== */
/* =========================== Event Stuff ============================ */
/* ==================================================================== */  

$(".events-list>ul>li:last").css("border","none"); 
$(".posts-list").find("li.separator").last().remove();
$("#calendar table td ").each(function(){ 
	
	if($(this).find('.event-details').length>0)
	$(this).find(".event-wrapper").jScrollPane();
	else
	$(this).find(".event-div").remove();
	
});

 $("#calendar table td").hover(function(){
		
	if($(this).find('.event-details').length>0)
	$(this).find('.event-div').css( "visibility" , "visible").stop(true,true).animate({opacity:1,bottom:-90},500);
	},function(){
	temp = $(this);
	$(this).find('.event-div').stop(true,true).animate({opacity:0,bottom:-70},500,function(){ $(this).css("visibility" ,"hidden") });	
});


/* ==================================================================== */
/* ===================== HTML5 Effects Generator ====================== */
/* ==================================================================== */ 

	
	function create_effect(parent)
	{
		counter = 0;
		parent.find("img").each(function(){
			 temp = $(this);
			 temp_parent = temp.parent();
			 
			 if(temp_parent.parent().hasClass("mainslider"))
			 return;
			 
			 if($("#image_effect").val()!="Disabled"&&!($.browser.msie))
			 {
			   generateEffect(temp,counter,temp.height(),temp.width());
			   	
					temp_parent.parent().hover(function(){
						  
						  if(parent.hasClass("gallery"))
						  {
							  
							  
							  $(this).find('.title').fadeOut('fast');
							  
						  }
							   
						  $(this).find('.icon-panel').hide();
						  
						  if($("#image_invert").val()=="false")
						  {
						  $(this).find('canvas').stop(true,true).fadeIn('normal');
						  $(this).find('.icon-panel').css("z-index",21).stop(true,true).fadeIn('normal');
						  }
						  else	
						  { 
						   $(this).find('canvas').stop(true,true).fadeOut('normal',function(){
							  $(this).find('.icon-panel').stop(true,true).fadeIn('normal');
							  }); 
						  }
						   if(parent.hasClass("gallery"))
						     $(this).find('.link-icon').css("z-index",21).stop(true,true).fadeIn('normal');
						  
						  },function(){ 
						  
						  if(parent.hasClass("gallery"))
						  {
							  
							  
							  $(this).find('.title').fadeIn('fast');
						  }
						  
							  $(this).find('.icon-panel').css("z-index",8).hide();
							   
							    if(parent.hasClass("gallery"))
								 $(this).find('.link-icon').css("z-index",8).hide();
								
						   if($("#image_invert").val()=="false")	  
						  $(this).find('canvas').stop(true,true).fadeOut('normal');
						  else
						   $(this).find('canvas').stop(true,true).fadeIn('normal');
			        });
			 }
			 else
			 {
				 temp.css( "visibility" , "visible");
				// temp.after("<span class='image-icon' href='#'></span><span class='link-icon' href='#'></span>");
				 temp_parent.parent().hover(function(){
						  
						  if(parent.hasClass("gallery"))
							  $(this).find('.title').fadeOut('fast');
					
						    $(this).find('.icon-panel').stop(true,true).fadeIn('normal');
							   if(parent.hasClass("gallery"))
						     $(this).find('.link-icon').css("z-index",21).stop(true,true).fadeIn('normal');
						  },function(){ 
						  
						  if(parent.hasClass("gallery"))
						    if(parent.hasClass("gallery"))
								 $(this).find('.link-icon').css("z-index",8).hide();
						   $(this).parent().find('.title').fadeIn('fast');
						 	  $(this).parent().find('.icon-panel').fadeOut('fast');
						 
			        });
			 }
            
			 
			 counter++;
			 
			 
			});
			
			
	}
	  
	
		
	
/* ==================================================================== */
/* ===================== Contact Form Settings ======================== */
/* ==================================================================== */ 
	
	
	$("#qsubmit").click(function(e){
	 temp = $(this).parent().find(".loader");
	 //temp.fadeIn("slow");
	 var loader = $(this).parent().find(".ajax-loading-icon").fadeIn("slow");
	 
	 $.post( $(this).parent().find("#ajax_contact_path").val(),{ 
	 notify_email : $(this).parent().find("#notify_email").val(),
	 name : $(this).parent().find("#qname").val(),
	 email : $(this).parent().find("#qemail").val(),
	 message : $(this).parent().find("#qmsg").val()
	 
	 },function(data){
		
		
		if(data=="success") {
		loader.fadeOut("slow",function(){
			 temp.addClass('success-box').removeClass('error-box').fadeToggle("slow");
			 setTimeout(function(){ temp.fadeToggle("normal"); },4000);
			 });
			 
		 }
		else
		{
		  loader.fadeOut("slow");
			temp.removeClass('success-box').addClass('error-box').html("<p>Error</p>");
			 temp.fadeToggle("slow");
			 setTimeout(function(){ temp.fadeToggle("normal"); },4000);
			 
		}	 
		
		});
		
		
		e.preventDefault();
		
	});
    
	$(".d_submit").click(function(e){
		
		
		obj = $(this);
		var msg = obj.parents(".dynamic_forms").find(".loader");
		var array = obj.parent().serializeArray();
		var loader = $(this).parents(".dynamic_forms").find(".ajax-loading-icon").fadeIn("slow");
		
		$.post( obj.parent().attr("action"), { key:obj.parent().find(".form_key").val() , values : array , notify_email : obj.parent().find(".notify_email").val() , recaptcha_challenge_field:obj.parent().find("#recaptcha_challenge_field").val()   , recaptcha_response_field:obj.parent().find("#recaptcha_response_field").val()  } , function(data){
			
			if(data=="success")
			{
				loader.fadeOut("slow");
			    msg.addClass('success-box').removeClass('error-box').html("<p> Your Message been sent </p>");
			    msg.fadeIn("slow");
			}
			else
			{
				loader.fadeOut("slow");
				msg.removeClass('success-box').addClass('error-box').html("<p>"+data+"</p>");
				msg.fadeIn("slow");
			}
			
			}  );
		
		e.preventDefault();
		
		});
	
			
			
	});
	
	
	
	
/* ==================================================================== */
/* ===================== HTML5 Effects Generator ====================== */
/* ==================================================================== */ 	
	
	 function generateEffect(image,index,height,width)
	{
		var image_effect=$("#image_effect").val(),
		 im = image,
		 parent = im.parent(),
		 arr = new Array(); i =0;  j =0, 
		 src = im.attr("src");
		
		parent.append("<canvas width='"+width+"' height='"+height+"' id='s"+index+"' />");
		
		
		image = im[0];
						
		var canvas = document.getElementById('s'+index);
		var context = canvas.getContext('2d');
       
	    context.drawImage(image, 0, 0);
        var grayscale,imageData    = context.getImageData(0,0,canvas.width,canvas.height),
		data        = imageData.data;
       
	  if(image_effect=="Greyscale") {
	  
        for(var i = 0,z=data.length;i<z;i++){

            // The values for red, green and blue are consecutive elements
            // in the imageData array. We modify the three of them at once:

             grayscale = data[i  ] * .24 + data[i+1] * .01 + data[i+2] * .1;
             data[i] = grayscale;
             data[++i] = grayscale;
             data[++i] = grayscale;
 			 data[++i] = 255;
			
			}
			
	  }
		else if (image_effect=="Screen") {
		 for(var i = 0,z=data.length;i<z;i++){	
			 data[i] =    255 - ( ( 255 - data[i] ) * ( 255 - data[i] ) ) / 255;
             data[++i] =   255 - ( ( 255 - data[i] ) * ( 255 - data[i] ) ) / 255;
             data[++i] =   255 - ( ( 255 - data[i] ) * ( 255 - data[i] ) ) / 255;
 			 data[++i] = 255;
			}
			
          
		}
		else if (image_effect=="Color Burn") {
		
        for(var i = 0,z=data.length;i<z;i++){
			data[i] =     data[i] <= 0? 0 : Math.max(255 - ((255 -  data[i]) * 255 /  data[i]), 0);
            data[++i] =  data[i] <= 0? 0 : Math.max(255 - ((255 -  data[i]) * 255 /  data[i]), 0);
            data[++i] =   data[i] <= 0? 0 : Math.max(255 - ((255 -  data[i]) * 255 /  data[i]), 0);
 			data[++i] = 255;
			
          }
	         
		}
		else if (image_effect=="Overlay") {
		
        for(var i = 0,z=data.length;i<z;i++){
			  data[i] = data[i] < 128 ? ( 2 * data[i] *  data[i] ) / 255 : 255 - ( 2 * ( 255 - data[i]) * ( 255 -  data[i] ) / 255 );
            data[++i] =  data[i] < 128 ? ( 2 * data[i] *  data[i] ) / 255 : 255 - ( 2 * ( 255 - data[i] ) * ( 255 -  data[i] ) / 255 );
            data[++i] =  data[i] < 128 ? ( 2 * data[i] *  data[i] ) / 255 : 255 - ( 2 * ( 255 - data[i] ) * ( 255 -  data[i] ) / 255 );
 			data[++i] = 255;
			
          }
	         
		}
		else if (image_effect=="Red Channel") {
		
        for(var i = 0,z=data.length;i<z;i++){
			 data[i] = data[i] ;
            data[++i] = data[i-1] ;
            data[++i] = data[i-2] ;
 			data[++i] = 255;
          }
	         
		}
		else if (image_effect=="Green Channel") {
		
        for(var i = 0,z=data.length;i<z;i++){
			  data[i] = data[i+1] ;
            data[++i] = data[i] ;
            data[++i] = data[i-1] ;
 			data[++i] = 255;
			
          }
	         
		}
		else if (image_effect=="Blue Channel") {
		
        for(var i = 0,z=data.length;i<z;i++){
			   data[i] = data[i+2] ;
            data[++i] = data[i+1] ;
            data[++i] = data[i] ;
 			data[++i] = 255;
			
          }
	         
		}
		else if (image_effect=="Green Tone") {
		
        for(var i = 0,z=data.length;i<z;i++){
			 grayscale = data[i  ] * .3 + data[i+1] * .59 + data[i+2] * .11;
             data[i] = Math.min(grayscale,data[i]) ;
            data[++i] = Math.min(grayscale,data[i]) ;
            data[++i] =  Math.min(grayscale,data[i]) ;
 			data[++i] = 255;
          }
	         
		}
		else if (image_effect=="Red Tone") {
		
        for(var i = 0,z=data.length;i<z;i++){
			grayscale = data[i  ] * .3 + data[i+1] * .59 + data[i+2] * .11;
             data[i] = Math.max(grayscale,data[i]);
            data[++i] = grayscale;
            data[++i] = grayscale;
 			data[++i] = 255;
          }
	         
		}
		else if (image_effect=="Blue Tone") {
		
        for(var i = 0,z=data.length;i<z;i++){
			grayscale = data[i  ] * .3 + data[i+1] * .59 + data[i+2] * .11;
             data[i] = Math.min(grayscale,data[i]) ;
            data[++i] = Math.min(grayscale,data[i]) ;
            data[++i] =  Math.max(grayscale,data[i]) ;
 			data[++i] = 255;
          }
	         
		}
		

         // Putting the modified imageData back on the canvas.
        context.putImageData(imageData,0,0,0,0,imageData.width,imageData.height);
		
		 if($("#image_invert").val()=="false")
			$("#s"+index).hide();	
			else
			$("#s"+index).show();	
			
			im.css("visibility","visible");
				
			}