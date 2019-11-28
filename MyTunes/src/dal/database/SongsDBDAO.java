/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.database;

import be.Songs;
import dal.DalException;
import dal.ISongsDao;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;


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
        try (Connection con = dbCon.getConnection())
        {
            String sql = "SELECT * FROM Songs;";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            ArrayList<Songs> allSongs = new ArrayList<>();
            while (rs.next())
            {
                int id = rs.getInt("id");
                String songName = rs.getString("songName");
                String songArtist = rs.getString("songArtist");
                double time = rs.getDouble("time");
                String category = rs.getString("category");
                Songs song = new Songs(id, songName, songArtist, time, category);
                allSongs.add(song);
            }
            return allSongs;
        } catch (SQLException ex)
        {
            throw new DalException(); 
        }
    }
    
    public static void main(String[] args) throws Exception
    {
        try
        {
        SongsDBDAO songsDao = new SongsDBDAO();

        List<Songs> allSongs = songsDao.getAllSongs();
        for (Songs allSong : allSongs)
        {
            System.out.println(allSong + " ID: " + allSong.getId());
        }
        System.out.println("Done done!!");
        }
        catch (Throwable t)
        {
            t.printStackTrace();
            throw new DalException();
        }
    }
    
    

    @Override
    public Songs createSongs(String title, double length) throws DalException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
/**
 * Deletes a song from the database
 * @param songs
 * @throws DalException 
 */
    @Override
    public void deleteSongs(Songs songs) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {
            int id = songs.getId();
            String sql = "DELETE FROM movie WHERE id=?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int affectedRows = ps.executeUpdate();
            if (affectedRows != 1)
            {
                throw new DalException();
            }
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new DalException();
        }
    }

    @Override
    public void updateSongs(Songs songs) throws DalException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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



