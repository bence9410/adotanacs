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
        for (let i = 0; i < articles.length; i++) {
            articlesRoot.append(convertToHtmlCard(articles[i].title, articles[i].date, articles[i].content));
        }
    }
})

function convertToHtmlCard(title, date, article) {
    return "<div class=\"col-12 offet-1 col-md-6 mb-3 mb-md-4\"><div class=\"card text-white bg-info shadow\" ><div class=\"card-body\"><h5 class=\"card-title\">" + title + "</h5>" +
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