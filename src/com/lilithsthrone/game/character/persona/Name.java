package com.lilithsthrone.game.character.persona;

import com.lilithsthrone.controller.xmlParsing.Element;
import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.race.AbstractRace;
import com.lilithsthrone.game.character.race.AbstractSubspecies;
import com.lilithsthrone.game.character.race.Race;
import com.lilithsthrone.game.character.race.Subspecies;
import com.lilithsthrone.threading.DocBuilders;
import com.lilithsthrone.utils.Util;
import org.w3c.dom.Document;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @since 0.1.0
 * @version 0.3.9.1
 * @author Innoxia
 */
public class Name {
	// Some help from behindthename.com's name lists to find unusual forms and/or same-letter names.
	// Name etymologies / explanations omitted to comply with their terms of use about redistributing their article contents.
	public static List<NameTriplet> human = (Util.newArrayListOfValues(
					new NameTriplet("亚历山大", "亚历克斯", "亚莉山德莉娅"),
					new NameTriplet("阿历克修斯", "亚历克斯", "亚历克西娅"),
					new NameTriplet("亚历克斯", "亚历克斯", "亚历克斯"),
					new NameTriplet("艾什", "艾什", "艾什利"),
					
					new NameTriplet("巴特", "贝利", "芭芭拉"),
					new NameTriplet("本", "本尼", "贝拉"),
					new NameTriplet("布里杰", "贝弗利", "布丽姬特"),
					new NameTriplet("布莱恩", "布里", "布丽安娜"),
					new NameTriplet("布兰特", "布雷特", "布丽塔"),
					
					new NameTriplet("凯里", "凯西", "坎迪斯"),
					new NameTriplet("卡尔", "卡罗", "卡洛琳"),
					new NameTriplet("塞西尔", "塞西尔", "塞西莉亚"),
					new NameTriplet("查理", "查理", "查理"),
					new NameTriplet("克里斯", "克里斯", "克里斯汀"),
					new NameTriplet("查克", "查理", "夏洛特"),
					
					new NameTriplet("丹尼尔", "丹尼", "达妮"),
					new NameTriplet("戴尔", "丹娜", "狄安娜"),
					new NameTriplet("大卫", "黛比", "黛比"),
					new NameTriplet("迪安", "德温", "迪安娜"),
					
					new NameTriplet("艾德华", "艾迪", "艾德娜"),
					new NameTriplet("伊莱", "艾默里", "伊芙琳"),
					new NameTriplet("艾略特", "艾默生", "伊莱恩"),
					new NameTriplet("伊曼纽尔", "马努", "艾曼纽"),
					new NameTriplet("艾米尔", "艾姆", "艾米丽"),
					new NameTriplet("伊凡", "伊夫林", "伊薇特"),
					
					new NameTriplet("菲利克斯", "菲力克", "菲莉瑟缇"),
					new NameTriplet("弗兰克", "弗兰基", "弗朗西丝"),
					new NameTriplet("弗莱德", "弗莱迪", "弗雷德丽卡"),
					
					new NameTriplet("盖布", "盖比", "盖尔"),
					new NameTriplet("乔治", "乔吉", "金杰儿"),
					new NameTriplet("格雷格", "格雷", "格蕾丝"),
					
					new NameTriplet("哈利", "哈莉", "海莉"),
					new NameTriplet("亨利", "亨尼", "亨丽埃塔"),
					new NameTriplet("汉克", "海登", "霍莉"),
					
					new NameTriplet("伊安", "茵迪歌", "伊莉雅"),
					new NameTriplet("伊西多尔", "伊兹", "伊莎贝尔"),

					new NameTriplet("詹姆斯", "詹米", "洁伊"),
					new NameTriplet("杰克", "杰基", "杰奎琳"),
					new NameTriplet("詹森", "杰基", "杰丝弥"),
					new NameTriplet("加雷斯", "杰伊", "詹妮弗"), // 加雷斯和詹妮弗一样，显然来自亚瑟王传说；詹妮弗是没有男性版本的
					new NameTriplet("伊安", "琴", "珍妮"),
					new NameTriplet("杰罗姆", "杰瑞", "杰丽"),
					new NameTriplet("杰西", "婕斯", "洁西卡"),
					new NameTriplet("约翰", "琴", "简"),
					new NameTriplet("约瑟夫", "乔乔", "乔茜"),

					new NameTriplet("卡尔", "卡洛", "卡拉"),
					new NameTriplet("凯文", "凯尔", "凯蒂"),
					new NameTriplet("卡斯帕", "卡特", "凯瑟琳"),
					new NameTriplet("肯尼斯", "凯丽", "坎德拉"),
					new NameTriplet("克里斯托夫", "克里斯", "克里斯蒂"),

					new NameTriplet("劳伦斯", "劳伦", "劳伦"),
					new NameTriplet("李", "利", "莉亚"),
					new NameTriplet("伦纳德", "林登", "利亚"),
					new NameTriplet("伦", "卢米", "劳拉"),
					new NameTriplet("莱斯", "莱斯利", "莱斯利"),
					new NameTriplet("路易斯", "卢", "路易斯"),
					
					new NameTriplet("麦迪逊", "曼迪", "玛德琳"),
					new NameTriplet("马克", "马里恩", "玛利亚"),
					new NameTriplet("马克斯韦尔", "马克斯", "玛克辛"),
					new NameTriplet("梅尔文", "梅尔", "梅丽莎"),
					new NameTriplet("米歇尔", "米奇", "迈卡拉"),
					//new NameTriplet("Mike", "Max", "Miranda"), // moved "Miranda" to "Randy/Randi/Miranda"
					
					new NameTriplet("内森", "纳特", "娜塔莉"),
					new NameTriplet("尼古拉斯", "尼基", "妮可"),
					new NameTriplet("诺曼", "诺伯", "诺拉"),
					
					new NameTriplet("奥斯卡", "奥德尔", "奥珀尔"),
					new NameTriplet("奥利弗", "奥利", "奥莉维亚"),
					
					new NameTriplet("帕特", "帕斯迪", "特里西娅"),
					new NameTriplet("佩吉", "帕克", "佩姬"),
					new NameTriplet("彼得", "佩顿", "佩特拉"),
					new NameTriplet("菲利普", "皮皮", "菲比"),
					
					new NameTriplet("昆汀", "奎因", "奎茵塔"),
					
					new NameTriplet("兰迪", "兰迪", "米兰达"),
					new NameTriplet("理查德", "里奇", "蕾切尔"),
					new NameTriplet("罗伯特", "罗比", "萝萍"),
					
					new NameTriplet("萨缪尔", "萨姆", "萨曼莎"),
					new NameTriplet("斯蒂芬", "斯蒂夫", "斯蒂芬妮"),
					//new NameTriplet("Stanley", "Sam", "Stephanie"),
					new NameTriplet("斯坦", "萨夏", "萨沐尔"),
					
					new NameTriplet("特伦斯", "特丽", "特蕾莎"),
					new NameTriplet("希欧多尔", "泰德", "朵拉"),
					new NameTriplet("托马斯", "托米", "塔弥欣"),
					new NameTriplet("提姆", "泰普勒", "缇娜"),
					new NameTriplet("特雷西", "特蕾西", "特莎"),
					new NameTriplet("托尼", "托倪", "冬妮娅"),

					new NameTriplet("尤利西斯", "安柏", "厄休拉"),

					new NameTriplet("瓦伦汀", "瓦尔", "瓦莱丽"),
					new NameTriplet("维恩", "瓦尔", "维奥莱特"),
					new NameTriplet("维克多", "维姬", "维多利亚"),
					new NameTriplet("维吉尔", "维克", "弗吉尼娅"),

					new NameTriplet("华莱士", "沃利斯", "万达"),
					new NameTriplet("威廉", "温特", "惠特尼"),
					new NameTriplet("威尔", "韦恩", "威洛"),
					new NameTriplet("韦恩", "韦恩", "格温")
	));
	
