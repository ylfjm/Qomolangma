package com.boz.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 身份证工具类.
 *
 * @author June
 * @version 1.0, 2010-06-17.
 */
public class GAHelper {

    /**
     * 中国公民身份证号码最小长度。
     */
    public static final int CHINA_ID_MIN_LENGTH = 15;
    /**
     * 中国公民身份证号码最大长度。
     */
    public static final int CHINA_ID_MAX_LENGTH = 18;
    /**
     * 省、直辖市代码表
     */
    public static final String cityCode[] = {
            "11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33", "34", "35", "36", "37", "41",
            "42", "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63", "64", "65", "71",
            "81", "82", "91"
    };
    /**
     * 每位加权因子
     */
    public static final int power[] = {
            7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2
    };
    /**
     * 第18位校检码
     */
    public static final String verifyCode[] = {
            "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"
    };
    /**
     * 最低年限
     */
    public static final int MIN = 1930;
    public static Map<String, String> provinceCodes = new HashMap<String, String>();
    public static Map<String, String> cityCodes = new HashMap<String, String>();

    //    /** 台湾身份首字母对应数字 */
//    public static Map<String, Integer> twFirstCode = new HashMap<String, Integer>();
//    /** 香港身份首字母对应数字 */
//    public static Map<String, Integer> hkFirstCode = new HashMap<String, Integer>();
    static {
        provinceCodes.put("11", "北京");
        provinceCodes.put("12", "天津");
        provinceCodes.put("13", "河北");
        provinceCodes.put("14", "山西");
        provinceCodes.put("15", "内蒙古");
        provinceCodes.put("21", "辽宁");
        provinceCodes.put("22", "吉林");
        provinceCodes.put("23", "黑龙江");
        provinceCodes.put("31", "上海");
        provinceCodes.put("32", "江苏");
        provinceCodes.put("33", "浙江");
        provinceCodes.put("34", "安徽");
        provinceCodes.put("35", "福建");
        provinceCodes.put("36", "江西");
        provinceCodes.put("37", "山东");
        provinceCodes.put("41", "河南");
        provinceCodes.put("42", "湖北");
        provinceCodes.put("43", "湖南");
        provinceCodes.put("44", "广东");
        provinceCodes.put("45", "广西");
        provinceCodes.put("46", "海南");
        provinceCodes.put("50", "重庆");
        provinceCodes.put("51", "四川");
        provinceCodes.put("52", "贵州");
        provinceCodes.put("53", "云南");
        provinceCodes.put("54", "西藏");
        provinceCodes.put("61", "陕西");
        provinceCodes.put("62", "甘肃");
        provinceCodes.put("63", "青海");
        provinceCodes.put("64", "宁夏");
        provinceCodes.put("65", "新疆");
        provinceCodes.put("71", "台湾");
        provinceCodes.put("81", "香港");
        provinceCodes.put("82", "澳门");
        provinceCodes.put("91", "国外");
//        twFirstCode.put("A", 10);
//        twFirstCode.put("B", 11);
//        twFirstCode.put("C", 12);
//        twFirstCode.put("D", 13);
//        twFirstCode.put("E", 14);
//        twFirstCode.put("F", 15);
//        twFirstCode.put("G", 16);
//        twFirstCode.put("H", 17);
//        twFirstCode.put("J", 18);
//        twFirstCode.put("K", 19);
//        twFirstCode.put("L", 20);
//        twFirstCode.put("M", 21);
//        twFirstCode.put("N", 22);
//        twFirstCode.put("P", 23);
//        twFirstCode.put("Q", 24);
//        twFirstCode.put("R", 25);
//        twFirstCode.put("S", 26);
//        twFirstCode.put("T", 27);
//        twFirstCode.put("U", 28);
//        twFirstCode.put("V", 29);
//        twFirstCode.put("X", 30);
//        twFirstCode.put("Y", 31);
//        twFirstCode.put("W", 32);
//        twFirstCode.put("Z", 33);
//        twFirstCode.put("I", 34);
//        twFirstCode.put("O", 35);
//        hkFirstCode.put("A", 1);
//        hkFirstCode.put("B", 2);
//        hkFirstCode.put("C", 3);
//        hkFirstCode.put("R", 18);
//        hkFirstCode.put("U", 21);
//        hkFirstCode.put("Z", 26);
//        hkFirstCode.put("X", 24);
//        hkFirstCode.put("W", 23);
//        hkFirstCode.put("O", 15);
//        hkFirstCode.put("N", 14);
    }

