#include <stdlib.h>
#include "room.h"
#include "command.h"
#include "backpack.h"
#include "parser.h"
#include "container.h"
#include "game.h"
#include "world.h"

void play_game(struct game* game) {
    return;
}

struct game* create_game() { 
    struct game* hra = calloc((size_t) 1, sizeof(struct game));
    if(hra == NULL) {
        return NULL;
    }

    hra->state = PLAYING;
    hra->world = create_world();
    hra->current_room = hra->world->room;
    hra->backpack = create_backpack(50);
    hra->parser = create_parser();

    return hra;
}

struct game* destroy_game(struct game* game) {
    if(game == NULL) {
        return NULL;
    }

    destroy_world(game->world);
    destroy_parser(game->parser);
    destroy_backpack(game->backpack);
    free(game);
    return NULL;
}

void execute_command(struct game* game, struct command* command) {
    return;
}
