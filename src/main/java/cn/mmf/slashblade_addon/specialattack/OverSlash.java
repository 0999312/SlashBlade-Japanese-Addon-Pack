package cn.mmf.slashblade_addon.specialattack;

import java.util.List;

import cn.mmf.slashblade_addon.item.ItemSlashBladeRF;
import mods.flammpfeil.slashblade.ability.StylishRankManager;
import mods.flammpfeil.slashblade.entity.EntityDrive;
import mods.flammpfeil.slashblade.entity.EntitySpearManager;
import mods.flammpfeil.slashblade.entity.selector.EntitySelectorAttackable;
import mods.flammpfeil.slashblade.item.ItemSlashBlade;
import mods.flammpfeil.slashblade.specialattack.SpecialAttackBase;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class OverSlash extends SpecialAttackBase {

	public String toString(){
		return "overslash";
	}

    private Entity getEntityToWatch(EntityPlayer player){
        World world = player.world;
        Entity target = null;
        for(int dist = 2; dist < 20; dist+=2){
            AxisAlignedBB bb = player.getEntityBoundingBox();
            Vec3d vec = player.getLookVec();
            vec = vec.normalize();
            bb = bb.grow(2.0f, 0.25f, 2.0f);
            bb = bb.offset(vec.x*(float)dist,vec.y*(float)dist,vec.z*(float)dist);

            List<Entity> list = world.getEntitiesInAABBexcluding(player, bb, EntitySelectorAttackable.getInstance());
            float distance = 30.0f;
            for(Entity curEntity : list){
                float curDist = curEntity.getDistance(player);
                if(curDist < distance)
                {
                    target = curEntity;
                    distance = curDist;
                }
            }
            if(target != null)
                break;
        }
        return target;
    }
    
	@Override
	public void doSpacialAttack(ItemStack stack, EntityPlayer player)
	{
		World world = player.world;
  
		NBTTagCompound tag = ItemSlashBlade.getItemTagCompound(stack);
		
		Entity target = null;
        int entityId = ItemSlashBlade.TargetEntityId.get(tag);
        if(entityId != 0){
            Entity tmp = world.getEntityByID(entityId);
            if(tmp != null){
                if(tmp.getDistance(player) < 30.0f)
                    target = tmp;
            }
        }

        if(target == null){
            target = getEntityToWatch(player);
        }

		for (int i = 0; i < 20; i++)
		{
			double d0 = player.getRNG().nextGaussian() * 0.02D;
			double d1 = player.getRNG().nextGaussian() * 0.02D;
			double d2 = player.getRNG().nextGaussian() * 0.02D;
			double d3 = 10.0D;
			world.spawnParticle(EnumParticleTypes.DRAGON_BREATH, player.posX + player
					.getRNG().nextFloat() * player.width * 2.0F - player.width - d0 * d3, player.posY, player.posZ + player
      
					.getRNG().nextFloat() * player.width * 2.0F - player.width - d2 * d3, d0, d1, d2);
		}
		player.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 1, 1F);
		if (!world.isRemote){
            final int cost = -40;
            if(!ItemSlashBlade.ProudSoul.tryAdd(tag,cost,false)){
                ItemSlashBlade.damageItem(stack, 10, player);
            }
            ItemSlashBlade blade = (ItemSlashBlade)stack.getItem();

            if(blade instanceof ItemSlashBladeRF){
                float baseModif = blade.getBaseAttackModifiers(tag);
                int level = Math.max(1, EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack));
                float magicDamage = baseModif / 2.0F;
                
                int rank = StylishRankManager.getStylishRank(player);
                if (5 <= rank) {
                  magicDamage += ItemSlashBlade.AttackAmplifier.get(tag).floatValue() * (0.25F + level / 5.0F);
                }
            	ItemSlashBladeRF bladeRF = (ItemSlashBladeRF)blade;
        		bladeRF.extractEnergy(stack, 100 * (int)Math.pow(2.0D, rank), false);
            	if(bladeRF.isEmpowered(stack)){
            		bladeRF.extractEnergy(stack, 300 * (int)Math.pow(2.0D, rank), false);
            		player.playSound(SoundEvents.ENTITY_LIGHTNING_THUNDER, 1, 1F);
                    for(int i = 0; i < 6;i++){
                        EntityDrive entityDrive = new EntityDrive(world, player, magicDamage,false,0);
                        entityDrive.setLocationAndAngles(player.posX,
                                player.posY + (double)player.getEyeHeight()/2D,
                                player.posZ,
                                player.rotationYaw + 60 * i ,
                                0);
                        entityDrive.setDriveVector(0.5f);
                        entityDrive.setLifeTime(10);
                        entityDrive.setIsMultiHit(false);
                        entityDrive.setRoll(90.0f);
                        if (entityDrive != null) {
                            world.spawnEntity(entityDrive);
                        }
                    }
            	}
            }
            
            EntitySpearManager entityDA = new EntitySpearManager(world, player, false);
            entityDA.setLifeTime(7);
            if (entityDA != null) {
                world.spawnEntity(entityDA);
            }
  		ItemSlashBlade.setComboSequence(tag, ItemSlashBlade.ComboSequence.Battou);
		}
	
	}
}