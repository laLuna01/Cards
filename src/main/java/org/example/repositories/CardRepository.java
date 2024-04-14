package org.example.repositories;

import org.example.entities.Card;
import org.example.entities.Set;
import org.example.infrastructure.DatabaseConfig;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public Card Read(int readId) {
        Card card = new Card();
        String selectSQL = "SELECT * FROM CP_CARD WHERE CARD_ID = ";
        try(PreparedStatement pstmt = databaseConfig.getConnection().prepareStatement(selectSQL + readId)) {

            ResultSet rs = pstmt.executeQuery();
            rs.next();

            SetRepository setRepository = new SetRepository();
            Set set = setRepository.Read(rs.getInt("SET_ID"));

            card = new Card(rs.getInt("CARD_ID"), rs.getString("CARD_NAME"), rs.getString("REGION"), rs.getString("TYPE"), rs.getString("RARITY"), rs.getInt("MANA_COST"), rs.getInt("POWER"), rs.getInt("HEALTH"), rs.getString("EFFECT"), rs.getString("DESCRIPTION"), rs.getString("LEVEL_UP"), rs.getString("FLAVOR"), set, rs.getString("LINK_IMAGE"));

            databaseConfig.closeConnection();
        }
        catch (SQLException e) {
            System.out.println("Erro ao ler Card: " + e.getMessage());
            try {
                databaseConfig.closeConnection();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        return card;
    }

    public List<Card> ReadAll() {
        List<Card> cardList = new ArrayList<>();
        String selectAllSQL = "SELECT * FROM CP_CARD";
        try(PreparedStatement preparedStatement = databaseConfig.getConnection().prepareStatement(selectAllSQL)) {

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                SetRepository setRepository = new SetRepository();
                Set set = setRepository.Read(rs.getInt("SET_ID"));

                Card card = new Card(rs.getInt("CARD_ID"), rs.getString("CARD_NAME"), rs.getString("REGION"), rs.getString("TYPE"), rs.getString("RARITY"), rs.getInt("MANA_COST"), rs.getInt("POWER"), rs.getInt("HEALTH"), rs.getString("EFFECT"), rs.getString("DESCRIPTION"), rs.getString("LEVEL_UP"), rs.getString("FLAVOR"), set, rs.getString("LINK_IMAGE"));
                cardList.add(card);
            }

            databaseConfig.closeConnection();
        }
        catch (SQLException e) {
            System.out.println("Erro ao ler Cards: " + e.getMessage());
            try {
                databaseConfig.closeConnection();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        return cardList;
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
