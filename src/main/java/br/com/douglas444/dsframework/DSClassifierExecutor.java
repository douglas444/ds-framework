package br.com.douglas444.dsframework;

import br.com.douglas444.mltk.datastructure.Sample;

import java.io.IOException;

public class DSClassifierExecutor {

    public static void start(final DSClassifierController dsClassifierController, final DSFileReader dsFileReader,
                             final boolean enableClassifierLogging, final int classifierLoggingTimestampInterval)
            throws IOException {

        Sample sample;
        int timestamp = 0;

        while ((sample = dsFileReader.next()) != null) {

            ++timestamp;

            dsClassifierController.process(sample);

            if (enableClassifierLogging && classifierLoggingTimestampInterval > 0 && timestamp %
                    classifierLoggingTimestampInterval == 0) {

                System.out.println(dsClassifierController.getLog());

            }

        }

    }

}
