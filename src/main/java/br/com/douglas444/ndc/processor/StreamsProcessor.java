package br.com.douglas444.ndc.processor;

import br.com.douglas444.ndc.datastructures.Sample;

import java.util.Optional;

public interface StreamsProcessor {

    Optional<Integer> process(final Sample sample);

    String getLog();

}
