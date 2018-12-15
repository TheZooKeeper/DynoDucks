package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

import java.awt.Point;
import java.util.ArrayList;

public class Dynamite extends Rectangle {

    Duck duck;

    int xCoord;
    int yCoord;

    int rotationAngle;
    boolean exploding;

    private Texture dynamite = new Texture(Gdx.files.internal("Dynamite (Transparent).png"));

    public Dynamite(Duck thrower, int xCoord)
    {
        duck = thrower;
        // set a spawn x and y coordinate for the dynamite depending on whether the player or enemy threw it
        this.xCoord = xCoord;
        if(duck.type == DuckType.PLAYER)
        {
            yCoord = 120;
        }
        else
        {
            yCoord = 750;
        }

        // rotation angle variable for visual effect
        rotationAngle = 0;
    }

    // not useful lol
    public boolean shouldExplode() {
        if ((getY() > Globals.RESOLUTION_HEIGHT - 192 && duck.type == DuckType.PLAYER) ||
                (getY() < 192 && duck.type == DuckType.ENEMY)) {
            return true;
        } else {
            return false;
        }
    }

    // whether or not a dynamite explosion has started
    public void explode()
    {
        exploding = true;
    }

    public void draw(DynoDucks game)
    {

        game.batch.begin();
        // if a dynamite is not exploding
        if(!exploding)
        {
            if(duck.type == DuckType.PLAYER)
            {
                // move it up if the play threw it
                yCoord += 5;
            }
            else
            {
                // move it down if the enemy threw it
                yCoord -= 5;
            }

            // create a sprite for the dynamite
            Sprite dynamiteSprite = new Sprite(dynamite);
            dynamiteSprite.setSize(64,64);
            dynamiteSprite.setPosition(xCoord,yCoord);
            dynamiteSprite.rotate(rotationAngle);
            // for some reason if this line isn't here the rotation effects the trajectory of the dynamite, idk
            dynamiteSprite.setOriginCenter();
            dynamiteSprite.draw(game.batch);
            // increase the rotation angle so the dynamite keeps spinning
            rotationAngle+= 3;
        }

        // if the dynamite is exploding, it shouldn't have a sprite
        else
        {

        }

        game.batch.end();
    }
}
