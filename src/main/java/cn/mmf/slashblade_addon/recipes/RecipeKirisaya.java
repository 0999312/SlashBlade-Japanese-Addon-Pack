package cn.mmf.slashblade_addon.recipes;

import mods.flammpfeil.slashblade.RecipeAwakeBlade;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class RecipeKirisaya extends RecipeAwakeBlade{
	private final ItemStack sphere;

	public RecipeKirisaya(ResourceLocation group,ItemStack result,ItemStack requiredStateBlade,ItemStack reqiredSphere,Object... recipe){
		super(group, result, requiredStateBlade, recipe);
		this.sphere = reqiredSphere;
	}

    @Override
	public boolean matches(InventoryCrafting inv, World world)
	{
		if (this.sphere == null) return false;

		boolean result = super.matches(inv, world);
		if (!result) return false;

		for (int i = 0; i < inv.getSizeInventory(); i++) {
			ItemStack current = inv.getStackInSlot(i);
			if (!current.isItemEqual(this.sphere)) continue;
			int requiredsa = ItemSlashBlade.SpecialAttackType.get(this.sphere.getTagCompound());
			int currentsa = ItemSlashBlade.SpecialAttackType.get(current.getTagCompound());
			if (requiredsa != currentsa) return false;

		}
		return true;
	}
}
