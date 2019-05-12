
let searchDepartDate = document.getElementById("depart_date");
let searchDepartReturnDate = document.getElementById("date_return_depart");
if(searchDepartDate){
 searchDepartDate.addEventListener("change", checkSearchDate, false);
}
if(searchDepartReturnDate){
    searchDepartReturnDate.addEventListener("change", checkSearchDate, false);
}
function checkSearchDate(e) {
    let current = e.target;
    let currentDate = getCurrentDateInFormat();
    let currentSeconds = new Date(currentDate).getTime();
    let searchSeconds = new Date(current.value).getTime();
    let form = FindElemId(current, "wrapper_input");

    let message = form.querySelector(".message");

    if(currentSeconds > searchSeconds){
        if(!message) {
            let message = document.createElement("div");
            message.textContent = "Not valid date";
            message.className= "message";
            form.classList.add("error");
            form.appendChild(message);
            if(e.target.id === "depart_date"){
                addError();
            }

        }

    } else {
        if(message){
            form.removeChild(message);
            if(e.target.id === "depart_date"){
                removeError();
            }

        }
    }
}

function FindElemId(self, classElem){
    let item = self.parentElement;
    if(item.id == classElem){
        return item;
    } else {
        return FindElem(item, classElem);
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

function getCurrentDateInFormat(){
    let currentDate = new Date();
    let dd = currentDate.getDate();
    let mm = currentDate.getMonth()+1;
    if(("" + mm).length < 2){
        mm = "0" + mm;
    }
    let yyyy = currentDate.getFullYear();
    return yyyy + "-" +  mm + "-" + dd;
}

let findingMenu = document.querySelector(".finding_menu");

if(findingMenu) {
    findingMenu.addEventListener("click", findContainerFunc, false);
}

function findContainerFunc(e) {

    let input =  findingMenu.querySelectorAll("input");

    for(let i = 0; i < input.length; i++) {
        let id = input[i].id;
        switch (id) {
            case  "depart_station":
                checkInput(input[i]);
                break;
            case "arrival_station":
                checkInput(input[i]);
                break;
            case "depart_date":
                checkInput(input[i]);
                break;
        }
    }
}

function checkInput(current) {
    let indexSelected = current.value;
    if(indexSelected !== ''){
        removeError();
    } else {
        addError();
    }
}

let regbutton = document.getElementById("findBtn");

function addError() {
    regbutton.classList.remove("active");
    return regbutton.disabled = true;
}

function removeError() {
    regbutton.disabled = false;
    regbutton.classList.add("active");
}