package com.mygdx.game;

import com.badlogic.gdx.Screen;
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
    // tells the Ai whether it should choose an action
    boolean shouldChooseAction;
    //Locks the action of the duck to stationary
    boolean actionLock;
    long lastThrow;
    MainGameScreen game;

    public Enemy(MainGameScreen game)
    {
        super(DuckType.ENEMY);
        xCoord = 738;
        direction = false;
        shouldChooseAction = true;
        actionLock = false;
        this.game = game;
    }

    // move method is basically the AI. Depending on certain conditions the enemy should be
    // in a certain state
    public void move()
    {
        // check to see how Player move works before attempting this
        // Once the duck is in the Throw state, keep it in the throw state for at least .25 seconds
        // Once the duck is in the rebuilding state, keep it in that state for at least 1 second
        // This is so the the duck can't act too soon out of the animations

        /*
        1st check if there is a lock
        2nd make a decision for the action to be preformed
        3rd do action
         */

        //Is action locked because of throw or rebuild
        if(actionLock)
        {
            if((state == DuckState.THROWING && System.currentTimeMillis() - throwTimerAction > 1500)
                    || (state == DuckState.REBUILDING && System.currentTimeMillis() - throwTimerAction > 1500))
            {
                //We should open the lock
                actionLock = false;
            }
            else {
                //We don't want to do anything move related so return
                return;
            }
        }
        //Let's make a decision for the duck
        if(shouldChooseAction)
        {
            //Enemy duck should never stop moving. unless rebuilding or throwing
            //Let's check if we have the option to rebuild
            if(game.eBridge.bridgeArr[game.getClosestBlock(this)].state == BlockState.HIT ||
                    game.eBridge.bridgeArr[game.getClosestBlock(this)].state == BlockState.DESTROYED)
            {
                //TODO: Add Logic
                state = DuckState.REBUILDING;
            }
            else
            {
                if(System.currentTimeMillis() - lastThrow > 2500)
                {
                    state = DuckState.THROWING;
                }
                else
                {
                    state = DuckState.MOVING;
                }
            }
        }

        if(state == DuckState.MOVING)
        {
            if(!direction)
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
        else if(state == DuckState.REBUILDING)
        {
            // time player is locked into the throw animation
            //rebuildTimerAnimation = System.currentTimeMillis();
            // time before another dynamite can be thrown
            rebuildTimerAction = System.currentTimeMillis();
            actionLock = true;
        }
        else if(state == DuckState.THROWING)
        {
                // time player is locked into the throw animation
                throwTimerAnimation = System.currentTimeMillis();
                // time before another dynamite can be thrown
                throwTimerAction = System.currentTimeMillis();
                // the new dynamite created
                newDynamite = new Dynamite(this, xCoord);
                actionLock = true;
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
                if(direction)
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
                if(direction)
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
            if(direction)
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
            if(direction)
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
                if(direction)
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
                if(direction)
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
                if(direction)
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
            if(direction)
            {
                temp.flip(true, false);
            }
            game.batch.draw(temp, xCoord,yCoord,64,96);
            currentFrame = 0;
        }

        game.batch.end();
    }

}
