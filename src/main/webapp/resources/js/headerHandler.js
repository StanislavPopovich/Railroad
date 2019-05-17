
(function(){
    let url = window.location.pathname;
    let headerMenu = document.querySelector(".header_menu");

    if(headerMenu){
        let urls = headerMenu.querySelectorAll("a");

        for(let i = 0; i < urls.length; i++){
            let currentRef = urls[i];
            let currentUrl = urls[i].href.split("8081")[1];
            if(currentUrl === url){
                currentRef.classList.add("active");
            }
        }
    }
}());