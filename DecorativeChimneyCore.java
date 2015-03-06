package DecorativeChimney;

import java.io.File;
import java.util.Random;

import DecorativeChimney.Blocks.*;
import DecorativeChimney.Items.*;
import DecorativeChimney.Models.*;
import DecorativeChimney.TileEntities.*;
import DecorativeChimney.TileEntityRenders.*;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;


@Mod(modid=DecorativeChimneyCore.modid, name="Decorative Chimneys", version="Beta 1.164.2", dependencies="required-after:DecorativeMarble")
@NetworkMod(clientSideRequired=true, serverSideRequired=false)

public class DecorativeChimneyCore
{
	public static final String modid = "DecorativeChimney";
	
	public static CreativeTabs tabChimney = new TabChimney("tabChimney");
	
	static int blockChimneyHolllowBricksBlockID;
	static int blockLogsOnBlockID;
	static int blockLogsOffBlockID;
	static int blockMantelCornerBlockID;
	static int blockMantelCenterBlockID;
	static int blockMantelCenterABlockID;
	static int blockMantelSideBlockID;
	static int blockMantelPlainSideBlockID;
	static int blockMantelFootBlockID;
	static int blockChimney1BlockID;
	static int blockChimney2BlockID;
	static int blockChimney2ABlockID;
	static int blockChimney2BBlockID;
	static int blockChimney2CBlockID;
	static int blockChimney3BlockID;

	static int itemChimney1BlockID;
	static int itemChimney2BlockID;
//	static int itemChimney3BlockID;
	static int itemMantelCornerBlockID;

	public static int blockHollowBricksModelID;
	public static int blockMantelCornerModelID;
	public static int blockMantelCenterModelID;
	public static int blockMantelSideModelID;
	public static int blockMantelPlainSideModelID;
	public static int blockMantelFootModelID;
	public static int blockChimney1ModelID;
	public static int blockChimney2ModelID;
	public static int blockChimney3ModelID;
	
	public static Block blockChimneyHollowBricks;
	public static Block blockLogsOn;
	public static Block blockLogsOff;
	public static Block blockMantelCorner;
	public static Block blockMantelCenter;
	public static Block blockMantelCenterA;
	public static Block blockMantelSide;
	public static Block blockMantelPlainSide;
	public static Block blockMantelFoot;
	public static Block blockChimney1;
	public static Block blockChimney2;
	public static Block blockChimney2A;
	public static Block blockChimney2B;
	public static Block blockChimney2C;
	public static Block blockChimney3;
	
	public static Item itemChimney1;
	public static Item itemChimney2;
//	public static Item itemChimney3 = new ItemChimney1(itemChimney3BlockID);
	public static Item itemMantelCorner;
	
	private static final String[] blockChimneyBricksNames =
		{ 
			"Black Marble", "Gray Marble", "White Marble", "Black Marble Bricks",
			"Small Black Marble Bricks", "Small Stone Bricks", "White Marble Bricks", "Small White Marble Bricks"
		};

	private static final String[] blockChimneyBrickTypeNames =
		{ 
			"Black Marble", "Gray Marble", "White Marble", "Black Marble Bricks", "Small Black Marble Bricks",
			"Stone Bricks", "Small Stone Bricks", "Stone", "Cobblestone", "White Marble Bricks",
			"Small White Marble Bricks", "Nether Brick", "Brick", "Emerald", "Gold", "Diamond"
		};

	private static final String[] blockMantelTypeNames =
		{ 
			"White with Gray Marble", "White with Black Marble", "Gray with White Marble", "Gray with Black Marble",
			"Black with White Marble", "Black with Gray Marble", "Stone", "Wood", "Cobblestone", "Emerald", "Gold", "Diamond"
		};
	
	private static final String[] blockMantel2TypeNames =
		{ 
			"Black Marble", "Gray Marble", "White Marble", "Black Marble Bricks", "Small Black Marble Bricks",
			"Stone Bricks", "Small Stone Bricks", "Stone", "Cobblestone", "White Marble Bricks",
			"Small White Marble Bricks", "Wood", "Brick", "Emerald", "Gold", "Diamond"
		};

