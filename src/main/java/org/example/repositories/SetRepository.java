package org.example.repositories;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.example.entities.Set;
import org.example.infrastructure.DatabaseConfig;

public class SetRepository implements _Loggable {
    private final DatabaseConfig databaseConfig = new DatabaseConfig();

    public SetRepository() {
        Initialize();
    }

    public void Initialize() {
        String createTableSQL =
                "CREATE TABLE CP_SET (" +
                        "SET_ID NUMBER PRIMARY KEY," +
                        "SET_NAME VARCHAR2(40) NOT NULL," +
                        "QUANTITY NUMBER NOT NULL," +
                        "RELEASE_PATCH VARCHAR2(20) NOT NULL," +
                        "RELEASE_DATE DATE NOT NULL" +
                        ")";

        try (Statement stmt = databaseConfig.getConnection().createStatement()) {
            stmt.execute(createTableSQL);
            logInfo("Tabela CP_SET criada com sucesso");
            databaseConfig.closeConnection();
        } catch (Exception e) {
            logError("Erro ao criar a tabela CP_SET: " + e.getMessage());
            databaseConfig.closeConnection();
        }
    }

    public void Create(Set set) {
        String insertSetSQL = "INSERT INTO CP_SET(SET_ID, SET_NAME, QUANTITY, RELEASE_PATCH, RELEASE_DATE) " +
                "VALUES(" + set.getId() +
                ", '" + set.getName() +
                "', " + set.getQuantity() +
                ", '" + set.getPatch() +
                "', '" + set.getReleaseDate() +
                "')";
        try(Statement stmt = databaseConfig.getConnection().createStatement()){
            stmt.execute(insertSetSQL);
            logInfo("Set " + set + " criado com sucesso");
            databaseConfig.closeConnection();
        }
        catch (SQLException e) {
            logError("Erro ao criar Set " + set + ": " + e.getMessage());
            databaseConfig.closeConnection();
        }
    }

    public Set Read(int readId) {
        Set set = new Set();
        String selectSQL = "SELECT * FROM CP_SET WHERE SET_ID = ";
        try(PreparedStatement pstmt = databaseConfig.getConnection().prepareStatement(selectSQL + readId)) {

            ResultSet rs = pstmt.executeQuery();
            rs.next();

            Date date = rs.getDate("RELEASE_DATE");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
            String formattedDate = simpleDateFormat.format(date);

            set = new Set(rs.getInt("SET_ID"), rs.getString("SET_NAME"), rs.getInt("QUANTITY"), rs.getString("RELEASE_PATCH"), formattedDate);

            logInfo("Leitura de Set realizada com sucesso: " + set);
            databaseConfig.closeConnection();
        }
        catch (SQLException e) {
            logError("Erro ao ler Set com id " + readId + ": " + e.getMessage());
            databaseConfig.closeConnection();
        }
        return set;
    }

    public List<Set> ReadAll() {
        List<Set> setList = new ArrayList<>();
        String selectAllSQL = "SELECT * FROM CP_SET";
        try(PreparedStatement pstmt = databaseConfig.getConnection().prepareStatement(selectAllSQL)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Date date = rs.getDate("RELEASE_DATE");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
                String formattedDate = simpleDateFormat.format(date);

                Set set = new Set(rs.getInt("SET_ID"), rs.getString("SET_NAME"), rs.getInt("QUANTITY"), rs.getString("RELEASE_PATCH"), formattedDate);
                setList.add(set);
            }
            logInfo("Leitura de Sets realizada com sucesso");
            databaseConfig.closeConnection();
        }
        catch (SQLException e) {
            logError("Erro ao ler Sets: " + e.getMessage());
            databaseConfig.closeConnection();
        }
        return setList;
    }

    public void Update(Set set) {
        String updateSQL = "UPDATE CP_SET SET " +
                "SET_ID = " + set.getId() +
                ", SET_NAME = '" + set.getName() +
                "', QUANTITY = " + set.getQuantity() +
                ", RELEASE_PATCH = '" + set.getPatch() +
                "', RELEASE_DATE = '" + set.getReleaseDate() +
                "' WHERE SET_ID = " + set.getId();
        try(Statement stmt = databaseConfig.getConnection().createStatement()){
            stmt.execute(updateSQL);
            logInfo("Set com id " + set.getId() + " atualizado com sucesso");
            databaseConfig.closeConnection();
        }
        catch (SQLException e) {
            logError("Erro ao atualizar Set com id " + set.getId() + ": " + e.getMessage());
            databaseConfig.closeConnection();
        }
    }

    public void Delete(int delId) {
        String deleteSQL = "DELETE FROM CP_SET WHERE SET_ID = " + delId;
        try(Statement stmt = databaseConfig.getConnection().createStatement()){
            stmt.execute(deleteSQL);
            logInfo("Set com id " + delId + " deletado com sucesso");
            databaseConfig.closeConnection();
        }
        catch (SQLException e) {
            logError("Erro ao deletar Set com id " + delId + ": " + e.getMessage());
            databaseConfig.closeConnection();
        }
    }

}
