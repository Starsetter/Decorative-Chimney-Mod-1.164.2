package DecorativeChimney.Items;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockChimney2C extends ItemBlock
{

	public ItemBlockChimney2C(int id)
	{
		super(id);
		setMaxDamage(0);
		setHasSubtypes(true);
		setUnlocalizedName("blockChimney2C");
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
                return getUnlocalizedName() + ".wood";
            case 4:
                return getUnlocalizedName() + ".emerald";
            case 8:
                return getUnlocalizedName() + ".gold";
            case 12:
                return getUnlocalizedName() + ".diamond";
            default:
                return getUnlocalizedName() + ".wood";
        }
    }	
}
