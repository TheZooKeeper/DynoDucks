package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;



    /**
     * Created by matheussber on 12/15/15.
     */
    public class InstructionScreen {// implements Screen {
//        private Viewport viewport;
//        private Stage stage;
//        private Drop game;
//        private Texture imgNinja;
//        Label instructionLeft;
//        Label instructionRight;
//        Label instructionThrow;
//        Label instructionRepair;
//        Label goBack;
//        public InstructionScreen(Drop game) {
//            this.game = game;
//            this.viewport = new FitViewport(Drop.V_WIDTH,GameScreen.Drop,new OrthographicCamera());
//            stage = new Stage(this.viewport,game.batch);
//            imgNinja = new Texture(Gdx.files.internal("Ninja_desk_vector_Clipart.png"));
//            imgNinja.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
//            FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Sansation-Bold.ttf"));
//            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
//            parameter.genMipMaps = true;
//            parameter.minFilter = Texture.TextureFilter.Linear.MipMapLinearNearest;
//            parameter.magFilter = Texture.TextureFilter.Linear;
//            parameter.size = 20;
//            BitmapFont font = generator.generateFont(parameter);
//
//            instructionLeft = new Label("\"D\" to move left!", new Label.LabelStyle(font, Color.BLACK));
//            instructionLeft.setPosition(390,420);
//            instructionRight = new Label("\"A\" to move right!", new Label.LabelStyle(font, Color.BLACK));
//            instructionRight.setPosition(390,370);
//            instructionThrow = new Label("\"E\" to throw dynamite!", new Label.LabelStyle(font,Color.BLACK));
//            instructionThrow.setPosition(390,320);
//            instructionRepair = new Label("\"Q\" to repair dock section (if able)!", new Label.LabelStyle(font,Color.BLACK));
//            instructionRepair.setPosition(390,300);
//            goBack = new Label("Click anywhere to go back to start screen", new Label.LabelStyle(font, Color.BLACK));
//            goBack.setPosition(35, 0);
//            stage.addActor(instructionLeft);
//            stage.addActor(instructionThrow);
//            stage.addActor(instructionRight);
//            stage.addActor(instructionRepair);
//            stage.addActor(goBack);
//        }
//
//        @Override
//        public void show() {
//
//        }
//
//        @Override
//        public void render(float delta) {
//            Gdx.gl.glClearColor(255, 255, 255, 1);
//            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//            if(Gdx.input.justTouched()) {
//                game.setScreen(new StartScreen(game));
//                dispose();
//            }
//            stage.draw();
//            game.batch.begin();
//            game.batch.draw(imgNinja,0,10,380,350);
//            game.batch.end();
//        }
//
//        @Override
//        public void resize(int width, int height) {
//
//        }
//
//        @Override
//        public void pause() {
//
//        }
//
//        @Override
//        public void resume() {
//
//        }
//
//        @Override
//        public void hide() {
//
//        }
//
//        @Override
//        public void dispose() {
//
//        }
//    }
}
