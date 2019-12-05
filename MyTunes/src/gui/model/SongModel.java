/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.model;

import be.Playlist;
import be.Songs;
import bll.BLLException;
import bll.PlaylistManager;
import bll.SongManager;
import dal.DalException;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Slider;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Jacob
 */
public class SongModel
{
    private static SongModel instance;
    private ObservableList<Songs> allSongs;
    private ObservableList<Songs> selectedSong;
    private SongManager songManager;
    public ObservableList<Playlist> playlistList;
    public ObservableList<Playlist> selectedPlaylist;
    public ObservableList<Playlist> playlists;
     private boolean listEnded;
     private PlaylistManager plm;

    public SongModel() throws DalException, Exception 
    {
        this.songManager = new SongManager();
        allSongs = FXCollections.observableArrayList();
        selectedSong = FXCollections.observableArrayList();
    }

    public ObservableList<Songs> getAllSongs()
    {
        return allSongs;
    }
    
    public void createSongs(Songs songs) throws DalException, BLLException
    {
        songManager.createSongs(songs);
    }
    
    public static SongModel getInstance() throws IOException, Exception
    {
        if (instance == null)
        {
            instance = new SongModel();
        }
        return instance;
    }
    
    public void loadSongs() throws DalException {
        allSongs.clear();
        allSongs.addAll(songManager.getAllSongs());
    }

    public ObservableList<Songs> getSelectedSong() 
    {
        return selectedSong;
    }
    
    public void addSelectedSong(Songs songs) 
    {
        selectedSong.add(songs);
    }
    
    public void edit(Songs songs) throws DalException 
    {
        songManager.edit(songs);
        allSongs.add(songs);
        allSongs.clear();
        allSongs.addAll(songManager.getAllSongs());           
    }
    
    public void deleteSong(Songs selectedSong) throws DalException 
    {
        songManager.deleteSong(selectedSong);
        allSongs.remove(selectedSong);
    }
   
  /*
   * Sends information of the playlist and adds it to a list
   */
   public void addPlaylist(Playlist playlist)
   {
       plm.add(playlist);
       playlistList.add(playlist);
   }
     /*
   * returns the list
   */
   public ObservableList<Playlist> getSelectedPlaylist() {
       return selectedPlaylist;
   }
 /*
   * adds the selected playlist to a list
   */
   public void addSelectedPlaylist(Playlist playlist) {
       selectedPlaylist.add(playlist);
   }
   
   /*
   * gets all playlists
   */
   public ObservableList<Playlist> getPlaylists() {
       return playlistList;
   }
/*
   * clears the playlist list and adds all playlist from playlist manager
   */
   public void loadPlaylist() {
       playlistList.clear();
       playlistList.addAll(plm.getAllPlaylists());
   }
   
   /*
   * Sends information on the selected playlist and adds it to the list
   * clears the list and adds all playlist for the view to be updated correct
   */
   public void edit(Playlist playlist) {
       plm.edit(playlist);
       playlistList.add(playlist);
       playlistList.clear();
       playlistList.addAll(plm.getAllPlaylists());
   }
   
   /*
   * Sends information of the selected playlist and removes it from the list
   */
   public void remove(Playlist selectedPlaylist) {
       playlistList.remove(selectedPlaylist);
       plm.remove(selectedPlaylist);
   }

   /*
   * Sends information of the selected playlist and song
   */
    public void addSongToPlaylist(Playlist playlist, Songs songs) {
        plm.addSongToPlaylist(playlist, songs);
    }
    
    public void PlaySong(Songs songPlay) {
       songManager.PlaySong(songPlay);
    }
    
    public void PauseSong(Songs songPlay) {
       songManager.PauseSong(songPlay);
    }

    public void setVolume(Slider volumeSlider) {
        songManager.setVolume(volumeSlider);
    }
    
    public MediaPlayer getMediaPlayer() {
        return songManager.getMediaPlay();
    }

    /*
    * gets all songs from a playlist
    */
    public void loadSongsInPlaylist() {
        plm.getAllSongsFromPlaylist();
    }

    /*
    * Sends information on selected song and playlist and removes the song
    * from that playlist
    */
    public void removeSongPl(Songs selectedSong, Playlist selectedPlaylist) {
        plm.removeSongPl(selectedSong, selectedPlaylist);
        playlistList.remove(selectedPlaylist.getSongList().remove(selectedSong));
    }
    
  /*
    * returns list
    */
    public boolean listEnded() {
        return listEnded;
    }

    /*
    * sees if list has ended
    */
    public void setListEnded(boolean listEnded) {
        this.listEnded = listEnded;
    }

}
