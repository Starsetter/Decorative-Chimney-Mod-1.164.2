package DecorativeChimney.TileEntityRenders;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import DecorativeChimney.Models.ModelChimney2;
import DecorativeChimney.TileEntities.TileEntityChimney2B;


public class TileEntityChimney2BRender extends TileEntitySpecialRenderer
{

	public TileEntityChimney2BRender()
	{
		model = new ModelChimney2();
	}

	public void renderAModelAt(TileEntityChimney2B tile, double d, double d1, double d2, float f)
	{
		int i = tile.worldObj.getBlockMetadata(tile.xCoord, tile.yCoord, tile.zCoord); //this is for rotation
		int j = 0;

		if(i == 1 || i == 3 || i == 5 || i == 7 || i == 9 || i == 11 || i == 13 || i == 15)
		{
			j = 90;
		}

		switch (i) // the good part. get's your block muliple textures
		{
			case 0:
				tileEntityRenderer.renderEngine.bindTexture(new ResourceLocation("decorativechimney:textures/Chimney1BW.png")); // bindTextureByName + the path to your image. for the block that you gave damage number 0
				break;
			case 1:
				tileEntityRenderer.renderEngine.bindTexture(new ResourceLocation("decorativechimney:textures/Chimney1BW.png"));
				break;
			case 2:
				tileEntityRenderer.renderEngine.bindTexture(new ResourceLocation("decorativechimney:textures/Chimney1BW.png"));
				break;
			case 3:
				tileEntityRenderer.renderEngine.bindTexture(new ResourceLocation("decorativechimney:textures/Chimney1BW.png"));
				break;
			case 4:
				tileEntityRenderer.renderEngine.bindTexture(new ResourceLocation("decorativechimney:textures/Chimney1BG.png"));
				break;
			case 5:
				tileEntityRenderer.renderEngine.bindTexture(new ResourceLocation("decorativechimney:textures/Chimney1BG.png"));
				break;
			case 6:
				tileEntityRenderer.renderEngine.bindTexture(new ResourceLocation("decorativechimney:textures/Chimney1BG.png"));
				break;
			case 7:
				tileEntityRenderer.renderEngine.bindTexture(new ResourceLocation("decorativechimney:textures/Chimney1BG.png"));
				break;
			case 8:
				tileEntityRenderer.renderEngine.bindTexture(new ResourceLocation("decorativechimney:textures/Chimney1B.png"));
				break;
			case 9:
				tileEntityRenderer.renderEngine.bindTexture(new ResourceLocation("decorativechimney:textures/Chimney1B.png"));
				break;
			case 10:
				tileEntityRenderer.renderEngine.bindTexture(new ResourceLocation("decorativechimney:textures/Chimney1B.png"));
				break;
			case 11:
				tileEntityRenderer.renderEngine.bindTexture(new ResourceLocation("decorativechimney:textures/Chimney1B.png"));
				break;
			case 12:
				tileEntityRenderer.renderEngine.bindTexture(new ResourceLocation("decorativechimney:textures/Chimney1CS.png"));
				break;
			case 13:
				tileEntityRenderer.renderEngine.bindTexture(new ResourceLocation("decorativechimney:textures/Chimney1CS.png"));
				break;
			case 14:
				tileEntityRenderer.renderEngine.bindTexture(new ResourceLocation("decorativechimney:textures/Chimney1CS.png"));
				break;
			case 15:
				tileEntityRenderer.renderEngine.bindTexture(new ResourceLocation("decorativechimney:textures/Chimney1Cs.png"));
				break;
			default:
				tileEntityRenderer.renderEngine.bindTexture(new ResourceLocation("decorativechimney:textures/Chimney1BW.png"));
		}
		
		GL11.glPushMatrix(); //start
		GL11.glTranslatef((float)d + 0.5F, (float)d1 + 1.5F, (float)d2 + 0.5F); //size
		GL11.glRotatef(j, 0.0F, 1.0F, 0.0F); //rotate based on metadata
		GL11.glScalef(1.0F, -1F, -1F); //if you read this comment out this line and you can see what happens
		model.renderModel(0.0625F); //renders and yes 0.0625 is a random number
		GL11.glPopMatrix(); //end
	}

	public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
	{
		renderAModelAt((TileEntityChimney2B) tileentity, d, d1, d2, f); //where to render
	}

	private ModelChimney2 model;
}