#include <stdlib.h>
#include <string.h>
#include "item.h"

struct item* create_item(char* name, char* description, unsigned int properties) {
    if(name == NULL || description == NULL) {
        return NULL;
    }

    if(strcmp(name, "") == 0 || strcmp(description, "") == 0) {
        return NULL;
    }

    struct item* vec = calloc((size_t) 1, sizeof(struct item));
    if(vec == NULL) {
        return NULL;
    }

    vec->name = name;
    vec->description = description;
    vec->properties = properties;

    return vec;
}

struct item* destroy_item(struct item* item) {
    if(item != NULL) {
        free(item);
    }
    return NULL;
}
