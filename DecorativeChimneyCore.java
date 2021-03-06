package DecorativeChimney;

import java.io.File;
import java.util.Random;

import DecorativeChimney.Blocks.*;
import DecorativeChimney.InventoryRenders.ItemChimney1Render;
import DecorativeChimney.InventoryRenders.ItemChimney2Render;
import DecorativeChimney.InventoryRenders.ItemChimney3Render;
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
//	static int blockMantelCenterABlockID;
	static int blockMantelSideBlockID;
	static int blockMantelPlainSideBlockID;
	static int blockMantelFootBlockID;
	static int blockChimney1BlockID;
	static int blockChimney2BlockID;
	static int blockChimney3BlockID;

	static int itemChimney1BlockID;
	static int itemChimney2BlockID;
	static int itemChimney3BlockID;
	static int itemMantelCornerBlockID;
	static int itemMantelCenterBlockID;

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
//	public static Block blockMantelCenterA;
	public static Block blockMantelSide;
	public static Block blockMantelPlainSide;
	public static Block blockMantelFoot;
	public static Block blockChimney1;
	public static Block blockChimney2;
	public static Block blockChimney3;
	
	public static Item itemChimney1;
	public static Item itemChimney2;
	public static Item itemChimney3;
	public static Item itemMantelCorner;
	public static Item itemMantelCenter;
	
	private final static ItemChimney1Render itemChimney1Render = new ItemChimney1Render();
	private final static ItemChimney2Render itemChimney2Render = new ItemChimney2Render();
	private final static ItemChimney3Render itemChimney3Render = new ItemChimney3Render();
	
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
			"Black with Gray Marble", "Black with White Marble", "Gray with Black Marble", "Gray with White Marble",
			"White with Black Marble", "White with Gray Marble", "Brick", "Stone",
			"Cobblestone", "Emerald", "Gold", "Diamond",
			"Oak Wood Plank", "Birch Wood Plank", "Spruce Wood Plank", "Jungle Wood Plank"
		};
	
	private static final String[] blockMantel2TypeNames =
		{ 
			"Black Marble", "Gray Marble", "White Marble", "Black Marble Bricks", "Small Black Marble Bricks",
			"Stone Bricks", "Small Stone Bricks", "Stone", "Cobblestone", "White Marble Bricks",
			"Small White Marble Bricks", "Nether Brick", "Brick", "Emerald", "Gold", "Diamond"
		};

	private static final String[] blockChimneyTypeNames =
		{ 
			"Black Marble", "Black with Gray Marble", "Black with White Marble", "Gray with Black Marble",
			"Gray Marble", "Gray with White Marble", "White with Black Marble", "White with Gray Marble",
			"White Marble", "Black Marble with Netherbrick", "White Marble with Brick", "Stone",
			"Cobblestone", "Emerald", "Gold", "Diamond",
			"Smooth Sand Stone"
		};
	
	private static final String[] blockChimney2TypeNames =
		{ 
			"Black Marble", "Gray Marble", "White Marble", "Black Marble Bricks", "Small Black Marble Bricks",
			"Stone Bricks", "Small Stone Bricks", "Stone", "Cobblestone", "White Marble Bricks",
			"Small White Marble Bricks", "Nether Brick", "Brick", "Emerald", "Gold", "Diamond", "Smooth Sand Stone"
		};


	@SidedProxy(clientSide="DecorativeChimney.ClientProxy", serverSide="DecorativeChimney.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		proxy.registerRenderers();
		proxy.initRenders();
		
		ConfigurationHandler.configurationInit(event.getSuggestedConfigurationFile());
	}

	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
		blockChimneyHollowBricks = new BlockChimneyHollow(blockChimneyHolllowBricksBlockID);
		blockMantelCorner = new BlockMantelCorner(blockMantelCornerBlockID, TileEntityColor.class);
		blockMantelCenter = new BlockMantelCenter(blockMantelCenterBlockID, TileEntityColor.class);
