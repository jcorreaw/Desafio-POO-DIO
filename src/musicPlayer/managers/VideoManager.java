package musicPlayer.managers;

import musicPlayer.enums.EnumVideoExampleData;
import musicPlayer.objects.Video;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class VideoManager {
    private List<Video> videos;
    private int videoId;
    private List<Video> playlist;

    public VideoManager() {
        this.videos = EnumVideoExampleData.getVideos();
        this.videoId = -1;
        this.playlist = new ArrayList<>();
    }

    public Video getPlayingVideo(){
        if(videoId != -1){
            return videos.stream().filter(m -> m.getId() == videoId).toList().get(0);
        }else {
            playlist = videos;
            videoId = videos.get(0).getId();
            return videos.get(0);
        }
    }

    public Video getNextVideo(){
        Video tempVideo = playlist.stream().filter(m -> m.getId() == videoId).toList().get(0);
        int videoIndex = playlist.indexOf(tempVideo);
        Video newVideo;

        System.out.println("Trocando para o próximo vídeo...");

        videoIndex+=1;
        if(playlist.size() == videoIndex){
            newVideo = playlist.get(0);
            setVideoId(newVideo.getId());
        }else{
            newVideo = playlist.get(videoIndex);
            setVideoId(newVideo.getId());
        }

        return newVideo;
    }

    public Video getPreviousVideo(){
        Video tempVideo = playlist.stream().filter(m -> m.getId() == videoId).toList().get(0);
        int videoIndex = playlist.indexOf(tempVideo);
        Video newVideo;
        videoIndex-=1;

        System.out.println("Trocando para vídeo anterior...");

        if(videoIndex == -1){
            newVideo = playlist.get(playlist.size()-1);
            setVideoId(newVideo.getId());
        }else {
            newVideo = playlist.get(videoIndex);
            setVideoId(newVideo.getId());
        }
        return newVideo;
    }

    public List<Video> getAllVideos(){
        List<Video> ordenedMusics = videos;
        Collections.sort(ordenedMusics, new Comparator<Video>() {
            @Override
            public int compare(Video m1, Video m2) {
                return m1.getName().compareTo(m2.getName());
            }
        });
        System.out.println("Retornando todos os vídeos no dispositivo...");
        setPlaylist(ordenedMusics);
        setVideoId(playlist.get(0).getId());
        return ordenedMusics;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    public List<Video> getVideosByRate(int rate){
        setPlaylist(videos.stream().filter(m -> m.getRate() == rate).toList());
        setVideoId(playlist.get(0).getId());
        System.out.println("Retornando apenas vídeos de nota ".concat(String.valueOf(rate)));
        return playlist;
    }

    public List<Video> orderVideosByRate(){
        List<Video> ordenedVideos = videos;
        Collections.sort(ordenedVideos, new Comparator<Video>() {
            @Override
            public int compare(Video m1, Video m2) {
                return Integer.compare(m2.getRate(), m1.getRate());
            }
        });
        setPlaylist(ordenedVideos);
        setVideoId(playlist.get(0).getId());
        System.out.println("Ordenando os vídeos com base na nota ");
        return ordenedVideos;
    }

    public int getVideoId() {
        return videoId;
    }

    public void setVideoId(int videoId) {
        this.videoId = videoId;
    }

    public List<Video> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(List<Video> tempVideos) {
        this.playlist = tempVideos;
    }
}