    static {
        cityCodes.put("1100", "北京市");
        cityCodes.put("1101", "北京市");
        cityCodes.put("1102", "北京市");
        cityCodes.put("1200", "天津市");
        cityCodes.put("1201", "天津市");
        cityCodes.put("1202", "天津市");
        cityCodes.put("1300", "河北省");
        cityCodes.put("1301", "石家庄市");
        cityCodes.put("1302", "唐山市");
        cityCodes.put("1303", "秦皇岛市");
        cityCodes.put("1304", "邯郸市");
        cityCodes.put("1305", "邢台市");
        cityCodes.put("1306", "保定市");
        cityCodes.put("1307", "张家口市");
        cityCodes.put("1308", "承德市");
        cityCodes.put("1309", "沧州市");
        cityCodes.put("1310", "廊坊市");
        cityCodes.put("1311", "衡水市");
        cityCodes.put("1400", "山西省");
        cityCodes.put("1401", "太原市");
        cityCodes.put("1402", "大同市");
        cityCodes.put("1403", "阳泉市");
        cityCodes.put("1404", "长治市");
        cityCodes.put("1405", "晋城市");
        cityCodes.put("1406", "朔州市");
        cityCodes.put("1407", "晋中市");
        cityCodes.put("1408", "运城市");
        cityCodes.put("1409", "忻州市");
        cityCodes.put("1410", "临汾市");
        cityCodes.put("1411", "吕梁市");
        cityCodes.put("1500", "内蒙古自治区");
        cityCodes.put("1501", "呼和浩特市");
        cityCodes.put("1502", "包头市");
        cityCodes.put("1503", "乌海市");
        cityCodes.put("1504", "赤峰市");
        cityCodes.put("1505", "通辽市");
        cityCodes.put("1506", "鄂尔多斯市");
        cityCodes.put("1507", "呼伦贝尔市");
        cityCodes.put("1508", "巴彦淖尔市");
        cityCodes.put("1509", "乌兰察布市");
        cityCodes.put("1522", "兴安盟");
        cityCodes.put("1525", "锡林郭勒盟");
        cityCodes.put("1529", "阿拉善盟");
        cityCodes.put("2100", "辽宁省");
        cityCodes.put("2101", "沈阳市");
        cityCodes.put("2102", "大连市");
        cityCodes.put("2103", "鞍山市");
        cityCodes.put("2104", "抚顺市");
        cityCodes.put("2105", "本溪市");
        cityCodes.put("2106", "丹东市");
        cityCodes.put("2107", "锦州市");
        cityCodes.put("2108", "营口市");
        cityCodes.put("2109", "阜新市");
        cityCodes.put("2110", "辽阳市");
        cityCodes.put("2111", "盘锦市");
        cityCodes.put("2112", "铁岭市");
        cityCodes.put("2113", "朝阳市");
        cityCodes.put("2114", "葫芦岛市");
        cityCodes.put("2200", "吉林省");
        cityCodes.put("2201", "长春市");
        cityCodes.put("2202", "吉林市");
        cityCodes.put("2203", "四平市");
        cityCodes.put("2204", "辽源市");
        cityCodes.put("2205", "通化市");
        cityCodes.put("2206", "白山市");
        cityCodes.put("2207", "松原市");
        cityCodes.put("2208", "白城市");
        cityCodes.put("2224", "延边朝鲜族自治州");
        cityCodes.put("2300", "黑龙江省");
        cityCodes.put("2301", "哈尔滨市");
        cityCodes.put("2302", "齐齐哈尔市");
        cityCodes.put("2303", "鸡西市");
        cityCodes.put("2304", "鹤岗市");
        cityCodes.put("2305", "双鸭山市");
        cityCodes.put("2306", "大庆市");
        cityCodes.put("2307", "伊春市");
        cityCodes.put("2308", "佳木斯市");
        cityCodes.put("2309", "七台河市");
        cityCodes.put("2310", "牡丹江市");
        cityCodes.put("2311", "黑河市");
        cityCodes.put("2312", "绥化市");
        cityCodes.put("2327", "大兴安岭地区");
        cityCodes.put("3100", "上海市");
        cityCodes.put("3101", "上海市");
        cityCodes.put("3102", "上海市");
        cityCodes.put("3200", "江苏省");
        cityCodes.put("3201", "南京市");
        cityCodes.put("3202", "无锡市");
        cityCodes.put("3203", "徐州市");
        cityCodes.put("3204", "常州市");
        cityCodes.put("3205", "苏州市");
        cityCodes.put("3206", "南通市");
        cityCodes.put("3207", "连云港市");
        cityCodes.put("3208", "淮安市");
        cityCodes.put("3209", "盐城市");
        cityCodes.put("3210", "扬州市");
        cityCodes.put("3211", "镇江市");
        cityCodes.put("3212", "泰州市");
        cityCodes.put("3213", "宿迁市");
        cityCodes.put("3300", "浙江省");
        cityCodes.put("3301", "杭州市");
        cityCodes.put("3302", "宁波市");
        cityCodes.put("3303", "温州市");
        cityCodes.put("3304", "嘉兴市");
        cityCodes.put("3305", "湖州市");
        cityCodes.put("3306", "绍兴市");
        cityCodes.put("3307", "金华市");
        cityCodes.put("3308", "衢州市");
        cityCodes.put("3309", "舟山市");
        cityCodes.put("3310", "台州市");
        cityCodes.put("3311", "丽水市");
        cityCodes.put("3400", "安徽省");
        cityCodes.put("3401", "合肥市");
        cityCodes.put("3402", "芜湖市");
        cityCodes.put("3403", "蚌埠市");
        cityCodes.put("3404", "淮南市");
        cityCodes.put("3405", "马鞍山市");
        cityCodes.put("3406", "淮北市");
        cityCodes.put("3407", "铜陵市");
        cityCodes.put("3408", "安庆市");
        cityCodes.put("3410", "黄山市");
        cityCodes.put("3411", "滁州市");
        cityCodes.put("3412", "阜阳市");
        cityCodes.put("3413", "宿州市");
        cityCodes.put("3415", "六安市");
        cityCodes.put("3416", "亳州市");
        cityCodes.put("3417", "池州市");
        cityCodes.put("3418", "宣城市");
        cityCodes.put("3500", "福建省");
        cityCodes.put("3501", "福州市");
        cityCodes.put("3502", "厦门市");
        cityCodes.put("3503", "莆田市");
        cityCodes.put("3504", "三明市");
        cityCodes.put("3505", "泉州市");
        cityCodes.put("3506", "漳州市");
        cityCodes.put("3507", "南平市");
        cityCodes.put("3508", "龙岩市");
        cityCodes.put("3509", "宁德市");
        cityCodes.put("3600", "江西省");
        cityCodes.put("3601", "南昌市");
        cityCodes.put("3602", "景德镇市");
        cityCodes.put("3603", "萍乡市");
        cityCodes.put("3604", "九江市");
        cityCodes.put("3605", "新余市");
        cityCodes.put("3606", "鹰潭市");
        cityCodes.put("3607", "赣州市");
        cityCodes.put("3608", "吉安市");
        cityCodes.put("3609", "宜春市");
        cityCodes.put("3610", "抚州市");
        cityCodes.put("3611", "上饶市");
        cityCodes.put("3700", "山东省");
        cityCodes.put("3701", "济南市");
        cityCodes.put("3702", "青岛市");
        cityCodes.put("3703", "淄博市");
        cityCodes.put("3704", "枣庄市");
        cityCodes.put("3705", "东营市");
        cityCodes.put("3706", "烟台市");
        cityCodes.put("3707", "潍坊市");
        cityCodes.put("3708", "济宁市");
        cityCodes.put("3709", "泰安市");
        cityCodes.put("3710", "威海市");
        cityCodes.put("3711", "日照市");
        cityCodes.put("3712", "莱芜市");
        cityCodes.put("3713", "临沂市");
        cityCodes.put("3714", "德州市");
        cityCodes.put("3715", "聊城市");
        cityCodes.put("3716", "滨州市");
        cityCodes.put("3717", "菏泽市");
        cityCodes.put("4100", "河南省");
        cityCodes.put("4101", "郑州市");
        cityCodes.put("4102", "开封市");
        cityCodes.put("4103", "洛阳市");
        cityCodes.put("4104", "平顶山市");
        cityCodes.put("4105", "安阳市");
        cityCodes.put("4106", "鹤壁市");
        cityCodes.put("4107", "新乡市");
        cityCodes.put("4108", "焦作市");
        cityCodes.put("4109", "濮阳市");
        cityCodes.put("4110", "许昌市");
        cityCodes.put("4111", "漯河市");
        cityCodes.put("4112", "三门峡市");
        cityCodes.put("4113", "南阳市");
        cityCodes.put("4114", "商丘市");
        cityCodes.put("4115", "信阳市");
        cityCodes.put("4116", "周口市");
        cityCodes.put("4117", "驻马店市");
        cityCodes.put("4190", "省直辖县级行政区划");
        cityCodes.put("4200", "湖北省");
        cityCodes.put("4201", "武汉市");
        cityCodes.put("4202", "黄石市");
        cityCodes.put("4203", "十堰市");
        cityCodes.put("4205", "宜昌市");
        cityCodes.put("4206", "襄阳市");
        cityCodes.put("4207", "鄂州市");
        cityCodes.put("4208", "荆门市");
        cityCodes.put("4209", "孝感市");
        cityCodes.put("4210", "荆州市");
        cityCodes.put("4211", "黄冈市");
        cityCodes.put("4212", "咸宁市");
        cityCodes.put("4213", "随州市");
        cityCodes.put("4228", "恩施土家族苗族自治州");
        cityCodes.put("4290", "省直辖县级行政区划");
        cityCodes.put("4300", "湖南省");
        cityCodes.put("4301", "长沙市");
        cityCodes.put("4302", "株洲市");
        cityCodes.put("4303", "湘潭市");
        cityCodes.put("4304", "衡阳市");
        cityCodes.put("4305", "邵阳市");
        cityCodes.put("4306", "岳阳市");
        cityCodes.put("4307", "常德市");
        cityCodes.put("4308", "张家界市");
        cityCodes.put("4309", "益阳市");
        cityCodes.put("4310", "郴州市");
        cityCodes.put("4311", "永州市");
        cityCodes.put("4312", "怀化市");
        cityCodes.put("4313", "娄底市");
        cityCodes.put("4331", "湘西土家族苗族自治州");
        cityCodes.put("4400", "广东省");
        cityCodes.put("4401", "广州市");
        cityCodes.put("4402", "韶关市");
        cityCodes.put("4403", "深圳市");
        cityCodes.put("4404", "珠海市");
        cityCodes.put("4405", "汕头市");
        cityCodes.put("4406", "佛山市");
        cityCodes.put("4407", "江门市");
        cityCodes.put("4408", "湛江市");
        cityCodes.put("4409", "茂名市");
        cityCodes.put("4412", "肇庆市");
        cityCodes.put("4413", "惠州市");
        cityCodes.put("4414", "梅州市");
        cityCodes.put("4415", "汕尾市");
        cityCodes.put("4416", "河源市");
        cityCodes.put("4417", "阳江市");
        cityCodes.put("4418", "清远市");
        cityCodes.put("4419", "东莞市");
        cityCodes.put("4420", "中山市");
        cityCodes.put("4451", "潮州市");
        cityCodes.put("4452", "揭阳市");
        cityCodes.put("4453", "云浮市");
        cityCodes.put("4500", "广西壮族自治区");
        cityCodes.put("4501", "南宁市");
        cityCodes.put("4502", "柳州市");
        cityCodes.put("4503", "桂林市");
        cityCodes.put("4504", "梧州市");
        cityCodes.put("4505", "北海市");
        cityCodes.put("4506", "防城港市");
        cityCodes.put("4507", "钦州市");
        cityCodes.put("4508", "贵港市");
        cityCodes.put("4509", "玉林市");
        cityCodes.put("4510", "百色市");
        cityCodes.put("4511", "贺州市");
        cityCodes.put("4512", "河池市");
        cityCodes.put("4513", "来宾市");
        cityCodes.put("4514", "崇左市");
        cityCodes.put("4600", "海南省");
        cityCodes.put("4601", "海口市");
        cityCodes.put("4602", "三亚市");
        cityCodes.put("4603", "三沙市");
        cityCodes.put("4690", "省直辖县级行政区划");
        cityCodes.put("5000", "重庆市");
        cityCodes.put("5001", "重庆市");
        cityCodes.put("5002", "重庆市");
        cityCodes.put("5100", "四川省");
        cityCodes.put("5101", "成都市");
        cityCodes.put("5103", "自贡市");
        cityCodes.put("5104", "攀枝花市");
        cityCodes.put("5105", "泸州市");
        cityCodes.put("5106", "德阳市");
        cityCodes.put("5107", "绵阳市");
        cityCodes.put("5108", "广元市");
        cityCodes.put("5109", "遂宁市");
        cityCodes.put("5110", "内江市");
        cityCodes.put("5111", "乐山市");
        cityCodes.put("5113", "南充市");
        cityCodes.put("5114", "眉山市");
        cityCodes.put("5115", "宜宾市");
        cityCodes.put("5116", "广安市");
        cityCodes.put("5117", "达州市");
        cityCodes.put("5118", "雅安市");
        cityCodes.put("5119", "巴中市");
        cityCodes.put("5120", "资阳市");
        cityCodes.put("5132", "阿坝藏族羌族自治州");
        cityCodes.put("5133", "甘孜藏族自治州");
        cityCodes.put("5134", "凉山彝族自治州");
        cityCodes.put("5200", "贵州省");
        cityCodes.put("5201", "贵阳市");
        cityCodes.put("5202", "六盘水市");
        cityCodes.put("5203", "遵义市");
        cityCodes.put("5204", "安顺市");
        cityCodes.put("5205", "毕节市");
        cityCodes.put("5206", "铜仁市");
        cityCodes.put("5223", "黔西南布依族苗族自治州");
        cityCodes.put("5226", "黔东南苗族侗族自治州");
        cityCodes.put("5227", "黔南布依族苗族自治州");
        cityCodes.put("5300", "云南省");
        cityCodes.put("5301", "昆明市");
        cityCodes.put("5303", "曲靖市");
        cityCodes.put("5304", "玉溪市");
        cityCodes.put("5305", "保山市");
        cityCodes.put("5306", "昭通市");
        cityCodes.put("5307", "丽江市");
        cityCodes.put("5308", "普洱市");
        cityCodes.put("5309", "临沧市");
        cityCodes.put("5323", "楚雄彝族自治州");
        cityCodes.put("5325", "红河哈尼族彝族自治州");
        cityCodes.put("5326", "文山壮族苗族自治州");
        cityCodes.put("5328", "西双版纳傣族自治州");
        cityCodes.put("5329", "大理白族自治州");
        cityCodes.put("5331", "德宏傣族景颇族自治州");
        cityCodes.put("5333", "怒江傈僳族自治州");
        cityCodes.put("5334", "迪庆藏族自治州");
        cityCodes.put("5400", "西藏自治区");
        cityCodes.put("5401", "拉萨市");
        cityCodes.put("5421", "昌都地区");
        cityCodes.put("5422", "山南地区");
        cityCodes.put("5423", "日喀则地区");
        cityCodes.put("5424", "那曲地区");
        cityCodes.put("5425", "阿里地区");
        cityCodes.put("5426", "林芝地区");
        cityCodes.put("6100", "陕西省");
        cityCodes.put("6101", "西安市");
        cityCodes.put("6102", "铜川市");
        cityCodes.put("6103", "宝鸡市");
        cityCodes.put("6104", "咸阳市");
        cityCodes.put("6105", "渭南市");
        cityCodes.put("6106", "延安市");
        cityCodes.put("6107", "汉中市");
        cityCodes.put("6108", "榆林市");
        cityCodes.put("6109", "安康市");
        cityCodes.put("6110", "商洛市");
        cityCodes.put("6200", "甘肃省");
        cityCodes.put("6201", "兰州市");
        cityCodes.put("6202", "嘉峪关市");
        cityCodes.put("6203", "金昌市");
        cityCodes.put("6204", "白银市");
        cityCodes.put("6205", "天水市");
        cityCodes.put("6206", "武威市");
        cityCodes.put("6207", "张掖市");
        cityCodes.put("6208", "平凉市");
        cityCodes.put("6209", "酒泉市");
        cityCodes.put("6210", "庆阳市");
        cityCodes.put("6211", "定西市");
        cityCodes.put("6212", "陇南市");
        cityCodes.put("6229", "临夏回族自治州");
        cityCodes.put("6230", "甘南藏族自治州");
        cityCodes.put("6300", "青海省");
        cityCodes.put("6301", "西宁市");
        cityCodes.put("6302", "海东市");
        cityCodes.put("6322", "海北藏族自治州");
        cityCodes.put("6323", "黄南藏族自治州");
        cityCodes.put("6325", "海南藏族自治州");
        cityCodes.put("6326", "果洛藏族自治州");
        cityCodes.put("6327", "玉树藏族自治州");
        cityCodes.put("6328", "海西蒙古族藏族自治州");
        cityCodes.put("6400", "宁夏回族自治区");
        cityCodes.put("6401", "银川市");
        cityCodes.put("6402", "石嘴山市");
        cityCodes.put("6403", "吴忠市");
        cityCodes.put("6404", "固原市");
        cityCodes.put("6405", "中卫市");
        cityCodes.put("6500", "新疆维吾尔自治区");
        cityCodes.put("6501", "乌鲁木齐市");
        cityCodes.put("6502", "克拉玛依市");
        cityCodes.put("6521", "吐鲁番地区");
        cityCodes.put("6522", "哈密地区");
        cityCodes.put("6523", "昌吉回族自治州");
        cityCodes.put("6527", "博尔塔拉蒙古自治州");
        cityCodes.put("6528", "巴音郭楞蒙古自治州");
        cityCodes.put("6529", "阿克苏地区");
        cityCodes.put("6530", "克孜勒苏柯尔克孜自治州");
        cityCodes.put("6531", "喀什地区");
        cityCodes.put("6532", "和田地区");
        cityCodes.put("6540", "伊犁哈萨克自治州");
        cityCodes.put("6542", "塔城地区");
        cityCodes.put("6543", "阿勒泰地区");
        cityCodes.put("6590", "自治区直辖县级行政区划");
        cityCodes.put("7100", "台湾省");
        cityCodes.put("8100", "香港特别行政区");
        cityCodes.put("8200", "澳门特别行政区");

    }

