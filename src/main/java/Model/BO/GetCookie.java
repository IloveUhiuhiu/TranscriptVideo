package Model.BO;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Model.Bean.User;

import java.util.logging.Logger;


public class GetCookie {
    
    private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";
    private static final int COOKIE_MAX_AGE = 24 * 60 * 60; // 1 ngày (giây)
    private static final Logger LOGGER = Logger.getLogger(GetCookie.class.getName());

    // Lưu thông tin người dùng vào Cookie.
    public static void storeUserCookie(HttpServletResponse response, User user) {
        try {
            LOGGER.info("Storing user cookie");
            Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, user.getEmail());
            cookieUserName.setMaxAge(COOKIE_MAX_AGE);
            cookieUserName.setHttpOnly(true); // Chỉ cho phép truy cập cookie qua HTTP(S)
            cookieUserName.setSecure(true);  // Chỉ hoạt động trên HTTPS
            cookieUserName.setPath("/");    // Cookie khả dụng trên toàn bộ ứng dụng
            response.addCookie(cookieUserName);
        } catch (Exception e) {
            LOGGER.severe("Error storing user cookie: " + e.getMessage());
        }
    }

    // Lấy thông tin người dùng từ Cookie.
    public static String getUserNameInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ATT_NAME_USER_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    // Xóa Cookie của người dùng.
    public static void deleteUserCookie(HttpServletResponse response) {
        try {
            LOGGER.info("Deleting user cookie");
            Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, null);
            cookieUserName.setMaxAge(0);    // Hết hiệu lực ngay lập tức
            cookieUserName.setPath("/");   // Đảm bảo xóa trên toàn bộ ứng dụng
            response.addCookie(cookieUserName);
        } catch (Exception e) {
            LOGGER.severe("Error deleting user cookie: " + e.getMessage());
        }
    }
}
