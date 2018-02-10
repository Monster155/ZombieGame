package ru.itl.kpfu;

import com.badlogic.gdx.Game;

public class MainActivity extends Game {
    @Override
    public void create() {
        setScreen(new GameScreen());
    }

}
