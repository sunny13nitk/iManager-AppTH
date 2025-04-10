package imgr.com.iManager_App.utilities;

import org.springframework.util.StringUtils;

public class StringsUtility
{
    public static String replaceURLwithParams(String baseString, String[] replStrings, String separator)
            throws Exception
    {
        String parsedUrl = null;
        if (StringUtils.hasText(baseString))
        {
            parsedUrl = new String();
            // Check the Occurences and REplacements Count
            int countOccurances = org.apache.commons.lang3.StringUtils.countMatches(baseString, separator);
            int countREpl = replStrings.length;

            // Substitute all occurences with 1st repl
            if (countOccurances > countREpl)
            {
                if (countREpl == 1)
                {
                    baseString = baseString.replaceAll(separator, replStrings[0]);
                    log.info("Base String post replacements: " + baseString);
                    return baseString;
                }
                else
                {
                    throw new Exception("Occurences to Replace - " + countOccurances + " in String - " + baseString
                            + " : " + " are more that replacement values : " + countREpl);
                }
            }
            else if (countOccurances < countREpl)
            {
                throw new Exception("Occurences to Replace - " + countOccurances + " in String - " + baseString + " : "
                        + " are less that replacement values : " + countREpl);

            }
            else // Occurences = Replacements
            {

                String[] allparts = baseString.split(separator);

                int i = 0;
                for (i = 0; i < (allparts.length); i++)
                {
                    if (i < replStrings.length)
                    {
                        parsedUrl = parsedUrl + allparts[i] + replStrings[i];
                    }
                    else
                    {

                        parsedUrl += allparts[i]; // Final Part

                    }

                }

                if (i <= (allparts.length - 1))
                {
                    parsedUrl += allparts[i]; // Final Part
                }
            }

        }

        return parsedUrl;
    }
}
