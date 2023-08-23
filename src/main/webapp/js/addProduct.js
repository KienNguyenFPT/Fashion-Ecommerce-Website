/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */


function checkValid() {
    var category = document.getElementById("category");
    if (category.value == "") {
        var status = document.getElementById("message");
        status.innerHTML = "Please choose category!";
        window.setTimeout(() => {
            status.innerHTML = "";
        }, 2000);
        return false;
    } else {
        if (confirm("Add New Product?")) {
            addNewProduct();
        } else {
            return false;
        }
    }
}

function addNewProduct() {
    dataToSend = {
        name: document.getElementById("name").value,
        category: document.getElementById("category").value,
        description: document.getElementById("description").value,
        qCurrent: document.getElementById("quantity_current").value,
        qSold: document.getElementById("quantity_sold").value,
        nPrice: document.getElementById("new_price").value,
        oPrice: document.getElementById("old_price").value,
        img: document.getElementById("file_img").value
    }
    
    fetch('./AddNewProductController', {
        method: 'POST',
        headers: {
            'content-type': 'application/json'
        },
        body: JSON.stringify(dataToSend)
    }).then(response => {
        if (!response.ok) {
            throw new Error("Network was not ok! Status: " + response.status);
        } else
            return response.text();
    }).then(data => {
        var status = document.createElement("div");
        status.innerText = "Add new Product success!";
        document.getElementById("status").appendChild(status);
        window.setTimeout(() => {
            status.remove();
        }, 2000);
    }).then(error => {
        var status = document.createElement("div");
        status.innerText = error;
        document.getElementById("status").appendChild(status);
        window.setTimeout(() => {
            status.remove();
        }, 2000);
    });
}