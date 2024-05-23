package com.codejstudio.algi.samples;

import java.util.Arrays;

import com.codejstudio.algi.action.Action;
import com.codejstudio.algi.action.symbolAction.SimpleThinkAction;
import com.codejstudio.algi.common.ALGIAutoInitConstant;
import com.codejstudio.lim.common.util.InitializationUtil;
import com.codejstudio.lim.pojo.concept.Concept;
import com.codejstudio.lim.pojo.condition.AttributeCondition;
import com.codejstudio.lim.pojo.condition.ConditionGroup;
import com.codejstudio.lim.pojo.condition.HypotheticalCondition;
import com.codejstudio.lim.pojo.condition.OrConditionGroup;
import com.codejstudio.lim.pojo.i.IDynamicable.DynamicParameterType;
import com.codejstudio.lim.pojo.relation.EquivalenceRelation;
import com.codejstudio.lim.pojo.statement.Definition;
import com.codejstudio.lim.pojo.statement.HypotheticalProposition;
import com.codejstudio.lim.pojo.util.DynamicableUtil;
import com.codejstudio.lim.pojo.util.InputParameter;

/**
 * sample: calculate: 3+2=? (via SimpleThinkAction)
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     com.codejstudio.lim.pojo.concept.Concept
 * @see     com.codejstudio.lim.pojo.statement.Definition
 * @see     com.codejstudio.lim.pojo.condition.AttributeCondition
 * @see     com.codejstudio.lim.pojo.statement.HypotheticalProposition
 * @see     com.codejstudio.lim.pojo.relation.EquivalenceRelation
 * @see     com.codejstudio.algi.action.symbolAction.SimpleThinkAction
 * @since   algi_v1.0.0
 */
public class HelloHoliday2_3 {

	/* static methods */

	public static void main(String[] args) throws Exception {
		InitializationUtil.initSampleMode(ALGIAutoInitConstant.AUTO_INITS);

		doXmlMarshal();
	}

