$(document).ready(function () {

    var btnTrainNumber = document.getElementById("btn_train_number");

    if(btnTrainNumber) {
       btnTrainNumber.addEventListener("click", getFieldsForAddSchedules, false);
    }

    function getFieldsForAddSchedules() {
        var trainNumber = document.getElementById("train_number");
        var value = trainNumber.options[trainNumber.selectedIndex].text;



        if(value !== "Train number"){
            var train = {};
            train["trainNumber"] = value;
            $.ajax({
                url: "/railroad/schedule/get-train",
                type : "POST",
                data : train,
                dataType : "json",
                success: function (data) {
                   var markup;
                   markup = '<h2>Add dates to stations</h2><div class="schedules_list">';
                    for(var i = 0; i < data.stations.length; i++){
                        markup += '<div class="schedules_item">' +
                            '<div id="station">' + data.stations[i] + '</div>' +
                            '<div>' +
                            '<h2>Arrival date</h2>' +
                            '<input id="arrival_date" type="text" placeholder="yyyy-MM-dd HH:mm">' +
                            '</div>' +
                            '<div>' +
                            '<h2>Departing date</h2>'+
                            '<input id="departing_date" type="text" placeholder="yyyy-MM-dd HH:mm">'+
                            '</div>'+
                        '</div>';
                    }
                    markup += '</div><div id="add_train_schedule" class="add_train_schedule btn btn_blue">Add train to schedule</div>';
                    markup += '<input hidden id="train_number" value="' + data.number + '">';
                    $('#schedules_list').append().html(markup);

                    var addTrainSchedule = document.getElementById("add_train_schedule");
                    if(addTrainSchedule) {
                        addTrainSchedule.addEventListener("click", addTrainToSchedule, false);
                    }
                }
            })
        }

    }

    function  addTrainToSchedule() {
        var item = document.querySelectorAll(".schedules_item");
        var firstDate =item[0].querySelector("#departing_date").value.split(" ");
        var trainNumber = document.querySelector("#train_number").value;
        for(var i = 0; i < item.length; i++){
            var scheduleDto ={};
            scheduleDto["stationName"] = item[i].querySelector("#station").textContent;
            scheduleDto["arrivalDate"] = item[i].querySelector("#arrival_date").value;
            scheduleDto["departDate"] = item[i].querySelector("#departing_date").value;
            scheduleDto["departDateFromFirstStation"] = firstDate[0];
            scheduleDto["trainNumber"] = trainNumber;
            setTimeout(function () {

            }, 200);
            $.ajax({
                url: "/railroad/schedule/add-success",
                type : "POST",
                data : JSON.stringify(scheduleDto),
                contentType: 'application/json'
            })
        }
        window.location.href = "/railroad/user";
    }

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

});

