package xyz.uniblood.trop.item;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.IIcon;
import thaumcraft.api.IRunicArmor;
import thaumcraft.api.IWarpingGear;
import xyz.uniblood.trop.init.ItemGroups;

import java.util.ArrayList;
import java.util.List;

import static xyz.uniblood.trop.Config.greatRingRunicShield;
import static xyz.uniblood.trop.Config.normalRingWarp;

public class ItemRing extends Item implements IBauble, IWarpingGear, IRunicArmor {
    public IIcon[] icon = new IIcon[20];

    private final ItemRingGreat ringGreat = new ItemRingGreat();

    public ItemRing() {
        this.setHasSubtypes(true);
        setMaxDamage(0);
        setMaxStackSize(1);
        setCreativeTab(ItemGroups.TAB_RINGS);
        this.setHasSubtypes(true);
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister ir) {
        this.icon[0] = ir.registerIcon("trop:ring_great");
        this.icon[1] = ir.registerIcon("trop:ring_narya");
        this.icon[2] = ir.registerIcon("trop:ring_nenya");
        this.icon[3] = ir.registerIcon("trop:ring_vilya");
        this.icon[4] = ir.registerIcon("trop:ring_thror");
        this.icon[5] = ir.registerIcon("trop:ring_thulin");
        this.icon[6] = ir.registerIcon("trop:ring_khibil");
        this.icon[7] = ir.registerIcon("trop:ring_farin");
        this.icon[8] = ir.registerIcon("trop:ring_khain");
        this.icon[9] = ir.registerIcon("trop:ring_baraz");
        this.icon[10] = ir.registerIcon("trop:ring_burin");
        this.icon[11] = ir.registerIcon("trop:ring_murazor");
        this.icon[12] = ir.registerIcon("trop:ring_hoarmurath");
        this.icon[13] = ir.registerIcon("trop:ring_akhorahil");
        this.icon[14] = ir.registerIcon("trop:ring_adunaphel");
        this.icon[15] = ir.registerIcon("trop:ring_jiindur");
        this.icon[16] = ir.registerIcon("trop:ring_khamul");
        this.icon[17] = ir.registerIcon("trop:ring_uvatha");
        this.icon[18] = ir.registerIcon("trop:ring_ren");
        this.icon[19] = ir.registerIcon("trop:ring_dwar");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int par1) {
        return this.icon[par1];
    }

    public String getUnlocalizedName(ItemStack par1ItemStack) {
        return super.getUnlocalizedName() + "." + par1ItemStack.getItemDamage();
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par11List) {
        par11List.add(new ItemStack(this, 1, 0));
        par11List.add(new ItemStack(this, 1, 1));
        par11List.add(new ItemStack(this, 1, 2));
        par11List.add(new ItemStack(this, 1, 3));
        par11List.add(new ItemStack(this, 1, 4));
        par11List.add(new ItemStack(this, 1, 5));
        par11List.add(new ItemStack(this, 1, 6));
        par11List.add(new ItemStack(this, 1, 7));
        par11List.add(new ItemStack(this, 1, 8));
        par11List.add(new ItemStack(this, 1, 9));
        par11List.add(new ItemStack(this, 1, 10));
        par11List.add(new ItemStack(this, 1, 11));
        par11List.add(new ItemStack(this, 1, 12));
        par11List.add(new ItemStack(this, 1, 13));
        par11List.add(new ItemStack(this, 1, 14));
        par11List.add(new ItemStack(this, 1, 15));
        par11List.add(new ItemStack(this, 1, 16));
        par11List.add(new ItemStack(this, 1, 17));
        par11List.add(new ItemStack(this, 1, 18));
        par11List.add(new ItemStack(this, 1, 19));
    }

    public List<PotionEffect> getPotionEffects(ItemStack itemStack) {
        return switch (itemStack.getItemDamage()) {
            case 1 -> ItemRingNarya.getPotionEffects();
            case 2 -> ItemRingNenya.getPotionEffects();
            case 3 -> ItemRingVilya.getPotionEffects();
            case 4, 5, 6, 7, 8, 9, 10 -> ItemRingDwarf.getPotionEffects();
            case 11, 12, 13, 14, 15, 16, 17, 18, 19 -> ItemRingMan.getPotionEffects();
            default -> new ArrayList<>();
        };
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean advanced) {
        switch (itemStack.getItemDamage()) {
            case 0 -> list.addAll(ItemRingGreat.addInformation());
            case 1 -> list.addAll(ItemRingNarya.addInformation());
            case 2 -> list.addAll(ItemRingNenya.addInformation());
            case 3 -> list.addAll(ItemRingVilya.addInformation());
            case 4, 5, 6, 7, 8, 9, 10 -> list.addAll(ItemRingDwarf.addInformation());
            case 11, 12, 13, 14, 15, 16, 17, 18, 19 -> list.addAll(ItemRingMan.addInformation());
        }
    }

    @Override
    public BaubleType getBaubleType(ItemStack itemstack) {
        return BaubleType.RING;
    }

    @Override
    public void onWornTick(ItemStack itemStack, EntityLivingBase player) {
        if (itemStack.getItemDamage() == 0) {
            ringGreat.onWornTick(itemStack, player);
        } else {
            List<PotionEffect> potionEffects = getPotionEffects(itemStack);
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
        List<PotionEffect> potionEffects = getPotionEffects(itemstack);
        for (PotionEffect potionEffect : potionEffects) {
            player.addPotionEffect(potionEffect);
        }
        player.addPotionEffect(new PotionEffect(Potion.confusion.id, 200, 1, false));
    }

    @Override
    public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
        return player instanceof EntityPlayer;
    }

    @Override
    public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
        return true;
    }

    @Override
    public int getWarp(ItemStack itemStack, EntityPlayer var2) {
        return switch (itemStack.getItemDamage()) {
            case 0 -> ringGreat.getWarp(itemStack);
            case 1, 2, 3 -> 0; // The elven rings don't get warp by default
            default -> normalRingWarp;
        };
    }


    @Override
    public int getRunicCharge(ItemStack itemStack) {
        int runicCharge = 0;
        if (itemStack.getItemDamage() == 0) {
            runicCharge = greatRingRunicShield;
        }
        return runicCharge;
    }
}
