package Utility;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import AutomationExercise.AccountInfo;

public class JsonReader {

    public static List<AccountInfo> getAccountData(String filePath) {

        ObjectMapper mapper = new ObjectMapper();

        try {
            return Arrays.asList(mapper.readValue(new File(filePath), AccountInfo[].class)
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
