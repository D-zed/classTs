package util.xml2;


import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.json.XMLParserConfiguration;

/**
 * 用这个json转xml的工具的问题就是层级的定义何之前的有些不一样 list的节点外必须有一级
 */
public class XmlUtil2 {


    public static void main(String[] args) {
        String jsonStr ="{\n" +
                "    \"ebizHead\":{\n" +
                "        \"businessNo\":\"O4120210301192007\",\n" +
                "        \"serialNo\":\"20210301192008327565\",\n" +
                "        \"sourceCode\":\"WADBY04\",\n" +
                "        \"transType\":\"101\"\n" +
                "    },\n" +
                "    \"transInfo\":{\n" +
                "        \"policyInfo\":{\n" +
                "            \"appDate\":\"2021-03-01 19:20:08\",\n" +
                "            \"appNTInfo\":{\n" +
                "                \"addressList\":[\n" +
                "                    {\n" +
                "                        \"addressDetail\":\"阿斯顿asdasd\",\n" +
                "                        \"addressType\":\"01\",\n" +
                "                        \"city\":\"110100\",\n" +
                "                        \"county\":\"110101\",\n" +
                "                        \"province\":\"110000\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"birthday\":\"1996-05-09\",\n" +
                "                \"cardNo\":\"232301199605091119\",\n" +
                "                \"cardType\":\"01\",\n" +
                "                \"email\":\"asda@qq.com\",\n" +
                "                \"gender\":\"0\",\n" +
                "                \"height\":188,\n" +
                "                \"mobile\":\"18868804027\",\n" +
                "                \"name\":\"刘思阳\",\n" +
                "                \"nationality\":\"CHN\",\n" +
                "                \"occupation\":\"1303001\",\n" +
                "                \"validate\":\"2021-03-01\",\n" +
                "                \"weight\":188\n" +
                "            },\n" +
                "            \"autoReBuyFlag\":\"Y\",\n" +
                "            \"hasSocialSecurity\":\"Y\",\n" +
                "            \"heathFlag\":\"Y\",\n" +
                "            \"insureType\":\"0\",\n" +
                "            \"insureYears\":\"105\",\n" +
                "            \"insureYearsIntv\":\"A\",\n" +
                "            \"insuredInfoList\":[\n" +
                "                {\n" +
                "                    \"addressList\":[\n" +
                "                        {\n" +
                "                            \"addressDetail\":\"阿斯顿asdasd\",\n" +
                "                            \"addressType\":\"01\",\n" +
                "                            \"city\":\"110100\",\n" +
                "                            \"province\":\"110000\"\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"birthday\":\"1996-05-09\",\n" +
                "                    \"cardNo\":\"232301199605091119\",\n" +
                "                    \"cardType\":\"01\",\n" +
                "                    \"email\":\"asda@qq.com\",\n" +
                "                    \"gender\":\"0\",\n" +
                "                    \"height\":188,\n" +
                "                    \"mobile\":\"18868804027\",\n" +
                "                    \"name\":\"刘思阳\",\n" +
                "                    \"nationality\":\"CHN\",\n" +
                "                    \"occupation\":\"1303001\",\n" +
                "                    \"relationToApp\":\"00\",\n" +
                "                    \"valEndDate\":\"2021-03-01\",\n" +
                "                    \"weight\":188\n" +
                "                }\n" +
                "            ],\n" +
                "            \"isLegalBNF\":\"Y\",\n" +
                "            \"isSelfFlag\":\"Y\",\n" +
                "            \"mainRiskCode\":\"110096\",\n" +
                "            \"payIntv\":\"0\",\n" +
                "            \"payYears\":\"1\",\n" +
                "            \"payYearsIntv\":\"Y\",\n" +
                "            \"planCode\":\"JH1100961\",\n" +
                "            \"planName\":\"基本责任\",\n" +
                "            \"productCode\":\"110096\",\n" +
                "            \"productName\":\"复星联合阿童沐1号（含身故）重疾险\",\n" +
                "            \"renewFlag\":\"Y\",\n" +
                "            \"renewInfo\":{\n" +
                "                \"account\":\"asd\",\n" +
                "                \"accountName\":\"刘思阳\",\n" +
                "                \"bankCode\":\"9001\",\n" +
                "                \"payMode\":\"4\"\n" +
                "            },\n" +
                "            \"riskList\":[\n" +
                "                {\n" +
                "                    \"amnt\":100000,\n" +
                "                    \"copies\":\"1\",\n" +
                "                    \"dutyInfo\":[\n" +
                "                        {\n" +
                "                            \"amnt\":100000,\n" +
                "                            \"dutyCode\":\"110962\",\n" +
                "                            \"dutyName\":\"复星联合阿童沐1号（含身故）重疾险\",\n" +
                "                            \"prem\":29636,\n" +
                "                            \"rate\":1\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"insureType\":\"0\",\n" +
                "                    \"insureYears\":\"105\",\n" +
                "                    \"insureYearsIntv\":\"A\",\n" +
                "                    \"mainFlag\":\"Y\",\n" +
                "                    \"payIntv\":\"0\",\n" +
                "                    \"payYears\":\"1\",\n" +
                "                    \"payYearsIntv\":\"Y\",\n" +
                "                    \"prem\":29636,\n" +
                "                    \"riskCode\":\"110096\",\n" +
                "                    \"riskName\":\"复星联合阿童沐1号（含身故）重疾险\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"amnt\":305000,\n" +
                "                    \"copies\":\"1\",\n" +
                "                    \"dutyInfo\":[\n" +
                "                        {\n" +
                "                            \"amnt\":305000,\n" +
                "                            \"dutyCode\":\"120271\",\n" +
                "                            \"dutyName\":\"可附加1年期甲癌医疗\",\n" +
                "                            \"prem\":43,\n" +
                "                            \"rate\":1\n" +
                "                        }\n" +
                "                    ],\n" +
                "                    \"insureType\":\"0\",\n" +
                "                    \"insureYears\":\"1\",\n" +
                "                    \"insureYearsIntv\":\"Y\",\n" +
                "                    \"mainFlag\":\"N\",\n" +
                "                    \"payIntv\":\"0\",\n" +
                "                    \"payYears\":\"1\",\n" +
                "                    \"payYearsIntv\":\"Y\",\n" +
                "                    \"prem\":43,\n" +
                "                    \"riskCode\":\"120027\",\n" +
                "                    \"riskName\":\"可附加1年期甲癌医疗\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"totalPrem\":29679\n" +
                "        },\n" +
                "        \"sellFineType\":\"03\",\n" +
                "        \"sellType\":\"23\",\n" +
                "        \"transAmount\":29679,\n" +
                "        \"transTime\":\"2021-03-01 19:20:08\"\n" +
                "    }\n" +
                "}";

        JSONObject jsonObject = new JSONObject(jsonStr);
        String s = XML.toString(jsonObject);
        System.out.println(s);


        System.out.println("-------2-----------------------------");
        JSONObject json = new JSONObject();
        JSONObject ebizHead = new JSONObject();
        ebizHead.put("BusinessNo","O4120210301192007");
        ebizHead.put("SerialNo","20210301192008327565");
        ebizHead.put("SourceCode","WADBY04");
        ebizHead.put("TransType","101");
        JSONObject transInfo = new JSONObject();
        JSONObject insuredInfoList = new JSONObject();
        JSONObject insureItem1 = new JSONObject();
        insureItem1.put("name","刘思阳");
        JSONObject insureItem2 = new JSONObject();
        insureItem2.put("name","李阳");
        JSONArray insureInfo=new JSONArray();
        insureInfo.put(insureItem1);
        insureInfo.put(insureItem2);
        insuredInfoList.put("insureInfo",insureInfo);
        transInfo.put("insuredInfoList",insuredInfoList);

        json.put("EbizHead",ebizHead);
        json.put("TransInfo",transInfo);
        XMLParserConfiguration config=new XMLParserConfiguration();
        config.withConvertNilAttributeToNull(true);
        String xml2 =XML.toString(json);
        System.out.println(xml2);

        JSONObject str = XML.toJSONObject(xml2);
        System.out.println(str);

    }
}
