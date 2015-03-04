package DecorativeChimney.Items;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockChimney2B extends ItemBlock
{

	public ItemBlockChimney2B(int id)
	{
		super(id);
		setMaxDamage(0);
		setHasSubtypes(true);
		setUnlocalizedName("blockChimney2B");
	}
	
	public int getMetadata(int metadata)
	{
		return metadata;
	}

    public String getUnlocalizedName(ItemStack itemStack)
    {
        switch (itemStack.getItemDamage())
        {
            case 0:
                return getUnlocalizedName() + ".blackwhite";
            case 4:
                return getUnlocalizedName() + ".blackgray";
            case 8:
                return getUnlocalizedName() + ".black";
            case 12:
                return getUnlocalizedName() + ".cobblestone";
            default:
                return getUnlocalizedName() + ".blackwhite";
        }
    }	
}
