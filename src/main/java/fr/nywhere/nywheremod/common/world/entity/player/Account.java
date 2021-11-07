package fr.nywhere.nywheremod.common.world.entity.player;

import net.minecraft.nbt.NbtCompound;

import java.util.Date;
import java.util.Random;

public class Account {
    private final String label;
    private String code;
    private double amount = 0.0;
    private long createdAt = new Date(System.currentTimeMillis()).getTime();
    private boolean isActive = true;
    private Office office;

    /**
     * Constructor for command
     */
    public Account(String label, String bank) {
        this.label = label;
        this.office = this.getRandomOfficeFromBank(bank);
        this.code = Utils.generateRandomNumber(11);
    }

    public Account(String label) {
        this.label = label;
    }

    public Account(NbtCompound nbtCompound) {
        this.label = nbtCompound.getString("label");
        this.code = nbtCompound.getString("code");
        this.amount = nbtCompound.getDouble("amount");
        this.createdAt = nbtCompound.getLong("created_at");
    }

    public Office getRandomOfficeFromBank(String bank) {
        Random random = new Random();
        //return list.get(random.nextInt(list.size()));
        return null;
    }

    public String getLabel() {
        return label;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    //todo setUser

    @Override
    public String toString() {
        return "Account{" +
                "label='" + label + '\'' +
                ", amount=" + amount +
                ", createdAt=" + createdAt +
                ", isActive=" + isActive +
                '}';
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public String getCode() {
        return code;
    }
}
