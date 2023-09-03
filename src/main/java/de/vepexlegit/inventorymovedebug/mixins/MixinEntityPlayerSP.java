package de.vepexlegit.inventorymovedebug.mixins;

import de.vepexlegit.inventorymovedebug.InventoryMove;
import de.vepexlegit.inventorymovedebug.command.InventoryMoveCommand;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.ClientCommandHandler;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityPlayerSP.class)
public class MixinEntityPlayerSP {
    @Inject(method = "onUpdate", at = @At("RETURN"))
    private void startGame(final CallbackInfo ci) {
        ClientCommandHandler.instance.registerCommand(new InventoryMoveCommand());
    }

    @Shadow private Minecraft mc = Minecraft.getMinecraft();

    @Inject(method = "onUpdate", at = @At("HEAD"))
    private void onUpdate(final CallbackInfo ci) {
        if (InventoryMove.INSTANCE.isEnabled()) {
            final KeyBinding[] keys = {mc.gameSettings.keyBindForward, mc.gameSettings.keyBindBack, mc.gameSettings.keyBindLeft, mc.gameSettings.keyBindRight, mc.gameSettings.keyBindJump, mc.gameSettings.keyBindSneak, mc.gameSettings.keyBindSprint};
            if (mc.currentScreen != null && !(mc.currentScreen instanceof GuiChat)) {
                for (int i = 0; i < keys.length; ++i) {
                    KeyBinding.setKeyBindState(keys[i].getKeyCode(), Keyboard.isKeyDown(keys[i].getKeyCode()));
                }
            }
        }
    }
}
