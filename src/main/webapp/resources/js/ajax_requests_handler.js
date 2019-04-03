$().ready(function () {
    var local = "ru";
    var findTableText = {
        ru: {
            trainNumber: "Номер поезда",
            departureStation: "Станция отправления",
            arrivalStation: "Станция прибытия",
            departureDate: "Дата отправления",
            arrivalDat: "Дата прибытия",
            tickets: "Количество билетов",
            buyButton: "Купить",
            trainNotFound: "Поезда не найдены",
            departStation: "Откуда",
            arrivalStation: "Куда"
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
            departStation: "From",
            arrivalStation: "To"
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
                markup+= '<option value="0">' + findTableText[local].arrivalStation + '</option>';
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
                var markup
                if(data.length === 0){
                    markup = '<div class="not_found">' + findTableText[local].trainNotFound + '</div>';
                    $('#find').append().html(markup);

                } else {
                    markup = '<table class="header_table"> <tr> <th>' + findTableText[local].trainNumber +
                        '</th> <th>' + findTableText[local].departureStation + '</th><th>' +
                        findTableText[local].arrivalStation + '</th><th>' + findTableText[local].departureDate +
                        '</th><th>' + findTableText[local].tickets + '</th><th class="th_hidden"></th></tr></table>'
                    markup+='<div class="find_content"><table>';

                    for(var i = 0; i < data.length; i++){
                        markup+='<tr>';
                        markup+= '<td>' + data[i].number + '</td>';
                        markup+= '<td>' + stations.start + '</td>';
                        markup+= '<td>' + stations.end + '</td>';
                        markup+= '<td>' + data[i].departDate + '</td>';
                        markup+= '<td>' + data[i].seats + '</td>';
                        markup+='<td class="btn btn_edit">' + '<a href="/railroad/user/buy-ticket/' + data[i].number + '/' +
                            data[i].departDate + '">' + findTableText[local].buyButton + '</a></td>';
                        markup+='</tr>';
                    }
                    markup+='</table></div>';
                    $('#find').append().html(markup);
                }

            }
        })
    });
});