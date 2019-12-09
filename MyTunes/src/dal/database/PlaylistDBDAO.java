/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.database;

import be.Playlist;
import be.Songs;
import dal.DalException;
import dal.IPlaylistDao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tramm
 */
public class PlaylistDBDAO implements IPlaylistDao {

    private DatabaseConnector dbConnector;

//    private final List<Playlist> allPlaylists = new ArrayList();

    /*
    * Constructor for PlaylistDAO
     */
    public PlaylistDBDAO() throws IOException {
        dbConnector = new DatabaseConnector();
    }

    /*
    * Inserts the values of a playlist into the database table "playlist"
     */
    public void createPlaylist(Playlist playlist) {
        try ( Connection con = dbConnector.getConnection()) {
            String sql = "INSERT INTO Playlist (plName) VALUES (?);";
            PreparedStatement pstmt
                    = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, playlist.getPlName());

            int affected = pstmt.executeUpdate();
            if (affected < 1) {
                throw new SQLException("Can't save playlist");
            }

            //Gets the auto generated keys in database and sets it for the playlist
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                playlist.setPlaylistId(rs.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
    * Gets all playlists from database table "playlist" with id and names.
    * Adds each to a list and returns the list
     */
    public List<Playlist> getAllPlaylists() {
        ArrayList<Playlist> allPlaylists = new ArrayList<>();
        try ( Connection con = dbConnector.getConnection()) {
            PreparedStatement pstmt
                    = con.prepareStatement("SELECT * FROM Playlist");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Playlist playlist = new Playlist();
                playlist.setPlaylistId(rs.getInt("PlaylistId"));
                playlist.setPlName(rs.getString("plName"));

                allPlaylists.add(playlist);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allPlaylists;
    }

    /*
    * Updates the specific values of a playlist in the database by id.
     */
    public void updatePlaylist(Playlist playlist) {
        try ( Connection con = dbConnector.getConnection()) {
            String sql
                    = "UPDATE Playlist SET plName=? WHERE PlaylistId=?;";
            PreparedStatement pstmt
                    = con.prepareStatement(sql);
            pstmt.setString(1, playlist.getPlName());
            pstmt.setInt(2, playlist.getPlaylistId());

            int affected = pstmt.executeUpdate();
            if (affected < 1) {
                throw new SQLException("Can't edit playlist");
            }

        } catch (SQLException ex) {
            Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
    * Removes a playlist from the database by getting the selectedPlaylist id.
     */
    public void deletePlaylist(Playlist selectedPlaylist) {
        try ( Connection con = dbConnector.getConnection()) {
            String sql
                    = "DELETE FROM Playlist WHERE playlistID=?";
            PreparedStatement pstmt
                    = con.prepareStatement(sql);
            pstmt.setInt(1, selectedPlaylist.getPlaylistId());

            pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
    * Adds a song into the relations table "playlistSongs" in the database.
     */
    public void addSongToPlaylist(Playlist playlist, Songs songs) {

        try ( Connection con = dbConnector.getConnection()) {
            String sql = "INSERT INTO playlistSongs"
                    + "(songID, playlistID)"
                    + "VALUES (?,?)";
            PreparedStatement pstmt
                    = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(1, songs.getId());
            pstmt.setInt(2, playlist.getPlaylistId());

            int affected = pstmt.executeUpdate();
            if (affected < 1) {
                throw new SQLException("Can't save song to playlist");
            }

        } catch (SQLException ex) {
            Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /*
    * Selects all tables in the database and compare id's. 
     */
    public void getAllSongsFromPlaylist() {
    }
//        try ( Connection con = dbConnector.getConnection()) {
//            PreparedStatement pstmt
//                    = con.prepareStatement("SELECT * FROM PlaylistSongs, song, playlist"
//                            + " WHERE playlistSongs.songID = Songs.songID AND playlistSongs.playlistID = playlist.playlistID");
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next()) {
//                Playlist playlist = new Playlist();
//                Songs song = new Songs();
//                playlist.setID(rs.getInt("playlistID"));
//                song.setId(rs.getInt("id"));
//                song.setSongName(rs.getString("songName"));
//                song.setTime(rs.getInt("time"));
//                song.setFilePath(rs.getString("filePath"));
//
//                // Goes through the list of all playlists and if a id on the list is the same as one in database
//                // it will get the song list from that specific playlist and add the song that is on the database.
//                for (int i = 0; i < allPlaylists.size(); i++) {
//                    if (allPlaylists.get(i).getID() == playlist.getID()) {
//                        allPlaylists.get(i).getSongList().add(song);
//                    }
//                }
//
//            }
//            allPlaylists.clear();
//        } catch (SQLException ex) {
//            Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }

    /*
    * Deletes a song from a playlist by getting song id and playlist id.
     */
    public void removeSongFromPlaylist(Songs selectedSong, Playlist selectedPlaylist) {
        try ( Connection con = dbConnector.getConnection()) {
            String sql
                    = "DELETE FROM playlistSongs WHERE songID=? AND playlistID=?";
            PreparedStatement pstmt
                    = con.prepareStatement(sql);
            pstmt.setInt(1, selectedSong.getId());
            pstmt.setInt(2, selectedPlaylist.getPlaylistId());
            pstmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
