package com.codejstudio.algi.samples;

import java.util.Arrays;

import com.codejstudio.algi.action.ActionDefinition;
import com.codejstudio.algi.action.ActionFlow;
import com.codejstudio.algi.action.dynamicAction.DynamicHypotheticalPropositionAction;
import com.codejstudio.algi.action.symbol.DynamicStatementSymbol;
import com.codejstudio.algi.common.ALGIAutoInitConstant;
import com.codejstudio.lim.Informationiverse;
import com.codejstudio.lim.common.util.InitializationUtil;
import com.codejstudio.lim.pojo.Root;
import com.codejstudio.lim.pojo.concept.Concept;
import com.codejstudio.lim.pojo.condition.AttributeCondition;
import com.codejstudio.lim.pojo.condition.ConditionGroup;
import com.codejstudio.lim.pojo.condition.HypotheticalCondition;
import com.codejstudio.lim.pojo.condition.OrConditionGroup;
import com.codejstudio.lim.pojo.i.IActionable;
import com.codejstudio.lim.pojo.i.IDynamicable.DynamicParameterType;
import com.codejstudio.lim.pojo.relation.EquivalenceRelation;
import com.codejstudio.lim.pojo.statement.Definition;
import com.codejstudio.lim.pojo.statement.HypotheticalProposition;
import com.codejstudio.lim.pojo.util.DynamicableUtil;
import com.codejstudio.lim.pojo.util.InputParameter;

/**
 * sample: calculate: 3+2=? (via ActionableElement)
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     com.codejstudio.lim.pojo.concept.Concept
 * @see     com.codejstudio.lim.pojo.statement.Definition
 * @see     com.codejstudio.lim.pojo.condition.AttributeCondition
 * @see     com.codejstudio.lim.pojo.statement.HypotheticalProposition
 * @see     com.codejstudio.lim.pojo.relation.EquivalenceRelation
 * @see     com.codejstudio.algi.action.dynamicAction.DynamicHypotheticalPropositionAction
 * @see     com.codejstudio.algi.action.dynamicAction.DynamicStatementAction
 * @see     com.codejstudio.algi.action.dynamicAction.VerifySubElementAction
 * @see     com.codejstudio.algi.action.ActionDefinition
 * @since   algi_v1.0.0
 */
public class HelloHoliday2_2 {

	/* static methods */

