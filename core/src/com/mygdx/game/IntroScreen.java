package com.mygdx.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
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
    Label instructionLeft;
    Label instructionRight;
    Label instructionThrow;
    Label instructionRepair;
    Label goBack;
    public IntroScreen(DynoDucks game){
        this.game = game;
        // Pass this MainMenuScreen to a new GameScreen, so that GameScreen can
        // switch back to this MainMenuScreen when paused.
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

//        instructionLeft = new Label("\"D\" to move left!", new Label.LabelStyle(font, Color.BLACK));
//        instructionLeft.setPosition(390,420);
//        instructionRight = new Label("\"A\" to move right!", new Label.LabelStyle(font, Color.BLACK));
//        instructionRight.setPosition(390,370);
//        instructionThrow = new Label("\"E\" to throw dynamite!", new Label.LabelStyle(font,Color.BLACK));
//        instructionThrow.setPosition(390,320);
//        instructionRepair = new Label("\"Q\" to repair dock section (if able)!", new Label.LabelStyle(font,Color.BLACK));
//        instructionRepair.setPosition(390,300);
//        goBack = new Label("Click anywhere to go back to start screen", new Label.LabelStyle(font, Color.BLACK));
//        goBack.setPosition(35, 0);
//        stage.addActor(instructionLeft);
//        stage.addActor(instructionThrow);
//        stage.addActor(instructionRight);
//        stage.addActor(instructionRepair);
//        stage.addActor(goBack);
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
//        game.font.draw(game.batch, "The ducks have been fighting for weeks now about their docks.\n" +
//                "After one duck’s colorful comment about the other ducks mother, war was started.\n" +
//                "The war takes place on two docks over water, one at the top and bottom of the screen.\n" +
//                "The ducks must be swift if they want they want to survive.", 400, 920);
        game.batch.draw(imgMainScreen,-130,-140,1600,960);
        game.font.draw(game.batch,"\"D\" to move left!", 390,420);
        game.font.draw(game.batch,"\"A\" to move right!", 390,370);
        game.font.draw(game.batch,"\"E\" to throw dynamite!", 390,320);
        game.font.draw(game.batch,"\"Q\" to repair dock section (if able)!",390,300);
        game.font.draw(game.batch,"Click anywhere to go back to start screen", 35, 0);

        //game.font.draw(game.batch, "Tap anywhere to begin!", 100, 100);
        game.batch.end();

        if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Input.Keys.SPACE))
        {
            // OLD (starts new game every time):
            game.setScreen(new MainGameScreen(game));
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
