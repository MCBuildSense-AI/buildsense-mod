package com.buildsense;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BuildSenseMod implements ModInitializer {
	public static final String MOD_ID = "buildsense";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("BuildSense AI loaded. First Fabric entrypoint is working.");
	}
}