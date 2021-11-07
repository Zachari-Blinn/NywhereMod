package fr.nywhere.nywheremod.common.world.entity.player.component;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import fr.nywhere.nywheremod.NywhereMod;

public final class PlayerComponent implements EntityComponentInitializer {
    public static final ComponentKey<IPlayerComponent> PLAYER =
            ComponentRegistryV3.INSTANCE.getOrCreate(NywhereMod.id("player"), IPlayerComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        // Add the component to every PlayerEntity instance, and copy it on respawn with keepInventory
        registry.registerForPlayers(PLAYER, SyncedPlayerComponent::new, RespawnCopyStrategy.INVENTORY);
    }

}