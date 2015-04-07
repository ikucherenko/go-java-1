$(document).ready(function() {
	$.get("categories", function(data) {
		for (var id in data) {
			var category = data[id];
			categoriesListener(category);
		}
	});
	
	var categoriesListener = function(category) {
		
		var id = category.id;
		var cssId = 'category' + id;
		
		$("#categories").append('<a id="' + cssId + '" href="#categories/' + id + '">' + category.name + '</a> ');
		
		$("#" + cssId).click(function(){
			$.get("categories/" + id, function(data){
				$("#projects").html('');
				for (var id in data) {
					var project = data[id];
					$("#projects").append('<h3>' + project.name + '</h3>');
				}
			});
		});
	}
	
});