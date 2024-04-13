package org.example.repositories;

import org.example.infrastructure.DatabaseConfig;

import java.sql.Connection;
import java.sql.Statement;

public class CardRepository {

    public CardRepository() {
        Initialize();
    }

    private void Initialize() {
        String createTableSQL =
                "CREATE TABLE CP_CARD (" +
                        "CARD_ID NUMBER PRIMARY KEY," +
                        "CARD_NAME VARCHAR2(40) NOT NULL," +
                        "REGION VARCHAR2(40) NOT NULL," +
                        "TYPE VARCHAR2(40) NOT NULL," +
                        "RARITY VARCHAR2(40) NOT NULL," +
                        "MANA_COST NUMBER NOT NULL," +
                        "POWER NUMBER NOT NULL," +
                        "HEALTH NUMBER NOT NULL," +
                        "EFFECT VARCHAR2(40) NOT NULL," +
                        "DESCRIPTION VARCHAR2(200) NOT NULL," +
                        "LEVEL_UP VARCHAR2(200) NOT NULL," +
                        "FLAVOR VARCHAR2(300) NOT NULL," +
                        "SET_ID NUMBER REFERENCES CP_SET(SET_ID)," +
                        "LINK_IMAGE VARCHAR2(300) NOT NULL" +
                        ")";

        try (Connection conn = new DatabaseConfig().getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute(createTableSQL);
            System.out.println("Tabela 'CP_CARD' criada com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao criar a tabela 'CP_CARD': " + e.getMessage());
        }
    }
}
