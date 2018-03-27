package ua.com.zzz.davimuka.musicplayer;

public class Song {
    private int smallImageID;
    private int fullImageID;
    private String title;
    private String artist;
    private int duration;
    private boolean isLiked;
    private Song previousSong;
    private Song nextSong;
    private Song previousLikedSong;
    private Song nextLikedSong;

    public Song(int smallImageID, int fullImageID, String title, String artist, int duration) {
        this.fullImageID = fullImageID;
        this.smallImageID = smallImageID;
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        isLiked = false;
    }

    public int getFullImageID() {
        return fullImageID;
    }

    public void setFullImageID(int fullImageID) {
        this.fullImageID = fullImageID;
    }

    public int getSmallImageID() {
        return smallImageID;
    }

    public void setSmallImageID(int smallImageID) {
        this.smallImageID = smallImageID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public Song getPreviousSong() {
        return previousSong;
    }

    public void setPreviousSong(Song previousSong) {
        this.previousSong = previousSong;
    }

    public Song getNextSong() {
        return nextSong;
    }

    public void setNextSong(Song nextSong) {
        this.nextSong = nextSong;
    }

    public Song getPreviousLikedSong() {
        return previousLikedSong;
    }

    public void setPreviousLikedSong(Song previousLikedSong) {
        this.previousLikedSong = previousLikedSong;
    }

    public Song getNextLikedSong() {
        return nextLikedSong;
    }

    public void setNextLikedSong(Song nextLikedSong) {
        this.nextLikedSong = nextLikedSong;
    }
}
