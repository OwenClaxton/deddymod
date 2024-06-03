package net.deddybones.techplusplus.util;

/*

This enumeration is to keep track of all armour sets, such that I can ensure balance.
I also log vanilla numbers in here (or, if I override vanilla items, their values) for comparison.
Note that some entries are armour-only, and some are tool-only.
Also note that 'level' is deprecated, and instead tier ordering is handled by forge.

*/
import net.deddybones.techplusplus.util.EquipmentCollection.EquipmentName;
import com.google.common.collect.Maps;
import net.minecraft.Util;

import java.util.Map;

@SuppressWarnings("unused")
public enum TierNumerics {
    //           LVL    NumUse  Tool                    Armour                                  Sword          Shovel         Pickaxe        Axe             Hoe
    //           L      #       bASB    bADB    tEV     B  L  C  H  Bd  DM   TGH   KBR   aEV    ADB   ASB      ADB   ASB      ADB   ASB      ADB   ASB       ADB   ASB
    WOOD(        0,     59,     2.0F,   0.0F,   15,     0, 0, 0, 0, 0,  0,   0.0F, 0.0F, 0 ,    3.0F, -2.4F,   1.5F, -3.0F,   1.0F, -2.8F,   6.0F, -3.2F,    0.0F, -3.0F), // vanilla, r.
    GOLD(        0,     32,     12.0F,  0.0F,   22,     1, 3, 5, 2, 7,  7,   0.0F, 0.0F, 25,    3.0F, -2.4F,   1.5F, -3.0F,   1.0F, -2.8F,   6.0F, -3.0F,    0.0F, -3.0F), // vanilla, !! USING !!
    PRIMITIVE(   0,     50,     1.0f,   0.0f,   0,      0, 0, 0, 0, 0,  0,   0.0F, 0.0F, 0 ,    3.0F, -2.4F,   1.5F, -3.0F,   1.0F, -2.8F,   6.0F, -3.1F,   -2.0F, -1.0F),
    LEATHER(     0,     0,      0.0f,   0.0f,   0,      1, 2, 3, 1, 3,  5,   0.0F, 0.0F, 15,    0.0F,  0.0F,   0.0F,  0.0F,   0.0F,  0.0F,   0.0F,  0.0F,    0.0F,  0.0F),
    STONE(       1,     131,    4.0F,   1.0F,   5,      0, 0, 0, 0, 0,  0,   0.0F, 0.0F, 0 ,    0.0F,  0.0F,   0.0F,  0.0F,   0.0F,  0.0F,   0.0F,  0.0F,    0.0F,  0.0F), // vanilla, r.
    COPPER(      1,     131,    2.0f,   1.0f,   8,      1, 3, 4, 1, 4,  9,   0.0F, 0.0F, 8 ,    3.0F, -2.4F,   1.5F, -3.0F,   1.0F, -2.8F,   6.0F, -3.1F,   -2.0F, -1.0F),
    TIN(         1,     131,    2.0f,   1.0f,   8,      1, 3, 4, 1, 4,  9,   0.0F, 0.0F, 8 ,    3.0F, -2.4F,   1.5F, -3.0F,   1.0F, -2.8F,   6.0F, -3.1F,   -2.0F, -1.0F),
    CHAIN(       1,     0,      0.0F,   0.0F,   0,      1, 4, 5, 2, 4,  15,  0.0F, 0.0F, 12,    0.0F,  0.0F,   0.0F,  0.0F,   0.0F,  0.0F,   0.0F,  0.0F,    0.0F,  0.0F), // vanilla, r.
    BRONZE(      2,     220,    5.0f,   1.5f,   10,     2, 4, 5, 2, 5,  12,  0.0F, 0.0F, 10,    3.0F, -2.4F,   1.5F, -3.0F,   1.0F, -2.8F,   6.0F, -3.1F,   -2.0F, -1.0F),
    TURTLE(      2,     0,      0.0F,   0.0F,   0,      2, 5, 6, 2, 5,  25,  0.0F, 0.0F, 9 ,    0.0F,  0.0F,   0.0F,  0.0F,   0.0F,  0.0F,   0.0F,  0.0F,    0.0F,  0.0F), // vanilla, !! USING !!
    IRON(        2,     250,    6.0F,   2.0F,   14,     2, 5, 6, 2, 5,  15,  0.0F, 0.0F, 9 ,    3.0F, -2.4F,   1.5F, -3.0F,   1.0F, -2.8F,   6.0F, -3.1F,   -2.0F, -1.0F), // vanilla, !! USING !!
    PLASTIMETAL( 3,     500,    8.0f,   2.5f,   18,     3, 5, 7, 3, 7,  27,  2.0F, 0.1F, 10,    3.0F, -2.4F,   1.5F, -3.0F,   1.0F, -2.8F,   6.0F, -3.1F,   -3.0F, -0.5F),
    DIAMOND(     3,     1561,   8.0F,   3.0F,   10,     3, 6, 8, 3, 11, 33,  2.0F, 0.0F, 10,    3.0F, -2.4F,   1.5F, -3.0F,   1.0F, -2.8F,   5.0F, -3.0F,   -3.0F,  0.0F), // vanilla, r.
    NETHERITE(   4,     2031,   9.0F,   4.0F,   15,     3, 6, 8, 3, 11, 37,  3.0F, 0.1F, 15,    3.0F, -2.4F,   1.5F, -3.0F,   1.0F, -2.8F,   5.0F, -3.0F,   -4.0F,  0.0F), // vanilla, !! USING !!
    ;

