/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

var inputMessage = document.getElementById("message-input");
var sendMessage = document.getElementById("message-input");
var divStatus = document.getElementById("status");
var chatArea = document.getElementById("message-content");
var room;
var ws;

fetch('./getChatRoomAddress', {
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
    if (data === null) {
        window.location.href = '404.jsp';
    } else {
        room = data.toString();
        ws = new WebSocket(`ws://localhost:8080/ShopOnline/${room}`);
        inputMessage.value = "";
        ws.onopen = () => {
            chatArea.innerHTML += `<p>Connect to room: ${room}</p>`;
        };
        ws.onmessage = event => {
            var mess = JSON.parse(event.data);
            chatArea.innerHTML += `<p><strong>${mess.from}:</strong> ${mess.content}</p>`;
        };
        ws.onclose = () => {
            chatArea.innerHTML += `<p>Connection closed</p>`;
        };
    }
}).then(error => {
    var status = document.createElement("div");
    status.innerText = error;
    divStatus.appendChild(status);
    window.setTimeout(() => {
        status.remove();
    }, 2000);
});


sendMessage.addEventListener("click", () => {
    inputMessage.addEventListener("change", () => {
        var message = inputMessage.value.trim();
        if (message.length > 0) {
            ws.send(message);
            inputMessage.value = "";
        }
    });
});


