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
 * @author Jacob
 */
public class SongManager {

    private MediaPlayer mediaPlay;
    private ISongsDao songsDao;

    public SongManager() throws Exception {
        songsDao = (ISongsDao) new SongsDBDAO();
    }

    public List<Songs> getAllSongs() throws DalException {
        return songsDao.getAllSongs();
    }

    public void createSongs(Songs songs) throws BLLException, DalException {
        songsDao.createSongs(songs);
    }

    public ArrayList<Songs> search(List<Songs> songs, String searchQuery) {
        ArrayList<Songs> result = new ArrayList<>();

        for (Songs song : songs) {
            String songName = song.getSongName().trim().toLowerCase();
            String songArtist = song.getSongArtist().trim().toLowerCase();
            String category = song.getCategory().trim().toLowerCase();

            if (songName.contains(searchQuery.toLowerCase().trim())
                    || songArtist.contains(searchQuery.toLowerCase().trim())
                    || category.contains(searchQuery.toLowerCase().trim())
                    && !result.contains(songs)) {
                result.add(song);
            }

        }
        return result;
    }

    public void edit(Songs songs) throws DalException {
        songsDao.updateSongs(songs);
    }

    public void deleteSong(Songs selectedSongs) throws DalException {
        songsDao.deleteSongs(selectedSongs);
    }

    public void PlaySong(Songs songPlay) {
        songPlay = songPlay;
        String filePlay = new File(songPlay.getFilePath()).toURI().toString();
        Media hit = new Media(filePlay);
        mediaPlay = new MediaPlayer(hit);
        mediaPlay.play();

//        if (crntPath == null || !crntPath.equals(soundFile.getAbsolutePath())) {
//            crntPath = soundFile.toString();
//            Media me = new Media(soundFile.toURI().toString());
//            if (mp != null) {
//                mp.dispose();
//            }
//            mp = new MediaPlayer(me);
//        }
//        mp.play();
    }
    
    public void PauseSong(Songs songPlay){
        mediaPlay.pause();
    }

    public void setVolume(Slider volumeSlider) {
        volumeSlider.setValue(mediaPlay.getVolume() * 100);
        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                mediaPlay.setVolume(volumeSlider.getValue() / 100);
            }
        });
    }

    public MediaPlayer getMediaPlay() {
        return mediaPlay;
    }

}
