<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
    <style>
        /* Add your existing styles here */
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Arial', sans-serif;
            background: linear-gradient(135deg, #6c63ff, #84fab0);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            color: #333;
        }

        .login-container {
            background: #fff;
            padding: 30px 40px;
            border-radius: 10px;
            box-shadow: 0 8px 15px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 400px;
        }

        .form-title {
            font-size: 2rem;
            margin-bottom: 20px;
            text-align: center;
            color: #6c63ff;
        }

        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }

        input {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
            font-size: 1rem;
        }

        input:focus {
            border-color: #6c63ff;
            outline: none;
            box-shadow: 0 0 5px rgba(108, 99, 255, 0.5);
        }

        .form-options {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
            font-size: 0.9rem;
        }

        .remember-me {
            display: flex;
            align-items: center;
            gap: 5px;
        }

        .forgot-password {
            text-decoration: none;
            color: #6c63ff;
            transition: color 0.3s;
        }

        .forgot-password:hover {
            color: #333;
        }

        .btn-submit {
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

        .btn-submit:hover {
            background: #574b90;
        }

        .signup-link {
            text-align: center;
            margin-top: 15px;
            font-size: 0.9rem;
        }

        .signup-link a {
            text-decoration: none;
            color: #6c63ff;
            font-weight: bold;
            transition: color 0.3s;
        }

        .signup-link a:hover {
            color: #333;
        }

        /* Error message styling */
        .error-message {
            background-color: #f8d7da;
            color: #721c24;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #f5c6cb;
            border-radius: 5px;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <form action="Login" method="post" class="login-form">
            <h1 class="form-title">Login</h1>
            
            <!-- Display error message if available -->
            <%
                String errorString = (String) request.getAttribute("errorString");
                if (errorString != null) {
            %>
                <div class="error-message">
                    <%= errorString %>
                </div>
            <%
                }
            %>
            
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" placeholder="Enter your username" required>
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="Enter your password" required>
            </div>

            <div class="form-options">
                <label class="remember-me">
                    <input type="checkbox" name="rememberMe" value="Y"> Remember Me
                </label>
                <a href="#" class="forgot-password">Forgot Password?</a>
            </div>

            <button type="submit" class="btn-submit">Login</button>

            <p class="signup-link">
                Don't have an account? <a href="/TranscriptVideo/signup.jsp">Sign Up</a>
            </p>
        </form>
    </div>
</body>
</html>
