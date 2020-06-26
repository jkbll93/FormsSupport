var refresh = document.querySelector('#refresh');

function showAllFiles(){
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/downloadNames");


	xhr.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            document.querySelector('#main').innerHTML =
            this.responseText;
       }
    };

    xhr.send();
}

refresh.addEventListener('click',function(event) {

	showAllFiles();


    event.preventDefault();
}, true);