package com.mygdx.game;

public class Block {
    protected BlockState state;
    //TODO: Change Tile image
    public Block() {
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
                //Do Nothing
                break;
        }
    }

    public boolean repair() {
        //We can only repair destroyed blocks
        if (state == BlockState.DESTROYED) {
            state = BlockState.REPAIRED;
            return true;
        } else {
            return false;
        }
    }
}
