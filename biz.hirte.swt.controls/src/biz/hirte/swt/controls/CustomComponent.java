package biz.hirte.swt.controls;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Composite;

/**
 * CustomComponent is supposed to be the base class of every own custom widget.
 * The idea of CustomComponent is to be as atomic to values of type T as Textbox is to String.
 * In this sense the CustomComponent has a model of type T that contains all the
 * data needed to render the component. And user interaction with the component should directly
 * reflect in changes to the model. Changes to the Model should then be forwarded to the 
 * {@link ValueChangeListener} via {@link #notifyValueChangeListener(Object)}.
 * 
 * @author hirte
 *
 * @param <T>
 */
public class CustomComponent<T> extends Composite {

	protected T model;

	private List<ValueChangeListener<T>> changeListener = new ArrayList<ValueChangeListener<T>>();

	public CustomComponent(T t, Composite parent, int style) {
		super(parent, style);
		this.model = t;
	}

	public void addValueChangeListener(ValueChangeListener<T> listener) {
		this.changeListener.add(listener);
	}

	public void removeValueChangeListener(ValueChangeListener<T> l) {
		this.changeListener.remove(l);
	}

	protected void notifyValueChangeListener(T t) {
		this.changeListener.forEach(l -> l.valueChanged(t));
	}

	public T getModel() {
		return model;
	}

	public void setModel(T model) {
		this.model = model;
	}

}
