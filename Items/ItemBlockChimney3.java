package DecorativeChimney.Items;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockChimney3 extends ItemBlock
{

	public ItemBlockChimney3(int id)
	{
		super(id);
		setMaxDamage(0);
		setHasSubtypes(true);
		setUnlocalizedName("blockChimney3");
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
        		return getUnlocalizedName() + ".blackmarble";
        	case 1:
        		return getUnlocalizedName() + ".graymarble";
        	case 2:
        		return getUnlocalizedName() + ".whitemarble";
            case 3:
                return getUnlocalizedName() + ".black";
            case 4:
                return getUnlocalizedName() + ".smallblack";
            case 5:
                return getUnlocalizedName() + ".gray";
            case 6:
                return getUnlocalizedName() + ".smallgray";
            case 7:
                return getUnlocalizedName() + ".stone";
            case 8:
                return getUnlocalizedName() + ".cobblestone";
            case 9:
                return getUnlocalizedName() + ".white";
            case 10:
                return getUnlocalizedName() + ".smallwhite";
            case 11:
                return getUnlocalizedName() + ".brick";
            case 12:
                return getUnlocalizedName() + ".netherbrick";
            case 13:
                return getUnlocalizedName() + ".emerald";
            case 14:
                return getUnlocalizedName() + ".gold";
            case 15:
                return getUnlocalizedName() + ".diamond";
            default:
                return getUnlocalizedName() + ".blackmarble";
        }
    }	
}
