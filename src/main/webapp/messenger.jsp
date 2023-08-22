<%-- 
    Document   : messenger
    Created on : Aug 19, 2023, 4:42:58 PM
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
        <title>Messenger Box</title>
        <style>
            body {
                display:flex;
                align-items:center;
                justify-content:center;
                flex-direction:column;
                gap: 1rem;
            }


            table, th, td{
                font-size: 1.2rem;
            }
            th{
                background-color: #D19C97;
            }
            tbody > tr{
                border-left: 1px solid gray;
                border-right: 1px solid gray;
                border-bottom: 1px solid gray;
                border-collapse: collapse;
            }
            th, td{
                text-align: center;
                padding: .5rem 1rem;
            }

            button{
                font-size: .8em;
                padding: .1rem;
            }
        </style>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <form action="openChatRoomController">
            <div class="col-12 d-flex align-items-center justify-content-center">
                <label class="p-2 m-2" for="createChat">Have problem?</label>
                <button class="p-2 m-2" id="createChat" name="chat" value="createNew">Chat Now</button>
            </div>
            <div id="status"></div>

            <c:choose>
                <c:when test="${sessionScope.userRole == 'customer'}">
                    <table>
                        <thead>
                            <tr>
                                <th>Room ID</th>
                                <th>Admin ID</th>
                                <th>Chat</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${sessionScope.listChatRoom}" var="room">
                                <tr>
                                    <td>${room.roomId}</td>
                                    <td>${room.user2Id.adminId}</td>
                                    <td><button name="chat" value="${room.roomId}">Chat now!</button></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:when>
                <c:when test="${sessionScope.userRole == 'admin'}">
                    <table>
                        <thead>
                            <tr>
                                <th>Room ID</th>
                                <th>Customer ID</th>
                                <th>Customer Name</th>
                                <th>Customer Email</th>
                                <th>Customer Phone</th>
                                <th>Customer Address</th>
                                <th>Chat</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${sessionScope.listChatRoom}" var="room">
                                <tr>
                                    <td>${room.roomId}</td>
                                    <td>${room.user1Id.customerId}</td>
                                    <td>${room.user1Id.customerName}</td>
                                    <td>${room.user1Id.email}</td>
                                    <td>${room.user1Id.phone}</td>
                                    <td>${room.user1Id.address}</td>
                                    <td><button name="chat" value="${room.roomId}">Continue!</button></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </c:when>
            </c:choose>
        </form>
        <script src="js/messenger.js" defer></script>

        <%@include file="footer.jsp" %>
    </body>
</html>
