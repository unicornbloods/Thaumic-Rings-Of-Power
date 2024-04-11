package xyz.uniblood.trop.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

import thaumcraft.api.IWarpingGear;
import xyz.uniblood.trop.init.ItemGroups;

public class ItemRing extends Item implements IBauble, IWarpingGear {

    public ItemRing() {
        this.setHasSubtypes(true);
        setMaxDamage(0);
        setMaxStackSize(1);
        setCreativeTab(ItemGroups.TAB_RINGS);
    }

    public List<PotionEffect> getPotionEffects() {
        return new ArrayList<>();
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean advanced) {
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        if (itemstack.getItemDamage() <= 0) {
            return BaubleType.RING;
        } else {
            return null;
        }
    }

    @Override
    public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
        List<PotionEffect> potionEffects = getPotionEffects();
        {
            for (PotionEffect potionEffect : potionEffects) {
                player.addPotionEffect(potionEffect);
            }
        }
    }

    @Override
    public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
    }

    @Override
    public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
        List<PotionEffect> potionEffects = getPotionEffects();
        {
            for (PotionEffect potionEffect : potionEffects) {
                player.addPotionEffect(potionEffect);
            }
        }
        player.addPotionEffect(new PotionEffect(Potion.confusion.id, 200, 1, false));
    }

    @Override
    public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
        return itemstack.getItemDamage() == 0 && player instanceof EntityPlayer;
    }

    @Override
    public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    @Override
    public int getWarp(ItemStack itemstack, EntityPlayer var2) {
        return 3;
    }


}
