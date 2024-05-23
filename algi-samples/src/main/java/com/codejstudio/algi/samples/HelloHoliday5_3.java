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
 * sample: calculate: 10+1=? (via SimpleThinkAction & SimpleSentenceThinkAction)
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
public class HelloHoliday5_3 {

	/* static methods */

	public static void main(String[] args) throws Exception {
		InitializationUtil.initSampleMode(ALGIAutoInitConstant.AUTO_INITS);

		doXmlMarshal();
//		HelloAction.doXmlUnmarshal(HelloHoliday5_3.class.getSimpleName());
	}

	static void doXmlMarshal() throws Exception {
		Concept c000 = new Concept("0");
		Concept c001 = new Concept("1");
		Concept c010 = new Concept("10");
		Concept c101 = new Concept("+");
		Concept c102 = new Concept("=");
		Concept c114 = new Concept("数字");
		Concept c115 = new Concept("算术表达式");
		Concept c116 = new Concept("计算");
		Concept c201 = new Concept("0+1");
		Concept c210 = new Concept("1+0");

		c000.addDefaultAttribute(c114);
		c001.addDefaultAttribute(c114);
		c010.addDefaultAttribute(c114);
		c201.addSubConceptWithPosition(c000, c101, c001);
		c201.addDefaultAttribute(c115);
		c210.addSubConceptWithPosition(c001, c101, c000);
		c210.addDefaultAttribute(c115);


		Definition df01 = new Definition("0+1=1");
		Definition df10 = new Definition("1+0=1");

		df01.addConceptWithPosition(c201, c102, c001);
		df10.addConceptWithPosition(c210, c102, c001);


		Definition dscdf_atcd = new Definition("将目标对象拆分为多个单位字符组成的单位字符集<$sv>，"
				+ "其中每一个单位字符分别为<$v0><$v1>...等。"
				+ "顺次检验每一个单位字符，如果每一个单位字符都符合该目标条件，则目标对象也符合该目标条件；"
				+ "如果有某一个单位字符不符合该目标条件，则目标对象也不符合该目标条件。");
		Definition dscdf51 = new Definition("如果<$a>和<$b>两个数，位数不相同，位数的差为<$d>，"
				+ "则在位数较少的那个数，最左侧位的左边补充数量为<$d>的位数，且内容为“0”。"
				+ "将补充位数后的数，和原位数较多的数，分别记作<$ad>和<$bd>；"
				+ "将它们分别拆分为多个单位数组成的单位数集<$sa>和<$sb>，"
				+ "其中每一个单位数分别为<$a0><$a1>...和<$b0><$b1>...等；"
				+ "将它们一一配对，从右侧开始，顺次计算每一组数字的和，如“<$a0>+<$b0>”，记作<$s0>；"
				+ "将所有的各数字位求和的值按顺序合并，作为结果<#R>输出。");


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


		ActionDefinition adf501 = new ActionDefinition("<ps(><#R>,,null<)>"); //新建临时概念元素：<#R>；
		ActionDefinition adf502 = new ActionDefinition("<ps(><#loa>,<lo(><$a><)><)>"); //新建并赋值临时概念元素：<#loa>，<$a>的位数值；
		ActionDefinition adf503 = new ActionDefinition("<ps(><#lob>,<lo(><$b><)><)>"); //新建并赋值临时概念元素：<#lob>，<$b>的位数值；
		ActionDefinition adf504 = new ActionDefinition("<ps(><#d>,<cx(>loa-lob<)><)>"); //新建并赋值临时概念元素：<#d>，<$a>和<$b>的位数差值；
		ActionDefinition adf505 = new ActionDefinition(
				"<po{>"
					+ "<ps(><#bd>,<$b><)>" //新建并初始化临时概念元素：<#bd>；
					+ "<ps(>i,0,int<)>"
				+ "<}"
				+ "cd{><cx(>i<d<)><}"
				+ "dr(>true<){>"
					+ "<ps(><#bd>,<cb(>0,<$bd><)><)>" //在<$b>最左侧位的左边补<$d>个位数，内容为“0”，可记作<#bd>；
				+ "<}"
				+ "pr{><ps(>i,<cx(>i+1<)>,int<)><}>");
		ActionDefinition adf506 = new ActionDefinition("<ps(><#ad>,<$a><)>"); //新建并赋值临时概念元素：<#ad>；
		ActionDefinition adf507 = new ActionDefinition("<ps(><#d>,<cx(>lob-loa<)><)>"); //新建并赋值临时概念元素：<#d>，<$b>和<$a>的位数差值；
		ActionDefinition adf508 = new ActionDefinition(
				"<po{>"
					+ "<ps(><#ad>,<$a><)>" //新建并初始化临时概念元素：<#ad>；
					+ "<ps(>i,0,int<)>"
				+ "<}"
				+ "cd{><cx(>i<d<)><}"
				+ "dr(>true<){>"
					+ "<ps(><#ad>,<cb(>0,<$ad><)><)>" //在<$a>最左侧位的左边补<$d>个位数，内容为“0”，可记作<#ad>；
				+ "<}"
				+ "pr{><ps(>i,<cx(>i+1<)>,int<)><}>");
		ActionDefinition adf509 = new ActionDefinition("<ps(><#bd>,<$b><)>"); //新建并赋值临时概念元素：<#bd>；
		ActionDefinition adf510 = new ActionDefinition("<ps(><#sa>,<sp(><$ad><)><)>"); //新建并赋值临时概念元素：<#sa>，将<$ad>拆分为多个单位数组成的单位数集；
		ActionDefinition adf511 = new ActionDefinition("<ps(><#sb>,<sp(><$bd><)><)>"); //新建并赋值临时概念元素：<#sb>，将<$bd>拆分为多个单位数组成的单位数集；
		ActionDefinition adf512 = new ActionDefinition("<ps(><#loa>,<lo(><$sa><)><)>"); //更新临时概念元素：<#loa>，<$sa>的位数值；
		ActionDefinition adf513 = new ActionDefinition(
				"<po{><ps(>i,0,int<)><}"
				+ "cd{><cx(>i<loa<)><}"
				+ "dr(>true<){>"
					+ "<ps(><#a<$i>>,<ge(><$sa>,<cx(>loa-1-i<)><)><)>" //将单位数集<$sa>中的多个单位数分别赋值给新建的临时概念元素：<#a<$i>>；
					+ "<ps(><#b<$i>>,<ge(><$sb>,<cx(>loa-1-i<)><)><)>" //将单位数集<$sb>中的多个单位数分别赋值给新建的临时概念元素：<#b<$i>>；
				+ "<}"
				+ "pr{><ps(>i,<cx(>i+1<)>,int<)><}>");
		ActionDefinition adf514 = new ActionDefinition(
				"<po{><ps(>i,0,int<)><}"
				+ "cd{><cx(>i<<lo(><$sa><)><)><}"
				+ "dr(>true<){>"
					+ "<ps(><#s<$i>>,"
						+ "<ds(><sdi(>" + ddf1_1.getId() + ",a<$i>,b<$i>,s<$i><)><)>," //新建并赋值临时概念元素：<#s<$i>>，“<$a<$i>>+<$b<$i>>+<$c<$i>>”的值；
					+ PutSessionAttributeSymbol.TYPE_INFO_STRING + "<)>"
				+ "<}"
				+ "pr{><ps(>i,<cx(>i+1<)>,int<)><}>");
		ActionDefinition adf515 = new ActionDefinition("<ps(><#s>,,null<)>"); //新建临时概念元素：<#s>；
		ActionDefinition adf516 = new ActionDefinition(
				"<po{><ps(>i,0,int<)><}"
				+ "cd{><cx(>i<<lo(><$sa><)><)><}"
				+ "dr(>true<){>"
					+ "<ps(><#s>,<cb(><$s<$i>>,<$s><)><)>" //更新临时概念元素：<#s>，将所有数字位<$s<$i>>按顺序合并得到内容；
				+ "<}"
				+ "pr{><ps(>i,<cx(>i+1<)>,int<)><}>");
		ActionDefinition adf517 = new ActionDefinition("<ps(><#R>,<iu(><$s>,true<)><)>"); //更新临时概念元素：结果<#R>，按<$s>内容找到对应元素并得到结果；
		ActionDefinition adf518 = new ActionDefinition("<gs(><#R><)>"); //输出结果<#R>值；

		ActionFlow afl51 = new ActionFlow(adf501, adf502, adf503);
		ActionFlow afl52 = new ActionFlow(adf504, adf505, adf506);
		ActionFlow afl53 = new ActionFlow(adf507, adf508, adf509);
		ActionFlow afl54 = new ActionFlow(adf510, adf511, adf512, adf513, adf514, adf515, adf516, adf517, adf518);

		ActionDefinition adf50 = new ActionDefinition(
				"<po{>"
					+ "<ax(>" + afl51.getId() + "<)>" //执行任务流afl51
				+ "<}"
				+ "cd{><cp(><$loa>,<$lob><)><}" //条件：比较<$a>和<$b>的位数
				+ "do(>cd>0<){>" //条件结果：<$a>的位数大于<$b>的位数
					+ "<ax(>" + afl52.getId() + "<)>" //执行任务流afl52
				+ "<}"
				+ "do(>cd<0<){>" //条件结果：<$b>的位数大于<$a>的位数
					+ "<ax(>" + afl53.getId() + "<)>" //执行任务流afl53
				+ "<}>"
				+ "<ax(>" + afl54.getId() + "<)>"); //执行任务流afl54


//		EquivalenceRelation eqr11 = 
				new EquivalenceRelation(dc3, ddf1_1);
//		EquivalenceRelation eqr30 = 
				new EquivalenceRelation(dscdf_atcd, adf_atcd);
//		EquivalenceRelation eqr50 = 
				new EquivalenceRelation(dscdf51, adf50);

//		BaseRelation br1 = 
				new BaseRelation(c101, dc3);
//		BaseRelation br5 = 
				new BaseRelation(dc3, dscdf51);




		// HelloHoliday5_3.java
		//SimpleThinkAction
		Action sta5_3_1 = new SimpleThinkAction("<st(>" + dc3.getId() + "<)>");
		InputParameter[] ipa5_3_1 = InputParameter.informationInstances("a", c010, "b", c001);//10, 1
		Object[] staOutputArray5_3_1 = sta5_3_1.execute(ipa5_3_1);
		System.out.println();
		System.out.println("SimpleThinkAction5_3_1.execute: " 
				+ ((staOutputArray5_3_1 == null) ? null : Arrays.asList((Object[]) staOutputArray5_3_1)));

		//SimpleSentenceThinkAction
		Action ssta5_3_1 = new SimpleSentenceThinkAction("<sst(>10,+,1<)>");
		Object[] sstaOutputArray5_3_1 = ssta5_3_1.execute();
		System.out.println();
		System.out.println("SimpleSentenceThinkAction5_3_1.execute: " 
				+ ((sstaOutputArray5_3_1 == null) ? null : Arrays.asList((Object[]) sstaOutputArray5_3_1)));



		Root root = new Root(Informationiverse.getAllActionableElementCollection(), 
				Informationiverse.getAllElementCollection());
		System.out.println();
		root.marshalToXml(System.out);
	}

}
