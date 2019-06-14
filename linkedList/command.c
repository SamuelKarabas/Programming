#include <stdlib.h>
#include <string.h>
#include <regex.h>

#include "command.h"

struct command* create_command(char* name, char* description, char* pattern, size_t nmatch) {
    if(name == NULL || description == NULL) {
        return NULL;
    }

    if(strcmp(name, "") == 0 || strcmp(description, "") == 0) {
        return NULL;
    }

    struct command* prikaz = calloc((size_t) 1, sizeof(struct command));
    if(prikaz == NULL) {
        return NULL;
    }

    prikaz->name = name;
    prikaz->description = description;

    if(pattern != NULL) {
        if(regcomp(&prikaz->preg, pattern, REG_ICASE|REG_EXTENDED) != 0) {
            free(prikaz);
            return NULL;
        }
    }
    prikaz->nmatch = nmatch;

    return prikaz;
}

struct command* destroy_command(struct command* command) {
    if(command == NULL) {
        return NULL;
    }

    regfree(&command->preg);
    free(command);

    return NULL;
}