	public static void main(String[] args) throws Exception {
		InitializationUtil.initSampleMode(ALGIAutoInitConstant.AUTO_INITS);

		doXmlMarshal();
//		HelloAction.doXmlUnmarshal(HelloHoliday2_2.class.getSimpleName());
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
		Definition ddf11 = new Definition("如果记忆中存在\"<$a>+<$b>\"对应的值<#c>，则直接将<#c>作为结果输出。");//（属性：[备注：$a、$b、#c都是数字]）
		Definition ddf21_1 = new Definition("因为：“如果<a>=<b>，则<b>=<a>，其中<a>、<b>是数字”，而“1+1=2”，所以：“2=1+1”。");
		Definition ddf21_2 = new Definition("因为：“如果<a>=<b>，则<a>+<c>=<b>+<c>，其中<a>、<b>、<c>是数字”，而“2=1+1”，所以：“2+3=1+1+3”。");
		Definition ddf21_3 = new Definition("因为：“如果<a>=<b>，则<c>+<a>=<c>+<b>，其中<a>、<b>、<c>是数字”，而“1+3=4”，所以：“1+1+3=1+4”。");
		Definition ddf21_4 = new Definition("因为：“如果<a>=<b>,<b>=<c>，则<a>=<c>，其中<a>、<b>、<c>是数字”，而“2+3=1+1+3, 1+1+3=1+4”，所以：“2+3=1+4”。");
		Definition ddf21_5 = new Definition("因为：“如果<a>=<b>,<b>=<c>，则<a>=<c>，其中<a>、<b>、<c>是数字”，而“2+3=1+4, 1+4=5”，所以：“2+3=5”。");

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


		String parameterName21 = DynamicHypotheticalPropositionAction.PARAMETER_NAME_PREFIX + "adf21";
		String parameterName22 = DynamicHypotheticalPropositionAction.PARAMETER_NAME_PREFIX + "adf22";
		String parameterName23 = DynamicHypotheticalPropositionAction.PARAMETER_NAME_PREFIX + "adf23";
		String parameterName24 = DynamicHypotheticalPropositionAction.PARAMETER_NAME_PREFIX + "adf24";
		String parameterName25 = DynamicHypotheticalPropositionAction.PARAMETER_NAME_PREFIX + "adf25";
		String parameterName26 = DynamicHypotheticalPropositionAction.PARAMETER_NAME_PREFIX + "adf26";

		ActionDefinition adf11 = new ActionDefinition(
				"<ps(><#R>,"
					+ "<ds(>" + ddf1_1.getId() + "," + DynamicStatementSymbol.TYPE_INFO + "<)>"
				+ "<)>"); //新建并更新临时定义元素：结果<#R>值
		ActionDefinition adf12 = new ActionDefinition("<gs(><#R><)>"); //输出结果<#R>值

		ActionDefinition adf201 = new ActionDefinition(
				"<ps(>" + parameterName21 + ","
					+ "<ie(>" + df11.getId() + "<)>"
				+ "<)>");
		ActionDefinition adf202 = new ActionDefinition(
				"<dhp(>" + hpp1.getId() + "," + parameterName21 + "<)>");//1+1=2 => 2=1+1
		ActionDefinition adf203 = new ActionDefinition(
				new ActionDefinition("<ps(>" + parameterName22 + ","
					+ "<oa(>"), 
						adf202, new ActionDefinition(","
						+ "<ie(>" + c003.getId() + "<)>"
					+ "<)>"
				+ "<)>"));
		ActionDefinition adf204 = new ActionDefinition(
				"<dhp(>" + hpp2.getId() + "," + parameterName22 + "<)>");//2=1+1 => 3+2=3+1+1
		ActionDefinition adf205 = new ActionDefinition(
				"<ps(>" + parameterName23 + ","
					+ "<oa(>"
						+ "<ie(>" + df31.getId() + "<)>,"
						+ "<ie(>" + c001.getId() + "<)>"
					+ "<)>"
				+ "<)>");
		ActionDefinition adf206 = new ActionDefinition(
				"<dhp(>" + hpp3.getId() + "," + parameterName23 + "<)>");//3+1=4 => 3+1+1=4+1
		ActionDefinition adf207 = new ActionDefinition(
				new ActionDefinition("<ps(>" + parameterName24 + ","
					+ "<oa(>"), 
						adf204, new ActionDefinition(","), 
						adf206, 
					new ActionDefinition("<)>"
				+ "<)>"));
		ActionDefinition adf208 = new ActionDefinition(
				"<dhp(>" + hpp4.getId() + "," + parameterName24 + "<)>");//3+2=3+1+1, 3+1+1=4+1 => 3+2=4+1
		ActionDefinition adf209 = new ActionDefinition(
				new ActionDefinition("<ps(>" + parameterName25 + ","
					+ "<oa(>"), 
						adf208, new ActionDefinition(","
						+ "<ie(>" + df41.getId() + "<)>"
					+ "<)>"
				+ "<)>"));
		ActionDefinition adf210 = new ActionDefinition(
				"<dhp(>" + hpp4.getId() + "," + parameterName25 + "<)>");//3+2=4+1, 4+1=5 => 3+2=5
		ActionDefinition adf211 = new ActionDefinition(
				new ActionDefinition("<ps(>" + parameterName26 + ","), 
					adf210, 
				new ActionDefinition("<)>"));
		ActionDefinition adf212 = new ActionDefinition("<ps(><#R>,<gs(>" + parameterName26 + "<)><)>"); //新建并更新临时定义元素：结果<#R>值
		ActionDefinition adf213 = new ActionDefinition("<gs(><#R><)>"); //输出结果<#R>值

		ActionDefinition adf221 = new ActionDefinition(adf201, adf202);
		ActionDefinition adf222 = new ActionDefinition(adf203, adf204);
		ActionDefinition adf223 = new ActionDefinition(adf205, adf206);
		ActionDefinition adf224 = new ActionDefinition(adf207, adf208);
		ActionDefinition adf225 = new ActionDefinition(adf209, adf210);

		ActionFlow afl11 = new ActionFlow(adf11, adf12);
		ActionFlow afl21 = new ActionFlow(adf201, adf203, adf205, adf207, adf209, adf211, adf212, adf213);

		ActionDefinition adf10 = new ActionDefinition(
				"<cd{><iu(><$a>+<$b>=<#><)><}" //条件：存在"<$a>+<$b>"对应的值<#>
				+ "do(>cd!=null<){>" //条件结果：结果存在
					+ "<ax(>" + afl11.getId() + "<)>" //执行任务流afl11
				+ "<}>");
		ActionDefinition adf20 = new ActionDefinition(
				"<ax(>" + afl21.getId() + "<)>" //执行任务流afl21
				);


//		EquivalenceRelation eqr11 = 
				new EquivalenceRelation(dc3, ddf1_1);
//		EquivalenceRelation eqr20 = 
				new EquivalenceRelation(ddf11, adf10);
//		EquivalenceRelation eqr21 = 
				new EquivalenceRelation(ddf21_1, adf221);
//		EquivalenceRelation eqr22 = 
				new EquivalenceRelation(ddf21_2, adf222);
//		EquivalenceRelation eqr23 = 
				new EquivalenceRelation(ddf21_3, adf223);
//		EquivalenceRelation eqr24 = 
				new EquivalenceRelation(ddf21_4, adf224);
//		EquivalenceRelation eqr25 = 
				new EquivalenceRelation(ddf21_5, adf225);



		// HelloHoliday2_2.java
		//ActionableElement
		IActionable afl2_2_1 = new ActionFlow(adf20);
		Object[] aflOutputArray2_2_1 = afl2_2_1.execute();
		System.out.println();
		System.out.println("ActionableElement2_2_1.execute: " 
				+ ((aflOutputArray2_2_1 == null) ? null : Arrays.asList((Object[]) aflOutputArray2_2_1)));

		IActionable afl2_2_2 = new ActionFlow(adf10);
		InputParameter[] ipa2_2_1 = InputParameter.informationInstances("a", c003, "b", c002);//3, 2
		Object[] aflOutputArray2_2_2 = afl2_2_2.execute(ipa2_2_1);
		System.out.println();
		System.out.println("ActionableElement2_2_2.execute: " 
				+ ((aflOutputArray2_2_2 == null) ? null : Arrays.asList((Object[]) aflOutputArray2_2_2)));



		Root root = new Root(Informationiverse.getAllActionableElementCollection(), 
				Informationiverse.getAllElementCollection());
		System.out.println();
		root.marshalToXml(System.out);
	}

}
