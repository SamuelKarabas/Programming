#include <stdbool.h>
#include <stdlib.h>

#include "backpack.h"
#include "item.h"
#include "container.h"

struct backpack* create_backpack(const int capacity) {
    struct backpack* batoh = calloc((size_t) 1, sizeof(struct backpack));
    if(batoh == NULL) {
        return NULL;
    }

    batoh->capacity = capacity;
    batoh->size = 0;
    
    return batoh;
}

struct backpack* destroy_backpack(struct backpack* backpack) {
    if(backpack == NULL) {
        return NULL;
    }
    destroy_containers(backpack->items);
    free(backpack);
    return NULL;
}

bool add_item_to_backpack(struct backpack* backpack, struct item* item) {
    if(backpack == NULL || item == NULL) {
        return false;
    }

    if(backpack->size == backpack->capacity) {
        return false;
    }

    backpack->items = create_container(backpack->items, ITEM, item);
    backpack->size += 1;

    return true;
}

void delete_item_from_backpack(struct backpack* backpack, struct item* item) {
    if(backpack == NULL || item == NULL) {
        return;
    }

    backpack->items = remove_container(backpack->items, item);
    backpack->size -= 1;
}

struct item* get_item_from_backpack(const struct backpack* backpack, char* name) {
    if(backpack == NULL || name == NULL) {
        return NULL;
    }

    return get_from_container_by_name(backpack->items, name);
}