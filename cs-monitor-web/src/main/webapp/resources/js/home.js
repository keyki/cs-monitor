$(document).ready(

function() {

	$('#cs_banner').parallax("50%", -0.3);
	$('.dropdown-toggle').dropdownHover();
	
	jQuery.fn.center = function () {
	    this.css("position","absolute");
	    this.css("top", Math.max(0, (($(window).height() - $(this).outerHeight()) / 2) + 
	                                                $(window).scrollTop()) + "px");
	    this.css("left", Math.max(0, (($(window).width() - $(this).outerWidth()) / 2) + 
	                                                $(window).scrollLeft()) + "px");
	    return this;
	};
	$(".center").center();
	
	
	 $('#slideleft').click(function() {
		    var $lefty = $("#connection-div");
		    if(parseInt($lefty.css('left'),10)<0){
			    $lefty.animate({
			      left: parseInt($lefty.css('left'),10) == GetWidth()*0.03 ? -$lefty.outerWidth() : GetWidth()*0.03 
			    });
			    $('#slideleft>i').removeClass('icon-chevron-right').addClass('icon-chevron-left');
			   
			    if(GetWidth()*0.2>240){
			    	$('#connectionform').width(GetWidth()*0.2);
			    }else{
			    	 $('#connectionform').width(240);
			    }
		   }else if(parseInt($lefty.css('left'),10)>0){
			    $lefty.animate({
			      left: parseInt($lefty.css('left'),-10) == -GetWidth()*0.5 ? $lefty.outerWidth() :-GetWidth()*0.5
			   });
			   $('#slideleft>i').removeClass('icon-chevron-left').addClass('icon-chevron-right');
		   }
	 });
	 
	 
	 
	 function GetWidth(){
		 return $(document).width();
	  }
	
});

