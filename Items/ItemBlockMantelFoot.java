package DecorativeChimney.Items;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockMantelFoot extends ItemBlock
{

	public ItemBlockMantelFoot(int id)
	{
		super(id);
		setMaxDamage(0);
		setHasSubtypes(true);
		setUnlocalizedName("blockMantelFoot");
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
        	case 1:
        		return getUnlocalizedName() + ".whiteblack";
        	case 2:
        		return getUnlocalizedName() + ".graywhite";
        	case 3:
        		return getUnlocalizedName() + ".grayblack";
        	case 4:
        		return getUnlocalizedName() + ".blackwhite";
        	case 5:
        		return getUnlocalizedName() + ".blackgray";
        	case 6:
        		return getUnlocalizedName() + ".stone";
        	case 7:
        		return getUnlocalizedName() + ".wood";
        	case 8:
        		return getUnlocalizedName() + ".cobblestone";
        	case 9:
        		return getUnlocalizedName() + ".emerald";
        	case 10:
        		return getUnlocalizedName() + ".gold";
        	case 11:
        		return getUnlocalizedName() + ".diamond";
        	default:
        		return getUnlocalizedName() + ".whitegray";
        }
    }
}
