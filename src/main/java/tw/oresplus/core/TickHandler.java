package tw.oresplus.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import tw.oresplus.OresPlus;
import tw.oresplus.core.helpers.BCHelper;
import tw.oresplus.core.helpers.Helpers;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.chunk.Chunk;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class TickHandler {
	public static HashMap<Integer, ArrayList<ChunkCoordIntPair>> oreRegenList = new HashMap();
	public static HashMap<Integer, ArrayList<ChunkCoordIntPair>> oilRegenList = new HashMap();
	public static HashMap<Integer, ArrayList<ChunkCoordIntPair>> rubberTreeRegenList = new HashMap();
	public static HashMap<Integer, ArrayList<ChunkCoordIntPair>> beehiveRegenList = new HashMap();
	
	@SubscribeEvent
	public boolean onWorldTick(TickEvent.WorldTickEvent event) {
		int dim = event.world.provider.dimensionId;
		
		ArrayList chunks = oreRegenList.get(Integer.valueOf(dim));
		if (chunks != null && !chunks.isEmpty()) {
			ChunkCoordIntPair coords = (ChunkCoordIntPair) chunks.get(0);
			Chunk chunk = event.world.getChunkFromChunkCoords(coords.chunkXPos, coords.chunkZPos);
			OresPlus.worldGen.doWorldGen(event.world.rand, event.world, chunk.xPosition, chunk.zPosition, false);
			chunks.remove(0);
			oreRegenList.put(Integer.valueOf(dim), chunks);
		}
		
		if (Helpers.BuildCraft.isLoaded()) {
			chunks = oilRegenList.get(Integer.valueOf(dim));
			if (chunks != null && !chunks.isEmpty()) {
				ChunkCoordIntPair coords = (ChunkCoordIntPair) chunks.get(0);
				Chunk chunk = event.world.getChunkFromChunkCoords(coords.chunkXPos, coords.chunkZPos);
				Helpers.BuildCraft.generate(event.world, event.world.rand, chunk.xPosition, chunk.zPosition);
				chunks.remove(0);
				oilRegenList.put(Integer.valueOf(dim), chunks);
			}
		}
		
		if (Helpers.IC2.isLoaded()) {
			chunks = rubberTreeRegenList.get(Integer.valueOf(dim));
			if (chunks != null && !chunks.isEmpty()) {
				ChunkCoordIntPair coords = (ChunkCoordIntPair) chunks.get(0);
				Chunk chunk = event.world.getChunkFromChunkCoords(coords.chunkXPos, coords.chunkZPos);
				Helpers.IC2.generate(event.world, event.world.rand, chunk.xPosition, chunk.zPosition);
				chunks.remove(0);
				rubberTreeRegenList.put(Integer.valueOf(dim), chunks);
			}
		}
		
		if (Helpers.Forestry.isLoaded()) {
			chunks = beehiveRegenList.get(Integer.valueOf(dim));
			if (chunks != null && !chunks.isEmpty()) {
				ChunkCoordIntPair coords = (ChunkCoordIntPair) chunks.get(0);
				Chunk chunk = event.world.getChunkFromChunkCoords(coords.chunkXPos, coords.chunkZPos);
				Helpers.Forestry.generate(event.world, event.world.rand, chunk.xPosition, chunk.zPosition);
				chunks.remove(0);
				beehiveRegenList.put(Integer.valueOf(dim), chunks);
			}
		}
		
		return true;
	}

}