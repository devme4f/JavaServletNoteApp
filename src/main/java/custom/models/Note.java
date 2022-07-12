package custom.models;

/*
* Model class represents a note entity
*
*/

import java.util.Date;

public class Note {
    protected int id;
    protected String title;
    protected String author;
    protected String content;
    protected String type;
    protected String date;

    public Note(int id, String title, String author, String content, String type, String date) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.type = type;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }
}
