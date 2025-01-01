<html>
<head>
<title>Another Page</title>
</head>
<body>
      <form action="signup" method="post">
            <h2 class="text-center">Sign Up</h2>
            <div class="form-group">
                <input type="text" class="form-control" name="name" id="name" onChange="onNameChange()" placeholder="Enter your name" >
                <span id="userName" style="color: red;"></span>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="email" id="email" onChange="onEmailChange()" placeholder="Enter your email" >
                <span id="userEmail" style="color: red;"></span>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="phone" id="phone" onChange="onPhoneChange()" placeholder="##########" >
                <span id="userPhone" style="color: red;"></span>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="alterEmail" id="alterEmail" onChange="onAlterEmailChange()" placeholder="Enter your alternate email">
                <span id="userAltEmail" style="color: red;"></span>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="alterPhone" id="alterPhone" onChange="onAlterPhoneChange()" placeholder="##########">
                <span id="userAltPhone" style="color: red;"></span>
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="location" placeholder="Enter your location">
                <span class="input-group-text"><i class="fa-solid fa-location-dot"></i></span>
            </div>
            <button type="submit" class="btn btn-primary btn-block">Another Submit</button> 
      </form>
</body>
</html>