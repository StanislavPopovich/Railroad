$(document).ready(function () {
    window.locale = "en";

    /*schedule page*/
    let btnTrainAddToSchedule = document.getElementById("btn_add_train");
    let btnTrainDeleteFromSchedule = document.getElementById("btn_remove_train");
    let btnTrainScheduleChange= document.getElementById("btn_edit");
    let btnViewSchedule= document.getElementById("btn_view");
    if(btnViewSchedule) {
        btnViewSchedule.addEventListener("click", redirectToViewSchedulePage, false);
    }
    if(btnTrainAddToSchedule) {
        btnTrainAddToSchedule.addEventListener("click", redirectToAddSchedulePage, false);
    }
    if(btnTrainDeleteFromSchedule){
        btnTrainDeleteFromSchedule.addEventListener("click", redirectToDeleteSchedulePage,false);
    }

    if(btnTrainScheduleChange){
        btnTrainScheduleChange.addEventListener("click", redirectToEditSchedulePage,false);
    }

    function redirectToAddSchedulePage(){
        window.location.href = "/railroad/schedule/add";
    }

    function redirectToEditSchedulePage(){
        window.location.href = "/railroad/schedule/edit";
    }

    function redirectToDeleteSchedulePage(){
        window.location.href = "/railroad/schedule/delete";
    }

    function redirectToViewSchedulePage(){
        window.location.href = "/railroad/user/schedule-view";
    }

    /*for add or edit page*/

    function trainNumber() {
        let url = location.href.split("/");
        if(url[url.length - 1] === "add"){
            getAllTrainNumbers();
        } else if(url[url.length - 1] === "edit"){
            getTrainNumbersFromSchedule();
        } else if(url[url.length - 1] === "delete"){
            getTrainNumbersFromSchedule();
        } else if(url[url.length - 1] === "schedule-view"){
            getAllStations();
        }
    }
    trainNumber();

    function getAllTrainNumbers() {
        $.ajax({
            url: '/railroad/train/all-numbers',
            type: "GET",
            success: function (data) {
                let markup = "";
                for (let i = 0; i < data.length; i++) {
                    markup += '<li data-value="' + data[i] + '">' + data[i] + '</li>';
                }
                $('#train_numbers').append().html(markup);
            }
        })
    }

    function getTrainNumbersFromSchedule() {
        $.ajax({
            url: '/railroad/train/all-from-schedule',
            type: "GET",
            success: function (data) {
                let markup = "";
                for (let i = 0; i < data.length; i++) {
                    markup += '<li data-value="' + data[i] + '">' + data[i] + '</li>';
                }
                $('#train_numbers').append().html(markup);
            }
        })
    }


    /*Add schedule*/
    let trainNumberSelect = document.getElementById("train_numbers");
    function getContentForAddPage() {
        let url = location.href.split("/");
        if(url[url.length - 1] === "add") {
            if (trainNumberSelect) {
                trainNumberSelect.addEventListener("click", getFieldsForAddSchedules, false);
            }
        }

    }
    getContentForAddPage();

    function getFieldsForAddSchedules() {
        let trainNumber = document.getElementById("train_number");
        let value = "";
        if(trainNumber){
            value = trainNumber.value;
        }
        if(value !== ""){
            let train = {};
            train["trainNumber"] = value;
            $.ajax({
                url: "/railroad/schedule/get-train",
                type : "POST",
                data : train,
                dataType : "json",
                success: function (data) {
                    let markup = "";
                    let count = 0;
                    for(let i = 0; i < data.stations.length; i++){
                        markup += '<div class="item">' +
                            '<div class="wrapper_item">' +
                            '   <div class="station">' + data.stations[i] + '</div>' +
                            ' </div>' +
                            '<div class="wrapper_date">' +
                            '   <div class="label">' +
                            '<span>Arrival date</span>' +
                            '</div>' +
                            '    <input data-id="' + count + '" class="arrival_date" type="text" placeholder="yyyy-MM-dd HH:mm">' +
                            '   <div class="error"></div>' +
                            '</div>';
                        count++;
                        markup +=       '<div class="wrapper_date">' +
                            '    <div class="label">' +
                            '<span>Departing date</span>' +
                            '</div>' +
                            '     <input data-id="' + count + '" class="departing_date" type="text" placeholder="yyyy-MM-dd HH:mm">' +
                            '     <div class="error"></div>' +
                            '  </div>' +
                            '</div>';
                        count++;
                    }

                    /* markup += '<input hidden id="train_number" value="' + data.number + '">';*/
                    $('#schedule').append().html(markup);
                    let schedule = document.getElementById("schedule");
                    if(schedule) {
                        schedule.addEventListener("click", function (event) {
                            getScheduleItem(event, "add_train_to_schedule", "add");
                        });
                    }
                }
            })
        }

    }

    function getScheduleItem(event, buttonId, method) {
        let current = event.target;
        let currentLocale = window.locale;
        if(current.tagName === "INPUT"){
            current.addEventListener("blur", function () {
                let addTrainToSchedule = document.getElementById(buttonId);
                let inputs = document.querySelectorAll(".items input");
                for(let i = 0; i < inputs.length; i++){
                    let currentMessageField = inputs[i].nextElementSibling;
                    let currentValue = inputs[i].value;
                    let firstValid = checkDateFormatForSchedule(currentValue);
                    if(i === 0 && inputs[i].value !== ""){
                        let currentDate = new Date(new Date().getTime() + 43200000);
                        if(firstValid === 0){
                            let inputDate = new Date(currentValue);
                            let secondValid = checkDates(currentDate, inputDate);
                            if(secondValid){
                                removeErrorMessage(currentMessageField);
                                checkButton(inputs, addTrainToSchedule, method);
                            }else{
                                setErrorMessage(message[currentLocale].scheduleDateError, currentMessageField);
                                turnButtonOff(addTrainToSchedule);
                            }
                        }else{
                            setErrorMessage(message[currentLocale].scheduleFormatError, currentMessageField);
                            turnButtonOff(addTrainToSchedule);
                        }

                    }else if(inputs[i].value !== ""){
                        let prevValue = inputs[i - 1].value;
                        if (firstValid === 0){
                            let inputDate = new Date(currentValue);
                            let prevDate = new Date(prevValue);
                            let secondValid = checkDates(prevDate, inputDate);
                            if(secondValid){
                                removeErrorMessage(currentMessageField);
                                checkButton(inputs, addTrainToSchedule, method);
                            }else{
                                setErrorMessage(message[currentLocale].scheduleDateError, currentMessageField);
                                turnButtonOff(addTrainToSchedule);
                            }
                        }else{
                            setErrorMessage(message[currentLocale].scheduleFormatError, currentMessageField);
                            turnButtonOff(addTrainToSchedule);
                        }
                    }
                }
            });
        }
    }

    function checkButton(inputs, button, method) {
        let result = false;
        for(let i = 0; i < inputs.length; i++){
            let val = inputs[i].value;
            if(val === ""){
                result = false;
                return;
            } else {
                let error = inputs[i].nextElementSibling;
                if(error.textContent === ""){
                    result = true;
                } else {
                    result = false;
                    return;
                }
            }
        }

        if(result){
            if(method === "add") {
                turnButtonOn(button);
                button.addEventListener("click", addSchedule, false);
            } else if(method === "update") {
                turnButtonOn(button);
                button.addEventListener("click", changeScheduleOfTrain, false);
            }
        } else {
            if(method === "add") {
                button.removeEventListener("click", addSchedule);
            } else if(method === "update") {
                button.removeEventListener("click", changeScheduleOfTrain);
            }

        }
    }

    function addSchedule() {
        let number = document.getElementById("train_number").value;
        let stationList = document.querySelectorAll(".station");
        let departDateList = document.querySelectorAll(".departing_date");
        let arrivalDateList = document.querySelectorAll(".arrival_date");
        let stations = [];
        let arrivalDates = [];
        let departDates = [];
        for(let i = 0; i < stationList.length; i++){
            stations[i] = stationList[i].textContent;
            arrivalDates[i] = arrivalDateList[i].value;
            departDates[i] = departDateList[i].value;
        }
        let scheduleTrainDto = {"number": number, "stations": stations, "arrivalDates": arrivalDates,
            "departDates" : departDates};
        $.ajax({
            url: "/railroad/schedule/add",
            type : "POST",
            data : JSON.stringify(scheduleTrainDto),
            contentType: 'application/json',
            success: function (data) {

            }
        });
        window.location.href = "/railroad/schedule";
    }


    /*Edit page*/

    //getting departing dates for selected train
    $('#train_numbers').on('click',function () {
        let train= {};
        train["trainNumber"] = $("#train_number").val();
        $.ajax({
            url : '/railroad/schedule/get-depart-dates',
            type : "POST",
            data : train,
            success : function (data) {
                let markup = "";
                for (let i = 0; i < data.length; i++) {
                    markup += '<li data-value="' + data[i] + '">' + data[i] + '</li>';
                }
                $('#train_dates').append().html(markup);

            }
        })
        let url = location.href.split("/");
        if (url[url.length - 1] === "delete"){
            updateContent();
        }
        if (url[url.length - 1] === "edit"){
            updateContentEdit();
        }
    });

    function updateContent(){

        let trainDate = document.getElementById("train_date");
        let select = FindElem(trainDate, "wrapper_dropdown_in");

        select.querySelector(".dropdown_value").textContent = "Train dates";

        let btnTrainNumberDelete = document.getElementById("btn_train_number_delete");
        turnButtonOff(btnTrainNumberDelete);
        btnTrainNumberDelete.removeEventListener("click", deleteSchedule);
    }

    function updateContentEdit(){

        let trainDate = document.getElementById("train_date");
        let select = FindElem(trainDate, "wrapper_dropdown_in");

        select.querySelector(".dropdown_value").textContent = "Train dates";

        let changeTrainSchedule = document.getElementById("change_train_schedule");
        turnButtonOff(changeTrainSchedule);
        /*changeTrainSchedule.removeEventListener("click", deleteSchedule);*/

        document.getElementById("updateSchedule").innerHTML = "";
    }

    $('#train_dates').on('click',function () {
        let url = location.href.split("/");
        if(url[url.length - 1] === "edit"){
            let trainNumber = document.getElementById("train_number").value;;
            let date = document.getElementById("train_date").value;;
            if(trainNumber !== "Train number" && date !== "Train dates"){
                let train = {};
                train["trainNumber"] = trainNumber;
                train["date"] = date;
                $.ajax({
                    url: "/railroad/schedule/get-schedule-for-train",
                    type : "POST",
                    data : train,
                    dataType : "json",
                    success: function (data) {
                        let markup = "";
                        let count = 0;
                        for(let i = 0; i < data.stations.length; i++){
                            markup += '<div class="item">' +
                                '<div class="wrapper_item">' +
                                '   <div class="station">' + data.stations[i] + '</div>' +
                                ' </div>' +
                                '<div class="wrapper_date">' +
                                '   <div class="label">' +
                                '<span>Arrival date</span>' +
                                '</div>' +
                                '    <input data-id="' + count + '" class="arrival_date" type="text" placeholder="yyyy-MM-dd HH:mm" ' +
                                'value="' + data.arrivalDates[i] + '">' +
                                '   <div class="error"></div>' +
                                '</div>';
                            count++;
                            markup +=       '<div class="wrapper_date">' +
                                '    <div class="label">' +
                                '<span>Departing date</span>' +
                                '</div>' +
                                '     <input data-id="' + count + '" class="departing_date" type="text" placeholder="yyyy-MM-dd HH:mm" ' +
                                'value="' + data.departDates[i] + '">' +
                                '     <div class="error"></div>' +
                                '  </div>' +
                                '</div>';
                            count++;
                        }

                        $('#updateSchedule').append().html(markup);

                        let inputHidden = '';
                        inputHidden += '<input hidden id="train_number" value="' + data.number+ '">';
                        inputHidden += '<input hidden id="train_oldDate" value="' + data.oldDepartDateFromFirstStation+ '">';

                        $('#search_menu').append(inputHidden);

                        let schedule = document.getElementById("updateSchedule");
                        if(schedule) {
                            schedule.addEventListener("click", function (event) {
                                getScheduleItem(event, "change_train_schedule", "update");
                            });
                        }
                    }
                })
            }
        } else if (url[url.length - 1] === "delete"){
            let btnTrainNumberDelete = document.getElementById("btn_train_number_delete");
            turnButtonOn(btnTrainNumberDelete);
            btnTrainNumberDelete.addEventListener("click", deleteSchedule, false);
        }

    });

    function  changeScheduleOfTrain() {
        let stationList = document.querySelectorAll(".station");
        let departDateList = document.querySelectorAll(".departing_date");
        let arrivalDateList = document.querySelectorAll(".arrival_date");
        let number = document.querySelector("#train_number").value;
        let oldDate = document.querySelector("#train_oldDate").value;
        let stations = [];
        let arrivalDates = [];
        let departDates = [];
        for(let i = 0; i < stationList.length; i++){
            stations[i] = stationList[i].textContent;
            arrivalDates[i] = arrivalDateList[i].value;
            departDates[i] = departDateList[i].value;
        }
        let scheduleUpdateDto = {"oldDepartDateFromFirstStation": oldDate,"number": number, "stations": stations,
            "arrivalDates": arrivalDates, "departDates" : departDates};
        $.ajax({
            url: "/railroad/schedule/update",
            type : "POST",
            data : JSON.stringify(scheduleUpdateDto),
            contentType: 'application/json',
            success: function (data) {

            }
        });
        window.location.href = "/railroad/schedule";
    }


   /*For delete page*/
    function deleteSchedule() {
        let schedule = {};
        schedule["trainNumber"] = $("#train_number").val();
        schedule["date"] = $("#train_date").val();
        $.ajax({
            url : '/railroad/schedule/delete-train-success',
            type : "POST",
            data : schedule,
            dataType : "json"
        });
        window.location.href = "/railroad/schedule";
    }

    function FindElem(self, classElem){
        let item = self.parentElement;
        if(item.classList.contains(classElem)){
            return item;
        } else {
            return FindElem(item, classElem);
        }
    }

    /*View*/

    function getAllStations(){
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

        let selectButton = document.getElementById("find_schedule");
        if(selectButton){
            selectButton.addEventListener("click", getAllTrainOnDateAndStation, false);
        }

    }
    function getAllTrainOnDateAndStation(){
        let schedule = {};
        schedule["station"] = $("#depart_station").val();
        schedule["date"] = $("#date").val();
        $.ajax({
            url : '/railroad/user/schedule/get-schedule-by-station-date',
            type : "POST",
            data : schedule,
            dataType : "json",
            success: function (data) {
                let markup;
                if(data.length === 0){
                    markup = '<div class="not_found">' + "Not found"+ '</div>';
                    $('#items').append().html(markup);

                } else {
                    markup = '';
                    for(let i = 0; i < data.length; i++){
                        markup+='<div class="item">' +
                            '<div class="wrapper_item">' +
                            '   <div class="train">' +
                            '       <div class="img">' +
                            '           <img src="/resources/img/train.svg">' +
                            '       </div>' +
                            '       <div class="trainNumber_1">№' + data[i].train + '</div>' +
                            '   </div>' +
                            '</div>' +
                            '<div class="wrapper_item">' +
                            '   <div class="wrapper_rout">' +
                            '       <div class="route">' +
                            '           <div class="label">' +
                            '               <span>From</span>' +
                            '           </div>                ' +
                            '           <div class="departStation_1">' + data[i].stations[0] + '</div>            ' +
                            '       </div>            ' +
                            '       <div class="arrow_right"></div>' +
                            '       <div class="route">' +
                            '           <div class="label">' +
                            '               <span>To</span>' +
                            '           </div>' +
                            '           <div class="arrivalStation_1">' + data[i].stations[data[i].stations.length -1] + '</div>' +
                            '       </div>' +
                            '   </div>' +
                            '</div>' +
                            '<div class="wrapper_item">' +
                            '   <div class="date">' +
                            '       <div class="label">' +
                            '           <span>Arrival time</span>' +
                            '       </div>' +
                            '       <div class="date_depart_1">' + data[i].arrivalDate + '</div>' +
                            '   </div>' +
                            '</div>' +
                            '<div class="wrapper_item">' +
                            '   <div class="date">' +
                            '       <div class="label">' +
                            '           <span>Departure time</span>' +
                            '       </div>' +
                            '       <div class="date_depart_1">' + data[i].departDate + '</div>' +
                            '   </div>' +
                            '</div>' +
                        '</div>';
                    }
                    $('#items').append().html(markup);
                }
            }
        })
    }

   /* $('#find_schedule').on('click', function () {

    });*/
});

