


$(document).ready(function() {
	var game = "";
	
	    $("#addComment").click(function(e) {
	        e.preventDefault(); //add this line to prevent reload
	        game = $("#inputGameName").val();
	        console.log(game);
	        var comment = $("#inputComment").val();
	        console.log(comment);

	        $.ajax({
	            url : '/comment',
	            type : 'post',
	            dataType : 'html',
	            data : {
	                game : game,
	                comment : comment
	            },
	            success : function(data) {
	            	
	            	addComment();
	            	
	            }

	        });
	    });
	    
	    /* add Comment*/

	    function addComment(){

	        var settings = {
	            "async": true,
	            "crossDomain": true,
	            "url": "http://localhost:8888/rest/comment/"+game,
	            "method": "GET",
	            "headers": {
	                "content-type": "application/json",

	            }
	        }

	        $.ajax(settings).done(function (response) {
	            var comments = response;

	            for(let comment of comments ){
	                var row = $('<tr>');
	                row.append('<td>' + comment.player +'</td>');
	                row.append('<td>' + comment.comment +'</td>');
	                row.append('<td>' + alert(comment.date.toString()) +'</td>');
	                row.append('</tr>');


	                $('#table_ajax').append(row);
	            }
	            console.log(response);
	        });
	    }
 
	});



