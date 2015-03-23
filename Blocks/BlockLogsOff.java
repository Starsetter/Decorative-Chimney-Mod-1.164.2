package DecorativeChimney.Blocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import DecorativeChimney.CommonProxy;
import DecorativeChimney.DecorativeChimneyCore;
import DecorativeChimney.TileEntities.TileEntityLogs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.EntityInteractEvent;


public class BlockLogsOff extends BlockContainer
{

    private Random random = new Random();

	public BlockLogsOff(int id, Class class1)
	{
		super(id, Material.wood);
		anEntityClass = class1;
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
    	setHardness(1.5F);
    	setResistance(1.0F);
    	setStepSound(Block.soundWoodFootstep);
    	setUnlocalizedName("blockLogsOff");
    	setCreativeTab(DecorativeChimneyCore.tabChimney);
	}

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconRegister)
    {
    	blockIcon = iconRegister.registerIcon("DecorativeChimney:Logs");
    }

	public int idDropped(int l, Random random, int m)
	{
		return blockID;
	}

	public int quantityDropped(Random random)
	{
		return 1;
	}
	
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityPlayer, int l, float i, float j, float k)
    {
        ItemStack itemStack = entityPlayer.getCurrentEquippedItem();
        if(itemStack != null && itemStack.itemID == Item.flintAndSteel.itemID)
        {
            int q = world.getBlockMetadata(x, y, z);
            world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, "fire.ignite", 1.0F, this.random.nextFloat() * 0.4F + 0.8F);
            itemStack.damageItem(1, entityPlayer);
            world.setBlock(x, y, z, DecorativeChimneyCore.blockLogsOn.blockID);
            world.setBlockMetadataWithNotify(x, y, z, q, 2);
            return true;
        }
        else
        {
            return false;
        }
    }

	public boolean isOpaqueCube()
	{
		return false;
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}

	public int getRenderType()
	{
		return -1;
	}

	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemStack)
	{
		int l = MathHelper.floor_double((double)((entityLivingBase.rotationYaw * 4F) / 360F) + 0.5D) & 3;
		world.setBlockMetadataWithNotify(x, y, z, l, 2);
	}
	
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityLogs();
	}

	private Class anEntityClass;
	
    public boolean getBlocksMovement(IBlockAccess iblockAccess, int x, int y, int z)
    {
        return false;
    }
    
    public void addCollidingBlockToList(World world, int x, int y, int z, AxisAlignedBB axisAlignedBB, List list, Entity entity)
    {
        this.setBlockBoundsBasedOnState(world, x, y, z);
        super.addCollisionBoxesToList(world, x, y, z, axisAlignedBB, list, entity);
    }
}