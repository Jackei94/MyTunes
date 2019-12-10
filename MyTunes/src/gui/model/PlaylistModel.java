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
import dal.DalException;
import java.io.IOException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Jacob
 */
public class PlaylistModel
{
    private static PlaylistModel instance;
    private ObservableList<Playlist> allPlaylists;
    private ObservableList<Playlist> selectedPlaylist;
    private ObservableList<Songs> allSongsOnPlaylist;
    private PlaylistManager playlistManager;
    
    public PlaylistModel() throws DalException, Exception
    {
        this.playlistManager = new PlaylistManager();
        allPlaylists = FXCollections.observableArrayList();
        selectedPlaylist = FXCollections.observableArrayList();
        allSongsOnPlaylist = FXCollections.observableArrayList();
    }
    
    public static PlaylistModel getInstance() throws IOException, Exception
    {
        if (instance == null)
        {
            instance = new PlaylistModel();
        }
        return instance;
    }
    
    public ObservableList<Playlist> getAllPlaylists()
    {
        return allPlaylists;
    }
    
    public void createPlaylist(Playlist playlist) throws DalException, BLLException
    {
        playlistManager.createPlaylist(playlist);
    }
    
    public void loadPlaylists() throws DalException {
        allPlaylists.clear();
        allPlaylists.addAll(playlistManager.getAllPlaylists());
    }

    public ObservableList<Playlist> getSelectedPlaylist() 
    {
        return selectedPlaylist;
    }
    
    public void addSelectedPlaylist(Playlist playlist) 
    {
        selectedPlaylist.add(playlist);
    }
    
    public void editPlaylist(Playlist playlist) throws DalException 
    {
        playlistManager.editPlaylist(playlist);
        allPlaylists.add(playlist);
        allPlaylists.clear();
        allPlaylists.addAll(playlistManager.getAllPlaylists());
    }
    
    public void deletePlaylist(Playlist playlist) throws DalException, BLLException 
    {
        playlistManager.deletePlaylist(playlist);
        allPlaylists.remove(selectedPlaylist);
    }
    
    private StringProperty newOrEdit = new SimpleStringProperty();

    public StringProperty newOrEditProperty()
    {
        return newOrEdit;
    }
    
    public void setNewOrEdit(String newOrEdit)
    {
        newOrEditProperty().set(newOrEdit);
    }

    public final String getNewOrEdit()
    {
        return newOrEditProperty().get();
    }
    
    public ObservableList<Songs> getAllSongsOnPlaylist()
    {
        return allSongsOnPlaylist;
    }
    
}
