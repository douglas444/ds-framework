package br.com.douglas444.dsframework;

import br.com.douglas444.mltk.Point;

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

    public DSFileReader(String separator, Reader dataReader, Reader classReader) {

        this.usingSeparatedFiles = true;
        this.separator = separator;
        this.bufferedReaderData = new BufferedReader(dataReader);
        this.bufferedReaderLabel = new BufferedReader(classReader);

    }

    public DSFileReader(String separator, Reader reader) {

        this.usingSeparatedFiles = false;
        this.separator = separator;
        this.bufferedReaderData = new BufferedReader(reader);

    }

    /** Reads the next point of the stream.
     *
     * @return the next point of the stream.
     */
    public Point next() throws IOException, NumberFormatException {

        String line = bufferedReaderData.readLine();

        if (line == null) {
            bufferedReaderData.close();
            if (usingSeparatedFiles) {
                bufferedReaderLabel.close();
            }
            return null;
        }

        String[] splittedLine = line.split(this.separator);
        double[] x = new double[splittedLine.length];
        int numberOfFeatures = usingSeparatedFiles ? splittedLine.length - 1 : splittedLine.length;

        for (int i = 0; i < numberOfFeatures; ++i) {
            x[i] = Double.parseDouble(splittedLine[i]);
        }

        int y;
        if (usingSeparatedFiles) {
            line = bufferedReaderLabel.readLine();
            y = Integer.parseInt(line);
        } else {
            y = Integer.parseInt(splittedLine[splittedLine.length - 1]);
        }

        return new Point(x, y);
    }


    public List<Point> next(int n) throws IOException, NumberFormatException {

        List<Point> points = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            points.add(next());
        }
        return points;

    }

}
