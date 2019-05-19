images = [
    "dog1.jpg",
    "dog2.jpg",
    "dog3.jpg",
    "dog4.jpg",
    "dog5.jpg",
];


$(document).ready(function () {
    myAnimation();
    loop();

});
function myAnimation() {
    $("#mover").append("<img src="+ images[0]+ ">");
    $("#mover").append("<img src="+ images[1]+ ">");
    $("#mover").append("<img src="+ images[2]+ ">");
    $("#mover").append("<img src="+ images[3]+ ">");
    $("#mover").append("<img src="+ images[4]+ ">");
    $("#mover").append("<img src="+ images[0]+ ">");
    $("#mover").append("<img src="+ images[1]+ ">");
    $("#mover").append("<img src="+ images[2]+ ">");
    $("#mover").append("<img src="+ images[3]+ ">");
    $("#mover").append("<img src="+ images[4]+ ">");
}

function loop() {
    $.each($("#mover"), function () {
        console.log($(this).css("right"));
        // if ($(this).css("right") == '1500px')
        // {
            $("#mover").css({right: 0});
        // }

    });
    $("#mover").animate({
        right: '+=1500',
    }, 5000, 'linear', function () {
        loop();
    });
}

$(document).ready(function () {
    $("img").click(
        (e) => {
            console.log(e.target.src);
            $("#modal").css({"display" : "block", "z-index": 3, "position" : "absolute"});
            $("#modal>img").attr("src", e.target.src);
            $("#mover").stop()
        }
    )
});

$(document).ready(function () {
    $("#modal").click( () => {
        loop();
        $("#modal").css({display: "none", "z-index" : 1});
        console.log("aici2");
        }
    )
});


