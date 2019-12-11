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
 * @author Jacob, Jonas Charlie & René
 */
public class PlaylistManager
{

    private IPlaylistDao playlistDao;

    /**
     * Constructor for PlaylistManager.
     *
     * @throws Exception
     */
    public PlaylistManager() throws Exception
    {
        playlistDao = (IPlaylistDao) new PlaylistDBDAO();
    }

    /**
     * Returns all playlists
     *
     * @return
     * @throws DalException
     */
    public List<Playlist> getAllPlaylists() throws DalException
    {
        return playlistDao.getAllPlaylists();
    }

    /**
     * Sends information to create new playlist.
     *
     * @param playlist
     * @throws BLLException
     * @throws DalException
     */
    public void createPlaylist(Playlist playlist) throws BLLException, DalException
    {
        playlistDao.createPlaylist(playlist);
    }

    /**
     * Deletes the specified playlist.
     *
     * @param playlist
     * @throws BLLException
     * @throws DalException
     */
    public void deletePlaylist(Playlist playlist) throws BLLException, DalException
    {
        playlistDao.deletePlaylist(playlist);
    }

    /**
     * Edits the specified playlist
     *
     * @param playlist
     * @throws DalException
     */
    public void editPlaylist(Playlist playlist) throws DalException
    {
        playlistDao.updatePlaylist(playlist);
    }

    /**
     * Add songs to playlists.
     *
     * @param playlist
     * @param songs
     */
    public void addSongToPlaylist(Playlist playlist, Songs songs)
    {
        playlistDao.addSongToPlaylist(playlist, songs);
    }

    /**
     * Gets all songs from the playlists and returns it.
     *
     * @return
     * @throws DalException
     */
    public List<Playlist> getAllSongsFromPlaylist() throws DalException
    {
        return playlistDao.getAllSongsFromPlaylist();
    }
}
