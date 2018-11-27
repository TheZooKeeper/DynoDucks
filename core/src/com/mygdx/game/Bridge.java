package com.mygdx.game;

public class Bridge {
    public Block[] bridgeArr;
    int locationDivider;
    int sideBuffer = 32; //There is a 32 pixel side buffer

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
}
