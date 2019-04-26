$(document).ready(function () {
    var btnTrainNumberCnahge= document.getElementById("btn_train_date_select");

    if(btnTrainNumberCnahge) {
        btnTrainNumberCnahge.addEventListener("click", getTrainPassengers, false);
    }

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
            haveNotTrips: "You haven't forthcoming trips",
            trainTrips: "TRAIN",
            routeTrips: "ROUTE",
            departDateTrips: "DEPARTING DATE",
            details: "Details",
            return: "Return",
            allOrders: "ALL ORDERS",
            completedTrips: "COMPLETED TRIPS",
            haveNotPassengers: "You haven't any passengers",
            allPassenger: "ALL PASSENGERS"
        }
    };

    //getting departing dates for selected train
    $('#train_number').on('change',function () {
        var train= {};
        train["trainNumber"] = $("#train_number").val();
        $.ajax({
            url : '/railroad/schedule/get-depart-dates',
            type : "POST",
            data : train,
            success : function (data) {
                var markup = '';
                markup+= '<option value="0">' + 'Departing date' + '</option>';
                for(var i = 0; i < data.length; i++){
                    markup+= '<option value="' + data[i] + '">' + data[i] + '</option>'
                }
                $('#train_dates').html(markup);
            }
        })
    });

    function getTrainPassengers() {
        var data= {};
        data["trainNumber"] = $("#train_number").val();
        data["date"] = $("#train_dates").val();
        $.ajax({
            url : '/railroad/passenger/train/all',
            type : "POST",
            data : data,
            dataType : "json",
            success: function (data) {
                var  markup;
                markup = '<div class="content">';
                markup += '<div class="passengers">';
                if(data.length === 0){
                    markup+= '<div class="not_found"> Haven\'t any passengers </div>';
                }else{
                    for(var i = 0; i< data.length;i++ ){
                        markup += '<div class="passenger"><div class="passenger_info"><div><h3>Passenger</h3>';
                        markup += '<p>' + data[i].lastName + ' ' + data[i].name + '</p>';
                        markup += '</div><div><h3> Birth date</h3>';
                        markup += '<p>'+ data[i].birthDate + '</p></div></div></div>';
                    }
                    markup += " </div></div>";
                }
                $('#find_passengers').append().html(markup);

            }
        });
    }
});