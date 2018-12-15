package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public abstract class Duck {
    protected DuckType type;
    protected DuckState state;
    protected TextureAtlas textureAtlas = new TextureAtlas(Gdx.files.internal("dynoducks.atlas"));
    protected long throwTimerAnimation;
    protected long throwTimerAction;


    public Duck(DuckType type)
    {
        this.type = type;
        state = DuckState.STATIONARY;

    }
    public void moveRight()
    {

    }

    public void moveLeft()
    {

    }

}
