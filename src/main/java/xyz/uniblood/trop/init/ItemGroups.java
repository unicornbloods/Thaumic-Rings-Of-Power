package xyz.uniblood.trop.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import xyz.uniblood.trop.item.ItemRing;

public class ItemGroups {

    public static final CreativeTabs TAB_RINGS = new CreativeTabs("trop.rings") {

        @Override
        public Item getTabIconItem() {
            return ItemRing.getItemById(1);
        }
    };

    private ItemGroups() {
    }
}
