<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Model.Bean.*"%>
<%@page import="Model.BO.*"%>

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
          	background: hsla(0, 0%, 100%, .8);
		    padding: 12px 24px;
		    font-size: 14px;
		    text-align: center;
		    margin-top: 100px; 
		    box-shadow: 0 -2px 5px rgba(0, 0, 0, 0.1);
        }
           .auth-buttons {
	    	display: flex;
	    	gap: 10px;
	    	align-items: right;
	    	
		}

		.auth-buttons a {
		    text-decoration: none;
		    font-size: 1rem;
		    font-weight: 600;
		    padding: 8px 16px;
		    border-radius: 5px;
		    transition: all 0.3s ease-in-out;
		}


		.btn-login {
		    background-color: white;
		    color: #333;
		    border: 2px solid #81c408;
		}

		.btn-login:hover {
		    background-color: #81c408;
		    color: white;
		}


		.btn-signup {
		    background-color: #81c408;
		    color: white;
		    border: 2px solid #81c408;
		}
		
		.btn-signup:hover {
		    background-color: white;
		    color: #81c408;
		}
		.history-table {
		    width: 100%;
		    border-collapse: collapse;
		    margin-top: 20px;
		    font-size: 1rem;
		    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
		    background-color: white;
		}

		.history-table thead {
		    background-color: #81c408;
		    color: white;
		}
		
		.history-table th, .history-table td {
		    padding: 10px 15px;
		    text-align: center;
		    border: 1px solid #ddd;
		}
		
		.history-table th {
		    font-weight: bold;
		}
		
		.history-table tbody tr:nth-child(odd) {
		    background-color: #f9f9f9;
		}
		
		.history-table tbody tr:nth-child(even) {
		    background-color: #ffffff;
		}
		
		.history-table tbody tr:hover {
		    background-color: #f1f1f1;
		}
		
		.history-table a {
		    text-decoration: none;
		    color: #81c408;
		    font-weight: bold;
		    transition: color 0.3s;
		}
		
		.history-table a:hover {
		    color: #218838;
		}
		
		.status-icon {
		    display: inline-block;
		    width: 20px;
		    height: 20px;
		}
		
		.processing-icon {
		    border: 3px solid #ccc;
		    border-top: 3px solid #81c408;
		    border-radius: 50%;
		    width: 20px;
		    height: 20px;
		    animation: spin 1s linear infinite;
		}
		
		.failed-icon {
		    color: red;
		    font-size: 20px;
		    font-weight: bold;
		}
		
		.completed-icon {
		    color: #218838;
		    font-size: 20px;
		    font-weight: bold;
		}
		.btn-logout {
		    background-color: white;
		    color: #333;
		    border: 2px solid #81c408;
		    padding: 8px 16px;
		    border-radius: 5px;
		    font-size: 1rem;
		    font-weight: 600;
		    margin-left: 10px;
		    text-decoration: none;
		    transition: all 0.3s ease-in-out;
		}
		
		.btn-logout:hover {
		    background-color: #81c408;
		    color: white;
		}
		.email-view{
			margin-left: 700px;
		}

    </style>
</head>
<%
	User user = (User) session.getAttribute("User");
	boolean isLoggedIn = user != null;
	VideoBo videoBo = new VideoBo();
	List<Video> videoHistory;
	if (isLoggedIn) {
	    videoHistory = videoBo.getVideoHistoryByEmail(user.getEmail());
	} else {
	    videoHistory = new ArrayList<>();
	}
