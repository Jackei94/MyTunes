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
                String filePath = rs.getString("filePath");
                Songs song = new Songs(id, songName, songArtist, time, category, filePath);
                allSongs.add(song);
            }
            return allSongs;
        } catch (SQLException ex)
        {
            throw new DalException(); 
        }
    }
    
    public Songs createSongs(Songs songs) throws DalException
    {
        try (Connection con = dbCon.getConnection())
        {
            String sql = "INSERT INTO Songs VALUES (?,?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, songs.getSongName());
            ps.setString(2, songs.getSongArtist());
            ps.setDouble(3, songs.getTime());
            ps.setString(4, songs.getCategory());
            ps.setString(5, songs.getFilePath());
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 1)
            {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next())
                {
                    int id = rs.getInt(1);
                    Songs song = new Songs(id, songName, songArtist, time, category, filePath);
                    return song;
                }
            }
            throw new DalException();
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new DalException();
        }
    }
    
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

    @Override
    public Songs createSongs(String songName, String songArtist, double time, String category, String filePath) throws DalException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}






