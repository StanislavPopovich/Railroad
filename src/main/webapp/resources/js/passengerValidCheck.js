var passengerName = document.getElementById("name");
var lastName = document.getElementById("lastname");
var birthDate = document.getElementById("birthDate");
var button = document.getElementById("b_button");
var alphaExp = /^[a-zA-Z]+$/;

function checkLastName() {
    console.log(lastName);
    if (lastName.value === "" || lastName.value.length < 1 || !lastName.value.match(alphaExp)) {
        getMessage("Last name must consist of only letters", "error_lastName");
        if (lastName.classList.contains("correct")) {
            lastName.classList.remove("correct");

            turnOnButton();
        }
    }
    else {
        deleteMessage("error_lastName");
        if (!lastName.classList.contains("correct")) {
            lastName.classList.add("correct");
            turnOnButton();
        }
    }
}

function checkName() {
    if (passengerName.value === "" || passengerName.value.length < 1 || !passengerName.value.match(alphaExp)) {
        getMessage("Name must consist of only letters", "error_name");
        if (passengerName.classList.contains("correct")) {
            passengerName.classList.remove("correct");
            turnOnButton();
        }
    }
    else {
        deleteMessage("error_name");
        if (!passengerName.classList.contains("correct")) {
            passengerName.classList.add("correct");
            turnOnButton();
        }
    }
}

function checkBirthDate() {
    if (birthDate.value === "" || birthDate.value.length < 1) {
        getMessage("Required", "error_birthDate");
        if (birthDate.classList.contains("correct")) {
            birthDate.classList.remove("correct");
            turnOnButton();
        }
    }
    else {
        deleteMessage("error_birthDate");
        if (!birthDate.classList.contains("correct")) {
            birthDate.classList.add("correct");
            turnOnButton();
        }
    }
}

function turnOnButton() {
    var inputs = document.getElementsByTagName("input");

    for(var i = 0; i < inputs.length; i++) {
        if (inputs[i].classList.contains("correct")) {
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