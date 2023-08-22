/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */


document.addEventListener('DOMContentLoaded', () => {
    fetch('./MessengerManagerController', {
        method: 'GET',
        headers: {
            'content-type': 'application/json'
        }
    }).then(response => {
        if (!response.ok) {
            throw new Error('Network was not ok! Status: ' + response.status);
        } else
            return response.text();
    }).then(data => {

    }).then(error => {
        var status = document.createElement("div");
        status.innerText = error;
        document.getElementById("status").appendChild(status);
        window.setTimeout(() => {
            status.remove();
        }, 2000);
    });

});

