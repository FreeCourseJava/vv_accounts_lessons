package org.homework.di;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Set;

public class Injector {

    private HashMap<String, Object> services = new HashMap<>();

    public void createServices() throws InvocationTargetException, InstantiationException, IllegalAccessException {
        Reflections reflections = new Reflections("org.homework");
        for (Class service : reflections.getTypesAnnotatedWith(Service.class)) {
            createService(service).getClass();
        }
    }

    private Object createService(Class service) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        String serviceName = service.getName();
        if (!services.containsKey(serviceName)) {
            //System.out.println("===========Creating obj of class" + service.getName());
            Constructor[] constructors = service.getConstructors();
            if (constructors.length == 0) {
                //System.out.println("No constructors");
                throw new InstantiationException("No constructors defined for the class " + serviceName);
            } else {
                Constructor constructor = constructors[0];
                //System.out.println(constructor);
                Parameter[] parameters = constructor.getParameters();
                Object[] constructorParameters = new Object[parameters.length];
                for (int i = 0; i < parameters.length; i++) {

                    Parameter parameter = parameters[i];

                    //System.out.println(parameter.getType());
                    if (parameter.getType().isAnnotationPresent(Service.class)) {
                        constructorParameters[i] = createService(parameter.getType());
                    } else if (parameter.isAnnotationPresent(DefaultValue.class)) {
                        constructorParameters[i] = parameter.getAnnotation(DefaultValue.class).value();
                    }

                }
                services.put(serviceName, constructor.newInstance(constructorParameters));
            }

            System.out.println("\n");
        }
        return services.get(serviceName);

    }

    public void start() throws InvocationTargetException, IllegalAccessException {

        //System.out.println("=====================");
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forPackage("org.homework"))
                .setScanners(new MethodAnnotationsScanner()));
            Set<Method> methods = reflections.getMethodsAnnotatedWith(Genesis.class);
            if (methods.size() != 1 ) {
                System.out.println("application can't be stared - zero or more than one methods annotated with the Genesis annotation");
                System.out.println(methods.size());
            }
            else {

                Method startMethod = (Method) methods.toArray()[0];
                String serviceName = startMethod.getDeclaringClass().getName();
                //System.out.println(serviceName);
                Object obj = services.get(serviceName);
                startMethod.invoke(obj);

            }
        }

}
