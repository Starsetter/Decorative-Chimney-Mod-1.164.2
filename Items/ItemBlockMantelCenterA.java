package DecorativeChimney.Items;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockMantelCenterA extends ItemBlock
{

	public ItemBlockMantelCenterA(int id)
	{
		super(id);
		setMaxDamage(0);
		setHasSubtypes(true);
		setUnlocalizedName("blockMantelCenterA");
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
                return getUnlocalizedName() + ".stone";
            case 12:
                return getUnlocalizedName() + ".wood";
            default:
                return getUnlocalizedName() + ".blackwhite";
        }
    }
}
