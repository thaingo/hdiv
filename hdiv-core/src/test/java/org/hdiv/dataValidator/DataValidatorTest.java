/**
 * Copyright 2005-2013 hdiv.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hdiv.dataValidator;

import org.hdiv.AbstractHDIVTestCase;
import org.hdiv.state.IParameter;
import org.hdiv.state.Parameter;

/**
 * Unit tests for the <code>org.hdiv.dataValidator.DataValidator</code> class.
 * 
 * @author Gorka Vicente
 */
public class DataValidatorTest extends AbstractHDIVTestCase {

	protected IDataValidator dataValidator;

	protected void onSetUp() throws Exception {

		this.dataValidator = this.getApplicationContext().getBean(IDataValidator.class);
	}

	/**
	 * Validation test with a noneditable parameter. It should not pass the validation as the received value is not an
	 * integer.
	 */
	public void testValidateDataIsNotInt() {

		// XXX String editableDataType, boolean actionParam no estaban definidos
		IParameter param1 = new Parameter("param1", "value1", false, null, false);

		IValidationResult result = dataValidator.validate("dataIsNotInt", "simpleAction", "param1", param1, null);
		assertFalse(result.getLegal());
	}

//	/**
//	 * Validation test with a noneditable parameter. It should not pass the validation as the received parameter doesn't
//	 * exists.
//	 */
//	public void testValidateParameterDoesNotExist() {
//
//		IParameter param1 = new Parameter("param1", "value1", false, null, false);
//
//		boolean confidentiality = this.getConfig().getConfidentiality();
//		String value = (confidentiality) ? "0" : "value1";
//		try {
//			IValidationResult result = dataValidator.validate(value, "simpleAction", "parameterDoesNotExist", param1,
//					null);
//			assertFalse(result != null);
//		} catch (NullPointerException e) {
//			assertTrue(true);
//			return;
//		}
//		assertFalse(true);
//	}

	/**
	 * Validation test with a noneditable parameter. It should not pass the validation as the received parameter doesn't
	 * exists.
	 */
	public void testValidatePositionDoesNotExist() {

		IParameter param1 = new Parameter("param1", "value1", false, null, false);

		IValidationResult result = dataValidator.validate("1", "simpleAction", "param1", param1, null);
		assertFalse(result.getLegal());
	}

	/**
	 * Validation test with a noneditable parameter. The validation is correct.
	 */
	public void testValidateCorrectData() {

		IParameter param1 = new Parameter("param1", "value1", false, null, false);

		boolean confidentiality = this.getConfig().getConfidentiality();
		String value = (confidentiality) ? "0" : "value1";
		IValidationResult result = dataValidator.validate(value, "simpleAction", "param1", param1, null);

		assertEquals(((String) result.getResult()), "value1");
		assertTrue(result.getLegal());
	}
	
	public void testValidateActionParams() {

		String[] values = new String[]{"value1"};

		boolean confidentiality = this.getConfig().getConfidentiality();
		String value = (confidentiality) ? "0" : "value1";
		IValidationResult result = dataValidator.validate(value, "simpleAction", "param1", null, values);

		assertEquals(((String) result.getResult()), "value1");
		assertTrue(result.getLegal());
	}
}