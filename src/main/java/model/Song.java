package model;

public class Song {
    private int id;
    private String name;
    private Author author;
    private Category category;
    private Singer singer;
    private String song;
    private String image;
    public Song(){}

    public Song(int id, String name,String song, String image,  Author authors, Category category, Singer singer) {
        this.id = id;
        this.name = name;
        this.song = song;
        this.image = image;
        this.author =authors;
        this.category = category;
        this.singer = singer;
    }

    public Song(String name, Author author, Category category, Singer singer, String song, String image) {
        this.name = name;
        this.author = author;
        this.category = category;
        this.singer = singer;
        this.song = song;
        this.image = image;
    }

    public Song(int id, String name, Author author, Category category, Singer singer, String song, String image) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.category = category;
        this.singer = singer;
        this.song = song;
        this.image = image;
    }

    public Song(String name, String song) {
        this.name = name;
        this.song = song;
    }

    public Song(int id, String name, String song) {
        this.id = id;
        this.name = name;
        this.song = song;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
