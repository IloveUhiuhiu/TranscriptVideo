<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="UploadServlet" method="post" enctype="multipart/form-data">
    <label for="language">Language:</label>
    <input type="text" id="language" name="language" required>
    <br>
    <label for="video">Video:</label>
    <input type="file" id="video" name="video" accept="video/*" required>
    <br>
    <input type="submit" value="Upload Video">
</form>
</body>
</html>