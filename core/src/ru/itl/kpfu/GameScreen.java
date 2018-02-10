package ru.itl.kpfu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

public class GameScreen implements Screen {

    Level level;
    SpriteBatch batch;
    ChaseCam chaseCam;
    ExtendViewport viewport;
    Character c;

    @Override
    public void show() {
        level = new Level();
        batch = new SpriteBatch();
        c = new Character();
        viewport = new ExtendViewport(Constants.WORLD_SIZE, Constants.WORLD_SIZE);
        chaseCam = new ChaseCam(viewport.getCamera(), c);
    }

    @Override
    public void render(float delta) {
        Gdx.app.log("Position", "X:"+c.getPosition().x+" Y:"+c.getPosition().y);
        level.update(delta);
        chaseCam.update();
        viewport.apply();
        Gdx.gl20.glClearColor(Constants.BLUE.r, Constants.BLUE.g, Constants.BLUE.b, Constants.BLUE.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(viewport.getCamera().combined);
        level.render(batch);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        c.assetManager.dispose();
    }
}
