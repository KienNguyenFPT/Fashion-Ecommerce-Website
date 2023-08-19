/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/ClientSide/javascript.js to edit this template
 */

function changeQuantity(productId, productQuantity, status) {
    var dataToSend = {
        pId: productId,
        pQ: productQuantity,
        st: status
    }
    fetch('./UpdateProductQuantityController', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(dataToSend)
    })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok. Status: ' + response.status);
                }
                return response.text();
            })
            .then(data => {
                var changeTotalPriceProduct = document.querySelector(`tr[data-id="${productId}"]`);
                changeTotalPriceProduct.querySelector('input').value = data;
                var priceProduct = document.querySelector(`td[data-id="product-price${productId}"]`).innerHTML;
                var totalProduct = document.querySelector(`td[data-id="totalPriceProduct${productId}"]`);
                var oldPrice = totalProduct.innerHTML;
                totalProduct.innerHTML = data * priceProduct;
                var changeTotalPriceCart = document.getElementById("totalPriceCart");
                var totalCart = changeTotalPriceCart.innerHTML;
                updateTotalCart((data * priceProduct) - Number(oldPrice) + Number(totalCart), 10, 0);
            })
            .catch(error => {
                var statusDiv = document.createElement("div");
                statusDiv.textContent = error;
                document.getElementById("addCartStatus").appendChild(statusDiv);
                window.setTimeout(function () {
                    statusDiv.remove();
                }, 2000);
                return false;
            });
}

function removeProductFromCart(pId) {
    var dataToSend = {
        pId: pId
    }
    fetch('./RemoveProductFromCart', {
        method: 'POST',
        header: {
            'Content-type': 'application/json'
        },
        body: JSON.stringify(dataToSend)
    }).then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok. Status: ', response.status);
        }
        return response.text();
    }).then(data => {
        if (data.toString() === "Fail")
            throw new Error('Remove fail!');
        else {
            var removeItem = document.querySelector(`tr[data-id="${pId}"]`);
            var totalProduct = document.querySelector(`td[data-id="totalPriceProduct${pId}"]`).innerHTML;
            var changeTotalPriceCart = document.getElementById("totalPriceCart").innerHTML;
            updateTotalCart(Number(changeTotalPriceCart) - Number(totalProduct), 10, 0);
            removeItem.remove();
        }
    }).catch(error => {
        var statusDiv = document.createElement("div");
        statusDiv.textContent = error;
        document.getElementById("addCartStatus").appendChild(statusDiv);
        window.setTimeout(function () {
            statusDiv.remove();
        }, 2000);
        return false;
    });
}

function applyCoupon() {
    dataToSend = {
        cp: document.getElementById("coupon").value
    };
    fetch('./DiscountController', {
        method: 'POST',
        header: {
            'content-type': 'application/json'
        },
        body: JSON.stringify(dataToSend)
    }).then(response => {
        if (!response.ok) {
            throw new Error("Network was not ok. Status: " + response.status);
        }
        return response.text();
    }).then(data => {
        const discount = JSON.parse(data.toString());
        var ds = document.getElementById("discount");
        var totalCart = document.getElementById("totalPriceCart");
         var totalPriceAll = document.getElementById("totalPriceCartAddShip");
        var shipFees = document.getElementById("shipFees");
        if (discount.discountAmount > 0) {
            ds.innerHTML = "-" + discount.discountAmount + "$";
        } else if (discount.discountPercent > 0) {
            ds.innerHTML = "-" + discount.discountPercent + "%";
        }
        totalPriceAll.innerHTML = Number(totalCart.innerHTML) + Number(shipFees.innerHTML) - discount.discountAmount - (Number(totalCart.innerHTML) * discount.discountPercent / 100);
    }).catch(error => {
        var statusDiv = document.createElement("div");
        statusDiv.textContent = error;
        document.getElementById("checkOutForm").appendChild(statusDiv);
        window.setTimeout(function () {
            statusDiv.remove();
        }, 2000);
        return false;
    });
    return false;
}

function proceedToCheckout() {
    var cart = document.querySelectorAll(`tr[id="item"]`);
    if (cart.length > 0){
        window.location.assign("checkout.jsp");
    }else{
        var statusDiv = document.createElement("div");
        statusDiv.textContent = "Cart empty!";
        document.getElementById("addCartStatus").appendChild(statusDiv);
        window.setTimeout(function () {
            statusDiv.remove();
        }, 2000);
    }
}


function updateTotalCart(totalCart, ship, discount) {
    var changeTotalPriceCart = document.getElementById("totalPriceCart");
    changeTotalPriceCart.innerHTML = totalCart;
    var changeTotalPriceCartAddShip = document.getElementById("totalPriceCartAddShip");
    changeTotalPriceCartAddShip.innerHTML = Number(changeTotalPriceCart.innerHTML) + ship - discount;
}