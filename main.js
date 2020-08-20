var articles=[];
articles.push({title:"What is Lorem Ipsum?",article:"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum."});
articles.push({title:"Where does it come from?",article:"Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, \"Lorem ipsum dolor sit amet..\", comes from a line in section 1.10.32."});

let articlesRoot=$("#articlesRoot");
    for (let i = 0; i < articles.length; i++) {
        articlesRoot.append(convertToHtmlCard(articles[i].title,articles[i].article));
        
    }

var weeks=[];
var date=new Date();

var availableBookings=["14:30","16:00","17:30"];
let bookingsRoot=$("#bookingRoot");
for (let i = 0; i < availableBookings.length; i++) {
    bookingsRoot.append(`<div class="card">
              <div class="card-body row">
                <div class="col-4 offset-1 pt-2">`+ availableBookings[i]+`-tól</div>
              <select class="custom-select col-5" >
                <option value="0">30 perc</option>
                <option value="1">60 perc</option>
              </select>
              <div class="col-12 text-center mt-2">
              <input type="button" onclick="showBookingModal('`+availableBookings[i]+`')" class="btn btn-success col-12" value="Foglalás">
            </div>
            </div>
            </div>`);
    
}


function itemsShow() {
    $("#items").show();
    $("#mainPage").hide();
    $("#booking").hide();
}

function mainPageShow() {
    $("#mainPage").show();
    $("#items").hide();
    $("#booking").hide();
}

function bookingShow(){
    $("#booking").show();
    $("#items").hide();
    $("#mainPage").hide();
}

function addArticle(){
  var titleElement=$("#title");
  var articleElement=$("#article");
  var title=titleElement.val();
  var article=articleElement.val();
  articles.push({title,article})
  $("#articlesRoot").append(convertToHtmlCard(title,article));
  titleElement.val("");
  articleElement.val("");
  
}

function convertToHtmlCard(title,article){
    return "<div class=\"col-12 offet-1 col-md-6 mb-3 mb-md-4\"><div class=\"card text-white bg-info shadow\" ><div class=\"card-body\"><h5 class=\"card-title\">"+title+"</h5><p class=\"card-text\">"+article+"</p></div></div></div>";

}
function showBookingModal(time){
    console.log(date.getFullYear()+"."+(date.getMonth()+ 1)+"."+date.getDate());
}
