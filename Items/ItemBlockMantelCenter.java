package DecorativeChimney.Items;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockMantelCenter extends ItemBlock
{

	public ItemBlockMantelCenter(int id)
	{
		super(id);
		setMaxDamage(0);
		setHasSubtypes(true);
		setUnlocalizedName("blockMantelCenter");
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
                return getUnlocalizedName() + ".whitegray";
            case 4:
                return getUnlocalizedName() + ".whiteblack";
            case 8:
                return getUnlocalizedName() + ".graywhite";
            case 12:
                return getUnlocalizedName() + ".grayblack";
            default:
                return getUnlocalizedName() + ".whitegray";
        }
    }
}
