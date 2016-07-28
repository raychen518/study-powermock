package com.raychen518.study.powermock;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * <pre>
 * This class introduces PowerMock by demonstrating PowerMock's features for Mockito using test methods.
 * 
 * +++++++++++++++++
 * Contents
 * +++++++++++++++++
 * Versions
 * Overview
 * Motivation
 * Usages
 * Limitations
 * References
 * 
 * =================
 * Versions
 * =================
 * Library          Version
 * -------------------------------------
 * PowerMock        1.6.5
 * Mockito          1.10.19
 * JUnit            4.12
 * 
 * =================
 * Overview
 * =================
 * PowerMock is a Java framework that allows to perform unit testing on code normally regarded as untestable.
 * 
 * Writing unit tests can be hard and sometimes good design has to be sacrificed for the sole purpose of testability.
 * Often testability corresponds to good design, but this is not always the case.
 * 
 * For example, final classes and methods cannot be used, private methods sometimes need to be protected or unnecessarily moved to a collaborator,
 * static methods should be avoided completely and so on simply because of the limitations of existing frameworks.
 * 
 * PowerMock is a framework that extends other mock libraries such as EasyMock with more powerful capabilities.
 * PowerMock uses a custom classloader and bytecode manipulation to enable mocking of static methods, constructors, final classes and methods, private methods, removal of static initializers and more.
 * 
 * Currently PowerMock supports EasyMock and Mockito.
 * 
 * When writing unit tests it is often useful to bypass encapsulation and therefore PowerMock includes several features that simplifies reflection specifically useful for testing.
 * This allows easy access to internal state, but also simplifies partial and private mocking.
 * 
 * Extension APIs and Supported Test Frameworks
 * PowerMock consists of 2 extension APIs, one for EasyMock and one for Mockito.
 * To use PowerMock, one of the extension APIs should be depended, as well as a test framework.
 * Currently PowerMock supports 2 test frameworks - JUnit and TestNG.
 * 
 * =================
 * Motivation
 * =================
 * PowerMock can be used to solve testing problems that is normally considered difficult or even impossible.
 * 
 * Using PowerMock, it becomes possible to mock static methods, constructors, final classes and methods, private methods, remove static initializers, mock without dependency injection, and more.
 * 
 * PowerMock does these tricks by modifying the bytecode at runtime when the tests are being executed.
 * PowerMock also contains some utilities that provide easier access to the internal state of an object.
 * 
 * Here are some scenarios when PowerMock makes sense.
 *      Using a 3rd Party or Legacy Framework
 *          If communication with the framework is done through static methods...
 *              PowerMock allows to setup expectations for these static methods and simulate the behaviors.
 * 
 *              An Alternative without PowerMock
 *              Wrap all static method calls in a separate class and use dependency injection to mock this object.
 *              This will create an extra layer of unnecessary classes in the application.
 *              However, this can of course be useful if wanting to encapsulate the framework to be able to replace it.
 * 
 *          If the framework requires to subclass classes that contains static initializers or constructors...
 *              PowerMock allows to remove static initializers and suppress constructors.
 *              PowerMock also allows to mock classes inside a signed JAR file, even if the classes are package-private.
 * 
 *              An Alternative without PowerMock
 *              Move all the code into a delegate and let the framework class simply delegate all method calls.
 * 
 *      Design
 *          If some methods have to be private or final...
 *               PowerMock allows to mock both private and final methods.
 * 
 *               An Alternative without PowerMock
 *               Private methods are not an option. Create a new class and move all private methods to this as public.
 *               Use dependency injection and mocking.
 *               This can force to use an unwanted design.
 * 
 *      Performance
 *          If dependency injection is too expensive so static method calls or a static factory instead are used...
 *              PowerMock allows to use static methods for performance and mock.
 * 
 *              An Alternative without PowerMock
 *              Create a singleton for each service and somehow make sure that during a test a special instance is created.
 *              This sometimes involves breaking the singleton pattern and adding the possibility to change the instance.
 * 
 * =================
 * Usages
 * =================
 * Basically, PowerMock provides a class called "PowerMockito" for creating mock/object/class and initiating verification, and expectations,
 * everything else can still use Mockito to setup and verify expectation (e.g. times(), anyInt()).
 * 
 * All usages require @RunWith(PowerMockRunner.class) and @PrepareForTest annotated at class level.
 * 
 * For specific usages, refer to the following test methods.
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * 01. Mocking Static Methods
 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 * 
 * =================
 * Limitations
 * =================
 * - Cannot use code coverage from EclEmma.
 * 
 * =================
 * References
 * =================
 * - PowerMock - Official Website
 *   https://github.com/jayway/powermock
 * </pre>
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ SomeObjectWithStaticMethods.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IntroductionForMockitoTest {

    @Test
    public void test01MockingStaticMethods() throws Exception {
        /**
         * <pre>
         * 01. Mocking Static Methods
         * - Steps to Mock
         *   1. Add the class containing the static methods to test, into the class-level annotation @PrepareForTest.
         *      e.g.
         *          PrepareForTest({ SomeObject.class })
         * 
         *   2. Call the PowerMockito.mockStatic(...) method to mock that class.
         *      e.g.
         *          PowerMockito.mockStatic(SomeObject.class);
         * 
         *      Note: To mock a specific method, use the PowerMockito.spy(...) method instead.
         * 
         *   3. Use the Mockito.when(...) method to setup the expectation.
         *      e.g.
         *          Mockito.when(SomeObject.doSomething(...)).thenReturn(...);
         * 
         *   Note: If needing to mock classes loaded by the Java system/bootstrap classloader (those defined in the java.lang, java.net, etc packages), this approach should be used.
         * 
         * - Verifying the Behaviors
         *   Verification of calling a static method is done in 2 steps, as follows.
         *   1. Call the PowerMockito.verifyStatic(...) method to start the verification.
         *   2. Call the static method to verify to finish the verification.
         * 
         *   e.g.
         *      PowerMockito.verifyStatic();
         *      SomeObject.doSomething();
         * 
         *      PowerMockito.verifyStatic(Mockito.times(3));
         *      SomeObject.doSomething();
         * 
         *      PowerMockito.verifyStatic(Mockito.atLeastOnce());
         *      SomeObject.doSomething();
         * 
         *   Note: It is important to remember that the PowerMockito.verifyStatic() method should be called per each verification.
         * 
         * - Using the Argument Matchers
         *   Argument matchers from Mockito are also applied to PowerMock mocks.
         *   So those argument matchers can still be used in the stubbing and behavior verification.
         * </pre>
         */

        // Without Mocking
        {
            System.out.println("SomeObjectWithStaticMethods.doSomething(): "
                    + SomeObjectWithStaticMethods.doSomething());
        }

        System.out.println();

        // With Mocking
        {
            PowerMockito.mockStatic(SomeObjectWithStaticMethods.class);

            Mockito.when(SomeObjectWithStaticMethods.doSomething()).thenReturn("Some Special Result");

            System.out.println("SomeObjectWithStaticMethods.doSomething(): "
                    + SomeObjectWithStaticMethods.doSomething());
        }

        System.out.println();

        // With Mocking (containing the behavior verification #1)
        {
            PowerMockito.mockStatic(SomeObjectWithStaticMethods.class);

            Mockito.when(SomeObjectWithStaticMethods.doSomething()).thenReturn("Some Special Result");

            System.out.println("SomeObjectWithStaticMethods.doSomething(): "
                    + SomeObjectWithStaticMethods.doSomething());

            PowerMockito.verifyStatic();
            SomeObjectWithStaticMethods.doSomething();
        }

        System.out.println();

        // With Mocking (containing the behavior verification #2)
        {
            PowerMockito.mockStatic(SomeObjectWithStaticMethods.class);

            Mockito.when(SomeObjectWithStaticMethods.doSomething(Mockito.anyInt())).thenReturn("Some Special Result");

            System.out.println("SomeObjectWithStaticMethods.doSomething(123): "
                    + SomeObjectWithStaticMethods.doSomething(123));
            System.out.println("SomeObjectWithStaticMethods.doSomething(456): "
                    + SomeObjectWithStaticMethods.doSomething(456));
            System.out.println("SomeObjectWithStaticMethods.doSomething(789): "
                    + SomeObjectWithStaticMethods.doSomething(789));

            PowerMockito.verifyStatic();
            SomeObjectWithStaticMethods.doSomething(123);

            PowerMockito.verifyStatic();
            SomeObjectWithStaticMethods.doSomething(456);

            PowerMockito.verifyStatic();
            SomeObjectWithStaticMethods.doSomething(789);
        }

        System.out.println();

        // With Mocking (using the argument matchers)
        {
            PowerMockito.mockStatic(SomeObjectWithStaticMethods.class);

            // Using the argument matchers in the stubbing.
            Mockito.when(SomeObjectWithStaticMethods.doSomething(Mockito.anyInt())).thenReturn("Some Special Result");

            System.out.println("SomeObjectWithStaticMethods.doSomething(123): "
                    + SomeObjectWithStaticMethods.doSomething(123));
            System.out.println("SomeObjectWithStaticMethods.doSomething(456): "
                    + SomeObjectWithStaticMethods.doSomething(456));
            System.out.println("SomeObjectWithStaticMethods.doSomething(789): "
                    + SomeObjectWithStaticMethods.doSomething(789));

            // Using the argument matchers in the behavior verification.
            PowerMockito.verifyStatic(Mockito.times(3));
            SomeObjectWithStaticMethods.doSomething(Mockito.anyInt());
        }

        System.out.println();

        // With Mocking
        {
            // PowerMockito.mockStatic(SomeObjectWithStaticMethods.class);

            // PowerMockito.doNothing().when(SomeObjectWithStaticMethods.class, "doSomethingWithoutReturn");
            System.out.println("================================");
            SomeObjectWithStaticMethods.doSomethingWithoutReturn();
            System.out.println("================================");
        }
    }

    @Test
    public void test111() {
        System.out.println("================================");
        SomeObjectWithStaticMethods.doSomethingWithoutReturn();
        System.out.println("================================");
        PowerMockito.mockStatic(SomeObjectWithStaticMethods.class);
        SomeObjectWithStaticMethods.doSomethingWithoutReturn();
        System.out.println("================================");
    }

    // -------------------------------------------------------------------------

    @Test
    public void test99Xxx() {
        /**
         * <pre>
         * 99. Xxx
         * - Xxx xxx xxx.
         * </pre>
         */

        // ...
    }

}
