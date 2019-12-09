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
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Tramm
 */
public class PlaylistManager
{
    private IPlaylistDao pDBDAO;

   /*
   * Constructor for PlaylistManager
   */
    public PlaylistManager() throws IOException {
        this.pDBDAO = new PlaylistDBDAO();
    }
    
    /*
    * Sends information of a new playlist
    */
    public void createPlaylist(Playlist playlist) throws DalException
    {
        pDBDAO.createPlaylist(playlist);
    }
    
    /*
    * returns all playlists
    */
    public List<Playlist> getAllPlaylists() throws DalException {
        return pDBDAO.getAllPlaylists();
    }
    
    /*
    * Sends information on which play list should be edited and to what
    */
    public void updatePlaylist(Playlist playlist) throws DalException {
        pDBDAO.updatePlaylist(playlist);
    }
    
    /*
    * Sends information on which playlist should be removed
    */
    public void deletePlaylist(Playlist selectedPlaylist) throws DalException {
        pDBDAO.deletePlaylist(selectedPlaylist);
    }

    /*
    * sends information on which playlist should the song be added to
    */
    public void addSongToPlaylist(Playlist playlist, Songs songs) {
        pDBDAO.addSongToPlaylist(playlist, songs);
    }
    
    /*
    * gets all songs from playlists
    */
    public void getAllSongsFromPlaylist() {
        pDBDAO.getAllSongsFromPlaylist();
    }

    /*
    * removes a song from a specific playlist
    */
    public void removeSongFromPlaylist(Songs selectedSong, Playlist selectedPlaylist) {
        pDBDAO.removeSongFromPlaylist(selectedSong, selectedPlaylist);
    }
}
