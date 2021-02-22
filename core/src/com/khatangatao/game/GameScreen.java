package com.khatangatao.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    final Moving game;

    Texture playerImage;
    Music music;

    OrthographicCamera camera;
    Rectangle player;

    //For developent
    long javaHeap;
    long nativeHeap;

    public GameScreen(Moving game) {
        this.game = game;

        // Images
        playerImage = new Texture(Gdx.files.internal("pacman.png"));

        //Music and sounds
        music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
        music.setLooping(true);

        //Camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        //Create a Rectangle to logically represent the player
        player = new Rectangle();
        player.x = camera.viewportWidth / 2 - playerImage.getWidth() / 2;
        player.y = 20;
        player.width = playerImage.getWidth();
        player.height = playerImage.getHeight();

        //Development
        javaHeap = Gdx.app.getJavaHeap();
        nativeHeap = Gdx.app.getNativeHeap();
    }

    @Override
    public void show() {
        // start the playback of the background music
        // when the screen is shown
        music.play();
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

        //Text info
        game.font.draw(game.batch, "My point will be here", 0, camera.viewportHeight);
        game.font.draw(game.batch, "JavaHeap: " + javaHeap, 0, camera.viewportHeight - 50);
        game.font.draw(game.batch, "NativeHeap: " + nativeHeap, 0, camera.viewportHeight - 70);


        game.batch.draw(playerImage, player.x, player.y);
        game.batch.end();

        //process user input
        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            player.x = touchPos.x - player.getWidth() / 2;
            player.y = touchPos.y - player.getHeight() / 2;
        }

        if (Gdx.input.isKeyPressed(Keys.UP)) {
            player.y += 200 * Gdx.graphics.getDeltaTime();

        }

        if (Gdx.input.isKeyPressed(Keys.DOWN)) {
            player.y -= 200 * Gdx.graphics.getDeltaTime();
        }

        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            player.x -= 200 * Gdx.graphics.getDeltaTime();
        }

        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            player.x += 200 * Gdx.graphics.getDeltaTime();
        }

        // make sure the player stays within the screen bounds
        if (player.x < 0) {
            player.x = 0;
        }

        if (player.x > camera.viewportWidth - player.getWidth()) {
            player.x = camera.viewportWidth - player.getWidth();
        }

        if (player.y < 0) {
            player.y = 0;
        }

        if (player.y > camera.viewportHeight - player.getHeight()) {
            player.y = camera.viewportHeight - player.getHeight();
        }

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
        game.dispose();
        playerImage.dispose();
        music.dispose();
    }
}
