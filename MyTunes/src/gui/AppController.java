package gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 *
 * @author Jacob
 */
public class AppController implements Initializable
{
    
    @FXML
    private TextField search;
    @FXML
    private ImageView reversebutton;
    @FXML
    private ImageView playbutton;
    @FXML
    private ImageView forwardbutton;
    @FXML
    private Slider volumeSlider;
    @FXML
    private Label currentSong;
    @FXML
    private ListView<?> playlists;
    @FXML
    private Button playlistsNew;
    @FXML
    private Button playlistsEdit;
    @FXML
    private Button playlistsDelete;
    @FXML
    private ListView<?> playlistSongs;
    @FXML
    private Button playlistSongsUp;
    @FXML
    private Button playlistSongsDown;
    @FXML
    private Button playlistSongsDelete;
    @FXML
    private Button songToPlaylistMove;
    @FXML
    private ListView<?> songs;
    @FXML
    private Button songsNew;
    @FXML
    private Button songsEdit;
    @FXML
    private Button songsDelete;
    @FXML
    private Button close;
    
    private void handleButtonAction(ActionEvent event)
    {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }    
    
}
