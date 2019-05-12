document.addEventListener("DOMContentLoaded", eventDropdown, false);

function eventDropdown (){
    let fieldsDropdown = document.querySelectorAll(".dropdown");

    for(let i = 0; i < fieldsDropdown.length; i++) {
        fieldsDropdown[i].addEventListener("click", dropdownInit.clickFieldsDropdown, false);
    }
}

let dropdownInit = {
    clickFieldsDropdown: function (event) {

        dropdownInit.dropdown (event);
        let dropdownList = event.target.parentNode.querySelectorAll("ul li");
        dropdownList.forEach(function(element) {
            element.addEventListener("click", dropdownInit.dropdown, false);
            element.addEventListener("click", dropdownInit.selectionList, false);
        });

        document.addEventListener("click", dropdownInit.anywhere, false);

    },
    selectionList: function (event) {

        let item = event.target;
        let itemValue = event.target.textContent;
        let dropdownInput = item.parentNode.parentNode.querySelector(".js-dropdown_value");
        dropdownInput.value = itemValue;
        let dropdownField = item.parentNode.parentNode.querySelector(".dropdown");
        dropdownField.textContent = itemValue;
    },
    anywhere: function(event) {

        if(event.target.tagName !== "LI" && event.target.className !== 'dropdown_value dropdown'){
            let dropdownSelects = document.querySelectorAll(".js-dropdown");
            for(let i = 0; i < dropdownSelects.length; i++){
                dropdownSelects[i].classList.remove("open");
            }
        }

    },
    dropdown: function (event) {

        let element = event.target;
        let selectCustom = element.parentNode;

        if(selectCustom.classList.contains("js-dropdown")) {
            if(selectCustom.classList.contains("open")) {
                selectCustom.classList.remove("open");
            } else {
                selectCustom.classList.add("open");
            }
        } else {
            selectCustom.parentNode.classList.remove("open");
        }
    }
};