    /**
     * 将15位身份证号码转换为18位
     *
     * @param idCard 15位身份编码
     * @return 18位身份编码
     */
    public static String conver15CardTo18(String idCard) {
        String idCard18 = "";
        if (idCard.length() != CHINA_ID_MIN_LENGTH) {
            return null;
        }
        if (isNum(idCard)) {
            // 获取出生年月日
            String birthday = idCard.substring(6, 12);
            Date birthDate = null;
            try {
                birthDate = new SimpleDateFormat("yyMMdd").parse(birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar cal = Calendar.getInstance();
            if (birthDate != null)
                cal.setTime(birthDate);
            // 获取出生年(完全表现形式,如：2010)
            String sYear = String.valueOf(cal.get(Calendar.YEAR));
            idCard18 = idCard.substring(0, 6) + sYear + idCard.substring(8);
            // 转换字符数组
            char[] cArr = idCard18.toCharArray();
            if (cArr != null) {
                int[] iCard = converCharToInt(cArr);
                int iSum17 = getPowerSum(iCard);
                // 获取校验位
                String sVal = getCheckCode18(iSum17);
                if (sVal.length() > 0) {
                    idCard18 += sVal;
                } else {
                    return null;
                }
            }
        } else {
            return null;
        }
        return idCard18;
    }

    /**
     * 验证身份证是否合法
     */
    public static boolean validateCard(String idCard) {
        String card = idCard.trim();
        if (validateIdCard18(card)) {
            return true;
        }
        if (validateIdCard15(card)) {
            return true;
        }
        return false;
    }

    /**
     * 验证18位身份编码是否合法
     *
     * @param idCard 身份编码
     * @return 是否合法
     */
    public static boolean validateIdCard18(String idCard) {
        boolean bTrue = false;
        if (idCard.length() == CHINA_ID_MAX_LENGTH) {
            // 前17位
            String code17 = idCard.substring(0, 17);
            // 第18位
            String code18 = idCard.substring(17, CHINA_ID_MAX_LENGTH);
            if (isNum(code17)) {
                char[] cArr = code17.toCharArray();
                if (cArr != null) {
                    int[] iCard = converCharToInt(cArr);
                    int iSum17 = getPowerSum(iCard);
                    // 获取校验位
                    String val = getCheckCode18(iSum17);
                    if (val.length() > 0) {
                        if (val.equalsIgnoreCase(code18)) {
                            bTrue = true;
                        }
                    }
                }
            }
        }
        return bTrue;
    }

    /**
     * 验证15位身份编码是否合法
     *
     * @param idCard 身份编码
     * @return 是否合法
     */
    public static boolean validateIdCard15(String idCard) {
        if (idCard.length() != CHINA_ID_MIN_LENGTH) {
            return false;
        }
        if (isNum(idCard)) {
            String proCode = idCard.substring(0, 2);
            if (provinceCodes.get(proCode) == null) {
                return false;
            }
            String birthCode = idCard.substring(6, 12);
            Date birthDate = null;
            try {
                birthDate = new SimpleDateFormat("yy").parse(birthCode.substring(0, 2));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar cal = Calendar.getInstance();
            if (birthDate != null)
                cal.setTime(birthDate);
            if (!valiDate(cal.get(Calendar.YEAR), Integer.valueOf(birthCode.substring(2, 4)),
                    Integer.valueOf(birthCode.substring(4, 6)))) {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * 验证10位身份编码是否合法
     *
     * @param idCard 身份编码
     * @return 身份证信息数组
     * <p>
     * [0] - 台湾、澳门、香港 [1] - 性别(男M,女F,未知N) [2] - 是否合法(合法true,不合法false) 若不是身份证件号码则返回null
     * </p>
     */
    public static String[] validateIdCard10(String idCard) {
        String[] info = new String[3];
        String card = idCard.replaceAll("[\\(|\\)]", "");
        if (card.length() != 8 && card.length() != 9 && idCard.length() != 10) {
            return null;
        }
        if (idCard.matches("^[a-zA-Z][0-9]{9}$")) { // 台湾
            info[0] = "台湾";
            System.out.println("11111");
            String char2 = idCard.substring(1, 2);
            if (char2.equals("1")) {
                info[1] = "M";
                System.out.println("MMMMMMM");
            } else if (char2.equals("2")) {
                info[1] = "F";
                System.out.println("FFFFFFF");
            } else {
                info[1] = "N";
                info[2] = "false";
                System.out.println("NNNN");
                return info;
            }
            info[2] = validateTWCard(idCard) ? "true" : "false";
        } else if (idCard.matches("^[1|5|7][0-9]{6}\\(?[0-9A-Z]\\)?$")) { // 澳门
            info[0] = "澳门";
            info[1] = "N";
            // TODO
        } else if (idCard.matches("^[A-Z]{1,2}[0-9]{6}\\(?[0-9A]\\)?$")) { // 香港
            info[0] = "香港";
            info[1] = "N";
            info[2] = validateHKCard(idCard) ? "true" : "false";
        } else {
            return null;
        }
        return info;
    }

    /**
     * 验证台湾身份证号码
     *
     * @param idCard 身份证号码
     * @return 验证码是否符合
     */
    public static boolean validateTWCard(String idCard) {
//        String start = idCard.substring(0, 1);
//        String mid = idCard.substring(1, 9);
//        String end = idCard.substring(9, 10);
//        Integer iStart = twFirstCode.get(start);
//        Integer sum = iStart / 10 + (iStart % 10) * 9;
//        char[] chars = mid.toCharArray();
//        Integer iflag = 8;
//        for (char c : chars) {
//            sum = sum + Integer.valueOf(c + "") * iflag;
//            iflag--;
//        }
//        return (sum % 10 == 0 ? 0 : (10 - sum % 10)) == Integer.valueOf(end) ? true : false;
        return false;
    }

    /**
     * 验证香港身份证号码(存在Bug，部份特殊身份证无法检查)
     * <p>
     * 身份证前2位为英文字符，如果只出现一个英文字符则表示第一位是空格，对应数字58 前2位英文字符A-Z分别对应数字10-35 最后一位校验码为0-9的数字加上字符"A"，"A"代表10
     * </p>
     * <p>
     * 将身份证号码全部转换为数字，分别对应乘9-1相加的总和，整除11则证件号码有效
     * </p>
     *
     * @param idCard 身份证号码
     * @return 验证码是否符合
     */
    public static boolean validateHKCard(String idCard) {
        String card = idCard.replaceAll("[\\(|\\)]", "");
        Integer sum = 0;
        if (card.length() == 9) {
            sum = (Integer.valueOf(card.substring(0, 1).toUpperCase().toCharArray()[0]) - 55) * 9
                    + (Integer.valueOf(card.substring(1, 2).toUpperCase().toCharArray()[0]) - 55) * 8;
            card = card.substring(1, 9);
        } else {
            sum = 522 + (Integer.valueOf(card.substring(0, 1).toUpperCase().toCharArray()[0]) - 55) * 8;
        }
        String mid = card.substring(1, 7);
        String end = card.substring(7, 8);
        char[] chars = mid.toCharArray();
        Integer iflag = 7;
        for (char c : chars) {
            sum = sum + Integer.valueOf(c + "") * iflag;
            iflag--;
        }
        if (end.toUpperCase().equals("A")) {
            sum = sum + 10;
        } else {
            sum = sum + Integer.valueOf(end);
        }
        return (sum % 11 == 0) ? true : false;
    }

    /**
     * 将字符数组转换成数字数组
     *
     * @param ca 字符数组
     * @return 数字数组
     */
    public static int[] converCharToInt(char[] ca) {
        int len = ca.length;
        int[] iArr = new int[len];
        try {
            for (int i = 0; i < len; i++) {
                iArr[i] = Integer.parseInt(String.valueOf(ca[i]));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return iArr;
    }

    /**
     * 将身份证的每位和对应位的加权因子相乘之后，再得到和值
     *
     * @param iArr
     * @return 身份证编码。
     */
    public static int getPowerSum(int[] iArr) {
        int iSum = 0;
        if (power.length == iArr.length) {
            for (int i = 0; i < iArr.length; i++) {
                for (int j = 0; j < power.length; j++) {
                    if (i == j) {
                        iSum = iSum + iArr[i] * power[j];
                    }
                }
            }
        }
        return iSum;
    }

    /**
     * 将power和值与11取模获得余数进行校验码判断
     *
     * @param iSum
     * @return 校验位
     */
    public static String getCheckCode18(int iSum) {
        String sCode = "";
        switch (iSum % 11) {
            case 10:
                sCode = "2";
                break;
            case 9:
                sCode = "3";
                break;
            case 8:
                sCode = "4";
                break;
            case 7:
                sCode = "5";
                break;
            case 6:
                sCode = "6";
                break;
            case 5:
                sCode = "7";
                break;
            case 4:
                sCode = "8";
                break;
            case 3:
                sCode = "9";
                break;
            case 2:
                sCode = "x";
                break;
            case 1:
                sCode = "0";
                break;
            case 0:
                sCode = "1";
                break;
        }
        return sCode;
    }

    /**
     * 根据身份编号获取年龄
     *
     * @param idCard 身份编号
     * @return 年龄
     */
    public static int getAgeByIdCard(String idCard) {
        int iAge = 0;
        if (idCard.length() == CHINA_ID_MIN_LENGTH) {
            idCard = conver15CardTo18(idCard);
        }
        String year = idCard.substring(6, 10);
        Calendar cal = Calendar.getInstance();
        int iCurrYear = cal.get(Calendar.YEAR);
        iAge = iCurrYear - Integer.valueOf(year);
        return iAge;
    }

    /**
     * 根据身份编号获取生日
     *
     * @param idCard 身份编号
     * @return 生日(yyyyMMdd)
     */
    public static String getBirthByIdCard(String idCard) {
        Integer len = idCard.length();
        if (len < CHINA_ID_MIN_LENGTH) {
            return null;
        } else if (len == CHINA_ID_MIN_LENGTH) {
            idCard = conver15CardTo18(idCard);
        }
        return idCard.substring(6, 14);
    }

    /**
     * 根据身份编号获取生日年
     *
     * @param idCard 身份编号
     * @return 生日(yyyy)
     */
    public static Short getYearByIdCard(String idCard) {
        Integer len = idCard.length();
        if (len < CHINA_ID_MIN_LENGTH) {
            return null;
        } else if (len == CHINA_ID_MIN_LENGTH) {
            idCard = conver15CardTo18(idCard);
        }
        return Short.valueOf(idCard.substring(6, 10));
    }

    /**
     * 根据身份编号获取生日月
     *
     * @param idCard 身份编号
     * @return 生日(MM)
     */
    public static Short getMonthByIdCard(String idCard) {
        Integer len = idCard.length();
        if (len < CHINA_ID_MIN_LENGTH) {
            return null;
        } else if (len == CHINA_ID_MIN_LENGTH) {
            idCard = conver15CardTo18(idCard);
        }
        return Short.valueOf(idCard.substring(10, 12));
    }

    /**
     * 根据身份编号获取生日天
     *
     * @param idCard 身份编号
     * @return 生日(dd)
     */
    public static Short getDateByIdCard(String idCard) {
        Integer len = idCard.length();
        if (len < CHINA_ID_MIN_LENGTH) {
            return null;
        } else if (len == CHINA_ID_MIN_LENGTH) {
            idCard = conver15CardTo18(idCard);
        }
        return Short.valueOf(idCard.substring(12, 14));
    }

    /**
     * 根据身份编号获取性别
     *
     * @param idCard 身份编号
     * @return 性别(M-男，F-女，N-未知)
     */
    public static String getGenderByIdCard(String idCard) {
        String sGender = "N";
        if (idCard.length() == CHINA_ID_MIN_LENGTH) {
            idCard = conver15CardTo18(idCard);
        }
        if (null != idCard && idCard.length() == 18) {
            String sCardNum = idCard.substring(16, 17);
            if (Integer.parseInt(sCardNum) % 2 != 0) {
                sGender = "M";
            } else {
                sGender = "F";
            }
        }
        return sGender;
    }

    /**
     * 根据身份编号获取户籍省份
     *
     * @param idCard 身份编码
     * @return 省级编码。
     */
    public static String getProvinceByIdCard(String idCard) {
        int len = idCard.length();
        String sProvince = null;
        String sProvinNum = "";
        if (len == CHINA_ID_MIN_LENGTH || len == CHINA_ID_MAX_LENGTH) {
            sProvinNum = idCard.substring(0, 2);
        }
        sProvince = provinceCodes.get(sProvinNum);
        return sProvince;
    }

    /**
     * 根据身份编号获取户籍城市
     *
     * @param idCard 身份编码
     * @return 城市编码。
     */
    public static String getCityByIdCard(String idCard) {
        int len = idCard.length();
        String sProvince = null;
        String sCityNum = "";
        if (len == CHINA_ID_MIN_LENGTH || len == CHINA_ID_MAX_LENGTH) {
            sCityNum = idCard.substring(0, 4);
        }
        sProvince = cityCodes.get(sCityNum);
        return sProvince;
    }

    /**
     * 数字验证
     *
     * @param val
     * @return 提取的数字。
     */
    public static boolean isNum(String val) {
        return val == null || "".equals(val) ? false : val.matches("^[0-9]*$");
    }

    /**
     * 验证小于当前日期 是否有效
     *
     * @param iYear  待验证日期(年)
     * @param iMonth 待验证日期(月 1-12)
     * @param iDate  待验证日期(日)
     * @return 是否有效
     */
    public static boolean valiDate(int iYear, int iMonth, int iDate) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int datePerMonth;
        if (iYear < MIN || iYear >= year) {
            return false;
        }
        if (iMonth < 1 || iMonth > 12) {
            return false;
        }
        switch (iMonth) {
            case 4:
            case 6:
            case 9:
            case 11:
                datePerMonth = 30;
                break;
            case 2:
                boolean dm = ((iYear % 4 == 0 && iYear % 100 != 0) || (iYear % 400 == 0))
                        && (iYear > MIN && iYear < year);
                datePerMonth = dm ? 29 : 28;
                break;
            default:
                datePerMonth = 31;
        }
        return (iDate >= 1) && (iDate <= datePerMonth);
    }

    /**
     * @param 手机号
     * @return 是否有效
     * @description 验证手机号是否有效
     * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
     * 联通：130、131、132、152、155、156、185、186
     * 133、153、180、189、（1349卫通）
     * 总结起来就是第一位必定为1，第二位必定为3或5或7或8，其他位置的可以为0-9
     * @author zhangzhenyong
     * @update 2015年3月10日 下午3:10:18
     */
    public static boolean isMobileNO(String mobile) {
        String telRegex = "[1][3578]\\d{9}"; //"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (null == mobile || "".equals(mobile)) {
            return false;
        } else {
            return mobile.matches(telRegex);
        }
    }


    /**
     * 根据公安系统返回户籍信息解析其中省份信息及市级信息
     *
     * @param cityStr
     * @return
     */
    public static String[] matchCityByCityStr(String cityStr) {
        if (!StringUtils.isEmpty(cityStr)) {
            String[] array = new String[2];
            String provinceName = null;
            String cityName = null;
            //比对处理省份信息
            Iterator<String> it = provinceCodes.values().iterator();
            while (it.hasNext()) {
                provinceName = it.next();
                if (cityStr.indexOf(provinceName) != -1) {
                    array[0] = provinceName;
                    cityStr = cityStr.substring(cityStr.lastIndexOf(provinceName) + provinceName.length(), cityStr.length());
                    break;
                }
            }
            if (array[0] == null) {
                array[0] = "";
            }
            it = cityCodes.values().iterator();
            while (it.hasNext()) {
                cityName = it.next();
                if (cityStr.indexOf(cityName) != -1) {
                    array[1] = cityName;
                    break;
                }
            }
            if (array[1] == null) {
                array[1] = "";
            }
            return array;
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(validateCard("342201198610293617"));
    }
}
