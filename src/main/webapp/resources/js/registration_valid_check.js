// Checking registration input
// registration_page

var username = document.getElementById("username");
var password = document.getElementById("password");
var passwordConfirm = document.getElementById("passwordConfirm");
var regbutton = document.getElementById("button");

function checkUsername() {
    if (username.value == "" || username.value.length < 3) {
        if (username.classList.contains("correct")) {
            username.classList.remove("correct");
            turnOnButton();
        }
    }
    else {
        if (!username.classList.contains("correct")) {
            username.classList.add("correct");
            turnOnButton();
        }
    }
}

function checkPassword() {
    if (password.value == "" || password.value.length < 3) {
        if (password.classList.contains("correct")) {
            password.classList.remove("correct");
            turnOnButton();
        }
    }
    else {
        if (!password.classList.contains("correct")) {
            password.classList.add("correct");
            turnOnButton();
        }
    }
}

function checkPasswordConfirm() {
    if (passwordConfirm.value == "" ||passwordConfirm.value.length < 3
        || passwordConfirm.value != password.value) {
        if (passwordConfirm.classList.contains("correct")) {
            passwordConfirm.classList.remove("correct");
            turnOnButton();
        }
    }
    else {
        if (!passwordConfirm.classList.contains("correct")) {
            passwordConfirm.classList.add("correct");
            turnOnButton();
        }
    }
}


function turnOnButton() {
    if (username.classList.contains("correct") && password.classList.contains("correct")
        && passwordConfirm.classList.contains("correct")) {
        regbutton.disabled = false;
    }
    else {
        regbutton.disabled = true;
    }
}
