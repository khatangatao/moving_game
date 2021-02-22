package com.khatangatao.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.khatangatao.game.Moving;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = Moving.HEIGHT;
		config.width = Moving.WIDTH;
		config.title = Moving.TITLE;

		new LwjglApplication(new Moving(), config);
	}
}
