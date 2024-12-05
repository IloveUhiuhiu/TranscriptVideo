<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="model.bean.*"%>
<%@page import="model.bo.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AutoCapCap</title>
    
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            font-family:  sans-serif;
            background-color: #f4f4f4;
            color: #333;
        }
        header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: white;
            padding: 10px 20px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            position: fixed;
            width: 100%;
            z-index: 1000;
        }
        .logo {
            font-size: 1.8rem;
            font-weight: 600;
            color: #81c408; 
        }
        nav {
        	display: flex;
            gap: 20px;
        }
        nav a {
            color: #333;
            font-size: 1.1rem;
            font-weight: 600;
            text-decoration: none;
            padding: 8px 12px;
            transition: color 0.3s;
        }
        nav a:hover {
            color: #81c408;
        }

        .content {
            padding: 100px 20px; 
            text-align: center;  
        }
        h2 {
            font-size: 2.5rem;
            font-weight: 600;
            margin-bottom: 20px;
        }
        p {
            font-size: 1.2rem;
            line-height: 1.6;
            margin-bottom: 30px;
            color: #666;
        }
        .form-container {
		    display: flex;
		    flex-direction: column; 
		    align-items: center;    
		    margin-top: 20px;     
		}
		
		.input-container {
		    display: flex;          
		    align-items: center;    
		    margin-bottom: 20px;   
		}

		.button-container {
		    margin-top: 20px; 
		    text-align: center; 
		}
        .language-select {
		    padding: 10px;
		    border: 1px solid #ccc;
		    border-radius: 5px;
		    font-size: 16px;
		    appearance: none;
		    background-size: 12px; 
		    width: 200px; 
		}

		/* Tùy chỉnh option */
		.language-select option {
		    padding: 5px;
		}
		
		/* Hover effect */
		.language-select:hover {
		    border-color: #007bff;
		}
		
		/* Focus effect */
		.language-select:focus {
		    outline: none;
		    box-shadow: 0 0 0 2px rgba(0, 123, 255, 0.25);
		}

        .btn-upload {
        	padding: 6px 24px;
            font-weight: 500;
            font-size: 18px;
            background: #81c408;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            margin-left: 10px;
            transition: background 0.3s;
        }
        .btn-select {
            padding: 12px 40px;
            font-weight: 500;
            font-size: 18px;
            background: #81c408;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            margin-left: 10px;
            transition: background 0.3s;
            border: none;
        }

        .btn-upload:hover, .btn-select:hover {
            background: #218838;
        }

        .file-input {
            display: none;
        }
        
        
        footer {
            position: fixed;
            left: 0;
            right: 0;
            z-index: 10000;
            bottom: 0;
            background: hsla(0, 0%, 100%, .8);
            padding: 12px 24px;
            font-size: 14px;
            text-align: center;
            
        }

        
    </style>
</head>
<%	
	LanguageBo langBo = new LanguageBo();
	List<Language> langs = langBo.getAll();
	
%>
<body>
    <header>
        <nav>
        	<div class="logo">AutoCapCap</div>
            <a href="#merge">Trang chủ</a>
            <a href="#compress">Kết quả</a>
        </nav>
    </header>


    <div class="content">
        <h2>Tạo subtitle cho Video</h2>
        <p>Chọn ngôn ngữ và video mà bạn muốn chuyển đổi.</p>

        <form class="form-container" method="POST" action="UploadServlet" enctype="multipart/form-data">
           <div class="input-container">
			    <select class="language-select" name="language">
			    	<%
			    		for (Language lang: langs) {
			    	%>
			        <option value=<%=lang.getLanguageId() %>><%=lang.getName() %></option>
			        <%
			        }
			        %>
			    </select>
			    <label class="btn-upload" for="file-input">Chọn Video</label>
			    <input type="file" id="file-input" class="file-input" accept="video/*" multiple name="video">
			</div>
			<div class="button-container">
			    <input type="submit" class="btn-select" value="Xác Nhận">
			</div>
            
        </form>
    </div>
	
		
    <footer>
        <p>© ILoveUhiuhiu & hung55555 2024, All rights reserved.</p>
    </footer>
</body>

</html>