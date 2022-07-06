package net.pcampus.pchologram.object;

import lombok.Getter;
import lombok.Setter;
import net.pcampus.pchologram.PChologram;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

public class HologramLocation {

	@Getter @Setter	private World world;
	@Getter @Setter private double x;
	@Getter @Setter private double y;
	@Getter @Setter private double z;

	public HologramLocation(World world, double x, double y, double z) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public HologramLocation(Location location) {
		this.world = location.getWorld();
		this.x = location.getX();
		this.y = location.getY();
		this.z = location.getZ();
	}

	public HologramLocation(String configString) {
		try {
			List<String> configStringList = Arrays.asList(configString.split(","));
			this.world	= Bukkit.getWorld(configStringList.get(0));
			this.x		= Double.parseDouble(configStringList.get(1));
			this.y		= Double.parseDouble(configStringList.get(2));
			this.z		= Double.parseDouble(configStringList.get(3));
		} catch (Exception e) {
			PChologram.logger().log(Level.WARNING, ChatColor.YELLOW + "Something wrong with location!");
			e.printStackTrace();
			PChologram.logger().log(Level.WARNING, ChatColor.YELLOW + "------------------------------");
		}
	}

	public Location toLocation() {
		return new Location(this.world, this.x, this.y, this.z);
	}

	public String toConfigString() {
		return 	this.world.getName() + "," +
				this.x + "," +
				this.y + "," +
				this.z;
	}
}
