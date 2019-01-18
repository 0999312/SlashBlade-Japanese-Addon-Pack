package cn.mmf.slashblade_addon.advancements;

import net.minecraft.advancements.CriteriaTriggers;

public class SlashBladeAdvancements {
	public static final SlashBladeCreateTrigger SlashBlade_Create = CriteriaTriggers.register(new SlashBladeCreateTrigger());
	
	public static void init() {}
}
