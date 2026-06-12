package com.buildsense.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public final class AiCommands {

    private AiCommands() {
        // Utility class: do not instantiate.
    }

    public static LiteralArgumentBuilder<CommandSourceStack> branch() {
        return Commands.literal("ai")
                .then(
                        Commands.literal("status")
                                .executes(ctx -> {
                                    ctx.getSource().sendSuccess(
                                            () -> Component.literal("AI provider: not configured. Fallback planner only. (Phase 4 feature)"),
                                            false
                                    );
                                    return 1;
                                })
                );
    }
}