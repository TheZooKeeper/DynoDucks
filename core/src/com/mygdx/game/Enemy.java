package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Enemy extends  Duck
{
    // false means facing left, true means facing right
    boolean direction;
    // frame counter for walking animation
    int currentFrame;
    // a new dynamite the enemy may or may not have created
    Dynamite newDynamite;

    public Enemy()
    {
        super(DuckType.ENEMY);
        xCoord = 738;
        direction = false;
    }

    // move method is basically the AI. Depending on certain conditions the enemy should be
    // in a certain state
    public void move()
    {
        // check to see how Player move works before attempting this
        // Once the duck is in the Throw state, keep it in the throw state for at least .25 seconds
        // Once the duck is in the rebuilding state, keep it in that state for at least 1 second
        // This is so the the duck can't act too soon out of the animations
        if(state != DuckState.REBUILDING && state != DuckState.THROWING && state!= DuckState.DEAD)
        {
            state = DuckState.MOVING;
            if(direction == false )
            {
                if(xCoord > 64)
                {
                    xCoord -= 8;
                }
                else
                {
                    direction = true;
                    xCoord += 8;
                }
            }
            else
            {
                if(xCoord < 1460)
                {
                    xCoord += 8;
                }
                else
                {
                    direction = false;
                    xCoord -= 8;
                }
            }
        }
        else if(state == DuckState.DEAD)
        {
            // do nothing if duck is dead (stay still but be dead)
        }
        // if no input AND duck isn't dead, it must be stationary
        else
        {
            state = DuckState.STATIONARY;
        }

    }
    public void draw(DynoDucks game)
    {
        game.batch.begin();
        int yCoord = 800;

        if(state == DuckState.MOVING)
        {
            // walking animation part 1
            if(currentFrame < 5)
            {
                TextureAtlas.AtlasRegion region = textureAtlas.findRegion("duck_walk_1_red");
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
                TextureAtlas.AtlasRegion region = textureAtlas.findRegion("duck_walk_2_red");

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
        // Stationary animation
        else if(state == DuckState.STATIONARY)
        {
            TextureAtlas.AtlasRegion region = textureAtlas.findRegion("duck_walk_1_red");
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
            TextureAtlas.AtlasRegion region = textureAtlas.findRegion("duck_throw_red");
            Sprite temp = new Sprite(region);
            // mirror depending on direction
            if(direction  == true)
            {
                temp.flip(true, false);
            }
            game.batch.draw(temp, xCoord,yCoord,64,96);
            currentFrame = 0;
        }
        // rebuilding animation
        else if (state == DuckState.REBUILDING)
        {
            // Rebuilding animation part 1
            if(currentFrame < 10)
            {
                TextureAtlas.AtlasRegion region = textureAtlas.findRegion("duck_mallet_up_red");
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
            else if ((currentFrame < 20 && currentFrame > 10))
            {
                TextureAtlas.AtlasRegion region = textureAtlas.findRegion("duck_mallet_middle_red");

                Sprite temp = new Sprite(region);
                if(direction  == true)
                {
                    temp.flip(true, false);
                }
                game.batch.draw(temp, xCoord,yCoord,64,96);
                currentFrame++;
            }
            else if((currentFrame < 30 && currentFrame > 20))
            {
                TextureAtlas.AtlasRegion region = textureAtlas.findRegion("duck_mallet_down_red");

                Sprite temp = new Sprite(region);
                if(direction  == true)
                {
                    temp.flip(true, false);
                }
                game.batch.draw(temp, xCoord,yCoord,64,96);
                currentFrame++;
                if(currentFrame > 30)
                {
                    currentFrame = 0;
                }
            }
        }
        // dead animation
        else if(state == DuckState.DEAD)
        {
            TextureAtlas.AtlasRegion region = textureAtlas.findRegion("duck_dead_red");
            Sprite temp = new Sprite(region);
            // mirror depending on direction
            if(direction  == true)
            {
                temp.flip(true, false);
            }
            game.batch.draw(temp, xCoord,yCoord,64,96);
            currentFrame = 0;
        }

        game.batch.end();
    }

}
