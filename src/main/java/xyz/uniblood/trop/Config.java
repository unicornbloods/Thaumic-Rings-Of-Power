package xyz.uniblood.trop;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    public static boolean greatRingPermanentWarp = false;

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        greatRingPermanentWarp = configuration.getBoolean("One Ring adds Permanent Warp", Configuration.CATEGORY_GENERAL, greatRingPermanentWarp, "This setting can cause warp levels to become unmanageable over time.");

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
