$(document).ready(function () {
    let local = "en";
    let findTableText = {
        ru: {
            trainNumber: "Номер поезда",
            departureStation: "Станция отправления",
            arrivalStation: "Станция прибытия",
            departureDate: "Дата отправления",
            arrivalDate: "Дата прибытия",
            tickets: "Количество билетов",
            buyButton: "Купить",
            trainNotFound: "Поезда не найдены",
            depart: "Откуда",
            arrival: "Куда"
        },
        en: {
            trainNumber: "Train number",
            departureStation: "Departing station",
            arrivalStation: "Arrival station",
            departureDate: "Departing date",
            arrivalDate: "Arrival date",
            tickets: "Number of tickets",
            buyButton: "Buy",
            trainNotFound: "Trains not found",
            depart: "From",
            arrival: "To"
        }
    };

    function createMenuAndStartContent() {
        let returnDate = $("#departReturnDate").val();
        let page = $("#page").val();
        getDirectTrains(page, returnDate);
    }

    createMenuAndStartContent();

    function eventListener(page, returnDate) {

        let labelDirect = document.getElementById("label_direct");
        let labelTransfer = document.getElementById("label_transfer");
        let buyTicket = document.getElementById("buy_ticket");
        let returnTicket = document.getElementById("return_ticket");

        labelDirect.addEventListener("click", function () {
            let current = event.target;
            if(current.classList.contains("checked")){
                return;
            } else {
                current.classList.add("checked");
                labelTransfer.classList.remove("checked");
                getDirectTrains(page, returnDate)
            }
        });

        labelTransfer.addEventListener("click", function () {
            let current = event.target;
            if(current.classList.contains("checked")){
                return;
            } else {
                current.classList.add("checked");
                labelDirect.classList.remove("checked");
                getTransferTrains(page, returnDate)
            }
        });

        if(buyTicket){
            buyTicket.addEventListener("click", function () {
                let selectTicket = getSelectTicket();
                let selectRadio = getSelectRadio();
                if(selectRadio === "direct"){
                    if(selectTicket){
                        if(page === "start"){
                            setToDirectForm(selectTicket);
                        }
                        else{
                            setReturnDirectForm(selectTicket);
                        }

                    }
                }else{
                    if(selectTicket){
                        if(page === "start"){
                            setToTransferForm(selectTicket)
                        }
                        else{
                            setReturnTransferForm(selectTicket)
                        }
                    }
                }

            });
        }
        if(returnTicket){
            returnTicket.addEventListener("click", function () {
                let selectTicket = getSelectTicket();
                let selectRadio = getSelectRadio();
                if(selectRadio === "direct"){
                    if(selectTicket){
                        setToDirectForm(selectTicket);
                    }
                }else{
                    if(selectTicket){
                        setToTransferForm(selectTicket)
                    }
                }
            });
        }
    }

    function getSelectRadio() {
        let searchMenu = document.getElementById("search_menu");
        if(searchMenu) {
            return searchMenu.querySelector("input[type=radio]:checked").value;
        }
    }

    function setToDirectForm(item){
        $('#to_first_number').val(item.querySelector(".trainNumber_1").textContent.split("№")[1]);
        $('#to_first_departDate').val(item.querySelector(".date_depart_1").textContent);
        $('#to_first_arrivalDate').val(item.querySelector(".date_arrival_1").textContent);
        $('#to_first_departStation').val(item.querySelector(".departStation_1").textContent);
        $('#to_first_arrivalStation').val(item.querySelector(".arrivalStation_1").textContent);
        autoSubmitTrainFrom("trainTicket");
    }
    function setReturnDirectForm(item){
        $('#return_first_number').val(item.querySelector(".trainNumber_1").textContent.split("№")[1]);
        $('#return_first_departDate').val(item.querySelector(".date_depart_1").textContent);
        $('#return_first_arrivalDate').val(item.querySelector(".date_arrival_1").textContent);
        $('#return_first_departStation').val(item.querySelector(".departStation_1").textContent);
        $('#return_first_arrivalStation').val(item.querySelector(".arrivalStation_1").textContent);
        autoSubmitTrainFrom("trainTicket");
    }

    function setToTransferForm(item) {
        $('#to_first_trainNumber').val(item.querySelector(".trainNumber_1").textContent.split("№")[1]);
        $('#to_first_departDate').val(item.querySelector(".date_depart_1").textContent);
        $('#to_first_arrivalDate').val(item.querySelector(".date_arrival_1").textContent);
        $('#to_first_departStation').val(item.querySelector(".departStation_1").textContent);
        $('#to_first_arrivalStation').val(item.querySelector(".arrivalStation_1").textContent);
        $('#to_second_trainNumber').val(item.querySelector(".trainNumber_2").textContent.split("№")[1]);
        $('#to_second_departDate').val(item.querySelector(".date_depart_2").textContent);
        $('#to_second_arrivalDate').val(item.querySelector(".date_arrival_2").textContent);
        $('#to_second_departStation').val(item.querySelector(".departStation_2").textContent);
        $('#to_second_arrivalStation').val(item.querySelector(".arrivalStation_2").textContent);
        autoSubmitTrainFrom("trainTicket");

    }

    function setReturnTransferForm(item) {
        $('#return_first_trainNumber').val(item.querySelector(".trainNumber_1").textContent.split("№")[1]);
        $('#return_first_departDate').val(item.querySelector(".date_depart_1").textContent);
        $('#return_first_arrivalDate').val(item.querySelector(".date_arrival_1").textContent);
        $('#return_first_departStation').val(item.querySelector(".departStation_1").textContent);
        $('#return_first_arrivalStation').val(item.querySelector(".arrivalStation_1").textContent);
        $('#return_second_trainNumber').val(item.querySelector(".trainNumber_2").textContent.split("№")[1]);
        $('#return_second_departDate').val(item.querySelector(".date_depart_2").textContent);
        $('#return_second_arrivalDate').val(item.querySelector(".date_arrival_2").textContent);
        $('#return_second_departStation').val(item.querySelector(".departStation_2").textContent);
        $('#return_second_arrivalStation').val(item.querySelector(".arrivalStation_2").textContent);
        autoSubmitTrainFrom("trainTicket");

    }

    function autoSubmitTrainFrom(formId) {
        let form =document.getElementById(formId);
        form.submit();
    }

    function getSelectTicket() {
        let item = document.querySelectorAll('.item');
        for(let i = 0; i < item.length; i++) {
            let checkedTicket = item[i].querySelector("input[type=radio]:checked");
            if(checkedTicket){
                return item[i];
            }
        }
    }

    function getSearchObject(type) {
        let object= {};
        if(type === "return"){
            object["departStation"] = $("#toStation").val();
            object["arrivalStation"] = $("#fromStation").val();
            object["date"] = $("#departReturnDate").val();
        }else{
             object["departStation"] = $("#fromStation").val();
            object["arrivalStation"] = $("#toStation").val();
            object["date"] = $("#departDate").val();
        }
        return object;

    }

    function getDirectTrains(page, returnDate) {
        $('#search_menu').append().html("");
        $('#items').append().html("");
        let object;
        if(page === "return"){
            object = getSearchObject("return");
        }else{
            object = getSearchObject("");
        }
        let menu_markup = '<div>' +
            '<div class="wrapper_radio">' +
            '<div class="item_radio">' +
            '<input type="radio" name="radio" value="direct" id="radio-1" checked/>' +
            '<label id="label_direct" class="checked" for="radio-1">' + "Direct" + '</label>' +
            '</div>' +
            '</div>' +
            '<div class="wrapper_radio">' +
            '<div class="item_radio">' +
            '<input type="radio" name="radio" value="transfer" id="radio-2" />' +
            '<label id="label_transfer" for="radio-2">' + "With transfer" + '</label>' +
            '</div>' +
            '</div>';
        if(page === "start"){
            if(returnDate){
                menu_markup += '<div id="return_ticket" class="btn btn_blue">' + 'Select return ticket' + '</div></div>';
            }else{
                menu_markup += '<div id="buy_ticket" class="btn btn_blue">' + 'Buy ticket' + '</div></div>';
            }
        }else{
            menu_markup += '<div id="buy_ticket" class="btn btn_blue">' + 'Buy ticket' + '</div></div>';
        }

        $.ajax({
            url : '/railroad/find-direct-trains',
            type : "POST",
            data : object,
            dataType : "json",
            success: function (data) {
                let markup = '';
                if(data.length === 0){
                    markup += '<div class="not_found">' + findTableText[local].trainNotFound + '</div>';
                    $('#search_menu').append(menu_markup);
                    $('#items').append().html(markup);
                    eventListener(page, returnDate);
                } else {
                    for(let i = 0; i < data.length; i++){
                        let markup = '';
                        markup += '<div class="item">';
                        markup += '<div class="item_select"><input type="radio" name="ticket" id="select_' + (i+1) + '"></div>';
                        markup += '<div class="wrapper_item"><div class="train"><div class="img">' +
                            '<img src="/resources/img/train.svg"></div><div class="trainNumber_1">№' +
                            data[i].number+ '</div></div></div>';
                        markup += '<div class="wrapper_item"><div class="wrapper_rout"><div class="route">' +
                            '<div class="label"><span>From</span></div><div class="departStation_1">' +
                            object.departStation + '</div></div><div class="arrow_right"></div><div class="route">' +
                            '<div class="label"><span>To</span></div><div class="arrivalStation_1">' +
                            object.arrivalStation + '</div></div></div></div>';
                        markup += '<div class="wrapper_item"><div class="date"><div class="label"><span>' +
                            'Departure date</span></div><div class="date_depart_1">' + data[i].departDate + '</div></div></div>';
                        markup += '<div class="wrapper_item"><div class="date"><div class="label"><span>' +
                            'Arrival date</span></div><div class="date_arrival_1">' + data[i].arrivalDate + '</div></div></div>';
                        markup += '<div class="number_tickets"><div class="label"><span>Number of tickets</span>' +
                            '</div><div>' + data[i].seats + '</div></div>';
                        $('#items').append(markup);
                    }
                    let form = '';
                    if(returnDate){
                        if(page === "start"){
                            form += '<form id="trainTicket" action="/railroad/trains/return" method="post">' +
                                '<input id="to_first_number" name="toTrain.firstTrain.number" type="hidden" value>' +
                                '<input id="to_first_departDate" name="toTrain.firstTrain.departDate" type="hidden" value>' +
                                '<input id="to_first_arrivalDate" name="toTrain.firstTrain.arrivalDate" type="hidden" value>' +
                                '<input id="to_first_departStation" name="toTrain.firstTrain.departStation" type="hidden" value>'+
                                '<input id="to_first_arrivalStation" name="toTrain.firstTrain.arrivalStation" type="hidden" value>';
                        }else{
                            form += '<form id="trainTicket" action="/railroad/passenger/add" method="post">' +
                                '<input id="return_first_number" name="returnTrain.firstTrain.number" type="hidden" value>' +
                                '<input id="return_first_departDate" name="returnTrain.firstTrain.departDate" type="hidden" value>' +
                                '<input id="return_first_arrivalDate" name="returnTrain.firstTrain.arrivalDate" type="hidden" value>' +
                                '<input id="return_first_departStation" name="returnTrain.firstTrain.departStation" type="hidden" value>'+
                                '<input id="return_secondTrainExist" name="returnTrain.isExistSecondTrain" type="hidden" value="false">' +
                                '<input id="return_first_arrivalStation" name="returnTrain.firstTrain.arrivalStation" type="hidden" value>';
                        }

                    }else{
                        form += '<form id="trainTicket" action="/railroad/passenger/add" method="post">' +
                            '<input id="to_first_number" name="toTrain.firstTrain.number" type="hidden" value>' +
                            '<input id="to_first_departDate" name="toTrain.firstTrain.departDate" type="hidden" value>' +
                            '<input id="to_first_arrivalDate" name="toTrain.firstTrain.arrivalDate" type="hidden" value>' +
                            '<input id="to_first_departStation" name="toTrain.firstTrain.departStation" type="hidden" value>'+
                            '<input id="to_first_arrivalStation" name="toTrain.firstTrain.arrivalStation" type="hidden" value>';
                    }
                    $('#search_menu').append(menu_markup);
                    $('#items').append(form);
                    eventListener(page, returnDate);
                }
            }
        })
    }

    function getTransferTrains(page, returnDate) {
        $('#search_menu').append().html("");
        $('#items').append().html("");
        let object;
        if(page === "return"){
            object = getSearchObject("return");
        }else{
            object = getSearchObject("");
        }
        let menu_markup = '<div>' +
            '<div class="wrapper_radio">' +
            '<div class="item_radio">' +
            '<input type="radio" name="radio" value="direct" id="radio-1"/>' +
            '<label id="label_direct"  for="radio-1">' + "Direct" + '</label>' +
            '</div>' +
            '</div>' +
            '<div class="wrapper_radio">' +
            '<div class="item_radio">' +
            '<input type="radio" name="radio" value="transfer" id="radio-2" checked/>' +
            '<label id="label_transfer" class="checked" for="radio-2">' + "With transfer" + '</label>' +
            '</div>' +
            '</div>';
        if (page === "start") {
            if (returnDate) {
                menu_markup += '<div id="return_ticket"><div class="btn btn_blue">' + 'Select return ticket' + '</div></div></div>';
            } else {
                menu_markup += '<div id="buy_ticket"><div class="btn btn_blue">' + 'Buy ticket' + '</div></div></div>';
            }
        } else {
            menu_markup += '<div id="buy_ticket"><div class="btn btn_blue">' + 'Buy ticket' + '</div></div>';
        }
            $.ajax({
                url : '/railroad/find-transfer-trains',
                type : "POST",
                data : object,
                dataType : "json",
                success: function (data) {
                    let markup = '';
                    if(data.length === 0){
                        markup += '<div class="not_found">' + findTableText[local].trainNotFound + '</div>';
                        $('#search_menu').append(menu_markup);
                        $('#items').append().html(markup);
                        eventListener(page, returnDate);
                    } else {
                        for(let i = 0; i < data.length; i++){
                            let markup = '';
                            markup += '<div class="item">';
                            markup += '<div class="item_select"><input type="radio" name="ticket" id="select_' + (i+1) + '"></div>';
                            markup += '<div class="wrapper_item"><div class="train"><div class="img">' +
                                '<img src="/resources/img/train.svg"></div><div class="trainNumber_1">№' +
                                data[i].trains[0].number + '</div></div><div class="train">' +
                                '<div class="img"><img src="/resources/img/train.svg"></div>' +
                                '<div class="trainNumber_2">№' + data[i].trains[1].number  + '</div></div> </div>';
                            markup += '<div class="wrapper_item"><div class="wrapper_rout"><div class="route">' +
                                '<div class="label"><span>From</span></div><div class="departStation_1">' +
                                object.departStation + '</div></div><div class="arrow_right"></div><div class="route">' +
                                '<div class="label"><span>To</span></div><div class="arrivalStation_1">' +
                                data[i].transferStation + '</div></div></div><div class="wrapper_rout">' +
                                '<div class="route"><div class="label"><span>From</span></div>' +
                                '<div class="departStation_2">' + data[i].transferStation + '</div></div>' +
                                '<div class="arrow_right"></div><div class="route"><div class="label"><span>To</span>' +
                                '</div><div class="arrivalStation_2">' +  object.arrivalStation + '</div></div></div></div>';
                            markup += '<div class="wrapper_item"><div class="date"><div class="label">' +
                                '<span>Departure date</span></div><div class="date_depart_1">' + data[i].trains[0].departDate + '</div> </div>' +
                                '<div class="date"><div class="label"><span>Departure date</span></div><div class="date_depart_2">' +
                                data[i].trains[1].departDate + '</div></div></div>';
                            markup += '<div class="wrapper_item"><div class="date"><div class="label">' +
                                '<span>Arrival date</span></div><div class="date_arrival_1">' + data[i].trains[0].arrivalDate + '</div>' +
                                '</div><div class="date"><div class="label"><span>Arrival date</span>' +
                                '</div><div class="date_arrival_2">' + data[i].trains[1].arrivalDate + '</div></div></div>';
                            markup += '<div class="number_tickets"><div class="label"><span>Number of tickets</span>' +
                                '</div><div>' + data[i].seats + '</div></div></div>';
                            $('#items').append(markup);
                        }
                        let form = '';
                            if(returnDate){
                                if(page === "start"){
                                    form += '<form id="trainTicket" action="/railroad/trains/return" method="post">' +
                                        '<input id="to_first_trainNumber" name="toTrain.firstTrain.number" type="hidden" value>' +
                                        '<input id="to_first_departDate" name="toTrain.firstTrain.departDate" type="hidden" value>' +
                                        '<input id="to_first_arrivalDate" name="toTrain.firstTrain.arrivalDate" type="hidden" value>'+
                                        '<input id="to_first_departStation" name="toTrain.firstTrain.departStation" type="hidden" value>'+
                                        '<input id="to_first_arrivalStation" name="toTrain.firstTrain.arrivalStation" type="hidden" value>' +
                                        '<input id="to_second_trainNumber" name="toTrain.secondTrain.number" type="hidden" value>' +
                                        '<input id="to_second_departDate" name="toTrain.secondTrain.departDate" type="hidden" value>' +
                                        '<input id="to_second_arrivalDate" name="toTrain.secondTrain.arrivalDate" type="hidden" value>' +
                                        '<input id="to_second_departStation" name="toTrain.secondTrain.departStation" type="hidden" value>'+
                                        '<input id="to_second_arrivalStation" name="toTrain.secondTrain.arrivalStation" type="hidden" value>';
                                }else{
                                    form += '<form id="trainTicket" action="/railroad/passenger/add" method="post">' +
                                        '<input id="return_first_trainNumber" name="returnTrain.firstTrain.number" type="hidden" value>' +
                                        '<input id="return_first_departDate" name="returnTrain.firstTrain.departDate" type="hidden" value>' +
                                        '<input id="return_first_arrivalDate" name="returnTrain.firstTrain.arrivalDate" type="hidden" value>' +
                                        '<input id="return_first_departStation" name="returnTrain.firstTrain.departStation" type="hidden" value>'+
                                        '<input id="return_first_arrivalStation" name="returnTrain.firstTrain.arrivalStation" type="hidden" value>' +
                                        '<input id="return_second_trainNumber" name="returnTrain.secondTrain.number" type="hidden" value>' +
                                        '<input id="return_second_departDate" name="returnTrain.secondTrain.departDate" type="hidden" value>' +
                                        '<input id="return_second_arrivalDate" name="returnTrain.secondTrain.arrivalDate" type="hidden" value>' +
                                        '<input id="return_second_departStation" name="returnTrain.secondTrain.departStation" type="hidden" value>'+
                                        '<input id="return_second_arrivalStation" name="returnTrain.secondTrain.arrivalStation" type="hidden" value>';
                                }
                            }else{
                                form += '<form id="trainTicket" action="/railroad/passenger/add" method="post">' +
                                    '<input id="to_first_trainNumber" name="toTrain.firstTrain.number" type="hidden" value>' +
                                    '<input id="to_first_departDate" name="toTrain.firstTrain.departDate" type="hidden" value>' +
                                    '<input id="to_first_arrivalDate" name="toTrain.firstTrain.arrivalDate" type="hidden" value>' +
                                    '<input id="to_first_departStation" name="toTrain.firstTrain.departStation" type="hidden" value>'+
                                    '<input id="to_first_arrivalStation" name="toTrain.firstTrain.arrivalStation" type="hidden" value>' +
                                    '<input id="to_second_trainNumber" name="toTrain.secondTrain.number" type="hidden" value>' +
                                    '<input id="to_second_departDate" name="toTrain.secondTrain.departDate" type="hidden" value>' +
                                    '<input id="to_second_arrivalDate" name="toTrain.secondTrain.arrivalDate" type="hidden" value>' +
                                    '<input id="to_second_departStation" name="toTrain.secondTrain.departStation" type="hidden" value>'+
                                    '<input id="to_second_arrivalStation" name="toTrain.secondTrain.arrivalStation" type="hidden" value>';
                            }
                        $('#search_menu').append(menu_markup);
                        $('#items').append(form);
                        eventListener(page, returnDate);
                    }
                }
            })
        }
        
        function f() {
            
        }
});