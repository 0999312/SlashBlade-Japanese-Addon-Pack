package cn.mmf.slashblade_addon.entity;

import cn.mmf.slashblade_addon.ability.EnderTeleportCanceller;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityFlareEdge extends EntityDriveEx
{
	public EntityFlareEdge(World worldIn)
    {
        super(worldIn);
	}


	public EntityFlareEdge(World worldIn,
						  EntityLivingBase thrower,
						  float attackLevel)
	{
		super(worldIn, thrower, attackLevel);

		setColor(0xFF0000);
		setMultiHit(false);
	}

	@Override
	protected boolean onImpact(Entity target, float damage, DamageSource ds)
	{
		target.setFire(5);
		
		super.onImpact(target, damage, ds);

		if (target instanceof EntityEnderman) {
			EnderTeleportCanceller.setTeleportCancel(target, 600);
			coolDownEnderman((EntityEnderman)target);
		}

		return false;
	}
}
