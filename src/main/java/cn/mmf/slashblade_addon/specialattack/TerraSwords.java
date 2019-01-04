package cn.mmf.slashblade_addon.specialattack;

import java.util.List;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.ability.StylishRankManager;
import mods.flammpfeil.slashblade.entity.EntityStormSwords;
import mods.flammpfeil.slashblade.entity.selector.EntitySelectorAttackable;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import vazkii.botania.api.mana.ManaItemHandler;

public class TerraSwords
  extends SpecialAttackBase
{
  public String toString()
  {
    return "terra";
  }
  
  public void doSpacialAttack(ItemStack stack, EntityPlayer player)
  {
    World world = player.world;
    
    NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(stack);
    if (!world.isRemote)
    {
      ItemSlashBlade blade = (ItemSlashBlade)stack.getItem();
      
      Entity target = null;
      
      int entityId = ItemSlashBlade.TargetEntityId.get(tag).intValue();
      if (entityId != 0)
      {
        Entity tmp = world.getEntityByID(entityId);
        if ((tmp != null) && 
          (tmp.getDistance(player) < 30.0F)) {
          target = tmp;
        }
      }
      if (target == null) {
        target = getEntityToWatch(player);
      }
      if (target != null)
      {
        ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.SlashDim);
        
        int cost = -40;
        if (!ItemSlashBlade.ProudSoul.tryAdd(tag, cost, false)) {
          stack.damageItem(10, player);
        }
        StylishRankManager.setNextAttackType(player, StylishRankManager.AttackTypes.PhantomSword);
        blade.attackTargetEntity(stack, target, player, Boolean.valueOf(true));
        player.onCriticalHit(target);
        
        target.motionX = 0.0D;
        target.motionY = 0.0D;
        target.motionZ = 0.0D;
        if ((target instanceof EntityLivingBase))
        {
          blade.setDaunting((EntityLivingBase)target);
          ((EntityLivingBase)target).hurtTime = 0;
          ((EntityLivingBase)target).hurtResistantTime = 0;
        }
        int level = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
        float magicDamage = 1.0F + ItemSlashBlade.AttackAmplifier.get(tag).floatValue() * (level / 5.0F);
        
        ItemStack terraSwordStack = SlashBlade.findItemStack("botania", "terraSword", 1);
        
        int count = 3;
        
        int rank = StylishRankManager.getStylishRank(player);
        for (int i = 1; i < rank; i++) {
          if (ManaItemHandler.requestManaExactForTool(terraSwordStack, player, 100, true)) {
            count++;
          }
        }
        for (int i = 0; i < count; i++) {
          if (!world.isRemote)
          {
            EntityStormSwords entityDrive = new EntityStormSwords(world, player, magicDamage);
            if (entityDrive != null)
            {
              entityDrive.setInterval(7 + i * 2);
              entityDrive.setLifeTime(30);
              
              entityDrive.setColor(3524113);
              
              entityDrive.setTargetEntityId(target.getEntityId());
              
              world.spawnEntity(entityDrive);
            }
          }
        }
      }
    }
    ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.Kiriorosi);
  }
  
  private Entity getEntityToWatch(EntityPlayer player)
  {
    World world = player.world;
    Entity target = null;
    for (int dist = 2; dist < 20; dist += 2)
    {
      AxisAlignedBB bb = player.getEntityBoundingBox();
      Vec3d vec = player.getLookVec();
      vec = vec.normalize();
      bb = bb.grow(2.0D, 0.25D, 2.0D);
      bb = bb.offset(vec.x * dist, vec.y * dist, vec.z * dist);
      
      List<Entity> list = world.getEntitiesInAABBexcluding(player, bb, EntitySelectorAttackable.getInstance());
      float distance = 30.0F;
      for (Entity curEntity : list)
      {
        float curDist = curEntity.getDistance(player);
        if (curDist < distance)
        {
          target = curEntity;
          distance = curDist;
        }
      }
      if (target != null) {
        break;
      }
    }
    return target;
  }
}
