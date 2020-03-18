package cn.mmf.slashblade_addon.specialattack;

import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.named.event.LoadEvent.InitEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SAloader {
	@SubscribeEvent
	public void InitSA(InitEvent event){
		ItemSlashBlade.specialAttacks.put(20, new OverSlash());
		ItemSlashBlade.specialAttacks.put(30, new RapidPhantomSwords());
		ItemSlashBlade.specialAttacks.put(31, new SpiralEdge());
		ItemSlashBlade.specialAttacks.put(32, new GalePhantomSwords());
		ItemSlashBlade.specialAttacks.put(35, new None());
		ItemSlashBlade.specialAttacks.put(36, new AquaEdge());
		ItemSlashBlade.specialAttacks.put(37, new FlareSpiral());
		ItemSlashBlade.specialAttacks.put(38, new LightningSwords());
		ItemSlashBlade.specialAttacks.put(39, new TerraSwords());
	}
}
