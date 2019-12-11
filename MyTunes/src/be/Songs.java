/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package be;

/**
 *
 * @author Jacob, Jonas Charlie & René
 */
public class Songs
{

    public String songName;
    public String songArtist;
    public String category;
    public String filePath;
    public int time;
    private int id;

    /**
     * Gets the Id and returns it.
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
     * Gets the SongName and returns it.
     *
     * @return
     */
    public String getSongName()
    {
        return songName;
    }

    /**
     * Sets the songName.
     *
     * @param songName
     */
    public void setSongName(String songName)
    {
        this.songName = songName;
    }

    /**
     * Gets the songArtist and returns it.
     *
     * @return
     */
    public String getSongArtist()
    {
        return songArtist;
    }

    /**
     * Sets the songArtist.
     *
     * @param songArtist
     */
    public void setSongArtist(String songArtist)
    {
        this.songArtist = songArtist;
    }

    /**
     * Gets the category and returns it.
     *
     * @return
     */
    public String getCategory()
    {
        return category;
    }

    /**
     * Sets the category.
     *
     * @param category
     */
    public void setCategory(String category)
    {
        this.category = category;
    }

    /**
     * Gets the filePath and returns it.
     *
     * @return
     */
    public String getFilePath()
    {
        return filePath;
    }

    /**
     * Sets the filePath.
     *
     * @param filePath
     */
    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    /**
     * Gets the time and returns it.
     *
     * @return
     */
    public int getTime()
    {
        return time;
    }

    /**
     * Sets the time.
     *
     * @param time
     */
    public void setTime(int time)
    {
        this.time = time;
    }

    /**
     * Returns toString.
     *
     * @return
     */
    public String toString()
    {
        return songName;
    }

}
