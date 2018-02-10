package ru.itl.kpfu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Level {

    Character c;

    public Level() {
        c = new Character();
    }

    public void update(float delta) {
        c.update(delta);
    }

    public void render(SpriteBatch batch) {
        batch.begin();
        c.render(batch);
        batch.end();
    }
}
