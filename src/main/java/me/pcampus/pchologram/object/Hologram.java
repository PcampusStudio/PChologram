package me.pcampus.pchologram.object;


import me.pcampus.pchologram.PChologram;
import me.pcampus.pchologram.utilties.ChatUtil;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

import java.util.List;

public class Hologram {

    private ArmorStand phsyicalEntity;
    private List<String> content;
    private List<String> commands;

    public Hologram(Location location, List<String> content) {
        // new hologram
        this.phsyicalEntity = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        this.phsyicalEntity.setVisible(false);
        this.phsyicalEntity.setGravity(false);
        this.phsyicalEntity.setCustomNameVisible(true);
        this.phsyicalEntity.setCustomName(ChatUtil.format(ChatUtil.convertIntoStringWithNewLines(content)));
        //this.phsyicalEntity.setInvulnerable(true);


    }
    public Hologram(String name) {
        //

    }

    public boolean isTouchScreen() {
        if (commands == null ) {
            return false;
        }
        return commands.size() > 0;
    }

    public List<String> getContent() {
        return content;
    }

    public void executeCommands(PChologram user) {
        // TODO
    }

    public String contentAsString() {
        return ChatUtil.format(ChatUtil.convertIntoStringWithNewLines(getContent()));
    }

    public void serialize() {
        //

    }

}
