$(document).ready(

function() {

	$('#cs_banner').parallax("50%", -0.3);
	$('.dropdown-toggle').dropdownHover();
	$(".center").center();
	jQuery.fn.center = function () {
	    this.css("position","absolute");
	    this.css("top", Math.max(0, (($(window).height() - $(this).outerHeight()) / 2) + 
	                                                $(window).scrollTop()) + "px");
	    this.css("left", Math.max(0, (($(window).width() - $(this).outerWidth()) / 2) + 
	                                                $(window).scrollLeft()) + "px");
	    return this;
	};
	
	
	function Scroller()={
			
	}
	
	
	
	$('#slideleft').click(function() {
		    var $lefty = $("#conn-div");
		    if(parseInt($lefty.css('left'),10)<0){
			    $lefty.animate({"left": parseInt($lefty.css('left'),10) == GetWidth()*0.03 ? -$lefty.outerWidth() : GetWidth()*0.03},"1000");
			    $('#slideleft>i').removeClass('icon-chevron-right').addClass('icon-chevron-left');
			   
			    if(GetWidth()*0.2>240){
			    	$('#connectionform').width('100%');
			    	$('#content-div').width('70%');
			    	$('#content-div').addClass('offset3');
			    }else{
			    	 $('#connectionform').width(240);
			    	 $('#content-div').width('70%');
			    	 $('#content-div').addClass('offset3');
			    }
		   }else if(parseInt($lefty.css('left'),10)>0){
			    $lefty.animate({
			      left: parseInt($lefty.css('left'),-10) == -GetWidth()*0.5 ? $lefty.outerWidth() :-GetWidth()*0.5
			   });
			   $('#slideleft>i').removeClass('icon-chevron-left').addClass('icon-chevron-right');
			   $('#content-div').width('90%');
			   $('#content-div').removeClass('offset3');
		   }
	 });
	 
	 
	 
	 function GetWidth(){
		 return $(document).width();
	  }
	
});

