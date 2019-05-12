$(document).ready(function () {
    function getAllStations() {
        $.ajax({
            url: '/railroad/stations',
            type: "GET",
            success: function (data) {
                let markup = '';
                for (let i = 0; i < data.length; i++) {
                    markup += '<li data-value="' + data[i] + '">' + data[i] + '</li>'
                }
                $('#from_stations').html(markup);
            }
        })
    }
    getAllStations();

    // Returns station without departing station
    $('#from_stations').on('click',function () {
        let station = {};
        station["departStation"] = $("#firstStation").val();
        $.ajax({
            url : '/railroad/dest-station',
            type : "POST",
            data : station,
            success : function (data) {
                let markup = '';
                for(let i = 0; i < data.length; i++){
                    markup+= '<li data-value="' + data[i] + '">' + data[i] + '</li>'
                }
                $('#to_stations').html(markup);
            }
        })
    });

});