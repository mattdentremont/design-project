package com.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.game.escapeGame;

public class DesktopLauncher {
	public static void main (String[] arg) {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Dungeon Escape";
		config.width = 1920;
		config.height=1080;
		config.fullscreen = false;
		config.addIcon("jamilIcon.jpg", Files.FileType.Internal);
		new LwjglApplication(new escapeGame(), config);
	}
}
