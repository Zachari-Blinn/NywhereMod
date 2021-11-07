package fr.nywhere.nywheremod;

import fr.nywhere.nywheremod.common.commands.BankCommand;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class NywhereMod implements ModInitializer {

    public static final String MODID = "nywheremod";
    private static final Logger LOGGER = LogManager.getLogger("Nywhere Mod");

    public static Logger getLogger() {
        return LOGGER;
    }

    public static Identifier id(String valueIn) {
        return new Identifier(MODID, valueIn);
    }

    @Override
    public void onInitialize() {
        LOGGER.info("Nywhere-Mod initialized");
        CommandRegistrationCallback.EVENT.register(BankCommand::listener);
    }
}
