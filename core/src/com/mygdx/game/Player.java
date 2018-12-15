package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Player extends  Duck
{

    int xCoord;
    // false means facing left, true means facing right
    boolean direction;
    // frame counter for walking animation
    int currentFrame;
    // a new dynamite the player may or may not have created
    Dynamite newDynamite;


    public Player()
    {
        super(DuckType.PLAYER);
        xCoord = 738;
        direction = false;

    }
    public void processMovement()
    {
        // set a new dynamite to null so it forgets anything thrown the previous pass
        newDynamite = null;

        // don't allow the player to move during the throw or repair actions, they are locked in
        // the animation
        if(state != DuckState.THROWING && state != DuckState.REBUILDING)
        {
            // d moves right until a certain point.
            // player always faces right while moving right
            if(Gdx.input.isKeyPressed(Input.Keys.D) && xCoord < 1460)
            {
                state = DuckState.MOVING;
                direction = true;
                xCoord+= 5;
            }
            // a moves left until a certain point
            // player always faces left while moving left
            else if(Gdx.input.isKeyPressed(Input.Keys.A) && xCoord > 64)
            {
                state = DuckState.MOVING;
                direction = false;
                xCoord-= 5;
            }
            // e throws a dynamite
            // must wait 1.5 seconds after the last dynamite thrown to throw
            else if(Gdx.input.isKeyPressed(Input.Keys.E) && state != DuckState.THROWING
                    && System.currentTimeMillis() - throwTimerAction > 1500)
            {
                state = DuckState.THROWING;
                // time player is locked into the throw animation
                throwTimerAnimation = System.currentTimeMillis();
                // time before another dynimate can be thrown
                throwTimerAction = System.currentTimeMillis();
                // the new dynamite created
                newDynamite = new Dynamite(this, xCoord);

            }
            // if no input, the player is stationary
            else
            {
                state = DuckState.STATIONARY;
                //currentFrame = 0;
            }
        }
        // if the player state is throwing, check to see if the animation lock timer is up
        else if(state == DuckState.THROWING)
        {
           if((System.currentTimeMillis() - throwTimerAnimation > 500))
           {
               state = DuckState.STATIONARY;
           }
        }
        // if no input, player is stationary
        else
        {
            state = DuckState.STATIONARY;
        }


    }
    public void draw(DynoDucks game)
    {
        game.batch.begin();
        int yCoord = 64;
        // if player is moving
        if(state == DuckState.MOVING)
        {
            // walking animation part 1
            if(currentFrame < 5)
            {
                TextureAtlas.AtlasRegion region = textureAtlas.findRegion("duck_walk");
                Sprite temp = new Sprite(region);
                // mirror image depending on direction
                if(direction  == true)
                {
                    temp.flip(true, false);
                }
                game.batch.draw(temp, xCoord,yCoord,64,96);
                currentFrame++;
            }
            // walking animation part 2
            else
            {
                TextureAtlas.AtlasRegion region = textureAtlas.findRegion("duck_walk_2");
                Sprite temp = new Sprite(region);
                if(direction  == true)
                {
                    temp.flip(true, false);
                }
                game.batch.draw(temp, xCoord,yCoord,64,96);
                currentFrame++;
                if(currentFrame > 10)
                {
                    currentFrame = 0;
                }
            }

        }
        // stationary animation
        else if(state == DuckState.STATIONARY)
        {
            TextureAtlas.AtlasRegion region = textureAtlas.findRegion("duck_walk");
            Sprite temp = new Sprite(region);
            // mirror depending on direction
            if(direction  == true)
            {
                temp.flip(true, false);
            }
            game.batch.draw(temp, xCoord,yCoord,64,96);
            currentFrame = 0;
        }
        // throwing animation
        else if(state == DuckState.THROWING)
        {
            TextureAtlas.AtlasRegion region = textureAtlas.findRegion("duck_throw");
            Sprite temp = new Sprite(region);
            // mirror depending on direction
            if(direction  == true)
            {
                temp.flip(true, false);
            }
            game.batch.draw(temp, xCoord,yCoord,64,96);
            currentFrame = 0;
        }

        // TODO add rebuilding animation
        // TODO add death animation
        game.batch.end();
    }
}
