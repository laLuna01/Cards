package org.example.repositories;

import java.sql.SQLException;
import java.sql.Statement;
import org.example.entities.Set;
import org.example.infrastructure.DatabaseConfig;

public class SetRepository {
    private final DatabaseConfig databaseConfig = new DatabaseConfig();

    public SetRepository() {

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
            System.out.println("Tabela 'CP_SET' criada com sucesso!");
            databaseConfig.closeConnection();
        } catch (Exception e) {
            System.out.println("Erro ao criar a tabela 'CP_SET': " + e.getMessage());
            try {
                databaseConfig.closeConnection();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
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
            System.out.println("Set criado com sucesso");
            databaseConfig.closeConnection();
        }
        catch (SQLException e) {
            System.out.println("Erro ao criar Set: " + e.getMessage());
            try {
                databaseConfig.closeConnection();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void Delete(int delId) {
        String deleteSQL = "DELETE FROM CP_SET WHERE SET_ID = " + delId;
        try(Statement stmt = databaseConfig.getConnection().createStatement()){
            stmt.execute(deleteSQL);
            System.out.println("Set deletado com sucesso");
            databaseConfig.closeConnection();
        }
        catch (SQLException e) {
            System.out.println("Erro ao deletar Set: " + e.getMessage());
            try {
                databaseConfig.closeConnection();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
