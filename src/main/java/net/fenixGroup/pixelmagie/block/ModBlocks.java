package net.fenixGroup.pixelmagie.block;

import net.fenixGroup.pixelmagie.item.ModCreativeModeTab;
import net.fenixGroup.pixelmagie.item.ModItems;
import net.fenixGroup.pixelmagie.pixelmagie;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, pixelmagie.MOD_ID);

    public static final RegistryObject<Block> AMETHYST_BLOCK_ORE = registerBlock("amethyst_block_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
            .strength(2.0f, 1200.0f)
            .requiresCorrectToolForDrops(),
            UniformInt.of(5, 10)), ModCreativeModeTab.PIXELMAGIE_TAB);

    public static final RegistryObject<Block> DEEPSLATE_AMETHYST_BLOCK_ORE = registerBlock("deepslate_amethyst_block_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
            .strength(2.0f, 1200.0f)
            .requiresCorrectToolForDrops(),
            UniformInt.of(5, 10)), ModCreativeModeTab.PIXELMAGIE_TAB);

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toRetorn = BLOCKS.register(name, block);
        registryBlockItem(name, toRetorn, tab);
        return toRetorn;
    }

    private static <T extends Block> RegistryObject<Item> registryBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab){

        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
