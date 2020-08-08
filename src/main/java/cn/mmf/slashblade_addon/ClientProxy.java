package cn.mmf.slashblade_addon;

import org.lwjgl.input.Keyboard;

import cn.mmf.slashblade_addon.entity.EntityLoader;
import cn.mmf.slashblade_addon.item.ItemLoader;
import mods.flammpfeil.slashblade.SlashBlade;
import mods.flammpfeil.slashblade.client.model.BladeSpecialRender;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.Thaumcraft;

@EventBusSubscriber
public class ClientProxy extends CommonProxy {
	public static final KeyBinding ChangeMode = new KeyBinding("key.flammpfeil.slashblade.mode_change", Keyboard.KEY_V,
			"key.categories.sjap");
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		EntityLoader.registerRender();
		if (ConfigLoader.switch_MURASAMA)
			ModelLoader.setCustomModelResourceLocation(ItemLoader.FPNCore, 0,
					new ModelResourceLocation(SlashBlade.modid + ":" + "sphere.obj"));
		Slashblade_model(ItemLoader.rfblade);

		if (Loader.isModLoaded(Thaumcraft.MODID))
			Slashblade_model(ItemLoader.tcblade);
	}

	private static final ModelResourceLocation modelLoc = new ModelResourceLocation(
			"flammpfeil.slashblade:model/named/blade.obj");
	
	@SideOnly(Side.CLIENT)
	public static void Slashblade_model(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, modelLoc);
		item.setTileEntityItemStackRenderer(new BladeSpecialRender());
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		ClientRegistry.registerKeyBinding(ChangeMode);
	}

	@SubscribeEvent
	public static void KeyInput(InputEvent.KeyInputEvent event) {
		if (ChangeMode.isPressed()) {
			getNetwork().sendToServer(new PacketKeyMessage(SJAP.MODID));
		}
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);

	}

}
