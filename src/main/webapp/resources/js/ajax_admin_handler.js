$().ready(function () {
    var local = "ru";
    var findTableText = {
        ru: {
            userName: "Пользователь",
            userRole: "Роль",
            userNotFound: "Пользователи не найдены",
            editButton: "Изменить",
            deleteButton: "Удалить"

        },
        en: {
            userName: "User name",
            userRole: "Role",
            userNotFound: "Users not found",
            editButton: "Edit",
            deleteButton: "Delete"
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
                $('#find_user').append().html(markup);
            }
        })
    }

    console.log("dfdf");
    getStartAdminPage();
});