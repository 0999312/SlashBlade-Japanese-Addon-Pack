package cn.mmf.slashblade_addon;

import org.apache.logging.log4j.Logger;

import net.minecraft.client.resources.I18n;
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

    public ConfigLoader(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        config = new Configuration(event.getSuggestedConfigurationFile());
        
        switch_Nihil = config.getBoolean("Switch Nihil Addon", Configuration.CATEGORY_GENERAL, true, I18n.format("config.switch.nihil.text"));
        switch_DarkRaven = config.getBoolean("Switch DarkRaven Addon", Configuration.CATEGORY_GENERAL, true, I18n.format("config.switch.darkraven.text"));
        switch_WA = config.getBoolean("Switch WA Addon", Configuration.CATEGORY_GENERAL, true, I18n.format("config.switch.WA.text"));
        switch_Toyako = config.getBoolean("Switch Toyako Addon", Configuration.CATEGORY_GENERAL, true, I18n.format("config.switch.toyako.text"));
        switch_FluorescentBar = config.getBoolean("Switch FluorescentBar Addon", Configuration.CATEGORY_GENERAL, true, I18n.format("config.switch.fluorescentbar.text"));
        switch_Yukari = config.getBoolean("Switch Yukari Addon", Configuration.CATEGORY_GENERAL, true, I18n.format("config.switch.yukari.text"));
        switch_Kamuy = config.getBoolean("Switch Kamuy Addon", Configuration.CATEGORY_GENERAL, true, I18n.format("config.switch.kamuy.text"));
        switch_Kirisaya = config.getBoolean("Switch Kirisaya Addon", Configuration.CATEGORY_GENERAL, true, I18n.format("config.switch.kirisaya.text"));
        switch_FrostWolf = config.getBoolean("Switch FrostWolf Addon", Configuration.CATEGORY_GENERAL, true, I18n.format("config.switch.frostwolf.text"));
        switch_Laemmle = config.getBoolean("Switch Laemmle Addon", Configuration.CATEGORY_GENERAL, true, I18n.format("config.switch.laemmle.text"));
        switch_BladeMaster = config.getBoolean("Switch BladeMaster Addon", Configuration.CATEGORY_GENERAL, true, I18n.format("config.switch.blademaster.text"));
        switch_MURASAMA = config.getBoolean("Switch MURASAMA Addon", Configuration.CATEGORY_GENERAL, true, I18n.format("config.switch.MURASAMA.text"));
        switch_Terra = config.getBoolean("Switch TerraBlade Addon", Configuration.CATEGORY_GENERAL, true, I18n.format("config.switch.terra.text"));
        switch_Wanderer = config.getBoolean("Switch Wanderer Addon", Configuration.CATEGORY_GENERAL, true, I18n.format("config.switch.wanderer.text"));
        switch_Zephyr = config.getBoolean("Switch BladeofZephyr Addon", Configuration.CATEGORY_GENERAL, true, I18n.format("config.switch.zephyr.text"));
        
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
