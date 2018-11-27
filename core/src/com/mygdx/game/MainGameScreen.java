package com.mygdx.game;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;

import java.util.ArrayList;

public class MainGameScreen implements Screen {

    public DynoDucks game;
    OrthographicCamera camera;
    //Game Objects
    public Player player;
    public Enemy enemy;
    public Bridge pBridge;
    public Bridge eBridge;
    public ArrayList<Dynamite> dynamiteAL;


    public  MainGameScreen(DynoDucks game){
        this.game = game;
        // Pass this MainMenuScreen to a new GameScreen, so that GameScreen can
        // switch back to this MainMenuScreen when paused.
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Globals.RESOLUTION_WIDTH,Globals.RESOLUTION_HEIGHT);
        player = new Player();
        enemy = new Enemy();
        pBridge = new Bridge(8);
        eBridge = new Bridge(8);
        dynamiteAL = new ArrayList<Dynamite>();
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //TODO: We have to freeze the player/enemy
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
