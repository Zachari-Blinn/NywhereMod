package fr.nywhere.nywheremod.common.world.entity.player;

import java.util.ArrayList;
import java.util.List;

public class Office {
    private final String label;
    private final String code;
    private final Bank bank;
    private List<Account> accounts = new ArrayList<>();

    public Office(String label, Bank bank) {
        this.label = label;
        this.bank = bank;
        this.code = Utils.generateRandomNumber(5);
    }

    public Office getRandomOfficeFromBank(Bank bank){
        return null;
    }

    public String getLabel() {
        return label;
    }

    public String getCode() {
        return code;
    }

    public Bank getBank() {
        return bank;
    }

    public void addAccounts(Account account) {
        this.accounts.add(account);
    }
}
