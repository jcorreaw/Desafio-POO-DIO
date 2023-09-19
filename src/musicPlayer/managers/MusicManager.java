package musicPlayer.managers;

import musicPlayer.enums.EnumMusicExampleData;
import musicPlayer.objects.Music;

import java.util.*;

public class MusicManager {
    private List<Music> musics;
    private int musicId;
    private List<Music> playlist;

    public MusicManager() {
        this.musics = EnumMusicExampleData.getMusics();
        this.musicId = -1;
        this.playlist = new ArrayList<>();
    }

    public Music getPlayingMusic(){
        if(musicId != -1){
            return musics.stream().filter(m -> m.getId() == musicId).toList().get(0);
        }else {
            playlist = musics;
            musicId = musics.get(0).getId();
            return musics.get(0);
        }
    }

    public Music getNextMusic(){
        Music tempMusic = playlist.stream().filter(m -> m.getId() == musicId).toList().get(0);
        int musicIndex = playlist.indexOf(tempMusic);
        Music newMusic;

        System.out.println("Trocando para a próxima música...");

        musicIndex+=1;
        if(playlist.size() == musicIndex){
            newMusic = playlist.get(0);
            setMusicId(newMusic.getId());
        }else{
            newMusic = playlist.get(musicIndex);
            setMusicId(newMusic.getId());
        }
        return newMusic;
    }

    public Music getPreviousMusic(){
        Music tempMusic = playlist.stream().filter(m -> m.getId() == musicId).toList().get(0);
        int musicIndex = playlist.indexOf(tempMusic);
        Music newMusic;
        musicIndex-=1;

        System.out.println("Trocando para música anterior...");

        if(musicIndex == -1){
            newMusic = playlist.get(playlist.size()-1);
            setMusicId(newMusic.getId());
        }else {
            newMusic = playlist.get(musicIndex);
            setMusicId(newMusic.getId());
        }
        return newMusic;
    }

    public List<Music> getAllMusics(){
        List<Music> ordenedMusics = musics;
        Collections.sort(ordenedMusics, new Comparator<Music>() {
            @Override
            public int compare(Music m1, Music m2) {
                return m1.getName().compareTo(m2.getName());
            }
        });
        System.out.println("Retornando todas as músicas no dispositivo...");
        setPlaylist(ordenedMusics);
        setMusicId(playlist.get(0).getId());
        return ordenedMusics;
    }

    public void setMusics(List<Music> musics) {
        this.musics = musics;
    }

    public List<Music> getMusicByArtist(String artist){
        setPlaylist(musics.stream().filter(m -> m.getArtist().equals(artist)).toList());
        setMusicId(playlist.get(0).getId());
        System.out.println("Retornando as músicas do artista ".concat(artist));
        return playlist;
    }

    public Map<String,List<Music>> getAllAlbuns(){
        List<Music> ordenedMusicByAlbuns = orderMusicsByAlbuns();

        Map<String,List<Music>> albuns = new HashMap<>();
        ordenedMusicByAlbuns.forEach(m ->{
            List<Music> tempMusics = new ArrayList<Music>();
            tempMusics.add(m);
            albuns.put(m.getAlbumName(),tempMusics);
        });
        System.out.println("Retornando todos os albúns...");

        Set<String> albumNames = albuns.keySet();
        albumNames.forEach(albumName -> {
            System.out.println("Album: ".concat(albumName));
            List<Music> albumMusics = albuns.get(albumName);
            albumMusics.forEach(m ->{
                System.out.println(m.toString());
            });
        });
        return albuns;
    }

    public List<Music> getMusicsByAlbumName(String album){
        setPlaylist(musics.stream().filter(m -> m.getAlbumName().equals(album)).toList());
        setMusicId(playlist.get(0).getId());

        System.out.println("Retornando as músicas do albúm ".concat(album));

        return playlist;
    }

    public List<Music> getMusicsByRate(int rate){
        setPlaylist(musics.stream().filter(m -> m.getRate() == rate).toList());
        setMusicId(playlist.get(0).getId());
        System.out.println("Retornando apenas músicas do nota ".concat(String.valueOf(rate)));
        return playlist;
    }

    public List<Music> orderMusicsByRate(){
        List<Music> ordenedMusics = musics;
        Collections.sort(ordenedMusics, new Comparator<Music>() {
            @Override
            public int compare(Music m1, Music m2) {
                return Integer.compare(m1.getRate(), m2.getRate());
            }
        });
        setPlaylist(ordenedMusics);
        setMusicId(playlist.get(0).getId());
        System.out.println("Ordenando as músicas com base na nota ");
        return ordenedMusics;
    }

    public List<Music> orderMusicsByAlbuns(){
        List<Music> ordenedMusics = musics;
        Collections.sort(ordenedMusics, new Comparator<Music>() {
            @Override
            public int compare(Music m1, Music m2) {
                return m1.getAlbumName().compareTo(m2.getAlbumName());
            }
        });
        setPlaylist(ordenedMusics);
        setMusicId(playlist.get(0).getId());
        System.out.println("Ordenando as músicas usando os albúns, em orde alfabética.");
        return ordenedMusics;
    }

    public int getMusicId() {
        return musicId;
    }

    public void setMusicId(int musicId) {
        this.musicId = musicId;
    }

    public List<Music> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(List<Music> tempMusics) {
        this.playlist = tempMusics;
    }
}