	private static final String[] blockChimneyTypeNames =
		{ 
			"White Marble", "White with Gray Marble", "White with Black Marble", "White Marble with Brick",
			"Gray with White Marble", "Gray Marble", "Gray with Black Marble", "Stone",
			"Black with White Marble", "Black with Gray Marble", "Black Marble", "Cobblestone",
			 "Wood", "Emerald", "Gold", "Diamond", "Smooth Sand Stone"
		};

	@SidedProxy(clientSide="DecorativeChimney.ClientProxy", serverSide="DecorativeChimney.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		Configuration configuration = new Configuration(event.getSuggestedConfigurationFile());

		configuration.load();

		blockChimneyHolllowBricksBlockID = configuration.getBlock("Block", "Hollow Chimney Brick Blocks", 2001).getInt();
		blockLogsOnBlockID = configuration.getBlock("Block", "Logs On", 2002).getInt();
		blockLogsOffBlockID = configuration.getBlock("Block", "Logs Off", 2003).getInt();
		blockMantelCornerBlockID = configuration.getBlock("Block", "Mantel Corner", 2004).getInt();
		blockMantelCenterBlockID = configuration.getBlock("Block", "Mantel Center", 2006).getInt();
		blockMantelCenterABlockID = configuration.getBlock("Block", "Mantel Center A", 2007).getInt();
		blockMantelSideBlockID = configuration.getBlock("Block", "Mantel Side", 2008).getInt();
		blockMantelPlainSideBlockID = configuration.getBlock("Block", "Mantel Plain Side", 2009).getInt();
		blockMantelFootBlockID = configuration.getBlock("Block", "Mantel Footer", 2010).getInt();
		blockChimney1BlockID = configuration.getBlock("Block", "Chimney Style 1", 2011).getInt();
		blockChimney2BlockID = configuration.getBlock("Block", "Chimney Style 2", 2012).getInt();
		blockChimney2ABlockID = configuration.getBlock("Block", "Chimney Style 2A", 2013).getInt();
		blockChimney2BBlockID = configuration.getBlock("Block", "Chimney Style 2B", 2014).getInt();
		blockChimney2CBlockID = configuration.getBlock("Block", "Chimney Style 2C", 2015).getInt();
		blockChimney3BlockID = configuration.getBlock("Block", "Chimney Style 3", 2016).getInt();

		itemChimney1BlockID = configuration.getItem("Item", "Chimney Style 1", 20015).getInt();
		itemChimney2BlockID = configuration.getItem("Item", "Chimney Style 2", 20016).getInt();
//		itemChimney3BlockID = configuration.getItem("Item", "Chimney Style 3", 20017).getInt();
		itemMantelCornerBlockID = configuration.getItem("Item", "Mantel Corner", 20018).getInt();

		configuration.save();
	}

	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
		proxy.registerRenderers();
		proxy.initRendering();
		
		blockChimneyHollowBricks = new BlockChimneyHollow(blockChimneyHolllowBricksBlockID);
		blockMantelCorner = new BlockMantelCorner(blockMantelCornerBlockID, TileEntityColor.class);
		blockMantelCenter = new BlockMantelCenter(blockMantelCenterBlockID);
		blockMantelCenterA = new BlockMantelCenterA(blockMantelCenterABlockID);
		blockMantelSide = new BlockMantelSide(blockMantelSideBlockID);
		blockMantelPlainSide = new BlockMantelPlainSide(blockMantelPlainSideBlockID);
		blockMantelFoot = new BlockMantelFoot(blockMantelFootBlockID);
        blockLogsOn = new BlockLogsOn(blockLogsOnBlockID, TileEntityLogs.class);
        blockLogsOff = new BlockLogsOff(blockLogsOffBlockID, TileEntityLogs.class);
		blockChimney1 = new BlockChimney1(blockChimney1BlockID, TileEntityChimney1.class);
		blockChimney2 = new BlockChimney2(blockChimney2BlockID, TileEntityChimney2.class);
		blockChimney2A = new BlockChimney2A(blockChimney2ABlockID, TileEntityChimney2A.class);
		blockChimney2B = new BlockChimney2B(blockChimney2BBlockID, TileEntityChimney2B.class);
		blockChimney2C = new BlockChimney2C(blockChimney2CBlockID, TileEntityChimney2C.class);
        blockChimney3 = new BlockChimney3(blockChimney3BlockID, TileEntityChimney3.class);
        
