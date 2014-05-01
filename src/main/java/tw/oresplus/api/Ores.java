package tw.oresplus.api;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class Ores {
	private static Ores instance = new Ores();
	
	private Ores () {}
	
	public static IOreManager manager;
	
	public static IOreRecipeManager grinderRecipes;
}
