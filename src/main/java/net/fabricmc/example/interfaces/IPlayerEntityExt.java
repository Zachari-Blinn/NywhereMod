package net.fabricmc.example.interfaces;

import net.fabricmc.example.Account;

import java.util.List;

<<<<<<< HEAD:src/main/java/net/fabricmc/example/PlayerEntityExt.java
public interface PlayerEntityExt {
=======
public interface IPlayerEntityExt {
  void setAmount(int amount);

  int getAmount();

>>>>>>> 4f98736b9adb873a430623eee03e2534c60b5628:src/main/java/net/fabricmc/example/interfaces/IPlayerEntityExt.java
  List<Account> getAccounts();

  void addAccounts(Account account);

  Account findAccount(String label);
}
