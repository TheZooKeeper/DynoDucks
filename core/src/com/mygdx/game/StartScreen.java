package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.*;


    public class StartScreen {//} implements Screen {
//        final Drop game;
//        private Viewport viewport;
//        private Stage stage;
//        private boolean changeToInstruction = false;
//        Label startLabel;
//        Label instructionLabel;
//        Label quitLabel;
//        private Texture imgBack;
//        public StartScreen(final Drop game) {
//            this.game = game;
//            this.viewport = new FitViewport(Drop.V_WIDTH, Drop.V_HEIGHT, new OrthographicCamera());
//            stage = new Stage(viewport, game.batch);
//            Gdx.input.setInputProcessor(stage);
//        }
//
//        @Override
//        public void render(float delta) {
//            Gdx.gl.glClearColor(255,255,255,1);
//            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//            stage.draw();
//            game.batch.begin();
//            game.batch.draw(imgBack,10,10,300,470);
//            game.batch.end();
//
//        }
//
//        @Override
//        public void show() {
//            imgBack = new Texture(Gdx.files.internal("duckBackground.png"));
//            imgBack.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
//           /* FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/myfont.ttf"));
//            FreeTypeFontParameter parameter = new FreeTypeFontParameter();
//            parameter.size = 12;
//            BitmapFont font12 = generator.generateFont(parameter); // font size 12 pixels
//            generator.dispose(); // don't forget to dispose to avoid memory leaks!*/
//
//            FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Sansation-Bold.ttf"));
//            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
//            parameter.genMipMaps = true;
//            parameter.minFilter = Texture.TextureFilter.Linear.MipMapLinearNearest;
//            parameter.magFilter = Texture.TextureFilter.Linear;
//            parameter.size = 26;
//            final BitmapFont font = generator.generateFont(parameter);
//            generator.dispose();
//
//            startLabel = new Label("Start Game", new Label.LabelStyle(font, Color.BLACK));
//            startLabel.setPosition(500,300);
//            startLabel.setTouchable(Touchable.enabled);
//            startLabel.setBounds(500,300,startLabel.getWidth(),startLabel.getHeight());
//            startLabel.addListener(new ClickListener() {
//                @Override
//                public void clicked(InputEvent event, float x, float y) {
//                    game.setScreen(new PlayScreen(game));
//                    dispose();
//                }
//            });
//
//
//            instructionLabel = new Label("Instructions", new Label.LabelStyle(font, Color.BLACK));
//            instructionLabel.setPosition(500, 250);
//            instructionLabel.setTouchable(Touchable.enabled);
//            instructionLabel.setBounds(500,250,instructionLabel.getWidth(),instructionLabel.getHeight());
//            instructionLabel.addListener(new ClickListener() {
//                @Override
//                public void clicked(InputEvent event, float x, float y) {
//                    game.setScreen(new InstructionScreen(game));
//                    dispose();
//                }
//            });
//
//
//            quitLabel = new Label("Quit", new Label.LabelStyle(font, Color.BLACK));
//            quitLabel.setPosition(500, 200);
//            quitLabel.setTouchable(Touchable.enabled);
//            quitLabel.setBounds(500,150,quitLabel.getWidth(),quitLabel.getHeight());
//            quitLabel.addListener(new ClickListener() {
//                @Override
//                public void clicked(InputEvent event, float x, float y) {
//                    Gdx.app.exit();
//                }
//            });
//            stage.addActor(startLabel);
//            stage.addActor(instructionLabel);
//            stage.addActor(quitLabel);
//
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
//            startLabel.setTouchable(Touchable.disabled);
//            instructionLabel.setTouchable(Touchable.disabled);
//            quitLabel.setTouchable(Touchable.disabled);
//        }
//    }
//
}
