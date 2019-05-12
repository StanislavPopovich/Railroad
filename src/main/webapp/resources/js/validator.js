$(document).ready(function () {
    window.locale = "en";
    let url = location.href.split("8081");
    let sectionUrl = url[1];
    switch (sectionUrl) {
        case "/railroad/station/add":
            addNewStation();
            break;
        case "/railroad/way/add":
            addNewWay();
            break;
        case "/railroad/registration":
            findAllInput();
            break;
    }
});


function addNewStation() {
    let elem = eventFunction(["firstStation", 'secondStation']),
        button = document.getElementById("button"),
        select = document.getElementById("from_stations");

    findAllSelect();
    findAllInput();

    elem[0].addEventListener("input", function (event) {
        validObjectOnLetters(event);
        checkAndDoActiveButton(elem, button);
    });
    select.addEventListener("click", function () {
        checkAndDoActiveButton(elem, button);
    });
}

function addNewWay() {

    let elem = eventFunction(["firstStation", 'secondStation']),
        button = document.getElementById("button");

    findAllSelect();
    findAllInput();

    for(let i = 0; i < elem.length; i++){
        let select = document.getElementById("to_stations");
        select.addEventListener("click", function () {
            checkAndDoActiveButton(elem, button);
        });
    }
}

function addNewTrain() {
    let elem = eventFunction(["train_number", 'number_seats']),
        button = document.getElementById("button");

    findAllSelect();
    findAllInput();

    if(elem[0] !== null){
        for(let i = 0; i < elem.length; i++){
            elem[i].addEventListener("input", function () {
                validObjectOnDigits(event, 4);
                checkAndDoActiveButton(elem, button);
            });
        }
    }

}

function eventFunction(arr) {
    let arr2 = [];
    for(let i = 0; i < arr.length; i++){
        arr2[i] = document.getElementById(arr[i]);
    }
    return arr2;
}

function findAllSelect() {
    let dropdownValue  = document.querySelectorAll(".dropdown_value");

    if(dropdownValue){
        for(let i = 0; i < dropdownValue.length; i++){
            dropdownValue[i].addEventListener("click", removeErrors, false);
        }
    }
}

function findAllInput() {
    let input  = document.querySelectorAll(".wrapper_input input");

    if(input){
        for(let i = 0; i < input.length; i++){
            input[i].addEventListener("click", removeErrors, false);
        }
    }
}

function removeErrors() {
    let backError = document.querySelector(".back_error"),
        errors = document.querySelectorAll(".error");
    if(backError){
        backError.innerHTML = "";
    }
    if(errors){
        for(let i = 0; i < errors.length; i++){
            errors[i].innerHTML = "";
        }
    }
}

function validObjectOnDigits(event, size) {
    let current = event.target,
        fieldError = current.nextElementSibling,
        val = current.value,
        result = validOnlyDigits(val);
    if(result === -1){
        let currentLocale = window.locale;
        setErrorMessage(message[currentLocale].onlyDigitsError, fieldError);
    } else {
        if(size){
            result = checkSizeNumber(val, size);

            if(result === false){
                let currentLocale = window.locale;
                setErrorMessage(message[currentLocale].countDigitsError + size, fieldError);
            } else{
                removeErrorMessage(fieldError);
            }
        }else{
            removeErrorMessage(fieldError);
        }
    }
}

function validObjectOnLetters(event) {
    let current = event.target,
        fieldError = current.nextElementSibling,
        val = current.value,
        result = validCitiesNames(val);

    if(result === -1){
        let currentLocale = window.locale;
        setErrorMessage(message[currentLocale].onlyLettersError, fieldError);
    } else {
        removeErrorMessage(fieldError);
    }
}

function checkInputFields(elements){
    let result = false;
    for(let i = 0; i < elements.length; i++){
        let val = elements[i].value.trim();
        if(val){
            let errorField = elements[i].nextElementSibling.classList.contains("error");
            if(errorField) {
                let error = elements[i].nextElementSibling.textContent;
                if(error === ""){
                    result = true;
                } else {
                    result = false;
                    return result;
                }
            } else {
                result = true;
            }
        } else {
            result = false;
            return false;
        }
    }
    return result;
}

function checkSizeNumber(val, size) {
    if(val.length > size){
        return false;
    }
    return true;
}

function turnButtonOn(button) {
    button.classList.add("active");
    button.disabled = false;
}

function turnButtonOff(button) {
    button.classList.remove("active");
    button.disabled = true;
}


/*  */
function setErrorMessage(message, fieldError) {
    fieldError.textContent = message;
}

function removeErrorMessage(fieldError) {
    fieldError.textContent = "";
}

function validCitiesNames(field) {
    return field.search(/^[a-zA-Z]+[\-'\s]?[a-zA-Z ]+$/);
}

function validOnlyDigits(field) {
    return field.search(/^[0-9]+$/);
}

function checkAndDoActiveButton(elem, button){
    let isValid = checkInputFields(elem);
    if(isValid){
        turnButtonOn(button);
    } else {
        turnButtonOff(button);
    }
}

function checkDateFormatForSchedule(field) {
    let regex = /[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1]) (2[0-3]|[01][0-9]):[0-5][0-9]/;
    return field.search(regex);

}

function checkDates(firstDate, secondDate) {
    if(firstDate < secondDate){
        return true;
    }else{
        return false;
    }
}