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
            haveNotTrips: "You haven't forthcoming trips",
            trainTrips: "TRAIN",
            routeTrips: "ROUTE",
            departDateTrips: "DEPARTING DATE",
            details: "Details",
            return: "Return",
            allOrders: "ALL ORDERS",
            completedTrips: "COMPLETED TRIPS"
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

    //    Start page for User
    function getStartUserPage(){
        $.ajax({
            url : '/railroad/user/user',
            type : "GET",
            dataType : "json",
            success: function (data) {
                var  markup;
                markup = '<div class="content"><h2>' + findTableText[local].upcomingTrips + '</h2>';
                if(data.length === 0){
                    markup+= '<div class="not_found">' + findTableText[local].haveNotTrips + '</div>';
                }else{
                    markup += '<div class="caption"><div>' + findTableText[local].trainTrips +'</div><div>' +
                        findTableText[local].routeTrips + '</div><div>' + findTableText[local].departDateTrips +
                            '</div></div><div class="items">';
                    markup += getAllTickets(data, "upcoming");
                    markup += '</div></div>';
                    markup += '<form id="ticket" action="" method="post">' +
                        '<input id="trainTicketDto_more_number" name="trainTicketDto.number" type="hidden" value>' +
                        '<input id="trainTicketDto_more_departDate" name="trainTicketDto.departDate" type="hidden" value>' +
                        '<input id="trainTicketDto_more_arrivalDate" name="trainTicketDto.arrivalDate" type="hidden" value>' +
                        '<input id="trainTicketDto_more_departStation" name="trainTicketDto.departStation" type="hidden" value>' +
                        '<input id="trainTicketDto_more_arrivalStation" name="trainTicketDto.arrivalStation" type="hidden" value>' +
                        '<input id="passengerTicketDto_more_lastName" name="passengerDto.lastName" type="hidden" value>' +
                        '<input id="passengerTicketDto_more_name" name="passengerDto.name" type="hidden" value>' +
                        '<input id="passengerTicketDto_more_birthDate" name="passengerDto.birthDate" type="hidden" value>' +
                        '<input id="ticket_number" name="number" type="hidden" value>';
                }
                $('#find_orders').append().html(markup);
                moreAndReturnTicketButton()

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
        }else if(user){
            getStartUserPage();
        }
    }
    getPageForRole();

    function FindElem(self, classElem){
        var item = self.parentElement;
        if(item.classList.contains(classElem)){
            return item;
        } else {
            return FindElem(item, classElem);
        }
    }

    function moreAndReturnTicketButton(){
        $('.item_btn').on('click', function (event) {
            event.preventDefault();
            var url;
            if(event.target.className === 'btn_more'){
                url = '/railroad/ticket/info';
            }else if(event.target.className === 'btn_return_ticket'){
                url = '/railroad/ticket/delete';
            }
            var self = event.target;
            var item = FindElem(self, "item");
            var number = item.getElementsByClassName("trainNumber_more")[0].textContent.split(" ");
            var onlyNumber = number[1];
            var departStationMore = item.getElementsByClassName("departStation_more")[0].textContent;
            var arrivalStationMore = item.getElementsByClassName("arrivalStation_more")[0].textContent;
            var dateDeparture = item.getElementsByClassName("date_departure")[0].textContent;
            var arrivalDate = item.getElementsByClassName("trainTicketDto_arrivalDate")[0].value;
            var lastName = item.getElementsByClassName("passengerTicketDto_lastName")[0].value;
            var name = item.getElementsByClassName("passengerTicketDto_name")[0].value;
            var birthDate = item.getElementsByClassName("passengerTicketDto_birthDate")[0].value;
            var ticketNumber = item.getElementsByClassName("ticketNumber")[0].value;
            $('#trainTicketDto_more_number').val(onlyNumber);
            $('#trainTicketDto_more_departDate').val(dateDeparture);
            $('#trainTicketDto_more_arrivalDate').val(arrivalDate);
            $('#trainTicketDto_more_departStation').val(departStationMore);
            $('#trainTicketDto_more_arrivalStation').val(arrivalStationMore);
            $('#passengerTicketDto_more_lastName').val(lastName);
            $('#passengerTicketDto_more_name').val(name);
            $('#ticket_number').val(ticketNumber);
            $('#passengerTicketDto_more_birthDate').val(birthDate);
            autoSubmitMore(url , "ticket");
        })
    }

    function autoSubmitMore(url, entity) {
        var form =document.getElementById(entity);
        form.action = url;
        form.submit();
    }

    function getAllTickets(data, typeOfTicket) {
        var markup = "";
            for(var i = 0; i < data.length;i++){
                markup += '<div class="item">' +
                    '<div class="train">' +
                    '<div class="img"><img src="/resources/img/train.svg"></div>' +
                    '<div class="trainNumber_more">' + '№ ' + data[i].trainTicketDto.number + '</div>' +
                    '</div>' +
                    '<div class="route">' +
                    '<div class="departStation_more">' + data[i].trainTicketDto.departStation+ '</div>' +
                    '<div class="arrivalStation_more">' + data[i].trainTicketDto.arrivalStation + '</div>' +
                    '</div>' +
                    '<div class="date_departure">' +
                    data[i].trainTicketDto.departDate +
                    '</div>' +
                    '<div class="item_btn">' +
                    '<div class="btn_more">' + findTableText[local].details + '</div>';
                    if(typeOfTicket === "upcoming"){
                        markup +='<div class="btn_return_ticket">' + findTableText[local].return + '</div>';
                    }
                    markup += '</div>' +
                    '<input type="hidden" class="trainTicketDto_arrivalDate" value="' + data[i].trainTicketDto.arrivalDate + '">' +
                    '<input type="hidden" class="passengerTicketDto_lastName" value="' + data[i].passengerDto.lastName + '">' +
                    '<input type="hidden" class="passengerTicketDto_name" value="' + data[i].passengerDto.name + '">' +
                    '<input type="hidden" class="passengerTicketDto_birthDate" value="' + data[i].passengerDto.birthDate + '">' +
                    '<input type="hidden" class="ticketNumber" value="' + data[i].number + '">' +
                    '</div>';
            }
        return markup;
    }
});