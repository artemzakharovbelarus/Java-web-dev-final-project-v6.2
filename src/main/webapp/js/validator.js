"use strict";

const COLOR_INPUT_VALID = "2px solid #66ff00";
const COLOR_INPUT_INVALID = "2px solid red";
const COLOR_OUTPUT_VALID = "#66ff00";
const COLOR_OUTPUT_INVALID = "red";

const TAG_ATR_USERNAME = "username_output";
const TAG_ATR_PASSWORD = "password_output";
const TAG_ATR_EMAIL = "email_output";
const TAG_ATR_CONFIRM = "confirmed_output";

const USERNAME_REGEX = /^[A-Za-z0-9][A-Za-z0-9_-]{4,13}[A-Za-z0-9]$/;
const PASSWORD_REGEX = /^[A-Za-z0-9][A-Za-z0-9_-]{6,28}[A-Za-z0-9]$/;
const EMAIL_REGEX = /^([A-Za-z\d.\-]+)@([A-Za-z\d-]+)\.([a-z]{2,8})(\.[a-z]{2,8})?$/;

const MESSAGE_INVALID_USERNAME = "*Username is invalid!";
const MESSAGE_VALID_USERNAME = "*Username is valid!";

const MESSAGE_INVALID_EMAIL = "*E-mail is invalid!";
const MESSAGE_VALID_EMAIL = "*E-mail is invalid!";

const MESSAGE_INVALID_PASSWORD = "*Password is invalid";
const MESSAGE_VALID_PASSWORD = "*Password is valid!";

function validateSignIn(){

    let usernameAtr = document.getElementById("username");
    let passwordAtr = document.getElementById("password");

    let usernameValue = usernameAtr.value;
    let passwordValue = passwordAtr.value;

    let counter1 = 0;
    let counter2 = 0;
    while(usernameValue[counter1] === " " ||
          passwordValue[counter2] === " "){
        if (usernameValue[counter1] === " "){
            counter1++;
        }
        else if (passwordValue[counter2] === " "){
            counter2++;
        }
    }
    if (counter1 === usernameValue.length || usernameValue === "") {
        usernameAtr.classList.add("text-danger");
        document.getElementById(TAG_ATR_USERNAME).classList.add("text-danger");
        event.preventDefault();
    } else{
        usernameAtr.classList.add("text-success");
        document.getElementById(TAG_ATR_USERNAME).classList.add("text-success");
    }
    if (counter2 === passwordValue.length || passwordValue === ""){
        passwordAtr.classList.add("text-danger");
        document.getElementById(TAG_ATR_PASSWORD).classList.add("text-danger");
        event.preventDefault();
    } else{
        passwordAtr.classList.add("text-success");
        document.getElementById(TAG_ATR_PASSWORD).classList.add("text-success");
    }
    return true;
}

function validateSignUp() {

}