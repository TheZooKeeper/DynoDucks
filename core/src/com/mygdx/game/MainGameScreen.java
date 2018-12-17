package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Timer;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Iterator;

public class MainGameScreen implements Screen {

    public DynoDucks game;
    OrthographicCamera camera;
    //Game Objects
    public Player player;
    public Enemy enemy;
    public Bridge pBridge;
    public Bridge eBridge;
    public ArrayList<Dynamite> dynamiteAL;

    private int[] blockCenters = new int[]{128, 320, 512, 704, 896, 1088, 1280, 1472};
    private Music fightToTheDeath;

    private ArrayList<ExplosionAnimation> explosionArrayList;


    TiledMap tiledMap;
    TiledMapRenderer tiledMapRenderer;

    private boolean gameOver;
    private long timeSinceGameOver;


    public MainGameScreen(DynoDucks game) {
        this.game = game;
        // Pass this MainMenuScreen to a new GameScreen, so that GameScreen can
        // switch back to this MainMenuScreen when paused.
        camera = new OrthographicCamera();
        //camera.setToOrtho(false, Globals.RESOLUTION_WIDTH,Globals.RESOLUTION_HEIGHT);
        player = new Player();
        enemy = new Enemy(this);
        pBridge = new Bridge(8);
        eBridge = new Bridge(8);
        dynamiteAL = new ArrayList<Dynamite>();

        fightToTheDeath = Gdx.audio.newMusic(Gdx.files.internal("The Man with the Machine Gun.mp3"));
        fightToTheDeath.setLooping(true);


        explosionArrayList = new ArrayList<ExplosionAnimation>();

        gameOver = false;

        setupBackground();


    }

    @Override
    public void show() {
        fightToTheDeath.play();
    }

