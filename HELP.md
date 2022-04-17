    //execution(static * com.xyz..*.*(..)) – выполнение кода любого статического метода в пакете com.xyz;

    //call(void MyInterface.*(..)) – вызов любого метода, возвращающего void, интерфейса MyInterface;

    //initialization(MyClass || MyOtherClass) – инициализация класса MyClass или MyOtherClass;

    //staticinitialization(MyClass+ && !MyClass) – статическая инициализация класса, имя которого начинается на MyClass, но не сам MyClass;

    //handler(ArrayOutOfBoundsException) – выполнение обработчика исключения ArrayOutOfBoundsException;

    //get/set(static int MyClass.x) — чтение / запись свойства x класса MyClass;

    //this/target(MyClass) – выполнение точки соединения, соответствующей объекту типа MyClass;

    //args(Integer) – выполнение точки соединения, в которой доступен аргумент типа Integer;

    //if(thisJoinPoint.getKind().equals(«call»)) – совпадает со всеми точками соединения, в которых заданное выражение истинно;

    //within/withincode(MyClass) — совпадает со всеми точками соединения, встречающимися в коде заданного класса;

    //cflow/cflowbelow(call(void MyClass.test())) – совпадает со всеми точками соединения, встречающимися в потоке выполнения заданного среза;

    //@annotation(MyAnnotation) – выполнение точки соединения, цель которой помечена аннотацией @MyAnnotation.

    //@Aspect
    //public class MethodAspect {
    //
    //    @Before("execution(* me.msvasilets.aop.Application.print(String))")
    //    public void beforePrint(JoinPoint joinPoint) {
    //        System.out.println("Method was called with: " + Arrays.toString(joinPoint.getArgs()));
    //    }
    //
    //    @Around("execution(static * me.msvasilets.aop.*.*(..))")
    //    public Object aroundPrint(ProceedingJoinPoint joinPoint) throws Throwable {
    //        System.out.println(Arrays.toString(joinPoint.getArgs()));
    ////        Object[] args = Arrays.stream(joinPoint.getArgs()).map(s -> "From Aspect: " + s).toArray();
    //        return joinPoint.proceed();
    //    }
    //
    //    @Around("@annotation(me.msvasilets.aop.LoggingAnnotation)")
    //    public Object aroundAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
    //        System.out.println("Method was calles with args: " + Arrays.toString(joinPoint.getArgs()));
    //        Object proceed = joinPoint.proceed();
    //        System.out.println("Method return result" + proceed);
    //        return proceed;
    //    }
    //}

    //        System.out.println("Hello World");
    
    //        User user = new User();
    //
    //        Class<?> aClass = Class.forName("org.example.User");
    //        Class<? extends User> aClass1 = user.getClass();//
    //
    //        Method[] methods = aClass.getMethods();
    //        System.out.println(Arrays.toString(methods));
    //
    //        Method[] declaredMethods = aClass.getDeclaredMethods();
    //        System.out.println(Arrays.toString(declaredMethods));
    //
    //        Field[] declaredFields = aClass.getDeclaredFields();
    //
    //        Method method = aClass.getDeclaredMethod("setName", String.class);
    //
    //        method.setAccessible(true);
    //
    //        System.out.println(user);
    //        method.invoke(user, "Name");
    //        System.out.println(user);
    //
    //        Field field = aClass.getDeclaredField("name");
    //        field.setAccessible(true);
    //
    //        System.out.println(user);
    //        field.set(user, "New Name");
    //        System.out.println(user);
    //
    //        Class<?> superclass = aClass.getSuperclass();
    //        Method[] currentFields = superclass.getDeclaredMethods();
    //        System.out.println(Arrays.toString(currentFields));
    //        System.out.println(superclass);
    //
    //        field.setAccessible(false);
    //
    //        method.setAccessible(false);
