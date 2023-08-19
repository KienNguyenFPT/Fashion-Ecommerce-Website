<%-- 
    Document   : orderDetails
    Created on : Aug 16, 2023, 8:22:26 PM
    Author     : Raiku
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">
    <head>
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">

        <link href="img/favicon.ico" rel="icon">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet"> 
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
        <title>Your order detail</title>
        <style type="text/css">
            #order-detail{
                background:#eee;
                display:flex;
                align-items:center;
                justify-content:center;
                flex-direction:column;
                gap: 1rem;
            }
            tr td:first-child {
                text-align: left;
            }
            tr td:last-child {
                text-align: right;
            }
            .card {
                box-shadow: 0 20px 27px 0 rgb(0 0 0 / 5%);
            }
            .card {
                position: relative;
                display: flex;
                flex-direction: column;
                min-width: 0;
                word-wrap: break-word;
                background-color: #fff;
                background-clip: border-box;
                border: 0 solid rgba(0,0,0,.125);
                border-radius: 1rem;
            }
            .item-name{
                display: flex;
                justify-content:center;
                flex-direction: column;
            }

            .text-reset {
                --bs-text-opacity: 1;
                color: inherit!important;
            }
            a {
                color: #5465ff;
                text-decoration: none;
            }
        </style>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div id="order-detail" class="container-fluid">
            <div class="container">
                <c:set var="orderDetail" value="${sessionScope.orderDetail}"/>
                <div class="d-flex justify-content-between align-items-center py-3">
                    <h2 class="h5 mb-0"><a href="#" class="text-muted"></a> Order #${orderDetail.getOrderId()}</h2>
                </div>

                <div class="row">
                    <div class="col-lg-8">

                        <div class="card mb-4">
                            <div class="card-body">
                                <div class="mb-3 d-flex justify-content-between">
                                    <div>
                                        <span class="me-3">${orderDetail.getOrderDate()}</span>
                                        <span class="me-3">#${orderDetail.getOrderId()}</span>
                                        <span class="me-3">Payment Method </span>
                                        <span class="badge rounded-pill bg-info" style="color: white;">${orderDetail.getPaymentMethod()}</span>
                                    </div>
                                    <div class="d-flex">
                                        <button class="btn btn-link p-0 me-3 d-none d-lg-block btn-icon-text"><i class="bi bi-download"></i> <span class="text">Invoice</span></button>
                                        <div class="dropdown">
                                            <button class="btn btn-link p-0 text-muted" type="button" data-bs-toggle="dropdown">
                                                <i class="bi bi-three-dots-vertical"></i>
                                            </button>
                                            <ul class="dropdown-menu dropdown-menu-end">
                                                <li><a class="dropdown-item" href="#"><i class="bi bi-pencil"></i> Edit</a></li>
                                                <li><a class="dropdown-item" href="#"><i class="bi bi-printer"></i> Print</a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <table class="table table-borderless">
                                    <tbody>
                                        <c:set var="total" value="${0}"/>
                                        <c:forEach items="${orderDetail.getOrderItemList()}" var="item">
                                            <tr>
                                                <td>
                                                    <div class="d-flex mb-2">
                                                        <div class="item-name flex-shrink-0" style="margin-right: 1rem;">
                                                            <img src="${item.getProductId().getImg()}" alt width="35" class="img-fluid">
                                                        </div>
                                                        <div class="item-name flex-lg-grow-1 ms-3">
                                                            <h6 class="small mb-0"><a href="#" class="text-reset">${item.getProductId().getName()}</a></h6>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td  class="item-name">${item.getQuantity()}</td>
                                                <td class="text-end">$${item.getQuantity() * item.getProductId().getPrice()}</td>
                                                <c:set var="total" value="${total + (item.getQuantity() * item.getProductId().getPrice())}"/>
                                            </tr>
                                        </c:forEach>

                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <td colspan="2">Subtotal</td>
                                            <td class="text-end">$${total}</td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">Shipping</td>
                                            <td class="text-end">$${orderDetail.getShipFee()}</td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">Discount (Code: ${orderDetail.getDiscountCode()})</td>
                                            <td class="text-danger text-end"></td>
                                        </tr>
                                        <tr class="fw-bold">
                                            <td colspan="2">TOTAL</td>
                                            <td class="text-end">$${orderDetail.getTotalAmount()}</td>
                                        </tr>
                                    </tfoot>
                                </table>
                            </div>
                        </div>

                        <div class="card mb-4">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-lg-4">
                                        <h3 class="h6">Payment Method</h3>
                                        <p>${orderDetail.getPaymentMethod()} <br>
                                            Total: $${orderDetail.getTotalAmount()} <span class="badge bg-success rounded-pill" id="statusOrder" style="color: white;">${orderDetail.getStatus()}</span></p>
                                    </div>
                                    <div class="col-lg-4">
                                        <h3 class="h6">Bill</h3>
                                        <address>
                                            <strong>${orderDetail.getCustomerId().getCustomerName()}</strong><br>
                                            ${orderDetail.getCustomerId().getAddress()}<br>
                                            ${orderDetail.getCustomerId().getEmail()}<br>
                                            <abbr title="Phone">Phone:</abbr> ${orderDetail.getCustomerId().getPhone()}
                                        </address>
                                    </div>
                                    <div class="col-lg-4">
                                        <h3 class="h6">Shipping Information</h3>
                                        <address>
                                            <strong>${orderDetail.getShipName()}</strong><br>
                                            ${orderDetail.getShipAddress()}<br>
                                            ${orderDetail.getShipMail()}<br>
                                            <abbr title="Phone">Phone:</abbr> ${orderDetail.getShipPhone()}
                                        </address>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="card mb-4">
                            <div class="card-body">
                                <div class="mb-3 d-flex justify-content-between">
                                    <label for="updateStatus">Update Status</label>
                                    <select id="updateStatus">
                                        <option value="first">---Select---</option>
                                        <option value="paid">paid</option>
                                        <option value="processing">processing</option>
                                        <option value="complete">completed</option>
                                    </select>
                                </div>
                                <div id="status-message"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="footer.jsp" %>
        <<script src="js/updateOrderDetail.js"></script>
    </body>
</html>
