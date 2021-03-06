$(document).ready(function () {
    window.locale = "en";

    function getUserPassengers() {
        let currentLocale = window.locale;
        $.ajax({
            url : '/railroad/user/passenger/all-for-current-user',
            type : "GET",
            dataType : "json",
            success: function (data) {
                let  markup = "";
                if(data.length === 0){
                    markup+= '<div class="not_found">' + message[currentLocale].userHaveNotPassengers + '</div>';
                }else{
                    for(var i = 0; i< data.length;i++ ){
                        markup += ''+
                        '<div class="item">' +
                        '    <div class="wrap_passenger">' +
                        '       <div class="info">' +
                        '          <div class="label">' +
                        '               <span>' +  message[currentLocale].passenger + '</span>' +
                        '           </div>' +
                        '           <div>' + data[i].lastName + ' ' + data[i].name + '</div>' +
                        '       </div>' +
                        '       <div class="info">' +
                        '           <div class="label">' +
                        '               <span>' + message[currentLocale].birthDate + '</span>' +
                        '           </div>' +
                        '           <div>' + data[i].birthDate + '</div>' +
                        '       </div>' +
                        '    </div>' +
                        '    <div class="wrapper_btn">' +
                        '        <div class="btn btn_blue btn_all_orders">' + message[currentLocale].allOrdersButton + '</div>' +
                        '    </div>' +
                            '<input type="hidden" class="passengerDto_lastName" value="' + data[i].lastName + '">' +
                            '<input type="hidden" class="passengerDto_name" value="' + data[i].name + '">' +
                            '<input type="hidden" class="passengerDto__birthDate" value="' + data[i].birthDate + '">' +
                        '</div>';
                    }
                    markup += '<form id="currentPassenger" action="" method="post">' +
                        '<input id="passengerDto_form_lastName" name="lastName" type="hidden" value>' +
                        '<input id="passengerDto_form_name" name="name" type="hidden" value>' +
                        '<input id="passengerDto_form_birthDate" name="birthDate" type="hidden" value></form>';
                }


                $('#items').append().html(markup);
                getAllPassengerTickets();

            }
        });
    }

    function FindElem(self, classElem){
        let item = self.parentElement;
        if(item.classList.contains(classElem)){
            return item;
        } else {
            return FindElem(item, classElem);
        }
    }

    function getAllPassengerTickets(){
        $('.btn_all_orders').on('click', function (event) {
            let self = event.target;
            let item = FindElem(self, "item");
            let lastName = item.getElementsByClassName("passengerDto_lastName")[0].value;
            let name = item.getElementsByClassName("passengerDto_name")[0].value;
            let birthDate = item.getElementsByClassName("passengerDto__birthDate")[0].value;
            $('#passengerDto_form_lastName').val(lastName);
            $('#passengerDto_form_name').val(name);
            $('#passengerDto_form_birthDate').val(birthDate);
            autoSubmitMore('/railroad/passenger/tickets', "currentPassenger");
        })
    }
    function autoSubmitMore(url, entity) {
        let form = document.getElementById(entity);
        form.action = url;
        form.submit();
    }
    getUserPassengers();


});
