/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import be.Songs;
import java.util.List;

/**
 *
 * @author Jacob
 */
public interface ISongsDao
{
    void createSongs(Songs songs) throws DalException;
    
    void deleteSongs(Songs songs) throws DalException;

    List<Songs> getAllSongs() throws DalException;

    void updateSongs(Songs songs) throws DalException;
}
