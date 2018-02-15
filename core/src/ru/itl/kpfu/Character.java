package ru.itl.kpfu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

public class Character {

    public int lives;
    public Vector2 position = new Vector2(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);;
    TextureAtlas.AtlasRegion region;
    TextureAtlas atlas;
    public AssetManager assetManager;

    public Character(){
        assetManager = new AssetManager();
        assetManager.load(Constants.C_ATLAS, TextureAtlas.class);
        assetManager.finishLoading();
        atlas = assetManager.get(Constants.C_ATLAS);
        region = atlas.findRegion(Constants.C_TEXTURE);
    }

    public void update(float delta) {
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        boolean left = Gdx.input.isKeyPressed(Input.Keys.A);
        boolean right = Gdx.input.isKeyPressed(Input.Keys.D);
        boolean up = Gdx.input.isKeyPressed(Input.Keys.W);
        boolean down = Gdx.input.isKeyPressed(Input.Keys.S);
        if (left && down && !up && !right) {
            if(!(position.x-delta * Constants.C_SPEED / Math.sqrt(2) < 0))
                position.x -= delta * Constants.C_SPEED / Math.sqrt(2);
            if(!(position.y-delta * Constants.C_SPEED / Math.sqrt(2) < 0))
                position.y -= delta * Constants.C_SPEED / Math.sqrt(2);
        } else if (down && !left && !up && !right) {
            if(!(position.y-delta * Constants.C_SPEED < 0))
                position.y -= delta * Constants.C_SPEED;
        } else if (right && down && !up && !left) {
            if(!(position.x+Constants.C_SIZE.x+delta * Constants.C_SPEED / Math.sqrt(2) > w))
                position.x += delta * Constants.C_SPEED / Math.sqrt(2);
            if(!(position.y-delta * Constants.C_SPEED / Math.sqrt(2) < 0))
                position.y -= delta * Constants.C_SPEED / Math.sqrt(2);
        } else if (left && !right && !up && !down) {
            if(!(position.x-delta * Constants.C_SPEED < 0))
                position.x -= delta * Constants.C_SPEED;
        } else if (right && !left && !up && !down) {
            if(!(position.x+Constants.C_SIZE.x+delta * Constants.C_SPEED > w))
                position.x += delta * Constants.C_SPEED;
        } else if (left && up && !right && !down) {
            if (!(position.x - delta * Constants.C_SPEED / Math.sqrt(2) < 0))
                position.x -= delta * Constants.C_SPEED / Math.sqrt(2);
            if(!(position.y + Constants.C_SIZE.y + delta * Constants.C_SPEED / Math.sqrt(2) > h))
                position.y += delta * Constants.C_SPEED / Math.sqrt(2);
        } else if (up && !left && !right && !down) {
            if(!(position.y+Constants.C_SIZE.y+delta * Constants.C_SPEED > h))
                position.y += delta * Constants.C_SPEED;
        } else if (right && up && !left && !down) {
            if(!(position.x+Constants.C_SIZE.x+delta * Constants.C_SPEED / Math.sqrt(2) > w))
                position.x += delta * Constants.C_SPEED / Math.sqrt(2);
            if(!(position.y+Constants.C_SIZE.y+delta * Constants.C_SPEED / Math.sqrt(2) > h))
                position.y += delta * Constants.C_SPEED / Math.sqrt(2);
        }
        //Gdx.app.log("Position", "X:"+position.x+" Y:"+position.y);
    }

    public void render(SpriteBatch batch) {
        batch.draw(
                region.getTexture(),
                position.x,
                position.y,
                0,
                0,
                region.getRegionWidth(),
                region.getRegionHeight(),
                1,
                1,
                0,
                region.getRegionX(),
                region.getRegionY(),
                region.getRegionWidth(),
                region.getRegionHeight(),
                false,
                false);
    }

    public void damaged(int damage) {
        lives -= damage;
        if (lives < 0) dead();
    }

    public void heal(int heal) {
        lives += heal;
        if (lives > Constants.C_MAX_LIVES) lives = Constants.C_MAX_LIVES;
    }

    public void dead() {
        lives = 0;
    }
}
