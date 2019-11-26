/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.model;

import be.Songs;
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
    private ObservableList<Songs> allSongs;

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

    public void search(String query) throws IOException, DalException
    {
        if (query.isEmpty())
        {
            allSongs.clear();
            allSongs.addAll(songManager.getAllSongs());
        } else
        {
            allSongs.clear();
            allSongs.addAll(songManager.search(query));
        }
    }
}
