$(document).ready(function () {
    var local = "en";
    var findTableText = {
        ru: {
            userName: "Пользователь",
            userRole: "Роль",
            userNotFound: "Пользователи не найдены",
            editButton: "Изменить",
            deleteButton: "Удалить",
            trainNumber: "Номер поезда",
            seats: "Количество мест",
            timeWay: "Время в пути",
            route: "Маршрут",
            trainsNotFound: "Поезда не найдены",
            upcomingTrips: "ПРЕДСТОЯЩИЕ ПОЕЗДКИ",
            haveNotTrips: "У вас нет предстоящих поезок"
        },
        en: {
            userName: "User name",
            userRole: "Role",
            userNotFound: "Users not found",
            editButton: "Edit",
            deleteButton: "Delete",
            trainNumber: "Train number",
            seats: "Seats",
            timeWay: "Time",
            route: "Route",
            trainsNotFound: "Trains is not found",
            upcomingTrips: "FORTHCOMING TRIPS",
            haveNotTrips: "You haven't upcoming trips",
            trainTrips: "TRAIN",
            routeTrips: "ROUTE",
            departDateTrips: "DEPARTING DATE",
            details: "Details",
            return: "Return",
            allOrders: "ALL ORDERS",
            completedTrips: "COMPLETED TRIPS",
            depart: "From",
            arrival: "To"
        }
    };

    function getAllTrains(){
        $.ajax({
            url : '/railroad/trains/all',
            type : "GET",
            dataType : "json",
            success: function (data) {
                var  markup = '<table class="header_table"><tr><th>' + findTableText[local].trainNumber +
                    '</th><th>' + findTableText[local].seats + '</th><th class="th_route">' +
                    findTableText[local].route + '</th><th class="th_hidden"></th>' +
                    '<th class="th_hidden"></th></tr></table>';
                markup+='<div class="find_content"><table>';
                if(data.length === 0){
                    markup+= '<tr><th>' + findTableText[local].trainsNotFound + '</th></tr>'
                }
                for(var i = 0; i < data.length; i++){
                    markup+='<tr>';
                    markup+= '<td>' + data[i].number + '</td>';
                    markup+= '<td>' + data[i].seats + '</td>';
                    markup+= '<td class="td_route">';
                    for(var j = 0; j < data[i].stations.length; j++){
                        if(j != data[i].stations.length - 1){
                            markup+= data[i].stations[j] + ' => ';
                        }else{
                            markup+= data[i].stations[j];
                        }
                    }
                    markup+= '</td>';
                    markup+='</tr>';
                }
                markup+='</table></div>';
                $('#find_trains').append().html(markup);
            }
        });
    }
    getAllTrains();

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
                var markup = '';
                for(var i = 0; i < data.length; i++){
                    markup+= '<div class="wrapper_radio item">' +
                    '<input type="radio" id="route'+ i + '" name="route" value="' + data[i].stations + '">' +
                    '<label for="route'+ i + '">';
                    for(var j = 0; j < data[i].stations.length; j++){
                        markup+= '<div>' + data[i].stations[j] + '</div>';
                    }
                    markup+= '</label>' +
                    '</div>';
                }
                $('#routes').append().html(markup);
                console.log(addInputsForNuberAndSeats());
                $('#fields_button').append().html(addInputsForNuberAndSeats());

                var markup2 = '<a href="/railroad/station/add"><button class="btn btn_blue">Add new station</button></a>' +
                    '<a href="/railroad/way/add"><button class="btn btn_blue">Add way between stations</button></a>';
                $('#buttons').append().html(markup2);
                addTrainButton();

            },
        })
    });

    function addTrainButton(){
        $('#add_train_button').on('click', function (event) {
            event.preventDefault();
            var stations = $("input[name='route']:checked").val().split(",");
            var number =  $("#number").val();
            var seats =  $("#seats").val();
            $('#train_add_number').val(number);
            $('#train_add_seats').val(seats);
            $('#train_add_stations').val(stations);
            autoSubmitTrainFrom();
        })
    }
    function autoSubmitTrainFrom() {
        var form =document.getElementById("trainForm");
        form.submit();
    }

    function addInputsForNuberAndSeats(){
        var markup = '<div class="wrapper_input">\n' +
            '<label for="number"> Number </label>\n' +
            '<input id="number" name="number" type="text" value="">\n' +
            '</div>'+
            '<div class="wrapper_input">\n' +
            '<label for="seats"> Seats </label>\n' +
            '<input id="seats" name="seats" type="text" value="">\n' +
            '</div>' +
            '<button id="add_train_button" class="btn btn_blue" type="submit">Add train</button>';
        markup += '<form id="trainForm" action="/railroad/train/add" method="post">' +
            '<input id="train_add_number" name="number" type="hidden" value>' +
            '<input id="train_add_seats" name="seats" type="hidden" value>' +
            '<input id="train_add_stations" name="stations" type="hidden" value>';
        return markup;
    }

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
});

