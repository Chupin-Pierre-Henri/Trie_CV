package fr.univ_lyon1.info.m1.cv_search.view;

import java.util.ArrayList;
import java.util.List;

public class Request implements Component{
	private List<Component> components;
	
	public Request(){
		this.components = new ArrayList<Component>();
	}

	@Override
	public boolean isFilter() {
		return false;
	}

	@Override
	public boolean isSkill() {
		return false;
	}

	/**
	 *
	 * @param c a Component to add in List's Request
	 * @throws RequestAddExeption if Component c is not skill or filter
	 */
	public void addComponent(Component c) throws RequestAddExeption{
		if (c.isFilter() || c.isSkill()){
			components.add(c);
			return;
		}
		throw new RequestAddExeption("Wrong component");
	}
	
	public List<Component> getComponent(){
		return this.components;
	}
}
