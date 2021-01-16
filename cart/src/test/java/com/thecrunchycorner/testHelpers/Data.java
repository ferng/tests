package com.thecrunchycorner.testHelpers;

public class Data {
    public static String productCsv = "id,sequence\n" +
            "1,10\n" +
            "2,7\n" +
            "3,15\n" +
            "4,13\n" +
            "5,6";

    public static String stockCsv = "sizeId,quantity\n" +
            "11,0\n" +
            "12,0\n" +
            "13,0\n" +
            "22,0\n" +
            "31,10\n" +
            "32,10\n" +
            "33,10\n" +
            "41,0\n" +
            "42,0\n" +
            "43,0\n" +
            "44,10\n" +
            "51,10\n" +
            "52,10\n" +
            "53,10\n" +
            "54,10\n";

    public static String sizeCsv = "id,productId,backSoon,special\n" +
            "11,1,true,false\n" +
            "12,1,false,false\n" +
            "13,1 ,true,false\n" +
            "21,2,false,false\n" +
            "22,2,false,false\n" +
            "23,2,true,true\n" +
            "31,3,true,false\n" +
            "32,3,true,false\n" +
            "33,3,false,false\n" +
            "41,4,false,false\n" +
            "42,4,false,false\n" +
            "43,4,false,false\n" +
            "44,4,true,true\n" +
            "51,5,true,false\n" +
            "52,5,false,false\n" +
            "53,5,false,false\n" +
            "54,5,true,true\n";

    public static String badProductCsv =
            "1,10\n" +
                    "2,7\n" +
                    "3,15\n" +
                    "4,13\n" +
                    "5,6";
}

