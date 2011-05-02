package final_project.input;

import java.util.*;
import final_project.model.*;
import java.io.*;

public interface IDataInput {
    void updateDataStore(IDataStore dataStore, String fileName);
    void updateDataStore(IDataStore dataStore, File file);

    IDataStore initializeDataStore(String fileName);
    IDataStore initializeDataStore(File file);

    ITournamentInfo getTournamentInfo(String fileName);
    ITournamentInfo getTournamentInfo(File file);
}