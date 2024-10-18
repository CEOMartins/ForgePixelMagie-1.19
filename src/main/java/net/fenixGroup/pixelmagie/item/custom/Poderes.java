package net.fenixGroup.pixelmagie.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;

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
            // Posicione a bola de fogo na posição do jogador
            Vec3 posicaoJogador = player.position();
            Fireball fireball = new Fireball(EntityType.FIREBALL, serverLevel) {
                // Definindo um tempo de vida para a bola de fogo
                private int tempoDeVida = 0;

                @Override
                public void tick() {
                    super.tick(); // Chamando o tick da superclasse para garantir que o movimento e a lógica da bola de fogo sejam aplicadas
                    tempoDeVida++;

                    // Verifica se a bola de fogo colidiu com algo
                    if (tempoDeVida > 20) { // Exemplo: 20 ticks (1 segundo) antes de verificar a colisão
                        if (this.isOnFire()) {
                            this.remove(Entity.RemovalReason.DISCARDED); // Remover a bola de fogo após a colisão
                        }
                    }
                }

                @Override
                protected void onHit(HitResult result) {
                    // Cria a explosão ao colidir
                    if (!level.isClientSide) {
                        // Define a posição da explosão na posição da bola de fogo
                        Vec3 posicaoExplosao = this.position();
                        level.explode(this, posicaoExplosao.x, posicaoExplosao.y, posicaoExplosao.z, 3.0F, Explosion.BlockInteraction.DESTROY); // Altere BLOCKS para DESTROY

                        // Causa dano a entidades ao redor da explosão
                        List<Entity> entidadesAtingidas = level.getEntities(this, this.getBoundingBox().inflate(3.0D));
                        for (Entity entidade : entidadesAtingidas) {
                            if (entidade instanceof LivingEntity) {
                                // Causa 5 de dano a cada entidade viva atingida (ajuste o valor conforme necessário)
                                ((LivingEntity) entidade).hurt(DamageSource.fireball(this, player), 5.0F);
                            }
                        }
                    }
                    this.remove(Entity.RemovalReason.DISCARDED); // Remover a bola de fogo após a explosão
                }
            };

            // Define a posição inicial da bola de fogo na altura dos olhos do jogador
            fireball.moveTo(posicaoJogador.x, posicaoJogador.y + player.getEyeHeight(), posicaoJogador.z);

            // Calcule a direção para onde o jogador está olhando e defina a velocidade
            Vec3 direcao = player.getLookAngle().scale(1.5); // Aumente o multiplicador se necessário para alterar a velocidade
            fireball.setDeltaMovement(direcao);

            // Adiciona a bola de fogo ao mundo
            serverLevel.addFreshEntity(fireball);
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
