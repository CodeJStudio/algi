package com.codejstudio.algi.samples;

import java.util.Arrays;

import com.codejstudio.algi.action.Action;
import com.codejstudio.algi.action.ActionDefinition;
import com.codejstudio.algi.action.ActionFlow;
import com.codejstudio.algi.action.symbol.PutSessionAttributeSymbol;
import com.codejstudio.algi.action.symbolAction.SimpleSentenceThinkAction;
import com.codejstudio.algi.action.symbolAction.SimpleThinkAction;
import com.codejstudio.algi.common.ALGIAutoInitConstant;
import com.codejstudio.lim.Informationiverse;
import com.codejstudio.lim.common.util.InitializationUtil;
import com.codejstudio.lim.pojo.Root;
import com.codejstudio.lim.pojo.concept.Concept;
import com.codejstudio.lim.pojo.condition.AttributeCondition;
import com.codejstudio.lim.pojo.condition.Condition;
import com.codejstudio.lim.pojo.condition.ConditionGroup;
import com.codejstudio.lim.pojo.condition.OrConditionGroup;
import com.codejstudio.lim.pojo.i.IDynamicable.DynamicParameterType;
import com.codejstudio.lim.pojo.relation.BaseRelation;
import com.codejstudio.lim.pojo.relation.EquivalenceRelation;
import com.codejstudio.lim.pojo.statement.Definition;
import com.codejstudio.lim.pojo.util.InputParameter;

/**
 * sample: calculate: XXXXX+XXXX=? (via SimpleThinkAction & SimpleSentenceThinkAction)
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     com.codejstudio.lim.pojo.concept.Concept
 * @see     com.codejstudio.lim.pojo.statement.Definition
 * @see     com.codejstudio.lim.pojo.condition.AttributeCondition
 * @see     com.codejstudio.lim.pojo.relation.EquivalenceRelation
 * @see     com.codejstudio.algi.action.ActionDefinition
 * @see     com.codejstudio.algi.action.ActionFlow
 * @see     com.codejstudio.algi.action.symbolAction.SimpleThinkAction
 * @see     com.codejstudio.algi.action.symbolAction.SimpleSentenceThinkAction
 * @since   algi_v1.0.0
 */
public class HelloHoliday7_3 {

	/* static methods */

	public static void main(String[] args) throws Exception {
		InitializationUtil.initSampleMode(ALGIAutoInitConstant.AUTO_INITS);

		doXmlMarshal();
//		HelloAction.doXmlUnmarshal(HelloHoliday7_3.class.getSimpleName());
	}

