/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll;

import be.Playlist;
import be.Songs;
import dal.DalException;
import dal.IPlaylistDao;
import dal.database.PlaylistDBDAO;
import java.util.List;

/**
 *
 * @author Tramm
 */
public class PlaylistManager
{
    private IPlaylistDao playlistDao;
    
    public PlaylistManager() throws Exception {
        playlistDao = (IPlaylistDao) new PlaylistDBDAO();
    }

    public List<Playlist> getAllPlaylists() throws DalException {
        return playlistDao.getAllPlaylists();
    }
    
    public void createPlaylist(Playlist playlist) throws BLLException, DalException {
        playlistDao.createPlaylist(playlist);
    }
    
    public void deletePlaylist(Playlist playlist) throws BLLException, DalException {
        playlistDao.deletePlaylist(playlist);
    }
    
    public void editPlaylist(Playlist playlist) throws DalException {
        playlistDao.updatePlaylist(playlist);
    }
    public void addSongToPlaylist(Playlist playlist, Songs songs)
    {
       playlistDao.addSongToPlaylist(playlist, songs);
    }
    public List<Playlist> getAllSongsFromPlaylist() throws DalException
    {
       return playlistDao.getAllSongsFromPlaylist();
    }
}