//		blockMantelCenterA = new BlockMantelCenterA(blockMantelCenterABlockID);
		blockMantelSide = new BlockMantelSide(blockMantelSideBlockID);
		blockMantelPlainSide = new BlockMantelPlainSide(blockMantelPlainSideBlockID);
		blockMantelFoot = new BlockMantelFoot(blockMantelFootBlockID);
        blockLogsOn = new BlockLogsOn(blockLogsOnBlockID, TileEntityLogs.class);
        blockLogsOff = new BlockLogsOff(blockLogsOffBlockID, TileEntityLogs.class);
		blockChimney1 = new BlockChimney1(blockChimney1BlockID, TileEntityChimney1.class);
		blockChimney2 = new BlockChimney2(blockChimney2BlockID, TileEntityChimney2.class);
        blockChimney3 = new BlockChimney3(blockChimney3BlockID, TileEntityChimney3.class);
        
        itemChimney1 = new ItemChimney1(itemChimney1BlockID);
        itemChimney2 = new ItemChimney2(itemChimney2BlockID);
        itemChimney3 = new ItemChimney3(itemChimney3BlockID);
        itemMantelCorner = new ItemMantelCorner(itemMantelCornerBlockID);
        itemMantelCenter = new ItemMantelCenter(itemMantelCenterBlockID);

		LanguageRegistry.instance().addStringLocalization("itemGroup.tabChimney", "en_US", "Fireplaces and Chimneys");

		GameRegistry.registerBlock(blockChimneyHollowBricks, ItemBlockChimneyHollow.class, "Hollow Chimney Bricks");
		GameRegistry.registerBlock(blockLogsOn, "Ignited Logs");
		GameRegistry.registerBlock(blockLogsOff, "Logs");
		GameRegistry.registerBlock(blockMantelCorner, "Mantel Corner");
		GameRegistry.registerBlock(blockMantelCenter, "Mantel Center");
//		GameRegistry.registerBlock(blockMantelCenterA, ItemBlockMantelCenterA.class, "Mantel Center A");
		GameRegistry.registerBlock(blockMantelSide, ItemBlockMantelSide.class, "Mantel Side");
		GameRegistry.registerBlock(blockMantelPlainSide, ItemBlockMantelPlainSide.class, "Mantel Plain Side");
		GameRegistry.registerBlock(blockMantelFoot, ItemBlockMantelFoot.class, "Mantel Foot");
		GameRegistry.registerBlock(blockChimney1, "Chimney Style 1");
		GameRegistry.registerBlock(blockChimney2, "Chimney Style 2");
		GameRegistry.registerBlock(blockChimney3, "Chimney Style 3");

		GameRegistry.registerItem(itemChimney1, "Item Chimney Style 1");
		GameRegistry.registerItem(itemChimney2, "Item Chimney Style 2");
		GameRegistry.registerItem(itemChimney3, "Item Chimney Style 3");
		GameRegistry.registerItem(itemMantelCorner, "Item Mantel Corner");
		GameRegistry.registerItem(itemMantelCenter, "Item Mantel Center");
		
		GameRegistry.registerTileEntity(TileEntityLogs.class, "LogsOn");
		GameRegistry.registerTileEntity(TileEntityLogs.class, "LogsOff");
		GameRegistry.registerTileEntity(TileEntityChimney1.class, "Chimney1");
		GameRegistry.registerTileEntity(TileEntityChimney2.class, "Chimney2");
		GameRegistry.registerTileEntity(TileEntityChimney3.class, "Chimney3");
		GameRegistry.registerTileEntity(TileEntityColor.class, "Color");
		
		MinecraftForgeClient.registerItemRenderer(itemChimney1.itemID, itemChimney1Render);
		MinecraftForgeClient.registerItemRenderer(itemChimney2.itemID, itemChimney2Render);
		MinecraftForgeClient.registerItemRenderer(itemChimney3.itemID, itemChimney3Render);

