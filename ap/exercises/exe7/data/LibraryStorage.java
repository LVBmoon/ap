package ap.exercises.exe7.data;

import ap.exercises.exe7.core.Library;
import java.io.IOException;

public abstract class LibraryStorage {
    protected String fileAddress;

    public LibraryStorage(String fileAddress) {
        this.fileAddress = fileAddress;
    }

    public abstract void save(Library library) throws IOException;

    public abstract Library load() throws IOException;
}
