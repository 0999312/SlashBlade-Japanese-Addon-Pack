package cn.mmf.slashblade_addon.ability;

import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class EnderTeleportCanceller
{

	public static final String CancelingTimesStr = "SB.Kamuy.WarpCancel";

	public static void setTeleportCancel(Entity target, int ticks)
	{
		if (!(target instanceof EntityEnderman))
			return;

		target.getEntityData().setLong(CancelingTimesStr, target.world.getTotalWorldTime() + ticks);
	}

    static private boolean canTeleport(Entity entity)
	{
		World world = entity.world;

        if (entity != null && !world.isRemote) {

            long current = world.getTotalWorldTime();
            long timeout = entity.getEntityData().getLong(CancelingTimesStr);

            return timeout < current;
        }

        return false;
    }

    @SubscribeEvent
    public void onEnderTeleportEvent(EnderTeleportEvent event)
	{
        if (!canTeleport(event.getEntityLiving()))
            event.setCanceled(true);
    }
}
