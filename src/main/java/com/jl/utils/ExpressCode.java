package com.jl.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

/**
 * Created by fannairu on 2016/9/21.
 */
public class ExpressCode {
    private static String codeMapStr = "{\"JTKD\":\"捷特快递\",\"ZTKY\":\"中铁快运\",\"GTO\":\"国通快递\",\"XFEX\":\"信丰快递\",\"SUBIDA\":\"速必达物流\",\"QFKD\":\"全峰快递\",\"YD\":\"韵达快递\",\"HXLWL\":\"华夏龙物流\",\"STO\":\"申通快递\",\"MHKD\":\"民航快递\",\"CCES\":\"CCES快递\",\"XBWL\":\"新邦物流\",\"FKD\":\"飞康达\",\"ZENY\":\"增益快递\",\"CJKD\":\"城际快递\",\"FEDEX\":\"FEDEX联邦\",\"ZJS\":\"宅急送\",\"AJ\":\"安捷快递\",\"SURE\":\"速尔快递\",\"FEDEX_GJ\":\"FEDEX联邦\",\"YFSD\":\"亚风快递\",\"CITY100\":\"城市100\",\"UAPEX\":\"全一快递\",\"SBWL\":\"盛邦物流\",\"AMAZON\":\"亚马逊物流\",\"DTWL\":\"大田物流\",\"JYWL\":\"佳怡物流\",\"ZTE\":\"众通快递\",\"SFWL\":\"盛丰物流\",\"NEDA\":\"能达速递\",\"QUICK\":\"快客快递\",\"QRT\":\"全日通快递\",\"SF\":\"顺丰快递\",\"ZTO\":\"中通速递\",\"COE\":\"COE东方快递\",\"LHT\":\"联昊通速递\",\"HOAU\":\"天地华宇\",\"HTKY\":\"百世快递\",\"WXWL\":\"万象物流\",\"ST\":\"速通物流\",\"BQXHM\":\"北青小红帽\",\"ZHQKD\":\"汇强快递\",\"JGSD\":\"京广速递\",\"CNPEX\":\"CNPEX中邮快递\",\"JLDT\":\"嘉里物流\",\"HFWL\":\"汇丰物流\",\"XYT\":\"希优特\",\"LB\":\"龙邦快递\",\"YCWL\":\"远成物流\",\"PANEX\":\"泛捷快递\",\"FAST\":\"快捷速递\",\"ZYWL\":\"中邮物流\",\"DSWL\":\"D速物流\",\"SAWL\":\"圣安物流\",\"AYCA\":\"澳邮专线\",\"JJKY\":\"佳吉快运\",\"UC\":\"优速快递\",\"DBL\":\"德邦\",\"MLWL\":\"明亮物流\",\"RFD\":\"如风达\",\"YXKD\":\"亿翔快递\",\"YTO\":\"圆通速递\",\"YDH\":\"义达国际物流\",\"YTKD\":\"运通快递\",\"PADTF\":\"平安达腾飞快递\",\"HHTT\":\"天天快递\",\"ZTWL\":\"中铁物流\",\"GDEMS\":\"广东邮政\",\"YFEX\":\"越丰物流\",\"EMS\":\"EMS\",\"PCA\":\"PCA\",\"JIUYE\":\"九曳供应链\",\"JXD\":\"急先达\",\"SAD\":\"赛澳递\",\"ANE\":\"安能物流\",\"BTWL\":\"百世快运\",\"SDWL\":\"上大物流\",\"WJWL\":\"万家物流\",\"UEQ\":\"UEQ\",\"RFEX\":\"瑞丰速递\",\"YZPY\":\"邮政平邮\",\"YFHEX\":\"原飞航物流\",\"JYKD\":\"晋越快递\",\"HYLSD\":\"好来运快递\",\"SHWL\":\"盛辉物流\",\"QCKD\":\"全晨快递\",\"STWL\":\"速腾快递\",\"BFDF\":\"百福东方\",\"GSD\":\"共速达\",\"GTSD\":\"高铁速递\",\"JYM\":\"加运美\",\"KYWL\":\"跨越物流\",\"hq568\":\"华强物流\",\"HPTEX\":\"海派通物流公司\",\"TSSTO\":\"唐山申通\",\"YADEX\":\"源安达快递\",\"AXD\":\"安信达快递\",\"HOTSCM\":\"鸿桥供应链\",\"XJ\":\"新杰物流\",\"HLWL\":\"恒路物流\",\"CSCY\":\"长沙创一\"}";
    private static ObjectMapper objectMapper = new ObjectMapper();
    public static Map getCodeMap() {
        Map reMap = null;
        try {
            reMap = objectMapper.readValue(codeMapStr, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reMap;
    }

}