    private final int level;
    private final int numUses;
    private final float baseAttackSpeedBonus;
    private final float baseAttackDamageBonus;
    private final int toolEnchantmentValue;
    private final int bootsProtection;
    private final int leggingsProtection;
    private final int chestplateProtection;
    private final int helmetProtection;
    private final int bodyProtection;
    private final int durabilityMultiplier;
    private final float toughness;
    private final float knockbackResistance;
    private final int armorEnchantmentValue;
    private final float swordAttackDamageBonus;
    private final float shovelAttackDamageBonus;
    private final float pickaxeAttackDamageBonus;
    private final float axeAttackDamageBonus;
    private final float hoeAttackDamageBonus;
    private final float swordAttackSpeedBonus;
    private final float shovelAttackSpeedBonus;
    private final float pickaxeAttackSpeedBonus;
    private final float axeAttackSpeedBonus;
    private final float hoeAttackSpeedBonus;

    public static final Map<String, TierNumerics> NUMERICS_MAP = Util.make(Maps.newHashMap(),
            (instance) -> {
                instance.put("wood",        TierNumerics.WOOD);
                instance.put("primitive",   TierNumerics.PRIMITIVE);
                instance.put("gold",        TierNumerics.GOLD);
                instance.put("leather",     TierNumerics.LEATHER);
                instance.put("stone",       TierNumerics.STONE);
                instance.put("copper",      TierNumerics.COPPER);
                instance.put("tin",         TierNumerics.TIN);
                instance.put("chain",       TierNumerics.CHAIN);
                instance.put("bronze",      TierNumerics.BRONZE);
                instance.put("turtle",      TierNumerics.TURTLE);
                instance.put("iron",        TierNumerics.IRON);
                instance.put("plastimetal", TierNumerics.PLASTIMETAL);
                instance.put("diamond",     TierNumerics.DIAMOND);
                instance.put("netherite",   TierNumerics.NETHERITE);
            });

