/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.model;

import be.Songs;
import bll.SongManager;
import static com.oracle.nio.BufferSecrets.instance;
import static com.sun.source.util.DocTrees.instance;
import dal.DalException;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    public SongModel() throws DalException, Exception 
    {
        songManager = new SongManager();
        allSongs = FXCollections.observableArrayList();
        allSongs.addAll(songManager.getAllSongs());
    }

    public ObservableList<Songs> getAllSongs()
    {
        return allSongs;
    }
    
    public void createSongs(Songs songs) throws DalException
    {
        songManager.add(songs);
        allSongs.add(songs);
    }

//    public void search(String query) throws IOException, DalException
//    {
//        if (query.isEmpty())
//        {
//            allSongs.clear();
//            allSongs.addAll(songManager.getAllSongs());
//        } else
//        {
//            allSongs.clear();
//            allSongs.addAll(songManager.search(query));
//        }
//    }
    
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
    
    public void edit(Songs song) throws DalException 
    {
        songManager.edit(song);
        allSongs.add(song);
        allSongs.clear();
        allSongs.addAll(songManager.getAllSongs());
                
    }
}
