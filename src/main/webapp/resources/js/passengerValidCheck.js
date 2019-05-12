var passengerName = document.getElementById("name");
var lastName = document.getElementById("lastname");
var birthDate = document.getElementById("birthDate");
var button = document.getElementById("b_button");
var email = document.getElementById("email");
var lastNameReg = /^[a-zA-Z]+((['-][a-zA-Z ])?[a-zA-Z]*)*$/;
var nameReg = /^[a-zA-Z]*$/;
var emailReg = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
var birthDateReg = /^([0-9]{4})-([0-9]{2})-([0-9]{2})$/;
function checkLastName() {
    if(lastName.value === "" || lastName.value.length < 1){
        getMessage("It is required field", "error_lastName");
        if (lastName.classList.contains("correct")) {
            lastName.classList.remove("correct");

            turnOnButton();
        }
    }else if (!lastName.value.match(lastNameReg)) {
        getMessage("Insert only letters", "error_lastName");
        if (lastName.classList.contains("correct")) {
            lastName.classList.remove("correct");

            turnOnButton();
        }
    } else {
        deleteMessage("error_lastName");
        if (!lastName.classList.contains("correct")) {
            lastName.classList.add("correct");
            turnOnButton();
        }
    }
}

function checkEmail() {
    if(email.value === "" || email.value.length < 1){
        getMessage("It is required field", "error_email");
        if (email.classList.contains("correct")) {
            email.classList.remove("correct");
            turnOnButton();
        }
    }else if (validateEmail(email.value)) {
        deleteMessage("error_email");
        if (!email.classList.contains("correct")) {
            email.classList.add("correct");
            turnOnButton();
        }
    } else {
        getMessage("Required format: example@email.com", "error_email");
        if (email.classList.contains("correct")) {
            email.classList.remove("correct");
            turnOnButton();
        }
    }
}

function validateEmail(email) {
    return emailReg .test(email);
}

function checkName() {
    if(passengerName.value === "" || passengerName.value.length < 1){
        getMessage("It is required field", "error_name");
        if (passengerName.classList.contains("correct")) {
            passengerName.classList.remove("correct");
            turnOnButton();
        }
    }else if (!passengerName.value.match(nameReg)) {
        getMessage("Insert only letters", "error_name");
        if (passengerName.classList.contains("correct")) {
            passengerName.classList.remove("correct");
            turnOnButton();
        }
    } else {
        deleteMessage("error_name");
        if (!passengerName.classList.contains("correct")) {
            passengerName.classList.add("correct");
            turnOnButton();
        }
    }
}

function checkBirthDate() {
    var date = birthDate.value.split("-");
    if (birthDate.value === "" || birthDate.value.length < 1) {
        getMessage("It is required field", "error_birthDate");
        if (birthDate.classList.contains("correct")) {
            birthDate.classList.remove("correct");
            turnOnButton();
        }
    }else if (!birthDate.value.match(birthDateReg)) {
        getMessage("Required format: yyyy-MM-dd", "error_birthDate");
        if (birthDate.classList.contains("correct")) {
            birthDate.classList.remove("correct");
            turnOnButton();
        }
    }else if(!validYear(date[0])){
        getMessage("Insert date between 1900 and 2019", "error_birthDate");
        if (birthDate.classList.contains("correct")) {
            birthDate.classList.remove("correct");
            turnOnButton();
        }
    }else if(!validMonth(date[1])){
        getMessage("Incorrect value of month", "error_birthDate");
        if (birthDate.classList.contains("correct")) {
            birthDate.classList.remove("correct");
            turnOnButton();
        }
    }else if(!validDay(date[3], date[2], date[3])){
        getMessage("Incorrect value of date", "error_birthDate");
        if (birthDate.classList.contains("correct")) {
            birthDate.classList.remove("correct");
            turnOnButton();
        }
    }else {
        deleteMessage("error_birthDate");
        if (!birthDate.classList.contains("correct")) {
            birthDate.classList.add("correct");
            turnOnButton();
        }
    }
}


function validDay(day, month, year){
    if(month === 1 || month === 3 || month === 5 || month === 7 || month === 8 || month === 10 || month === 12){
        if(day < 1 || day > 31){
            return false;
        }
    }else if(month === 2){
        if(isLeapYear(year)){
            if(day < 1 || day > 29){
                return false;
            }
        }else{
            if(day < 1 || day > 28){
                return false;
            }
        }
    }else {
        if(day < 1 || day > 30){
            return false;
        }
    }
    return true;
}

function isLeapYear(year) {
    var leapYear = [];
    var j = 0;
    for(var i = 1904; i < 2100; i +=4){
        leapYear[j] = i;
        j++;
    }
    for(var i = 0; i < leapYear.length; i++){
        if(year === leapYear[i]){
            return true;
        }
    }
    return false;
}

function validMonth(month) {
    if(month < 1 || month > 12){
        return false;
    }
    return true;
}

function validYear(year) {
    if(year < 1900 || year > 2019){
        return false;
    }
    return true;
}

function turnOnButton() {
    var wrapperInput = document.querySelectorAll(".wrapper_input");
    for(var i = 0; i < wrapperInput.length; i++) {
        var input = wrapperInput[i].getElementsByTagName("input");
        if (input[0].classList.contains("correct")) {
            button.disabled = false;
            button.classList.add("active");
        }
        else {
            button.classList.remove("active");
            return button.disabled = true;
        }
    }
}
function getMessage(message, id) {
    document.getElementById(id).textContent = message;
}
function deleteMessage(id) {
    document.getElementById(id).textContent = "";
}