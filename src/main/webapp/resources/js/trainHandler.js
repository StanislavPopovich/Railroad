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
            trainsNotFound: "Trains not found",
            upcomingTrips: "UPCOMING TRIPS",
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
                console.log(data);
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

