$(document).ready(function () {
    window.locale = "en";

    //getting all trains from db and render content
    function getAllTrains(){
        let currentLocale = window.locale;
        $.ajax({
            url : '/railroad/trains/get-all',
            type : "GET",
            dataType : "json",
            success: function (data) {
                let  markup = '';
                if(data.length === 0){
                    markup+= '<h1>' +  message[currentLocale].trainsNotFound + '</h1>'
                }
                for(let i = 0; i < data.length; i++){
                    let item = '';
                    item+='<div class="item">' +
                        '    <div class="wrapper_item">' +
                        '        <div class="train">' +
                        '            <div class="img"><img src="/resources/img/train.svg"></div>' +
                        '            <div class="trainNumber_1">' +  message[currentLocale].trainNumberSymbol +
                        data[i].number + '</div>' +
                        '        </div>' +
                        '    </div>' +
                        '    <div class="wrapper_item">' +
                        '        <div class="wrapper_rout">';

                                    item+='<div class="route">' +
                                '                <div class="label"><span>' + message[currentLocale].from + '</span></div>' +
                                '                <div class="departStation_1">' + data[i].stations[0] + '</div>' +
                                '            </div>' +
                                '            <div class="arrow_right"></div>';

                                    item+='<div class="route">' +
                                '                <div class="label"><span>' + message[currentLocale].to + '</span></div>' +
                                '                <div class="arrivalStation_1">' + data[i].stations[data[i].stations.length - 1] + '</div>' +
                                '            </div>';
                        item+='</div></div>';

                        item+=' <div class="number_tickets">' +
                            '        <div class="label"><span>' + message[currentLocale].numberOfTickets + '</span></div>' +
                            '        <div>' + data[i].seats + '</div>' +
                            '    </div>' +
                                '<div class="btn_details">' +
                                '   <div class="btn btn_blue">' + message[currentLocale].details + '</div>' +
                                '</div>' +
                        '</div>';
                    markup += item;
                }

                $('#items').append().html(markup);
                let itemsContent = document.getElementById("items");
                if(itemsContent){
                    itemsContent.addEventListener("click", getTrainDetail, false);
                }
                let addTrainListener = document.getElementById("btn_add");
                if(addTrainListener){
                    addTrainListener.addEventListener("click", redirectToAddTrainPage, false);
                }
            }
        });
    }

    getAllTrains();

    //getting train by number and create modal window
    function getTrainDetail(event) {
        let current = event.target;
        if(current.classList.contains("btn")){
            let item = FindElem(current, "item");
            let trainNumber = item.querySelector(".trainNumber_1").textContent.split("№")[1];
            if(trainNumber){
                getSelectedTrain(trainNumber);
            }
        }
    }

    function getSelectedTrain(trainNumber) {
        let train = {"trainNumber": trainNumber };
        $.ajax({
            url : '/railroad/train/by-number',
            type : "POST",
            data : train,
            success : function(data){
                createModalTrain(data);
            },
        })
    }

    function createModalTrain(train) {
        let currentLocale = window.locale;
        let markup = "<div class='wrapper_modal'>" +
            "        <div class='close'></div>" +
            "        <div class='modal_content'>" +
            "            <div>" +
            "                <div>" + message[currentLocale].trainNumberTrainModal + "</div>" +
            "                <div>" + train.number + "</div>" +
            "            </div>" +
            "            <div>" +
            "                <div>" + message[currentLocale].numberOfSeatsTrainModal + "</div>" +
            "                <div>" + train.seats + "</div>" +
            "            </div>" +
            "            <div>" + message[currentLocale].stationsTrainModal + "</div>" +
            "            <div>";
                for(let i =0; i < train.stations.length; i++){
                    markup += "<div class='station'>"+ train.stations[i] + "</div>";
                }
            markup +="         </div>" +
            "        </div>";

        $('body').append(markup);
        let wrapperModal = document.querySelector(".wrapper_modal");
        wrapperModal.addEventListener("click", removeModal, false);
    }

    function removeModal(event) {
        let body = document.querySelector('body');
        let wrapperModal = document.querySelector('.wrapper_modal');
        let currentClass = event.target.className;
        if(currentClass === "wrapper_modal" || currentClass === "close") {
            body.removeChild(wrapperModal);
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

    function redirectToAddTrainPage() {
        window.location.href = "/railroad/train/add";
    }
});

