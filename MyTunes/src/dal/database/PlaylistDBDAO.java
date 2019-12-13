/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.database;

import be.Songs;
import be.Playlist;
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
 * @author Jacob, Jonas Charlie & René
 */
public class PlaylistDBDAO implements IPlaylistDao
{

    private DatabaseConnector dbCon;

    /**
     * Constructor for PlaylistDBDAO.
     *
     * @throws IOException
     */
    public PlaylistDBDAO() throws IOException
    {
        dbCon = new DatabaseConnector();
    }

    /**
     * Gets all Playlists in our database and returns it.
     *
     * @return
     * @throws DalException
     */
    public List<Playlist> getAllPlaylists() throws DalException
    {
        ArrayList<Playlist> allPlaylists = new ArrayList<>();
        // Attempts to connect to the database.
        try ( Connection con = dbCon.getConnection())
        {
            // SQL code.
            String sql = "SELECT * FROM Playlist;";
            // Prepared statement.
            Statement statement = con.createStatement();
            // Attempts to execute the database Query.
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
            {
                // Add all to a list.
                Playlist playlist = new Playlist();
                playlist.setId(rs.getInt("id"));
                playlist.setPlName(rs.getString("plName"));

                allPlaylists.add(playlist);
            }
            //Return.
            return allPlaylists;
        } catch (SQLException ex)
        {
            Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DalException();
        }
    }

    /**
     * Creates a new Playlist in our database with the values.
     *
     * @param playlist
     * @throws DalException
     */
    public void createPlaylist(Playlist playlist) throws DalException
    {
        // Attempts to connect to the database.
        try ( Connection con = dbCon.getConnection())
        {
            // SQL code.
            String sql = "INSERT INTO Playlist (plName) VALUES (?);";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // Sets the Strings.
            ps.setString(1, playlist.getPlName());

            // Attempts to update the database.
            int affectedRows = ps.executeUpdate();
            if (affectedRows < 1)
            {
                throw new SQLException("Can't save playlist");
            }

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next())
            {
                playlist.setId(rs.getInt(1));
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Deletes the Playlist with the matching Id.
     *
     * @param selectedPlaylist
     * @throws DalException
     */
    public void deletePlaylist(Playlist selectedPlaylist) throws DalException
    {
        // Attempts to connect to the database.
        try ( Connection con = dbCon.getConnection())
        {
            // SQL code
            String sql = "DELETE FROM Playlist WHERE id=?;";
            // Prepared statement.
            PreparedStatement ps = con.prepareStatement(sql);
            // Sets the string
            ps.setInt(1, selectedPlaylist.getId());
            // Attempts to execute the sql Code.
            ps.execute();
        } catch (SQLException ex)
        {
            Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    /**
     * Adds a song into the into the table "playlistSongs" in the database.
     *
     * @param playlist
     * @param songs
     */
    public void addSongToPlaylist(Playlist playlist, Songs songs)
    {
        // Attempts to connect to the database.
        try ( Connection con = dbCon.getConnection())
        {
            // SQL code.
            String sql = "INSERT INTO PlaylistSongs"
                    + "(songId, playlistsId)"
                    + "VALUES (?,?)";
            // Prepared statement.
            PreparedStatement pstmt
                    = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // Sets the strings.
            pstmt.setInt(1, songs.getId());
            pstmt.setInt(2, playlist.getId());
            // Attempts to execute the SQL code.
            int affected = pstmt.executeUpdate();
            if (affected < 1)
            {
                throw new SQLException("Can't save song to playlist");
            }

        } catch (SQLException ex)
        {
            Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Gets all tables the databases, compares the id's and returns it.
     *
     * @return
     */
    public List<Playlist> getAllSongsFromPlaylist()
    {
        ArrayList<Playlist> allSongsOnPlaylist = new ArrayList<>();
        // Attempts to connect to the database.
        try ( Connection con = dbCon.getConnection())
        {
            // Prepared statement and SQL code.
            PreparedStatement pstmt = con.prepareStatement("SELECT * FROM PlaylistSongs, Songs, Playlist WHERE PlaylistSongs.songId = Songs.id AND PlaylistSongs.playlistsId = Playlist.id;");

            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
            {
                // Sets the strings.
                Playlist playlist = new Playlist();
                Songs song = new Songs();
                playlist.setId(rs.getInt("playlistsId"));
                song.setId(rs.getInt("id"));
                song.setSongName(rs.getString("songName"));
                song.setTime(rs.getInt("time"));
                song.setFilePath(rs.getString("filePath"));

                // Goes through the list of all playlists and if a id on the list is the same as one in database
                // it will get the song list from that specific playlist and add the song that is on the database.
                for (int i = 0; i < allSongsOnPlaylist.size(); i++)
                {
                    if (allSongsOnPlaylist.get(i).getId() == playlist.getId())
                    {
                        allSongsOnPlaylist.get(i).getSongList().add(song);
                    }
                }
            }

        } catch (SQLException ex)
        {
            Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        //Return.
        return allSongsOnPlaylist;
    }

    /**
     * Updates the Playlist with the specified Id.
     *
     * @param playlist
     */
    public void updatePlaylist(Playlist playlist)
    {
        // Attempts to connect to the database.
        try ( Connection con = dbCon.getConnection())
        {
            // SQL code.
            String sql = "UPDATE Playlist SET plName=? WHERE id=?;";
            // Prepared statement.
            PreparedStatement ps = con.prepareStatement(sql);
            // Sets the strings.
            ps.setString(1, playlist.getPlName());
            ps.setInt(2, playlist.getId());
            // Attempts to execute the SQL code.
            int affected = ps.executeUpdate();
            if (affected < 1)
            {
                throw new SQLException("Can't edit song");
            }

        } catch (SQLException ex)
        {
            Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
