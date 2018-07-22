
(function ($) {
    "use strict";
    $(this).load("abc.txt");

    $(".m2").hide();
    /*==================================================================
    [ Validate ]*/
    $(".m1").click(function(){
        var index=$(this).index()+1;
        $(".m1:nth-child("+index+") .m2").slideToggle();
    });
    /*==================================================================
    [ adminpage ]*/
    $(".vacation").hover(
        function() {
            $( this ).addClass("select");
          },
        function() {
            $( this ).removeClass("select");
        }
    );
    $(".vacation .consent").hover(
        function(event) {
            //var x=event.originalEvent.clientX;
            //var y=event.originalEvent.clientY;
            $(this).append("<div class='msg'>승인</div>");
          },
        function() {
            $(".msg").remove();
            
        }
    );
    $(".vacation .cancle").hover(
        function(event) {
            var x=event.originalEvent.clientX;
            var y=event.originalEvent.clientY;
            $(this).append("<div class='msg'>반려</div>");
          },
        function() {
            $(".msg").remove();
        }
    );
    $(".vacation").click(function(){
        var index=$(this).index()+1;
        $(".vacation:nth-child("+index+") .info").slideToggle();
        $(".vacation:nth-child("+index+") span").fadeToggle();
    });
    
    $(".vacation-null").hover(
        function() {
            $( this ).animate({
                opacity: '0.5'
            });
          },
        function() {
            $( this ).animate({
                opacity: '1.0'
            });
        }
    );
    /*==================================================================
    [ vacation]*/
    $(".board th").hover(
        function() {
            $( this ).toggleClass("select");
          },
        function() {
            $( this ).toggleClass("select");
        }
    );
    $(".board tr").hover(
        function() {
            $( this ).toggleClass("select");
          },
        function() {
            $( this ).toggleClass("select");
        }
    );
    /*==================================================================
    [ myInfo]*/
})(jQuery);


/*==================================================================
JavaScript 함수들 .. */
function YesVacation(no){//승낙
    var div=$("#v"+no);
    div.animate({left:'100px'});
    div.slideUp(500);
    div.remove();
    if($(".vacation").length<=0)$(".vacation-null").fadeIn(500);
}
function NoVacation(no){//반려
    var div=$("#v"+no);
    div.fadeOut(500);
    div.remove();
    if($(".vacation").length<=0)$(".vacation-null").fadeIn(500);
}

function addVacation(){
    $(".vacation-null").hide();
    var no=$(".vacation").length;
    var consentHtml="<span class='consent' onclick='YesVacation("+no+")'>"
                        +"<img src='images/consent.png'>"
                    +"</span>";
    var cancelHtml="<span class='cancle' onclick='NoVacation("+no+")'>"
                        +"<img src='images/cancle.png'>"
                    +"</span>";
    var infoHtml="<div class='info'>"
            +"<hr>"
            +"<table>"
            +"<tr><td>열0</td><td>열0</td><td>열0</td><td>열0</td><td>열0</td><td>열0</td><td>열0</td></tr>"
            +"<tr><td>내용0</td><td>내용0</td><td>내용0</td><td>내용0</td><td>내용0</td><td>내용0</td><td>내용0</td></tr>"
            +"</table>"
        +"</div>";
    $(".right-view").append(
        "<div id='v"+no+"' class='vacation'>OOO님 휴가신청하였습니다..."
        +cancelHtml
        +consentHtml
        +infoHtml
        +"</div>"
    );
}