	public static List<NameTriplet> equine = (Util.newArrayListOfValues(
					new NameTriplet("阿库亚", "阿夸", "阿库娅"),
					
					new NameTriplet("布兰博", "布兰博", "布兰博"),

					new NameTriplet("达舍", "达舍", "达舍"),
					new NameTriplet("达佐", "达佐", "达佐"),

					new NameTriplet("燧石", "燧石", "燧石"),
					
					new NameTriplet("弗利特", "弗利特", "芙莉特"),
					
					new NameTriplet("午夜", "午夜", "午夜"),
					new NameTriplet("月风", "月风", "月风"),

					new NameTriplet("光轮", "光轮", "光轮"),

					new NameTriplet("珀尔", "珀尔", "珀尔"),
					new NameTriplet("刺精", "刺精", "刺精"),
					
					new NameTriplet("天足", "天足", "天足"),
					new NameTriplet("斯塔尔", "斯塔尔", "斯塔尔"),
					new NameTriplet("斯皮里忒", "斯皮里忒", "斯皮里忒"),
					
					new NameTriplet("雷鬃", "雷鬃", "雷鬃"),
					new NameTriplet("暮光", "暮光", "暮光"),
					
					new NameTriplet("狂野之光", "野性之光", "自然之光")));
	
	// Similar to equine names
	public static List<NameTriplet> reindeer = (Util.newArrayListOfValues(
			
			new NameTriplet("达舍", "达舍", "达舍"),
			new NameTriplet("舞者", "舞者", "舞者"),
			new NameTriplet("阔步者", "腾跃者", "雀跃儿"),
			new NameTriplet("维克森", "维克森", "薇可森"),
			new NameTriplet("彗星", "彗星", "彗星"),
			new NameTriplet("丘比特", "丘比特", "丘比特"),
			new NameTriplet("淘气包", "淘气包", "淘气包"),
			new NameTriplet("布莱克森", "布莱克森", "布莱克森"),
			
			new NameTriplet("阿库亚", "阿夸", "阿库娅"),
			
			new NameTriplet("布兰博", "布兰博", "布兰博"),

			new NameTriplet("达舍", "达舍", "达舍"),
			new NameTriplet("炫光", "炫光", "炫光"),

			new NameTriplet("弗林特", "伏林特", "芙林特"),
			
			new NameTriplet("弗利特", "弗利特", "芙莉塔"),
			
			new NameTriplet("午夜", "午夜", "午夜"),
			new NameTriplet("月风", "月风", "月风"),

			new NameTriplet("雨云", "雨云", "雨云"),

			new NameTriplet("珀尔", "珀尔", "珀尔"),
			new NameTriplet("普利谢", "普利希", "普利茜"),
			
			new NameTriplet("天足", "天足", "天足"),
			new NameTriplet("斯塔尔", "斯塔尔", "斯塔尔"),
			new NameTriplet("斯皮里忒", "斯皮里忒", "斯皮里忒"),
			
			new NameTriplet("雷鬃", "雷鬃", "雷鬃"),
			new NameTriplet("暮光", "暮光", "暮光"),
			
			new NameTriplet("狂野之光", "野性之光", "自然之光")));
	
