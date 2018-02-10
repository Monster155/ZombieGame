package ru.itl.kpfu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

public class Character {

    public int lives;
    public Vector2 position = new Vector2(0,0);;
    TextureAtlas.AtlasRegion region;
    TextureAtlas atlas;
    public AssetManager assetManager;

    public void Character(){}

    public void update(float delta) {
        boolean left = Gdx.input.isKeyPressed(Input.Keys.A);
        boolean right = Gdx.input.isKeyPressed(Input.Keys.D);
        boolean up = Gdx.input.isKeyPressed(Input.Keys.W);
        boolean down = Gdx.input.isKeyPressed(Input.Keys.S);
        if (left && down && !up && !right) {
            move(delta, 1);
        } else if (down && !left && !up && !right) {
            move(delta, 2);
        } else if (right && down && !up && !left) {
            move(delta, 3);
        } else if (left && !right && !up && !down) {
            move(delta, 4);
        } else if (right && !left && !up && !down) {
            move(delta, 6);
        } else if (left && up && !right && !down) {
            move(delta, 7);
        } else if (up && !left && !right && !down) {
            move(delta, 8);
        } else if (right && up && !left && !down) {
            move(delta, 9);
        }
    }

    private void move(float delta, int state) {
        switch (state) {
            case 1:
                position.x -= delta * Constants.C_SPEED / Math.sqrt(2);
                position.y -= delta * Constants.C_SPEED / Math.sqrt(2);
                break;
            case 2:
                position.y -= delta * Constants.C_SPEED;
                break;
            case 3:
                position.x += delta * Constants.C_SPEED / Math.sqrt(2);
                position.y -= delta * Constants.C_SPEED / Math.sqrt(2);
                break;
            case 4:
                position.x -= delta * Constants.C_SPEED;
                break;
            case 6:
                position.x += delta * Constants.C_SPEED;
                break;
            case 7:
                position.x -= delta * Constants.C_SPEED / Math.sqrt(2);
                position.y += delta * Constants.C_SPEED / Math.sqrt(2);
                break;
            case 8:
                position.y += delta * Constants.C_SPEED;
                break;
            case 9:
                position.x += delta * Constants.C_SPEED / Math.sqrt(2);
                position.y += delta * Constants.C_SPEED / Math.sqrt(2);
                Gdx.app.log("Position", "X:"+position.x+" Y:"+position.y);
                break;
        }
    }

    public void render(SpriteBatch batch) {
        assetManager = new AssetManager();
        assetManager.load(Constants.C_ATLAS, TextureAtlas.class);
        assetManager.finishLoading();
        atlas = assetManager.get(Constants.C_ATLAS);
        region = atlas.findRegion(Constants.C_TEXTURE);
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
