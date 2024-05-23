package com.codejstudio.algi.samples;

import java.net.URL;
import java.util.Arrays;

import com.codejstudio.algi.action.Action;
import com.codejstudio.algi.action.ActionDefinition;
import com.codejstudio.algi.action.ActionFlow;
import com.codejstudio.algi.action.ActionQueue;
import com.codejstudio.algi.action.ActionSession;
import com.codejstudio.algi.action.ActionTemplate;
import com.codejstudio.algi.action.dynamicAction.DynamicHypotheticalPropositionAction;
import com.codejstudio.algi.action.symbol.DynamicStatementSymbol;
import com.codejstudio.algi.action.symbol.GetSessionAttributeSymbol;
import com.codejstudio.algi.action.symbol.PutSessionAttributeSymbol;
import com.codejstudio.algi.action.symbolAction.ActionableExecuteAction;
import com.codejstudio.algi.action.symbolAction.CodeExecuteAction;
import com.codejstudio.algi.action.symbolAction.CombineAction;
import com.codejstudio.algi.action.symbolAction.CompareAction;
import com.codejstudio.algi.action.symbolAction.ConditionDoOnceAction;
import com.codejstudio.algi.action.symbolAction.ConditionDoRepeatedlyAction;
import com.codejstudio.algi.action.symbolAction.DoRepeatedlyConditionAction;
import com.codejstudio.algi.action.symbolAction.DynamicHypotheticalPropositionSymbolAction;
import com.codejstudio.algi.action.symbolAction.DynamicStatementSymbolAction;
import com.codejstudio.algi.action.symbolAction.EqualAction;
import com.codejstudio.algi.action.symbolAction.GetElementAction;
import com.codejstudio.algi.action.symbolAction.GetSessionAttributeAction;
import com.codejstudio.algi.action.symbolAction.InformationElementAction;
import com.codejstudio.algi.action.symbolAction.InformationiverseAction;
import com.codejstudio.algi.action.symbolAction.LengthOfAction;
import com.codejstudio.algi.action.symbolAction.ObjectArrayAction;
import com.codejstudio.algi.action.symbolAction.PutSessionAttributeAction;
import com.codejstudio.algi.action.symbolAction.SplitAction;
import com.codejstudio.algi.action.symbolAction.StartWithAction;
import com.codejstudio.algi.action.symbolAction.SubstituteDynamicIdentifierAction;
import com.codejstudio.algi.action.symbolAction.SubstringAction;
import com.codejstudio.algi.common.ALGIAutoInitConstant;
import com.codejstudio.lim.Informationiverse;
import com.codejstudio.lim.common.util.InitializationUtil;
import com.codejstudio.lim.pojo.Root;
import com.codejstudio.lim.pojo.concept.Concept;
import com.codejstudio.lim.pojo.condition.AttributeCondition;
import com.codejstudio.lim.pojo.condition.ConditionGroup;
import com.codejstudio.lim.pojo.condition.HypotheticalCondition;
import com.codejstudio.lim.pojo.i.IDynamicable.DynamicParameterType;
import com.codejstudio.lim.pojo.relation.EquivalenceRelation;
import com.codejstudio.lim.pojo.statement.Definition;
import com.codejstudio.lim.pojo.statement.HypotheticalProposition;
import com.codejstudio.lim.pojo.util.Description;
import com.codejstudio.lim.pojo.util.InputParameter;

/**
 * sample: Action samples
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     com.codejstudio.lim.pojo.concept.Concept
 * @see     com.codejstudio.lim.pojo.statement.Definition
 * @see     com.codejstudio.lim.pojo.condition.AttributeCondition
 * @see     com.codejstudio.lim.pojo.statement.HypotheticalProposition
 * @see     com.codejstudio.lim.pojo.relation.EquivalenceRelation
 * @see     com.codejstudio.algi.action.symbolAction.LengthOfAction
 * @see     com.codejstudio.algi.action.symbolAction.SplitAction
 * @see     com.codejstudio.algi.action.symbolAction.GetElementAction
 * @see     com.codejstudio.algi.action.symbolAction.CompareAction
 * @see     com.codejstudio.algi.action.symbolAction.EqualAction
 * @see     com.codejstudio.algi.action.symbolAction.StartWithAction
 * @see     com.codejstudio.algi.action.symbolAction.SubstringAction
 * @see     com.codejstudio.algi.action.symbolAction.CombineAction
 * @see     com.codejstudio.algi.action.symbolAction.ObjectArrayAction
 * @see     com.codejstudio.algi.action.symbolAction.InformationElementAction
 * @see     com.codejstudio.algi.action.symbolAction.InformationiverseAction
 * @see     com.codejstudio.algi.action.symbolAction.PutSessionAttributeAction
 * @see     com.codejstudio.algi.action.symbolAction.GetSessionAttributeAction
 * @see     com.codejstudio.algi.action.symbolAction.DynamicStatementSymbolAction
 * @see     com.codejstudio.algi.action.symbolAction.DynamicHypotheticalPropositionSymbolAction
 * @see     com.codejstudio.algi.action.symbolAction.SubstituteDynamicIdentifierAction
 * @see     com.codejstudio.algi.action.symbolAction.CodeExecuteAction
 * @see     com.codejstudio.algi.action.symbolAction.ConditionDoOnceAction
 * @see     com.codejstudio.algi.action.symbolAction.ConditionDoRepeatedlyAction
 * @see     com.codejstudio.algi.action.symbolAction.DoRepeatedlyConditionAction
 * @see     com.codejstudio.algi.action.ActionFlow
 * @see     com.codejstudio.algi.action.ActionTemplate
 * @see     com.codejstudio.algi.action.ActionQueue
 * @see     com.codejstudio.algi.action.symbolAction.ActionableExecuteAction
 * @since   algi_v1.0.0
 */
