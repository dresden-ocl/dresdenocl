/*
 * Copyright (C) 2010 by Claas Wilke (claaswilke@gmx.net) This file is part of
 * the Java Meta Model of Dresden OCL2 for Eclipse. Dresden OCL2 for Eclipse is
 * free software: you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version. Dresden OCL2 for Eclipse is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser
 * General Public License for more details. You should have received a copy of
 * the GNU Lesser General Public License along with Dresden OCL2 for Eclipse. If
 * not, see <http://www.gnu.org/licenses/>.
 */
package package1.package2.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * A Java class that can be used as a model to test the adaptation of
 * Collections.
 * </p>
 * 
 * @author Claas Wilke
 * @author Michael Thiele
 */
public class CollectionTestModel {

	/** Should be adapted to CollectionType<CollectionTestModel>. */
	public Collection<CollectionTestModel> getCollection() {
		return new ArrayList<CollectionTestModel>();
	}

	/** Should be adapted to SequenceType<CollectionTestModel>. */
	public List<CollectionTestModel> getList() {
		return new ArrayList<CollectionTestModel>();
	}

	/** Should be adapted to SetType<CollectionTestModel>. */
	public Set<CollectionTestModel> getSet() {
		return new HashSet<CollectionTestModel>();
	}

	/** Should be adapted to SequenceType<CollectionTestModel>. */
	public CollectionTestModel[] getArray() {
		return new CollectionTestModel[0];
	}

	/**
	 * Should be adapted to CollectionType<CollectionType<CollectionTestModel>>.
	 */
	public Collection<Collection<CollectionTestModel>> getCollectionCollection() {
		return new ArrayList<Collection<CollectionTestModel>>();
	}

	/**
	 * Should be adapted to
	 * CollectionType<SequenceType<SetType<CollectionTestModel>>>.
	 */
	public Collection<List<Set<CollectionTestModel>>> getCollectionListSet() {
		return new ArrayList<List<Set<CollectionTestModel>>>();
	}
	
	/**
	 * Should be adapted to
	 * CollectionType<SequenceType<CollectionTestModel>>.
	 */
	public Collection<CollectionTestModel[]> getCollectionArray() {
		return new ArrayList<CollectionTestModel[]>();
	}

	/**
	 * Should be adapted to
	 * SequenceType<CollectionType<CollectionTestModel>>.
	 */
	public Collection<CollectionTestModel>[] getArrayCollection() {
		return null;
	}
}