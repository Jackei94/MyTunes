package gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import be.Playlist;
import be.Songs;
import bll.BLLException;
import bll.PlaylistManager;
import bll.SongManager;
import dal.DalException;
import gui.model.PlaylistModel;
import gui.model.SongModel;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Jacob, Jonas Charlie & René
 */
public class AppController implements Initializable
{

    private SongModel songModel;
    private PlaylistModel playlistModel;
    private final ObservableList<Songs> searchedSongs;
    private final SongManager songManager;
    private final PlaylistManager playlistManager;
    private final ToggleButton playButton = new ToggleButton("Play");
    private double currentVolume;
    private Image playImage = new Image(getClass().getResource("img/pausebutton.png").toExternalForm());
    private Image pauseImage = new Image(getClass().getResource("img/playbutton.png").toExternalForm());
    private double intVolume = .05;

    // FXML list.
    @FXML
    private TextField txtSearch;
    @FXML
    private ImageView reversebutton;
    @FXML
    private ImageView playbutton;
    @FXML
    private ImageView forwardbutton;
    @FXML
    private Label currentSong;
    @FXML
    private TableView<Playlist> playlists;
    @FXML
    private TableColumn<Playlist, String> playlistsName;
    @FXML
    private TableColumn<Playlist, Integer> playlistsSongs;
    @FXML
    private TableColumn<Playlist, Integer> playlistsTime;
    @FXML
    private Button playlistsNew;
    @FXML
    private Button playlistsEdit;
    @FXML
    private Button playlistsDelete;
    @FXML
    private TableView<Playlist> songPL;
    @FXML
    private TableColumn<Playlist, String> playlistSongs;
    @FXML
    private Button playlistSongsUp;
    @FXML
    private Button playlistSongsDown;
    @FXML
    private Button playlistSongsDelete;
    @FXML
    private Button songToPlaylistMove;
    @FXML
    private Button songsNew;
    @FXML
    private Button songsEdit;
    @FXML
    private Button songsDelete;
    @FXML
    private Button close;
    @FXML
    private TableView<Songs> allSongs;
    @FXML
    private TableColumn<Songs, String> songName;
    @FXML
    private TableColumn<Songs, String> songArtist;
    @FXML
    private TableColumn<Songs, String> category;
    @FXML
    private TableColumn<Songs, Integer> time;
    @FXML
    private Slider volumeSlider;

    /**
     * Initializer for AppController.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // Gets the instance for songModel and playlistModel
        try
        {
            songModel = SongModel.getInstance();
            playlistModel = PlaylistModel.getInstance();
        } catch (Exception ex)
        {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Sets the items on song table
        allSongs.setItems(songModel.getAllSongs());
        // Sets all cells to their values for song table
        songName.setCellValueFactory(new PropertyValueFactory("songName"));
        songArtist.setCellValueFactory(new PropertyValueFactory("songArtist"));
        category.setCellValueFactory(new PropertyValueFactory("category"));
        time.setCellValueFactory(new PropertyValueFactory("time"));
        // Sets the items on playlist table
        playlists.setItems(playlistModel.getAllPlaylists());
        // Sets all cells to their values for playlist table
        playlistsName.setCellValueFactory(new PropertyValueFactory("plName"));
        // Sets the playlists songs on the song table (Not working atm.)
        songPL.setItems(playlistModel.getAllSongsFromPlaylist());
        playlistSongs.setCellValueFactory(new PropertyValueFactory("songName"));
        // Sets the starting volume and sets the slider to the correct position 
        currentVolume = intVolume;
        volumeSlider.setValue(currentVolume);
        // Loads songs, playlists and playlistsongs
        try
        {
            // Loads all songs
            songModel.loadSongs();
            // Loads all playlists
            playlistModel.loadPlaylists();
            // Loads all songs on playlist (Not working atm.)
            playlistModel.loadSongsOnPlaylist();
        } catch (DalException ex)
        {
            Logger.getLogger(AppController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Contructor for AppController.
     *
     * @throws Exception
     */
    public AppController() throws Exception
    {
        this.searchedSongs = FXCollections.observableArrayList();
        this.songManager = new SongManager();
        this.playlistManager = new PlaylistManager();
    }

