/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.dotnet.test

import jetbrains.buildServer.dotnet.DotnetConstants
import jetbrains.buildServer.dotnet.dotnet.BuildArgumentsProvider
import jetbrains.buildServer.dotnet.dotnet.PackArgumentsProvider
import jetbrains.buildServer.dotnet.dotnet.PublishArgumentsProvider
import jetbrains.buildServer.dotnet.dotnet.RestoreArgumentsProvider
import org.testng.Assert
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

/**
 * @author Dmitry.Tretyakov
 * *         Date: 1/19/2016
 * *         Time: 4:01 PM
 */
class DotnetRunnerBuildServiceTest {

    @Test(dataProvider = "testBuildArgumentsData")
    fun testBuildArguments(parameters: Map<String, String>, arguments: List<String>) {
        val argumentsProvider = BuildArgumentsProvider()
        val result = argumentsProvider.getArguments(parameters)

        Assert.assertEquals(result, arguments)
    }

    @Test(dataProvider = "testRestoreArgumentsData")
    fun testRestoreArguments(parameters: Map<String, String>, arguments: List<String>) {
        val argumentsProvider = RestoreArgumentsProvider()
        val result = argumentsProvider.getArguments(parameters)

        Assert.assertEquals(result, arguments)
    }

    @Test(dataProvider = "testPublishArgumentsData")
    fun testPublishArguments(parameters: Map<String, String>, arguments: List<String>) {
        val argumentsProvider = PublishArgumentsProvider()
        val result = argumentsProvider.getArguments(parameters)

        Assert.assertEquals(result, arguments)
    }

    @Test(dataProvider = "testPackArgumentsData")
    fun testPackArguments(parameters: Map<String, String>, arguments: List<String>) {
        val argumentsProvider = PackArgumentsProvider()
        val result = argumentsProvider.getArguments(parameters)

        Assert.assertEquals(result, arguments)
    }

    @DataProvider(name = "testBuildArgumentsData")
    fun testBuildArgumentsData(): Array<Array<Any>> {
        return arrayOf(
                arrayOf(mapOf(Pair(DotnetConstants.PARAM_PATHS, "path/")), listOf("build", "path/")),
                arrayOf(mapOf(
                        Pair(DotnetConstants.PARAM_BUILD_FRAMEWORK, "dnxcore50"),
                        Pair(DotnetConstants.PARAM_BUILD_CONFIG, "Release")),
                        listOf("build", "--framework", "dnxcore50", "--configuration", "Release")),
                arrayOf(mapOf(
                        Pair(DotnetConstants.PARAM_BUILD_OUTPUT, "output/"),
                        Pair(DotnetConstants.PARAM_ARGUMENTS, "--quiet")),
                        listOf("build", "--output", "output/", "--quiet")))
    }

    @DataProvider(name = "testRestoreArgumentsData")
    fun testRestoreArgumentsData(): Array<Array<Any>> {
        return arrayOf(
                arrayOf(mapOf(Pair(DotnetConstants.PARAM_PATHS, "path/")), listOf("restore", "path/")),
                arrayOf(mapOf(
                        Pair(DotnetConstants.PARAM_RESTORE_PACKAGES, "packages/"),
                        Pair(DotnetConstants.PARAM_RESTORE_PARALLEL, "false")),
                        listOf("restore", "--packages", "packages/")),
                arrayOf(mapOf(Pair(DotnetConstants.PARAM_RESTORE_PARALLEL, "true")), listOf("restore", "--disable-parallel")))
    }

    @DataProvider(name = "testPublishArgumentsData")
    fun testPublishArgumentsData(): Array<Array<Any>> {
        return arrayOf(
                arrayOf(mapOf(Pair(DotnetConstants.PARAM_PATHS, "path/")), listOf("publish", "path/")),
                arrayOf(mapOf(
                        Pair(DotnetConstants.PARAM_PUBLISH_FRAMEWORK, "dotcore"),
                        Pair(DotnetConstants.PARAM_PUBLISH_CONFIG, "Release")),
                        listOf("publish", "--framework", "dotcore", "--configuration", "Release")),
                arrayOf(mapOf(
                        Pair(DotnetConstants.PARAM_PUBLISH_RUNTIME, "active"),
                        Pair(DotnetConstants.PARAM_PUBLISH_NATIVE, "true")),
                        listOf("publish", "--runtime", "active", "--native-subdirectory")))
    }

    @DataProvider(name = "testPackArgumentsData")
    fun testPackArgumentsData(): Array<Array<Any>> {
        return arrayOf(
                arrayOf(mapOf(Pair(DotnetConstants.PARAM_PATHS, "path/")), listOf("pack", "path/")),
                arrayOf(mapOf(Pair(DotnetConstants.PARAM_PACK_CONFIG, "Release")), listOf("pack", "--configuration", "Release")),
                arrayOf(mapOf(
                        Pair(DotnetConstants.PARAM_PACK_OUTPUT, "output/"),
                        Pair(DotnetConstants.PARAM_ARGUMENTS, "--quiet")),
                        listOf("pack", "--output", "output/", "--quiet")))
    }
}
