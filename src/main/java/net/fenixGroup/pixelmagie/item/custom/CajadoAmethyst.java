package net.fenixGroup.pixelmagie.item.custom;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CajadoAmethyst extends Item {
    private int poderAtual = 0; // 0 para Raio, 1 para Creeper

    public CajadoAmethyst(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {
            if (player.isShiftKeyDown()) {
                alternarPoder();
            } else {
                // Chama o metodo do poder atual
                if (poderAtual == 0) {
                    Poderes.conjurarRaio(level, player);
                } else if (poderAtual == 1) {
                    Poderes.conjurarCreeper(level, player);
                }
                player.getCooldowns().addCooldown(this, 10);
            }
        }

        return super.use(level, player, hand);
    }

    private void alternarPoder() {
        poderAtual = (poderAtual + 1) % 2; // Alterna entre Raio e Creeper
    }

    private String obterNomePoder() {
        return poderAtual == 0 ? "Raio" : "Creeper"; // Retorna o nome do poder atual
    }
}
