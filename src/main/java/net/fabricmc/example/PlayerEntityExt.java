package net.fabricmc.example;

import java.util.List;

public interface PlayerEntityExt {
  void setAmount(int amount);

  int getAmount();

  List<Account> getAccounts();

  void addAccounts(Account account);

  Account findAccount(String label);
}
