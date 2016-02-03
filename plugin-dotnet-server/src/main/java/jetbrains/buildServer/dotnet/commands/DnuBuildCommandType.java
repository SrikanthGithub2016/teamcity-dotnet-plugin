/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * See LICENSE in the project root for license information.
 */

package jetbrains.buildServer.dotnet.commands;

import jetbrains.buildServer.dotnet.DnuConstants;
import org.jetbrains.annotations.NotNull;

/**
 * Provides parameters for dnu build command.
 */
public class DnuBuildCommandType implements CommandType {
    @NotNull
    @Override
    public String getName() {
        return DnuConstants.COMMAND_BUILD;
    }

    @NotNull
    @Override
    public String getEditPage() {
        return "editBuildParameters.jsp";
    }

    @NotNull
    @Override
    public String getViewPage() {
        return "viewBuildParameters.jsp";
    }
}