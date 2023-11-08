/*
 * This file is part of Voile, a library mod for Minecraft.
 * Copyright (C) 2023  Maxmani
 *
 * Voile is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Voile is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Voile.  If not, see <https://www.gnu.org/licenses/>.
 */

package net.reimaden.voile.power;

import io.github.apace100.apoli.data.ApoliDataTypes;
import io.github.apace100.apoli.power.Power;
import io.github.apace100.apoli.power.factory.PowerFactory;
import io.github.apace100.apoli.registry.ApoliRegistries;
import io.github.apace100.calio.data.SerializableData;
import io.github.apace100.calio.data.SerializableDataType;
import net.minecraft.registry.Registry;
import net.reimaden.voile.Voile;

@SuppressWarnings("unused")
public class VoilePowers {

    public static final PowerFactory<Power> ZOMBIE_ARMS = registerPower(new PowerFactory<>(Voile.id("zombie_arms"), new SerializableData(),
            data -> ZombieArmsPower::new)).allowCondition();
    public static final PowerFactory<Power> MODIFY_BEHAVIOR = registerPower(new PowerFactory<>(Voile.id("modify_behavior"), new SerializableData()
            .add("behavior", SerializableDataType.enumValue(ModifyBehaviorPower.EntityBehavior.class))
            .add("entity_condition", ApoliDataTypes.ENTITY_CONDITION, null)
            .add("bientity_condition", ApoliDataTypes.BIENTITY_CONDITION, null),
            data -> (type, player) -> new ModifyBehaviorPower(type, player, data.get("behavior"), data.get("entity_condition"), data.get("bientity_condition")))
            .allowCondition());

    private static PowerFactory<Power> registerPower(PowerFactory<Power> factory) {
        return Registry.register(ApoliRegistries.POWER_FACTORY, factory.getSerializerId(), factory);
    }

    public static void register() {
        Voile.LOGGER.debug("Registering powers for " + Voile.MOD_ID);
    }
}
