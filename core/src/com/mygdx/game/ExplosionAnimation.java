package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;


// Class for the explosion of the dynamite
// also plays the sound effect
public class ExplosionAnimation
{
    private int x;
    private int y;
    private int frame = 0;

    TextureAtlas textureAtlas;
    Sound explosionSound;

    // location of the explosion
    public ExplosionAnimation(int x, int y,TextureAtlas textureAtlas, Sound explosionSound,DuckType duckType)
    {

        // need an offset due to original texture map being slightly off center
        this.x = x - 50;
        this.y = y-30;
        if(duckType == DuckType.ENEMY)
        {
            this.y = y -120;
        }

        this.textureAtlas = textureAtlas;
        this.explosionSound = explosionSound;

    }
    public void explode (DynoDucks game, Boolean gameOver, MainGameScreen mainGame)
    {
        game.batch.begin();
        // for each 3 frame window
        // draw the next stage of the explosion
        // animation takes 24 frames total
        if(frame <= 3)
        {
            // play sound effect on the first frame
            // take game over as a parameter to ensure that the sound doesn't overpower daffy duck
            // telling you how despicable you really are after the game is over
            if(frame == 1)
            {
                if(gameOver && System.currentTimeMillis() - mainGame.timeSinceGameOver < 3000)
                {
                    explosionSound.play();
                }
                else if(!gameOver)
                {
                    explosionSound.play();
                }
            }
            TextureAtlas.AtlasRegion region = textureAtlas.findRegion("1");
            Sprite temp = new Sprite(region);
            game.batch.draw(temp, x,y,192,192);
        }
        else if(frame > 3 && frame <= 6)
        {
            TextureAtlas.AtlasRegion region = textureAtlas.findRegion("2");
            Sprite temp = new Sprite(region);
            game.batch.draw(temp, x,y,192,192);
        }
        else if(frame > 6 && frame <= 9)
        {
            TextureAtlas.AtlasRegion region = textureAtlas.findRegion("3");
            Sprite temp = new Sprite(region);
            game.batch.draw(temp, x,y,192,192);
        }
        else if(frame > 9 && frame <= 12)
        {
            TextureAtlas.AtlasRegion region = textureAtlas.findRegion("4");
            Sprite temp = new Sprite(region);
            game.batch.draw(temp, x,y,192,192);
        }
        else if(frame > 12 && frame <= 15)
        {
            TextureAtlas.AtlasRegion region = textureAtlas.findRegion("5");
            Sprite temp = new Sprite(region);
            game.batch.draw(temp, x,y,192,192);
        }
        else if(frame > 15 && frame <= 18)
        {
            TextureAtlas.AtlasRegion region = textureAtlas.findRegion("6");
            Sprite temp = new Sprite(region);
            game.batch.draw(temp, x,y,192,192);
        }
        else if(frame > 18 && frame <= 21)
        {
            TextureAtlas.AtlasRegion region = textureAtlas.findRegion("7");
            Sprite temp = new Sprite(region);
            game.batch.draw(temp, x,y,192,192);
        }
        else if(frame > 21 && frame <= 24)
        {
            TextureAtlas.AtlasRegion region = textureAtlas.findRegion("8");
            Sprite temp = new Sprite(region);
            game.batch.draw(temp, x,y,192,192);
        }
        game.batch.end();
        frame++;
    }
}

