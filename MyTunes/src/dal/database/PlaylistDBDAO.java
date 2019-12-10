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
 * @author Tramm
 */
public class PlaylistDBDAO implements IPlaylistDao
{

    private DatabaseConnector dbCon;

    public PlaylistDBDAO() throws IOException
    {
        dbCon = new DatabaseConnector();
    }

    private final List<Playlist> allPlaylists = new ArrayList();
    private final List<Playlist> allSongsOnPlaylist = new ArrayList();
    
    public List<Playlist> getAllPlaylists() throws DalException
    {
        ArrayList<Playlist> allPlaylists = new ArrayList<>();
        try ( Connection con = dbCon.getConnection())
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
        {
            Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new DalException();
        }
    }

    public void createPlaylist(Playlist playlist) throws DalException
    {
        try ( Connection con = dbCon.getConnection())
        {
            // SQL code
            String sql = "INSERT INTO Playlist (plName) VALUES (?);";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // Sets the Strings
            ps.setString(1, playlist.getPlName());

            // Attempts to update the database
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

    public void deletePlaylist(Playlist selectedPlaylist) throws DalException
    {
        try ( Connection con = dbCon.getConnection())
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
    
    /*
    * Adds a song into the relations table "playlistSongs" in the database.
    */
    public void addSongToPlaylist(Playlist playlist, Songs songs) {
        
                try (Connection con = dbCon.getConnection()) {
           String sql = "INSERT INTO PlaylistSongs"
                   + "(songId, playlistsId)"
                   + "VALUES (?,?)";
           PreparedStatement pstmt
                   = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
           pstmt.setInt(1, songs.getId());
           pstmt.setInt(2, playlist.getId());
           
           int affected = pstmt.executeUpdate();
           if (affected<1)
                   throw new SQLException("Can't save song to playlist");
                 
        }
        
        catch (SQLException ex) {
            Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        
    }
    
    /*
    * Selects all tables in the database and compare id's. 
    */
    public List<Playlist> getAllSongsFromPlaylist() {
       ArrayList<Playlist> allSongsOnPlaylist = new ArrayList<>();     
       
        try (Connection con = dbCon.getConnection()) {
            PreparedStatement pstmt
                    = con.prepareStatement("SELECT * FROM PlaylistSongs, Songs, Playlist WHERE PlaylistSongs.songId = Songs.id AND PlaylistSongs.playlistsId = Playlist.id;");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Playlist playlist = new Playlist();
                Songs song = new Songs();
                playlist.setId(rs.getInt("playlistsId"));
                song.setId(rs.getInt("id"));
                song.setSongName(rs.getString("songName"));
                song.setTime(rs.getInt("time"));
                song.setFilePath(rs.getString("filePath"));
                
                // Goes through the list of all playlists and if a id on the list is the same as one in database
                // it will get the song list from that specific playlist and add the song that is on the database.
                for (int i = 0; i < allSongsOnPlaylist.size(); i++) { 
                    if(allSongsOnPlaylist.get(i).getId() == playlist.getId() ) 
                    {
                    allSongsOnPlaylist.get(i).getSongList().add(song);
                    }
                } 
            }
              
            } catch (SQLException ex) {
            Logger.getLogger(PlaylistDBDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
            return allSongsOnPlaylist;
        }
    
    @Override
    public void updatePlaylist(Playlist playlist)
    {
        try ( Connection con = dbCon.getConnection())
        {
            String sql = "UPDATE Playlist SET plName=? WHERE id=?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, playlist.getPlName());
            ps.setInt(2, playlist.getId());

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
