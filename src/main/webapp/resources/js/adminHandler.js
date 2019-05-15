$().ready(function () {
    window.locale = "en";
    $.ajax({
        url : '/railroad/admin/get-users',
        type : "GET",
        success: function (data) {
            let markup = '';
            for(let i = 1; i < data.length; i++){
                markup += '<div class="item"><div class="item_select">' +
                    '<input type="radio" name="user" id="select_' + i + '"></div>';
                markup += '<div class="wrapper_user"><div class="login">' + data[i].userName +'</div>';
                markup += '<div class="role">' + data[i].roles[0] + '</div></div><div id="wrap_user_edit" class="wrapper_dropdown_user"></div></div>';
            }
            $('#items').append().html(markup);
            let items = document.getElementById("items");
            items.addEventListener("click", selectUser, false);
        }
    });


});

function selectUser(event) {
    let currentItem = event.target;
    if(currentItem.tagName === "INPUT"){
        console.log(currentItem.checked);
        if(currentItem.checked){
            let btnEditRole = document.getElementById("btn_edit_role");
            let btnRemove = document.getElementById("btn_remove");
            let item = FindElem(currentItem, "item");

            turnButtonOn(btnEditRole);
            turnButtonOn(btnRemove);
            removeEditMenu();
            btnEditRole.addEventListener("click", function(){
                editRoleAdminPanel(item);
            });
            btnRemove.addEventListener("click", removeUser, false);

        }

    }

}
function removeUser() {
    let items = document.getElementById("items");
    let radio = items.querySelector("input[type=radio]:checked");
    let item = FindElem(radio, "item");
    let login = item.querySelector(".login").textContent;

    $.ajax({
        url : '/railroad/admin/user-delete',
        type : "POST",
        data : {"userName": login},
        dataType : "json",
    });
    window.location.href = "/railroad/admin/users";
}

function removeEditMenu(){

    let item = document.querySelectorAll(".item");

    for(let i = 0; i < item.length; i++){

        let wrapUserEdit = item[i].querySelector(".wrapper_dropdown_user");
        if(wrapUserEdit.innerHTML !== ""){
            wrapUserEdit.innerHTML = "";
        }
    }
}

function editRoleAdminPanel(item) {
    let currentLocale = window.locale;
    $.ajax({
        url : '/railroad/admin/get-all-roles',
        type : "GET",
        success: function (data) {
            let markup = '<div class="wrapper_dropdown">' +
                '       <div class="wrapper_dropdown_in js-dropdown">' +
                '            <span class="dropdown_value dropdown">' + message[currentLocale].adminRoles + '</span>' +
                '            <input id="role" class="js-dropdown_value dropdownCheck" type="text" name="departStation">' +
                '             <ul id="roles">';
            for(let i = 0; i < data.length; i++){
                markup+= '<li data-value="' + data[i].name + '">' + data[i].name + '</li>';
            }
            markup +='</ul>' +
                '       </div>' +
                '    </div>' +
                '    <div class="wrap_btn">' +
                '       <div id="btn_edit" class="btn btn_blue">' +  message[currentLocale].adminEditButton + '</div>' +
                '    </div>';


            let radio = item.querySelector("input[type=radio]:checked");
            if(radio){
                let wrapUserEdit = item.querySelector(".wrapper_dropdown_user");
                wrapUserEdit.innerHTML = markup;
                eventDropdown();
                let btnEdit = item.querySelector("#btn_edit");
                btnEdit.addEventListener("click", function(){
                    editRole(item);
                });

            }
        }
    });

}

function editRole(item) {
    let userName = item.querySelector(".login").textContent;
    let role= item.querySelector("#role").value;
    let object = {"userName": userName, "role": role};
    if(role !== ""){
        $.ajax({
            url : '/railroad/admin/update',
            type : "POST",
            data : object,
            dataType : "json",
        });
        window.location.href = "/railroad/admin/users";
    }

}

function FindElem(self, classElem){
    let item = self.parentElement;
    if(item.classList.contains(classElem)){
        return item;
    } else {
        return FindElem(item, classElem);
    }
}



