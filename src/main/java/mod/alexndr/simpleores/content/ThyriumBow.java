package mod.alexndr.simpleores.content;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


public class ThyriumBow extends BowItem
{
	private static final int EFFICIENCY = 60;
    private Random rng;
	
    public ThyriumBow(Properties builder){
        super(builder);
        rng = new Random();
    }

    @Override
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft)
    {
        // add the default enchantments for Onyx bow.
        Map<Enchantment,Integer> oldEnchants = EnchantmentHelper.getEnchantments(stack);
        stack = this.addThyriumEnchantments(oldEnchants, stack);

        super.onPlayerStoppedUsing(stack, worldIn, entityLiving, timeLeft);

        // remove temporary intrinsic enchantments.
        EnchantmentHelper.setEnchantments(oldEnchants, stack);
    }

    private ItemStack addThyriumEnchantments(Map<Enchantment,Integer> oldEnch, ItemStack stack)
    {
        if (stack.isEmpty()) return stack;

        Map<Enchantment,Integer> enchMap = new HashMap<>(oldEnch);

        // add intrinsic POWER enchantment only if bow does not already have
        // one >= 2.
        if (! (enchMap.containsKey(Enchantments.POWER) && enchMap.get(Enchantments.POWER) > 1) ){
            enchMap.put(Enchantments.POWER, 2);
        }
        
        if (! enchMap.containsKey(Enchantments.PUNCH) )
        	enchMap.put(Enchantments.PUNCH, 1);
        
        // add intrinsic INFINITY enchantment if RNG <= EFFICIENCY.
        if (! enchMap.containsKey(Enchantments.INFINITY)) {
            if (rng.nextInt(100) < EFFICIENCY) enchMap.put(Enchantments.INFINITY, 1);
        }

        // add intrinsic enchantments, if any.
        if (enchMap.size() > 0) {
            EnchantmentHelper.setEnchantments(enchMap, stack);
        }
        return stack;
    } // end addThyriumEnchantments()

    @Override
    @OnlyIn(Dist.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
    {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        tooltip.add((new TranslationTextComponent("tips.damage_tooltip")).applyTextStyle(TextFormatting.GREEN));
        tooltip.add((new TranslationTextComponent("tips.punch_tooltip")).applyTextStyle(TextFormatting.GREEN));
    }
}  // end class ThyriumBow
