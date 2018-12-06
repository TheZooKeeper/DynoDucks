package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

public class Explosion implements ApplicationListener {



    private SpriteBatch batch;
    private TextureAtlas textureAtlas;
    private Animation animation;
    private float elapsedTime;
    // Save sprite frames in an Array for later:
    private Array<TextureAtlas.AtlasRegion> explosionFrames;

    // Make a sprite:
    private Sprite explosionSprite;



    @Override
    public void create() {
        batch = new SpriteBatch();
        textureAtlas = new TextureAtlas(Gdx.files.internal("explosion.atlas"));

        // explosionFrames = textureAtlas.findRegions("1");
        explosionFrames = textureAtlas.getRegions();
        animation = new Animation(1/5f, explosionFrames);
        // animation = new Animation(1/5f, textureAtlas.getRegions());

        // Make a sprite
        explosionSprite = new Sprite(explosionFrames.get(0));

    }

    @Override
    public void dispose() {
        batch.dispose();
        textureAtlas.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        explosionSprite.draw(batch); // <== We're drawing the explosionSprite.
        batch.end();

        // Set the next frame for the Sprite:

        elapsedTime += Gdx.graphics.getDeltaTime();
        // Set sprite's next frame from the animation:
        explosionSprite.setRegion((TextureRegion) animation.getKeyFrame(elapsedTime, true));
        // explosionSprite.setRegion(explosionFrames.get(7));


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
}

