package util;

import core.AntiFarmPlugin;
import org.bukkit.Material;

import java.util.function.Consumer;

public class TypeUtil {

    public static void parseMaterial(String material, Consumer<Material> consumer) {
        String toUpperCase = material.toUpperCase();
        Material result = Material.getMaterial(toUpperCase);
        if (result == null) result = Material.getMaterial(toUpperCase, true);
        if (result == null) {
            try {
                result = Material.valueOf(toUpperCase);
            } catch (Exception ignored) {}
        }

        if (result == null) {
            AntiFarmPlugin.getInstance().getLogger().warning(String.format("%s is not a valid existing material", material));
            return;
        }
        consumer.accept(result);
    }
}
