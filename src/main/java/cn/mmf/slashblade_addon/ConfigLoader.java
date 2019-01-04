package cn.mmf.slashblade_addon;

import org.apache.logging.log4j.Logger;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ConfigLoader {
	private static Configuration config;

    private static Logger logger;
    
    public static boolean switch_Nihil;
    public static boolean switch_DarkRaven;
    public static boolean switch_WA;
    public static boolean switch_Toyako;
    public static boolean switch_FluorescentBar;
    public static boolean switch_Yukari;
    public static boolean switch_Kamuy;
    public static boolean switch_Kirisaya;
    public static boolean switch_FrostWolf;
    public static boolean switch_Laemmle;
    public static boolean switch_BladeMaster;
    public static boolean switch_MURASAMA;
    public static boolean switch_Terra;
    public static boolean switch_Wanderer;
    public static boolean switch_Zephyr;
    public static boolean switch_CS2;
    public static boolean switch_Bamboo;
    public ConfigLoader(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        config = new Configuration(event.getSuggestedConfigurationFile());
        
        switch_Nihil = config.getBoolean("Switch Nihil Addon", Configuration.CATEGORY_GENERAL, true,"");
        switch_DarkRaven = config.getBoolean("Switch DarkRaven Addon", Configuration.CATEGORY_GENERAL, true,"");
        switch_WA = config.getBoolean("Switch WA Addon", Configuration.CATEGORY_GENERAL, true,"");
        switch_Toyako = config.getBoolean("Switch Toyako Addon", Configuration.CATEGORY_GENERAL, true,"");
        switch_FluorescentBar = config.getBoolean("Switch FluorescentBar Addon", Configuration.CATEGORY_GENERAL, true,"");
        switch_Yukari = config.getBoolean("Switch Yukari Addon", Configuration.CATEGORY_GENERAL, true,"");
        switch_Kamuy = config.getBoolean("Switch Kamuy Addon", Configuration.CATEGORY_GENERAL, true,"");
        switch_Kirisaya = config.getBoolean("Switch Kirisaya Addon", Configuration.CATEGORY_GENERAL,true,"");
        switch_FrostWolf = config.getBoolean("Switch FrostWolf Addon", Configuration.CATEGORY_GENERAL, true, "");
        switch_Laemmle = config.getBoolean("Switch Laemmle Addon", Configuration.CATEGORY_GENERAL, true,"");
        switch_BladeMaster = config.getBoolean("Switch BladeMaster Addon", Configuration.CATEGORY_GENERAL, true, "");
        switch_MURASAMA = config.getBoolean("Switch MURASAMA Addon", Configuration.CATEGORY_GENERAL, true,"");
        switch_Terra = config.getBoolean("Switch TerraBlade Addon", Configuration.CATEGORY_GENERAL, true, "");
        switch_Wanderer = config.getBoolean("Switch Wanderer Addon", Configuration.CATEGORY_GENERAL, true, "");
        switch_Zephyr = config.getBoolean("Switch BladeofZephyr Addon", Configuration.CATEGORY_GENERAL, true, "");
        switch_CS2 = config.getBoolean("Switch CS2 Template Addon", Configuration.CATEGORY_GENERAL, true, "");
        switch_Bamboo = config.getBoolean("Switch Bamboo Compat Addon", Configuration.CATEGORY_GENERAL, true,"");
        
        config.load();
        load();
    }

    public static void load()
    {
        logger.info("Started loading config. ");
        
        
        
        config.save();
        logger.info("Finished loading config. ");
    }

    public static Logger logger()
    {
        return logger;
    }

}
