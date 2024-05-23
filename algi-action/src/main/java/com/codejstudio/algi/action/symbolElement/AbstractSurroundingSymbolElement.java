package com.codejstudio.algi.action.symbolElement;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codejstudio.algi.action.symbol.ISurroundingSymbol;
import com.codejstudio.algi.action.symbol.ISurroundingSymbolCombination;
import com.codejstudio.algi.action.symbolAction.SymbolAction;
import com.codejstudio.algi.common.exception.ALGIException;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.StringUtil;
import com.codejstudio.lim.pojo.i.ISession;
import com.codejstudio.lim.pojo.i.ISymbol;
import com.codejstudio.lim.pojo.i.ISymbolCombination;
import com.codejstudio.lim.pojo.i.ISymbolElement;
import com.codejstudio.lim.pojo.util.InputParameter;
import com.codejstudio.lim.pojo.util.Position;
import com.codejstudio.lim.pojo.util.SymbolUtil;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public abstract class AbstractSurroundingSymbolElement extends AbstractSymbolElement {

	/* constants */

	static final Logger log = LoggerFactory.getLogger(AbstractSurroundingSymbolElement.class);

	private static final long serialVersionUID = 1404993154593668915L;


	/* variables */

	protected ISurroundingSymbolCombination symbolCombination;

	protected Class<? extends SymbolAction> actionClazz;

	protected SymbolAction action;

	protected String strippedContent;


	/* variables: arrays, collections, maps, groups */

	protected String[] sourceFragmentArray;

	protected String[] sourceSymbolFragmentArray;

	protected String[] outsideOfSymbolArray;

	private List<ISymbolElement> subSymbolElementList;

	private List<Position> subSymbolElementPositionList;


	/* initializers */

	private void initSubSymbolElementList() throws LIMException {
		if (this.subSymbolElementList == null) {
			this.subSymbolElementList = CollectionUtil.generateNewList();
		}
	}

	private void initSubSymbolElementPositionList() throws LIMException {
		if (this.subSymbolElementPositionList == null) {
			this.subSymbolElementPositionList = CollectionUtil.generateNewList();
		}
	}


	protected void init(final String source, final Class<? extends SymbolAction> actionClazz, 
			final ISurroundingSymbol surroundingSymbol) throws LIMException {
		if (StringUtil.isEmpty(source) || !(surroundingSymbol instanceof ISymbol)) {
			throw new ALGIException(new IllegalArgumentException());
		}
		this.source = source;
		this.actionClazz = actionClazz;
		this.symbol = (surroundingSymbol instanceof ISymbol) ? (ISymbol) surroundingSymbol : this.symbol;
		this.strippedContent = surroundingSymbol.strip(source);

		initSymbolContent(this.strippedContent);
		initSubSymbolContent();
	}

	protected void initSymbolContent(final String symbolContent) throws LIMException {
		String[][] arrayOfFragmentArrays = SymbolUtil.analyzeSymbolContent(symbolContent);
		if (CollectionUtil.checkNullOrEmpty(arrayOfFragmentArrays) || arrayOfFragmentArrays.length != 3) {
			throw new ALGIException(new IllegalArgumentException());
		}
		this.sourceFragmentArray = arrayOfFragmentArrays[0];
		this.sourceSymbolFragmentArray = arrayOfFragmentArrays[2];
	}

	private void initSubSymbolContent() throws LIMException {
		this.outsideOfSymbolArray = new String[2];
		initSubSymbolElementList();
		initSubSymbolElementPositionList();
		if (!generateSubSymbolElements(this.sourceFragmentArray, this.outsideOfSymbolArray, 
				this.getSubSymbolElementList(), this.getSubSymbolElementPositionList())) {
			throw new ALGIException(new IllegalArgumentException());
		}
	}


	protected void initAction(final SymbolAction symbolAction) throws LIMException {
		try {
			this.action = (symbolAction != null) ? symbolAction 
					: ((this.action == null && this.actionClazz != null) 
							? this.actionClazz.newInstance() : this.action);
			if (this.action != null) {
				this.action.setSymbolElement(this);
				this.action.setSymbolCombination(this.symbolCombination);
				this.action.setSourceDescription(this.source);
			}
		} catch (Exception e) {
			throw ALGIException.getALGIException(e);
		}
	}


	/* getters & setters */

	public Class<? extends SymbolAction> getActionClass() {
		return actionClazz;
	}

	public String getStrippedContent() {
		return strippedContent;
	}

	public String[] getSourceFragmentArray() {
		return sourceFragmentArray;
	}

	public String[] getSourceSymbolFragmentArray() {
		return sourceSymbolFragmentArray;
	}

	public String[] getOutsideOfSymbolArray() {
		return outsideOfSymbolArray;
	}

	public List<ISymbolElement> getSubSymbolElementList() {
		return subSymbolElementList;
	}

	public List<Position> getSubSymbolElementPositionList() {
		return subSymbolElementPositionList;
	}


	/* overridden methods */

	@Override
	public Object[] executeAction(final ISession session, final InputParameter... inputParameters) 
			throws LIMException {
		try {
			initAction(null);
			return this.action.execute(session, inputParameters);
		} catch (Exception e) {
			log.error(e.toString(), e);
		}
		return null;
	}


	/* static methods */

	public static boolean generateSubSymbolElements(final String[] allFragmentArray, 
			String[] outsideOfSymbolArray, List<ISymbolElement> subSymbolElementList, 
			List<Position> subSymbolElementPositionList) throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(allFragmentArray) 
				) {
			return false;
		}

		int symbolCombinationIndex = 0;
		int first = -1, last = 0;
		int surroundingSymbolLevel = 0;
		Stack<Integer> symbolCombinationIndexStack = new Stack<>();
		boolean stackPeekFlag = false;
		List<Integer> allFragmentIndexList = CollectionUtil.generateNewList();
		List<Integer> surroundingSymbolLevelList = CollectionUtil.generateNewList();
		List<List<ISurroundingSymbolCombination>> surroundingSymbolCombinationsList 
				= CollectionUtil.generateNewList();
		List<Integer> symbolCombinationIndexList = CollectionUtil.generateNewList();
		for (int i = 0; i < allFragmentArray.length; i++) {
			ISymbolCombination[] sca = SymbolUtil
					.verifyAndGetSymbolCombinations(allFragmentArray[i], symbolCombinationIndex);
			List<ISurroundingSymbolCombination> sscl;
			if (!CollectionUtil.checkNullOrEmpty(sca) 
					&& !CollectionUtil.checkNullOrEmpty(sscl = CollectionUtil
							.convertToList(ISurroundingSymbolCombination.class, sca))) {
				if (stackPeekFlag && !symbolCombinationIndexStack.empty() 
						&& (symbolCombinationIndex == symbolCombinationIndexStack.peek())) {
					symbolCombinationIndexStack.pop();
					stackPeekFlag = false;
				}
				List<ISurroundingSymbolCombination> currentSscl = null;
				List<ISurroundingSymbolCombination> tempSscl = null;
				if (symbolCombinationIndex > 0) {
					List<Integer> tempSsll = CollectionUtil.copyList(surroundingSymbolLevelList);
					int index;
					while ((index = tempSsll.lastIndexOf(surroundingSymbolLevel)) >= 0) {
						List<ISurroundingSymbolCombination> lastSscl 
								= surroundingSymbolCombinationsList.get(index);
						if (lastSscl != null) {
							if (currentSscl == null) {
								Collection<ISurroundingSymbolCombination> mutualSscc 
										= CollectionUtil.getIntersection(lastSscl, sscl);
								currentSscl = (!CollectionUtil.checkNullOrEmpty(mutualSscc)) 
										? (List<ISurroundingSymbolCombination>) mutualSscc : currentSscl;
								tempSscl = currentSscl;
							}
							if (currentSscl != null) {
								Collection<ISurroundingSymbolCombination> exclusiveSscc 
										= CollectionUtil.getRelativeComplement(lastSscl, currentSscl);
								lastSscl.removeAll(exclusiveSscc);
							}
						}
						if (symbolCombinationIndexList.get(index) == 0) {
							break;
						}
						tempSsll.remove(index);
					}
				} else {
					surroundingSymbolLevel++;
					first = (first < 0) ? i : first;
				}

				if (currentSscl == null) {
					currentSscl = CollectionUtil.copyList(sscl);
					symbolCombinationIndex = 0;
				}

				allFragmentIndexList.add(i);
				surroundingSymbolLevelList.add(surroundingSymbolLevel);
				surroundingSymbolCombinationsList.add(currentSscl);
				symbolCombinationIndexList.add(symbolCombinationIndex);

				if (tempSscl != null 
						&& ((ISymbolCombination) tempSscl.get(0)).lastSingleSymbol(allFragmentArray[i])) {
					surroundingSymbolLevel--;
					symbolCombinationIndex = 0;
				} else {
					symbolCombinationIndex++;
				}
				if (surroundingSymbolLevel < 0) {
					return false;
				} else if (surroundingSymbolLevel == 0) {
					last = i;
				}
			} else {
				ISymbolCombination[] tempSca 
						= SymbolUtil.verifyAndGetSymbolCombinations(allFragmentArray[i]);
				if (!CollectionUtil.checkNullOrEmpty(tempSca) 
						&& tempSca[0] instanceof ISurroundingSymbolCombination) {
					if (tempSca[0].firstSingleSymbol(allFragmentArray[i])) {
						symbolCombinationIndexStack.push(symbolCombinationIndex);
						symbolCombinationIndex = 0;
					} else if (tempSca[0].lastSingleSymbol(allFragmentArray[i])) {
						symbolCombinationIndex = symbolCombinationIndexStack.empty() 
								? 0 : symbolCombinationIndexStack.pop();
					} else {
						symbolCombinationIndex = symbolCombinationIndexStack.empty() 
								? 0 : symbolCombinationIndexStack.peek();
						stackPeekFlag = true;
					}
					i--;
				}
			}
		}
		if (surroundingSymbolLevel > 0 || allFragmentIndexList.size() != surroundingSymbolLevelList.size() 
				|| surroundingSymbolLevelList.size() != surroundingSymbolCombinationsList.size() 
				|| surroundingSymbolCombinationsList.size() != symbolCombinationIndexList.size()) {
			return false;
		}

		generateOutsideOfSymbolArray(allFragmentArray, first, last, outsideOfSymbolArray);

		return generateSubSourceOfSymbolCombinations(allFragmentArray, allFragmentIndexList, 
				surroundingSymbolLevelList, surroundingSymbolCombinationsList, symbolCombinationIndexList, 
				subSymbolElementList, subSymbolElementPositionList);
	}

	private static void generateOutsideOfSymbolArray(final String[] allFragmentArray, final int first, 
			final int last, String[] outsideOfSymbolArray) {
		if (outsideOfSymbolArray == null || first < 0 || outsideOfSymbolArray.length < 2) {
			return;
		}

		outsideOfSymbolArray[0] = (first > 0) 
				? String.join("", Arrays.copyOfRange(allFragmentArray, 0, first)) 
				: outsideOfSymbolArray[0];
		outsideOfSymbolArray[1] = (last < allFragmentArray.length - 1) 
				? String.join("", Arrays.copyOfRange(allFragmentArray, last + 1, allFragmentArray.length)) 
				: outsideOfSymbolArray[1];
	}

	private static boolean generateSubSourceOfSymbolCombinations(final String[] allFragmentArray, 
			final List<Integer> allFragmentIndexList, final List<Integer> surroundingSymbolLevelList, 
			final List<List<ISurroundingSymbolCombination>> surroundingSymbolCombinationsList, 
			final List<Integer> symbolCombinationIndexList, List<ISymbolElement> subSymbolElementList, 
			List<Position> subSymbolElementPositionList) throws LIMException {
		ISurroundingSymbolCombination firstLevelSsc = null;
		int from = -1;
		List<ISurroundingSymbolCombination> surroundingSymbolCombinationList 
				= CollectionUtil.generateNewList();
		List<String> subSourceList = CollectionUtil.generateNewList();
		List<Position> subSourcePositionList = CollectionUtil.generateNewList();
		for (int i = 0; i < surroundingSymbolCombinationsList.size(); i++) {
			List<ISurroundingSymbolCombination> sscl = surroundingSymbolCombinationsList.get(i);
			if (CollectionUtil.checkNullOrEmpty(sscl)) {
				continue;
			}

			if (firstLevelSsc == null) {
				firstLevelSsc = sscl.get(0);
			}
			int length;
			if (firstLevelSsc == null || !(firstLevelSsc instanceof ISymbolCombination) 
					|| (length = ((ISymbolCombination) firstLevelSsc).length()) <= 1) {
				return false;
			}

			boolean flag = false;
			for (ISurroundingSymbolCombination issc : sscl) {
				if (firstLevelSsc.equals(issc)) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				continue;
			}

			int ssl = surroundingSymbolLevelList.get(i);
			int afi = allFragmentIndexList.get(i);
			int sci = symbolCombinationIndexList.get(i);
			int el = ((ISymbolCombination) firstLevelSsc).extraLength();
			if (from >= 0 && ssl == 1 
					&& ((el == 0) ? (sci == length - 1) 
							: ((sci >= length - 1) && ((sci - length + 1) % el == 0) 
									&& firstLevelSsc.lastSingleSymbol(allFragmentArray[afi])))) {
				surroundingSymbolCombinationList.add(firstLevelSsc);
				subSourceList.add(String.join("", Arrays.copyOfRange(allFragmentArray, from, afi + 1)));
				subSourcePositionList.add(new Position(from, (afi + 1 - from)));
				firstLevelSsc = null;
				from = -1;
			} else {
				from = (from < 0) ? afi : from;
			}
		}

		return generateSubSymbolElements(surroundingSymbolCombinationList, subSourceList, 
				subSourcePositionList, subSymbolElementList, subSymbolElementPositionList);
	}

	private static boolean generateSubSymbolElements(
			final List<ISurroundingSymbolCombination> surroundingSymbolCombinationList, 
			final List<String> subSourceList, final List<Position> subSourcePositionList, 
			List<ISymbolElement> subSymbolElementList, List<Position> subSymbolElementPositionList) {
		for (int i = 0; i < surroundingSymbolCombinationList.size(); i++) {
			ISurroundingSymbolCombination ssc = surroundingSymbolCombinationList.get(i);
			ISymbolElement se = ssc.generateSymbolElement(subSourceList.get(i));
			if (se == null) {
				continue;
			}
			if (subSymbolElementList != null) {
				subSymbolElementList.add(se);
			}
			if (subSymbolElementPositionList != null) {
				subSymbolElementPositionList.add(subSourcePositionList.get(i));
			}
		}

		return true;
	}

}
