package ConstantValues;

import static ConstantValues.Config.getValueFromNestedMap;

public class KafkaConstantValues {

    public static final String Topic = getValueFromNestedMap("APP.Kafka.Topic");
    public static final String server = getValueFromNestedMap("APP.Kafka.server");

}
