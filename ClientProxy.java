package DecorativeChimney;

import DecorativeChimney.InventoryRenders.InvBlock;
import DecorativeChimney.InventoryRenders.ItemChimney1Render;
import DecorativeChimney.InventoryRenders.ItemChimney2Render;
import DecorativeChimney.TileEntityRenders.*;

import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.MinecraftForgeClient;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderers()
	{
//        MinecraftForgeClient.registerItemRenderer(DecorativeChimneyCore.itemChimney1.itemID, (IItemRenderer) new ItemChimney1Render());
//        MinecraftForgeClient.registerItemRenderer(DecorativeChimneyCore.itemChimney2.itemID, (IItemRenderer) new ItemChimney2Render());
	}

	public void preInitLoading()
    {
    }

    public void initRendering()
    {
		DecorativeChimneyCore.blockHollowBricksModelID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(DecorativeChimneyCore.blockHollowBricksModelID, new InvBlock());
        
		DecorativeChimneyCore.blockMantelCornerModelID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(DecorativeChimneyCore.blockMantelCornerModelID, new InvBlock());
        
		DecorativeChimneyCore.blockMantelCenterModelID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(DecorativeChimneyCore.blockMantelCenterModelID, new InvBlock());
        
		DecorativeChimneyCore.blockMantelSideModelID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(DecorativeChimneyCore.blockMantelSideModelID, new InvBlock());
        
		DecorativeChimneyCore.blockMantelPlainSideModelID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(DecorativeChimneyCore.blockMantelPlainSideModelID, new InvBlock());
        
		DecorativeChimneyCore.blockMantelFootModelID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(DecorativeChimneyCore.blockMantelFootModelID, new InvBlock());
        
		DecorativeChimneyCore.blockChimney1ModelID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(DecorativeChimneyCore.blockChimney1ModelID, new InvBlock());
        
		DecorativeChimneyCore.blockChimney2ModelID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(DecorativeChimneyCore.blockChimney2ModelID, new InvBlock());
        
		DecorativeChimneyCore.blockChimney3ModelID = RenderingRegistry.getNextAvailableRenderId();
        RenderingRegistry.registerBlockHandler(DecorativeChimneyCore.blockChimney3ModelID, new InvBlock());
        
        ClientRegistry.bindTileEntitySpecialRenderer(DecorativeChimney.TileEntities.TileEntityLogs.class, new TileEntityLogsRender());
        ClientRegistry.bindTileEntitySpecialRenderer(DecorativeChimney.TileEntities.TileEntityChimney1.class, new TileEntityChimney1Render());
        ClientRegistry.bindTileEntitySpecialRenderer(DecorativeChimney.TileEntities.TileEntityChimney2.class, new TileEntityChimney2Render());
        ClientRegistry.bindTileEntitySpecialRenderer(DecorativeChimney.TileEntities.TileEntityChimney2A.class, new TileEntityChimney2ARender());
        ClientRegistry.bindTileEntitySpecialRenderer(DecorativeChimney.TileEntities.TileEntityChimney2B.class, new TileEntityChimney2BRender());
        ClientRegistry.bindTileEntitySpecialRenderer(DecorativeChimney.TileEntities.TileEntityChimney2C.class, new TileEntityChimney2CRender());
        ClientRegistry.bindTileEntitySpecialRenderer(DecorativeChimney.TileEntities.TileEntityChimney3.class, new TileEntityChimney3Render());
    }	
}