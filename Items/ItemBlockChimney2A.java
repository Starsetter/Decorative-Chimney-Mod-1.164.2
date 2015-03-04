package DecorativeChimney.Items;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockChimney2A extends ItemBlock
{

	public ItemBlockChimney2A(int id)
	{
		super(id);
		setMaxDamage(0);
		setHasSubtypes(true);
		setUnlocalizedName("blockChimney2A");
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
                return getUnlocalizedName() + ".graywhite";
            case 4:
                return getUnlocalizedName() + ".gray";
            case 8:
                return getUnlocalizedName() + ".grayblack";
            case 12:
                return getUnlocalizedName() + ".stone";
            default:
                return getUnlocalizedName() + ".graywhite";
        }
    }	
}
