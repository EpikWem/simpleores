package mod.alexndr.simpleores.content;

import mod.alexndr.simpleores.init.ModItems;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyLoadBase;

import java.util.function.Supplier;

public enum SimpleOresItemTier implements IItemTier
{
   COPPER(1, 185, 4.0F, 1.5F, -2.0F, 8, ()->{ return Ingredient.fromItems( ModItems.copper_ingot); }),
   TIN(1, 220, 3.5F, 1.0F, -1.8F, 8, ()->{ return Ingredient.fromItems( ModItems.tin_ingot); }),
   MYTHRIL(2, 928,8.0F, 3.5F, -2.4F, 12, ()->{ return Ingredient.fromItems( ModItems.mythril_ingot); }),
   ADAMANTIUM(2, 1150, 14.0F, 3.0F, -2.2F, 3, ()->{ return Ingredient.fromItems( ModItems.adamantium_ingot); }),
   ONYX(4, 3280, 10.0F, 5.0F, -2.6F, 15, ()->{ return Ingredient.fromItems( ModItems.onyx_gem); }),
   STEEL(2, 870, 6.0F, 2.0F, -2.5F, 10, ()->{ return Ingredient.fromItems( ModItems.steel_ingot); }),
   BRONZE(2, 1086, 5.5F, 2.0F, -2.3F, 14, ()->{ return Ingredient.fromItems( ModItems.bronze_ingot); }),
   THYRIUM(4, 2860, 16.0F, 5.5F, -2.3F, 24, ()->{ return Ingredient.fromItems( ModItems.thyrium_ingot); }),
   SINISITE(5, 4820, 18.0F, 8.0F, -2.5F, 18, ()->{ return Ingredient.fromItems( ModItems.sinisite_ingot); });

   private final int harvestLevel;
   private final int maxUses;
   private final float efficiency;
   private final float attackDamage;
   private final float attackSpeed;
   private final int enchantability;
   private final LazyLoadBase<Ingredient> repairMaterial;

   private SimpleOresItemTier(int harvestLevelIn, int maxUsesIn, float efficiencyIn, float attackDamageIn, float attackSpeedIn, int enchantabilityIn,
                    Supplier<Ingredient> repairMaterialIn){
      this.harvestLevel = harvestLevelIn;
      this.maxUses = maxUsesIn;
      this.efficiency = efficiencyIn;
      this.attackDamage = attackDamageIn;
      this.attackSpeed = attackSpeedIn;
      this.enchantability = enchantabilityIn;
      this.repairMaterial = new LazyLoadBase<>(repairMaterialIn);
   }

   @Override
   public int getMaxUses() {
      return this.maxUses;
   }

   @Override
   public float getEfficiency() {
      return this.efficiency;
   }

   @Override
   public float getAttackDamage() {
      return this.attackDamage;
   }
   
   public float getAttackSpeed() {
      return this.attackSpeed;
   }

   @Override
   public int getHarvestLevel() {
      return this.harvestLevel;
   }

   @Override
   public int getEnchantability() {
      return this.enchantability;
   }

   @Override
   public Ingredient getRepairMaterial() {
      return this.repairMaterial.getValue();
   }
}  // end class SimpleOresItemTier
