package ConstantValues;

import static ConstantValues.Config.getValueFromNestedMap;

public class SparkConstantValues {
    public static final String Table_name = getValueFromNestedMap("APP.SPARK.Table_name");
    public static final String Directory = getValueFromNestedMap("APP.SPARK.Directory");
    public static final String URL = getValueFromNestedMap("APP.SPARK.URL");
}
