// Checking registration input
// registration_page

var username = document.getElementById("username");
var password = document.getElementById("password");
var passwordConfirm = document.getElementById("passwordConfirm");
var regbutton = document.getElementById("button");

function checkUsername() {
    if (username.value === "" || username.value.length < 3) {
        getMessage("Username must be 3-20 characters in length", "error_name");
        if (username.classList.contains("correct")) {
            username.classList.remove("correct");
            turnOnButton();
        }
    }
    else {
        deleteMessage("error_name");
        if (!username.classList.contains("correct")) {
            username.classList.add("correct");
            turnOnButton();
        }
    }
}

function checkPassword() {
    getMessage("Password must be more 3 characters in length", "error_password");
    if (password.value === "" || password.value.length < 3) {
        if (password.classList.contains("correct")) {
            password.classList.remove("correct");
            turnOnButton();
        }
    }
    else {
        deleteMessage("error_password");
        if (!password.classList.contains("correct")) {
            password.classList.add("correct");
            turnOnButton();
            if(passwordConfirm){
                checkPasswordConfirm()
            }
        }
        else{
            if(passwordConfirm){
                checkPasswordConfirm()
            }
        }
    }
}

function checkPasswordConfirm() {
    getMessage("Repeat password", "error_password_confirm");
    if (passwordConfirm.value === "" || passwordConfirm.value.length < 3
        || passwordConfirm.value !== password.value) {
        if (passwordConfirm.classList.contains("correct")) {
            passwordConfirm.classList.remove("correct");
            turnOnButton();
        }
    }
    else {
        deleteMessage("error_password_confirm");
        if (!passwordConfirm.classList.contains("correct")) {
            passwordConfirm.classList.add("correct");
            turnOnButton();
        }
    }
}


function turnOnButton() {
    var inputs = document.getElementsByTagName("input");

    for(var i = 0; i < inputs.length; i++) {
        if (inputs[i].classList.contains("correct")) {
            regbutton.disabled = false;
            regbutton.classList.add("active");
        }
        else {
            regbutton.classList.remove("active");
            return regbutton.disabled = true;
        }
    }
}
function getMessage(message, id) {
    document.getElementById(id).textContent = message;
}
function deleteMessage(id) {
    document.getElementById(id).textContent = "";
}

