package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
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


    public class StartScreen implements Screen
    {


        private Texture background = new Texture(Gdx.files.internal("Title.png"));
        DynoDucks game;
        public StartScreen(DynoDucks game)
        {
            this.game = game;

        }

        @Override
        public void show()
        {
            Globals.hellfire.play();
        }

        @Override
        public void render(float delta)
        {
            game.batch.begin();
            Sprite backgroundSprite = new Sprite(background);
            backgroundSprite.setSize(1600,1000);
            backgroundSprite.setPosition(0,0);
            backgroundSprite.draw(game.batch);
            if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
            {
                game.setScreen(new IntroScreen(game));
            }
            game.batch.end();
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
