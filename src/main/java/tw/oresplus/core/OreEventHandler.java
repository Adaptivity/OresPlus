package tw.oresplus.core;

import java.util.ArrayList;
import java.util.List;

import tw.oresplus.OresPlus;
import tw.oresplus.blocks.Blocks;
import tw.oresplus.worldgen.OreGenType;
import tw.oresplus.worldgen.OreGenerators;
import tw.oresplus.worldgen.OreGeneratorsEnd;
import tw.oresplus.worldgen.OreGeneratorsNether;
import tw.oresplus.worldgen.WorldGenCore;
import tw.oresplus.worldgen.WorldGenOre;
import cpw.mods.fml.common.event.FMLMissingMappingsEvent;
import cpw.mods.fml.common.event.FMLInterModComms.IMCMessage;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable;
import net.minecraftforge.event.world.ChunkDataEvent;
import net.minecraftforge.event.world.WorldEvent;

public class OreEventHandler {
	@SubscribeEvent
	public void chunkLoad(ChunkDataEvent.Load event) {
		NBTTagCompound oresPlusRegen = event.getData().getCompoundTag("OresPlus");
		
		if (oresPlusRegen.hasNoTags()) { // regen info not found, checking for old regen key
			oresPlusRegen.setString("ores", event.getData().getString("OresPlus:regenKey"));
		}
		
	    NBTTagCompound oreRegenArray = oresPlusRegen.getCompoundTag("oreRegenArray");
	    int dimId = event.world.provider.dimensionId;
	    ArrayList<WorldGenOre> oreGenerators = WorldGenCore.oreGenerators.get(dimId);
	    for (WorldGenOre oreGen : oreGenerators) {
	    	String oreRegenKey = oreRegenArray.getString(oreGen.getOreName());
	    	if (oreRegenKey.equals("")) {
	    		oreRegenKey = oresPlusRegen.getString("ores");
	    	}
	    	if (oreGen.doRegen && (!oreGen.regenKey.equals(oreRegenKey))) {
	    		ArrayList<ChunkCoordIntPair> chunks = oreGen.regenList.get(Integer.valueOf(dimId));
	    		if (chunks == null)
	    			chunks = new ArrayList();
	    		chunks.add(event.getChunk().getChunkCoordIntPair());
	    		oreGen.regenList.put(dimId, chunks);
	    	}
	    }
	    /*
	    for (OreGenerators oreGen : OreGenerators.values()) {
	      String oreRegenKey = oreRegenArray.getString(oreGen.toString());
	      if (oreRegenKey.equals("")) {
	        oreRegenKey = oresPlusRegen.getString("ores");
	      }
	      if ((oreGen.generator.doRegen) && (!oreGen.generator.regenKey.equals(oreRegenKey))) {
	        int dim = event.world.provider.dimensionId;
	        if (oreGen.generator.dimension == dim) {
	          ArrayList<ChunkCoordIntPair> chunks = (ArrayList)oreGen.generator.regenList.get(Integer.valueOf(dim));
	          if (chunks == null)
	            chunks = new ArrayList();
	          chunks.add(event.getChunk().getChunkCoordIntPair());
	          oreGen.generator.regenList.put(Integer.valueOf(dim), chunks);
	        }
	      }
	    }
	    for (OreGeneratorsNether oreGen : OreGeneratorsNether.values()) {
		      String oreRegenKey = oreRegenArray.getString(oreGen.toString());
		      if (oreRegenKey.equals("")) {
		        oreRegenKey = oresPlusRegen.getString("ores");
		      }
		      if ((oreGen.generator.doRegen) && (!oreGen.generator.regenKey.equals(oreRegenKey))) {
		        int dim = event.world.provider.dimensionId;
		        if (oreGen.generator.dimension == dim) {
		          ArrayList<ChunkCoordIntPair> chunks = (ArrayList)oreGen.generator.regenList.get(Integer.valueOf(dim));
		          if (chunks == null)
		            chunks = new ArrayList();
		          chunks.add(event.getChunk().getChunkCoordIntPair());
		          oreGen.generator.regenList.put(Integer.valueOf(dim), chunks);
		        }
		      }
		    }
	    for (OreGeneratorsEnd oreGen : OreGeneratorsEnd.values()) {
		      String oreRegenKey = oreRegenArray.getString(oreGen.toString());
		      if (oreRegenKey.equals("")) {
		        oreRegenKey = oresPlusRegen.getString("ores");
		      }
		      if ((oreGen.generator.doRegen) && (!oreGen.generator.regenKey.equals(oreRegenKey))) {
		        int dim = event.world.provider.dimensionId;
		        if (oreGen.generator.dimension == dim) {
		          ArrayList<ChunkCoordIntPair> chunks = (ArrayList)oreGen.generator.regenList.get(Integer.valueOf(dim));
		          if (chunks == null)
		            chunks = new ArrayList();
		          chunks.add(event.getChunk().getChunkCoordIntPair());
		          oreGen.generator.regenList.put(Integer.valueOf(dim), chunks);
		        }
		      }
		    }
		    */
		
		if (!OresPlus.regenKeyOil.equals("DISABLED") && !oresPlusRegen.getString("oil").equals(OresPlus.regenKeyOil)) {
			int dim = event.world.provider.dimensionId;
			ArrayList chunks = TickHandler.oilRegenList.get(Integer.valueOf(dim));
			if (chunks == null)
				chunks = new ArrayList();
			chunks.add(event.getChunk().getChunkCoordIntPair());
			TickHandler.oilRegenList.put(Integer.valueOf(dim), chunks);
		}
		
		if (!OresPlus.regenKeyRubberTree.equals("DISABLED") && !oresPlusRegen.getString("rubberTree").equals(OresPlus.regenKeyRubberTree)) {
			int dim = event.world.provider.dimensionId;
			ArrayList chunks = TickHandler.rubberTreeRegenList.get(Integer.valueOf(dim));
			if (chunks == null)
				chunks = new ArrayList();
			chunks.add(event.getChunk().getChunkCoordIntPair());
			TickHandler.rubberTreeRegenList.put(Integer.valueOf(dim), chunks);
		}
		
		if (!OresPlus.regenKeyBeehives.equals("DISABLED") && !oresPlusRegen.getString("beehives").equals(OresPlus.regenKeyBeehives)) {
			int dim = event.world.provider.dimensionId;
			ArrayList chunks = TickHandler.beehiveRegenList.get(Integer.valueOf(dim));
			if (chunks == null)
				chunks = new ArrayList();
			chunks.add(event.getChunk().getChunkCoordIntPair());
			TickHandler.beehiveRegenList.put(Integer.valueOf(dim), chunks);
		}
	}

