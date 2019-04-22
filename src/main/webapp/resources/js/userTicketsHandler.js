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
            completedTrips: "COPLETED TRIPS"
        }
    };


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
                        '<input id="trainTicketDto_more_stations" name="trainTicketDto.stations" type="hidden" value>' +
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
    getStartUserPage();

    function getCompletedTickets(){
        $.ajax({
            url : '/railroad/ticket/all-not-actual',
            type : "GET",
            dataType : "json",
            success: function (data) {
                var  markup;
                markup = '<div class="content"><h2>' + findTableText[local].completedTrips + '</h2>';
                if(data.length === 0){
                    markup+= '<div class="not_found">' + findTableText[local].haveNotTrips + '</div>';
                }else{
                    markup += '<div class="caption"><div>' + findTableText[local].trainTrips +'</div><div>' +
                        findTableText[local].routeTrips + '</div><div>' + findTableText[local].departDateTrips +
                        '</div></div><div class="items">';
                    markup += getAllTickets(data, "completed");
                    markup += '</div></div>';
                    markup += '<form id="ticket" action="" method="post">' +
                        '<input id="trainTicketDto_more_number" name="trainTicketDto.number" type="hidden" value>' +
                        '<input id="trainTicketDto_more_departDate" name="trainTicketDto.departDate" type="hidden" value>' +
                        '<input id="trainTicketDto_more_arrivalDate" name="trainTicketDto.arrivalDate" type="hidden" value>' +
                        '<input id="trainTicketDto_more_stations" name="trainTicketDto.stations" type="hidden" value>' +
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
            var stations = [departStationMore, arrivalStationMore];
            var dateDeparture = item.getElementsByClassName("date_departure")[0].textContent;
            var arrivalDate = item.getElementsByClassName("trainTicketDto_arrivalDate")[0].value;
            var lastName = item.getElementsByClassName("passengerTicketDto_lastName")[0].value;
            var name = item.getElementsByClassName("passengerTicketDto_name")[0].value;
            var birthDate = item.getElementsByClassName("passengerTicketDto_birthDate")[0].value;
            var ticketNumber = item.getElementsByClassName("ticketNumber")[0].value;
            $('#trainTicketDto_more_number').val(onlyNumber);
            $('#trainTicketDto_more_departDate').val(dateDeparture);
            $('#trainTicketDto_more_arrivalDate').val(arrivalDate);
            $('#trainTicketDto_more_stations').val(stations);
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


    var ticketButtons = document.getElementById("ticket_buttons");
    if(ticketButtons){
        ticketButtons.addEventListener("click", checkActiveButton, false);
    }

    function checkActiveButton(e) {

        var current = e.target;
        var id = current.id;
        var buttons = document.querySelectorAll(".ticket_buttons div");

        for(var i = 0; i < buttons.length; i++){
            buttons[i].classList.remove("active");
        }

        if(id === "upcoming_trips"){
            current.classList.add("active");
            getStartUserPage();
        } else{
            current.classList.add("active");
            getCompletedTickets();
        }
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
                '<div class="departStation_more">' + data[i].trainTicketDto.stations[0] + '</div>' +
                '<div class="arrivalStation_more">' + data[i].trainTicketDto.stations[1] + '</div>' +
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