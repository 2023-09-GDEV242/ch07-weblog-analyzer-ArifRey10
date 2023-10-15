
 
/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author Arif Reyhan.
 * @version 10/15/2023
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;
    private LogfileReader logfileReader;
    /**
     * Create an object to analyze hourly web accesses.
     */
     public int busiestHour() {
        int maxCount = 0;
        int busiestHour = -1;

        for (int hour = 0; hour < hourCounts.length; hour++) {
            if (hourCounts[hour] > maxCount) {
                maxCount = hourCounts[hour];
                busiestHour = hour;
            }
        }

        return busiestHour;
    }
     public int quietestHour() {
        int minCount = Integer.MAX_VALUE;  // Initialize to a very large value
        int quietestHour = -1;  // Initialize to an invalid hour

        for (int hour = 0; hour < hourCounts.length; hour++) {
            if (hourCounts[hour] < minCount) {
                minCount = hourCounts[hour];
                quietestHour = hour;
            }
        }

        return quietestHour;
    }
     public int busiestTwoHour() {
        int maxCount = 0;
        int busiestStartHour = -1;

        for (int startHour = 0; startHour < 24; startHour++) {
            // Calculate the total count for the current two-hour window
            int totalCount = hourCounts[startHour] + hourCounts[(startHour + 1) % 24];
            
            // Check if this is the new busiest two-hour window
            if (totalCount > maxCount) {
                maxCount = totalCount;
                busiestStartHour = startHour;
            }
        }

        return busiestStartHour;
    }
    public LogAnalyzer()
    {  
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
    }
  

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
}
