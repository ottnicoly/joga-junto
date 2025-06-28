import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JsonToPostgres {
    public static void main(String[] args) {
        String jsonFile = "src/csvFiles/output.json";

        String url = "jdbc:postgresql://localhost:5432/jogajunto";
        String user = "postgres";
        String password = "postgres";

        try {
            FileReader reader = new FileReader(jsonFile);
            JSONTokener tokener = new JSONTokener(reader);
            JSONArray jsonArray = new JSONArray(tokener);

            Connection conn = DriverManager.getConnection(url, user, password);

            String sql = "INSERT INTO pagamentos (data_pagamento, responsavel_pagamento, valor) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);

                String dataLancamento = obj.getString("Data Lançamento");
                String descricao = obj.getString("Descrição");
                String valorStr = obj.getString("Valor");

                double valor = Double.parseDouble(valorStr.replace(",", "."));

                pstmt.setString(1, dataLancamento);
                pstmt.setString(2, descricao);
                pstmt.setDouble(3, valor);

                pstmt.executeUpdate();
            }

            pstmt.close();
            conn.close();
            reader.close();

            System.out.println("Dados inseridos no PostgreSQL com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}