	// No offence if your name is on here... x_x
	// Significantly modified with help from behindthename.com. (No more infinite Carls!)
	// Code from later on edited a bit to throw some of these names onto other NPCs.
	public static List<NameTriplet> prostitute = (Util.newArrayListOfValues(
					new NameTriplet("阿洛", "阿尔登", "阿尔琳"),
					new NameTriplet("安布罗斯", "安柏", "安柏"),
					new NameTriplet("奥古斯特", "奥布里", "奥特姆"),

					new NameTriplet("鲍德温", "邦比", "班比"),
					new NameTriplet("布兰登", "布朗迪", "布兰迪"),
					new NameTriplet("布雷特", "布里特", "布里特妮"),
					new NameTriplet("布莱恩", "布琳", "布里安娜"),

					new NameTriplet("卡西迪", "凯西", "卡桑德拉"),
					new NameTriplet("卡尔", "查理", "夏琳"),
					new NameTriplet("查德", "钱宁", "仙黛尔"),
					new NameTriplet("奇普", "钱宁", "克洛艾"),
					new NameTriplet("克劳迪奥", "克劳德", "克劳迪娅"),
					new NameTriplet("科尔", "康尼", "考特妮"),
					new NameTriplet("克里斯", "克丽西", "克丽丝特尔"),
					new NameTriplet("凯西", "凯西", "凯茜"),

					new NameTriplet("多姆", "多利", "多莉"),
					new NameTriplet("德文", "德文", "德文"),
					new NameTriplet("戴尔", "达科塔", "达科塔"),

					new NameTriplet("艾美特", "艾玛露", "艾玛露"), // Great Scott!
					
					new NameTriplet("希斯", "希瑟", "希瑟"),

					new NameTriplet("吉米", "珍妮", "珍妮"),
					new NameTriplet("乔", "乔", "茱莲妮"),

					new NameTriplet("凯尔", "凯莉", "吉拉"),
					new NameTriplet("肯", "肯尼", "坎德拉"),
					new NameTriplet("克里斯", "克里斯", "克里斯塔"),
					new NameTriplet("凯尔西", "凯尔西", "凯尔茜"),

					new NameTriplet("劳伦斯", "劳伦", "劳伦"),
					
					new NameTriplet("米奇", "米丝蒂", "米丝蒂"),
					new NameTriplet("梅尔", "梅尔", "美洛蒂"),
					new NameTriplet("麦克", "明迪", "明迪"),
					
					new NameTriplet("尼基", "尼基", "尼基"),
					new NameTriplet("诺埃尔", "诺埃尔", "诺艾尔"),
		
					new NameTriplet("皮尔斯", "菲尼克斯", "佩内洛普"),
					
					new NameTriplet("瑞茜", "莉丝", "瑞芭"),
					new NameTriplet("里纳德", "雷内", "蕾妮"),
					new NameTriplet("鲁迪", "鲁迪", "露迪"),

					new NameTriplet("萨万纳", "萨万娜", "萨万娜"),
					new NameTriplet("萨姆", "萨姆", "萨曼莎"),
					new NameTriplet("斯科特", "谢尔比", "斯卡尔莱特"),
					new NameTriplet("塞思", "赛普特伯", "塞雷娜"),
					new NameTriplet("谢尔比", "谢尔比", "谢尔比"),
					new NameTriplet("肖恩", "谢恩", "肖娜"),
					new NameTriplet("锡德", "悉尼", "西拉"),

					new NameTriplet("塔米", "塔米", "塔米"),
					new NameTriplet("泰特", "塔拉", "塔拉"),
					new NameTriplet("泰勒", "泰勒", "泰勒"),
					new NameTriplet("特里斯坦", "特里纳", "特里娜"),
					
					new NameTriplet("文森特", "维克", "维克辛"),
					
					new NameTriplet("扬西", "约奇", "约兰达")
		
					//new NameTriplet("Urleen", "Urleen", "Urleen") // supplanted by the Arlo/Arden/Arleen triplet - "Urleen" seems much rarer
	));
	
	public static List<NameTriplet> petNames = Util.newArrayListOfValues(
			new NameTriplet("埃斯", "阿比", "阿比"),
			new NameTriplet("班迪特", "巴韦", "班比"),
			new NameTriplet("钱普", "凯西", "坎迪"),
			new NameTriplet("杜克", "多蒂", "杜切斯"),
			new NameTriplet("恩贝尔", "恩贝尔", "恩贝尔"),
			new NameTriplet("费利克斯", "费里斯", "福克茜"),
			new NameTriplet("刚诺", "戈尔迪", "戈尔迪"),
			new NameTriplet("因迪", "因迪", "艾薇"),
			new NameTriplet("杰特", "朱厄尔", "乔伊"),
			new NameTriplet("金", "基珀", "姬蒂"),
			new NameTriplet("利奥", "洛乌", "洛拉"),
			new NameTriplet("马克斯韦尔", "马克斯", "玛克辛"),
			new NameTriplet("奥利", "奥利", "奥利维娅"),
			new NameTriplet("佩珀", "佩尼", "皮切斯"),
			new NameTriplet("斯科特", "桑迪", "桑迪"),
			new NameTriplet("司泊特", "索科斯", "索科斯"),
			new NameTriplet("特克斯", "特丝", "特茜"),
			new NameTriplet("威士奇", "文斯克斯", "薇洛"));
	
