package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class Query {

    public static void main(String[] args) {
        Connection connection = ConnectionManager.getConnection();
        String query = "select typename from type;";
        String insert = "insert into typeeffect (attacker, attackee, effect) values (?,?,?)";
        try (PreparedStatement insertTypeEffect = connection.prepareStatement(insert);
                PreparedStatement qeuryType = connection.prepareStatement(query)) {
            
            ResultSet typesResultSet = qeuryType.executeQuery();
            String[] types = new String[18];
            int i = 0;
            while (typesResultSet.next()) {
                types[i++] = typesResultSet.getString(1);
            }
            for (String type1 : types) {
                for (String type2 : types) {
                    insertTypeEffect.setString(1, type1);
                    insertTypeEffect.setString(2, type2);
                    insertTypeEffect.setFloat(3, 1);
                    insertTypeEffect.executeUpdate();
                }
            }
            System.out.println(Arrays.toString(types));
            
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        
    }

}
