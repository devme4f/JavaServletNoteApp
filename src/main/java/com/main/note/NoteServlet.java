package com.main.note;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import custom.models.Note;
import custom.utils.DBUtils;

public class NoteServlet extends HttpServlet {
    private String urlEncode(String value) {
        return URLEncoder.encode(value);
    }

    private String urlDecode(String value) {
        return URLDecoder.decode(value);
    }
    public static boolean emtyStr(String s) {
        return s == null || s.trim().length() == 0;
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String author_debug = "admin";
        String error = null;
        List<Note> notes = null; // .get(`index`) để lấy phần tử
        try {
            notes = DBUtils.getNote(author_debug, "");
        } catch (Exception e) {
            e.printStackTrace();
            error = e.getMessage();
        }
        request.setAttribute("error", error);
        request.setAttribute("notes", notes);
        request.setAttribute("_e", request.getParameter("_e"));
        request.setAttribute("_m", request.getParameter("_m"));
        request.setAttribute("_s", request.getParameter("_s"));
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String author = "admin"; // debug - implement login/session
        String content = request.getParameter("content");
        String[] type_ = {"note-business", "note-important", "note-business"}; // ko biết front-end nên phải set!
        Random rand = new Random();
        String type = type_[rand.nextInt(3)];
        System.out.println(rand.nextInt(3));
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        String formattedDate = localDate.format(formatter);

        int status = 0;
        String error = "";
        String msg = "";
        if (emtyStr(title) || emtyStr(content)) {
            msg = "Title and Content cannot be empty!";
            String redirectURI = "/" + "?_e=" + urlEncode(error) + "&_m=" + urlEncode(msg) + "&_s=" + status;
            response.sendRedirect(redirectURI);
            return;
        }
        Note note = new Note(1, title, author, content, type, formattedDate); // id làm cảnh

        try {
            status = DBUtils.addNote(note);
            if (status == 0) {
                System.out.println("Failed to add note!!");
                msg = "Error when adding new note. Please try again!!";
            } else {
                System.out.println("Added 1 note!");
                msg = "Successful adding new note!!";
            }
        } catch (Exception e) {
            e.printStackTrace();
            error = e.getMessage();
        }

        String redirectURI = "/" + "?_e=" + urlEncode(error) + "&_m=" + urlEncode(msg) + "&_s=" + status;
        response.sendRedirect(redirectURI);
    }
}
