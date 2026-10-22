package com.grandfruits;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, GrandFruits.MOD_ID);

    public static final RegistryObject<Item> GOMU_GOMU_NO_MI = ITEMS.register(
            "gomu_gomu_no_mi", () -> new DevilFruitItem(DevilFruitItem.Power.GOMU,
                    new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> MERA_MERA_NO_MI = ITEMS.register(
            "mera_mera_no_mi", () -> new DevilFruitItem(DevilFruitItem.Power.MERA,
                    new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> PIKA_PIKA_NO_MI = ITEMS.register(
            "pika_pika_no_mi", () -> new DevilFruitItem(DevilFruitItem.Power.PIKA,
                    new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> GURA_GURA_NO_MI = ITEMS.register(
            "gura_gura_no_mi", () -> new DevilFruitItem(DevilFruitItem.Power.GURA,
                    new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> HAKI_CHARM = ITEMS.register(
            "haki_charm", () -> new DevilFruitItem(DevilFruitItem.Power.HAKI,
                    new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON)));

    private ModItems() {}
}