    /**
     * Sets the volume based on the placement of the volumeslider.
     *
     * @param event
     */
    @FXML
    private void volumeSlide(MouseEvent event)
    {
        songModel.setVolume(volumeSlider);
    }

    /**
     * Opens the window to create a new song.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void songsNewButton(ActionEvent event) throws IOException
    {
        // Sets the title of the stage.
        this.songModel = songModel;
        songModel.setNewOrEdit("New Song");

        Parent loader = FXMLLoader.load(getClass().getResource("SongsNew.fxml"));

        Scene scene = new Scene(loader);

        Stage stage = new Stage();

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Opens the windows to edit the selected song.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void songsEditButton(ActionEvent event) throws IOException
    {
        // Sets the title of the stage.
        this.songModel = songModel;
        songModel.setNewOrEdit("Edit Song");
        // Sets the selected song to be edited
        Songs songs = allSongs.getSelectionModel().getSelectedItem();
        songModel.addSelectedSong(songs);
        // Opens the window to make the changes
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SongsNew.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        SongsNewController controller = fxmlLoader.getController();
        controller.setModel(songModel);
        Stage stage = new Stage();

        stage.setScene(new Scene(root1));
        stage.show();
    }

    /**
     * Deletes the selected item.
     *
     * @param event
     * @throws DalException
     */
    @FXML
    private void songDeleteButton(ActionEvent event) throws DalException
    {
        // Checks if the song is on playlist before deleting it
        boolean isSongOnPlaylist = false;
        Songs selectedSong = allSongs.getSelectionModel().getSelectedItem();
        if (isSongOnPlaylist == true)
        {
            Alert warningAlert = new Alert(Alert.AlertType.WARNING, "The song is part of a playlist. Remove the song from the playlist first");
            warningAlert.showAndWait();

        } else
        {
            // Popup stage to confirm delete song.
            Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm Delete", ButtonType.YES, ButtonType.NO);
            deleteAlert.setContentText("Are you sure you want to delete: " + selectedSong.getSongName() + "?");
            deleteAlert.showAndWait();
            if (deleteAlert.getResult() == ButtonType.YES)
            {
                songModel.deleteSong(selectedSong);
            } else
            {
                deleteAlert.close();
            }
        }
    }

    /**
     * Plays the selected song, and changes the state of the button to be able
     * to pause the song.
     */
    @FXML
    public void mousePressedPlay()
    {
        // Sets the curent song playing 
        Songs songPlay = allSongs.getSelectionModel().getSelectedItem();
        if (playButton.getText().equals("Play"))
        {
            // Changes the image of the button.
            playbutton.setImage(playImage);
            // Plays the selected song.
            songModel.PlaySong(songPlay);
            // Sets the button state.
            playButton.setText("Pause");
            // Displays the current song that is playing  
            currentSong.setText(allSongs.getSelectionModel().getSelectedItem().getSongArtist() + " - " + allSongs.getSelectionModel().getSelectedItem().getSongName());
            // Sets the volume.
            songModel.getMediaPlayer().setVolume(currentVolume);

        } else if (playButton.getText().equals("Pause"))
        {
            // Changes the image of the button.
            playbutton.setImage(pauseImage);
            // Pauses the selected song.
            songModel.PauseSong(songPlay);
            // Sets the button state.
            playButton.setText("Play");
            // Sets the volume.
            songModel.setVolume(volumeSlider);
        }

    }

    /**
     * Plays the next song based on current song playing
     *
     * @throws DalException
     */
    @FXML
    public void mousePressedNext() throws DalException
    {
        // Stops the mediaplayer
        songModel.getMediaPlayer().stop();
        // Gets the next song on the list
        allSongs.getSelectionModel().selectNext();
        // Sets the new song as the song to be played
        Songs songPlay = allSongs.getSelectionModel().getSelectedItem();
        // Plays the new song
        songModel.PlaySong(songPlay);
        // Sets the button to Pause
        playButton.setText("Pause");
        // Displays the current song that is playing
        currentSong.setText(allSongs.getSelectionModel().getSelectedItem().getSongArtist() + " - " + allSongs.getSelectionModel().getSelectedItem().getSongName());
    }

