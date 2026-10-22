package com.grandfruits;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.eventbus.api.IEventBus;

@Mod(GrandFruits.MOD_ID)
public class GrandFruits {
    public static final String MOD_ID = "grandfruits";

    public GrandFruits() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.ITEMS.register(modBus);
        modBus.addListener(this::addCreativeItems);
    }

    @SubscribeEvent
    public void addCreativeItems(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(CreativeModeTabs.FOOD_AND_DRINKS)) {
            event.accept(ModItems.GOMU_GOMU_NO_MI.get());
            event.accept(ModItems.MERA_MERA_NO_MI.get());
            event.accept(ModItems.PIKA_PIKA_NO_MI.get());
            event.accept(ModItems.GURA_GURA_NO_MI.get());
        }
        if (event.getTabKey().equals(CreativeModeTabs.COMBAT)) {
            event.accept(ModItems.HAKI_CHARM.get());
        }
    }
}
