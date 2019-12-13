/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.database;

import be.Songs;
import dal.DalException;
import dal.ISongsDao;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jacob, Jonas Charlie & René
 */
public class SongsDBDAO implements ISongsDao
{

    private DatabaseConnector dbCon;

    /**
     * Constructor for PlaylistDBDAO.
     *
     * @throws Exception
     */
    public SongsDBDAO() throws Exception
    {
        dbCon = new DatabaseConnector();
    }

    /**
     * Gets all Songs in our database and returns it.
     *
     * @return
     * @throws DalException
     */
    public List<Songs> getAllSongs() throws DalException
    {
        ArrayList<Songs> allSongs = new ArrayList<>();
        // Attempts to connect to the database.
        try ( Connection con = dbCon.getConnection())
        {
            // SQL code. 
            String sql = "SELECT * FROM Songs;";
            // Create statement.
            Statement statement = con.createStatement();
            // Attempts to execute the statement.
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
            {
                // Add all to a list
                Songs song = new Songs();
                song.setId(rs.getInt("id"));
                song.setSongName(rs.getString("songName"));
                song.setSongArtist(rs.getString("songArtist"));
                song.setCategory(rs.getString("category"));
                song.setFilePath(rs.getString("filePath"));
                song.setTime(rs.getInt("time"));

                allSongs.add(song);
            }
            //Return
            return allSongs;
        } catch (SQLException ex)
        {
            Logger.getLogger(SongsDBDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DalException();
        }
    }

    /**
     * Creates a new Song in our database with the values.
     *
     * @param songs
     * @throws DalException
     */
    public void createSongs(Songs songs) throws DalException
    {
        // Attempts to connect to the database.
        try ( Connection con = dbCon.getConnection())
        {
            // SQL code
            String sql = "INSERT INTO Songs (songName, songArtist, time, category, filePath) VALUES (?,?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // Sets the Strings
            ps.setString(1, songs.getSongName());
            ps.setString(2, songs.getSongArtist());
            ps.setInt(3, songs.getTime());
            ps.setString(4, songs.getCategory());
            ps.setString(5, songs.getFilePath());

            // Attempts to update the database
            int affectedRows = ps.executeUpdate();
            if (affectedRows < 1)
            {
                throw new SQLException("Can't save song");
            }

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next())
            {
                songs.setId(rs.getInt(1));
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(SongsDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Deletes the Song with the matching Id.
     *
     * @param selectedSong
     * @throws DalException
     */
    public void deleteSongs(Songs selectedSong) throws DalException
    {
        // Attempts to connect to the database.
        try ( Connection con = dbCon.getConnection())
        {
            // SQL code. 
            String sql = "DELETE FROM Songs WHERE id=?;";
            // Prepared statement. 
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, selectedSong.getId());
            // Attempts to execute the statement.
            ps.execute();
        } catch (SQLException ex)
        {
            Logger.getLogger(SongsDBDAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    /**
     * Updates the Song with the specified Id.
     *
     * @param song
     * @throws DalException
     */
    public void updateSongs(Songs song) throws DalException
    {
        // Attempts to connect to the database.
        try ( Connection con = dbCon.getConnection())
        {
            // SQL code. 
            String sql = "UPDATE Songs SET songName=?, songArtist=?, category=?, filePath=?, time=? WHERE id=?;";
            // Prepared statement
            PreparedStatement ps = con.prepareStatement(sql);
            // Sets the strings.
            ps.setString(1, song.getSongName());
            ps.setString(2, song.getSongArtist());
            ps.setString(3, song.getCategory());
            ps.setString(4, song.getFilePath());
            ps.setInt(5, song.getTime());
            ps.setInt(6, song.getId());
            // Attempts to execute SQL code.
            int affected = ps.executeUpdate();
            if (affected < 1)
            {
                throw new SQLException("Can't edit song");
            }

        } catch (SQLException ex)
        {
            Logger.getLogger(SongsDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
