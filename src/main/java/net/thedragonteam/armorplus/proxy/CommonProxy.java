package net.thedragonteam.armorplus.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.FMLInjectionData;
import net.thedragonteam.armorplus.ArmorPlus;
import net.thedragonteam.armorplus.client.gui.ARPTab;
import net.thedragonteam.armorplus.tileentity.TileEntityAdvancedArmorForge;
import net.thedragonteam.armorplus.tileentity.TileEntityArmorForge;
import net.thedragonteam.armorplus.worldgen.OreGen;
import net.thedragonteam.core.util.LogHelper;

import java.io.File;
import java.lang.ref.WeakReference;

public class CommonProxy {

    protected static WeakReference<EntityPlayer> dummyPlayer = new WeakReference<EntityPlayer>(null);

    public void preInit(FMLPreInitializationEvent event) {
        LogHelper.info("Finished PreInitialization");
    }

    public void init(FMLInitializationEvent event) {
        ARPTab.initialize();

        LogHelper.info("Finished Initialization");
    }

    public void postInit(FMLPostInitializationEvent event) {
        LogHelper.info("Finished PostInitialization");
    }

    /**
     * Whether or not the game is paused.
     */
    public boolean isPaused() {
        return false;
    }

    public void registerRenderers(ArmorPlus armorPlus) {
    }

    public void registerRenderer() {
    }

    public void registerModels() {
    }

    /**
     * Gets the Minecraft base directory.
     *
     * @return base directory
     */
    public File getMinecraftDir() {
        return (File) FMLInjectionData.data()[6];
    }

    public void registerTileEntities() {
        GameRegistry.registerTileEntity(TileEntityArmorForge.class, "ArmorForge");
        GameRegistry.registerTileEntity(TileEntityAdvancedArmorForge.class, "AdvancedArmorForge");
    }

    private WeakReference<EntityPlayer> createNewPlayer(WorldServer world) {
        EntityPlayer player = FakePlayerFactory.get(world, ArmorPlus.gameProfile);

        return new WeakReference<EntityPlayer>(player);
    }

    private WeakReference<EntityPlayer> createNewPlayer(WorldServer world, double x, double y, double z) {
        EntityPlayer player = FakePlayerFactory.get(world, ArmorPlus.gameProfile);

        player.posX = x;
        player.posY = y;
        player.posZ = z;

        return new WeakReference<EntityPlayer>(player);
    }

    public final WeakReference<EntityPlayer> getDummyPlayer(WorldServer world) {
        if (dummyPlayer.get() == null) {
            dummyPlayer = createNewPlayer(world);
        } else {
            dummyPlayer.get().worldObj = world;
        }

        return dummyPlayer;
    }

    public final WeakReference<EntityPlayer> getDummyPlayer(WorldServer world, double x, double y, double z) {
        if (dummyPlayer.get() == null) {
            dummyPlayer = createNewPlayer(world, x, y, z);
        } else {
            dummyPlayer.get().worldObj = world;
            dummyPlayer.get().posX = x;
            dummyPlayer.get().posY = y;
            dummyPlayer.get().posZ = z;
        }

        return dummyPlayer;
    }

    public EntityPlayer getPlayer(MessageContext context) {
        return context.getServerHandler().playerEntity;
    }

    public void handlePacket(Runnable runnable, EntityPlayer player) {
        if (player instanceof EntityPlayerMP) {
            ((WorldServer) player.worldObj).addScheduledTask(runnable);
        }
    }
}
