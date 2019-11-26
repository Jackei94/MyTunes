/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal.database;

import be.Songs;
import dal.DalException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Tramm
 */
public class SongsDBDAO
{
   private DatabaseConnector dbCon;
   
   public SongsDBDAO() throws Exception
   {
       dbCon = new DatabaseConnector();
   }
   
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
                double time = rs.getInt("time");
                String category = rs.getString("category");
                Songs song = new Songs(id, songName, songArtist, time, category);
                allSongs.add(song);
            }
            return allSongs;
        } catch (SQLException ex)
        {
            ex.printStackTrace();
            throw new DalException(); 
        }
    }
}
