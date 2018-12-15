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

public class Explosion {


/*
    class not used

 */

    private SpriteBatch batch;
    private TextureAtlas textureAtlas;
    private Animation animation;
    private float elapsedTime;
    // Save sprite frames in an Array for later:
    private Array<TextureAtlas.AtlasRegion> explosionFrames;

    // Make a sprite:
    private Sprite explosionSprite;

    public boolean ready;
    private int x;
    private int y;

    public Explosion(int x, int y)
    {
        this.x = x;
        this.y = y;
    }


    public void setup()
    {
        textureAtlas = new TextureAtlas(Gdx.files.internal("explosion.atlas"));

        // explosionFrames = textureAtlas.findRegions("1");
        explosionFrames = textureAtlas.getRegions();
        animation = new Animation(1/5f, explosionFrames);
        // animation = new Animation(1/5f, textureAtlas.getRegions());

        // Make a sprite
        explosionSprite = new Sprite(explosionFrames.get(0));

    }


    public void dispose() {
        batch.dispose();
        textureAtlas.dispose();
    }


    public void explode(DynoDucks game)
    {
        textureAtlas = new TextureAtlas(Gdx.files.internal("explosion.atlas"));

        // explosionFrames = textureAtlas.findRegions("1");
        explosionFrames = textureAtlas.getRegions();
        animation = new Animation(1/5f, explosionFrames);
        // animation = new Animation(1/5f, textureAtlas.getRegions());

        // Make a sprite
        explosionSprite = new Sprite(explosionFrames.get(0));

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        explosionSprite.setPosition(x,y);
        explosionSprite.draw(game.batch);// <== We're drawing the explosionSprite.

        game.batch.end();

        // Set the next frame for the Sprite:

        elapsedTime += Gdx.graphics.getDeltaTime();
        // Set sprite's next frame from the animation:
        explosionSprite.setRegion((TextureRegion) animation.getKeyFrame(elapsedTime, false));
        // explosionSprite.setRegion(explosionFrames.get(7));


    }


}

