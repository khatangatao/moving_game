package com.khatangatao.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    final Moving game;

    Texture playerImage;
    Music music;

    OrthographicCamera camera;
    Rectangle player;

    public GameScreen(Moving game) {
        this.game = game;

        // Images
        playerImage = new Texture(Gdx.files.internal("player.png"));

        //Music and sounds
        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));

        //Camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        //Create a Rectangle to logically represent the bucket
        player = new Rectangle();
        player.x = camera.viewportWidth / 2 - playerImage.getWidth() / 2;
        player.y = 20;
        player.width = playerImage.getWidth();
        player.height = playerImage.getHeight();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // clear the screen with a dark blue color. The
        // arguments to clear are the red, green
        // blue and alpha component in the range [0,1]
        // of the color to be used to clear the screen.
        ScreenUtils.clear(0, 0, 0.2f, 1);

        // tell the camera to update its matrices
        camera.update();

        //tell the SpriteBatch to render in the coordinate system specified by the camera
        game.batch.setProjectionMatrix(camera.combined);

        //begin a new batch and draw the player
        game.batch.begin();
        game.font.draw(game.batch, "My point will be here", 0, camera.viewportHeight);
        game.batch.draw(playerImage, player.x, player.y);
        game.batch.end();

        //process user input

    }

    @Override
    public void resize(int width, int height) {

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

    }
}
