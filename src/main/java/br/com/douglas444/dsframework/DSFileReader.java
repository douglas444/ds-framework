package br.com.douglas444.dsframework;

import br.com.douglas444.mltk.datastructure.Sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class DSFileReader {

    private final String separator;
    private final BufferedReader bufferedReaderData;
    private final BufferedReader bufferedReaderLabel;
    private final boolean usingSeparatedFiles;

    public DSFileReader(String separator, Reader dataReader, Reader labelReader) {

        this.usingSeparatedFiles = true;
        this.separator = separator;
        this.bufferedReaderData = new BufferedReader(dataReader);
        this.bufferedReaderLabel = new BufferedReader(labelReader);

    }

    public DSFileReader(String separator, Reader reader) {

        this.usingSeparatedFiles = false;
        this.separator = separator;
        this.bufferedReaderData = new BufferedReader(reader);
        this.bufferedReaderLabel = null;

    }

    public Sample next() throws IOException, NumberFormatException {

        String line = this.bufferedReaderData.readLine();

        if (line == null) {
            this.bufferedReaderData.close();

            if (this.usingSeparatedFiles) {
                assert this.bufferedReaderLabel != null;
                this.bufferedReaderLabel.close();
            }

            return null;
        }

        final String[] splittedLine = line.split(this.separator);
        final int numberOfFeatures = this.usingSeparatedFiles ? splittedLine.length : splittedLine.length - 1;
        final double[] x = new double[numberOfFeatures];

        for (int i = 0; i < numberOfFeatures; ++i) {
            x[i] = Double.parseDouble(splittedLine[i]);
        }

        final int y;

        if (this.usingSeparatedFiles) {

            assert this.bufferedReaderLabel != null;
            line = this.bufferedReaderLabel.readLine();
            y = (int) Double.parseDouble(line);

        } else {
            y = (int) Double.parseDouble(splittedLine[splittedLine.length - 1]);
        }

        return new Sample(x, y);
    }


    public List<Sample> next(final int n) throws IOException, NumberFormatException {

        final List<Sample> samples = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            samples.add(next());
        }
        return samples;

    }

}
