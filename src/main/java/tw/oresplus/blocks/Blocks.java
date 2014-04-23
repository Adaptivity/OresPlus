package tw.oresplus.blocks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import cpw.mods.fml.common.event.FMLMissingMappingsEvent;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent.Action;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent.MissingMapping;
import cpw.mods.fml.common.event.FMLModIdMappingEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import tw.oresplus.OresPlus;
import tw.oresplus.api.Ores;
import tw.oresplus.core.Config;
import tw.oresplus.core.OreClass;
import tw.oresplus.core.OreLog;
import tw.oresplus.ores.DustOres;
import tw.oresplus.ores.GemstoneOres;
import tw.oresplus.ores.MetallicOres;
import tw.oresplus.ores.OreDrops;
import tw.oresplus.ores.GeneralOres;
import tw.oresplus.recipes.RecipeManager;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.oredict.OreDictionary;

public class Blocks {
	private static boolean isInitialized = false;
	
	public static ItemStack grinder;
	public static ItemStack grinder_lit;
	public static ItemStack cracker;
	public static ItemStack cracker_lit;
	
	public static void init() {
		if (isInitialized) {
			OresPlus.log.info("Block initialization failed, already initialized");
			return;
		}
		OresPlus.log.info("Initializing Blocks");

		for (MetallicOres ore : MetallicOres.values()) {
			ore.registerBlocks();
		}
		
		for (GemstoneOres ore : GemstoneOres.values()) {
			ore.registerBlocks();
		}
		
		for (DustOres ore : DustOres.values()) {
			ore.registerBlocks();
		}
		
		for (GeneralOres ore : GeneralOres.values()) {
			ore.RegisterBlocks();
		}
		
		// register machine blocks
		grinder = new ItemStack(new BlockGrinder(false), 1);
		grinder_lit = new ItemStack(new BlockGrinder(true), 1);
		RecipeManager.hideItem(grinder_lit);
		
		cracker = new ItemStack(new BlockCracker(false), 1);
		cracker_lit = new ItemStack(new BlockCracker(true), 1);
		RecipeManager.hideItem(cracker_lit);
		
		// register vanilla ores for custom ore generators
		Ores.manager.registerOre("oreIron", net.minecraft.init.Blocks.iron_ore);
		Ores.manager.registerOre("oreGold", net.minecraft.init.Blocks.gold_ore);
		Ores.manager.registerOre("oreDiamond", net.minecraft.init.Blocks.diamond_ore);
		Ores.manager.registerOre("oreEmerald", net.minecraft.init.Blocks.emerald_ore);
		Ores.manager.registerOre("oreLapis",  net.minecraft.init.Blocks.lapis_ore);
		Ores.manager.registerOre("oreQuartz", net.minecraft.init.Blocks.quartz_ore);
		Ores.manager.registerOre("oreCoal", net.minecraft.init.Blocks.coal_ore);
		
		isInitialized=true;
	}
	
	public static Block getBlock(String blockName) {
		try {
			return Ores.getBlock(blockName);
		} catch (Throwable e){
			return null;
		}
	}

	public static void handleRemaps(FMLModIdMappingEvent event) {
		OresPlus.log.info("recieved remap event");
		
	}

	public static void handleMissingMaps(FMLMissingMappingsEvent event) {
		OresPlus.log.info("recieved missing maps event");
		for (MissingMapping map : event.get()) {
			OresPlus.log.info("Missing Mapping for " + map.name);
			if (map.name.startsWith("OresPlus:netherOre"))
				map.setAction(Action.IGNORE);
		}
	}
	
}