        itemChimney1 = new ItemChimney1(itemChimney1BlockID);
        itemChimney2 = new ItemChimney1(itemChimney2BlockID);
    	itemMantelCorner = new ItemMantelCorner(itemMantelCornerBlockID);

		LanguageRegistry.instance().addStringLocalization("itemGroup.tabChimney", "en_US", "Fireplaces and Chimneys");

		GameRegistry.registerBlock(blockChimneyHollowBricks, ItemBlockChimneyHollow.class, "Hollow Chimney Bricks");
		GameRegistry.registerBlock(blockLogsOn, "Ignited Logs");
		GameRegistry.registerBlock(blockLogsOff, "Logs");
		GameRegistry.registerBlock(blockMantelCorner, "Mantel Corner");
		GameRegistry.registerBlock(blockMantelCenter, ItemBlockMantelCenter.class, "Mantel Center");
		GameRegistry.registerBlock(blockMantelCenterA, ItemBlockMantelCenterA.class, "Mantel Center A");
		GameRegistry.registerBlock(blockMantelSide, ItemBlockMantelSide.class, "Mantel Side");
		GameRegistry.registerBlock(blockMantelPlainSide, ItemBlockMantelPlainSide.class, "Mantel Plain Side");
		GameRegistry.registerBlock(blockMantelFoot, ItemBlockMantelFoot.class, "Mantel Foot");
		GameRegistry.registerBlock(blockChimney1, "Chimney Style 1");
		GameRegistry.registerBlock(blockChimney2, "Chimney Style 2");
		GameRegistry.registerBlock(blockChimney2A, ItemBlockChimney2A.class, "Chimney Style 2A");
		GameRegistry.registerBlock(blockChimney2B, ItemBlockChimney2B.class, "Chimney Style 2B");
		GameRegistry.registerBlock(blockChimney2C, ItemBlockChimney2C.class, "Chimney Style 2C");
		GameRegistry.registerBlock(blockChimney3, ItemBlockChimney3.class, "Chimney Style 3");

		GameRegistry.registerItem(itemChimney1, "Item Chimney Style 1");
		GameRegistry.registerItem(itemChimney2, "Item Chimney Style 2");
//		GameRegistry.registerItem(itemChimney3, "Item Chimney Style 3");
		GameRegistry.registerItem(itemMantelCorner, "Item Mantel Corner");
		
		GameRegistry.registerTileEntity(TileEntityLogs.class, "LogsOn");
		GameRegistry.registerTileEntity(TileEntityLogs.class, "LogsOff");
		GameRegistry.registerTileEntity(TileEntityChimney1.class, "Chimney1");
		GameRegistry.registerTileEntity(TileEntityChimney2.class, "Chimney2");
		GameRegistry.registerTileEntity(TileEntityChimney2A.class, "Chimney2A");
		GameRegistry.registerTileEntity(TileEntityChimney2B.class, "Chimney2B");
		GameRegistry.registerTileEntity(TileEntityChimney2C.class, "Chimney2C");
		GameRegistry.registerTileEntity(TileEntityChimney3.class, "Chimney3");
		GameRegistry.registerTileEntity(TileEntityColor.class, "Color");

//Tool Tip Name

