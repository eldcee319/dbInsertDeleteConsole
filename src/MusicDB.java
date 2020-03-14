import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MusicDB {
    private static final String DB_NAME = "music01.db";
    private static final String CONN_STRING = "jdbc:sqlite:" + DB_NAME;

    private static final String TABLE_ARTISTS = "artists";
    private static final String COLUMN_ARTISTS_ID = "id";
    private static final String COLUMN_ARTISTS_NAME = "name";

    private static final String TABLE_SONGS = "songs";
    private static final String COLUMN_SONGS_ID = "id";
    private static final String COLUMN_SONGS_TITLE = "title";
    private static final String COLUMN_SONGS_ALBUM = "album";

    public static final String TABLE_ALBUMS = "albums";
    public static final String COLUMN_ALBUMS_ID = "id";
    public static final String COLUMN_ALBUMS_NAME = "name";
    public static final String COLUMN_ALBUMS_ARTIST = "artist";


    private Connection conn;
    private Statement statement;

    public boolean open() {
        try {
            // Connect to JDBC driver
            conn = DriverManager.getConnection(CONN_STRING);
            statement = conn.createStatement();

            // created tables here
            statement.execute("DROP TABLE IF EXISTS " + TABLE_ARTISTS);
            statement.execute("DROP TABLE IF EXISTS " + TABLE_SONGS);
            statement.execute("DROP TABLE IF EXISTS " + TABLE_ALBUMS);
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_ARTISTS + "(" + COLUMN_ARTISTS_ID + " INTEGER PRIMARY KEY," + COLUMN_ARTISTS_NAME + " STRING)");
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_SONGS + "(" + COLUMN_SONGS_ID + " INTEGER PRIMARY KEY," + COLUMN_SONGS_TITLE + " STRING, " + COLUMN_SONGS_ALBUM + " INTEGER)");
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_ALBUMS + "(" + COLUMN_ALBUMS_ID  + " INTEGER PRIMARY KEY," + COLUMN_ALBUMS_NAME + " STRING, " + COLUMN_ALBUMS_ARTIST + " INTEGER)");
            return true;
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void close() {
        try {
            if(conn!=null) {
                statement.close();
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void insertArtists(String name) {
        try {
            statement.execute("INSERT INTO " + TABLE_ARTISTS + "(name) " +  "VALUES (\"" + name + "\")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void insertAlbums(String name, int artistsId) {
        try {
            statement.execute("INSERT INTO " + TABLE_ALBUMS + "(name,artist)" + "VALUES (\"" + name + "\"," + artistsId + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void insertSongs(String title, int album) {
        try{
            statement.execute("INSERT INTO " + TABLE_SONGS + "(title,album) " + "VALUES(\"" + title + "\"," + album + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String tableName, String columnName, String columnValue) {
        try {
            statement.execute("DELETE FROM " + tableName + " WHERE " + columnName + "=" + "\"" + columnValue + "\"");
        } catch (SQLException e) {
            System.out.println("Error deleting table!");
            e.printStackTrace();
        }
    }
}
