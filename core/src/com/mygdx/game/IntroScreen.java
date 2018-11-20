package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class IntroScreen implements Screen{
    public DynoDucks game;
    OrthographicCamera camera;
    public IntroScreen(DynoDucks game){
        this.game = game;
        // Pass this MainMenuScreen to a new GameScreen, so that GameScreen can
        // switch back to this MainMenuScreen when paused.
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Globals.RESOLUTION_WIDTH,Globals.RESOLUTION_HEIGHT);
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "The ducks have been fighting for weeks now about their respective docks.\n" +
                "After one duck’s colorful comment about the other ducks mother, war was started.\n" +
                "The war takes place on two docks on water, one at the top and bottom of the screen.\n" +
                "The ducks must be swift if they want their precious dock to survive.", 400, 920);
        //game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            // OLD (starts new game every time):
            // game.setScreen(new GameScreen(game));
            // dispose();

            // NEW: Implement pause to main menu;
            // switches to new gamescreen to start game;
            // or switches back to gamescreen to resume game

            //game.setScreen(gamescreen);
            //dispose();

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
