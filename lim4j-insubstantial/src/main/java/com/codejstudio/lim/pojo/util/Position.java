package com.codejstudio.lim.pojo.util;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

import com.codejstudio.lim.common.exception.LIMException;
import com.codejstudio.lim.common.util.StringUtil;
import com.codejstudio.lim.common.util.CaseFormatUtil.WordSeparator;
import com.codejstudio.lim.pojo.BaseElement;
import com.codejstudio.lim.pojo.i.ICloneable;

/**
 * @Description TODO
 * 
 * @author <ul><li>Jeffrey Jiang</li></ul>
 * @see     
 * @since   lim4j_v2.0.0
 */
public class Position implements ICloneable, Serializable {

	/* constants */

	private static final long serialVersionUID = -5939492549717142533L;

	public static final String POSITION_SEPARATOR = "" + WordSeparator.COMMA.getCharacter();


	/* variables */

	private Integer index;

	private Integer length;


	/* constructors */

	public Position(Integer index, Integer length) throws LIMException {
		if (index == null || index < 0 || length == null || length < 0) {
			throw new LIMException(new IllegalArgumentException());
		}

		this.index = index;
		this.length = length;
	}

	public Position(String positionInfo) throws LIMException {
		String[] psia;
		if (positionInfo == null || (psia = positionInfo.split(POSITION_SEPARATOR)).length != 2) {
			throw new LIMException(new IllegalArgumentException());
		}

		this.index = StringUtil.integerValue(psia[0]);
		this.length = StringUtil.integerValue(psia[1]);
	}


	/* getters & setters */

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer[] getIndexAndLength() {
		return new Integer[]{this.index, this.length};
	}

	public String getPositionInfo() {
		return (this.index == null || this.length == null) ? null : toString();
	}


	/* overridden methods */

	@Override
	public final String toString() {
		return "" + this.index + POSITION_SEPARATOR + this.length;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.index, this.length);
	}

	@Override
	public boolean equals(final Object object) {
		if (this == object) {
			return true;
		}
		if (object == null) {
			return false;
		}
		if (getClass() != object.getClass()) {
			return false;
		}
		Position ps = (Position) object;
		return Objects.equals(this.index, ps.index) && Objects.equals(this.length, ps.length);
	}


	@Override
	public Position cloneElement(final Map<String, BaseElement> clonedElementMap) 
			throws LIMException {
		Position clonedElement;
		try {
			clonedElement = (Position) this.getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw LIMException.getLIMException(e);
		}

		clonedElement.index = this.index;
		clonedElement.length = this.length;

		return clonedElement;
	}

}
