$(document).ready(function () {

    var btnTrainNumberAdd = document.getElementById("btn_train_number_add");
    var btnTrainNumberDelete = document.getElementById("btn_train_number_delete");
    var btnTrainNumberCnahge= document.getElementById("btn_train_number_change");

    if(btnTrainNumberAdd) {
        btnTrainNumberAdd.addEventListener("click", getFieldsForAddSchedules, false);
    }
    if(btnTrainNumberDelete){
        btnTrainNumberDelete.addEventListener("click", deleteSchedule,false);
    }

    if(btnTrainNumberCnahge){
        btnTrainNumberCnahge.addEventListener("click", changeSchedule,false);
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
                   markup = '<div class="schedules_list">';
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
                    markup += '</div><div id="add_train_schedule" class="add_train_schedule btn btn_blue">Add</div>';
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
            $.ajax({
                url: "/railroad/schedule/add",
                type : "POST",
                data : JSON.stringify(scheduleDto),
                contentType: 'application/json'
            });

        }
        window.location.href = "/railroad/user";
    }

    function changeSchedule(){
        var trainNumber = document.getElementById("train_number");
        var date= document.getElementById("train_dates");
        var trainValue = trainNumber.options[trainNumber.selectedIndex].text;
        var dateValue = date.options[date.selectedIndex].text;
        if(trainValue !== "Train number" && dateValue !== "Departing date"){
            var train = {};
            train["trainNumber"] = trainValue;
            train["date"] = dateValue;
            $.ajax({
                url: "/railroad/schedule/get-schedule-for-train",
                type : "POST",
                data : train,
                dataType : "json",
                success: function (data) {
                    var markup;
                    markup = '<div class="schedules_list">';
                    for(var i = 0; i < data.length; i++){
                        markup += '<div class="schedules_item">' +
                            '<div id="station">' + data[i].stationName+ '</div>' +
                            '<div>' +
                            '<h2>Arrival date</h2>' +
                            '<input id="arrival_date" type="text" placeholder="yyyy-MM-dd HH:mm" value="' +
                             data[i].arrivalDate + '">' +
                            '</div>' +
                            '<div>' +
                            '<h2>Departing date</h2>'+
                            '<input id="departing_date" type="text" placeholder="yyyy-MM-dd HH:mm" value="' +
                            data[i].departDate + '">' +
                            '</div>'+
                            '</div>';
                    }
                    markup += '</div><div id="change_train_schedule" class="add_train_schedule btn btn_blue">Change</div>';
                    markup += '<input hidden id="train_number" value="' + data[0].trainNumber+ '">';
                    markup += '<input hidden id="train_oldDate" value="' + data[0].oldDepartDateFromFirstStation + '">';
                    $('#schedules_list').append().html(markup);

                    var changeTrainSchedule = document.getElementById("change_train_schedule");
                    if(changeTrainSchedule) {
                        changeTrainSchedule.addEventListener("click", changeScheduleOfTrain, false);
                    }
                }
            })
        }
    }

    function  changeScheduleOfTrain() {
        var item = document.querySelectorAll(".schedules_item");
        var firstDate =item[0].querySelector("#departing_date").value.split(" ");
        var trainNumber = document.querySelector("#train_number").value;
        var oldDate = document.querySelector("#train_oldDate").value;
        for(var i = 0; i < item.length; i++){
            var scheduleDto ={};
            scheduleDto["stationName"] = item[i].querySelector("#station").textContent;
            scheduleDto["arrivalDate"] = item[i].querySelector("#arrival_date").value;
            scheduleDto["departDate"] = item[i].querySelector("#departing_date").value;
            scheduleDto["departDateFromFirstStation"] = firstDate[0];
            scheduleDto["trainNumber"] = trainNumber;
            scheduleDto["oldDepartDateFromFirstStation"] = oldDate;
            $.ajax({
                url: "/railroad/schedule/update",
                type : "POST",
                data : JSON.stringify(scheduleDto),
                contentType: 'application/json'
            });

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

    function deleteSchedule() {
        var schedule = {};
        schedule["trainNumber"] = $("#train_number").val();
        schedule["date"] = $("#train_dates").val();
        $.ajax({
            url : '/railroad/schedule/delete-train-success',
            type : "POST",
            data : schedule,
            dataType : "json"
        });
        window.location.href = "/railroad/user";
    }
});

