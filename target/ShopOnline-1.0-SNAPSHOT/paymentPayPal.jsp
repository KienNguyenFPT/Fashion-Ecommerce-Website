<%-- 
    Document   : paymentPayPal
    Created on : Aug 17, 2023, 11:07:36 PM
    Author     : Raiku
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>HOME FASHION</title>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">

        <link href="img/favicon.ico" rel="icon">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet"> 
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <style>
            body {
                display:flex;
                align-items:center;
                justify-content:center;
                flex-direction:column;
                gap: 1rem;
            }
            #paypal-button-container{
                width: 40rem;
            }
        </style>
    </head>

    <body>
        <%@include file="header.jsp" %>
        <!-- Replace "test" with your own sandbox Business account app client ID -->
        <script src="https://www.paypal.com/sdk/js?client-id=Ab98Q28k3JktqKyFi21rzdra3u8ZHAxZl-uD7rChJaKWMArvSHp1PUwCjw7t8Ugwy4--KK3Xz9LOI9qM&currency=USD"></script>
        <!-- Set up a container element for the button -->
        <div id="paypal-button-container"></div>
        <script>
            paypal.Buttons({
                createOrder: function (data, actions) {
                    // This function sets up the details of the transaction, including the amount and line item details.
                    return actions.order.create({
                        purchase_units: [{
                                amount: {
                                    currency_code: "USD",
                                    value: ${sessionScope.orderDetail.getTotalAmount()}
                                }
                            }]
                    });
                },
                onApprove: function (data, actions) {
                    // This function captures the funds from the transaction.
                    return actions.order.capture().then(function (details) {
                        // This function shows a transaction success message to your buyer.
                        alert('Transaction completed by ' + details.payer.name.given_name);
                        const element = document.getElementById('paypal-button-container');
                        element.innerHTML = '<h3>Thank you for your payment!</h3>';
                        updateOrderStatus("paid");
                        document.update.submit();
                    });
                },
                onError: function (error) {
                    var status = document.createElement("div");
                    status.innerText = error;
                    document.getElementById("paypal-button-container").appendChild(status);
                    window.setTimeout(function () {
                        status.remove();
                    }, 5000);
                }
            }).render('#paypal-button-container');
            //This function displays Smart Payment Buttons on your web page.
        </script>
        <script src="js/payment.js"></script>
        <%@include file="footer.jsp" %>
    </body>
</html>

