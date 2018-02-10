package ru.itl.kpfu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;

public class ChaseCam {

    Camera camera;
    Character c;

    public ChaseCam(Camera camera, Character c) {
        this.c = c;
        this.camera = camera;
    }

    public void update() {
        camera.position.x = c.getPosition().x;
        camera.position.y = c.getPosition().y;
    }
}