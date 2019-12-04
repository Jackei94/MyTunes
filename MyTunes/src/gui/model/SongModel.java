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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    
}
