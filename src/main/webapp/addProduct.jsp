<%-- 
    Document   : addProduct
    Created on : Aug 23, 2023, 4:28:18 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <meta content="Free HTML Templates" name="keywords">
        <meta content="Free HTML Templates" name="description">

        <link href="img/favicon.ico" rel="icon">
        <link rel="preconnect" href="https://fonts.gstatic.com">
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet"> 
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Product</title>
    </head>

    <body>
        <%@include file="header.jsp" %>
        <div class="container">
            <section class="panel panel-default">
                <div class="panel-heading"> 
                    <h3 class="panel-title">Add product</h3> 
                </div> 
                <div class="panel-body">

                    <form onsubmit="return checkValid()" class="form-horizontal" role="form">

                        <div class="form-group">
                            <label for="name" class="col-sm-3 control-label">Seller_id</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="sellerId" id="sellerId" placeholder="${sessionScope.user.getSellerId()}" disabled>
                            </div>
                        </div> 

                        <div class="form-group">
                            <label for="name" class="col-sm-3 control-label">Product name</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="name" id="name" placeholder="Product name" required>
                            </div>
                        </div> 

                        <div class="form-group">
                            <label for="category" class="col-sm-3 control-label">Product category</label>
                            <div class="col-sm-3">
                                <select id="category" name="category" class="form-control">
                                    <option value="">Choose option</option>
                                    <option value="1">Shirts</option>
                                    <option value="2">Dresses</option>
                                    <option value="3">Jeans</option>
                                    <option value="4">Swimwears</option>
                                    <option value="5">Sleepwears</option>
                                    <option value="6">Sportswears</option>
                                    <option value="7">Jumpsuits</option>
                                    <option value="8">Blazers</option>
                                    <option value="9">Jackets</option>
                                    <option value="10">Shoes</option>
                                </select>
                            </div>
                            <div class="col-sm-3">
                                <p id="message" style="color: red;"></p>
                            </div>
                        </div> 
                        
                        <div class="form-group">
                            <label for="description" class="col-sm-3 control-label">Product description</label>
                            <div class="col-sm-9">
                                <textarea name="description" id="description" class="form-control"></textarea>
                            </div>
                        </div> 

                        <div class="form-group">
                            <label class="col-sm-3 control-label">Quantity</label>
                            <div class="col-sm-3"> 
                                <label class="control-label small">Quantity current have:</label>
                                <input type="number" min="0" class="form-control" name="quantity_current" id="quantity_current" placeholder="quantity current have" required>
                            </div>
                            <div class="col-sm-3">   
                                <label class="control-label small">Quantity sold:</label>
                                <input type="number" min="0" class="form-control" name="quantity_sold" id="quantity_sold" placeholder="quantity sold">
                            </div>         
                        </div> 

                        <div class="form-group">
                            <label class="col-sm-3 control-label">Price</label>
                            <div class="col-sm-3"> 
                                <label class="control-label small">New price:</label>
                                <input type="number" min="0" class="form-control" name="new_price" id="new_price" placeholder="new price" required>
                            </div>
                            <div class="col-sm-3">   
                                <label class="control-label small">Old price:</label>
                                <input type="number" min="0" class="form-control" name="old_price" id="old_price" placeholder="old price">
                            </div>         
                        </div> 
                        <div class="form-group">
                            <label for="name" class="col-sm-3 control-label">URL image</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="file_img" name="file_img" required>
                            </div>
                        </div> 

                        <hr>
                        <div class="form-group">
                            <div class="col-sm-offset-3 col-sm-9">
                                <button type="submit" class="btn btn-primary">ADD PRODUCT</button>
                            </div>
                        </div> 
                    </form>
                </div>
                <div id="status"></div>
            </section>
        </div> 
        <%@include file="footer.jsp" %>
        <script src="js/addProduct.js"></script>
    </body>
</html>

