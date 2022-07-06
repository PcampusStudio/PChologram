package net.pcampus.pchologram.object;


import net.pcampus.pchologram.PChologram;
import net.pcampus.pchologram.utilties.ChatUtil;
import net.pcampus.pchologram.utilties.FileManager;
import net.pcampus.pchologram.utilties.PChologramUtil;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.List;

public class Hologram {

    private final HologramLocation holoLocation;
    private final String name;
    private final List<String> contents;

    public Hologram(HologramLocation location, String name, String content) {
        this.holoLocation = location;
        this.name = name;
        this.contents = new ArrayList<>(); this.contents.add(content);
    }

    public Hologram(String name) {
        this.holoLocation = new HologramLocation(FileManager.gc().getDataConfig().getString("holograms." + name + ".location"));
        this.name = name;
        this.contents = new ArrayList<>(FileManager.gc().getDataConfig().getStringList("holograms." + name + ".contents"));
    }

    public static void loadHologram() {
        FileManager.gc().getDataConfig().get("holograms");
        if (FileManager.gc().getDataConfig().get("holograms") == null) return;
        for (String name : FileManager.gc().getDataConfig().getConfigurationSection("holograms").getKeys(false)) {
            HologramLocation holoLocation = new HologramLocation(FileManager.gc().getDataConfig().getString("holograms." + name + ".location"));
            List<String> contents = new ArrayList<>(FileManager.gc().getDataConfig().getStringList("holograms." + name + ".contents"));

            Location location = holoLocation.toLocation();

            for (int i = 0; i < contents.size(); i++) {
                ArmorStand armorStand = (ArmorStand) holoLocation.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
                PChologramUtil.setHoloArmorStand(armorStand);
                armorStand.setCustomName(ChatUtil.format(contents.get(i)));

                location.add(0, -0.25, 0);

                FileManager.gc().getDataConfig().set("holograms." + name + ".id." + i, armorStand.getEntityId());
            }
            FileManager.gc().saveDataConfig();

            PChologram.logger().info(ChatUtil.format("&aLoaded hologram \"" + name + "\""));
        }
    }

    public static void unLoadHologram() {
        FileManager.gc().getDataConfig().get("holograms");
        if (FileManager.gc().getDataConfig().get("holograms") == null) return;
        for (String name : FileManager.gc().getDataConfig().getConfigurationSection("holograms").getKeys(false)) {
            HologramLocation holoLocation = new HologramLocation(FileManager.gc().getDataConfig().getString("holograms." + name + ".location"));

            for (Entity entity : holoLocation.getWorld().getEntities()) {
                if (!entity.getType().equals(EntityType.ARMOR_STAND)) continue;
                ArmorStand armorStand = (ArmorStand) entity;
                if (armorStand.isVisible()) continue;

                for (String line : FileManager.gc().getDataConfig().getConfigurationSection("holograms." + name + ".id").getKeys(false)) {
                    if (armorStand.getEntityId() != FileManager.gc().getDataConfig().getInt("holograms." + name + ".id." + line)) continue;
                    armorStand.remove();
                }
            }

            FileManager.gc().getDataConfig().set("holograms." + name + ".id", null);
            FileManager.gc().saveDataConfig();

            PChologram.logger().info(ChatUtil.format("&aUnloaded hologram \"" + name + "\""));
        }
    }

    public boolean isCreated() {
        if (FileManager.gc().getDataConfig().get("holograms." + name) != null) {
            return true;
        }
        return false;
    }

    public void create() {
        Location location = this.holoLocation.toLocation();

        ArmorStand armorStand = (ArmorStand) this.holoLocation.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        PChologramUtil.setHoloArmorStand(armorStand);
        armorStand.setCustomName(ChatUtil.format(this.contents.get(0)));

        FileManager.gc().getDataConfig().set("holograms." + this.name + ".id.0", armorStand.getEntityId());
        FileManager.gc().getDataConfig().set("holograms." + this.name + ".location", this.holoLocation.toConfigString());
        FileManager.gc().getDataConfig().set("holograms." + this.name + ".contents", this.contents);
        FileManager.gc().saveDataConfig();
    }

    public void delete() {
        for (Entity entity : this.holoLocation.getWorld().getEntities()) {
            if (!entity.getType().equals(EntityType.ARMOR_STAND)) continue;
            ArmorStand armorStand = (ArmorStand) entity;
            if (armorStand.isVisible()) continue;

            for (String line : FileManager.gc().getDataConfig().getConfigurationSection("holograms." + this.name + ".id").getKeys(false)) {
                if (armorStand.getEntityId() != FileManager.gc().getDataConfig().getInt("holograms." + this.name + ".id." + line)) continue;
                armorStand.remove();
            }
        }
        FileManager.gc().getDataConfig().set("holograms." + this.name, null);
        FileManager.gc().saveDataConfig();
    }

    public void addContent(String content) {
        this.contents.add(content);
        Location location = this.holoLocation.toLocation();
        location.add(0, -0.25*(contents.size() - 1), 0);

        ArmorStand armorStand = (ArmorStand) this.holoLocation.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        PChologramUtil.setHoloArmorStand(armorStand);
        armorStand.setCustomName(ChatUtil.format(this.contents.get(contents.size() - 1)));

        FileManager.gc().getDataConfig().set("holograms." + this.name + ".id." + (contents.size() - 1), armorStand.getEntityId());
        FileManager.gc().getDataConfig().set("holograms." + this.name + ".contents", this.contents);
        FileManager.gc().saveDataConfig();
    }

    public List<String> getContents() {
        return contents;
    }


    public String contentsAsString() {
        return ChatUtil.format(ChatUtil.convertIntoStringWithNewLines(getContents()));
    }

}
