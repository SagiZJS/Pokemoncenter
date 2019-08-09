package database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Insert {

    private final String[][] data;

    private final StringBuilder query;

    private int count = 0;
    
    private boolean finished = false;

    public StringBuilder getQuery() {
        return query;
    }

    public Insert(String table, int rows, String... columns) {
        super();
        query = new StringBuilder();
        this.data = new String[rows][];
        query.append(String.format("insert into %s (%s) values ", table, String.join(", ", columns)));
    }

    public void setData(String... data) {
        if (count < data.length) {
            this.data[count++] = data;
        } else {
            throw new IllegalArgumentException("too many arguments");
        }
    }

    public void setOneColumnData(String[] data) {
        for (int i = 0; i < data.length; i++) {
            this.data[i] = new String[] { data[i] };
        }
    }

    public void setData(String[][] data) {
        System.arraycopy(data, 0, this.data, 0, data.length);
    }

    public void finishQuery() {
        if (!finished) {
            for (String[] dataRow : data) {
                query.append(String.format("(\"%s\"), ", String.join(", ", dataRow)));
            }
            query.delete(query.length() - 2, query.length());
            query.append(';');
        }
        finished = true;
    }

    public void execute() {
        finishQuery();
        try (PreparedStatement insertStatement = ConnectionManager.getConnection().prepareStatement(query.toString())) {
            System.out.println(String.format("%d rows updated", insertStatement.executeUpdate()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
