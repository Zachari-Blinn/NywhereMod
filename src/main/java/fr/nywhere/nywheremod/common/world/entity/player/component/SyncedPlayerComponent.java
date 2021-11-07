package fr.nywhere.nywheremod.common.world.entity.player.component;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import fr.nywhere.nywheremod.common.world.entity.player.Account;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;

import java.util.ArrayList;
import java.util.List;

public class SyncedPlayerComponent implements IPlayerComponent, AutoSyncedComponent {
    private final PlayerEntity playerEntity;

    private final List<Account> accounts = new ArrayList<>();

    public SyncedPlayerComponent(PlayerEntity playerEntity) {
        this.playerEntity = playerEntity;
    }

    @Override
    public List<Account> getAccounts() {
        return accounts;
    }

    @Override
    public void addAccounts(Account account) {
        this.accounts.add(account);
    }

    @Override
    public Account findAccount(String label) {
        return accounts.stream().filter(account -> label.equals(account.getLabel())).findAny().orElse(null);
    }

    @Override
    public PlayerEntity getPlayer() {
        return this.playerEntity;
    }

    @Override
    public void readFromNbt(NbtCompound tag) {

        /*this.firstConnexion = tag.getBoolean("firstConnexion");
        this.emeralds = tag.getInt("emeralds");
        this.mana = tag.getInt("mana");
        this.maxMana = tag.getInt("maxMana");
        this.stamina = tag.getInt("stamina");
        this.maxStamina = tag.getInt("maxStamina");
        this.rage = tag.getInt("rage");
        this.maxRage = tag.getInt("maxRage");
        this.experience = tag.getInt("experience");
        this.level = tag.getInt("level");
        this.race = Race.get(tag.getInt("raceId"));
        this.classe = Classe.get(tag.getInt("classeId"));
        tag.getList("unlockedSpells", 10).forEach(spellNbt -> {
            this.getUnlockedSpell().add(ModSpells.getSpell((NbtCompound) spellNbt));
        });
        tag.getList("spellSlots", 10).forEach(spellSlotNbt -> {
            this.getSpellSlot().add(new SpellSlot(
                    ((NbtCompound) spellSlotNbt).getInt("slotId"),
                    ((NbtCompound) spellSlotNbt).getUuid("spellId"),
                    ((NbtCompound) spellSlotNbt).getString("spellIcon"))
            );
        });*/
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        /*tag.putBoolean("firstConnexion", this.firstConnexion);
        tag.putInt("emeralds", this.emeralds);
        tag.putInt("mana", this.mana);
        tag.putInt("maxMana", this.maxMana);
        tag.putInt("stamina", this.stamina);
        tag.putInt("maxStamina", this.maxStamina);
        tag.putInt("rage", this.rage);
        tag.putInt("maxRage", this.maxRage);
        tag.putInt("experience", this.experience);
        tag.putInt("level", this.level);
        tag.putInt("raceId", this.race.getId());
        tag.putInt("classeId", this.classe.getId());
        NbtList unlockedSpells = new NbtList();
        this.getUnlockedSpell().forEach(spell -> {
            unlockedSpells.add(spell.writeSpellToNbt());
        });
        tag.put("unlockedSpells", unlockedSpells);

        NbtList spellSlotList = new NbtList();
        this.getSpellSlot().forEach(spellSlot -> {
            NbtCompound spellSlotNbt = new NbtCompound();
            spellSlotNbt.putUuid("spellId", spellSlot.getSpellId());
            spellSlotNbt.putInt("slotId", spellSlot.getSlotId());
            spellSlotNbt.putString("spellIcon", spellSlot.getIcon().toString());
            spellSlotList.add(spellSlotNbt);
        });
        tag.put("spellSlots", spellSlotList);*/

        NbtList accountNbtList = new NbtList();

        this.getAccounts().forEach((account -> {
            NbtCompound accountNbt = new NbtCompound();
            accountNbt.putString("label", account.getLabel());
            accountNbt.putString("code", account.getCode());
            accountNbt.putDouble("amount", account.getAmount());
            accountNbt.putLong("created_at", account.getCreatedAt());
            accountNbtList.add(accountNbt);
        }));

        tag.put("accounts", accountNbtList);
    }
}