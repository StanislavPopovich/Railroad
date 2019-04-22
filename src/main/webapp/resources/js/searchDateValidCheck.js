
var searchDate = document.getElementById("date");
if(searchDate){
 searchDate.addEventListener("change", checkSearchDate, false);
}
function checkSearchDate(e) {
    var current = e.target;
    var currentDate = getCurrentDateInFormat();
    var currentSeconds = new Date(currentDate).getTime();
    var searchSeconds = new Date(current.value).getTime();
    var form = FindElemId(current, "date_depart");
    var message = form.querySelector(".message");

    if(currentSeconds > searchSeconds){
        if(!message) {
            var message = document.createElement("div");
            message.textContent = "Not valid date";
            message.className= "message";
            form.classList.add("error");
            form.appendChild(message);
            addError();
        }

    } else {
        if(message){
            form.removeChild(message);
            removeError();
        }

    }
}

function FindElemId(self, classElem){
    var item = self.parentElement;
    if(item.id == classElem){
        return item;
    } else {
        return FindElem(item, classElem);
    }
}

function FindElem(self, classElem){
    var item = self.parentElement;
    if(item.classList.contains(classElem)){
        return item;
    } else {
        return FindElem(item, classElem);
    }
}

function getCurrentDateInFormat(){
    var currentDate = new Date();
    var dd = currentDate.getDate();
    var mm = currentDate.getMonth()+1;
    if(("" + mm).length < 2){
        mm = "0" + mm;
    }
    var yyyy = currentDate.getFullYear();
    return yyyy + "-" +  mm + "-" + dd;
}

var findContainer = document.querySelector(".find_container");

if(findContainer) {
    findContainer.addEventListener("click", findContainerFunc, false);
}

function findContainerFunc(e) {

    var select =  findContainer.querySelectorAll(".select");

    for(var i = 0; i < select.length; i++) {
        var id = select[i].id;
        switch (id) {
            case  "start":
                checkSelect(select[i]);
                break
            case "end":
                checkSelect(select[i]);
                break
        }
    }
}

function checkSelect(current) {
    var indexSelected = current.selectedIndex;

    if(indexSelected){
        removeError();
    } else {
        addError();
    }
}

var regbutton = document.getElementById("findBtn");

function addError() {
    regbutton.classList.remove("active");
    return regbutton.disabled = true;
}

function removeError() {
    regbutton.disabled = false;
    regbutton.classList.add("active");
}