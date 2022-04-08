package audios;

import java.io.IOException;

public class AudioTester {

    public static void main(String[] args) throws IOException {
        AudioMaster.init();
        AudioMaster.setListenerData(0, 0, 0);

        int buffer = AudioMaster.loadSound("res/bg.wav");
        Source source = new Source();

        char c = ' ';
        while (c != 'q') {
            c = (char) System.in.read();

            if (c == 'p') {
                source.play(buffer);
            }
        }

        source.delete();
        AudioMaster.cleanUp();

    }

}
