#include <stdlib.h>
#include "room.h"
#include "world.h"

struct container* create_world() {
    struct container* svet = calloc((size_t) 1, sizeof(struct container));
    if(svet == NULL) {
        return NULL;
    }

    svet->type = ROOM;

    svet->next = NULL;

    struct room* obyvacka = create_room("obyvacka", "Si v obyvacke");
    struct item* gauc = create_item("GAUC", "To ti je na nic", NONE);
    add_item_to_room(obyvacka, gauc);
    set_exits_from_room(obyvacka, NULL, NULL, NULL, NULL);
    svet = add_room_to_world(svet, obyvacka);

    return svet;
}

struct container* add_room_to_world(struct container* world, struct room* room) {
    if(room == NULL) {
        return world;
    }

    if(get_room(world, room->name) == NULL) {
        world = create_container(world, ROOM, room);
    }
    return world;
}

struct container* destroy_world(struct container* world) {
    if(world == NULL) {
        return NULL;
    }

    return destroy_containers(world);
}

struct room* get_room(struct container* world, char* name) {
    return get_from_container_by_name(world, name);
}
