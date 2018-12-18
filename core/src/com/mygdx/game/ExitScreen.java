package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class ExitScreen implements Screen
{
    public DynoDucks game;
    OrthographicCamera camera;
    private long exitTimer;
    private Sound yourDisssssssstttthhhhhhpicable;
    private Music gameOver;
    private boolean playerDied;

    private Texture background;

    public ExitScreen(DynoDucks game, boolean playerDied){
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Globals.RESOLUTION_WIDTH,Globals.RESOLUTION_HEIGHT);
        exitTimer = System.currentTimeMillis();
        yourDisssssssstttthhhhhhpicable = Gdx.audio.newSound(Gdx.files.internal("You're Despicable!.mp3"));

        gameOver = Gdx.audio.newMusic(Gdx.files.internal("Withdrawing.mp3"));

        // whether or not the player died in the previous screen influences what happens here
        // if they died, game over music will play and game over image shown
        // if they didn't die, daffy duck insults you
        this.playerDied = playerDied;
        if(playerDied)
        {
            background = new Texture(Gdx.files.internal("Game Over.png"));
        }
        else
        {
            background = new Texture(Gdx.files.internal("Win.png"));
        }

    }
    @Override
    public void show()
    {
        if(playerDied)
        {
            gameOver.play();
            gameOver.setLooping(true);
        }
        else
        {
            yourDisssssssstttthhhhhhpicable.play();
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        if(playerDied)
        {
            Sprite backgroundSprite = new Sprite(background);
            backgroundSprite.setSize(1600,1000);
            backgroundSprite.setPosition(50,0);
            backgroundSprite.draw(game.batch);
            game.batch.end();
            if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            {
                gameOver.stop();
                game.setScreen(new StartScreen(game));
            }
        }
        else
        {
            Sprite backgroundSprite = new Sprite(background);
            backgroundSprite.setSize(1600,1000);
            backgroundSprite.setPosition(0,0);
            backgroundSprite.draw(game.batch);
            game.batch.end();
            // wait 3 seconds before returning to start screen
            if(System.currentTimeMillis() - exitTimer > 3000)
            {
                game.setScreen(new StartScreen(game));
            }
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
}
