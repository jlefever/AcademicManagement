<!DOCTYPE html >
<html >
	<head>
		<title>Login Page</title>
	</head>
<style>      
</style>
</head>
	<body>
  <!-- Begin Page Content -->
            <div id="container">
                <form action="login_process.php" method="post">
                	<p>
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username">
                    </p>
                    <p>
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password">
                    </p>
                    <p>
                     <label for="password">Re-enter Password</label>
                    <input type="password" id="password" name="password">
                    </p>
                    <p>
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email">
                    </p>
                    <div id="lower">
                    <input type="submit" value="Login">
                    </div><!--/ lower-->
                </form>
            </div><!--/ container-->
            <!-- End Page Content -->
     </body>
 </html>