<%@page isELIgnored = "false"%>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Success Page</title>
  <!-- Bootstrap CSS -->
  <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
  <!-- Custom CSS -->
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f7fa;
      margin: 0;
      padding: 0;
    }

    .container {
      max-width: 600px;
      margin: 100px auto;
      background-color: #fff;
      padding: 40px;
      border-radius: 8px;
      box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
      text-align: center;
    }

    .success-message {
      color: #28a745;
      font-size: 2rem;
      font-weight: bold;
    }

    .lead {
      color: #6c757d;
      font-size: 1.25rem;
    }

    footer {
      text-align: center;
      padding: 20px;
      background-color: #1e3d58;
      color: white;
      margin-top: 30px;
    }

  </style>
</head>
<body>

  <div class="container">
    <h2 class="success-message">Success!</h2>
    <p class="lead">Your action was successful. Thank you for your submission!</p>

    <img src="download?filePath=${filePath}" alt="profile picture" width="100" height="100">

    <form action="update" method="get">
<input type="text" name="name" value="${userName}" />
    <input type="submit" value="Update Profile">
    </form>
  </div>
  <!-- Bootstrap JS and dependencies -->
  <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
