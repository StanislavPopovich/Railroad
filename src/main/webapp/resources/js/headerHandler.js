
(function(){
    let url = window.location.pathname;
    console.log(url);
    let headerMenu = document.querySelector(".header_menu");
    console.log(headerMenu);
    if(headerMenu){
        let urls = headerMenu.querySelectorAll("a");

        for(let i = 0; i < urls.length; i++){
            let currentRef = urls[i];
            let currentUrl = urls[i].href.split("8081")[1];
            if(currentUrl === url){
                currentRef.classList.add("active");
            }
            console.log(currentUrl);
        }
    }
}());