		for (int i = 0; i < blockChimneyTypeNames.length; i++)
		{
			ItemStack itemChimneyStyle1Stack = new ItemStack(itemChimney1, 64, i);

			LanguageRegistry.addName(itemChimneyStyle1Stack, blockChimneyTypeNames[itemChimneyStyle1Stack.getItemDamage()] + " Chimney");

			ItemStack itemChimneyStyle2Stack = new ItemStack(itemChimney2, 64, i);

			LanguageRegistry.addName(itemChimneyStyle2Stack, blockChimneyTypeNames[itemChimneyStyle2Stack.getItemDamage()] + " Chimney");

//			ItemStack itemChimneyStyle3Stack = new ItemStack(itemChimney3, 64, i);

//			LanguageRegistry.addName(itemChimneyStyle3Stack, blockChimneyTypeNames[itemChimneyStyle3Stack.getItemDamage()] + " Chimney");
		}
			
		for (int i = 0; i < 16; i++)
		{
			ItemStack blockChimneyHollowBricksStack = new ItemStack(blockChimneyHollowBricks, 64, i);

			LanguageRegistry.addName(blockChimneyHollowBricksStack, "Hollow " + blockChimneyBrickTypeNames[blockChimneyHollowBricksStack.getItemDamage()]);

			ItemStack blockMantelPlainSideStack = new ItemStack(blockMantelPlainSide, 64, i);

			LanguageRegistry.addName(blockMantelPlainSideStack, blockMantel2TypeNames[blockMantelPlainSideStack.getItemDamage()] + " Mantel Plain Side");

			ItemStack blockChimney3Stack = new ItemStack(blockChimney3, 64, i);

			LanguageRegistry.addName(blockChimney3Stack, blockChimneyBrickTypeNames[blockChimney3Stack.getItemDamage()] + " Chimney Style 3");
		}

		LanguageRegistry.addName(blockLogsOn, "Logs");
		LanguageRegistry.addName(blockLogsOff, "Logs");

		for (int i = 0; i < blockMantelTypeNames.length; i++)
		{
			ItemStack itemMantelCornerStack = new ItemStack(itemMantelCorner, 64, i);

			LanguageRegistry.addName(itemMantelCornerStack, blockMantelTypeNames[itemMantelCornerStack.getItemDamage()] + " Mantel Corner");

			ItemStack blockMantelSideStack = new ItemStack(blockMantelSide, 64, i);

			LanguageRegistry.addName(blockMantelSideStack, blockMantelTypeNames[blockMantelSideStack.getItemDamage()] + " Mantel Side");

			ItemStack blockMantelFootStack = new ItemStack(blockMantelFoot, 64, i);

			LanguageRegistry.addName(blockMantelFootStack, blockMantelTypeNames[blockMantelFootStack.getItemDamage()] + " Mantel Footer");
		}

		LanguageRegistry.addName(new ItemStack(blockMantelCenter, 64, 0), "White with Gray Marble Mantel Center");
		LanguageRegistry.addName(new ItemStack(blockMantelCenter, 64, 4), "White with Black Marble Mantel Center");
		LanguageRegistry.addName(new ItemStack(blockMantelCenter, 64, 8), "Gray with White Marble Mantel Center");
		LanguageRegistry.addName(new ItemStack(blockMantelCenter, 64, 12), "Gray with Black Marble Mantel Center");

