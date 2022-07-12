package com.main.note;

import custom.utils.DBUtils;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class TypeNoteServlet extends HttpServlet {
    public static boolean emtyStr(String s) {
        return s == null || s.trim().length() == 0;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        String id_ = request.getParameter("id");
        String author = "admin"; // debug
        int code = 200;
        String msg = null;

        if (emtyStr(type) || emtyStr(id_)) {
            code = 400;
            msg = "{\"code\":400,\"msg\":\"Missing required parameter!\"}";
        } else {
            try {
                int id = Integer.parseInt(id_);
                int status = DBUtils.typeNote(id, type);
                if (status != 0) {
                    msg = "{\"code\":200,\"msg\":\"Successful delete note!\"}";
                } else {
                    code = 400;
                    msg = "{\"code\":400,\"msg\":\"Note doesn't exist or you're not authorized to delete this note!\"}";
                }
            } catch (Exception e) {
                e.printStackTrace();
                code = 500;
                msg = "{\"code\":500,\"msg\":\"Failed to delete note!\"}";
            }
            response.setHeader("Content-Type", "application/json;charset=utf-8");
            response.setStatus(code);
            response.getWriter().print(msg);
            response.getWriter().flush();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
