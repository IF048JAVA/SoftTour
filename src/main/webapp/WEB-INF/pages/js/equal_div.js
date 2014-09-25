// JavaScript Document

jQuery.fn.equalHeights = function() { 
 var currentTallest = 0; 
 jQuery(this).each(function(){ 
  if (jQuery(this).height() > currentTallest) { currentTallest = jQuery(this).height(); } 
 }); 
 jQuery(this).css({'min-height': currentTallest});  
 return this; 
}; 


jQuery(document).ready(function() { 
 jQuery("#left_div, #right_div").equalHeights(); 
}); 

