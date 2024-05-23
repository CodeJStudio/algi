package com.codejstudio.algi.samples;

import java.util.Arrays;

import com.codejstudio.algi.action.ActionDefinition;
import com.codejstudio.algi.action.ActionFlow;
import com.codejstudio.algi.action.symbol.PutSessionAttributeSymbol;
import com.codejstudio.algi.common.ALGIAutoInitConstant;
import com.codejstudio.lim.Informationiverse;
import com.codejstudio.lim.common.util.InitializationUtil;
import com.codejstudio.lim.pojo.Root;
import com.codejstudio.lim.pojo.concept.Concept;
import com.codejstudio.lim.pojo.condition.AttributeCondition;
import com.codejstudio.lim.pojo.condition.ConditionGroup;
import com.codejstudio.lim.pojo.condition.OrConditionGroup;
import com.codejstudio.lim.pojo.i.IActionable;
import com.codejstudio.lim.pojo.i.IDynamicable.DynamicParameterType;
import com.codejstudio.lim.pojo.relation.EquivalenceRelation;
import com.codejstudio.lim.pojo.statement.Definition;
import com.codejstudio.lim.pojo.util.InputParameter;

/**
 * sample: calculate: 16+7=? (via ActionableElement)
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     com.codejstudio.lim.pojo.concept.Concept
 * @see     com.codejstudio.lim.pojo.statement.Definition
 * @see     com.codejstudio.lim.pojo.condition.AttributeCondition
 * @see     com.codejstudio.lim.pojo.relation.EquivalenceRelation
 * @see     com.codejstudio.algi.action.ActionDefinition
 * @see     com.codejstudio.algi.action.ActionFlow
 * @since   algi_v1.0.0
 */
public class HelloHoliday6_2 {

	/* static methods */

	public static void main(String[] args) throws Exception {
		InitializationUtil.initSampleMode(ALGIAutoInitConstant.AUTO_INITS);

		doXmlMarshal();
//		HelloAction.doXmlUnmarshal(HelloHoliday6_2.class.getSimpleName());
	}

