
$(document).ready(function() {

getComment();	
	
	    $("#addComment").click(function(e) {
	        e.preventDefault(); //add this line to prevent reload
	        player =$("#inputPlayerName").val();
	        commentDate = new Date().toJSON(); 
	        console.log(gameName);
	        console.log(commentDate);
	       
	        var comment = $("#inputComment").val();
	        console.log(comment);

	        var settings = {
	        		  url: "/rest/comment",
	        		  contentType : "application/json",
	        		  method: "POST",
	        		  data: JSON.stringify({"game": gameName, "comment": comment, "player": player, "date": commentDate })
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
        "url": "http://localhost:8888/rest/comment/"+gameName,
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




