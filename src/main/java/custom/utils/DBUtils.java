package custom.utils;

import custom.models.Note;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {
    public static Connection initConnect() {
        try {
            return ConnectionUtils.getMariaDBConnection();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
    // user method(cho login, register) ở đây


    public static List<Note> getNote(String author_input, String title_input) throws SQLException {
        Connection conn = initConnect();
        String sql = "select * from dev.notes where author=?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, author_input);
        // pstm.setString(2, "%" + title_input + "%");
        ResultSet results = pstm.executeQuery();
        conn.close();

        List<Note> notes = new ArrayList<Note>();
        while (results.next()) {
            int id = results.getInt(1);
            String title = results.getString(2);
            String author = results.getString(3);
            String content = results.getString(4);
            String type = results.getString(5);
            String date = results.getString(6);

            Note note = new Note(id, title, author, content, type, date);
            notes.add(note);
        }
        return notes;
    }

    public static int addNote(Note note) throws SQLException {
        Connection conn = initConnect();
        String sql = "insert into dev.notes(title, author, content, type, date) values(?, ?, ?, ?, ?)";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, note.getTitle());
        pstm.setString(2, note.getAuthor());
        pstm.setString(3, note.getContent());
        pstm.setString(4, note.getType());
        pstm.setString(5, note.getDate());
        int status = pstm.executeUpdate();
        conn.close();
        return status;
    }

    public static int updateNote(Note note, int id_input) throws SQLException {
        Connection conn = initConnect();
        String sql = "update dev.notes set title=?, note=?, date=? where id=?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, note.getTitle());
        pstm.setString(2, note.getContent());
        pstm.setString(3, note.getDate());
        pstm.setInt(4, id_input);
        int status = pstm.executeUpdate();
        conn.close();
        return status;
    }

    public static int deleteNote(int id_input, String author_input) throws  SQLException {
        Connection conn = initConnect();
        String sql = "delete from notes where id=? and author=?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setInt(1, id_input);
        pstm.setString(2, author_input);
        int status = pstm.executeUpdate();
        conn.close();
        return status;
    }

    public static int typeNote(int id_input, String type_input) throws SQLException {
        Connection conn = initConnect();
        String sql = "update dev.notes set type=? where id=?";

        PreparedStatement pstm = conn.prepareStatement(sql);
        pstm.setString(1, type_input);
        pstm.setInt(2, id_input);
        int status = pstm.executeUpdate();
        conn.close();
        return status;
    }
}
