package Utility;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import AutomationExercise.AccountInfo;

public class JsonReader {

    /**
     * Reads account data from JSON file and returns List of AccountInfo objects
     * @param filePath - Path to TestData.json file
     * @return List of AccountInfo objects
     */
    public static List<AccountInfo> getAccountData(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        
        try {
            // Check if file exists
            File file = new File(filePath);
            if (!file.exists()) {
                System.err.println("TestData.json file not found at path: " + filePath);
                return null;
            }
            
            // Read JSON array and convert to AccountInfo array
            AccountInfo[] accounts = mapper.readValue(file, AccountInfo[].class);
            
            // Verify data was read
            if (accounts == null || accounts.length == 0) {
                System.err.println("No test data found in JSON file");
                return null;
            }
            
            return Arrays.asList(accounts);
            
        } catch (Exception e) {
            System.err.println("Error reading TestData.json: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }
    
    /**
     * Reads specific account data by index
     * @param filePath - Path to TestData.json file
     * @param index - Index of account to retrieve
     * @return AccountInfo object or null if not found
     */
    public static AccountInfo getAccountDataByIndex(String filePath, int index) {
        List<AccountInfo> accounts = getAccountData(filePath);
        
        if (accounts != null && index >= 0 && index < accounts.size()) {
            return accounts.get(index);
        }
        
        System.err.println("Invalid index: " + index);
        return null;
    }
}
