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

    // Returns station without departing station
    $('#from_stations').on('click',function () {
        let station = {};
        station["departStation"] = $("#depart_station").val();
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

    $('#findBtn').on('click',function (event) {
        let isActive = event.target.classList.contains("active");
        if(isActive){
            autoSubmitFindButton();
        }
    });

    function autoSubmitFindButton() {
        let form =document.getElementById("searchData");
        form.submit();
    }
});