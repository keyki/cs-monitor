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
	  
	var scroller=new Scroller();
	$('#slideleft').click(function(){
		scroller.decider();
	});
	  
	function Scroller(){
		this.id="#conn-div";
		this.modifyClass="left";
		this.modifyIconRightClass='icon-chevron-right';
		this.modifyIconLeftClass='icon-chevron-left';
		this.animateSpeed="1000";
		this.iconSelector="#slideleft>i";
		this.nextDiv="#content-div";
		this.formSelector='#connectionform';
		this.offset="offset3";
		this.pressedSize="70%";
		this.originalSize="90%";	
	 };
		
	 Scroller.prototype.decider=function(){
		 var $lefty = $(this.id);
		 if(parseInt($lefty.css(this.modifyClass),10)<0){
			 this.pullLeft();
		 }else if(parseInt($lefty.css(this.modifyClass),10)>0){
			 this.pullRight();
		 }
	 };
		
	 Scroller.prototype.pullLeft=function(){
			var $lefty = $(this.id);	
			 $lefty.animate({left: parseInt($lefty.css(this.modifyClass),10) == this.GetWidth()*0.03 ? -$lefty.outerWidth() : this.GetWidth()*0.03},this.animateSpeed);
			    $(this.iconSelector).removeClass(this.modifyIconRightClass).addClass(this.modifyIconLeftClass);
			    if(this.GetWidth()*0.2>240){
			    	$(this.formSelector).width('100%');
			    }else{
			    	 $(this.formSelector).width(240);
			    }
			    $(this.nextDiv).width(this.pressedSize);
		    	$(this.nextDiv).addClass(this.offset);
	 };
		
	 Scroller.prototype.GetWidth=function(){
			 return $(document).width();
	 };
		
	 Scroller.prototype.pullRight=function(){
		   var $lefty = $(this.id);	
		   $lefty.animate({
			      left: parseInt($lefty.css(this.modifyClass),-10) == -this.GetWidth()*0.5 ? $lefty.outerWidth() :-this.GetWidth()*0.5 },this.animateSpeed);
			   $(this.iconSelector).removeClass(this.modifyIconLeftClass).addClass(this.modifyIconRightClass);
			   $(this.nextDiv).width(this.originalSize);
			   $(this.nextDiv).removeClass(this.offset);
	 };
});

