package org.homework.di;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Set;

public class Injector {

    private Injector() {
    }

    private final static HashMap<String, Object> services = new HashMap<>();

    public static void createServices() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Reflections reflections = new Reflections("org.homework");
        for (Class service : reflections.getTypesAnnotatedWith(Service.class)) {
            createService(service);
        }
    }

    private static Object createService(Class service) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        String serviceName = service.getName();
        if (!services.containsKey(serviceName)) {
            Constructor[] constructors = service.getConstructors();
            if (constructors.length == 0) {
                throw new InstantiationException("No constructors defined for the class " + serviceName);
            } else {
                Constructor constructor = constructors[0];
                Parameter[] parameters = constructor.getParameters();
                Object[] constructorParameters = new Object[parameters.length];
                for (int i = 0; i < parameters.length; i++) {
                    Parameter parameter = parameters[i];
                    if (parameter.getType().isAnnotationPresent(Service.class)) {
                        constructorParameters[i] = createService(parameter.getType());
                    } else if (parameter.isAnnotationPresent(DefaultValue.class)) {
                        constructorParameters[i] = parameter.getAnnotation(DefaultValue.class).value();
                    } else {
                        throw new InstantiationException("Can't guess how to create the class: " + serviceName);
                    }

                }
                services.put(serviceName, constructor.newInstance(constructorParameters));
            }
        }
        return services.get(serviceName);
    }

    public static void start() throws InvocationTargetException, IllegalAccessException {
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage("org.homework"))
                .setScanners(Scanners.MethodsAnnotated));
        Set<Method> methods = reflections.getMethodsAnnotatedWith(Genesis.class);
        if (methods.size() != 1) {
            System.err.println("application can't be stared - zero or more than one methods annotated with the Genesis annotation");
        } else {
            Method startMethod = (Method) methods.toArray()[0];
            String serviceName = startMethod.getDeclaringClass().getName();
            Object service = services.get(serviceName);
            startMethod.invoke(service);
        }
    }

}
