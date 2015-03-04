package DecorativeChimney.TileEntityRenders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import DecorativeChimney.Models.ModelChimney3;
import DecorativeChimney.TileEntities.TileEntityChimney3;


public class TileEntityChimney3Render extends TileEntitySpecialRenderer
{
	public static TileEntityChimney3Render instance = new TileEntityChimney3Render();
	public TileEntityChimney3Render()
	{
		model = new ModelChimney3();
	}

	public void renderAModelAt(TileEntityChimney3 tile, double d, double d1, double d2, float f)
	{
		int i =0; // a regular int, with a zero. more on this below

		if(tile.worldObj != null) // to tell the world that your object is placed.
		{
			i =(tile.worldObj.getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord)); // to tell the game it needs to pick up metadata from your block
		}

		switch (i) // the good part. get's your block muliple textures
		{
			case 0:
				tileEntityRenderer.renderEngine.bindTexture(new ResourceLocation("decorativechimney:textures/Chimney3B.png")); // bindTextureByName + the path to your image. for the block that you gave damage number 0
				break;
			case 1:
				tileEntityRenderer.renderEngine.bindTexture(new ResourceLocation("decorativechimney:textures/Chimney3G.png")); // and so on and so on
				break;
			case 2:
				tileEntityRenderer.renderEngine.bindTexture(new ResourceLocation("decorativechimney:textures/Chimney3W.png"));
				break;
			case 3:
				tileEntityRenderer.renderEngine.bindTexture(new ResourceLocation("decorativechimney:textures/Chimney3LB.png"));
				break;
			case 4:
				tileEntityRenderer.renderEngine.bindTexture(new ResourceLocation("decorativechimney:textures/Chimney3SB.png"));
				break;
			case 5:
				tileEntityRenderer.renderEngine.bindTexture(new ResourceLocation("decorativechimney:textures/Chimney3LG.png"));
				break;
			case 6:
				tileEntityRenderer.renderEngine.bindTexture(new ResourceLocation("decorativechimney:textures/Chimney3SG.png"));
				break;
			case 7:
				tileEntityRenderer.renderEngine.bindTexture(new ResourceLocation("decorativechimney:textures/Chimney3S.png"));
				break;
			case 8:
				tileEntityRenderer.renderEngine.bindTexture(new ResourceLocation("decorativechimney:textures/Chimney3CS.png"));
				break;
			case 9:
				tileEntityRenderer.renderEngine.bindTexture(new ResourceLocation("decorativechimney:textures/Chimney3LW.png"));
				break;
			case 10:
				tileEntityRenderer.renderEngine.bindTexture(new ResourceLocation("decorativechimney:textures/Chimney3SW.png"));
				break;
			case 11:
				tileEntityRenderer.renderEngine.bindTexture(new ResourceLocation("decorativechimney:textures/Chimney3NB.png"));
				break;
			case 12:
				tileEntityRenderer.renderEngine.bindTexture(new ResourceLocation("decorativechimney:textures/Chimney3Br.png"));
				break;
			case 13:
				tileEntityRenderer.renderEngine.bindTexture(new ResourceLocation("decorativechimney:textures/Chimney3E.png"));
				break;
			case 14:
				tileEntityRenderer.renderEngine.bindTexture(new ResourceLocation("decorativechimney:textures/Chimney3Go.png"));
				break;
			default:
				tileEntityRenderer.renderEngine.bindTexture(new ResourceLocation("decorativechimney:textures/Chimney3D.png"));
		}
		
		GL11.glPushMatrix(); //start
		GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F); //size
		GL11.glRotatef(0, 0.0F, 1.0F, 0.0F); //rotate based on metadata
		GL11.glScalef(1.0F, -1F, -1F); //if you read this comment out this line and you can see what happens
		model.renderModel(0.0625F); //renders and yes 0.0625 is a random number
		GL11.glPopMatrix(); //end
	}

	public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
	{
		renderAModelAt((TileEntityChimney3) tileentity, d, d1, d2, f); //where to render
	}

	private ModelChimney3 model;
}