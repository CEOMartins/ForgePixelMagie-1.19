package net.fenixGroup.pixelmagie.item;

import net.fenixGroup.pixelmagie.pixelmagie;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.EventBus;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, pixelmagie.MOD_ID);

    public static final RegistryObject<Item> DEMONICMARK = ITEMS.register( "demonicmark",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.PIXELMAGIE_TAB)));

    public static final RegistryObject<Item> ANGELICMARK = ITEMS.register( "angelicmark",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.PIXELMAGIE_TAB)));

    public static final RegistryObject<Item> ADAGA = ITEMS.register( "adaga",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.PIXELMAGIE_TAB)));

    public static final RegistryObject<Item> AMETHYST = ITEMS.register( "amethyst",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.PIXELMAGIE_TAB)));

    public static final RegistryObject<Item> RAWAMETHYST = ITEMS.register( "rawamethyst",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.PIXELMAGIE_TAB)));

    public static final RegistryObject<Item> ORELARIMAR = ITEMS.register( "orelarimar",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.PIXELMAGIE_TAB)));

    public static final RegistryObject<Item> LARIMAR = ITEMS.register( "larimar",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.PIXELMAGIE_TAB)));

    public static final RegistryObject<Item> ADAGADELARIMAR = ITEMS.register( "adagadelarimar",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.PIXELMAGIE_TAB)));

    public static final RegistryObject<Item> PICARETADELARIMAR = ITEMS.register( "picaretadelarimar",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.PIXELMAGIE_TAB)));


    public static void  register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
