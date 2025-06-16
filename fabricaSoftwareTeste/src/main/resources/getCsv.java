import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class getCsv {
    public static void main(String[] args) {
        String arquivoCSV = "C:\\Users\\windows_matheus\\Desktop\\fabricaSoftware\\csvFiles\\csvTest.csv";
        String linha = "";
        String separador = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(arquivoCSV))) {

            System.out.println("Conteúdo do arquivo CSV:");
            System.out.println("-----------------------");

            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(separador);

                for (String dado : dados) {
                    System.out.print(dado + "\t");
                }
                System.out.println();
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }
}