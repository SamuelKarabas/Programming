#include <stdlib.h>
#include <string.h>
#include <stdio.h>

#include "item.h"
#include "container.h"
#include "room.h"

struct room* create_room(char *name, char *description) {
    if(name == NULL || description == NULL) {
        return NULL;
    }

    if(strcmp(name, "") == 0 || strcmp(description, "") == 0) {
        return NULL;
    }

    struct room* izba = calloc((size_t) 1, sizeof(struct room));
    if(izba == NULL) {
        return NULL;
    }

    izba->name = name;
    izba->description = description;

    return izba;
}

struct room* destroy_room(struct room* room) {
    if(room == NULL) {
        return NULL;
    }

    destroy_containers(room->items);
    free(room);

    return NULL;
}

void set_exits_from_room(struct room *room, struct room *north, struct room *south, struct room *east, struct room *west) {
    if(room == NULL) {
        return;
    }

    room->north = north;
    room->south = south;
    room->east = east;
    room->west = west;
    
}

void show_room(const struct room* room) {
    if(room == NULL) {
        return;
    }

    printf("%s\nMozne vychody z miestnosti:\n", room->description);
    if(room->north != NULL) printf("sever\n");
    if(room->south != NULL) printf("juh\n");
    if(room->east != NULL) printf("vychod\n");
    if(room->west != NULL) printf("zapad\n");
    printf("Vidis:\n");
    struct container* pomocna = room->items;
    while(pomocna != NULL) {
        printf("%s\n", pomocna->item->name);
        pomocna = pomocna->next;
    }
}

void delete_item_from_room(struct room* room, struct item* item) {
    if(room == NULL || item == NULL) {
        return;
    }

    room->items = remove_container(room->items, item);
}

void add_item_to_room(struct room* room, struct item* item) {
    if(room == NULL || item == NULL) {
        return;
    }

    room->items = create_container(room->items, ITEM, item);
}

struct item* get_item_from_room(const struct room* room, const char* name) {
    if(room == NULL || name == NULL) {
        return NULL;
    }
    struct item* result = get_from_container_by_name(room->items, name);
    return result;
}