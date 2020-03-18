package cn.mmf.slashblade_addon.entity;

import cn.mmf.slashblade_addon.ability.EnderTeleportCanceller;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class EntityAquaEdge extends EntityDriveEx
{

	public EntityAquaEdge(World worldIn)
    {
        super(worldIn);
	}

    public EntityAquaEdge(World worldIn,
						  EntityLivingBase thrower,
						  float attackLevel)
	{
		super(worldIn, thrower, attackLevel);

		setMultiHit(true);
		setColor(0x0000FF);
	}

	@Override
	public void setInitialPosition(double x, double y, double z, float yaw, float pitch, float roll, float speed){
		super.setInitialPosition(x, y, z, yaw, pitch, roll, speed);
		setPosition(posX + motionX, posY + motionY, posZ + motionZ);
	}

	@Override
	protected boolean onImpact(Entity target, float damage) {
		return onImpact(target, damage, "drown");
	}

	@Override
	protected boolean onImpact(Entity target, float damage, DamageSource ds)
	{
		spawnParticle(target);

		super.onImpact(target, damage, ds);
    
		ObfuscationReflectionHelper.setPrivateValue(Entity.class,
										 target,
										 true,
										 "field_70171_ac");
    
		if (target.isBurning()) {
			target.playSound(SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE,
							 0.7f,
							 1.2f + 0.8f*this.rand.nextFloat());
			target.extinguish();

			try {
				ObfuscationReflectionHelper.findMethod(
					Entity.class, "func_70052_a",
					int.class, boolean.class).invoke(target, 0, false);
			} catch (IllegalAccessException ex) {
				throw new RuntimeException(ex);
			} catch (IllegalArgumentException ex) {
				throw new RuntimeException(ex);
			} catch (java.lang.reflect.InvocationTargetException ex) {
				throw new RuntimeException(ex);
			}
		}
    
		if (target instanceof EntityEnderman) {
			EnderTeleportCanceller.setTeleportCancel(target, 100);
			coolDownEnderman((EntityEnderman)target);
		}

		return false;	
	}


	private void spawnParticle(Entity target)
	{
		target.world.spawnParticle(
			EnumParticleTypes.EXPLOSION_LARGE,
			target.posX,
			target.posY + target.height,
			target.posZ,
			3.0, 3.0, 3.0);

		target.world.spawnParticle(
			EnumParticleTypes.EXPLOSION_LARGE,
			target.posX + 1.0,
			target.posY + target.height + 1.0,
			target.posZ,
			3.0, 3.0, 3.0);

		target.world.spawnParticle(
			EnumParticleTypes.EXPLOSION_LARGE,
			target.posX,
			target.posY + target.height + 0.5,
			target.posZ + 1.0,
			3.0, 3.0, 3.0);
	}
}
