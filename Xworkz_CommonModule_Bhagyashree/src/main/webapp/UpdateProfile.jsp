<%@page isELIgnored = "false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Update Profile</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Overall page background color */
        body {
            background-color: #f7f7f7; /* Light gray background for the whole page */
            padding: 20px;
            font-family: Arial, sans-serif;
        }

        /* Centering the form */
        .form-container {
            background-color: gray; /* White background for the form */
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); /* Slight shadow around the form */
            max-width: 500px;
            margin: 0 auto;
        }

        /* Centering the form title */
        .text-center {
            text-align: center;
            color: #333;
        }

        /* Form group styling */
        .form-group {
            margin-bottom: 15px;
        }

        /* Button styling */
        .btn-block {
            display: block;
            width: 100%;
        }

        /* Error message style */
        span {
            font-size: 12px;
            color: red;
        }

        /* Input field focus styling */
        .form-control:focus {
            border-color: #007bff; /* Change border color on focus */
            box-shadow: 0 0 5px rgba(0, 123, 255, 0.5); /* Light shadow effect on focus */
        }

        /* Styling the select dropdown */
        select.form-control {
            padding: 0.375rem 0.75rem;
        }
    </style>
</head>
<body>
    <!-- Form container -->
    <div class="form-container">
<c:forEach items="${error}" var="i">
    <span style="color:red">${i.message}</span>
</c:forEach>
            <form action="updateProfile" method="post" enctype="multipart/form-data">
            <h2 class="text-center">Update Profile</h2>

            <!-- Name input field -->
            <div class="form-group">
                <input type="text" class="form-control" name="name" id="name" onChange="onNameChange()" value="${userName}">
                <span id="userName"></span>
            </div>

            <!-- Email input field -->
            <div class="form-group">
                <input type="text" class="form-control" name="email" id="email" onChange="onEmailChange()" placeholder="Enter your email">
                <span id="userEmail"></span>
            </div>

            <!-- Phone input field -->
            <div class="form-group">
                <input type="text" class="form-control" name="phone" id="phone" onChange="onPhoneChange()" placeholder="##########">
                <span id="userPhone"></span>
            </div>

            <!-- Alternate Email input field -->
            <div class="form-group">
                <input type="text" class="form-control" name="alterEmail" id="alterEmail" onChange="onAlterEmailChange()" placeholder="Enter your alternate email">
                <span id="userAltEmail"></span>
            </div>

            <!-- Alternate Phone input field -->
            <div class="form-group">
                <input type="text" class="form-control" name="alterPhone" id="alterPhone" onChange="onAlterPhoneChange()" placeholder="##########">
                <span id="userAltPhone"></span>
            </div>

            <!-- Location dropdown -->
            <div class="form-group">
                <select id="location" name="location">
                   <option value = "">Select the option</option>
                      <c:forEach items="${listoflocation}" var="location">
                          <option value = "${location}">${location}</option>
                      </c:forEach>
                </select>
            </div>

            <input type="file" name="picture" />

            <!-- Submit button -->
            <button type="submit" class="btn btn-primary btn-block">Update Profile</button>
        </form>
    </div>
         <script>
             function onNameChange(){
             console.log('this is on place name');
             var name = document.getElementById('name');
             var nameValue = name.value;

             if (nameValue.trim().length < 4){
                 document.getElementById("userName").innerHTML = "Name must be at least 4 characters long.";
                 return;
             }else {
                 document.getElementById("userName").innerHTML = "";
             }

             var xhttp = new XMLHttpRequest();
             xhttp.open("GET","http://localhost:8080/Xworkz_CommonModule_Bhagyashree/name/" + nameValue);
             xhttp.send();

             xhttp.onload = function(){
             document.getElementById("userName").innerHTML = this.responseText;
             }
             }



             function onEmailChange() {
             console.log('this is on place email');
             var email = document.getElementById('email');
             var emailValue = email.value;

             if (!emailValue.includes('@gmail.com') && !emailValue.includes('@yahoo.com') && !emailValue.includes('@outlook.com') && !emailValue.includes('@hotmail.com') && !emailValue.includes('.edu')
                   && !emailValue.includes('.org') && !emailValue.includes('.info') && !emailValue.includes('.net'))
                   {
                 document.getElementById("userEmail").innerHTML = "Enter a valid email address.";
                 return;
             }else {
                 document.getElementById("userEmail").innerHTML = "";
             }

             var xhttp = new XMLHttpRequest();
             xhttp.open("GET", "http://localhost:8080/Xworkz_CommonModule_Bhagyashree/email/" + emailValue);
             xhttp.send();

             xhttp.onload = function(){
             document.getElementById("userEmail").innerHTML = this.responseText;
             }
             }



             function onPhoneChange() {
             console.log('this is on place phone');
             var phone = document.getElementById('phone');
             var phoneValue = phone.value;

             if (phoneValue.trim().length !== 10 || (!phoneValue.startsWith("6") && !phoneValue.startsWith("7")
               && !phoneValue.startsWith("8") && !phoneValue.startsWith("9")))
               {
                 document.getElementById("userPhone").innerHTML = "Phone number must contain 10 digits and should be valid.";
                 return;
             }else {
                 document.getElementById("userPhone").innerHTML = "";
             }

             var xhttp = new XMLHttpRequest();
             xhttp.open("GET", "http://localhost:8080/Xworkz_CommonModule_Bhagyashree/phone/" + phoneValue);
             xhttp.send();

             xhttp.onload = function(){
             document.getElementById("userPhone").innerHTML = this.responseText;
             }
             }



             function onAlterEmailChange() {
             console.log('this is on place alterEmail');
             var alterEmail = document.getElementById('alterEmail');
             var alterEmailValue = alterEmail.value;

             if (!alterEmailValue.includes('@gmail.com') && !alterEmailValue.includes('@yahoo.com') && !alterEmailValue.includes('@outlook.com') && !alterEmailValue.includes('@hotmail.com')
                 && !alterEmailValue.includes('.edu') && !alterEmailValue.includes('.org') && !alterEmailValue.includes('.info') && !alterEmailValue.includes('.net'))
                 {
                   document.getElementById("userAltEmail").innerHTML = "Enter a valid alter email address.";
                   return;
             }else {
                   document.getElementById("userAltEmail").innerHTML = "";
             }

             var xhttp = new XMLHttpRequest();
             xhttp.open("GET", "http://localhost:8080/Xworkz_CommonModule_Bhagyashree/alterEmail/" + alterEmailValue);
             xhttp.send();

             xhttp.onload = function(){
             document.getElementById("userAltEmail").innerHTML = this.responseText;
             }
             }



             function onAlterPhoneChange() {
             console.log('this is on place alterPhone');
             var alterPhone = document.getElementById('alterPhone');
             var alterPhoneValue = alterPhone.value;

             if (alterPhoneValue.trim().length !== 10 || (!alterPhoneValue.startsWith("6") && !alterPhoneValue.startsWith("7")
                 && !alterPhoneValue.startsWith("8") && !alterPhoneValue.startsWith("9")))
                  {
                     document.getElementById("userAltPhone").innerHTML = "Enter alter phone number must contain 10 digits and should be valid.";
                     return;
             }else {
                     document.getElementById("userAltPhone").innerHTML = "";
             }

             var xhttp = new XMLHttpRequest();
             xhttp.open("GET", "http://localhost:8080/Xworkz_CommonModule_Bhagyashree/alterPhone/" + alterPhoneValue);
             xhttp.send();

             xhttp.onload = function(){
             document.getElementById("userAltPhone").innerHTML = this.responseText;
             }
             }
         </script>
    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
