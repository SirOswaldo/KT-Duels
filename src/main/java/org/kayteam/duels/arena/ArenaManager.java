/*
 *
 *  * Copyright (C) 2021 SirOswaldo
 *  *
 *  *     This program is free software: you can redistribute it and/or modify
 *  *     it under the terms of the GNU General Public License as published by
 *  *     the Free Software Foundation, either version 3 of the License, or
 *  *     (at your option) any later version.
 *  *
 *  *     This program is distributed in the hope that it will be useful,
 *  *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  *     GNU General Public License for more details.
 *  *
 *  *     You should have received a copy of the GNU General Public License
 *  *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.kayteam.duels.arena;

import org.kayteam.duels.DuelsPlugin;
import org.kayteam.duels.arena.enums.ArenaType;
import org.kayteam.duels.util.yaml.Yaml;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ArenaManager {

    private final DuelsPlugin duelsPlugin;
    private final HashMap<String, Arena> arenas;

    public ArenaManager(DuelsPlugin duelsPlugin) {
        this.duelsPlugin = duelsPlugin;
        arenas = new HashMap<>();
    }

    /**
     * Load all arenas from "arenas" folder.
     */
    public void loadAllArenas() {
        File directory = new File(duelsPlugin.getDataFolder() + File.separator + "arenas");
        if (directory.isDirectory()) {
            File[] files = directory.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    return pathname.getName().endsWith(".yml");
                }
            });
            assert files != null;
            for (File file:files) {
                loadArena(file.getName().split("\\.")[0]);
            }
        }
    }

    /**
     * Load a specific arena from "arenas" folder.
     * @param name The name for the arena
     */
    public void loadArena(String name) {
        Yaml yaml = new Yaml(duelsPlugin, "arenas", name);
        yaml.registerFileConfiguration();
        boolean enable = false;
        ArenaType arenaType = ArenaType.DUEL;
        if (yaml.getFileConfiguration().contains("enabled")) {
            if (yaml.getFileConfiguration().isBoolean("enable")) {
                enable = yaml.getFileConfiguration().getBoolean("enable");
            }
        }
        if (yaml.getFileConfiguration().contains("arenaType")) {
            if (yaml.getFileConfiguration().isString("arenaType")) {
                arenaType = ArenaType.valueOf(yaml.getFileConfiguration().getString("arenaType"));
            }
        }
        Arena arena = new Arena(name, arenaType, enable);
        arenas.put(name, arena);
    }

    /**
     * Unload all arenas to "arenas" folder.
     */
    public void unloadAllArenas() {
        for (String name:arenas.keySet()) {
            unloadArena(name);
        }
    }

    /**
     * Unload a specific arena to "arenas" folder.
     * @param name The name for the arena
     */
    public void unloadArena(String name) {
        Yaml yaml = new Yaml(duelsPlugin, "arenas", name);
        yaml.reloadFileConfiguration();
        Arena arena = arenas.get(name);
        yaml.getFileConfiguration().set("enable", arena.isEnabled());
        yaml.getFileConfiguration().set("arenaType", arena.getArenaType());
        yaml.saveFileConfiguration();
        arenas.remove(name);
    }

    /**
     * Get a specific loaded arena
     * @param name The name for the arena
     * @return Arena if exist or null if no exist.
     */
    public Arena getArena(String name) {
        return arenas.get(name);
    }

    /**
     * Get all names of the arenas.
     * @return All names in a list
     */
    public List<String> getArenaNames() {
        return new ArrayList<>(arenas.keySet());
    }

}
