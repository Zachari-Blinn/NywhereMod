package fr.nywhere.nywheremod.common.commands;

import com.mojang.brigadier.CommandDispatcher;
import fr.nywhere.nywheremod.NywhereMod;
import fr.nywhere.nywheremod.common.world.entity.player.Account;
import fr.nywhere.nywheremod.common.world.entity.player.component.IPlayerComponent;
import fr.nywhere.nywheremod.common.world.entity.player.component.PlayerComponent;
import net.minecraft.network.MessageType;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

import static com.mojang.brigadier.arguments.IntegerArgumentType.getInteger;
import static com.mojang.brigadier.arguments.IntegerArgumentType.integer;
import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.StringArgumentType.string;
import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;

public class BankCommand {

    public static void listener(CommandDispatcher<ServerCommandSource> dispatcher, boolean dedicated) {
        dispatcher.register(literal("nywhere")
                        .then(literal("money")
                                /* MONEY ADD COMMAND */
                                .then(literal("add")
                                        .then(argument("account", string())
                                                .then(argument("amount", integer())
                                                        .executes(context -> {
                                                            IPlayerComponent currentPlayer = ((IPlayerComponent) context.getSource().getPlayer());
                                                            Account account = currentPlayer.findAccount(getString(context, "account"));
                                                            Text message = Text.of("Account not found");
                                                            if (account != null) {
                                                                account.setAmount(account.getAmount() + getInteger(context, "amount"));
                                                                message = Text.of("added " + getInteger(context, "amount") + " to your banking account");
                                                            }
                                                            context.getSource().getServer().getPlayerManager().broadcastChatMessage(message, MessageType.CHAT, context.getSource().getPlayer().getUuid());
                                                            NywhereMod.getLogger().info(getInteger(context, "amount") + " added to " + getString(context, "account") + " of " + context.getSource().getPlayer().getName());
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
                                                            IPlayerComponent currentPlayer = ((IPlayerComponent) context.getSource().getPlayer());
                                                            Account account = currentPlayer.findAccount(getString(context, "account"));
                                                            Text message = Text.of("Account not found");
                                                            if (account != null) {
                                                                account.setAmount(account.getAmount() - getInteger(context, "amount"));
                                                                message = Text.of("remove " + getInteger(context, "amount") + " to your banking account");
                                                            }
                                                            context.getSource().getServer().getPlayerManager().broadcastChatMessage(message, MessageType.CHAT, context.getSource().getPlayer().getUuid());
                                                            NywhereMod.getLogger().info(getInteger(context, "amount") + " removed from " + getString(context, "account") + " of " + context.getSource().getPlayer().getName());
                                                            return 1;
                                                        })
                                                )
                                        )
                                )
                                /* MONEY VIEW COMMAND */
                                .then(literal("view")
                                        .then(argument("account", string())
                                                .executes(context -> {
                                                    IPlayerComponent currentPlayer = ((IPlayerComponent) context.getSource().getPlayer());
                                                    Account account = currentPlayer.findAccount(getString(context, "account"));
                                                    Text message = Text.of("Account not found");
                                                    if (account != null) {
                                                        message = Text.of("Account " + account);
                                                    }
                                                    context.getSource().getServer().getPlayerManager().broadcastChatMessage(message, MessageType.CHAT, context.getSource().getPlayer().getUuid());
                                                    System.out.println(message);
                                                    return 1;
                                                })
                                        )
                                )
                        )
                        /* ACCOUNT CREATE COMMAND */
                        .then(literal("account")
                                .then(literal("create")
                                        .then(argument("label", string())
                                                .then(argument("bank", string())
                                                        .executes(context -> {
                                                            /* IPlayerComponent currentPlayer = ((IPlayerComponent) context.getSource().getPlayer());*/
                                                            IPlayerComponent currentPlayer = PlayerComponent.PLAYER.get(context.getSource().getPlayer());
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
