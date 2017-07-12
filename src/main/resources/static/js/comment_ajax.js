
var game=$("#inputGameName").val();

$(document).ready(function() {
ratingGames();	
getComment();	
	
	var game = "";
	
	    $("#addComment").click(function(e) {
	        e.preventDefault(); //add this line to prevent reload
	        game = $("#inputGameName").val();
	        player =$("#inputPlayerName").val();
	        commentDate = new Date().toJSON(); 
	        console.log(game);
	        console.log(commentDate);
	       
	        var comment = $("#inputComment").val();
	        console.log(comment);

	        var settings = {
	        		  url: "/rest/comment",
	        		  contentType : "application/json",
	        		  method: "POST",
	        		  data: JSON.stringify({"game": game, "comment": comment, "player": player, "date": commentDate })
	        		}

	        		$.ajax(settings).done(function (response) {
	        		  console.log(response);
	        		});
	        getComment();
	    });
	    
	    /* add Comment*/

	    
 
	});

function getComment(){


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
       $('#table_ajax').empty();
        for(let comment of comments ){
            var row = $('<tr>');
            row.append('<td>' + comment.player +'</td>');
            row.append('<td>' + comment.comment +'</td>');
            row.append('<td>' + formatDate(comment.date) +'</td>');
            row.append('</tr>');


            $('#table_ajax').append(row);
        }
        console.log(response);
    });
	
}

function formatDate(inputStr) {
    var timestamp = parseInt(inputStr, 10);
    var date = new Date(timestamp);
    return date.toISOString().substr(0, 10);}

function rating(obj) {
	var numOfStars = parseFloat(obj.value);
	document.getElementById('inputRating').value = numOfStars;
	
}
function ratingGames() {
	var ratingPexeso = [[${ratingPexeso}]];
	var ratingMinesweeper = [[${ratingMinesweeper}]];
	var ratingStones = [[${ratingStones}]];
	console.log(ratingMinesweeper);
}



