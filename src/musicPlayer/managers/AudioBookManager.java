package musicPlayer.managers;

import musicPlayer.enums.EnumAudioBookExampleData;
import musicPlayer.objects.AudioBook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AudioBookManager {
    private List<AudioBook> audioBooks;
    private int audioBookId;
    private List<AudioBook> playlist;

    public AudioBookManager() {
        this.audioBooks = EnumAudioBookExampleData.getAudioBooks();
        this.audioBookId = -1;
        this.playlist = new ArrayList<>();
    }

    public AudioBook getPlayingAudioBook(){
        if(audioBookId != -1){
            return audioBooks.stream().filter(m -> m.getId() == audioBookId).toList().get(0);
        }else {
            playlist = audioBooks;
            audioBookId = audioBooks.get(0).getId();
            return audioBooks.get(0);
        }
    }

    public AudioBook getNextAudioBook(){
        AudioBook tempAudioBook = playlist.stream().filter(m -> m.getId() == audioBookId).toList().get(0);
        int audioBookIndex = playlist.indexOf(tempAudioBook);
        AudioBook newAudioBook;

        System.out.println("Trocando para a próximo audiobook...");

        audioBookIndex+=1;
        if(playlist.size() == audioBookIndex){
            newAudioBook = playlist.get(0);
            setAudioBookId(newAudioBook.getId());
        }else{
            newAudioBook = playlist.get(audioBookIndex);
            setAudioBookId(newAudioBook.getId());
        }
        return newAudioBook;
    }

    public AudioBook getPreviousAudioBook(){
        AudioBook tempAudioBook = playlist.stream().filter(m -> m.getId() == audioBookId).toList().get(0);
        int musicIndex = playlist.indexOf(tempAudioBook);
        AudioBook newAudioBook;
        musicIndex-=1;

        System.out.println("Trocando para o audiobook anterior...");

        if(musicIndex == -1){
            newAudioBook = playlist.get(playlist.size()-1);
            setAudioBookId(newAudioBook.getId());
        }else {
            newAudioBook = playlist.get(musicIndex);
            setAudioBookId(newAudioBook.getId());
        }
        return newAudioBook;
    }

    public List<AudioBook> getAllAudioBooks(){
        List<AudioBook> ordenedMusics = audioBooks;
        Collections.sort(ordenedMusics, new Comparator<AudioBook>() {
            @Override
            public int compare(AudioBook m1, AudioBook m2) {
                return m1.getName().compareTo(m2.getName());
            }
        });
        System.out.println("Retornando todas as audiobooks no dispositivo...");
        setPlaylist(ordenedMusics);
        setAudioBookId(playlist.get(0).getId());
        return ordenedMusics;
    }

    public void setAudioBooks(List<AudioBook> audioBooks) {
        this.audioBooks = audioBooks;
    }

    public List<AudioBook> getAudioBooksByWriter(String writer){
        setPlaylist(audioBooks.stream().filter(m -> m.getWriter().equals(writer)).toList());
        setAudioBookId(playlist.get(0).getId());
        System.out.println("Retornando os audiobooks do autor ".concat(writer));
        return playlist;
    }

    public List<AudioBook> getAudioBooksByRate(int rate){
        setPlaylist(audioBooks.stream().filter(m -> m.getRate() == rate).toList());
        setAudioBookId(playlist.get(0).getId());
        System.out.println("Retornando apenas audiobooks do avaliação ".concat(String.valueOf(rate)));
        return playlist;
    }

    public List<AudioBook> orderAudioBooksByRate(){
        List<AudioBook> ordenedMusics = audioBooks;
        Collections.sort(ordenedMusics, new Comparator<AudioBook>() {
            @Override
            public int compare(AudioBook m1, AudioBook m2) {
                return Integer.compare(m1.getRate(), m2.getRate());
            }
        });
        setPlaylist(ordenedMusics);
        setAudioBookId(playlist.get(0).getId());
        System.out.println("Ordenando as audiobooks com base na avaliação ");
        return ordenedMusics;
    }

    public int getAudioBookId() {
        return audioBookId;
    }

    public void setAudioBookId(int musicId) {
        this.audioBookId = musicId;
    }

    public List<AudioBook> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(List<AudioBook> tempAudioBooks) {
        this.playlist = tempAudioBooks;
    }

}
