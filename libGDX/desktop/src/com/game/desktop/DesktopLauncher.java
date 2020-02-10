package com.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.game.escapeGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "EscapeGame";
		config.width = 1920;
		config.height = 1080;
		config.resizable = false;
		config.fullscreen = true;
		config.addIcon("murr.jpg", Files.FileType.Internal);
		new LwjglApplication(new escapeGame(), config);
	}
}
