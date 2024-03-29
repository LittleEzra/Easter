package net.feliscape.easter.datagen;

import net.feliscape.easter.Easter;
import net.feliscape.easter.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Easter.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        //this.tag(ModTags.Blocks.NAME)
        //        .add(Tags.Blocks.STONE);

        this.tag(ModTags.Blocks.CAN_DROP_EGG)
                .add(
                        Blocks.IRON_ORE,
                        Blocks.GOLD_ORE,
                        Blocks.DIAMOND_ORE,
                        Blocks.REDSTONE_ORE,
                        Blocks.LAPIS_ORE,
                        Blocks.EMERALD_ORE,
                        Blocks.DEEPSLATE_IRON_ORE,
                        Blocks.DEEPSLATE_GOLD_ORE,
                        Blocks.DEEPSLATE_DIAMOND_ORE,
                        Blocks.DEEPSLATE_REDSTONE_ORE,
                        Blocks.DEEPSLATE_LAPIS_ORE,
                        Blocks.DEEPSLATE_EMERALD_ORE,
                        Blocks.ACACIA_LEAVES,
                        Blocks.AZALEA_LEAVES,
                        Blocks.BIRCH_LEAVES,
                        Blocks.CHERRY_LEAVES,
                        Blocks.DARK_OAK_LEAVES,
                        Blocks.JUNGLE_LEAVES,
                        Blocks.FLOWERING_AZALEA_LEAVES,
                        Blocks.MANGROVE_LEAVES,
                        Blocks.OAK_LEAVES,
                        Blocks.SPRUCE_LEAVES,
                        Blocks.GRASS,
                        Blocks.TALL_GRASS,
                        Blocks.FERN,
                        Blocks.LARGE_FERN
                );
    }
}
