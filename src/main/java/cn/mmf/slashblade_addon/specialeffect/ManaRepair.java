package cn.mmf.slashblade_addon.specialeffect;

import mods.flammpfeil.slashblade.entity.EntityBladeStand;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.specialeffect.ISpecialEffect;
import mods.flammpfeil.slashblade.util.SlashBladeEvent;
import mods.flammpfeil.slashblade.util.SlashBladeHooks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import vazkii.botania.common.block.tile.mana.TilePool;

public class ManaRepair implements ISpecialEffect
{
  static final int RepairManaCost = 100;
  public static final String EffectKey = "ManaPoolRepair";
  
  @SubscribeEvent
  public void onUpdateBladeStand(SlashBladeEvent.OnEntityBladeStandUpdateEvent event)
  {
    try
    {
      EntityBladeStand stand = (EntityBladeStand)ObfuscationReflectionHelper.getPrivateValue(SlashBladeEvent.OnEntityBladeStandUpdateEvent.class, event, "entityBladeStand");
      if (!stand.hasBlade()) {
        return;
      }
      ItemStack blade = event.blade;
      if (!blade.isItemDamaged()) {
        return;
      }
      NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(blade);
      
      NBTTagCompound etag = tag.getCompoundTag("SB.SEffect");
      
      int reqiredLevel = etag.getInteger("ManaPoolRepair");
      if (reqiredLevel == 0) {
        return;
      }
      int x = (int)Math.floor(stand.posX);
      int y = (int)Math.floor(stand.posY);
      int z = (int)Math.floor(stand.posZ);
      
      y--;
      
      TileEntity tile = stand.getEntityWorld().getTileEntity(new BlockPos(x, y, z));
      if (tile == null) {
        return;
      }
      if (!(tile instanceof TilePool)) {
        return;
      }
      TilePool pool = (TilePool)tile;
      
      int current = pool.getCurrentMana();
      if (current < 100) {
        return;
      }
      pool.recieveMana(-100);
      
      blade.setItemDamage(blade.getItemDamage() - 1);
    }
    catch (Throwable localThrowable) {}
  }
	@Override
	public int getDefaultRequiredLevel() {
	return 1;
	}

	@Override
	public String getEffectKey() {
	return EffectKey;
	}

  @Override
	public void register()
	{
		SlashBladeHooks.EventBus.register(this);
	}
}
