package DecorativeChimney.TileEntities;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;

public class TileEntityChimney1 extends TileEntity
{
    private int chimneyType;

    private int chimneyRotation;

    @Override
    public void writeToNBT(NBTTagCompound nbtTagCompound)
    {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setInteger("Type", (int)(this.chimneyType & 255));
        nbtTagCompound.setInteger("Rot", (int)(this.chimneyRotation & 255));
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound)
    {
        super.readFromNBT(nbtTagCompound);
        this.chimneyType = nbtTagCompound.getInteger("Type");
        this.chimneyRotation = nbtTagCompound.getInteger("Rot");
    }

    public void onDataPacket(INetworkManager inetworkManager, Packet132TileEntityData packet132TileEntityData)
    {
        readFromNBT(packet132TileEntityData.data);
    }

    public Packet getDescriptionPacket()
    {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        this.writeToNBT(nbtTagCompound);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 4, nbtTagCompound);
    }

    public void setChimneyType(int type)
    {
        this.chimneyType = type;
    }

    public int getChimneyType()
    {
        return this.chimneyType;
    }

    public void setChimneyRotation(int rotation)
    {
        this.chimneyRotation = rotation;
    }

    @SideOnly(Side.CLIENT)
    public int getChimneyRotation()
    {
        return this.chimneyRotation;
    }
}