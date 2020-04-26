package br.com.douglas444.dsframework;

import br.com.douglas444.mltk.datastructure.Sample;

import java.io.IOException;

public class DSClassifierExecutor {

    public static void start(DSClassifierController dsClassifierController, DSFileReader dsFileReader)
            throws IOException {

        start(dsClassifierController, dsFileReader, true, 1);

    }

    public static void start(DSClassifierController dsClassifierController, DSFileReader dsFileReader,
                             boolean enableLogger) throws IOException {

        start(dsClassifierController, dsFileReader, enableLogger, 1);

    }

    public static void start(DSClassifierController dsClassifierController, DSFileReader dsFileReader,
                             boolean enableLogger, int loggerTimestampInterval) throws IOException {

        Sample sample;
        int timestamp = 0;

        while ((sample = dsFileReader.next()) != null) {

            sample.setT(timestamp++);
            dsClassifierController.predictAndUpdate(sample);

            if (enableLogger && loggerTimestampInterval > 0 && timestamp % loggerTimestampInterval == 0) {
                System.out.println(dsClassifierController.getLog());
            }

        }

        if (enableLogger) {
            System.out.println(dsClassifierController.getLog());
        }
    }

}
