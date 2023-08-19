/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */


function updateOrderStatus(status) {
    dataToSend = {
        statusOrder: status
    }
    fetch('./updateStatusOrderController', {
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
        if (data.toString() === "success") {
            var status = document.createElement("div");
            status.innerText = "Payment Success! Updata status order success!";
            document.getElementById("paypal-button-container").appendChild(status);
        }else{
            var status = document.createElement("div");
            status.innerText = "Payment Success! Updata status order fail!";
            document.getElementById("paypal-button-container").appendChild(status);
        }
    }).catch(error => {
        var status = document.createElement("div");
        status.innerText = error;
        document.getElementById("paypal-button-container").appendChild(status);
        window.setTimeout(function(){
          status.remove();  
        }, 5000);
    });
}