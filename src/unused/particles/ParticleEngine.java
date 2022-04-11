package unused.particles;

import java.util.ArrayList;
import java.util.List;

import core.utils.Camera;

public class ParticleEngine {

    private static List<ParticleBatch> batches = new ArrayList<ParticleBatch>();

    public static void addParticleBatch(ParticleBatch particleBatch) {
        batches.add(particleBatch);
    }

    public void update() {

        for (ParticleBatch batch : batches) {
            batch.update();
        }

    }

    public void render(Camera camera) {

        for (ParticleBatch batch : batches) {
            batch.render(camera);
        }

    }

}
