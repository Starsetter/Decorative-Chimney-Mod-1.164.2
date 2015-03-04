package DecorativeChimney.Items;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockChimney2 extends ItemBlock
{

	public ItemBlockChimney2(int id)
	{
		super(id);
		setMaxDamage(0);
		setHasSubtypes(true);
		setUnlocalizedName("blockChimney2");
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
                return getUnlocalizedName() + ".white";
            case 4:
                return getUnlocalizedName() + ".whitegray";
            case 8:
                return getUnlocalizedName() + ".whiteblack";
            case 12:
                return getUnlocalizedName() + ".whitebrick";
            default:
                return getUnlocalizedName() + ".white";
        }
    }	
}
