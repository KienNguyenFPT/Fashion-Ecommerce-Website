/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

var displayByPaymentMethod = document.getElementById("payment");
var displayByStatus = document.getElementById("status");

displayByPaymentMethod.addEventListener("click", () => {
    displayByPaymentMethod.addEventListener("change", () => {
        switch (displayByPaymentMethod.value) {
            case "paypal":
                orderManager("paypal");
                break;
            case "direct":
                orderManager("direct");
                break;
            case "bank":
                orderManager("bank");
                break;
        }
    })
});

displayByStatus.addEventListener("click", () => {
    displayByStatus.addEventListener("change", () => {
        switch (displayByStatus.value) {
            case "displayPending":
                orderManager("displayPending");
                break;
            case "displayPaid":
                orderManager("displayPaid");
                break;
            case "displayComplete":
                orderManager("displayComplete");
                break;
        }
    })
});

function orderManager(s) {
    dataToSend = {
        require: s
    }
    fetch('./OrderManagerSortFilterController', {
        method: 'POST',
        headers: {
            'content-type': 'application/json'
        },
        body: JSON.stringify(dataToSend)
    }).then(response => {
        if (!response.ok) {
            throw new Error("Network was not ok. Status: " + response.status);
        } else
            return response.json();
    }).then(data => {
        if (data !== null) {
            var orderList = $("#table-body");
            orderList.empty();
            data.forEach(function (order) {
                orderList.append("<tr>" +
                        "<td>" + order.orderId + "</td>" +
                        "<td>" + order.customerId + "</td>" +
                        "<td>" + order.orderDate + "</td>" +
                        "<td>" + order.totalAmount + "</td>" +
                        "<td>" + order.paymentMethod + "</td>" +
                        "<td>" + order.status + "</td>" +
                        "<td><button name='view' id='view' value='" + order.orderId + "'>View Detail</button></td>" +
                        "</tr>");
            });
        } else {
            throw new Error("Not Found!");
        }
    }).then(error => {
        var status = document.createElement("div");
        status.innerText = error;
        document.getElementById("status-error").appendChild(status);
        window.setTimeout(function(){
            status.remove();
        },2000);
    });
}

