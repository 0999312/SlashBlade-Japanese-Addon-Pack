package cn.mmf.slashblade_addon.recipes;

import cn.mmf.slashblade_addon.item.ItemLoader;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class RecipeAwakeBladeRF extends RecipeAwakeBlade {

	public RecipeAwakeBladeRF(ResourceLocation loc, ItemStack result, ItemStack requiredStateBlade, Object[] recipe) {
		super(loc, result, requiredStateBlade, recipe);
	}

	@Override
	public boolean matches(InventoryCrafting inv, World world) {
		boolean result = super.matches(inv, world);
		if (result)
	    {
	      ItemStack fpnmCore = new ItemStack(ItemLoader.FPNCore);
	      
	      boolean findCore = false;
	      for (int i = inv.getSizeInventory(); 0 < i--;)
	      {
	        ItemStack stack = inv.getStackInSlot(i);
	        if ((stack != null) && 
	          (fpnmCore.isItemEqual(stack)))
	        {
	            break;
	        }
	      }
	      if (!findCore) {
	        result = false;
	      }
	    }
	    return result;
	  }
	}
	
