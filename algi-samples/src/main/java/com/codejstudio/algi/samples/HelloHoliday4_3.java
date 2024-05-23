package com.codejstudio.algi.samples;

import java.util.Arrays;

import com.codejstudio.algi.action.Action;
import com.codejstudio.algi.action.ActionDefinition;
import com.codejstudio.algi.action.ActionFlow;
import com.codejstudio.algi.action.symbol.PutSessionAttributeSymbol;
import com.codejstudio.algi.action.symbolAction.SimpleThinkAction;
import com.codejstudio.algi.common.ALGIAutoInitConstant;
import com.codejstudio.lim.Informationiverse;
import com.codejstudio.lim.common.util.InitializationUtil;
import com.codejstudio.lim.pojo.Root;
import com.codejstudio.lim.pojo.concept.Concept;
import com.codejstudio.lim.pojo.condition.AttributeCondition;
import com.codejstudio.lim.pojo.condition.ConditionGroup;
import com.codejstudio.lim.pojo.relation.BaseRelation;
import com.codejstudio.lim.pojo.relation.EquivalenceRelation;
import com.codejstudio.lim.pojo.statement.Definition;
import com.codejstudio.lim.pojo.util.InputParameter;

/**
 * sample: calculate: 9+1=? (via SimpleThinkAction)
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     com.codejstudio.lim.pojo.concept.Concept
 * @see     com.codejstudio.lim.pojo.statement.Definition
 * @see     com.codejstudio.lim.pojo.condition.AttributeCondition
 * @see     com.codejstudio.lim.pojo.relation.EquivalenceRelation
 * @see     com.codejstudio.algi.action.ActionDefinition
 * @see     com.codejstudio.algi.action.ActionFlow
 * @see     com.codejstudio.algi.action.symbolAction.SimpleThinkAction
 * @since   algi_v1.0.0
 */
public class HelloHoliday4_3 {

	/* static methods */

	public static void main(String[] args) throws Exception {
		InitializationUtil.initSampleMode(ALGIAutoInitConstant.AUTO_INITS);

		doXmlMarshal();
//		HelloAction.doXmlUnmarshal(HelloHoliday4_3.class.getSimpleName());
	}