		LanguageRegistry.addName(new ItemStack(blockMantelCenterA, 64, 0), "Black with White Marble Mantel Center");
		LanguageRegistry.addName(new ItemStack(blockMantelCenterA, 64, 4), "Black with Gray Marble Mantel Center");
		LanguageRegistry.addName(new ItemStack(blockMantelCenterA, 64, 8), "Stone Mantel Center");
		LanguageRegistry.addName(new ItemStack(blockMantelCenterA, 64, 12), "Wood Mantel Center");

/*		LanguageRegistry.addName(new ItemStack(blockChimney2, 64, 0), "White Marble Chimney Style 2");
		LanguageRegistry.addName(new ItemStack(blockChimney2, 64, 4), "White with Gray Marble Chimney Style 2");
		LanguageRegistry.addName(new ItemStack(blockChimney2, 64, 8), "White with Black Marble Chimney Style 2");
		LanguageRegistry.addName(new ItemStack(blockChimney2, 64, 12), "White Marble with Brick Chimney Style 2");

		LanguageRegistry.addName(new ItemStack(blockChimney2A, 64, 0), "Gray with White Marble Chimney Style 2");
		LanguageRegistry.addName(new ItemStack(blockChimney2A, 64, 4), "Gray Marble Chimney Style 2");
		LanguageRegistry.addName(new ItemStack(blockChimney2A, 64, 8), "Gray with Black Marble Chimney Style 2");
		LanguageRegistry.addName(new ItemStack(blockChimney2A, 64, 12), "Stone Chimney Style 2");

		LanguageRegistry.addName(new ItemStack(blockChimney2B, 64, 0), "Black with White Marble Chimney Style 2");
		LanguageRegistry.addName(new ItemStack(blockChimney2B, 64, 4), "Black with Gray Marble Chimney Style 2");
		LanguageRegistry.addName(new ItemStack(blockChimney2B, 64, 8), "Black Marble Chimney Style 2");
		LanguageRegistry.addName(new ItemStack(blockChimney2B, 64, 12), "Cobblestone Chimney Style 2");

		LanguageRegistry.addName(new ItemStack(blockChimney2C, 64, 0), "Wood Chimney Style 2");
		LanguageRegistry.addName(new ItemStack(blockChimney2C, 64, 4), "Emerald Chimney Style 2");
		LanguageRegistry.addName(new ItemStack(blockChimney2C, 64, 8), "Gold Chimney Style 2");
		LanguageRegistry.addName(new ItemStack(blockChimney2C, 64, 12), "Diamond Chimney Style 2");
*/
//Recipes

		GameRegistry.addRecipe(new ItemStack(blockLogsOff, 4, 7), " L ", "LLL", Character.valueOf('L'), Block.wood);

		addHollowRecipe(0, "stoneMarbleBlack", "stoneMarbleBlack");
		addHollowRecipe(1, "stoneMarbleGray", "stoneMarbleGray");
		addHollowRecipe(2, "stoneMarble", "stoneMarble");
		addHollowRecipe(3, "brickMarbleBlack", "brickMarbleBlack");
		addHollowRecipe(4, "brickMarbleSmallBlack", "brickMarbleSmallBlack");
		addHollowRecipe(5, Block.stoneBrick, Block.stoneBrick);
		addHollowRecipe(6, "brickStoneSmall", "brickStoneSmall");
		addHollowRecipe(7, Block.stone, Block.stone);
		addHollowRecipe(8, Block.cobblestone, Block.cobblestone);
		addHollowRecipe(9, "brickMarble", "brickMarble");
		addHollowRecipe(10, "brickMarbleSmall", "brickMarbleSmall");
		addHollowRecipe(11, Block.netherBrick, Block.netherBrick);
		addHollowRecipe(12, Block.brick, Block.brick);
		addHollowRecipe(13, Block.glass, Item.emerald);
		addHollowRecipe(14, Block.planks, Item.ingotGold);
		addHollowRecipe(15, Block.glass, Item.diamond);

		addMantelCornerRecipe(0, "stoneMarble", "stoneMarbleGray");
		addMantelCornerRecipe(1, "stoneMarble", "stoneMarbleBlack");
		addMantelCornerRecipe(2, "stoneMarbleGray", "stoneMarble");
		addMantelCornerRecipe(3, "stoneMarbleGray", "stoneMarbleBlack");
		addMantelCornerRecipe(4, "stoneMarbleBlack", "stoneMarble");
		addMantelCornerRecipe(5, "stoneMarbleBlack", "stoneMarbleGray");
		addMantelCornerRecipe(6, Block.stone, Item.coal);
		addMantelCornerRecipe(7, Block.planks, Item.coal);
		addMantelCornerRecipe(8, Block.cobblestone, Item.coal);
		addMantelCornerRecipe(9, Block.glass, Item.emerald);
		addMantelCornerRecipe(10, Block.planks, Item.ingotGold);
		addMantelCornerRecipe(11, Block.glass, Item.diamond);
		
