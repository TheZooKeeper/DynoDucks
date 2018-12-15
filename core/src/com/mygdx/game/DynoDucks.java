package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
	//This is our main Start things here
public class DynoDucks extends Game{
	public SpriteBatch batch;
	public BitmapFont font;


	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new IntroScreen(this));
	}

	@Override
	public void render () {
		super.render(); //important!
	}

	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}
}
