/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
var shipAdr;
var shipName;
var shipEmail;
var shipPhone;

var checkShippingAddress = document.getElementById("shipto");
checkShippingAddress.addEventListener("change", function () {
    var addressInfo = document.querySelectorAll("input[name='shipping-address-info']");
    addressInfo.forEach(function (input) {
        if (checkShippingAddress.checked) {
            input.setAttribute("required", "");
        } else {
            input.removeAttribute("required");
        }
    });
});

function placeOrder() {
    getShipInfo();
    if (document.getElementById("paypal").checked) {
        orderPaypal();
    } else if (document.getElementById("directcheck").checked) {
        orderDirectCheck();
    } else if (document.getElementById("banktransfer").checked) {
        orderBankTransfer();
    }
    return false;
}

function orderDirectCheck() {
    dataToSend = {
        shipToAnotherAdr: document.getElementById("shipto").checked,
        shipAdr: shipAdr,
        shipName: shipName,
        shipEmail: shipEmail,
        shipPhone: shipPhone
    };
    fetch('./OrderDirectController', {
        method: 'POST',
        header: {
            'Content-type': 'application/json'
        }, body: JSON.stringify(dataToSend)
    }).then(response => {
        if (!response.ok) {
            throw new Error("Network was not ok! Status: " + response.status);
        }
        return response.text();
    }).then(data => {
        if (data.toString() === "success") {
            var status = document.createElement("div");
            status.textContent = "Order successful! Your order is pending....";
            document.getElementById("order-info").appendChild(status);
            window.setTimeout(function () {
                status.remove();
            }, 2000);
            var orderDetailLink = document.createElement("a");
            orderDetailLink.href = './orderDetails.jsp';
            orderDetailLink.innerText = "View Order Detail";
            document.getElementById("order-info").appendChild(orderDetailLink);
        } else {
            throw new Error("Order fail! Please check your information!");
        }
    }).catch(error => {
        var status = document.createElement("div");
        status.textContent = error;
        document.getElementById("order-info").appendChild(status);
        window.setTimeout(function () {
            status.remove();
        }, 2000);
    });
}

function orderBankTransfer() {
    dataToSend = {
        shipToAnotherAdr: document.getElementById("shipto").checked,
        shipAdr: shipAdr,
        shipName: shipName,
        shipEmail: shipEmail,
        shipPhone: shipPhone
    };
    fetch('./OrderBankTranferController', {
        method: 'POST',
        header: {
            'Content-type': 'application/json'
        }, body: JSON.stringify(dataToSend)
    }).then(response => {
        if (!response.ok) {
            throw new Error("Network was not ok! Status: " + response.status);
        }
        return response.text();
    }).then(data => {
        var link = data.toString();
        if (link === "fail") {
            throw new Error("Order fail! Please check your information!");
        } else {
            window.location.href = "./paymentBankTransfer.jsp";
        }
    }).catch(error => {
        var status = document.createElement("div");
        status.textContent = error;
        document.getElementById("order-info").appendChild(status);
        window.setTimeout(function () {
            status.remove();
        }, 2000);
    });
}

function orderPaypal() {
    dataToSend = {
        shipToAnotherAdr: document.getElementById("shipto").checked,
        shipAdr: shipAdr,
        shipName: shipName,
        shipEmail: shipEmail,
        shipPhone: shipPhone
    };
    fetch('./OrderPaypalController', {
        method: 'POST',
        header: {
            'Content-type': 'application/json'
        }, body: JSON.stringify(dataToSend)
    }).then(response => {
        if (!response.ok) {
            throw new Error("Network was not ok! Status: " + response.status);
        }
        return response.text();
    }).then(data => {
        if (data.toString() === "fail") {
            throw new Error("Order fail! Please check your information!");
        } else {
            window.location.href = "./paymentPayPal.jsp";
        }
    }).catch(error => {
        var status = document.createElement("div");
        status.textContent = error;
        document.getElementById("order-info").appendChild(status);
        window.setTimeout(function () {
            status.remove();
        }, 2000);
    });
}

function getShipInfo() {
    shipAdr = document.getElementById("shipAdr").value + " - " + document.getElementById("shipAdrCity").value + " - " + document.getElementById("shipAdrProvince").value + " - " + document.getElementById("shipAdrCountry").value;
    shipName = document.getElementById("shipFirstName").value + " " + document.getElementById("shipLastName").value;
    shipEmail = document.getElementById("shipEmail").value;
    shipPhone = document.getElementById("shipPhone").value;
}