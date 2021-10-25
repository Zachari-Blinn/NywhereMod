package net.fabricmc.example.mixin;

import net.fabricmc.example.Account;
import net.fabricmc.example.PlayerEntityExt;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.nbt.NbtCompound;

import java.util.ArrayList;
import java.util.List;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements PlayerEntityExt {

  private List<Account> accounts = new ArrayList<>();
  private int amount;

  protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
    super(entityType, world);
  }

  public int getAmount() {
    return this.amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  @Inject(method = "writeCustomDataToNbt", at = @At("RETURN"))
  public void writeCustomDataToTag(NbtCompound nbt, CallbackInfo ci) {
    nbt.putInt("amount", this.amount);
  }

  @Inject(method = "readCustomDataFromNbt", at = @At("RETURN"))
  public void readCustomDataToTag(NbtCompound nbt, CallbackInfo ci) {
    amount = nbt.getInt("amount");
  }

  public Account findAccount(String label) {
    return accounts.stream().filter(account -> label.equals(account.getLabel())).findAny().orElse(null);
  }

  public List<Account> getAccounts() {
    return accounts;
  }

  public void addAccounts(Account account) {
    this.accounts.add(account);
  }
}
