<%-- 
    Document   : loginSeller
    Created on : Aug 23, 2023, 6:21:35 PM
    Author     : Raiku
--%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Login Admin</title>
        <link rel="stylesheet" href="material-design-iconic-font.min.css">
        <link rel="stylesheet" href="css/register.css" >
        <style>
            .form-submit {
                margin-bottom: 10px;
            }
            .loginhere {
                margin-top: 5px;
            }
        </style>
    </head>

    <body style="background-image: url(img/Fpt02.jpg);">
        <div class="main">

            <section class="signup">

                <div class="container">
                    <div class="signup-content">
                        <form action="LoginSellerController" class="signin-form">
                            <h2 class="form-title">Login Seller</h2>
                            <div class="form-group">
                                <input name ="user" type="text" class="form-input" placeholder="Username" required>
                            </div>
                            <div class="form-group">
                                <input name="pass" id="password-field" type="password" class="form-input" placeholder="Password" required>
                            </div>
                            <div class="form-group">
                                <button type="submit" class="form-submit">Log in</button>
                            </div>
                            <p class="text-danger" style="font-size: 26px;">
                                ${message}
                            </p>
                        </form>

                    </div>
                </div>
            </section>

        </div>   
    </body>
</html>