//Tool Tip Name
		LanguageRegistry.addName(blockLogsOn, "Logs");
		LanguageRegistry.addName(blockLogsOff, "Logs");

		for (int i = 0; i < blockChimneyTypeNames.length; i++)
		{
			ItemStack itemChimneyStyle1Stack = new ItemStack(itemChimney1, 64, i);

			LanguageRegistry.addName(itemChimneyStyle1Stack, blockChimneyTypeNames[itemChimneyStyle1Stack.getItemDamage()] + " Chimney");

			ItemStack itemChimneyStyle2Stack = new ItemStack(itemChimney2, 64, i);

			LanguageRegistry.addName(itemChimneyStyle2Stack, blockChimneyTypeNames[itemChimneyStyle2Stack.getItemDamage()] + " Chimney");

			ItemStack itemChimneysytle3Stack = new ItemStack(itemChimney3, 64, i);

			LanguageRegistry.addName(itemChimneysytle3Stack, blockChimney2TypeNames[itemChimneysytle3Stack.getItemDamage()] + " Chimney");
		}
		for (int i = 0; i < 16; i++)
		{
			ItemStack blockChimneyHollowBricksStack = new ItemStack(blockChimneyHollowBricks, 64, i);

			LanguageRegistry.addName(blockChimneyHollowBricksStack, "Hollow " + blockChimneyBrickTypeNames[blockChimneyHollowBricksStack.getItemDamage()]);

			ItemStack blockMantelPlainSideStack = new ItemStack(blockMantelPlainSide, 64, i);

			LanguageRegistry.addName(blockMantelPlainSideStack, blockMantel2TypeNames[blockMantelPlainSideStack.getItemDamage()] + " Mantel Plain Side");
		}
		for (int i = 0; i < blockMantelTypeNames.length; i++)
		{
			ItemStack itemMantelCornerStack = new ItemStack(itemMantelCorner, 64, i);

			LanguageRegistry.addName(itemMantelCornerStack, blockMantelTypeNames[itemMantelCornerStack.getItemDamage()] + " Mantel Corner");

			ItemStack itemMantelCenterStack = new ItemStack(itemMantelCenter, 64, i);

			LanguageRegistry.addName(itemMantelCenterStack, blockMantelTypeNames[itemMantelCenterStack.getItemDamage()] + " Mantel Center");

			ItemStack blockMantelSideStack = new ItemStack(blockMantelSide, 64, i);

			LanguageRegistry.addName(blockMantelSideStack, blockMantelTypeNames[blockMantelSideStack.getItemDamage()] + " Mantel Side");

			ItemStack blockMantelFootStack = new ItemStack(blockMantelFoot, 64, i);

			LanguageRegistry.addName(blockMantelFootStack, blockMantelTypeNames[blockMantelFootStack.getItemDamage()] + " Mantel Footer");
		}
		
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

		addMantelCornerRecipe(0, "stoneMarbleBlack", "stoneMarbleGray");
		addMantelCornerRecipe(1, "stoneMarbleBlack", "stoneMarble");
		addMantelCornerRecipe(2, "stoneMarbleGray", "stoneMarbleBlack");
		addMantelCornerRecipe(3, "stoneMarbleGray", "stoneMarble");
		addMantelCornerRecipe(4, "stoneMarble", "stoneMarbleBlack");
		addMantelCornerRecipe(5, "stoneMarble", "stoneMarbleGray");
		addMantelCornerRecipe(6, Block.brick, Item.coal);
		addMantelCornerRecipe(7, Block.stone, Item.coal);
		addMantelCornerRecipe(8, Block.cobblestone, Item.coal);
		addMantelCornerRecipe(9, Block.glass, Item.emerald);
		addMantelCornerRecipe(10, Block.planks, Item.ingotGold);
		addMantelCornerRecipe(11, Block.glass, Item.diamond);
		addMantelCornerRecipe(12, new ItemStack(Block.planks, 1, 0), Item.coal);
		addMantelCornerRecipe(13, new ItemStack(Block.planks, 1, 2), Item.coal);
		addMantelCornerRecipe(14, new ItemStack(Block.planks, 1, 1), Item.coal);
		addMantelCornerRecipe(15, new ItemStack(Block.planks, 1, 3), Item.coal);
		
		addMantelCenterRecipe(0, "stoneMarbleBlack", "stoneMarbleGray");
		addMantelCenterRecipe(1, "stoneMarbleBlack", "stoneMarble");
		addMantelCenterRecipe(2, "stoneMarbleGray", "stoneMarbleBlack");
		addMantelCenterRecipe(3, "stoneMarbleGray", "stoneMarble");
		addMantelCenterRecipe(4, "stoneMarble", "stoneMarbleBlack");
		addMantelCenterRecipe(5, "stoneMarble", "stoneMarbleGray");
		addMantelCenterRecipe(6, Block.brick, Item.coal);
		addMantelCenterRecipe(7, Block.stone, Item.coal);
		addMantelCenterRecipe(8, Block.cobblestone, Item.coal);
		addMantelCenterRecipe(9, Block.glass, Item.emerald);
		addMantelCenterRecipe(10, Block.planks, Item.ingotGold);
		addMantelCenterRecipe(11, Block.glass, Item.diamond);
		addMantelCenterRecipe(12, new ItemStack(Block.planks, 1, 0), Item.coal);
		addMantelCenterRecipe(13, new ItemStack(Block.planks, 1, 2), Item.coal);
		addMantelCenterRecipe(14, new ItemStack(Block.planks, 1, 1), Item.coal);
		addMantelCenterRecipe(15, new ItemStack(Block.planks, 1, 3), Item.coal);
		
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
		
		addMantelSideRecipe(0, "stoneMarbleBlack", "stoneMarbleGray");
		addMantelSideRecipe(1, "stoneMarbleBlack", "stoneMarble");
		addMantelSideRecipe(2, "stoneMarbleGray", "stoneMarbleBlack");
		addMantelSideRecipe(3, "stoneMarbleGray", "stoneMarble");
		addMantelSideRecipe(4, "stoneMarble", "stoneMarbleBlack");
		addMantelSideRecipe(5, "stoneMarble", "stoneMarbleGray");
		addMantelSideRecipe(6, Block.brick, Item.coal);
		addMantelSideRecipe(7, Block.stone, Item.coal);
		addMantelSideRecipe(8, Block.cobblestone, Item.coal);
		addMantelSideRecipe(9, Block.glass, Item.emerald);
		addMantelSideRecipe(10, Block.planks, Item.ingotGold);
		addMantelSideRecipe(11, Block.glass, Item.diamond);
		addMantelSideRecipe(12, new ItemStack(Block.planks, 1, 0), Item.coal);
		addMantelSideRecipe(13, new ItemStack(Block.planks, 1, 2), Item.coal);
		addMantelSideRecipe(14, new ItemStack(Block.planks, 1, 1), Item.coal);
		addMantelSideRecipe(15, new ItemStack(Block.planks, 1, 3), Item.coal);

		addMantelFootRecipe(0, "stoneMarbleBlack", "stoneMarbleGray");
		addMantelFootRecipe(1, "stoneMarbleBlack", "stoneMarble");
		addMantelFootRecipe(2, "stoneMarbleGray", "stoneMarbleBlack");
		addMantelFootRecipe(3, "stoneMarbleGray", "stoneMarble");
		addMantelFootRecipe(4, "stoneMarble", "stoneMarbleBlack");
		addMantelFootRecipe(5, "stoneMarble", "stoneMarbleGray");
		addMantelFootRecipe(6, Block.brick, Item.coal);
		addMantelFootRecipe(7, Block.stone, Item.coal);
		addMantelFootRecipe(8, Block.cobblestone, Item.coal);
		addMantelFootRecipe(9, Block.glass, Item.emerald);
		addMantelFootRecipe(10, Block.planks, Item.ingotGold);
		addMantelFootRecipe(11, Block.glass, Item.diamond);
		addMantelFootRecipe(12, new ItemStack(Block.planks, 1, 0), Item.coal);
		addMantelFootRecipe(13, new ItemStack(Block.planks, 1, 2), Item.coal);
		addMantelFootRecipe(14, new ItemStack(Block.planks, 1, 1), Item.coal);
		addMantelFootRecipe(15, new ItemStack(Block.planks, 1, 3), Item.coal);

		addChimney1Recipe(0, "stoneMarbleBlack", "stoneMarbleBlack");
		addChimney1Recipe(1, "stoneMarbleBlack", "stoneMarbleGray");
		addChimney1Recipe(2, "stoneMarbleBlack", "stoneMarble");
		addChimney1Recipe(3, "stoneMarbleGray", "stoneMarbleBlack");
		addChimney1Recipe(4, "stoneMarbleGray", "stoneMarbleGray");
		addChimney1Recipe(5, "stoneMarbleGray", "stoneMarble");
		addChimney1Recipe(6, "stoneMarble", "stoneMarbleBlack");
		addChimney1Recipe(7, "stoneMarble", "stoneMarbleGray");
		addChimney1Recipe(8, "stoneMarble", "stoneMarble");
		addChimney1Recipe(9, "stoneMarbleBlack", Item.netherrackBrick);
		addChimney1Recipe(10, "stoneMarble", Item.brick);
		addChimney1Recipe(11, Block.stone, Item.coal);
		addChimney1Recipe(12, Block.cobblestone, Item.coal);
		addChimney1Recipe(13, Block.glass, Item.emerald);
		addChimney1Recipe(14, Block.planks, Item.ingotGold);
		addChimney1Recipe(15, Block.glass, Item.diamond);
		addChimney1Recipe(16, new ItemStack(Block.sandStone, 1, 2), Item.coal);

		addChimney2Recipe(0, "stoneMarbleBlack", "stoneMarbleBlack");
		addChimney2Recipe(1, "stoneMarbleBlack", "stoneMarbleGray");
		addChimney2Recipe(2, "stoneMarbleBlack", "stoneMarble");
		addChimney2Recipe(3, "stoneMarbleGray", "stoneMarbleBlack");
		addChimney2Recipe(4, "stoneMarbleGray", "stoneMarbleGray");
		addChimney2Recipe(5, "stoneMarbleGray", "stoneMarble");
		addChimney2Recipe(6, "stoneMarble", "stoneMarbleBlack");
		addChimney2Recipe(7, "stoneMarble", "stoneMarbleGray");
		addChimney2Recipe(8, "stoneMarble", "stoneMarble");
		addChimney2Recipe(9, "stoneMarbleBlack", Item.netherrackBrick);
		addChimney2Recipe(10, "stoneMarble", Item.brick);
		addChimney2Recipe(11, Block.stone, Item.coal);
		addChimney2Recipe(12, Block.cobblestone, Item.coal);
		addChimney2Recipe(13, Block.glass, Item.emerald);
		addChimney2Recipe(14, Block.planks, Item.ingotGold);
		addChimney2Recipe(15, Block.glass, Item.diamond);
		addChimney2Recipe(16, new ItemStack(Block.sandStone, 1, 2), Item.coal);

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
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(itemMantelCorner, 2, i), "III", "IJI", " I ",
				Character.valueOf('I'), obj1,
				Character.valueOf('J'), obj2));
	}

	public static void addMantelCenterRecipe(int i, Object obj1, Object obj2)
	{
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(itemMantelCenter, 2, i), "IJI", " I ",
				Character.valueOf('I'), obj1,
				Character.valueOf('J'), obj2));
	}

	public static void addMantelSideRecipe(int i, Object obj1, Object obj2)
	{
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(blockMantelSide, 2, i), " I ", " J ", " I ",
				Character.valueOf('I'), obj1,
				Character.valueOf('J'), obj2));
	}

	public static void addMantelPlainSideRecipe(int i, Object obj1, Object obj2)
	{
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(blockMantelPlainSide, 2, i), " I ", " I ", " J ",
				Character.valueOf('I'), obj1,
				Character.valueOf('J'), obj2));
	}

	public static void addMantelFootRecipe(int i, Object obj1, Object obj2)
	{
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(blockMantelFoot, 2, i), " I ", " I ", "IJI",
				Character.valueOf('I'), obj1,
				Character.valueOf('J'), obj2));
	}

	public static void addChimney1Recipe(int i, Object obj1, Object obj2)
	{
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(itemChimney1, 1, i), " J ", "III",
				Character.valueOf('I'), obj1,
				Character.valueOf('J'), obj2));
	}

	public static void addChimney2Recipe(int i, Object obj1, Object obj2)
	{
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(itemChimney2, 1, i), "IJI", "III",
				Character.valueOf('I'), obj1,
				Character.valueOf('J'), obj2));
	}

	public static void addChimney3Recipe(int i, Object obj1, Object obj2)
	{
		CraftingManager.getInstance().getRecipeList().add(new ShapedOreRecipe(new ItemStack(itemChimney3, 1, i), " J ", "I I",
				Character.valueOf('I'), obj1,
				Character.valueOf('J'), obj2));
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
	}
}
