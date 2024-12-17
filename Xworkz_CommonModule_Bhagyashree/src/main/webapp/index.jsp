<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Xworkz</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f9fa;
        }

        .navbar-custom {
            background-color: gray; /* Custom navbar color */
        }

        .navbar-custom .nav-link {
            margin-left: 10px;
            color: white;
        }

        .content {
            text-align: center;
            margin-top: 20px;
        }

        .btn-custom {
            margin-left: 5px;
            color: white;
            background-color: #007bff; /* Change button color */
            border: none;
        }

        .btn-custom:hover {
            background-color: #0056b3;
        }

        .logo {
            border-radius: 10px;
        }

        .scrolling-text {
            overflow: hidden;
            white-space: nowrap;
            box-sizing: border-box;
            animation: marquee 10s linear infinite;
            color: #ff5722; /* Change the text color */
        }

        @keyframes marquee {
            0%   { transform: translateX(-100%); }
            100% { transform: translateX(100%); }
        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-custom">
    <a class="navbar-brand" href="#">
        <img src="https://x-workz.in/static/media/Logo.cf195593dc1b3f921369.png" width="80" height="70" alt="Xworkz Logo" class="logo">
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link btn btn-success" href="SignIn.jsp">SignIn</a>
            </li>
            <li class="nav-item">
                <a class="nav-link btn btn-success" href="SignUp.jsp">SignUp</a>
            </li>
        </ul>
    </div>
</nav>
<div class="container content">
    <h2 class="scrolling-text">Welcome to Our Web Page</h2>
</div>

<!-- Include Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
