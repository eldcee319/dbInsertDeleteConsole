public class Main {
    // This will create Database music.db with reusable insert and delete method using sqlite
    // used insertSongs(titleName,AlbumId) in main method to insert songs in the database


    public static void main(String[] args) {
        MusicDB musicDB = new MusicDB();
        if(!musicDB.open()) {
            System.out.println("Coudn't connect to datasource database");
        }

        //Add artists here
        musicDB.insertArtists("Utada Hikaru");
        musicDB.insertArtists("Ohayou Keno");
        musicDB.insertArtists("Samurai Champoo");

        //Add albums here
        musicDB.insertAlbums("SamChampooAlbum",3);
        musicDB.insertAlbums("UtadaAlbum",1);
        musicDB.insertAlbums("HxHAlbum",2);

        //Add songs here
        musicDB.insertSongs("First Love",2);
        musicDB.insertSongs("Hiki no bin", 1);
        musicDB.insertSongs("Hxh",3);
        musicDB.insertSongs("Secret",2);
        musicDB.insertSongs("Requiem",2);

        musicDB.delete("artists","name", "Utada Hikaru");

        //Close connection and statement
        musicDB.close();

    }
}
