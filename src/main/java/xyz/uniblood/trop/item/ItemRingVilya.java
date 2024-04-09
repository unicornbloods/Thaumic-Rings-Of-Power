package xyz.uniblood.trop.item;

import java.util.ArrayList;
import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

public class ItemRingVilya extends ItemRing {

    @Override
    public List<PotionEffect> getPotionEffects() {
        List<PotionEffect> potionEffects = new ArrayList<>();
        potionEffects.add(new PotionEffect(Potion.moveSpeed.getId(), 20, 1));
        potionEffects.add(new PotionEffect(Potion.jump.getId(), 20, 1));
        return potionEffects;
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean advanced) {
        String potionColorCode = "" + EnumChatFormatting.DARK_GREEN;
        String loreColorCode = "" + EnumChatFormatting.BLUE + EnumChatFormatting.ITALIC;
        list.add(
                potionColorCode
                        + "Speed");
        list.add(
                potionColorCode
                        + "Jump Boost");
        list.add(
                loreColorCode
                        + "Three Rings for the Elven-kings under the sky");
    }
}