	public static List<String> surnames = new ArrayList<>(Arrays.asList("Adams", "Ali", "Allen", "Anderson",
			"安德鲁", "阿姆斯特朗", "阿特金森", "贝利",
			"贝克", "巴克", "巴恩斯", "贝尔",
			"本内特", "贝里", "布思", "布拉德利",
			"布鲁克斯", "布朗", "巴特勒", "坎贝尔",
			"卡拉", "卡雷特", "钱伯斯", "查普曼",
			"克拉克", "克莱克", "科尔", "科林斯",
			"库克", "库珀", "考克斯", "坎宁安",
			"戴维兹", "戴维斯", "道森", "迪安",
			"迪克森", "爱德华兹", "埃利斯", "埃文斯",
			"费舍尔", "福斯特", "福克斯", "加德纳",
			"乔治", "吉布森", "吉尔", "戈登",
			"格兰姆", "格雷特", "盖瑞", "格林",
			"格里菲斯", "哈尔", "汉密尔顿", "哈珀",
			"哈里斯", "哈里森", "哈特", "哈维",
			"希尔", "霍姆斯", "哈德森", "休斯",
			"亨特", "亨特", "杰克逊", "詹姆斯",
			"詹金斯", "约翰逊", "约翰斯顿", "琼斯",
			"考尔", "凯丽", "肯尼迪", "汗",
			"金", "奈特", "林恩", "劳伦斯",
			"罗森", "李", "里维斯", "罗伊德",
			"麦克唐纳", "马歇尔", "马丁", "梅森",
			"马修斯", "麦当劳", "米勒", "米尔斯",
			"米歇尔", "摩尔", "摩根", "莫里斯",
			"墨菲", "穆雷", "欧文", "帕尔默",
			"帕克", "帕特尔", "皮尔斯", "皮尔森",
			"菲利普斯", "普尔", "鲍威尔", "普莱斯",
			"里德", "雷诺兹", "理查兹", "理查森",
			"罗伯茨", "罗伯逊", "鲁滨逊", "罗杰斯",
			"萝丝", "罗斯", "罗素", "瑞安",
			"桑德斯", "斯考特", "肖", "辛普森",
			"史密斯", "斯宾塞", "史蒂文斯", "斯图尔特",
			"史东", "泰勒", "托马斯", "汤普森",
			"汤姆森", "特纳", "沃克尔", "沃尔什",
			"沃德", "沃森", "瓦特", "韦伯",
			"威尔斯", "韦斯特", "怀特", "威尔金森",
			"威廉姆斯", "威廉姆森", "威尔森", "伍德",
			"Wright", "Young"));
	
