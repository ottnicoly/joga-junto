import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SendDataDb {

    private static final String URL = "jdbc:postgresql://localhost:5432/transacoes";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "postgres";
    private static final String ARQUIVO_CSV = "C:\\Users\\windows_matheus\\Desktop\\fabricaSoftwareTeste\\src\\csvFiles\\csvTest1.csv";
    private static final String SEPARADOR_CSV = ",";

    public static void main(String[] args) {
        Connection conexao = null;
        try {
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            conexao.setAutoCommit(false);

            String sql = "INSERT INTO transacoes(data_transacao, nome_cliente, valor) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conexao.prepareStatement(sql);

            int registrosImportados = processarArquivoCSV(pstmt);

            conexao.commit();
            System.out.printf("Importação concluída com sucesso! %d registros importados.%n", registrosImportados);

        } catch (SQLException e) {
            System.err.println("Erro na conexão com o banco:");
            e.printStackTrace();
            if (conexao != null) {
                try {
                    conexao.rollback();
                    System.err.println("Rollback executado.");
                } catch (SQLException ex) {
                    System.err.println("Erro ao fazer rollback:");
                    ex.printStackTrace();
                }
            }
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar conexão:");
                    e.printStackTrace();
                }
            }
        }
    }

    private static int processarArquivoCSV(PreparedStatement pstmt) {
        int contador = 0;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_CSV))) {
            String linha;
            boolean primeiraLinha = true;

            while ((linha = br.readLine()) != null) {
                if (primeiraLinha) {
                    primeiraLinha = false;
                    continue;
                }

                String[] dados = linha.split(SEPARADOR_CSV);
                if (dados.length >= 3) {
                    try {
                        Date data = dateFormat.parse(dados[0].trim());
                        String nome = dados[1].trim();
                        double valor = Double.parseDouble(dados[2].trim());

                        pstmt.setDate(1, new java.sql.Date(data.getTime()));
                        pstmt.setString(2, nome);
                        pstmt.setBigDecimal(3, java.math.BigDecimal.valueOf(valor));

                        pstmt.addBatch();
                        contador++;

                        if (contador % 100 == 0) {
                            pstmt.executeBatch();
                        }

                    } catch (ParseException e) {
                        System.err.printf("Erro na linha %d: Formato de data inválido - %s%n", contador+1, dados[0]);
                    } catch (NumberFormatException e) {
                        System.err.printf("Erro na linha %d: Valor numérico inválido - %s%n", contador+1, dados[2]);
                    }
                }
            }

            pstmt.executeBatch();

        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo CSV:");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erro ao executar batch:");
            e.printStackTrace();
        }

        return contador;
    }
}