package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.entity.Entity;
import net.minecraft.network.MessageType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static com.mojang.brigadier.arguments.IntegerArgumentType.getInteger;
import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;
import static net.minecraft.server.command.CommandManager.*;


public class NywhereMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LogManager.getLogger("modid");

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("NywhereMod initialized");

		CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
			dispatcher.register(literal("nywhere")
				.then(literal("money")
						/* ADD COMMAND */
					.then(literal("add")
						.then(argument("amount", integer())
							.executes(context -> {
								((PlayerEntityExt) context.getSource().getPlayer()).setAmount(getInteger(context, "amount"));
								Text message = Text.of("[Nywhere-Mod] added " + getInteger(context, "amount") + " to your banking account");
								context.getSource().getServer().getPlayerManager().broadcastChatMessage(message, MessageType.CHAT, context.getSource().getPlayer().getUuid());
								System.out.println("accountValue: " + ((PlayerEntityExt) context.getSource().getPlayer()).getAmount());
								return 1;
							})
						)
					)
					/* REMOVE COMMAND */
					.then(literal("remove")
						.then(argument("amount", integer())
							.executes(context -> {
								//setAmount(getAmount() - getInteger(context, "amount"));
								((PlayerEntityExt) context.getSource().getPlayer()).setAmount(((PlayerEntityExt) context.getSource().getPlayer()).getAmount() - getInteger(context, "amount"));
								Text message = Text.of("[NywhereMod] remove " + getInteger(context, "amount") + " to your banking account");
								context.getSource().getServer().getPlayerManager().broadcastChatMessage(message, MessageType.CHAT, context.getSource().getPlayer().getUuid());
								System.out.println(message);
								return 1;
							})
						)
					)
					/* VIEW COMMAND */
					.then(literal("view")
						.executes(context -> {
							Text message = Text.of("[NywhereMod] Amount of your money " + ((PlayerEntityExt) context.getSource().getPlayer()).getAmount());
							context.getSource().getServer().getPlayerManager().broadcastChatMessage(message, MessageType.CHAT, context.getSource().getPlayer().getUuid());
							System.out.println(message);
							return 1;
						})
					)
				)
			);
		});
	}
}
