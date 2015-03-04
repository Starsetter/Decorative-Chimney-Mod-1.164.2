package DecorativeChimney.Blocks;

import java.util.List;
import java.util.Random;

import DecorativeChimney.CommonProxy;
import DecorativeChimney.DecorativeChimneyCore;
import DecorativeChimney.TileEntities.TileEntityChimney2A;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;


public class BlockChimney2A extends BlockContainer
{

	public BlockChimney2A(int id, Class class1)
	{
		super(id, Material.rock);
		anEntityClass = class1;
		setHardness(5.0F);
    	setResistance(1.0F);
    	setStepSound(Block.soundStoneFootstep);
    	setUnlocalizedName("blockChimney2A");
    	setCreativeTab(DecorativeChimneyCore.tabChimney);
	}

    @SideOnly(Side.CLIENT)
	public void getSubBlocks(int i, CreativeTabs creativetabs, List list)
    {
		list.add(new ItemStack(this, 1, 0));
		list.add(new ItemStack(this, 1, 4));
		list.add(new ItemStack(this, 1, 8));
		list.add(new ItemStack(this, 1, 12));
    }

    @SideOnly(Side.CLIENT)
    private static Icon[] icons;

    @SideOnly(Side.CLIENT)
    private static Icon[] icons2;

    private static final String[] blockChimneyBottomNames =
		{ 
    		"GrayMarble", "GrayMarble", "GrayMarble", "GrayMarble", "GrayMarble", "GrayMarble", "GrayMarble", "GrayMarble", 
    		"GrayMarble", "GrayMarble", "GrayMarble", "GrayMarble", "Stone", "Stone", "Stone", "Stone", 
		};

    private static final String[] blockChimneyTopNames =
		{ 
		"WhiteMarble", "WhiteMarble", "WhiteMarble", "WhiteMarble", "GrayMarble", "GrayMarble", "GrayMarble", "GrayMarble", 
		"BlackMarble", "BlackMarble", "BlackMarble", "BlackMarble", "Stone", "Stone", "Stone", "Stone", 
		};

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconregister)
    {
    	icons = new Icon[16];
    	icons2 = new Icon[16];
    	
    	for(int i = 0; i < 16; i++)
    	{
    		ItemStack blockChimney1Stack = new ItemStack(DecorativeChimneyCore.blockChimney1, 64, i);

    		icons[i] = iconregister.registerIcon(DecorativeChimneyCore.modid + ":" + blockChimneyBottomNames[blockChimney1Stack.getItemDamage()]);

    		icons2[i] = iconregister.registerIcon(DecorativeChimneyCore.modid + ":" + blockChimneyTopNames[blockChimney1Stack.getItemDamage()]);

    	}
    }
    
    @SideOnly(Side.CLIENT)
    public Icon getIcon(int i, int meta)
    {
    	if(i == 6)
    	{
    		return icons[meta];
    
    	}

    	if(i == 7)
    	{
    		return icons2[meta];
    	}
    	return icons[meta];
    }
    
    public static void triggerSmoke(World world, int i, int j, int k, Random random)
    {
		float f = (float)i + 0.25F; //Location Width
		float f1 = (float)j + 0.8125F + (random.nextFloat() * 6F) / 10F; //Location Height
		float f2 = (float)k + 0.5F; //Location Length
		float f3 = 0.52F;
		float f4 = random.nextFloat() * 0.6F - 0.3F;
		float f5 = (float)i + 0.75F; //Location1
		float f6 = (float)k + 0.5F; //Location 3
		world.spawnParticle("smoke", f, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
		world.spawnParticle("smoke", f, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
		world.spawnParticle("largesmoke", f, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
		world.spawnParticle("largesmoke", f, f1, f2 + f4, 0.0D, 0.0D, 0.0D);
		world.spawnParticle("smoke", f5, f1, f6 + f4, 0.0D, 0.0D, 0.0D);
		world.spawnParticle("smoke", f5, f1, f6 + f4, 0.0D, 0.0D, 0.0D);
		world.spawnParticle("largesmoke", f5, f1, f6 + f4, 0.0D, 0.0D, 0.0D);
		world.spawnParticle("largesmoke", f5, f1, f6 + f4, 0.0D, 0.0D, 0.0D);
	}	

	public int idDropped(int i, Random par2Random, int j)
	{
		return blockID;
	}

    public int damageDropped(int metadata)
    {
    	return metadata / 4;
    }

	public int quantityDropped(Random random)
	{
		return 1;
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
		return DecorativeChimneyCore.blockChimney2ModelID;
	}

	//Rotates based on players direction
	
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack itemstack)
	{
		int l = MathHelper.floor_double((double)((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
		world.setBlockMetadataWithNotify(i, j, k, world.getBlockMetadata(i, j, k) + l, 2);
	}
	
	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityChimney2A();
	}

	private Class anEntityClass;
	
    public boolean getBlocksMovement(IBlockAccess iblockaccess, int i, int j, int k)
    {
        return false;
    }
    
    public void setBlockBoundsBasedOnState(IBlockAccess iBlockAccess, int i, int j, int k)
    {
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
    }
    
    public void addCollidingBlockToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity)
    {
        this.setBlockBoundsBasedOnState(par1World, par2, par3, par4);
        super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
    }
}