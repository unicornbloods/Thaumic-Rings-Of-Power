package xyz.uniblood.trop.item;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;

public class ItemRingMan {

    public static List<PotionEffect> getPotionEffects() {
        List<PotionEffect> potionEffects = new ArrayList<>();
        potionEffects.add(new PotionEffect(Potion.damageBoost.getId(), 20, 1));
        potionEffects.add(new PotionEffect(Potion.nightVision.getId(), 220));
        return potionEffects;
    }

    public static List addInformation() {
        List list = new ArrayList();
        String potionColorCode = "" + EnumChatFormatting.DARK_GREEN;
        String loreColorCode = "" + EnumChatFormatting.BLUE + EnumChatFormatting.ITALIC;
        list.add(
                potionColorCode
                        + "Strength");
        list.add(
                potionColorCode
                        + "Night Vision");
        list.add(
                loreColorCode
                        + "Nine for Mortal Men doomed to die");
        return list;
    }
}
