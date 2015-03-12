        <!DOCTYPE html >
        <html >
        <head>
        <title>Login Page</title>
        <style>
       
        </style>

        </head>

        <body>
            <!-- Begin Page Content -->
            <div id="container">
                <form action="login_process.php" method="post">
                    <label for="loginmsg" style="color:hsla(0,100%,50%,0.5); font-family:"Helvetica Neue",Helvetica,sans-serif;"><?php  echo @$_GET['msg'];?></label>
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password">
                    <div id="lower">
                        <input type="checkbox"><label class="check" for="checkbox">Keep me logged in</label>
                        <input type="submit" value="Login">
                    </div><!--/ lower-->
                </form>
            </div><!--/ container-->
            <!-- End Page Content -->
        </body>
        </html>