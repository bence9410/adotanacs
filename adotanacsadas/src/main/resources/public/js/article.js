$("#searchInput").on("keyup", function () {
    var value = $(this).val().toLowerCase();
    $(".dropdown-menu li").filter(function () {
        $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
});

var articles = [];

$.ajax({
    url: "/articles",
    method: "GET",
    success: function (data) {
        articles = data;

        let articlesRoot = $("#articlesRoot");

        var getUrl = window.location.pathname.replace("/cikkek/", "");
        var decoder = decodeURIComponent(getUrl);
        if (articles.length == 1 && window.location.pathname == "/cikkek") {
            articlesRoot.append("<div class=\"col-12 col-md-10 offset-md-1 mb-3 mb-md-4 unselectable\" style=\"user-select: none;\"><div class=\"card text-white bg-info shadow\" ><div class=\"card-body\"><h5 class=\"card-title\">" + articles[0].title + "</h5>" +
                "<h6 class=\"card-subtitle mb-2 \">" + articles[0].date + "</h6><p class=\"card-text \" >" + articles[0].content + "</p></div></div></div>");
            $("#searchLinks").append("<li class=\"pl-1\"><a href=\"/cikkek/" + articles[0].searchKey + '"' + "style=\"user-select: none;\">" + articles[0].title + "</a></li>");

        } else if (window.location.pathname == "/cikkek") {
            for (let i = 0; i < articles.length; i++) {
                articlesRoot.append(convertToHtmlCard(articles[i].title, articles[i].date, articles[i].content));
                $("#searchLinks").append("<li class=\"pl-1\"><a href=\"/cikkek/" + articles[i].searchKey + '"' + "style=\"user-select: none;\">" + articles[i].title + "</a></li>");

            }
        } else {
            for (let i = 0; i < articles.length; i++) {

                if (decoder == articles[i].searchKey) {
                    articlesRoot.append("<div class=\"col-12 col-md-10 offset-md-1 mb-3 mb-md-4 unselectable\" style=\"user-select: none;\"><div class=\"card text-white bg-info shadow\" ><div class=\"card-body\"><h5 class=\"card-title\">" + articles[i].title + "</h5>" +
                        "<h6 class=\"card-subtitle mb-2 \">" + articles[i].date + "</h6><p class=\"card-text \" >" + articles[i].content + "</p></div></div></div>");

                    $("#searchResult").html("");
                    $("#searchResult").append(articles[i].title.substring(0, 30));
                }
                if (window.location.pathname == "/cikkek") {
                    articlesRoot.append(convertToHtmlCard(articles[i].title, articles[i].date, articles[i].content));
                    for (let i = 0; i < articles.length; i++) {
                        articlesRoot.append(convertToHtmlCard(articles[i].title, articles[i].date, articles[i].content));
                        $("#searchLinks").append("<li class=\"pl-1\"><a href=\"/cikkek/" + articles[i].searchKey + '"' + "style=\"user-select: none;\">" + articles[i].title + "</a></li>");

                    }
                }
                $("#searchLinks").append("<li class=\"pl-1\"><a href=\"/cikkek/" + articles[i].searchKey + '"' + "style=\"user-select: none;\">" + articles[i].title + "</a></li>");

            }
        }

    }
})

function convertToHtmlCard(title, date, article) {
    return "<div class=\"col-12 col-md-6 mb-3 mb-md-4\" style=\"user-select: none;\"><div class=\"card text-white bg-info shadow\" ><div class=\"card-body\"><h5 class=\"card-title\">" + title + "</h5>" +
        "<h6 class=\"card-subtitle mb-2 \">" + date + "</h6><p class=\"card-text\">" + article + "</p></div></div></div>";

}

function getArticleSearch() {
    var articleSearch = $("#searchInput").val();

    if (articleText !== "") {
        articleSearch.articleSearchText = articleText;
    }
    $("#searchInput").val("");

    $.ajax({
        url: "/articles?articleSearch=" + $("#searchInput").val(),
        method: "GET",
        success: function (data) {

        }
    })
}