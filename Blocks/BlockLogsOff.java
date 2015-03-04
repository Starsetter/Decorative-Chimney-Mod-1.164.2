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
    public void registerIcons(IconRegister iconregister)
    {
    	blockIcon = iconregister.registerIcon("DecorativeChimney:Logs");
    }

	public int idDropped(int par1, Random par2Random, int par3)
	{
		return blockID;
	}

	public int quantityDropped(Random random)
	{
		return 1;
	}
	
    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int l, float m, float n, float p)
    {
        if (entityplayer.getCurrentEquippedItem() != null && entityplayer.getCurrentEquippedItem().itemID == Item.flintAndSteel.itemID)
        {
            int q = world.getBlockMetadata(i, j, k);
            world.playSoundEffect((double)i + 0.5D, (double)j + 0.5D, (double)k + 0.5D, "fire.ignite", 1.0F, this.random.nextFloat() * 0.4F + 0.8F);
            entityplayer.getCurrentEquippedItem().damageItem(1, entityplayer);
            world.setBlock(i, j, k, DecorativeChimneyCore.blockLogsOn.blockID);
            world.setBlockMetadataWithNotify(i, j, k, q, 2);
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

	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack itemstack)
	{
		int l = MathHelper.floor_double((double)((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
		world.setBlockMetadataWithNotify(i, j, k, l, 2);
	}
	
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityLogs();
	}

	private Class anEntityClass;
	
    public boolean getBlocksMovement(IBlockAccess iblockaccess, int i, int j, int k)
    {
        return false;
    }
    
    public void addCollidingBlockToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity)
    {
        this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
        super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
    }
}