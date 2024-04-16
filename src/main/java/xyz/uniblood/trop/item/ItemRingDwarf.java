package xyz.uniblood.trop.item;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;
import java.util.List;

public class ItemRingDwarf {

    public static List<PotionEffect> getPotionEffects() {
        List<PotionEffect> potionEffects = new ArrayList<>();
        potionEffects.add(new PotionEffect(Potion.moveSpeed.getId(), 20, 1));
        potionEffects.add(new PotionEffect(Potion.resistance.getId(), 20, 1));
        return potionEffects;
    }

    public static List addInformation() {
        List list = new ArrayList();
        String potionColorCode = "" + EnumChatFormatting.DARK_GREEN;
        String loreColorCode = "" + EnumChatFormatting.BLUE + EnumChatFormatting.ITALIC;
        list.add(
                potionColorCode
                        + "Speed");
        list.add(
                potionColorCode
                        + "Resistance");
        list.add(
                loreColorCode
                        + "Seven for the Dwarf-lords in their halls of stone");
        return list;
    }
}
