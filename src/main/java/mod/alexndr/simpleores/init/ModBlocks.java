package mod.alexndr.simpleores.init;

import mod.alexndr.simpleores.ModUtil;
import mod.alexndr.simpleores.SimpleOres;
import net.minecraft.block.Block;
import net.minecraftforge.registries.ObjectHolder;
/*import src.main.java.alexndr.plugins.Fusion.BlockFusionFurnace;
import src.main.java.alexndr.plugins.Fusion.Fusion;
import src.main.java.alexndr.plugins.Fusion.SimpleBlock;*/

@ObjectHolder(SimpleOres.MODID)
public final class ModBlocks
{
    // Ore Blocks
    public static final Block copper_ore = ModUtil._null();
    public static final Block tin_ore = ModUtil._null();
    public static final Block mythril_ore = ModUtil._null();
    public static final Block adamantium_ore = ModUtil._null();
    public static final Block onyx_ore = ModUtil._null();

    // Metal Blocks
    public static final Block copper_block = ModUtil._null();
    public static final Block tin_block = ModUtil._null();
    public static final Block mythril_block = ModUtil._null();
    public static final Block adamantium_block = ModUtil._null();
    public static final Block onyx_block = ModUtil._null();
    
    // STORAGE BLOCKS
 	public static final Block steel_block = ModUtil._null(); 	
 	public static final Block bronze_block = ModUtil._null();
 	public static final Block thyrium_block = ModUtil._null();
 	public static final Block sinisite_block = ModUtil._null();
 	
 	/*// MACHINES
 	public static BlockFusionFurnace fusion_furnace = ModUtil._null();
 			new BlockFusionFurnace("fusion_furnace", Fusion.plugin, false);
 	public static BlockFusionFurnace fusion_furnace_lit = ModUtil._null();
 			new BlockFusionFurnace("fusion_furnace_lit", Fusion.plugin, true);*/

}  // end class ModBlocks
