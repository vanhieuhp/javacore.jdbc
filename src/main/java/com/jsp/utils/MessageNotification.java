package com.jsp.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.ResourceBundle;

public class MessageNotification {
    public static void showMessage(HttpServletRequest request) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("message");
        String alert = request.getParameter("alert");
        String message = request.getParameter("message");
        if (message != null && alert != null) {
            request.setAttribute("message",resourceBundle.getString(message));
            request.setAttribute("alert", alert);
        }
    }
}
