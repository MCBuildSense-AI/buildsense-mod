package com.buildsense;

import com.buildsense.command.BuildSenseCommands;
import com.buildsense.config.BuildSenseConfig;
import com.buildsense.config.ConfigLoader;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BuildSenseMod implements ModInitializer {
	public static final String MOD_ID = "buildsense";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static BuildSenseConfig CONFIG;

	@Override
	public void onInitialize() {
		LOGGER.info("BuildSense AI loaded. First Fabric entrypoint is working.");
		CONFIG = ConfigLoader.loadOrCreate();
		BuildSenseCommands.register();
	}
}