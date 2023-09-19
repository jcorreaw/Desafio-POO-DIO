package musicPlayer.managers;

import musicPlayer.enums.EnumPodcastExampleData;
import musicPlayer.objects.Podcast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PodcastManager {
    private List<Podcast> podcasts;
    private int podcastId;
    private List<Podcast> playlist;

    public PodcastManager() {
        this.podcasts = EnumPodcastExampleData.getPodcasts();
        this.podcastId = -1;
        this.playlist = new ArrayList<>();
    }

    public Podcast getPlayingPodcast(){
        if(podcastId != -1){
            return podcasts.stream().filter(m -> m.getId() == podcastId).toList().get(0);
        }else {
            playlist = podcasts;
            podcastId = podcasts.get(0).getId();
            return podcasts.get(0);
        }
    }

    public Podcast getNextPodcast(){
        Podcast tempPodcast = playlist.stream().filter(m -> m.getId() == podcastId).toList().get(0);
        int podcastIndex = playlist.indexOf(tempPodcast);
        Podcast newPodcast;

        System.out.println("Trocando para o próximo podcast...");

        podcastIndex+=1;
        if(playlist.size() == podcastIndex){
            newPodcast = playlist.get(0);
            setPodcastId(newPodcast.getId());
        }else{
            newPodcast = playlist.get(podcastIndex);
            setPodcastId(newPodcast.getId());
        }
        return newPodcast;
    }

    public Podcast getPreviousPodcast(){
        Podcast tempPodcast = playlist.stream().filter(m -> m.getId() == podcastId).toList().get(0);
        int podcastIndex = playlist.indexOf(tempPodcast);
        Podcast newPodcast;
        podcastIndex-=1;

        System.out.println("Trocando para podcast anterior...");

        if(podcastIndex == -1){
            newPodcast = playlist.get(playlist.size()-1);
            setPodcastId(newPodcast.getId());
        }else {
            newPodcast = playlist.get(podcastIndex);
            setPodcastId(newPodcast.getId());
        }
        return newPodcast;
    }

    public List<Podcast> getAllPodcasts(){
        List<Podcast> ordenedMusics = podcasts;
        Collections.sort(ordenedMusics, new Comparator<Podcast>() {
            @Override
            public int compare(Podcast m1, Podcast m2) {
                return m1.getName().compareTo(m2.getName());
            }
        });
        System.out.println("Retornando todos os podcasts no dispositivo...");
        setPlaylist(ordenedMusics);
        setPodcastId(playlist.get(0).getId());
        return ordenedMusics;
    }

    public void setPodcasts(List<Podcast> podcasts) {
        this.podcasts = podcasts;
    }

    public int getPodcastId() {
        return podcastId;
    }

    public void setPodcastId(int podcastId) {
        this.podcastId = podcastId;
    }

    public List<Podcast> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(List<Podcast> temppodcasts) {
        this.playlist = temppodcasts;
    }
}