	static void doXmlMarshal() throws Exception {
		Concept c000 = new Concept("0");
		Concept c001 = new Concept("1");
		Concept c002 = new Concept("2");
		Concept c009 = new Concept("9");
		Concept c010 = new Concept("10");
		Concept c101 = new Concept("+");
		Concept c102 = new Concept("=");
		Concept c103 = new Concept(">");
		Concept c114 = new Concept("数字");
		Concept c115 = new Concept("算术表达式");
		Concept c116 = new Concept("计算");
		Concept c201 = new Concept("0+1");
		Concept c291 = new Concept("9+1");

		c000.addDefaultAttribute(c114);
		c001.addDefaultAttribute(c114);
		c002.addDefaultAttribute(c114);
		c009.addDefaultAttribute(c114);
		c010.addDefaultAttribute(c114);
		c201.addSubConceptWithPosition(c000, c101, c001);
		c201.addDefaultAttribute(c115);
		c291.addSubConceptWithPosition(c009, c101, c001);
		c291.addDefaultAttribute(c115);


		Definition df01 = new Definition("0+1=1");
		Definition df191 = new Definition("9+1>9");

		df01.addConceptWithPosition(c201, c102, c001);
		df191.addConceptWithPosition(c291, c103, c009);


		Definition dscdf41 = new Definition("两个个位数相加，如果结果大于“9”，"
				+ "则设定结果有十位数和个位数；"
				+ "个位数上，将相加结果超过“9”的部分，从“0”开始重新计数；"
				+ "十位数上，在初始值“0”的基础上，加上“1”；"
				+ "将十位数和个位数合并，输出结果。");


		ConditionGroup cdg1 = new ConditionGroup(new AttributeCondition(c114));


		Concept dc1 = new Concept("<$a>");//（属性：[备注：$a是数字]）
		Concept dc2 = new Concept("<$a>+<$b>");//（属性：[备注：$a、$b都是数字]）
		Concept dc3 = new Concept("计算<$a>+<$b>");//（属性：[备注：$a、$b都是数字]）

		dc1.setNetDynamicConditions(cdg1);
		dc2.addSubConceptWithPosition(c101, dc1);
		dc2.setNetDynamicConditions(cdg1, cdg1);
		dc3.addSubConceptWithPosition(c116, dc1, dc2);
		dc3.setNetDynamicConditions(cdg1, cdg1);


		Definition ddf1 = new Definition("<$a>+<$b>=<$c>");//（属性：[备注：$a、$b、$c都是数字]）

		ddf1.addConceptWithPosition(dc2, c102, dc1);
		ddf1.setNetDynamicConditions(cdg1, cdg1, cdg1);


		ActionDefinition adf401 = new ActionDefinition("<ps(><#R>,,null<)>"); //新建临时概念元素：<#R>；
		ActionDefinition adf402 = new ActionDefinition("<ps(><#r1>,,null<)>"); //新建临时概念元素：<#r1>；
		ActionDefinition adf403 = new ActionDefinition("<ps(><#r2>,,null<)>"); //新建临时概念元素：<#r2>；
		ActionDefinition adf404 = new ActionDefinition("<ps(><#t>,,null<)>"); //新建临时概念元素：<#t>；
		ActionDefinition adf405 = new ActionDefinition("<ps(><#r1>,0<)>"); //新建临时定义元素：设定<#r1>初始值
		ActionDefinition adf406 = new ActionDefinition("<ps(><#r2>,0<)>"); //新建临时定义元素：设定<#r2>初始值
		ActionDefinition adf407 = new ActionDefinition("<ps(><#t>,1<)>"); //新建临时定义元素：设定<#t>初始值
		ActionDefinition adf408 = new ActionDefinition(
				"<ps(><#r2>,"
					+ "<ds(><$r2>+<$t>=<#c><)>," 
					+ PutSessionAttributeSymbol.TYPE_INFO_STRING 
				+ "<)>"); //新建临时定义元素：更新<#r2>值
		ActionDefinition adf409 = new ActionDefinition("<ps(><#R>,<iu(><cb(><$r2>,<$r1><)>,true<)><)>"); //新建并更新临时定义元素：结果<#R>值
		ActionDefinition adf410 = new ActionDefinition("<gs(><#R><)>"); //输出结果<#R>值

		ActionFlow afl41 = new ActionFlow(adf401, adf402, adf403, adf404);
		ActionFlow afl42 = new ActionFlow(adf405, adf406, adf407, adf408, adf409, adf410);

		ActionDefinition adf40 = new ActionDefinition(
				"<po{>"
					+ "<ax(>" + afl41.getId() + "<)>" //执行任务流afl41
				+ "<}cd{><iu(><$a>+<$b>>9<)><}" //条件："<$a>+<$b>"大于9
				+ "do(>cd!=null<){>" //条件结果：结果存在
					+ "<ax(>" + afl42.getId() + "<)>" //执行任务流afl42
				+ "<}>");


//		EquivalenceRelation eqr40 = 
				new EquivalenceRelation(dscdf41, adf40);

//		BaseRelation br4 = 
				new BaseRelation(dc3, dscdf41);



		// HelloHoliday4_3.java
		//SimpleThinkAction
		Action sta4_3_1 = new SimpleThinkAction("<st(>" + dc3.getId() + "<)>");
		InputParameter[] ipa4_3_1 = InputParameter.informationInstances("a", c009, "b", c001);//9, 1
		Object[] staOutputArray4_3_1 = sta4_3_1.execute(ipa4_3_1);
		System.out.println();
		System.out.println("SimpleThinkAction4_3_1.execute: " 
				+ ((staOutputArray4_3_1 == null) ? null : Arrays.asList((Object[]) staOutputArray4_3_1)));



		Root root = new Root(Informationiverse.getAllActionableElementCollection(), 
				Informationiverse.getAllElementCollection());
		System.out.println();
		root.marshalToXml(System.out);
	}

}
