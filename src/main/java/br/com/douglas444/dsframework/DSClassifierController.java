package br.com.douglas444.dsframework;

import br.com.douglas444.mltk.Sample;

import java.util.Optional;

public interface DSClassifierController {

    /** Tries to predicts the label of a sample using the current model and
     * then update the model using the true label.
     *
     * @param sample the sample that the label will be predicted.
     * @return the predicted label or empty if the label could not be predicted.
     */
    public Optional<Integer> predictAndUpdate(Sample sample);

    public String getLog();

}
