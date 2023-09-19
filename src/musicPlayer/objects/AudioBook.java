package musicPlayer.objects;

import musicPlayer.interfaces.Playable;

import java.io.File;

public class AudioBook implements Playable {
    private int id;
    private String name;
    private File audioBookArt;
    private String writer;
    private int rate;

    public AudioBook(int id, String name, File audioBookArt, String writer, int rate) {
        this.id = id;
        this.name = name;
        this.audioBookArt = audioBookArt;
        this.writer = writer;
        this.rate = rate;
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

    public File getAudioBookArt() {
        return audioBookArt;
    }

    public void setAudioBookArt(File audioBookArt) {
        this.audioBookArt = audioBookArt;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public void play() {
        System.out.println("Tocando o album ".concat(name).concat("!"));
    }

    @Override
    public void stop() {
        System.out.println("Album ".concat(name).concat(" pausado!"));
    }
}
