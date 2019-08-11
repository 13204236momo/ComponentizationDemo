package com.example.componentizationdemo.compiler;

import com.example.componentizationdemo.annotation.ARouter;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

// AutoService则是固定的写法，加个注解即可
// 通过auto-service中的@AutoService可以自动生成AutoService注解处理器，用来注册
// 用来生成 META-INF/services/javax.annotation.processing.Processor 文件

@AutoService(Processor.class)
// 允许/支持的注解类型，让注解处理器处理
@SupportedAnnotationTypes("com.example.componentizationdemo.annotation.ARouter")
// 指定JDK编译版本
@SupportedSourceVersion(SourceVersion.RELEASE_7)
@SupportedOptions("content")
public class ARouterProcessor extends AbstractProcessor {

    //操作Element工具类
    private Elements elementsUtils;
    //type(信息类)工具类
    private Types typesUtils;
    //用来输出警告和错误日志
    private Messager messager;
    //文件生成器 类/资源，Filter用来创建新的源文件，class文件以及辅助文件
    private Filer filer;

    // 该方法主要用于一些初始化的操作，通过该方法的参数ProcessingEnvironment可以获取一些列有用的工具类
    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        elementsUtils = processingEnvironment.getElementUtils();
        typesUtils = processingEnvironment.getTypeUtils();
        messager = processingEnvironment.getMessager();
        filer = processingEnvironment.getFiler();

        String options = processingEnvironment.getOptions().get("content");
        messager.printMessage(Diagnostic.Kind.NOTE, options);
    }

    /**
     * 相当于main函数，开始处理注解
     * 注解处理器的核心方法，处理具体的注解，生成Java文件
     *
     * @param set              使用了支持处理注解的节点集合（类 上面写了注解）
     * @param roundEnvironment 当前或是之前的运行环境,可以通过该对象查找找到的注解。
     * @return true 表示后续处理器不会再处理（已经处理完成）
     */

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        if (set.isEmpty()) {
            return false;
        }

        //获取项目中所有使用了ARouter注解的节点
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(ARouter.class);
        //遍历所有节点
        for (Element element : elements) {
            //类节点之上就是包节点
            String packageName = elementsUtils.getPackageOf(element).getQualifiedName().toString();
            //获取简单类名
            String className = element.getSimpleName().toString();
            messager.printMessage(Diagnostic.Kind.NOTE, "被注解的类有" + className);
            //最终我们想要生成的类文件，如：MainActivity$$ARouter
            String finalClassName = className + "$$ARouter";

//            try {
//                JavaFileObject sourceFile = filer.createSourceFile(packageName+"."+finalClassName);
//                Writer writer = sourceFile.openWriter();
//                // 设置包名
//                writer.write("package " + packageName + ";\n");
//
//                writer.write("public class " + finalClassName + " {\n");
//
//                writer.write("public static Class<?> findTargetClass(String path) {\n");
//
//                // 获取类之上@ARouter注解的path值
//                ARouter aRouter = element.getAnnotation(ARouter.class);
//
//                writer.write("if (path.equalsIgnoreCase(\"" + aRouter.path() + "\")) {\n");
//
//                writer.write("return " + className + ".class;\n}\n");
//
//                writer.write("return null;\n");
//
//                writer.write("}\n}");
//
//                // 最后结束别忘了
//                writer.close();
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            //高级写法，javapoet构建工具
            try {
                //获取类之上@ARouter注解的path值
                ARouter aRouter = element.getAnnotation(ARouter.class);
                //构建方法体
                MethodSpec method = MethodSpec.methodBuilder("finalTargetClass")
                        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
                        .returns(Class.class)
                        .addParameter(String.class, "path")
                        .addStatement("return path.equals($S) ? $T.class : null",
                                aRouter.path(), ClassName.get((TypeElement) element))
                        .build();
                //构建类
                TypeSpec type = TypeSpec.classBuilder(finalClassName)
                        .addModifiers(Modifier.PUBLIC)
                        .addMethod(method) //添加方法体
                        .build();
                //在指定的包名下，生成java类文件
                JavaFile javaFile = JavaFile.builder(packageName, type)
                        .build();
                javaFile.writeTo(filer);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return true;
    }
}
