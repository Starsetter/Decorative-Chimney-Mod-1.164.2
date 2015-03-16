package DecorativeChimney.Items;

import java.util.List;

import DecorativeChimney.DecorativeChimneyCore;
import DecorativeChimney.TileEntities.TileEntityColor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class ItemMantelCenter extends Item
{
	public static Icon[] IconMantelCenterArray;
	public static String[] IconMantelCenterNames =
	{
		"MantelCenterBG", "MantelCenterBW", "MantelCenterGB", "MantelCenterGW",
		"MantelCenterWB", "MantelCenterWG", "Brick", "Stone",
		"CobbleStone", "Emerald", "Gold", "Diamond",
		"OakPlank", "BirchPlank", "SprucePlank", "JunglePlank"
	};

	public ItemMantelCenter(int ID)
	{
		super(ID);
		setMaxDamage(0);
		setHasSubtypes(true);
		setUnlocalizedName("itemMantelCenter1");
    	setCreativeTab(DecorativeChimneyCore.tabChimney);
	}

	public void getSubItems(int metaData, CreativeTabs creativeTabs, List list)
    {
        for (int j = 0; j < IconMantelCenterArray.length; ++j)
        {
            list.add(new ItemStack(this, 1, j));
        }
    }
	
    public int getMetadata(int metaData)
    {
        return metaData;
    }

    @SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int damage) //Gets the texture
	{
        if (damage < 0 || damage >= IconMantelCenterArray.length)
        {
        	damage = 0;
        }
		return this.IconMantelCenterArray[damage];
	}

	public String getUnlocalizedName(ItemStack itemStack) //Get's the item incode name from an itemstack
	{
        int i = itemStack.getItemDamage();

        if (i < 0 || i >= IconMantelCenterNames.length)
        {
            i = 0;
        }
        return super.getUnlocalizedName() + "." + IconMantelCenterNames[i];	}
	
	public void registerIcons(IconRegister iconRegister)
    {
		IconMantelCenterArray = new Icon[16];
		 for(int i = 0; i < IconMantelCenterArray.length; i++)
		 {
			 ItemStack itemStack = new ItemStack(DecorativeChimneyCore.itemMantelCenter, 64, i);

			 IconMantelCenterArray[i] = iconRegister.registerIcon(DecorativeChimneyCore.modid + ":" + IconMantelCenterNames[itemStack.getItemDamage()]);
		 }
    }

	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack itemStack)
	{
		return EnumRarity.common;
		//common=white
		//uncommon=yellow
		//rare=blue
		//epic=purple
	}

	@SideOnly(Side.CLIENT)
	public boolean isFull3D()
	{
		return false;
	}
	
	public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int metaData, float hitx, float hity, float hitz)
    {
        //par7 = side
    	if (metaData == 0)
    	{
    		--y;
    	}
    	if (metaData == 1)
    	{
    		++y;
    	}
    	if (metaData == 2)
    	{
    		--z;
    	}
    	if (metaData == 3)
    	{
    		++z;
    	}
    	if (metaData == 4)
    	{
    		--x;
    	}
    	if (metaData == 5)
    	{
    		++x;
    	}
    	if (!entityPlayer.canPlayerEdit(x, y, z, metaData, itemStack))
    	{
    		return false;
    	}
    	else if (!DecorativeChimneyCore.blockMantelCenter.canPlaceBlockAt(world, x, y, z))
    	{
    		return false;
    	}
    	else
    	{
        	int i1 = MathHelper.floor_double((double)(entityPlayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
            int i2 = 0;

            if(metaData == 0)
            {
            	i2 = 0;
            }
            else if(metaData == 1)
            {
            	i2 = 1;
            }
            else if (metaData != 0 || metaData != 1)
            {
            	i2 = ((double)hity >= 0.5D) ? i2 : i2 | 1;
            }
    		world.setBlock(x, y, z, DecorativeChimneyCore.blockMantelCenter.blockID, (i2 * 4) + i1, 2);

    		TileEntity tileentity = world.getBlockTileEntity(x, y, z);
    		if(tileentity != null && tileentity instanceof TileEntityColor)
    		{
    			((TileEntityColor)tileentity).setColor1(itemStack.getItemDamage());
    			((TileEntityColor)tileentity).setColor2(itemStack.getItemDamage());
    		}
    		--itemStack.stackSize;
    		return true;
    	}
    }
}