package com.example.demo;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2022-11-09 23:16
 * @blog <a href="https://blog.csdn.net/next_second">...</a>
 * @github <a href="https://github.com/YoungBear">...</a>
 * @description 代码架构看护类
 */
@AnalyzeClasses(packages = "com.example.demo")
public class CodeGuardArchTest {

    /**
     * infrastructure 不能被其他包引用（常用于不同领域之间的依赖看护）
     */
    @ArchTest
    static final ArchRule infrastructure_should_not_depend_on_others = noClasses().that()
            .resideInAPackage("..infrastructure..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..application..", "..domain..", "..interface..");

    /**
     * 代码分层依赖看护
     */
    @ArchTest
    static final ArchRule codeLayeredArchTest = layeredArchitecture().consideringAllDependencies()
            // 定义 interface 层
            .layer("interface").definedBy("com.example.demo..interface..")
            // 定义 application 层
            .layer("application").definedBy("com.example.demo..application..")
            // 定义 domain 层
            .layer("domain").definedBy("com.example.demo..domain..")
            // 定义 infrastructure 层
            .layer("infrastructure").definedBy("com.example.demo..infrastructure..")
            // interface 层不能被其他层依赖
            .whereLayer("interface").mayNotBeAccessedByAnyLayer()
            // application 层只能被 interface 层依赖
            .whereLayer("application").mayOnlyBeAccessedByLayers("interface")
            // domain 层只能被 interface application 层依赖
            .whereLayer("domain").mayOnlyBeAccessedByLayers("interface","application");
}
