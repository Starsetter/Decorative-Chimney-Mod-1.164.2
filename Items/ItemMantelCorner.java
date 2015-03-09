package DecorativeChimney.Items;

import java.util.List;

import tutorial.WiduXTutorial;

import DecorativeChimney.DecorativeChimneyCore;
import DecorativeChimney.Blocks.BlockMantelCorner;
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

public class ItemMantelCorner extends Item
{
	public static Icon[] IconMantelCornerArray;
	public static String[] IconMantelCornerNames =
	{
		"MantelCornerBG", "MantelCornerBW", "MantelCornerGB", "MantelCornerGW",
		"MantelCornerWB", "MantelCornerWG", "OakPlank", "Stone",
		"CobbleStone", "Emerald", "Gold", "Diamond"
	};

	public ItemMantelCorner(int ID)
	{
		super(ID);
		setMaxDamage(0);
		setHasSubtypes(true);
		setUnlocalizedName("itemMantelCorner1");
    	setCreativeTab(DecorativeChimneyCore.tabChimney);
	}

	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (par1 = 0; par1 < IconMantelCornerArray.length; ++par1)
        {
            par3List.add(new ItemStack(this, 1, par1));
        }
    }
	
    public int getMetadata(int metaData)
    {
        return metaData;
    }

    @SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int damage) //Gets the texture
	{
        if (damage < 0 || damage >= IconMantelCornerArray.length)
        {
        	damage = 0;
        }
		return this.IconMantelCornerArray[damage];
	}

	public String getUnlocalizedName(ItemStack itemStack) //Get's the item incode name from an itemstack
	{
        int i = itemStack.getItemDamage();

        if (i < 0 || i >= IconMantelCornerNames.length)
        {
            i = 0;
        }
        return super.getUnlocalizedName() + "." + IconMantelCornerNames[i];	}
	
	public void registerIcons(IconRegister iconRegister)
    {
		IconMantelCornerArray = new Icon[12];
		 for(int i = 0; i < IconMantelCornerArray.length; i++)
		 {
			 ItemStack itemStack = new ItemStack(DecorativeChimneyCore.itemMantelCorner, 64, i);

			 IconMantelCornerArray[i] = iconRegister.registerIcon(DecorativeChimneyCore.modid + ":" + IconMantelCornerNames[itemStack.getItemDamage()]);
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
    	else if (!DecorativeChimneyCore.blockMantelCorner.canPlaceBlockAt(world, x, y, z))
    	{
    		return false;
    	}
    	else
    	{
            int i1 = 0;

            if(metaData == 0)
            {
            	i1 = 0;
            }
            else if(metaData == 1)
            {
            	i1 = 1;
            }
            else if (metaData != 0 || metaData != 1)
            {
            	i1 = ((double)hity >= 0.5D) ? i1 : i1 | 1;
            }
    		world.setBlock(x, y, z, DecorativeChimneyCore.blockMantelCorner.blockID, i1, 2);

    		TileEntity tileentity = world.getBlockTileEntity(x, y, z);
    		if(tileentity != null && tileentity instanceof TileEntityColor)
    		{
    			((TileEntityColor)tileentity).setColor(itemStack.getItemDamage());
    		}
    		--itemStack.stackSize;
    		return true;
    	}
    }
}