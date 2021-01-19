package mod.alexndr.simpleores;

import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.base.Preconditions;

import mod.alexndr.simpleores.config.ConfigHelper;
import mod.alexndr.simpleores.config.ConfigHolder;
import mod.alexndr.simpleores.content.CopperBucket;
import mod.alexndr.simpleores.content.MythrilBow;
import mod.alexndr.simpleores.content.OnyxBow;
import mod.alexndr.simpleores.content.SimpleMetalBlock;
import mod.alexndr.simpleores.content.SimpleOresArmorMaterial;
import mod.alexndr.simpleores.content.SimpleOresItemTier;
import mod.alexndr.simpleores.content.SinisiteBow;
import mod.alexndr.simpleores.content.ThyriumBow;
import mod.alexndr.simpleores.generation.OreGeneration;
import mod.alexndr.simpleores.init.ModTabGroups;
import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShearsItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

@EventBusSubscriber(modid = SimpleOres.MODID, bus = MOD)
public final class ModEventSubscriber 
{
	private static final Logger LOGGER = LogManager.getLogger(SimpleOres.MODID + " Mod Event Subscriber");

    @SubscribeEvent
    public static void onCommonSetup(final FMLCommonSetupEvent event)
    {
        OreGeneration.setupOreGen();
        LOGGER.debug("Common setup done");
    } // end onCommonSetup

	/**
	 * This method will be called by Forge when it is time for the mod to register its Blocks.
	 * This method will always be called before the Item registry method.
	 */
	@SubscribeEvent
	public static void onRegisterBlocks(final RegistryEvent.Register<Block> event)
	{
		// Register all your blocks inside this registerAll call
        event.getRegistry().registerAll(
                setup(new OreBlock(Block.Properties.create(Material.ROCK)
                             .hardnessAndResistance( 1.7F, 3.0F)
                             .harvestTool(ToolType.PICKAXE).harvestLevel(1)), "copper_ore"),
				setup(new OreBlock(Block.Properties.create(Material.ROCK)
                             .hardnessAndResistance( 3.0F, 3.0F)
                             .harvestTool(ToolType.PICKAXE).harvestLevel(1)), "tin_ore"),
				setup(new OreBlock(Block.Properties.create(Material.ROCK)
                             .hardnessAndResistance( 4.0F, 3.0F)
                             .harvestTool(ToolType.PICKAXE).harvestLevel(2)), "adamantium_ore"),
				setup(new OreBlock(Block.Properties.create(Material.ROCK)
                             .hardnessAndResistance( 5.0F, 3.0F)
                             .harvestTool(ToolType.PICKAXE).harvestLevel(2)), "mythril_ore"),
				setup(new OreBlock(Block.Properties.create(Material.ROCK)
                             .hardnessAndResistance( 7.0F, 3.0F)
                             .harvestTool(ToolType.PICKAXE).harvestLevel(3)), "onyx_ore"),
				setup(new SimpleMetalBlock(Block.Properties.create(Material.IRON, MaterialColor.ORANGE_TERRACOTTA)
							.hardnessAndResistance(3.0F, 6.0F)
                            .harvestTool(ToolType.PICKAXE).harvestLevel(0)), "copper_block"),
				setup(new SimpleMetalBlock(Block.Properties.create(Material.IRON)
							.hardnessAndResistance(4.0F, 6.0F)
                            .harvestTool(ToolType.PICKAXE).harvestLevel(0)), "tin_block"),
				setup(new SimpleMetalBlock(Block.Properties.create(Material.IRON, MaterialColor.BLUE)
							.hardnessAndResistance(7.0F, 6.0F)
                            .harvestTool(ToolType.PICKAXE).harvestLevel(0)), "mythril_block"),
				setup(new SimpleMetalBlock(Block.Properties.create(Material.IRON, MaterialColor.GREEN)
							.hardnessAndResistance(7.0F, 12.0F)
                            .harvestTool(ToolType.PICKAXE).harvestLevel(0)), "adamantium_block"),
				setup(new Block(Block.Properties.create(Material.ROCK, MaterialColor.OBSIDIAN)
							.hardnessAndResistance(20.0F, 100.0F)
                            .harvestTool(ToolType.PICKAXE).harvestLevel(0)), "onyx_block"),
				setup(new SimpleMetalBlock(Block.Properties.create(Material.IRON)
							.hardnessAndResistance(4.0F, 6.0F)
	                        .harvestTool(ToolType.PICKAXE).harvestLevel(0)), "steel_block"),
				setup(new SimpleMetalBlock(Block.Properties.create(Material.IRON)
							.hardnessAndResistance(7.0F, 6.0F)
	                        .harvestTool(ToolType.PICKAXE).harvestLevel(0)), "bronze_block"),
				setup(new SimpleMetalBlock(Block.Properties.create(Material.IRON)
							.hardnessAndResistance(7.0F, 12.0F)
	                        .harvestTool(ToolType.PICKAXE).harvestLevel(0)), "thyrium_block"),
				setup(new SimpleMetalBlock(Block.Properties.create(Material.IRON)
						.hardnessAndResistance(7.0F, 12.0F)
                        .harvestTool(ToolType.PICKAXE).harvestLevel(0)), "sinisite_block")
        );
		LOGGER.debug("Registered Blocks");
	}

