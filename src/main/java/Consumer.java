import org.apache.spark.sql.*;
import org.apache.spark.sql.streaming.StreamingQueryException;
import org.apache.spark.sql.types.DataTypes;

import static ConstantValues.CSVConstantValues.*;
import static ConstantValues.SparkConstantValues.*;
import static ConstantValues.KafkaConstantValues.*;

public class Consumer {
    public static void main(String[] args) throws StreamingQueryException, InterruptedException {

        SparkSession spark = SparkSession.builder()
                .appName("Consumer")
                .config("hive.metastore.uris", URL)
                .master("local[*]")
                .enableHiveSupport()
                .getOrCreate();

        Dataset<Row> ds = spark.readStream().format("kafka")
                .option("kafka.bootstrap.servers", server)
                .option("subscribe", Topic)
                .load();

        Dataset<Row> lines = ds.selectExpr("CAST(value AS STRING)");

        Dataset<Row> dataset = processCsvDataSet(lines);

        dataset.writeStream()
                .foreachBatch((rowDataset, aLong) -> rowDataset
                        .write()
                        .mode(SaveMode.Append)
                        .insertInto(Table_name))
                .option("spark.sql.streaming.checkpointLocation", Directory)
                .start()
                .awaitTermination();
    }
    public static Dataset<Row> processCsvDataSet(Dataset<Row> lines) {
        Dataset<Row> schemaDS = lines
                .selectExpr("value",
                        "split(value,',')[0] as player_country",
                        "split(value,',')[1] as first_name",
                        "split(value,',')[2] as last_name",
                        "split(value,',')[3] as goals_scored",
                        "split(value,',')[4] as champions_league_matches_played",
                        "split(value,',')[5] as english_league_matches_played",
                        "split(value,',')[6] as minutes_played",
                        "split(value,',')[7] as assists",
                        "split(value,',')[8] as tackles",
                        "split(value,',')[9] as age")
                .drop("value");

        schemaDS = schemaDS

                .withColumn(player_country, functions.regexp_replace(functions.col(player_country), " ", ""))
                .withColumn(first_name, functions.regexp_replace(functions.col(first_name), " ", ""))
                .withColumn(last_name, functions.regexp_replace(functions.col(last_name), " ", ""))
                .withColumn(goals_scored, functions.regexp_replace(functions.col(goals_scored), " ", ""))
                .withColumn(champions_league_matches_played, functions.regexp_replace(functions.col(champions_league_matches_played), " ", ""))
                .withColumn(english_league_matches_played, functions.regexp_replace(functions.col(english_league_matches_played), " ", ""))
                .withColumn(minutes_played, functions.regexp_replace(functions.col(minutes_played), " ", ""))
                .withColumn(assists, functions.regexp_replace(functions.col(assists), " ", ""))
                .withColumn(tackles, functions.regexp_replace(functions.col(tackles), " ", ""))
                .withColumn(age, functions.regexp_replace(functions.col(age), " ", ""));

        schemaDS = schemaDS
                .withColumn(player_country, functions.col(player_country).cast(DataTypes.StringType))
                .withColumn(first_name, functions.col(first_name).cast(DataTypes.StringType))
                .withColumn(last_name, functions.col(last_name).cast(DataTypes.StringType))
                .withColumn(goals_scored, functions.col(goals_scored).cast(DataTypes.IntegerType))
                .withColumn(champions_league_matches_played, functions.col(champions_league_matches_played).cast(DataTypes.IntegerType))
                .withColumn(english_league_matches_played, functions.col(english_league_matches_played).cast(DataTypes.IntegerType))
                .withColumn(minutes_played, functions.col(minutes_played).cast(DataTypes.DoubleType))
                .withColumn(assists, functions.col(assists).cast(DataTypes.IntegerType))
                .withColumn(tackles, functions.col(tackles).cast(DataTypes.IntegerType))
                .withColumn(age, functions.col(age).cast(DataTypes.IntegerType));
        return schemaDS;
    }
}
