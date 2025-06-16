import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

public class LeitorCSV {
    public static void main(String[] args) {
        String arquivoCSV = "C:\\Users\\windows_matheus\\Desktop\\fabricaSoftwareTeste\\src\\csvFiles\\csvTest.csv";
        String arquivoJSON = "C:\\Users\\windows_matheus\\Desktop\\fabricaSoftwareTeste\\src\\csvFiles\\dados.json";
        String linha = "";
        String separador = ",";

        JSONArray jsonArray = new JSONArray();

        try (BufferedReader br = new BufferedReader(new FileReader(arquivoCSV))) {
            String cabecalho = br.readLine();
            String[] colunas = cabecalho.split(separador);

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(separador);
                JSONObject jsonObject = new JSONObject();

                for (int i = 0; i < colunas.length; i++) {
                    String valor = (i < dados.length) ? dados[i] : "";
                    jsonObject.put(colunas[i].trim(), valor.trim());
                }

                jsonArray.put(jsonObject);
            }

            try (FileWriter file = new FileWriter(arquivoJSON)) {
                file.write(jsonArray.toString(4));
                System.out.println("Dados salvos com sucesso em " + arquivoJSON);
            }

        } catch (IOException e) {
            System.err.println("Erro ao processar os arquivos: " + e.getMessage());
        }
    }
}