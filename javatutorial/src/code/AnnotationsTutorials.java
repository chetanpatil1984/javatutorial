/*
 * Class: AnnotationsTutorials Created on Apr 6, 2013 (c) Chetan Subhash Patil,
 * unpublished work, All use, disclosure, and/or reproduction of this material
 * is prohibited unless authorized in writing. All Rights Reserved. Rights in
 * this program belong to: Chetan Subhash Patil
 */
package code;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

/**
 * <tt>AnnotationsTutorials</tt> ...
 */
// Annotation
@MyAnnotationType(version = 1.1, clients = { "Programs" })
public class AnnotationsTutorials
{
    public void method1()
    {
    }
}

class AnnotationsTutorials1 extends AnnotationsTutorials
{
    public static void main(String[] args)
    {
        AnnotationsTutorials1 m = new AnnotationsTutorials1();
        m.method1();
    }
    
    // Annotation provided by java
    // annotation tells the compiler to suppress specific warnings that it would
    // otherwise generate.
    @SuppressWarnings({ "unchecked", "deprecation" })
    // annotation informs the compiler that the element is meant to override an
    // element declared in a superclass.
    @Override
    // annotation indicates that the marked element is deprecated and should no
    // longer be used.
    @Deprecated
    public void method1()
    {
        List l;
    }
}

// The filename should be named same as public class or interface
// meta annotation - annotation to annotation
@Documented
@Inherited
// public enum ElementType {
// TYPE, // Class, interface, or enum (but not annotation)
// FIELD, // Field (including enumerated values)
// METHOD, // Method (does not include constructors)
// PARAMETER, // Method parameter
// CONSTRUCTOR, // Constructor
// LOCAL_VARIABLE, // Local variable or catch clause
// ANNOTATION_TYPE, // Annotation Types (meta-annotations)
// PACKAGE // Java package
// }
@Target({ ElementType.FIELD, ElementType.TYPE })
// public enum RetentionPolicy {
// SOURCE, // Annotation is discarded by the compiler
// CLASS, // Annotation is stored in the class file, but ignored by the VM
// RUNTIME // Annotation is stored in the class file and read by the VM
// }
@Retention(RetentionPolicy.SOURCE)
// simple annotation type
@interface MyAnnotationType {
    
    public enum MyEnum {
        ONE, TWO, THREE
    };
    
    // Annotation declaration should start with an 'at' sign like @, following
    // with an interface keyword, following with the annotation name.
    // Method declarations should not have any parameters.
    // Method declarations should not have any throws clauses.
    // Return types of the method should be one of the following:
    // primitives,String,Class,enum,array of the above types
    
    // string type
    String author() default "n/a";
    
    // array type
    String[] clients() default { "Programs", "GUI", "TestJig" };
    
    // enum
    MyEnum update() default MyEnum.ONE;
    
    // double type
    double version() default 1.0;
    
}