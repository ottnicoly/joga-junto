import java.io.*;
import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class CsvToJson {
    public static void main(String[] args) {
        String csvFile = "src/csvFiles/csvTest.csv";
        String jsonFile = "src/csvFiles/output.json";

        List<Map<String, String>> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            String[] headers = null;

            if ((line = br.readLine()) != null) {
                headers = line.split(";");
            }

            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                Map<String, String> record = new LinkedHashMap<>();

                for (int i = 0; i < headers.length && i < values.length; i++) {
                    record.put(headers[i], values[i]);
                }

                String valor = record.get("Valor");
                if ("120,00".equals(valor) || "180,00".equals(valor)) {
                    records.add(record);
                }
            }

            JSONArray jsonArray = new JSONArray(records);

            try (FileWriter file = new FileWriter(jsonFile)) {
                file.write(jsonArray.toString(4));
                System.out.println("Arquivo JSON gerado com sucesso!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}