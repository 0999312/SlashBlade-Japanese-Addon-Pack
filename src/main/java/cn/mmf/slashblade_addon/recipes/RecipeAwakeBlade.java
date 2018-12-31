package cn.mmf.slashblade_addon.recipes;

import java.util.Map;

import mods.flammpfeil.slashblade.TagPropertyAccessor;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class RecipeAwakeBlade extends mods.flammpfeil.slashblade.RecipeAwakeBlade {
    ItemStack requiredStateBlade = ItemStack.EMPTY;
    
	public RecipeAwakeBlade(ResourceLocation loc, ItemStack result, ItemStack requiredStateBlade, Object[] recipe) {
		super(loc, result, requiredStateBlade, recipe);
		
	}
    int tagValueCompare1(TagPropertyAccessor access, NBTTagCompound reqTag, NBTTagCompound srcTag){
        return access.get(reqTag).compareTo(access.get(srcTag));
    }
    @Override
    public boolean matches(InventoryCrafting inv, World world) {

        boolean result = super.matches(inv, world);

        if(result && !requiredStateBlade.isEmpty()){
        	requiredStateBlade.setItemDamage(Short.MAX_VALUE);
            for(int idx = 0; idx < inv.getSizeInventory(); idx++){
                ItemStack curIs = inv.getStackInSlot(idx);
                if(!curIs.isEmpty()
                        && curIs.getItem() instanceof ItemSlashBlade
                        && curIs.hasTagCompound()){
                	
                    Map<Enchantment,Integer> oldItemEnchants = EnchantmentHelper.getEnchantments(requiredStateBlade);
                    for(Map.Entry<Enchantment,Integer> enchant: oldItemEnchants.entrySet())
                    {
                        int level = EnchantmentHelper.getEnchantmentLevel(enchant.getKey(),curIs);
                        if(level < enchant.getValue()){
                            return false;
                        }
                    }

                    NBTTagCompound reqTag = ItemSlashBlade.getItemTagCompound(requiredStateBlade);
                    NBTTagCompound srcTag = ItemSlashBlade.getItemTagCompound(curIs);

                    if(!curIs.getUnlocalizedName().equals(requiredStateBlade.getUnlocalizedName()))
                        return false;
                    
                    if(0 < tagValueCompare1(ItemSlashBlade.ProudSoul, reqTag, srcTag))
                        return false;
                    if(0 < tagValueCompare1(ItemSlashBlade.KillCount, reqTag, srcTag))
                        return false;
                    if(0 < tagValueCompare1(ItemSlashBlade.RepairCount, reqTag, srcTag))
                        return false;

                    break;
                }
                
            }
        }

        return result;
    }

}
