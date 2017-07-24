package project.neutrino.library.repository;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConnectionManager {

    private static ConnectionManager manager;

    DBI dbi;

    private ConnectionManager() {
    }

    public DBI getDbi() {
        return dbi;
    }

    public static ConnectionManager getManager() {
        if (manager == null) {
            createAndConnect(null);
        }

        return manager;
    }

    public static void createAndConnect(String url) {
        if (manager == null) {
            manager = new ConnectionManager();

            if (url == null) {
                String defaultUrl = manager.createDefaultDbUrl();
                manager.connect(defaultUrl);
            } else {
                manager.connect(url);
            }
        }
    }

    public void executeSqlScript(String path) {
        String query = readSqlScript(path);
        try (Handle handle = dbi.open()) {
            handle.execute(query);
        }
    }

    private String readSqlScript(String path) {
        InputStream initScript = this.getClass()
                .getClassLoader()
                .getResourceAsStream(path);

        try (BufferedReader initScriptReader = new BufferedReader(new InputStreamReader(initScript))) {
            StringBuilder queryBuilder = new StringBuilder();

            while (initScriptReader.ready()) {
                String query = initScriptReader.readLine();
                queryBuilder.append(query);
            }

            initScript.close();
            return queryBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String createDefaultDbUrl() {
        String homeDir = System.getProperty("user.home");
        return "jdbc:h2:file:" + homeDir + "/.library/db";
    }

    private void connect(String url) {
        //try {
        //Class.forName("org.sqlite.JDBC");

        dbi = new DBI(url);
        executeSqlScript("db/initDB.sql");

        /*} catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/
    }

    /*private boolean isTablesExist() {
        Handle handle = null;

        try {
            handle = dbi.open();
            String name = handle.createQuery(
                    "SELECT name FROM sqlite_master WHERE name ='books' and type='table';")
                    .map(StringColumnMapper.INSTANCE)
                    .first();

            return name != null;

        } finally {
            if (handle != null)
                handle.close();
        }
    }*/
}
