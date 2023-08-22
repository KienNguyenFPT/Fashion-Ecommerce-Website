<%-- 
    Document   : orderManager
    Created on : Aug 17, 2023, 2:20:43 AM
    Author     : Raiku
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
        <title>Order Manager</title>

        <link href="css/styleOrders.css" rel="stylesheet">
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div>
            <label style="margin: 2rem;" for="payment">Display Payment Method by</label>
            <select class="" id="payment">
                <option style="text-align: center;" value="all">----Display All----</option>
                <option style="text-align: center;" value="PayPal">Display all PayPal Payment Method</option>
                <option style="text-align: center;" value="Bank">Display all Bank Payment Method</option>
                <option style="text-align: center;" value="direct">Display all Direct Payment Method</option>
            </select>
            <label style="margin: 2rem;" for="status">Display Status by</label>
            <select id="status">
                <option style="text-align: center;" value="all">---Display All---</option>
                <option style="text-align: center;" value="pending">Display all pending order</option>
                <option style="text-align: center;" value="paid">Display all paid order</option>
                <option style="text-align: center;" value="processing">Display all processing order</option>
                <option style="text-align: center;" value="complete">Display all completed order</option>
            </select>
        </div>
        <div id="status-error"></div>
        <form action="OrderDetailController">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Customer ID</th>
                        <th>Order Date</th>
                        <th>Total Amount</th>
                        <th>Payment Method</th>
                        <th>Status</th>
                        <th>Detail</th>
                    </tr>
                </thead>
                <tbody id="table-body">
                    <c:forEach items="${sessionScope.orderList}" var="item">
                        <tr>
                            <td>${item.getOrderId()}</td>
                            <td>${item.getCustomerId().getCustomerId()}</td>
                            <td>${item.getOrderDate()}</td>
                            <td>${item.getTotalAmount()}</td>
                            <td>${item.getPaymentMethod()}</td>
                            <td>${item.getStatus()}</td>
                            <td><button name="view" id="view" value="${item.getOrderId()}">View Detail</button></td>
                        </tr>
                    </c:forEach> 
                </tbody>
            </table>
        </form>
        <div class="col-12 pb-1">
            <nav aria-label="Page navigation">
                <ul id="all-pages" class="pagination justify-content-center mb-3">
                    <li class="page-item disabled">
                        <button class="page-link" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                            <span class="sr-only">Previous</span>
                        </button>
                    </li>
                    <c:forEach begin="1" end="${totalOrders}" var="o">
                        <li class="page-item">
                            <button onclick="changePage(${o})" class="page-link active">${o}</button>
                        </li>
                    </c:forEach>
                    <li class="page-item">
                        <button class="page-link" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                            <span class="sr-only">Next</span>
                        </button>
                    </li>
                </ul>
            </nav>
        </div>
        <%@include file="footer.jsp" %>
        <script src="js/orderManager.js"></script>
    </body>
</html>
