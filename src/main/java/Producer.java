import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import static ConstantValues.CSVConstantValues.csvFilePath;
import static ConstantValues.KafkaConstantValues.*;


public class Producer {
    public static void main(String[] args) throws InterruptedException {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", server);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            int j = 1;
            while ((line = br.readLine()) != null) {
                    producer.send(new ProducerRecord<>(Topic, String.valueOf(j), line));
                    System.out.println(line);
                    j++;
                    Thread.sleep(1000);
                }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            producer.close();
        }
    }
}