$(document).ready(function () {
    window.locale = "en";
    let btnTrainNumberCnahge= document.getElementById("btn_train_date_select");

    if(btnTrainNumberCnahge) {
        btnTrainNumberCnahge.addEventListener("click", getTrainPassengers, false);
    }


    function updateContent(){

        let trainDate = document.getElementById("train_date");
        let select = FindElem(trainDate, "wrapper_dropdown_in");

        select.querySelector(".dropdown_value").textContent = "Train dates";

        let btnTrainNumberSelect = document.getElementById("btn_train_date_select");
        turnButtonOff(btnTrainNumberSelect);
        btnTrainNumberSelect.removeEventListener("click", getTrainPassengers);
    }

    function trainNumber() {
        $.ajax({
            url: '/railroad/train/all-from-schedule',
            type: "GET",
            success: function (data) {
                let markup = "";
                for (let i = 0; i < data.length; i++) {
                    markup += '<li data-value="' + data[i] + '">' + data[i] + '</li>';
                }
                $('#train_numbers').append().html(markup);
            }
        })
    }
    trainNumber();

    //getting departing dates for selected train
    $('#train_numbers').on('click',function () {
        let train= {};
        train["trainNumber"] = $("#train_number").val();
        $.ajax({
            url : '/railroad/schedule/get-depart-dates',
            type : "POST",
            data : train,
            success : function (data) {
                let markup = '';
                for(let i = 0; i < data.length; i++){
                    markup+= '<li data-value="' + data[i] + '">' + data[i] + '</li>'
                }
                $('#train_dates').html(markup);
            }
        })

        updateContent();

    });

    $('#train_dates').on('click',function () {

        let btnTrainNumberSelect = document.getElementById("btn_train_date_select");
        turnButtonOn(btnTrainNumberSelect);
        btnTrainNumberSelect.addEventListener("click", getTrainPassengers, false);

    });


    function getTrainPassengers() {
        let currentLocale = window.locale;
        let data= {};
        data["trainNumber"] = $("#train_number").val();
        data["date"] = $("#train_date").val();
        $.ajax({
            url : '/railroad/passenger/train/all',
            type : "POST",
            data : data,
            dataType : "json",
            success: function (data) {
                console.log(data);
                let  markup = '';
                if(data.length === 0){
                    markup+= '<div class="not_found">' +  message[currentLocale].haveNotPassengers + '</div>';
                }else{
                    markup = '';
                    for(let i = 0; i < data.length;i++ ){
                        markup += '' +
                        '<div class="item">' +
                            '<div class="wrap_passenger">' +
                                '<div class="info">' +
                                    '<div class="label">' +
                                    '   <span>' +  message[currentLocale].passenger + '</span>' +
                                    '</div>' +
                                    '<div>' + data[i].lastName + ' ' + data[i].name + '</div>' +
                                '</div>       ' +
                                '<div class="info">' +
                                '   <div class="label">' +
                                '       <span>' +  message[currentLocale].birthDate + '</span>' +
                                '   </div>' +
                                '   <div>'+ data[i].birthDate + '</div>' +
                                '</div>' +
                            '</div>' +
                        '</div>';
                    }
                }
                $('#items').append().html(markup);
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
});