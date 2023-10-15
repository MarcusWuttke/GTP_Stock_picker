import ChatGpt.ChatGptService;
import Database.DatabaseConnector;
import Forum.PostHandler;
import Stocks.JsonModel.StockPriceHistory;
import Stocks.StockPriceService;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Main {

    private static String SCOUT_ID = "t3117108";
    private static String TELIA_ID = "t1556241";
    private static String RATOS_ID = "t1865773";
    private static String ERICSSON_ID = "t10230";
    private static String HM_ID = "t1974020";



    public static void main(String[] args) {
        PostHandler postHandler = new PostHandler();
        DatabaseConnector databaseConnector = new DatabaseConnector();
        Runner runner = new Runner();
        StockPriceService stockPriceService = new StockPriceService();
        ChatGptService chatGptService = new ChatGptService();

        //chatGptService.getTextScoreFromChatGptAndUpdateDatabaseForPostIds(List.of("85073508"));

        //System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        /**try{
            String text = databaseConnector.getTextFromPostId("85073508");
            System.out.println(text);
        } catch (Exception e){
            System.out.println(e);
        }

        String defaultEncoding = System.getProperty("file.encoding");
        System.out.println("Default Character Encoding: " + defaultEncoding);**/


        //runner.runProgram(postHandler);

        //postHandler.printSinglePost("72284649");

        //postHandler.printAllPostInForumThread(SCOUT);

        //databaseSaver.testSave();

        //postHandler.saveSinglePost("72284649", "SCOUT");

        //postHandler.saveAllPostsInForumThread(SCOUT_ID, "SCOUT");
        //postHandler.saveAllPostsInForumThread(RATOS_ID, "RATOS");
        List<String> failedPostIds = postHandler.saveAllPostsInForumThread(HM_ID, "HNNMY");
        System.out.println("All failed postids: ");
        failedPostIds.forEach(postId -> System.out.println(postId));


        //runner.runProgram(postHandler);


        /**String ticker = "HNNMY";
        String date = "2023-07-28";

        StockPriceHistory stockPriceHistory = stockPriceService.getStockPriceHistory(ticker);
        System.out.println("Showing values from Main");
        System.out.println();
        System.out.println("Price for: " + ticker);
        System.out.println("Date: " + date);
        System.out.println("Open: " + stockPriceHistory.getTimeSeries().getTimeSeriesData().get(date).getOpen());
        System.out.println("Close: " + stockPriceHistory.getTimeSeries().getTimeSeriesData().get(date).getClose());
        **/

    }
}
