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
            font-family: sans-serif;
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

        
        .progress-container {
		  z-index: -99;
		  position: relative;
		  margin: 50px auto 50px;
		  padding: 4px 0 0;
		  width: 600px;
		  height: 10px;
		  background: #444;
		  -moz-box-shadow: inset 0 0 2px #666;
		      -webkit-box-shadow: inset 0 0 2px #666;
		       box-shadow: inset 0 0 2px #666;
		  
		  ul li {
		    position: absolute;
		    top: -1px;
		    left: 0;
		    margin: 0;
		    padding: 0;
		    display: inline-table;
		    height: 10px;
		    width: 10px;
		    background: #81c408;
		    border-radius: 50%;
		    
		    &:nth-child(2) {
		      margin-left: 190px;
		    }
		    
		    &:nth-child(3) {
		      margin-left: 400px;
		    }
		    
		    &:last-child {
		      margin-left: 600px;
		    }
		    
		    &:before {
		      z-index: -99;
		      position: absolute;
		      top: -5px;
		      left: -5px;
		      content:'';
		      height: 20px;
		      width: 20px;
		      background: #444;
		      border-radius: 50%;
		    }
		  }
		  
		  .progress-bar {
		   height: 2px;
		   width: 0%;
		   background: #81c408;
		   -webkit-transition: width 1s ease-in-out;
		      -moz-transition: width 1s ease-in-out;
		        -o-transition: width 1s ease-in-out;
		           transition: width 1s ease-in-out;
		  }
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

<body>
    <header>
        <nav>
        	<div class="logo">AutoCapCap</div>
            <a href="#merge">Trang chủ</a>
            <a href="#compress">Kết quả</a>
        </nav>
    </header>

	
    <div class="content">
    	
        <div class="progress-container">
		  <ul>
		    <li></li>
		    <li></li>
		    <li></li>
		    <li></li>	    
		  </ul>
		  <div class="progress-bar"></div>
		</div>
		
		<p class="message">Đang tạo audio...</p>
    </div>
	
		
    <footer>
        <p>© ILoveUhiuhiu & hung55555 2024, All rights reserved.</p>
    </footer>
    <script>
    let previousMessage = null; 
    console.log('<%= request.getAttribute("videoId") %>');
	let intervalId;
    function fetchMessage() {
    	console.log("dc roi 1");
        var videoId = '<%= request.getAttribute("videoId") %>'; // Bao quanh bằng dấu nháy đơn

        var xhr = new XMLHttpRequest();
        xhr.open('GET', 'http://localhost:8080/TranscriptVideo/GetMes?videoId=' + encodeURIComponent(videoId), true);
        
        xhr.onload = function() {
            if (xhr.status >= 200 && xhr.status < 300) {
                const response = JSON.parse(xhr.responseText);
                const newMessage = response.message;
				console.log(newMessage + ", " + previousMessage);
                document.querySelector('.message').textContent = newMessage;

                if (previousMessage !== null && newMessage !== previousMessage) {
                    const progressBar = document.querySelector('.progress-bar');
                    if (!progressBar) {
                        console.error('Progress bar element not found');
                        return;
                    }

                    let currentWidth = parseInt(progressBar.style.width) || 0; // Mặc định là 0 nếu chưa có chiều rộng
                    progressBar.style.width = (currentWidth + 200) + 'px'; // Cập nhật chiều rộng

                    if (currentWidth + 200 >= 600) {
                        clearInterval(intervalId);
                        console.log("Progress bar reached 800px, stopping fetch.");
                    }
                }
                previousMessage = newMessage; 
            } else {
                console.error('Error fetching message');
            }
        };

        xhr.onerror = function() {
            console.error('Request failed');
        };

        xhr.send();
    }

    function startFetchingMessages() {
    	console.log("dc roi 2");
        fetchMessage();
        intervalId = setInterval(fetchMessage, 100); 
        
    }

    startFetchingMessages();
</script>
</body>

</html>