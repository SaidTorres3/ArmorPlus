package net.thedragonteam.armorplus.items.materials;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.datafix.IFixableData;

import static net.thedragonteam.armorplus.events.RegistryEventHandler.DATA_FIXER_VERSION;

public class ItemRename implements IFixableData {
    private static final String OLD = "armorplus:material";

    private static final String[] REMAP_TO = new String[]{"chainmail", "guardian_scale", "wither_bone", "ender_dragon_scale", "the_ultimate_material"};

    @Override
    public int getFixVersion() {
        return DATA_FIXER_VERSION;
    }

    @Override
    public NBTTagCompound fixTagCompound(NBTTagCompound nbt) {
        if (OLD.equals(nbt.getString("id"))) {
            for (int i = 0; i < REMAP_TO.length; i++) {
                if (REMAP_TO[i] != null) {
                    rename(nbt, i, REMAP_TO[i]);
                }
            }
        }
        return nbt;
    }

    private void rename(NBTTagCompound compound, int dmg, String regName) {
        if (compound.getShort("Damage") == dmg) {
            compound.setString("id", "armorplus:" + regName);
            compound.setShort("Damage", (short) 0);
        }
    }
}