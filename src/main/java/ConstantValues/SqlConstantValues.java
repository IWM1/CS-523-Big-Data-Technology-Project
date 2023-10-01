package ConstantValues;

import static ConstantValues.Config.getValueFromNestedMap;

public class SqlConstantValues {
    public static final String Use_default_schema = getValueFromNestedMap("APP.SQL.Use_default_schema");
    public static final String Get_footballer_data = getValueFromNestedMap("APP.SQL.Get_footballer_data");
    public static  final String Advanced_sql = getValueFromNestedMap("APP.SQL.Advanced_sql");
}
