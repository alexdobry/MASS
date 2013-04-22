$(function(){
	$("#category").change(function(){
		$("#subCategory").empty();
		$("#dealer").empty();
		changedCategory();

	});
	$("#subCategory").change(function(){
		$("#criteria").empty();
		$('#brand').empty();
		changedSubCategory();

	});
	function changedCategory(){
		jQuery.getJSON("/subcategories?category="+$("#category").val(),function(data){
			jQuery.each(data,function(key, val){
				$("#subCategory").append("<option value=\""+val.name+"\">"+val.name+"</option>");
			});
		});
		
		jQuery.getJSON("/dealers?category="+$("#category").val(),function(data){
			jQuery.each(data,function(key, val){
				$("#dealer").append("<option value=\""+val.name+"\">"+val.name+"</option>");
			});
		});
	};
	function changedSubCategory(){
		jQuery.getJSON("/criteria?subCategory="+$("#subCategory").val(),function(data){
			var i = 0;
			jQuery.each(data,function(key, val){
				i += 1;
				$("#criteria").append("<div class='control-group'>");
 				$("#criteria").append("<label for='criteria"+i+"' class='control-label'>"+val.name+": </label> ");
 				$("#criteria").append("<div class='controls'>");
				$("#criteria").append("<input type='text' id='criteria"+i+"' name='criteria"+i+"'>");
				$("#criteria").append("</div>");
				$("#criteria").append("</div>");
			});
		});
		
		jQuery.getJSON("/brands?subCategory="+$("#subCategory").val(),function(data){
			jQuery.each(data,function(key, val){
				$("#brand").append("<option value=\""+val.name+"\">"+val.name+"</option>");
			});
		});
	};
});