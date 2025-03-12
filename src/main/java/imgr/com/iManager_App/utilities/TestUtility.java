package imgr.com.iManager_App.utilities;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import imgr.com.iManager_App.ui.pojos.TY_PF;
import imgr.com.iManager_App.ui.pojos.TY_WLDB;

public class TestUtility
{
    public static String getWLJSON()
    {
        String jsonString = "[\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"ABB\",\r\n" + //
                "\t\t\"rating\": \"Accumulate\",\r\n" + //
                "\t\t\"avgReturns\": 19.1,\r\n" + //
                "\t\t\"mcapCr\": 115366.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 4,\r\n" + //
                "\t\t\"dailyPL\": \"-2.39%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 4491.0,\r\n" + //
                "\t\t\"cmp\": 5444.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 18.0,\r\n" + //
                "\t\t\"currpe\": 68.4,\r\n" + //
                "\t\t\"pe5y\": 103.0,\r\n" + //
                "\t\t\"mcap2sales\": 9.96,\r\n" + //
                "\t\t\"mcap2sales3y\": 9.4,\r\n" + //
                "\t\t\"epsttm\": 79.4,\r\n" + //
                "\t\t\"revgr3y\": 21.5,\r\n" + //
                "\t\t\"patgr3y\": 92.7,\r\n" + //
                "\t\t\"ttmpatgr\": 39.6,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 22.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 60.0,\r\n" + //
                "\t\t\"baseCaseTP\": 10554.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 18.0,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 25.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 65.0,\r\n" + //
                "\t\t\"bullCaseTP\": 12600.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 23.3,\r\n" + //
                "\t\t\"bullwtRatio\": 0.9,\r\n" + //
                "\t\t\"peg\": 3.42,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"ACE\",\r\n" + //
                "\t\t\"rating\": \"Sell\",\r\n" + //
                "\t\t\"avgReturns\": 10.0,\r\n" + //
                "\t\t\"mcapCr\": 14445.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 5,\r\n" + //
                "\t\t\"dailyPL\": \"-2.54%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 543.0,\r\n" + //
                "\t\t\"cmp\": 1213.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 55.0,\r\n" + //
                "\t\t\"currpe\": 52.0,\r\n" + //
                "\t\t\"pe5y\": 29.5,\r\n" + //
                "\t\t\"mcap2sales\": 4.51,\r\n" + //
                "\t\t\"mcap2sales3y\": 3.7,\r\n" + //
                "\t\t\"epsttm\": 23.3,\r\n" + //
                "\t\t\"revgr3y\": 33.4,\r\n" + //
                "\t\t\"patgr3y\": 63.0,\r\n" + //
                "\t\t\"ttmpatgr\": 0.2,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 22.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 25.0,\r\n" + //
                "\t\t\"baseCaseTP\": 1574.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 5.3,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 25.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 28.0,\r\n" + //
                "\t\t\"bullCaseTP\": 1991.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 10.4,\r\n" + //
                "\t\t\"bullwtRatio\": 0.9,\r\n" + //
                "\t\t\"peg\": 1.22,\r\n" + //
                "\t\t\"exitKeyVals\": [\r\n" + //
                "\t\t\t{\r\n" + //
                "\t\t\t\t\"key\": \"Expected Rate of Returns \",\r\n" + //
                "\t\t\t\t\"value\": 10.0\r\n" + //
                "\t\t\t}\r\n" + //
                "\t\t]\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"ANANDRATHI\",\r\n" + //
                "\t\t\"rating\": \"Hold\",\r\n" + //
                "\t\t\"avgReturns\": 13.1,\r\n" + //
                "\t\t\"mcapCr\": 15282.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 5,\r\n" + //
                "\t\t\"dailyPL\": \"-1.51%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 2217.0,\r\n" + //
                "\t\t\"cmp\": 3649.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 39.0,\r\n" + //
                "\t\t\"currpe\": 54.0,\r\n" + //
                "\t\t\"pe5y\": 38.6,\r\n" + //
                "\t\t\"mcap2sales\": 17.0,\r\n" + //
                "\t\t\"mcap2sales3y\": 10.4,\r\n" + //
                "\t\t\"epsttm\": 67.6,\r\n" + //
                "\t\t\"revgr3y\": 40.2,\r\n" + //
                "\t\t\"patgr3y\": 70.9,\r\n" + //
                "\t\t\"ttmpatgr\": 34.1,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 22.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 35.0,\r\n" + //
                "\t\t\"baseCaseTP\": 6395.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 11.9,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 25.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 40.0,\r\n" + //
                "\t\t\"bullCaseTP\": 8252.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 17.7,\r\n" + //
                "\t\t\"bullwtRatio\": 0.9,\r\n" + //
                "\t\t\"peg\": 1.74,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"ANGELONE\",\r\n" + //
                "\t\t\"rating\": \"Hold\",\r\n" + //
                "\t\t\"avgReturns\": 16.7,\r\n" + //
                "\t\t\"mcapCr\": 20364.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 5,\r\n" + //
                "\t\t\"dailyPL\": \"-3.65%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 1609.0,\r\n" + //
                "\t\t\"cmp\": 2256.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 29.0,\r\n" + //
                "\t\t\"currpe\": 15.2,\r\n" + //
                "\t\t\"pe5y\": 19.6,\r\n" + //
                "\t\t\"mcap2sales\": 3.68,\r\n" + //
                "\t\t\"mcap2sales3y\": 4.9,\r\n" + //
                "\t\t\"epsttm\": 151.0,\r\n" + //
                "\t\t\"revgr3y\": 49.1,\r\n" + //
                "\t\t\"patgr3y\": 55.5,\r\n" + //
                "\t\t\"ttmpatgr\": 27.1,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 17.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 14.0,\r\n" + //
                "\t\t\"baseCaseTP\": 4635.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 15.5,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 20.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 16.0,\r\n" + //
                "\t\t\"bullCaseTP\": 6012.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 21.7,\r\n" + //
                "\t\t\"bullwtRatio\": 0.8,\r\n" + //
                "\t\t\"peg\": 0.22,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"ARROWGREEN\",\r\n" + //
                "\t\t\"rating\": \"StrongBuy\",\r\n" + //
                "\t\t\"avgReturns\": 30.4,\r\n" + //
                "\t\t\"mcapCr\": 946.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 4,\r\n" + //
                "\t\t\"dailyPL\": \"-8.42%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 748.0,\r\n" + //
                "\t\t\"cmp\": 627.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": -19.0,\r\n" + //
                "\t\t\"currpe\": 15.4,\r\n" + //
                "\t\t\"pe5y\": 24.0,\r\n" + //
                "\t\t\"mcap2sales\": 4.0,\r\n" + //
                "\t\t\"mcap2sales3y\": 5.6,\r\n" + //
                "\t\t\"epsttm\": 41.0,\r\n" + //
                "\t\t\"revgr3y\": 41.5,\r\n" + //
                "\t\t\"patgr3y\": 69.7,\r\n" + //
                "\t\t\"ttmpatgr\": 92.4,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 20.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 20.0,\r\n" + //
                "\t\t\"baseCaseTP\": 1700.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 28.3,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 24.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 24.0,\r\n" + //
                "\t\t\"bullCaseTP\": 2326.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 38.8,\r\n" + //
                "\t\t\"bullwtRatio\": 0.9,\r\n" + //
                "\t\t\"peg\": 0.51,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"AURIONPRO\",\r\n" + //
                "\t\t\"rating\": \"Accumulate\",\r\n" + //
                "\t\t\"avgReturns\": 18.3,\r\n" + //
                "\t\t\"mcapCr\": 7901.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 5,\r\n" + //
                "\t\t\"dailyPL\": \"-6.08%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 1092.0,\r\n" + //
                "\t\t\"cmp\": 1431.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 24.0,\r\n" + //
                "\t\t\"currpe\": 45.4,\r\n" + //
                "\t\t\"pe5y\": 12.2,\r\n" + //
                "\t\t\"mcap2sales\": 7.23,\r\n" + //
                "\t\t\"mcap2sales3y\": 5.5,\r\n" + //
                "\t\t\"epsttm\": 32.1,\r\n" + //
                "\t\t\"revgr3y\": 33.4,\r\n" + //
                "\t\t\"patgr3y\": 51.9,\r\n" + //
                "\t\t\"ttmpatgr\": 36.9,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 27.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 30.0,\r\n" + //
                "\t\t\"baseCaseTP\": 3182.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 17.3,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 30.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 33.0,\r\n" + //
                "\t\t\"bullCaseTP\": 3933.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 22.4,\r\n" + //
                "\t\t\"bullwtRatio\": 0.8,\r\n" + //
                "\t\t\"peg\": 2.68,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"BALUFORGE\",\r\n" + //
                "\t\t\"rating\": \"Buy\",\r\n" + //
                "\t\t\"avgReturns\": 23.3,\r\n" + //
                "\t\t\"mcapCr\": 6754.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 4,\r\n" + //
                "\t\t\"dailyPL\": \"-3.98%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 587.0,\r\n" + //
                "\t\t\"cmp\": 617.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 5.0,\r\n" + //
                "\t\t\"currpe\": 39.9,\r\n" + //
                "\t\t\"pe5y\": 32.5,\r\n" + //
                "\t\t\"mcap2sales\": 8.29,\r\n" + //
                "\t\t\"mcap2sales3y\": 6.0,\r\n" + //
                "\t\t\"epsttm\": 15.9,\r\n" + //
                "\t\t\"revgr3y\": 57.9,\r\n" + //
                "\t\t\"patgr3y\": 131.0,\r\n" + //
                "\t\t\"ttmpatgr\": 111.0,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 25.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 35.0,\r\n" + //
                "\t\t\"baseCaseTP\": 1359.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 21.8,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 30.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 38.0,\r\n" + //
                "\t\t\"bullCaseTP\": 1726.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 29.3,\r\n" + //
                "\t\t\"bullwtRatio\": 0.8,\r\n" + //
                "\t\t\"peg\": 0.0,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"BIKAJI\",\r\n" + //
                "\t\t\"rating\": \"Buy\",\r\n" + //
                "\t\t\"avgReturns\": 20.6,\r\n" + //
                "\t\t\"mcapCr\": 15146.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 5,\r\n" + //
                "\t\t\"dailyPL\": \"-5.45%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 506.0,\r\n" + //
                "\t\t\"cmp\": 605.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 16.0,\r\n" + //
                "\t\t\"currpe\": 56.0,\r\n" + //
                "\t\t\"pe5y\": 75.6,\r\n" + //
                "\t\t\"mcap2sales\": 5.77,\r\n" + //
                "\t\t\"mcap2sales3y\": 6.4,\r\n" + //
                "\t\t\"epsttm\": 10.9,\r\n" + //
                "\t\t\"revgr3y\": 21.1,\r\n" + //
                "\t\t\"patgr3y\": 43.0,\r\n" + //
                "\t\t\"ttmpatgr\": 46.4,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 22.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 50.0,\r\n" + //
                "\t\t\"baseCaseTP\": 1473.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 19.5,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 25.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 55.0,\r\n" + //
                "\t\t\"bullCaseTP\": 1830.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 24.8,\r\n" + //
                "\t\t\"bullwtRatio\": 0.8,\r\n" + //
                "\t\t\"peg\": 0.0,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"BLS\",\r\n" + //
                "\t\t\"rating\": \"Hold\",\r\n" + //
                "\t\t\"avgReturns\": 16.6,\r\n" + //
                "\t\t\"mcapCr\": 16706.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 4,\r\n" + //
                "\t\t\"dailyPL\": \"-4.39%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 308.0,\r\n" + //
                "\t\t\"cmp\": 406.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 24.0,\r\n" + //
                "\t\t\"currpe\": 40.0,\r\n" + //
                "\t\t\"pe5y\": 37.4,\r\n" + //
                "\t\t\"mcap2sales\": 8.92,\r\n" + //
                "\t\t\"mcap2sales3y\": 113.9,\r\n" + //
                "\t\t\"epsttm\": 10.2,\r\n" + //
                "\t\t\"revgr3y\": 51.9,\r\n" + //
                "\t\t\"patgr3y\": 84.1,\r\n" + //
                "\t\t\"ttmpatgr\": 56.3,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 22.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 32.0,\r\n" + //
                "\t\t\"baseCaseTP\": 723.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 15.5,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 25.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 35.0,\r\n" + //
                "\t\t\"bullCaseTP\": 872.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 21.1,\r\n" + //
                "\t\t\"bullwtRatio\": 0.8,\r\n" + //
                "\t\t\"peg\": 1.21,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"BLUESTARCO\",\r\n" + //
                "\t\t\"rating\": \"Accumulate\",\r\n" + //
                "\t\t\"avgReturns\": 18.5,\r\n" + //
                "\t\t\"mcapCr\": 39033.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 4,\r\n" + //
                "\t\t\"dailyPL\": \"-4.00%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 1533.0,\r\n" + //
                "\t\t\"cmp\": 1898.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 19.0,\r\n" + //
                "\t\t\"currpe\": 71.3,\r\n" + //
                "\t\t\"pe5y\": 63.4,\r\n" + //
                "\t\t\"mcap2sales\": 3.46,\r\n" + //
                "\t\t\"mcap2sales3y\": 2.2,\r\n" + //
                "\t\t\"epsttm\": 27.1,\r\n" + //
                "\t\t\"revgr3y\": 31.5,\r\n" + //
                "\t\t\"patgr3y\": 77.6,\r\n" + //
                "\t\t\"ttmpatgr\": 52.9,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 22.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 60.0,\r\n" + //
                "\t\t\"baseCaseTP\": 3602.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 17.4,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 25.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 65.0,\r\n" + //
                "\t\t\"bullCaseTP\": 4301.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 22.7,\r\n" + //
                "\t\t\"bullwtRatio\": 0.9,\r\n" + //
                "\t\t\"peg\": 4.16,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"CDSL\",\r\n" + //
                "\t\t\"rating\": \"Accumulate\",\r\n" + //
                "\t\t\"avgReturns\": 18.5,\r\n" + //
                "\t\t\"mcapCr\": 25799.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 7,\r\n" + //
                "\t\t\"dailyPL\": \"-4.87%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 853.0,\r\n" + //
                "\t\t\"cmp\": 1234.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 31.0,\r\n" + //
                "\t\t\"currpe\": 46.4,\r\n" + //
                "\t\t\"pe5y\": 45.4,\r\n" + //
                "\t\t\"mcap2sales\": 23.5,\r\n" + //
                "\t\t\"mcap2sales3y\": 32.1,\r\n" + //
                "\t\t\"epsttm\": 26.6,\r\n" + //
                "\t\t\"revgr3y\": 33.2,\r\n" + //
                "\t\t\"patgr3y\": 27.9,\r\n" + //
                "\t\t\"ttmpatgr\": 57.3,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 20.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 40.0,\r\n" + //
                "\t\t\"baseCaseTP\": 3813.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 17.5,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 25.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 40.0,\r\n" + //
                "\t\t\"bullCaseTP\": 5074.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 22.4,\r\n" + //
                "\t\t\"bullwtRatio\": 0.9,\r\n" + //
                "\t\t\"peg\": 1.55,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"CHOICEIN\",\r\n" + //
                "\t\t\"rating\": \"Sell\",\r\n" + //
                "\t\t\"avgReturns\": 8.8,\r\n" + //
                "\t\t\"mcapCr\": 9778.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 4,\r\n" + //
                "\t\t\"dailyPL\": \"-4.85%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 240.0,\r\n" + //
                "\t\t\"cmp\": 490.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 51.0,\r\n" + //
                "\t\t\"currpe\": 68.2,\r\n" + //
                "\t\t\"pe5y\": 41.8,\r\n" + //
                "\t\t\"mcap2sales\": 11.2,\r\n" + //
                "\t\t\"mcap2sales3y\": 288.5,\r\n" + //
                "\t\t\"epsttm\": 7.19,\r\n" + //
                "\t\t\"revgr3y\": 62.7,\r\n" + //
                "\t\t\"patgr3y\": 98.5,\r\n" + //
                "\t\t\"ttmpatgr\": 19.1,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 22.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 35.0,\r\n" + //
                "\t\t\"baseCaseTP\": 557.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 3.3,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 25.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 40.0,\r\n" + //
                "\t\t\"bullCaseTP\": 702.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 9.4,\r\n" + //
                "\t\t\"bullwtRatio\": 0.9,\r\n" + //
                "\t\t\"peg\": 0.79,\r\n" + //
                "\t\t\"exitKeyVals\": [\r\n" + //
                "\t\t\t{\r\n" + //
                "\t\t\t\t\"key\": \"Expected Rate of Returns \",\r\n" + //
                "\t\t\t\t\"value\": 8.8\r\n" + //
                "\t\t\t}\r\n" + //
                "\t\t]\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"CUMMINSIND\",\r\n" + //
                "\t\t\"rating\": \"Buy\",\r\n" + //
                "\t\t\"avgReturns\": 20.1,\r\n" + //
                "\t\t\"mcapCr\": 78669.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 4,\r\n" + //
                "\t\t\"dailyPL\": \"-1.81%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 2423.0,\r\n" + //
                "\t\t\"cmp\": 2838.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 15.0,\r\n" + //
                "\t\t\"currpe\": 39.1,\r\n" + //
                "\t\t\"pe5y\": 37.5,\r\n" + //
                "\t\t\"mcap2sales\": 7.68,\r\n" + //
                "\t\t\"mcap2sales3y\": 6.2,\r\n" + //
                "\t\t\"epsttm\": 72.5,\r\n" + //
                "\t\t\"revgr3y\": 27.3,\r\n" + //
                "\t\t\"patgr3y\": 38.5,\r\n" + //
                "\t\t\"ttmpatgr\": 31.2,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 20.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 38.0,\r\n" + //
                "\t\t\"baseCaseTP\": 5713.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 19.1,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 25.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 38.0,\r\n" + //
                "\t\t\"bullCaseTP\": 6726.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 24.1,\r\n" + //
                "\t\t\"bullwtRatio\": 0.8,\r\n" + //
                "\t\t\"peg\": 2.14,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"DOMS\",\r\n" + //
                "\t\t\"rating\": \"Hold\",\r\n" + //
                "\t\t\"avgReturns\": 16.1,\r\n" + //
                "\t\t\"mcapCr\": 16410.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 5,\r\n" + //
                "\t\t\"dailyPL\": \"-4.96%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 1869.0,\r\n" + //
                "\t\t\"cmp\": 2704.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 31.0,\r\n" + //
                "\t\t\"currpe\": 82.4,\r\n" + //
                "\t\t\"pe5y\": 0.89,\r\n" + //
                "\t\t\"mcap2sales\": 9.08,\r\n" + //
                "\t\t\"mcap2sales3y\": 10.3,\r\n" + //
                "\t\t\"epsttm\": 32.8,\r\n" + //
                "\t\t\"revgr3y\": 56.0,\r\n" + //
                "\t\t\"patgr3y\": 190.0,\r\n" + //
                "\t\t\"ttmpatgr\": 40.2,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 25.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 55.0,\r\n" + //
                "\t\t\"baseCaseTP\": 5505.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 15.3,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 27.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 60.0,\r\n" + //
                "\t\t\"bullCaseTP\": 6502.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 19.2,\r\n" + //
                "\t\t\"bullwtRatio\": 0.9,\r\n" + //
                "\t\t\"peg\": 0.0,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"DSSL\",\r\n" + //
                "\t\t\"rating\": \"Buy\",\r\n" + //
                "\t\t\"avgReturns\": 23.0,\r\n" + //
                "\t\t\"mcapCr\": 1426.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 5,\r\n" + //
                "\t\t\"dailyPL\": \"-5.28%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 1039.0,\r\n" + //
                "\t\t\"cmp\": 1121.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 7.0,\r\n" + //
                "\t\t\"currpe\": 20.9,\r\n" + //
                "\t\t\"pe5y\": 17.5,\r\n" + //
                "\t\t\"mcap2sales\": 1.17,\r\n" + //
                "\t\t\"mcap2sales3y\": 0.9,\r\n" + //
                "\t\t\"epsttm\": 53.7,\r\n" + //
                "\t\t\"revgr3y\": 33.0,\r\n" + //
                "\t\t\"patgr3y\": 84.6,\r\n" + //
                "\t\t\"ttmpatgr\": 31.2,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 25.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 18.0,\r\n" + //
                "\t\t\"baseCaseTP\": 2950.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 21.4,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 28.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 22.0,\r\n" + //
                "\t\t\"bullCaseTP\": 4059.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 29.3,\r\n" + //
                "\t\t\"bullwtRatio\": 0.8,\r\n" + //
                "\t\t\"peg\": 0.35,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"DYCL\",\r\n" + //
                "\t\t\"rating\": \"Accumulate\",\r\n" + //
                "\t\t\"avgReturns\": 19.5,\r\n" + //
                "\t\t\"mcapCr\": 1722.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 4,\r\n" + //
                "\t\t\"dailyPL\": \"None%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 594.0,\r\n" + //
                "\t\t\"cmp\": 711.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 16.0,\r\n" + //
                "\t\t\"currpe\": 31.3,\r\n" + //
                "\t\t\"pe5y\": 13.5,\r\n" + //
                "\t\t\"mcap2sales\": 1.84,\r\n" + //
                "\t\t\"mcap2sales3y\": 1.3,\r\n" + //
                "\t\t\"epsttm\": 23.3,\r\n" + //
                "\t\t\"revgr3y\": 30.9,\r\n" + //
                "\t\t\"patgr3y\": 56.6,\r\n" + //
                "\t\t\"ttmpatgr\": 62.0,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 25.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 25.0,\r\n" + //
                "\t\t\"baseCaseTP\": 1422.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 18.9,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 28.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 25.0,\r\n" + //
                "\t\t\"bullCaseTP\": 1564.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 21.8,\r\n" + //
                "\t\t\"bullwtRatio\": 0.8,\r\n" + //
                "\t\t\"peg\": 2.56,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"ETHOSLTD\",\r\n" + //
                "\t\t\"rating\": \"Buy\",\r\n" + //
                "\t\t\"avgReturns\": 21.0,\r\n" + //
                "\t\t\"mcapCr\": 5557.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 5,\r\n" + //
                "\t\t\"dailyPL\": \"-3.39%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 1938.0,\r\n" + //
                "\t\t\"cmp\": 2270.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 15.0,\r\n" + //
                "\t\t\"currpe\": 61.3,\r\n" + //
                "\t\t\"pe5y\": 73.0,\r\n" + //
                "\t\t\"mcap2sales\": 5.03,\r\n" + //
                "\t\t\"mcap2sales3y\": 5.2,\r\n" + //
                "\t\t\"epsttm\": 37.0,\r\n" + //
                "\t\t\"revgr3y\": 37.2,\r\n" + //
                "\t\t\"patgr3y\": 160.0,\r\n" + //
                "\t\t\"ttmpatgr\": 28.2,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 25.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 50.0,\r\n" + //
                "\t\t\"baseCaseTP\": 5646.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 20.0,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 28.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 55.0,\r\n" + //
                "\t\t\"bullCaseTP\": 6992.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 25.2,\r\n" + //
                "\t\t\"bullwtRatio\": 0.8,\r\n" + //
                "\t\t\"peg\": 1.39,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"GANESHHOUC\",\r\n" + //
                "\t\t\"rating\": \"Buy\",\r\n" + //
                "\t\t\"avgReturns\": 20.7,\r\n" + //
                "\t\t\"mcapCr\": 11224.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 7,\r\n" + //
                "\t\t\"dailyPL\": \"-3.55%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 1058.0,\r\n" + //
                "\t\t\"cmp\": 1346.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 21.0,\r\n" + //
                "\t\t\"currpe\": 20.5,\r\n" + //
                "\t\t\"pe5y\": 21.6,\r\n" + //
                "\t\t\"mcap2sales\": 11.4,\r\n" + //
                "\t\t\"mcap2sales3y\": 13.3,\r\n" + //
                "\t\t\"epsttm\": 65.5,\r\n" + //
                "\t\t\"revgr3y\": 74.4,\r\n" + //
                "\t\t\"patgr3y\": 85.5,\r\n" + //
                "\t\t\"ttmpatgr\": 41.0,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 22.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 18.0,\r\n" + //
                "\t\t\"baseCaseTP\": 4743.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 19.7,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 25.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 20.0,\r\n" + //
                "\t\t\"bullCaseTP\": 6247.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 24.5,\r\n" + //
                "\t\t\"bullwtRatio\": 0.9,\r\n" + //
                "\t\t\"peg\": 0.27,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"GODFRYPHLP\",\r\n" + //
                "\t\t\"rating\": \"Hold\",\r\n" + //
                "\t\t\"avgReturns\": 9.3,\r\n" + //
                "\t\t\"mcapCr\": 28065.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 8,\r\n" + //
                "\t\t\"dailyPL\": \"-3.23%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 1890.0,\r\n" + //
                "\t\t\"cmp\": 5398.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 65.0,\r\n" + //
                "\t\t\"currpe\": 29.8,\r\n" + //
                "\t\t\"pe5y\": 14.6,\r\n" + //
                "\t\t\"mcap2sales\": 5.88,\r\n" + //
                "\t\t\"mcap2sales3y\": 2.9,\r\n" + //
                "\t\t\"epsttm\": 174.0,\r\n" + //
                "\t\t\"revgr3y\": 20.5,\r\n" + //
                "\t\t\"patgr3y\": 34.2,\r\n" + //
                "\t\t\"ttmpatgr\": 17.6,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 18.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 15.0,\r\n" + //
                "\t\t\"baseCaseTP\": 9811.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 7.8,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 22.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 20.0,\r\n" + //
                "\t\t\"bullCaseTP\": 17079.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 15.5,\r\n" + //
                "\t\t\"bullwtRatio\": 0.9,\r\n" + //
                "\t\t\"peg\": 1.06,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"GRAVITA\",\r\n" + //
                "\t\t\"rating\": \"Hold\",\r\n" + //
                "\t\t\"avgReturns\": 17.0,\r\n" + //
                "\t\t\"mcapCr\": 13581.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 4,\r\n" + //
                "\t\t\"dailyPL\": \"-5.34%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 1415.0,\r\n" + //
                "\t\t\"cmp\": 1840.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 23.0,\r\n" + //
                "\t\t\"currpe\": 47.5,\r\n" + //
                "\t\t\"pe5y\": 19.0,\r\n" + //
                "\t\t\"mcap2sales\": 3.68,\r\n" + //
                "\t\t\"mcap2sales3y\": 1.9,\r\n" + //
                "\t\t\"epsttm\": 40.7,\r\n" + //
                "\t\t\"revgr3y\": 30.9,\r\n" + //
                "\t\t\"patgr3y\": 61.5,\r\n" + //
                "\t\t\"ttmpatgr\": 22.3,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 24.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 35.0,\r\n" + //
                "\t\t\"baseCaseTP\": 3368.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 16.3,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 26.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 37.0,\r\n" + //
                "\t\t\"bullCaseTP\": 3796.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 19.8,\r\n" + //
                "\t\t\"bullwtRatio\": 0.9,\r\n" + //
                "\t\t\"peg\": 0.65,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"GRWRHITECH\",\r\n" + //
                "\t\t\"rating\": \"Buy\",\r\n" + //
                "\t\t\"avgReturns\": 21.2,\r\n" + //
                "\t\t\"mcapCr\": 8459.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 4,\r\n" + //
                "\t\t\"dailyPL\": \"-0.40%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 3236.0,\r\n" + //
                "\t\t\"cmp\": 3641.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 11.0,\r\n" + //
                "\t\t\"currpe\": 27.2,\r\n" + //
                "\t\t\"pe5y\": 12.4,\r\n" + //
                "\t\t\"mcap2sales\": 4.21,\r\n" + //
                "\t\t\"mcap2sales3y\": 1.7,\r\n" + //
                "\t\t\"epsttm\": 134.0,\r\n" + //
                "\t\t\"revgr3y\": 19.2,\r\n" + //
                "\t\t\"patgr3y\": 17.6,\r\n" + //
                "\t\t\"ttmpatgr\": 65.1,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 22.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 25.0,\r\n" + //
                "\t\t\"baseCaseTP\": 7421.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 19.5,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 25.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 30.0,\r\n" + //
                "\t\t\"bullCaseTP\": 9814.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 28.1,\r\n" + //
                "\t\t\"bullwtRatio\": 0.9,\r\n" + //
                "\t\t\"peg\": 1.33,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"HBLENGINE\",\r\n" + //
                "\t\t\"rating\": \"Buy\",\r\n" + //
                "\t\t\"avgReturns\": 23.2,\r\n" + //
                "\t\t\"mcapCr\": 13056.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 4,\r\n" + //
                "\t\t\"dailyPL\": \"-10.75%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 445.0,\r\n" + //
                "\t\t\"cmp\": 471.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 6.0,\r\n" + //
                "\t\t\"currpe\": 39.6,\r\n" + //
                "\t\t\"pe5y\": 45.2,\r\n" + //
                "\t\t\"mcap2sales\": 6.21,\r\n" + //
                "\t\t\"mcap2sales3y\": 4.6,\r\n" + //
                "\t\t\"epsttm\": 11.3,\r\n" + //
                "\t\t\"revgr3y\": 34.8,\r\n" + //
                "\t\t\"patgr3y\": 160.0,\r\n" + //
                "\t\t\"ttmpatgr\": 39.1,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 28.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 35.0,\r\n" + //
                "\t\t\"baseCaseTP\": 1062.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 22.5,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 29.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 38.0,\r\n" + //
                "\t\t\"bullCaseTP\": 1189.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 26.0,\r\n" + //
                "\t\t\"bullwtRatio\": 0.8,\r\n" + //
                "\t\t\"peg\": 0.6,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"IGIL\",\r\n" + //
                "\t\t\"rating\": \"Hold\",\r\n" + //
                "\t\t\"avgReturns\": 0.0,\r\n" + //
                "\t\t\"mcapCr\": 19797.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 5,\r\n" + //
                "\t\t\"dailyPL\": \"-4.76%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 0.0,\r\n" + //
                "\t\t\"cmp\": 458.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 100.0,\r\n" + //
                "\t\t\"currpe\": 60.9,\r\n" + //
                "\t\t\"pe5y\": 0.0,\r\n" + //
                "\t\t\"mcap2sales\": 31.0,\r\n" + //
                "\t\t\"mcap2sales3y\": 33.1,\r\n" + //
                "\t\t\"epsttm\": 0.0,\r\n" + //
                "\t\t\"revgr3y\": 0.0,\r\n" + //
                "\t\t\"patgr3y\": 0.0,\r\n" + //
                "\t\t\"ttmpatgr\": 34.4,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 25.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 60.0,\r\n" + //
                "\t\t\"baseCaseTP\": 0.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 0.0,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 30.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 65.0,\r\n" + //
                "\t\t\"bullCaseTP\": 0.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 0.0,\r\n" + //
                "\t\t\"bullwtRatio\": 0.8,\r\n" + //
                "\t\t\"peg\": 0.0,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"INDOTECH\",\r\n" + //
                "\t\t\"rating\": \"Buy\",\r\n" + //
                "\t\t\"avgReturns\": 20.6,\r\n" + //
                "\t\t\"mcapCr\": 2516.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 4,\r\n" + //
                "\t\t\"dailyPL\": \"None%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 2067.0,\r\n" + //
                "\t\t\"cmp\": 2369.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 13.0,\r\n" + //
                "\t\t\"currpe\": 42.0,\r\n" + //
                "\t\t\"pe5y\": 21.9,\r\n" + //
                "\t\t\"mcap2sales\": 4.64,\r\n" + //
                "\t\t\"mcap2sales3y\": 1.2,\r\n" + //
                "\t\t\"epsttm\": 56.4,\r\n" + //
                "\t\t\"revgr3y\": 34.7,\r\n" + //
                "\t\t\"patgr3y\": 95.3,\r\n" + //
                "\t\t\"ttmpatgr\": 77.4,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 35.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 25.0,\r\n" + //
                "\t\t\"baseCaseTP\": 4683.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 18.6,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 40.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 30.0,\r\n" + //
                "\t\t\"bullCaseTP\": 6500.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 28.7,\r\n" + //
                "\t\t\"bullwtRatio\": 0.8,\r\n" + //
                "\t\t\"peg\": 0.84,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"ITDCEM\",\r\n" + //
                "\t\t\"rating\": \"Hold\",\r\n" + //
                "\t\t\"avgReturns\": 17.6,\r\n" + //
                "\t\t\"mcapCr\": 9004.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 4,\r\n" + //
                "\t\t\"dailyPL\": \"-2.45%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 412.0,\r\n" + //
                "\t\t\"cmp\": 524.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 21.0,\r\n" + //
                "\t\t\"currpe\": 26.5,\r\n" + //
                "\t\t\"pe5y\": 25.2,\r\n" + //
                "\t\t\"mcap2sales\": 1.04,\r\n" + //
                "\t\t\"mcap2sales3y\": 0.7,\r\n" + //
                "\t\t\"epsttm\": 19.8,\r\n" + //
                "\t\t\"revgr3y\": 41.4,\r\n" + //
                "\t\t\"patgr3y\": 170.0,\r\n" + //
                "\t\t\"ttmpatgr\": 89.0,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 22.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 22.0,\r\n" + //
                "\t\t\"baseCaseTP\": 965.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 16.5,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 24.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 25.0,\r\n" + //
                "\t\t\"bullCaseTP\": 1170.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 22.2,\r\n" + //
                "\t\t\"bullwtRatio\": 0.9,\r\n" + //
                "\t\t\"peg\": 1.0,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"JASH\",\r\n" + //
                "\t\t\"rating\": \"Hold\",\r\n" + //
                "\t\t\"avgReturns\": 10.5,\r\n" + //
                "\t\t\"mcapCr\": 3230.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 4,\r\n" + //
                "\t\t\"dailyPL\": \"-6.49%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 316.0,\r\n" + //
                "\t\t\"cmp\": 516.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 39.0,\r\n" + //
                "\t\t\"currpe\": 41.6,\r\n" + //
                "\t\t\"pe5y\": 25.1,\r\n" + //
                "\t\t\"mcap2sales\": 5.29,\r\n" + //
                "\t\t\"mcap2sales3y\": 5.6,\r\n" + //
                "\t\t\"epsttm\": 12.7,\r\n" + //
                "\t\t\"revgr3y\": 19.9,\r\n" + //
                "\t\t\"patgr3y\": 29.9,\r\n" + //
                "\t\t\"ttmpatgr\": 40.3,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 18.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 30.0,\r\n" + //
                "\t\t\"baseCaseTP\": 739.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 9.4,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 22.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 32.0,\r\n" + //
                "\t\t\"bullCaseTP\": 900.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 14.9,\r\n" + //
                "\t\t\"bullwtRatio\": 0.9,\r\n" + //
                "\t\t\"peg\": 0.77,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"KAYNES\",\r\n" + //
                "\t\t\"rating\": \"StrongBuy\",\r\n" + //
                "\t\t\"avgReturns\": 30.3,\r\n" + //
                "\t\t\"mcapCr\": 25640.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 5,\r\n" + //
                "\t\t\"dailyPL\": \"-2.74%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 4940.0,\r\n" + //
                "\t\t\"cmp\": 4006.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": -23.0,\r\n" + //
                "\t\t\"currpe\": 99.1,\r\n" + //
                "\t\t\"pe5y\": 115.0,\r\n" + //
                "\t\t\"mcap2sales\": 10.8,\r\n" + //
                "\t\t\"mcap2sales3y\": 13.1,\r\n" + //
                "\t\t\"epsttm\": 40.4,\r\n" + //
                "\t\t\"revgr3y\": 62.5,\r\n" + //
                "\t\t\"patgr3y\": 165.0,\r\n" + //
                "\t\t\"ttmpatgr\": 80.4,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 45.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 55.0,\r\n" + //
                "\t\t\"baseCaseTP\": 14242.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 28.9,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 50.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 60.0,\r\n" + //
                "\t\t\"bullCaseTP\": 18407.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 35.7,\r\n" + //
                "\t\t\"bullwtRatio\": 0.9,\r\n" + //
                "\t\t\"peg\": 0.0,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"KFINTECH\",\r\n" + //
                "\t\t\"rating\": \"Accumulate\",\r\n" + //
                "\t\t\"avgReturns\": 19.8,\r\n" + //
                "\t\t\"mcapCr\": 17895.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 6,\r\n" + //
                "\t\t\"dailyPL\": \"-9.75%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 816.0,\r\n" + //
                "\t\t\"cmp\": 1041.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 22.0,\r\n" + //
                "\t\t\"currpe\": 55.5,\r\n" + //
                "\t\t\"pe5y\": 42.4,\r\n" + //
                "\t\t\"mcap2sales\": 17.3,\r\n" + //
                "\t\t\"mcap2sales3y\": 12.6,\r\n" + //
                "\t\t\"epsttm\": 18.8,\r\n" + //
                "\t\t\"revgr3y\": 20.3,\r\n" + //
                "\t\t\"patgr3y\": 79.9,\r\n" + //
                "\t\t\"ttmpatgr\": 40.9,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 25.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 40.0,\r\n" + //
                "\t\t\"baseCaseTP\": 2869.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 18.4,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 30.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 45.0,\r\n" + //
                "\t\t\"bullCaseTP\": 4083.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 25.6,\r\n" + //
                "\t\t\"bullwtRatio\": 0.9,\r\n" + //
                "\t\t\"peg\": 0.59,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"KPITTECH\",\r\n" + //
                "\t\t\"rating\": \"Hold\",\r\n" + //
                "\t\t\"avgReturns\": 16.2,\r\n" + //
                "\t\t\"mcapCr\": 38069.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 4,\r\n" + //
                "\t\t\"dailyPL\": \"-3.44%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 1037.0,\r\n" + //
                "\t\t\"cmp\": 1389.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 25.0,\r\n" + //
                "\t\t\"currpe\": 50.2,\r\n" + //
                "\t\t\"pe5y\": 58.2,\r\n" + //
                "\t\t\"mcap2sales\": 6.76,\r\n" + //
                "\t\t\"mcap2sales3y\": 17.1,\r\n" + //
                "\t\t\"epsttm\": 27.7,\r\n" + //
                "\t\t\"revgr3y\": 33.8,\r\n" + //
                "\t\t\"patgr3y\": 61.3,\r\n" + //
                "\t\t\"ttmpatgr\": 40.5,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 22.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 40.0,\r\n" + //
                "\t\t\"baseCaseTP\": 2455.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 15.3,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 25.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 42.0,\r\n" + //
                "\t\t\"bullCaseTP\": 2840.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 19.6,\r\n" + //
                "\t\t\"bullwtRatio\": 0.8,\r\n" + //
                "\t\t\"peg\": 1.01,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"LUMAXTECH\",\r\n" + //
                "\t\t\"rating\": \"Hold\",\r\n" + //
                "\t\t\"avgReturns\": 15.4,\r\n" + //
                "\t\t\"mcapCr\": 3655.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 4,\r\n" + //
                "\t\t\"dailyPL\": \"-2.65%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 389.0,\r\n" + //
                "\t\t\"cmp\": 536.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 27.0,\r\n" + //
                "\t\t\"currpe\": 23.5,\r\n" + //
                "\t\t\"pe5y\": 23.6,\r\n" + //
                "\t\t\"mcap2sales\": 1.18,\r\n" + //
                "\t\t\"mcap2sales3y\": 1.9,\r\n" + //
                "\t\t\"epsttm\": 22.8,\r\n" + //
                "\t\t\"revgr3y\": 36.6,\r\n" + //
                "\t\t\"patgr3y\": 41.2,\r\n" + //
                "\t\t\"ttmpatgr\": 59.8,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 18.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 21.0,\r\n" + //
                "\t\t\"baseCaseTP\": 928.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 14.7,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 20.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 22.0,\r\n" + //
                "\t\t\"bullCaseTP\": 1040.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 18.0,\r\n" + //
                "\t\t\"bullwtRatio\": 0.9,\r\n" + //
                "\t\t\"peg\": 1.75,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"MOTILALOFS\",\r\n" + //
                "\t\t\"rating\": \"Buy\",\r\n" + //
                "\t\t\"avgReturns\": 22.2,\r\n" + //
                "\t\t\"mcapCr\": 37934.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 6,\r\n" + //
                "\t\t\"dailyPL\": \"-3.37%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 556.0,\r\n" + //
                "\t\t\"cmp\": 633.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 12.0,\r\n" + //
                "\t\t\"currpe\": 11.6,\r\n" + //
                "\t\t\"pe5y\": 11.6,\r\n" + //
                "\t\t\"mcap2sales\": 4.08,\r\n" + //
                "\t\t\"mcap2sales3y\": 4.7,\r\n" + //
                "\t\t\"epsttm\": 55.0,\r\n" + //
                "\t\t\"revgr3y\": 24.9,\r\n" + //
                "\t\t\"patgr3y\": 22.5,\r\n" + //
                "\t\t\"ttmpatgr\": 74.7,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 20.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 12.0,\r\n" + //
                "\t\t\"baseCaseTP\": 1971.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 20.8,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 25.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 13.0,\r\n" + //
                "\t\t\"bullCaseTP\": 2728.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 27.6,\r\n" + //
                "\t\t\"bullwtRatio\": 0.9,\r\n" + //
                "\t\t\"peg\": 0.22,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"NEWGEN\",\r\n" + //
                "\t\t\"rating\": \"Hold\",\r\n" + //
                "\t\t\"avgReturns\": 16.4,\r\n" + //
                "\t\t\"mcapCr\": 13918.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 4,\r\n" + //
                "\t\t\"dailyPL\": \"-3.63%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 742.0,\r\n" + //
                "\t\t\"cmp\": 983.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 25.0,\r\n" + //
                "\t\t\"currpe\": 44.6,\r\n" + //
                "\t\t\"pe5y\": 23.0,\r\n" + //
                "\t\t\"mcap2sales\": 9.72,\r\n" + //
                "\t\t\"mcap2sales3y\": 6.4,\r\n" + //
                "\t\t\"epsttm\": 22.2,\r\n" + //
                "\t\t\"revgr3y\": 22.7,\r\n" + //
                "\t\t\"patgr3y\": 25.7,\r\n" + //
                "\t\t\"ttmpatgr\": 38.0,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 22.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 35.0,\r\n" + //
                "\t\t\"baseCaseTP\": 1721.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 15.0,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 25.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 40.0,\r\n" + //
                "\t\t\"bullCaseTP\": 2168.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 21.9,\r\n" + //
                "\t\t\"bullwtRatio\": 0.9,\r\n" + //
                "\t\t\"peg\": 2.28,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"NUVAMA\",\r\n" + //
                "\t\t\"rating\": \"Buy\",\r\n" + //
                "\t\t\"avgReturns\": 21.5,\r\n" + //
                "\t\t\"mcapCr\": 19826.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 6,\r\n" + //
                "\t\t\"dailyPL\": \"-1.55%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 4667.0,\r\n" + //
                "\t\t\"cmp\": 5524.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 16.0,\r\n" + //
                "\t\t\"currpe\": 21.8,\r\n" + //
                "\t\t\"pe5y\": 28.9,\r\n" + //
                "\t\t\"mcap2sales\": 5.01,\r\n" + //
                "\t\t\"mcap2sales3y\": 28.6,\r\n" + //
                "\t\t\"epsttm\": 256.0,\r\n" + //
                "\t\t\"revgr3y\": 31.6,\r\n" + //
                "\t\t\"patgr3y\": 82.1,\r\n" + //
                "\t\t\"ttmpatgr\": 72.0,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 22.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 20.0,\r\n" + //
                "\t\t\"baseCaseTP\": 16882.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 20.5,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 25.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 22.0,\r\n" + //
                "\t\t\"bullCaseTP\": 21484.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 25.4,\r\n" + //
                "\t\t\"bullwtRatio\": 0.9,\r\n" + //
                "\t\t\"peg\": 0.0,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"PERSISTENT\",\r\n" + //
                "\t\t\"rating\": \"Hold\",\r\n" + //
                "\t\t\"avgReturns\": 10.8,\r\n" + //
                "\t\t\"mcapCr\": 90804.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 5,\r\n" + //
                "\t\t\"dailyPL\": \"-2.78%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 3193.0,\r\n" + //
                "\t\t\"cmp\": 5826.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 45.0,\r\n" + //
                "\t\t\"currpe\": 68.8,\r\n" + //
                "\t\t\"pe5y\": 42.0,\r\n" + //
                "\t\t\"mcap2sales\": 8.04,\r\n" + //
                "\t\t\"mcap2sales3y\": 8.1,\r\n" + //
                "\t\t\"epsttm\": 85.5,\r\n" + //
                "\t\t\"revgr3y\": 32.9,\r\n" + //
                "\t\t\"patgr3y\": 35.9,\r\n" + //
                "\t\t\"ttmpatgr\": 28.2,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 22.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 40.0,\r\n" + //
                "\t\t\"baseCaseTP\": 9243.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 9.7,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 25.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 45.0,\r\n" + //
                "\t\t\"bullCaseTP\": 11742.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 15.0,\r\n" + //
                "\t\t\"bullwtRatio\": 0.9,\r\n" + //
                "\t\t\"peg\": 2.56,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"PGEL\",\r\n" + //
                "\t\t\"rating\": \"StrongBuy\",\r\n" + //
                "\t\t\"avgReturns\": 25.2,\r\n" + //
                "\t\t\"mcapCr\": 22929.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 5,\r\n" + //
                "\t\t\"dailyPL\": \"-0.70%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 823.0,\r\n" + //
                "\t\t\"cmp\": 810.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": -2.0,\r\n" + //
                "\t\t\"currpe\": 108.0,\r\n" + //
                "\t\t\"pe5y\": 48.6,\r\n" + //
                "\t\t\"mcap2sales\": 5.68,\r\n" + //
                "\t\t\"mcap2sales3y\": 3.0,\r\n" + //
                "\t\t\"epsttm\": 8.01,\r\n" + //
                "\t\t\"revgr3y\": 57.5,\r\n" + //
                "\t\t\"patgr3y\": 125.0,\r\n" + //
                "\t\t\"ttmpatgr\": 101.0,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 40.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 55.0,\r\n" + //
                "\t\t\"baseCaseTP\": 2369.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 23.9,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 45.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 60.0,\r\n" + //
                "\t\t\"bullCaseTP\": 3081.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 30.6,\r\n" + //
                "\t\t\"bullwtRatio\": 0.9,\r\n" + //
                "\t\t\"peg\": 0.0,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"POLYCAB\",\r\n" + //
                "\t\t\"rating\": \"Hold\",\r\n" + //
                "\t\t\"avgReturns\": 15.0,\r\n" + //
                "\t\t\"mcapCr\": 82946.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 5,\r\n" + //
                "\t\t\"dailyPL\": \"-2.43%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 3640.0,\r\n" + //
                "\t\t\"cmp\": 5514.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 34.0,\r\n" + //
                "\t\t\"currpe\": 45.1,\r\n" + //
                "\t\t\"pe5y\": 40.1,\r\n" + //
                "\t\t\"mcap2sales\": 3.95,\r\n" + //
                "\t\t\"mcap2sales3y\": 4.0,\r\n" + //
                "\t\t\"epsttm\": 122.0,\r\n" + //
                "\t\t\"revgr3y\": 27.1,\r\n" + //
                "\t\t\"patgr3y\": 25.5,\r\n" + //
                "\t\t\"ttmpatgr\": 10.6,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 17.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 40.0,\r\n" + //
                "\t\t\"baseCaseTP\": 10699.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 14.2,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 20.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 42.0,\r\n" + //
                "\t\t\"bullCaseTP\": 12750.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 18.3,\r\n" + //
                "\t\t\"bullwtRatio\": 0.9,\r\n" + //
                "\t\t\"peg\": 1.61,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"POLYMED\",\r\n" + //
                "\t\t\"rating\": \"Accumulate\",\r\n" + //
                "\t\t\"avgReturns\": 19.0,\r\n" + //
                "\t\t\"mcapCr\": 22950.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 5,\r\n" + //
                "\t\t\"dailyPL\": \"-3.16%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 1771.0,\r\n" + //
                "\t\t\"cmp\": 2265.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 22.0,\r\n" + //
                "\t\t\"currpe\": 72.8,\r\n" + //
                "\t\t\"pe5y\": 59.6,\r\n" + //
                "\t\t\"mcap2sales\": 14.3,\r\n" + //
                "\t\t\"mcap2sales3y\": 11.4,\r\n" + //
                "\t\t\"epsttm\": 31.9,\r\n" + //
                "\t\t\"revgr3y\": 20.5,\r\n" + //
                "\t\t\"patgr3y\": 24.2,\r\n" + //
                "\t\t\"ttmpatgr\": 26.7,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 22.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 60.0,\r\n" + //
                "\t\t\"baseCaseTP\": 5173.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 18.0,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 25.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 65.0,\r\n" + //
                "\t\t\"bullCaseTP\": 6328.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 22.8,\r\n" + //
                "\t\t\"bullwtRatio\": 0.8,\r\n" + //
                "\t\t\"peg\": 2.28,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"SENCO\",\r\n" + //
                "\t\t\"rating\": \"Accumulate\",\r\n" + //
                "\t\t\"avgReturns\": 19.5,\r\n" + //
                "\t\t\"mcapCr\": 7129.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 5,\r\n" + //
                "\t\t\"dailyPL\": \"-7.76%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 351.0,\r\n" + //
                "\t\t\"cmp\": 436.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 19.0,\r\n" + //
                "\t\t\"currpe\": 34.8,\r\n" + //
                "\t\t\"pe5y\": 35.7,\r\n" + //
                "\t\t\"mcap2sales\": 1.25,\r\n" + //
                "\t\t\"mcap2sales3y\": 1.4,\r\n" + //
                "\t\t\"epsttm\": 13.2,\r\n" + //
                "\t\t\"revgr3y\": 25.4,\r\n" + //
                "\t\t\"patgr3y\": 43.5,\r\n" + //
                "\t\t\"ttmpatgr\": 21.2,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 20.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 30.0,\r\n" + //
                "\t\t\"baseCaseTP\": 985.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 17.7,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 25.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 35.0,\r\n" + //
                "\t\t\"bullCaseTP\": 1410.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 26.5,\r\n" + //
                "\t\t\"bullwtRatio\": 0.9,\r\n" + //
                "\t\t\"peg\": 0.0,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"SHAILY\",\r\n" + //
                "\t\t\"rating\": \"Sell\",\r\n" + //
                "\t\t\"avgReturns\": 11.8,\r\n" + //
                "\t\t\"mcapCr\": 6904.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 4,\r\n" + //
                "\t\t\"dailyPL\": \"-5.00%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 823.0,\r\n" + //
                "\t\t\"cmp\": 1502.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 45.0,\r\n" + //
                "\t\t\"currpe\": 82.3,\r\n" + //
                "\t\t\"pe5y\": 47.0,\r\n" + //
                "\t\t\"mcap2sales\": 9.34,\r\n" + //
                "\t\t\"mcap2sales3y\": 3.0,\r\n" + //
                "\t\t\"epsttm\": 18.3,\r\n" + //
                "\t\t\"revgr3y\": 0.0,\r\n" + //
                "\t\t\"patgr3y\": 0.0,\r\n" + //
                "\t\t\"ttmpatgr\": 75.2,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 20.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 50.0,\r\n" + //
                "\t\t\"baseCaseTP\": 1897.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 6.0,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 25.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 55.0,\r\n" + //
                "\t\t\"bullCaseTP\": 2457.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 13.1,\r\n" + //
                "\t\t\"bullwtRatio\": 0.8,\r\n" + //
                "\t\t\"peg\": 0.0,\r\n" + //
                "\t\t\"exitKeyVals\": [\r\n" + //
                "\t\t\t{\r\n" + //
                "\t\t\t\t\"key\": \"Expected Rate of Returns \",\r\n" + //
                "\t\t\t\t\"value\": 11.8\r\n" + //
                "\t\t\t}\r\n" + //
                "\t\t]\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"SJS\",\r\n" + //
                "\t\t\"rating\": \"Buy\",\r\n" + //
                "\t\t\"avgReturns\": 22.3,\r\n" + //
                "\t\t\"mcapCr\": 2957.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 4,\r\n" + //
                "\t\t\"dailyPL\": \"-3.89%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 869.0,\r\n" + //
                "\t\t\"cmp\": 944.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 8.0,\r\n" + //
                "\t\t\"currpe\": 26.6,\r\n" + //
                "\t\t\"pe5y\": 26.3,\r\n" + //
                "\t\t\"mcap2sales\": 3.96,\r\n" + //
                "\t\t\"mcap2sales3y\": 5.8,\r\n" + //
                "\t\t\"epsttm\": 35.7,\r\n" + //
                "\t\t\"revgr3y\": 0.0,\r\n" + //
                "\t\t\"patgr3y\": 0.0,\r\n" + //
                "\t\t\"ttmpatgr\": 51.2,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 20.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 27.0,\r\n" + //
                "\t\t\"baseCaseTP\": 1999.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 20.6,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 25.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 30.0,\r\n" + //
                "\t\t\"bullCaseTP\": 2615.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 29.0,\r\n" + //
                "\t\t\"bullwtRatio\": 0.8,\r\n" + //
                "\t\t\"peg\": 0.0,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"SKYGOLD\",\r\n" + //
                "\t\t\"rating\": \"StrongBuy\",\r\n" + //
                "\t\t\"avgReturns\": 28.7,\r\n" + //
                "\t\t\"mcapCr\": 4998.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 5,\r\n" + //
                "\t\t\"dailyPL\": \"-5.00%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 396.0,\r\n" + //
                "\t\t\"cmp\": 341.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": -16.0,\r\n" + //
                "\t\t\"currpe\": 46.2,\r\n" + //
                "\t\t\"pe5y\": 37.1,\r\n" + //
                "\t\t\"mcap2sales\": 1.66,\r\n" + //
                "\t\t\"mcap2sales3y\": 0.9,\r\n" + //
                "\t\t\"epsttm\": 7.81,\r\n" + //
                "\t\t\"revgr3y\": 0.0,\r\n" + //
                "\t\t\"patgr3y\": 0.0,\r\n" + //
                "\t\t\"ttmpatgr\": 228.0,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 40.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 27.0,\r\n" + //
                "\t\t\"baseCaseTP\": 1134.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 27.2,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 45.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 30.0,\r\n" + //
                "\t\t\"bullCaseTP\": 1502.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 34.5,\r\n" + //
                "\t\t\"bullwtRatio\": 0.8,\r\n" + //
                "\t\t\"peg\": 0.0,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"TARIL\",\r\n" + //
                "\t\t\"rating\": \"Buy\",\r\n" + //
                "\t\t\"avgReturns\": 22.8,\r\n" + //
                "\t\t\"mcapCr\": 12918.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 4,\r\n" + //
                "\t\t\"dailyPL\": \"-4.99%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 805.0,\r\n" + //
                "\t\t\"cmp\": 861.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 7.0,\r\n" + //
                "\t\t\"currpe\": 80.8,\r\n" + //
                "\t\t\"pe5y\": 38.9,\r\n" + //
                "\t\t\"mcap2sales\": 6.96,\r\n" + //
                "\t\t\"mcap2sales3y\": 1.1,\r\n" + //
                "\t\t\"epsttm\": 10.8,\r\n" + //
                "\t\t\"revgr3y\": 20.3,\r\n" + //
                "\t\t\"patgr3y\": 83.6,\r\n" + //
                "\t\t\"ttmpatgr\": 1044.0,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 45.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 40.0,\r\n" + //
                "\t\t\"baseCaseTP\": 1910.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 22.0,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 50.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 40.0,\r\n" + //
                "\t\t\"bullCaseTP\": 2187.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 26.2,\r\n" + //
                "\t\t\"bullwtRatio\": 0.9,\r\n" + //
                "\t\t\"peg\": 1.41,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"TIMETECHNO\",\r\n" + //
                "\t\t\"rating\": \"Hold\",\r\n" + //
                "\t\t\"avgReturns\": 0.0,\r\n" + //
                "\t\t\"mcapCr\": 8386.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 0,\r\n" + //
                "\t\t\"dailyPL\": \"-5.50%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 0.0,\r\n" + //
                "\t\t\"cmp\": 370.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 100.0,\r\n" + //
                "\t\t\"currpe\": 23.2,\r\n" + //
                "\t\t\"pe5y\": 12.2,\r\n" + //
                "\t\t\"mcap2sales\": 1.58,\r\n" + //
                "\t\t\"mcap2sales3y\": 1.4,\r\n" + //
                "\t\t\"epsttm\": 15.9,\r\n" + //
                "\t\t\"revgr3y\": 18.4,\r\n" + //
                "\t\t\"patgr3y\": 43.0,\r\n" + //
                "\t\t\"ttmpatgr\": 43.9,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 0.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 0.0,\r\n" + //
                "\t\t\"baseCaseTP\": 0.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 0.0,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 0.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 0.0,\r\n" + //
                "\t\t\"bullCaseTP\": 0.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 0.0,\r\n" + //
                "\t\t\"bullwtRatio\": 0.0,\r\n" + //
                "\t\t\"peg\": 2.78,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"TIPSMUSIC\",\r\n" + //
                "\t\t\"rating\": \"Hold\",\r\n" + //
                "\t\t\"avgReturns\": 12.5,\r\n" + //
                "\t\t\"mcapCr\": 8046.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 4,\r\n" + //
                "\t\t\"dailyPL\": \"None%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 414.0,\r\n" + //
                "\t\t\"cmp\": 629.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 34.0,\r\n" + //
                "\t\t\"currpe\": 49.8,\r\n" + //
                "\t\t\"pe5y\": 32.0,\r\n" + //
                "\t\t\"mcap2sales\": 27.2,\r\n" + //
                "\t\t\"mcap2sales3y\": 19.9,\r\n" + //
                "\t\t\"epsttm\": 12.6,\r\n" + //
                "\t\t\"revgr3y\": 38.7,\r\n" + //
                "\t\t\"patgr3y\": 42.8,\r\n" + //
                "\t\t\"ttmpatgr\": 35.1,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 25.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 32.0,\r\n" + //
                "\t\t\"baseCaseTP\": 984.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 11.8,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 27.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 34.0,\r\n" + //
                "\t\t\"bullCaseTP\": 1114.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 15.4,\r\n" + //
                "\t\t\"bullwtRatio\": 0.8,\r\n" + //
                "\t\t\"peg\": 0.38,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"TRENT\",\r\n" + //
                "\t\t\"rating\": \"Buy\",\r\n" + //
                "\t\t\"avgReturns\": 23.7,\r\n" + //
                "\t\t\"mcapCr\": 184900.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 4,\r\n" + //
                "\t\t\"dailyPL\": \"0.13%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 5003.0,\r\n" + //
                "\t\t\"cmp\": 5201.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 4.0,\r\n" + //
                "\t\t\"currpe\": 124.0,\r\n" + //
                "\t\t\"pe5y\": 182.0,\r\n" + //
                "\t\t\"mcap2sales\": 11.4,\r\n" + //
                "\t\t\"mcap2sales3y\": 10.5,\r\n" + //
                "\t\t\"epsttm\": 54.4,\r\n" + //
                "\t\t\"revgr3y\": 68.4,\r\n" + //
                "\t\t\"patgr3y\": 101.0,\r\n" + //
                "\t\t\"ttmpatgr\": 82.9,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 30.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 75.0,\r\n" + //
                "\t\t\"baseCaseTP\": 11653.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 22.3,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 35.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 80.0,\r\n" + //
                "\t\t\"bullCaseTP\": 14455.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 29.1,\r\n" + //
                "\t\t\"bullwtRatio\": 0.9,\r\n" + //
                "\t\t\"peg\": 2.2,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"VBL\",\r\n" + //
                "\t\t\"rating\": \"Hold\",\r\n" + //
                "\t\t\"avgReturns\": 17.8,\r\n" + //
                "\t\t\"mcapCr\": 175822.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 5,\r\n" + //
                "\t\t\"dailyPL\": \"-5.21%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 388.0,\r\n" + //
                "\t\t\"cmp\": 520.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 25.0,\r\n" + //
                "\t\t\"currpe\": 67.7,\r\n" + //
                "\t\t\"pe5y\": 65.6,\r\n" + //
                "\t\t\"mcap2sales\": 8.79,\r\n" + //
                "\t\t\"mcap2sales3y\": 9.5,\r\n" + //
                "\t\t\"epsttm\": 7.67,\r\n" + //
                "\t\t\"revgr3y\": 31.4,\r\n" + //
                "\t\t\"patgr3y\": 54.3,\r\n" + //
                "\t\t\"ttmpatgr\": 22.4,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 22.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 55.0,\r\n" + //
                "\t\t\"baseCaseTP\": 1140.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 17.0,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 25.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 58.0,\r\n" + //
                "\t\t\"bullCaseTP\": 1358.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 21.2,\r\n" + //
                "\t\t\"bullwtRatio\": 0.9,\r\n" + //
                "\t\t\"peg\": 1.66,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"VENUSPIPES\",\r\n" + //
                "\t\t\"rating\": \"Hold\",\r\n" + //
                "\t\t\"avgReturns\": 15.6,\r\n" + //
                "\t\t\"mcapCr\": 2806.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 4,\r\n" + //
                "\t\t\"dailyPL\": \"-1.45%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 1011.0,\r\n" + //
                "\t\t\"cmp\": 1378.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 27.0,\r\n" + //
                "\t\t\"currpe\": 28.2,\r\n" + //
                "\t\t\"pe5y\": 42.9,\r\n" + //
                "\t\t\"mcap2sales\": 3.12,\r\n" + //
                "\t\t\"mcap2sales3y\": 4.0,\r\n" + //
                "\t\t\"epsttm\": 49.0,\r\n" + //
                "\t\t\"revgr3y\": 37.4,\r\n" + //
                "\t\t\"patgr3y\": 53.7,\r\n" + //
                "\t\t\"ttmpatgr\": 59.6,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 20.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 23.0,\r\n" + //
                "\t\t\"baseCaseTP\": 2337.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 14.1,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 25.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 25.0,\r\n" + //
                "\t\t\"bullCaseTP\": 2991.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 21.4,\r\n" + //
                "\t\t\"bullwtRatio\": 0.8,\r\n" + //
                "\t\t\"peg\": 0.32,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"WAAREEENER\",\r\n" + //
                "\t\t\"rating\": \"Buy\",\r\n" + //
                "\t\t\"avgReturns\": 22.1,\r\n" + //
                "\t\t\"mcapCr\": 62665.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 4,\r\n" + //
                "\t\t\"dailyPL\": \"-3.74%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 1998.0,\r\n" + //
                "\t\t\"cmp\": 2181.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 8.0,\r\n" + //
                "\t\t\"currpe\": 63.4,\r\n" + //
                "\t\t\"pe5y\": 54.3,\r\n" + //
                "\t\t\"mcap2sales\": 5.5,\r\n" + //
                "\t\t\"mcap2sales3y\": 7.3,\r\n" + //
                "\t\t\"epsttm\": 62.8,\r\n" + //
                "\t\t\"revgr3y\": 80.0,\r\n" + //
                "\t\t\"patgr3y\": 180.0,\r\n" + //
                "\t\t\"ttmpatgr\": 100.0,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 35.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 22.0,\r\n" + //
                "\t\t\"baseCaseTP\": 4589.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 20.4,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 40.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 25.0,\r\n" + //
                "\t\t\"bullCaseTP\": 6031.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 29.0,\r\n" + //
                "\t\t\"bullwtRatio\": 0.8,\r\n" + //
                "\t\t\"peg\": 0.0,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"ZAGGLE\",\r\n" + //
                "\t\t\"rating\": \"Buy\",\r\n" + //
                "\t\t\"avgReturns\": 22.8,\r\n" + //
                "\t\t\"mcapCr\": 5125.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 5,\r\n" + //
                "\t\t\"dailyPL\": \"-9.99%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 351.0,\r\n" + //
                "\t\t\"cmp\": 382.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": 8.0,\r\n" + //
                "\t\t\"currpe\": 67.5,\r\n" + //
                "\t\t\"pe5y\": 89.0,\r\n" + //
                "\t\t\"mcap2sales\": 4.4,\r\n" + //
                "\t\t\"mcap2sales3y\": 5.1,\r\n" + //
                "\t\t\"epsttm\": 6.06,\r\n" + //
                "\t\t\"revgr3y\": 0.0,\r\n" + //
                "\t\t\"patgr3y\": 0.0,\r\n" + //
                "\t\t\"ttmpatgr\": 134.0,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 30.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 45.0,\r\n" + //
                "\t\t\"baseCaseTP\": 1013.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 21.5,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 35.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 48.0,\r\n" + //
                "\t\t\"bullCaseTP\": 1304.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 27.8,\r\n" + //
                "\t\t\"bullwtRatio\": 0.8,\r\n" + //
                "\t\t\"peg\": 0.0,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t},\r\n" + //
                "\t{\r\n" + //
                "\t\t\"scrip\": \"ZENTEC\",\r\n" + //
                "\t\t\"rating\": \"StrongBuy\",\r\n" + //
                "\t\t\"avgReturns\": 27.2,\r\n" + //
                "\t\t\"mcapCr\": 13506.0,\r\n" + //
                "\t\t\"longEPSCAGR\": 3,\r\n" + //
                "\t\t\"dailyPL\": \"-7.59%\",\r\n" + //
                "\t\t\"price2StrongBuy\": 1582.0,\r\n" + //
                "\t\t\"cmp\": 1496.0,\r\n" + //
                "\t\t\"deltaStrongBuyCMP\": -6.0,\r\n" + //
                "\t\t\"currpe\": 66.6,\r\n" + //
                "\t\t\"pe5y\": 81.0,\r\n" + //
                "\t\t\"mcap2sales\": 18.3,\r\n" + //
                "\t\t\"mcap2sales3y\": 22.4,\r\n" + //
                "\t\t\"epsttm\": 23.9,\r\n" + //
                "\t\t\"revgr3y\": 100.0,\r\n" + //
                "\t\t\"patgr3y\": 257.0,\r\n" + //
                "\t\t\"ttmpatgr\": 127.0,\r\n" + //
                "\t\t\"baseCaseEPSCAGR\": 40.0,\r\n" + //
                "\t\t\"baseCaseTermPE\": 45.0,\r\n" + //
                "\t\t\"baseCaseTP\": 2951.0,\r\n" + //
                "\t\t\"baseCaseRetCAGR\": 25.4,\r\n" + //
                "\t\t\"bullCaseEPSCAGR\": 45.0,\r\n" + //
                "\t\t\"bullCaseTermPE\": 50.0,\r\n" + //
                "\t\t\"bullCaseTP\": 3643.0,\r\n" + //
                "\t\t\"bullCaseRetCAGR\": 34.5,\r\n" + //
                "\t\t\"bullwtRatio\": 0.8,\r\n" + //
                "\t\t\"peg\": 1.17,\r\n" + //
                "\t\t\"exitKeyVals\": []\r\n" + //
                "\t}\r\n" + //
                "]";

        return jsonString;

    }

