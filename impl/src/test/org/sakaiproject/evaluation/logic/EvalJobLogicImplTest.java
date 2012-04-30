/**
 * Copyright 2005 Sakai Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package org.sakaiproject.evaluation.logic;

import junit.framework.Assert;

import org.sakaiproject.evaluation.constant.EvalConstants;
import org.sakaiproject.evaluation.logic.scheduling.EvalJobLogicImpl;
import org.sakaiproject.evaluation.test.EvalTestDataLoad;

/**
 * FIXME test the rest of the methods -AZ
 * 
 * @author Dick Ellis (rwellis@umich.edu)
 */
public class EvalJobLogicImplTest extends BaseTestEvalLogic {
	
	protected EvalJobLogicImpl jobLogic;
   private EvalEvaluationService evaluationService;

	// run this before each test starts
	protected void onSetUpBeforeTransaction() throws Exception {
      super.onSetUpBeforeTransaction();

      // load up any other needed spring beans
      EvalSettings settings = (EvalSettings) applicationContext.getBean("org.sakaiproject.evaluation.logic.EvalSettings");
      if (settings == null) {
         throw new NullPointerException("EvalSettings could not be retrieved from spring context");
      }

      evaluationService = (EvalEvaluationService) applicationContext.getBean("org.sakaiproject.evaluation.logic.EvalEvaluationService");
      if (evaluationService == null) {
         throw new NullPointerException("EvalEvaluationService could not be retrieved from spring context");
      }

      // setup the mock objects if needed
      EvalEmailsLogicImpl emailsLogicImpl = new EvalEmailsLogicImpl();
      emailsLogicImpl.setEvaluationService(evaluationService);
      emailsLogicImpl.setCommonLogic(commonLogic);
      emailsLogicImpl.setSettings(settings);
		
		//create and setup the object to be tested
		jobLogic = new EvalJobLogicImpl();
		jobLogic.setEmails(emailsLogicImpl);
		jobLogic.setEvaluationService(evaluationService);
		jobLogic.setCommonLogic(commonLogic);
		jobLogic.setSettings(settings);
		// FIXME set the remaining dependencies
		
	}
	
	// run this before each test starts and as part of the transaction
	protected void onSetUpInTransaction() {
		// preload additional data if desired
		
	}
	
	/**
	 * Test method for {@link EvalJobLogicImpl#isValidJobType(String)}
	 */
	public void testIsValidJobType() {
		//each valid type returns true
		Assert.assertTrue( EvalJobLogicImpl.isValidJobType(EvalConstants.JOB_TYPE_ACTIVE));
		Assert.assertTrue( EvalJobLogicImpl.isValidJobType(EvalConstants.JOB_TYPE_CLOSED));
		Assert.assertTrue( EvalJobLogicImpl.isValidJobType(EvalConstants.JOB_TYPE_CREATED));
		Assert.assertTrue( EvalJobLogicImpl.isValidJobType(EvalConstants.JOB_TYPE_DUE));
		Assert.assertTrue( EvalJobLogicImpl.isValidJobType(EvalConstants.JOB_TYPE_REMINDER));
		Assert.assertTrue( EvalJobLogicImpl.isValidJobType(EvalConstants.JOB_TYPE_VIEWABLE));
		Assert.assertTrue( EvalJobLogicImpl.isValidJobType(EvalConstants.JOB_TYPE_VIEWABLE_INSTRUCTORS));
		Assert.assertTrue( EvalJobLogicImpl.isValidJobType(EvalConstants.JOB_TYPE_VIEWABLE_STUDENTS));
		//invalid or "" type returns false
		Assert.assertFalse( EvalJobLogicImpl.isValidJobType(EvalTestDataLoad.INVALID_CONSTANT_STRING));
		Assert.assertFalse( EvalJobLogicImpl.isValidJobType(new String("")));
		//null type retuns false
		Assert.assertFalse( EvalJobLogicImpl.isValidJobType(null));
	}

	// FIXME add in the remaining test cases

}