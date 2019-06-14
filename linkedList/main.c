#include <stdio.h>
#include <stdlib.h>

#include "game.h"
#include "backpack.h"
#include "item.h"
#include "command.h"
#include "room.h"
#include "parser.h"

int main() {
    struct game* hra = create_game();
    struct item* vec = create_item("VEC", "To by mala byt vec", NONE);
    add_item_to_backpack(hra->backpack, vec);
    get_item_from_backpack(hra->backpack, vec->name);
    delete_item_from_backpack(hra->backpack, vec);
    add_item_to_room(hra->world->room, vec);
    get_item_from_room(hra->world->room, vec->name);
    show_room(hra->world->room);
    delete_item_from_room(hra->world->room, vec);
    destroy_item(vec);
    execute_command(hra, NULL);
    parse_input(hra->parser, NULL);
    play_game(hra);
    destroy_game(hra);
    return 0;
}