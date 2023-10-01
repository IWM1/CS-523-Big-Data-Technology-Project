import org.apache.spark.SparkConf;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import static ConstantValues.SparkConstantValues.*;
import static ConstantValues.SqlConstantValues.*;

public class Spark {
    public static void main(String[] args) {
        final SparkConf sparkConf = new SparkConf();
        sparkConf.setMaster("local[*]");
        sparkConf.set("hive.metastore.uris", URL);

        SparkSession sparkSession = SparkSession
                .builder()
                .config(sparkConf)
                .appName("Spark")
                .config("spark.sql.warehouse.dir", Directory)
                .enableHiveSupport()
                .getOrCreate();

        sparkSession.sql(Use_default_schema);

        Dataset<Row> rowDataset = sparkSession.sql(Get_footballer_data);
        rowDataset.show();

        rowDataset = sparkSession.sql(Advanced_sql);
        rowDataset.show();
    }
}
