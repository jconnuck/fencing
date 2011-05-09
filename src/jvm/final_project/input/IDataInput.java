package final_project.input;

import java.util.*;
import final_project.model.store.*;
import java.io.*;

public interface IDataInput {
    ITournamentInfo getTournamentInfo(String fileName);
    ITournamentInfo getTournamentInfo(File file);

    ITournamentInfo updateTournamentInfo(IDataStore dataStore, String fileName);
    ITournamentInfo updateTournamentInfo(IDataStore dataStore, File file);
}