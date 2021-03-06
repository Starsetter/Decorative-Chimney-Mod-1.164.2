package DecorativeChimney.Items;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockMantelSide extends ItemBlock
{

	public ItemBlockMantelSide(int id)
	{
		super(id);
		setMaxDamage(0);
		setHasSubtypes(true);
		setUnlocalizedName("blockMantelSide");
	}
	
	public int getMetadata(int metaData)
	{
		return metaData;
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
        		return getUnlocalizedName() + ".brick";
        	case 7:
        		return getUnlocalizedName() + ".stone";
        	case 8:
        		return getUnlocalizedName() + ".cobblestone";
        	case 9:
        		return getUnlocalizedName() + ".emerald";
        	case 10:
        		return getUnlocalizedName() + ".gold";
        	case 11:
        		return getUnlocalizedName() + ".diamond";
        	case 12:
        		return getUnlocalizedName() + ".oakplank";
        	case 13:
        		return getUnlocalizedName() + ".birchplank";
        	case 14:
        		return getUnlocalizedName() + ".spruceplank";
        	case 15:
        		return getUnlocalizedName() + ".jungleplank";
        	default:
        		return getUnlocalizedName() + ".whitegray";
        }
    }
}
