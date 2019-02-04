package cn.mmf.slashblade_addon.specialattack;

import mods.flammpfeil.slashblade.ability.StylishRankManager;
import mods.flammpfeil.slashblade.entity.EntityHeavyRainSwords;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class SXSA1 extends PhantomSwordsBase
{
	@Override
	public String toString()
	{
		return "sxsa_1";
	}

	@Override
	protected void spawnEntity(float damage, int count,
							   Entity target, EntityPlayer player)
	{
		final World world = player.world;
		final int targetId = target.getEntityId();
        int rank = StylishRankManager.getStylishRank(player);
		world.playSound(player, target.prevPosX, target.prevPosY, target.prevPosZ, SoundEvents.ENTITY_ENDERMEN_TELEPORT, SoundCategory.NEUTRAL, 1F, 1.0F);

        count = 15;
        int multiplier = 2;
        if (5 <= rank)
            multiplier += 1;
        float magicDamage = 2;

        for (int i = 0; i < count; i++) {
            for (int j = 0; j < multiplier; j++) {
                EntityHeavyRainSwords summonedSword = new EntityHeavyRainSwords(world, player, magicDamage, player.getRNG().nextFloat() * 360.0f, i , targetId);
                if (summonedSword != null) {
                    summonedSword.setLifeTime(30+i);
                    summonedSword.setColor(15204584);
            		world.spawnEntity(summonedSword);
                }
            }
        }
        
	}

	public void onImpact(Entity target)
	{
		target.motionX = 0.0;
		target.motionY = 0.0;
		target.motionZ = 0.0;
	}

	public void onSticking(Entity target)
	{
		target.motionX = 0.0;
		target.motionY = 0.0;
		target.motionZ = 0.0;

	}
	
}
