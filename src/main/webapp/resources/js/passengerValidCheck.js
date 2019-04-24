var passengerName = document.getElementById("name");
var lastName = document.getElementById("lastname");
var birthDate = document.getElementById("birthDate");
var button = document.getElementById("b_button");
var email = document.getElementById("email");
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

function checkEmail() {
    var value = email.value;

    if (validateEmail(value)) {
        deleteMessage("error_email");
        if (!email.classList.contains("correct")) {
            email.classList.add("correct");
            turnOnButton();
        }
    } else {
        getMessage("email is not correct", "error_email");
        if (email.classList.contains("correct")) {
            email.classList.remove("correct");
            turnOnButton();
        }
    }
}

function validateEmail(email) {
    var pattern  = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return pattern .test(email);
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

/*function checkBirthDate() {
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
}*/

function turnOnButton() {
    var wrapperInput = document.querySelectorAll(".wrapper_input");


    for(var i = 0; i < wrapperInput.length; i++) {
        var input = wrapperInput[i].getElementsByTagName("input");
        console.log(input);
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