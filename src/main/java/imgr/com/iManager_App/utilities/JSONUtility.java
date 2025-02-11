package imgr.com.iManager_App.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONUtility
{
    public static String readPropertyValue(String json, String nodePath) throws JsonProcessingException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(json);

        JsonNode targetNode = rootNode.at(nodePath);
        if (!targetNode.isMissingNode())
        {
            return targetNode.asText();
        }
        else
        {
            // Handle case where node is not found
            return null;
        }
    }
}