package com.yakovliam.taps.api.predictor;

import jakarta.xml.bind.JAXBException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import org.jpmml.evaluator.Evaluator;
import org.jpmml.evaluator.InputField;
import org.jpmml.evaluator.LoadingModelEvaluatorBuilder;
import org.jpmml.evaluator.OutputField;
import org.slf4j.Logger;
import org.xml.sax.SAXException;

public class PMMLModelPredictor<I, O> {

  private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(PMMLModelPredictor.class);

  private final Evaluator evaluator;

  private final OutputField outputField;

  public PMMLModelPredictor(String modelPath, String outputField) {
    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    InputStream is = classloader.getResourceAsStream(modelPath);

    Evaluator evaluator;
    try {
      evaluator = new LoadingModelEvaluatorBuilder().load(is).build();
    } catch (ParserConfigurationException | SAXException | JAXBException e) {
      LOGGER.error("Error loading model", e);
      throw new RuntimeException(e);
    }

    // performing the self-check
    evaluator.verify();

    this.evaluator = evaluator;
    this.outputField =
        evaluator.getOutputFields().stream().filter(field -> field.getName().equals(outputField))
            .findFirst().orElseThrow(() -> new IllegalArgumentException("Output field not found"));
  }

  public O predict(I input) {
    Map<InputField, Object> arguments = evaluator.getInputFields().stream()
        .collect(HashMap::new, (map, field) -> map.put(field, input), Map::putAll);

    Map<String, ?> argumentsMapped = arguments.entrySet().stream()
        .collect(HashMap::new, (map, entry) -> map.put(entry.getKey().getName(), entry.getValue()),
            Map::putAll);

    Map<String, ?> result = evaluator.evaluate(argumentsMapped);

    @SuppressWarnings("unchecked") O resultValue = (O) result.get(outputField.getName());

    return resultValue;
  }
}
