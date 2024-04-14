package org.example.repositories;

import org.example.entities.Card;
import org.example.infrastructure.DatabaseConfig;
import java.sql.SQLException;
import java.sql.Statement;

public class CardRepository {
    private final DatabaseConfig databaseConfig = new DatabaseConfig();

    public CardRepository() {

    }

    public void Initialize() {
        String createTableSQL =
                "CREATE TABLE CP_CARD (" +
                        "CARD_ID NUMBER PRIMARY KEY," +
                        "CARD_NAME VARCHAR2(80) NOT NULL," +
                        "REGION VARCHAR2(80) NOT NULL," +
                        "TYPE VARCHAR2(80) NOT NULL," +
                        "RARITY VARCHAR2(80) NOT NULL," +
                        "MANA_COST NUMBER NOT NULL," +
                        "POWER NUMBER NOT NULL," +
                        "HEALTH NUMBER NOT NULL," +
                        "EFFECT VARCHAR2(80) NOT NULL," +
                        "DESCRIPTION VARCHAR2(300) NOT NULL," +
                        "LEVEL_UP VARCHAR2(300) NOT NULL," +
                        "FLAVOR VARCHAR2(500) NOT NULL," +
                        "SET_ID NUMBER REFERENCES CP_SET(SET_ID)," +
                        "LINK_IMAGE VARCHAR2(500) NOT NULL" +
                        ")";

        try (Statement stmt = databaseConfig.getConnection().createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("Tabela 'CP_CARD' criada com sucesso!");
            databaseConfig.closeConnection();
        } catch (Exception e) {
            System.out.println("Erro ao criar a tabela 'CP_CARD': " + e.getMessage());
            try {
                databaseConfig.closeConnection();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void Create(Card card) {
        String insertCardSQL = "INSERT INTO CP_CARD(CARD_ID, CARD_NAME, REGION, TYPE, RARITY, MANA_COST, POWER, HEALTH, EFFECT, DESCRIPTION, LEVEL_UP, FLAVOR, SET_ID, LINK_IMAGE) " +
                "VALUES(" + card.getId() +
                ", '" + card.getName() +
                "', '" + card.getRegion() +
                "', '" + card.getType() +
                "', '" + card.getRarity() +
                "', " + card.getManaCost() +
                ", " + card.getPower() +
                ", " + card.getHealth() +
                ", '" + card.getEffect() +
                "', '" + card.getDescription() +
                "', '" + card.getLevelUp() +
                "', '" + card.getFlavorText() +
                "', " + card.getSet().getId() +
                ", '" + card.getLinkImage() +
                "')";
        try(Statement stmt = databaseConfig.getConnection().createStatement()){
            stmt.execute(insertCardSQL);
            System.out.println("Card criada com sucesso");
            databaseConfig.closeConnection();
        }
        catch (SQLException e) {
            System.out.println("Erro ao criar Card: " + e.getMessage());
            try {
                databaseConfig.closeConnection();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void Delete(int delId) {
        String deleteSQL = "DELETE FROM CP_CARD WHERE CARD_ID = " + delId;
        try(Statement stmt = databaseConfig.getConnection().createStatement()){
            stmt.execute(deleteSQL);
            System.out.println("Card deletada com sucesso");
            databaseConfig.closeConnection();
        }
        catch (SQLException e) {
            System.out.println("Erro ao deletar Card: " + e.getMessage());
            try {
                databaseConfig.closeConnection();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
