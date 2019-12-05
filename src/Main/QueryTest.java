package Main;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class QueryTest {
    @Test
    public void testInsertSongIntoPlaylist() throws Exception {
       String message = "Song is already in playlist";
        String actual = Query.insertSongIntoPlaylist(2, 4);

        Assert.assertEquals(message, actual);
    }
}