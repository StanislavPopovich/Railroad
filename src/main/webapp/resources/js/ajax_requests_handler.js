$().ready(function () {
    $('#start_station').on('change',function () {
        // var station = $("#start").val();
        var station = {};
        station["start"] = $("#start").val();
        $.ajax({
            url : '/railroad/dest-station',
            type : "POST",
            data : station,
            success : function (data) {
                var markup = '';
                markup+= '<option value="0">Select</option>';
                for(var i = 0; i < data.length; i++){
                    markup+= '<option value="' + data[i] + '">' + data[i] + '</option>'
                }
                $('#end').html(markup);
            }
        })
    });
    $('#end_station').on('change', function () {
        var stations = {};
        stations["start"] = $("#start").val();
        stations["end"] = $("#end").val();
        $.ajax({
            url : '/railroad/user/all-routes',
            type : "POST",
            data : stations,
            dataType : "json",
            success : function(data){
                console.log(data);
                var markup = '';
                markup+= '<option value="0">Select</option>';
                for(var i = 0; i < data.length; i++){
                    markup+= '<option value="' + data[i] + '">' + data[i] + '</option>'
                }
                $('#routes').append().html(markup);
            },
        })
    });

    /*$('#end_station_login').on('change', function () {
        var stations = {};
        stations["start"] = $("#start").val();
        stations["end"] = $("#end").val();
        $.ajax({
            url : '/railroad/find-trains',
            type : "POST",
            data : stations,
            dataType : "json",
            success : function(data){
                console.log('find-trains',data);
                console.log(data);
                var markup = '</div><table class="header_table"> <tr> <th>Train number</th> ' +
                    '<th>Seats</th><th></th></tr></table>';
                markup+='<div class="find_content"><table>';
                if(data.length === 0){
                    markup+= '<tr><th>' + 'trains not found' + '</th></tr>'
                }
                for(var i = 0; i < data.length; i++){
                            markup+='<tr>';
                            markup+= '<td>' + data[i].number + '</td>';
                            markup+= '<td>' + data[i].seats + '</td>';
                            markup+='<td>' + '<a class="buy_button" href="/railroad/user/buy-ticket/' + data[i].number + '">' + 'Buy' + '</a></td>';
                            markup+='</tr>';
                }
                markup+='</table></div>';
                $('#find').append().html(markup);
                console.log("find train")
            },
            error:function (data) {
                alert('error find')
            }
        })
    });*/
    $('#findButton').on('submit', function (event) {
        event.preventDefault();
        var stations = {};
        stations["start"] = $("#start").val();
        stations["end"] = $("#end").val();
        stations["date"] = $("#date").val();
        console.log(stations);
        $.ajax({
            url : 'railroad/find-trains-with-date',
            type : "POST",
            data : stations,
            dataType : "json",
            success: function (data) {
                var markup = '</div><table class="header_table"> <tr> <th>Train number</th> ' +
                    '<th>Departure station</th><th>Destination station</th><th>Departure date</th><th>Seats</th><th></th></tr></table>';
                markup+='<div class="find_content"><table>';
                if(data.length === 0){
                    markup+= '<tr><th>' + 'trains not found' + '</th></tr>'
                }
                for(var i = 0; i < data.length; i++){
                    markup+='<tr>';
                    markup+= '<td>' + data[i].number + '</td>';
                    markup+= '<td>' + stations.start + '</td>';
                    markup+= '<td>' + stations.end + '</td>';
                    markup+= '<td>' + data[i].departDate + '</td>';
                    markup+= '<td>' + data[i].seats + '</td>';
                    markup+='<td>' + '<a class="buy_button" href="/railroad/user/buy-ticket/' + data[i].number + '/' + data[i].departDate + '">' + 'Buy' + '</a></td>';
                    markup+='</tr>';
                }
                markup+='</table></div>';
                $('#find').append().html(markup);
                console.log("find train")

            }
        })
    })
});