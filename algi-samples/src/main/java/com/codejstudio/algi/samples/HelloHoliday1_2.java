package com.codejstudio.algi.samples;

import java.util.Arrays;

import com.codejstudio.algi.action.ActionDefinition;
import com.codejstudio.algi.action.ActionFlow;
import com.codejstudio.algi.action.symbol.DynamicStatementSymbol;
import com.codejstudio.algi.common.ALGIAutoInitConstant;
import com.codejstudio.lim.Informationiverse;
import com.codejstudio.lim.common.util.InitializationUtil;
import com.codejstudio.lim.pojo.Root;
import com.codejstudio.lim.pojo.concept.Concept;
import com.codejstudio.lim.pojo.condition.AttributeCondition;
import com.codejstudio.lim.pojo.condition.ConditionGroup;
import com.codejstudio.lim.pojo.i.IActionable;
import com.codejstudio.lim.pojo.i.IDynamicable.DynamicParameterType;
import com.codejstudio.lim.pojo.relation.EquivalenceRelation;
import com.codejstudio.lim.pojo.statement.Definition;
import com.codejstudio.lim.pojo.util.InputParameter;

/**
 * sample: calculate: 1+1=? (via ActionableElement)
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     com.codejstudio.lim.pojo.concept.Concept
 * @see     com.codejstudio.lim.pojo.statement.Definition
 * @see     com.codejstudio.lim.pojo.condition.AttributeCondition
 * @see     com.codejstudio.lim.pojo.relation.EquivalenceRelation
 * @see     com.codejstudio.algi.action.ActionDefinition
 * @since   algi_v1.0.0
 */
public class HelloHoliday1_2 {

	/* static methods */

	public static void main(String[] args) throws Exception {
		InitializationUtil.initSampleMode(ALGIAutoInitConstant.AUTO_INITS);

		doXmlMarshal();
//		HelloAction.doXmlUnmarshal(HelloHoliday1_2.class.getSimpleName());
	}

	static void doXmlMarshal() throws Exception {
		Concept c001 = new Concept("1");
		Concept c002 = new Concept("2");
		Concept c101 = new Concept("+");
		Concept c102 = new Concept("=");
		Concept c114 = new Concept("数字");
		Concept c115 = new Concept("算术表达式");
		Concept c116 = new Concept("计算");
		Concept c211 = new Concept("1+1");

		c001.addDefaultAttribute(c114);
		c002.addDefaultAttribute(c114);
		c211.addSubConceptWithPosition(c001, c101);
		c211.addDefaultAttribute(c115);


		Definition df11 = new Definition("1+1=2");

		df11.addConceptWithPosition(c211, c102, c002);


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
		Definition ddf11 = new Definition("如果记忆中存在\"<$a>+<$b>\"对应的值<#c>，则直接将<#c>作为结果输出。");//（属性：[备注：$a、$b、#c都是数字]）

		ddf1.addConceptWithPosition(dc2, c102, dc1);
		ddf1.setNetDynamicConditions(cdg1, cdg1, cdg1);

		Definition ddf1_1 = (Definition) ddf1.substituteDynamicSymbolByParameterType(
				new DynamicParameterType[] {DynamicParameterType.INPUT, DynamicParameterType.INPUT, 
						DynamicParameterType.OUTPUT});//"<$a>+<$b>=<#c>"（属性：[备注：$a、$b、#c都是数字]）


		ActionDefinition adf11 = new ActionDefinition(
				"<ps(><#R>,"
					+ "<ds(>" + ddf1_1.getId() + "," + DynamicStatementSymbol.TYPE_INFO + "<)>"
				+ "<)>"); //新建并更新临时定义元素：结果<#R>值
		ActionDefinition adf12 = new ActionDefinition("<gs(><#R><)>"); //输出结果<#R>值

		ActionFlow afl11 = new ActionFlow(adf11, adf12);

		ActionDefinition adf10 = new ActionDefinition(
				"<cd{><iu(><$a>+<$b>=<#><)><}" //条件：存在"<$a>+<$b>"对应的值<#>
				+ "do(>cd!=null<){>" //条件结果：结果存在
					+ "<ax(>" + afl11.getId() + "<)>" //执行任务流afl11
				+ "<}>");


//		EquivalenceRelation eqr11 = 
				new EquivalenceRelation(dc3, ddf1_1);
//		EquivalenceRelation eqr20 = 
				new EquivalenceRelation(ddf11, adf10);



		// HelloHoliday1_2.java
		//ActionableElement
		IActionable afl1_2_1 = new ActionFlow(adf10);
		InputParameter[] ipa1_2_1 = InputParameter.informationInstances("a", c001, "b", c001);//1, 1
		Object[] aflOutputArray1_2_1 = afl1_2_1.execute(ipa1_2_1);
		System.out.println();
		System.out.println("ActionableElement1_2_1.execute: " 
				+ ((aflOutputArray1_2_1 == null) ? null : Arrays.asList((Object[]) aflOutputArray1_2_1)));



		Root root = new Root(Informationiverse.getAllActionableElementCollection(), 
				Informationiverse.getAllElementCollection());
		System.out.println();
		root.marshalToXml(System.out);
	}

}
