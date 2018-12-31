package cn.mmf.slashblade_addon.specialeffect;

import mods.flammpfeil.slashblade.specialeffect.ISpecialEffect;
import mods.flammpfeil.slashblade.specialeffect.SpecialEffects;
import net.minecraftforge.fml.common.Loader;

public class SELoader {
	public static ISpecialEffect BurstDrive;
	public static ISpecialEffect ManaBurst;
	public static ISpecialEffect ManaRepair;
	public SELoader() {
		BurstDrive = SpecialEffects.register(new BurstDrive());
		if(Loader.isModLoaded("botania")){
		ManaBurst = SpecialEffects.register(new ManaBurst());
		ManaRepair = SpecialEffects.register(new ManaRepair());}
	}
}
