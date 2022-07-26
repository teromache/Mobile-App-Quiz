package android.demo.entities;

import java.io.Serializable;

public class Mark implements Serializable {

    private int id;
    private int userid;
    private String username;
    private String subject;
    private int cMark;
    private int wMark;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getcMark() {
        return cMark;
    }

    public void setcMark(int cMark) {
        this.cMark = cMark;
    }

    public int getwMark() {
        return wMark;
    }

    public void setwMark(int wMark) {
        this.wMark = wMark;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
