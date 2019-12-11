/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import be.Playlist;
import be.Songs;
import java.util.List;

/**
 *
 * @author Jacob, Jonas Charlie & René
 */

/*
 * Interface for our PlaylistManager 
 */
public interface IPlaylistDao
{

    void createPlaylist(Playlist playlist) throws DalException;

    void deletePlaylist(Playlist playlist) throws DalException;

    void updatePlaylist(Playlist playlist) throws DalException;

    List<Playlist> getAllPlaylists() throws DalException;

    void addSongToPlaylist(Playlist playlist, Songs songs);

    List<Playlist> getAllSongsFromPlaylist() throws DalException;
}
