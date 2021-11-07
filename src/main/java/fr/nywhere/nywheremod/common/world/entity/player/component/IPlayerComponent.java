package fr.nywhere.nywheremod.common.world.entity.player.component;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import fr.nywhere.nywheremod.common.world.entity.player.Account;
import net.minecraft.entity.player.PlayerEntity;

import java.util.List;

public interface IPlayerComponent extends ComponentV3 {
    List<Account> getAccounts();

    void addAccounts(Account account);

    Account findAccount(String label);

    PlayerEntity getPlayer();
}
