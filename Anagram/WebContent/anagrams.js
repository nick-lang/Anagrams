function load(){$(document).ready(function(){

		$('#submit').click(function(e){

			var myReq = $.ajax({
				type: "GET",
				url: "./api/anagram/" + $('#word').val(),
				dataType: "json"
			});

			var countReq = $.ajax({
				type: "GET",
				url: "./api/anagram/" + $('#word').val() + "/count",
				dataType: "json"
			});

			countReq.done(function(data){
				var totalReq = $.ajax({
					type: "GET",
					url: "./api/anagram/total",
					dataType: "json"
				});

				totalReq.done(function(total){
					$('div').remove();
					var $div = $('<div>');
					$div.html("'" + $('#word').val() + "'" + " has been searched for "
					+ data + " times out of " + total + " searches. That's "
					+ ((data/total)*100).toFixed(2) + "%");
					$('form').append($div);
					console.log(data);
				});

			});

			myReq.done(function(data){
				console.log(data);
				$('ul').remove();

				var $ul = $('<ul>');
				$.each(data, function(){
					var $li = $('<li>');
					$li.html(this);
					$ul.append($li);
				});
				$('body').append($ul);
			});

		});
		$('#add').click(function(e){
			console.log('add');
			var createWord = $('#word').val();
			var addReq = $.ajax({
				type: "POST",
				url: "./api/anagram/",
				data: createWord
			});
		});
		$('#delete').click(function(e){
			console.log('delete');
			var deleteReq = $.ajax({
				type: "DELETE",
				url: "./api/anagram/" + $('#word').val()
			});
		});
	});
}
load();
