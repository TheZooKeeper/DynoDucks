package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class ExitScreen implements Screen
{
    public DynoDucks game;
    OrthographicCamera camera;
    private long exitTimer;
    private Sound yourDisssssssstttthhhhhhpicable;

    public ExitScreen(DynoDucks game){
        this.game = game;
        // Pass this MainMenuScreen to a new GameScreen, so that GameScreen can
        // switch back to this MainMenuScreen when paused.
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Globals.RESOLUTION_WIDTH,Globals.RESOLUTION_HEIGHT);
        exitTimer = System.currentTimeMillis();
        yourDisssssssstttthhhhhhpicable = Gdx.audio.newSound(Gdx.files.internal("You're Despicable!.mp3"));
        yourDisssssssstttthhhhhhpicable.play();

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.font.draw(game.batch, "You're Despicable", 400, 920);
        //game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
        game.batch.end();
        if(System.currentTimeMillis() - exitTimer > 3000)
        {
            game.setScreen(new IntroScreen(game));
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
