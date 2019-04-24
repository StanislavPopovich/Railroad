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
            completedTrips: "COMPLETED TRIPS"
        }
    };

    $('#find_schedule').on('click', function () {
        var schedule = {};
        schedule["station"] = $("#start").val();
        schedule["date"] = $("#date").val();
        $.ajax({
            url : '/railroad/user/schedule/get-schedule-by-station-date',
            type : "POST",
            data : schedule,
            dataType : "json",
            success: function (data) {
               console.log(data);
                var markup;
                if(data.length === 0){
                    markup = '<div class="not_found">' + "Not found"+ '</div>';
                    $('#schedule').append().html(markup);

                } else {
                    markup = '<div class="caption">\n' +
                        '<div class="train">Train</div><div class="route">Route</div><div class="arrival">Arrival date</div><div class="departing">Departing date</div>' +
                        '</div>' +
                        '<div class="items">';
                    for(var i = 0; i < data.length; i++){
                        markup+='<div class="item">' +
                        '<div class="train">' +
                        '<div class="img">' +
                        '<img src="/resources/img/train.svg">' +
                        '</div>' +
                        '<div class="trainNumber_more">№' + data[i].train + '</div>' +
                        '</div>' +
                        '<div class="route">' +
                        '<div class="departStation_more">' + data[i].stations[0] + '</div>' +
                        '<div class="arrivalStation_more">' + data[i].stations[data[i].stations.length -1] + '</div>' +
                        '</div>' +
                        '<div class="date_arrival">' + data[i].arrivalDate +
                            '</div><div class="date_departure">' + data[i].departDate + '</div>' +
                        '</div>';
                    }
                    markup += '</div>';
                    $('#schedule').append().html(markup);
                }
            }
        })
    });
});