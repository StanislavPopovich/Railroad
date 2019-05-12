$(document).ready(function () {
    function getAllStations() {
        $.ajax({
            url : '/railroad/stations',
            type : "GET",
            success : function (data) {
                let markup = '';
                for(let i = 0; i < data.length; i++){
                    markup+= '<li data-value="' + data[i] + '">' + data[i] + '</li>'
                }
                $('#from_stations').html(markup);
            }
        })
    }
    getAllStations();
});