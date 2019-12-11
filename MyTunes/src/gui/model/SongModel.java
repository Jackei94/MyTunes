/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.model;

import be.Songs;
import bll.BLLException;
import bll.SongManager;
import dal.DalException;
import java.io.IOException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Slider;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Jacob, Jonas Charlie & René
 */
public class SongModel
{

    private static SongModel instance;
    private ObservableList<Songs> allSongs;
    private ObservableList<Songs> selectedSong;
    private SongManager songManager;
    private StringProperty newOrEdit = new SimpleStringProperty();

    /**
     * Constructor for Songmodel.
     *
     * @throws DalException
     * @throws Exception
     */
    public SongModel() throws DalException, Exception
    {
        this.songManager = new SongManager();
        allSongs = FXCollections.observableArrayList();
        selectedSong = FXCollections.observableArrayList();
    }

    /**
     * Gets the instance of the Songmodel and returns it.
     *
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static SongModel getInstance() throws IOException, Exception
    {
        if (instance == null)
        {
            instance = new SongModel();
        }
        return instance;
    }

    /**
     * Gets all songs and returns it.
     *
     * @return
     */
    public ObservableList<Songs> getAllSongs()
    {
        return allSongs;
    }

    /**
     * Creates song.
     *
     * @param songs
     * @throws DalException
     * @throws BLLException
     */
    public void createSongs(Songs songs) throws DalException, BLLException
    {
        songManager.createSongs(songs);
    }

    /**
     * Clears all songs first and then loads all songs in after, so the shown
     * songs is updated.
     *
     * @throws DalException
     */
    public void loadSongs() throws DalException
    {
        allSongs.clear();
        allSongs.addAll(songManager.getAllSongs());
    }

    /**
     * Gets the selected song and returns it.
     *
     * @return
     */
    public ObservableList<Songs> getSelectedSong()
    {
        return selectedSong;
    }

    /**
     * Adds the selected song.
     *
     * @param songs
     */
    public void addSelectedSong(Songs songs)
    {
        selectedSong.add(songs);
    }

    /**
     * Edits the song and then updates the view by clearing and adding it all
     * after.
     *
     * @param songs
     * @throws DalException
     */
    public void edit(Songs songs) throws DalException
    {
        songManager.edit(songs);
        allSongs.add(songs);
        allSongs.clear();
        allSongs.addAll(songManager.getAllSongs());
    }

    /**
     * Deletes the selected song, and then removes it from the GUI.
     *
     * @param selectedSong
     * @throws DalException
     */
    public void deleteSong(Songs selectedSong) throws DalException
    {
        songManager.deleteSong(selectedSong);
        allSongs.remove(selectedSong);
    }

    /**
     * Plays the song.
     *
     * @param songPlay
     */
    public void PlaySong(Songs songPlay)
    {
        songManager.PlaySong(songPlay);
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
     * Pauses the song that is playing.
     *
     * @param songPlay
     */
    public void PauseSong(Songs songPlay)
    {
        songManager.PauseSong(songPlay);
    }

    /**
     * Sets the volume based on the volume slider.
     *
     * @param volumeSlider
     */
    public void setVolume(Slider volumeSlider)
    {
        songManager.setVolume(volumeSlider);
    }

    /**
     * Gets the mediaplayer and returns it.
     *
     * @return
     */
    public MediaPlayer getMediaPlayer()
    {
        return songManager.getMediaPlay();
    }
}
