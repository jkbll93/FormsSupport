var downloadButton = document.querySelector('#downloadButton');
var myIFrame = document.getElementById('myIFrame');
var showStatus = document.querySelector('#downloadShowStatus');

function downloadFile(fileToDownload){
    var xhr = new XMLHttpRequest();
    fileToDownload = /downloadFile/ + fileToDownload;
    xhr.open("GET", fileToDownload);

	xhr.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			myIFrame.src = fileToDownload;
			showStatus.innerHTML = "";
       	} else {
       		showStatus.innerHTML = "Błąd pobrania pliku";
		}
    };

    xhr.send();
}

downloadButton.addEventListener('click',function(event) {

    var fileToDownload = document.getElementById('fileToDownload').value;
    if(fileToDownload != ''){
    	downloadFile(fileToDownload);
    }
    
    event.preventDefault();
}, true);