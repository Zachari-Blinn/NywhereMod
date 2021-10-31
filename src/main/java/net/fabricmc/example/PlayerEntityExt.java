package net.fabricmc.example;

import java.util.List;

public interface PlayerEntityExt {
  List<Account> getAccounts();

  void addAccounts(Account account);

  Account findAccount(String label);
}
