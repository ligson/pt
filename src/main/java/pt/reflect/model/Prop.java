package pt.reflect.model;

import java.lang.reflect.Field;

public class Prop {
	private Field field;
	private Class clazz;
	private Object value;
	public Field getField() {
		return field;
	}
	public void setField(Field field) {
		this.field = field;
	}
	public Class getClazz() {
		return clazz;
	}
	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "Prop [field=" + field + ", clazz=" + clazz + ", value=" + value + "]";
	}
	
	
}
