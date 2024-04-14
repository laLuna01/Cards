package org.example.repositories;

import org.example.entities.Card;
import org.example.entities.Set;
import org.example.infrastructure.DatabaseConfig;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CardRepository implements _Loggable {
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
            logInfo("Tabela CP_CARD criada com sucesso");
            databaseConfig.closeConnection();
        } catch (Exception e) {
            logError("Erro ao criar a tabela CP_CARD: " + e.getMessage());
            databaseConfig.closeConnection();
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
            logInfo("Card " + card + " criada com sucesso");
            databaseConfig.closeConnection();
        }
        catch (SQLException e) {
            logError("Erro ao criar Card " + card + ": " + e.getMessage());
            databaseConfig.closeConnection();
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

            logInfo("Leitura de Card realizada com sucesso: " + card);
            databaseConfig.closeConnection();
        }
        catch (SQLException e) {
            logError("Erro ao ler Card com id " + readId + ": " + e.getMessage());
            databaseConfig.closeConnection();
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
            logInfo("Leitura de Cards realizada com sucesso");
            databaseConfig.closeConnection();
        }
        catch (SQLException e) {
            logError("Erro ao ler Cards: " + e.getMessage());
            databaseConfig.closeConnection();
        }
        return cardList;
    }

    public void Update(Card card) {
        String updateSQL = "UPDATE CP_CARD SET " +
                "CARD_ID = " + card.getId() +
                ", CARD_NAME = '" + card.getName() +
                "', REGION = '" + card.getRegion() +
                "', TYPE = '" + card.getType() +
                "', RARITY = '" + card.getRarity() +
                "', MANA_COST = " + card.getManaCost() +
                ", POWER = " + card.getPower() +
                ", HEALTH = " + card.getHealth() +
                ", EFFECT = '" + card.getEffect() +
                "', DESCRIPTION = '" + card.getDescription() +
                "', LEVEL_UP = '" + card.getLevelUp() +
                "', FLAVOR = '" + card.getFlavorText() +
                "', SET_ID = " + card.getSet().getId() +
                ", LINK_IMAGE = '" + card.getLinkImage() +
                "' WHERE CARD_ID = " + card.getId();
        try(Statement stmt = databaseConfig.getConnection().createStatement()){
            stmt.execute(updateSQL);
            logInfo("Card com id " + card.getId() + " atualizada com sucesso");
            databaseConfig.closeConnection();
        }
        catch (SQLException e) {
            logError("Erro ao atualizar Card com id " + card.getId() + ": " + e.getMessage());
            databaseConfig.closeConnection();
        }
    }

    public void Delete(int delId) {
        String deleteSQL = "DELETE FROM CP_CARD WHERE CARD_ID = " + delId;
        try(Statement stmt = databaseConfig.getConnection().createStatement()){
            stmt.execute(deleteSQL);
            logInfo("Card com id " + delId + " deletada com sucesso");
            databaseConfig.closeConnection();
        }
        catch (SQLException e) {
            logError("Erro ao deletar Card com id " + delId + ": " + e.getMessage());
            databaseConfig.closeConnection();
        }
    }

}
