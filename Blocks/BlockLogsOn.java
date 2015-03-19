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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.EntityInteractEvent;


public class BlockLogsOn extends BlockContainer
{

    private Random random = new Random();

	public BlockLogsOn(int id, Class class1)
	{
		super(id, Material.wood);
		anEntityClass = class1;
		setLightValue(1.0F);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
    	setHardness(1.5F);
    	setResistance(1.0F);
    	setStepSound(Block.soundWoodFootstep);
    	setUnlocalizedName("blockLogsOn");
	}

    @SideOnly(Side.CLIENT)
    public void registerIcons(IconRegister iconregister)
    {
    	blockIcon = iconregister.registerIcon("DecorativeChimney:Logs");
    }

	public void randomDisplayTick(World world, int i, int j, int k, Random random)
	{
		float f = (float)i + 0.25F; //Location length
		float f1 = (float)i + (random.nextFloat() * 1F); //Location length
		float f2 = (float)i + (random.nextFloat() / 1F); //Location length
		float f3 = (float)j + 0.25F + (random.nextFloat() * 1F) / 10F; //Location Height
		float f4 = (float)j + 0.75F + (random.nextFloat() * 1F) / 10F; //Location width
		float f5 = (float)k + (random.nextFloat() * 1F); //Location width
		float f6 = (float)k + (random.nextFloat() / 1F); //Location width
		float f7 = (float)k + 0.25F; //Location width
		float f8 = random.nextFloat() * 0.6F - 0.3F; //Location width
		world.spawnParticle("largesmoke", f1, f4, f5 + f8, 0.0D, 0.0D, 0.0D);
		world.spawnParticle("smoke", f1, f3, f5, 0.0D, 0.0D, 0.0D);
        world.spawnParticle("flame", f1, f3, f5, 0.0D, 0.0D, 0.0D);
        
		world.spawnParticle("smoke", f1, f3, f5, 0.0D, 0.0D, 0.0D);
        world.spawnParticle("flame", f1, f3, f5, 0.0D, 0.0D, 0.0D);
        
		world.spawnParticle("smoke", f1, f3, f5, 0.0D, 0.0D, 0.0D);
        world.spawnParticle("flame", f1, f3, f5, 0.0D, 0.0D, 0.0D);

		world.spawnParticle("smoke", f1, f3, f5, 0.0D, 0.0D, 0.0D);
        world.spawnParticle("flame", f1, f3, f5, 0.0D, 0.0D, 0.0D);
        
		world.spawnParticle("smoke", f1, f3, f5, 0.0D, 0.0D, 0.0D);
        world.spawnParticle("flame", f1, f3, f5, 0.0D, 0.0D, 0.0D);
        world.playSoundEffect((double)((float)i), (double)((float)j), (double)((float)k), "fire.fire", 0.03175F, 0.3F);
	}	

	public int idDropped(int i, Random random, int j)
	{
		return DecorativeChimneyCore.blockLogsOff.blockID;
	}

	public int quantityDropped(Random random)
	{
		return 1;
	}

    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int l, float m, float n, float p)
    {
    	if (entityplayer.getCurrentEquippedItem() == null || entityplayer.getCurrentEquippedItem().itemID == Item.bucketWater.itemID)
    	{
    		Random q = new Random();
    		world.playSoundEffect((double)i + 0.5D, (double)j + 0.5D, (double)k + 0.5D, "random.fizz", 1.0F, q.nextFloat() * 0.4F + 0.8F);
    		world.spawnParticle("largesmoke", (double)i + 0.5D - (this.random.nextDouble() - 0.5D) / 4.0D, (double)j + 0.6D, (double)k + 0.5D - (this.random.nextDouble() - 0.5D) / 4.0D, 0.0D, 0.0D, 0.0D);
    		world.spawnParticle("largesmoke", (double)i + 0.5D - (this.random.nextDouble() - 0.5D) / 4.0D, (double)j + 0.6D, (double)k + 0.5D - (this.random.nextDouble() - 0.5D) / 4.0D, 0.0D, 0.0D, 0.0D);
    		int r = world.getBlockMetadata(i, j, k);
            world.setBlock(i, j, k, DecorativeChimneyCore.blockLogsOff.blockID);
            world.setBlockMetadataWithNotify(i, j, k, r, 2);
            if (entityplayer.getCurrentEquippedItem() != null && entityplayer.getCurrentEquippedItem().itemID == Item.bucketWater.itemID)
            {
            	onUse(entityplayer.getCurrentEquippedItem(), world, entityplayer);
            }
    		return  true;
    	}
    	else
    	{
    		return false;
    	}
    }

    public ItemStack onUse(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {
        if (--itemStack.stackSize <= 0)
        {
            return new ItemStack(Item.bucketEmpty);
        }
        return itemStack;
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

	public TileEntity createNewTileEntity(World world)
	{
		return new TileEntityLogs();
	}

	
	private Class anEntityClass;
	
    public boolean getBlocksMovement(IBlockAccess iblockaccess, int i, int j, int k)
    {
        return false;
    }
    
    public void addCollidingBlockToList(World world, int i, int j, int k, AxisAlignedBB axisAlignedBB, List list, Entity entity)
    {
        setBlockBoundsBasedOnState(world, i, j, k);
        super.addCollisionBoxesToList(world, i, j, k, axisAlignedBB, list, entity);
    }
    
    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
    {
        entity.setFire(5);
    }
}