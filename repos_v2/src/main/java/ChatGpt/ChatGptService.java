package ChatGpt;

import Database.DatabaseConnector;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ChatGptService {

    private static final String API_KEY = "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";
    private final DatabaseConnector databaseConnector;

    public ChatGptService(){
        this.databaseConnector = new DatabaseConnector();
    }

    public void getTextScoreFromChatGptAndUpdateDatabaseForPostIds(List<String> postIds){
        for (String postId : postIds){
            String response = connectToChatGptAndGetJsonString(postId);
            System.out.println("Response from chatgpt: ");
            System.out.println(response);
            TextScore textScore = parseResponseAndGetTextScoreFromChatGpt(response);
            textScore.setPostId(postId);
            databaseConnector.updateScoreOnPost(textScore);
        }
    }

    private String connectToChatGptAndGetJsonString(String postId){
        int responseCode = 0;
        StringBuilder content = new StringBuilder();
        try {
            // Prepare the request payload
            String requestPayload = getChatGptRequestLoad(postId);

            // Set up the connection
            URL url = new URL("https://api.openai.com/v1/chat/completions");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try (OutputStream outputStream = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8))) {
                writer.write(requestPayload);
                writer.flush();
            }

            // Get the response from the connection
            responseCode = connection.getResponseCode();
            BufferedReader reader;
            if (responseCode == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            }

            // Process the response
            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                content.append(inputLine);
            }
            reader.close();

            // Print the response
            System.out.println("Response Code: " + responseCode);
            System.out.println("Response Body: " + content);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (responseCode == HttpURLConnection.HTTP_OK){
            return content.toString();
        } else{
            return "Error";
        }
    }

    private String getChatGptRequestLoad(String postId) throws Exception{
        String text = new String();
        try{
            text = databaseConnector.getTextFromPostId(postId);
        } catch (Exception e){
            System.out.println("Getting text failed for postid: "+postId);
            throw e;
        }

        return "{\n" +
                "  \"model\": \"gpt-3.5-turbo\",\n" +
                "  \"messages\": [\n" +
                "    {\"role\": \"system\", \"content\": \"" + getSystemInstructionPromt() + "\"},\n" +
                "    {\"role\": \"user\", \"content\": \"" + text + "\"}\n" +
                "  ]\n" +
                "}";
    }

    /** ChatGpts orginal
     * String requestPayload = "{\n" +
     *                     "  \"model\": \"gpt-3.5-turbo\",\n" +
     *                     "  \"messages\": [\n" +
     *                     "    {\"role\": \"system\", \"content\": \"You are a helpful assistant.\"},\n" +
     *                     "    {\"role\": \"user\", \"content\": \"Who won the world series in 2020?\"}\n" +
     *                     "  ]\n" +
     *                     "}"
     */

    private String getSystemInstructionPromt(){
        return "I want you to help me evaluate texts related to stocks. "
        +"Please rate the information's relevance to the stock price on a scale of 0 to 10 (0 = no relevance, 10 = strong relevance) "
        +"and the sentiment (positive or negative) on a scale of -10 to 10 (-10 = very negative, 0 = neutral, 10 = strongly positive). "
        +"Your response should only contain these two numbers separated by a comma. Example response: '8,-5'. "
        +"It's very important that you only answer with these 2 comma-separated numbers. The numbers should be whole numbers and not fractions";
    }

    private TextScore parseResponseAndGetTextScoreFromChatGpt(String response){
        //TODO

        TextScore textScore = new TextScore();
        return textScore;
    }

}
