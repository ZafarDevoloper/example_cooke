<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Download file</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
        }

        h1 {
            color: #333;
        }

        form {
            margin-top: 20px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }

        input[type="text"] {
            padding: 5px;
            width: 200px;
        }

        button[type="submit"] {
            padding: 5px 10px;
            background-color: #4caf50;
            color: #fff;
            border: none;
            cursor: pointer;
        }
    </style>
</head>
<body>
<h1>Download File</h1>
<form method="post">
    <div>
        <label for="file">Download File</label>
        <input type="text" name="myFile" id="file">
    </div>
    <button type="submit">Download</button>
</form>
</body>
</html>
