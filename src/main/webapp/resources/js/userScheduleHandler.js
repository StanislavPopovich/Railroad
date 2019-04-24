$(document).ready(function () {

    $('#find_schedule').on('click', function () {
        var schedule = {};
        schedule["station"] = $("#start").val();
        schedule["date"] = $("#date").val();
        $.ajax({
            url : '/railroad/user/schedule/get-schedule-by-station-date',
            type : "POST",
            data : schedule,
            dataType : "json",
            success: function (data) {
               console.log(data);
                /*var markup;
                if(data.length === 0){
                    markup = '<div class="not_found">' + findTableText[local].trainNotFound + '</div>';
                    $('#find').append().html(markup);

                } else {
                    markup = '<table class="header_table"><tr><th>' + findTableText[local].trainNumber +
                        '</th><th>' + findTableText[local].departureStation + '</th><th>' +
                        findTableText[local].arrivalStation + '</th><th>' + findTableText[local].departureDate + '</th>'
                        + '<th>' + findTableText[local].arrivalDate + '</th><th>' + findTableText[local].tickets +
                        '</th><th class="th_hidden"></th></tr></table>';
                    markup+='<div class="find_content"><table id="tickets_table_buy">';

                    for(var i = 0; i < data.length; i++){
                        markup+='<tr>';
                        markup+= '<td>' + data[i].number + '</td>';
                        markup+= '<td>' + stations.departStation + '</td>';
                        markup+= '<td>' + stations.arrivalStation + '</td>';
                        markup+= '<td>' + data[i].departDate + '</td>';
                        markup+= '<td>' + data[i].arrivalDate + '</td>';
                        markup+= '<td>' + data[i].seats + '</td>';
                        markup+='<td class="btn btn_edit"><button>'+ findTableText[local].buyButton + '</button></td>';
                        markup+='</tr>';
                    }
                    markup+='</table></div>';
                    markup += '<form id="trainForm" action="/railroad/passenger/add" method="post">' +
                        '<input id="ticket_buy_form_trainNumber" name="number" type="hidden" value>' +
                        '<input id="ticket_buy_form_departDate" name="departDate" type="hidden" value>' +
                        '<input id="ticket_buy_form_arrivalDate" name="arrivalDate" type="hidden" value>' +
                        '<input id="ticket_buy_form_stations" name="stations" type="hidden" value>';
                    $('#find').append().html(markup);
                    buyTicketButton();
                }*/
            }
        })
    });
});