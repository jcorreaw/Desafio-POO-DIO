package browser;

import browser.additional.Favorite;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class WebBrowser {

    private List<Favorite> favorites;
    private List<String> openSites;
    private List<String> historic;

    public WebBrowser() {

        favorites.add(new Favorite("https://www.google.com", "Google"));
        favorites.add(new Favorite("https://github.com/", "Github"));

        openSites = new ArrayList<>();
        historic = new ArrayList<>();
    }

    public void openNewSite(String url) {
        System.out.println(url.concat(" sendo carregado!"));

        for (int i = 0; i < 3; i++) {
            try {
                TimeUnit.SECONDS.sleep((long) (Math.random()*4));
                System.out.println("Carregado ".concat(String.valueOf(i*25)).concat("%"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Carregado 100%!");
        openSites.add(url);
        historic.add(url);
    }

    public void openNewSite(Favorite favorite){
        String url = favorite.getUrl();
        System.out.println(url.concat(" está sendo carregado!"));

        for(int i=0;i<3;i++){
            try {
                TimeUnit.SECONDS.sleep((long) (Math.random()*4));
                System.out.println("Carregado ".concat(String.valueOf(i*25)).concat("%"));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println("Carregado 100%!");
        openSites.add(url);
        historic.add(url);
    }

    public void closeSiteByIndex(int index){
        String url = openSites.get(index);
        System.out.println("Fechando ".concat(url));
        openSites.remove(index);
        System.out.println("Fechado.");
    }

    public void addBookmar(String url, String name){
        favorites.add(new Favorite(url,name));
        System.out.println(name.concat(" adicionado ao favoritos."));
    }

    public void removeBookmarkByName(String name){
        favorites.removeIf(b->(b.getName().equals(name)));
        System.out.println(name.concat(" removido."));
    }

    public List<Favorite> getFavorites() {
        System.out.println("Favoritos:");
        for(int i=0; i<favorites.size(); i++){
            System.out.println("Nome:"+favorites.get(i).getName()+" Url:"+favorites.get(i).getUrl());
        }
        return favorites;
    }

    public void setBookmarks(List<Favorite> bookmarks) {
        this.favorites = bookmarks;
    }

    public List<String> getOpenSites() {
        System.out.println("Sites abertos:");
        for(int i=0;i<openSites.size();i++){
            System.out.println("Index:"+i+" Url:"+openSites.get(i));
        }
        return openSites;
    }

    public void setOpenSites(List<String> openSites) {
        this.openSites = openSites;
    }

    public List<String> getHistory() {
        return historic;
    }

    public void setHistory(List<String> history) {
        this.historic = history;
    }
}
