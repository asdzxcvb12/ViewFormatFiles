package ts.restfultest;

/**
 * Created by user on 2017-03-04.
 */
import java.util.ArrayList;

public class MelonDataModel {
    private Melon melon;

    public Melon getMelon() {
        return melon;
    }

    public void setMelon(Melon melon) {
        this.melon = melon;
    }

}
class Melon {
    private String menuId;
    private String count;
    private String totalPages;
    private String rankDay;
    private String rankHour;
    private Songs songs;


    public String getMenuId() {
        return menuId;
    }
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
    public String getCount() {
        return count;
    }
    public void setCount(String count) {
        this.count = count;
    }
    public String getTotalPages() {
        return totalPages;
    }
    public void setTotalPages(String totalPages) {
        this.totalPages = totalPages;
    }
    public String getRankDay() {
        return rankDay;
    }
    public void setRankDay(String rankDay) {
        this.rankDay = rankDay;
    }
    public String getRankHour() {
        return rankHour;
    }
    public void setRankHour(String rankHour) {
        this.rankHour = rankHour;
    }
    public Songs getSongs() {
        return songs;
    }
    public void setSongs(Songs songs) {
        this.songs = songs;
    }

}
class Songs {
    private ArrayList<Song> song;

    public ArrayList<Song> getSong() {
        return song;
    }

    public void setSong(ArrayList<Song> song) {
        this.song = song;
    }
}
class Song{
    private String songId;
    private String songName;
    private Artists artists;
    public String getSongId() {
        return songId;
    }
    public void setSongId(String songId) {
        this.songId = songId;
    }
    public String getSongName() {
        return songName;
    }
    public void setSongName(String songName) {
        this.songName = songName;
    }
    public Artists getArtists() {
        return artists;
    }
    public void setArtists(Artists artists) {
        this.artists = artists;
    }

}


class Artists{
    private ArrayList<Artist> artist;

    public ArrayList<Artist> getArtist() {
        return artist;
    }

    public void setArtist(ArrayList<Artist> artist) {
        this.artist = artist;
    }
}
class Artist{
    private String artistId;
    private String artistName;
    public String getArtistId() {
        return artistId;
    }
    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }
    public String getArtistName() {
        return artistName;
    }
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
}