	/**
	 * This method will be called by Forge when it is time for the mod to register its Items.
	 * This method will always be called after the Block registry method.
	 */
	@SubscribeEvent
	public static void onRegisterItems(final RegistryEvent.Register<Item> event)
	{
		final IForgeRegistry<Item> registry = event.getRegistry();

		// ingots, nuggets, gems, parts
		registry.registerAll(
				setup(new Item(new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "copper_ingot"),
				setup(new Item(new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "tin_ingot"),
				setup(new Item(new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "mythril_ingot"),
				setup(new Item(new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "adamantium_ingot"),
				setup(new Item(new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "onyx_gem"),
				setup(new Item(new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "steel_ingot"),
				setup(new Item(new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "bronze_ingot"),
				setup(new Item(new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "thyrium_ingot"),
				setup(new Item(new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "sinisite_ingot"),
				
				setup(new Item(new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "copper_nugget"),
				setup(new Item(new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "tin_nugget"),
				setup(new Item(new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "mythril_nugget"),
				setup(new Item(new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "adamantium_nugget"),
				setup(new Item(new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "steel_nugget"),
				setup(new Item(new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "bronze_nugget"),
				setup(new Item(new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "thyrium_nugget"),
				setup(new Item(new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "sinisite_nugget"),

				setup(new Item(new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "mythril_rod"),
				setup(new Item(new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "onyx_rod"),
				setup(new Item(new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "thyrium_rod"),
				setup(new Item(new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "sinisite_rod")				
		);

		// armors
		registry.registerAll(
				// copper
				setup(new ArmorItem(SimpleOresArmorMaterial.COPPER, EquipmentSlotType.HEAD,
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "copper_helmet"),
				setup(new ArmorItem(SimpleOresArmorMaterial.COPPER, EquipmentSlotType.LEGS,
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "copper_leggings"),
				setup(new ArmorItem(SimpleOresArmorMaterial.COPPER, EquipmentSlotType.CHEST,
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "copper_chestplate"),
				setup(new ArmorItem(SimpleOresArmorMaterial.COPPER, EquipmentSlotType.FEET,
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "copper_boots"),

				// tin
				setup(new ArmorItem(SimpleOresArmorMaterial.TIN, EquipmentSlotType.HEAD,
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "tin_helmet"),
				setup(new ArmorItem(SimpleOresArmorMaterial.TIN, EquipmentSlotType.LEGS,
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "tin_leggings"),
				setup(new ArmorItem(SimpleOresArmorMaterial.TIN, EquipmentSlotType.CHEST,
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "tin_chestplate"),
				setup(new ArmorItem(SimpleOresArmorMaterial.TIN, EquipmentSlotType.FEET,
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "tin_boots"),

				// mythril
				setup(new ArmorItem(SimpleOresArmorMaterial.MYTHRIL, EquipmentSlotType.HEAD,
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "mythril_helmet"),
				setup(new ArmorItem(SimpleOresArmorMaterial.MYTHRIL, EquipmentSlotType.LEGS,
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "mythril_leggings"),
				setup(new ArmorItem(SimpleOresArmorMaterial.MYTHRIL, EquipmentSlotType.CHEST,
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "mythril_chestplate"),
				setup(new ArmorItem(SimpleOresArmorMaterial.MYTHRIL, EquipmentSlotType.FEET,
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "mythril_boots"),

				// adamantium
				setup(new ArmorItem(SimpleOresArmorMaterial.ADAMANTIUM, EquipmentSlotType.HEAD,
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "adamantium_helmet"),
				setup(new ArmorItem(SimpleOresArmorMaterial.ADAMANTIUM, EquipmentSlotType.LEGS,
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "adamantium_leggings"),
				setup(new ArmorItem(SimpleOresArmorMaterial.ADAMANTIUM, EquipmentSlotType.CHEST,
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "adamantium_chestplate"),
				setup(new ArmorItem(SimpleOresArmorMaterial.ADAMANTIUM, EquipmentSlotType.FEET,
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "adamantium_boots"),

				// onyx
				setup(new ArmorItem(SimpleOresArmorMaterial.ONYX, EquipmentSlotType.HEAD,
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "onyx_helmet"),
				setup(new ArmorItem(SimpleOresArmorMaterial.ONYX, EquipmentSlotType.LEGS,
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "onyx_leggings"),
				setup(new ArmorItem(SimpleOresArmorMaterial.ONYX, EquipmentSlotType.CHEST,
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "onyx_chestplate"),
				setup(new ArmorItem(SimpleOresArmorMaterial.ONYX, EquipmentSlotType.FEET,
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "onyx_boots"),
				
				// steel
				setup(new ArmorItem(SimpleOresArmorMaterial.STEEL, EquipmentSlotType.HEAD,
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "steel_helmet"),
				setup(new ArmorItem(SimpleOresArmorMaterial.STEEL, EquipmentSlotType.LEGS,
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "steel_leggings"),
				setup(new ArmorItem(SimpleOresArmorMaterial.STEEL, EquipmentSlotType.CHEST,
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "steel_chestplate"),
				setup(new ArmorItem(SimpleOresArmorMaterial.STEEL, EquipmentSlotType.FEET,
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "steel_boots"),
				
				// bronze
				setup(new ArmorItem(SimpleOresArmorMaterial.BRONZE, EquipmentSlotType.HEAD,
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "bronze_helmet"),
				setup(new ArmorItem(SimpleOresArmorMaterial.BRONZE, EquipmentSlotType.LEGS,
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "bronze_leggings"),
				setup(new ArmorItem(SimpleOresArmorMaterial.BRONZE, EquipmentSlotType.CHEST,
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "bronze_chestplate"),
				setup(new ArmorItem(SimpleOresArmorMaterial.BRONZE, EquipmentSlotType.FEET,
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "bronze_boots"),
				
				// thyrium
				setup(new ArmorItem(SimpleOresArmorMaterial.THYRIUM, EquipmentSlotType.HEAD,
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "thyrium_helmet"),
				setup(new ArmorItem(SimpleOresArmorMaterial.THYRIUM, EquipmentSlotType.LEGS,
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "thyrium_leggings"),
				setup(new ArmorItem(SimpleOresArmorMaterial.THYRIUM, EquipmentSlotType.CHEST,
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "thyrium_chestplate"),
				setup(new ArmorItem(SimpleOresArmorMaterial.THYRIUM, EquipmentSlotType.FEET,
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "thyrium_boots"),
				
				// sinisite
				setup(new ArmorItem(SimpleOresArmorMaterial.SINISITE, EquipmentSlotType.HEAD,
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "sinisite_helmet"),
				setup(new ArmorItem(SimpleOresArmorMaterial.SINISITE, EquipmentSlotType.LEGS,
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "sinisite_leggings"),
				setup(new ArmorItem(SimpleOresArmorMaterial.SINISITE, EquipmentSlotType.CHEST,
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "sinisite_chestplate"),
				setup(new ArmorItem(SimpleOresArmorMaterial.SINISITE, EquipmentSlotType.FEET,
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "sinisite_boots")
		);

		// tools
		registry.registerAll(
				// axes
				setup(new AxeItem(SimpleOresItemTier.COPPER, 5, -1.0F+SimpleOresItemTier.COPPER.getAttackSpeed(),
						new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "copper_axe"),
				setup(new AxeItem(SimpleOresItemTier.TIN, 5, -1.0F+SimpleOresItemTier.TIN.getAttackSpeed(),
						new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "tin_axe"),
				setup(new AxeItem(SimpleOresItemTier.MYTHRIL, 5, -1.0F+SimpleOresItemTier.MYTHRIL.getAttackSpeed(),
						new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "mythril_axe"),
				setup(new AxeItem(SimpleOresItemTier.ADAMANTIUM, 5, -1.0F+SimpleOresItemTier.ADAMANTIUM.getAttackSpeed(),
						new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "adamantium_axe"),
				setup(new AxeItem(SimpleOresItemTier.ONYX, 5, -1.0F+SimpleOresItemTier.ONYX.getAttackSpeed(),
						new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "onyx_axe"),
				setup(new AxeItem(SimpleOresItemTier.STEEL, 5, -1.0F+SimpleOresItemTier.STEEL.getAttackSpeed(),
						new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "steel_axe"),
				setup(new AxeItem(SimpleOresItemTier.BRONZE, 5, -1.0F+SimpleOresItemTier.BRONZE.getAttackSpeed(),
						new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "bronze_axe"),
				setup(new AxeItem(SimpleOresItemTier.THYRIUM, 5, -1.0F+SimpleOresItemTier.THYRIUM.getAttackSpeed(),
						new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "thyrium_axe"),
				setup(new AxeItem(SimpleOresItemTier.SINISITE, 5, -1.0F+SimpleOresItemTier.SINISITE.getAttackSpeed(),
						new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "sinisite_axe"),

				// bows
				setup(new MythrilBow(new Item.Properties().maxDamage(780).group(ModTabGroups.MOD_ITEM_GROUP)),
					  "mythril_bow"),
				setup(new OnyxBow(new Item.Properties().maxDamage(1000).group(ModTabGroups.MOD_ITEM_GROUP)),
					  "onyx_bow"),
				setup(new ThyriumBow(new Item.Properties().maxDamage(870).group(ModTabGroups.MOD_ITEM_GROUP)),
					  "thyrium_bow"),
				setup(new SinisiteBow(new Item.Properties().maxDamage(1200).group(ModTabGroups.MOD_ITEM_GROUP)),
					  "sinisite_bow"),

				// bucket - not stackable until fillBucket() gets fixed.
				setup(new CopperBucket(new Item.Properties().maxStackSize(1).group(ModTabGroups.MOD_ITEM_GROUP)), "copper_bucket"),
				setup(new CopperBucket(Fluids.WATER,
                                       new Item.Properties().maxStackSize(1).group(ModTabGroups.MOD_ITEM_GROUP)), "copper_bucket_water"),

				// hoes
				setup(new HoeItem(SimpleOresItemTier.COPPER, -0.0F+SimpleOresItemTier.COPPER.getAttackSpeed(),
							new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "copper_hoe"),
				setup(new HoeItem(SimpleOresItemTier.TIN, -0.0F+SimpleOresItemTier.TIN.getAttackSpeed(),
						  	new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "tin_hoe"),
				setup(new HoeItem(SimpleOresItemTier.MYTHRIL, -0.0F+SimpleOresItemTier.MYTHRIL.getAttackSpeed(),
							new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "mythril_hoe"),
				setup(new HoeItem(SimpleOresItemTier.ADAMANTIUM, -0.0F+SimpleOresItemTier.ADAMANTIUM.getAttackSpeed(),
							new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "adamantium_hoe"),
				setup(new HoeItem(SimpleOresItemTier.ONYX, -0.0F+SimpleOresItemTier.ONYX.getAttackSpeed(),
							new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "onyx_hoe"),
				setup(new HoeItem(SimpleOresItemTier.STEEL, -0.0F+SimpleOresItemTier.STEEL.getAttackSpeed(),
							new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "steel_hoe"),
				setup(new HoeItem(SimpleOresItemTier.BRONZE, -0.0F+SimpleOresItemTier.BRONZE.getAttackSpeed(),
							new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "bronze_hoe"),
				setup(new HoeItem(SimpleOresItemTier.THYRIUM, -0.0F+SimpleOresItemTier.THYRIUM.getAttackSpeed(),
							new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "thyrium_hoe"),
				setup(new HoeItem(SimpleOresItemTier.SINISITE, -0.0F+SimpleOresItemTier.SINISITE.getAttackSpeed(),
							new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "sinisite_hoe"),

                // pickaxes
				setup(new PickaxeItem(SimpleOresItemTier.COPPER, 1, -0.8F+SimpleOresItemTier.COPPER.getAttackSpeed(),
							new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "copper_pickaxe"),
				setup(new PickaxeItem(SimpleOresItemTier.TIN, 1, -0.8F+SimpleOresItemTier.TIN.getAttackSpeed(),
							new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "tin_pickaxe"),
				setup(new PickaxeItem(SimpleOresItemTier.MYTHRIL, 1, -0.8F+SimpleOresItemTier.MYTHRIL.getAttackSpeed(),
							new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "mythril_pickaxe"),
				setup(new PickaxeItem(SimpleOresItemTier.ADAMANTIUM, 1, -0.8F+SimpleOresItemTier.ADAMANTIUM.getAttackSpeed(),
							new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "adamantium_pickaxe"),
				setup(new PickaxeItem(SimpleOresItemTier.ONYX, 1, -0.8F+SimpleOresItemTier.ONYX.getAttackSpeed(),
							new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "onyx_pickaxe"),
				setup(new PickaxeItem(SimpleOresItemTier.STEEL, 1, -0.8F+SimpleOresItemTier.STEEL.getAttackSpeed(),
							new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "steel_pickaxe"),
				setup(new PickaxeItem(SimpleOresItemTier.BRONZE, 1, -0.8F+SimpleOresItemTier.BRONZE.getAttackSpeed(),
							new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "bronze_pickaxe"),
				setup(new PickaxeItem(SimpleOresItemTier.THYRIUM, 1, -0.8F+SimpleOresItemTier.THYRIUM.getAttackSpeed(),
							new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "thyrium_pickaxe"),
				setup(new PickaxeItem(SimpleOresItemTier.SINISITE, 1, -0.8F+SimpleOresItemTier.SINISITE.getAttackSpeed(),
							new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "sinisite_pickaxe"),

                // shears
				// TODO - won't shear leaves, grass.
				setup(new ShearsItem(new Item.Properties().maxDamage(SimpleOresItemTier.COPPER.getMaxUses())
                                                 .group(ModTabGroups.MOD_ITEM_GROUP)), "copper_shears"),
				setup(new ShearsItem(new Item.Properties().maxDamage(SimpleOresItemTier.TIN.getMaxUses())
                                             	.group(ModTabGroups.MOD_ITEM_GROUP)), "tin_shears"),
				setup(new ShearsItem(new Item.Properties().maxDamage(SimpleOresItemTier.MYTHRIL.getMaxUses())
                                         		.group(ModTabGroups.MOD_ITEM_GROUP)), "mythril_shears"),
				setup(new ShearsItem(new Item.Properties().maxDamage(SimpleOresItemTier.ADAMANTIUM.getMaxUses())
                                                 .group(ModTabGroups.MOD_ITEM_GROUP)), "adamantium_shears"),
				setup(new ShearsItem(new Item.Properties().maxDamage(SimpleOresItemTier.ONYX.getMaxUses())
                                                 .group(ModTabGroups.MOD_ITEM_GROUP)), "onyx_shears"),
				setup(new ShearsItem(new Item.Properties().maxDamage(SimpleOresItemTier.STEEL.getMaxUses())
												.group(ModTabGroups.MOD_ITEM_GROUP)), "steel_shears"),
				setup(new ShearsItem(new Item.Properties().maxDamage(SimpleOresItemTier.BRONZE.getMaxUses())
												.group(ModTabGroups.MOD_ITEM_GROUP)), "bronze_shears"),

				// shovels
				setup(new ShovelItem(SimpleOresItemTier.COPPER, 1.5F, -1.0F+SimpleOresItemTier.COPPER.getAttackSpeed(),
							 new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "copper_shovel"),
				setup(new ShovelItem(SimpleOresItemTier.TIN, 1.5F, -1.0F+SimpleOresItemTier.TIN.getAttackSpeed(),
							 new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "tin_shovel"),
				setup(new ShovelItem(SimpleOresItemTier.MYTHRIL, 1.5F, -1.0F+SimpleOresItemTier.MYTHRIL.getAttackSpeed(),
							 new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "mythril_shovel"),
				setup(new ShovelItem(SimpleOresItemTier.ADAMANTIUM, 1.5F, -1.0F+SimpleOresItemTier.ADAMANTIUM.getAttackSpeed(),
							 new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "adamantium_shovel"),
				setup(new ShovelItem(SimpleOresItemTier.ONYX, 1.5F, -1.0F+SimpleOresItemTier.ONYX.getAttackSpeed(),
						 	new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "onyx_shovel"),
				setup(new ShovelItem(SimpleOresItemTier.STEEL, 1.5F, -1.0F+SimpleOresItemTier.STEEL.getAttackSpeed(),
							new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "steel_shovel"),
				setup(new ShovelItem(SimpleOresItemTier.BRONZE, 1.5F, -1.0F+SimpleOresItemTier.BRONZE.getAttackSpeed(),
							new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "bronze_shovel"),
				setup(new ShovelItem(SimpleOresItemTier.THYRIUM, 1.5F, -1.0F+SimpleOresItemTier.THYRIUM.getAttackSpeed(),
							new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "thyrium_shovel"),
				setup(new ShovelItem(SimpleOresItemTier.SINISITE, 1.5F, -1.0F+SimpleOresItemTier.SINISITE.getAttackSpeed(),
							new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "sinisite_shovel"),

				// swords
				setup(new SwordItem(SimpleOresItemTier.COPPER, 3, -0.4F+SimpleOresItemTier.COPPER.getAttackSpeed(),
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "copper_sword"),
				setup(new SwordItem(SimpleOresItemTier.TIN, 3, -0.4F+SimpleOresItemTier.TIN.getAttackSpeed(),
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "tin_sword"),
				setup(new SwordItem(SimpleOresItemTier.MYTHRIL, 3, -0.4F+SimpleOresItemTier.MYTHRIL.getAttackSpeed(),
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "mythril_sword"),
				setup(new SwordItem(SimpleOresItemTier.ADAMANTIUM, 3, -0.4F+SimpleOresItemTier.ADAMANTIUM.getAttackSpeed(),
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "adamantium_sword"),
				setup(new SwordItem(SimpleOresItemTier.ONYX, 3, -0.4F+SimpleOresItemTier.ONYX.getAttackSpeed(),
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "onyx_sword"),
				setup(new SwordItem(SimpleOresItemTier.STEEL, 3, -0.4F+SimpleOresItemTier.STEEL.getAttackSpeed(),
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "steel_sword"),
				setup(new SwordItem(SimpleOresItemTier.BRONZE, 3, -0.4F+SimpleOresItemTier.BRONZE.getAttackSpeed(),
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "bronze_sword"),
				setup(new SwordItem(SimpleOresItemTier.THYRIUM, 3, -0.4F+SimpleOresItemTier.THYRIUM.getAttackSpeed(),
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "thyrium_sword"),
				setup(new SwordItem(SimpleOresItemTier.SINISITE, 3, -0.4F+SimpleOresItemTier.SINISITE.getAttackSpeed(),
									new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP)), "sinisite_sword")
		);

		// We need to go over the entire registry so that we include any potential Registry Overrides
		for (final Block block : ForgeRegistries.BLOCKS.getValues()) {

			final ResourceLocation blockRegistryName = block.getRegistryName();
			Preconditions.checkNotNull(blockRegistryName, "Registry Name of Block \"" + block + "\" is null! This is not allowed!");

			// Check that the blocks is from our mod, if not, continue to the next block
			if (!blockRegistryName.getNamespace().equals(SimpleOres.MODID)) {
				continue;
			}
			// Make the properties, and make it so that the item will be on our ItemGroup (CreativeTab)
			final Item.Properties properties = new Item.Properties().group(ModTabGroups.MOD_ITEM_GROUP);

			// Create the new BlockItem with the block and it's properties
			final BlockItem blockItem = new BlockItem(block, properties);
			// Setup the new BlockItem with the block's registry name and register it
			registry.register(setup(blockItem, blockRegistryName));
		}
		LOGGER.debug("Registered Items");
	}  // end onRegisterItems()

	@SubscribeEvent
	public static void onModConfigEvent(final ModConfig.ModConfigEvent event)
	{
		final ModConfig config = event.getConfig();

		// Rebake the configs when they change
		if (config.getSpec() == ConfigHolder.SERVER_SPEC) {
			ConfigHelper.bakeServer(config);
		}
	} // onModConfigEvent

    public static <T extends IForgeRegistryEntry<T>> T setup(final T entry,
													   final String name)
	{
		return setup(entry, new ResourceLocation(SimpleOres.MODID, name));
	}

	public static <T extends IForgeRegistryEntry<T>> T setup(final T entry,
													   final ResourceLocation registryName)
	{
		entry.setRegistryName(registryName);
		return entry;
	}

} // end class ModEventSubscriber
