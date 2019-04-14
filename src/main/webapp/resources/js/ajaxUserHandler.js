$(document).ready(function () {
    var local = "ru";
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
            trainsNotFound: "Поезда не найдены"
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
            trainsNotFound: "Trains not found"
        }
    };

    //    Start user page for Admin
    function getStartAdminPage(){
        $.ajax({
            url : '/railroad/user/admin',
            type : "GET",
            dataType : "json",
            success: function (data) {
                var  markup = '<table class="header_table"> <tr> <th>' + findTableText[local].userName +
                    '</th> <th>' + findTableText[local].userRole + '</th><th class="th_hidden"></th>' +
                    '<th class="th_hidden"></th></tr></table>'
                markup+='<div class="find_content"><table>';
                if(data.length === 0){
                    markup+= '<tr><th>' + findTableText[local].userNotFound + '</th></tr>'
                }
                console.log(data);
                for(var i = 0; i < data.length; i++){
                    markup+='<tr>';
                    markup+= '<td>' + data[i].userName+ '</td>';
                    markup+= '<td>'
                    for(var j = 0; j < data[i].roles.length; j++){
                        markup+= data[i].roles[j] + ' ';
                    }
                    markup+= '</td>';
                    markup+='<td class="btn btn_edit">' + '<a href="/railroad/user/edit-user/'
                        + data[i].userName +'">' + findTableText[local].editButton + '</a></td>';
                    markup+='<td class="btn btn_delete">' + '<a href="/railroad/user/delete-user/'
                        + data[i].userName +'">' + findTableText[local].deleteButton + '</a></td>';
                    markup+='</tr>';
                }
                markup+='</table></div>';
                $('#find_users').append().html(markup);
            }
        });
    }


    //    Start user page for Moderator
    function getStartModeratorPage(){
        $.ajax({
            url : '/railroad/user/moderator',
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

    function getStartUserPage(){
        $.ajax({
            url : '/railroad/user/user',
            type : "GET",
            dataType : "json",
            success: function (data) {
                console.log(data);
                var  markup = '<div class="items"><p>Ближайшие поездки</p>' +
                    '<div class="caption"><div>Поезд</div><div>Маршруты</div><div>Дата отправления</div></div>';
                for(var i = 0; i < data.length;i++){
                    console.log(data[i]);
                    markup += '<div class="item">' +
                        '<div class="train">' +
                        '<div class="img"><img src="/resources/img/train.svg"></div>' +
                        '<div class="trainNumber_more">' + data[i].trainTicketDto.number + '</div>' +
                        '</div>' +
                        '<div class="route">' +
                        '<div class="departStation_more">' + data[i].trainTicketDto.stations[0] + '</div>' +
                        '<div class="arrivalStation_more">' + data[i].trainTicketDto.stations[data[i].trainTicketDto.stations.length - 1] + '</div>' +
                        '</div>' +
                        '<div class="date_departure">' +
                            data[i].trainTicketDto.departDate +
                        '</div>' +
                        '<div class="item_btn">' +
                        '<div class="btn_more">' + 'Подробнее' + '</div>' +
                        '<div class="btn_return_tiket">' + 'Вернуть' + '</div>' +
                       '</div>' +
                        '<input type="hidden" class="trainTicketDto_arrivalDate" value="' + data[i].trainTicketDto.arrivalDate + '">' +
                        '<input type="hidden" class="passengerTicketDto_lastName" value="' + data[i].passengerDto.lastName + '">' +
                        '<input type="hidden" class="passengerTicketDto_name" value="' + data[i].passengerDto.name + '">' +
                        '<input type="hidden" class="passengerTicketDto_birthDate" value="' + data[i].passengerDto.birthDate + '">' +
                        '</div>';
                }
                $('#find_orders').append().html(markup);
                moreTicketButton()

            }
        });
    }

        function getPageForRole(){
            var admin = document.getElementById("find_users");
            var moderator = document.getElementById("find_trains");
            var user = document.getElementById("find_orders");
            if(admin){
                getStartAdminPage();
            }else if(moderator){
                getStartModeratorPage();
            }else if(user){
                getStartUserPage();
            }
        }
        getPageForRole();

    // Set values in trainForm for buy ticket
    function FindElem(self){
        var item = self.parentElement;
        if(item.classList.contains("item")){
            return item;
        } else {
            return FindElem(item);
        }
    }

    function moreTicketButton(){
        $('.btn_more').on('click', function (event) {
            event.preventDefault();
            var self = event.target;
            var item = FindElem(self);
            var number = item.getElementsByClassName("trainNumber_more")[0].textContent;
            var departStationMore = item.getElementsByClassName("departStation_more")[0].textContent;
            var arrivalStationMore = item.getElementsByClassName("arrivalStation_more")[0].textContent;
            var stations = [departStationMore, arrivalStationMore];
            var dateDeparture = item.getElementsByClassName("date_departure")[0].textContent;
            var arrivalDate = item.getElementsByClassName("trainTicketDto_arrivalDate")[0].value;
            var lastName = item.getElementsByClassName("passengerTicketDto_lastName")[0].value;
            var name = item.getElementsByClassName("passengerTicketDto_name")[0].value;
            var birthDate = item.getElementsByClassName("passengerTicketDto_birthDate")[0].value;
            console.log(birthDate);
            $('#trainTicketDto_more_number').val(number);
            $('#trainTicketDto_more_departDate').val(dateDeparture);
            $('#trainTicketDto_more_arrivalDate').val(arrivalDate);
            $('#trainTicketDto_more_stations').val(stations);
            $('#passengerTicketDto_more_lastName').val(lastName);
            $('#passengerTicketDto_more_name').val(name);
            /*$('#passengerTicketDto_more_birthDate').val(birthDate);*/
            autoSubmitMore();
/*
            console.log( event.target.parentElement);
            console.log(train);
            console.log(passenger);
            setTimeout(function () {

            }, 25000);
*/
        })
    }

    function autoSubmitMore() {
        var form =document.getElementById("ticket");
        form.submit();
    }


});