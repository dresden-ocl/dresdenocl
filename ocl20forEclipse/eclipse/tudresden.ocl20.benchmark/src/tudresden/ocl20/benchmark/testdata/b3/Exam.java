/*
Copyright (C) 2009 by Franz Eichhorn (franz.eichhorn@gmx.de)

This file is part of the OCL Interpreter Test Suite of Dresden OCL2 for
Eclipse.

Dresden OCL2 for Eclipse is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL2 for Eclipse is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>.
*/
package tudresden.ocl20.benchmark.testdata.b3;

public class Exam {
	public Person examiner;
	public Person examinee;
	public Person recorder;
	
	public int date;
	
	/**
	 * Instantiates a new exam.
	 * 
	 * @param examinee 
	 * @param examiner 
	 * @param recorder 
	 * @param date 
	 */
	public Exam(Person examinee, Person examiner, Person recorder, int date)
	{
		this.examiner = examiner;
		this.examinee = examinee;
		this.recorder = recorder;
		this.date = date;
	}
	
	
}
