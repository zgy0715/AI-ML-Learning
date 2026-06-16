package exercises;

import java.lang.reflect.*;

/**
 * 第9章练习题：反射机制
 * 包含：改错题、填空题、设计题
 */
public class Chapter9Exercises {

    public static void main(String[] args) throws Exception {
        System.out.println("========== 第9章练习题 ==========\n");

        // 练习1：代码改错
        System.out.println("--- 练习1：代码改错 ---");
        exercise1_codeError();

        // 练习2：填空题
        System.out.println("\n--- 练习2：填空题 ---");
        exercise2_filling();

        // 练习3：设计题
        System.out.println("\n--- 练习3：设计题 ---");
        exercise3_design();
    }

    /**
     * 练习1：代码改错
     */
    public static void exercise1_codeError() {
        System.out.println("【题目】找出并修复以下代码中的错误：\n");

        // 错误1：类名错误
        System.out.println("错误1：类名错误");
        System.out.println("原代码：");
        System.out.println("  Class<?> clazz = Class.forName(\"String\");");
        System.out.println("分析：需要使用全限定类名");
        System.out.println("修正：Class<?> clazz = Class.forName(\"java.lang.String\");\n");

        // 错误2：访问私有字段未设置可访问
        System.out.println("错误2：访问私有字段未设置可访问");
        System.out.println("原代码：");
        System.out.println("  Field field = clazz.getDeclaredField(\"privateField\");");
        System.out.println("  Object value = field.get(obj);  // IllegalAccessException!");
        System.out.println("分析：访问私有字段需要设置setAccessible(true)");
        System.out.println("修正：field.setAccessible(true); value = field.get(obj);\n");

        // 错误3：方法调用参数类型不匹配
        System.out.println("错误3：方法调用参数类型不匹配");
        System.out.println("原代码：");
        System.out.println("  Method method = clazz.getMethod(\"add\", int.class, int.class);");
        System.out.println("  method.invoke(obj, 10, 20);  // 正确");
        System.out.println("  method.invoke(obj, 10.5, 20.5);  // 参数类型不匹配！");
        System.out.println("分析：invoke参数类型必须与方法签名匹配");
        System.out.println("修正：method.invoke(obj, 10, 20);\n");

        // 实际运行修正后的代码
        System.out.println("【运行修正后的代码】");
        try {
            Class<?> clazz = Class.forName("java.lang.String");
            System.out.println("类名: " + clazz.getName());

            Constructor<?> constructor = clazz.getConstructor(String.class);
            Object str = constructor.newInstance("Hello");
            Method lengthMethod = clazz.getMethod("length");
            int length = (int) lengthMethod.invoke(str);
            System.out.println("字符串长度: " + length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 练习2：填空题
     */
    public static void exercise2_filling() {
        System.out.println("【题目】请补全代码：\n");

        // 填空1：获取Class对象
        System.out.println("填空1：获取Class对象");
        System.out.println("代码：");
        System.out.println("  Class<?> clazz = ______.forName(\"java.lang.String\");  // 第一空");
        System.out.println("答案：第一空填 Class\n");

        // 填空2：获取构造器
        System.out.println("填空2：获取构造器");
        System.out.println("代码：");
        System.out.println("  Constructor<?> constructor = clazz.______(String.class);  // 第二空");
        System.out.println("答案：第二空填 getConstructor\n");

        // 填空3：访问私有字段
        System.out.println("填空3：访问私有字段");
        System.out.println("代码：");
        System.out.println("  Field field = clazz.getDeclaredField(\"name\");");
        System.out.println("  field.______(true);  // 第三空");
        System.out.println("答案：第三空填 setAccessible\n");

        // 实际运行
        System.out.println("\n【运行结果】");
        try {
            Class<?> clazz = Class.forName("chapter9.Person");
            System.out.println("获取Class对象成功: " + clazz.getSimpleName());

            Constructor<?> constructor = clazz.getConstructor(String.class, int.class);
            Object person = constructor.newInstance("测试", 20);
            System.out.println("创建对象成功: " + person);

            Field nameField = clazz.getDeclaredField("name");
            nameField.setAccessible(true);
            String name = (String) nameField.get(person);
            System.out.println("访问私有字段: " + name);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 练习3：设计题
     */
    public static void exercise3_design() {
        System.out.println("【题目】设计一个简单的依赖注入框架");
        System.out.println("要求：");
        System.out.println("1. 使用反射自动创建对象");
        System.out.println("2. 使用注解标记依赖字段");
        System.out.println("3. 自动注入依赖对象\n");

        System.out.println("【参考答案】");
        System.out.println("代码实现：");
        System.out.println("// 自定义注解");
        System.out.println("@Retention(RetentionPolicy.RUNTIME)");
        System.out.println("@Target(ElementType.FIELD)");
        System.out.println("@interface Inject {}");
        System.out.println("");
        System.out.println("// 服务类");
        System.out.println("class UserService {");
        System.out.println("    @Inject");
        System.out.println("    private UserRepository repository;");
        System.out.println("    public void saveUser(String name) {");
        System.out.println("        System.out.println(\"保存用户: \" + name);");
        System.out.println("        repository.save(name);");
        System.out.println("    }");
        System.out.println("}");
        System.out.println("");
        System.out.println("// 依赖仓库");
        System.out.println("class UserRepository {");
        System.out.println("    public void save(String name) {");
        System.out.println("        System.out.println(\"仓库保存: \" + name);");
        System.out.println("    }");
        System.out.println("}");
        System.out.println("");
        System.out.println("// 简单容器");
        System.out.println("class SimpleContainer {");
        System.out.println("    public static <T> T create(Class<T> clazz) throws Exception {");
        System.out.println("        T obj = clazz.getDeclaredConstructor().newInstance();");
        System.out.println("        Field[] fields = clazz.getDeclaredFields();");
        System.out.println("        for (Field field : fields) {");
        System.out.println("            if (field.isAnnotationPresent(Inject.class)) {");
        System.out.println("                field.setAccessible(true);");
        System.out.println("                Class<?> fieldType = field.getType();");
        System.out.println("                Object dependency = create(fieldType);");
        System.out.println("                field.set(obj, dependency);");
        System.out.println("            }");
        System.out.println("        }");
        System.out.println("        return obj;");
        System.out.println("    }");
        System.out.println("}");

        System.out.println("\n【运行结果】");
        try {
            UserService service = SimpleContainer.create(UserService.class);
            service.saveUser("张三");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 * 辅助类（用于练习3的实际运行）
 */
class UserRepository {
    public void save(String name) {
        System.out.println("仓库保存: " + name);
    }
}

class UserService {
    @Inject
    private UserRepository repository;

    public void saveUser(String name) {
        System.out.println("保存用户: " + name);
        repository.save(name);
    }
}

// 简单容器
class SimpleContainer {
    public static <T> T create(Class<T> clazz) throws Exception {
        T obj = clazz.getDeclaredConstructor().newInstance();

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Inject.class)) {
                field.setAccessible(true);
                Class<?> fieldType = field.getType();
                Object dependency = create(fieldType);
                field.set(obj, dependency);
            }
        }
        return obj;
    }
}

// 注解定义
@interface Inject {}
