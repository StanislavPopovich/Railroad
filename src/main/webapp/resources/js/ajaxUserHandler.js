$(document).ready(function () {
    var local = "ru";
    var findTableText = {
        ru: {
            userName: "Пользователь",
            userRole: "Роль",
            userNotFound: "Пользователи не найдены",
            editButton: "Изменить",
            deleteButton: "Удалить",
            trainNumber: "Номер поезда",
            seats: "Количество мест",
            route: "Маршрут",
            trainsNotFound: "Поезда не найдены"
        },
        en: {
            userName: "User name",
            userRole: "Role",
            userNotFound: "Users not found",
            editButton: "Edit",
            deleteButton: "Delete",
            trainNumber: "Train number",
            seats: "Seats",
            route: "Route",
            trainsNotFound: "Trains not found"
        }
    };

    //    Start user page for Admin
    function getStartAdminPage(){
        $.ajax({
            url : '/railroad/user/admin',
            type : "GET",
            dataType : "json",
            success: function (data) {
                var  markup = '<table class="header_table"> <tr> <th>' + findTableText[local].userName +
                    '</th> <th>' + findTableText[local].userRole + '</th><th class="th_hidden"></th>' +
                    '<th class="th_hidden"></th></tr></table>'
                markup+='<div class="find_content"><table>';
                if(data.length === 0){
                    markup+= '<tr><th>' + findTableText[local].userNotFound + '</th></tr>'
                }
                console.log(data);
                for(var i = 0; i < data.length; i++){
                    markup+='<tr>';
                    markup+= '<td>' + data[i].userName+ '</td>';
                    markup+= '<td>'
                    for(var j = 0; j < data[i].roles.length; j++){
                        markup+= data[i].roles[j] + ' ';
                    }
                    markup+= '</td>';
                    markup+='<td class="btn btn_edit">' + '<a href="/railroad/user/edit-user/'
                        + data[i].userName +'">' + findTableText[local].editButton + '</a></td>';
                    markup+='<td class="btn btn_delete">' + '<a href="/railroad/user/delete-user/'
                        + data[i].userName +'">' + findTableText[local].deleteButton + '</a></td>';
                    markup+='</tr>';
                }
                markup+='</table></div>';
                $('#find_users').append().html(markup);
            }
        });
    }


    //    Start user page for Moderator
    function getStartModeratorPage(){
        $.ajax({
            url : '/railroad/user/moderator',
            type : "GET",
            dataType : "json",
            success: function (data) {
                var  markup = '<table class="header_table"> <tr> <th>' + findTableText[local].trainNumber +
                    '</th> <th>' + findTableText[local].seats + '</th> <th class="th_route">' + findTableText[local].route +
                    '</th><th class="th_hidden"></th>' +
                    '<th class="th_hidden"></th></tr></table>'
                markup+='<div class="find_content"><table>';
                if(data.length === 0){
                    markup+= '<tr><th>' + findTableText[local].trainsNotFound + '</th></tr>'
                }
                console.log(data);
                for(var i = 0; i < data.length; i++){
                    markup+='<tr>';
                    markup+= '<td>' + data[i].number + '</td>';
                    markup+= '<td>' + data[i].seats + '</td>';
                    markup+= '<td class="td_route">'
                    for(var j = 0; j < data[i].stations.length; j++){
                        if(j != data[i].stations.length - 1){
                            markup+= data[i].stations[j] + ' => ';
                        }else{
                            markup+= data[i].stations[j];
                        }
                    }
                    markup+= '</td>';
                    /*markup+='<td class="btn btn_edit">' + '<a href="/railroad/user/edit-user/'
                        + data[i].userName +'">' + findTableText[local].editButton + '</a></td>';
                    markup+='<td class="btn btn_delete">' + '<a href="/railroad/user/delete-user/'
                        + data[i].userName +'">' + findTableText[local].deleteButton + '</a></td>';*/
                    markup+='</tr>';
                }
                markup+='</table></div>';
                $('#find_trains').append().html(markup);
            }
        });
    }

        function getPageForRole(){
        var aaa = document.getElementById("find_users");
        var bbb = document.getElementById("find_trains");
            if(aaa){
                getStartAdminPage();
            }else if(bbb){
                getStartModeratorPage();
            }else{

            }
        }
        getPageForRole();


});