    public static String getPFJSON()
    {
        String jsonString = "{\r\n" + //
                "\t\"pfHeader\": {\r\n" + //
                "\t\t\"user\": \"admin\",\r\n" + //
                "\t\t\"totalInvestment\": 9386877.0,\r\n" + //
                "\t\t\"totalInvestmentM\": \"93.87 Lacs\",\r\n" + //
                "\t\t\"totalCurrentValue\": 7855849.0,\r\n" + //
                "\t\t\"totalCurrentValueM\": \"78.56 Lacs\",\r\n" + //
                "\t\t\"totalPL\": -1531028.0,\r\n" + //
                "\t\t\"totalPLM\": \"-15.31 Lacs\",\r\n" + //
                "\t\t\"totalPLPer\": -16.3,\r\n" + //
                "\t\t\"dayPL\": -77115.0,\r\n" + //
                "\t\t\"dayPLM\": \"-77.1 K\",\r\n" + //
                "\t\t\"dayPLPer\": -98.1\r\n" + //
                "\t},\r\n" + //
                "\t\"pfItems\": [\r\n" + //
                "\t\t{\r\n" + //
                "\t\t\t\"scrip\": \"ABB\",\r\n" + //
                "\t\t\t\"units\": 40.0,\r\n" + //
                "\t\t\t\"ppu\": 5157.68,\r\n" + //
                "\t\t\t\"cmp\": 5116.0,\r\n" + //
                "\t\t\t\"daysChg\": \"-0.28%\",\r\n" + //
                "\t\t\t\"daysChgD\": -0.28,\r\n" + //
                "\t\t\t\"inv\": 206307.0,\r\n" + //
                "\t\t\t\"invM\": \"2.06 Lacs\",\r\n" + //
                "\t\t\t\"perByInv\": 2.2,\r\n" + //
                "\t\t\t\"curVal\": 204640.0,\r\n" + //
                "\t\t\t\"curValM\": \"2.05 Lacs\",\r\n" + //
                "\t\t\t\"perByCurVal\": 2.6,\r\n" + //
                "\t\t\t\"plPer\": -0.8,\r\n" + //
                "\t\t\t\"plAbs\": -1667.0,\r\n" + //
                "\t\t\t\"plAbsM\": \"-1.67 K\",\r\n" + //
                "\t\t\t\"plPerDay\": -573.0,\r\n" + //
                "\t\t\t\"plPerDayM\": \"-573.0\"\r\n" + //
                "\t\t},\r\n" + //
                "\t\t{\r\n" + //
                "\t\t\t\"scrip\": \"ARROWGREEN\",\r\n" + //
                "\t\t\t\"units\": 254.0,\r\n" + //
                "\t\t\t\"ppu\": 646.47,\r\n" + //
                "\t\t\t\"cmp\": 530.0,\r\n" + //
                "\t\t\t\"daysChg\": \"0.08%\",\r\n" + //
                "\t\t\t\"daysChgD\": 0.08,\r\n" + //
                "\t\t\t\"inv\": 164203.0,\r\n" + //
                "\t\t\t\"invM\": \"1.64 Lacs\",\r\n" + //
                "\t\t\t\"perByInv\": 1.7,\r\n" + //
                "\t\t\t\"curVal\": 134620.0,\r\n" + //
                "\t\t\t\"curValM\": \"1.35 Lacs\",\r\n" + //
                "\t\t\t\"perByCurVal\": 1.7,\r\n" + //
                "\t\t\t\"plPer\": -18.0,\r\n" + //
                "\t\t\t\"plAbs\": -29583.0,\r\n" + //
                "\t\t\t\"plAbsM\": \"-29.58 K\",\r\n" + //
                "\t\t\t\"plPerDay\": 107.7,\r\n" + //
                "\t\t\t\"plPerDayM\": \"107.7\"\r\n" + //
                "\t\t},\r\n" + //
                "\t\t{\r\n" + //
                "\t\t\t\"scrip\": \"AURIONPRO\",\r\n" + //
                "\t\t\t\"units\": 190.0,\r\n" + //
                "\t\t\t\"ppu\": 1746.52,\r\n" + //
                "\t\t\t\"cmp\": 1319.0,\r\n" + //
                "\t\t\t\"daysChg\": \"-4.45%\",\r\n" + //
                "\t\t\t\"daysChgD\": -4.45,\r\n" + //
                "\t\t\t\"inv\": 331839.0,\r\n" + //
                "\t\t\t\"invM\": \"3.32 Lacs\",\r\n" + //
                "\t\t\t\"perByInv\": 3.5,\r\n" + //
                "\t\t\t\"curVal\": 250610.0,\r\n" + //
                "\t\t\t\"curValM\": \"2.51 Lacs\",\r\n" + //
                "\t\t\t\"perByCurVal\": 3.2,\r\n" + //
                "\t\t\t\"plPer\": -24.5,\r\n" + //
                "\t\t\t\"plAbs\": -81229.0,\r\n" + //
                "\t\t\t\"plAbsM\": \"-81.23 K\",\r\n" + //
                "\t\t\t\"plPerDay\": -11152.1,\r\n" + //
                "\t\t\t\"plPerDayM\": \"-11.15 K\"\r\n" + //
                "\t\t},\r\n" + //
                "\t\t{\r\n" + //
                "\t\t\t\"scrip\": \"BALUFORGE\",\r\n" + //
                "\t\t\t\"units\": 379.0,\r\n" + //
                "\t\t\t\"ppu\": 571.06,\r\n" + //
                "\t\t\t\"cmp\": 453.0,\r\n" + //
                "\t\t\t\"daysChg\": \"-1.03%\",\r\n" + //
                "\t\t\t\"daysChgD\": -1.03,\r\n" + //
                "\t\t\t\"inv\": 216432.0,\r\n" + //
                "\t\t\t\"invM\": \"2.16 Lacs\",\r\n" + //
                "\t\t\t\"perByInv\": 2.3,\r\n" + //
                "\t\t\t\"curVal\": 171687.0,\r\n" + //
                "\t\t\t\"curValM\": \"1.72 Lacs\",\r\n" + //
                "\t\t\t\"perByCurVal\": 2.2,\r\n" + //
                "\t\t\t\"plPer\": -20.7,\r\n" + //
                "\t\t\t\"plAbs\": -44745.0,\r\n" + //
                "\t\t\t\"plAbsM\": \"-44.74 K\",\r\n" + //
                "\t\t\t\"plPerDay\": -1768.4,\r\n" + //
                "\t\t\t\"plPerDayM\": \"-1.77 K\"\r\n" + //
                "\t\t},\r\n" + //
                "\t\t{\r\n" + //
                "\t\t\t\"scrip\": \"BLS\",\r\n" + //
                "\t\t\t\"units\": 510.0,\r\n" + //
                "\t\t\t\"ppu\": 373.49,\r\n" + //
                "\t\t\t\"cmp\": 328.0,\r\n" + //
                "\t\t\t\"daysChg\": \"0.20%\",\r\n" + //
                "\t\t\t\"daysChgD\": 0.2,\r\n" + //
                "\t\t\t\"inv\": 190480.0,\r\n" + //
                "\t\t\t\"invM\": \"1.9 Lacs\",\r\n" + //
                "\t\t\t\"perByInv\": 2.0,\r\n" + //
                "\t\t\t\"curVal\": 167280.0,\r\n" + //
                "\t\t\t\"curValM\": \"1.67 Lacs\",\r\n" + //
                "\t\t\t\"perByCurVal\": 2.1,\r\n" + //
                "\t\t\t\"plPer\": -12.2,\r\n" + //
                "\t\t\t\"plAbs\": -23200.0,\r\n" + //
                "\t\t\t\"plAbsM\": \"-23.2 K\",\r\n" + //
                "\t\t\t\"plPerDay\": 334.6,\r\n" + //
                "\t\t\t\"plPerDayM\": \"334.6\"\r\n" + //
                "\t\t},\r\n" + //
                "\t\t{\r\n" + //
                "\t\t\t\"scrip\": \"CDSL\",\r\n" + //
                "\t\t\t\"units\": 190.0,\r\n" + //
                "\t\t\t\"ppu\": 1736.8,\r\n" + //
                "\t\t\t\"cmp\": 1091.0,\r\n" + //
                "\t\t\t\"daysChg\": \"-1.21%\",\r\n" + //
                "\t\t\t\"daysChgD\": -1.21,\r\n" + //
                "\t\t\t\"inv\": 329992.0,\r\n" + //
                "\t\t\t\"invM\": \"3.3 Lacs\",\r\n" + //
                "\t\t\t\"perByInv\": 3.5,\r\n" + //
                "\t\t\t\"curVal\": 207290.0,\r\n" + //
                "\t\t\t\"curValM\": \"2.07 Lacs\",\r\n" + //
                "\t\t\t\"perByCurVal\": 2.6,\r\n" + //
                "\t\t\t\"plPer\": -37.2,\r\n" + //
                "\t\t\t\"plAbs\": -122702.0,\r\n" + //
                "\t\t\t\"plAbsM\": \"-1.23 Lacs\",\r\n" + //
                "\t\t\t\"plPerDay\": -2508.2,\r\n" + //
                "\t\t\t\"plPerDayM\": \"-2.51 K\"\r\n" + //
                "\t\t},\r\n" + //
                "\t\t{\r\n" + //
                "\t\t\t\"scrip\": \"CUMMINSIND\",\r\n" + //
                "\t\t\t\"units\": 70.0,\r\n" + //
                "\t\t\t\"ppu\": 2718.24,\r\n" + //
                "\t\t\t\"cmp\": 2791.0,\r\n" + //
                "\t\t\t\"daysChg\": \"-2.96%\",\r\n" + //
                "\t\t\t\"daysChgD\": -2.96,\r\n" + //
                "\t\t\t\"inv\": 190277.0,\r\n" + //
                "\t\t\t\"invM\": \"1.9 Lacs\",\r\n" + //
                "\t\t\t\"perByInv\": 2.0,\r\n" + //
                "\t\t\t\"curVal\": 195370.0,\r\n" + //
                "\t\t\t\"curValM\": \"1.95 Lacs\",\r\n" + //
                "\t\t\t\"perByCurVal\": 2.5,\r\n" + //
                "\t\t\t\"plPer\": 2.7,\r\n" + //
                "\t\t\t\"plAbs\": 5093.0,\r\n" + //
                "\t\t\t\"plAbsM\": \"5.09 K\",\r\n" + //
                "\t\t\t\"plPerDay\": -5783.0,\r\n" + //
                "\t\t\t\"plPerDayM\": \"-5.78 K\"\r\n" + //
                "\t\t},\r\n" + //
                "\t\t{\r\n" + //
                "\t\t\t\"scrip\": \"DOMS\",\r\n" + //
                "\t\t\t\"units\": 80.0,\r\n" + //
                "\t\t\t\"ppu\": 2130.23,\r\n" + //
                "\t\t\t\"cmp\": 2802.0,\r\n" + //
                "\t\t\t\"daysChg\": \"2.39%\",\r\n" + //
                "\t\t\t\"daysChgD\": 2.39,\r\n" + //
                "\t\t\t\"inv\": 170418.0,\r\n" + //
                "\t\t\t\"invM\": \"1.7 Lacs\",\r\n" + //
                "\t\t\t\"perByInv\": 1.8,\r\n" + //
                "\t\t\t\"curVal\": 224160.0,\r\n" + //
                "\t\t\t\"curValM\": \"2.24 Lacs\",\r\n" + //
                "\t\t\t\"perByCurVal\": 2.9,\r\n" + //
                "\t\t\t\"plPer\": 31.5,\r\n" + //
                "\t\t\t\"plAbs\": 53742.0,\r\n" + //
                "\t\t\t\"plAbsM\": \"53.74 K\",\r\n" + //
                "\t\t\t\"plPerDay\": 5357.4,\r\n" + //
                "\t\t\t\"plPerDayM\": \"5.36 K\"\r\n" + //
                "\t\t},\r\n" + //
                "\t\t{\r\n" + //
                "\t\t\t\"scrip\": \"DSSL\",\r\n" + //
                "\t\t\t\"units\": 222.0,\r\n" + //
                "\t\t\t\"ppu\": 1086.41,\r\n" + //
                "\t\t\t\"cmp\": 1002.0,\r\n" + //
                "\t\t\t\"daysChg\": \"-0.51%\",\r\n" + //
                "\t\t\t\"daysChgD\": -0.51,\r\n" + //
                "\t\t\t\"inv\": 241183.0,\r\n" + //
                "\t\t\t\"invM\": \"2.41 Lacs\",\r\n" + //
                "\t\t\t\"perByInv\": 2.6,\r\n" + //
                "\t\t\t\"curVal\": 222444.0,\r\n" + //
                "\t\t\t\"curValM\": \"2.22 Lacs\",\r\n" + //
                "\t\t\t\"perByCurVal\": 2.8,\r\n" + //
                "\t\t\t\"plPer\": -7.8,\r\n" + //
                "\t\t\t\"plAbs\": -18739.0,\r\n" + //
                "\t\t\t\"plAbsM\": \"-18.74 K\",\r\n" + //
                "\t\t\t\"plPerDay\": -1134.5,\r\n" + //
                "\t\t\t\"plPerDayM\": \"-1.13 K\"\r\n" + //
                "\t\t},\r\n" + //
                "\t\t{\r\n" + //
                "\t\t\t\"scrip\": \"DYCL\",\r\n" + //
                "\t\t\t\"units\": 287.0,\r\n" + //
                "\t\t\t\"ppu\": 643.16,\r\n" + //
                "\t\t\t\"cmp\": 593.0,\r\n" + //
                "\t\t\t\"daysChg\": \"-2.35%\",\r\n" + //
                "\t\t\t\"daysChgD\": -2.35,\r\n" + //
                "\t\t\t\"inv\": 184587.0,\r\n" + //
                "\t\t\t\"invM\": \"1.85 Lacs\",\r\n" + //
                "\t\t\t\"perByInv\": 2.0,\r\n" + //
                "\t\t\t\"curVal\": 170191.0,\r\n" + //
                "\t\t\t\"curValM\": \"1.7 Lacs\",\r\n" + //
                "\t\t\t\"perByCurVal\": 2.2,\r\n" + //
                "\t\t\t\"plPer\": -7.8,\r\n" + //
                "\t\t\t\"plAbs\": -14396.0,\r\n" + //
                "\t\t\t\"plAbsM\": \"-14.4 K\",\r\n" + //
                "\t\t\t\"plPerDay\": -3999.5,\r\n" + //
                "\t\t\t\"plPerDayM\": \"-4.0 K\"\r\n" + //
                "\t\t},\r\n" + //
                "\t\t{\r\n" + //
                "\t\t\t\"scrip\": \"ETHOSLTD\",\r\n" + //
                "\t\t\t\"units\": 114.0,\r\n" + //
                "\t\t\t\"ppu\": 3065.1,\r\n" + //
                "\t\t\t\"cmp\": 2488.0,\r\n" + //
                "\t\t\t\"daysChg\": \"-0.56%\",\r\n" + //
                "\t\t\t\"daysChgD\": -0.56,\r\n" + //
                "\t\t\t\"inv\": 349421.0,\r\n" + //
                "\t\t\t\"invM\": \"3.49 Lacs\",\r\n" + //
                "\t\t\t\"perByInv\": 3.7,\r\n" + //
                "\t\t\t\"curVal\": 283632.0,\r\n" + //
                "\t\t\t\"curValM\": \"2.84 Lacs\",\r\n" + //
                "\t\t\t\"perByCurVal\": 3.6,\r\n" + //
                "\t\t\t\"plPer\": -18.8,\r\n" + //
                "\t\t\t\"plAbs\": -65789.0,\r\n" + //
                "\t\t\t\"plAbsM\": \"-65.79 K\",\r\n" + //
                "\t\t\t\"plPerDay\": -1588.3,\r\n" + //
                "\t\t\t\"plPerDayM\": \"-1.59 K\"\r\n" + //
                "\t\t},\r\n" + //
                "\t\t{\r\n" + //
                "\t\t\t\"scrip\": \"GANESHHOUC\",\r\n" + //
                "\t\t\t\"units\": 304.0,\r\n" + //
                "\t\t\t\"ppu\": 1268.7,\r\n" + //
                "\t\t\t\"cmp\": 1008.0,\r\n" + //
                "\t\t\t\"daysChg\": \"-2.78%\",\r\n" + //
                "\t\t\t\"daysChgD\": -2.78,\r\n" + //
                "\t\t\t\"inv\": 385685.0,\r\n" + //
                "\t\t\t\"invM\": \"3.86 Lacs\",\r\n" + //
                "\t\t\t\"perByInv\": 4.1,\r\n" + //
                "\t\t\t\"curVal\": 306432.0,\r\n" + //
                "\t\t\t\"curValM\": \"3.06 Lacs\",\r\n" + //
                "\t\t\t\"perByCurVal\": 3.9,\r\n" + //
                "\t\t\t\"plPer\": -20.5,\r\n" + //
                "\t\t\t\"plAbs\": -79253.0,\r\n" + //
                "\t\t\t\"plAbsM\": \"-79.25 K\",\r\n" + //
                "\t\t\t\"plPerDay\": -8518.8,\r\n" + //
                "\t\t\t\"plPerDayM\": \"-8.52 K\"\r\n" + //
                "\t\t},\r\n" + //
                "\t\t{\r\n" + //
                "\t\t\t\"scrip\": \"GRAVITA\",\r\n" + //
                "\t\t\t\"units\": 127.0,\r\n" + //
                "\t\t\t\"ppu\": 1690.71,\r\n" + //
                "\t\t\t\"cmp\": 1613.0,\r\n" + //
                "\t\t\t\"daysChg\": \"-2.44%\",\r\n" + //
                "\t\t\t\"daysChgD\": -2.44,\r\n" + //
                "\t\t\t\"inv\": 214720.0,\r\n" + //
                "\t\t\t\"invM\": \"2.15 Lacs\",\r\n" + //
                "\t\t\t\"perByInv\": 2.3,\r\n" + //
                "\t\t\t\"curVal\": 204851.0,\r\n" + //
                "\t\t\t\"curValM\": \"2.05 Lacs\",\r\n" + //
                "\t\t\t\"perByCurVal\": 2.6,\r\n" + //
                "\t\t\t\"plPer\": -4.6,\r\n" + //
                "\t\t\t\"plAbs\": -9869.0,\r\n" + //
                "\t\t\t\"plAbsM\": \"-9.87 K\",\r\n" + //
                "\t\t\t\"plPerDay\": -4998.4,\r\n" + //
                "\t\t\t\"plPerDayM\": \"-5.0 K\"\r\n" + //
                "\t\t},\r\n" + //
                "\t\t{\r\n" + //
                "\t\t\t\"scrip\": \"GRWRHITECH\",\r\n" + //
                "\t\t\t\"units\": 99.0,\r\n" + //
                "\t\t\t\"ppu\": 4505.77,\r\n" + //
                "\t\t\t\"cmp\": 3926.0,\r\n" + //
                "\t\t\t\"daysChg\": \"-0.61%\",\r\n" + //
                "\t\t\t\"daysChgD\": -0.61,\r\n" + //
                "\t\t\t\"inv\": 446071.0,\r\n" + //
                "\t\t\t\"invM\": \"4.46 Lacs\",\r\n" + //
                "\t\t\t\"perByInv\": 4.8,\r\n" + //
                "\t\t\t\"curVal\": 388674.0,\r\n" + //
                "\t\t\t\"curValM\": \"3.89 Lacs\",\r\n" + //
                "\t\t\t\"perByCurVal\": 4.9,\r\n" + //
                "\t\t\t\"plPer\": -12.9,\r\n" + //
                "\t\t\t\"plAbs\": -57397.0,\r\n" + //
                "\t\t\t\"plAbsM\": \"-57.4 K\",\r\n" + //
                "\t\t\t\"plPerDay\": -2370.9,\r\n" + //
                "\t\t\t\"plPerDayM\": \"-2.37 K\"\r\n" + //
                "\t\t},\r\n" + //
                "\t\t{\r\n" + //
                "\t\t\t\"scrip\": \"HBLENGINE\",\r\n" + //
                "\t\t\t\"units\": 583.0,\r\n" + //
                "\t\t\t\"ppu\": 598.22,\r\n" + //
                "\t\t\t\"cmp\": 424.0,\r\n" + //
                "\t\t\t\"daysChg\": \"-3.18%\",\r\n" + //
                "\t\t\t\"daysChgD\": -3.18,\r\n" + //
                "\t\t\t\"inv\": 348762.0,\r\n" + //
                "\t\t\t\"invM\": \"3.49 Lacs\",\r\n" + //
                "\t\t\t\"perByInv\": 3.7,\r\n" + //
                "\t\t\t\"curVal\": 247192.0,\r\n" + //
                "\t\t\t\"curValM\": \"2.47 Lacs\",\r\n" + //
                "\t\t\t\"perByCurVal\": 3.1,\r\n" + //
                "\t\t\t\"plPer\": -29.1,\r\n" + //
                "\t\t\t\"plAbs\": -101570.0,\r\n" + //
                "\t\t\t\"plAbsM\": \"-1.02 Lacs\",\r\n" + //
                "\t\t\t\"plPerDay\": -7860.7,\r\n" + //
                "\t\t\t\"plPerDayM\": \"-7.86 K\"\r\n" + //
                "\t\t},\r\n" + //
                "\t\t{\r\n" + //
                "\t\t\t\"scrip\": \"KAYNES\",\r\n" + //
                "\t\t\t\"units\": 144.0,\r\n" + //
                "\t\t\t\"ppu\": 5391.14,\r\n" + //
                "\t\t\t\"cmp\": 4365.0,\r\n" + //
                "\t\t\t\"daysChg\": \"1.38%\",\r\n" + //
                "\t\t\t\"daysChgD\": 1.38,\r\n" + //
                "\t\t\t\"inv\": 776324.0,\r\n" + //
                "\t\t\t\"invM\": \"7.76 Lacs\",\r\n" + //
                "\t\t\t\"perByInv\": 8.3,\r\n" + //
                "\t\t\t\"curVal\": 628560.0,\r\n" + //
                "\t\t\t\"curValM\": \"6.29 Lacs\",\r\n" + //
                "\t\t\t\"perByCurVal\": 8.0,\r\n" + //
                "\t\t\t\"plPer\": -19.0,\r\n" + //
                "\t\t\t\"plAbs\": -147764.0,\r\n" + //
                "\t\t\t\"plAbsM\": \"-1.48 Lacs\",\r\n" + //
                "\t\t\t\"plPerDay\": 8674.1,\r\n" + //
                "\t\t\t\"plPerDayM\": \"8.67 K\"\r\n" + //
                "\t\t},\r\n" + //
                "\t\t{\r\n" + //
                "\t\t\t\"scrip\": \"KFINTECH\",\r\n" + //
                "\t\t\t\"units\": 337.0,\r\n" + //
                "\t\t\t\"ppu\": 1114.29,\r\n" + //
                "\t\t\t\"cmp\": 921.0,\r\n" + //
                "\t\t\t\"daysChg\": \"0.85%\",\r\n" + //
                "\t\t\t\"daysChgD\": 0.85,\r\n" + //
                "\t\t\t\"inv\": 375516.0,\r\n" + //
                "\t\t\t\"invM\": \"3.76 Lacs\",\r\n" + //
                "\t\t\t\"perByInv\": 4.0,\r\n" + //
                "\t\t\t\"curVal\": 310377.0,\r\n" + //
                "\t\t\t\"curValM\": \"3.1 Lacs\",\r\n" + //
                "\t\t\t\"perByCurVal\": 4.0,\r\n" + //
                "\t\t\t\"plPer\": -17.3,\r\n" + //
                "\t\t\t\"plAbs\": -65139.0,\r\n" + //
                "\t\t\t\"plAbsM\": \"-65.14 K\",\r\n" + //
                "\t\t\t\"plPerDay\": 2638.2,\r\n" + //
                "\t\t\t\"plPerDayM\": \"2.64 K\"\r\n" + //
                "\t\t},\r\n" + //
                "\t\t{\r\n" + //
                "\t\t\t\"scrip\": \"MOTILALOFS\",\r\n" + //
                "\t\t\t\"units\": 414.0,\r\n" + //
                "\t\t\t\"ppu\": 925.66,\r\n" + //
                "\t\t\t\"cmp\": 585.0,\r\n" + //
                "\t\t\t\"daysChg\": \"-1.16%\",\r\n" + //
                "\t\t\t\"daysChgD\": -1.16,\r\n" + //
                "\t\t\t\"inv\": 383223.0,\r\n" + //
                "\t\t\t\"invM\": \"3.83 Lacs\",\r\n" + //
                "\t\t\t\"perByInv\": 4.1,\r\n" + //
                "\t\t\t\"curVal\": 242190.0,\r\n" + //
                "\t\t\t\"curValM\": \"2.42 Lacs\",\r\n" + //
                "\t\t\t\"perByCurVal\": 3.1,\r\n" + //
                "\t\t\t\"plPer\": -36.8,\r\n" + //
                "\t\t\t\"plAbs\": -141033.0,\r\n" + //
                "\t\t\t\"plAbsM\": \"-1.41 Lacs\",\r\n" + //
                "\t\t\t\"plPerDay\": -2809.4,\r\n" + //
                "\t\t\t\"plPerDayM\": \"-2.81 K\"\r\n" + //
                "\t\t},\r\n" + //
                "\t\t{\r\n" + //
                "\t\t\t\"scrip\": \"NUVAMA\",\r\n" + //
                "\t\t\t\"units\": 40.0,\r\n" + //
                "\t\t\t\"ppu\": 5295.45,\r\n" + //
                "\t\t\t\"cmp\": 5198.0,\r\n" + //
                "\t\t\t\"daysChg\": \"1.18%\",\r\n" + //
                "\t\t\t\"daysChgD\": 1.18,\r\n" + //
                "\t\t\t\"inv\": 211818.0,\r\n" + //
                "\t\t\t\"invM\": \"2.12 Lacs\",\r\n" + //
                "\t\t\t\"perByInv\": 2.3,\r\n" + //
                "\t\t\t\"curVal\": 207920.0,\r\n" + //
                "\t\t\t\"curValM\": \"2.08 Lacs\",\r\n" + //
                "\t\t\t\"perByCurVal\": 2.6,\r\n" + //
                "\t\t\t\"plPer\": -1.8,\r\n" + //
                "\t\t\t\"plAbs\": -3898.0,\r\n" + //
                "\t\t\t\"plAbsM\": \"-3.9 K\",\r\n" + //
                "\t\t\t\"plPerDay\": 2453.5,\r\n" + //
                "\t\t\t\"plPerDayM\": \"2.45 K\"\r\n" + //
                "\t\t},\r\n" + //
                "\t\t{\r\n" + //
                "\t\t\t\"scrip\": \"PGEL\",\r\n" + //
                "\t\t\t\"units\": 712.0,\r\n" + //
                "\t\t\t\"ppu\": 870.2,\r\n" + //
                "\t\t\t\"cmp\": 860.0,\r\n" + //
                "\t\t\t\"daysChg\": \"-3.67%\",\r\n" + //
                "\t\t\t\"daysChgD\": -3.67,\r\n" + //
                "\t\t\t\"inv\": 619582.0,\r\n" + //
                "\t\t\t\"invM\": \"6.2 Lacs\",\r\n" + //
                "\t\t\t\"perByInv\": 6.6,\r\n" + //
                "\t\t\t\"curVal\": 612320.0,\r\n" + //
                "\t\t\t\"curValM\": \"6.12 Lacs\",\r\n" + //
                "\t\t\t\"perByCurVal\": 7.8,\r\n" + //
                "\t\t\t\"plPer\": -1.2,\r\n" + //
                "\t\t\t\"plAbs\": -7262.0,\r\n" + //
                "\t\t\t\"plAbsM\": \"-7.26 K\",\r\n" + //
                "\t\t\t\"plPerDay\": -22472.1,\r\n" + //
                "\t\t\t\"plPerDayM\": \"-22.47 K\"\r\n" + //
                "\t\t},\r\n" + //
                "\t\t{\r\n" + //
                "\t\t\t\"scrip\": \"POLYMED\",\r\n" + //
                "\t\t\t\"units\": 104.0,\r\n" + //
                "\t\t\t\"ppu\": 2163.32,\r\n" + //
                "\t\t\t\"cmp\": 2206.0,\r\n" + //
                "\t\t\t\"daysChg\": \"-0.08%\",\r\n" + //
                "\t\t\t\"daysChgD\": -0.08,\r\n" + //
                "\t\t\t\"inv\": 224985.0,\r\n" + //
                "\t\t\t\"invM\": \"2.25 Lacs\",\r\n" + //
                "\t\t\t\"perByInv\": 2.4,\r\n" + //
                "\t\t\t\"curVal\": 229424.0,\r\n" + //
                "\t\t\t\"curValM\": \"2.29 Lacs\",\r\n" + //
                "\t\t\t\"perByCurVal\": 2.9,\r\n" + //
                "\t\t\t\"plPer\": 2.0,\r\n" + //
                "\t\t\t\"plAbs\": 4439.0,\r\n" + //
                "\t\t\t\"plAbsM\": \"4.44 K\",\r\n" + //
                "\t\t\t\"plPerDay\": -183.5,\r\n" + //
                "\t\t\t\"plPerDayM\": \"-183.5\"\r\n" + //
                "\t\t},\r\n" + //
                "\t\t{\r\n" + //
                "\t\t\t\"scrip\": \"SJS\",\r\n" + //
                "\t\t\t\"units\": 310.0,\r\n" + //
                "\t\t\t\"ppu\": 1127.09,\r\n" + //
                "\t\t\t\"cmp\": 833.0,\r\n" + //
                "\t\t\t\"daysChg\": \"-1.16%\",\r\n" + //
                "\t\t\t\"daysChgD\": -1.16,\r\n" + //
                "\t\t\t\"inv\": 349398.0,\r\n" + //
                "\t\t\t\"invM\": \"3.49 Lacs\",\r\n" + //
                "\t\t\t\"perByInv\": 3.7,\r\n" + //
                "\t\t\t\"curVal\": 258230.0,\r\n" + //
                "\t\t\t\"curValM\": \"2.58 Lacs\",\r\n" + //
                "\t\t\t\"perByCurVal\": 3.3,\r\n" + //
                "\t\t\t\"plPer\": -26.1,\r\n" + //
                "\t\t\t\"plAbs\": -91168.0,\r\n" + //
                "\t\t\t\"plAbsM\": \"-91.17 K\",\r\n" + //
                "\t\t\t\"plPerDay\": -2995.5,\r\n" + //
                "\t\t\t\"plPerDayM\": \"-3.0 K\"\r\n" + //
                "\t\t},\r\n" + //
                "\t\t{\r\n" + //
                "\t\t\t\"scrip\": \"SKYGOLD\",\r\n" + //
                "\t\t\t\"units\": 1287.0,\r\n" + //
                "\t\t\t\"ppu\": 350.0,\r\n" + //
                "\t\t\t\"cmp\": 344.0,\r\n" + //
                "\t\t\t\"daysChg\": \"-1.18%\",\r\n" + //
                "\t\t\t\"daysChgD\": -1.18,\r\n" + //
                "\t\t\t\"inv\": 450450.0,\r\n" + //
                "\t\t\t\"invM\": \"4.5 Lacs\",\r\n" + //
                "\t\t\t\"perByInv\": 4.8,\r\n" + //
                "\t\t\t\"curVal\": 442728.0,\r\n" + //
                "\t\t\t\"curValM\": \"4.43 Lacs\",\r\n" + //
                "\t\t\t\"perByCurVal\": 5.6,\r\n" + //
                "\t\t\t\"plPer\": -1.7,\r\n" + //
                "\t\t\t\"plAbs\": -7722.0,\r\n" + //
                "\t\t\t\"plAbsM\": \"-7.72 K\",\r\n" + //
                "\t\t\t\"plPerDay\": -5224.2,\r\n" + //
                "\t\t\t\"plPerDayM\": \"-5.22 K\"\r\n" + //
                "\t\t},\r\n" + //
                "\t\t{\r\n" + //
                "\t\t\t\"scrip\": \"TARIL\",\r\n" + //
                "\t\t\t\"units\": 674.0,\r\n" + //
                "\t\t\t\"ppu\": 553.51,\r\n" + //
                "\t\t\t\"cmp\": 383.0,\r\n" + //
                "\t\t\t\"daysChg\": \"-1.93%\",\r\n" + //
                "\t\t\t\"daysChgD\": -1.93,\r\n" + //
                "\t\t\t\"inv\": 373066.0,\r\n" + //
                "\t\t\t\"invM\": \"3.73 Lacs\",\r\n" + //
                "\t\t\t\"perByInv\": 4.0,\r\n" + //
                "\t\t\t\"curVal\": 258142.0,\r\n" + //
                "\t\t\t\"curValM\": \"2.58 Lacs\",\r\n" + //
                "\t\t\t\"perByCurVal\": 3.3,\r\n" + //
                "\t\t\t\"plPer\": -30.8,\r\n" + //
                "\t\t\t\"plAbs\": -114924.0,\r\n" + //
                "\t\t\t\"plAbsM\": \"-1.15 Lacs\",\r\n" + //
                "\t\t\t\"plPerDay\": -4982.1,\r\n" + //
                "\t\t\t\"plPerDayM\": \"-4.98 K\"\r\n" + //
                "\t\t},\r\n" + //
                "\t\t{\r\n" + //
                "\t\t\t\"scrip\": \"TRENT\",\r\n" + //
                "\t\t\t\"units\": 65.0,\r\n" + //
                "\t\t\t\"ppu\": 5425.0,\r\n" + //
                "\t\t\t\"cmp\": 5005.0,\r\n" + //
                "\t\t\t\"daysChg\": \"0.21%\",\r\n" + //
                "\t\t\t\"daysChgD\": 0.21,\r\n" + //
                "\t\t\t\"inv\": 352625.0,\r\n" + //
                "\t\t\t\"invM\": \"3.53 Lacs\",\r\n" + //
                "\t\t\t\"perByInv\": 3.8,\r\n" + //
                "\t\t\t\"curVal\": 325325.0,\r\n" + //
                "\t\t\t\"curValM\": \"3.25 Lacs\",\r\n" + //
                "\t\t\t\"perByCurVal\": 4.1,\r\n" + //
                "\t\t\t\"plPer\": -7.7,\r\n" + //
                "\t\t\t\"plAbs\": -27300.0,\r\n" + //
                "\t\t\t\"plAbsM\": \"-27.3 K\",\r\n" + //
                "\t\t\t\"plPerDay\": 683.2,\r\n" + //
                "\t\t\t\"plPerDayM\": \"683.2\"\r\n" + //
                "\t\t},\r\n" + //
                "\t\t{\r\n" + //
                "\t\t\t\"scrip\": \"VBL\",\r\n" + //
                "\t\t\t\"units\": 442.0,\r\n" + //
                "\t\t\t\"ppu\": 508.97,\r\n" + //
                "\t\t\t\"cmp\": 482.0,\r\n" + //
                "\t\t\t\"daysChg\": \"-0.67%\",\r\n" + //
                "\t\t\t\"daysChgD\": -0.67,\r\n" + //
                "\t\t\t\"inv\": 224965.0,\r\n" + //
                "\t\t\t\"invM\": \"2.25 Lacs\",\r\n" + //
                "\t\t\t\"perByInv\": 2.4,\r\n" + //
                "\t\t\t\"curVal\": 213044.0,\r\n" + //
                "\t\t\t\"curValM\": \"2.13 Lacs\",\r\n" + //
                "\t\t\t\"perByCurVal\": 2.7,\r\n" + //
                "\t\t\t\"plPer\": -5.3,\r\n" + //
                "\t\t\t\"plAbs\": -11921.0,\r\n" + //
                "\t\t\t\"plAbsM\": \"-11.92 K\",\r\n" + //
                "\t\t\t\"plPerDay\": -1427.4,\r\n" + //
                "\t\t\t\"plPerDayM\": \"-1.43 K\"\r\n" + //
                "\t\t},\r\n" + //
                "\t\t{\r\n" + //
                "\t\t\t\"scrip\": \"WAAREEENER\",\r\n" + //
                "\t\t\t\"units\": 107.0,\r\n" + //
                "\t\t\t\"ppu\": 2240.43,\r\n" + //
                "\t\t\t\"cmp\": 2124.0,\r\n" + //
                "\t\t\t\"daysChg\": \"0.37%\",\r\n" + //
                "\t\t\t\"daysChgD\": 0.37,\r\n" + //
                "\t\t\t\"inv\": 239726.0,\r\n" + //
                "\t\t\t\"invM\": \"2.4 Lacs\",\r\n" + //
                "\t\t\t\"perByInv\": 2.6,\r\n" + //
                "\t\t\t\"curVal\": 227268.0,\r\n" + //
                "\t\t\t\"curValM\": \"2.27 Lacs\",\r\n" + //
                "\t\t\t\"perByCurVal\": 2.9,\r\n" + //
                "\t\t\t\"plPer\": -5.2,\r\n" + //
                "\t\t\t\"plAbs\": -12458.0,\r\n" + //
                "\t\t\t\"plAbsM\": \"-12.46 K\",\r\n" + //
                "\t\t\t\"plPerDay\": 840.9,\r\n" + //
                "\t\t\t\"plPerDayM\": \"840.9\"\r\n" + //
                "\t\t},\r\n" + //
                "\t\t{\r\n" + //
                "\t\t\t\"scrip\": \"ZAGGLE\",\r\n" + //
                "\t\t\t\"units\": 793.0,\r\n" + //
                "\t\t\t\"ppu\": 536.33,\r\n" + //
                "\t\t\t\"cmp\": 324.0,\r\n" + //
                "\t\t\t\"daysChg\": \"-2.69%\",\r\n" + //
                "\t\t\t\"daysChgD\": -2.69,\r\n" + //
                "\t\t\t\"inv\": 425310.0,\r\n" + //
                "\t\t\t\"invM\": \"4.25 Lacs\",\r\n" + //
                "\t\t\t\"perByInv\": 4.5,\r\n" + //
                "\t\t\t\"curVal\": 256932.0,\r\n" + //
                "\t\t\t\"curValM\": \"2.57 Lacs\",\r\n" + //
                "\t\t\t\"perByCurVal\": 3.3,\r\n" + //
                "\t\t\t\"plPer\": -39.6,\r\n" + //
                "\t\t\t\"plAbs\": -168378.0,\r\n" + //
                "\t\t\t\"plAbsM\": \"-1.68 Lacs\",\r\n" + //
                "\t\t\t\"plPerDay\": -6911.5,\r\n" + //
                "\t\t\t\"plPerDayM\": \"-6.91 K\"\r\n" + //
                "\t\t},\r\n" + //
                "\t\t{\r\n" + //
                "\t\t\t\"scrip\": \"ZENTEC\",\r\n" + //
                "\t\t\t\"units\": 221.0,\r\n" + //
                "\t\t\t\"ppu\": 1852.99,\r\n" + //
                "\t\t\t\"cmp\": 1196.0,\r\n" + //
                "\t\t\t\"daysChg\": \"0.40%\",\r\n" + //
                "\t\t\t\"daysChgD\": 0.4,\r\n" + //
                "\t\t\t\"inv\": 409511.0,\r\n" + //
                "\t\t\t\"invM\": \"4.1 Lacs\",\r\n" + //
                "\t\t\t\"perByInv\": 4.4,\r\n" + //
                "\t\t\t\"curVal\": 264316.0,\r\n" + //
                "\t\t\t\"curValM\": \"2.64 Lacs\",\r\n" + //
                "\t\t\t\"perByCurVal\": 3.4,\r\n" + //
                "\t\t\t\"plPer\": -35.5,\r\n" + //
                "\t\t\t\"plAbs\": -145195.0,\r\n" + //
                "\t\t\t\"plAbsM\": \"-1.45 Lacs\",\r\n" + //
                "\t\t\t\"plPerDay\": 1057.3,\r\n" + //
                "\t\t\t\"plPerDayM\": \"1.06 K\"\r\n" + //
                "\t\t}\r\n" + //
                "\t]\r\n" + //
                "}";

        return jsonString;
    }

    public static List<TY_WLDB> getWLDB4mJSON() throws Exception
    {
        ObjectMapper objectMapper = new ObjectMapper();

        TypeFactory typeFactory = objectMapper.getTypeFactory();
        CollectionType collectionType = typeFactory.constructCollectionType(List.class, TY_WLDB.class);
        List<TY_WLDB> wlDBResp = objectMapper.readValue(getWLJSON(), collectionType);

        return wlDBResp;
    }

    public static TY_PF getPF4mJSON() throws Exception
    {
        ObjectMapper objectMapper = new ObjectMapper();

        TY_PF pf = objectMapper.readValue(getPFJSON(), TY_PF.class);

        return pf;
    }

}
