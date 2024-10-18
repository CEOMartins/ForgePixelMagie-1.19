package net.fenixGroup.pixelmagie.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class Poderes {
    public static void conjurarRaio(Level level, Player player) {
        if (level instanceof ServerLevel serverLevel) {
            BlockHitResult resultado = pegarPosicaoMirada(player, serverLevel);

            if (resultado.getType() == HitResult.Type.BLOCK) {
                BlockPos posicaoMirada = resultado.getBlockPos();
                LightningBolt raio = new LightningBolt(EntityType.LIGHTNING_BOLT, serverLevel);
                raio.moveTo(posicaoMirada.getX() + 0.5, posicaoMirada.getY() + 0.5, posicaoMirada.getZ() + 0.5);
                serverLevel.addFreshEntity(raio);
            }
        }
    }

    public static void conjurarBolaDeFogo(Level level, Player player) {
        if (level instanceof ServerLevel serverLevel) {
            BlockHitResult resultado = pegarPosicaoMirada(player, serverLevel);
            if (resultado.getType() == HitResult.Type.BLOCK) {
                BlockPos posicaoMirada = resultado.getBlockPos();
                Fireball fireball = new Fireball(EntityType.FIREBALL, serverLevel) {
                };
                fireball.moveTo(posicaoMirada.getX() + 0.5 , posicaoMirada.getY() + 0.5 , posicaoMirada.getZ() + 0.5);
                    Vec3 direcao = player.getLookAngle().scale(1.5);
                fireball.setDeltaMovement(direcao);
                serverLevel.addFreshEntity(fireball);
            }
        }
    }

    private static BlockHitResult pegarPosicaoMirada(Player player, Level level) {
        double distanciaMaxima = 100.0D;
        Vec3 posicaoOlhos = player.getEyePosition(1.0F);
        Vec3 direcao = player.getLookAngle().scale(distanciaMaxima);
        Vec3 destino = posicaoOlhos.add(direcao);

        ClipContext contexto = new ClipContext(
                posicaoOlhos,
                destino,
                ClipContext.Block.OUTLINE,
                ClipContext.Fluid.NONE,
                player
        );

        return level.clip(contexto);
    }
}
