package xyz.uniblood.trop.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import thaumcraft.api.ThaumcraftApiHelper;
import xyz.uniblood.trop.Config;
import xyz.uniblood.trop.util.TropTextHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static xyz.uniblood.trop.Config.maximumGreatRingWarp;
import static xyz.uniblood.trop.Config.minimumGreatRingWarp;


public class ItemRingGreat {

    private static final String NBT_TAG_FORGE_DATA = "Trop";
    private static final String NBT_TAG_MIND_CORRUPTION = "MindCorruption";
    private static final String NBT_TAG_WARP = "warp";
    public Random random = new Random();

    public static List addInformation() {
        List list = new ArrayList();
        String potionColorCode = "" + EnumChatFormatting.DARK_GREEN;
        String loreColorCode = "" + EnumChatFormatting.BLUE + EnumChatFormatting.ITALIC;
        String warningColorCode = "" + EnumChatFormatting.DARK_RED;
        list.add(
                potionColorCode
                        + "Invisibility");
        list.add(
                loreColorCode
                        + "One Ring to rule them all");
        list.add(
                warningColorCode
                        + "The Ring whispers to you, promising power.");
        list.add(
                warningColorCode
                        + "But at what cost?");

        return list;
    }

    public void onWornTick(ItemStack stack, EntityLivingBase player) {
        if (!player.isInvisible()) {
            player.setInvisible(true);
        }

        // Stop doing this 20 times per second...
        if (random.nextInt(20) > 0) {
            return;
        }

        /*
         * Why dump to a new tag when you can just grab the tag and work with it? NBTTagCompound tag = new
         * NBTTagCompound(); ((EntityPlayer) player).writeToNBT(tag); NBTTagCompound forgeTag =
         * tag.getCompoundTag("ForgeData");
         */

        // Retrieve Players NBT Data

        NBTTagCompound forgeTag = getOrSetForgeTag((EntityPlayer) player);

        int corruption = 0;

        // get corruption levels, init to 0 if not existing yet
        if (forgeTag.hasKey(NBT_TAG_MIND_CORRUPTION)) {
            corruption = forgeTag.getInteger(NBT_TAG_MIND_CORRUPTION);
        } else {
            forgeTag.setInteger(NBT_TAG_MIND_CORRUPTION, 0);
        }

        if (!player.worldObj.isRemote) {
            // EMT.LOGGER.info( String.format( "Total Corruption: %d ItemWarpLevel: %d", corruption, getItemWarpLevel(
            // stack, false ) ));

            if (corruption == 0) {
                ((EntityPlayer) player).addChatMessage(
                        new ChatComponentText(
                                TropTextHelper.PURPLE + "You have worn the Ring. Your soul has now been forever "
                                        + TropTextHelper.PURPLE
                                        + "tainted. "
                                        + TropTextHelper.RED
                                        + TropTextHelper.ITALIC
                                        + "Beware of wearing the ring. The tainting will only "
                                        + TropTextHelper.RED
                                        + TropTextHelper.ITALIC
                                        + "increase, and strange things will start happening."));
            }
            // First effect should become active after ~5 Minutes
            else if (corruption > 300 && corruption < 1400 && random.nextInt(200) == 0) {
                ((EntityPlayer) player)
                        .addChatMessage(new ChatComponentText(TropTextHelper.PURPLE + "The Ring somehow feels heavy"));
                if (random.nextBoolean()) {
                    player.addPotionEffect(new PotionEffect(Potion.blindness.id, 500, 2, false));
                } else {
                    player.addPotionEffect(new PotionEffect(Potion.confusion.id, 500, 2, false));
                }
            } else if (corruption >= 1400 && corruption < 3800 && random.nextInt(200) == 0) {
                player.addPotionEffect(new PotionEffect(Potion.poison.id, 500, 1, false));
            } else if (corruption >= 3800 && corruption < 6500 && random.nextInt(200) == 0) {
                player.motionY += 2d;
            } else if (corruption >= 6500 && random.nextInt(10000) == 0) {
                player.addPotionEffect(new PotionEffect(Potion.wither.id, 100, 2, false));
            }

            if (corruption >= 300) { // worn more than 5 minutes
                if (random.nextInt(300) == 0) { // randomly every 5 min
                    getItemWarpLevel(stack, true);
                    ((EntityPlayer) player).addChatMessage(
                            new ChatComponentText(TropTextHelper.PURPLE + "The Ring suddenly starts to glow purple"));
                }
                if (random.nextInt(600) == 0) { // randomly every 10 minutes
                    if (Config.greatRingPermanentWarp) {
                        // adds 1 perm warp
                        ThaumcraftApiHelper.addWarpToPlayer((EntityPlayer) player, 1, false);
                    }
                    // adds 0-1 sticky warp
                    ThaumcraftApiHelper.addStickyWarpToPlayer((EntityPlayer) player, random.nextInt(1));
                    ((EntityPlayer) player).addChatMessage(
                            new ChatComponentText(TropTextHelper.PURPLE + "Your body suddenly starts to glow purple"));
                }
                if (corruption >= 3600) { // if you wear it for more than an hour total
                    if (random.nextInt(1800) == 0) { // randomly every 30 minutes
                        if (Config.greatRingPermanentWarp) {
                            // adds 0-5 perm warp
                            ThaumcraftApiHelper.addWarpToPlayer((EntityPlayer) player, random.nextInt(5), false);
                        }
                        // adds 5-10 sticky warp
                        ThaumcraftApiHelper.addStickyWarpToPlayer((EntityPlayer) player, 5 + random.nextInt(5));
                        // adds 10-50 temp warp but, this is ultra easy to remove
                        ThaumcraftApiHelper.addWarpToPlayer((EntityPlayer) player, 10 + random.nextInt(40), true);

                        ((EntityPlayer) player).addChatMessage(
                                new ChatComponentText(
                                        TropTextHelper.PURPLE
                                                + "Your body resonates with the Ring and suddenly starts to glow purple ominously"));
                    }
                }
            }
        }
        forgeTag.setInteger(NBT_TAG_MIND_CORRUPTION, ++corruption);
//        tag.setTag(NBT_TAG_FORGE_DATA, forgeTag);
//        ((EntityPlayer) player).readFromNBT(tag);
    }

