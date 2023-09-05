package model;

public class History {
    private int id;
    private User user;
    private Song song;

    private int view;

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public History(int id, User user, Song song) {
        this.id = id;
        this.user = user;
        this.song = song;
    }

    public History() {
    }

    public History(User user, Song song) {
        this.user = user;
        this.song = song;
    }

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
}
