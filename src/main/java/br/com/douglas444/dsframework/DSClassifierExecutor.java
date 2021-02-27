package br.com.douglas444.dsframework;

import br.com.douglas444.mltk.datastructure.Sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DSClassifierExecutor {

    private boolean alive;

    public DSClassifierExecutor() {
        this.alive = false;
    }

    public void interrupt() {
        this.alive = false;
    }

    public boolean start(final DSClassifierController dsClassifierController,
                         final DSFileReader... dsFileReader) throws IOException {
        return start(dsClassifierController, 0, dsFileReader);
    }
    public boolean start(final DSClassifierController dsClassifierController, final int classifierLoggingTimestampInterval, final DSFileReader... dsFileReader)
            throws IOException {

        this.alive = true;
        Sample sample;
        int timestamp = 0;

        for (DSFileReader f : dsFileReader) {
            while (this.alive && (sample = f.next()) != null) {
                ++timestamp;
                dsClassifierController.process(sample);
                if (classifierLoggingTimestampInterval > 0 && timestamp % classifierLoggingTimestampInterval == 0) {
                    System.out.println(dsClassifierController.getLog());
                }
            }
        }

        return alive;
    }

}
