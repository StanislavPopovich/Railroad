$(document).ready(function () {
    window.locale = "en";

    //    Start page for User
    function getStartUserPage(){
        let currentLocale = window.locale;
        $.ajax({
            url : '/railroad/user/get-actual-tickets',
            type : "GET",
            dataType : "json",
            success: function (data) {
                let  markup = '';
                if(data.length === 0){
                    markup+= '<div class="not_found">' + message[currentLocale].haveNotUpcomingTrips + '</div>';
                }else{
                    markup += getAllTickets(data, "upcoming");
                    markup += '</div>';
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
                $('#items').append().html(markup);
                moreTicketButton();
                returnTicketButton();
                buttonListener();
                let upcoming = document.getElementById("upcoming");
                upcoming.classList.add("active");
                let completed = document.getElementById("completed");
                completed.classList.remove("active");
            }
        });
    }
    getStartUserPage();


    function FindElem(self, classElem){
        let item = self.parentElement;
        if(item.classList.contains(classElem)){
            return item;
        } else {
            return FindElem(item, classElem);
        }
    }

    function buttonListener(){
        let upcoming = document.getElementById("upcoming");
        let completed = document.getElementById("completed");
        upcoming.addEventListener("click", getStartUserPage, false);
        completed.addEventListener("click", getCompletedTrips, false);
    }

    function getCompletedTrips(){
        let currentLocale = window.locale;
        $.ajax({
            url : '/railroad/user/get-not-actual-tickets',
            type : "GET",
            dataType : "json",
            success: function (data) {
                let  markup = '';
                if(data.length === 0){
                    markup+= '<div class="not_found">' + message[currentLocale].haveNotCompletedTrips + '</div>';
                }else{
                    markup += getAllTickets(data, "completed");
                    markup += '</div>';
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
                $('#items').append().html(markup);
                moreTicketButton();
                buttonListener();
                let upcoming = document.getElementById("upcoming");
                upcoming.classList.remove("active");
                let completed = document.getElementById("completed");
                completed.classList.add("active");
            }
        });
    }

/*    function updateMenu(){
        let upcoming = document.getElementById("upcoming");
        upcoming.classList.add("active");
        let completed = document.getElementById("completed");
        completed.classList.remove("active");
    }*/

    function moreTicketButton(){
        $('.btn_more').on('click', function (event) {
            let url = '/railroad/ticket/info';
            let current = event.target;
            setTicketForm(url, current);
        })
    }

    function returnTicketButton(){
        $('.btn_return').on('click', function (event) {
            let url = '/railroad/ticket/delete';
            let current = event.target;
            setTicketForm(url, current);
        })
    }

    function  setTicketForm(url, current) {
        let item = FindElem(current, "item");
        let number = item.getElementsByClassName("trainNumber_1")[0].textContent.split(" ");
        let onlyNumber = number[1];
        let departStationMore = item.getElementsByClassName("departStation_1")[0].textContent;
        let arrivalStationMore = item.getElementsByClassName("arrivalStation_1")[0].textContent;
        let dateDeparture = item.getElementsByClassName("date_depart_1")[0].textContent;
        let arrivalDate = item.getElementsByClassName("trainTicketDto_arrivalDate")[0].value;
        let lastName = item.getElementsByClassName("passengerTicketDto_lastName")[0].value;
        let name = item.getElementsByClassName("passengerTicketDto_name")[0].value;
        let birthDate = item.getElementsByClassName("passengerTicketDto_birthDate")[0].value;
        let ticketNumber = item.getElementsByClassName("ticketNumber")[0].value;
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

    }

    function autoSubmitMore(url, entity) {
        let form =document.getElementById(entity);
        form.action = url;
        form.submit();
    }

    function getAllTickets(data, typeOfTicket) {
        let currentLocale = window.locale;
        let markup = '';
        for(let i = 0; i < data.length;i++){
            markup += '<div class="item">' +
                '<div class="wrapper_item"><div class="train">'+
                '<div class="img"><img src="/resources/img/train.svg"></div>'+
                '<div class="trainNumber_1">' + '№ ' + data[i].trainTicketDto.number + '</div></div></div>';
            markup += '<div class="wrapper_item">' +
                '<div class="wrapper_rout"><div class="route"><div class="label"><span>From</span></div>' +
                '<div class="departStation_1">' + data[i].trainTicketDto.departStation+ '</div></div><div class="arrow_right"></div>' +
                '<div class="route"><div class="label"><span>To</span></div>' +
                '<div class="arrivalStation_1">' + data[i].trainTicketDto.arrivalStation + '</div></div></div></div>';

            markup += '<div class="wrapper_item">' +
                ' <div class="date">' +
                '<div class="label"><span>Departure date</span></div>' +
                '<div class="date_depart_1">' + data[i].trainTicketDto.departDate + '</div></div></div>';

            markup += '<div class="wrapper_btn"><div class="btn btn_more btn_blue">' + message[currentLocale].details + '</div></div>';
            if(typeOfTicket === "upcoming"){
                markup +='<div class="wrapper_btn"><div class="btn btn_return btn_blue">' + message[currentLocale].return + '</div></div>';
            }

            markup += '<input type="hidden" class="trainTicketDto_arrivalDate" value="' + data[i].trainTicketDto.arrivalDate + '">' +
                '<input type="hidden" class="passengerTicketDto_lastName" value="' + data[i].passengerDto.lastName + '">' +
                '<input type="hidden" class="passengerTicketDto_name" value="' + data[i].passengerDto.name + '">' +
                '<input type="hidden" class="passengerTicketDto_birthDate" value="' + data[i].passengerDto.birthDate + '">' +
                '<input type="hidden" class="ticketNumber" value="' + data[i].number + '">' +
                '</div>';
        }
        markup += '</div>';
        return markup;
    }
});