	static void doXmlMarshal() throws Exception {
		Concept c000 = new Concept("0");
		Concept c001 = new Concept("1");
		Concept c002 = new Concept("2");
		Concept c006 = new Concept("6");
		Concept c007 = new Concept("7");
		Concept c013 = new Concept("13");
		Concept c016 = new Concept("16");
		Concept c101 = new Concept("+");
		Concept c102 = new Concept("=");
		Concept c114 = new Concept("数字");
		Concept c115 = new Concept("算术表达式");
		Concept c116 = new Concept("计算");
		Concept c210 = new Concept("1+0");
		Concept c211 = new Concept("1+1");
		Concept c267 = new Concept("6+7");

		c000.addDefaultAttribute(c114);
		c001.addDefaultAttribute(c114);
		c002.addDefaultAttribute(c114);
		c006.addDefaultAttribute(c114);
		c007.addDefaultAttribute(c114);
		c013.addDefaultAttribute(c114);
		c016.addDefaultAttribute(c114);
		c210.addSubConceptWithPosition(c001, c101, c000);
		c210.addDefaultAttribute(c115);
		c211.addSubConceptWithPosition(c001, c101);
		c211.addDefaultAttribute(c115);
		c267.addSubConceptWithPosition(c006, c101, c007);
		c267.addDefaultAttribute(c115);


		Definition df10 = new Definition("1+0=1");
		Definition df11 = new Definition("1+1=2");
		Definition df67 = new Definition("6+7=13");

		df10.addConceptWithPosition(c210, c102, c001);
		df11.addConceptWithPosition(c211, c102, c002);
		df67.addConceptWithPosition(c267, c102, c013);


		Definition dscdf61 = new Definition("如果<$a>和<$b>两个数，位数不相同，位数的差为<$d>，"
				+ "则在位数较少的那个数，最左侧位的左边补充数量为<$d>的位数，且内容为“0”；"
				+ "将补充位数后的数，和原位数较多的数，分别记作<$ad>和<$bd>。"
				+ "将它们分别拆分为多个单位数组成的单位数集<$sa>和<$sb>，"
				+ "其中每一个单位数分别为<$a0><$a1>...和<$b0><$b1>...等。"
				+ "同时，构建一组同样数量，内容都为“0”的单位数，为<$c0><$c1>...等；"
				+ "将它们三者一一配对，从右侧开始，顺次计算每一组数字的和，如“<$a0>+<$b0>+<$c0>”。"
				+ "如果结果不大于“9”，将其记作<$s0>；"
				+ "如果结果大于“9”，将相加结果超过“9”的部分，从“0”开始重新计数，将其记作<$s0>；"
				+ "同时，让相应数字位的左侧一位的<$c>值，如“<$c1>”，增加“1”，用于下一组数字的求和计算。"
				+ "将所有的各数字位求和的值按顺序合并，作为结果<#R>输出。");


		ConditionGroup cdg1 = new ConditionGroup(new AttributeCondition(c114));
		ConditionGroup cdg2 = new OrConditionGroup(new AttributeCondition(c114), new AttributeCondition(c115));


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


		ActionDefinition adf601 = new ActionDefinition("<ps(><#R>,,null<)>"); //新建临时概念元素：<#R>；
		ActionDefinition adf602 = new ActionDefinition("<ps(><#loa>,<lo(><$a><)><)>"); //新建并赋值临时概念元素：<#loa>，<$a>的位数值；
		ActionDefinition adf603 = new ActionDefinition("<ps(><#lob>,<lo(><$b><)><)>"); //新建并赋值临时概念元素：<#lob>，<$b>的位数值；
		ActionDefinition adf604 = new ActionDefinition("<ps(><#d>,<cx(>loa-lob<)><)>"); //新建并赋值临时概念元素：<#d>，<$a>和<$b>的位数差值；
		ActionDefinition adf605 = new ActionDefinition(
				"<po{>"
					+ "<ps(><#bd>,<$b><)>" //新建并初始化临时概念元素：<#bd>；
					+ "<ps(>i,0,int<)>"
				+ "<}"
				+ "cd{><cx(>i<d<)><}"
				+ "dr(>true<){>"
					+ "<ps(><#bd>,<cb(>0,<$bd><)><)>" //在<$b>最左侧位的左边补<$d>个位数，内容为“0”，可记作<#bd>；
				+ "<}"
				+ "pr{><ps(>i,<cx(>i+1<)>,int<)><}>");
		ActionDefinition adf606 = new ActionDefinition("<ps(><#ad>,<$a><)>"); //新建并赋值临时概念元素：<#ad>；
		ActionDefinition adf607 = new ActionDefinition("<ps(><#d>,<cx(>lob-loa<)><)>"); //新建并赋值临时概念元素：<#d>，<$b>和<$a>的位数差值；
		ActionDefinition adf608 = new ActionDefinition(
				"<po{>"
					+ "<ps(><#ad>,<$a><)>" //新建并初始化临时概念元素：<#ad>；
					+ "<ps(>i,0,int<)>"
				+ "<}"
				+ "cd{><cx(>i<d<)><}"
				+ "dr(>true<){>"
					+ "<ps(><#ad>,<cb(>0,<$ad><)><)>" //在<$a>最左侧位的左边补<$d>个位数，内容为“0”，可记作<#ad>；
				+ "<}"
				+ "pr{><ps(>i,<cx(>i+1<)>,int<)><}>");
		ActionDefinition adf609 = new ActionDefinition("<ps(><#bd>,<$b><)>"); //新建并赋值临时概念元素：<#bd>；
		ActionDefinition adf610 = new ActionDefinition("<ps(><#sa>,<sp(><$ad><)><)>"); //新建并赋值临时概念元素：<#sa>，将<$ad>拆分为多个单位数组成的单位数集；
		ActionDefinition adf611 = new ActionDefinition("<ps(><#sb>,<sp(><$bd><)><)>"); //新建并赋值临时概念元素：<#sb>，将<$bd>拆分为多个单位数组成的单位数集；
		ActionDefinition adf612 = new ActionDefinition("<ps(><#loa>,<lo(><$sa><)><)>"); //更新临时概念元素：<#loa>，<$sa>的位数值；
		ActionDefinition adf613 = new ActionDefinition(
				"<po{><ps(>i,0,int<)><}"
				+ "cd{><cx(>i<loa<)><}"
				+ "dr(>true<){>"
					+ "<ps(><#a<$i>>,<ge(><$sa>,<cx(>loa-1-i<)><)><)>" //将单位数集<$sa>中的多个单位数分别赋值给新建的临时概念元素：<#a<$i>>；
					+ "<ps(><#b<$i>>,<ge(><$sb>,<cx(>loa-1-i<)><)><)>" //将单位数集<$sb>中的多个单位数分别赋值给新建的临时概念元素：<#b<$i>>；
					+ "<ps(><#c<$i>>,0<)>" //新建并赋值的临时概念元素：<#c<$i>>；
				+ "<}"
				+ "pr{><ps(>i,<cx(>i+1<)>,int<)><}>");
		ActionDefinition adf614 = new ActionDefinition(
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
		ActionDefinition adf615 = new ActionDefinition(
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
		ActionDefinition adf616 = new ActionDefinition("<ps(><#s>,,null<)>"); //新建临时概念元素：<#s>；
		ActionDefinition adf617 = new ActionDefinition(
				"<po{><ps(>i,0,int<)><}"
				+ "cd{><cx(>i<<lo(><$sa><)><)><}"
				+ "dr(>true<){>"
					+ "<ps(><#s>,<cb(><$s<$i>>,<$s><)><)>" //更新临时概念元素：<#s>，将所有数字位<$s<$i>>按顺序合并得到内容；
				+ "<}"
				+ "pr{><ps(>i,<cx(>i+1<)>,int<)><}>");
		ActionDefinition adf618 = new ActionDefinition("<ps(><#R>,<iu(><$s>,true<)><)>"); //更新临时概念元素：结果<#R>，按<$s>内容找到对应元素并得到结果；
		ActionDefinition adf619 = new ActionDefinition("<gs(><#R><)>"); //输出结果<#R>值；

		ActionFlow afl61 = new ActionFlow(adf601, adf602, adf603);
		ActionFlow afl62 = new ActionFlow(adf604, adf605, adf606);
		ActionFlow afl63 = new ActionFlow(adf607, adf608, adf609);
		ActionFlow afl64 = new ActionFlow(adf606, adf609);
		ActionFlow afl65 = new ActionFlow(adf610, adf611, adf612, adf613);
		ActionFlow afl66 = new ActionFlow(adf614, adf615);
		ActionFlow afl67 = new ActionFlow(adf616, adf617, adf618, adf619);

		ActionDefinition adf61 = new ActionDefinition(
				"<po{>"
					+ "<ax(>" + afl61.getId() + "<)>" //执行任务流afl61
				+ "<}"
				+ "cd{><cp(><$loa>,<$lob><)><}" //条件：比较<$a>和<$b>的位数
				+ "do(>cd>0<){>" //条件结果：<$a>的位数大于<$b>的位数
					+ "<ax(>" + afl62.getId() + "<)>" //执行任务流afl62
				+ "<}"
				+ "do(>cd<0<){>" //条件结果：<$b>的位数大于<$a>的位数
					+ "<ax(>" + afl63.getId() + "<)>" //执行任务流afl63
				+ "<}"
				+ "do(>cd==0<){>" //条件结果：<$a>的位数等于<$b>的位数
					+ "<ax(>" + afl64.getId() + "<)>" //执行任务流afl64
				+ "<}>");
		ActionDefinition adf62 = new ActionDefinition(
				"<ax(>" + afl65.getId() + "<)>"); //执行任务流afl65
		ActionDefinition adf63 = new ActionDefinition(
				"<po{><ps(>i,0,int<)><}"
				+ "cd{><cx(>i<<lo(><$sa><)><)><}"
				+ "dr(>true<){>"
					+ "<ax(>" + afl66.getId() + "<)>" //执行任务流afl66
				+ "<}"
				+ "pr{><ps(>i,<cx(>i+1<)>,int<)><}>");
		ActionDefinition adf64 = new ActionDefinition(
				"<ax(>" + afl67.getId() + "<)>"); //执行任务流afl67
		ActionDefinition adf60 = new ActionDefinition(adf61, adf62, adf63, adf64);


//		EquivalenceRelation eqr11 = 
				new EquivalenceRelation(dc3, ddf1_1);
//		EquivalenceRelation eqr60 = 
				new EquivalenceRelation(dscdf61, adf60);



		// HelloHoliday6_2.java
		//ActionableElement
		IActionable afl6_2_1 = new ActionFlow(adf60);
		InputParameter[] ipa6_2_1 = InputParameter.informationInstances("a", c016, "b", c007);//16, 7
		Object[] aflOutputArray6_2_1 = afl6_2_1.execute(ipa6_2_1);
		System.out.println();
		System.out.println("ActionableElement6_2_1.execute: " 
				+ ((aflOutputArray6_2_1 == null) ? null : Arrays.asList((Object[]) aflOutputArray6_2_1)));



		Root root = new Root(Informationiverse.getAllActionableElementCollection(), 
				Informationiverse.getAllElementCollection());
		System.out.println();
		root.marshalToXml(System.out);
	}

}
