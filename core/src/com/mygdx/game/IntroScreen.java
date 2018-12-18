package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.Viewport;

public class IntroScreen implements Screen{
    public DynoDucks game;
    OrthographicCamera camera;
    private Viewport viewport;
    private Stage stage;
    private Texture imgMainScreen;
    private Music hellfire;
    Label instructionLeft;
    Label instructionRight;
    Label instructionThrow;
    Label instructionRepair;
    Label goBack;
    public IntroScreen(DynoDucks game){
        this.game = game;
        // Pass this MainMenuScreen to a new GameScreen, so that GameScreen can
        camera = new OrthographicCamera();
        //stage = new Stage(this.viewport,game.batch);
        camera.setToOrtho(false, Globals.RESOLUTION_WIDTH,Globals.RESOLUTION_HEIGHT);
        imgMainScreen = new Texture(Gdx.files.internal("duckBackgroundwithDuck.png"));
        imgMainScreen.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Sansation-Bold.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.genMipMaps = true;
        parameter.minFilter = Texture.TextureFilter.Linear.MipMapLinearNearest;
        parameter.magFilter = Texture.TextureFilter.Linear;
        parameter.size = 20;
        BitmapFont font = generator.generateFont(parameter);
        game.font = font;


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

        game.batch.draw(imgMainScreen,-155,-210,1800,1200);
        game.font.draw(game.batch,"Don't fall in the water!", 1100,750);
        game.font.draw(game.batch,"Press \"D\" to move left!", 1100,700);
        game.font.draw(game.batch,"Press \"A\" to move right!", 1100,650);
        game.font.draw(game.batch,"Press \"E\" to throw dynamite!", 1100,600);
        game.font.draw(game.batch,"Press \"Q\" to repair a dock section if it's destroyed!",1100,550);
        game.font.draw(game.batch,"Regular dock sections take 2 dynamite to destroy!", 1100, 500);
        game.font.draw(game.batch,"Rebuilt dock sections only take 1 dynamite!", 1100, 450);
        game.font.draw(game.batch,"Press Esc to quit!", 1100, 400);
        game.font.draw(game.batch,"Press Space or Click to begin!", 1100, 350);

        game.batch.end();

        if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.SPACE))
        {
            Globals.hellfire.stop();
            game.setScreen(new MainGameScreen(game));
        }
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
        {
            game.setScreen(new StartScreen(game));
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
