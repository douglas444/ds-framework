package br.com.douglas444.dsframework;

import br.com.douglas444.mltk.Sample;

import java.io.IOException;

public class DSRunnable {

    public static void run(DSClassifierController dsClassifierController, DSFileReader dsFileReader, boolean activeLogger, int loggerTimestampInterval) throws IOException {

        Sample sample;
        int timestamp = 0;

        while ((sample = dsFileReader.next()) != null) {

            sample.setT(timestamp++);
            dsClassifierController.predictAndUpdate(sample);

            if (activeLogger && loggerTimestampInterval > 0 && timestamp % loggerTimestampInterval == 0) {
                System.out.println(dsClassifierController.getLog());
            }

        }

        if (activeLogger) {
            System.out.println(dsClassifierController.getLog());
        }
    }

}
