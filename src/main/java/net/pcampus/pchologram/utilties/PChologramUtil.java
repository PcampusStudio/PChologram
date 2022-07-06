package net.pcampus.pchologram.utilties;

import org.bukkit.entity.ArmorStand;

public class PChologramUtil {
	public static void setHoloArmorStand(ArmorStand armorStand) {
		armorStand.setMarker(true);
		armorStand.setVisible(false);
		armorStand.setSmall(true);
		armorStand.setSilent(true);
		armorStand.setCollidable(false);
		armorStand.setInvulnerable(true);
		armorStand.setGravity(false);
		armorStand.setBasePlate(false);
		armorStand.setCustomNameVisible(true);
	}
}
