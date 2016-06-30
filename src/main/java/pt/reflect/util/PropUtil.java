package pt.reflect.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import pt.reflect.model.Book;
import pt.reflect.model.City;
import pt.reflect.model.Prop;
import pt.reflect.model.User;

/***
 * 动态递归解析字段属性
 * @author ligson
 *
 */
public class PropUtil {

	public static Prop getProp(String propName, Object instance, Class clazz) {
		String[] props = propName.split("\\.");
		if (props[0].equals("*")) {
			Prop prop = new Prop();
			prop.setClazz(clazz);
			prop.setValue(instance);
			prop.setField(null);
			return prop;
		}
		try {
			
			String prop = props[0].substring(0, 1).toUpperCase() + props[0].substring(1);
			Field field = clazz.getDeclaredField(props[0]);
			Method method = clazz.getDeclaredMethod("get" + prop);
			Object value = method.invoke(instance);
			Prop prop2 = new Prop();
			prop2.setField(field);
			prop2.setClazz(clazz);
			prop2.setValue(value);
			if (value == null) {
				return prop2;
			}
			if (props.length == 1) {
				return prop2;
			} else {

				String tmpProp = "";
				for (int i = 1; i < props.length; i++) {
					tmpProp += props[i];
					if (i != props.length - 1) {
						tmpProp += ".";
					}
				}
				return getProp(tmpProp, value, field.getType());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		String prop = "author.city.*";
		City city = new City();
		city.setName("北京");
		User user = new User();
		user.setName("习近平");
		user.setCity(city);
		Book book = new Book();
		book.setName("《中国现代化建设之路》");
		book.setAuthor(user);

		Prop field = getProp(prop, book, book.getClass());
		System.out.println(field);
	}
}
