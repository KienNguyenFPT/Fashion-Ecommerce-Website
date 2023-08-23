/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

var inputMessage = document.getElementById("message-input");
var sendMessage = document.getElementById("message-input");
var chatArea = document.getElementById("chat-area");
var roomId;
var ws = "";
var senderName;
var senderId;

document.addEventListener('DOMContentLoaded', () => {
    if (ws === "") {
        fetch('./GetChatRoomAddress', {
            method: 'GET',
            headers: {
                'content-type': 'application/json'
            }
        }).then(response => {
            if (!response.ok) {
                throw new Error('Network was not ok! Status: ' + response.status);
            } else
                return response.json();
        }).then(data => {
            if (data === null) {
                window.location.href = '404.jsp';
            } else {
                roomId = data.roomId;
                senderId = data.senderId;
                senderName = data.senderName;
                ws = new WebSocket(`ws://localhost:8080/ShopOnline//chatmessenger/${roomId}`);
                inputMessage.value = "";
                ws.onopen = () => {
                    chatArea.innerHTML += `<p>Connect to room: ${roomId}</p>`;
                    chatArea.scrollTop = chatArea.scrollHeight;
                };
                ws.onmessage = event => {
                    var mess = JSON.parse(event.data);
                    var message = document.createElement("div");
                    message.innerHTML = `<div style="display: flex; margin: 10px 0;">
                                    <span style="font-weight: bold; margin-right: 5px;">${mess.senderName}:</span>
                                    <div style="background-color: #f0f0f0; padding: 5px; border-radius: 5px;">${mess.content}</div>
                                </div>`;
                    chatArea.appendChild(message);
                    chatArea.scrollTop = chatArea.scrollHeight;
                };
                ws.onclose = () => {
                    var message = document.createElement("div");
                    message.innerHTML = `<p>Connection closed</p>`;
                    chatArea.appendChild(message);
                    chatArea.scrollTop = chatArea.scrollHeight;
                };
            }
        }).then(error => {
            var status = document.createElement("div");
            status.innerText = error;
            var divStatus = document.getElementById("status");
            divStatus.appendChild(status);
            window.setTimeout(() => {
                status.remove();
            }, 2000);
        });
    }

});

sendMessage.addEventListener("click", () => {
    inputMessage.addEventListener("change", () => {
        
        var message = inputMessage.value.trim();
        dataToSend = {
            content: message,
            senderId: senderId,
            senderName: senderName
        };
        if (message.length > 0) {
            updateMessage(dataToSend);
            ws.send(JSON.stringify(dataToSend));
            inputMessage.value = "";

        }
    });
});

function updateMessage(dataToSend) {
    fetch('./UpdateMessageController', {
        method: 'POST',
        headers: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify(dataToSend)
    }).then(response => {
        if (!response.ok) {
            throw new Exception("Network was not ok. Status: " + response.status);
        } else
            return response.text();
    }).then(data => {
    }).catch(error => {
        var status = document.createElement("div");
        status.innerText = error;
        document.getElementById("status").appendChild(status);
        window.setTimeout(function () {
            status.remove();
        }, 5000);
    });
}


