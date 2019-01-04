package cn.mmf.slashblade_addon.entity;

import mods.flammpfeil.slashblade.ability.StylishRankManager;
import mods.flammpfeil.slashblade.entity.selector.EntitySelectorAttackable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;


public class EntityLightningSword extends EntityPhantomSwordEx
{
	public static String NAME_ATTACK_TYPE = StylishRankManager.AttackTypes.registerAttackType("LightningSword", -0.5F);
	
	public EntityLightningSword(World worldIn)
	{
		super(worldIn);
	}

	public EntityLightningSword(World worldIn,
								EntityLivingBase thrower,
								float attackLevel)
	{
		super(worldIn, thrower, attackLevel, null);
	}

	@Override
	protected void attackToEntity(Entity target)
	{
		target.hurtResistantTime = 0;
        
		if (!world.isRemote) {
			world.addWeatherEffect(
				new EntityNoFireLightningBolt(world,
											  target,
											  EntitySelectorAttackable.getInstance()));
		}
		StylishRankManager.setNextAttackType(thrower_, NAME_ATTACK_TYPE);
		StylishRankManager.doAttack(thrower_);
        
		setDead();
	}
}
