package br.com.douglas444.dsframework;

import br.com.douglas444.mltk.Point;

import java.io.IOException;

public class DSRunnable {

    public static void run(DSClassifierController dsClassifierController, DSFileReader dsFileReader) throws IOException {

        Point point;
        while ((point = dsFileReader.next()) != null) {
            dsClassifierController.predictAndUpdate(point);
            System.out.println(dsClassifierController.getLog());
        }
    }

}
