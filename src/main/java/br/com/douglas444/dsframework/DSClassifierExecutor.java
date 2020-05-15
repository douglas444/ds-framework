package br.com.douglas444.dsframework;

import br.com.douglas444.mltk.datastructure.Sample;

import java.io.IOException;
import java.util.Optional;

public class DSClassifierExecutor {

    public static void start(final DSClassifierController dsClassifierController, final DSFileReader dsFileReader)
            throws IOException {

        start(dsClassifierController, dsFileReader, true, 1);

    }

    public static void start(final DSClassifierController dsClassifierController, final DSFileReader dsFileReader,
                             final boolean enableClassifierLogging) throws IOException {

        start(dsClassifierController, dsFileReader, enableClassifierLogging, 1);

    }

    public static void start(final DSClassifierController dsClassifierController, final DSFileReader dsFileReader,
                             final boolean enableClassifierLogging, final int classifierLoggingTimestampInterval)
            throws IOException {

        Sample sample;
        int timestamp = 0;

        while ((sample = dsFileReader.next()) != null) {

            sample.setT(timestamp++);

            Optional<Integer> optionalLabel = dsClassifierController.process(sample);
            if (optionalLabel.isPresent()) {
                System.out.println("Real label = " + sample.getY() + " | Classified as = " + optionalLabel.get() + "\n");
            } else {
                System.out.println("Real label = " + sample.getY() + " | Classified as = UNKNOWN\n");
            }

            if (enableClassifierLogging && classifierLoggingTimestampInterval > 0 && timestamp %
                    classifierLoggingTimestampInterval == 0) {

                System.out.println(dsClassifierController.getLog());

            }

        }

    }

}
