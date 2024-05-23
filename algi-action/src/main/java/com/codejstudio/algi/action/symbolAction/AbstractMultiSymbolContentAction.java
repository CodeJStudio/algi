package com.codejstudio.algi.action.symbolAction;

import com.codejstudio.algi.action.symbol.IMultiContentSymbol;
import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.CollectionUtil;
import com.codejstudio.lim.common.util.StringUtil;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   algi_v1.0.0
 */
public abstract class AbstractMultiSymbolContentAction extends AbstractSimpleSurroundingSymbolAction {

	/* constants */

	private static final long serialVersionUID = 5694210986761964967L;


	/* variables */

	protected Object[] multiContentArray;


	/* getters & setters */

	public Object[] getMultiContentArray() {
		return multiContentArray;
	}


	/* overridden methods */

	@Override
	protected boolean makeupContent() throws LIMException {
		if (StringUtil.isEmpty(this.sourceDescription) || !(this.symbol instanceof IMultiContentSymbol)) {
			return false;
		}

		String source = this.sourceDescription;
		boolean flag = this.symbolFlag;
		if (!flag) {
			source = this.symbol.surround(source);
			flag = this.symbol.verifySurroundingSymbol(source);
		}

		final IMultiContentSymbol mcsb = (IMultiContentSymbol) this.symbol;
		final int size = mcsb.getContentSize();
		final Class<?>[] cta = mcsb.getContentTypeArray();
		final boolean[] cna = mcsb.getContentNecessityArray();
		if ((cta.length != cna.length) || (size > 0 && size > cta.length) 
				|| (size <= 0 && Math.abs(size) > cta.length - 1)) {
			return false;
		}

		String strippedContent;
		String[] sca;
		if (flag) {
			strippedContent = this.symbol.strip(source);
			sca = mcsb.splitStrippedContent(strippedContent);
			if (sca != null) {
				if (size > 0 && sca.length > cta.length) {
					return false;
				}

				Object[] mca = new Object[mcsb.isFinite() ? size : sca.length];
				for (int i = 0; i < sca.length; i++) {
					Class<?> ct = (size > 0 || i <= Math.abs(size)) ? cta[i] : cta[Math.abs(size)];
					mca[i] = castContentByType(sca[i], ct);
				}
				this.multiContentArray = mca;
			}
		} else if (verifySingleNecessity(size, cna)) {
			multiContentArray = new Object[size];
			multiContentArray[0] = castContentByType(this.sourceDescription, cta[0]);
		} else {
			return false;
		}

		return true;
	}

	private boolean verifySingleNecessity(final int size, final boolean[] necessityArray) {
		if (size == 1) {
			return true;
		} else if (size > 1) {
			for (int i = 1; (necessityArray.length > 1 && i < necessityArray.length); i++) {
				if (necessityArray[i]) {
					return false;
				}
			}
			return true;
		}
		return false;
	}


	@Override
	protected boolean verifyContent() throws LIMException {
		if (CollectionUtil.checkNullOrEmpty(this.multiContentArray)) {
			return false;
		}

		IMultiContentSymbol mcsb = (IMultiContentSymbol) this.symbol;
		int size = mcsb.getContentSize();
		Class<?>[] cta = mcsb.getContentTypeArray();
		boolean[] cna = mcsb.getContentNecessityArray();
		for (int i = 0; i < this.multiContentArray.length; i++) {
			Class<?> ct = (size > 0 || i <= Math.abs(size)) ? cta[i] : cta[Math.abs(size)];
			boolean necessity = (size > 0 || i <= Math.abs(size)) 
					? cna[i] : cna[Math.abs(size)];
			if ((this.multiContentArray[i] == null && necessity) 
					|| (this.multiContentArray[i] != null && !ct.isInstance(this.multiContentArray[i]))) {
				return false;
			}
		}
		return true;
	}

}
