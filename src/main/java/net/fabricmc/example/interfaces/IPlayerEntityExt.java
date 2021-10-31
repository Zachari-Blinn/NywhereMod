package net.fabricmc.example.interfaces;

import net.fabricmc.example.Account;

import java.util.List;

public interface IPlayerEntityExt {
  List<Account> getAccounts();

  void addAccounts(Account account);

  Account findAccount(String label);
}
