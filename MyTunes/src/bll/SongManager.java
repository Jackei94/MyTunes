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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jacob
 */
public class SongManager
{
    private ISongsDao songsDao;

    public SongManager() throws Exception
    {
        songsDao = (ISongsDao) new SongsDBDAO();
    }

    public List<Songs> getAllSongs() throws DalException
    {
        return songsDao.getAllSongs();
    }

    public List<Songs> search(String query) throws DalException
    {
        List<Songs> searchBase = songsDao.getAllSongs();
        List<Songs> result = new ArrayList<>();

        for (Songs songs : searchBase)
        {
            if (songs.getSongName().toLowerCase().contains(query.toLowerCase()))
            {
                result.add(songs);
            }
        }
        return result;
    }
    
    
    
}
