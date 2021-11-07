package fr.nywhere.nywheremod.common.world.entity.player;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private final String name;
    private final String code;
    private List<Office> offices = new ArrayList<>();

    public Bank(String name) {
        this.name = name;
        this.code = Utils.generateRandomNumber(5);
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public void addOffices(Office office) {
        this.offices.add(office);
    }

    public List<Office> getAllOffice() {
        return this.offices;
    }
}
