package xyz.uniblood.trop.item;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;

import java.util.ArrayList;
import java.util.List;

public class ItemRingNarya {

    public static List<PotionEffect> getPotionEffects() {
        List<PotionEffect> potionEffects = new ArrayList<>();
        potionEffects.add(new PotionEffect(Potion.fireResistance.getId(), 20));
        return potionEffects;
    }

    public static List addInformation() {
        List list = new ArrayList();
        String potionColorCode = "" + EnumChatFormatting.DARK_GREEN;
        String loreColorCode = "" + EnumChatFormatting.BLUE + EnumChatFormatting.ITALIC;
        list.add(
                potionColorCode
                        + "Fire Resistance");
        list.add(
                loreColorCode
                        + "Three Rings for the Elven-kings under the sky");

        return list;
    }


}
