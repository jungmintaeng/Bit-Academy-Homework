package com.cafe24.mysite.action.guestbook;

import com.cafe24.mvc.action.AbstractActionFactory;
import com.cafe24.mvc.action.Action;

public class GuestBookActionFactory extends AbstractActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;

		if("deleteform".equals(actionName)) {
			action = new DeleteFormAction();
		} else if ("add".equals(actionName)) {
			action = new AddAction();
		} else if ("delete".equals(actionName)) {
			action = new DeleteAction();
		} else {	//index.jsp	//
			action = new ListAction();
		}
		
		return action;
	}

}
