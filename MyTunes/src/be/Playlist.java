/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jacob, Jonas Charlie & René
 */
public class Playlist
{

    public String plName;
    public int plSongs;
    public int plTime;
    private int id;
    private final List<Songs> songsList;

    /**
     * Constructor for the playlist.
     */
    public Playlist()
    {
        this.songsList = new ArrayList();
    }

    /**
     * Gets the PlName and returns it.
     *
     * @return
     */
    public String getPlName()
    {
        return plName;
    }

    /**
     * Sets the PlName.
     *
     * @param plName
     */
    public void setPlName(String plName)
    {
        this.plName = plName;
    }

    /**
     * Gets the PlSongs and return it.
     *
     * @return
     */
    public int getPlSongs()
    {
        return plSongs;
    }

    /**
     * Sets the PlSongs.
     *
     * @param plSongs
     */
    public void setPlSongs(int plSongs)
    {
        this.plSongs = plSongs;
    }

    /**
     * Gets the PlTime and returns it.
     *
     * @return
     */
    public int getPlTime()
    {
        return plTime;
    }

    /**
     * Sets the PlTime.
     *
     * @param plTime
     */
    public void setPlTime(int plTime)
    {
        this.plTime = plTime;
    }

    /**
     * Gets the ID and returns it.
     *
     * @return
     */
    public int getId()
    {
        return id;
    }

    /**
     * Sets the Id.
     *
     * @param id
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * Gets the songList and returns it.
     *
     * @return
     */
    public List<Songs> getSongList()
    {
        return songsList;
    }
}
