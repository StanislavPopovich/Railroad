$().ready(function () {
    var local = "ru";
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
            departureStation: "Departure station",
            arrivalStation: "Arrival station",
            departureDate: "Departure date",
            arrivalDate: "Arrival date",
            tickets: "Count of tickets",
            buyButton: "Buy",
            trainNotFound: "Trains not found",
            depart: "From",
            arrival: "To"
        }
    };

    $('#start_station').on('change',function () {
        // var station = $("#start").val();
        var station = {};
        station["start"] = $("#start").val();
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
    $('#end_station').on('change', function () {
        var stations = {};
        stations["start"] = $("#start").val();
        stations["end"] = $("#end").val();
        $.ajax({
            url : '/railroad/user/all-routes',
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
        stations["start"] = $("#start").val();
        stations["end"] = $("#end").val();
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
                        markup+= '<td>' + stations.start + '</td>';
                        markup+= '<td>' + stations.end + '</td>';
                        markup+= '<td>' + data[i].departDate + '</td>';
                        markup+= '<td>' + data[i].arrivalDate + '</td>';
                        markup+= '<td>' + data[i].seats + '</td>';
                        markup+='<td class="btn btn_edit"><button>'+ findTableText[local].buyButton + '</button></td>';
                        markup+='</tr>';
                    }
                    markup+='</table></div>';
                    $('#find').append().html(markup);
                    buyTicketButton();
                }
            }
        })
    });
    /*+ '<a href="/railroad/user/add-passenger/' + data[i].number + '/' +
                                data[i].departDate + '/' + data[i].arrivalDate + '/'+ stations.start + '/'
                                + stations.end +'"> + findTableText[local].buyButton + '</a>*/


    // Set values in trainForm for buy ticket
    function buyTicketButton(){
        $('#tickets_table_buy').on('click', function (event) {
            event.preventDefault();
            var tr_event = event.target;
            if(tr_event.tagName === 'BUTTON'){
                var curret_tr = tr_event.parentElement.parentElement;
                var current_tds = curret_tr.querySelectorAll("td");
                var stations =[current_tds[1].textContent, current_tds[2].textContent];
                $('#ticket_buy_form_trainNumber').val(current_tds[0].textContent);
                $('#ticket_buy_form_departDate').val(current_tds[3].textContent);
                $('#ticket_buy_form_arrivalDate').val(current_tds[4].textContent);
                $('#ticket_buy_form_stations').val(stations);
                autoSubmitTrainFrom();
            }
        })
    }

    function autoSubmitTrainFrom() {
        var form =document.getElementById("train");
        form.submit();
    }

});