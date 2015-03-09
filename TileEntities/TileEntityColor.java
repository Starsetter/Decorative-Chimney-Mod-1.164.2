package DecorativeChimney.TileEntities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileEntityColor extends TileEntity
{
	private int color1;
	private int color2;

	public TileEntityColor()
	{
		color1 = 0;
		color2 = 0;
	}

	public void setColor(int color)
	{
		this.color1 = color;
		this.color2 = color;
	}

	public int getColor1()
	{
		return color1;
	}

	public int getColor2()
	{
		return color2;
	}

	@Override
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readFromNBT(par1NBTTagCompound);
		color1 = par1NBTTagCompound.getInteger("color1");
		color2 = par1NBTTagCompound.getInteger("color2");
	}

	@Override
	public void writeToNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.writeToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setInteger("color1", color1 & 255);
		par1NBTTagCompound.setInteger("color2", color2 & 255);
	}

	public void onDataPacket(INetworkManager net, Packet132TileEntityData packet)
	{
		readFromNBT(packet.data);
	}

	public Packet getDescriptionPacket() {
		NBTTagCompound tagCompound = new NBTTagCompound();
		writeToNBT(tagCompound);
		return new Packet132TileEntityData(xCoord, yCoord, zCoord, 1, tagCompound);
	}
}
