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
 * @author Jacob, Jonas Charlie & René
 */
public class PlaylistModel
{

    private static PlaylistModel instance;
    private ObservableList<Playlist> allPlaylists;
    private ObservableList<Playlist> selectedPlaylist;
    private ObservableList<Playlist> allSongsOnPlaylist;
    private PlaylistManager playlistManager;
    private StringProperty newOrEdit = new SimpleStringProperty();

    /**
     * Constructor for PlaylistModel.
     *
     * @throws DalException
     * @throws Exception
     */
    public PlaylistModel() throws DalException, Exception
    {
        this.playlistManager = new PlaylistManager();
        allPlaylists = FXCollections.observableArrayList();
        selectedPlaylist = FXCollections.observableArrayList();
        allSongsOnPlaylist = FXCollections.observableArrayList();
    }

    /**
     * Sets the instance of the PlaylistModel and returns it.
     *
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static PlaylistModel getInstance() throws IOException, Exception
    {
        if (instance == null)
        {
            instance = new PlaylistModel();
        }
        return instance;
    }

    /**
     * Gets all playlists and returns it.
     *
     * @return
     */
    public ObservableList<Playlist> getAllPlaylists()
    {
        return allPlaylists;
    }

    /**
     * Creates new playlist.
     *
     * @param playlist
     * @throws DalException
     * @throws BLLException
     */
    public void createPlaylist(Playlist playlist) throws DalException, BLLException
    {
        playlistManager.createPlaylist(playlist);
    }

    /**
     * Clears all playlists first and then loads all playlists in after, so the
     * shown playlists is updated.
     *
     * @throws DalException
     */
    public void loadPlaylists() throws DalException
    {
        allPlaylists.clear();
        allPlaylists.addAll(playlistManager.getAllPlaylists());
    }

    /**
     * Gets the selected playlist and returns it.
     *
     * @return
     */
    public ObservableList<Playlist> getSelectedPlaylist()
    {
        return selectedPlaylist;
    }

    /**
     * Adds the selected playlist.
     *
     * @param playlist
     */
    public void addSelectedPlaylist(Playlist playlist)
    {
        selectedPlaylist.add(playlist);
    }

    /**
     * Edits the selected playlist, and updates the view afterwards.
     *
     * @param playlist
     * @throws DalException
     */
    public void editPlaylist(Playlist playlist) throws DalException
    {
        playlistManager.editPlaylist(playlist);
        allPlaylists.add(playlist);
        allPlaylists.clear();
        allPlaylists.addAll(playlistManager.getAllPlaylists());
    }

    /**
     * Deletes the selected playlist.
     *
     * @param playlist
     * @throws DalException
     * @throws BLLException
     */
    public void deletePlaylist(Playlist selectedPlaylist) throws DalException, BLLException
    {
        playlistManager.deletePlaylist(selectedPlaylist);
        allPlaylists.remove(selectedPlaylist);
    }

    /**
     * Returns the newOrEdit stringproperty.
     *
     * @return
     */
    public StringProperty newOrEditProperty()
    {
        return newOrEdit;
    }

    /**
     * Sets the newOrEdit stringproperty.
     *
     * @param newOrEdit
     */
    public void setNewOrEdit(String newOrEdit)
    {
        newOrEditProperty().set(newOrEdit);
    }

    /**
     * Returns newOrEditProperty with the get() method.
     *
     * @return
     */
    public final String getNewOrEdit()
    {
        return newOrEditProperty().get();
    }

    /**
     * Adds the specified song to the specified playlist.
     *
     * @param playlist
     * @param songs
     */
    public void addSongToPlaylist(Playlist playlist, Songs songs)
    {
        playlistManager.addSongToPlaylist(playlist, songs);
        allSongsOnPlaylist.add(playlist);
    }

    /**
     * Gets all songs from the specified playlist and returns it.
     *
     * @return
     */
    public ObservableList<Playlist> getAllSongsFromPlaylist()
    {
        return allSongsOnPlaylist;
    }

    /**
     * Clears all songs on playlist first and then loads it all in after, so the
     * shown songs on the playlists is updated.
     *
     * @throws DalException
     */
    public void loadSongsOnPlaylist() throws DalException
    {
        allSongsOnPlaylist.clear();
        allSongsOnPlaylist.addAll(playlistManager.getAllSongsFromPlaylist());
    }

}
