/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

var displayByPaymentMethod = document.getElementById("payment");
var displayByStatus = document.getElementById("status");

displayByPaymentMethod.addEventListener("click", () => {
    displayByPaymentMethod.addEventListener("change", () => {
        orderManager(displayByPaymentMethod.value, displayByStatus.value, 0)
    });
});

displayByStatus.addEventListener("click", () => {
    displayByStatus.addEventListener("change", () => {
        orderManager(displayByPaymentMethod.value, displayByStatus.value, 0);
    });
});

function changePage(offset){
    orderManager(displayByPaymentMethod.value, displayByStatus.value, (offset - 1)*10);
}

function orderManager(method, status, offset) {
    dataToSend = {
        method: method,
        status: status,
        offset: offset
    };
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
            var orderResult = data.orderResult;
            var total = data.count;
            orderResult.forEach(function (order) {
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
            var allPage = document.getElementById("all-pages");
            allPage.innerHTML = `<li class="page-item disabled">
                        <a class="page-link" href="#" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                            <span class="sr-only">Previous</span>
                        </a>
                    </li>
                    <c:forEach begin="1" end="${total}" var="o">
                        <li class="page-item">
                            <a href="" onclick="changePage(${o})" class="page-link active">${o}</a>
                        </li>
                    </c:forEach>
                    <li class="page-item">
                        <a class="page-link" href="#" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                            <span class="sr-only">Next</span>
                        </a>
                    </li>`;
            
        } else {
            throw new Error("Not Found!");
        }
    }).then(error => {
    });
}