    TierNumerics(int level, int numUses, float baseAttackSpeedBonus, float baseAttackDamageBonus, int toolEnchantmentValue,
                 int bootsProtection, int leggingsProtection, int chestplateProtection, int helmetProtection,
                 int bodyProtection, int durabilityMultiplier, float toughness, float knockbackResistance,
                 int armorEnchantmentValue, float swordAttackDamageBonus, float shovelAttackDamageBonus,
                 float pickaxeAttackDamageBonus, float axeAttackDamageBonus, float hoeAttackDamageBonus,
                 float swordAttackSpeedBonus, float shovelAttackSpeedBonus, float pickaxeAttackSpeedBonus,
                 float axeAttackSpeedBonus, float hoeAttackSpeedBonus) {

        this.level                    = level;
        this.numUses                  = numUses;
        this.baseAttackSpeedBonus     = baseAttackSpeedBonus;
        this.baseAttackDamageBonus    = baseAttackDamageBonus;
        this.toolEnchantmentValue     = toolEnchantmentValue;
        this.bootsProtection          = bootsProtection;
        this.leggingsProtection       = leggingsProtection;
        this.chestplateProtection     = chestplateProtection;
        this.helmetProtection         = helmetProtection;
        this.bodyProtection           = bodyProtection;
        this.durabilityMultiplier     = durabilityMultiplier;
        this.toughness                = toughness;
        this.knockbackResistance      = knockbackResistance;
        this.armorEnchantmentValue    = armorEnchantmentValue;
        this.swordAttackDamageBonus   = swordAttackDamageBonus;
        this.shovelAttackDamageBonus  = shovelAttackDamageBonus;
        this.pickaxeAttackDamageBonus = pickaxeAttackDamageBonus;
        this.axeAttackDamageBonus     = axeAttackDamageBonus;
        this.hoeAttackDamageBonus     = hoeAttackDamageBonus;
        this.swordAttackSpeedBonus    = swordAttackSpeedBonus;
        this.shovelAttackSpeedBonus   = shovelAttackSpeedBonus;
        this.pickaxeAttackSpeedBonus  = pickaxeAttackSpeedBonus;
        this.axeAttackSpeedBonus      = axeAttackSpeedBonus;
        this.hoeAttackSpeedBonus      = hoeAttackSpeedBonus;
    }

    public int getLevel() {
        return level;
    }

    public float getToughness() {
        return toughness;
    }

    public float getKnockbackResistance() {
        return knockbackResistance;
    }

    public int getNumUses() {
        return numUses;
    }

    public float getAttackDamageBonus(EquipmentName equipment) {
        return switch (equipment) {
            case SWORD -> this.swordAttackDamageBonus;
            case SHOVEL -> this.shovelAttackDamageBonus;
            case PICKAXE -> this.pickaxeAttackDamageBonus;
            case AXE -> this.axeAttackDamageBonus;
            case HOE -> this.hoeAttackDamageBonus;
            default -> 0.0F;
        };
    }

    public float getAttackSpeedBonus(EquipmentName equipment) {
        return switch (equipment) {
            case SWORD -> this.swordAttackSpeedBonus;
            case SHOVEL -> this.shovelAttackSpeedBonus;
            case PICKAXE -> this.pickaxeAttackSpeedBonus;
            case AXE -> this.axeAttackSpeedBonus;
            case HOE -> this.hoeAttackSpeedBonus;
            default -> 0.0F;
        };
    }

    public float getBaseAttackSpeedBonus() {
        return baseAttackSpeedBonus;
    }

    public float getBaseAttackDamageBonus() {
        return baseAttackDamageBonus;
    }

    public int getToolEnchantmentValue() {
        return toolEnchantmentValue;
    }

    public int getArmorEnchantmentValue() {
        return armorEnchantmentValue;
    }

    public int getBootsProtection() {
        return bootsProtection;
    }

    public int getLeggingsProtection() {
        return leggingsProtection;
    }

    public int getChestplateProtection() {
        return chestplateProtection;
    }

    public int getHelmetProtection() {
        return helmetProtection;
    }

    public int getBodyProtection() {
        return bodyProtection;
    }

    public int getDurabilityMultiplier() {
        return durabilityMultiplier;
    }
}
