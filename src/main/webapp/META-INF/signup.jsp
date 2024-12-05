<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style >
.signup-container {
    background: #fff;
    padding: 30px 40px;
    border-radius: 10px;
    box-shadow: 0 8px 15px rgba(0, 0, 0, 0.1);
    width: 100%;
    max-width: 400px;
}

/* Tiêu đề của form */
.signup-form .form-title {
    font-size: 2rem;
    margin-bottom: 20px;
    text-align: center;
    color: #6c63ff;
}

/* Nhóm nhập liệu */
.signup-form .form-group {
    margin-bottom: 15px;
}

.signup-form label {
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
}

.signup-form input {
    width: 100%;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 5px;
    font-size: 1rem;
}

.signup-form input:focus {
    border-color: #6c63ff;
    outline: none;
    box-shadow: 0 0 5px rgba(108, 99, 255, 0.5);
}

/* Nút bấm */
.signup-form .btn-submit {
    width: 100%;
    background: #6c63ff;
    color: #fff;
    padding: 10px;
    border: none;
    border-radius: 5px;
    font-size: 1rem;
    cursor: pointer;
    transition: background 0.3s;
}

.signup-form .btn-submit:hover {
    background: #574b90;
}

/* Liên kết dưới form */
.signup-form .signup-link {
    text-align: center;
    margin-top: 15px;
    font-size: 0.9rem;
}

.signup-form .signup-link a {
    text-decoration: none;
    color: #6c63ff;
    font-weight: bold;
    transition: color 0.3s;
}

.signup-form .signup-link a:hover {
    color: #333;
}

/* Hiển thị lỗi */
.signup-form .error-message {
    color: red;
    text-align: center;
    margin-bottom: 15px;
    font-size: 0.9rem;
}

</style>
</head>
<body>
<div class="signup-container">
    <form action="Signup" method="post" class="signup-form">
        <h1 class="form-title">Sign Up</h1>

        <!-- Thông báo lỗi -->
        <div class="error-message">
            <!-- Hiển thị lỗi nếu có -->
            ${errorString}
        </div>

        <!-- Email -->
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" id="email" name="email" placeholder="Enter your email" required>
        </div>

        <!-- Password -->
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" id="password" name="password" placeholder="Enter your password" required>
        </div>

        <!-- Confirm Password -->
        <div class="form-group">
            <label for="confirmPassword">Confirm Password</label>
            <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Re-enter your password" required>
        </div>

        <!-- Submit Button -->
        <button type="submit" class="btn-submit">Sign Up</button>

        <p class="signup-link">
            Already have an account? <a href="/login">Login</a>
        </p>
    </form>
</div>
</body>
</html>