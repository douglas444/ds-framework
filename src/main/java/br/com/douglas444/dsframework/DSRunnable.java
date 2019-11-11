package br.com.douglas444.dsframework;

import br.com.douglas444.mltk.Sample;

import java.io.IOException;

public class DSRunnable {

    public static void run(DSClassifierController dsClassifierController, DSFileReader dsFileReader) throws IOException {

        Sample sample;
        while ((sample = dsFileReader.next()) != null) {
            dsClassifierController.predictAndUpdate(sample);
            System.out.println(dsClassifierController.getLog());
        }
    }

}
