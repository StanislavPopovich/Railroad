$(document).ready(function () {
    window.locale = "en";
    function getAllStations() {
        $.ajax({
            url: '/railroad/stations',
            type: "GET",
            success: function (data) {
                let markup = '';
                for (let i = 0; i < data.length; i++) {
                    markup += '<li data-value="' + data[i] + '">' + data[i] + '</li>'
                }
                $('#from_stations').html(markup);
            }
        });
        let addNewStation = document.getElementById("add_new_station");
        if(addNewStation){
            addNewStation.addEventListener("click", redirectToAddStationPage, false);
        }
    }

    getAllStations();

});

function redirectToAddStationPage(){
    window.location.href = "/railroad/station/add";
}

function redirectToAddWayPage(){
    window.location.href = "/railroad/way/add";
}


// Returns station without departing station
$('#from_stations').on('click',function () {
    updateContent();
    let station = {};
    station["departStation"] = $("#depart_station").val();
    $.ajax({
        url: '/railroad/dest-station',
        type: "POST",
        data: station,
        success: function (data) {
            let markup = '';
            for (let i = 0; i < data.length; i++) {
                markup += '<li data-value="' + data[i] + '">' + data[i] + '</li>'
            }
            $('#to_stations').html(markup);
        }
    });
});

function updateContent(){
    $('#routes').html("");
    let arrivalStation = document.getElementById("arrival_station");
    let select = FindElem(arrivalStation, "wrapper_dropdown_in");
    select.querySelector(".dropdown_value").textContent = "Select station";
    document.getElementById("add_train").classList.add("not_active");

    let searchMenuDiv = document.querySelector("#search_menu > div");
    let btnAddNewWay = document.getElementById("add_new_way");
    if(btnAddNewWay){
        searchMenuDiv.removeChild(btnAddNewWay);
    }
}

function FindElem(self, classElem){
    let item = self.parentElement;
    if(item.classList.contains(classElem)){
        return item;
    } else {
        return FindElem(item, classElem);
    }
}

$('#to_stations').on('click', function () {
    let stations = {};
    stations["departStation"] = $("#depart_station").val();
    stations["arrivalStation"] = $("#arrival_station").val();
    $.ajax({
        url : '/railroad/train/all-routes',
        type : "POST",
        data : stations,
        dataType : "json",
        success : function(data){
            let markup = '';
            for(let i = 0; i < data.length; i++){
                markup+= '<div class="item">' +
                    '<div class="item_select">' +
                    '<input type="radio" id="route'+ i + '" name="route" value="' + data[i].stations + '">' +
                    '</div>' +
                    '<div class="item_route">';
                for(let j = 0; j < data[i].stations.length; j++){
                    markup+= '<div>' + data[i].stations[j] + '</div>';
                    if(j < data[i].stations.length - 1){
                        markup+= '<div class="arrow_right"></div>';
                    }
                }
                markup+= '</div>' +
                    '</div>';
            }
            $('#routes').html(markup);
            let routes = document.getElementById("routes");
            if(routes){
                routes.addEventListener("click", selectRoute, false);
            }

            let searchMenuDiv = document.querySelector("#search_menu > div");
            let btnAddNewWay = document.getElementById("add_new_way");

            if(!btnAddNewWay){
                btnAddNewWay = document.createElement("div");
                btnAddNewWay.className = "btn btn_blue";
                btnAddNewWay.id = "add_new_way";
                btnAddNewWay.textContent = "Add new way";
                searchMenuDiv.appendChild(btnAddNewWay);
            }
            if(btnAddNewWay){
                btnAddNewWay.addEventListener("click", redirectToAddWayPage, false);
            }

         },
    });
    let btnAddTrain = document.getElementById("add_train");
    if(btnAddTrain){
        btnAddTrain.classList.add("not_active");
        btnAddTrain.removeEventListener("click", addTrain);
    }
});


function selectRoute(event) {
    let current = event.target;
    if(current.getAttribute("type") === "radio"){
        let btnAddTrain = document.getElementById("add_train");
        if(btnAddTrain){
            btnAddTrain.classList.remove("not_active");
            btnAddTrain.addEventListener("click", addTrain, false);
        }
    }
}
function addTrain() {
    let routes = document.getElementById("routes");
    let route = routes.querySelector("input[type='radio']:checked");
    if(route){
        createModalTrain(route.value.split(","));
        addNewTrain();
    }

}

