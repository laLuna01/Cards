package org.example.repositories;

import java.sql.Connection;
import java.sql.Statement;
import org.example.infrastructure.DatabaseConfig;

public class SetRepository {

    public SetRepository() {
        Initialize();
    }

    private void Initialize() {
        String createTableSQL =
                "CREATE TABLE CP_SET (" +
                        "SET_ID NUMBER PRIMARY KEY," +
                        "SET_NAME VARCHAR2(40) NOT NULL," +
                        "QUANTITY NUMBER NOT NULL," +
                        "RELEASE_PATCH VARCHAR2(20) NOT NULL," +
                        "RELEASE_DATE DATE NOT NULL" +
                        ")";

        try (Connection conn = new DatabaseConfig().getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(createTableSQL);
            System.out.println("Tabela 'CP_SET' criada com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao criar a tabela: " + e.getMessage());
        }
    }
}
