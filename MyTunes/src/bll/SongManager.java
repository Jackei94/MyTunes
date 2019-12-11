/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll;

import be.Songs;
import dal.DalException;
import dal.ISongsDao;
import dal.database.SongsDBDAO;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Jacob, Jonas Charlie & René
 */
public class SongManager
{

    private MediaPlayer mediaPlay;
    private ISongsDao songsDao;

    /**
     * Constructor for SongManager
     *
     * @throws Exception
     */
    public SongManager() throws Exception
    {
        songsDao = (ISongsDao) new SongsDBDAO();
    }

    /**
     * Gets all songs and returns it.
     *
     * @return
     * @throws DalException
     */
    public List<Songs> getAllSongs() throws DalException
    {
        return songsDao.getAllSongs();
    }

    /**
     * Sends information to create new song.
     *
     * @param songs
     * @throws BLLException
     * @throws DalException
     */
    public void createSongs(Songs songs) throws BLLException, DalException
    {
        songsDao.createSongs(songs);
    }

    /**
     * Sends information to edit/update the specified song
     *
     * @param songs
     * @throws DalException
     */
    public void edit(Songs songs) throws DalException
    {
        songsDao.updateSongs(songs);
    }

    /**
     * Deletes the specified song
     *
     * @param selectedSongs
     * @throws DalException
     */
    public void deleteSong(Songs selectedSongs) throws DalException
    {
        songsDao.deleteSongs(selectedSongs);
    }

    /**
     * Gets the MediaPlayer and returns it.
     *
     * @return
     */
    public MediaPlayer getMediaPlay()
    {
        return mediaPlay;
    }

    /**
     * Method to play song based in its filepath.
     *
     * @param songPlay
     */
    public void playSong(Songs songPlay)
    {
        songPlay = songPlay;
        String filePlay = new File(songPlay.getFilePath()).toURI().toString();
        Media hit = new Media(filePlay);
        mediaPlay = new MediaPlayer(hit);
        mediaPlay.play();
    }

    /**
     * Pauses the current song.
     *
     * @param songPlay
     */
    public void pauseSong(Songs songPlay)
    {
        mediaPlay.pause();
    }

    /**
     * Sets the volume.
     *
     * @param volumeSlider
     */
    public void setVolume(Slider volumeSlider)
    {
        volumeSlider.setValue(mediaPlay.getVolume() * 100);
        volumeSlider.valueProperty().addListener(new InvalidationListener()
        {
            @Override
            public void invalidated(Observable observable)
            {
                mediaPlay.setVolume(volumeSlider.getValue() / 100);
            }
        });
    }

    /**
     * Searches for songs by changing the GUI with the matching results.
     *
     * @param songs
     * @param searchQuery
     * @return
     */
    public ArrayList<Songs> search(List<Songs> songs, String searchQuery)
    {
        ArrayList<Songs> result = new ArrayList<>();

        for (Songs song : songs)
        {
            String songName = song.getSongName().trim().toLowerCase();
            String songArtist = song.getSongArtist().trim().toLowerCase();
            String category = song.getCategory().trim().toLowerCase();

            if (songName.contains(searchQuery.toLowerCase().trim())
                    || songArtist.contains(searchQuery.toLowerCase().trim())
                    || category.contains(searchQuery.toLowerCase().trim())
                    && !result.contains(songs))
            {
                result.add(song);
            }

        }
        return result;
    }
}
