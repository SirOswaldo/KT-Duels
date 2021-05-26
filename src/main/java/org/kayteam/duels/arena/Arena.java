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

import org.kayteam.duels.arena.enums.ArenaType;

public class Arena {

    private final String name;
    private ArenaType arenaType;
    private boolean enabled;

    public Arena(String name, ArenaType arenaType, boolean enabled) {
        this.name = name;
        this.arenaType = arenaType;
        this.enabled = enabled;
    }
    public Arena(String name) {
        this.name = name;
        arenaType = ArenaType.DUEL;
        enabled = false;
    }

    /**
     * Get the arena name.
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Get the arena type.
     * @return ArenaType
     */
    public ArenaType getArenaType() {
        return arenaType;
    }

    /**
     * Set the arena type
     * @param arenaType The new ArenaType
     */
    public void setArenaType(ArenaType arenaType) {
        this.arenaType = arenaType;
    }

    /**
     * Verify if the arena are enabled.
     * @return Boolean
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Set the arena to the enable or disable
     * @param enabled The new value
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

}
