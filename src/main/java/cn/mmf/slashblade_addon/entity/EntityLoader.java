package cn.mmf.slashblade_addon.entity;

import cn.mmf.slashblade_addon.SJAP;
import mods.flammpfeil.slashblade.SlashBlade;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class EntityLoader {
	public EntityLoader() {
			EntityRegistry.registerModEntity(new ResourceLocation(SlashBlade.modid, "PhantomSwordEx"),EntityPhantomSwordEx.class,"PhantomSwordEx",1,SJAP.instance,250,1,true);
			EntityRegistry.registerModEntity(new ResourceLocation(SlashBlade.modid, "LightningSword"),EntityLightningSword.class,"LightningSword",2,SJAP.instance,250,1,true);
			EntityRegistry.registerModEntity(new ResourceLocation(SlashBlade.modid, "NoFireLigntningBolt"),EntityNoFireLightningBolt.class,"NoFireLigntningBolt",3,SJAP.instance,250,1,true);
			EntityRegistry.registerModEntity(new ResourceLocation(SlashBlade.modid, "EntityDriveEx"),EntityDriveEx.class,"EntityDriveEx",4,SJAP.instance,250,1,true);
			EntityRegistry.registerModEntity(new ResourceLocation(SlashBlade.modid, "EntityAquaEdge"),EntityAquaEdge.class,"EntityAquaEdge",5,SJAP.instance,250,1,true);
			EntityRegistry.registerModEntity(new ResourceLocation(SlashBlade.modid, "EntityFlareEdge"),EntityFlareEdge.class,"EntityFlareEdge",6,SJAP.instance,250,1,true);
	}

}
