package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class Globals {
    //Attributes and variable should be added to this file sparingly, if you don't know why
    //http://wiki.c2.com/?GlobalVariablesAreBad
    //All variables in this class should be public static final
    public static final int RESOLUTION_HEIGHT = 960;
    public static final int RESOLUTION_WIDTH = 1600;
    public static final Music hellfire = Gdx.audio.newMusic(Gdx.files.internal("hellfire.mp3"));

}
