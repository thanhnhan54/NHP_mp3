package model;

public class Like {
    private int id;
    private User user;
    private Song song;
    private int status;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public Like(int id, User user, Song song, int status) {
        this.id = id;
        this.user = user;
        this.song = song;
        this.status = status;
    }

    public Like(User user, Song song, int status) {
        this.user = user;
        this.song = song;
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
