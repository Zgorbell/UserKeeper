import org.junit.Assert;
import org.junit.Test;
import repository.IndexFile;

public class IndexFileTest {

    private static final String DEFAULT_PATH = "users/";
    private static final int INDEX1 = 1;
    private static final int INDEX2 = 2;

    private IndexFile indexFile;

    @Test
    public void test(){
        indexFile = new IndexFile(DEFAULT_PATH);
        indexFile.saveIndex(INDEX1);
        Assert.assertEquals( indexFile.getNextIndex(), INDEX1);
        indexFile.saveIndex(INDEX2);
        Assert.assertEquals( indexFile.getNextIndex(), INDEX2);
    }
}
