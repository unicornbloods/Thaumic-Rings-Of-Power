package xyz.uniblood.trop.init;

import com.google.common.base.CaseFormat;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import xyz.uniblood.trop.item.*;

import static baubles.common.Config.itemRing;

@SuppressWarnings({"WeakerAccess", "PublicField"})
public class Items {

    public static void preInit() {

        itemRing = new ItemRing();

        register(itemRing, "itemRing");
    }

    private static void register(Item item, String name) {
        String itemName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, name);
        item.setUnlocalizedName(itemName);
        item.setTextureName("trop:" + itemName);
        GameRegistry.registerItem(item, itemName);
    }
}
