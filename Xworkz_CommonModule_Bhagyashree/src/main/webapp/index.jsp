<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Xworkz</title>

    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-image: url('data:image/jpeg;base64,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');
            background-size: cover;
            background-position: center;
            background-attachment: fixed;
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f8f9fa;
            color: #ffffff;
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
            color: black; /* Change the text color */
        }

        @keyframes marquee {
            0%   { transform: translateX(-100%); }
            100% { transform: translateX(100%); }
        }

        /* Adjust navbar for better visibility */
        .navbar {
            background: rgba(0, 0, 0, 0.5); /* Semi-transparent background */
        }

        /* Ensure the logo is clear */
        .logo {
            max-width: 80px;
            max-height: 70px;
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

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