	public static List<String> youkoSurnames = new ArrayList<>(Arrays.asList("Abiko", "Abo", "Aburaya", "Achikita",
            "足立", "足立原", "阿賀野", "阿形",
            "我妻", "阿川", "粟国", "亚波根",
            "英田", "相原", "相川", "爱川", "合口",
            "爱敬", "相本", "相奈良", "爱野",
            "逢坂", "相内", "赤川", "赤木",
            "赤星", "赤井", "赤池", "赤松",
            "赤峰", "赤西", "川满", "赤崎",
            "赤葦", "赤城", "赤舌", "赤月",
            "赤堤", "明美", "亚纪", "明羽",
            "秋原", "秋元", "秋野", "秋里",
            "秋岛","秋筱","秋田","秋谷",
            "天池","天海","天川","天井",
            "雨宫","天野","天成","天谷",
            "天谷", "雨森", "天地", "安室",
            "天津散", "谷口", "安藤", "安野",
            "安斉", "青叶", "葵", "青池",
            "青木", "青岭", "青沼", "青田",
            "青柳", "青山", "苍崎", "青空",
            "荒", "新垣", "新井", "新垣",
            "荒川", "荒木", "荒波", "嵐",
            "新城", "新田", "新谷", "新谷",
            "有马", "有冈", "有沢", "有田",
            "有吉", "朝", "朝比奈", "浅井",
            "朝香", "朝川", "浅野", "安里",
            "足利", "中里", "厚田", "绫野",
            "绫小路", "绫小路", "阿座原", "东",
            "马场", "阪东", "武藤", "武士田",
            "茶柱", "茶芸", "千秋", "千叶",
            "知花", "千草", "千贺藤", "千野",
            "千荣", "千里", "长曾我部", "长宗我部",
            "伊达", "出口", "土井", "户谷",
            "江口", "江尾", "江夏", "远藤",
            "榎田", "榎本", "江藤", "江藤",
            "藤", "藤原", "藤桥", "藤井",
            "藤川", "藤森", "藤村", "藤中",
            "藤野", "藤宫", "藤崎", "藤里",
            "藤沢", "藤浦", "藤原", "藤山",
            "藤吉", "深川", "深见", "深濑",
            "福田", "福原", "福泉", "福本",
            "福永", "福岛", "福山", "福世",
            "古川", "古沢", "古濑", "古屋",
            "双叶", "二村", "冬木", "雅紀",
            "郭田", "后藤", "后藤", "吴屋",
            "具志坚", "八光", "八村", "秦",
            "芳贺", "萩野", "萩原", "始",
            "浜", "浜田", "浜田手", "浜口",
            "浜川", "浜村", "浜野", "浜崎",
            "花房", "花井", "花村", "花沢",
            "半田", "羽田", "羽根山", "羽生",
            "羽入", "原", "原田", "原口",
            "旗本", "张替", "春树", "春菜",
            "春野", "春田", "春山", "长谷川",
            "桥", "桥口", "桥仓", "桥丘",
            "柱", "端谷", "桥山", "秦",
            "畑", "波多野", "幡谷", "初",
            "服部", "隼", "早川", "早河",
            "叶山", "早坂", "林原", "林田",
            "日高", "比嘉", "东", "东田",
            "东山", "冰见", "日野", "日出",
            "平井", "平川", "平松", "平野",
            "平坂", "平泽", "平岛", "平田",
            "枚谷", "裕井", "裕美", "裕野",
            "裕沢", "裕濑", "裕岛", "裕田",
            "昼间", "久松", "日垂", "仁岁",
            "一柳", "北条", "洞上", "焰",
            "堀", "堀江", "堀米", "堀北",
            "广野", "星", "星宫", "星野",
            "星崎", "星田", "细川", "细尾",
            "北条", "伊吹", "市田", "市原",
            "市桥", "市川", "市野", "一宫",
            "市罔", "井田", "家入", "家上",
            "五十岚", "伊芸", "齐", "池",
            "池原", "池本", "池留", "池杉",
            "生田", "今田", "今枝", "今川",
            "今井", "今石", "今村", "今里",
            "今禄罔", "今田", "稻垣", "稻村",
            "稻妻", "猪", "井上", "乾",
            "犬饲", "伊织", "伊佐后", "勇",
            "谏山", "石田", "石户", "石黑",
            "石原", "石井", "石仓", "石本",
            "石内", "石渡", "石山", "石塚",
            "矶部", "矶谷", "矶崎", "岩秋",
            "岩江", "岩本", "岩野", "岩冈",
            "岩崎", "岩清水", "岩田", "岩谷",
            "岩山", "泉原", "泉", "出云",
            "阵内", "顺子", "壁", "甲",
            "角川", "角松", "角岛", "角田",
            "加濑", "加贺", "海马", "梶",
            "梶谷", "梶浦", "梶原", "柿",
            "柿原", "柿村", "柿沼", "柿田",
            "角", "角谷", "蒲田", "龟井",
            "龟梨", "神野", "神谷", "神山",
            "加森", "加本", "神原", "神林",
            "神户", "神田", "金田", "金木",
            "金子", "金丸", "金城", "菅野",
            "甘露寺", "唐松", "鸟", "笠井",
            "加势", "柏", "柏原", "柏田",
            "柏出", "柏户", "柏枝田", "柏木",
            "柏原", "片桐", "桂", "川",
            "川端", "川田", "川原", "川井",
            "川北", "川本", "川村", "川边",
            "川中", "川西", "川野", "川崎",
            "川岛", "川下", "川田", "川内",
            "风间", "风见", "风", "风早",
            "风谷", "一之", "见马", "吉田",
            "木田", "木田村", "木户", "木原",
            "桔梗", "金城", "木野", "木下",
            "衣笠", "桐谷", "切村", "霧岛",
            "桐谷", "岸", "岸本", "北林",
            "北川", "北原", "北村", "北仁",
            "北野", "北尾", "北冈", "木谷",
            "清子", "清水", "清田", "清武",
            "小桥", "小平", "小金", "小平",
            "小出", "恋涯久保", "小池", "小岛",
            "小川", "小木", "小松", "小松崎",
            "小宫", "小室", "小中", "近田",
            "近藤", "近藤", "小西", "今野",
            "今枝", "小坂", "小杉", "小竹",
            "小谷", "寿", "小月", "高内",
            "小山", "子安", "上树", "上野",
            "上月", "久保", "久保", "久保田",
            "朽木", "工藤", "久贺", "钉宫",
            "久原", "鲸", "熊谷", "熊井",
            "熊木", "国田", "国松", "国崎",
            "仓真", "仓泽", "仓田", "栗林",
            "栗原", "栗桥", "栗本", "栗栖",
            "栗山", "栗塚", "黑马", "黑田",
            "黑木", "黑桥", "黑岩", "黑川",
            "黑木", "黑子", "黑宫", "黑沼",
            "黑坂", "黑崎", "黑岛", "黑柳",
            "草柳", "串田", "串枝", "楠木",
            "桑原", "桑原", "桑子", "京极",
            "久谷", "町", "町田", "前原",
            "前岛", "前川", "前岛", "前山田",
            "真岛", "牧村", "牧野", "牧田",
            "真锅", "真中", "真下", "增山",
            "松", "松原", "松枝", "松田",
            "松平", "松桥", "松井", "松方",
            "松木", "松前", "松村", "松永",
            "松泽", "松野", "松尾", "松岛",
            "松浦", "松山", "松雪", "松崎",
            "真渡", "满添", "绿川", "三船",
            "三原", "三上", "三日月", "三木",
            "源", "湊", "港崎", "峰",
            "岬", "三岛", "御曹", "三田",
            "光江", "光木", "光具", "光井",
            "三浦", "三和", "宫", "宫部",
            "宫口", "宫原", "宫市", "宫家",
            "宫子", "宫间", "宫野", "宫良",
            "宫下", "宫田", "宫内", "宫崎",
            "宫里", "宫泽", "水深", "水原",
            "水川", "水野", "水泽", "水田",
            "水玉", "水谷", "望月", "萌黄",
            "桃井", "百濑", "守合", "守藤",
            "守原", "守平", "森川", "森北",
            "森本", "森中", "森下", "森田",
            "守内", "守屋", "守山", "茂木",
            "本桥", "本目", "本泽", "向井",
            "宗川", "村", "村滨", "村桥",
            "村上", "村川", "村北", "村松",
            "村中", "村冈", "村岛", "村田",
            "村滝", "武迫", "武迫", "武者小路",
            "武者小路", "武藤", "陆", "陆实",
            "明石", "苗木", "永井", "永松",
            "永野", "永尾", "永冈", "永岛",
            "长洲", "长门", "永塚", "永山",
            "内藤", "中田", "中藤", "中井",
            "中川", "中井", "中松", "中岭",
            "中本", "中西", "中尾", "中田",
            "中内", "中浦", "中山", "七海",
            "七岛", "七月", "奈良", "成泽",
            "成田", "奈留", "夏川", "夏实",
            "根保", "扭", "新仓", "二阶堂",
            "二宫", "西", "西田", "西野",
            "西尾", "西山", "新田", "登良",
            "信长", "野岛", "野尾", "能美",
            "野村", "野中", "能登", "能登宇",
            "沼", "小原", "织田", "大贺",
            "大神", "小笠原", "小川", "荻田",
            "小仓", "小栗", "大滨", "大原",
            "大桥", "大林", "大贺", "大堀",
            "大谷", "冈", "冈田", "冈本",
            "冈村", "冈野", "冈岛", "大川",
            "冈崎", "大内", "大田", "奥",
            "大久保", "奥平", "奥川", "奥川",
            "奥村", "奥野", "奥山", "大森",
            "大本", "大村", "大西", "大野",
            "大野", "大上", "大口", "大谷",
            "大殿", "大殿", "大阪", "尾崎",
            "大迫", "大里", "大泽", "大岛",
            "太田", "大高", "大滨", "大谷",
            "大谷", "邻里", "大冢", "大月",
            "尾張", "小谷川", "大山", "大山田",
            "尾崎", "小泽", "小津", "力丸",
            "凛", "凛林", "梨岛", "路加",
            "鹿山", "龙神", "龙子", "龙峰",
            "龙渊", "龙崎", "冴木", "相模",
            "西原", "西园", "西本", "坂",
            "坂林", "坂口", "坂原", "坂井",
            "坂木", "坂本", "坂根", "坂谷",
            "咲", "咲本", "咲山", "佐久间",
            "佐仓", "樱本", "樱见", "樱野",
            "樱坂", "鲨岛", "左门", "真田",
            "三条", "三条", "佐野", "笹野",
            "笹山", "蝎", "里美", "里屋",
            "沢", "沢田", "沢城", "狭",
            "关", "关口", "仙寿", "仙里",
            "芹泽", "濑户", "濑头", "节史",
            "柴崎", "柴田", "柴山", "柴谷",
            "涩谷", "岛", "岛田", "岛津",
            "岛本", "岛村", "岛冈", "岛崎",
            "岛津", "志免", "下野", "下冈",
            "下月", "下山", "志村", "新田",
            "新开", "信夫", "新田", "莜原",
            "莜本", "莜冈", "莜崎", "莜津",
            "莜夜", "汐", "汐川", "白井",
            "白石", "白川", "白森", "宍户",
            "定祐", "园田", "末野", "末冈",
            "菅", "菅野", "菅原", "杉枝",
            "杉原", "杉森", "杉本", "杉村",
            "杉野", "杉田", "杉谷", "杉浦",
            "杉山", "隅水", "住友", "砂取",
            "凉风", "铃村", "铃谷", "鈴屋",
            "多田", "田所", "田原", "平",
            "田尻", "高田", "高木", "高桐",
            "高原", "高畑", "高秀", "高井",
            "高石", "高木", "高桑", "高丸",
            "高松", "田上", "高光", "高森",
            "高村", "高梨", "高野", "高尾",
            "高濑", "高须", "高田", "高山",
            "高柳", "高里", "武田", "竹原",
            "武井", "武宫", "竹水", "竹村",
            "竹野", "竹下", "竹田津", "竹内",
            "泷", "泷上", "玉川", "玉井",
            "宍上", "翔", "庄司", "酱油",
            "田村", "田丸", "玉城", "玉月",
            "玉山", "玉之", "田门", "多村",
            "谷川", "谷本", "谷山", "谷地",
            "田之濑", "田沼", "立胁", "立田",
            "巽", "龙冈", "立岛", "手冢",
            "手游", "寺田", "寺崎", "寺内",
            "手塚", "户部", "户田", "户上",
            "东条", "户谷", "德川", "德井",
            "富松", "富家", "富田", "富安",
            "友枝", "友坂", "友良", "鸟越",
            "东条", "冬谷", "豊田", "豊口",
            "丰田", "坪", "坪井", "土田",
            "土井", "土谷", "津田", "津爪",
            "津连", "津根", "杉原", "宇内",
            "宇治原", "宇岛", "宇理", "内田",
            "塚本", "月形", "月见", "月野",
            "月山", "月夜见", "月岚", "常松",
            "常田", "常井", "鹤冈", "津野田",
            "津野井", "津田", "津藏", "宇千田",
            "浦本", "浦冈", "浦冈", "浦下",
            "植木", "植松", "植村", "植下",
            "上杉", "上田", "马场", "梅田",
            "梅原", "梅沢", "宇门", "宇野",
            "浦岛", "浦野", "浦野", "浦田",
            "宇都宫", "漩涡", "和田", "涉原",
            "若林", "若木", "若松", "若田",
            "若土", "和邇", "若里", "渡部",
            "渡永", "渡", "矢部", "矢田",
            "八神", "八木", "八木良", "八雲",
            "山", "山形", "山胁", "山端",
            "山川", "山水", "山村", "山中",
            "山根", "山里", "山谷", "柳",
            "柳本", "柳泽", "柳井", "安井",
            "靖树", "安西", "安良冈", "安山",
            "安里", "矢崎", "与市", "与市园",
            "与市前", "横滨", "横沟", "横村",
            "横谷", "横手", "横山", "东西北南",
            "夜长", "与那岭", "与音", "米田",
            "米一", "米泽", "吉浜", "吉原",
            "吉井", "吉川", "吉本", "吉村",
            "吉野", "吉沼", "吉冈", "芳泽",
            "唯", "行森", "幸元", "雪山",
            "Yukizome", "Yumi", "Yuuma", "Zabatsu"));
	
