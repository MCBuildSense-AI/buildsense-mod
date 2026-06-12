package com.buildsense.command;

import com.buildsense.BuildSenseMod;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public final class BuildSenseCommands {

    private BuildSenseCommands() {
        // Utility class: do not instantiate.
    }

    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, buildContext, selection) -> {

            LiteralArgumentBuilder<CommandSourceStack> root =
                    Commands.literal("buildsense")
                            .executes(ctx -> {
                                ctx.getSource().sendSuccess(
                                        () -> Component.literal("BuildSense AI is alive."),
                                        false
                                );
                                return 1;
                            })
                            .then(
                                    Commands.literal("ping")
                                            .executes(ctx -> {
                                                ctx.getSource().sendSuccess(
                                                        () -> Component.literal("pong"),
                                                        false
                                                );
                                                return 1;
                                            })
                            )
                            .then(
                                    Commands.literal("version")
                                            .executes(ctx -> {
                                                String version = FabricLoader.getInstance()
                                                        .getModContainer(BuildSenseMod.MOD_ID)
                                                        .map(container -> container.getMetadata().getVersion().getFriendlyString())
                                                        .orElse("unknown");

                                                ctx.getSource().sendSuccess(
                                                        () -> Component.literal("BuildSense version: " + version),
                                                        false
                                                );
                                                return 1;
                                            })
                            )
                            .then(AiCommands.branch());

            dispatcher.register(root);
        });
    }
}