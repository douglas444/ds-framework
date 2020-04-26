package br.com.douglas444.dsframework;

import br.com.douglas444.mltk.datastructure.Sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class DSFileReader {

    private String separator;
    private BufferedReader bufferedReaderData;
    private BufferedReader bufferedReaderLabel;
    private boolean usingSeparatedFiles;

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

    }

    /** Reads the next sample of the stream.
     *
     * @return the next sample of the stream.
     */
    public Sample next() throws IOException, NumberFormatException {

        String line = bufferedReaderData.readLine();

        if (line == null) {
            bufferedReaderData.close();
            if (usingSeparatedFiles) {
                bufferedReaderLabel.close();
            }
            return null;
        }

        String[] splittedLine = line.split(this.separator);
        final int numberOfFeatures = usingSeparatedFiles ? splittedLine.length : splittedLine.length - 1;
        double[] x = new double[numberOfFeatures];

        for (int i = 0; i < numberOfFeatures; ++i) {
            x[i] = Double.parseDouble(splittedLine[i]);
        }

        int y;
        if (usingSeparatedFiles) {
            line = bufferedReaderLabel.readLine();
            y = (int) Double.parseDouble(line);
        } else {
            y = (int) Double.parseDouble(splittedLine[splittedLine.length - 1]);
        }

        return new Sample(x, y);
    }


    public List<Sample> next(int n) throws IOException, NumberFormatException {

        final List<Sample> samples = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            samples.add(next());
        }
        return samples;

    }

}