%>
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
        <% if (isLoggedIn) { %>
    		<div class="auth-buttons">
        		<span class="email-view">Chào,<%=user.getEmail() %></span>
        		 
    		</div>
    		<a href="/TranscriptVideo/logout" class="btn-logout">ĐĂNG XUẤT</a>
		<% } else { %>
		    <div class="auth-buttons">
		        <a href="/TranscriptVideo/login.jsp" class="btn-login">ĐĂNG NHẬP</a>
		        <a href="/TranscriptVideo/signup.jsp" class="btn-signup">ĐĂNG KÍ</a>
		    </div>
		<% } %>
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
			    <label class="btn-upload" for="file-input" onclick="checkLogin()">Chọn Video</label>
			    <input type="file" id="file-input" class="file-input" accept="video/*" multiple name="video">
			</div>
			<div class="button-container">
			    <input type="submit" class="btn-select" value="Xác Nhận">
			</div>
        </form>
    </div>
    <% if (isLoggedIn && !videoHistory.isEmpty()) { %>
    <div class="history-container">
        <h3>Lịch sử Upload Video</h3>
        <table class="history-table">
            <thead>
                <tr>
                    <th>STT</th>
                    <th>Tên File</th>
                    <th>Trạng Thái</th>
                    <th>Ngày Upload</th>
                    <th>Kết Quả</th>
                </tr>
            </thead>
            <tbody id="videoHistoryTable">
                <% int count = 1; %>
                <% for (Video video : videoHistory) { %>
                <tr id="status-<%= video.getVideoId() %>">
                    <td><%= count++ %></td>
                    <td><%= java.nio.file.Paths.get(video.getOriginalUrl()).getFileName().toString() %></td>
                    <td id="status-<%= video.getVideoId() %>">
                        <% if ("PROCESSING".equals(video.getStatus())) { %>
                      <!--  <div class="status-icon processing-icon"></div> -->
                      ĐANG XỬ LÝ
                        <% } else if ("FAILED".equals(video.getStatus())) { %>
                       <!--  <div class="status-icon failed-icon">X</div> -->
                       LỖI
                        <% } else { %>
                        HOÀN THÀNH
                        <% } %>
                    </td>
                    <td><%= video.getTimeUpload() %></td>
                    <td id="result-<%= video.getVideoId() %>">
                       <% if ("COMPLETED".equals(video.getStatus())) { %>
						    <a href="/TranscriptVideo/dowload?fileName=<%= video.getResultUrl() %>" target="_blank">Tải xuống</a> |
						    <a href="/TranscriptVideo/watch?fileName=<%= video.getResultUrl() %>" target="_blank">Xem</a>
						    <% } else { %>
						    N/A
					    <% } %>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
    <% } %>
	
    <footer>
        <p>© ILoveUhiuhiu & hung55555 2024, All rights reserved.</p>
    </footer>
    
    <script>
        function checkLogin() {
            const isLoggedIn = <%= isLoggedIn %>;
            if (!isLoggedIn) {
            	event.preventDefault();
                alert("Vui lòng đăng nhập trước khi chọn video.");
            } 
        }
    </script>
    <!-- <script>
        function updateStatus() {
            // Gọi API để lấy danh sách video cập nhật trạng thái
            fetch('/TranscriptVideo/api/videos')
                .then(response => response.json())
                .then(data => {
                    data.forEach(video => {
                        const statusCell = document.getElementById(`status-${video.id}`);
                        const resultCell = document.getElementById(`result-${video.id}`);
                        
                        // Xử lý cập nhật trạng thái
                        if (video.status === "PROCESSING") {
                            statusCell.innerHTML = '<div class="status-icon processing-icon"></div>';
                            resultCell.innerHTML = 'N/A';
                        } else if (video.status === "COMPLETED") {
                            statusCell.innerHTML = 'Hoàn thành';
                            resultCell.innerHTML = `<a href="${video.resultUrl}" target="_blank">Tải xuống</a>`;
                        } else if (video.status === "FAILED") {
                            statusCell.innerHTML = '<div class="status-icon failed-icon">X</div>';
                            resultCell.innerHTML = 'N/A';
                        }
                    });
                });
        }
        // Tự động gọi cập nhật trạng thái mỗi 5 giây
        setInterval(updateStatus, 1000);
    </script> -->
</body>

</html>