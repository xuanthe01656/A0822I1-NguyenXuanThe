
//<![CDATA[

    window.addEventListener('load', function() {

        $(".main-menu >li>a[href='" + location.pathname + "']").parent().addClass("active").each(function() {

            $(".main-menu >li>a[href='" + $(this).attr("href") + "']").parent().addClass("active")

        });
    });

    //]]>
    //<![CDATA[
	
	/*
	window.addEventListener('load', function() {
         $(".sb_menu >li>a[href='" + location.pathname + "']").parent().addClass("active-menu").each(function() {

            $(".sb_menu >li>a[href='" + $(this).attr("href") + "']").parent().addClass("active-menu")

        });
    });
	$(document).on('click', '.sb_menu li', function(){  
  $(this).addClass('active-menu').siblings().removeClass('active-menu')  
}) 
	$(document).ready(function() {
   	$(".sb_menu").on('click', 'li', function () {
    $(".sb_menu li.active").removeClass("active-menu");
    // adding classname 'active' to current click li 
    $(this).addClass("active-menu");
});
});*/
    //]]>
$(function() {

  // Slider
  $('#coin-slider').coinslider({width:935,height:307,opacity:1});

  // Radius Box
  $('p.infopost').css({"border-radius":"6px", "-moz-border-radius":"6px", "-webkit-border-radius":"6px"});
  $('.searchform, .content .sidebar .gadget').css({"border-radius":"8px", "-moz-border-radius":"8px", "-webkit-border-radius":"8px"});
  $('.content .sidebar .gadget, .fbg_resize').css({"border-radius":"12px", "-moz-border-radius":"12px", "-webkit-border-radius":"12px"});
  $('.content p.pages span, .content p.pages a').css({"border-radius":"16px", "-moz-border-radius":"16px", "-webkit-border-radius":"16px"});
  $('.menu_nav').css({"border-bottom-left-radius":"16px", "border-bottom-right-radius":"16px", "-moz-border-radius-bottomleft":"16px", "-moz-border-radius-bottomright":"16px", "-webkit-border-bottom-left-radius":"16px", "-webkit-border-bottom-right-radius":"16px"});

});	
$(document).ready(function() {
		$(".form").validate({
		ignore: [],
		debug: false,
		rules:{
			name: {
				required: true,
				minlength: 1,
				maxlength: 32,
			},
			email: {
				required: true,
				email:  true,
				minlength: 2,
			},
			message: {
				required: true,
				},
					
		},
		messages:{
			name: {
				required: "Hãy nhập vào họ tên.",
				minlength: "Hãy nhập vào nhiều hơn 1 ký tự và ít hơn 32 ký tự.",
				maxlength: "Hãy nhập vào ít hơn 32 ký tự.",
			},
			email: {
				required: "Hãy nhập vào email",
				email: "Hãy nhập đúng định dạng.",
				minlength: "Hãy nhập vào nhiều hơn 2 ký tự.",
			},
			message: {
				required: "Nhập vào chi tiết",
			},
		},
	});
	
});
CKEDITOR.config.toolbar = [
    ['Bold','Italic','Underline','myCombo']
] ;
//deal with copying the ckeditor text into the actual textarea
CKEDITOR.on('instanceReady', function () {
    $.each(CKEDITOR.instances, function (instance) {
        CKEDITOR.instances[instance].document.on("keyup", CK_jQ);
        CKEDITOR.instances[instance].document.on("paste", CK_jQ);
        CKEDITOR.instances[instance].document.on("keypress", CK_jQ);
        CKEDITOR.instances[instance].document.on("blur", CK_jQ);
        CKEDITOR.instances[instance].document.on("change", CK_jQ);
    });
});

function CK_jQ() {
    for (instance in CKEDITOR.instances) {
        CKEDITOR.instances[instance].updateElement();
    }
}
CKFinder.popup( {
    language: 'de'
} );