$().ready(function () {
    $('#start_station').on('change',function () {
        var start_station = {};
        start_station["start"] = $("#start").val();
        $.ajax({
            url : '/dest-station',
            type : "POST",
            data : start_station,
            success : function (data) {
                var arrayStations =data.split('/');
                var markup = '';
                markup+= '<option value="0">Select</option>';
                for(var i = 0; i < arrayStations.length; i++){
                    markup+= '<option value="' + arrayStations[i] + '">' + arrayStations[i] + '</option>'
                }
                $('#end').html(markup)
            },
            error:function (data) {
                alert('error 11')
            }
        })
    })
    $('#end_station').on('change', function (event) {
        event.preventDefault();
        var stations = {};
        stations["start"] = $("#start").val();
        stations["end"] = $("#end").val();
        $.ajax({
            url : '/railroad/admin/all-routes',
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
                $('#routes').append().html(markup)
            },
            error:function (data) {
                alert('error')
            }
        })
    })
    $('#end_station_login').on('change', function (event) {
        event.preventDefault();
        var stations = {}
        stations["start"] = $("#start").val();
        stations["end"] = $("#end").val();
        $.ajax({
            url : 'railroad/find-trains',
            type : "POST",
            data : stations,
            dataType : "json",
            success : function(data){
                console.log(data);
                var markup = '<table> <tr> <th width="80">Train number</th> ' +
                    '<th width="80">Seats</th><th width="80"></th></tr>';
                if(data.length === 0){
                    markup+= '<tr><th>' + 'trains not found' + '</th></tr>'
                }
                for(var i = 0; i < data.length; i++){
                            markup+='<tr>';
                            markup+= '<th>' + data[i].number + '</th>';
                            markup+= '<th>' + data[i].seats + '</th>';
                            markup+='<th>' + '<a href="/railroad/user/buy-ticket/' + data[i].number + '">' + 'Buy' + '</a></th>';
                            markup+='</tr>';
                }
                markup+='</table>'
                $('#find').append().html(markup)
            },
            error:function (data) {
                alert('error find')
            }
        })
    })
});