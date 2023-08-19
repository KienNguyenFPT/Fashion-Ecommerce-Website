<%-- 
    Document   : paymentBankTransfer
    Created on : Aug 18, 2023, 1:00:03 AM
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
        <h3>Order Success! Please payment....</h3>
        <img id="bankTransfer" src="" width="600" height="800" alt="alt"/>
        <script>
            var linkQr = document.getElementById("bankTransfer");
            linkQr.src = "<%=request.getSession().getAttribute("linkQr")%>";
        </script>
        <%@include file="footer.jsp" %>
    </body>
</html>
