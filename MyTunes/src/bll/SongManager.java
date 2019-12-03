/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll;

import be.Songs;
import dal.DalException;
import dal.ISongsDao;
import dal.database.SongsDBDAO;
import gui.SongsNewController;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Jacob
 */
public class SongManager
{
    SongsDBDAO songsDBDAO;
    private ISongsDao songsDao;

    public SongManager() throws Exception
    {
        songsDao = (ISongsDao) new SongsDBDAO();
    }

    public List<Songs> getAllSongs() throws DalException
    {
        return songsDao.getAllSongs();
    }
    
    public void createSongs(Songs songs) throws BLLException, DalException
    {
        songsDao.createSongs(songs);
    }

//    public List<Songs> search(String query) throws DalException
//    {
//        List<Songs> searchBase = songsDao.getAllSongs();
//        List<Songs> result = new ArrayList<>();
//
//        for (Songs songs : searchBase)
//        {
//            if (songs.getSongName().toLowerCase().contains(query.toLowerCase()))
//            {
//                result.add(songs);
//            }
//        }
//        return result;
//    }
    
    public ArrayList<Songs> search(List<Songs> songs, String searchQuery) {
        ArrayList<Songs> result = new ArrayList<>();
        
        for (Songs song : songs) {
            String songName = song.getSongName().trim().toLowerCase();
            String songArtist = song.getSongArtist().trim().toLowerCase();
            String category = song.getCategory().trim().toLowerCase();
            
            if(songName.contains(searchQuery.toLowerCase().trim())
                    || songArtist.contains(searchQuery.toLowerCase().trim())
                    || category.contains(searchQuery.toLowerCase().trim())
                    && !result.contains(songs)) {
                result.add(song);
            }
            
        }
        return result;
    }

    public void add(Songs songs) throws DalException
    {
        songsDBDAO.createSongs(songs);
    }
    
    public void edit(Songs song) throws DalException {
        songsDBDAO.updateSongs(song);
    }
}
