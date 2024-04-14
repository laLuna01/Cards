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

            databaseConfig.closeConnection();
        }
        catch (SQLException e) {
            System.out.println("Erro ao ler Set: " + e.getMessage());
            try {
                databaseConfig.closeConnection();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        return set;
    }

    public List<Set> ReadAll() {
        List<Set> setList = new ArrayList<>();
        String selectAllSQL = "SELECT * FROM CP_SET";
        try(PreparedStatement pstmt = databaseConfig.getConnection().prepareStatement(selectAllSQL)) {

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Date date = rs.getDate("RELEASE_DATE");
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
                String formattedDate = simpleDateFormat.format(date);

                Set set = new Set(rs.getInt("SET_ID"), rs.getString("SET_NAME"), rs.getInt("QUANTITY"), rs.getString("RELEASE_PATCH"), formattedDate);
                setList.add(set);
            }

            databaseConfig.closeConnection();
        }
        catch (SQLException e) {
            System.out.println("Erro ao ler Sets: " + e.getMessage());
            try {
                databaseConfig.closeConnection();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
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
            System.out.println("Set atualizado com sucesso");
            databaseConfig.closeConnection();
        }
        catch (SQLException e) {
            System.out.println("Erro ao atualizar Set: " + e.getMessage());
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
