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
            trainNotFound: "Trains is not found",
            depart: "From",
            arrival: "To"
        }
    };
    // Returns station without departing station
    $('#start').on('change',function () {
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

// Set values in wayForm
    $('#add_way_form').on('click', function (event) {
        var departStation=$('#start').val();
        var arrivalStation=$('#end').val();
        var distance=$('#distance').val();
        $('#way_first_station').val(departStation);
        $('#way_second_station').val(arrivalStation);
        autoSubmitWayForm();

    });

    function autoSubmitWayForm() {
        var form =document.getElementById("wayForm");
        form.submit();

    }
});