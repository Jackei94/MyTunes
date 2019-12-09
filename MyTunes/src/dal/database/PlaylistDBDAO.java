/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.database;

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
 * @author Tramm
 */
public class PlaylistDBDAO implements IPlaylistDao
{
    private DatabaseConnector dbCon;
    
    public PlaylistDBDAO() throws IOException
    {
        dbCon = new DatabaseConnector();    
    }
    
    public List<Playlist> getAllPlaylists() throws DalException
    {
        ArrayList<Playlist> allPlaylists = new ArrayList<>();
        try (Connection con = dbCon.getConnection())
        { // prepare statement
            String sql = "SELECT * FROM Playlist;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next())
            { 
      // Add all to a list
                Playlist playlist = new Playlist();
                playlist.setId(rs.getInt("id"));
                playlist.setPlName(rs.getString("plName"));
                
                allPlaylists.add(playlist);
            }
            //Return
            return allPlaylists;
        } catch (SQLException ex)
        {   Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DalException(); 
        }
    }
    
    public void createPlaylist(Playlist playlist) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {   
            // SQL code
            String sql = "INSERT INTO Playlist (plName) VALUES (?);";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // Sets the Strings
            ps.setString(1, playlist.getPlName());
            
            // Attempts to update the database
            int affectedRows = ps.executeUpdate();
            if (affectedRows < 1)
                throw new SQLException("Can't save playlist");
            
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
    
    public void deletePlaylist(Playlist selectedPlaylist)
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "DELETE FROM Playlist WHERE id=?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, selectedPlaylist.getId());
            ps.execute();
        } catch (SQLException ex)
        {
            Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }
    
    @Override
    public void updatePlaylist(Playlist playlist)
    {
        
    }
}
