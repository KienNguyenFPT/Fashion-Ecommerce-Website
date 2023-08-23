<%-- 
    Document   : chatBox
    Created on : Aug 19, 2023, 4:48:22 PM
    Author     : Raiku
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            body{
                overflow-x: hidden;
            }
            #chat-area {
                overflow-y: scroll;
            }
            .message-container {
                display: flex;
                margin: 10px 0;
                align-items: flex-start;
            }

            .message-sender {
                font-weight: bold;
                margin-right: 5px;
            }

            .message-content {
                background-color: #f0f0f0;
                padding: 5px;
                border-radius: 5px;
            }
        </style>
        <title>Chat Box</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container mt-5 mb-5">
            <div class="row">
                <div class="card col-lg-12">
                    <div class="card-body d-flex flex-column justify-content-between" style="height: 50vh;">
                        <div id="chat-area">
                            <c:forEach items="${sessionScope.chatList}" var="mess">
                                <div class="message-container">
                                    <span class="message-sender">${mess.senderName}:</span>
                                    <div class="message-content">${mess.content}</div>
                                </div>
                            </c:forEach>
                        </div>
                        <div id="input" class="mb-2 d-flex justify-content-center align-items-center">
                            <input class="border-right border-5 rounded" id="message-input" type="text" placeholder="Enter message....">
                            <button class="border-5 rounded" id="send-message">Send</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="status"></div>
        <%@include file="footer.jsp" %>
        <<script src="js/chatBox.js" defer></script>
    </body>
</html>