public class HelloAction {

	/* static methods */

	public static void main(String[] args) throws Exception {
		InitializationUtil.initSampleMode(ALGIAutoInitConstant.AUTO_INITS);

		doXmlMarshal();
//		HelloAction.doXmlUnmarshal(HelloAction.class.getSimpleName());
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
		Concept c100 = new Concept("100");
		Concept c101 = new Concept("+");
		Concept c102 = new Concept("=");
		Concept c111 = new Concept("十");
		Concept c112 = new Concept("一百");
		Concept c113 = new Concept("等于");
		Concept c114 = new Concept("数字");
		Concept c211 = new Concept("1+1");
		Concept c221 = new Concept("2+1");
		Concept c261 = new Concept("6+1");
		Concept c281 = new Concept("8+1");

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
		c100.addDefaultAttribute(c114);
		c211.addSubConceptWithPosition(c001, c101);
		c221.addSubConceptWithPosition(c002, c101, c001);
		c261.addSubConceptWithPosition(c006, c101, c001);
		c281.addSubConceptWithPosition(c008, c101, c001);


		Definition df11 = new Definition("1+1=2");
		Definition df21 = new Definition("2+1=3");
		Definition df61 = new Definition("6+1=7");
		Definition df81 = new Definition("8+1=9");

		df11.addConceptWithPosition(c211, c102, c002);
		df21.addConceptWithPosition(c221, c102, c003);
		df61.addConceptWithPosition(c261, c102, c007);
		df81.addConceptWithPosition(c281, c102, c009);


		ConditionGroup cdg1 = new ConditionGroup(new AttributeCondition(c114));


		Concept dc1 = new Concept("<$a>");//（属性：[备注：$a是数字]）
		Concept dc2 = new Concept("<$a>+<$b>");//（属性：[备注：$a、$b都是数字]）

		dc1.setNetDynamicConditions(cdg1);
		dc2.addSubConceptWithPosition(c101, dc1);
		dc2.setNetDynamicConditions(cdg1, cdg1);


		Definition ddf1 = new Definition("<$a>+<$b>=<$c>");//（属性：[备注：$a、$b、$c都是数字]）
		Definition ddf2 = new Definition("<$1>=<$2>");//（属性：[备注：$1、$2都是数字，或算术表达式]）
		Definition ddf2_0 = new Definition("<$2>=<$1>");//（属性：[备注：$1、$2都是数字，或算术表达式]）
		Definition ddf3_2 = new Definition("<$3>+<$1>=<$3>+<$2>");//（属性：[备注：$1、$2、$3都是数字，或算术表达式]）

		ddf1.addConceptWithPosition(dc2, c102, dc1);
		ddf1.setNetDynamicConditions(cdg1, cdg1, cdg1);

		Definition ddf1_1 = (Definition) ddf1.substituteDynamicSymbolByParameterType(
				new DynamicParameterType[] {DynamicParameterType.INPUT, DynamicParameterType.INPUT, 
						DynamicParameterType.OUTPUT});//"<$a>+<$b>=<#c>"（属性：[备注：$a、$b、#c都是数字]）


		HypotheticalProposition hpp1 = new HypotheticalProposition(ddf2_0, new HypotheticalCondition(ddf2));
		HypotheticalProposition hpp2 = new HypotheticalProposition(ddf3_2, new HypotheticalCondition(ddf2));


//		EquivalenceRelation eqr01 = 
				new EquivalenceRelation(c102, c113);
//		EquivalenceRelation eqr02 = 
				new EquivalenceRelation(c010, c111);
//		EquivalenceRelation eqr03 = 
				new EquivalenceRelation(c100, c112);



		//LengthOfAction
		Action loa01 = new LengthOfAction("<lo(>Definition<)>");
		Object[] loaOutputArray01 = loa01.execute();

		Action loa02 = new LengthOfAction("EquivalenceRelation");
		Object[] loaOutputArray02 = loa02.execute();

		System.out.println();
		System.out.println("LengthOfAction01.execute: " 
				+ ((loaOutputArray01 == null) ? null : Arrays.asList((Object[]) loaOutputArray01)));
		System.out.println("LengthOfAction02.execute: " 
				+ ((loaOutputArray02 == null) ? null : Arrays.asList((Object[]) loaOutputArray02)));


		//SplitAction
		Action spa01 = new SplitAction("<sp(>1+1=2<)>");
		Object[] spaOutputArray01 = spa01.execute();

		Action spa02 = new SplitAction("4+1=5");
		Object[] spaOutputArray02 = spa02.execute();

		Action spa03 = new SplitAction("<sp(>1+1=2,1<)>");
		Object[] spaOutputArray03 = spa03.execute();

		Action spa04 = new SplitAction("4+1=5,=");
		Object[] spaOutputArray04 = spa04.execute();

		System.out.println();
		System.out.println("SplitAction01.execute: " + ((spaOutputArray01 == null) ? null : ""));
		for (int i = 0; (spaOutputArray01 != null && i < spaOutputArray01.length); i++) {
			System.out.println("[" + i + "]: " 
					+ ((spaOutputArray01[i] == null) ? null : Arrays.asList((Object[]) spaOutputArray01[i])));
		}
		System.out.println("SplitAction02.execute: " + ((spaOutputArray02 == null) ? null : ""));
		for (int i = 0; (spaOutputArray02 != null && i < spaOutputArray02.length); i++) {
			System.out.println("[" + i + "]: " 
					+ ((spaOutputArray02[i] == null) ? null : Arrays.asList((Object[]) spaOutputArray02[i])));
		}
		System.out.println("SplitAction03.execute: " + ((spaOutputArray03 == null) ? null : ""));
		for (int i = 0; (spaOutputArray03 != null && i < spaOutputArray03.length); i++) {
			System.out.println("[" + i + "]: " 
					+ ((spaOutputArray03[i] == null) ? null : Arrays.asList((Object[]) spaOutputArray03[i])));
		}
		System.out.println("SplitAction04.execute: " + ((spaOutputArray04 == null) ? null : ""));
		for (int i = 0; (spaOutputArray04 != null && i < spaOutputArray04.length); i++) {
			System.out.println("[" + i + "]: " 
					+ ((spaOutputArray04[i] == null) ? null : Arrays.asList((Object[]) spaOutputArray04[i])));


		//GetElementAction
		Action gea01 = new GetElementAction("<ge(><sp(>5+1=6,1<)>,0<)>");
		Object[] geaOutputArray01 = gea01.execute();

		Action gea02 = new GetElementAction("<sp(>1+1=2<)>,1");
		Object[] geaOutputArray02 = gea02.execute();

		System.out.println();
		System.out.println("GetElementAction01.execute: " 
				+ ((geaOutputArray01 == null) ? null : Arrays.asList((Object[]) geaOutputArray01)));
		System.out.println("GetElementAction02.execute: " 
				+ ((geaOutputArray02 == null) ? null : Arrays.asList((Object[]) geaOutputArray02)));
		}


		//CompareAction
		Action cpa01 = new CompareAction("<cp(>2,2.01<)>");
		Object[] cpaOutputArray01 = cpa01.execute();

		Action cpa02 = new CompareAction("2,2.00");
		Object[] cpaOutputArray02 = cpa02.execute();

		System.out.println();
		System.out.println("CompareAction01.execute: " 
				+ ((cpaOutputArray01 == null) ? null : Arrays.asList((Object[]) cpaOutputArray01)));
		System.out.println("CompareAction02.execute: " 
				+ ((cpaOutputArray02 == null) ? null : Arrays.asList((Object[]) cpaOutputArray02)));


		//EqualAction
		Action eqa01 = new EqualAction("<eq(>10,十<)>");
		Object[] eqaOutputArray01 = eqa01.execute();

		Action eqa02 = new EqualAction("等于,=");
		Object[] eqaOutputArray02 = eqa02.execute();

		System.out.println();
		System.out.println("EqualAction01.execute: " 
				+ ((eqaOutputArray01 == null) ? null : Arrays.asList((Object[]) eqaOutputArray01)));
		System.out.println("EqualAction02.execute: " 
				+ ((eqaOutputArray02 == null) ? null : Arrays.asList((Object[]) eqaOutputArray02)));


		//StartWithAction
		Action swa01 = new StartWithAction("<sw(>1+1=2,1+2<)>");
		Object[] swaOutputArray01 = swa01.execute();

		Action swa02 = new StartWithAction("5+1=6,5+1");
		Object[] swaOutputArray02 = swa02.execute();

		System.out.println();
		System.out.println("StartWithAction01.execute: " 
				+ ((swaOutputArray01 == null) ? null : Arrays.asList((Object[]) swaOutputArray01)));
		System.out.println("StartWithAction02.execute: " 
				+ ((swaOutputArray02 == null) ? null : Arrays.asList((Object[]) swaOutputArray02)));


		//SubstringAction
		Action ssa01 = new SubstringAction("<ss(>1+1=2,1<)>");
		Object[] ssaOutputArray01 = ssa01.execute();

		Action ssa02 = new SubstringAction("<ss(>1+1=2,2,4<)>");
		Object[] ssaOutputArray02 = ssa02.execute();

		Action ssa03 = new SubstringAction("Definition,3");
		Object[] ssaOutputArray03 = ssa03.execute();

		Action ssa04 = new SubstringAction("EquivalenceRelation,5,10");
		Object[] ssaOutputArray04 = ssa04.execute();

		System.out.println();
		System.out.println("SubstringAction01.execute: " 
				+ ((ssaOutputArray01 == null) ? null : Arrays.asList((Object[]) ssaOutputArray01)));
		System.out.println("SubstringAction02.execute: " 
				+ ((ssaOutputArray02 == null) ? null : Arrays.asList((Object[]) ssaOutputArray02)));
		System.out.println("SubstringAction03.execute: " 
				+ ((ssaOutputArray03 == null) ? null : Arrays.asList((Object[]) ssaOutputArray03)));
		System.out.println("SubstringAction04.execute: " 
				+ ((ssaOutputArray04 == null) ? null : Arrays.asList((Object[]) ssaOutputArray04)));


		//CombineAction
		Action cba01 = new CombineAction("<cb(>0,<$10><)>");
		Object[] cbaOutputArray01 = cba01.execute();

		Action cba02 = new CombineAction("1+1,=,2");
		Object[] cbaOutputArray02 = cba02.execute();

		System.out.println();
		System.out.println("CombineAction01.execute: " 
				+ ((cbaOutputArray01 == null) ? null : Arrays.asList((Object[]) cbaOutputArray01)));
		System.out.println("CombineAction02.execute: " 
				+ ((cbaOutputArray02 == null) ? null : Arrays.asList((Object[]) cbaOutputArray02)));


		//ObjectArrayAction
		Action oaa01 = new ObjectArrayAction("<oa(><sp(>1+1=2,1<)>,1+2<)>");
		Object[] oaaOutputArray01 = oaa01.execute();

		Action oaa02 = new ObjectArrayAction("<eq(>100,一百<)>,<sp(>7+1=8<)>");
		Object[] oaaOutputArray02 = oaa02.execute();

		System.out.println();
		System.out.println("ObjectArrayAction01.execute: " + ((oaaOutputArray01 == null) ? null : ""));
		if (oaaOutputArray01 != null && oaaOutputArray01.length == 1 && oaaOutputArray01[0] instanceof Object[]) {
			Object[] outputArray = (Object[]) oaaOutputArray01[0];
			for (int i = 0; i < outputArray.length; i++) {
				System.out.println("[" + i + "]: " + ((outputArray[i] instanceof Object[]) 
						? Arrays.asList((Object[]) outputArray[i]) : outputArray[i]));
			}
		}
		System.out.println("ObjectArrayAction02.execute: " + ((oaaOutputArray02 == null) ? null : ""));
		if (oaaOutputArray02 != null && oaaOutputArray02.length == 1 && oaaOutputArray02[0] instanceof Object[]) {
			Object[] outputArray = (Object[]) oaaOutputArray02[0];
			for (int i = 0; i < outputArray.length; i++) {
				System.out.println("[" + i + "]: " + ((outputArray[i] instanceof Object[]) 
						? Arrays.asList((Object[]) outputArray[i]) : outputArray[i]));
			}
		}


		//InformationElementAction
		Action iea01 = new InformationElementAction("<ie(>" + df11.getId() + "<)>");
		Object[] ieaOutputArray01 = iea01.execute();

		Action iea02 = new InformationElementAction(df21.getId());
		Object[] ieaOutputArray02 = iea02.execute();

		System.out.println();
		System.out.println("InformationElementAction01.execute: " 
				+ ((ieaOutputArray01 == null) ? null : Arrays.asList((Object[]) ieaOutputArray01)));
		System.out.println("InformationElementAction02.execute: " 
				+ ((ieaOutputArray02 == null) ? null : Arrays.asList((Object[]) ieaOutputArray02)));


		//InformationiverseAction
		Action iua01 = new InformationiverseAction("<iu(>10<)>");
		Object[] iuaOutputArray01 = iua01.execute();

		Action iua02 = new InformationiverseAction("21+1");
		Object[] iuaOutputArray02 = iua02.execute();

		Action iua03 = new InformationiverseAction("<iu(>10000,1<)>");
		Object[] iuaOutputArray03 = iua03.execute();

		Action iua04 = new InformationiverseAction("11+1=12,true");
		Object[] iuaOutputArray04 = iua04.execute();

		System.out.println();
		System.out.println("InformationiverseAction01.execute: " 
				+ ((iuaOutputArray01 == null) ? null : Arrays.asList((Object[]) iuaOutputArray01)));
		System.out.println("InformationiverseAction02.execute: " 
				+ ((iuaOutputArray02 == null) ? null : Arrays.asList((Object[]) iuaOutputArray02)));
		System.out.println("InformationiverseAction03.execute: " 
				+ ((iuaOutputArray03 == null) ? null : Arrays.asList((Object[]) iuaOutputArray03)));
		System.out.println("InformationiverseAction04.execute: " 
				+ ((iuaOutputArray04 == null) ? null : Arrays.asList((Object[]) iuaOutputArray04)));


		//PutSessionAttributeAction
		Action psa01 = new PutSessionAttributeAction("<ps(>十,10," + PutSessionAttributeSymbol.TYPE_INT + "<)>");
		Object[] psaOutputArray01 = psa01.execute();

		Action psa02 = new PutSessionAttributeAction("<ps(>10,<iu(>十<)><)>");
		Object[] psaOutputArray02 = psa02.execute();

		Action psa03 = new PutSessionAttributeAction("七," + "<ie(>" + df61.getId() + "<)>");
		Object[] psaOutputArray03 = psa03.execute();

		Action psa04 = new PutSessionAttributeAction("九," + "<ie(>" + df81.getId() + "<)>" 
				+ "," + PutSessionAttributeSymbol.TYPE_INFO_STRING);
		Object[] psaOutputArray04 = psa04.execute();

		System.out.println();
		System.out.println("PutSessionAttributeAction01.execute: " 
				+ ((psaOutputArray01 == null) ? null : Arrays.asList((Object[]) psaOutputArray01)));
		System.out.println("PutSessionAttributeAction02.execute: " 
				+ ((psaOutputArray02 == null) ? null : Arrays.asList((Object[]) psaOutputArray02)));
		System.out.println("PutSessionAttributeAction03.execute: " 
				+ ((psaOutputArray03 == null) ? null : Arrays.asList((Object[]) psaOutputArray03)));
		System.out.println("PutSessionAttributeAction04.execute: " 
				+ ((psaOutputArray04 == null) ? null : Arrays.asList((Object[]) psaOutputArray04)));


		//GetSessionAttributeAction
		Action gsa01 = new GetSessionAttributeAction("<gs(>十<)>");
		gsa01.setSession(psa01.getSession());
		Object[] gsaOutputArray01 = gsa01.execute();

		Action gsa02 = new GetSessionAttributeAction("10," + GetSessionAttributeSymbol.TYPE_INFO_STRING);
		gsa02.setSession(psa02.getSession());
		Object[] gsaOutputArray02 = gsa02.execute();

		Action gsa03 = new GetSessionAttributeAction("<gs(>七," + GetSessionAttributeSymbol.TYPE_INFO_STRING + "<)>");
		gsa03.setSession(psa03.getSession());
		Object[] gsaOutputArray03 = gsa03.execute();

		Action gsa04 = new GetSessionAttributeAction("九");
		gsa04.setSession(psa04.getSession());
		Object[] gsaOutputArray04 = gsa04.execute();

		System.out.println();
		System.out.println("GetSessionAttributeAction01.execute: " 
				+ ((gsaOutputArray01 == null) ? null : Arrays.asList((Object[]) gsaOutputArray01)));
		System.out.println("GetSessionAttributeAction02.execute: " 
				+ ((gsaOutputArray02 == null) ? null : Arrays.asList((Object[]) gsaOutputArray02)));
		System.out.println("GetSessionAttributeAction03.execute: " 
				+ ((gsaOutputArray03 == null) ? null : Arrays.asList((Object[]) gsaOutputArray03)));
		System.out.println("GetSessionAttributeAction04.execute: " 
				+ ((gsaOutputArray04 == null) ? null : Arrays.asList((Object[]) gsaOutputArray04)));


		//DynamicStatementSymbolAction
		Action dssa01 = new DynamicStatementSymbolAction("<ds(><$a>+<$b>=<#c><)>");
		InputParameter[] ipa01 = InputParameter.informationInstances("a", c002, "b", c001);
		Object[] dssaOutputArray01 = dssa01.execute(ipa01);

		Action dssa02 = new DynamicStatementSymbolAction("<ds(>" + ddf1_1.getId() 
				+ "," + DynamicStatementSymbol.TYPE_INFO + "<)>");
		InputParameter[] ipa02 = InputParameter.informationInstances("a", c006, "b", c001);
		Object[] dssaOutputArray02 = dssa02.execute(ipa02);

		System.out.println();
		System.out.println("DynamicStatementSymbolAction01.execute: " 
				+ ((dssaOutputArray01 == null) ? null : Arrays.asList((Object[]) dssaOutputArray01)));
		System.out.println("DynamicStatementSymbolAction02.execute: " 
				+ ((dssaOutputArray02 == null) ? null : Arrays.asList((Object[]) dssaOutputArray02)));


		//DynamicHypotheticalPropositionSymbolAction
		Action dhppsa01 = new DynamicHypotheticalPropositionSymbolAction("<dhp(>" + hpp1.getId() + "<)>");
		InputParameter[] ipa03 = InputParameter.generateInstances(
				DynamicHypotheticalPropositionAction.PARAMETER_ELEMENT, new Object[] {df11});
		Object[] dhppsaOutputArray01 = dhppsa01.execute(ipa03);

		String parameterName01 = DynamicHypotheticalPropositionAction.PARAMETER_NAME_PREFIX + "dhppsa02";
		Action dhppsa02 = new DynamicHypotheticalPropositionSymbolAction(
				"<dhp(>" + hpp2.getId() + "," + parameterName01 + "<)>");
		InputParameter[] ipa04 = InputParameter.generateInstances(
				parameterName01, new Object[] {dhppsaOutputArray01, c003});
		Object[] dhppsaOutputArray02 = dhppsa02.execute(ipa04);

		System.out.println();
		System.out.println("DynamicHypotheticalPropositionSymbolAction01.execute: " 
				+ ((dhppsaOutputArray01 == null) ? null : Arrays.asList((Object[]) dhppsaOutputArray01)));
		System.out.println("DynamicHypotheticalPropositionSymbolAction02.execute: " 
				+ ((dhppsaOutputArray02 == null) ? null : Arrays.asList((Object[]) dhppsaOutputArray02)));


		//SubstituteDynamicIdentifierAction
		Action sdia01 = new SubstituteDynamicIdentifierAction("<sdi(>" + ddf1_1.getId() + ",a1,b1,c1<)>");
		Object[] sdiaOutputArray01 = sdia01.execute(ipa01);

		System.out.println();
		System.out.println("SubstituteDynamicIdentifierAction01.execute: " 
				+ ((sdiaOutputArray01 == null) ? null : Arrays.asList((Object[]) sdiaOutputArray01)));


		//CodeExecuteAction
		Action cxa01 = new CodeExecuteAction("<cx(>1+1<)>");
		Object[] cxaOutputArray01 = cxa01.execute();

		String attributeName01 = "i";
		Action cxa02 = new CodeExecuteAction(attributeName01 + "==0");
		cxa02.setSession(new ActionSession());
		cxa02.getSession().putAttribute(attributeName01, 123);
		Object[] cxaOutputArray02 = cxa02.execute();

		String attributeName02 = "obj";
		Action cxa03 = new CodeExecuteAction("<cx(>" + attributeName02 + "!=null<)>");
		cxa03.setSession(new ActionSession());
		cxa03.getSession().putAttribute(attributeName02, ieaOutputArray01);
		Object[] cxaOutputArray03 = cxa03.execute();

		System.out.println();
		System.out.println("CodeExecuteAction01.execute: " 
				+ ((cxaOutputArray01 == null) ? null : Arrays.asList((Object[]) cxaOutputArray01)));
		System.out.println("CodeExecuteAction02.execute: " 
				+ ((cxaOutputArray02 == null) ? null : Arrays.asList((Object[]) cxaOutputArray02)));
		System.out.println("CodeExecuteAction03.execute: " 
				+ ((cxaOutputArray03 == null) ? null : Arrays.asList((Object[]) cxaOutputArray03)));



		//ConditionDoOnceAction
		Action cdoa01 = new ConditionDoOnceAction(
				"<cd{><sw(>1+1=2,1+1<)><}"
				+ "do(>true<){><iu(>12345,true<)><}>");
		Object[] cdoaOutputArray01 = cdoa01.execute();

		Action cdoa02 = new ConditionDoOnceAction(
				"<cd{><sw(>1+1=2,1+2<)><}"
				+ "do(>true<){><iu(>12345,1<)><}"
				+ "do(>false<){><iu(>67890,true<)><}>");
		Object[] cdoaOutputArray02 = cdoa02.execute();

		Action cdoa03 = new ConditionDoOnceAction(
				"<cd{><ss(>12345,1<)><}"
				+ "do(>true<){><iu(>12345,false<)><}"
				+ "do(>false<){><iu(>67890,0<)><}"
				+ "do(>345<){><iu(>1234567890,0<)><}>");
		Object[] cdoaOutputArray03 = cdoa03.execute();

		Action cdoa04 = new ConditionDoOnceAction(
				"<cd{><ss(>12345,2<)><}"
				+ "do(>true<){><iu(>12345,1<)><}"
				+ "do(>false<){><iu(>67890,false<)><}"
				+ "do(>2345<){><iu(>234567,0<)><}"
				+ "do(>345<){><iu(>1234567890,true<)><}>");
		Object[] cdoaOutputArray04 = cdoa04.execute();

		System.out.println();
		System.out.println("ConditionDoOnceAction01.execute: " + ((cdoaOutputArray01 == null) ? null : ""));
		for (int i = 0; (cdoaOutputArray01 != null && i < cdoaOutputArray01.length); i++) {
			System.out.println("[" + i + "]: " 
					+ (((cdoaOutputArray01[i] != null) && cdoaOutputArray01[i] instanceof Object[]) 
							? Arrays.asList((Object[]) cdoaOutputArray01[i]) : cdoaOutputArray01[i]));
		}
		System.out.println("ConditionDoOnceAction02.execute: " + ((cdoaOutputArray02 == null) ? null : ""));
		for (int i = 0; (cdoaOutputArray02 != null && i < cdoaOutputArray02.length); i++) {
			System.out.println("[" + i + "]: " 
					+ (((cdoaOutputArray02[i] != null) && cdoaOutputArray02[i] instanceof Object[]) 
							? Arrays.asList((Object[]) cdoaOutputArray02[i]) : cdoaOutputArray02[i]));
		}
		System.out.println("ConditionDoOnceAction03.execute: " + ((cdoaOutputArray03 == null) ? null : ""));
		for (int i = 0; (cdoaOutputArray03 != null && i < cdoaOutputArray03.length); i++) {
			System.out.println("[" + i + "]: " 
					+ (((cdoaOutputArray03[i] != null) && cdoaOutputArray03[i] instanceof Object[]) 
							? Arrays.asList((Object[]) cdoaOutputArray03[i]) : cdoaOutputArray03[i]));
		}
		System.out.println("ConditionDoOnceAction04.execute: " + ((cdoaOutputArray04 == null) ? null : ""));
		for (int i = 0; (cdoaOutputArray04 != null && i < cdoaOutputArray04.length); i++) {
			System.out.println("[" + i + "]: " 
					+ (((cdoaOutputArray04[i] != null) && cdoaOutputArray04[i] instanceof Object[]) 
							? Arrays.asList((Object[]) cdoaOutputArray04[i]) : cdoaOutputArray04[i]));
		}


		//ConditionDoRepeatedlyAction
		Action cdra01 = new ConditionDoRepeatedlyAction(
				"<cd{><sw(>1+1=2,1+1<)><}"
				+ "dr(>true<){><iu(>12345<)><}>");
		Object[] cdraOutputArray01 = cdra01.execute();

		Action cdra02 = new ConditionDoRepeatedlyAction(
				new Description("<po{><ps(>i,1,int<)><}"
						+ "cd{><cx(>i<=5<)><}"
						+ "dr(>true<){><iu(><gs(>i<)>+12345,true<)><}"
						+ "pr{><ps(>i,<cx(>i+1<)>,int<)><}>"));
		Object[] cdraOutputArray02 = cdra02.execute();

		System.out.println();
		System.out.println("ConditionDoRepeatedlyAction01.execute: " + ((cdraOutputArray01 == null) ? null : ""));
		for (int i = 0; (cdraOutputArray01 != null && i < cdraOutputArray01.length); i++) {
			System.out.println("[" + i + "]: " 
					+ (((cdraOutputArray01[i] != null) && cdraOutputArray01[i] instanceof Object[]) 
							? Arrays.asList((Object[]) cdraOutputArray01[i]) : cdraOutputArray01[i]));
		}
		System.out.println("ConditionDoRepeatedlyAction02.execute: " + ((cdraOutputArray02 == null) ? null : ""));
		for (int i = 0; (cdraOutputArray02 != null && i < cdraOutputArray02.length); i++) {
			System.out.println("[" + i + "]: " 
					+ (((cdraOutputArray02[i] != null) && cdraOutputArray02[i] instanceof Object[]) 
							? Arrays.asList((Object[]) cdraOutputArray02[i]) : cdraOutputArray02[i]));
		}


		//DoRepeatedlyConditionAction
		Action drca01 = new DoRepeatedlyConditionAction(
				"<dr(>true<){><iu(>12345<)><}"
				+ "cd{><sw(>1+1=2,1+1<)><}>");
		Object[] drcaOutputArray01 = drca01.execute();

		Action drca02 = new DoRepeatedlyConditionAction(
				new Description("<po{><ps(>i,1,int<)><}"
						+ "dr(>true<){><iu(><gs(>i<)>+12345,true<)><}"
						+ "cd{><cx(>i<=5<)><}"
						+ "pr{><ps(>i,<cx(>i+1<)>,int<)><}>"));
		Object[] drcaOutputArray02 = drca02.execute();

		System.out.println();
		System.out.println("DoRepeatedlyConditionAction01.execute: " + ((drcaOutputArray01 == null) ? null : ""));
		for (int i = 0; (drcaOutputArray01 != null && i < drcaOutputArray01.length); i++) {
			System.out.println("[" + i + "]: " 
					+ (((drcaOutputArray01[i] != null) && drcaOutputArray01[i] instanceof Object[]) 
							? Arrays.asList((Object[]) drcaOutputArray01[i]) : drcaOutputArray01[i]));
		}
		System.out.println("DoRepeatedlyConditionAction02.execute: " + ((drcaOutputArray02 == null) ? null : ""));
		for (int i = 0; (drcaOutputArray02 != null && i < drcaOutputArray02.length); i++) {
			System.out.println("[" + i + "]: " 
					+ (((drcaOutputArray02[i] != null) && drcaOutputArray02[i] instanceof Object[]) 
							? Arrays.asList((Object[]) drcaOutputArray02[i]) : drcaOutputArray02[i]));
		}



		//ActionFlow
		ActionFlow afl01 = new ActionFlow(new ActionDefinition("<cb(>0,<$10><)>"), 
				new ActionDefinition("<cp(>2,2.01<)>"), 
				new ActionDefinition("<eq(>10,十<)>"), 
				new ActionDefinition("<iu(>1+1=2<)>"), 
				new ActionDefinition("<lo(>Definition<)>"), 
				new ActionDefinition("<sp(>1+1=2,1<)>"), 
				new ActionDefinition("<ss(>1+1=2,0,3<)>"), 
				new ActionDefinition("<sw(>1+1=2,1+2<)>"));
		Object[] aflOutputArray01 = afl01.execute();

		System.out.println();
		System.out.println("ActionFlow01.executeAction: " 
				+ ((aflOutputArray01 == null) ? null : Arrays.asList((Object[]) aflOutputArray01)));



		//ActionTemplate
		ActionTemplate atp01 = new ActionTemplate(
				new ActionDefinition("<cb(><cp(>2,2.01<)>,<sw(>1+1=2,1+1<)><)>"));
		Object[] atpOutputArray01 = atp01.execute();

		System.out.println();
		System.out.println("ActionTemplate01.execute: " 
				+ ((atpOutputArray01 == null) ? null : Arrays.asList((Object[]) atpOutputArray01)));


		ActionTemplate atp02 = new ActionTemplate(
				new ActionDefinition("<po{><ps(>i,1,int<)><}"
						+ "dr(>true<){><iu(><gs(>i<)>+12345,true<)><}"
						+ "cd{><cx(>i<=5<)><}"
						+ "pr{><ps(>i,<cx(>i+1<)>,int<)><}>"
				));
		Object[] atpOutputArray02 = atp02.execute();

		System.out.println();
		System.out.println("ActionTemplate02.execute: " + ((atpOutputArray02 == null) ? null : ""));
		for (int i = 0; (atpOutputArray02 != null && i < atpOutputArray02.length); i++) {
			System.out.println("[" + i + "]: " 
					+ ((atpOutputArray02[i] == null) ? null : Arrays.asList(atpOutputArray02[i])));
		}



		//ActionQueue
		ActionQueue aqu01 = new ActionQueue(
				new ActionDefinition("<cb(>0,<$10><)>"), 
				new ActionDefinition("<cp(>2,2.01<)>"), 
				new ActionDefinition("<eq(>10,十<)>"), 
				new ActionDefinition("<iu(>1+1=2<)>"), 
				new ActionDefinition("<lo(>Definition<)>"), 
				new ActionDefinition("<sp(>1+1=2,1<)>"), 
				new ActionDefinition("<ss(>1+1=2,0,3<)>"), 
				new ActionDefinition("<sw(>1+1=2,1+2<)>"));
		Object[] aquOutputArray01 = aqu01.execute();

		System.out.println();
		System.out.println("ActionQueue01.execute: " + ((aquOutputArray01 == null) ? null : ""));
		for (int i = 0; (aquOutputArray01 != null && i < aquOutputArray01.length); i++) {
			System.out.println("[" + i + "]: " 
					+ ((aquOutputArray01[i] == null) ? null : Arrays.asList((Object[]) aquOutputArray01[i])));
		}


		ActionQueue aqu02 = new ActionQueue(
				new ActionDefinition("<cb(><cp(>2,2.01<)>,<sw(>1+1=2,1+1<)><)>"
						+ "<cp(><lo(>1+1=2<)>,<lo(>1+2=3<)><)>"
						+ "<eq(><lo(>Definition<)>,十<)>"
						+ "<iu(><cp(>2,2.01<)>,true<)>"
						+ "<iu(><sw(>1+1=2,1+1<)>,true<)>"
						+ "<iu(><ss(>Definition,3<)>,true<)>"
						+ "<iu(><cb(><cp(>2,2.01<)>,<sw(>1+1=2,1+1<)><)>,true<)>"
						+ "<lo(><cp(>2,2.01<)><)>"
						+ "<lo(><sw(>1+1=2,1+1<)><)>"
						+ "<lo(><ss(>Definition,3<)><)>"
						+ "<lo(><cb(><cp(>2,2.01<)>,<sw(>1+1=2,1+1<)><)><)>"
						+ "<sp(>1+1=2,<lo(><ss(>Definition,9<)><)><)>"
						+ "<ss(><cb(><cp(>2,2.01<)>,<sw(>1+1=2,1+1<)><)>,<lo(>1+1<)><)>"
						+ "<sw(><lo(>Definition<)>,<ss(>1+2,0,1<)><)>"
				));
		Object[] aquOutputArray02 = aqu02.execute();

		System.out.println();
		System.out.println("ActionQueue02.execute: " + ((aquOutputArray02 == null) ? null : ""));
		for (int i = 0; (aquOutputArray02 != null && i < aquOutputArray02.length); i++) {
			System.out.println("[" + i + "]: " 
					+ ((aquOutputArray02[i] == null) ? null : Arrays.asList((Object[]) aquOutputArray02[i])));
		}


		ActionQueue aqu03 = new ActionQueue(
				new ActionDefinition("<cd{><sw(>1+1=2,1+2<)><}"
						+ "do(>true<){><iu(>12345,1<)><}"
						+ "do(>false<){><iu(>67890,true<)><}>"
						), 
				new ActionDefinition("<po{><ps(>i,1,int<)><}"
						+ "dr(>true<){><iu(><gs(>i<)>+12345,true<)><}"
						+ "cd{><cx(>i<=5<)><}"
						+ "pr{><ps(>i,<cx(>i+1<)>,int<)><}>"
						));
		Object[] aquOutputArray03 = aqu03.execute();

		System.out.println();
		System.out.println("ActionQueue03.execute: " + ((aquOutputArray03 == null) ? null : ""));
		for (int i = 0; (aquOutputArray03 != null && i < aquOutputArray03.length); i++) {
			Object[] aquOutputArray03i = (Object[]) aquOutputArray03[i];
			for (int j = 0; (aquOutputArray03i != null && j < aquOutputArray03i.length); j++) {
				System.out.println("[" + i + "," + j + "]: " 
						+ (((aquOutputArray03i[j] != null) && aquOutputArray03i[j] instanceof Object[]) 
								? Arrays.asList((Object[]) aquOutputArray03i[j]) : aquOutputArray03i[j]));
			}
		}



		//ActionableExecuteAction
		Action axa01 = new ActionableExecuteAction("<ax(>" + afl01.getId() + "<)>");
		Object[] axaOutputArray01 = axa01.execute();

		System.out.println();
		System.out.println("ActionableExecuteAction01.execute: " 
				+ ((axaOutputArray01 == null) ? null : Arrays.asList((Object[]) axaOutputArray01)));


		Action axa02 = new ActionableExecuteAction("<ax(>" + atp01.getId() + "<)>");
		Object[] axaOutputArray02 = axa02.execute();

		System.out.println();
		System.out.println("ActionableExecuteAction02.execute: " 
				+ ((axaOutputArray02 == null) ? null : Arrays.asList((Object[]) axaOutputArray02)));


		Action axa03 = new ActionableExecuteAction("<ax(>" + aqu01.getId() + "<)>");
		Object[] axaOutputArray03 = axa03.execute();

		System.out.println();
		System.out.println("ActionableExecuteAction03.execute: " + ((axaOutputArray03 == null) ? null : ""));
		for (int i = 0; (axaOutputArray03 != null && i < axaOutputArray03.length); i++) {
			System.out.println("[" + i + "]: " 
					+ ((axaOutputArray03[i] == null) ? null : Arrays.asList((Object[]) axaOutputArray03[i])));
		}


		Action axa04 = new ActionableExecuteAction("<ax(>" + aqu03.getId() + "<)>");
		Object[] axaOutputArray04 = axa04.execute();

		System.out.println();
		System.out.println("ActionableExecuteAction04.execute: " + ((axaOutputArray04 == null) ? null : ""));
		for (int i = 0; (axaOutputArray04 != null && i < axaOutputArray04.length); i++) {
			Object[] axaOutputArray04i = (Object[]) axaOutputArray04[i];
			for (int j = 0; (axaOutputArray04i != null && j < axaOutputArray04i.length); j++) {
				System.out.println("[" + i + "," + j + "]: " 
						+ (((axaOutputArray04i[j] != null) && axaOutputArray04i[j] instanceof Object[]) 
								? Arrays.asList((Object[]) axaOutputArray04i[j]) : axaOutputArray04i[j]));
			}
		}



		Root root = new Root(Informationiverse.getAllActionableElementCollection(), 
				Informationiverse.getAllElementCollection());
		System.out.println();
		root.marshalToXml(System.out);
	}

	public static void doXmlUnmarshal(String clazzName) throws Exception {
		String inputXmlFilePath = "samples/" + clazzName + ".xml";
		URL url = Thread.currentThread().getContextClassLoader().getResource(inputXmlFilePath);
		Root root = Root.unmarshalFromXml(url);
		root.redecorate();
		System.out.println();
		root.marshalToXml(System.out);
	}

}
