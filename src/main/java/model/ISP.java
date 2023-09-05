package model;

public class ISP {
    private int id;
    private Song song;
    private Playlist playlist;

    public ISP(int id, Song song, Playlist playlist) {
        this.id = id;
        this.song = song;
        this.playlist = playlist;
    }

    public ISP() {
    }

    public ISP(Song song, Playlist playlist) {
        this.song = song;
        this.playlist = playlist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Song getSong() {
        return song;
    }

    public void setSong(Song song) {
        this.song = song;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }
}