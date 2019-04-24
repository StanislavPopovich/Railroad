$().ready(function () {
    var local = "en";
    var findTableText = {
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
    // Returns station without departing station
    $('#start_station').on('change',function () {
        var station = {};
        station["departStation"] = $("#start").val();
        $.ajax({
            url : '/railroad/dest-station',
            type : "POST",
            data : station,
            success : function (data) {
                var markup = '';
                markup+= '<option value="0">' + findTableText[local].arrival + '</option>';
                for(var i = 0; i < data.length; i++){
                    markup+= '<option value="' + data[i] + '">' + data[i] + '</option>'
                }
                $('#end').html(markup);
            }
        })
    });

    //Returns all routes between departing and arrival stations
    $('#end_station').on('change', function () {
        var stations = {};
        stations["departStation"] = $("#start").val();
        stations["arrivalStation"] = $("#end").val();
        $.ajax({
            url : '/railroad/train/all-routes',
            type : "POST",
            data : stations,
            dataType : "json",
            success : function(data){
                console.log(data);
                var markup = '';
                markup+= '<option value="0"></option>';
                for(var i = 0; i < data.length; i++){
                    markup+= '<option value="' + data[i] + '">' + data[i] + '</option>'
                }
                $('#routes').append().html(markup);
            },
        })
    });

    /*Table of trains on index page*/
    $('#findButton').on('submit', function (event) {
        event.preventDefault();
        var stations = {};
        stations["departStation"] = $("#start").val();
        stations["arrivalStation"] = $("#end").val();
        stations["date"] = $("#date").val();
        $.ajax({
            url : 'railroad/find-trains-with-date',
            type : "POST",
            data : stations,
            dataType : "json",
            success: function (data) {
                var markup;
                if(data.length === 0){
                    markup = '<div class="not_found">' + findTableText[local].trainNotFound + '</div>';
                    $('#find').append().html(markup);

                } else {
                    markup = '<table class="header_table"><tr><th>' + findTableText[local].trainNumber +
                        '</th><th>' + findTableText[local].departureStation + '</th><th>' +
                        findTableText[local].arrivalStation + '</th><th>' + findTableText[local].departureDate + '</th>'
                        + '<th>' + findTableText[local].arrivalDate + '</th><th>' + findTableText[local].tickets +
                        '</th><th class="th_hidden"></th></tr></table>';
                    markup+='<div class="find_content"><table id="tickets_table_buy">';

                    for(var i = 0; i < data.length; i++){
                        markup+='<tr>';
                        markup+= '<td>' + data[i].number + '</td>';
                        markup+= '<td>' + stations.departStation + '</td>';
                        markup+= '<td>' + stations.arrivalStation + '</td>';
                        markup+= '<td>' + data[i].departDate + '</td>';
                        markup+= '<td>' + data[i].arrivalDate + '</td>';
                        markup+= '<td>' + data[i].seats + '</td>';
                        markup+='<td class="btn btn_edit"><button>'+ findTableText[local].buyButton + '</button></td>';
                        markup+='</tr>';
                    }
                    markup+='</table></div>';
                    markup += '<form id="trainForm" action="/railroad/passenger/add" method="post">' +
                        '<input id="ticket_buy_form_trainNumber" name="number" type="hidden" value>' +
                        '<input id="ticket_buy_form_departDate" name="departDate" type="hidden" value>' +
                        '<input id="ticket_buy_form_arrivalDate" name="arrivalDate" type="hidden" value>' +
                        '<input id="ticket_buy_form_departStation" name="departStation" type="hidden" value>'+
                        '<input id="ticket_buy_form_arrivalStation" name="arrivalStation" type="hidden" value>';
                    $('#find').append().html(markup);
                    buyTicketButton();
                }
            }
        })
    });

    // Set values in trainForm for buy ticket
    function buyTicketButton(){
        $('#tickets_table_buy').on('click', function (event) {
            event.preventDefault();
            var tr_event = event.target;
            if(tr_event.tagName === 'BUTTON'){
                var current_tr = tr_event.parentElement.parentElement;
                var current_tds = current_tr.querySelectorAll("td");
                var stations =[current_tds[1].textContent, current_tds[2].textContent];
                $('#ticket_buy_form_trainNumber').val(current_tds[0].textContent);
                $('#ticket_buy_form_departDate').val(current_tds[3].textContent);
                $('#ticket_buy_form_arrivalDate').val(current_tds[4].textContent);
                $('#ticket_buy_form_departStation').val(stations[0]);
                $('#ticket_buy_form_arrivalStation').val(stations[1]);
                autoSubmitTrainFrom();
            }
        })
    }

    function autoSubmitTrainFrom() {
        var form =document.getElementById("trainForm");
        form.submit();
    }

    // Set values in wayForm
    $('#add_way_form').on('submit', function (event) {
            event.preventDefault();
        var departStation=$('#start').val();
        var arrivalStation=$('#end').val();
        var distance=$('#distance').val();
        $('#way_first_station').val(departStation);
        $('#way_second_station').val(arrivalStation);
        $('#way_distance').val(distance);
        autoSubmitWayForm();

    });

    function autoSubmitWayForm() {
        var form =document.getElementById("wayForm");
        form.submit();
    }

});