package xyz.uniblood.trop;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    public static boolean greatRingPermanentWarp = false;
    public static int minimumGreatRingWarp = 3;
    public static int maximumGreatRingWarp = 30;
    public static int greatRingRunicShield = 60;
    public static int normalRingWarp = 3;

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        greatRingPermanentWarp = configuration.getBoolean("One Ring adds Permanent Warp", Configuration.CATEGORY_GENERAL, greatRingPermanentWarp, "This setting can cause warp levels to become unmanageable over time.");
        minimumGreatRingWarp = configuration.getInt("Minimum One Ring Warp", Configuration.CATEGORY_GENERAL, minimumGreatRingWarp, 0, 100, "Lowest value for The One Ring's Warp");
        maximumGreatRingWarp = configuration.getInt("Maximum One Ring Warp", Configuration.CATEGORY_GENERAL, maximumGreatRingWarp, 0, 100, "Highest value for The One Ring's Warp");
        greatRingRunicShield = configuration.getInt("One Ring Runic Shield", Configuration.CATEGORY_GENERAL, greatRingRunicShield, 0, 100, "Runic Shielding level for The One Ring");


        normalRingWarp = configuration.getInt("Ring Warp", Configuration.CATEGORY_GENERAL, normalRingWarp, 0, 100, "Warp value for rings other than The One Ring");

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