	public static List<String> lilinNames = List.of(
            "洛维耶纳",
            "拉谢尔",
            "莉西丝",
            "莉安娜",
            "莉莉莎",
            "莉尼克西",
            "Liloria");
	
	private static Map<String, List<NameTriplet>> racialNames = new HashMap<>();
	
	static {
		for(AbstractSubspecies subspecies : Subspecies.getAllSubspecies()) {
			if(subspecies.getRace()==Race.HORSE_MORPH) {
				racialNames.put(Subspecies.getIdFromSubspecies(subspecies), equine);
			}
			if(subspecies.getRace()==Race.REINDEER_MORPH) {
				racialNames.put(Subspecies.getIdFromSubspecies(subspecies), reindeer);
			}
		}
		
		// Modded names:
		
		Map<String, Map<String, File>> moddedFilesMap = Util.getExternalModFilesById("/race", null, "names");
		for(Entry<String, Map<String, File>> entry : moddedFilesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					String raceID = innerEntry.getKey().replaceAll("_race", "");
					raceID = raceID.replaceAll("_names", "");
					
					Map<String, List<NameTriplet>> importedNames = importNames(innerEntry.getValue(), entry.getKey(), true, raceID);
					if(importedNames!=null && !importedNames.isEmpty()) {
						for(Entry<String, List<NameTriplet>> importedNameEntry : importedNames.entrySet()) {
							racialNames.putIfAbsent(importedNameEntry.getKey(), new ArrayList<>());
							racialNames.get(importedNameEntry.getKey()).addAll(importedNameEntry.getValue());
						}
//						System.out.println("Added modded names of race: "+raceID);
					}
				} catch(Exception ex) {
					System.err.println("Loading modded names failed. File path: "+innerEntry.getValue().getAbsolutePath());
										ex.printStackTrace(System.err);
				}
			}
		}
		
		// External res names:
		
		Map<String, Map<String, File>> filesMap = Util.getExternalFilesById("res/race", null, "names");
		for(Entry<String, Map<String, File>> entry : filesMap.entrySet()) {
			for(Entry<String, File> innerEntry : entry.getValue().entrySet()) {
				try {
					String raceID = innerEntry.getKey().replaceAll("_race", "");
					raceID = raceID.replaceAll("_names", "");
					
					Map<String, List<NameTriplet>> importedNames = importNames(innerEntry.getValue(), entry.getKey(), false, raceID);
					if(importedNames!=null && !importedNames.isEmpty()) {
						for(Entry<String, List<NameTriplet>> importedNameEntry : importedNames.entrySet()) {
							racialNames.putIfAbsent(importedNameEntry.getKey(), new ArrayList<>());
							racialNames.get(importedNameEntry.getKey()).addAll(importedNameEntry.getValue());
						}
//						System.out.println("Added res names of race: "+raceID);
					}
				} catch(Exception ex) {
					System.err.println("Loading names failed. File path: "+innerEntry.getValue().getAbsolutePath());
										ex.printStackTrace(System.err);
				}
			}
		}
	}
	

	private static Map<String, List<NameTriplet>> importNames(File XMLFile, String author, boolean mod, String raceID) {
		if (XMLFile.exists()) {
			try {
				Document doc = DocBuilders.parseDoc(XMLFile);
				
				// Cast magic:
				doc.getDocumentElement().normalize();
				
				Element coreElement = Element.getDocumentRootElement(XMLFile); // Loads the document and returns the root element - in statusEffect files it's <statusEffect>
				
				boolean additionalNames = Boolean.valueOf(coreElement.getAttribute("additional"));
				
				Map<String, List<NameTriplet>> importedNameMap = new HashMap<>();
				
				for(Element outerElement : coreElement.getAllOf("subspecies")) {
					String subspeciesId = outerElement.getAttribute("id");
					List<NameTriplet> importedNames = new ArrayList<>();
					for(Element e : outerElement.getAllOf("nameTriplet")) {
						String femName = e.getOptionalFirstOf("fem").isPresent()?e.getMandatoryFirstOf("fem").getTextContent():null;
						String andName = e.getOptionalFirstOf("and").isPresent()?e.getMandatoryFirstOf("and").getTextContent():null;
						String masName = e.getOptionalFirstOf("mas").isPresent()?e.getMandatoryFirstOf("mas").getTextContent():null;
						
						if(femName!=null || andName!=null || masName!=null) {
							if(femName==null) {
								femName = andName!=null?andName:masName;
							}
							if(andName==null) {
								andName = femName!=null?femName:masName;
							}
							if(masName==null) {
								masName = andName!=null?andName:femName;
							}
							importedNames.add(new NameTriplet(masName, andName, femName));
//							System.out.println("Added ("+subspeciesId+"): "+masName+", "+andName+", "+femName);
						}
					}
					if(subspeciesId.isEmpty() || subspeciesId.equalsIgnoreCase("ALL")) {
						for(AbstractSubspecies subspecies : Subspecies.getAllSubspecies()) {
							if(subspecies.getRace()==Race.getRaceFromId(raceID)) {
								importedNameMap.putIfAbsent(Subspecies.getIdFromSubspecies(subspecies), new ArrayList<>());
								importedNameMap.get(Subspecies.getIdFromSubspecies(subspecies)).addAll(importedNames);
							}
						}
						
					} else {
						importedNameMap.putIfAbsent(subspeciesId, new ArrayList<>());
						importedNameMap.get(subspeciesId).addAll(importedNames);
					}
				}
				if(additionalNames) {
					for(AbstractSubspecies subspecies : Subspecies.getAllSubspecies()) {
						if(subspecies.getRace()==Race.getRaceFromId(raceID)) {
							importedNameMap.get(Subspecies.getIdFromSubspecies(subspecies)).addAll(human);
						}
					}
				}
				
				return importedNameMap;
				
			} catch(Exception ex) {
				ex.printStackTrace();
				System.err.println("AbstractRacialBody was unable to be loaded from file! (" + XMLFile.getName() + ")\n" + ex);
			}
		}
		return null;
	}
	
	public static String getRandomName(GameCharacter gc) {
		switch(gc.getFemininity()) {
			case MASCULINE_STRONG:
			case MASCULINE:
				return getRandomTriplet(gc.getSubspecies()).getMasculine();
			case ANDROGYNOUS:
				return getRandomTriplet(gc.getSubspecies()).getAndrogynous();
			case FEMININE:
			case FEMININE_STRONG:
			default:
				return getRandomTriplet(gc.getSubspecies()).getFeminine();
		}
	}
	
	/** Surnames of all demons and half-demons reflect their Lilin lineage.<br/>
	 * For the case of descendents of Lyssieth, a surname would be:<br/>
	 * Lyssieth<b>martusarri</b> (Lyssieth's designated heir. Only Lilaya has this surname. This needs to be manually set.)<br/>
	 * Lyssieth<b>marturabitu</b> (Eldest daughter, if not the designated heir. As most Lilin's eldest daughters are also their designated heir, this surname is very rare.)<br/>
	 * Lyssieth<b>martuilani</b> (A direct daughter of Lyssieth.)<br/>
	 * Lyssieth<b>martu</b> (Lyssieth's grand-daughters or further.)<br/>
	 * <b>Note:</b> Imps descended from Lilin (in these examples, Lyssieth) are given the surname 'Lyssiethmartu', <i>however</i>, in LT's society, it is considered a great insult against Lyssieth to ever address an imp by this.
	 *  If they are ever transformed into a demon, they may use this surname, even if the Lilin who transformed them is not Lyssieth herself. (Again, however, that would be a grave insult against Lyssieth.)
	 * @param gc
	 * @return
	 */
	private static String getDemonSurname(GameCharacter gc) {
		String surname = "";
		GameCharacter mother = gc.getMother();
		
		if(mother!=null) {
			while(mother.getMother()!=null) {
				mother = mother.getMother();
			}
			if(mother.getSubspecies()==Subspecies.LILIN
					|| mother.getSubspecies()== Subspecies.ELDER_LILIN) {
				surname = mother.getName(false);
				List<GameCharacter> offspring = mother.getAllCharactersOfRelationType(Relationship.Parent);
				if(offspring.contains(gc)) {
					offspring.sort((c1, c2) -> c1.getAgeValue()-c2.getAgeValue());
					if(offspring.get(0).equals(gc)) {
						surname+="马尔图拉比图";
					} else {
						surname+="马尔图拉尼";
					}
				} else {
					surname+="马尔图";
				}
			}
			
		} else {
			surname = lilinNames.get(Util.random.nextInt(lilinNames.size()))+"martu";
		}
		
		return surname;
	}
	
	public static String getSurname(GameCharacter gc) {
		GameCharacter mother = gc.getMother();
		if(mother!=null) {
			while(mother.getMother()!=null) {
				mother = mother.getMother();
			}
			return mother.getSurname();
		}
		
		if(gc.getBody()!=null
				&& (gc.getSubspecies()==Subspecies.FOX_ASCENDANT
						|| gc.getSubspecies()==Subspecies.FOX_ASCENDANT_ARCTIC
						|| gc.getSubspecies()==Subspecies.FOX_ASCENDANT_FENNEC)) {
			return youkoSurnames.get(Util.random.nextInt(youkoSurnames.size()));
		}
		if(gc.getBody()!=null) {
			if(gc.getRace()==Race.DEMON || gc.getRace()==Race.ELEMENTAL) {
				return getDemonSurname(gc);
			}
		}
		return surnames.get(Util.random.nextInt(surnames.size()));
	}
	
	public static NameTriplet getRandomTriplet(AbstractSubspecies subspecies) {
		NameTriplet name = Util.randomItemFrom(human);
		AbstractRace r = subspecies.getRace();
		
		if(r==Race.DEMON || r==Race.ELEMENTAL) {
			name = getDemonName();
			
		} else if(racialNames.containsKey(Subspecies.getIdFromSubspecies(subspecies))) {
			name = Util.randomItemFrom(racialNames.get(Subspecies.getIdFromSubspecies(subspecies)));
			
		} else if(Math.random()<0.1) { // If no racial names are found, then occasionally throw some "prostitute" names in there
			name = Util.randomItemFrom(prostitute); 
		}
		
		return name;
	}
	
	public static List<NameTriplet> getAllNameTriplets(AbstractSubspecies subspecies) {
		if(racialNames.containsKey(Subspecies.getIdFromSubspecies(subspecies))) {
			return new ArrayList<>(racialNames.get(Subspecies.getIdFromSubspecies(subspecies)));
		}
		return new ArrayList<>(human);
	}
	
	private static NameTriplet getDemonName() {
		String[] prefixFem = new String[] {"艾拉", "贝拉", "卡埃", "德瓦", "伊拉", "法埃", "赫拉", "伊萨", "卡萨", "洛", "妮萨", "奥艾拉", "蕾", "希萨", "维克萨", "维娜"};
		String[] prefixMas = new String[] {"埃达", "博罗", "弗罗", "赫利奥", "基里", "扎拉"};
		
		String[] postfixFem = new String[] {"吉克丝", "瑞思", "妮", "妮可丝", "西丝", "崔克丝"};
		String[] postfixMas = new String[] {"吉克斯", "瑞思", "尼", "尼克斯", "西斯", "崔克斯"};
		
		String femName = prefixFem[Util.random.nextInt(prefixFem.length)] + postfixFem[Util.random.nextInt(postfixFem.length)];
		char startingChar = femName.charAt(0);

		String masName = prefixMas[Util.random.nextInt(prefixMas.length)] + postfixMas[Util.random.nextInt(postfixMas.length)];
		
		List<String> masculineNames = new ArrayList<>();
		for(String s : prefixMas) {
			if(s.charAt(0) == startingChar) {
				masculineNames.add(s);
			}
		}
		if(!masculineNames.isEmpty()) {
			masName = masculineNames.get(Util.random.nextInt(masculineNames.size())) + postfixMas[Util.random.nextInt(postfixMas.length)];
		}
		
		return new NameTriplet(masName, femName, femName);
	}
	
	public static NameTriplet getRandomProstituteTriplet() {
		// occasionally throw some "regular" names in there - 25% of the time
		if(Math.random()<0.25) {
			return Util.randomItemFrom(human);
		}
		else
		{
			return Util.randomItemFrom(prostitute);
		}
	}
}
