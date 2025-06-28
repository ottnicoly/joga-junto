import java.io.*;
import java.sql.*;
import java.util.*;
import org.json.*;

public class CsvReader {

    public static void main(String[] args) {
        convertCsvToJson();
        insertJsonToPostgres();
    }

    private static void convertCsvToJson() {
        String csvFile = "src/csvFiles/csvTest.csv";
        String jsonFile = "src/csvFiles/output.json";

        List<Map<String, String>> records = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            String[] headers = br.readLine().split(";");

            while ((line = br.readLine()) != null) {
                String[] values = line.split(";", -1);
                Map<String, String> record = new LinkedHashMap<>();

                for (int i = 0; i < headers.length; i++) {
                    String value = (i < values.length) ? values[i] : "";
                    record.put(headers[i], value);
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
            System.err.println("Erro na conversão CSV-JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void insertJsonToPostgres() {
        String jsonFile = "src/csvFiles/output.json";
        String url = "jdbc:postgresql://localhost:5432/jogajunto";
        String user = "postgres";
        String password = "postgres";

        try (FileReader reader = new FileReader(jsonFile)) {
            JSONArray jsonArray = new JSONArray(new JSONTokener(reader));

            try (Connection conn = DriverManager.getConnection(url, user, password)) {
                String sql = "INSERT INTO pagamentos (data_pagamento, responsavel_pagamento, valor) VALUES (?, ?, ?)";

                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);

                        String data = obj.optString("Data Lançamento", "");
                        String responsavel = obj.optString("Descrição", "");
                        String valorStr = obj.optString("Valor", "");

                        if (data.isEmpty() || responsavel.isEmpty() || valorStr.isEmpty()) {
                            System.out.println("Registro inválido ignorado: " + obj);
                            continue;
                        }

                        try {
                            double valor = Double.parseDouble(valorStr.replace(",", "."));

                            pstmt.setString(1, data);
                            pstmt.setString(2, responsavel);
                            pstmt.setDouble(3, valor);

                            pstmt.executeUpdate();
                        } catch (NumberFormatException e) {
                            System.err.println("Valor numérico inválido: " + valorStr);
                        }
                    }
                    System.out.println("Dados inseridos no PostgreSQL com sucesso!");
                }
            }
        } catch (Exception e) {
            System.err.println("Erro na inserção no PostgreSQL: " + e.getMessage());
            e.printStackTrace();
        }
    }
}