		addMantelCenterRecipe(new ItemStack(blockMantelCenter, 1, 0), "stoneMarble", "stoneMarbleGray");
		addMantelCenterRecipe(new ItemStack(blockMantelCenter, 1, 4), "stoneMarble", "stoneMarbleBlack");
		addMantelCenterRecipe(new ItemStack(blockMantelCenter, 1, 8), "stoneMarbleGray", "stoneMarble");
		addMantelCenterRecipe(new ItemStack(blockMantelCenter, 1, 12), "stoneMarbleGray", "stoneMarbleBlack");
		addMantelCenterRecipe(new ItemStack(blockMantelCenterA, 1, 0), "stoneMarbleBlack", "stoneMarble");
		addMantelCenterRecipe(new ItemStack(blockMantelCenterA, 1, 4), "stoneMarbleBlack", "stoneMarbleGray");
		addMantelCenterRecipe(new ItemStack(blockMantelCenterA, 1, 8), Block.stone, Item.coal);
		addMantelCenterRecipe(new ItemStack(blockMantelCenterA, 1, 12), Block.planks, Item.coal);

		addMantelPlainSideRecipe(0, "stoneMarbleBlack", "stoneMarbleBlack");
		addMantelPlainSideRecipe(1, "stoneMarbleGray", "stoneMarbleGray");
		addMantelPlainSideRecipe(2, "stoneMarble", "stoneMarble");
		addMantelPlainSideRecipe(3, "brickMarbleBlack", "brickMarbleBlack");
		addMantelPlainSideRecipe(4, "brickMarbleSmallBlack", "brickMarbleSmallBlack");
		addMantelPlainSideRecipe(5, Block.stoneBrick, Item.coal);
		addMantelPlainSideRecipe(6, "brickStoneSmall", "brickStoneSmall");
		addMantelPlainSideRecipe(7, Block.stone, Item.coal);
		addMantelPlainSideRecipe(8, Block.cobblestone, Item.coal);
		addMantelPlainSideRecipe(9, "brickMarble", "brickMarble");
		addMantelPlainSideRecipe(10, "brickMarbleSmall", "brickMarbleSmall");
		addMantelPlainSideRecipe(11, Block.netherBrick, Item.coal);
		addMantelPlainSideRecipe(12, Block.brick, Item.coal);
		addMantelPlainSideRecipe(13, Block.glass, Item.emerald);
		addMantelPlainSideRecipe(14, Block.planks, Item.ingotGold);
		addMantelPlainSideRecipe(15, Block.glass, Item.diamond);
		
		addMantelSideRecipe(0, "stoneMarble", "stoneMarbleGray");
		addMantelSideRecipe(1, "stoneMarble", "stoneMarbleBlack");
		addMantelSideRecipe(2, "stoneMarbleGray", "stoneMarble");
		addMantelSideRecipe(3, "stoneMarbleGray", "stoneMarbleBlack");
		addMantelSideRecipe(4, "stoneMarbleBlack", "stoneMarble");
		addMantelSideRecipe(5, "stoneMarbleBlack", "stoneMarbleGray");
		addMantelSideRecipe(6, Block.stone, Item.coal);
		addMantelSideRecipe(7, Block.planks, Item.coal);
		addMantelSideRecipe(8, Block.cobblestone, Item.coal);
		addMantelSideRecipe(9, Block.glass, Item.emerald);
		addMantelSideRecipe(10, Block.planks, Item.ingotGold);
		addMantelSideRecipe(11, Block.glass, Item.diamond);
		
