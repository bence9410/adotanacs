var articles = [];
articles.push({ title: "What is Lorem Ipsum?", article: "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum." });
articles.push({ title: "Where does it come from?", article: "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32." });

let articlesRoot = $("#articlesRoot");
for (let i = 0; i < articles.length; i++) {
    articlesRoot.append(convertToHtmlCard(articles[i].title, articles[i].article));

}

var weeks = [nextFarFriday()];

for (let i = 0; i < 2; i++) {
    let date = new Date(weeks[0].getTime())
    date.setDate(date.getDate() + 7 * (i + 1))
    weeks.push(date)
}

var availableTimesRoot = $("#avaiableTimes");
for (let i = 0; i < weeks.length; i++) {
    availableTimesRoot.append(generateDay(weeks[i]));

}
var dateTime = "";


function itemsShow() {
    $("#items").show();
    $("#mainPage").hide();
    $("#booking").hide();
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;

}

function mainPageShow() {
    $("#mainPage").show();
    $("#items").hide();
    $("#booking").hide();
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;

}

function bookingShow() {
    $("#booking").show();
    $("#items").hide();
    $("#mainPage").hide();
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;

}

function addArticle() {
    var titleElement = $("#title");
    var articleElement = $("#article");
    var title = titleElement.val();
    var article = articleElement.val();
    articles.push({ title, article })
    $("#articlesRoot").append(convertToHtmlCard(title, article));
    titleElement.val("");
    articleElement.val("");

}

function convertToHtmlCard(title, article) {
    return "<div class=\"col-12 offet-1 col-md-6 mb-3 mb-md-4\"><div class=\"card text-white bg-info shadow\" ><div class=\"card-body\"><h5 class=\"card-title\">" + title + "</h5><p class=\"card-text\">" + article + "</p></div></div></div>";

}
function showBookingModal(time, monthAndDay) {
    $("#bookingModal").modal("show");
    $("#actualDateTime").val();
    var parts = monthAndDay.split(',');
    var month = parts[0];
    var day = parts[1];
    var hour = $("#" + time.replace(':', '') + monthAndDay.replace(',', '')).val();
    $("#actualDateTime").html(month + "." + day + ". Péntek " + time + (hour == "0" ? " 30 perc" : " 60 perc"));
    $("#toBooking").attr("onclick", "toBooking(" + month + "," + day + ", '" + time.replace(':', "','") + "'," + hour + ")");

}
function toBooking(month, day, timehour, timeMin, hour) {
    var name = $("#bookingInputName").val();
    var email = $("#bookingInputEmail").val();
    var meetingType = $("#meetingType").val();
    var description = $("#bookingInputText").val();
    var booking = { month, day, time: timehour + ":" + timeMin, hour, name, email, meetingType, description };
    $("#bookingModal").modal("hide");
    $("#finalBooking").html(JSON.stringify(booking));
    $("#bookingSucces").modal("show");
    $("#bookingInputName").val("");
    $("#bookingInputEmail").val("");
    $("#bookingInputText").val("");
    var card = $("#" + timehour + timeMin + month + day + "Card");

    if (card.siblings().length) {
        console.log("elso" + card.siblings().length);
        card.remove();

    } else {

        card.parent().parent().remove();
        console.log("második" + card.siblings().length);


    }

}

function nextFarFriday() {
    var date = new Date();

    if (date.getDay() < 3) {
        date.setDate(date.getDate() + (5 - date.getDay()));

    } else {
        date.setDate(date.getDate() + (12 - date.getDay()));
    }
    return date;
}

function generateDay(date) {
    var availableBookings = ["14:30", "16:00", "17:30"];
    var fri = `<div class="card mt-5 mb-3">
              <div class="card-header text-center">`+ ((date.getMonth() + 1) < 10 ? "0" + (date.getMonth() + 1) : (date.getMonth() + 1)) + "." + (date.getDate() < 10 ? "0" + date.getDate() : date.getDate()) + `. Péntek</div>
              <div class="card-body">`;

    for (let i = 0; i < availableBookings.length; i++) {
        fri += generateTime(availableBookings[i], (date.getMonth() + 1) + "," + date.getDate());

    }
    return fri + "</div></div>";

}

function generateTime(time, monthAndDay) {
    let id = time.replace(':', '') + monthAndDay.replace(',', '');
    return `<div class="card" id="` + id + `Card">
    <div class="card-body row">
      <div class="col-6  pt-2">`+ time + `-tól</div>
    <select id="`+ id + `" class="custom-select col-5" >
      <option value="0">30 perc</option>
      <option value="1">60 perc</option>
    </select>
    <div class="col-12 text-center mt-2">
    <input type="button" onclick="showBookingModal('`+ time + "','" + monthAndDay + `')" class="btn btn-success col-12 shadow " value="Foglalás">
  </div>
  </div>
  </div>`

}

function meetingType() {

}

function finalBookingDates() {

}

