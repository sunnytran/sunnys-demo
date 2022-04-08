package core.utils;

import java.io.File;
import java.io.InputStream;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.lwjgl.system.MemoryUtil;

public class RenderUtils {

    public static FloatBuffer storeDataInFloatBuffer(float data[]) {
        FloatBuffer buffer = MemoryUtil.memAllocFloat(data.length);
        buffer.put(data).flip();

        return buffer;
    }

    public static IntBuffer storeDataInIntBuffer(int data[]) {
        IntBuffer buffer = MemoryUtil.memAllocInt(data.length);
        buffer.put(data).flip();

        return buffer;
    }

    public static String loadResource(String filename) throws Exception {
        String result;

        try (Scanner scanner = new Scanner(new File(filename), StandardCharsets.UTF_8.name())) {
            result = scanner.useDelimiter("\\A").next();
        }

        return result;
    }

}
