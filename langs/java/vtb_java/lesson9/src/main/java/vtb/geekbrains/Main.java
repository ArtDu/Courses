package vtb.geekbrains;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement preparedStatement;

    public static void main(String[] args) throws Exception {
        try {
            connect();
            insertEx();
            selectEx();
            clearTableEx();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
    }

    private static void runMigrations() throws SQLException {
        Set<Class> classes = findAllClassesUsingClassLoader("vtb.geekbrains");
        Map<String, List<String>> tables = getTables(classes);
        for (Map.Entry<String, List<String>> entry : tables.entrySet()) {
            String key = entry.getKey();
            List<String> value = entry.getValue();
            createTable(key, value);
        }
    }

    public static void createTable(String tableName, List<String> fieldsTypeAndName) throws SQLException {
        StringBuilder sql = new StringBuilder("CREATE TABLE IF NOT EXISTS " + tableName + " ( ");
        for (int i = 0; i < fieldsTypeAndName.size(); i += 2) {
            String type = fieldsTypeAndName.get(i);
            String name = fieldsTypeAndName.get(i + 1);

            sql.append(name).append(" ").append(type);
            if (i == 0) {
                sql.append(" PRIMARY KEY AUTOINCREMENT ");
            }
            if (i != fieldsTypeAndName.size() - 2) {
                sql.append(" , ");
            }
        }
        sql.append(");");
        statement.executeUpdate(sql.toString());
    }

    private static Map<String, List<String>> getTables(Set<Class> classes) {
        Map<String, List<String>> tables = new HashMap<>();
        for (Class aClass :
                classes) {
            Table table = (Table) aClass.getAnnotation(Table.class);
            if (table != null) {
                String tableName = table.title();
                Field[] fields = aClass.getDeclaredFields();
                List<String> fieldsTypeAndName = new ArrayList<>();
                for (Field field :
                        fields) {
                    Column a = field.getAnnotation(Column.class);
                    if (a != null) {
                        fieldsTypeAndName.add(field.getType().getSimpleName());
                        fieldsTypeAndName.add(field.getName());
                    }
                }
                tables.put(tableName, fieldsTypeAndName);
            }
        }
        return tables;
    }

    private static Set<Class> findAllClassesUsingClassLoader(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
    }

    private static Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }

    private static void rollbackEx() throws SQLException {
        statement.executeUpdate("INSERT INTO students (name, score) VALUES ('Bob1', 80);");
        Savepoint sp1 = connection.setSavepoint();
        statement.executeUpdate("INSERT INTO students (name, score) VALUES ('Bob2', 80);");
        connection.rollback(sp1);
        statement.executeUpdate("INSERT INTO students (name, score) VALUES ('Bob3', 80);");
        connection.commit();
    }

    private static void batchEx() throws SQLException {
        connection.setAutoCommit(false);
        for (int i = 0; i < 10000; i++) {
            preparedStatement.setString(1, "Bob" + (i + 1));
            preparedStatement.setInt(2, 50);
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        connection.commit();
    }

    private static void transactionAndPreparedStatementEx() throws SQLException {
        connection.setAutoCommit(false);
        for (int i = 0; i < 10000; i++) {
            preparedStatement.setString(1, "Bob" + (i + 1));
            preparedStatement.setInt(2, 50);
            preparedStatement.executeUpdate();
        }
        connection.commit();
    }

    private static void dropTableEx() throws SQLException {
        statement.executeUpdate("DROP TABLE students");
    }

    private static void clearTableEx() throws SQLException {
        statement.executeUpdate("DELETE FROM students");
    }

    private static void deleteEx() throws SQLException {
        statement.executeUpdate("DELETE FROM students WHERE id = 3");
    }

    private static void updateEx() throws SQLException {
        statement.executeUpdate("UPDATE students SET score = 80 WHERE id = 1;");
    }

    private static void selectEx() {
        try (ResultSet rs = statement.executeQuery("SELECT * FROM students;")) {
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString("name") + " " + rs.getInt("score"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void insertEx() throws SQLException {
        statement.executeUpdate("INSERT INTO students (name, score) VALUES ('Bob3', 100);");
    }

    public static void connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:main.db");
            statement = connection.createStatement();
            runMigrations();
            preparedStatement = connection.prepareStatement("INSERT INTO students (name, score) VALUES (?, ?);");
        } catch (ClassNotFoundException | SQLException e) {
            throw new SQLException("Unable to connect");
        }
    }

    public static void disconnect() {
        try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