function createModalTrain(route) {
    let currentLocale = window.locale;
    let markup = "<div class='wrapper_modal'>" +
"        <div class='close'></div>" +
"        <div class='modal_content'>" +
"             <div class='wrapper_add_train'>" +
                  "<div><div class='caption'>" + message[currentLocale].routeAddTrainModal + "</div><div class='wrapper_route'>";
                for(let i =0; i < route.length; i++){
                    markup += "<div class='route'>"+ route[i] + "</div>";
                }
         markup +="</idv></div></div>";
          markup +="<div>" +
                      '<div class="back_error"></div>' +
                      '<div class="wrapper_input">' +
                      '     <small>' + message[currentLocale].trainNumberAddTrainModal + '</small>' +
                      '     <div class="wrapper_input_in">' +
                      '         <input id="train_number" class="input" type="text" value="">' +
                                '<div class="error"></div>' +
                      '     </div>' +
                      '</div>' +
                      '<div class="wrapper_input">' +
                            '<small>' + message[currentLocale].ticketsAddTrainModal + '</small>' +
                      '     <div class="wrapper_input_in">' +
                      '         <input id="number_seats" class="input" type="text" value="">' +
                                '<div class="error"></div>' +
                      '     </div>' +
                      '</div>' +
              '<button class="btn btn_blue" id="button" type="submit" disabled="true">'
              + message[currentLocale].addButtonAddTrainModal + '</button>'+
                   "</div>" +
        "</div></div></div>";

    $('body').append(markup);
    let wrapperModal = document.querySelector(".wrapper_modal");
    wrapperModal.addEventListener("click", removeModal, false);
    let addTrainButton = document.getElementById("button");
    if(addTrainButton){
        addTrainButton.addEventListener("click", sendNewTrain, false);
    }
}

function sendNewTrain() {
    let route = document.querySelectorAll(".wrapper_add_train .route");
    let stations = [];
    for(let i = 0; i < route.length; i++){
        stations[i] = route[i].textContent;
    }
    let number  = document.getElementById("train_number").value;
    let seats  = document.getElementById("number_seats").value;
    let trainDto = {"number": number, "seats": seats, "stations": stations};
    $.ajax({
        url: "/railroad/train/add-new-train",
        type : "POST",
        data : JSON.stringify(trainDto),
        dataType : "json",
        contentType: 'application/json',
        success : function(data){
            let res = checkTrainResponse(data);
            if(res === true){
                window.location.href = "/railroad/train/all";
            }
        }

    });


}

function checkTrainResponse(data) {
    window.locale = "en";
    let currentLocale = window.locale;
    let count = 0;
    for(let i = 0; i < data.length; i++){
        for(let j = 0; j < data[i].length; j++){
            if(data[i][j] === false && i === 0){
                if(j === 0){
                    setMessage(message[currentLocale].countDigitsError + 4, "train_number");
                    count ++;
                    return;
                }else{
                    setMessage(message[currentLocale].countDigitsError + 4, "number_seats");
                    count ++;
                    return;
                }
            }else if(data[i][j] === false && i !== 0 && count === 0){
                if(j=== 0){
                    setBackMessage(message[currentLocale].trainExistError, ".back_error");
                    return;
                }else{
                    setBackMessage(message[currentLocale].incorrectDataError, ".back_error");
                    return;
                }
            }
        }
    }
    if(count === 0){
        return true;
    }
    return false;
}

function setMessage(message, id){
    let elem = document.getElementById(id);
    if(elem){
        elem.nextElementSibling.textContent = message;
    }
}
function setBackMessage(message, classElem) {
    let elem = document.querySelector(classElem);
    if(elem){
        elem.innerHTML = '<span>' + message + '</span>';
    }
}

function removeModal(event) {
    let body = document.querySelector('body');
    let wrapperModal = document.querySelector('.wrapper_modal');
    let currentClass = event.target.className;
    if(currentClass === "wrapper_modal" || currentClass === "close") {
        body.removeChild(wrapperModal);
    }

}
