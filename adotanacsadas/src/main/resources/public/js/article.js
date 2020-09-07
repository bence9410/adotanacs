var articles = [];
$.ajax({
    url: "/articles",
    method: "GET",
    success: function (data) {
        articles = data;
        let articlesRoot = $("#articlesRoot");
        for (let i = 0; i < articles.length; i++) {
            articlesRoot.append(convertToHtmlCard(articles[i].title, articles[i].content));

        }

    }
})

function convertToHtmlCard(title, article) {
    return "<div class=\"col-12 offet-1 col-md-6 mb-3 mb-md-4\"><div class=\"card text-white bg-info shadow\" ><div class=\"card-body\"><h5 class=\"card-title\">" + title + "</h5><p class=\"card-text\">" + article + "</p></div></div></div>";

}