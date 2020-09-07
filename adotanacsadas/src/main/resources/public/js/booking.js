var availableBookings = ["14:30", "16:00", "17:30"];
var available = ["PM2_30", "PM4_00", "PM5_30"];

$.ajax({
    url: "/free-times",
    method: "GET",
    success: function (data) {
        $.each(data, function (key, value) {
            var availableTimesRoot = $("#avaiableTimes");
            availableTimesRoot.append(generateDay(key, value));


        });
    }
})

function showBookingModal(time, monthAndDay) {
    $("#bookingModal").modal("show");
    $("#actualDateTime").val();
    var parts = monthAndDay.split(',');
    var month = parts[0];
    var day = parts[1];
    var hour = $("#" + time.replace(':', '') + monthAndDay.replace(',', '')).val();
    $("#actualDateTime").html(month + "." + day + ". Péntek " + time + (hour == "HALF_HOUR" ? " 30 perc" : " 60 perc"));
    $("#toBooking").attr("onclick", "toBooking('" + month + "' , '" + day + "' , '" + time.replace(':', "','") + "', '" + hour + "')");

}
function toBooking(month, day, timehour, timeMin, meetingLenght) {
    var name = $("#bookingInputName").val();
    var email = $("#bookingInputEmail").val();
    var meetingType = $("#meetingType").val();
    var description = $("#bookingInputText").val();
    var booking = { name, email, meetingType, meetingLenght, description, meetingDate: "2020-" + month + "-" + day, meetingTime: available[availableBookings.indexOf(timehour + ":" + timeMin)] };
    $("#bookingModal").modal("hide");

    $.ajax({
        url: "/book",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify(booking),
        success: function () {
            $("#bookingSucces").modal("show");
            var card = $("#" + timehour + timeMin + month + day + "Card");

            if (card.siblings().length) {
                card.remove();

            } else {
                card.parent().parent().remove();

            }
        },
        error: function () {
            $("#bookingError").modal("show")
        },
        completed: function () {
            $("#bookingInputName").val("");
            $("#bookingInputEmail").val("");
            $("#bookingInputText").val("");


        }
    });
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

function generateDay(date, times) {
    var dateParts = date.split("-");
    var realTimes = [];

    for (var i = 0; i < times.length; i++) {
        realTimes.push(availableBookings[available.indexOf(times[i])]);
    }

    var fri = `<div class="card mt-5 mb-3 day">
              <div class="card-header text-center">`+ dateParts[1] + "." + dateParts[2] + `. Péntek</div>
              <div class="card-body">`;

    for (let i = 0; i < realTimes.length; i++) {
        fri += generateTime(realTimes[i], dateParts[1] + "," + dateParts[2]);

    }
    return fri + "</div></div>";

}

function generateTime(time, monthAndDay) {
    let id = time.replace(':', '') + monthAndDay.replace(',', '');
    return `<div class="card time" id="` + id + `Card">
    <div class="card-body row">
      <div class="col-6  pt-2">`+ time + `-tól</div>
    <select id="`+ id + `" class="custom-select col-5" >
      <option value="HALF_HOUR">30 perc</option>
      <option value="ONE_HOUR">60 perc</option>
    </select>
    <div class="col-12 text-center mt-2">
    <input type="button" onclick="showBookingModal('`+ time + "','" + monthAndDay + `')" class="btn btn-success col-12 shadow " value="Foglalás">
  </div>
  </div>
  </div>`

}