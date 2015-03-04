package DecorativeChimney.Items;

import java.util.List;

import DecorativeChimney.DecorativeChimneyCore;
import DecorativeChimney.Blocks.BlockChimney1;
import DecorativeChimney.TileEntities.TileEntityChimney1;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class ItemChimney1 extends Item
{
	public static Icon[] IconArray;
	public static String[] IconNames = {"stone","stonebrick","dirt","brick","bedrock","sand","gravel","coal_block","iron_block","gold_block",
		"lapis_block","diamond_block","redstone_Block","planks_birch","planks_jungle","planks_oak","planks_spruce"};

	public ItemChimney1(int ID)
	{
		super(ID);
		setMaxDamage(0);
		setHasSubtypes(true);
		setUnlocalizedName("itemChimney1");
    	setCreativeTab(DecorativeChimneyCore.tabChimney);
	}

	public void getSubItems(int metaData, CreativeTabs creativeTabs, List list)
    {
        for (int j = 0; j < IconArray.length; ++j)
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
        if (damage < 0 || damage >= IconArray.length)
        {
        	damage = 0;
        }
		return this.IconArray[damage];
	}

	public String getUnlocalizedName(ItemStack itemStack) //Get's the item incode name from an itemstack
	{
        int i = itemStack.getItemDamage();

        if (i < 0 || i >= IconNames.length)
        {
            i = 0;
        }
        return super.getUnlocalizedName() + "." + IconNames[i];	}
	
	public void registerIcons(IconRegister iconRegister)
    {
		IconArray = new Icon[17];
		 for(int i = 0; i < IconArray.length; i++)
		 {
			 ItemStack itemStack = new ItemStack(DecorativeChimneyCore.itemChimney1, 64, i);

			 IconArray[i] = iconRegister.registerIcon(DecorativeChimneyCore.modid + ":" + IconNames[itemStack.getItemDamage()]);
		 }
    }
	
	@SideOnly(Side.CLIENT)
	public boolean isFull3D()
	{
		return true;
	}
	
	@Override
	public boolean hasEffect(ItemStack itemStack)
	{
		return false; //true to add enchanted effect to item.
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
	
    public void addInformation(ItemStack itemStack, EntityPlayer entityPlayer, List list, boolean par4)
	{
		list.add("Style 1");
	}
	
    public boolean onItemUse(ItemStack itemStack, EntityPlayer entityPlayer, World world, int x, int y, int z, int metaData, float par8, float par9, float par10)
    {
        //par7 = side
    	
    	if (metaData != 1)
        {
            return false;
        }
        else
        {
        	if (metaData == 1)
        	{
        		++y;
        	}
        	if (!entityPlayer.canPlayerEdit(x, y, z, metaData, itemStack))
        	{
        		return false;
        	}
        	else if (!DecorativeChimneyCore.blockChimney1.canPlaceBlockAt(world, x, y, z))
        	{
        		return false;
        	}
        	else
        	{
        		BlockChimney1 blockChimney1 = (BlockChimney1)DecorativeChimneyCore.blockChimney1;
        		world.setBlock(x, y, z, blockChimney1.blockID, metaData, 2);

        		TileEntity tileentity = world.getBlockTileEntity(x, y, z);
        		if(tileentity != null && tileentity instanceof TileEntityChimney1)
        		{
        			((TileEntityChimney1)tileentity).setChimneyType(itemStack.getItemDamage());
        		}
        		--itemStack.stackSize;
        		return true;
        	}
        }
    }
}
