package net.pcampus.pchologram;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemDespawnEvent;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ListenerManager implements Listener {
	@EventHandler
	public void onDespawnItem(ItemDespawnEvent e) {
		for (List<Entity> entityList: PChologram.hologramMap.values()) {
			for (Entity entity : entityList) {
				if (entity.getType() != EntityType.DROPPED_ITEM) continue;
				Item item = (Item) entity;

				if (e.getEntity() == item) {
					e.setCancelled(true);
					PChologram.logger().info("Cancel " + e.getEntity().getName() + " despawn event");
				}
			}
		}
	}

//	// For map image rendering testing
//	@EventHandler
//	public void onMapInit(MapInitializeEvent e) {
//		MapView mapView = e.getMap();
//
//		mapView.setScale(MapView.Scale.FARTHEST);
//		mapView.setUnlimitedTracking(false);
//		mapView.setLocked(true);
//
//		mapView.removeRenderer(mapView.getRenderers().get(0));
//		mapView.addRenderer(new mapRenderer());
//	}
//
//	private static class mapRenderer extends MapRenderer {
//
//		@Override
//		public void render(MapView map, MapCanvas canvas, Player player) {
//			BufferedImage image;
//			try {
//				image = ImageIO.read(new File(PChologram.getInstance().getDataFolder().getAbsolutePath(), "test128x128.jpg"));
//				canvas.drawImage(0, 0, image);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
}