    public int getWarp(ItemStack itemstack) {
        return getItemWarpLevel(itemstack, false);
    }

    private int getItemWarpLevel(ItemStack stack, boolean increment) {
        NBTTagCompound tWarpTag = new NBTTagCompound();
        int tWarpLevel = 0;
        if (stack.hasTagCompound()) {
            if (stack.getTagCompound().hasKey(NBT_TAG_WARP)) {
                tWarpLevel = stack.getTagCompound().getInteger(NBT_TAG_WARP);
                if (increment) {
                    stack.getTagCompound().setInteger(NBT_TAG_WARP, ++tWarpLevel);
                }
            }
        } else {
            tWarpTag.setInteger(NBT_TAG_WARP, 0);
            stack.setTagCompound(tWarpTag);
        }

        return Math.min(minimumGreatRingWarp + tWarpLevel, maximumGreatRingWarp);
    }

    private NBTTagCompound getOrSetForgeTag(EntityPlayer pPlayer) {
        NBTTagCompound tPlayerNBT = pPlayer.getEntityData();
        NBTTagCompound forgeTag = new NBTTagCompound();

        // getCompoundTag already returns the contents of "ForgeData" TagCompound. Changed this to
        // save our value in EMT Sub-Tag
        if (tPlayerNBT.hasKey(NBT_TAG_FORGE_DATA)) forgeTag = tPlayerNBT.getCompoundTag(NBT_TAG_FORGE_DATA);
        else tPlayerNBT.setTag(NBT_TAG_FORGE_DATA, forgeTag);

        return forgeTag;
    }

}
