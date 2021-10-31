package net.fabricmc.example;

import com.mojang.brigadier.CommandDispatcher;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.example.interfaces.IPlayerEntityExt;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.minecraft.network.MessageType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static com.mojang.brigadier.arguments.IntegerArgumentType.getInteger;
import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;
import static com.mojang.brigadier.arguments.StringArgumentType.string;
import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static net.minecraft.server.command.CommandManager.*;


public class NywhereMod implements ModInitializer {

	public static final Logger LOGGER = LogManager.getLogger("modid");

	@Override
	public void onInitialize() {
		LOGGER.info("Nywhere-Mod initialized");

<<<<<<< HEAD
		CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
			dispatcher.register(literal("nywhere")
				.then(literal("money")
					/* MONEY ADD COMMAND */
					.then(literal("add")
						.then(argument("account", string())
							.then(argument("amount", integer())
								.executes(context -> {
									PlayerEntityExt currentPlayer = ((PlayerEntityExt) context.getSource().getPlayer());
									Account account = currentPlayer.findAccount(getString(context, "account"));
									Text message = Text.of("[Nywhere-Mod] Account not found");
									if(account != null) {
										account.setAmount(account.getAmount() + getInteger(context, "amount"));
										message = Text.of("[Nywhere-Mod] added " + getInteger(context, "amount") + " to your banking account");
									}
									context.getSource().getServer().getPlayerManager().broadcastChatMessage(message, MessageType.CHAT, context.getSource().getPlayer().getUuid());
									return 1;
								})
							)
						)
					)
					/* MONEY REMOVE COMMAND */
					.then(literal("remove")
						.then(argument("account", string())
							.then(argument("amount", integer())
								.executes(context -> {
									PlayerEntityExt currentPlayer = ((PlayerEntityExt) context.getSource().getPlayer());
									Account account = currentPlayer.findAccount(getString(context, "account"));
									Text message = Text.of("[Nywhere-Mod] Account not found");
									if(account != null) {
										account.setAmount(account.getAmount() - getInteger(context, "amount"));
										message = Text.of("[Nywhere-Mod] remove " + getInteger(context, "amount") + " to your banking account");
									}
									context.getSource().getServer().getPlayerManager().broadcastChatMessage(message, MessageType.CHAT, context.getSource().getPlayer().getUuid());
									System.out.println(message);
									return 1;
								})
							)
						)
					)
					/* MONEY VIEW COMMAND */
					.then(literal("view")
						.then(argument("account", string())
							.executes(context -> {
								PlayerEntityExt currentPlayer = ((PlayerEntityExt) context.getSource().getPlayer());
								Account account = currentPlayer.findAccount(getString(context, "account"));
								Text message = Text.of("[Nywhere-Mod] Account not found");
								if(account != null) {
									message = Text.of("[Nywhere-Mod] Account " + account.toString());
								}
								context.getSource().getServer().getPlayerManager().broadcastChatMessage(message, MessageType.CHAT, context.getSource().getPlayer().getUuid());
								System.out.println(message);
								return 1;
							})
=======
		CommandRegistrationCallback.EVENT.register(NywhereMod::command);
	}

	private static void command(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
		dispatcher.register(literal("nywhere")
						.then(literal("money")
								/* MONEY ADD COMMAND */
								.then(literal("add")
										.then(argument("account", string())
												.then(argument("amount", integer())
														.executes(context -> {
															IPlayerEntityExt currentPlayer = ((IPlayerEntityExt) context.getSource().getPlayer());
															Account account = currentPlayer.findAccount(getString(context, "account"));
															Text message = Text.of("[Nywhere-Mod] Account not found");
															if (account != null) {
																account.setAmount(account.getAmount() + getInteger(context, "amount"));
																message = Text.of("[Nywhere-Mod] added " + getInteger(context, "amount") + " to your banking account");
															}
															context.getSource().getServer().getPlayerManager().broadcastChatMessage(message, MessageType.CHAT, context.getSource().getPlayer().getUuid());
															LOGGER.info("[Nywhere-Mod] " + getInteger(context, "amount") + " added to " + getString(context, "account") + " of " + context.getSource().getPlayer().getName());
															return 1;
														})
												)
										)
								)
								/* MONEY REMOVE COMMAND */
								.then(literal("remove")
										.then(argument("account", string())
												.then(argument("amount", integer())
														.executes(context -> {
															IPlayerEntityExt currentPlayer = ((IPlayerEntityExt) context.getSource().getPlayer());
															Account account = currentPlayer.findAccount(getString(context, "account"));
															Text message = Text.of("[Nywhere-Mod] Account not found");
															if (account != null) {
																account.setAmount(account.getAmount() - getInteger(context, "amount"));
																message = Text.of("[Nywhere-Mod] remove " + getInteger(context, "amount") + " to your banking account");
															}
															context.getSource().getServer().getPlayerManager().broadcastChatMessage(message, MessageType.CHAT, context.getSource().getPlayer().getUuid());
															LOGGER.info("[Nywhere-Mod] " + getInteger(context, "amount") + " removed from " + getString(context, "account") + " of " + context.getSource().getPlayer().getName());
															return 1;
														})
												)
										)
								)
								/* MONEY VIEW COMMAND */
								.then(literal("view")
										.then(argument("account", string())
												.executes(context -> {
													IPlayerEntityExt currentPlayer = ((IPlayerEntityExt) context.getSource().getPlayer());
													Account account = currentPlayer.findAccount(getString(context, "account"));
													Text message = Text.of("[Nywhere-Mod] Account not found");
													if (account != null) {
														message = Text.of("[Nywhere-Mod] Account " + account.toString());
													}
													context.getSource().getServer().getPlayerManager().broadcastChatMessage(message, MessageType.CHAT, context.getSource().getPlayer().getUuid());
													System.out.println(message);
													return 1;
												})
										)
								)
>>>>>>> 4f98736b9adb873a430623eee03e2534c60b5628
						)
						/* ACCOUNT CREATE COMMAND */
						.then(literal("account")
								.then(literal("create")
										.then(argument("label", string())
												.then(argument("bank", string())
														.executes(context -> {
															IPlayerEntityExt currentPlayer = ((IPlayerEntityExt) context.getSource().getPlayer());
															Account account = new Account(getString(context, "label"), getString(context, "bank"));
															currentPlayer.addAccounts(account);
															context.getSource().getServer().getPlayerManager().broadcastChatMessage(Text.of("New account was created: " + currentPlayer.findAccount(getString(context, "label"))), MessageType.CHAT, context.getSource().getPlayer().getUuid());
															return 1;
													})
												)
										)
								)
						)
				/* todo ACCOUNT LIST */
		);
	}

}
