package julianh06.wynnextras.features.render;


import julianh06.wynnextras.config.WynnExtrasConfig;
import julianh06.wynnextras.config.simpleconfig.SimpleConfig;
import net.minecraft.entity.player.PlayerEntity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PlayerRenderFilter {
    private static final WynnExtrasConfig config = SimpleConfig.getInstance(WynnExtrasConfig.class);


    private static final Set<UUID> hiddenPlayers = new HashSet<>();

    public static void hide(PlayerEntity player) {
        if(config.printDebugToConsole) {
            System.out.println(player.getName() + " is now hidden");
        }
        hiddenPlayers.add(player.getUuid());
    }

    public static void show(PlayerEntity player) {
        if(config.printDebugToConsole) {
            System.out.println(player.getName() + " is now shown");
        }
        hiddenPlayers.remove(player.getUuid());
    }

    public static boolean isHidden(PlayerEntity player) {
        return hiddenPlayers.contains(player.getUuid());
    }
}
