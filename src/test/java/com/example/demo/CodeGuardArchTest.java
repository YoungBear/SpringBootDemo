package com.example.demo;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

/**
 * @author youngbear
 * @email youngbear@aliyun.com
 * @date 2025-04-14 23:21
 * @blog <a href="https://blog.csdn.net/next_second">...</a>
 * @github <a href="https://github.com/YoungBear">...</a>
 * @description ddd 代码架构看护类
 */
@AnalyzeClasses(packages = "com.example.demo", importOptions = {ImportOption.DoNotIncludeTests.class})
public class CodeGuardArchTest {
    /**
     * infrastructure 不能被其他包引用（常用于不同领域之间的依赖看护）
     */
    @ArchTest
    static final ArchRule infrastructure_should_not_depend_on_others = noClasses().that()
            .resideInAPackage("..infrastructure..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..application..", "..domain..", "..api..");

    /**
     * 代码分层依赖看护
     */
    @ArchTest
    static final ArchRule codeLayeredArchTest = layeredArchitecture().consideringAllDependencies()
            // 定义 interface 层
            .layer("api").definedBy("com.example.demo..api..")
            // 定义 application 层
            .layer("application").definedBy("com.example.demo..application..")
            // 定义 domain 层
            .layer("domain").definedBy("com.example.demo..domain..")
            // 定义 infrastructure 层
            .layer("infrastructure").definedBy("com.example.demo..infrastructure..")
            // api 层不能被其他层依赖
            .whereLayer("api").mayNotBeAccessedByAnyLayer()
            // application 层只能被 api 层依赖
            .whereLayer("application").mayOnlyBeAccessedByLayers("api")
            // domain 层只能被 api application 层依赖
            .whereLayer("domain").mayOnlyBeAccessedByLayers("api", "application");
}
