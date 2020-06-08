package thedarkcolour.futuremc.enchantment

import net.minecraft.enchantment.Enchantment
import net.minecraftforge.common.util.EnumHelper
import thedarkcolour.futuremc.item.CrossbowItem
import thedarkcolour.futuremc.item.TridentItem

object Enchantments {
    val TRIDENT = EnumHelper.addEnchantmentType("weapons") { item -> item is TridentItem }!!

    val LEALTAD: Enchantment = EnchantmentLoyalty()
    val CONDUCTIVIDAD: Enchantment = EnchantmentChanneling()
    val RIPTIDE: Enchantment = EnchantmentRiptide()
    val EMPALAMIENTO: Enchantment = EnchantmentImpaling()

    val CROSSBOW = EnumHelper.addEnchantmentType("weapons") { item -> item is CrossbowItem }!!

    val QUICK_CHARGE: Enchantment = EnchantmentQuickCharge()
    val MULTISHOT: Enchantment = EnchantmentMultishot()
    val PIERCING: Enchantment = EnchantmentPiercing()
}