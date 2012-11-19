/*
 * Copyright (C) 2012 by Lars Schuetze (laze1989@gmail.com)
 * This file is part of the UML2 Meta Model of Dresden OCL. Dresden
 * OCL is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version. Dresden OCL is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
 * the GNU Lesser General Public License for more details. You should have
 * received a copy of the GNU Lesser General Public License along with Dresden
 * OCL. If not, see <http://www.gnu.org/licenses/>.
 */
package org.dresdenocl.model.util;

public final class Tuple2<A, B> {

	private A m_firstValue;
	private B m_secondValue;

	public Tuple2(final A first, final B second) {

		this.m_firstValue = first;
		this.m_secondValue = second;
	}

	public A getFirstValue() {

		return this.m_firstValue;
	}

	public B getSecondValue() {

		return this.m_secondValue;
	}

	public void setFirstValue(final A newValue) {

		this.m_firstValue = newValue;
	}

	public void setSecondValue(final B newValue) {

		this.m_secondValue = newValue;
	}
}