    /**
     * Plays the previous song based on current song playing
     */
    @FXML
    public void mousePressedPrevious()
    {
        // Stops the mediaplayer
        songModel.getMediaPlayer().stop();
        // Gets the previous song on the list
        allSongs.getSelectionModel().selectPrevious();
        // Sets the new song as the song to be played
        Songs songPlay = allSongs.getSelectionModel().getSelectedItem();
        // Plays the new song
        songModel.PlaySong(songPlay);
        // Sets the button to Pause
        playButton.setText("Pause");
        // Displays the current song that is playing
        currentSong.setText(allSongs.getSelectionModel().getSelectedItem().getSongArtist() + " - " + allSongs.getSelectionModel().getSelectedItem().getSongName());
    }

    /**
     * Search songs based on typed values, and updates the view.
     */
    @FXML
    private void searchSong()
    {
        txtSearch.textProperty().addListener((observable, oldValue, newValue)
                ->
        {
            searchedSongs.setAll(songManager.search(songModel.getAllSongs(), newValue));
            allSongs.setItems(searchedSongs);
        });
    }

    /**
     * Opens the window to create a new Playlist.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void newPlaylist(ActionEvent event) throws IOException
    {
        this.playlistModel = playlistModel;
        playlistModel.setNewOrEdit("New Playlist");

        Parent loader = FXMLLoader.load(getClass().getResource("PlaylistNew.fxml"));

        Scene root2 = new Scene(loader);

        Stage stage = new Stage();

        stage.setScene(root2);
        stage.show();
    }

    /**
     * Adds the selected song to the selected playlist
     *
     * @param event
     */
    @FXML
    private void addToPlaylist(ActionEvent event)
    {
        // Sets the selectedSong
        Songs selectedSong = allSongs.getSelectionModel().getSelectedItem();
        // Sets the playlist which the song should be added to
        Playlist getSelectedPlaylist = playlists.getSelectionModel().getSelectedItem();
        // Adds the song to the playlist
        getSelectedPlaylist.getSongList().add(selectedSong);
        playlistManager.addSongToPlaylist(getSelectedPlaylist, selectedSong);

    }

    /**
     * Opens the windows to edit the selected playlist.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void editPlaylist(ActionEvent event) throws IOException
    {
        // Sets the title of the stage.
        this.playlistModel = playlistModel;
        playlistModel.setNewOrEdit("Edit Playlist");
        // Sets the selected song to be edited
        Playlist playlist = playlists.getSelectionModel().getSelectedItem();
        playlistModel.addSelectedPlaylist(playlist);
        // Opens the window to make the changes
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PlaylistNew.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        PlaylistNewController controller = fxmlLoader.getController();
        controller.setModel(playlistModel);
        Stage stage = new Stage();

        stage.setScene(new Scene(root1));
        stage.show();
    }

    /**
     * Deletes the selected playlist.
     *
     * @param event
     * @throws DalException
     * @throws BLLException
     */
    @FXML
    private void deletePlaylist(ActionEvent event) throws DalException, BLLException
    {
        // Select the playlist to be deleted.
        Playlist selectedPlaylist = playlists.getSelectionModel().getSelectedItem();
        // Popup stage with confirmation.
        Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm Delete", ButtonType.YES, ButtonType.NO);
        deleteAlert.setContentText("Are you sure you want to delete: " + selectedPlaylist.getPlName() + "?");
        deleteAlert.showAndWait();
        if (deleteAlert.getResult() == ButtonType.YES)
        {
            // Deletes the playlist.
            playlistModel.deletePlaylist(selectedPlaylist);
        } else
        {
            // Cancels the delete and closes the window. 
            deleteAlert.close();
        }
        playlistModel.loadPlaylists();
    }

    /**
     * Closes the app.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void exitApp(ActionEvent event) throws IOException
    {
        // Popup stage with confirmation on exit of app.
        Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION, "Confirm Exit", ButtonType.YES, ButtonType.NO);
        deleteAlert.setContentText("Are you sure? ");
        // Close App.
        deleteAlert.showAndWait();
        if (deleteAlert.getResult() == ButtonType.YES)
        {
            // Get a handle to the stage
            Stage stage = (Stage) close.getScene().getWindow();
            // Do what you have to do - Outrageous!
            stage.close();

        } else
        {
            // Close the popup stage.
            deleteAlert.close();
        }
    }
}