    @Override
    public void render(float delta) {
        //TODO: We have to freeze the player/enemy
        renderBackground();
        // draw bridges and blocks
        pBridge.draw(game, DuckType.PLAYER);
        eBridge.draw(game, DuckType.ENEMY);
        //draw player
        player.draw(game);
        // handle movement
        player.processMovement();

        enemy.move();
        enemy.draw(game);



        /*
        if(shouldRepairBlock() == true && enemy.state != DuckState.REBUILDING)
        {
            enemy.repair(whichBlockShouldBeRepaired())
        }
        enemy.move();

        */



        // check if player threw a dynamite
        if (player.newDynamite != null) {
            dynamiteAL.add(player.newDynamite);
        }
        // check if enemy threw a dynamite
        if (enemy.newDynamite != null) {
            dynamiteAL.add(enemy.newDynamite);
        }

        //handle dynamite movement
        for (Dynamite dynamite : dynamiteAL) {
            dynamite.draw(game);
        }
        // check to see whether a bridge block should explode
        handleBridgeExplosions();

        // play explosion animations and sounds
        for (ExplosionAnimation explosion : explosionArrayList) {
            explosion.explode(game, gameOver);
        }

        // check if game is over
        if((checkIfGameOver(player) || checkIfGameOver(enemy)) && gameOver)
        {
            //game over does not remove dynamite. The player can possibly still die
            gameOver = true;
            timeSinceGameOver = System.currentTimeMillis();

        }

        // if game is over, wait a bit before ending the game
        if(gameOver && (System.currentTimeMillis() - timeSinceGameOver > 5000))
        {
            gameOver = false;
            fightToTheDeath.stop();
            game.setScreen(new ExitScreen(game));
        }


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

    public void setupBackground() {
        // stuff for bridge, don't ask idk
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, w, h);
        camera.update();
        tiledMap = new TmxMapLoader().load("final tileset empty bridges.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
    }

    public void renderBackground() {
        // more stuff for bridge, don't ask
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
    }

    public void handleBridgeExplosions() {
        // for every dynamite
        Iterator<Dynamite> iter = dynamiteAL.iterator();
        while (iter.hasNext()) {
            Dynamite dynamite = iter.next();

            // if its a player thrown dynamite and it's at the right y coordinate to hit a bridge blook
            if (dynamite.yCoord > 750 && dynamite.duck.type == DuckType.PLAYER) {

                int min = 1000000;
                int minIndex = 100000;
                int index = 0;
                // try find the closest block with the center closest to the dynamite
                for (Integer value : blockCenters) {
                    if (Math.abs(value - dynamite.xCoord) < min) {
                        min = Math.abs(value - dynamite.xCoord);
                        minIndex = index;
                    }
                    index++;
                }
                // if it's not currently destroyed, hit it
                if (eBridge.bridgeArr[minIndex].state != BlockState.DESTROYED) {
                    eBridge.bridgeArr[minIndex].hit();
                    dynamite.explode();
                    ExplosionAnimation explosion = new ExplosionAnimation(dynamite.xCoord, dynamite.yCoord);
                    explosionArrayList.add(explosion);
                    iter.remove();
                }
                // if it is destroyed, let it go

            }
            // do the same thing for enemy dynamite
            else if (dynamite.yCoord < 220 && dynamite.duck.type == DuckType.ENEMY) {
                int min = 1000000;
                int minIndex = 100000;
                int index = 0;
                for (Integer value : blockCenters) {
                    if (Math.abs(value - dynamite.xCoord) < min) {
                        min = Math.abs(value - dynamite.xCoord);
                        minIndex = index;
                    }
                    index++;
                }
                if (pBridge.bridgeArr[minIndex].state != BlockState.DESTROYED) {
                    pBridge.bridgeArr[minIndex].hit();
                    dynamite.explode();
                    iter.remove();
                }

            }

        }

    }

    // returns the index of the block a duck is standing on
    // actually tested and can confirm is accurate
    public int getBlockStandingOn(Duck duck)
    {
        int min = 1000000;
        int minIndex = 100000;
        int index = 0;
        for (Integer value : blockCenters)
        {
            System.out.println("Duck " + duck.type + " distance to block " + index + "; " + Math.abs(value - duck.getxCoord()));
            if ((Math.abs(value - duck.getxCoord())) < min)
            {
                min = Math.abs(value - duck.getxCoord());
                minIndex = index;
            }
            index++;
        }
        return minIndex;
    }
    // returns the index of the closest block the caller
    public int getClosestBlock(Duck duck)
    {
        int min = 1000000;
        int minIndex = 100000;
        int index = 0;
        for (Integer value : blockCenters)
        {
            System.out.println("Duck " + duck.type + " distance to block " + index + "; " + Math.abs(value - duck.getxCoord()));
            if ((Math.abs(value - duck.getxCoord())) < min)
            {
                min = Math.abs(value - duck.getxCoord());
                minIndex = index;
            }
            index++;
        }
        if (index == 1)
        {
            return 2;
        }
        else if (index == blockCenters.length -1 )
        {
            return index -1;
        }
        else
        {
            return (blockCenters[index] > duck.getxCoord() ? index - 1 : index +1);
        }
    }

    // returns whether or not there is a block on the enemy's left or right side that should be repaired
    // may or may not work (probably not)
    public boolean shouldRepairBlock()
    {
        if(getBlockStandingOn(enemy) == 0)
        {
            return eBridge.bridgeArr[getBlockStandingOn(enemy) + 1].state == BlockState.DESTROYED;
        }
        else if(getBlockStandingOn(enemy) == 7)
        {
            return eBridge.bridgeArr[getBlockStandingOn(enemy) - 1].state == BlockState.DESTROYED;

        }
        else
        {
            return eBridge.bridgeArr[getBlockStandingOn(enemy) + 1].state == BlockState.DESTROYED
                    || eBridge.bridgeArr[getBlockStandingOn(enemy) - 1].state == BlockState.DESTROYED;
        }
    }

    // returns the index of which block the enemy duck should repair, if it is destroyed
    // may or may not work
    // probably not
    public int whichBlockShouldBeRepaired()
    {
        if(getBlockStandingOn(enemy) == 0)
        {
            if(eBridge.bridgeArr[getBlockStandingOn(enemy) + 1].state == BlockState.DESTROYED)
            {
                return getBlockStandingOn(enemy) + 1;
            }
        }
        else if(getBlockStandingOn(enemy) == 7)
        {
            if(eBridge.bridgeArr[getBlockStandingOn(enemy) - 1].state == BlockState.DESTROYED)
            {
                return getBlockStandingOn(enemy) - 1;
            }

        }
        else
        {
            if(eBridge.bridgeArr[getBlockStandingOn(enemy) + 1].state == BlockState.DESTROYED
                    || eBridge.bridgeArr[getBlockStandingOn(enemy) - 1].state == BlockState.DESTROYED)
            {
                if(eBridge.bridgeArr[getBlockStandingOn(enemy) + 1].state == BlockState.DESTROYED)
                {
                    return getBlockStandingOn(enemy) + 1;
                }
                else
                {
                    return getBlockStandingOn(enemy) - 1;
                }
            }
        }
        // this default should never happen, only call this method if shouldRepairBlock() returns true
        return 1;
    }

    // checks to see if a duck is standing on a destroyed block
    // if so, set their state to dead and return true, signaling the end of the game
    public boolean checkIfGameOver(Duck duck)
    {
        int minIndex = getBlockStandingOn(duck);
        if(duck.type == DuckType.PLAYER)
        {
            if (pBridge.bridgeArr[minIndex].state == BlockState.DESTROYED)
            {
                duck.setState(DuckState.DEAD);
                return true;
            }
            return false;
        }
        else
        {
            if (eBridge.bridgeArr[minIndex].state == BlockState.DESTROYED)
            {
                duck.setState(DuckState.DEAD);
                return true;
            }
            return false;
        }

    }


}

