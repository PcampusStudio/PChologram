package net.pcampus.pchologram.object;


import net.pcampus.pchologram.PChologram;
import net.pcampus.pchologram.utilties.ChatUtil;
import net.pcampus.pchologram.utilties.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

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
        if (FileManager.gc().getDataConfig().get("holograms") == null) return;

        for (String name : FileManager.gc().getDataConfig().getConfigurationSection("holograms").getKeys(false)) {

            List<Entity> entityList = new ArrayList<>();
            for (int i = 0; i < getContents(name).size(); i++) {
                String content = getContents(name).get(i);
                if (checkContentType(content).equals("text")) {
                    ArmorStand armorStand = createTextArmorStand(getLocation(name), i, contentToText(content));
                    entityList.add(armorStand);
                    continue;
                }
                if (checkContentType(content).equals("item")) {
                    Item item = createItem(getLocation(name), i, contentToItem(content));
                    entityList.add(item);
                    continue;
                }
            }
            PChologram.hologramMap.put(name, entityList);

            PChologram.logger().info(ChatUtil.format("&aLoaded hologram \"" + name + "\" [" + getContents(name).size() + " line(s)]"));
        }
    }

    public static void unLoadHologram() {
        if (FileManager.gc().getDataConfig().get("holograms") == null) return;

        for (String name : FileManager.gc().getDataConfig().getConfigurationSection("holograms").getKeys(false)) {

            for (Entity entity : PChologram.hologramMap.get(name)) {
                entity.remove();
            }
            PChologram.hologramMap.remove(name);

            PChologram.logger().info(ChatUtil.format("&aUnloaded hologram \"" + name + "\" [" + getContents(name).size() + " line(s)]"));
        }
    }

    public boolean isCreated() {
        return FileManager.gc().getDataConfig().get("holograms." + name) != null;
    }

    public boolean isNoMoreLine() {
        return this.contents.size() == 1;
    }

    public boolean isIndexOut(int index) {
        return index >= this.contents.size() || index < 0;
    }

    public void create() {
        if (isCreated()) return;
        // Check contents

        // If contents is text

        createTextHologram(0, this.contents.get(0));

        saveLocation(this.name, this.holoLocation);
    }

    public void delete() {
        for (Entity entity : PChologram.hologramMap.get(this.name)) {
            entity.remove();
        }
        deleteConfig(this.name);
    }

    public void addContent(String content) {
        // Check if content is TextHologram
        this.contents.add(content);

        Bukkit.getScheduler().runTaskLater(PChologram.getInstance(), () -> {
            createTextHologram(this.contents.size() - 1, content);
        }, 4 - (PChologram.hologramMap.get(this.name).get(0).getTicksLived() % 3));
    }

    public void addContent(int index, String content) {
        if (isIndexOut(index)) return;
        this.contents.add(index, content);

        // Check if content is TextHologram

        Bukkit.getScheduler().runTaskLater(PChologram.getInstance(), () -> {
            for (int i = index; i < PChologram.hologramMap.get(this.name).size(); i++) {
                PChologram.hologramMap.get(this.name).get(i).teleport(PChologram.hologramMap.get(this.name).get(i).getLocation().add(0, -0.25, 0));
            }
            createTextHologram(index, this.contents.get(index));
        }, 4 - (PChologram.hologramMap.get(this.name).get(0).getTicksLived() % 3));
    }

    public void removeContent(int index) {
        if (isIndexOut(index)) return;
        this.contents.remove(index);

        PChologram.hologramMap.get(this.name).get(index).remove();
        PChologram.hologramMap.get(this.name).remove(index);

        for (int i = index; i < PChologram.hologramMap.get(this.name).size(); i++) {
            PChologram.hologramMap.get(this.name).get(i).teleport(PChologram.hologramMap.get(this.name).get(i).getLocation().add(0, 0.25, 0));
        }

        saveContents(this.name, this.contents);
    }

    public void move(HologramLocation newHoloLocation) {
        for (int i = 0; i < PChologram.hologramMap.get(this.name).size(); i++) {
            PChologram.hologramMap.get(this.name).get(i).teleport(newHoloLocation.toLocation().add(0, -0.25*i, 0));
        }

        saveLocation(this.name, newHoloLocation);
    }


    // Private Methods

    private void createTextHologram(int line, String text) {
        ArmorStand armorStand = createTextArmorStand(this.holoLocation, line, text);

        List<Entity> entityList = new ArrayList<>();
        if (!(PChologram.hologramMap.get(this.name) == null)) entityList = PChologram.hologramMap.get(this.name);
        entityList.add(line, armorStand);

        PChologram.hologramMap.put(this.name, entityList);
        saveContents(this.name, this.contents);
    }

    private static ArmorStand createTextArmorStand(HologramLocation holoLocation, int line, String text) {
        ArmorStand armorStand = (ArmorStand) holoLocation.getWorld().spawnEntity(holoLocation.toLocation().add(0, -0.25*line, 0), EntityType.ARMOR_STAND);
        armorStand.setInvisible(true);
        armorStand.setSmall(true);
        armorStand.setArms(false);
        armorStand.setGravity(false);
        armorStand.setBasePlate(false);
        armorStand.setMarker(true);
        armorStand.setSilent(true);

        if (text.isEmpty()) {
            armorStand.setCustomNameVisible(false);
        } else {
            armorStand.setCustomNameVisible(true);
            armorStand.setCustomName(ChatUtil.format(text));
        }

        return armorStand;
    }

    private static Item createItem(HologramLocation holoLocation, int line, ItemStack itemStack) {
        Item item = (Item) holoLocation.getWorld().spawnEntity(holoLocation.toLocation().add(0, -0.25*line, 0), EntityType.DROPPED_ITEM);
        item.setItemStack(itemStack);
        item.setGravity(false);
        item.setVelocity(new Vector(0, 0, 0));
        item.setPickupDelay(32767);
        item.setInvulnerable(true);
        item.setPersistent(false);

        return item;
    }

    private static String checkContentType(String content) {
        if (content.regionMatches(0,"item:",0,5)) {
            return "item";
        }

        return "text";
    }

    private static String contentToText(String content) {
        if (content.regionMatches(0,"text:",0,5)) {
            StringBuilder contentBuilder = new StringBuilder(content);
            return contentBuilder.delete(0, 4).toString();
        }
        return content;
    }

    private static ItemStack contentToItem(String content) {
        if (content.startsWith("item:")) {
            String itemName = content.replaceFirst("item:", "");

            Material itemMaterial = Material.getMaterial(itemName.toUpperCase());

            PChologram.logger().info(itemName.toUpperCase());

            if (itemMaterial == null) return new ItemStack(Material.STONE);
            return new ItemStack(itemMaterial);
        }
        return new ItemStack(Material.STONE);
    }


    // Config File Methods

    private static void saveContents(String name, List<String> contents) {
        FileManager.gc().getDataConfig().set("holograms." + name + ".contents", contents);
        FileManager.gc().saveDataConfig();
    }

    private static List<String> getContents(String name) {
        return FileManager.gc().getDataConfig().getStringList("holograms." + name + ".contents");
    }

    private static void saveLocation(String name, HologramLocation holoLocation) {
        FileManager.gc().getDataConfig().set("holograms." + name + ".location", holoLocation.toConfigString());
        FileManager.gc().saveDataConfig();
    }

    private static HologramLocation getLocation(String name) {
        return new HologramLocation(FileManager.gc().getDataConfig().getString("holograms." + name + ".location"));
    }

    private static void deleteConfig(String name) {
        FileManager.gc().getDataConfig().set("holograms." + name, null);
        FileManager.gc().saveDataConfig();
    }

}