	@SubscribeEvent
	public void chunkSave(ChunkDataEvent.Save event) {
		NBTTagCompound oresPlusRegen = new NBTTagCompound();
		
	    NBTTagCompound oreRegenArray = new NBTTagCompound();
	    for (OreGenerators oreGen : OreGenerators.values()) {
	      oreRegenArray.setString(oreGen.toString(), oreGen.generator.regenKey);
	    }
	    oresPlusRegen.setTag("oreRegenArray", oreRegenArray);
		oresPlusRegen.setString("ores", OresPlus.regenKeyOre);
		oresPlusRegen.setString("oil", OresPlus.regenKeyOil);
		oresPlusRegen.setString("rubberTree", OresPlus.regenKeyRubberTree);
		oresPlusRegen.setString("beehives", OresPlus.regenKeyBeehives);
		event.getData().setTag("OresPlus", oresPlusRegen);
	}
	
	@SubscribeEvent
	public void worldLoad(WorldEvent.Load event) {
		int dimId = event.world.provider.dimensionId;
		if (WorldGenCore.oreGenerators.get(dimId) != null)
			return;
		ArrayList<WorldGenOre> oreGenList = new ArrayList();
		switch (dimId) {
		case -1:
			for (OreGeneratorsNether oreGen : OreGeneratorsNether.values()) {
				WorldGenOre generator = oreGen.getGenerator();
				if (generator != null) {
					oreGenList.add(generator);
					OresPlus.log.debug("Added " + generator.getOreName() + " to dim " + dimId + " generator list");
				}
			}
			break;
		case 1:
			for (OreGeneratorsEnd oreGen : OreGeneratorsEnd.values()) {
				WorldGenOre generator = oreGen.getGenerator();
				if (generator != null) {
					oreGenList.add(generator);
					OresPlus.log.debug("Added " + generator.getOreName() + " to dim " + dimId + " generator list");
				}
			}
			break;
		default:
			for (OreGenerators oreGen : OreGenerators.values()) {
				WorldGenOre generator = oreGen.getGenerator(dimId);
				if (generator != null) {
					oreGenList.add(generator);
					OresPlus.log.debug("Added " + generator.getOreName() + " to dim " + dimId + " generator list");
				}
			}
		}
		
		if (!oreGenList.isEmpty()) {
			WorldGenCore.oreGenerators.put(dimId, oreGenList);
		}
		OresPlus.log.info("Loaded world gen for dimension id " + dimId);
	}
	
	/*
	@SubscribeEvent
	public void worldUnload(WorldEvent.Unload event) {
		WorldGenCore.oreGenerators.remove(event.world.provider.dimensionId);
		OresPlus.log.info("Unloaded world, dimension id " + event.world.provider.dimensionId);
	}
	*/
	
	@SubscribeEvent
	public void genOre(GenerateMinable event) {
		switch (event.type) {
		//case EMERALD:
		case QUARTZ:
		case IRON:
		case GOLD:
		case LAPIS:
		case REDSTONE:
		case DIAMOND:
		case COAL:
			event.setResult(Result.DENY);
			break;
		default:
			event.setResult(Result.ALLOW);
		}
	}
}












