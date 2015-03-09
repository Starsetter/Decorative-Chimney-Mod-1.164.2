package DecorativeChimney;

import java.io.File;

import net.minecraftforge.common.Configuration;

public class ConfigurationHandler
{
	public static void configurationInit(File file)
	{
		Configuration configuration = new Configuration(file);

		configuration.load();

		DecorativeChimneyCore.blockChimneyHolllowBricksBlockID = configuration.getBlock("Block", "Hollow Chimney Brick Blocks", 2001).getInt();
		DecorativeChimneyCore.blockLogsOnBlockID = configuration.getBlock("Block", "Logs On", 2002).getInt();
		DecorativeChimneyCore.blockLogsOffBlockID = configuration.getBlock("Block", "Logs Off", 2003).getInt();
		DecorativeChimneyCore.blockMantelCornerBlockID = configuration.getBlock("Block", "Mantel Corner", 2004).getInt();
		DecorativeChimneyCore.blockMantelCenterBlockID = configuration.getBlock("Block", "Mantel Center", 2006).getInt();
		DecorativeChimneyCore.blockMantelCenterABlockID = configuration.getBlock("Block", "Mantel Center A", 2007).getInt();
		DecorativeChimneyCore.blockMantelSideBlockID = configuration.getBlock("Block", "Mantel Side", 2008).getInt();
		DecorativeChimneyCore.blockMantelPlainSideBlockID = configuration.getBlock("Block", "Mantel Plain Side", 2009).getInt();
		DecorativeChimneyCore.blockMantelFootBlockID = configuration.getBlock("Block", "Mantel Footer", 2010).getInt();
		DecorativeChimneyCore.blockChimney1BlockID = configuration.getBlock("Block", "Chimney Style 1", 2011).getInt();
		DecorativeChimneyCore.blockChimney2BlockID = configuration.getBlock("Block", "Chimney Style 2", 2012).getInt();
		DecorativeChimneyCore.blockChimney3BlockID = configuration.getBlock("Block", "Chimney Style 3", 2016).getInt();

		DecorativeChimneyCore.itemChimney1BlockID = configuration.getItem("Item", "Chimney Style 1", 20015).getInt() - 256;
		DecorativeChimneyCore.itemChimney2BlockID = configuration.getItem("Item", "Chimney Style 2", 20016).getInt() - 256;
//		itemChimney3BlockID = configuration.getItem("Item", "Chimney Style 3", 20017).getInt() - 256;
		DecorativeChimneyCore.itemMantelCornerBlockID = configuration.getItem("Item", "Mantel Corner", 20018).getInt() - 256;

		configuration.save();

	}
}
