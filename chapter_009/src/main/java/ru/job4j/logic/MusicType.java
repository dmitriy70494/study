package ru.job4j.logic;

public class MusicType {

    private String id;

    private String musicType;

    public MusicType(String id, String musicType) {
        this.id = id;
        this.musicType = musicType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMusicType() {
        return musicType;
    }

    public void setMusicType(String musicType) {
        this.musicType = musicType;
    }
}
