package tw.oresplus.core.helpers;

import java.lang.reflect.Field;
import java.util.Random;

import tw.oresplus.OresPlus;
import mods.railcraft.api.crafting.IRockCrusherCraftingManager;
import mods.railcraft.api.crafting.IRockCrusherRecipe;
import mods.railcraft.api.crafting.RailcraftCraftingManager;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import cpw.mods.fml.common.Loader;

public class RCHelper extends OresHelper {
	private IRockCrusherCraftingManager rockCrusherManager;

	public RCHelper() {
		super("Railcraft");
	}

	@Override
	public void init() {
		if (!this.isLoaded()) {
			OresPlus.log.info("Railcraft not found, integration helper disabled");
			return;
		}
		
		OresPlus.log.info("RailCraft found, integration helper initialized");
	}

	@Override
	public void generate(World world, Random rand, int chunkX, int chunkZ) { }

	@Override
	public void registerRecipe(String recipeType, ItemStack input,
			NBTTagCompound metadata, ItemStack... outputs) {
		if (recipeType == "rockCrusher") {
			if (this.rockCrusherManager == null) {
				this.rockCrusherManager = RailcraftCraftingManager.rockCrusher;
			}
			IRockCrusherRecipe recipe = this.rockCrusherManager.createNewRecipe(input, true, true);
			recipe.addOutput(outputs[0], 1.0F);
		}
	}

}