	static void doXmlMarshal() throws Exception {
		Concept c000 = new Concept("0");
		Concept c001 = new Concept("1");
		Concept c002 = new Concept("2");
		Concept c003 = new Concept("3");
		Concept c004 = new Concept("4");
		Concept c005 = new Concept("5");
		Concept c006 = new Concept("6");
		Concept c007 = new Concept("7");
		Concept c008 = new Concept("8");
		Concept c009 = new Concept("9");
		Concept c010 = new Concept("10");
		Concept c011 = new Concept("11");
		Concept c012 = new Concept("12");
		Concept c013 = new Concept("13");
		Concept c014 = new Concept("14");
		Concept c015 = new Concept("15");
		Concept c016 = new Concept("16");
		Concept c017 = new Concept("17");
		Concept c018 = new Concept("18");
		Concept c019 = new Concept("19");
		Concept c101 = new Concept("+");
		Concept c102 = new Concept("=");
		Concept c114 = new Concept("数字");
		Concept c115 = new Concept("算术表达式");
		Concept c116 = new Concept("计算");
		Concept c117 = new Concept("加");
		Concept c118 = new Concept("plus");
		Concept c200 = new Concept("0+0");
		Concept c201 = new Concept("0+1");
		Concept c202 = new Concept("0+2");
		Concept c203 = new Concept("0+3");
		Concept c204 = new Concept("0+4");
		Concept c205 = new Concept("0+5");
		Concept c206 = new Concept("0+6");
		Concept c207 = new Concept("0+7");
		Concept c208 = new Concept("0+8");
		Concept c209 = new Concept("0+9");
		Concept c210 = new Concept("1+0");
		Concept c211 = new Concept("1+1");
		Concept c212 = new Concept("1+2");
		Concept c213 = new Concept("1+3");
		Concept c214 = new Concept("1+4");
		Concept c215 = new Concept("1+5");
		Concept c216 = new Concept("1+6");
		Concept c217 = new Concept("1+7");
		Concept c218 = new Concept("1+8");
		Concept c219 = new Concept("1+9");
		Concept c220 = new Concept("2+0");
		Concept c221 = new Concept("2+1");
		Concept c222 = new Concept("2+2");
		Concept c223 = new Concept("2+3");
		Concept c224 = new Concept("2+4");
		Concept c225 = new Concept("2+5");
		Concept c226 = new Concept("2+6");
		Concept c227 = new Concept("2+7");
		Concept c228 = new Concept("2+8");
		Concept c229 = new Concept("2+9");
		Concept c230 = new Concept("3+0");
		Concept c231 = new Concept("3+1");
		Concept c232 = new Concept("3+2");
		Concept c233 = new Concept("3+3");
		Concept c234 = new Concept("3+4");
		Concept c235 = new Concept("3+5");
		Concept c236 = new Concept("3+6");
		Concept c237 = new Concept("3+7");
		Concept c238 = new Concept("3+8");
		Concept c239 = new Concept("3+9");
		Concept c240 = new Concept("4+0");
		Concept c241 = new Concept("4+1");
		Concept c242 = new Concept("4+2");
		Concept c243 = new Concept("4+3");
		Concept c244 = new Concept("4+4");
		Concept c245 = new Concept("4+5");
		Concept c246 = new Concept("4+6");
		Concept c247 = new Concept("4+7");
		Concept c248 = new Concept("4+8");
		Concept c249 = new Concept("4+9");
		Concept c250 = new Concept("5+0");
		Concept c251 = new Concept("5+1");
		Concept c252 = new Concept("5+2");
		Concept c253 = new Concept("5+3");
		Concept c254 = new Concept("5+4");
		Concept c255 = new Concept("5+5");
		Concept c256 = new Concept("5+6");
		Concept c257 = new Concept("5+7");
		Concept c258 = new Concept("5+8");
		Concept c259 = new Concept("5+9");
		Concept c260 = new Concept("6+0");
		Concept c261 = new Concept("6+1");
		Concept c262 = new Concept("6+2");
		Concept c263 = new Concept("6+3");
		Concept c264 = new Concept("6+4");
		Concept c265 = new Concept("6+5");
		Concept c266 = new Concept("6+6");
		Concept c267 = new Concept("6+7");
		Concept c268 = new Concept("6+8");
		Concept c269 = new Concept("6+9");
		Concept c270 = new Concept("7+0");
		Concept c271 = new Concept("7+1");
		Concept c272 = new Concept("7+2");
		Concept c273 = new Concept("7+3");
		Concept c274 = new Concept("7+4");
		Concept c275 = new Concept("7+5");
		Concept c276 = new Concept("7+6");
		Concept c277 = new Concept("7+7");
		Concept c278 = new Concept("7+8");
		Concept c279 = new Concept("7+9");
		Concept c280 = new Concept("8+0");
		Concept c281 = new Concept("8+1");
		Concept c282 = new Concept("8+2");
		Concept c283 = new Concept("8+3");
		Concept c284 = new Concept("8+4");
		Concept c285 = new Concept("8+5");
		Concept c286 = new Concept("8+6");
		Concept c287 = new Concept("8+7");
		Concept c288 = new Concept("8+8");
		Concept c289 = new Concept("8+9");
		Concept c290 = new Concept("9+0");
		Concept c291 = new Concept("9+1");
		Concept c292 = new Concept("9+2");
		Concept c293 = new Concept("9+3");
		Concept c294 = new Concept("9+4");
		Concept c295 = new Concept("9+5");
		Concept c296 = new Concept("9+6");
		Concept c297 = new Concept("9+7");
		Concept c298 = new Concept("9+8");
		Concept c299 = new Concept("9+9");

		c000.addDefaultAttribute(c114);
		c001.addDefaultAttribute(c114);
		c002.addDefaultAttribute(c114);
		c003.addDefaultAttribute(c114);
		c004.addDefaultAttribute(c114);
		c005.addDefaultAttribute(c114);
		c006.addDefaultAttribute(c114);
		c007.addDefaultAttribute(c114);
		c008.addDefaultAttribute(c114);
		c009.addDefaultAttribute(c114);
		c010.addDefaultAttribute(c114);
		c011.addDefaultAttribute(c114);
		c012.addDefaultAttribute(c114);
		c013.addDefaultAttribute(c114);
		c014.addDefaultAttribute(c114);
		c015.addDefaultAttribute(c114);
		c016.addDefaultAttribute(c114);
		c017.addDefaultAttribute(c114);
		c018.addDefaultAttribute(c114);
		c019.addDefaultAttribute(c114);
		c200.addSubConceptWithPosition(c000, c101);
		c200.addDefaultAttribute(c115);
		c201.addSubConceptWithPosition(c000, c101, c001);
		c201.addDefaultAttribute(c115);
		c202.addSubConceptWithPosition(c000, c101, c002);
		c202.addDefaultAttribute(c115);
		c203.addSubConceptWithPosition(c000, c101, c003);
		c203.addDefaultAttribute(c115);
		c204.addSubConceptWithPosition(c000, c101, c004);
		c204.addDefaultAttribute(c115);
		c205.addSubConceptWithPosition(c000, c101, c005);
		c205.addDefaultAttribute(c115);
		c206.addSubConceptWithPosition(c000, c101, c006);
		c206.addDefaultAttribute(c115);
		c207.addSubConceptWithPosition(c000, c101, c007);
		c207.addDefaultAttribute(c115);
		c208.addSubConceptWithPosition(c000, c101, c008);
		c208.addDefaultAttribute(c115);
		c209.addSubConceptWithPosition(c000, c101, c009);
		c209.addDefaultAttribute(c115);
		c210.addSubConceptWithPosition(c001, c101, c000);
		c210.addDefaultAttribute(c115);
		c211.addSubConceptWithPosition(c001, c101);
		c211.addDefaultAttribute(c115);
		c212.addSubConceptWithPosition(c001, c101, c002);
		c212.addDefaultAttribute(c115);
		c213.addSubConceptWithPosition(c001, c101, c003);
		c213.addDefaultAttribute(c115);
		c214.addSubConceptWithPosition(c001, c101, c004);
		c214.addDefaultAttribute(c115);
		c215.addSubConceptWithPosition(c001, c101, c005);
		c215.addDefaultAttribute(c115);
		c216.addSubConceptWithPosition(c001, c101, c006);
		c216.addDefaultAttribute(c115);
		c217.addSubConceptWithPosition(c001, c101, c007);
		c217.addDefaultAttribute(c115);
		c218.addSubConceptWithPosition(c001, c101, c008);
		c218.addDefaultAttribute(c115);
		c219.addSubConceptWithPosition(c001, c101, c009);
		c219.addDefaultAttribute(c115);
		c220.addSubConceptWithPosition(c002, c101, c000);
		c220.addDefaultAttribute(c115);
		c221.addSubConceptWithPosition(c002, c101, c001);
		c221.addDefaultAttribute(c115);
		c222.addSubConceptWithPosition(c002, c101);
		c222.addDefaultAttribute(c115);
		c223.addSubConceptWithPosition(c002, c101, c003);
		c223.addDefaultAttribute(c115);
		c224.addSubConceptWithPosition(c002, c101, c004);
		c224.addDefaultAttribute(c115);
		c225.addSubConceptWithPosition(c002, c101, c005);
		c225.addDefaultAttribute(c115);
		c226.addSubConceptWithPosition(c002, c101, c006);
		c226.addDefaultAttribute(c115);
		c227.addSubConceptWithPosition(c002, c101, c007);
		c227.addDefaultAttribute(c115);
		c228.addSubConceptWithPosition(c002, c101, c008);
		c228.addDefaultAttribute(c115);
		c229.addSubConceptWithPosition(c002, c101, c009);
		c229.addDefaultAttribute(c115);
		c230.addSubConceptWithPosition(c003, c101, c000);
		c230.addDefaultAttribute(c115);
		c231.addSubConceptWithPosition(c003, c101, c001);
		c231.addDefaultAttribute(c115);
		c232.addSubConceptWithPosition(c003, c101, c002);
		c232.addDefaultAttribute(c115);
		c233.addSubConceptWithPosition(c003, c101);
		c233.addDefaultAttribute(c115);
		c234.addSubConceptWithPosition(c003, c101, c004);
		c234.addDefaultAttribute(c115);
		c235.addSubConceptWithPosition(c003, c101, c005);
		c235.addDefaultAttribute(c115);
		c236.addSubConceptWithPosition(c003, c101, c006);
		c236.addDefaultAttribute(c115);
		c237.addSubConceptWithPosition(c003, c101, c007);
		c237.addDefaultAttribute(c115);
		c238.addSubConceptWithPosition(c003, c101, c008);
		c238.addDefaultAttribute(c115);
		c239.addSubConceptWithPosition(c003, c101, c009);
		c239.addDefaultAttribute(c115);
		c240.addSubConceptWithPosition(c004, c101, c000);
		c240.addDefaultAttribute(c115);
		c241.addSubConceptWithPosition(c004, c101, c001);
		c241.addDefaultAttribute(c115);
		c242.addSubConceptWithPosition(c004, c101, c002);
		c242.addDefaultAttribute(c115);
		c243.addSubConceptWithPosition(c004, c101, c003);
		c243.addDefaultAttribute(c115);
		c244.addSubConceptWithPosition(c004, c101);
		c244.addDefaultAttribute(c115);
		c245.addSubConceptWithPosition(c004, c101, c005);
		c245.addDefaultAttribute(c115);
		c246.addSubConceptWithPosition(c004, c101, c006);
		c246.addDefaultAttribute(c115);
		c247.addSubConceptWithPosition(c004, c101, c007);
		c247.addDefaultAttribute(c115);
		c248.addSubConceptWithPosition(c004, c101, c008);
		c248.addDefaultAttribute(c115);
		c249.addSubConceptWithPosition(c004, c101, c009);
		c249.addDefaultAttribute(c115);
		c250.addSubConceptWithPosition(c005, c101, c000);
		c250.addDefaultAttribute(c115);
		c251.addSubConceptWithPosition(c005, c101, c001);
		c251.addDefaultAttribute(c115);
		c252.addSubConceptWithPosition(c005, c101, c002);
		c252.addDefaultAttribute(c115);
		c253.addSubConceptWithPosition(c005, c101, c003);
		c253.addDefaultAttribute(c115);
		c254.addSubConceptWithPosition(c005, c101, c004);
		c254.addDefaultAttribute(c115);
		c255.addSubConceptWithPosition(c005, c101);
		c255.addDefaultAttribute(c115);
		c256.addSubConceptWithPosition(c005, c101, c006);
		c256.addDefaultAttribute(c115);
		c257.addSubConceptWithPosition(c005, c101, c007);
		c257.addDefaultAttribute(c115);
		c258.addSubConceptWithPosition(c005, c101, c008);
		c258.addDefaultAttribute(c115);
		c259.addSubConceptWithPosition(c005, c101, c009);
		c259.addDefaultAttribute(c115);
		c260.addSubConceptWithPosition(c006, c101, c000);
		c260.addDefaultAttribute(c115);
		c261.addSubConceptWithPosition(c006, c101, c001);
		c261.addDefaultAttribute(c115);
		c262.addSubConceptWithPosition(c006, c101, c002);
		c262.addDefaultAttribute(c115);
		c263.addSubConceptWithPosition(c006, c101, c003);
		c263.addDefaultAttribute(c115);
		c264.addSubConceptWithPosition(c006, c101, c004);
		c264.addDefaultAttribute(c115);
		c265.addSubConceptWithPosition(c006, c101, c005);
		c265.addDefaultAttribute(c115);
		c266.addSubConceptWithPosition(c006, c101);
		c266.addDefaultAttribute(c115);
		c267.addSubConceptWithPosition(c006, c101, c007);
		c267.addDefaultAttribute(c115);
		c268.addSubConceptWithPosition(c006, c101, c008);
		c268.addDefaultAttribute(c115);
		c269.addSubConceptWithPosition(c006, c101, c009);
		c269.addDefaultAttribute(c115);
		c270.addSubConceptWithPosition(c007, c101, c000);
		c270.addDefaultAttribute(c115);
		c271.addSubConceptWithPosition(c007, c101, c001);
		c271.addDefaultAttribute(c115);
		c272.addSubConceptWithPosition(c007, c101, c002);
		c272.addDefaultAttribute(c115);
		c273.addSubConceptWithPosition(c007, c101, c003);
		c273.addDefaultAttribute(c115);
		c274.addSubConceptWithPosition(c007, c101, c004);
		c274.addDefaultAttribute(c115);
		c275.addSubConceptWithPosition(c007, c101, c005);
		c275.addDefaultAttribute(c115);
		c276.addSubConceptWithPosition(c007, c101, c006);
		c276.addDefaultAttribute(c115);
		c277.addSubConceptWithPosition(c007, c101);
		c277.addDefaultAttribute(c115);
		c278.addSubConceptWithPosition(c007, c101, c008);
		c278.addDefaultAttribute(c115);
		c279.addSubConceptWithPosition(c007, c101, c009);
		c279.addDefaultAttribute(c115);
		c280.addSubConceptWithPosition(c008, c101, c000);
		c280.addDefaultAttribute(c115);
		c281.addSubConceptWithPosition(c008, c101, c001);
		c281.addDefaultAttribute(c115);
		c282.addSubConceptWithPosition(c008, c101, c002);
		c282.addDefaultAttribute(c115);
		c283.addSubConceptWithPosition(c008, c101, c003);
		c283.addDefaultAttribute(c115);
		c284.addSubConceptWithPosition(c008, c101, c004);
		c284.addDefaultAttribute(c115);
		c285.addSubConceptWithPosition(c008, c101, c005);
		c285.addDefaultAttribute(c115);
		c286.addSubConceptWithPosition(c008, c101, c006);
		c286.addDefaultAttribute(c115);
		c287.addSubConceptWithPosition(c008, c101, c007);
		c287.addDefaultAttribute(c115);
		c288.addSubConceptWithPosition(c008, c101);
		c288.addDefaultAttribute(c115);
		c289.addSubConceptWithPosition(c008, c101, c009);
		c289.addDefaultAttribute(c115);
		c290.addSubConceptWithPosition(c009, c101, c000);
		c290.addDefaultAttribute(c115);
		c291.addSubConceptWithPosition(c009, c101, c001);
		c291.addDefaultAttribute(c115);
		c292.addSubConceptWithPosition(c009, c101, c002);
		c292.addDefaultAttribute(c115);
		c293.addSubConceptWithPosition(c009, c101, c003);
		c293.addDefaultAttribute(c115);
		c294.addSubConceptWithPosition(c009, c101, c004);
		c294.addDefaultAttribute(c115);
		c295.addSubConceptWithPosition(c009, c101, c005);
		c295.addDefaultAttribute(c115);
		c296.addSubConceptWithPosition(c009, c101, c006);
		c296.addDefaultAttribute(c115);
		c297.addSubConceptWithPosition(c009, c101, c007);
		c297.addDefaultAttribute(c115);
		c298.addSubConceptWithPosition(c009, c101, c008);
		c298.addDefaultAttribute(c115);
		c299.addSubConceptWithPosition(c009, c101);
		c299.addDefaultAttribute(c115);


		Definition df00 = new Definition("0+0=0");
		Definition df01 = new Definition("0+1=1");
		Definition df02 = new Definition("0+2=2");
		Definition df03 = new Definition("0+3=3");
		Definition df04 = new Definition("0+4=4");
		Definition df05 = new Definition("0+5=5");
		Definition df06 = new Definition("0+6=6");
		Definition df07 = new Definition("0+7=7");
		Definition df08 = new Definition("0+8=8");
		Definition df09 = new Definition("0+9=9");
		Definition df10 = new Definition("1+0=1");
		Definition df11 = new Definition("1+1=2");
		Definition df12 = new Definition("1+2=3");
		Definition df13 = new Definition("1+3=4");
		Definition df14 = new Definition("1+4=5");
		Definition df15 = new Definition("1+5=6");
		Definition df16 = new Definition("1+6=7");
		Definition df17 = new Definition("1+7=8");
		Definition df18 = new Definition("1+8=9");
		Definition df19 = new Definition("1+9=10");
		Definition df20 = new Definition("2+0=2");
		Definition df21 = new Definition("2+1=3");
		Definition df22 = new Definition("2+2=4");
		Definition df23 = new Definition("2+3=5");
		Definition df24 = new Definition("2+4=6");
		Definition df25 = new Definition("2+5=7");
		Definition df26 = new Definition("2+6=8");
		Definition df27 = new Definition("2+7=9");
		Definition df28 = new Definition("2+8=10");
		Definition df29 = new Definition("2+9=11");
		Definition df30 = new Definition("3+0=3");
		Definition df31 = new Definition("3+1=4");
		Definition df32 = new Definition("3+2=5");
		Definition df33 = new Definition("3+3=6");
		Definition df34 = new Definition("3+4=7");
		Definition df35 = new Definition("3+5=8");
		Definition df36 = new Definition("3+6=9");
		Definition df37 = new Definition("3+7=10");
		Definition df38 = new Definition("3+8=11");
		Definition df39 = new Definition("3+9=12");
		Definition df40 = new Definition("4+0=4");
		Definition df41 = new Definition("4+1=5");
		Definition df42 = new Definition("4+2=6");
		Definition df43 = new Definition("4+3=7");
		Definition df44 = new Definition("4+4=8");
		Definition df45 = new Definition("4+5=9");
		Definition df46 = new Definition("4+6=10");
		Definition df47 = new Definition("4+7=11");
		Definition df48 = new Definition("4+8=12");
		Definition df49 = new Definition("4+9=13");
		Definition df50 = new Definition("5+0=5");
		Definition df51 = new Definition("5+1=6");
		Definition df52 = new Definition("5+2=7");
		Definition df53 = new Definition("5+3=8");
		Definition df54 = new Definition("5+4=9");
		Definition df55 = new Definition("5+5=10");
		Definition df56 = new Definition("5+6=11");
		Definition df57 = new Definition("5+7=12");
		Definition df58 = new Definition("5+8=13");
		Definition df59 = new Definition("5+9=14");
		Definition df60 = new Definition("6+0=6");
		Definition df61 = new Definition("6+1=7");
		Definition df62 = new Definition("6+2=8");
		Definition df63 = new Definition("6+3=9");
		Definition df64 = new Definition("6+4=10");
		Definition df65 = new Definition("6+5=11");
		Definition df66 = new Definition("6+6=12");
		Definition df67 = new Definition("6+7=13");
		Definition df68 = new Definition("6+8=14");
		Definition df69 = new Definition("6+9=15");
		Definition df70 = new Definition("7+0=7");
		Definition df71 = new Definition("7+1=8");
		Definition df72 = new Definition("7+2=9");
		Definition df73 = new Definition("7+3=10");
		Definition df74 = new Definition("7+4=11");
		Definition df75 = new Definition("7+5=12");
		Definition df76 = new Definition("7+6=13");
		Definition df77 = new Definition("7+7=14");
		Definition df78 = new Definition("7+8=15");
		Definition df79 = new Definition("7+9=16");
		Definition df80 = new Definition("8+0=8");
		Definition df81 = new Definition("8+1=9");
		Definition df82 = new Definition("8+2=10");
		Definition df83 = new Definition("8+3=11");
		Definition df84 = new Definition("8+4=12");
		Definition df85 = new Definition("8+5=13");
		Definition df86 = new Definition("8+6=14");
		Definition df87 = new Definition("8+7=15");
		Definition df88 = new Definition("8+8=16");
		Definition df89 = new Definition("8+9=17");
		Definition df90 = new Definition("9+0=9");
		Definition df91 = new Definition("9+1=10");
		Definition df92 = new Definition("9+2=11");
		Definition df93 = new Definition("9+3=12");
		Definition df94 = new Definition("9+4=13");
		Definition df95 = new Definition("9+5=14");
		Definition df96 = new Definition("9+6=15");
		Definition df97 = new Definition("9+7=16");
		Definition df98 = new Definition("9+8=17");
		Definition df99 = new Definition("9+9=18");

		df00.addConceptWithPosition(c200, c102, c000);
		df01.addConceptWithPosition(c201, c102, c001);
		df02.addConceptWithPosition(c202, c102, c002);
		df03.addConceptWithPosition(c203, c102, c003);
		df04.addConceptWithPosition(c204, c102, c004);
		df05.addConceptWithPosition(c205, c102, c005);
		df06.addConceptWithPosition(c206, c102, c006);
		df07.addConceptWithPosition(c207, c102, c007);
		df08.addConceptWithPosition(c208, c102, c008);
		df09.addConceptWithPosition(c209, c102, c009);
		df10.addConceptWithPosition(c210, c102, c001);
		df11.addConceptWithPosition(c211, c102, c002);
		df12.addConceptWithPosition(c212, c102, c003);
		df13.addConceptWithPosition(c213, c102, c004);
		df14.addConceptWithPosition(c214, c102, c005);
		df15.addConceptWithPosition(c215, c102, c006);
		df16.addConceptWithPosition(c216, c102, c007);
		df17.addConceptWithPosition(c217, c102, c008);
		df18.addConceptWithPosition(c218, c102, c009);
		df19.addConceptWithPosition(c219, c102, c010);
		df20.addConceptWithPosition(c220, c102, c002);
		df21.addConceptWithPosition(c221, c102, c003);
		df22.addConceptWithPosition(c222, c102, c004);
		df23.addConceptWithPosition(c223, c102, c005);
		df24.addConceptWithPosition(c224, c102, c006);
		df25.addConceptWithPosition(c225, c102, c007);
		df26.addConceptWithPosition(c226, c102, c008);
		df27.addConceptWithPosition(c227, c102, c009);
		df28.addConceptWithPosition(c228, c102, c010);
		df29.addConceptWithPosition(c229, c102, c011);
		df30.addConceptWithPosition(c230, c102, c003);
		df31.addConceptWithPosition(c231, c102, c004);
		df32.addConceptWithPosition(c232, c102, c005);
		df33.addConceptWithPosition(c233, c102, c006);
		df34.addConceptWithPosition(c234, c102, c007);
		df35.addConceptWithPosition(c235, c102, c008);
		df36.addConceptWithPosition(c236, c102, c009);
		df37.addConceptWithPosition(c237, c102, c010);
		df38.addConceptWithPosition(c238, c102, c011);
		df39.addConceptWithPosition(c239, c102, c012);
		df40.addConceptWithPosition(c240, c102, c004);
		df41.addConceptWithPosition(c241, c102, c005);
		df42.addConceptWithPosition(c242, c102, c006);
		df43.addConceptWithPosition(c243, c102, c007);
		df44.addConceptWithPosition(c244, c102, c008);
		df45.addConceptWithPosition(c245, c102, c009);
		df46.addConceptWithPosition(c246, c102, c010);
		df47.addConceptWithPosition(c247, c102, c011);
		df48.addConceptWithPosition(c248, c102, c012);
		df49.addConceptWithPosition(c249, c102, c013);
		df50.addConceptWithPosition(c250, c102, c005);
		df51.addConceptWithPosition(c251, c102, c006);
		df52.addConceptWithPosition(c252, c102, c007);
		df53.addConceptWithPosition(c253, c102, c008);
		df54.addConceptWithPosition(c254, c102, c009);
		df55.addConceptWithPosition(c255, c102, c010);
		df56.addConceptWithPosition(c256, c102, c011);
		df57.addConceptWithPosition(c257, c102, c012);
		df58.addConceptWithPosition(c258, c102, c013);
		df59.addConceptWithPosition(c259, c102, c014);
		df60.addConceptWithPosition(c260, c102, c006);
		df61.addConceptWithPosition(c261, c102, c007);
		df62.addConceptWithPosition(c262, c102, c008);
		df63.addConceptWithPosition(c263, c102, c009);
		df64.addConceptWithPosition(c264, c102, c010);
		df65.addConceptWithPosition(c265, c102, c011);
		df66.addConceptWithPosition(c266, c102, c012);
		df67.addConceptWithPosition(c267, c102, c013);
		df68.addConceptWithPosition(c268, c102, c014);
		df69.addConceptWithPosition(c269, c102, c015);
		df70.addConceptWithPosition(c270, c102, c007);
		df71.addConceptWithPosition(c271, c102, c008);
		df72.addConceptWithPosition(c272, c102, c009);
		df73.addConceptWithPosition(c273, c102, c010);
		df74.addConceptWithPosition(c274, c102, c011);
		df75.addConceptWithPosition(c275, c102, c012);
		df76.addConceptWithPosition(c276, c102, c013);
		df77.addConceptWithPosition(c277, c102, c014);
		df78.addConceptWithPosition(c278, c102, c015);
		df79.addConceptWithPosition(c279, c102, c016);
		df80.addConceptWithPosition(c280, c102, c008);
		df81.addConceptWithPosition(c281, c102, c009);
		df82.addConceptWithPosition(c282, c102, c010);
		df83.addConceptWithPosition(c283, c102, c011);
		df84.addConceptWithPosition(c284, c102, c012);
		df85.addConceptWithPosition(c285, c102, c013);
		df86.addConceptWithPosition(c286, c102, c014);
		df87.addConceptWithPosition(c287, c102, c015);
		df88.addConceptWithPosition(c288, c102, c016);
		df89.addConceptWithPosition(c289, c102, c017);
		df90.addConceptWithPosition(c290, c102, c009);
		df91.addConceptWithPosition(c291, c102, c010);
		df92.addConceptWithPosition(c292, c102, c011);
		df93.addConceptWithPosition(c293, c102, c012);
		df94.addConceptWithPosition(c294, c102, c013);
		df95.addConceptWithPosition(c295, c102, c014);
		df96.addConceptWithPosition(c296, c102, c015);
		df97.addConceptWithPosition(c297, c102, c016);
		df98.addConceptWithPosition(c298, c102, c017);
		df99.addConceptWithPosition(c299, c102, c018);


		Definition dscdf_atcd = new Definition("将目标对象拆分为多个单位字符组成的单位字符集<$sv>，"
				+ "其中每一个单位字符分别为<$v0><$v1>...等。"
				+ "顺次检验每一个单位字符，如果每一个单位字符都符合该目标条件，则目标对象也符合该目标条件；"
				+ "如果有某一个单位字符不符合该目标条件，则目标对象也不符合该目标条件。");
		Definition dscdf71 = new Definition("如果<$a>和<$b>两个数，位数不相同，位数的差为<$d>，"
				+ "则在位数较少的那个数，最左侧位的左边补充数量为<$d>的位数，且内容为“0”；"
				+ "将补充位数后的数，和原位数较多的数，分别记作<$ad>和<$bd>。"
				+ "在<$ad>、<$bd>最左侧位的左边各补1个位数，且内容为“0”，分别记作<$ac>和<$bc>；"
				+ "将它们分别拆分为多个单位数组成的单位数集<$sa>和<$sb>，"
				+ "其中每一个单位数分别为<$a0><$a1>...和<$b0><$b1>...等。"
				+ "同时，构建一组同样数量，内容都为“0”的单位数，为<$c0><$c1>...等；"
				+ "将它们三者一一配对，从右侧开始，顺次计算每一组数字的和，如“<$a0>+<$b0>+<$c0>”。"
				+ "如果结果不大于“9”，将其记作<$s0>；"
				+ "如果结果大于“9”，将相加结果超过“9”的部分，从“0”开始重新计数，将其记作<$s0>；"
				+ "同时，让相应数字位的左侧一位的<$c>值，如“<$c1>”，增加“1”，用于下一组数字的求和计算。"
				+ "将所有的各数字位求和的值按顺序合并，"
				+ "从合并结果的最左侧数字位开始，顺次判断每一个数字位是否为“0”，"
				+ "如果为“0”，则将该数字位从合并结果中去除，然后继续判断最左侧的数字位。"
				+ "最后，将得到的内容作为结果<#R>输出。");


		ActionDefinition adf_atcd1 = new ActionDefinition("<ps(><#R>,true,boolean<)>"); //新建临时概念元素：<#R>；
		ActionDefinition adf_atcd2 = new ActionDefinition("<ps(><#sv>,<sp(><$" 
				+ Condition.INPUT_PARAMETER_NAME_VERIFIEE + "><)><)>"); //新建并赋值临时概念元素：<#sv>，将<$verifiee>拆分为多个单位字符组成的单位字符集；
		ActionDefinition adf_atcd3 = new ActionDefinition("<ps(><#lov>,<lo(><$sv><)><)>"); //更新临时概念元素：<#lov>，<$sv>的位数值；
		ActionDefinition adf_atcd4 = new ActionDefinition(
				"<po{><ps(>i,0,int<)><ps(>flag,true,boolean<)><}"
				+ "cd{><cx(>flag && i<lov<)><}"
				+ "dr(>true<){>"
					+ "<cd{>"
						+ "<vc(><$" + Condition.INPUT_PARAMETER_NAME_CONDITION + ">,"
						+ "<ge(><$sv>,<cx(>lov-1-i<)><)><)>" //将单位数集<$sv>中的多个单位字符分别进行条件验证；
					+ "<}do(>false<){>"
						+ "<ps(><#R>,false,boolean<)><ps(>flag,false,boolean<)>"
					+ "<}>"
				+ "<}"
				+ "pr{><ps(>i,<cx(>i+1<)>,int<)><}>");
		ActionDefinition adf_atcd5 = new ActionDefinition("<gs(><#R><)>"); //输出结果<#R>值
		ActionDefinition adf_atcd = new ActionDefinition(adf_atcd1, adf_atcd2, adf_atcd3, adf_atcd4, adf_atcd5);
		ActionFlow afl_atcd = new ActionFlow(adf_atcd);


		ConditionGroup cdg1 = new ConditionGroup(new AttributeCondition(c114));
		ConditionGroup cdg2 = new OrConditionGroup(new AttributeCondition(c114), new AttributeCondition(c115));
		cdg1.setVerifier(afl_atcd);


		Concept dc1 = new Concept("<$a>");//（属性：[备注：$a是数字]）
		Concept dc1_1 = new Concept("<$1>");//（属性：[备注：$1是数字，或算术表达式]）
		Concept dc2 = new Concept("<$a>+<$b>");//（属性：[备注：$a、$b都是数字]）
		Concept dc3 = new Concept("计算<$a>+<$b>");//（属性：[备注：$a、$b都是数字]）

		dc1.setNetDynamicConditions(cdg1);
		dc2.addSubConceptWithPosition(c101, dc1);
		dc2.setNetDynamicConditions(cdg1, cdg1);
		dc3.addSubConceptWithPosition(c116, dc1, dc2);
		dc3.setNetDynamicConditions(cdg1, cdg1);


		Definition ddf1 = new Definition("<$a>+<$b>=<$c>");//（属性：[备注：$a、$b、$c都是数字]）
		Definition ddf2 = new Definition("<$1>=<$2>");//（属性：[备注：$1、$2都是数字，或算术表达式]）

		ddf1.addConceptWithPosition(dc2, c102, dc1);
		ddf1.setNetDynamicConditions(cdg1, cdg1, cdg1);
		ddf2.addConceptWithPosition(dc1_1, c102);
		ddf2.setNetDynamicConditions(cdg2, cdg2);

		Definition ddf1_1 = (Definition) ddf1.substituteDynamicSymbolByParameterType(
				new DynamicParameterType[] {DynamicParameterType.INPUT, DynamicParameterType.INPUT, 
						DynamicParameterType.OUTPUT});//"<$a>+<$b>=<#c>"（属性：[备注：$a、$b、#c都是数字]）


		ActionDefinition adf701 = new ActionDefinition("<ps(><#R>,,null<)>"); //新建临时概念元素：<#R>；
		ActionDefinition adf702 = new ActionDefinition("<ps(><#loa>,<lo(><$a><)><)>"); //新建并赋值临时概念元素：<#loa>，<$a>的位数值；
		ActionDefinition adf703 = new ActionDefinition("<ps(><#lob>,<lo(><$b><)><)>"); //新建并赋值临时概念元素：<#lob>，<$b>的位数值；
		ActionDefinition adf704 = new ActionDefinition("<ps(><#d>,<cx(>loa-lob<)><)>"); //新建并赋值临时概念元素：<#d>，<$a>和<$b>的位数差值；
		ActionDefinition adf705 = new ActionDefinition(
				"<po{>"
					+ "<ps(><#bd>,<$b><)>" //新建并初始化临时概念元素：<#bd>；
					+ "<ps(>i,0,int<)>"
				+ "<}"
				+ "cd{><cx(>i<d<)><}"
				+ "dr(>true<){>"
					+ "<ps(><#bd>,<cb(>0,<$bd><)><)>" //在<$b>最左侧位的左边补<$d>个位数，内容为“0”，可记作<#bd>；
				+ "<}"
				+ "pr{><ps(>i,<cx(>i+1<)>,int<)><}>");
		ActionDefinition adf706 = new ActionDefinition("<ps(><#ad>,<$a><)>"); //新建并赋值临时概念元素：<#ad>；
		ActionDefinition adf707 = new ActionDefinition("<ps(><#d>,<cx(>lob-loa<)><)>"); //新建并赋值临时概念元素：<#d>，<$b>和<$a>的位数差值；
		ActionDefinition adf708 = new ActionDefinition(
				"<po{>"
					+ "<ps(><#ad>,<$a><)>" //新建并初始化临时概念元素：<#ad>；
					+ "<ps(>i,0,int<)>"
				+ "<}"
				+ "cd{><cx(>i<d<)><}"
				+ "dr(>true<){>"
					+ "<ps(><#ad>,<cb(>0,<$ad><)><)>" //在<$a>最左侧位的左边补<$d>个位数，内容为“0”，可记作<#ad>；
				+ "<}"
				+ "pr{><ps(>i,<cx(>i+1<)>,int<)><}>");
		ActionDefinition adf709 = new ActionDefinition("<ps(><#bd>,<$b><)>"); //新建并赋值临时概念元素：<#bd>；
		ActionDefinition adf710 = new ActionDefinition("<ps(><#ac>,<cb(>0,<$ad><)><)>"); //新建并赋值临时概念元素：<#ac>；
		ActionDefinition adf711 = new ActionDefinition("<ps(><#bc>,<cb(>0,<$bd><)><)>"); //新建并赋值临时概念元素：<#bc>；
		ActionDefinition adf712 = new ActionDefinition("<ps(><#sa>,<sp(><$ac><)><)>"); //新建并赋值临时概念元素：<#sa>，将<$ac>拆分为多个单位数组成的单位数集；
		ActionDefinition adf713 = new ActionDefinition("<ps(><#sb>,<sp(><$bc><)><)>"); //新建并赋值临时概念元素：<#sb>，将<$bc>拆分为多个单位数组成的单位数集；
		ActionDefinition adf714 = new ActionDefinition("<ps(><#loa>,<lo(><$sa><)><)>"); //更新临时概念元素：<#loa>，<$sa>的位数值；
		ActionDefinition adf715 = new ActionDefinition(
				"<po{><ps(>i,0,int<)><}"
				+ "cd{><cx(>i<loa<)><}"
				+ "dr(>true<){>"
					+ "<ps(><#a<$i>>,<ge(><$sa>,<cx(>loa-1-i<)><)><)>" //将单位数集<$sa>中的多个单位数分别赋值给新建的临时概念元素：<#a<$i>>；
					+ "<ps(><#b<$i>>,<ge(><$sb>,<cx(>loa-1-i<)><)><)>" //将单位数集<$sb>中的多个单位数分别赋值给新建的临时概念元素：<#b<$i>>；
					+ "<ps(><#c<$i>>,0<)>" //新建并赋值的临时概念元素：<#c<$i>>；
				+ "<}"
				+ "pr{><ps(>i,<cx(>i+1<)>,int<)><}>");
		ActionDefinition adf716 = new ActionDefinition(
				"<po{>"
					+ "<ps(><#t>,"
						+ "<ds(><sdi(>" + ddf1_1.getId() + ",a<$i>,b<$i>,t<)><)>," //新建并赋值临时概念元素：<#t>，“<$a<$i>>+<$b<$i>>”的值；
					+ PutSessionAttributeSymbol.TYPE_INFO_STRING + "<)>"
				+ "<}"
				+ "cd{><lo(><$t><)><}" //条件：<$t>的位数值
				+ "do(>cd==1<){>" //条件结果：<$t>的位数值等于1，即<$t>的数值不大于“9”
					+ "<ps(><#s<$i>>,<$t><)>" //新建并赋值临时概念元素：<#s<$i>>；
				+ "<}"
				+ "do(>cd==2<){>" //条件结果：<$t>的位数值等于2，即<$t>的数值大于“9”
					+ "<ps(>j,<cx(>i+1<)><)>"
					+ "<ps(><#st>,<sp(><$t><)><)>" //新建并赋值临时概念元素：<#st>，将<$t>拆分为多个单位数组成的单位数集；
					+ "<ps(><#c<$j>>,<ge(><$st>,0<)><)>" //将单位数集<$st>中的第一个单位数赋值给新建的临时概念元素：<#c<$j>>；
					+ "<ps(><#s<$i>>,<ge(><$st>,1<)><)>" //将单位数集<$st>中的第二个单位数赋值给新建的临时概念元素：<#s<$i>>；
				+ "<}>");
		ActionDefinition adf717 = new ActionDefinition(
				"<po{>"
					+ "<ps(><#t>,"
						+ "<ds(><sdi(>" + ddf1_1.getId() + ",s<$i>,c<$i>,t<)><)>," //更新临时概念元素：<#t>，“<$s<$i>>+<$c<$i>>”的值；
					+ PutSessionAttributeSymbol.TYPE_INFO_STRING + "<)>"
				+ "<}"
				+ "cd{><lo(><$t><)><}" //条件：<$t>的位数值
				+ "do(>cd==1<){>" //条件结果：<$t>的位数值等于1，即<$t>的数值不大于“9”
					+ "<ps(><#s<$i>>,<$t><)>" //新建并赋值临时概念元素：<#s<$i>>；
				+ "<}"
				+ "do(>cd==2<){>" //条件结果：<$t>的位数值等于2，即<$t>的数值大于“9”
				+ "<ps(>j,<cx(>i+1<)><)>"
					+ "<ps(><#st>,<sp(><$t><)><)>" //新建并赋值临时概念元素：<#st>，将<$t>拆分为多个单位数组成的单位数集；
					+ "<ps(><#c<$j>>,<ge(><$st>,0<)><)>" //将单位数集<$sa>中的多个单位数分别赋值给新建的临时概念元素：<#a<$i>>；
					+ "<ps(><#s<$i>>,<ge(><$st>,1<)><)>" //将单位数集<$sb>中的多个单位数分别赋值给新建的临时概念元素：<#b<$i>>；
				+ "<}>");
		ActionDefinition adf718 = new ActionDefinition("<ps(><#s>,,null<)>"); //新建临时概念元素：<#s>；
		ActionDefinition adf719 = new ActionDefinition(
				"<po{><ps(>i,0,int<)><}"
				+ "cd{><cx(>i<<lo(><$sa><)><)><}"
				+ "dr(>true<){>"
					+ "<ps(><#s>,<cb(><$s<$i>>,<$s><)><)>" //更新临时概念元素：<#s>，将所有数字位<$s<$i>>按顺序合并得到内容；
				+ "<}"
				+ "pr{><ps(>i,<cx(>i+1<)>,int<)><}>");
		ActionDefinition adf720 = new ActionDefinition(
				"<cd{><sw(><$s>,0<)><}"
				+ "dr(>true<){>"
					+ "<ps(><#s>,<ss(><$s>,1<)><)>" //更新临时概念元素：<#s>，将所有数字位<$s<$i>>按顺序合并得到内容；
				+ "<}>");
		ActionDefinition adf721 = new ActionDefinition("<ps(><#R>,<iu(><$s>,true<)><)>"); //更新临时概念元素：结果<#R>，按<$s>内容找到对应元素并得到结果；
		ActionDefinition adf722 = new ActionDefinition("<gs(><#R><)>"); //输出结果<#R>值；

		ActionFlow afl71 = new ActionFlow(adf701, adf702, adf703);
		ActionFlow afl72 = new ActionFlow(adf704, adf705, adf706);
		ActionFlow afl73 = new ActionFlow(adf707, adf708, adf709);
		ActionFlow afl74 = new ActionFlow(adf706, adf709);
		ActionFlow afl75 = new ActionFlow(adf710, adf711, adf712, adf713, adf714, adf715);
		ActionFlow afl76 = new ActionFlow(adf716, adf717);
		ActionFlow afl77 = new ActionFlow(adf718, adf719, adf720, adf721, adf722);

		ActionDefinition adf71 = new ActionDefinition(
				"<po{>"
					+ "<ax(>" + afl71.getId() + "<)>" //执行任务流afl71
				+ "<}"
				+ "cd{><cp(><$loa>,<$lob><)><}" //条件：比较<$a>和<$b>的位数
				+ "do(>cd>0<){>" //条件结果：<$a>的位数大于<$b>的位数
					+ "<ax(>" + afl72.getId() + "<)>" //执行任务流afl72
				+ "<}"
				+ "do(>cd<0<){>" //条件结果：<$b>的位数大于<$a>的位数
					+ "<ax(>" + afl73.getId() + "<)>" //执行任务流afl73
				+ "<}"
				+ "do(>cd==0<){>" //条件结果：<$a>的位数等于<$b>的位数
					+ "<ax(>" + afl74.getId() + "<)>" //执行任务流afl74
				+ "<}>");
		ActionDefinition adf72 = new ActionDefinition(
				"<ax(>" + afl75.getId() + "<)>"); //执行任务流afl75
		ActionDefinition adf73 = new ActionDefinition(
				"<po{><ps(>i,0,int<)><}"
				+ "cd{><cx(>i<<lo(><$sa><)><)><}"
				+ "dr(>true<){>"
					+ "<ax(>" + afl76.getId() + "<)>" //执行任务流afl76
				+ "<}"
				+ "pr{><ps(>i,<cx(>i+1<)>,int<)><}>");
		ActionDefinition adf74 = new ActionDefinition(
				"<ax(>" + afl77.getId() + "<)>"); //执行任务流afl77
		ActionDefinition adf70 = new ActionDefinition(adf71, adf72, adf73, adf74);


//		EquivalenceRelation eqr07 = 
				new EquivalenceRelation(c101, c117);
//		EquivalenceRelation eqr08 = 
				new EquivalenceRelation(c101, c118);
//		EquivalenceRelation eqr11 = 
				new EquivalenceRelation(dc3, ddf1_1);
//		EquivalenceRelation eqr30 = 
				new EquivalenceRelation(dscdf_atcd, adf_atcd);
//		EquivalenceRelation eqr70 = 
				new EquivalenceRelation(dscdf71, adf70);

//		BaseRelation br1 = 
				new BaseRelation(c101, dc3);
//		BaseRelation br7 = 
				new BaseRelation(dc3, dscdf71);



		// HelloHoliday7_3.java
		//SimpleThinkAction
		Action sta7_3_1 = new SimpleThinkAction("<st(>" + dc3.getId() + "<)>");
		InputParameter[] ipa7_3_1 = InputParameter.generateInstances("a", "1232659", "b", "3217725");
		Object[] staOutputArray7_3_1 = sta7_3_1.execute(ipa7_3_1);
		System.out.println();
		System.out.println("SimpleThinkAction7_3_1.execute: " 
				+ ((staOutputArray7_3_1 == null) ? null : Arrays.asList((Object[]) staOutputArray7_3_1)));

		//SimpleSentenceThinkAction
		Action ssta7_3_1 = new SimpleSentenceThinkAction("<sst(>678168361,+,5677345<)>");
		Object[] sstaOutputArray7_3_1 = ssta7_3_1.execute();
		System.out.println();
		System.out.println("SimpleSentenceThinkAction7_3_1.execute: " 
				+ ((sstaOutputArray7_3_1 == null) ? null : Arrays.asList((Object[]) sstaOutputArray7_3_1)));

		Action ssta7_3_2 = new SimpleSentenceThinkAction("<sst(>658678239,加,21234693<)>");
		Object[] sstaOutputArray7_3_2 = ssta7_3_2.execute();
		System.out.println();
		System.out.println("SimpleSentenceThinkAction7_3_2.execute: " 
				+ ((sstaOutputArray7_3_2 == null) ? null : Arrays.asList((Object[]) sstaOutputArray7_3_2)));

		Action ssta7_3_3 = new SimpleSentenceThinkAction("<sst(>626235,plus,4565474<)>");
		Object[] sstaOutputArray7_3_3 = ssta7_3_3.execute();
		System.out.println();
		System.out.println("SimpleSentenceThinkAction7_3_3.execute: " 
				+ ((sstaOutputArray7_3_3 == null) ? null : Arrays.asList((Object[]) sstaOutputArray7_3_3)));



		Root root = new Root(Informationiverse.getAllActionableElementCollection(), 
				Informationiverse.getAllElementCollection());
		System.out.println();
		root.marshalToXml(System.out);
	}

}