		addMantelFootRecipe(0, "stoneMarble", "stoneMarbleGray");
		addMantelFootRecipe(1, "stoneMarble", "stoneMarbleBlack");
		addMantelFootRecipe(2, "stoneMarbleGray", "stoneMarble");
		addMantelFootRecipe(3, "stoneMarbleGray", "stoneMarbleBlack");
		addMantelFootRecipe(4, "stoneMarbleBlack", "stoneMarble");
		addMantelFootRecipe(5, "stoneMarbleBlack", "stoneMarbleGray");
		addMantelFootRecipe(6, Block.stone, Item.coal);
		addMantelFootRecipe(7, Block.planks, Item.coal);
		addMantelFootRecipe(8, Block.cobblestone, Item.coal);
		addMantelFootRecipe(9, Block.glass, Item.emerald);
		addMantelFootRecipe(10, Block.planks, Item.ingotGold);
		addMantelFootRecipe(11, Block.glass, Item.diamond);
		
		addChimney1Recipe(0, "stoneMarble", "stoneMarble");
		addChimney1Recipe(1, "stoneMarble", "stoneMarbleGray");
		addChimney1Recipe(2, "stoneMarble", "stoneMarbleBlack");
		addChimney1Recipe(3, "stoneMarble", Item.brick);
		addChimney1Recipe(4, "stoneMarbleGray", "stoneMarble");
		addChimney1Recipe(5, "stoneMarbleGray", "stoneMarbleGray");
		addChimney1Recipe(6, "stoneMarbleGray", "stoneMarbleBlack");
		addChimney1Recipe(7, Block.stone, Item.coal);
		addChimney1Recipe(8, "stoneMarbleBlack", "stoneMarble");
		addChimney1Recipe(9, "stoneMarbleBlack", "stoneMarbleGray");
		addChimney1Recipe(10, "stoneMarbleBlack", "stoneMarbleBlack");
		addChimney1Recipe(11, Block.cobblestone, Item.coal);
		addChimney1Recipe(12, Block.planks, Item.coal);
		addChimney1Recipe(13, Block.glass, Item.emerald);
		addChimney1Recipe(14, Block.planks, Item.ingotGold);
		addChimney1Recipe(15, Block.glass, Item.diamond);
		addChimney1Recipe(16, new ItemStack(Block.sandStone, 1, 2), Item.coal);

		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(blockChimney2, 1, 0), "I I", "III",
				Character.valueOf('I'), "stoneMarble"));
		addChimney2Recipe(new ItemStack(blockChimney2, 1, 4), "stoneMarble", "stoneMarbleGray");
		addChimney2Recipe(new ItemStack(blockChimney2, 1, 8), "stoneMarble", "stoneMarbleBlack");
		addChimney2Recipe(new ItemStack(blockChimney2, 1, 12), "stoneMarble", Item.brick);
		addChimney2Recipe(new ItemStack(blockChimney2A, 1, 0), "stoneMarbleGray", "stoneMarble");
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(blockChimney2A, 1, 4), "I I", "III",
				Character.valueOf('I'), "stoneMarbleGray"));
		addChimney2Recipe(new ItemStack(blockChimney2A, 1, 8), "stoneMarbleGray", "stoneMarbleBlack");
		addChimney2Recipe(new ItemStack(blockChimney2A, 1, 12), Block.stone, Item.coal);
		addChimney2Recipe(new ItemStack(blockChimney2B, 1, 0), "stoneMarbleBlack", "stoneMarble");
		addChimney2Recipe(new ItemStack(blockChimney2B, 1, 4), "stoneMarbleBlack", "stoneMarbleGray");
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(blockChimney2B, 1, 8), "I I", "III",
				Character.valueOf('I'), "stoneMarbleBlack"));
		addChimney2Recipe(new ItemStack(blockChimney2B, 1, 12), Block.cobblestone, Item.coal);
		addChimney2Recipe(new ItemStack(blockChimney2C, 1, 0), Block.planks, Item.coal);
		addChimney2Recipe(new ItemStack(blockChimney2C, 1, 4), Block.glass, Item.emerald);
		addChimney2Recipe(new ItemStack(blockChimney2C, 1, 8), Block.planks, Item.ingotGold);
		addChimney2Recipe(new ItemStack(blockChimney2C, 1, 12), Block.glass, Item.diamond);

		addChimney3Recipe(0, "stoneMarbleBlack", "stoneMarbleBlack");
		addChimney3Recipe(1, "stoneMarbleGray", "stoneMarbleGray");
		addChimney3Recipe(2, "stoneMarble", "stoneMarble");
		addChimney3Recipe(3, "brickMarbleBlack", "brickMarbleBlack");
		addChimney3Recipe(4, "brickMarbleSmallBlack", "brickMarbleSmallBlack");
		addChimney3Recipe(5, Block.stoneBrick, Block.stoneBrick);
		addChimney3Recipe(6, "brickStoneSmall", "brickStoneSmall");
		addChimney3Recipe(7, Block.stone, Block.stone);
		addChimney3Recipe(8, Block.cobblestone, Block.cobblestone);
		addChimney3Recipe(9, "brickMarble", "brickMarble");
		addChimney3Recipe(10, "brickMarbleSmall", "brickMarbleSmall");
		addChimney3Recipe(11, Block.netherBrick, Block.netherBrick);
		addChimney3Recipe(12, Block.brick, Block.brick);
		addChimney3Recipe(13, Block.glass, Item.emerald);
		addChimney3Recipe(14, Block.planks, Item.ingotGold);
		addChimney3Recipe(15, Block.glass, Item.diamond);
	}

	public static void addHollowRecipe(int i, Object obj1, Object obj2)
	{
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(blockChimneyHollowBricks, 2, i), "I J", "   ", "J J",
				Character.valueOf('I'), obj1,
				Character.valueOf('J'), obj2));
	}

	public static void addMantelCornerRecipe(int i, Object obj1, Object obj2)
	{
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(itemMantelCorner, 2, i), "III", " JI", "  I",
				Character.valueOf('I'), obj1,
				Character.valueOf('J'), obj2));
	}

	public static void addMantelCenterRecipe(ItemStack itemStack, Object obj1, Object obj2)
	{
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(itemStack, "IJI", " I ",
				Character.valueOf('I'), obj1,
				Character.valueOf('J'), obj2));
	}

	public static void addMantelSideRecipe(int i, Object obj1, Object obj2)
	{
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(blockMantelSide, 1, i), " I ", " J ", " I ",
				Character.valueOf('I'), obj1,
				Character.valueOf('J'), obj2));
	}

	public static void addMantelPlainSideRecipe(int i, Object obj1, Object obj2)
	{
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(blockMantelPlainSide, 1, i), " I ", " I ", " J ",
				Character.valueOf('I'), obj1,
				Character.valueOf('J'), obj2));
	}

	public static void addMantelFootRecipe(int i, Object obj1, Object obj2)
	{
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(blockMantelFoot, 1, i), " I ", " I ", "IJI",
				Character.valueOf('I'), obj1,
				Character.valueOf('J'), obj2));
	}

	public static void addChimney1Recipe(int i, Object obj1, Object obj2)
	{
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(itemChimney1, 1, i), " J ", "III",
				Character.valueOf('I'), obj1,
				Character.valueOf('J'), obj2));
	}

	public static void addChimney2Recipe(ItemStack itemStack, Object obj1, Object obj2)
	{
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(itemStack, "IJI", "III",
				Character.valueOf('I'), obj1,
				Character.valueOf('J'), obj2));
	}

	public static void addChimney3Recipe(int i, Object obj1, Object obj2)
	{
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(blockChimney3, 1, i), " J ", "I I",
				Character.valueOf('I'), obj1,
				Character.valueOf('J'), obj2));
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
	}
}
