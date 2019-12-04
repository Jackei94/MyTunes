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
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Jacob
 */
public class SongsNewController implements Initializable
{

    ObservableList<String> newCategoryList = FXCollections.observableArrayList("Pop", "Rock", "Dubstep");

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

    private SongModel songModel;

    /**
    * Initializes the controller class.
    */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        newCategory.setValue("Pop");
        newCategory.setItems(newCategoryList);
        try
        {
            songModel = new SongModel();
        } 
        catch (Exception ex)
        {
            Logger.getLogger(SongsNewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
        

    @FXML
    private void newCancelButton(ActionEvent event)
    {
        Stage stage = (Stage) newCancel.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void newSaveButton(ActionEvent event) throws DalException, BLLException
    {
//        if(!songModel.getSelectedSong().isEmpty())
//        {
//            Songs song = new Songs();
//            song.setSongName(newTitle.getText());
//            song.setSongArtist(newArtist.getText());
//            song.setCategory((String) newCategory.getValue());
//            song.setFilePath(newFile.getText().trim());
//            song.setTime(Integer.parseInt(newTime.getText()));
//            song.setId(songModel.getSelectedSong().get(0).getId());
//            songModel.edit(song);
//            songModel.getSelectedSong().clear();
//        }
//        else
        {
            Songs songs = new Songs();
            songs.setId(-1);
            songs.setSongName(newTitle.getText());
            songs.setSongArtist(newArtist.getText());
            songs.setTime(Integer.parseInt(newTime.getText()));
            songs.setCategory((String) newCategory.getValue());
            songs.setFilePath(newFile.getText());

            songModel.createSongs(songs);
            
            songModel.loadSongs();
            
            Stage stage = (Stage) newSave.getScene().getWindow();
            stage.close();
        }
    }
    
}
