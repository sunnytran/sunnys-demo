package core.utils;

import java.nio.FloatBuffer;

import org.lwjgl.system.MemoryUtil;

public class RenderUtils {

    public static FloatBuffer storeDataInFloatBuffer(float data[]) {
        FloatBuffer buffer = MemoryUtil.memAllocFloat(data.length);
        buffer.put(data).flip();

        return buffer;
    }

}
