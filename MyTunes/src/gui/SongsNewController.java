/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import be.Songs;
import bll.BLLException;
import dal.DalException;
import gui.model.SongModel;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

/**
 * FXML Controller class
 *
 * @author Jacob
 */
public class SongsNewController implements Initializable
{
    ObservableList<String> newCategoryList = FXCollections.observableArrayList("Pop", "Rock", "Dubstep", "Slow", "Some shit");

    @FXML
    private Button newCancel;
    @FXML
    private TextField newTitle;
    @FXML
    private TextField newArtist;
    @FXML
    private TextField newTime;
    @FXML
    private Button newSave;
    @FXML
    private ChoiceBox newCategory;
    @FXML
    private TextField newFile;
    @FXML
    private Label newSong;
    
    
    private SongModel songModel;
    
    /**
    * Initializes the controller class.
    */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        try
        {
            songModel = SongModel.getInstance();
        } 
        catch (Exception ex)
        {
            Logger.getLogger(SongsNewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(!songModel.getSelectedSong().isEmpty()) {
        newTitle.setText(songModel.getSelectedSong().get(0).getSongName());
        newArtist.setText(songModel.getSelectedSong().get(0).getSongArtist());
        newCategory.setValue(songModel.getSelectedSong().get(0).getCategory());
        newFile.setText(songModel.getSelectedSong().get(0).getFilePath());
        newTime.setText(Integer.toString(songModel.getSelectedSong().get(0).getTime()));
        }
        newCategory.setValue("Pop");
        newCategory.setItems(newCategoryList);
        
        newSong.textProperty().unbind();
        this.songModel = songModel;
        newSong.textProperty().bind(songModel.newOrEditProperty());
    }

    @FXML
    private void newCancelButton(ActionEvent event)
    {
        songModel.getSelectedSong().clear();
        Stage stage = (Stage) newCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void newSaveButton(ActionEvent event) throws DalException, BLLException
    {
        if(!songModel.getSelectedSong().isEmpty())
        {
            Songs songs = new Songs();
            songs.setSongName(newTitle.getText());
            songs.setSongArtist(newArtist.getText());
            songs.setCategory((String) newCategory.getValue());
            songs.setFilePath(newFile.getText().trim());
            songs.setTime(Integer.parseInt(newTime.getText()));
            songs.setId(songModel.getSelectedSong().get(0).getId());
            songModel.edit(songs);
            songModel.getSelectedSong().clear();
        }
        else
        {
            Songs songs = new Songs();
            songs.setId(-1);
            songs.setSongName(newTitle.getText());
            songs.setSongArtist(newArtist.getText());
            songs.setTime(Integer.parseInt(newTime.getText()));
            songs.setCategory((String) newCategory.getValue());
            songs.setFilePath(newFile.getText());

            songModel.createSongs(songs);
        }
        songModel.loadSongs();
        Stage stage = (Stage) newSave.getScene().getWindow();
        stage.close();
    }
    
    public void setModel(SongModel model) 
    {
        this.songModel = model;
    }
    @FXML
    public void chooseFile(ActionEvent event) {
        
        try {
            FileChooser fileChooser = new FileChooser();
            Window stage = null;
            File file = fileChooser.showOpenDialog(stage);
            
            AudioFile f;
            f = AudioFileIO.read(file);
            Tag t = f.getTagOrCreateAndSetDefault();
            newTime.setText(Integer.toString(f.getAudioHeader().getTrackLength()));
            newArtist.setText(t.getFirst(FieldKey.ARTIST));
            
            newTitle.setText(t.getFirst(FieldKey.TITLE));
            //txtGenre.setText(t.getFirst(FieldKey.GENRE));
            newFile.setText(file.getPath());
            f.commit();      
             
        } catch (Exception e) {

        }

    }
}
