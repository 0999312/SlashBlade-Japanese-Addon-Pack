package cn.mmf.slashblade_addon;

import cn.mmf.slashblade_addon.advancements.SlashBladeAdvancements;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

public class EventLoader {
	public EventLoader()
    {
        MinecraftForge.EVENT_BUS.register(this);
    }
	
	@SubscribeEvent
	public void onSlashBladeCreate(ItemCraftedEvent event) {
		World worldIn = event.player.world;
		if(!worldIn.isRemote){
			if(event.craftMatrix instanceof InventoryCrafting){
				IRecipe recipe = CraftingManager.findMatchingRecipe((InventoryCrafting) event.craftMatrix, worldIn);
				if(event.crafting.getItem() instanceof ItemSlashBlade)SlashBladeAdvancements.SlashBlade_Create.trigger((EntityPlayerMP)event.player, recipe);
			}
		}
	}

	
}
