var menu=[
    "",
    "tea",
    "menu",
    "coffeehouse",
    "socialimpact",
    "",
    "blog",
    "giftcard",
]

$(document).ready(function() {

    $("#bottom div").on("click",function(){
        //alert();
        if(menu[$(this).index()].length!=0)
        $(location).attr('href', menu[$(this).index()]+".html");
    })
    $("#logo").on("click",function(){
        $(location).attr('href', "main.html");
    })
    $("#coffee").hover(function() {
        $(this).css({backgroundColor: "#1e1e1e",
                     color: "#f7f7f7"})
        $('#header_coffee').stop() 
        $("#header_coffee").slideDown("fast").css("display", "block")},
        
        function() {
            $(this).css({backgroundColor: "#f7f7f7",
                        color: "#1e1e1e"})
            $('#header_coffee').stop() 
            $("#header_coffee").slideUp("fast");
    })

    $("#header_coffee").hover(function() {
        $("#coffee").css({backgroundColor: "#1e1e1e",
                     color: "#f7f7f7"})
        $('#header_coffee').stop() 
        $(this).slideDown("display", "block")},

        function() {
            $("#coffee").css({backgroundColor: "#f7f7f7",
                              color: "#1e1e1e"})
            $('#header_coffee').stop() 
            $(this).slideUp("fast");
        })

    $("#tea").hover(function() {
        $(this).css({backgroundColor: "#1e1e1e",
                     color: "#f7f7f7"})
        $('#header_tea').stop() 
        $("#header_tea").slideDown("fast").css("display", "block")},
        
        function() {
            $(this).css({backgroundColor: "#f7f7f7",
                        color: "#1e1e1e"})
            $('#header_tea').stop() 
            $("#header_tea").slideUp("fast");
    })

    $("#header_tea").hover(function() {
        $("#tea").css({backgroundColor: "#1e1e1e",
                     color: "#f7f7f7"})
        $('#header_tea').stop() 
        $(this).slideDown("display", "block")},

        function() {
            $("#tea").css({backgroundColor: "#f7f7f7",
                              color: "#1e1e1e"})
            $('#header_tea').stop() 
            $(this).slideUp("fast");
        })    

    $("#menu").hover(function() {
        $(this).css({backgroundColor: "#1e1e1e",
                     color: "#f7f7f7"})
        $('#header_menu').stop() 
        $("#header_menu").slideDown("fast").css("display", "block")},
        
        function() {
            $(this).css({backgroundColor: "#f7f7f7",
                        color: "#1e1e1e"})
            $('#header_menu').stop() 
            $("#header_menu").slideUp("fast");
    })

    $("#header_menu").hover(function() {
        $("#menu").css({backgroundColor: "#1e1e1e",
                     color: "#f7f7f7"})
        $('#header_menu').stop() 
        $(this).slideDown("display", "block")},

        function() {
            $("#menu").css({backgroundColor: "#f7f7f7",
                              color: "#1e1e1e"})
            $('#header_menu').stop() 
            $(this).slideUp("fast");
        })    

    $("#coffeehouse").hover(function() {
        $(this).css({backgroundColor: "#1e1e1e",
                     color: "#f7f7f7"})
        $('#header_coffeehouse').stop() 
        $("#header_coffeehouse").slideDown("fast").css("display", "block")},
        
        function() {
            $(this).css({backgroundColor: "#f7f7f7",
                        color: "#1e1e1e"})
            $('#header_coffeehouse').stop() 
            $("#header_coffeehouse").slideUp("fast");
    })

    $("#header_coffeehouse").hover(function() {
        $("#coffeehouse").css({backgroundColor: "#1e1e1e",
                     color: "#f7f7f7"})
        $('#header_coffeehouse').stop() 
        $(this).slideDown("display", "block")},

        function() {
            $("#coffeehouse").css({backgroundColor: "#f7f7f7",
                              color: "#1e1e1e"})
            $('#header_coffeehouse').stop() 
            $(this).slideUp("fast");
        })

    $("#social").hover(function() {
        $(this).css({backgroundColor: "#1e1e1e",
                     color: "#f7f7f7"})
        $('#header_socialimpact').stop() 
        $("#header_socialimpact").slideDown("fast").css("display", "block")},
        
        function() {
            $(this).css({backgroundColor: "#f7f7f7",
                        color: "#1e1e1e"})
            $('#header_socialimpact').stop() 
            $("#header_socialimpact").slideUp("fast");
    })

    $("#header_socialimpact").hover(function() {
        $("#social").css({backgroundColor: "#1e1e1e",
                     color: "#f7f7f7"})
        $('#header_socialimpact').stop() 
        $(this).slideDown("display", "block")},

        function() {
            $("#social").css({backgroundColor: "#f7f7f7",
                              color: "#1e1e1e"})
            $('#header_socialimpact').stop() 
            $(this).slideUp("fast");
        }) 

    $("#rewards").hover(function() {
        $(this).css({backgroundColor: "#1e1e1e",
                     color: "#f7f7f7"})
        $('#header_starbucksrewards').stop() 
        $("#header_starbucksrewards").slideDown("fast").css("display", "block")},
        
        function() {
            $(this).css({backgroundColor: "#f7f7f7",
                        color: "#1e1e1e"})
            $('#header_starbucksrewards').stop() 
            $("#header_starbucksrewards").slideUp("fast");
    })

    $("#header_starbucksrewards").hover(function() {
        $("#rewards").css({backgroundColor: "#1e1e1e",
                     color: "#f7f7f7"})
        $('#header_starbucksrewards').stop() 
        $(this).slideDown("display", "block")},

        function() {
            $("#rewards").css({backgroundColor: "#f7f7f7",
                              color: "#1e1e1e"})
            $('#header_starbucksrewards').stop() 
            $(this).slideUp("fast");
    })   

    $("#blog").hover(function() {
        $(this).css({backgroundColor: "#1e1e1e",
                     color: "#f7f7f7"})
        $('#header_blog').stop() 
        $("#header_blog").slideDown("fast").css("display", "block")},
        
        function() {
            $(this).css({backgroundColor: "#f7f7f7",
                        color: "#1e1e1e"})
            $('#header_blog').stop() 
            $("#header_blog").slideUp("fast");
    })

    $("#header_blog").hover(function() {
        $("#blog").css({backgroundColor: "#1e1e1e",
                     color: "#f7f7f7"})
        $('#header_blog').stop() 
        $(this).slideDown("display", "block")},

        function() {
            $("#blog").css({backgroundColor: "#f7f7f7",
                              color: "#1e1e1e"})
            $('#header_blog').stop() 
            $(this).slideUp("fast");
    })

    $("#gift").hover(function() {
        $(this).css({backgroundColor: "#1e1e1e",
                     color: "#f7f7f7"})
        $('#header_giftcards').stop() 
        $("#header_giftcards").slideDown("fast").css("display", "block")},
        
        function() {
            $(this).css({backgroundColor: "#f7f7f7",
                        color: "#1e1e1e"})
            $('#header_giftcards').stop() 
            $("#header_giftcards").slideUp("fast");
    })

    $("#header_giftcards").hover(function() {
        $("#gift").css({backgroundColor: "#1e1e1e",
                     color: "#f7f7f7"})
        $('#header_giftcards').stop() 
        $(this).slideDown("display", "block")},

        function() {
            $("#gift").css({backgroundColor: "#f7f7f7",
                              color: "#1e1e1e"})
            $('#header_giftcards').stop() 
            $(this).slideUp("fast");
    })

    $("#searchInput").focus(function() {
        $(this).css({backgroundColor: "lightgreen",
                    border: "2px solid black"});
    });

    $("#searchInput").blur(function() {
        $(this).css({backgroundColor: "white",
                    border: "1px solid gray"});
    }); 
    

})