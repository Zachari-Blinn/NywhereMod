package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.network.MessageType;
import net.minecraft.text.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static com.mojang.brigadier.arguments.IntegerArgumentType.getInteger;
import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;
import static com.mojang.brigadier.arguments.StringArgumentType.string;
import static com.mojang.brigadier.arguments.StringArgumentType.getString;
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

		LOGGER.info("Nywhere-Mod initialized");

		CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
			dispatcher.register(literal("nywhere")
				.then(literal("money")
						/* ADD COMMAND */
					.then(literal("add")
						.then(argument("amount", integer())
							.executes(context -> {
								PlayerEntityExt currentPlayer = ((PlayerEntityExt) context.getSource().getPlayer());

								currentPlayer.setAmount(currentPlayer.getAmount() + getInteger(context, "amount"));
								Text message = Text.of("[Nywhere-Mod] added " + getInteger(context, "amount") + " to your banking account");
								context.getSource().getServer().getPlayerManager().broadcastChatMessage(message, MessageType.CHAT, context.getSource().getPlayer().getUuid());
								System.out.println("accountValue: " + currentPlayer.getAmount());

								/* TEST account replacement system */
								Account account = new Account("livret B", getInteger(context, "amount"), true);
								currentPlayer.addAccounts(account);
								context.getSource().getServer().getPlayerManager().broadcastChatMessage(Text.of("teest: " + currentPlayer.findAccount("livret B")), MessageType.CHAT, context.getSource().getPlayer().getUuid());
								return 1;
							})
						)
					)
					/* REMOVE COMMAND */
					.then(literal("remove")
						.then(argument("amount", integer())
							.executes(context -> {
								PlayerEntityExt currentPlayer = ((PlayerEntityExt) context.getSource().getPlayer());

								currentPlayer.setAmount(currentPlayer.getAmount() - getInteger(context, "amount"));
								Text message = Text.of("[Nywhere-Mod] remove " + getInteger(context, "amount") + " to your banking account");
								context.getSource().getServer().getPlayerManager().broadcastChatMessage(message, MessageType.CHAT, context.getSource().getPlayer().getUuid());
								System.out.println(message);
								return 1;
							})
						)
					)
					/* VIEW COMMAND */
					.then(literal("view")
						.executes(context -> {
							PlayerEntityExt currentPlayer = ((PlayerEntityExt) context.getSource().getPlayer());
							Text message = Text.of("[Nywhere-Mod] Amount of your money " + currentPlayer.getAmount());
							context.getSource().getServer().getPlayerManager().broadcastChatMessage(message, MessageType.CHAT, context.getSource().getPlayer().getUuid());
							System.out.println(message);
							return 1;
						})
					)
				)
				.then(literal("account")
					.then(literal("create")
						.then(argument("label", string())
							.executes(context -> {
								PlayerEntityExt currentPlayer = ((PlayerEntityExt) context.getSource().getPlayer());
								Account account = new Account(getString(context,"label"), 0, true);
								currentPlayer.addAccounts(account);
								context.getSource().getServer().getPlayerManager().broadcastChatMessage(Text.of("New account was created: " + currentPlayer.findAccount(getString(context, "label"))), MessageType.CHAT, context.getSource().getPlayer().getUuid());
								return 1;
							})
						)
					)
				)
			);
		});
	}
}
