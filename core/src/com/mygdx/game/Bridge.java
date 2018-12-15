package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Bridge {
    public Block[] bridgeArr;
    int locationDivider;
    int sideBuffer = 32; //There is a 32 pixel side buffer

    // all possible textures for different states
    private Texture NEW = new Texture(Gdx.files.internal("Block - New.png"));
    private Texture HIT = new Texture(Gdx.files.internal("Block - HIT.png"));
    private Texture REPAIRED = new Texture(Gdx.files.internal("Block - REBUILT.png"));

    public Bridge(int numBlocks){
        bridgeArr = new Block[numBlocks];
        for (int i = 0; i < bridgeArr.length;i++) {
            bridgeArr[i] = new Block();
        }
        locationDivider = (Globals.RESOLUTION_WIDTH - (sideBuffer*2)) / numBlocks;
    }

    public void hit(int yCoor){
        //check whether the hit was on bridge
        if(yCoor > sideBuffer && yCoor < Globals.RESOLUTION_WIDTH - sideBuffer ) {
            //hit piece
            bridgeArr[((yCoor - sideBuffer) / locationDivider)].hit();
        }
    }
    public boolean repair(int yCoor){
        //We do not to check if the duck is on a tile as the ducks can not stand on buffer tiles
        if(bridgeArr[((yCoor-sideBuffer)/locationDivider)].repair()) {
            return true;
        }else{
            return false;
        }
    }

    // draw the bridge
    public void draw(DynoDucks game, DuckType type)
    {
        int yCoord;
        // draw at a different y coordinate depending on the type of bridge
        if(type == DuckType.PLAYER)
        {
            yCoord = 32;
        }
        else
        {
            yCoord = 736;
        }
        int xCoord = 32;
        game.batch.begin();
        // for each block in the bridge
        for(Block block : bridgeArr)
        {
            // check its state
            if(block.state == BlockState.NEW)
            {
                // draw with the appropriate texture
                Sprite temp = new Sprite(NEW);
                game.batch.draw(temp, xCoord, yCoord,192, 192);
                xCoord += 192;
            }
            else if(block.state == BlockState.HIT)
            {
                Sprite temp = new Sprite(HIT);
                game.batch.draw(temp, xCoord, yCoord,192, 192);
                xCoord += 192;
            }
            else if(block.state == BlockState.DESTROYED)
            {
               // destroyed blocks don't have a texture
                xCoord += 192;
            }
            else if(block.state == BlockState.REPAIRED)
            {
                Sprite temp = new Sprite(REPAIRED);
                game.batch.draw(temp, xCoord, yCoord,192, 192);
                xCoord += 192;
            }

        }
        game.batch.end();

    }
}
