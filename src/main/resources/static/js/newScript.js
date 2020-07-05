'use strict';

var type = document.querySelector('#type');
var save = document.querySelector('#save');

var form = document.querySelector('#formHeader');
var contract = document.querySelector('#contractHeader');

var showStatus = document.querySelector('#showStatus');

var formFileName = document.querySelector('#formFileName');
var contractFileName = document.querySelector('#contractFileName');

var text = document.querySelector('#textArea');

type.addEventListener('click',function() {
	var x = type.options[type.selectedIndex].value;

    switch (x) {
        case "form":
	       	save.style.display = "block";
            formFileName.display = "block";
            form.style.display = "block";
            contract.style.display = "none";
            text.style.display = "block";
        break;
        case "contract":
    		save.style.display = "block";
            contractFileName.display = "block";
            form.style.display = "none";
            contract.style.display = "block";		
    		text.style.display = "block";
        break;
        default:
            save.style.display = "none";
            firstName.display = "none";
    		form.style.display = "none";
    		contract.style.display = "none";
    		text.style.display = "none";
        break;
	}
}, true);

function saveFile(fileName, typeValue, valuesToSend, data){
    var file = new File([data], fileName, {type: 'text/plain;charset=utf-8'});

    var formData = new FormData();
    formData.append("file",file);

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/sendData");

    var date = new Date();

    xhr.setRequestHeader('documentType', typeValue);
    xhr.setRequestHeader('values', valuesToSend);

    var dateString = date.toString();
    xhr.setRequestHeader('creationDate', dateString.substring(0, dateString.indexOf("(")-1));

    xhr.onload = function(){
        var response = JSON.parse(xhr.responseText);
        if (xhr.status == 200) {
            showStatus.innerHTML = "Zapisano plik pod nazwą " 
            + response.fileName + ", można go pobrać pod adresem: " + response.fileDownloadUri;
        } else {
            showStatus.innerHTML = "Błąd zapisu pliku na serwerze";
        }
    }

    xhr.send(formData);
}

save.addEventListener('click', function(event) {
    var x = type.options[type.selectedIndex].value;

    switch (x){
        case "form":
            var formFileName = document.getElementById('formFileName').value;
            var yourFirstName = document.getElementById('yourFirstName').value;
            var yourSurname = document.getElementById('yourSurname').value;
            var firstName = document.getElementById('firstName').value;
            var surname = document.getElementById('surname').value;
            var position = document.getElementById('position').value;
            var formText = document.getElementById('textArea').value;

            if (formFileName != '' && yourFirstName != '' && yourSurname != '' && firstName != '' && surname != '' && position != '' ){
                var valuesToSend = yourFirstName + "_" + yourSurname + "_" +
                    firstName + "_" + surname + "_" + position;

                saveFile(formFileName, x, valuesToSend, formText);
            } else {
                if (formFileName === '') { showStatus.innerHTML = "Podaj nazwę pliku "; }
                if (yourFirstName === '') { showStatus.innerHTML = "Podaj imię osoby składającej podanie"; }
                if (yourSurname === '') { showStatus.innerHTML = "Podaj nazwisko osoby składającej podanie"; }
                if (firstName === '') { showStatus.innerHTML = "Podaj imię osoby do której składasz podanie"; }
                if (surname === '') { showStatus.innerHTML = "Podaj nazwisko osoby do której składasz podanie"; }
                if (position === '') { showStatus.innerHTML = "Podaj stanowisko osoby do której składasz podanie"; }
            }
        break;
        case "contract":
            var contractFileName = document.getElementById('contractFileName').value;
            var employerName = document.getElementById('employerName').value;
            var employerSurname = document.getElementById('employerSurname').value;
            var companyName = document.getElementById('companyName').value;
            var employeeName = document.getElementById('employeeName').value;
            var employeeSurname = document.getElementById('employeeSurname').value;
            var contractText = document.getElementById('textArea').value;

            if (contractFileName != '' && employerName != '' && employerSurname != '' && companyName != '' 
                && employeeName != '' && employeeSurname != '') {
                var valuesToSend = employerName + "_" + employerSurname + "_" +
                    companyName + "_" + employeeName + "_" + employeeSurname;

                saveFile(contractFileName, x, valuesToSend, contractText);
            } else {
                if (contractFileName === '') { showStatus.innerHTML = "Podaj nazwę pliku"; }
                if (employerName === '') { showStatus.innerHTML = "Podaj imię pracodawcy"; }
                if (employerSurname === '') { showStatus.innerHTML = "Podaj nazwisko pracodawcy"; }
                if (companyName === '') {showStatus.innerHTML = "Podaj nazwę firmy";}
                if (employeeName === '') { showStatus.innerHTML = "Podaj imię pracownika"; }
                if (employeeSurname === '') { showStatus.innerHTML = "Podaj nazwisko pracownika"; }
            }
        break;
        default:

        break;
    }

    event.preventDefault();
}, true);
