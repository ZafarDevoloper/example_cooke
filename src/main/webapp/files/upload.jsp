<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload file</title>
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

        input[type="file"] {
            margin-bottom: 10px;
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
<h1>Upload File</h1>
<form enctype="multipart/form-data" method="post"  action="${pageContext.request.contextPath}/upload">
    <div>
        <label for="file">Upload File</label>
        <input type="file" name="file" id="file">
    </div>
    <button type="submit">Upload</button>
</form>
</body>
</html>
