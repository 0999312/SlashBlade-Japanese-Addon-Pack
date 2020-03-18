package cn.mmf.slashblade_addon.recipes;

import cn.mmf.slashblade_addon.item.ItemSlashBladeRF;
import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RecipeRestartUserRF extends RecipeAwakeBlade{

	public RecipeRestartUserRF(ResourceLocation loc, ItemStack result, ItemStack requiredStateBlade, Object[] recipe) {
		super(loc, result, requiredStateBlade, recipe);
	}
	@Override
	public ItemStack getCraftingResult(InventoryCrafting arg0) {
		   ItemStack stack = null;
		    for (int idx = 0; idx < arg0.getSizeInventory(); idx++)
		    {
		      ItemStack curIs = arg0.getStackInSlot(idx);
		      if ((curIs != null) && ((curIs.getItem() instanceof ItemSlashBlade)) && (curIs.hasTagCompound())) {
		        stack = curIs.copy();
		      }
		    }
		    if (stack != null) {
		      ItemSlashBladeRF.Username.remove(ItemSlashBlade.getItemTagCompound(stack));
		    }
		    return stack;
		  
	}
}
