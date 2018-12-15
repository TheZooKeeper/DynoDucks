package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.awt.Rectangle;

public class Block
{
    protected BlockState state;
    //TODO: Change Tile image


    public Block()
    {

        state = BlockState.NEW;
    }

    public void hit() {
        switch (state) {
            case NEW:
                state = BlockState.HIT;
                break;
            case HIT:
                state = BlockState.DESTROYED;
                break;
            case REPAIRED:
                state = BlockState.DESTROYED;
                break;
            case DESTROYED:
                break;
        }
    }

    public boolean repair() {
        //We can only repair destroyed blocks
        if (state == BlockState.DESTROYED) {
            state = BlockState.REPAIRED;
           //image = REPAIRED;
            return true;
        } else {
            return false;
        }
    }
}