	static void doXmlMarshal() throws Exception {
		Concept c001 = new Concept("1");
		Concept c002 = new Concept("2");
		Concept c003 = new Concept("3");
		Concept c004 = new Concept("4");
		Concept c005 = new Concept("5");
		Concept c101 = new Concept("+");
		Concept c102 = new Concept("=");
		Concept c114 = new Concept("数字");
		Concept c115 = new Concept("算术表达式");
		Concept c116 = new Concept("计算");
		Concept c211 = new Concept("1+1");
		Concept c221 = new Concept("2+1");
		Concept c231 = new Concept("3+1");
		Concept c232 = new Concept("3+2");
		Concept c241 = new Concept("4+1");
		Concept c301 = new Concept("3+1+1");

		c001.addDefaultAttribute(c114);
		c002.addDefaultAttribute(c114);
		c003.addDefaultAttribute(c114);
		c004.addDefaultAttribute(c114);
		c005.addDefaultAttribute(c114);
		c211.addSubConceptWithPosition(c001, c101);
		c211.addDefaultAttribute(c115);
		c221.addSubConceptWithPosition(c002, c101, c001);
		c221.addDefaultAttribute(c115);
		c231.addSubConceptWithPosition(c003, c101, c001);
		c231.addDefaultAttribute(c115);
		c232.addSubConceptWithPosition(c003, c101, c002);
		c232.addDefaultAttribute(c115);
		c241.addSubConceptWithPosition(c004, c101, c001);
		c241.addDefaultAttribute(c115);
		c301.addSubConceptWithPosition(c003, c101, c001);
		c301.addDefaultAttribute(c115);


		Definition df11 = new Definition("1+1=2");
		Definition df31 = new Definition("3+1=4");
		Definition df41 = new Definition("4+1=5");

		df11.addConceptWithPosition(c211, c102, c002);
		df31.addConceptWithPosition(c231, c102, c004);
		df41.addConceptWithPosition(c241, c102, c005);


		ConditionGroup cdg1 = new ConditionGroup(new AttributeCondition(c114));
		ConditionGroup cdg2 = new OrConditionGroup(new AttributeCondition(c114), new AttributeCondition(c115));
		ConditionGroup cdg3 = new ConditionGroup(DynamicableUtil.CONDITION_NOT_SAME);


		Concept dc1 = new Concept("<$a>");//（属性：[备注：$a是数字]）
		Concept dc1_1 = new Concept("<$1>");//（属性：[备注：$1是数字，或算术表达式]）
		Concept dc2 = new Concept("<$a>+<$b>");//（属性：[备注：$a、$b都是数字]）
		Concept dc2_1 = new Concept("<$1>+<$2>");//（属性：[备注：$1、$2都是数字，或算术表达式]）
		Concept dc3 = new Concept("计算<$a>+<$b>");//（属性：[备注：$a、$b都是数字]）

		dc1.setNetDynamicConditions(cdg1);
		dc1_1.setNetDynamicConditions(cdg2);
		dc2.addSubConceptWithPosition(c101, dc1);
		dc2.setNetDynamicConditions(cdg1, cdg1);
		dc2_1.addSubConceptWithPosition(c101, dc1_1);
		dc2_1.setNetDynamicConditions(cdg2, cdg2);
		dc3.addSubConceptWithPosition(c116, dc1, dc2);
		dc3.setNetDynamicConditions(cdg1, cdg1);


		Definition ddf1 = new Definition("<$a>+<$b>=<$c>");//（属性：[备注：$a、$b、$c都是数字]）
		Definition ddf2 = new Definition("<$1>=<$2>");//（属性：[备注：$1、$2都是数字，或算术表达式]）
		Definition ddf2_0 = new Definition("<$2>=<$1>");//（属性：[备注：$1、$2都是数字，或算术表达式]）
		Definition ddf2_1 = new Definition("<$2>=<$3>");//（属性：[备注：$2、$3都是数字，或算术表达式]）
		Definition ddf2_2 = new Definition("<$1>=<$3>");//（属性：[备注：$1、$3都是数字，或算术表达式]）
		Definition ddf3_1 = new Definition("<$1>+<$3>=<$2>+<$3>");//（属性：[备注：$1、$2、$3都是数字，或算术表达式]）
		Definition ddf3_2 = new Definition("<$3>+<$1>=<$3>+<$2>");//（属性：[备注：$1、$2、$3都是数字，或算术表达式]）

		ddf1.addConceptWithPosition(dc2, c102, dc1);
		ddf1.setNetDynamicConditions(cdg1, cdg1, cdg1);
		ddf2.addConceptWithPosition(dc1_1, c102);
		ddf2.setNetDynamicConditions(cdg2, cdg2);
		ddf2_0.addConceptWithPosition(dc1_1, c102);
		ddf2_0.setNetDynamicConditions(cdg2, cdg2);
		ddf2_1.addConceptWithPosition(dc1_1, c102);
		ddf2_1.setNetDynamicConditions(cdg2, cdg2);
		ddf2_2.addConceptWithPosition(dc1_1, c102);
		ddf2_2.setNetDynamicConditions(cdg2, cdg2);
		ddf3_1.addConceptWithPosition(dc2_1, c102);
		ddf3_1.setNetDynamicConditions(cdg2, cdg2, cdg2);
		ddf3_2.addConceptWithPosition(dc2_1, c102);
		ddf3_2.setNetDynamicConditions(cdg2, cdg2, cdg2);

		Definition ddf1_1 = (Definition) ddf1.substituteDynamicSymbolByParameterType(
				new DynamicParameterType[] {DynamicParameterType.INPUT, DynamicParameterType.INPUT, 
						DynamicParameterType.OUTPUT});//"<$a>+<$b>=<#c>"（属性：[备注：$a、$b、#c都是数字]）


		HypotheticalProposition hpp1 = new HypotheticalProposition(ddf2_0, new HypotheticalCondition(ddf2));
		HypotheticalProposition hpp2 = new HypotheticalProposition(ddf3_2, new HypotheticalCondition(ddf2));
		HypotheticalProposition hpp3 = new HypotheticalProposition(ddf3_1, new HypotheticalCondition(ddf2));
		HypotheticalProposition hpp4 = new HypotheticalProposition(ddf2_2, 
				new HypotheticalCondition(ddf2), new HypotheticalCondition(ddf2_1));

		hpp1.setWholeDynamicCondition(cdg3);
		hpp2.setWholeDynamicCondition(cdg3);
		hpp3.setWholeDynamicCondition(cdg3);
		hpp4.setWholeDynamicCondition(cdg3);


//		EquivalenceRelation eqr11 = 
				new EquivalenceRelation(dc3, ddf1_1);



		// HelloHoliday2_3.java
		//SimpleThinkAction
		Action sta2_3_1 = new SimpleThinkAction("<st(>" + dc3.getId() + "<)>");
		InputParameter[] ipa2_3_1 = InputParameter.informationInstances("a", c003, "b", c002);//3, 2
		Object[] staOutputArray2_3_1 = sta2_3_1.execute(ipa2_3_1);
		System.out.println();
		System.out.println("SimpleThinkAction2_3_1.execute: " 
				+ ((staOutputArray2_3_1 == null) ? null : Arrays.asList((Object[]) staOutputArray2_3_1)));
	}

}
