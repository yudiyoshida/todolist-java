package com.matsugumayudi.todolist.exceptions;

import java.util.List;

public record Exception(
    List<String> error
) {}
