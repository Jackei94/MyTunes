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
 * @author Tramm
 */
public class SongsDBDAO implements ISongsDao
{
   /**
    * Connects to the database
    */
   
   private DatabaseConnector dbCon;
   
   public SongsDBDAO() throws Exception
           
           
   { 
       dbCon = new DatabaseConnector();
   }
   /**
    * 
    * Fetch all songs from the database 
    */
   
    public List<Songs> getAllSongs() throws DalException
    {
        ArrayList<Songs> allSongs = new ArrayList<>();
        try (Connection con = dbCon.getConnection())
        { // prepare statement
            String sql = "SELECT * FROM Songs;";
            Statement statement = con.createStatement();
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
        {   Logger.getLogger(SongsDBDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DalException(); 
        }
    }
    // Creates a new song in database
    public void createSongs(Songs songs) throws DalException
    {
    // Connects to the database   
        try (Connection con = dbCon.getConnection())
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
                throw new SQLException("Can't save song");
            
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next())
            {
               songs.setId(rs.getInt(1));
            }
        } 
        catch (SQLException ex){
            Logger.getLogger(SongsDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void deleteSongs(Songs selectedSong) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {
           
            String sql = "DELETE FROM Songs WHERE id=?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, selectedSong.getId());
            ps.execute();
            
            {
                throw new DalException();
            }
        } catch (SQLException ex){
           Logger.getLogger(SongsDBDAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
            throw new DalException();
        }
        
           
            
        
    }

    @Override
    public void updateSongs(Songs song) throws DalException
    {
        try (Connection con = dbCon.getConnection()) {
            String sql = "UPDATE Songs SET songName=?, songArtist=?, category=?, filePath=?, time=? WHERE id=?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, song.getSongName());
            ps.setString(2, song.getSongArtist());
            ps.setString(3, song.getCategory());
            ps.setString(4, song.getFilePath());
            ps.setDouble(5, song.getTime());
            ps.setInt(6, song.getId());
            
            int affected = ps.executeUpdate();
            if (affected<1)
                throw new SQLException("Can't edit song");
            
        } catch (SQLException ex) {
            Logger.getLogger(SongsDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void writeAllSongs(List<Songs> allSongs, String songName) throws DalException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void playSong(String filepath) throws DalException
    {
    
    }

   
}






